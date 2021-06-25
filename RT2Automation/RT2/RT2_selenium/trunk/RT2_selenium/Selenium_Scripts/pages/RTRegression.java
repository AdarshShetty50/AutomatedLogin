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

public class RTRegression {

	public static Data_Interaction interact = new Data_Interaction();
	public static ProjectConstants p_constants = new ProjectConstants();
	public static ReadExcel read = new ReadExcel();
	Project project = new Project();
	CommonLib commonLib = new CommonLib();
	static BusinessRules br = new BusinessRules();
	UI_Interaction ui = new UI_Interaction();
	Data_Interaction data_Interaction = new Data_Interaction();
	public static XSSFWorkbook D_workbook;

	public RTRegression(RemoteWebDriver driver, String strConfig, String strUserRole)
			throws InterruptedException {

		CommonLib.getLogger(strConfig).info("Inside RTRegression");

		
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

				ArrayList<String> regressionData = new ArrayList<String>();
				XSSFSheet regressionSheet = read.readXLSXfile(ProjectConstants.RTTESTDATAEXCEL, ProjectConstants.REGRESSIONSHEET);
				int rowCountRegression = regressionSheet.getLastRowNum();
				int colCountRegression = read.columncount(regressionSheet, 0);
				
				ArrayList<String> settingsData = new ArrayList<String>();
				XSSFSheet settingsSheet = read.readXLSXfile(ProjectConstants.RTTESTDATAEXCEL, ProjectConstants.SETTINGSSHEET);
				int rowCountSettings = settingsSheet.getLastRowNum();
				int colCountSettings = read.columncount(settingsSheet, 0);
				
				
				System.out.println("rowCountRiskDetails"+rowCountRegression);
				if (Value != "") {
					switch (Header) {

					case "BR_Verify_RiskOverview_And_RiskDetails_Info_OnDashboard_RiskCard":
						if (strUserRole.trim().contentEquals(Constants.UserRole.get(1))) {
							CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_RiskDashboard_AdminHomepage");
							Project.ExecuteRow(driver, strConfig, "LN_RiskDashboard_AdminHomepage", "Click");
						}
						
						
						CommonLib.waitForLoad(driver, strConfig);
						if (rowCountRegression > 2) {
							for (int k = 3; k <= rowCountRegression; k++) {
								for (int l = 0; l < colCountRegression; l++) {

									regressionData.add(l, (read.CellValue(regressionSheet, k, l)));
									System.out.println(regressionData.get(l));

								}
								int nullCounter = 0;
								if (!regressionData.isEmpty()) {
									for (int m = 0; m < regressionData.size(); m++) {
										if (regressionData.get(m) == null) {
											nullCounter = nullCounter + 1;
										}
									}

									if (!(nullCounter == regressionData.size())) {
										Boolean riskFound = false;
										int regTCCounter =0;
										
										if(regressionData.get(0).trim().contentEquals(strTestCaseID)) {
										riskFound = BusinessRules.searchRisk(driver, strConfig, regressionData.get(1),
												true);
										if (riskFound) {
											BusinessRules.verify_RiskOverview_And_RiskDetails_Info_OnDashboard_RiskCard(driver, strConfig, regressionData.get(1));
											
										}
										
										else {
											CommonLib.getLogger(strConfig)
											.info("WARNING: Skipping Execution of TC: "+ strTestCaseID +" as Risk supplied in RTTestdata.xlsx file was not found in Risk Tracker Application");
											TestReporter.Warning(driver, strConfig, "Skipping Execution of TC: "+ strTestCaseID,
											"Risk supplied in RTTestdata.xlsx file was not found in Risk Tracker Application");
										}
									}
										
										else {
											regTCCounter = regTCCounter+1;
											if(regTCCounter == rowCountRegression) {
											CommonLib.getLogger(strConfig)
											.info("WARNING: TCID in RTTestdata.xlsx file does not match with that in the Driver.xlsx file");
											TestReporter.Warning(driver, strConfig, "TCID Mismatch",
											"TCID in RTTestdata.xlsx file does not match with that in the Driver.xlsx file");
										}}
										regressionData.clear();
									}
								}
								regressionData.clear();
							}
						} else {
							CommonLib.getLogger(strConfig)
									.info("WARNING: No Risk Data supplied for Risk Details in RTTestdata.xlsx file");
							TestReporter.Warning(driver, strConfig, "NTU Risk",
									"No Risk Data supplied for Risk Details in RTTestdata.xlsx file");

						}
						
						break;
						
						
					case "BR_Verify_CancelBtn_On_RiskOverview_Tab":

						BusinessRules.verify_CancelBtn_On_RiskOverviewTab(driver, strConfig);
						break;
						
						
					case "BR_Verify_RiskOverview_Tab_MandatoryFields":

						BusinessRules.verify_RiskOverviewTab_MandatoryFields(driver, strConfig);
						BusinessRules.navigateToHomePage(driver, strConfig);
						break;
						
						
					case "BR_Verify_CancelBtn_On_RiskDetails_Tab":
						if (strUserRole.trim().contentEquals(Constants.UserRole.get(1))) {
							CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_RiskDashboard_AdminHomepage");
							Project.ExecuteRow(driver, strConfig, "LN_RiskDashboard_AdminHomepage", "Click");
						}
						
						
						CommonLib.waitForLoad(driver, strConfig);
						if (rowCountRegression > 2) {
							for (int k = 3; k <= rowCountRegression; k++) {
								for (int l = 0; l < colCountRegression; l++) {

									regressionData.add(l, (read.CellValue(regressionSheet, k, l)));
									System.out.println(regressionData.get(l));

								}
								int nullCounter = 0;
								if (!regressionData.isEmpty()) {
									for (int m = 0; m < regressionData.size(); m++) {
										if (regressionData.get(m) == null) {
											nullCounter = nullCounter + 1;
										}
									}

									if (!(nullCounter == regressionData.size())) {
										Boolean riskFound = false;
										int regTCCounter =0;
										
										if(regressionData.get(0).trim().contentEquals(strTestCaseID)) {
										riskFound = BusinessRules.searchRisk(driver, strConfig, regressionData.get(1),
												true);
										if (riskFound) {
											BusinessRules.verify_CancelBtn_On_RiskDetailsTab(driver, strConfig);
											
										}
										
										else {
											CommonLib.getLogger(strConfig)
											.info("WARNING: Skipping Execution of TC: "+ strTestCaseID +" as Risk supplied in RTTestdata.xlsx file was not found in Risk Tracker Application");
											TestReporter.Warning(driver, strConfig, "Skipping Execution of TC: "+ strTestCaseID,
											"Risk supplied in RTTestdata.xlsx file was not found in Risk Tracker Application");
										}
									}
										
										else {
											regTCCounter = regTCCounter+1;
											if(regTCCounter == rowCountRegression) {
											CommonLib.getLogger(strConfig)
											.info("WARNING: TCID in RTTestdata.xlsx file does not match with that in the Driver.xlsx file");
											TestReporter.Warning(driver, strConfig, "TCID Mismatch",
											"TCID in RTTestdata.xlsx file does not match with that in the Driver.xlsx file");
										
											}}
										regressionData.clear();
									}
								}
								regressionData.clear();
							}
						} else {
							CommonLib.getLogger(strConfig)
									.info("WARNING: No Risk Data supplied for Risk Details in RTTestdata.xlsx file");
							TestReporter.Warning(driver, strConfig, "NTU Risk",
									"No Risk Data supplied for Risk Details in RTTestdata.xlsx file");

						}
						
						break;
						
						
						
					
					case "BR_Verify_RiskDetails_Tab_MandatoryFields":
						
						if (strUserRole.trim().contentEquals(Constants.UserRole.get(1))) {
							CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_RiskDashboard_AdminHomepage");
							Project.ExecuteRow(driver, strConfig, "LN_RiskDashboard_AdminHomepage", "Click");
						}
						
						
						CommonLib.waitForLoad(driver, strConfig);
						if (rowCountRegression > 2) {
							for (int k = 3; k <= rowCountRegression; k++) {
								for (int l = 0; l < colCountRegression; l++) {

									regressionData.add(l, (read.CellValue(regressionSheet, k, l)));
									System.out.println(regressionData.get(l));

								}
								int nullCounter = 0;
								if (!regressionData.isEmpty()) {
									for (int m = 0; m < regressionData.size(); m++) {
										if (regressionData.get(m) == null) {
											nullCounter = nullCounter + 1;
										}
									}

									if (!(nullCounter == regressionData.size())) {
										Boolean riskFound = false;
										int regTCCounter =0;
										if(regressionData.get(0).trim().contentEquals(strTestCaseID)) {
										riskFound = BusinessRules.searchRisk(driver, strConfig, regressionData.get(1),
												true);
										if (riskFound) {
											BusinessRules.verify_RiskDetails_MandatoryFields(driver, strConfig);
											BusinessRules.navigateToHomePage(driver, strConfig);
										}
										
										else {
											CommonLib.getLogger(strConfig)
											.info("WARNING: Skipping Execution of TC: "+ strTestCaseID +" as Risk supplied in RTTestdata.xlsx file was not found in Risk Tracker Application");
											TestReporter.Warning(driver, strConfig, "Skipping Execution of TC: "+ strTestCaseID,
											"Risk supplied in RTTestdata.xlsx file was not found in Risk Tracker Application");
										}
									}
										
										else {
											
											regTCCounter = regTCCounter+1;
											if(regTCCounter == rowCountRegression) {
											CommonLib.getLogger(strConfig)
											.info("WARNING: TCID in RTTestdata.xlsx file does not match with that in the Driver.xlsx file");
											TestReporter.Warning(driver, strConfig, "TCID Mismatch",
											"TCID in RTTestdata.xlsx file does not match with that in the Driver.xlsx file");
										
											}}
										regressionData.clear();
									}
								}
								regressionData.clear();
							}
						} else {
							CommonLib.getLogger(strConfig)
									.info("WARNING: No Risk Data supplied for Risk Details in RTTestdata.xlsx file");
							TestReporter.Warning(driver, strConfig, "NTU Risk",
									"No Risk Data supplied for Risk Details in RTTestdata.xlsx file");

						}

						break;
						
						
					case "BR_Verify_ClientCommision_RequiredValues_ErrorMessage":

						if (strUserRole.trim().contentEquals(Constants.UserRole.get(1))) {
							CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_RiskDashboard_AdminHomepage");
							Project.ExecuteRow(driver, strConfig, "LN_RiskDashboard_AdminHomepage", "Click");
						}
						
						
						CommonLib.waitForLoad(driver, strConfig);
						if (rowCountRegression > 2) {
							for (int k = 3; k <= rowCountRegression; k++) {
								for (int l = 0; l < colCountRegression; l++) {

									regressionData.add(l, (read.CellValue(regressionSheet, k, l)));
									System.out.println(regressionData.get(l));

								}
								int nullCounter = 0;
								if (!regressionData.isEmpty()) {
									for (int m = 0; m < regressionData.size(); m++) {
										if (regressionData.get(m) == null) {
											nullCounter = nullCounter + 1;
										}
									}

									if (!(nullCounter == regressionData.size())) {
										Boolean riskFound = false;

										int regTCCounter = 0;
										if(regressionData.get(0).trim().contentEquals(strTestCaseID)) {
										riskFound = BusinessRules.searchRisk(driver, strConfig, regressionData.get(1),
												true);
										if (riskFound) {
											BusinessRules.verify_ClientCommision_RequiredValues_ErrorMessage(driver, strConfig);
											BusinessRules.navigateToHomePage(driver, strConfig);

										}
										
										else {

											CommonLib.getLogger(strConfig).info("WARNING: Skipping Execution of TC: "
													+ strTestCaseID
													+ " as Risk supplied in RTTestdata.xlsx file was not found in Risk Tracker Application");
											TestReporter.Warning(driver, strConfig,
													"Skipping Execution of TC: " + strTestCaseID,
													"Risk supplied in RTTestdata.xlsx file was not found in Risk Tracker Application");

										}
									}
										
										else {
											
											regTCCounter = regTCCounter+1;
											if(regTCCounter == rowCountRegression) {
											CommonLib.getLogger(strConfig)
											.info("WARNING: TCID in RTTestdata.xlsx file does not match with that in the Driver.xlsx file");
											TestReporter.Warning(driver, strConfig, "TCID Mismatch",
											"TCID in RTTestdata.xlsx file does not match with that in the Driver.xlsx file");
										
											}}
										regressionData.clear();
									}
								}
								regressionData.clear();
							}
						} else {
							CommonLib.getLogger(strConfig)
									.info("WARNING: No Risk Data supplied for Risk Details in RTTestdata.xlsx file");
							TestReporter.Warning(driver, strConfig, "NTU Risk",
									"No Risk Data supplied for Risk Details in RTTestdata.xlsx file");

						}

						break;
						
						
					case "BR_Verify_CancelBtn_On_FirmOrder_PopUp":

						if (strUserRole.trim().contentEquals(Constants.UserRole.get(1))) {
							CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_RiskDashboard_AdminHomepage");
							Project.ExecuteRow(driver, strConfig, "LN_RiskDashboard_AdminHomepage", "Click");
						}
						
						
						CommonLib.waitForLoad(driver, strConfig);
						if (rowCountRegression > 2) {
							for (int k = 3; k <= rowCountRegression; k++) {
								for (int l = 0; l < colCountRegression; l++) {

									regressionData.add(l, (read.CellValue(regressionSheet, k, l)));
									System.out.println(regressionData.get(l));

								}
								int nullCounter = 0;
								if (!regressionData.isEmpty()) {
									for (int m = 0; m < regressionData.size(); m++) {
										if (regressionData.get(m) == null) {
											nullCounter = nullCounter + 1;
										}
									}

									if (!(nullCounter == regressionData.size())) {
										Boolean riskFound = false;

										int regTCCounter = 0;
										if(regressionData.get(0).trim().contentEquals(strTestCaseID)) {
										riskFound = BusinessRules.searchRisk(driver, strConfig, regressionData.get(1),
												true);
										if (riskFound) {
											BusinessRules.verify_CancelBtn_On_FirmOrder_PopUp(driver, strConfig);
											BusinessRules.navigateToHomePage(driver, strConfig);

										}
										
										else {

											CommonLib.getLogger(strConfig).info("WARNING: Skipping Execution of TC: "
													+ strTestCaseID
													+ " as Risk supplied in RTTestdata.xlsx file was not found in Risk Tracker Application");
											TestReporter.Warning(driver, strConfig,
													"Skipping Execution of TC: " + strTestCaseID,
													"Risk supplied in RTTestdata.xlsx file was not found in Risk Tracker Application");

										}
									}
										
										else {
											
											regTCCounter = regTCCounter+1;
											if(regTCCounter == rowCountRegression) {
											CommonLib.getLogger(strConfig)
											.info("WARNING: TCID in RTTestdata.xlsx file does not match with that in the Driver.xlsx file");
											TestReporter.Warning(driver, strConfig, "TCID Mismatch",
											"TCID in RTTestdata.xlsx file does not match with that in the Driver.xlsx file");
										
											}}
										regressionData.clear();
									}
								}
								regressionData.clear();
							}
						} else {
							CommonLib.getLogger(strConfig)
									.info("WARNING: No Risk Data supplied for Risk Details in RTTestdata.xlsx file");
							TestReporter.Warning(driver, strConfig, "NTU Risk",
									"No Risk Data supplied for Risk Details in RTTestdata.xlsx file");

						}

						break;
						
						
					case "BR_Verify_FirmOrder_MandatoryFields":

						if (strUserRole.trim().contentEquals(Constants.UserRole.get(1))) {
							CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_RiskDashboard_AdminHomepage");
							Project.ExecuteRow(driver, strConfig, "LN_RiskDashboard_AdminHomepage", "Click");
						}
						
						
						CommonLib.waitForLoad(driver, strConfig);
						if (rowCountRegression > 2) {
							for (int k = 3; k <= rowCountRegression; k++) {
								for (int l = 0; l < colCountRegression; l++) {

									regressionData.add(l, (read.CellValue(regressionSheet, k, l)));
									System.out.println(regressionData.get(l));

								}
								int nullCounter = 0;
								if (!regressionData.isEmpty()) {
									for (int m = 0; m < regressionData.size(); m++) {
										if (regressionData.get(m) == null) {
											nullCounter = nullCounter + 1;
										}
									}

									if (!(nullCounter == regressionData.size())) {
										Boolean riskFound = false;

										int regTCCounter = 0;
										if(regressionData.get(0).trim().contentEquals(strTestCaseID)) {
										riskFound = BusinessRules.searchRisk(driver, strConfig, regressionData.get(1),
												true);
										if (riskFound) {
											BusinessRules.verify_FirmOrder_MandatoryFields(driver, strConfig);
											BusinessRules.navigateToHomePage(driver, strConfig);

										}
										
										else {

											CommonLib.getLogger(strConfig).info("WARNING: Skipping Execution of TC: "
													+ strTestCaseID
													+ " as Risk supplied in RTTestdata.xlsx file was not found in Risk Tracker Application");
											TestReporter.Warning(driver, strConfig,
													"Skipping Execution of TC: " + strTestCaseID,
													"Risk supplied in RTTestdata.xlsx file was not found in Risk Tracker Application");

										}
									}
										
										else {
											
											regTCCounter = regTCCounter+1;
											if(regTCCounter == rowCountRegression) {
											CommonLib.getLogger(strConfig)
											.info("WARNING: TCID in RTTestdata.xlsx file does not match with that in the Driver.xlsx file");
											TestReporter.Warning(driver, strConfig, "TCID Mismatch",
											"TCID in RTTestdata.xlsx file does not match with that in the Driver.xlsx file");
										
											}}
										regressionData.clear();
									}
								}
								regressionData.clear();
							}
						} else {
							CommonLib.getLogger(strConfig)
									.info("WARNING: No Risk Data supplied for Risk Details in RTTestdata.xlsx file");
							TestReporter.Warning(driver, strConfig, "NTU Risk",
									"No Risk Data supplied for Risk Details in RTTestdata.xlsx file");

						}

						break;
						
					case "BR_Verify_CancelBtn_On_SubmitToBrokerOps_PopUp":

						if (strUserRole.trim().contentEquals(Constants.UserRole.get(1))) {
							CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_RiskDashboard_AdminHomepage");
							Project.ExecuteRow(driver, strConfig, "LN_RiskDashboard_AdminHomepage", "Click");
						}
						
						
						CommonLib.waitForLoad(driver, strConfig);
						if (rowCountRegression > 2) {
							for (int k = 3; k <= rowCountRegression; k++) {
								for (int l = 0; l < colCountRegression; l++) {

									regressionData.add(l, (read.CellValue(regressionSheet, k, l)));
									System.out.println(regressionData.get(l));

								}
								int nullCounter = 0;
								if (!regressionData.isEmpty()) {
									for (int m = 0; m < regressionData.size(); m++) {
										if (regressionData.get(m) == null) {
											nullCounter = nullCounter + 1;
										}
									}

									if (!(nullCounter == regressionData.size())) {
										Boolean riskFound = false;

										int regTCCounter = 0;
										if(regressionData.get(0).trim().contentEquals(strTestCaseID)) {
										riskFound = BusinessRules.searchRisk(driver, strConfig, regressionData.get(1),
												true);
										if (riskFound) {
											BusinessRules.verify_CancelBtn_On_SubmitToBrokerOps_PopUp(driver, strConfig); 
											BusinessRules.navigateToHomePage(driver, strConfig);

										}
										
										else {

											CommonLib.getLogger(strConfig).info("WARNING: Skipping Execution of TC: "
													+ strTestCaseID
													+ " as Risk supplied in RTTestdata.xlsx file was not found in Risk Tracker Application");
											TestReporter.Warning(driver, strConfig,
													"Skipping Execution of TC: " + strTestCaseID,
													"Risk supplied in RTTestdata.xlsx file was not found in Risk Tracker Application");

										}
									}
										
										else {
											
											regTCCounter = regTCCounter+1;
											if(regTCCounter == rowCountRegression) {
											CommonLib.getLogger(strConfig)
											.info("WARNING: TCID in RTTestdata.xlsx file does not match with that in the Driver.xlsx file");
											TestReporter.Warning(driver, strConfig, "TCID Mismatch",
											"TCID in RTTestdata.xlsx file does not match with that in the Driver.xlsx file");
										
											}}
										regressionData.clear();
									}
								}
								regressionData.clear();
							}
						} else {
							CommonLib.getLogger(strConfig)
									.info("WARNING: No Risk Data supplied for Risk Details in RTTestdata.xlsx file");
							TestReporter.Warning(driver, strConfig, "NTU Risk",
									"No Risk Data supplied for Risk Details in RTTestdata.xlsx file");

						}

						break;
						
					case "BR_Verify_CancelBtn_On_NTUPopUp":

						if (strUserRole.trim().contentEquals(Constants.UserRole.get(1))) {
							CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_RiskDashboard_AdminHomepage");
							Project.ExecuteRow(driver, strConfig, "LN_RiskDashboard_AdminHomepage", "Click");
						}
						
						
						CommonLib.waitForLoad(driver, strConfig);
						if (rowCountRegression > 2) {
							for (int k = 3; k <= rowCountRegression; k++) {
								for (int l = 0; l < colCountRegression; l++) {

									regressionData.add(l, (read.CellValue(regressionSheet, k, l)));
									System.out.println(regressionData.get(l));

								}
								int nullCounter = 0;
								if (!regressionData.isEmpty()) {
									for (int m = 0; m < regressionData.size(); m++) {
										if (regressionData.get(m) == null) {
											nullCounter = nullCounter + 1;
										}
									}

									if (!(nullCounter == regressionData.size())) {
										Boolean riskFound = false;

										int regTCCounter = 0;
										if(regressionData.get(0).trim().contentEquals(strTestCaseID)) {
										riskFound = BusinessRules.searchRisk(driver, strConfig, regressionData.get(1),
												true);
										if (riskFound) {
											BusinessRules.verify_CancelBtn_onNTUPopUp( driver,  strConfig); 
											BusinessRules.navigateToHomePage(driver, strConfig);

										}
										
										else {

											CommonLib.getLogger(strConfig).info("WARNING: Skipping Execution of TC: "
													+ strTestCaseID
													+ " as Risk supplied in RTTestdata.xlsx file was not found in Risk Tracker Application");
											TestReporter.Warning(driver, strConfig,
													"Skipping Execution of TC: " + strTestCaseID,
													"Risk supplied in RTTestdata.xlsx file was not found in Risk Tracker Application");

										}
									}
										
										else {
											
											regTCCounter = regTCCounter+1;
											if(regTCCounter == rowCountRegression) {
											CommonLib.getLogger(strConfig)
											.info("WARNING: TCID in RTTestdata.xlsx file does not match with that in the Driver.xlsx file");
											TestReporter.Warning(driver, strConfig, "TCID Mismatch",
											"TCID in RTTestdata.xlsx file does not match with that in the Driver.xlsx file");
										
											}}
										regressionData.clear();
									}
								}
								regressionData.clear();
							}
						} else {
							CommonLib.getLogger(strConfig)
									.info("WARNING: No Risk Data supplied for Risk Details in RTTestdata.xlsx file");
							TestReporter.Warning(driver, strConfig, "NTU Risk",
									"No Risk Data supplied for Risk Details in RTTestdata.xlsx file");

						}

						break;
						
						
					case "BR_Verify_Fields_BeforeAndAfter_DirectInsured_SwitchedON":
						
						if (strUserRole.trim().contentEquals(Constants.UserRole.get(1))) {
							CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_RiskDashboard_AdminHomepage");
							Project.ExecuteRow(driver, strConfig, "LN_RiskDashboard_AdminHomepage", "Click");
						}
						
						BusinessRules.verify_Fields_BeforeAndAfter_DirectInsured_SwitchedON(driver, strConfig);
						BusinessRules.navigateToHomePage(driver, strConfig);
						break;
						
					case "BR_Verify_Fields_BeforeAndAfter_Reinsured_SwitchedON":

						if (strUserRole.trim().contentEquals(Constants.UserRole.get(1))) {
							CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_RiskDashboard_AdminHomepage");
							Project.ExecuteRow(driver, strConfig, "LN_RiskDashboard_AdminHomepage", "Click");
						}
						
						BusinessRules.verify_Fields_BeforeAndAfter_Reinsured_SwitchedON(driver, strConfig);
						BusinessRules.navigateToHomePage(driver, strConfig);
						break;
					
						
					case "BR_Change_DefaultActive_Organization":

						if (strUserRole.trim().contentEquals(Constants.UserRole.get(1))) {
							CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_RiskDashboard_AdminHomepage");
							Project.ExecuteRow(driver, strConfig, "LN_RiskDashboard_AdminHomepage", "Click");
						}
						
						
						
						CommonLib.waitForLoad(driver, strConfig);
						if (rowCountSettings > 2) {
							for (int k = 3; k <= rowCountSettings; k++) {
								for (int l = 0; l < colCountSettings; l++) {

									settingsData.add(l, (read.CellValue(settingsSheet, k, l)));
									System.out.println(settingsData.get(l));

								}
								int nullCounter = 0;
								if (!settingsData.isEmpty()) {
									for (int m = 0; m < settingsData.size(); m++) {
										if (settingsData.get(m) == null) {
											nullCounter = nullCounter + 1;
										}
									}

									if (!(nullCounter == settingsData.size())) {

										int regTCCounter = 0;
										if (settingsData.get(0).trim().contentEquals(strTestCaseID)) {

											if (settingsData.get(1) != null || !(settingsData.get(1).isEmpty()))
												BusinessRules.change_DefaultActive_Organization(driver, strConfig,
														settingsData.get(1));

											else {
												TestReporter.Warning(driver, strConfig,
														"Skipping Execution of TC: " + strTestCaseID,
														"No value supplied for 'Default Active Organization' in the 'User Settings' sheet of RTTestdata.xlsx");
											}
										}

										else {

											regTCCounter = regTCCounter + 1;
											if (regTCCounter == rowCountRegression) {
												TestReporter.Warning(driver, strConfig, "TCID Mismatch",
														"TCID in RTTestdata.xlsx file does not match with that in the Driver.xlsx file");

											}
										}
										regressionData.clear();
									}
								}
								regressionData.clear();
							}
						} else {
							
							TestReporter.Warning(driver, strConfig, "User Settings Test Scenario",
									"No Settings Data supplied in the 'User Settings' sheet of RTTestdata.xlsx");

						}

						break;
						
						
					case "BR_Change_DefaultActive_Division":

						if (strUserRole.trim().contentEquals(Constants.UserRole.get(1))) {
							CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_RiskDashboard_AdminHomepage");
							Project.ExecuteRow(driver, strConfig, "LN_RiskDashboard_AdminHomepage", "Click");
						}
						
						CommonLib.waitForLoad(driver, strConfig);
						if (rowCountSettings > 2) {
							for (int k = 3; k <= rowCountSettings; k++) {
								for (int l = 0; l < colCountSettings; l++) {

									settingsData.add(l, (read.CellValue(settingsSheet, k, l)));
									System.out.println(settingsData.get(l));

								}
								int nullCounter = 0;
								if (!settingsData.isEmpty()) {
									for (int m = 0; m < settingsData.size(); m++) {
										if (settingsData.get(m) == null) {
											nullCounter = nullCounter + 1;
										}
									}

									if (!(nullCounter == settingsData.size())) {

										int regTCCounter = 0;
										if (settingsData.get(0).trim().contentEquals(strTestCaseID)) {

											if (settingsData.get(2) != null || !(settingsData.get(2).isEmpty()))
												BusinessRules.change_DefaultActive_Division(driver, strConfig,
														settingsData.get(2));

											else {
												TestReporter.Warning(driver, strConfig,
														"Skipping Execution of TC: " + strTestCaseID,
														"No value supplied for 'Default Active Division' in the 'User Settings' sheet of RTTestdata.xlsx");
											}
										}

										else {

											regTCCounter = regTCCounter + 1;
											if (regTCCounter == rowCountRegression) {
												TestReporter.Warning(driver, strConfig, "TCID Mismatch",
														"TCID in RTTestdata.xlsx file does not match with that in the Driver.xlsx file");

											}
										}
										settingsData.clear();
									}
								}
								settingsData.clear();
							}
						} else {
							
							TestReporter.Warning(driver, strConfig, "User Settings Test Scenario",
									"No Settings Data supplied in the 'User Settings' sheet of RTTestdata.xlsx");

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



