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

public class RiskOverview {

	public static Data_Interaction interact = new Data_Interaction();
	public static ProjectConstants p_constants = new ProjectConstants();
	public static ReadExcel read = new ReadExcel();
	Project project = new Project();
	CommonLib commonLib = new CommonLib();
	static BusinessRules br = new BusinessRules();
	UI_Interaction ui = new UI_Interaction();
	Data_Interaction data_Interaction = new Data_Interaction();
	public static XSSFWorkbook D_workbook;

	public RiskOverview(RemoteWebDriver driver, String strConfig, String strTestingType, String strUserRole)
			throws InterruptedException {

		CommonLib.getLogger(strConfig).info("Inside Risk Overview Page");

		
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
				ArrayList<String> createRiskData = new ArrayList<String>();
				XSSFSheet riskCreationSheet = read.readXLSXfile(ProjectConstants.RTTESTDATAEXCEL, ProjectConstants.RISKCREATIONSHEET);
				int rowCnt = riskCreationSheet.getLastRowNum();
				int colCnt = read.columncount(riskCreationSheet, 0);
				
				ArrayList<String> riskOverviewData = new ArrayList<String>();
				XSSFSheet riskOverviewSheet = read.readXLSXfile(ProjectConstants.RTTESTDATAEXCEL, ProjectConstants.RISKOVERVIEWSHEET);
				int rowCount = riskOverviewSheet.getLastRowNum();
				int colCount = read.columncount(riskOverviewSheet, 0);
				
				
				ArrayList<String> riskOverviewRegData = new ArrayList<String>();
				XSSFSheet riskOverviewRegSheet = read.readXLSXfile(ProjectConstants.RTTESTDATAEXCEL, ProjectConstants.RISKOVERVIEWREGRESSIONSHEET);
				int rowCountReg = riskOverviewRegSheet.getLastRowNum();
				int colCountReg = read.columncount(riskOverviewRegSheet, 0);

				if (Value != "") {
					switch (Header) {
					

					case "BR_Create_NewRisk":
						
						if (strUserRole.trim().contentEquals(Constants.UserRole.get(1))) {
							CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_RiskDashboard_AdminHomepage");
							Project.ExecuteRow(driver, strConfig, "LN_RiskDashboard_AdminHomepage", "Click");
						}
						CommonLib.waitForLoad(driver, strConfig);
						System.out.println("rowCnt"+rowCnt);
						if (rowCnt > 2) {
							for (int k = 3; k <= rowCnt; k++) {
								for (int l = 0; l < colCnt; l++) {

									createRiskData.add(l, (read.CellValue(riskCreationSheet, k, l)));
									System.out.println("createRiskData.get(l)"+createRiskData.get(l));
								}
								int nullCounter = 0;
								System.out.println(riskOverviewData.isEmpty());
								if (!createRiskData.isEmpty()) {
									for (int m = 0; m < createRiskData.size(); m++) {
										if (createRiskData.get(m) == null) {
											nullCounter = nullCounter + 1;
										}
									}

									if (!(nullCounter == createRiskData.size())) {
										BusinessRules.createNewRisk(driver, strConfig, strTestingType,  createRiskData);
										BusinessRules.navigateToHomePage(driver, strConfig);
										createRiskData.clear();
									}
								}
								createRiskData.clear();
							}
						} else {
							CommonLib.getLogger(strConfig)
									.info("WARNING: No Risk Dashboard Data supplied in RTTestdata.xlsx file");
							TestReporter.Warning(driver, strConfig, "Risk Dashboard",
									"No Risk Dashboard Data supplied in RTTestdata.xlsx file");

						}

						break;
						
					case "BR_SearchRisk_And_Save_RiskOverview":
						
						if (strUserRole.trim().contentEquals(Constants.UserRole.get(1))) {
							CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_RiskDashboard_AdminHomepage");
							Project.ExecuteRow(driver, strConfig, "LN_RiskDashboard_AdminHomepage", "Click");
						}
						
						if(strTestingType.contentEquals("Sanity")) {
						CommonLib.waitForLoad(driver, strConfig);
						if (rowCount > 2) {
							for (int k = 3; k <= rowCount; k++) {
								for (int l = 0; l < colCount; l++) {

									riskOverviewData.add(l, (read.CellValue(riskOverviewSheet, k, l)));
									System.out.println(riskOverviewData.get(l));
								}
								int nullCounter = 0;
								if (!riskOverviewData.isEmpty()) {
									for (int m = 0; m < riskOverviewData.size(); m++) {
										if (riskOverviewData.get(m) == null) {
											nullCounter = nullCounter + 1;
										}
									}
									
									
									if (!(nullCounter == riskOverviewData.size())) {
										Boolean riskFound = false;

										riskFound = BusinessRules.searchRisk(driver, strConfig, riskOverviewData.get(0),
												true);

										if (riskFound) {
											BusinessRules.save_RiskOverview(driver, strConfig, strTestingType,  riskOverviewData);
											BusinessRules.navigateToHomePage(driver, strConfig);
										}
										riskOverviewData.clear();
									}
								}
								riskOverviewData.clear();
							}
						} else {
							CommonLib.getLogger(strConfig)
									.info("WARNING: No Risk Overview Data supplied in RTTestdata.xlsx file");
							TestReporter.Warning(driver, strConfig, "Risk Overview",
									"No Data supplied in RTTestdata.xlsx file");

						}
					}
						
						break;
						
					case "BR_Verify_TootipAddress_UponHover_On_ClientFieldOption":

						if (strUserRole.trim().contentEquals(Constants.UserRole.get(1))) {
							CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_RiskDashboard_AdminHomepage");
							Project.ExecuteRow(driver, strConfig, "LN_RiskDashboard_AdminHomepage", "Click");
						}
						
						CommonLib.waitForLoad(driver, strConfig);
						if (rowCountReg > 2) {
							for (int k = 3; k <= rowCountReg; k++) {
								for (int l = 0; l < colCountReg; l++) {

									riskOverviewRegData.add(l, (read.CellValue(riskOverviewRegSheet, k, l)));
									System.out.println(riskOverviewRegData.get(l));

								}
								int nullCounter = 0;
								if (!riskOverviewRegData.isEmpty()) {
									for (int m = 0; m < riskOverviewRegData.size(); m++) {
										if (riskOverviewRegData.get(m) == null) {
											nullCounter = nullCounter + 1;
										}
									}

									if (!(nullCounter == riskOverviewRegData.size())) {

										int regTCCounter = 0;
										if (riskOverviewRegData.get(0).trim().contentEquals(strTestCaseID)) {

											BusinessRules.verify_TootipAddress_UponHover_On_ClientFieldOption(driver,
													strConfig, riskOverviewRegData.get(3), riskOverviewRegData.get(4),
													riskOverviewRegData.get(15));

										}

										else {

											regTCCounter = regTCCounter + 1;
											if (regTCCounter == rowCountReg) {
												TestReporter.Warning(driver, strConfig, "TCID Mismatch",
														"TCID in RTTestdata.xlsx file does not match with that in the Driver.xlsx file");

											}
										}
										riskOverviewRegData.clear();
									}
								}
								riskOverviewRegData.clear();
							}
						} else {
							
							TestReporter.Warning(driver, strConfig, "Verify_TootipAddress_UponHover_On_ClientFieldOption: ",
									"No Data supplied in the 'RiskOverviewRegression' sheet of RTTestdata.xlsx");

						}

						break;	
						
						
					case "BR_Verify_WarningMsg_AfterSaving_UnapprovedClient_OnRiskOverviewTab":

						if (strUserRole.trim().contentEquals(Constants.UserRole.get(1))) {
							CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_RiskDashboard_AdminHomepage");
							Project.ExecuteRow(driver, strConfig, "LN_RiskDashboard_AdminHomepage", "Click");
						}
						
						CommonLib.waitForLoad(driver, strConfig);
						if (rowCountReg > 2) {
							for (int k = 3; k <= rowCountReg; k++) {
								for (int l = 0; l < colCountReg; l++) {

									riskOverviewRegData.add(l, (read.CellValue(riskOverviewRegSheet, k, l)));
									System.out.println(riskOverviewRegData.get(l));

								}
								int nullCounter = 0;
								if (!riskOverviewRegData.isEmpty()) {
									for (int m = 0; m < riskOverviewRegData.size(); m++) {
										if (riskOverviewRegData.get(m) == null) {
											nullCounter = nullCounter + 1;
										}
									}

									if (!(nullCounter == riskOverviewRegData.size())) {

										int regTCCounter = 0;
										if (riskOverviewRegData.get(0).trim().contentEquals(strTestCaseID)) {

											BusinessRules.verify_WarningMsg_AfterSaving_UnapprovedClient_OnRiskOverviewTab(driver, strConfig, riskOverviewRegData);
											BusinessRules.navigateToHomePage(driver, strConfig);
										}

										else {

											regTCCounter = regTCCounter + 1;
											if (regTCCounter == rowCountReg) {
												TestReporter.Warning(driver, strConfig, "TCID Mismatch",
														"TCID in RTTestdata.xlsx file does not match with that in the Driver.xlsx file");

											}
										}
										riskOverviewRegData.clear();
									}
								}
								riskOverviewRegData.clear();
							}
						} else {
							
							TestReporter.Warning(driver, strConfig, "Verify_TootipAddress_UponHover_On_ClientFieldOption: ",
									"No Data supplied in the 'RiskOverviewRegression' sheet of RTTestdata.xlsx");

						}

						break;	
						
						
					case "BR_Verify_DirectInsured_SwitchedOFF_When_ReinsuredIs_SwitchedON":
						
						if (strUserRole.trim().contentEquals(Constants.UserRole.get(1))) {
							CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_RiskDashboard_AdminHomepage");
							Project.ExecuteRow(driver, strConfig, "LN_RiskDashboard_AdminHomepage", "Click");
						}
						
						BusinessRules.verify_DirectInsured_SwitchedOFF_When_ReinsuredIs_SwitchedON(driver, strConfig);
						BusinessRules.navigateToHomePage(driver, strConfig);
						break;
						
					case "BR_Verify_Reinsured_SwitchedOFF_When_DirectInsured_Is_SwitchedON":
						
						if (strUserRole.trim().contentEquals(Constants.UserRole.get(1))) {
							CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_RiskDashboard_AdminHomepage");
							Project.ExecuteRow(driver, strConfig, "LN_RiskDashboard_AdminHomepage", "Click");
						}
						
						BusinessRules.verify_Reinsured_SwitchedOFF_When_DirectInsured_Is_SwitchedON(driver, strConfig);
						BusinessRules.navigateToHomePage(driver, strConfig);
						break;
						
						
					case "BR_Verify_InsuredField_PopulatedWith_ClientName_When_DirectInsured_SwitchedON":

						if (strUserRole.trim().contentEquals(Constants.UserRole.get(1))) {
							CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_RiskDashboard_AdminHomepage");
							Project.ExecuteRow(driver, strConfig, "LN_RiskDashboard_AdminHomepage", "Click");
						}
						
						CommonLib.waitForLoad(driver, strConfig);
						if (rowCountReg > 2) {
							for (int k = 3; k <= rowCountReg; k++) {
								for (int l = 0; l < colCountReg; l++) {

									riskOverviewRegData.add(l, (read.CellValue(riskOverviewRegSheet, k, l)));
									System.out.println(riskOverviewRegData.get(l));

								}
								int nullCounter = 0;
								if (!riskOverviewRegData.isEmpty()) {
									for (int m = 0; m < riskOverviewRegData.size(); m++) {
										if (riskOverviewRegData.get(m) == null) {
											nullCounter = nullCounter + 1;
										}
									}

									if (!(nullCounter == riskOverviewRegData.size())) {

										int regTCCounter = 0;
										if (riskOverviewRegData.get(0).trim().contentEquals(strTestCaseID)) {

											BusinessRules.verify_InsuredField_PopulatedWith_ClientName_When_DirectInsured_SwitchedON(driver, strConfig, riskOverviewRegData.get(3),riskOverviewRegData.get(4));
											BusinessRules.navigateToHomePage(driver, strConfig);
										}

										else {

											regTCCounter = regTCCounter + 1;
											if (regTCCounter == rowCountReg) {
												TestReporter.Warning(driver, strConfig, "TCID Mismatch",
														"TCID in RTTestdata.xlsx file does not match with that in the Driver.xlsx file");

											}
										}
										riskOverviewRegData.clear();
									}
								}
								riskOverviewRegData.clear();
							}
						} else {
							
							TestReporter.Warning(driver, strConfig, "Verify_InsuredField_PopulatedWith_ClientName_When_DirectInsured_SwitchedON: ",
									"No Data supplied in the 'RiskOverviewRegression' sheet of RTTestdata.xlsx");

						}

						break;	
						
					case "BR_Verify_AmmendedInsured_FieldValue_isRetained_When_DirectInsured_SwitchedOFF":

						if (strUserRole.trim().contentEquals(Constants.UserRole.get(1))) {
							CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_RiskDashboard_AdminHomepage");
							Project.ExecuteRow(driver, strConfig, "LN_RiskDashboard_AdminHomepage", "Click");
						}
						
						CommonLib.waitForLoad(driver, strConfig);
						if (rowCountReg > 2) {
							for (int k = 3; k <= rowCountReg; k++) {
								for (int l = 0; l < colCountReg; l++) {

									riskOverviewRegData.add(l, (read.CellValue(riskOverviewRegSheet, k, l)));
									System.out.println(riskOverviewRegData.get(l));

								}
								int nullCounter = 0;
								if (!riskOverviewRegData.isEmpty()) {
									for (int m = 0; m < riskOverviewRegData.size(); m++) {
										if (riskOverviewRegData.get(m) == null) {
											nullCounter = nullCounter + 1;
										}
									}

									if (!(nullCounter == riskOverviewRegData.size())) {

										int regTCCounter = 0;
										if (riskOverviewRegData.get(0).trim().contentEquals(strTestCaseID)) {

											BusinessRules.verify_AmmendedInsured_FieldValue_isRetained_When_DirectInsured_SwitchedOFF(driver, strConfig, riskOverviewRegData.get(3),riskOverviewRegData.get(4));
											BusinessRules.navigateToHomePage(driver, strConfig);
										}

										else {

											regTCCounter = regTCCounter + 1;
											if (regTCCounter == rowCountReg) {
												TestReporter.Warning(driver, strConfig, "TCID Mismatch",
														"TCID in RTTestdata.xlsx file does not match with that in the Driver.xlsx file");

											}
										}
										riskOverviewRegData.clear();
									}
								}
								riskOverviewRegData.clear();
							}
						} else {
							
							TestReporter.Warning(driver, strConfig, "Verify_AmmendedInsured_FieldValue_isRetained_When_DirectInsured_SwitchedOFF: ",
									"No Data supplied in the 'RiskOverviewRegression' sheet of RTTestdata.xlsx");

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


