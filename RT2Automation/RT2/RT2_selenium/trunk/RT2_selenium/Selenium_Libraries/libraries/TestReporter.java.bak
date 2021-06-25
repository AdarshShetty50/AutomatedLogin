package libraries;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import tests.Test1;
import tests.Test2;
import tests.Test3;
import tests.Test4;
import tests.Test5;

@SuppressWarnings("unused")
public class TestReporter {
	static LinkedHashMap<String, String> ObjOutput_DO;
	public ExtentTest test;
	public ExtentReports reports;
	static ReadExcel excel = new ReadExcel();
	public static LinkedHashMap<Long, ExtentTest> extentTestMap = new LinkedHashMap<Long, ExtentTest>();
	// static LinkedHashMap<String, String> ObjDriverDetails_DO =
	// DictionaryObjects.getObjDriverDetails_DO();
	public XSSFWorkbook outputWorkbook = null;

	private void Action(String strConfig, String Status, String Summary, String Description) {
		Logger logger;
		switch (strConfig) {
		case "Config1":
			logger = Test1.logger;
			break;
		case "Config2":
			logger = Test2.logger;
			break;
		case "Config3":
			logger = Test3.logger;
			break;
		case "Config4":
			logger = Test4.logger;
			break;
		case "Config5":
			logger = Test5.logger;
			break;
		default:
			logger = Test1.logger;
			break;
		}
		ObjOutput_DO = DictionaryObjects.getObjOutput_DO(strConfig);
		logger.info("Inside Test Reporter");
		try {
			String strTestCaseID = ObjOutput_DO.get("TCID");
			String strRiskTrackerReference = ObjOutput_DO.get("Risk Tracker Reference");
			String strStartTime = ObjOutput_DO.get("StartTime");
			XSSFSheet outputsheet = CommonLib.getOutputWorkbook(strConfig).getSheet("OutputSheet");

			int intTotalRows = outputsheet.getLastRowNum() + 1;
			String[] arrOutputValue = { strTestCaseID, Status, strRiskTrackerReference, Summary, Description,
					strStartTime };
			outputsheet.createRow(intTotalRows);
			XSSFRow valRow = outputsheet.getRow(intTotalRows);
			XSSFCellStyle dataStyle = excel.getDataStyle(CommonLib.getOutputWorkbook(strConfig));
			for (int i = 0; i <= 5; i++) {
				valRow.createCell(i);
				valRow.getCell(i).setCellValue(arrOutputValue[i]);
				valRow.getCell(i).setCellStyle(dataStyle);
			}

			excel.saveExcelFile(strConfig, CommonLib.getFilePathwithName(strConfig),
					CommonLib.getOutputWorkbook(strConfig));

		} catch (NullPointerException e) {

			logger.error("Workbook Instance Not Found");
		} catch (FileNotFoundException e) {

			logger.error("File Not Found");
		} finally {
			try {
				switch (Status.toUpperCase()) {
				case "ERROR":
					test.log(LogStatus.ERROR,
							Summary + " >>> " + Description + " >>> " + CommonLib.getDriver(strConfig).getCurrentUrl());
					break;
				case "FAIL":
					test.log(LogStatus.FAIL, Summary + " >>> " + Description + " >>> URL To access the failed case: "
							+ CommonLib.getDriver(strConfig).getCurrentUrl());
					break;
				case "INFO":
					test.log(LogStatus.INFO, Summary + " >>> " + Description);
					break;
				case "PASS":
					test.log(LogStatus.PASS, Summary + " >>> " + Description);
					break;
				case "WARNING":
					test.log(LogStatus.WARNING, Summary + " >>> " + Description);
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void Pass(RemoteWebDriver driver, String strConfig, String Summary, String Description) {
		switch (strConfig) {
		case "Config1":
			Test1 test1 = new Test1();
			test1.Action(driver, "Pass", Summary, Description);
			break;
		case "Config2":
			Test2 test2 = new Test2();
			test2.Action(driver, "Pass", Summary, Description);
			break;
		case "Config3":
			Test3 test3 = new Test3();
			test3.Action(driver, "Pass", Summary, Description);
			break;
		case "Config4":
			Test4 test4 = new Test4();
			test4.Action(driver, "Pass", Summary, Description);
			break;
		case "Config5":
			Test5 test5 = new Test5();
			test5.Action(driver, "Pass", Summary, Description);
			break;
		default:
			TestReporter reporter = new TestReporter();
			reporter.Action(strConfig, "Pass", Summary, Description);
			break;
		}
	}

	public static void Fail(RemoteWebDriver driver, String strConfig, String Summary, String Description) {
		switch (strConfig) {
		case "Config1":
			Test1 test1 = new Test1();
			test1.Action(driver, "Fail", Summary, Description);
			break;
		case "Config2":
			Test2 test2 = new Test2();
			test2.Action(driver, "Fail", Summary, Description);
			break;
		case "Config3":
			Test3 test3 = new Test3();
			test3.Action(driver, "Fail", Summary, Description);
			break;
		case "Config4":
			Test4 test4 = new Test4();
			test4.Action(driver, "Fail", Summary, Description);
			break;
		case "Config5":
			Test5 test5 = new Test5();
			test5.Action(driver, "Fail", Summary, Description);
			break;
		default:
			TestReporter reporter = new TestReporter();
			reporter.Action(strConfig, "Fail", Summary, Description);
			break;
		}
	}

	public static void Warning(RemoteWebDriver driver, String strConfig, String Summary, String Description) {
		switch (strConfig) {
		case "Config1":
			Test1 test1 = new Test1();
			test1.Action(driver, "Warning", Summary, Description);
			break;
		case "Config2":
			Test2 test2 = new Test2();
			test2.Action(driver, "Warning", Summary, Description);
			break;
		case "Config3":
			Test3 test3 = new Test3();
			test3.Action(driver, "Warning", Summary, Description);
			break;
		case "Config4":
			Test4 test4 = new Test4();
			test4.Action(driver, "Warning", Summary, Description);
			break;
		case "Config5":
			Test5 test5 = new Test5();
			test5.Action(driver, "Warning", Summary, Description);
			break;
		default:
			TestReporter reporter = new TestReporter();
			reporter.Action(strConfig, "Warning", Summary, Description);
			break;
		}
	}

	public static void Info(RemoteWebDriver driver, String strConfig, String Summary, String Description) {
		switch (strConfig) {
		case "Config1":
			Test1 test1 = new Test1();
			test1.Action(driver, "Info", Summary, Description);
			break;
		case "Config2":
			Test2 test2 = new Test2();
			test2.Action(driver, "Info", Summary, Description);
			break;
		case "Config3":
			Test3 test3 = new Test3();
			test3.Action(driver, "Info", Summary, Description);
			break;
		case "Config4":
			Test4 test4 = new Test4();
			test4.Action(driver, "Info", Summary, Description);
			break;
		case "Config5":
			Test5 test5 = new Test5();
			test5.Action(driver, "Info", Summary, Description);
			break;
		default:
			TestReporter reporter = new TestReporter();
			reporter.Action(strConfig, "Info", Summary, Description);
			break;
		}
	}

	public static void Error(RemoteWebDriver driver, String strConfig, String Summary, String Description) {
		switch (strConfig) {
		case "Config1":
			Test1 test1 = new Test1();
			test1.Action(driver, "Error", Summary, Description);
			break;
		case "Config2":
			Test2 test2 = new Test2();
			test2.Action(driver, "Error", Summary, Description);
			break;
		case "Config3":
			Test3 test3 = new Test3();
			test3.Action(driver, "Error", Summary, Description);
			break;
		case "Config4":
			Test4 test4 = new Test4();
			test4.Action(driver, "Error", Summary, Description);
			break;
		case "Config5":
			Test5 test5 = new Test5();
			test5.Action(driver, "Error", Summary, Description);
			break;
		default:
			TestReporter reporter = new TestReporter();
			reporter.Action(strConfig, "Error", Summary, Description);
			break;
		}
	}

	public static void Fatal(RemoteWebDriver driver, String strConfig, String Summary, String Description) {
		switch (strConfig) {
		case "Config1":
			Test1 test1 = new Test1();
			test1.Action(driver, "Fatal", Summary, Description);
			break;
		case "Config2":
			Test2 test2 = new Test2();
			test2.Action(driver, "Fatal", Summary, Description);
			break;
		case "Config3":
			Test3 test3 = new Test3();
			test3.Action(driver, "Fatal", Summary, Description);
			break;
		case "Config4":
			Test4 test4 = new Test4();
			test4.Action(driver, "Fatal", Summary, Description);
			break;
		case "Config5":
			Test5 test5 = new Test5();
			test5.Action(driver, "Fatal", Summary, Description);
			break;
		default:
			TestReporter reporter = new TestReporter();
			reporter.Action(strConfig, "Fatal", Summary, Description);
			break;
		}
	}

	public static void StartTest(String strConfig) {

		ExtentReports reports = Test1.report;
		ExtentTest test = null;
		String TestCaseModule = "";
		String TestCaseName = "";
		String TestCaseDesc = "";
		switch (strConfig) {
		case "Config1":
			TestCaseModule = Test1.getTestCaseModule();
			TestCaseName = Test1.getTestCaseName();
			TestCaseDesc = Test1.getTestCaseDesc();
			break;
		case "Config2":
			TestCaseModule = Test2.getTestCaseModule();
			TestCaseName = Test2.getTestCaseName();
			TestCaseDesc = Test2.getTestCaseDesc();
			break;
		case "Config3":
			TestCaseModule = Test3.getTestCaseModule();
			TestCaseName = Test3.getTestCaseName();
			TestCaseDesc = Test3.getTestCaseDesc();
			break;
		case "Config4":
			TestCaseModule = Test4.getTestCaseModule();
			TestCaseName = Test4.getTestCaseName();
			TestCaseDesc = Test4.getTestCaseDesc();
			break;
		case "Config5":
			TestCaseModule = Test5.getTestCaseModule();
			TestCaseName = Test5.getTestCaseName();
			TestCaseDesc = Test5.getTestCaseDesc();
			break;
		default:
			TestCaseModule = Test1.getTestCaseModule();
			TestCaseName = Test1.getTestCaseName();
			TestCaseDesc = Test1.getTestCaseDesc();
			break;
		}
		try {
			test = reports.startTest(TestCaseName, "Test Case Description: " + TestCaseDesc);
			test.assignCategory(TestCaseModule);
			extentTestMap.put(Thread.currentThread().getId(), test);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ExtentTest getTest() {
		return extentTestMap.get(Thread.currentThread().getId());
	}

	public static void EndTest() {
		Test1.report.endTest(extentTestMap.get(Thread.currentThread().getId()));

	}

	public static void Log(String strConfig, String LogSummary) {
		ObjOutput_DO = DictionaryObjects.getObjOutput_DO(strConfig);
		String strTestCaseID = ObjOutput_DO.get("TCID");
		String strRiskTrackerReference = ObjOutput_DO.get("strRiskTrackerReference");
		String strStartTime = ObjOutput_DO.get("StartTime");
		String strCurrDate = CommonLib.GetCurrentDate();
		strCurrDate = strCurrDate.replace("/", "-");
		String strFilePath = ProjectConstants.OUTPUTSHEETPATH + "/" + strCurrDate;
		String strFileName = "Output_Log_" + strCurrDate + ".txt";
		String strFilePathwithName = strFilePath + "/" + strFileName;
		File filePath = new File(strFilePath);
		Boolean b = false;
		if (!filePath.exists()) {
			b = filePath.mkdirs();
		}
		if (b) {
			CommonLib.getLogger(strConfig).info("file created successfully.");
		}
	}
}
