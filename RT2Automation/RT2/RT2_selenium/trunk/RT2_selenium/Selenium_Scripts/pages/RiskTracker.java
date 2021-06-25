package pages;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
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
import libraries.WriteExcel;

public class RiskTracker {

	public static Data_Interaction interact = new Data_Interaction();
	public static ProjectConstants p_constants = new ProjectConstants();
	public static ReadExcel read = new ReadExcel();
	public static WriteExcel write = new WriteExcel();
	Project project = new Project();
	CommonLib commonLib = new CommonLib();
	static BusinessRules br = new BusinessRules();
	UI_Interaction ui = new UI_Interaction();
	Data_Interaction data_Interaction = new Data_Interaction();
	public static XSSFWorkbook D_workbook;

	public RiskTracker(RemoteWebDriver driver,  String strConfig,String strTestingType, String strUserRole)
			throws InterruptedException {

		CommonLib.getLogger(strConfig).info("Inside RiskTracker");

		
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
				int rowCountCreateRisk = riskCreationSheet.getLastRowNum();
				int colCountCreateRisk = read.columncount(riskCreationSheet, 0);
				
				ArrayList<String> riskDetailsData = new ArrayList<String>();
				XSSFSheet riskDetailsSheet = read.readXLSXfile(ProjectConstants.RTTESTDATAEXCEL, ProjectConstants.RISKDETAILSSHEET);
				int rowCountRiskDetails = riskDetailsSheet.getLastRowNum();
				int colCountRiskDetails = read.columncount(riskDetailsSheet, 0);
				
				ArrayList<String> riskFirmOrderData = new ArrayList<String>();
				XSSFSheet riskFirmOrderSheet = read.readXLSXfile(ProjectConstants.RTTESTDATAEXCEL, ProjectConstants.RISKFIRMORDERSHEET);
				int rowCountFO = riskFirmOrderSheet.getLastRowNum();
				int colCountFO = read.columncount(riskFirmOrderSheet, 0);
				
				ArrayList<String> riskSubmitToBrokerOpsData = new ArrayList<String>();
				XSSFSheet riskSubmitToBrokerOpsSheet = read.readXLSXfile(ProjectConstants.RTTESTDATAEXCEL, ProjectConstants.RISKSUBMITTOBROKEROPSSHEET);
				int rowCountSBO = riskSubmitToBrokerOpsSheet.getLastRowNum();
				int colCountSBO = read.columncount(riskSubmitToBrokerOpsSheet, 0);
				
				ArrayList<String> riskNTUData = new ArrayList<String>();
				XSSFSheet riskNTUSheet = read.readXLSXfile(ProjectConstants.RTTESTDATAEXCEL, ProjectConstants.RISKNTUSHEET);
				int rowCountNTU = riskNTUSheet.getLastRowNum();
				int colCountNTU = read.columncount(riskNTUSheet, 0);
				
				ArrayList<String> riskReinstateData = new ArrayList<String>();
				
				ArrayList<String> riskCopyData = new ArrayList<String>();
				XSSFSheet riskCopySheet = read.readXLSXfile(ProjectConstants.RTTESTDATAEXCEL, ProjectConstants.RISKCOPYSHEET);
				int rowCountCopy = riskCopySheet.getLastRowNum();
				int colCountCopy = read.columncount(riskCopySheet, 0);
				
				if (Value != "") {
					switch (Header) {

					case "BR_Track_Risk_Lifecycle":
						if (strUserRole.trim().contentEquals(Constants.UserRole.get(1))) {
							CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_RiskDashboard_AdminHomepage");
							Project.ExecuteRow(driver, strConfig, "LN_RiskDashboard_AdminHomepage", "Click");
						}
						
						CommonLib.waitForLoad(driver, strConfig);
						if (rowCountCreateRisk > 2 && rowCountRiskDetails > 2 && rowCountFO > 2) {
							
							if (rowCountCreateRisk == rowCountRiskDetails && rowCountRiskDetails == rowCountFO ) {
							for (int k = 3; k <= rowCountCreateRisk; k++) {
								for (int l = 0; l < colCountCreateRisk; l++) {

									createRiskData.add(l, (read.CellValue(riskCreationSheet, k, l)));
									System.out.println(createRiskData.get(l));
								}
								String RTRefNum = BusinessRules.createNewRisk(driver, strConfig, strTestingType, createRiskData);
								
								WriteExcel.insertValueInCell(ProjectConstants.RTTESTDATAEXCEL, ProjectConstants.RISKNTUSHEET, k, 0, RTRefNum);
								WriteExcel.insertValueInCell(ProjectConstants.RTTESTDATAEXCEL, ProjectConstants.RISKREINSTATESHEET, k, 0, RTRefNum);
								WriteExcel.insertValueInCell(ProjectConstants.RTTESTDATAEXCEL, ProjectConstants.RISKDETAILSSHEET, k, 0, RTRefNum);
								WriteExcel.insertValueInCell(ProjectConstants.RTTESTDATAEXCEL, ProjectConstants.RISKFIRMORDERSHEET, k, 0, RTRefNum);
								WriteExcel.insertValueInCell(ProjectConstants.RTTESTDATAEXCEL, ProjectConstants.RISKSUBMITTOBROKEROPSSHEET, k, 0, RTRefNum);
								
								riskNTUSheet = read.readXLSXfile(ProjectConstants.RTTESTDATAEXCEL, ProjectConstants.RISKNTUSHEET);
								riskReinstateData.add(0, RTRefNum);
								riskDetailsSheet = read.readXLSXfile(ProjectConstants.RTTESTDATAEXCEL, ProjectConstants.RISKDETAILSSHEET);
								riskFirmOrderSheet = read.readXLSXfile(ProjectConstants.RTTESTDATAEXCEL, ProjectConstants.RISKFIRMORDERSHEET);
								riskSubmitToBrokerOpsSheet = read.readXLSXfile(ProjectConstants.RTTESTDATAEXCEL, ProjectConstants.RISKSUBMITTOBROKEROPSSHEET);
								
								
								for (int l = 0; l < colCountNTU; l++) {
									
									riskNTUData.add(l, (read.CellValue(riskNTUSheet, k, l)));
									System.out.println(riskNTUData.get(l));
									
								}
								BusinessRules.NTURisk(driver, strConfig, strTestingType, riskNTUData);
								
								BusinessRules.reinstateRisk(driver, strConfig, strTestingType, riskReinstateData);
								
								for (int l = 0; l < colCountRiskDetails; l++) {
									
									riskDetailsData.add(l, (read.CellValue(riskDetailsSheet, k, l)));
									System.out.println(riskDetailsData.get(l));
									
								}
								BusinessRules.saveRiskDetailsTab(driver, strConfig, strTestingType, riskDetailsData);
								
								for (int l = 0; l < colCountFO; l++) {
									riskFirmOrderData.add(l, (read.CellValue(riskFirmOrderSheet, k, l)));
									System.out.println(riskFirmOrderData.get(l));

								}
								
								BusinessRules.firmOrder_Risk(driver, strConfig, strTestingType, riskFirmOrderData);
								
								for (int l = 0; l < colCountSBO; l++) {
									riskSubmitToBrokerOpsData.add(l, (read.CellValue(riskSubmitToBrokerOpsSheet, k, l)));
									System.out.println(riskSubmitToBrokerOpsData.get(l));

								}
								BusinessRules.submitToBrokerOps(driver, strConfig,strTestingType,
										riskSubmitToBrokerOpsData);
								
								
								System.out.println("createRiskData.get(13).trim()"+createRiskData.get(13).trim());
								if(createRiskData.get(14).trim().contentEquals("Yes")) {
									
								if(rowCountCopy>2) {
								WriteExcel.insertValueInCell(ProjectConstants.RTTESTDATAEXCEL, ProjectConstants.RISKCOPYSHEET, k, 0, RTRefNum);
								riskCopySheet = null;
								riskCopySheet = read.readXLSXfile(ProjectConstants.RTTESTDATAEXCEL, ProjectConstants.RISKCOPYSHEET);
								for (int l = 0; l < colCountCopy; l++) {
									riskCopyData.add(l, (read.CellValue(riskCopySheet, k, l)));
									System.out.println(riskCopyData.get(l));
								}

								}
								
								BusinessRules.copyRisk(driver, strConfig, strTestingType, riskCopyData);
								}
								
								BusinessRules.navigateToHomePage(driver, strConfig);
								createRiskData.clear();
								riskDetailsData.clear();
								riskFirmOrderData.clear();
								riskSubmitToBrokerOpsData.clear();
								
							}
							} else {
								CommonLib.getLogger(strConfig)
										.info("WARNING: Create Risk, Save Risk Details and Firm Order Sheets should contain same number of rows for Risk Data");
								TestReporter.Warning(driver, strConfig, "Risk Test Data Issue",
										" Create Risk, Save Risk Details and Firm Order Sheets should contain same number of rows for Risk Data");

							}
						} else {
							
							if(!(rowCountCreateRisk>2)) {
								TestReporter.Warning(driver, strConfig, "Create Risk",
										"No Risk Data present in Sheet");
							}
							else if(!(rowCountRiskDetails>2)) {
								TestReporter.Warning(driver, strConfig, "Save Risk Details",
										"No Risk Data present in Sheet");
								}
							
							else if(!(rowCountFO>2)) {
								TestReporter.Warning(driver, strConfig, "Firm Order Test Data",
										"No Risk Data present in Sheet");
								}

						}
						break;

					case "BR_CreateRisk_NTU_Reinstate":
						if (strUserRole.trim().contentEquals(Constants.UserRole.get(1))) {
							CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_RiskDashboard_AdminHomepage");
							Project.ExecuteRow(driver, strConfig, "LN_RiskDashboard_AdminHomepage", "Click");
						}
						
						CommonLib.waitForLoad(driver, strConfig);
						if (rowCountCreateRisk> 2 && rowCountNTU > 2) {
							
							if (rowCountCreateRisk == rowCountNTU ) {
								for (int k = 3; k <= rowCountCreateRisk; k++) {
									for (int l = 0; l < colCountCreateRisk; l++) {

										createRiskData.add(l, (read.CellValue(riskCreationSheet, k, l)));
										System.out.println(createRiskData.get(l));
									}
									String RTRefNum = BusinessRules.createNewRisk(driver, strConfig, strTestingType, createRiskData);
									
									WriteExcel.insertValueInCell(ProjectConstants.RTTESTDATA3EXCEL, ProjectConstants.RISKNTUSHEET, k, 0, RTRefNum);
									riskNTUSheet = read.readXLSXfile(ProjectConstants.RTTESTDATA3EXCEL, ProjectConstants.RISKNTUSHEET);
									
									for (int l = 0; l < colCountNTU; l++) {
										
										riskNTUData.add(l, (read.CellValue(riskNTUSheet, k, l)));
										System.out.println(riskNTUData.get(l));
										
									}
									
									
									BusinessRules.NTURisk(driver, strConfig, strTestingType, riskNTUData);
									
									WriteExcel.insertValueInCell(ProjectConstants.RTTESTDATA3EXCEL, ProjectConstants.RISKREINSTATESHEET, k, 0, RTRefNum);
									
									riskReinstateData.add(0, RTRefNum);
									BusinessRules.reinstateRisk(driver, strConfig, strTestingType, riskReinstateData);
									
									BusinessRules.navigateToHomePage(driver, strConfig);
									createRiskData.clear();
									riskDetailsData.clear();
									riskFirmOrderData.clear();
									riskSubmitToBrokerOpsData.clear();
									
								}
								} else {
								CommonLib.getLogger(strConfig)
										.info("WARNING: Create Risk and NTU Sheets should contain same number of rows for Risk Data");
								TestReporter.Warning(driver, strConfig, "Risk Test Data Issue",
										" Create Risk and NTU Sheets should contain same number of rows for Risk Data");

							}
						} else {
							
							if(!(rowCountCreateRisk>2)) {
								TestReporter.Warning(driver, strConfig, "Create Risk",
										"No Risk Data present in Sheet");
							}
							else if(!(rowCountRiskDetails>2)) {
								TestReporter.Warning(driver, strConfig, "NTU Risk",
										"No Risk Data present in Sheet");
								}
							
							
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


