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

public class RiskReinstate {

	public static Data_Interaction interact = new Data_Interaction();
	public static ProjectConstants p_constants = new ProjectConstants();
	public static ReadExcel read = new ReadExcel();
	Project project = new Project();
	CommonLib commonLib = new CommonLib();
	static BusinessRules br = new BusinessRules();
	UI_Interaction ui = new UI_Interaction();
	Data_Interaction data_Interaction = new Data_Interaction();
	public static XSSFWorkbook D_workbook;

	public RiskReinstate(RemoteWebDriver driver, String strConfig, String strTestingType, String strUserRole)
			throws InterruptedException {

		CommonLib.getLogger(strConfig).info("Inside RiskNTU");

		
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

		System.out.println(businessrules);
		for (int j = 1; j <= iTotalIterations; j++) {
			for (int i = 0; i < businessrules.size(); i++) {

				String Header = businessrules.get(i);
				String Value = ObjCurrentScreenDO.get(Header);
				System.out.println("Working on value: " + Header + " with: " + Value);
				
				ArrayList<String> riskReinstateData = new ArrayList<String>();
				XSSFSheet riskReinstateSheet = read.readXLSXfile(ProjectConstants.RTTESTDATAEXCEL, ProjectConstants.RISKREINSTATESHEET);
				int rowCountREINSTATE = riskReinstateSheet.getLastRowNum();
				int colCountREINSTATE = read.columncount(riskReinstateSheet, 0);
				
				if (Value != "") {
					switch (Header) {

					case "BR_SearchRisk_And_Reinstate":
						if (strUserRole.trim().contentEquals(Constants.UserRole.get(1))) {
							CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_RiskDashboard_AdminHomepage");
							Project.ExecuteRow(driver, strConfig, "LN_RiskDashboard_AdminHomepage", "Click");
						}
						CommonLib.waitForLoad(driver, strConfig);
						if (rowCountREINSTATE > 2) {
							for (int k = 3; k <= rowCountREINSTATE; k++) {
								for (int l = 0; l < colCountREINSTATE; l++) {

									riskReinstateData.add(l, (read.CellValue(riskReinstateSheet, k, l)));
									System.out.println(riskReinstateData.get(l));

								}
								int nullCounter = 0;
								if (!riskReinstateData.isEmpty()) {
									for (int m = 0; m < riskReinstateData.size(); m++) {
										if (riskReinstateData.get(m) == null) {
											nullCounter = nullCounter + 1;
										}
									}

									if (!(nullCounter == riskReinstateData.size())) {
										Boolean riskFound = false;
										riskFound = BusinessRules.searchRisk(driver, strConfig,
												riskReinstateData.get(0), true);
										if (riskFound) {
											BusinessRules.reinstateRisk(driver, strConfig, strTestingType, riskReinstateData);
											BusinessRules.navigateToHomePage(driver, strConfig);
										}
										riskReinstateData.clear();
									}
								}
								riskReinstateData.clear();
							}
						} else {
							CommonLib.getLogger(strConfig)
									.info("WARNING: No Risk Data supplied for Risk Reinstate in RTTestdata.xlsx file");
							TestReporter.Warning(driver, strConfig, "NTU Risk",
									"No Risk Data supplied for Reinstate in RTTestdata.xlsx file");

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




