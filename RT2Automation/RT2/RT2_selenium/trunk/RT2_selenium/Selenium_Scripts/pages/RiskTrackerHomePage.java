package pages;

import java.util.ArrayList;
import java.util.LinkedHashMap;

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
import libraries.UI_Interaction;

public class RiskTrackerHomePage {

	public static Data_Interaction interact = new Data_Interaction();
	public static ProjectConstants p_constants = new ProjectConstants();
	public static ReadExcel read = new ReadExcel();
	Project project = new Project();
	CommonLib commonLib = new CommonLib();
	static BusinessRules br = new BusinessRules();
	UI_Interaction ui = new UI_Interaction();
	Data_Interaction data_Interaction = new Data_Interaction();
	public static XSSFWorkbook D_workbook;

	public RiskTrackerHomePage(RemoteWebDriver driver, String strConfig, String strUserRole)
			throws InterruptedException {

		CommonLib.getLogger(strConfig).info("Inside Risk Tracker 2.0 HomePage");

		// System.out.println(strConfig);
//		DictionaryObjects.init_ObjTestCaseDetails_DO(strConfig);
//		DictionaryObjects.init_objHomePage_DO(strConfig);
		Data_Interaction.Init_DictionaryObjects(strConfig);
		LinkedHashMap<String, String> ObjTestCaseDetails_DO = DictionaryObjects.getObjTestCaseDetails_DO(strConfig);
		LinkedHashMap<String, String> ObjCurrentScreenDO = DictionaryObjects.getObjHomePage_DO(strConfig);
//		LinkedHashMap<String, String> ObjDefaultDo = Constants.getDefaultDO_HomePage();

		String strTestCaseID = ObjTestCaseDetails_DO.get("strTestCaseID");
		// System.out.println(ObjTestCaseDetails_DO);
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

				if (Value != "") {
					switch (Header) {
					
					case "BR_Verify_HomePage":
						BusinessRules.verifyHomePageDefaultElements(driver, strConfig);
						
						if(strUserRole.contentEquals(Constants.UserRoles.get(0))) {
							BusinessRules.verifyAccountHandlerHomePage(driver, strConfig);
							break;
						}
						
						else if(strUserRole.contentEquals(Constants.UserRoles.get(1))) {
							BusinessRules.verifyAdministratorHomePage(driver, strConfig);
							break;
						}
						
						else if (strUserRole.contentEquals(Constants.UserRoles.get(2))) {
							BusinessRules.verifySeniorInsuranceTechHomePage(driver, strConfig);
							break;
						}

						else if (strUserRole.contentEquals(Constants.UserRoles.get(3))) {
							BusinessRules.verifyOperationsTeamLeadHomePage(driver, strConfig);
							break;
						}

						else if (strUserRole.contentEquals(Constants.UserRoles.get(4))) {
							BusinessRules.verifyAsOperationsTechHomePage(driver, strConfig);
							break;
						}

						else if (strUserRole.contentEquals(Constants.UserRoles.get(5))) {
							BusinessRules.verifyAdministrationsTeamHomePage(driver, strConfig);
							break;
						}
						
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
