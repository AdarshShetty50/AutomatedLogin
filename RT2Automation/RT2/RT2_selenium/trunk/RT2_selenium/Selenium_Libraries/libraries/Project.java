package libraries;
import java.awt.AWTException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.relevantcodes.extentreports.ExtentReports;

import libraries.ObjectRepository;
import pages.DMS;
import pages.RTRegression;
import pages.RiskCopy;
import pages.RiskDashboard;
import pages.RiskDetails;
import pages.RiskFirmOrder;
import pages.RiskNTU;
import pages.RiskOverview;
import pages.RiskReinstate;
import pages.RiskRenewal;
import pages.RiskSubmitToBrokerOps;
import pages.RiskTracker;
import pages.RiskTrackerHomePage;
import pages.RiskTrackerLogOut;
import pages.RiskTrackerLogin;

@SuppressWarnings("unused")
public class Project {

	ReadExcel excel = new ReadExcel();
	static CommonLib common = new CommonLib();
	static BusinessRules businessRules = new BusinessRules();
	static ObjectRepository repository = new ObjectRepository();

	
	
	public void RunTestCase(RemoteWebDriver driver, String strConfig, String strTestingType, String TestCaseName,
			String strUserRole, String strUserName, String strPassword) 
					throws MalformedURLException, InterruptedException, AWTException {
		
		RiskTrackerLogin loginPage = new RiskTrackerLogin(driver, strConfig, strUserName, strPassword);
		RiskTrackerHomePage homePage = new RiskTrackerHomePage(driver, strConfig, strUserRole);
		RiskTracker rt  = new RiskTracker(driver, strConfig,  strTestingType, strUserRole);
		RiskDashboard dashboard = new RiskDashboard(driver, strConfig, strTestingType, strUserRole);
		
		DMS loc = new DMS(driver, strConfig, strTestingType, strUserRole);
		RiskOverview riskOverview = new RiskOverview(driver, strConfig, strTestingType,  strUserRole);
		RiskDetails riskDetails = new RiskDetails(driver, strConfig, strTestingType, strUserRole);
		RiskFirmOrder fo = new RiskFirmOrder(driver, strConfig, strTestingType,  strUserRole);
		RiskSubmitToBrokerOps sbo =new RiskSubmitToBrokerOps(driver, strConfig, strTestingType, strUserRole);
		RiskNTU ntu = new RiskNTU(driver, strConfig, strTestingType, strUserRole);
		RiskReinstate reinstate = new RiskReinstate(driver, strConfig, strTestingType, strUserRole);
		RiskCopy copy =new RiskCopy(driver, strConfig, strTestingType, strUserRole);
		RiskRenewal renew = new RiskRenewal(driver, strConfig, strTestingType,  strUserRole); 
		
		RTRegression reg = new RTRegression(driver, strConfig, strUserRole);
		RiskTrackerLogOut logoutPage = new RiskTrackerLogOut(driver, strConfig);
		
	}


	
	void CaptureValuesandExecute(RemoteWebDriver driver, String strConfig, XSSFSheet DataWorksheet, int intTestCaseRow) {
		// Declarations
		int intTotalCells = excel.columncount(DataWorksheet, intTestCaseRow);
		//CommonLib.getLogger().info("Total Columns for this testcase are " + intTotalCells);
		String[] arrHeaders = new String[intTotalCells+1];
		String[] arrValues = new String[intTotalCells+1];
		//CommonLib.getLogger().info(arrHeaders.length);
		//CommonLib.getLogger().info(arrValues.length);
		// Logic
		for (int i = 0; i < intTotalCells; i = i + 1) {
			arrHeaders[i] = excel.CellValue(DataWorksheet, 0, i);
			arrValues[i] = excel.CellValue(DataWorksheet, intTestCaseRow, i);
		}
		ExecuteRow(driver, strConfig, arrHeaders, arrValues);
	}
public static boolean alternateclick (RemoteWebDriver driver, String strConfig,String Header)
{
	try {
	boolean b=false;
	int attempts = 0;
	//while(attempts < 3){
		//try{
	String[] arrObjElement = ObjectRepository.GetObjectlocators(Header);
	By byObjElement = ObjectRepository.GetObject(arrObjElement);
//	System.out.println(byObjElement.toString());
//	WebElement element=driver.findElement(byObjElement);
//	JavascriptExecutor executor = (JavascriptExecutor) driver;
//	WebDriverWait wait = new WebDriverWait(driver, 30);
//	wait.until(ExpectedConditions.elementToBeClickable(byObjElement));
//	driver.executeScript("arguments[0].scrollIntoView(true);", element);
//	executor.executeScript("arguments[0].click();", element);
//	b=true;
//		} catch(StaleElementReferenceException e) {}
//		attempts++;}
//	if(b==false)
//	{
		while(attempts < 3){
			try{

		System.out.println(byObjElement.toString());
		driver.findElement(byObjElement).click();
			b=true;
			} catch(StaleElementReferenceException e) {}
			attempts++;}
	//}
	return b;
} catch (NoSuchElementException e) {
	TestReporter.Error(driver, strConfig, "JS Error", "Unable to click on element: " + Header);
	return false;
} catch (Exception e) {
	TestReporter.Error(driver, strConfig, "JS Error", "Unable to click on element: " + Header + " - " + e.getClass().getCanonicalName());
	return false;
}
	
	
}
	
	void ExecuteRow(RemoteWebDriver driver, String strConfig, String[] arrHeaders, String[] arrValues) {
		// Declarations
		int intTotalarrValues;
		//String[] Arr_ObjectDetails = new String[2];

		// Logic
		intTotalarrValues = arrValues.length;
		for (int i = 0; i < (intTotalarrValues - 1); i = i + 1) {
			if (arrHeaders[i].equalsIgnoreCase("iTotalIterations")) {
				// Do Nothing
			} else if (arrHeaders[i].equalsIgnoreCase("iCurrentIteration")) {
				// Do Nothing
			} else if (arrHeaders[i].equalsIgnoreCase("TCID")){
				// Do Nothing
			} else if (arrHeaders[i].equalsIgnoreCase("strSheetName")) {
				// Do Nothing
			} else if (arrValues[i] != "") {
				// = ObjectRepository.GetObjectlocators(arrHeaders[i]);
				PerformOperation(driver, strConfig, arrHeaders[i], arrValues[i]);
			} else if (arrValues[i] == "Default") {

			}
		}
	}

	public static void ExecuteRow(RemoteWebDriver driver, String strConfig, String Header, String Value){
		if (Header.equalsIgnoreCase("iTotalIterations")) {
			// Do Nothing
		} else if (Header.equalsIgnoreCase("iCurrentIteration")) {
			// Do Nothing
		} else if (Header.equalsIgnoreCase("TCID")){
			// Do Nothing
		} else if (Header.equalsIgnoreCase("strSheetName")) {
			// Do Nothing
		} else if (Value != "") {
			Reporter.log("Performing Operation on: " + Header + " Operation is: " + Value);
			TestReporter.Info(driver, strConfig, "Performing Operation on: ", Header + " Operation is: " + Value);
			PerformOperation(driver, strConfig, Header, Value);
		} else if (Value == "Default") {

		}
	}

	
	public static void PerformOperation(RemoteWebDriver driver, String strConfig, String Header, String Value) {
		
		//STO - Search Text Option
		//DDO - Dropdown Option
		//DTI - Dropdown Text Input
		By by = null;
		Capabilities cap = (driver).getCapabilities();
		//CommonLib.getLogger().info(Arr_ObjectDetails[0] + " " + Arr_ObjectDetails[1]);
		String[] SplitHeader = Header.split("_");
		String ObjectType = SplitHeader[0];
		boolean blnReturnVal=false;
		String[] Arr_ObjectDetails;
		//CommonLib.getLogger().info("Object Type is: " + ObjectType);
		switch (ObjectType.trim()) {
		case "BR":
			if(Value != "False"){
				businessRules.SelectBR(driver, strConfig, SplitHeader[2], Value);
			} else {
				CommonLib.getLogger(strConfig).info("Business Rule Verification skipped for rule " + Header);
				//Reporter.log("<BR>Business Rule Verification skipped for rule <B>" + Header + "</B><BR>");
			}
			break;
		case "LN":
		
			if (cap.getBrowserName().equalsIgnoreCase("chrome") || cap.getBrowserName().equalsIgnoreCase("firefox")) {

				blnReturnVal = CommonLib.click_On_Buttonheader(driver, strConfig, Header);

			}
			else {
				new Actions(driver).moveToElement(repository.GetObject(driver, strConfig, Header));
				blnReturnVal = common.JSClick(driver, strConfig, Header);
			}
			if (blnReturnVal) {
				CommonLib.getLogger(strConfig).info(Header + " Clicked Successfully");
				TestReporter.Pass(driver, strConfig, "Clicked Successfully: ", Header);
			}
			// CommonLib.waitForPageLoad(driver, strConfig);
			CommonLib.waitForLoad(driver, strConfig);
			CommonLib.waitForJQueryToBeActive(driver, strConfig);
			break;
		case "BTN":
		case "STO":
		case "DDO":

			if (cap.getBrowserName().equalsIgnoreCase("chrome") || cap.getBrowserName().equalsIgnoreCase("firefox")) {
				try {
					// blnReturnVal=alternateclick(driver, strConfig, Header);
					blnReturnVal = CommonLib.click_On_Buttonheader(driver, strConfig, Header);
				} catch (Exception e) {
					WebElement e1 = repository.GetObject(driver, strConfig, Header);
					// new Actions(driver).moveToElement(e1).perform();
					driver.executeScript("arguments[0].click();", e1);
				}
			} else {
				new Actions(driver).moveToElement(repository.GetObject(driver, strConfig, Header));
				blnReturnVal = common.JSClick(driver, strConfig, Header);
			}
			if (blnReturnVal) {
				CommonLib.getLogger(strConfig).info(Header + " Clicked Successfully");
				TestReporter.Pass(driver, strConfig, "Clicked Successfully: ", Header);
			}
			// CommonLib.waitForPageLoad(driver, strConfig);
			CommonLib.waitForLoad(driver, strConfig);
			CommonLib.waitForJQueryToBeActive(driver, strConfig);
			break;
		
		case "CBO":
			common.select_comboboxOption(driver, strConfig, Header, Value);
			break;
		case "CBOS":
			Arr_ObjectDetails = ObjectRepository.GetObjectlocators(Header);
			by = ObjectRepository.GetObject(Arr_ObjectDetails);
			CommonLib.waitForLoad(driver, strConfig);
			CommonLib.WaitForElementToBeClickable(driver, strConfig, Header);
			WebElement objCBOS = driver.findElement(by);
			new Actions(driver).moveToElement(objCBOS).perform();
			common.JSClick(driver, strConfig, objCBOS);
			objCBOS.sendKeys(Keys.BACK_SPACE);
			//			objCBOS.click();
			switch(Value){
			case "SelectLast":
				objCBOS.sendKeys(Keys.DOWN);
				objCBOS.sendKeys(Keys.DOWN);
				objCBOS.sendKeys(Keys.RETURN);
				break;
			default:
				objCBOS.sendKeys(Value);
				objCBOS.sendKeys(Keys.RETURN);
				break;
			}
			break;
		case "CHK":
			
			Arr_ObjectDetails = ObjectRepository.GetObjectlocators(Header);
			by = ObjectRepository.GetObject(Arr_ObjectDetails);
			CommonLib.waitForLoad(driver, strConfig);
			WebElement objCheckBox = driver.findElement(by);
			driver.executeScript("arguments[0].scrollIntoView(true);", objCheckBox);
			
			Boolean strStatus = objCheckBox.isSelected();
			System.out.println("strStatus" + strStatus);
			if (strStatus == true && Value.equalsIgnoreCase("uncheck")){
				common.JSClick(driver, strConfig, objCheckBox);
			}
			else if (strStatus == false && Value.equalsIgnoreCase("check")){
				common.JSClick(driver, strConfig, objCheckBox);
			}
			else if (strStatus == true && Value.equalsIgnoreCase("check")){
				//Do nothing
			}
			else if (Value.equalsIgnoreCase("click")){
				common.JSClick(driver, strConfig, objCheckBox);
			}
			CommonLib.waitForLoad(driver, strConfig);
			break;
			
			
		/*	Arr_ObjectDetails = ObjectRepository.GetObjectlocators(Header);
			by = ObjectRepository.GetObject(Arr_ObjectDetails);
			CommonLib.waitForLoad(driver, strConfig);
			WebElement objCheckBox = driver.findElement(by);
			Boolean strStatus = objCheckBox.isSelected();
			System.out.println("strStatus" + strStatus);
			if (Value.equalsIgnoreCase("click")) {
				common.JSClick(driver, strConfig, objCheckBox);
				break;

			}

			new Actions(driver).moveToElement(objCheckBox).perform();
			// Boolean strStatus = objCheckBox.isSelected();
			if (strStatus == true && Value.equalsIgnoreCase("uncheck")) {
				common.JSClick(driver, strConfig, objCheckBox);
			} else if (strStatus == false && Value.equalsIgnoreCase("check")) {
				common.JSClick(driver, strConfig, objCheckBox);
			} else if (strStatus == true && Value.equalsIgnoreCase("check")) {
				// Do nothing
			}

			CommonLib.waitForLoad(driver, strConfig);
			break; */
		case "RDO":
			Header = Header + "_" + Value;
			Arr_ObjectDetails = ObjectRepository.GetObjectlocators(Header);
			by = ObjectRepository.GetObject(Arr_ObjectDetails);
			CommonLib.waitForLoad(driver, strConfig);
			common.JSClick(driver, by);
			break;
		
		case "LIST":
			String[] arrValue = Value.split(";");
			for(int iVal=0; iVal < arrValue.length; iVal++) {
				String value = arrValue[iVal];
				String strOptionXpath = ObjectRepository.GetObjectlocators(Header)[1] +"[text()='"+ value.trim() +"']";
				driver.findElement(By.xpath(strOptionXpath)).click();
				CommonLib.WaitForElementToBeClickable(driver, strConfig, strOptionXpath);
				common.JSClick(driver, strConfig, "BTN_AddTemplateDetail_MoveToRight");
			}
			break;
		case "TXT":
			Arr_ObjectDetails = ObjectRepository.GetObjectlocators(Header);
			by = ObjectRepository.GetObject(Arr_ObjectDetails);
			CommonLib.waitForLoad(driver, strConfig);
			//CommonLib.WaitForElement(driver, strConfig, Header);
			new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(driver.findElement(by)));
			driver.findElement(by).sendKeys(Keys.BACK_SPACE);
			driver.findElement(by).clear();
			driver.findElement(by).sendKeys(Value);
			driver.findElement(by).sendKeys(Keys.TAB);
			break;
			
		case "DTI":
			Arr_ObjectDetails = ObjectRepository.GetObjectlocators(Header);
			by = ObjectRepository.GetObject(Arr_ObjectDetails);
			CommonLib.waitForLoad(driver, strConfig);
			new WebDriverWait(driver, 2); 
			driver.findElement(by).sendKeys(Value);
			driver.findElement(by).sendKeys(Keys.ENTER);
			break;
		case "NAV":
			
		
		
			if(cap.getBrowserName().equalsIgnoreCase("chrome")||cap.getBrowserName().equalsIgnoreCase("firefox")) {
				System.out.println("Hovering");
			String javaScript = "var evObj = document.createEvent('MouseEvents');" +
	                "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" +
	                "arguments[0].dispatchEvent(evObj);";

				((JavascriptExecutor)driver).executeScript(javaScript, repository.GetObject(driver, strConfig, Header));
				repository.GetObject(driver, strConfig, Value).click();
				TestReporter.Info(driver, strConfig, "Hovered and Clicked Successfully :", Header+"->"+Value );
	}
			else {
			new Actions(driver).moveToElement(repository.GetObject(driver, strConfig, Header)).perform();
			boolean blnReturnVal11 = common.JSClick(driver, strConfig, Value);
			
			if (blnReturnVal11) {
				CommonLib.getLogger(strConfig).info(Header + "Hovered and Clicked Successfully");
				TestReporter.Info(driver, strConfig, "Hovered and Clicked Successfully :", Header+"->"+Value );
			}
			CommonLib.waitForPageLoad(driver, strConfig);
			//common.waitForLoad(driver, strConfig);
			}
			    break;
			       
			
		}
	}
}
