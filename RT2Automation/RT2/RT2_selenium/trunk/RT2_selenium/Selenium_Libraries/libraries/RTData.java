package libraries;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.relevantcodes.extentreports.ExtentReports;


public class RTData {
	
	
	static CommonLib common = new CommonLib();
	Constants constants = new Constants();
	//Project project=new Project();
	static ReadExcel excel = new ReadExcel();
	Data_Interaction data_Interaction = new Data_Interaction();
	public ObjectRepository repository = new ObjectRepository();
	public static String strTextFromHeader;
	public static String strApplicationStatus;
	public static ExtentReports report;
	
	
	

	public static String getAutomationReportReceiverPwd(String username) {
		String automationReport_ReceiverPwd_Encrypted = null;
		String automationReport_ReceiverPwd_Decrypted = null;

		XSSFSheet dataSelectorSheet = excel.readXLSXfile(ProjectConstants.DRIVEREXCEL,
				ProjectConstants.DATASELECTORSHEET);
		int rowNum = excel.RowNumber(dataSelectorSheet, username, 1);

		automationReport_ReceiverPwd_Encrypted = excel.CellValue(dataSelectorSheet, rowNum, 2);
		automationReport_ReceiverPwd_Decrypted = CommonLib.DecryptPassword(automationReport_ReceiverPwd_Encrypted);

		return automationReport_ReceiverPwd_Decrypted;
	}
	
	
	

	public static String getRiskTracker_RefNo(RemoteWebDriver driver, String strConfig, String riskTrackerRefXpath) {
		String RTRefNo = null;
		try {
			WebElement riskTrackRefEle = driver.findElement(By.xpath(riskTrackerRefXpath));
			RTRefNo = riskTrackRefEle.getText();

		} catch (NoSuchElementException e) {
			TestReporter.Fail(driver, strConfig, "Risk Ref", "Risk Tracker Ref Number not generated");
			System.err.format("No Risk Tracker Ref Number Element Found to extract text" + e);
		}
		return RTRefNo;
	}
	
	
	public static String getExpiringUMR(RemoteWebDriver driver, String strConfig, String expringUMRXpath) {
		String ExpUMRNo = null;
		try {
			WebElement expringUMREle = driver.findElement(By.xpath(expringUMRXpath));
			ExpUMRNo = expringUMREle.getText();

		} catch (NoSuchElementException e) {
			TestReporter.Fail(driver, strConfig, "Expiring UMR", "not generated");
			System.err.format("Expiring UMR not generated");
		}
		return ExpUMRNo;
	}
	
	public static String getCurrentStageDate() {
		String currentDate;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
		LocalDateTime now = LocalDateTime.now();  
		currentDate = formatter.format(now).trim();
		
		String firstChar = currentDate.substring(0, 1);
		
		if(firstChar.contains("0")) {
			currentDate = currentDate.substring(1);
		}
		return currentDate;
	}
	
	
	
	public static String getCurrentDate_Plus_OneYear(String dateFormat) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
		LocalDate now = LocalDate.now(); 
		
		now.format(formatter);
		LocalDate yearLater = now.plusYears ( 1 );
		return yearLater.toString();
	}
	
	
	//Check Status Date populated is in the format "dd MMM yyyy"
	public static Boolean isValidStatusDateAndFormat(String strDate) {
		
		StringBuilder strDateSB = new StringBuilder(strDate);
		if(strDate.length()==10) {
			strDateSB.insert(0, 0);
			
		}
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
		
		try {
			dateFormatter.parse(strDateSB);
		} catch (DateTimeParseException e) {
			return false;
		}
		return true;

	}
	
	
	public static int generateRandomNumbers(int max, int min) {
		Random rn = new Random();
		int num = rn.nextInt(max - min + 1) + min;
		return num;
	}
	
	
	
	
	

}
