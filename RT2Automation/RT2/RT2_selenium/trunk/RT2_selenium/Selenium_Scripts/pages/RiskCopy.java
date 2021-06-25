package pages;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.util.Strings;

import libraries.BusinessRules;
import libraries.CommonLib;
import libraries.Constants;
import libraries.Data_Interaction;
import libraries.DictionaryObjects;
import libraries.Project;
import libraries.ProjectConstants;
import libraries.ReadExcel;
import libraries.TestReporter;
import libraries.UI_Interaction;

public class RiskCopy {

	public static Data_Interaction interact = new Data_Interaction();
	public static ProjectConstants p_constants = new ProjectConstants();
	public static ReadExcel read = new ReadExcel();
	Project project = new Project();
	CommonLib commonLib = new CommonLib();
	static BusinessRules br = new BusinessRules();
	UI_Interaction ui = new UI_Interaction();
	Data_Interaction data_Interaction = new Data_Interaction();
	Constants constants = new Constants();
	public static XSSFWorkbook D_workbook;
	

	public RiskCopy(RemoteWebDriver driver, String strConfig, String strTestingType, String strUserRole)
			throws InterruptedException {

		CommonLib.getLogger(strConfig).info("Inside RiskCopy");

		
		Data_Interaction.Init_DictionaryObjects(strConfig);
		LinkedHashMap<String, String> ObjTestCaseDetails_DO = DictionaryObjects.getObjTestCaseDetails_DO(strConfig);
		LinkedHashMap<String, String> ObjCurrentScreenDO = DictionaryObjects.getObjHomePage_DO(strConfig);
//		LinkedHashMap<String, String> ObjDefaultDo = Constants.getDefaultDO_HomePage();

		String strTestCaseID = ObjTestCaseDetails_DO.get("strTestCaseID");
		String strWorksheetName = "HomePage";
		String strFileNameWithPath = ProjectConstants.TESTDATAEXCEL;

		String strTotalIterations = data_Interaction.ReadDataFromExcel(strConfig, strFileNameWithPath, strWorksheetName,
				strTestCaseID, "iTotalIterations", "1");

		if (strTotalIterations.equals("0") || Strings.isNullOrEmpty(strTotalIterations)) {
			System.out.println("Skipping " + strWorksheetName);
			return;
		}

		int iTotalIterations = Integer.parseInt(strTotalIterations);
//		int iCurrentIteration = 1;

		String strStopTestCaseAfterCurrentScreen = ObjCurrentScreenDO.get("StopTCAfterCurrentScreen");
		System.out.println(ObjCurrentScreenDO);
		if (strStopTestCaseAfterCurrentScreen.equalsIgnoreCase("yes")) {
			br.setStopTestCaseValue(driver, strConfig, strStopTestCaseAfterCurrentScreen);
		}

		ObjTestCaseDetails_DO.put("intTotalIteration", strTotalIterations);
		ObjTestCaseDetails_DO.put("intCurrentIteration", "1");
		DictionaryObjects.setObjTestCaseDetails_DO(strConfig, ObjTestCaseDetails_DO);

		LinkedHashMap<String, String> ObjExcelValuesUpdated = data_Interaction.UpdateDOValues(strConfig,
				ObjCurrentScreenDO, strFileNameWithPath, strWorksheetName);
		DictionaryObjects.setObjHomePage_DO(strConfig, ObjExcelValuesUpdated);
		ObjCurrentScreenDO = DictionaryObjects.getObjHomePage_DO(strConfig);

		ArrayList<String> keysObjCurrentScreenDO = new ArrayList<>(ObjCurrentScreenDO.keySet());
		// ArrayList<String> valueObjCurrentScreenDO = new
		// ArrayList<>(ObjCurrentScreenDO.values());
		ArrayList<String> businessrules = new ArrayList<String>();

		for (int i = 0; i < keysObjCurrentScreenDO.size(); i++) {
			String s = keysObjCurrentScreenDO.get(i);
			// System.out.println(ObjCurrentScreenDO.get(s));
			if (s.charAt(0) == 'B' && s.charAt(1) == 'R' && ObjCurrentScreenDO.get(s).equalsIgnoreCase("true")) {

				businessrules.add(keysObjCurrentScreenDO.get(i));

			}
		}

		D_workbook = constants.getDriverWorkbook();
		
		System.out.println(businessrules);
		for (int j = 1; j <= iTotalIterations; j++) {
			for (int i = 0; i < businessrules.size(); i++) {

				String Header = businessrules.get(i);
				String Value = ObjCurrentScreenDO.get(Header);
				System.out.println("Working on value: " + Header + " with: " + Value);
				
				ArrayList<String> riskCopyData = new ArrayList<String>();
				XSSFSheet riskCopySheet = read.readXLSXfile(ProjectConstants.RTTESTDATAEXCEL, ProjectConstants.RISKCOPYSHEET);
				int rowCountCopy = riskCopySheet.getLastRowNum();
				int colCountCopy = read.columncount(riskCopySheet, 0);
				
				
				if (Value != "") {
					switch (Header) {

					case "BR_SearchRisk_And_Copy":
						if (strUserRole.trim().contentEquals(Constants.UserRole.get(1))) {
							CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_RiskDashboard_AdminHomepage");
							Project.ExecuteRow(driver, strConfig, "LN_RiskDashboard_AdminHomepage", "Click");
						}
						
						if(strTestingType.contentEquals("Regression")) {
							TestReporter.Info(driver, strConfig, "Executing Regression Scenario : ", "verify_CancelBtn_On_CopyRisk_PopUp");
							BusinessRules.verify_CancelBtn_On_CopyRisk_PopUp(driver, strConfig); 
							BusinessRules.navigateToHomePage(driver, strConfig);
							}
						
						CommonLib.waitForLoad(driver, strConfig);
						if (rowCountCopy > 2) {
							for (int k = 3; k <= rowCountCopy; k++) {
								for (int l = 0; l < colCountCopy; l++) {

									riskCopyData.add(l, (read.CellValue(riskCopySheet, k, l)));
									System.out.println(riskCopyData.get(l));

								}
								int nullCounter = 0;
								if (!riskCopyData.isEmpty()) {
									for (int m = 0; m < riskCopyData.size(); m++) {
										if (riskCopyData.get(m) == null) {
											nullCounter = nullCounter + 1;
										}
									}

									if (!(nullCounter == riskCopyData.size())) {
										Boolean riskFound = false;

										riskFound = BusinessRules.searchRisk(driver, strConfig, riskCopyData.get(0),
												true);
										if (riskFound) {
											BusinessRules.copyRisk(driver, strConfig, strTestingType, riskCopyData);
											BusinessRules.navigateToHomePage(driver, strConfig);
										}
										riskCopyData.clear();
									}
								}
								riskCopyData.clear();
							}
						} else {
							CommonLib.getLogger(strConfig)
									.info("WARNING: No Risk Data supplied for Copy in RTTestdata.xlsx file");
							TestReporter.Warning(driver, strConfig, "Copy Risk",
									"No Risk Data supplied for Copy in RTTestdata.xlsx file");

						}
						break;

					default:
						break;
					}
				} else {
					break;
				}
			}
		}
	}

}







