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

public class Test1 {
	Constants constants = new Constants();
	CommonLib common = new CommonLib();
	
	public static XSSFWorkbook D_workbook;
	public static XSSFWorkbook I_workbook;
	
	public Instant startTest1, endTest1;
	public int TCRow_No;
	public static Logger logger;
	public static int iScreenNum;
	public static String strCurrDate;
	public static String strCurrTime;
	public static String strFilePath;
	private static String ScreenshotFilePath;
	private static String strFileName;
	private static String strFilePathwithName;
	private static XSSFWorkbook O_workbook1;
	private static String FullScreenshotFilePath;
	private static String TestCaseName, TestCaseDesc, TestCaseModule,totalTime;
	public static ExtentReports report;
	public static RemoteWebDriver driver;
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

		if (report == null) {
			report = new ExtentReports((ProjectConstants.OUTPUTSHEETPATH + "/" + strCurrDate + "/Automation_Report_"
					+ strCurrDate + "_" + strCurrTime + ".html"));

		}

		strFilePath = ProjectConstants.OUTPUTSHEETPATH + "/" + strCurrDate + "/Test1";
		ScreenshotFilePath = ProjectConstants.OUTPUTSHEETPATH + "/" + strCurrDate + "/Test1/Screenshots";
		strFileName = "Output_Summary_Test1_" + strCurrDate + ".xlsx";
		strFilePathwithName = strFilePath + "/" + strFileName;

		O_workbook1 = constants.getOutPutWorkbook(strFilePathwithName);
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
		Constants.DefaultDO_GridDetails.put(Thread.currentThread().getId(), "Test1");
	}
	
	@Test
	public void Test_1() throws InterruptedException {
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

			report.loadConfig(new File("extent-config.xml"));
			report.addSystemInfo("User Name", System.getProperty("user.name"));
			report.addSystemInfo("Browser Name", "Chrome");
			report.addSystemInfo("Environment", Constants.strEnvironment);
			report.addSystemInfo("OS", System.getenv("OS"));

			startTest1 = Instant.now();
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
						if (TCExecute.equalsIgnoreCase("Yes") && strNode.equalsIgnoreCase("Node 1")) {
							for (int j = 0; j < intTCIterations; j++) {
								// Before Execution
								driver = CommonLib.getRemoteDriver("Chrome");
								strDriverSessionTest = driver.getWindowHandle();
								TestReporter.StartTest("Config1");
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
									TestReporter.Info(driver, "Config1", "User Role", "Logging into Risk Tracker with User Role:"+strUserRole);
									common.enter_URL(driver, "Config1", Constants.strRTURL);
									RunProject.RunTestCase(driver, "Config1", strTestingType, TestCaseName, strUserRole, strUsername, strPassword);

								} catch (IOException e) {
									e.printStackTrace();
								} catch (NoSuchElementException e) {
									e.printStackTrace();
									StringWriter sw = new StringWriter();
									e.printStackTrace(new PrintWriter(sw));
									Test1.logger.fatal("Stacktrace -> " + sw.toString());
									TestReporter.Fail(driver, "Config1", "NoSuchElementException", sw.toString());
								}
								// After Execution
								driver.close();
								driver.quit();
								TestReporter.EndTest();
								report.flush();
							}
						}
					}
				} catch (Exception e) {
					if (!driver.toString().contains("(null)")) {
						StringWriter sw = new StringWriter();
						e.printStackTrace(new PrintWriter(sw));
						Test1.logger.fatal("Stacktrace -> " + sw.toString());
						TestReporter.Fatal(driver, "Config1", "Unhandled Exception", sw.toString());
						driver.close();
						driver.quit();
						TestReporter.EndTest();
						report.flush();
					} else {
						e.printStackTrace();
						TestReporter.Warning(driver, "Config1", "Driver Null", "Exiting Test Case");
						report.flush();
					}
				}
			}
			endTest1 = Instant.now();
			Duration timeElapsed = Duration.between(startTest1, endTest1);
			totalTime = (timeElapsed.toMillis() / 1000) / 60 + " mins & " + (timeElapsed.toMillis() / 1000) % 60
					+ " secs";
			logger.info("Execution Completed in " + totalTime);

			report.flush();
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			Test1.logger.fatal("Stacktrace -> " + sw.toString());
			TestReporter.Fatal(driver, "Config1", "Unhandled Exception (FATAL)", sw.toString());
		}
	}
	
	
	
	public void Action(RemoteWebDriver driver, String Status1, String Summary, String Description) {
		ReadExcel excel = new ReadExcel();
		LinkedHashMap<String, String> ObjOutput_DO = DictionaryObjects.getObjOutput_DO("Config1");
		
		
		logger.info("Inside Test Reporter");
		try {

			String strTestCaseID = ObjOutput_DO.get("TCID");
			String strRiskTrackerReference = ObjOutput_DO.get("Risk Tracker Reference");
			String strStartTime = ObjOutput_DO.get("StartTime");
			XSSFSheet outputsheet = O_workbook1.getSheet("OutputSheet");
			
			int intTotalRows = outputsheet.getLastRowNum() + 1;
			String[] arrOutputValue = { strTestCaseID, Status1, strRiskTrackerReference, Summary, Description, strStartTime };
			outputsheet.createRow(intTotalRows);
			XSSFRow valRow = outputsheet.getRow(intTotalRows);
			XSSFCellStyle dataStyle = excel.getDataStyle(O_workbook1);
			for (int i = 0; i <= 5; i++) {
				valRow.createCell(i);
				valRow.getCell(i).setCellValue(arrOutputValue[i]);
				valRow.getCell(i).setCellStyle(dataStyle);
			}
			// Main.O_workbook.close();
			excel.saveExcelFile("Config1", strFilePathwithName, O_workbook1);

		} catch (NullPointerException e) {
			logger.error("Workbook Instance Not Found");
		} catch (FileNotFoundException e) {
			logger.error("File Not Found");
		} finally {
			try {
				switch (Status1.toUpperCase()) {
				case "ERROR":
					CommonLib.CaptureErrorScreenshot(driver, "Config1");
					TestReporter.getTest().log(LogStatus.ERROR,
							Summary + " >>> " + Description + " >>> " + driver.getCurrentUrl());
					break;
				case "FAIL":
					CommonLib.CaptureErrorScreenshot(driver, "Config1");
					TestReporter.getTest().log(LogStatus.FAIL, Summary + " >>> " + Description
							+ " >>> URL To access the failed case: " + driver.getCurrentUrl());
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
	 * @return the o_workbook1
	 */
	public static XSSFWorkbook getO_workbook1() {
		return O_workbook1;
	}
	
	/**
	 * @return the reports1
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
