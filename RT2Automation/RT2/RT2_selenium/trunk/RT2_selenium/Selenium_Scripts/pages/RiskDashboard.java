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

public class RiskDashboard {

	public static Data_Interaction interact = new Data_Interaction();
	public static ProjectConstants p_constants = new ProjectConstants();
	public static ReadExcel read = new ReadExcel();
	Project project = new Project();
	CommonLib commonLib = new CommonLib();
	static BusinessRules br = new BusinessRules();
	UI_Interaction ui = new UI_Interaction();
	Data_Interaction data_Interaction = new Data_Interaction();
	public static XSSFWorkbook D_workbook;

	public RiskDashboard(RemoteWebDriver driver, String strConfig, String strTestingType,  String strUserRole)
			throws InterruptedException {

		CommonLib.getLogger(strConfig).info("Inside RiskDashboard");

		
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
				ArrayList<String> riskDashboardData = new ArrayList<String>();
				XSSFSheet riskDashboardSheet = read.readXLSXfile(ProjectConstants.RTTESTDATAEXCEL, ProjectConstants.RISKDASHBOARDSHEET);
				int rowCount = riskDashboardSheet.getLastRowNum();
				int colCount = read.columncount(riskDashboardSheet, 0);

				if (Value != "") {
					switch (Header) {
					
					case "BR_SearchRisk_DefaultView":
						if (strUserRole.trim().contentEquals(Constants.UserRole.get(1))) {
							CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_RiskDashboard_AdminHomepage");
							Project.ExecuteRow(driver, strConfig, "LN_RiskDashboard_AdminHomepage", "Click");
						}
						CommonLib.waitForLoad(driver, strConfig);
						if (rowCount > 2) {
							for (int k = 3; k <= rowCount; k++) {
								for (int l = 0; l < colCount; l++) {

									riskDashboardData.add(l, (read.CellValue(riskDashboardSheet, k, l)));
								}

								int nullCounter = 0;
								if (!riskDashboardData.isEmpty()) {
									for (int m = 0; m < riskDashboardData.size(); m++) {
										if (riskDashboardData.get(m) == null) {
											nullCounter = nullCounter + 1;
										}
									}

									if (!(nullCounter == riskDashboardData.size())) {
										// Set OpenRiskCard = False since we are only searching for a Risk and not doing
										// any operation
										BusinessRules.searchRisk(driver, strConfig, riskDashboardData.get(0), false);
										riskDashboardData.clear();
									}
								}
								riskDashboardData.clear();
							}
						} else {
							CommonLib.getLogger(strConfig)
									.info("WARNING: No Risk Dashboard Data supplied in RTTestdata.xlsx file");
							TestReporter.Warning(driver, strConfig, "Risk Dashboard",
									"No Risk Dashboard Data supplied in RTTestdata.xlsx file");

						}

						break;

					case "BR_Verify_Dashboard_Filter_Limit":

						if (strUserRole.trim().contentEquals(Constants.UserRole.get(1))) {
							CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_RiskDashboard_AdminHomepage");
							Project.ExecuteRow(driver, strConfig, "LN_RiskDashboard_AdminHomepage", "Click");
						}
						
						
						CommonLib.waitForLoad(driver, strConfig);
						if (rowCount> 2) {
							for (int k = 3; k <= rowCount; k++) {
								for (int l = 0; l < colCount; l++) {

									riskDashboardData.add(l, (read.CellValue(riskDashboardSheet, k, l)));
									System.out.println(riskDashboardData.get(l));

								}
								int nullCounter = 0;
								if (!riskDashboardData.isEmpty()) {
									for (int m = 0; m < riskDashboardData.size(); m++) {
										if (riskDashboardData.get(m) == null) {
											nullCounter = nullCounter + 1;
										}
									}

									if (!(nullCounter == riskDashboardData.size())) {
										Boolean riskFound = false;

										int regTCCounter = 0;
										if(riskDashboardData.get(0).trim().contentEquals(strTestCaseID)) {
										riskFound = BusinessRules.searchRisk(driver, strConfig, riskDashboardData.get(1),
												true);
										if (riskFound) {
											BusinessRules.verify_DashboardFilter_Limit(driver, strConfig, riskDashboardData.get(2), riskDashboardData.get(3));
											

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
											if(regTCCounter == rowCount) {
											CommonLib.getLogger(strConfig)
											.info("WARNING: TCID in RTTestdata.xlsx file does not match with that in the Driver.xlsx file");
											TestReporter.Warning(driver, strConfig, "TCID Mismatch",
											"TCID in RTTestdata.xlsx file does not match with that in the Driver.xlsx file");
										
											}}
										riskDashboardData.clear();
									}
								}
								riskDashboardData.clear();
							}
						} else {
							CommonLib.getLogger(strConfig)
									.info("WARNING: No Risk Data supplied for Risk Details in RTTestdata.xlsx file");
							TestReporter.Warning(driver, strConfig, "NTU Risk",
									"No Risk Data supplied for Risk Details in RTTestdata.xlsx file");

						}

						break;
						
						
						
					case "BR_Verify_Dashboard_RiskCard_HeaderColor_AsPer_RiskStatus":

						if (strUserRole.trim().contentEquals(Constants.UserRole.get(1))) {
							CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_RiskDashboard_AdminHomepage");
							Project.ExecuteRow(driver, strConfig, "LN_RiskDashboard_AdminHomepage", "Click");
						}
						
						
						CommonLib.waitForLoad(driver, strConfig);
						if (rowCount> 2) {
							for (int k = 3; k <= rowCount; k++) {
								for (int l = 0; l < colCount; l++) {

									riskDashboardData.add(l, (read.CellValue(riskDashboardSheet, k, l)));
									System.out.println(riskDashboardData.get(l));

								}
								int nullCounter = 0;
								if (!riskDashboardData.isEmpty()) {
									for (int m = 0; m < riskDashboardData.size(); m++) {
										if (riskDashboardData.get(m) == null) {
											nullCounter = nullCounter + 1;
										}
									}

									if (!(nullCounter == riskDashboardData.size())) {
										Boolean riskFound = false;

										int regTCCounter = 0;
										if(riskDashboardData.get(0).trim().contentEquals(strTestCaseID)) {
										riskFound = BusinessRules.searchRisk(driver, strConfig, riskDashboardData.get(1),
												false);
										if (riskFound) {
											BusinessRules.verify_Dashboard_RiskCard_HeaderColor_AsPer_RiskStatus(driver, strConfig, riskDashboardData.get(1));
											

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
											if(regTCCounter == rowCount) {
											CommonLib.getLogger(strConfig)
											.info("WARNING: TCID in RTTestdata.xlsx file does not match with that in the Driver.xlsx file");
											TestReporter.Warning(driver, strConfig, "TCID Mismatch",
											"TCID in RTTestdata.xlsx file does not match with that in the Driver.xlsx file");
										
											}}
										riskDashboardData.clear();
									}
								}
								riskDashboardData.clear();
							}
						} else {
							CommonLib.getLogger(strConfig)
									.info("WARNING: No Risk Data supplied for Risk Details in RTTestdata.xlsx file");
							TestReporter.Warning(driver, strConfig, "NTU Risk",
									"No Risk Data supplied for Risk Details in RTTestdata.xlsx file");

						}

						break;
						
						
					case "BR_Verify_Dashboard_RiskCard_HeaderColor_ForStatus_InNegotiation_For_NewRisk":

						if (strUserRole.trim().contentEquals(Constants.UserRole.get(1))) {
							CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_RiskDashboard_AdminHomepage");
							Project.ExecuteRow(driver, strConfig, "LN_RiskDashboard_AdminHomepage", "Click");
						}
						
						
						CommonLib.waitForLoad(driver, strConfig);
						if (rowCount> 2) {
							for (int k = 3; k <= rowCount; k++) {
								for (int l = 0; l < colCount; l++) {

									riskDashboardData.add(l, (read.CellValue(riskDashboardSheet, k, l)));
									System.out.println(riskDashboardData.get(l));

								}
								int nullCounter = 0;
								if (!riskDashboardData.isEmpty()) {
									for (int m = 0; m < riskDashboardData.size(); m++) {
										if (riskDashboardData.get(m) == null) {
											nullCounter = nullCounter + 1;
										}
									}

									if (!(nullCounter == riskDashboardData.size())) {
										Boolean riskFound = false;

										int regTCCounter = 0;
										if(riskDashboardData.get(0).trim().contentEquals(strTestCaseID)) {
										riskFound = BusinessRules.searchRisk(driver, strConfig, riskDashboardData.get(1),
												false);
										if (riskFound) {
											BusinessRules.verify_Dashboard_RiskCard_HeaderColor_ForStatus_InNegotiation_For_NewRisk(driver, strConfig, riskDashboardData.get(1));
											

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
											if(regTCCounter == rowCount) {
											CommonLib.getLogger(strConfig)
											.info("WARNING: TCID in RTTestdata.xlsx file does not match with that in the Driver.xlsx file");
											TestReporter.Warning(driver, strConfig, "TCID Mismatch",
											"TCID in RTTestdata.xlsx file does not match with that in the Driver.xlsx file");
										
											}}
										riskDashboardData.clear();
									}
								}
								riskDashboardData.clear();
							}
						} else {
							CommonLib.getLogger(strConfig)
									.info("WARNING: No Risk Data supplied for Risk Details in RTTestdata.xlsx file");
							TestReporter.Warning(driver, strConfig, "NTU Risk",
									"No Risk Data supplied for Risk Details in RTTestdata.xlsx file");

						}

						break;
						
						
					case "BR_Verify_Dashboard_RiskCard_HeaderColor_ForStatus_InNegotiation_For_RiskDetails_InfoSaved":

						if (strUserRole.trim().contentEquals(Constants.UserRole.get(1))) {
							CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_RiskDashboard_AdminHomepage");
							Project.ExecuteRow(driver, strConfig, "LN_RiskDashboard_AdminHomepage", "Click");
						}
						
						
						CommonLib.waitForLoad(driver, strConfig);
						if (rowCount> 2) {
							for (int k = 3; k <= rowCount; k++) {
								for (int l = 0; l < colCount; l++) {

									riskDashboardData.add(l, (read.CellValue(riskDashboardSheet, k, l)));
									System.out.println(riskDashboardData.get(l));

								}
								int nullCounter = 0;
								if (!riskDashboardData.isEmpty()) {
									for (int m = 0; m < riskDashboardData.size(); m++) {
										if (riskDashboardData.get(m) == null) {
											nullCounter = nullCounter + 1;
										}
									}

									if (!(nullCounter == riskDashboardData.size())) {
										Boolean riskFound = false;

										int regTCCounter = 0;
										if(riskDashboardData.get(0).trim().contentEquals(strTestCaseID)) {
										riskFound = BusinessRules.searchRisk(driver, strConfig, riskDashboardData.get(1),
												false);
										if (riskFound) {
											BusinessRules.verify_Dashboard_RiskCard_HeaderColor_ForStatus_InNegotiation_For_RiskDetails_InfoSaved(driver, strConfig, riskDashboardData.get(1));
											

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
											if(regTCCounter == rowCount) {
											CommonLib.getLogger(strConfig)
											.info("WARNING: TCID in RTTestdata.xlsx file does not match with that in the Driver.xlsx file");
											TestReporter.Warning(driver, strConfig, "TCID Mismatch",
											"TCID in RTTestdata.xlsx file does not match with that in the Driver.xlsx file");
										
											}}
										riskDashboardData.clear();
									}
								}
								riskDashboardData.clear();
							}
						} else {
							CommonLib.getLogger(strConfig)
									.info("WARNING: No Risk Data supplied for Risk Details in RTTestdata.xlsx file");
							TestReporter.Warning(driver, strConfig, "NTU Risk",
									"No Risk Data supplied for Risk Details in RTTestdata.xlsx file");

						}

						break;
						
						
					case "BR_Verify_Dashboard_RiskCard_HeaderColor_ForStatus_NBI":

						if (strUserRole.trim().contentEquals(Constants.UserRole.get(1))) {
							CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_RiskDashboard_AdminHomepage");
							Project.ExecuteRow(driver, strConfig, "LN_RiskDashboard_AdminHomepage", "Click");
						}
						
						
						CommonLib.waitForLoad(driver, strConfig);
						if (rowCount> 2) {
							for (int k = 3; k <= rowCount; k++) {
								for (int l = 0; l < colCount; l++) {

									riskDashboardData.add(l, (read.CellValue(riskDashboardSheet, k, l)));
									System.out.println(riskDashboardData.get(l));

								}
								int nullCounter = 0;
								if (!riskDashboardData.isEmpty()) {
									for (int m = 0; m < riskDashboardData.size(); m++) {
										if (riskDashboardData.get(m) == null) {
											nullCounter = nullCounter + 1;
										}
									}

									if (!(nullCounter == riskDashboardData.size())) {
										Boolean riskFound = false;

										int regTCCounter = 0;
										if(riskDashboardData.get(0).trim().contentEquals(strTestCaseID)) {
										riskFound = BusinessRules.searchRisk(driver, strConfig, riskDashboardData.get(1),
												false);
										if (riskFound) {
											BusinessRules.verify_Dashboard_RiskCard_HeaderColor_ForStatus_NBI(driver, strConfig, riskDashboardData.get(1));
											

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
											if(regTCCounter == rowCount) {
											CommonLib.getLogger(strConfig)
											.info("WARNING: TCID in RTTestdata.xlsx file does not match with that in the Driver.xlsx file");
											TestReporter.Warning(driver, strConfig, "TCID Mismatch",
											"TCID in RTTestdata.xlsx file does not match with that in the Driver.xlsx file");
										
											}}
										riskDashboardData.clear();
									}
								}
								riskDashboardData.clear();
							}
						} else {
							CommonLib.getLogger(strConfig)
									.info("WARNING: No Risk Data supplied for Risk Details in RTTestdata.xlsx file");
							TestReporter.Warning(driver, strConfig, "NTU Risk",
									"No Risk Data supplied for Risk Details in RTTestdata.xlsx file");

						}

						break;
						
						
					case "BR_Verify_Dashboard_RiskCard_HeaderColor_ForStatus_Quote":

						if (strUserRole.trim().contentEquals(Constants.UserRole.get(1))) {
							CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_RiskDashboard_AdminHomepage");
							Project.ExecuteRow(driver, strConfig, "LN_RiskDashboard_AdminHomepage", "Click");
						}
						
						
						CommonLib.waitForLoad(driver, strConfig);
						if (rowCount> 2) {
							for (int k = 3; k <= rowCount; k++) {
								for (int l = 0; l < colCount; l++) {

									riskDashboardData.add(l, (read.CellValue(riskDashboardSheet, k, l)));
									System.out.println(riskDashboardData.get(l));

								}
								int nullCounter = 0;
								if (!riskDashboardData.isEmpty()) {
									for (int m = 0; m < riskDashboardData.size(); m++) {
										if (riskDashboardData.get(m) == null) {
											nullCounter = nullCounter + 1;
										}
									}

									if (!(nullCounter == riskDashboardData.size())) {
										Boolean riskFound = false;

										int regTCCounter = 0;
										if(riskDashboardData.get(0).trim().contentEquals(strTestCaseID)) {
										riskFound = BusinessRules.searchRisk(driver, strConfig, riskDashboardData.get(1),
												false);
										if (riskFound) {
											BusinessRules.verify_Dashboard_RiskCard_HeaderColor_ForStatus_Quote(driver, strConfig, riskDashboardData.get(1));
											

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
											if(regTCCounter == rowCount) {
											CommonLib.getLogger(strConfig)
											.info("WARNING: TCID in RTTestdata.xlsx file does not match with that in the Driver.xlsx file");
											TestReporter.Warning(driver, strConfig, "TCID Mismatch",
											"TCID in RTTestdata.xlsx file does not match with that in the Driver.xlsx file");
										
											}}
										riskDashboardData.clear();
									}
								}
								riskDashboardData.clear();
							}
						} else {
							CommonLib.getLogger(strConfig)
									.info("WARNING: No Risk Data supplied for Risk Details in RTTestdata.xlsx file");
							TestReporter.Warning(driver, strConfig, "NTU Risk",
									"No Risk Data supplied for Risk Details in RTTestdata.xlsx file");

						}

						break;
						
						
					case "BR_Verify_Dashboard_RiskCard_HeaderColor_ForStatus_QuoteOrNBI":

						if (strUserRole.trim().contentEquals(Constants.UserRole.get(1))) {
							CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_RiskDashboard_AdminHomepage");
							Project.ExecuteRow(driver, strConfig, "LN_RiskDashboard_AdminHomepage", "Click");
						}
						
						
						CommonLib.waitForLoad(driver, strConfig);
						if (rowCount> 2) {
							for (int k = 3; k <= rowCount; k++) {
								for (int l = 0; l < colCount; l++) {

									riskDashboardData.add(l, (read.CellValue(riskDashboardSheet, k, l)));
									System.out.println(riskDashboardData.get(l));

								}
								int nullCounter = 0;
								if (!riskDashboardData.isEmpty()) {
									for (int m = 0; m < riskDashboardData.size(); m++) {
										if (riskDashboardData.get(m) == null) {
											nullCounter = nullCounter + 1;
										}
									}

									if (!(nullCounter == riskDashboardData.size())) {
										Boolean riskFound = false;

										int regTCCounter = 0;
										if(riskDashboardData.get(0).trim().contentEquals(strTestCaseID)) {
										riskFound = BusinessRules.searchRisk(driver, strConfig, riskDashboardData.get(1),
												false);
										if (riskFound) {
											BusinessRules.verify_Dashboard_RiskCard_HeaderColor_ForStatus_QuoteOrNBI(driver, strConfig, riskDashboardData.get(1));
											
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
											if(regTCCounter == rowCount) {
											CommonLib.getLogger(strConfig)
											.info("WARNING: TCID in RTTestdata.xlsx file does not match with that in the Driver.xlsx file");
											TestReporter.Warning(driver, strConfig, "TCID Mismatch",
											"TCID in RTTestdata.xlsx file does not match with that in the Driver.xlsx file");
										
											}}
										riskDashboardData.clear();
									}
								}
								riskDashboardData.clear();
							}
						} else {
							CommonLib.getLogger(strConfig)
									.info("WARNING: No Risk Data supplied for Risk Details in RTTestdata.xlsx file");
							TestReporter.Warning(driver, strConfig, "NTU Risk",
									"No Risk Data supplied for Risk Details in RTTestdata.xlsx file");

						}

						break;
						
						
					case "BR_Verify_Dashboard_RiskCard_HeaderColor_ForStatus_Policy":

						if (strUserRole.trim().contentEquals(Constants.UserRole.get(1))) {
							CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_RiskDashboard_AdminHomepage");
							Project.ExecuteRow(driver, strConfig, "LN_RiskDashboard_AdminHomepage", "Click");
						}
						
						
						CommonLib.waitForLoad(driver, strConfig);
						if (rowCount> 2) {
							for (int k = 3; k <= rowCount; k++) {
								for (int l = 0; l < colCount; l++) {

									riskDashboardData.add(l, (read.CellValue(riskDashboardSheet, k, l)));
									System.out.println(riskDashboardData.get(l));

								}
								int nullCounter = 0;
								if (!riskDashboardData.isEmpty()) {
									for (int m = 0; m < riskDashboardData.size(); m++) {
										if (riskDashboardData.get(m) == null) {
											nullCounter = nullCounter + 1;
										}
									}

									if (!(nullCounter == riskDashboardData.size())) {
										Boolean riskFound = false;

										int regTCCounter = 0;
										if(riskDashboardData.get(0).trim().contentEquals(strTestCaseID)) {
										riskFound = BusinessRules.searchRisk(driver, strConfig, riskDashboardData.get(1),
												false);
										if (riskFound) {
											BusinessRules.verify_Dashboard_RiskCard_HeaderColor_ForStatus_Policy(driver, strConfig, riskDashboardData.get(1));
											

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
											if(regTCCounter == rowCount) {
											CommonLib.getLogger(strConfig)
											.info("WARNING: TCID in RTTestdata.xlsx file does not match with that in the Driver.xlsx file");
											TestReporter.Warning(driver, strConfig, "TCID Mismatch",
											"TCID in RTTestdata.xlsx file does not match with that in the Driver.xlsx file");
										
											}}
										riskDashboardData.clear();
									}
								}
								riskDashboardData.clear();
							}
						} else {
							CommonLib.getLogger(strConfig)
									.info("WARNING: No Risk Data supplied for Risk Details in RTTestdata.xlsx file");
							TestReporter.Warning(driver, strConfig, "NTU Risk",
									"No Risk Data supplied for Risk Details in RTTestdata.xlsx file");

						}

						break;
						
						
					case "BR_Verify_Dashboard_RiskCard_HeaderColor_ForStatus_SubmittedToBrokerOps":

						if (strUserRole.trim().contentEquals(Constants.UserRole.get(1))) {
							CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_RiskDashboard_AdminHomepage");
							Project.ExecuteRow(driver, strConfig, "LN_RiskDashboard_AdminHomepage", "Click");
						}
						
						
						CommonLib.waitForLoad(driver, strConfig);
						if (rowCount> 2) {
							for (int k = 3; k <= rowCount; k++) {
								for (int l = 0; l < colCount; l++) {

									riskDashboardData.add(l, (read.CellValue(riskDashboardSheet, k, l)));
									System.out.println(riskDashboardData.get(l));

								}
								int nullCounter = 0;
								if (!riskDashboardData.isEmpty()) {
									for (int m = 0; m < riskDashboardData.size(); m++) {
										if (riskDashboardData.get(m) == null) {
											nullCounter = nullCounter + 1;
										}
									}

									if (!(nullCounter == riskDashboardData.size())) {
										Boolean riskFound = false;

										int regTCCounter = 0;
										if(riskDashboardData.get(0).trim().contentEquals(strTestCaseID)) {
										riskFound = BusinessRules.searchRisk(driver, strConfig, riskDashboardData.get(1),
												false);
										if (riskFound) {
											BusinessRules.verify_Dashboard_RiskCard_HeaderColor_ForStatus_SubmittedToBrokerOps(driver, strConfig, riskDashboardData.get(1));
											

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
											if(regTCCounter == rowCount) {
											CommonLib.getLogger(strConfig)
											.info("WARNING: TCID in RTTestdata.xlsx file does not match with that in the Driver.xlsx file");
											TestReporter.Warning(driver, strConfig, "TCID Mismatch",
											"TCID in RTTestdata.xlsx file does not match with that in the Driver.xlsx file");
										
											}}
										riskDashboardData.clear();
									}
								}
								riskDashboardData.clear();
							}
						} else {
							CommonLib.getLogger(strConfig)
									.info("WARNING: No Risk Data supplied for Risk Details in RTTestdata.xlsx file");
							TestReporter.Warning(driver, strConfig, "NTU Risk",
									"No Risk Data supplied for Risk Details in RTTestdata.xlsx file");

						}

						break;
						
						
					case "BR_Verify_Dashboard_RiskCard_HeaderColor_ForStatus_NTU":

						if (strUserRole.trim().contentEquals(Constants.UserRole.get(1))) {
							CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_RiskDashboard_AdminHomepage");
							Project.ExecuteRow(driver, strConfig, "LN_RiskDashboard_AdminHomepage", "Click");
						}
						
						
						CommonLib.waitForLoad(driver, strConfig);
						if (rowCount> 2) {
							for (int k = 3; k <= rowCount; k++) {
								for (int l = 0; l < colCount; l++) {

									riskDashboardData.add(l, (read.CellValue(riskDashboardSheet, k, l)));
									System.out.println(riskDashboardData.get(l));

								}
								int nullCounter = 0;
								if (!riskDashboardData.isEmpty()) {
									for (int m = 0; m < riskDashboardData.size(); m++) {
										if (riskDashboardData.get(m) == null) {
											nullCounter = nullCounter + 1;
										}
									}

									if (!(nullCounter == riskDashboardData.size())) {
										Boolean riskFound = false;

										int regTCCounter = 0;
										if(riskDashboardData.get(0).trim().contentEquals(strTestCaseID)) {
										riskFound = BusinessRules.searchRisk(driver, strConfig, riskDashboardData.get(1),
												false);
										if (riskFound) {
											BusinessRules.verify_Dashboard_RiskCard_HeaderColor_ForStatus_NTU(driver, strConfig, riskDashboardData.get(1));
											

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
											if(regTCCounter == rowCount) {
											CommonLib.getLogger(strConfig)
											.info("WARNING: TCID in RTTestdata.xlsx file does not match with that in the Driver.xlsx file");
											TestReporter.Warning(driver, strConfig, "TCID Mismatch",
											"TCID in RTTestdata.xlsx file does not match with that in the Driver.xlsx file");
										
											}}
										riskDashboardData.clear();
									}
								}
								riskDashboardData.clear();
							}
						} else {
							CommonLib.getLogger(strConfig)
									.info("WARNING: No Risk Data supplied for Risk Details in RTTestdata.xlsx file");
							TestReporter.Warning(driver, strConfig, "NTU Risk",
									"No Risk Data supplied for Risk Details in RTTestdata.xlsx file");

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
