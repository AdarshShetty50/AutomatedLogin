/**
 * 
 */
package tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.Duration;
import java.time.Instant;
import java.util.LinkedHashMap;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
//import com.relevantcodes.extentreports.reporter;

import libraries.CommonLib;
import libraries.Constants;
import libraries.DictionaryObjects;
import libraries.Project;
import libraries.ProjectConstants;
import libraries.ReadExcel;
import libraries.TestReporter;

public class Test4 {
	Constants constants = new Constants();
	CommonLib common = new CommonLib();
	
	public static XSSFWorkbook D_workbook;
	public static XSSFWorkbook I_workbook;
	
	public Instant startTest4, endTest4;
	public int TCRow_No;
	public static Logger logger;
	public static int iScreenNum;
	public static String strCurrDate;
	public static String strCurrTime;
	public static String strFilePath;
	private static String ScreenshotFilePath;
	private static String strFileName;
	private static String strFilePathwithName;
	private static XSSFWorkbook O_workbook4;
	private static String FullScreenshotFilePath;
	private static String TestCaseName, TestCaseDesc, TestCaseModule,totalTime;
	public static ExtentReports report;
	public static RemoteWebDriver driver4;
	public String strDriverSessionTest;
	public static Boolean blnStopCurrentTestCase;
	public static String blnStopAfterIssue;
	//public static ExtentHtmlReporter htmlReporter;
	@BeforeClass
	public void setUp() {
		D_workbook = constants.getDriverWorkbook();
		I_workbook = constants.getTestDataWorkbook();

		strCurrDate = CommonLib.GetCurrentDate().replace("/", "-");
		strCurrTime = CommonLib.GetCurrentTime().replace(":", "-").split(" ")[0];

		if (Test1.report == null) {
			Test1.report = new ExtentReports(ProjectConstants.OUTPUTSHEETPATH + "/" + strCurrDate +"/Automation_Report_" + strCurrDate + "_" + strCurrTime + ".html",true);
		}

		strFilePath = ProjectConstants.OUTPUTSHEETPATH + "/" + strCurrDate + "/Test4";
		ScreenshotFilePath = ProjectConstants.OUTPUTSHEETPATH + "/" + strCurrDate + "/Test4/Screenshots";
		strFileName = "Output_Summary_Test4_" + strCurrDate + ".xlsx";
		strFilePathwithName = strFilePath + "/" + strFileName;

		O_workbook4 = constants.getOutPutWorkbook(strFilePathwithName);
		logger = Logger.getRootLogger();
		logger.setLevel(Level.INFO);
		logger.addAppender(new ConsoleAppender(new PatternLayout("%d{ISO8601} [%t] %-5p %c %x - %m%n")));
		File filePath = new File(strFilePath);
		Boolean b = false;
		if (!filePath.exists()) {
			b = filePath.mkdirs();
		}
		if (b) {
			logger.info("Output Folder created successfully.");
		}
		filePath = new File(ScreenshotFilePath);
		b = false;
		if (!filePath.exists()) {
			b = filePath.mkdirs();
		}
		if (b) {
			logger.info("Screenshot Folder created successfully.");
		}
		try {
			logger.addAppender(new RollingFileAppender(new PatternLayout("%d{ISO8601} [%t] %-5p %c %x - %m%n"),
					strFilePath + "\\" + strFileName.split("\\.")[0] + "_" + strCurrTime + "_Logs.log"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("Execution started");
		Constants.DefaultDO_GridDetails.put(Thread.currentThread().getId(), "Test4");
	}
	
	@Test
	public void Test_4() throws InterruptedException {
		try {

			XSSFSheet driversheet = null;
			XSSFSheet configsheet = null;
			ReadExcel excel = new ReadExcel();

			driversheet = D_workbook.getSheet(ProjectConstants.DRIVERSHEET);
			configsheet = D_workbook.getSheet(ProjectConstants.CONFIGSHEET);
			Constants.strRTURL = excel.CellValue(configsheet, 1, 1);
			Constants.strEnvironment = excel.CellValue(configsheet, 2, 1);
			Constants.strScreenShots = excel.CellValue(configsheet, 3, 1);
			configsheet = null;


			startTest4 = Instant.now();
			for (int a = 1; a <= excel.rowcount(driversheet); a++) {
				try {
					TestCaseName = excel.CellValue(driversheet, a, 0);
					TestCaseDesc = excel.CellValue(driversheet, a, 1);
					TestCaseModule = excel.CellValue(driversheet, a, 2);
					String strTestingType = excel.CellValue(driversheet, a, 3);
					String strNode = excel.CellValue(driversheet, a, 4);
					String TCExecute = excel.CellValue(driversheet, a, 5);
					String strTCIterations = excel.CellValue(driversheet, a, 6);
					String strUsername = excel.CellValue(driversheet, a, 8);
					String strPassword = excel.CellValue(driversheet, a, 9);
					String strUserRole = excel.CellValue(driversheet, a, 10);

					if (TestCaseName != null && strTCIterations != null) {
						Integer intTCIterations = Integer.parseInt(strTCIterations);
						if (TCExecute.equalsIgnoreCase("Yes") && strNode.equalsIgnoreCase("Node 4")) {
							for (int j = 0; j < intTCIterations; j++) {
								// Before Execution
								driver4 = CommonLib.getRemoteDriver("Chrome");
								strDriverSessionTest = driver4.getWindowHandle();
								TestReporter.StartTest("Config4");
								// Execute Test case
								TCRow_No = a;
								if (intTCIterations > 1) {
									String strFolderName = TestCaseName + "_" + (j + 1);
									setFullScreenshotFilePath(getScreenshotFilePath() + "/" + strFolderName);
									logger.info("FullScreenshotFilePath=" + getFullScreenshotFilePath());
									if (new File(getFullScreenshotFilePath()).exists()) {
										for (File file : new File(getFullScreenshotFilePath()).listFiles())
											file.delete();
									} else {
										new File(getFullScreenshotFilePath()).mkdirs();
									}
									iScreenNum = 1;
								} else {
									String strFolderName = TestCaseName;
									setFullScreenshotFilePath(getScreenshotFilePath() + "/" + strFolderName);
									logger.info("FullScreenshotFilePath=" + getFullScreenshotFilePath());
									if (new File(getFullScreenshotFilePath()).exists()) {
										for (File file : new File(getFullScreenshotFilePath()).listFiles())
											file.delete();
									} else {
										new File(getFullScreenshotFilePath()).mkdirs();
									}
									iScreenNum = 1;
								}
								logger.info("Testcase being executed is:" + TestCaseName);
								Project RunProject = new Project();
								try {
									blnStopCurrentTestCase=false;
									logger.info("Logging into Risk Tracker with User Role:"+strUserRole);
									TestReporter.Info(driver4, "Config4", "User Role", "Logging into Risk Tracker with User Role:"+strUserRole);
									common.enter_URL(driver4, "Config4", Constants.strRTURL);
									RunProject.RunTestCase(driver4, "Config4", strTestingType, TestCaseName, strUserRole, strUsername, strPassword);

								} catch (IOException e) {
									e.printStackTrace();
								} catch (NoSuchElementException e) {
									e.printStackTrace();
									StringWriter sw = new StringWriter();
									e.printStackTrace(new PrintWriter(sw));
									Test4.logger.fatal("Stacktrace -> " + sw.toString());
									TestReporter.Fail(driver4, "Config4", "NoSuchElementException", sw.toString());
								}
								// After Execution
								driver4.close();
								driver4.quit();
								TestReporter.EndTest();
								Test1.report.flush();
							}
						}
					}
				} catch (Exception e) {
					if (!driver4.toString().contains("(null)")) {
						StringWriter sw = new StringWriter();
						e.printStackTrace(new PrintWriter(sw));
						Test4.logger.fatal("Stacktrace -> " + sw.toString());
						TestReporter.Fatal(driver4, "Config4", "Unhandled Exception", sw.toString());
						driver4.close();
						driver4.quit();
						TestReporter.EndTest();
						Test1.report.flush();
					} else {
						e.printStackTrace();
						TestReporter.Warning(driver4, "Config4", "Driver Null", "Exiting Test Case");
						Test1.report.flush();
					}
				}
			}
			endTest4 = Instant.now();
			Duration timeElapsed = Duration.between(startTest4, endTest4);
			totalTime = (timeElapsed.toMillis() / 1000) / 60 + " mins & " + (timeElapsed.toMillis() / 1000) % 60
					+ " secs";
			logger.info("Execution Completed in " + totalTime);

			Test1.report.flush();
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			Test4.logger.fatal("Stacktrace -> " + sw.toString());
			TestReporter.Fatal(driver4, "Config4", "Unhandled Exception (FATAL)", sw.toString());
		}
	}
	
	
	
	public void Action(RemoteWebDriver driver4, String Status4, String Summary, String Description) {
		ReadExcel excel = new ReadExcel();
		LinkedHashMap<String, String> ObjOutput_DO = DictionaryObjects.getObjOutput_DO("Config4");
		
		
		logger.info("Inside Test Reporter");
		try {

			String strTestCaseID = ObjOutput_DO.get("TCID");
			String strRiskTrackerReference = ObjOutput_DO.get("Risk Tracker Reference");
			String strStartTime = ObjOutput_DO.get("StartTime");
			XSSFSheet outputsheet = O_workbook4.getSheet("OutputSheet");
			
			int intTotalRows = outputsheet.getLastRowNum() + 1;
			String[] arrOutputValue = { strTestCaseID, Status4, strRiskTrackerReference, Summary, Description, strStartTime };
			outputsheet.createRow(intTotalRows);
			XSSFRow valRow = outputsheet.getRow(intTotalRows);
			XSSFCellStyle dataStyle = excel.getDataStyle(O_workbook4);
			for (int i = 0; i <= 5; i++) {
				valRow.createCell(i);
				valRow.getCell(i).setCellValue(arrOutputValue[i]);
				valRow.getCell(i).setCellStyle(dataStyle);
			}
			// Main.O_workbook.close();
			excel.saveExcelFile("Config4", strFilePathwithName, O_workbook4);

		} catch (NullPointerException e) {
			logger.error("Workbook Instance Not Found");
		} catch (FileNotFoundException e) {
			logger.error("File Not Found");
		} finally {
			try {
				switch (Status4.toUpperCase()) {
				case "ERROR":
					CommonLib.CaptureErrorScreenshot(driver4, "Config4");
					TestReporter.getTest().log(LogStatus.ERROR,
							Summary + " >>> " + Description + " >>> " + driver4.getCurrentUrl());
					break;
				case "FAIL":
					CommonLib.CaptureErrorScreenshot(driver4, "Config4");
					TestReporter.getTest().log(LogStatus.FAIL, Summary + " >>> " + Description
							+ " >>> URL To access the failed case: " + driver4.getCurrentUrl());
					break;
				case "INFO":
					TestReporter.getTest().log(LogStatus.INFO, Summary + " >>> " + Description);
					break;
				case "PASS":
					TestReporter.getTest().log(LogStatus.PASS, Summary + " >>> " + Description);
					break;
				case "WARNING":
					TestReporter.getTest().log(LogStatus.WARNING, Summary + " >>> " + Description);
					break;
				case "FATAL":
					TestReporter.getTest().log(LogStatus.FATAL, Summary + " >>> " + Description);
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @return the o_workbook4
	 */
	public static XSSFWorkbook getO_workbook4() {
		return O_workbook4;
	}
	
	/**
	 * @return the reports4
	 */
	public static ExtentReports getReport() {
		return report;
	}
	
	/**
	 * @return the fullScreenshotFilePath
	 */
	public static String getFullScreenshotFilePath() {
		return FullScreenshotFilePath;
	}

	/**
	 * @param fullScreenshotFilePath the fullScreenshotFilePath to set
	 */
	public static void setFullScreenshotFilePath(String fullScreenshotFilePath) {
		FullScreenshotFilePath = fullScreenshotFilePath;
	}
	
	/**
	 * @return the screenshotFilePath
	 */
	public static String getScreenshotFilePath() {
		return ScreenshotFilePath;
	}
	
	/**
	 * @return the testCaseName
	 */
	public static String getTestCaseName() {
		return TestCaseName;
	}

	/**
	 * @return the testCaseDesc
	 */
	public static String getTestCaseDesc() {
		return TestCaseDesc;
	}

	/**
	 * @return the testCaseModule
	 */
	public static String getTestCaseModule() {
		return TestCaseModule;
	}
	
	/**
	 * @return the strFilePathwithName
	 */
	public static String getStrFilePathwithName() {
		return strFilePathwithName;
	}
	
	/**
	 * @return the strDriverSessionTest
	 */
	public String getStrDriverSessionTest() {
		return strDriverSessionTest;
	}
}
