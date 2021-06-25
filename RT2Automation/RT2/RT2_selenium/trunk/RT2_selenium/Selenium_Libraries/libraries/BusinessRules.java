package libraries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.openqa.selenium.support.ui.Select;
import org.apache.bcel.Repository;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.Days360;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
//import org.apache.bcel.generic.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.security.UserAndPassword;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.ImagePath;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.util.Strings;
import tests.Test1;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.itextpdf.text.log.SysoCounter;
import com.relevantcodes.extentreports.ExtentReports;

import tests.Test1;
import tests.Test2;
import tests.Test3;
import tests.Test4;
import tests.Test5;
import libraries.Project;
import libraries.Constants;
import net.bytebuddy.asm.Advice.Enter;
import net.sourceforge.htmlunit.corejs.javascript.JavaScriptException;
import java.time.Duration;

@SuppressWarnings("unused")
public class BusinessRules {
	static CommonLib common = new CommonLib();
	Constants constants = new Constants();
	//Project project=new Project();
	static ReadExcel excel = new ReadExcel();
	Data_Interaction data_Interaction = new Data_Interaction();
	public static ObjectRepository repository = new ObjectRepository();
	public static String strTextFromHeader;
	public static String strApplicationStatus;
	public static ExtentReports report;

	public void SelectBR(RemoteWebDriver driver, String strConfig, String BusinessRule, String Value) {
		switch (BusinessRule) {
		case "BR_verifyElementsDisplayed":
	//		homePageElementsVerify(driver, strConfig);
		case "BR_calculatorverfiy":
			//calculator(driver, strConfig);


		default:
			CommonLib.getLogger(strConfig).info(BusinessRule + " is not yet implemented.");
		}
	}

	public void setStopTestCaseValue(RemoteWebDriver driver, String strConfig, String value){
		if(value.equalsIgnoreCase("Yes")){
			switch(strConfig){
			case "Config1":
				Test1.blnStopCurrentTestCase = true;
				break;
			case "Config2":
				Test2.blnStopCurrentTestCase = true;
				break;
			case "Config3":
				Test3.blnStopCurrentTestCase = true;
				break;
			case "Config4":
				Test4.blnStopCurrentTestCase = true;
				break;
			case "Config5":
				Test5.blnStopCurrentTestCase = true;
				break;
			default:
				Test1.blnStopCurrentTestCase = true;
			}
		}
	}
	
	public static void loginRiskTracker(RemoteWebDriver driver, String strConfig, String strUserName,
			String strPassword) {
		
		try {
			
		CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Login_IntegroAccount");
		Project.ExecuteRow(driver, strConfig, "BTN_Login_IntegroAccount", "Click");

		CommonLib.WaitForElementToBeVisible(driver, strConfig, "TXT_Login_IntegroUsername");
		Project.ExecuteRow(driver, strConfig, "TXT_Login_IntegroUsername", strUserName);

		CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Login_IntegroNext");
		Project.ExecuteRow(driver, strConfig, "BTN_Login_IntegroNext", "Click");

		CommonLib.WaitForElementToBeVisible(driver, strConfig, "TXT_Login_IntegroPassword");
		Project.ExecuteRow(driver, strConfig, "TXT_Login_IntegroPassword", CommonLib.DecryptPassword(strPassword));

		CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_SignIn");
		Project.ExecuteRow(driver, strConfig, "BTN_SignIn", "Click");
		CommonLib.waitForLoad(driver, strConfig);
		/*CommonLib.smallDelay();
		String[] locatorDetails = CommonLib.getLocatorDetails_From_ObjectRepository(driver, strConfig,
				"LBL_InvalidLogin_UserId_Or_Password");
		boolean InvalidLoginMsg_Present = CommonLib.isElementPresent(driver, strConfig, locatorDetails[1]);

		
		

		if (InvalidLoginMsg_Present) {
			WebElement InvalidLoginMsg = repository.GetObject(driver, strConfig, "LBL_InvalidLogin_UserId_Or_Password");

			String invalidLoginMsg = InvalidLoginMsg.getText();

			if (invalidLoginMsg.contentEquals(Constants.incorrectUserIdOrPwd_ErrorMsg)) {
				TestReporter.Fail(driver, strConfig, "Invalid Login", "UserId or Password is incorrect");
			}
		}

		else { */

			Boolean isStaySignedInPagePresent = ObjectRepository.GetObjects(driver, strConfig, "LBL_Stay_SignedIn")
					.size() > 0;

			if (isStaySignedInPagePresent) {
				Project.ExecuteRow(driver, strConfig, "BTN_Stay_SignedIn_NO", "Click");
			}
			CommonLib.waitForLoad(driver, strConfig);
		//}
			
		} catch (NoSuchElementException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (StaleElementReferenceException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (JavascriptException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (TimeoutException e) {
			TestReporter.Fail(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (NullPointerException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (IllegalArgumentException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		}

	}
	
	
	public static void verify_InvalidLogin_For_RiskTracker(RemoteWebDriver driver, String strConfig, String strUserName,
			String strPassword) {
		
		try {
		CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Login_IntegroAccount");
		Project.ExecuteRow(driver, strConfig, "BTN_Login_IntegroAccount", "Click");

		CommonLib.WaitForElementToBeVisible(driver, strConfig, "TXT_Login_IntegroUsername");
		Project.ExecuteRow(driver, strConfig, "TXT_Login_IntegroUsername", strUserName);

		CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Login_IntegroNext");
		Project.ExecuteRow(driver, strConfig, "BTN_Login_IntegroNext", "Click");

		CommonLib.WaitForElementToBeVisible(driver, strConfig, "TXT_Login_IntegroPassword");
		Project.ExecuteRow(driver, strConfig, "TXT_Login_IntegroPassword", CommonLib.DecryptPassword(strPassword));

		CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_SignIn");
		Project.ExecuteRow(driver, strConfig, "BTN_SignIn", "Click");
		CommonLib.waitForLoad(driver, strConfig);
		CommonLib.smallDelay();
		String[] locatorDetails = CommonLib.getLocatorDetails_From_ObjectRepository(driver, strConfig,
				"LBL_InvalidLogin_UserId_Or_Password");
		boolean InvalidLoginMsg_Present = CommonLib.isElementPresent(driver, strConfig, locatorDetails[1]);

		
		

		if (InvalidLoginMsg_Present) {
			WebElement InvalidLoginMsg = repository.GetObject(driver, strConfig, "LBL_InvalidLogin_UserId_Or_Password");

			String invalidLoginMsg = InvalidLoginMsg.getText();

			if (invalidLoginMsg.contentEquals(Constants.incorrectUserIdOrPwd_ErrorMsg)) {
				TestReporter.Pass(driver, strConfig, "Invalid Login", "UserId or Password is incorrect");
			}
		}

		else { 

			Boolean isStaySignedInPagePresent = ObjectRepository.GetObjects(driver, strConfig, "LBL_Stay_SignedIn")
					.size() > 0;

			if (isStaySignedInPagePresent) {
				Project.ExecuteRow(driver, strConfig, "BTN_Stay_SignedIn_NO", "Click");
			}
			CommonLib.waitForLoad(driver, strConfig);
		}
			
		} catch (NoSuchElementException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (StaleElementReferenceException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (JavascriptException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (TimeoutException e) {
			TestReporter.Fail(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (NullPointerException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (IllegalArgumentException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		}

	}
	
	
	
	
	
	
	public static void verifyHomePageDefaultElements(RemoteWebDriver driver, String strConfig) {

		try {
			// Wait for 3 links to be Clickable: User Support, Settings and Log Out to be
			// clickable
			CommonLib.waitForLoad(driver, strConfig);
			CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_UserSupport");
			String[] Array_SettingsLink = ObjectRepository
					.GetObjectlocators("LN_Settings");
			
			CommonLib.WaitForElementToBeClickable(driver, strConfig, String.format(
					Array_SettingsLink[1], Constants.defaultActiveOrg));
			CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_Logout_IntegroAccount");

		} catch (NoSuchElementException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (StaleElementReferenceException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (JavascriptException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (TimeoutException e) {
			TestReporter.Fail(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (NullPointerException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (IllegalArgumentException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		}

	}
	
	public static void verifyAccountHandlerHomePage(RemoteWebDriver driver, String strConfig) {
		
	}

	public static void verifyAdministratorHomePage(RemoteWebDriver driver, String strConfig) {

		CommonLib.waitForLoad(driver, strConfig);
		CommonLib.WaitForElementToBeVisible(driver, strConfig, "LBL_RiskDashboard_AdminDashboardText");

	}

	public static void verifySeniorInsuranceTechHomePage(RemoteWebDriver driver, String strConfig) {



	}

	public static void verifyOperationsTeamLeadHomePage(RemoteWebDriver driver, String strConfig) {



	}

	public static void verifyAsOperationsTechHomePage(RemoteWebDriver driver, String strConfig) {



	}

	public static void verifyAdministrationsTeamHomePage(RemoteWebDriver driver, String strConfig) {



	}
	
	public static void verifyRiskOverviewTabDefaultPopulatedFields(RemoteWebDriver driver, String strConfig) {
		
	}
	
	
	public static void verify_DashboardFilter_Limit(RemoteWebDriver driver, String strConfig, String sliderLowerLimit, String sliderUpperLimit) {
		try {

			CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_RiskDashboard_Filter");
			Project.ExecuteRow(driver, strConfig, "BTN_RiskDashboard_Filter", "Click");
			CommonLib.waitForLoad(driver, strConfig);
			CommonLib.WaitForElementToBeVisible(driver, strConfig, "LBL_RiskDashboard_Filter_Limit");
			
			CommonLib.WaitForElementToBeClickable(driver, strConfig, "LBL_RiskDashboard_Filter_Limit_LowerHandle");
			CommonLib.WaitForElementToBeClickable(driver, strConfig, "LBL_RiskDashboard_Filter_Limit_UpperHandle");
			
			CommonLib.changeSliderInterval(driver, strConfig, "LBL_RiskDashboard_Filter_Limit_LowerHandle", sliderLowerLimit);

			CommonLib.changeSliderInterval(driver, strConfig, "LBL_RiskDashboard_Filter_Limit_LowerHandle", sliderUpperLimit);
		} catch (NoSuchElementException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (StaleElementReferenceException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (JavascriptException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (TimeoutException e) {
			TestReporter.Fail(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (NullPointerException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (IllegalArgumentException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		}
	}
	
	
	
	public static void verify_Dashboard_RiskCard_HeaderColor_AsPer_RiskStatus(RemoteWebDriver driver, String strConfig, String strRiskRef) {
		try {
			CommonLib.waitForLoad(driver, strConfig);
			//Get Risk Status
			String[] Array_LN_RiskDashboard_RiskStatus = ObjectRepository
					.GetObjectlocators("LBL_RiskDashboard_RiskCard_StatusText");
			
			CommonLib.WaitForElementToBeVisible(driver, strConfig, String.format(
					Array_LN_RiskDashboard_RiskStatus[1], strRiskRef));
			
			WebElement RiskStatusEle = driver.findElementByXPath(String.format(Array_LN_RiskDashboard_RiskStatus[1], strRiskRef));

			String riskStatus = RiskStatusEle.getText();
			
			//Verify Risk Card Header Color as per the Status of Risk
			String[] Array_LN_RiskDashboard_RiskCard_HeaderColor = ObjectRepository
					.GetObjectlocators("LN_RiskDashboard_RiskCard_HeaderColor");
			
			if(riskStatus.trim().contentEquals("In Negotiation"))
			{
				String[] Array_LBL_RiskDashboard_RiskCard_ClassOfBusiness_Text = ObjectRepository
						.GetObjectlocators("LBL_RiskDashboard_RiskCard_ClassOfBusiness_Text");

				WebElement RiskCard_COBEle = driver.findElementByXPath(
						String.format(Array_LBL_RiskDashboard_RiskCard_ClassOfBusiness_Text[1], strRiskRef));

				String riskCard_COBValue = RiskCard_COBEle.getText();

				if (riskCard_COBValue.contentEquals("")) {
					CommonLib.WaitForElementToBeVisible(driver, strConfig, String
							.format(Array_LN_RiskDashboard_RiskCard_HeaderColor[1], strRiskRef, Constants.RGB_Grey));

					Boolean isGreyColor = CommonLib.isElementVisible(driver, strConfig,
							String.format(Array_LN_RiskDashboard_RiskCard_HeaderColor[1], strRiskRef, Constants.RGB_Grey));

					if (isGreyColor) {
						TestReporter.Pass(driver, strConfig,
								"Card Color for Risk Status: 'In Negotiation' [Risk Details Not saved] is", "Grey");
					} else {
						TestReporter.Fail(driver, strConfig,
								"Card Color for Risk Status: 'In Negotiation' [Risk Details Not saved] is not", "Grey");
					}

				}

				else if(!riskCard_COBValue.trim().isEmpty() && riskCard_COBValue.trim()!=null ){
					CommonLib.WaitForElementToBeVisible(driver, strConfig, String
							.format(Array_LN_RiskDashboard_RiskCard_HeaderColor[1], strRiskRef, Constants.RGB_Blue));

					Boolean isBlueColor = CommonLib.isElementVisible(driver, strConfig,
							 String.format(Array_LN_RiskDashboard_RiskCard_HeaderColor[1], strRiskRef, Constants.RGB_Blue));

					if (isBlueColor) {
						TestReporter.Pass(driver, strConfig,
								"Card Color for Risk Status: 'In Negotiation' [Risk Details Saved] is", "Blue");
					} else {
						TestReporter.Fail(driver, strConfig,
								"Card Color for Risk Status: 'In Negotiation' [Risk Details Saved] is not", "Blue");
					}
				}
			}
			
			else if(riskStatus.trim().contentEquals("NBI"))
			{
				CommonLib.WaitForElementToBeVisible(driver, strConfig, String
						.format(Array_LN_RiskDashboard_RiskCard_HeaderColor[1], strRiskRef, Constants.RGB_Blue));

				Boolean isBlueColor = CommonLib.isElementVisible(driver, strConfig,
						 String.format(Array_LN_RiskDashboard_RiskCard_HeaderColor[1], strRiskRef, Constants.RGB_Blue));

				if (isBlueColor) {
					TestReporter.Pass(driver, strConfig,
							"Card Color for Risk Status: 'NBI' [Risk Details Saved] is", "Blue");
				} else {
					TestReporter.Fail(driver, strConfig,
							"Card Color for Risk Status: 'NBI' [Risk Details Saved] is not", "Blue");
				}
			}
			
			
			else if(riskStatus.trim().contentEquals("Quote"))
			{
				CommonLib.WaitForElementToBeVisible(driver, strConfig, String
						.format(Array_LN_RiskDashboard_RiskCard_HeaderColor[1], strRiskRef, Constants.RGB_Blue));

				Boolean isBlueColor = CommonLib.isElementVisible(driver, strConfig,
						String.format(Array_LN_RiskDashboard_RiskCard_HeaderColor[1], strRiskRef, Constants.RGB_Blue));

				if (isBlueColor) {
					TestReporter.Pass(driver, strConfig,
							"Card Color for Risk Status: 'Quote' [Risk Details Saved] is", "Blue");
				} else {
					TestReporter.Fail(driver, strConfig,
							"Card Color for Risk Status: 'Quote' [Risk Details Saved] is not", "Blue");
				}
			}
			
			
			else if(riskStatus.trim().contentEquals("Quote/NBI"))
			{
				CommonLib.WaitForElementToBeVisible(driver, strConfig, String
						.format(Array_LN_RiskDashboard_RiskCard_HeaderColor[1], strRiskRef, Constants.RGB_Blue));

				Boolean isBlueColor = CommonLib.isElementVisible(driver, strConfig,
						String.format(Array_LN_RiskDashboard_RiskCard_HeaderColor[1], strRiskRef, Constants.RGB_Blue));

				if (isBlueColor) {
					TestReporter.Pass(driver, strConfig,
							"Card Color for Risk Status: 'Quote/NBI' [Risk Details Saved] is", "Blue");
				} else {
					TestReporter.Fail(driver, strConfig,
							"Card Color for Risk Status: 'Quote/NBI' [Risk Details Saved] is not", "Blue");
				}
			}
			
			else if(riskStatus.trim().contentEquals("Policy"))
			{
				CommonLib.WaitForElementToBeVisible(driver, strConfig, String
						.format(Array_LN_RiskDashboard_RiskCard_HeaderColor[1], strRiskRef, Constants.RGB_Green));

				Boolean isGreenColor = CommonLib.isElementVisible(driver, strConfig,
						String.format(Array_LN_RiskDashboard_RiskCard_HeaderColor[1], strRiskRef, Constants.RGB_Green));

				if (isGreenColor) {
					TestReporter.Pass(driver, strConfig,
							"Card Color for Risk Status: 'Policy' [Risk taken to Firm Order] is", "Green");
				} else {
					TestReporter.Fail(driver, strConfig,
							"Card Color for Risk Status: 'Policy' [Risk taken to Firm Order] is not", "Green");
				}
			}
			
			
			else if(riskStatus.trim().contentEquals("Submitted"))
			{
				CommonLib.WaitForElementToBeVisible(driver, strConfig, String
						.format(Array_LN_RiskDashboard_RiskCard_HeaderColor[1], strRiskRef, Constants.RGB_Green));

				Boolean isGreenColor = CommonLib.isElementVisible(driver, strConfig,
						String.format(Array_LN_RiskDashboard_RiskCard_HeaderColor[1], strRiskRef, Constants.RGB_Green));

				if (isGreenColor) {
					TestReporter.Pass(driver, strConfig,
							"Card Color for Risk Status: 'Submitted' [Risk Submitted to Broker Ops] is", "Green");
				} else {
					TestReporter.Fail(driver, strConfig,
							"Card Color for Risk Status: 'Submitted' [Risk Submitted to Broker Ops] is not", "Green");
				}
			}
			
			else if(riskStatus.trim().contains("NTU"))
			{
				CommonLib.WaitForElementToBeVisible(driver, strConfig, String
						.format(Array_LN_RiskDashboard_RiskCard_HeaderColor[1], strRiskRef, Constants.RGB_Red));

				Boolean isRedColor = CommonLib.isElementVisible(driver, strConfig,
						String.format(Array_LN_RiskDashboard_RiskCard_HeaderColor[1], strRiskRef, Constants.RGB_Red));

				if (isRedColor) {
					TestReporter.Pass(driver, strConfig,
							"Card Color for Risk Status: 'NTU' [Risk NTU'd] is", "Red");
				} else {
					TestReporter.Fail(driver, strConfig,
							"Card Color for Risk Status: 'NTU' [Risk NTU'd] is not", "Red");
				}
			}
			
		} catch (NoSuchElementException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (StaleElementReferenceException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (JavascriptException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (TimeoutException e) {
			TestReporter.Fail(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (NullPointerException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (IllegalArgumentException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		}
	}
	
	
	
	public static void verify_Dashboard_RiskCard_HeaderColor_ForStatus_InNegotiation_For_NewRisk(RemoteWebDriver driver, String strConfig, String strRiskRef) {
		try {
			CommonLib.waitForLoad(driver, strConfig);
			//Get Risk Status
			String[] Array_LN_RiskDashboard_RiskStatus = ObjectRepository
					.GetObjectlocators("LBL_RiskDashboard_RiskCard_StatusText");
			
			CommonLib.WaitForElementToBeVisible(driver, strConfig, String.format(
					Array_LN_RiskDashboard_RiskStatus[1], strRiskRef));
			
			WebElement RiskStatusEle = driver.findElementByXPath(String.format(Array_LN_RiskDashboard_RiskStatus[1], strRiskRef));

			String riskStatus = RiskStatusEle.getText();
			
			//Verify Risk Card Header Color as per the Status of Risk
			String[] Array_LN_RiskDashboard_RiskCard_HeaderColor = ObjectRepository
					.GetObjectlocators("LN_RiskDashboard_RiskCard_HeaderColor");
			
			if(riskStatus.trim().contentEquals("In Negotiation"))
			{
				String[] Array_LBL_RiskDashboard_RiskCard_ClassOfBusiness_Text = ObjectRepository
						.GetObjectlocators("LBL_RiskDashboard_RiskCard_ClassOfBusiness_Text");

				WebElement RiskCard_COBEle = driver.findElementByXPath(
						String.format(Array_LBL_RiskDashboard_RiskCard_ClassOfBusiness_Text[1], strRiskRef));

				String riskCard_COBValue = RiskCard_COBEle.getText();

				if (riskCard_COBValue.contentEquals("")) {
					CommonLib.WaitForElementToBeVisible(driver, strConfig, String
							.format(Array_LN_RiskDashboard_RiskCard_HeaderColor[1], strRiskRef, Constants.RGB_Grey));

					Boolean isGreyColor = CommonLib.isElementVisible(driver, strConfig,
							String.format(Array_LN_RiskDashboard_RiskCard_HeaderColor[1], strRiskRef, Constants.RGB_Grey));

					if (isGreyColor) {
						TestReporter.Pass(driver, strConfig,
								"Card Color for Risk Status: 'In Negotiation' [Risk Details Not saved] is", "Grey");
					} else {
						TestReporter.Fail(driver, strConfig,
								"Card Color for Risk Status: 'In Negotiation' [Risk Details Not saved] is not", "Grey");
					}

				}

			}
			
				
		} catch (NoSuchElementException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (StaleElementReferenceException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (JavascriptException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (TimeoutException e) {
			TestReporter.Fail(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (NullPointerException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (IllegalArgumentException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		}
	}
	
	
	public static void verify_Dashboard_RiskCard_HeaderColor_ForStatus_InNegotiation_For_RiskDetails_InfoSaved(RemoteWebDriver driver, String strConfig, String strRiskRef) {
		try {
			CommonLib.waitForLoad(driver, strConfig);
			//Get Risk Status
			String[] Array_LN_RiskDashboard_RiskStatus = ObjectRepository
					.GetObjectlocators("LBL_RiskDashboard_RiskCard_StatusText");
			
			CommonLib.WaitForElementToBeVisible(driver, strConfig, String.format(
					Array_LN_RiskDashboard_RiskStatus[1], strRiskRef));
			
			WebElement RiskStatusEle = driver.findElementByXPath(String.format(Array_LN_RiskDashboard_RiskStatus[1], strRiskRef));

			String riskStatus = RiskStatusEle.getText();
			
			//Verify Risk Card Header Color as per the Status of Risk
			String[] Array_LN_RiskDashboard_RiskCard_HeaderColor = ObjectRepository
					.GetObjectlocators("LN_RiskDashboard_RiskCard_HeaderColor");
			
			if(riskStatus.trim().contentEquals("In Negotiation"))
			{
				String[] Array_LBL_RiskDashboard_RiskCard_ClassOfBusiness_Text = ObjectRepository
						.GetObjectlocators("LBL_RiskDashboard_RiskCard_ClassOfBusiness_Text");

				WebElement RiskCard_COBEle = driver.findElementByXPath(
						String.format(Array_LBL_RiskDashboard_RiskCard_ClassOfBusiness_Text[1], strRiskRef));

				String riskCard_COBValue = RiskCard_COBEle.getText();

				if(!riskCard_COBValue.trim().isEmpty() && riskCard_COBValue.trim()!=null ){
					CommonLib.WaitForElementToBeVisible(driver, strConfig, String
							.format(Array_LN_RiskDashboard_RiskCard_HeaderColor[1], strRiskRef, Constants.RGB_Blue));

					Boolean isBlueColor = CommonLib.isElementVisible(driver, strConfig,
							 String.format(Array_LN_RiskDashboard_RiskCard_HeaderColor[1], strRiskRef, Constants.RGB_Blue));

					if (isBlueColor) {
						TestReporter.Pass(driver, strConfig,
								"Card Color for Risk Status: 'In Negotiation' [Risk Details Saved] is", "Blue");
					} else {
						TestReporter.Fail(driver, strConfig,
								"Card Color for Risk Status: 'In Negotiation' [Risk Details Saved] is not", "Blue");
					}
				}
				
				else {
					TestReporter.Warning(driver, strConfig, "Risk Details Tab for Risk not saved initially", "Risk Card Color Verification Skipped");
				}
			}
			
				
		} catch (NoSuchElementException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (StaleElementReferenceException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (JavascriptException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (TimeoutException e) {
			TestReporter.Fail(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (NullPointerException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (IllegalArgumentException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		}
	}
	
	
	public static void verify_Dashboard_RiskCard_HeaderColor_ForStatus_NBI(RemoteWebDriver driver, String strConfig, String strRiskRef) {
		try {
			CommonLib.waitForLoad(driver, strConfig);
			//Get Risk Status
			String[] Array_LN_RiskDashboard_RiskStatus = ObjectRepository
					.GetObjectlocators("LBL_RiskDashboard_RiskCard_StatusText");
			
			CommonLib.WaitForElementToBeVisible(driver, strConfig, String.format(
					Array_LN_RiskDashboard_RiskStatus[1], strRiskRef));
			
			WebElement RiskStatusEle = driver.findElementByXPath(String.format(Array_LN_RiskDashboard_RiskStatus[1], strRiskRef));

			String riskStatus = RiskStatusEle.getText();
			
			//Verify Risk Card Header Color as per the Status of Risk
			String[] Array_LN_RiskDashboard_RiskCard_HeaderColor = ObjectRepository
					.GetObjectlocators("LN_RiskDashboard_RiskCard_HeaderColor");
			
			 if(riskStatus.trim().contentEquals("NBI"))
			{
				CommonLib.WaitForElementToBeVisible(driver, strConfig, String
						.format(Array_LN_RiskDashboard_RiskCard_HeaderColor[1], strRiskRef, Constants.RGB_Blue));

				Boolean isBlueColor = CommonLib.isElementVisible(driver, strConfig,
						 String.format(Array_LN_RiskDashboard_RiskCard_HeaderColor[1], strRiskRef, Constants.RGB_Blue));

				if (isBlueColor) {
					TestReporter.Pass(driver, strConfig,
							"Card Color for Risk Status: 'NBI' [Risk Details Saved] is", "Blue");
				} else {
					TestReporter.Fail(driver, strConfig,
							"Card Color for Risk Status: 'NBI' [Risk Details Saved] is not", "Blue");
				}
			}
			
			
			
		} catch (NoSuchElementException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (StaleElementReferenceException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (JavascriptException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (TimeoutException e) {
			TestReporter.Fail(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (NullPointerException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (IllegalArgumentException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		}
	}
	
	
	
	
	public static void verify_Dashboard_RiskCard_HeaderColor_ForStatus_Quote(RemoteWebDriver driver, String strConfig, String strRiskRef) {
		try {
			CommonLib.waitForLoad(driver, strConfig);
			//Get Risk Status
			String[] Array_LN_RiskDashboard_RiskStatus = ObjectRepository
					.GetObjectlocators("LBL_RiskDashboard_RiskCard_StatusText");
			
			CommonLib.WaitForElementToBeVisible(driver, strConfig, String.format(
					Array_LN_RiskDashboard_RiskStatus[1], strRiskRef));
			
			WebElement RiskStatusEle = driver.findElementByXPath(String.format(Array_LN_RiskDashboard_RiskStatus[1], strRiskRef));

			String riskStatus = RiskStatusEle.getText();
			
			//Verify Risk Card Header Color = Blue for Risk Status = Quote
			String[] Array_LN_RiskDashboard_RiskCard_HeaderColor = ObjectRepository
					.GetObjectlocators("LN_RiskDashboard_RiskCard_HeaderColor");
			
			if(riskStatus.trim().contentEquals("Quote"))
			{
				CommonLib.WaitForElementToBeVisible(driver, strConfig, String
						.format(Array_LN_RiskDashboard_RiskCard_HeaderColor[1], strRiskRef, Constants.RGB_Blue));

				Boolean isBlueColor = CommonLib.isElementVisible(driver, strConfig,
						String.format(Array_LN_RiskDashboard_RiskCard_HeaderColor[1], strRiskRef, Constants.RGB_Blue));

				if (isBlueColor) {
					TestReporter.Pass(driver, strConfig,
							"Card Color for Risk Status: 'Quote' [Risk Details Saved] is", "Blue");
				} else {
					TestReporter.Fail(driver, strConfig,
							"Card Color for Risk Status: 'Quote' [Risk Details Saved] is not", "Blue");
				}
			}
			
		} catch (NoSuchElementException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (StaleElementReferenceException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (JavascriptException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (TimeoutException e) {
			TestReporter.Fail(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (NullPointerException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (IllegalArgumentException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		}
	}
	
	
	
	public static void verify_Dashboard_RiskCard_HeaderColor_ForStatus_QuoteOrNBI(RemoteWebDriver driver, String strConfig, String strRiskRef) {
		try {
			CommonLib.waitForLoad(driver, strConfig);
			//Get Risk Status
			String[] Array_LN_RiskDashboard_RiskStatus = ObjectRepository
					.GetObjectlocators("LBL_RiskDashboard_RiskCard_StatusText");
			
			CommonLib.WaitForElementToBeVisible(driver, strConfig, String.format(
					Array_LN_RiskDashboard_RiskStatus[1], strRiskRef));
			
			WebElement RiskStatusEle = driver.findElementByXPath(String.format(Array_LN_RiskDashboard_RiskStatus[1], strRiskRef));

			String riskStatus = RiskStatusEle.getText();
			
			//Verify Risk Card Header Color = BLUE for Risk Status = Quote/NBI
			String[] Array_LN_RiskDashboard_RiskCard_HeaderColor = ObjectRepository
					.GetObjectlocators("LN_RiskDashboard_RiskCard_HeaderColor");
			
			if(riskStatus.trim().contentEquals("Quote/NBI"))
			{
				CommonLib.WaitForElementToBeVisible(driver, strConfig, String
						.format(Array_LN_RiskDashboard_RiskCard_HeaderColor[1], strRiskRef, Constants.RGB_Blue));

				Boolean isBlueColor = CommonLib.isElementVisible(driver, strConfig,
						String.format(Array_LN_RiskDashboard_RiskCard_HeaderColor[1], strRiskRef, Constants.RGB_Blue));

				if (isBlueColor) {
					TestReporter.Pass(driver, strConfig,
							"Card Color for Risk Status: 'Quote/NBI' [Risk Details Saved] is", "Blue");
				} else {
					TestReporter.Fail(driver, strConfig,
							"Card Color for Risk Status: 'Quote/NBI' [Risk Details Saved] is not", "Blue");
				}
			}
			
			
		} catch (NoSuchElementException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (StaleElementReferenceException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (JavascriptException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (TimeoutException e) {
			TestReporter.Fail(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (NullPointerException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (IllegalArgumentException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		}
	}
	
	
	
	public static void verify_Dashboard_RiskCard_HeaderColor_ForStatus_Policy(RemoteWebDriver driver, String strConfig, String strRiskRef) {
		try {
			CommonLib.waitForLoad(driver, strConfig);
			//Get Risk Status
			String[] Array_LN_RiskDashboard_RiskStatus = ObjectRepository
					.GetObjectlocators("LBL_RiskDashboard_RiskCard_StatusText");
			
			CommonLib.WaitForElementToBeVisible(driver, strConfig, String.format(
					Array_LN_RiskDashboard_RiskStatus[1], strRiskRef));
			
			WebElement RiskStatusEle = driver.findElementByXPath(String.format(Array_LN_RiskDashboard_RiskStatus[1], strRiskRef));

			String riskStatus = RiskStatusEle.getText();
			
			//Verify Risk Card Header Color = GREEN for Risk Status = Policy
			String[] Array_LN_RiskDashboard_RiskCard_HeaderColor = ObjectRepository
					.GetObjectlocators("LN_RiskDashboard_RiskCard_HeaderColor");
			
			if(riskStatus.trim().contentEquals("Policy"))
			{
				CommonLib.WaitForElementToBeVisible(driver, strConfig, String
						.format(Array_LN_RiskDashboard_RiskCard_HeaderColor[1], strRiskRef, Constants.RGB_Green));

				Boolean isGreenColor = CommonLib.isElementVisible(driver, strConfig,
						String.format(Array_LN_RiskDashboard_RiskCard_HeaderColor[1], strRiskRef, Constants.RGB_Green));

				if (isGreenColor) {
					TestReporter.Pass(driver, strConfig,
							"Card Color for Risk Status: 'Policy' [Risk taken to Firm Order] is", "Green");
				} else {
					TestReporter.Fail(driver, strConfig,
							"Card Color for Risk Status: 'Policy' [Risk taken to Firm Order] is not", "Green");
				}
			}
			
			
			
			
		} catch (NoSuchElementException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (StaleElementReferenceException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (JavascriptException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (TimeoutException e) {
			TestReporter.Fail(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (NullPointerException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (IllegalArgumentException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		}
	}
	
	
	public static void verify_Dashboard_RiskCard_HeaderColor_ForStatus_SubmittedToBrokerOps(RemoteWebDriver driver, String strConfig, String strRiskRef) {
		try {
			CommonLib.waitForLoad(driver, strConfig);
			//Get Risk Status
			String[] Array_LN_RiskDashboard_RiskStatus = ObjectRepository
					.GetObjectlocators("LBL_RiskDashboard_RiskCard_StatusText");
			
			CommonLib.WaitForElementToBeVisible(driver, strConfig, String.format(
					Array_LN_RiskDashboard_RiskStatus[1], strRiskRef));
			
			WebElement RiskStatusEle = driver.findElementByXPath(String.format(Array_LN_RiskDashboard_RiskStatus[1], strRiskRef));

			String riskStatus = RiskStatusEle.getText();
			
			//Verify Risk Card Header Color = GREEN for Risk Status 'Submitted'
			String[] Array_LN_RiskDashboard_RiskCard_HeaderColor = ObjectRepository
					.GetObjectlocators("LN_RiskDashboard_RiskCard_HeaderColor");
			
			if(riskStatus.trim().contentEquals("Submitted"))
			{
				CommonLib.WaitForElementToBeVisible(driver, strConfig, String
						.format(Array_LN_RiskDashboard_RiskCard_HeaderColor[1], strRiskRef, Constants.RGB_Green));

				Boolean isGreenColor = CommonLib.isElementVisible(driver, strConfig,
						String.format(Array_LN_RiskDashboard_RiskCard_HeaderColor[1], strRiskRef, Constants.RGB_Green));

				if (isGreenColor) {
					TestReporter.Pass(driver, strConfig,
							"Card Color for Risk Status: 'Submitted' [Risk Submitted to Broker Ops] is", "Green");
				} else {
					TestReporter.Fail(driver, strConfig,
							"Card Color for Risk Status: 'Submitted' [Risk Submitted to Broker Ops] is not", "Green");
				}
			}
			
			
		} catch (NoSuchElementException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (StaleElementReferenceException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (JavascriptException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (TimeoutException e) {
			TestReporter.Fail(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (NullPointerException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (IllegalArgumentException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		}
	}
	
	
	
	
	public static void verify_Dashboard_RiskCard_HeaderColor_ForStatus_NTU(RemoteWebDriver driver, String strConfig, String strRiskRef) {
		try {
			CommonLib.waitForLoad(driver, strConfig);
			//Get Risk Status
			String[] Array_LN_RiskDashboard_RiskStatus = ObjectRepository
					.GetObjectlocators("LBL_RiskDashboard_RiskCard_StatusText");
			
			CommonLib.WaitForElementToBeVisible(driver, strConfig, String.format(
					Array_LN_RiskDashboard_RiskStatus[1], strRiskRef));
			
			WebElement RiskStatusEle = driver.findElementByXPath(String.format(Array_LN_RiskDashboard_RiskStatus[1], strRiskRef));

			String riskStatus = RiskStatusEle.getText();
			
			//Verify Risk Card Header Color is RED for Status ="NTU"
			String[] Array_LN_RiskDashboard_RiskCard_HeaderColor = ObjectRepository
					.GetObjectlocators("LN_RiskDashboard_RiskCard_HeaderColor");
			
			if(riskStatus.trim().contains("NTU"))
			{
				CommonLib.WaitForElementToBeVisible(driver, strConfig, String
						.format(Array_LN_RiskDashboard_RiskCard_HeaderColor[1], strRiskRef, Constants.RGB_Red));

				Boolean isRedColor = CommonLib.isElementVisible(driver, strConfig,
						String.format(Array_LN_RiskDashboard_RiskCard_HeaderColor[1], strRiskRef, Constants.RGB_Red));

				if (isRedColor) {
					TestReporter.Pass(driver, strConfig,
							"Card Color for Risk Status: 'NTU' [Risk NTU'd] is", "Red");
				} else {
					TestReporter.Fail(driver, strConfig,
							"Card Color for Risk Status: 'NTU' [Risk NTU'd] is not", "Red");
				}
			}
			
		} catch (NoSuchElementException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (StaleElementReferenceException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (JavascriptException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (TimeoutException e) {
			TestReporter.Fail(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (NullPointerException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (IllegalArgumentException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		}
	}
	
	
	
	
	
	public static void verify_RiskOverviewTab_MandatoryFields(RemoteWebDriver driver, String strConfig) {
		try {

			CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_RiskDashboard_NewRisk");
			Project.ExecuteRow(driver, strConfig, "BTN_RiskDashboard_NewRisk", "Click");
			CommonLib.waitForLoad(driver, strConfig);
			CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_MenuItem_RiskOverview");
			CommonLib.WaitForElementToBeClickable(driver, strConfig, "DDO_RiskOverview_Division");
			
			//Wait for Mandatory Fields to load
			CommonLib.WaitForElementToBeVisible(driver, strConfig, "LBL_RiskOverview_AgentOrClient_MandatoryField");
			CommonLib.WaitForElementToBeVisible(driver, strConfig, "LBL_RiskOverview_PCP_MandatoryField");
			CommonLib.WaitForElementToBeVisible(driver, strConfig, "LBL_RiskOverview_AccountExecutive_MandatoryField");
			CommonLib.WaitForElementToBeVisible(driver, strConfig, "LBL_RiskOverview_PlacingBroker_MandatoryField");
			
			CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskOverview_AgentOrClient");
			CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskOverview_Assured");
			CommonLib.WaitForElementToBeClickable(driver, strConfig, "DDO_RiskOverview_PCP");
			CommonLib.WaitForElementToBeClickable(driver, strConfig, "DDO_RiskOverview_PlacingBroker");
			
			CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Save");
			Project.ExecuteRow(driver, strConfig, "BTN_Save", "Click");
			CommonLib.waitForLoad(driver, strConfig);
			
			//Wait for the Validation Required Messages
			CommonLib.WaitForElementToBeVisible(driver, strConfig, "LBL_RiskOverview_Client_RequiredValidation_Msg");
			CommonLib.WaitForElementToBeVisible(driver, strConfig, "LBL_RiskOverview_Assured_RequiredValidation_Msg");
			CommonLib.WaitForElementToBeVisible(driver, strConfig, "LBL_RiskOverview_PCP_RequiredValidation_Msg");
			CommonLib.WaitForElementToBeVisible(driver, strConfig, "LBL_RiskOverview_PlacingBroker_RequiredValidation_Msg");
			
			//Wait for the common Validation Error message
			CommonLib.WaitForElementToBeVisible(driver, strConfig, Constants.NotifMsg_XPATH);

			String strNotifMsg = driver.findElement(By.xpath(Constants.NotifMsg_XPATH)).getText();
			String strRequiredFieldsMsg = Constants.MandatoryFieldRequired_Msg;
	
			if (strNotifMsg.trim().contains(strRequiredFieldsMsg)) {
				CommonLib.getLogger(strConfig).info(
						"PASS: Mandatory Fields Required message shown - '" + strRequiredFieldsMsg + "'");
				TestReporter.Pass(driver, strConfig, "Risk Overview Tab ",
						" Mandatory Fields Required message shown '" + strRequiredFieldsMsg + "'");

				CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_ErrorMsg_IconCancel");
				CommonLib.waitForLoad(driver, strConfig);

				}
			

		} catch (NoSuchElementException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (StaleElementReferenceException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (JavascriptException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (TimeoutException e) {
			TestReporter.Fail(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (NullPointerException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (IllegalArgumentException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		}
	}
	
	
	public static void navigateToHomePage(RemoteWebDriver driver, String strConfig) {
		try {
		CommonLib.WaitForElementToBeClickable(driver, strConfig,"LN_MenuItem_Dashboard");
		Project.ExecuteRow(driver, strConfig, "LN_MenuItem_Dashboard", "Click");
		CommonLib.waitForLoad(driver, strConfig);
		CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_Logout_IntegroAccount");
		
		} catch (NoSuchElementException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (StaleElementReferenceException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (JavascriptException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (TimeoutException e) {
			TestReporter.Fail(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (NullPointerException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (IllegalArgumentException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		}
	}
	
	
	public static String createNewRisk(RemoteWebDriver driver, String strConfig, String strTestingType,  ArrayList<String> riskOverviewData) {
		String riskTrackerRefNum = null;
		try {

			CommonLib.getLogger(strConfig).info("Inside createNewRisk");

			if (riskOverviewData.get(2) != null && !(riskOverviewData.get(2).trim().isEmpty())) {

				if (riskOverviewData.get(3) != null && !(riskOverviewData.get(3).trim().isEmpty())) {

					if (riskOverviewData.get(12) != null && !(riskOverviewData.get(12).trim().isEmpty())) {
						
						if (riskOverviewData.get(13) != null && !(riskOverviewData.get(13).trim().isEmpty())) {
							
							
						CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_RiskDashboard_NewRisk");
						Project.ExecuteRow(driver, strConfig, "BTN_RiskDashboard_NewRisk", "Click");
						CommonLib.waitForLoad(driver, strConfig);
						CommonLib.WaitForElementToBeClickable(driver, strConfig,
								"LN_MenuItem_RiskOverview");
						CommonLib.WaitForElementToBeClickable(driver, strConfig, "DDO_RiskOverview_Division");

						// NEW/RENEWALCLASSIFICATION
						// ---- Select New/Renewal Classification Option from the Dropdown
						if (riskOverviewData.get(0) != null && !(riskOverviewData.get(0).trim().isEmpty())) {
							CommonLib.WaitForElementToBeClickable(driver, strConfig,
									"DDO_RiskOverview_NewOrRenewal_Classification");
							Project.ExecuteRow(driver, strConfig, "DDO_RiskOverview_NewOrRenewal_Classification",
									"Click");
							CommonLib.WaitForElementToBeClickable(driver, strConfig, "DTI_RiskOverview_NewOrRenewal_Classification");
							Project.ExecuteRow(driver, strConfig, "DTI_RiskOverview_NewOrRenewal_Classification",
									riskOverviewData.get(0));
							
						}
				
						// DIVISION
						// ---- Select Division Option from the Dropdown
						if (riskOverviewData.get(1) != null && !(riskOverviewData.get(1).trim().isEmpty())) {
							CommonLib.WaitForElementToBeClickable(driver, strConfig, "DDO_RiskOverview_Division");
							Project.ExecuteRow(driver, strConfig, "DDO_RiskOverview_Division", "Click");

							CommonLib.WaitForElementToBeClickable(driver, strConfig, "DTI_RiskOverview_Division");
							Project.ExecuteRow(driver, strConfig, "DTI_RiskOverview_Division",
									riskOverviewData.get(1));
						} else {
							CommonLib.getLogger(strConfig).info(
									"info: No Division supplied in RTTestdata.xlsx file. Default Division value to be used");
							TestReporter.Info(driver, strConfig, "Division",
									"Not supplied in RTTestdata.xlsx file.Default Division value to be used");
						}

						// AGENT/CLIENT
						// ---- Enter Agent/Client Name in the Text Input Field [DATA FROM EXCEL]

						CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskOverview_AgentOrClient");
						Project.ExecuteRow(driver, strConfig, "TXT_RiskOverview_AgentOrClient",
								riskOverviewData.get(2));

						// ---- Wait for the Agent/Client Name and Account No Combination Options to
						// display [DATA FROM EXCEL]
						String[] STO_RiskOverview_AgentOrClient = ObjectRepository
								.GetObjectlocators("STO_RiskOverview_AgentOrClient");
						CommonLib.Delay();
						CommonLib.WaitForElementToBeClickable(driver, strConfig, String.format(
								STO_RiskOverview_AgentOrClient[1], riskOverviewData.get(2), riskOverviewData.get(3)));

						// ---- Click on the desired option for Agent/Client Name and Account No
						// Combination [DATA FROM EXCEL]
						WebElement agentOrClient_SearchTxt_Output = driver
								.findElement(By.xpath(String.format(STO_RiskOverview_AgentOrClient[1],
										riskOverviewData.get(2), riskOverviewData.get(3))));

						common.JSClick(driver, strConfig, agentOrClient_SearchTxt_Output);

						// DIRECT INSURED PLACEMENT OR REINSURANCE
						if ((riskOverviewData.get(5) == null || riskOverviewData.get(5).trim().isEmpty()
								|| riskOverviewData.get(5).trim().contentEquals("No"))
								&& (riskOverviewData.get(7) == null || riskOverviewData.get(7).trim().isEmpty()
										|| riskOverviewData.get(7).trim().contentEquals("No"))) {

							if (riskOverviewData.get(4) != null && !(riskOverviewData.get(4).trim().isEmpty())) {
								CommonLib.WaitForElementToBeClickable(driver, strConfig,
										"TXT_RiskOverview_Insured_Or_DirectInsured_Or_OriginalInsured");
								Project.ExecuteRow(driver, strConfig,
										"TXT_RiskOverview_Insured_Or_DirectInsured_Or_OriginalInsured",
										riskOverviewData.get(4));
							}
						}

						else if (riskOverviewData.get(5) != null && !(riskOverviewData.get(5).trim().isEmpty())
								&& riskOverviewData.get(5).trim().contentEquals("Yes")) {
							CommonLib.WaitForElementToBeClickable(driver, strConfig,
									"CHK_RiskOverview_DirectInsured_Placement_ONOFF_Switch");
							Project.ExecuteRow(driver, strConfig,
									"CHK_RiskOverview_DirectInsured_Placement_ONOFF_Switch", "Click");

							// This Logic needs to be clarified from BA or Dev
							// Since this is already populated when Direct Insured is Switched ON and the
							// value cannot be verified with Agent/Client entered previously
							if (riskOverviewData.get(6) != null && !(riskOverviewData.get(6).trim().isEmpty())) {
								CommonLib.WaitForElementToBeClickable(driver, strConfig,
										"TXT_RiskOverview_Insured_Or_DirectInsured_Or_OriginalInsured");
								Project.ExecuteRow(driver, strConfig,
										"TXT_RiskOverview_Insured_Or_DirectInsured_Or_OriginalInsured",
										riskOverviewData.get(6));
							}
						}

						else if (riskOverviewData.get(7) != null && !(riskOverviewData.get(7).trim().isEmpty())
								&& riskOverviewData.get(7).trim().contentEquals("Yes")) {
							CommonLib.WaitForElementToBeClickable(driver, strConfig,
									"CHK_RiskOverview_Reinsurance_ONOFF_Switch");
							Project.ExecuteRow(driver, strConfig, "CHK_RiskOverview_Reinsurance_ONOFF_Switch", "Click");
							// This Logic needs to be clarified from BA or Dev
							if (riskOverviewData.get(8) != null && !(riskOverviewData.get(8).trim().isEmpty())) {
								CommonLib.WaitForElementToBeClickable(driver, strConfig,
										"TXT_RiskOverview_Insured_Or_DirectInsured_Or_OriginalInsured");
								Project.ExecuteRow(driver, strConfig,
										"TXT_RiskOverview_Insured_Or_DirectInsured_Or_OriginalInsured",
										riskOverviewData.get(8));
							}

							// This Logic needs to be clarified from BA or Dev
							if (riskOverviewData.get(9) != null && !(riskOverviewData.get(9).trim().isEmpty())) {
								CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskOverview_Reinsured");
								Project.ExecuteRow(driver, strConfig, "TXT_RiskOverview_Reinsured",
										riskOverviewData.get(9));
							}

						}
						// ASSURED - (Logic will change as there is a condition to enter Assured...
						// Currently it is kept as Mandatory)
						CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskOverview_Assured");
						Project.ExecuteRow(driver, strConfig, "TXT_RiskOverview_Assured", riskOverviewData.get(10));

						// PCP-Select PCP Option from the Dropdown

						Project.ExecuteRow(driver, strConfig, "DDO_RiskOverview_PCP", "Click");

						CommonLib.WaitForElementToBeClickable(driver, strConfig, "DTI_RiskOverview_PCP");
						// ---- Select PCP Option from the Dropdown [DATA FROM EXCEL]
						Project.ExecuteRow(driver, strConfig, "DTI_RiskOverview_PCP",
								riskOverviewData.get(13));
						
						// ACCOUNT EXECUTIVE
						// ---- Select Account Executive Option from the Dropdown

						Project.ExecuteRow(driver, strConfig, "DDO_RiskOverview_AccountExecutive", "Click");

						CommonLib.WaitForElementToBeClickable(driver, strConfig, "DTI_RiskOverview_AccountExecutive");
						// ---- Select Account Executive Option from the Dropdown [DATA FROM EXCEL]
						Project.ExecuteRow(driver, strConfig, "DTI_RiskOverview_AccountExecutive",
								riskOverviewData.get(11));

						// PLACING BROKER
						// ---- Select Placing Broker Option from the Dropdown
						CommonLib.WaitForElementToBeClickable(driver, strConfig, "DDO_RiskOverview_PlacingBroker");
						Project.ExecuteRow(driver, strConfig, "DDO_RiskOverview_PlacingBroker", "Click");
						CommonLib.WaitForElementToBeClickable(driver, strConfig, "DTI_RiskOverview_PlacingBroker");
						// ---- Select Account Executive Option from the Dropdown [DATA FROM EXCEL]
						Project.ExecuteRow(driver, strConfig, "DTI_RiskOverview_PlacingBroker",
								riskOverviewData.get(12));

						// SAVE BUTTON
						CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Save");
						Project.ExecuteRow(driver, strConfig, "BTN_Save", "Click");

						CommonLib.WaitForElementToBeVisible(driver, strConfig, Constants.NotifMsg_XPATH);

						String strNotifMsg = driver.findElement(By.xpath(Constants.NotifMsg_XPATH)).getText();
						String strRiskCreatedSuccessMsg = Constants.NewRiskCreatedMsg;
						// Risk Created Success Message Shown
						if (strNotifMsg.trim().contains(strRiskCreatedSuccessMsg)) {
							CommonLib.getLogger(strConfig).info("PASS: Risk Creation Success Message shown - '"
									+ strRiskCreatedSuccessMsg + "'");
							TestReporter.Pass(driver, strConfig, " Risk Creation ",
									"Success message shown- '" + strRiskCreatedSuccessMsg + "'");

							CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Save");
							CommonLib.waitForLoad(driver, strConfig);

							CommonLib.WaitForElementToBeClickable(driver, strConfig, "DDO_RiskOverview_Division");
							CommonLib.WaitForElementToBeClickable(driver, strConfig,
									"DDO_RiskOverview_Division");

							// RISK TRACKER REF NUMBER
							riskTrackerRefNum = RTData.getRiskTracker_RefNo(driver, strConfig,
									Constants.riskTrackerRefNo_XPATH);

							if (riskTrackerRefNum != null && !(riskTrackerRefNum.trim().isEmpty())) {
								CommonLib.getLogger(strConfig).info("PASS:  Risk Tracker Reference is generated :");
								TestReporter.Pass(driver, strConfig, " Risk Tracker Reference is generated :",
										riskTrackerRefNum);

								BusinessRules.verify_RiskOverview_TabAfter_RiskGeneration_Or_Saving_RiskOverviewTab(driver, strTestingType,  strConfig, true);
							} else {
								CommonLib.getLogger(strConfig).info("FAIL:  Risk Tracker Reference is not generated :");
								TestReporter.Fail(driver, strConfig, " Risk Tracker Reference is not generated :", "");

							}

							return riskTrackerRefNum;
						}

						// Error Message Shown
						else {
							CommonLib.getLogger(strConfig)
									.info("FAIL: Risk Creation Success message not shown");
							if (strNotifMsg.trim().contentEquals(Constants.QuoteAlreadyCreatedErrorMsg)) {
								CommonLib.getLogger(strConfig).info(
										"FAIL: Risk Creation  : 'Quote already created' error message shown");
								TestReporter.Fail(driver, strConfig, "Risk Creation",
										" 'Quote already created' error message shown");
							}

							else if (strNotifMsg.trim().contentEquals(Constants.ConnectionTimeoutErrorMsg)) {
								CommonLib.getLogger(strConfig)
										.info("FAIL: Risk Creation  : 'Connection Timeout' error message shown");
								TestReporter.Fail(driver, strConfig, "Risk Creation",
										" 'Connection Timeout' error message shown");
							}

							else if (strNotifMsg.trim().contentEquals(Constants.InternalServerErrorMsg)) {
								CommonLib.getLogger(strConfig).info(
										"FAIL: Risk Creation  : '500 Internal Server Error' message shown");
								TestReporter.Fail(driver, strConfig, "Risk Creation",
										" '500 Internal Server Error' message shown");
							}
						}
						
						} else {
							CommonLib.getLogger(strConfig).info(
									"INFO: No PCP(MANDATORY FIELD) supplied in RTTestdata.xlsx file. Cannot proceed with creating a New Risk");
							TestReporter.Info(driver, strConfig, "PCP(MANDATORY FIELD)",
									"Not supplied in RTTestdata.xlsx file.Cannot proceed with creating a New Risk");
						}
					
					} else {
						CommonLib.getLogger(strConfig).info(
								"INFO: No Placing Broker(MANDATORY FIELD) supplied in RTTestdata.xlsx file. Cannot proceed with creating a New Risk");
						TestReporter.Info(driver, strConfig, "Placing Broker(MANDATORY FIELD)",
								"Not supplied in RTTestdata.xlsx file.Cannot proceed with creating a New Risk");
					}
				} else {
					CommonLib.getLogger(strConfig).info(
							"INFO: No Agent/Client Account No.(MANDATORY FIELD) supplied in RTTestdata.xlsx file. Cannot proceed with creating a New Risk");
					TestReporter.Info(driver, strConfig, "Agent/Client Account No.(MANDATORY FIELD)",
							"Not supplied in RTTestdata.xlsx file.Cannot proceed with creating a New Risk");
				}

			} else {
				CommonLib.getLogger(strConfig).info(
						"INFO: No Agent/Client(MANDATORY FIELD) supplied in RTTestdata.xlsx file. Cannot proceed with creating a New Risk");
				TestReporter.Info(driver, strConfig, "Agent/Client(MANDATORY FIELD)",
						"Not supplied in RTTestdata.xlsx file.Cannot proceed with creating a New Risk");
			}

		} catch (NoSuchElementException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (StaleElementReferenceException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (JavascriptException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (TimeoutException e) {
			TestReporter.Fail(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (NullPointerException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (IllegalArgumentException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		}

		return riskTrackerRefNum;

	}
	
	
	
	
	public static void verify_CancelBtn_On_RiskOverviewTab(RemoteWebDriver driver, String strConfig) {
		
		try {

			CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_RiskDashboard_NewRisk");
			Project.ExecuteRow(driver, strConfig, "BTN_RiskDashboard_NewRisk", "Click");
			CommonLib.waitForLoad(driver, strConfig);
			CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_MenuItem_RiskOverview");
			CommonLib.WaitForElementToBeClickable(driver, strConfig, "DDO_RiskOverview_Division");
			
			CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Cancel");
			Project.ExecuteRow(driver, strConfig, "BTN_Cancel", "Click");
			CommonLib.waitForLoad(driver, strConfig);
			
			CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_RiskDashboard_NewRisk");
			CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_Logout_IntegroAccount");
			

		} catch (NoSuchElementException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (StaleElementReferenceException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (JavascriptException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (TimeoutException e) {
			TestReporter.Fail(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (NullPointerException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (IllegalArgumentException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		}
	}
	
	
	public static void verify_RiskOverview_TabAfter_RiskGeneration_Or_Saving_RiskOverviewTab(RemoteWebDriver driver, String strConfig,String strTestingType,  boolean newRiskCreation) {

		CommonLib.waitForLoad(driver, strConfig);

		// RISK DETAILS TAB
		CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_MenuItem_RiskDetails");

		// INSTRUCTIONS SHEET TAB
		//CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_MenuItem_InstructionsSheet");

		// UMR Check Field is empty and Disabled
		CommonLib.WaitForElementToBeVisible(driver, strConfig, "TXT_RiskOverview_UMR_Disabled");
		WebElement UMRTxtEle = repository.GetObject(driver, strConfig, "TXT_RiskOverview_UMR");
		if (UMRTxtEle.getText() != null && !UMRTxtEle.getText().trim().isEmpty()) {
			CommonLib.getLogger(strConfig).info(
					"FAIL: UMR Text Input Field is not empty/UMR is populated in UMR Text Input Field after Risk Generation");
			TestReporter.Fail(driver, strConfig, "UMR Text Input Field", "Not empty/Has value after Risk Generation");
		} else {
			CommonLib.getLogger(strConfig).info(
					"PASS: UMR Text Input Field is empty/UMR is not populated in UMR Text Input Field after Risk Generation");
			TestReporter.Pass(driver, strConfig, "UMR Text Input Field", "Empty after Risk Generation");

		}
		// Check the presence of below elements after a new Risk is generated

		// RISK TRACKER REF LABEL
		CommonLib.WaitForElementToBeVisible(driver, strConfig, Constants.riskTrackerRefNo_XPATH);

		// AGENT/CLIENT TEXT FIELD INPUT
		//to uncomment for phase 2
		//CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskOverview_AgentOrClient");

		// ------------------Verify Action buttons---------------------------

		// NTU, DMS DOCUMENTS, NOTES Button is visible and clickable
		CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Actions_NTU");
		CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Actions_Dms_Documents");
		CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Actions_Notes");

		// FIRM ORDER, SUBMIT TO BROKER OPS Button is visible but disabled
		CommonLib.WaitForElementToBeVisible(driver, strConfig, "BTN_Actions_FirmOrder_Disabled");
		CommonLib.WaitForElementToBeVisible(driver, strConfig, "BTN_Actions_SubmitTo_BrokerOps_Disabled");

		// ------------------Verify Stage Checked and Unchecked & Date Populated if
		// Checked-------------------
		CommonLib.WaitForElementToBeVisible(driver, strConfig, Constants.riskOverview_Created_StageIcon_Checked_XPATH);
		CommonLib.WaitForElementToBeVisible(driver, strConfig, Constants.riskOverview_NBI_StageIcon_UnChecked_XPATH);
		CommonLib.WaitForElementToBeVisible(driver, strConfig, Constants.riskOverview_Quote_StageIcon_UnChecked_XPATH);
		CommonLib.WaitForElementToBeVisible(driver, strConfig,
				Constants.riskOverview_FirmOrder_StageIcon_UnChecked_XPATH);
		CommonLib.WaitForElementToBeVisible(driver, strConfig,
				Constants.riskOverview_SubmittedTo_BrokerOps_StageIcon_UnChecked_XPATH);
		
		
		// ---------------Verify Date Populated against Created Date Field------------
		WebElement CreatedDateEle = driver
				.findElement(By.xpath(Constants.riskOverview_Created_Stage_Date_Populated_XPATH));
		String createdDate = CreatedDateEle.getText();
		
		if(newRiskCreation) {
			if (createdDate.trim().contentEquals(RTData.getCurrentStageDate())) {
			CommonLib.getLogger(strConfig).info("PASS: Created Date visible and equal to Current Date upon Risk Creation in Risk Overview Tab");
			TestReporter.Pass(driver, strConfig, "Created Date: ",
					"Visible and equal to Current Date upon Risk Creation in Risk Overview Tab");
		}

		else {
			CommonLib.getLogger(strConfig)
					.info("FAIL: Created Date not visible upon Risk Creation in Risk Overview Tab");
			TestReporter.Fail(driver, strConfig, "Created Date: ",
					"Date not visible upon Risk Creation in Risk Overview Tab");

		}}
		
		else {
			Boolean isCreatedDateFormatValid = RTData.isValidStatusDateAndFormat(createdDate);

			if (isCreatedDateFormatValid) {
				CommonLib.getLogger(strConfig)
						.info("PASS: Created Date populated in the format 'dd MMM yyyy' after Saving Risk Overview Tab");
				TestReporter.Pass(driver, strConfig, "Created Date",
						"Populated in the format 'dd MMM yyyy' after Saving Risk Overview Tab");
			}

			else {
				CommonLib.getLogger(strConfig)
						.info("FAIL: Created Date not populated in the format 'dd MMM yyyy' after Saving Risk Overview Tab");
				TestReporter.Fail(driver, strConfig, "Created Date",
						"Not populated in the format 'dd MMM yyyy' after Saving Risk Overview Tab");

			}
			
		}
	}
		
		
		
	public static void saveRiskDetailsTab(RemoteWebDriver driver, String strConfig, String strTestingType, ArrayList<String> riskDetailsData) {
		String riskTrackerRefNum = null;
		try {
			CommonLib.getLogger(strConfig).info("Inside saveRiskDetailsTab");
			CommonLib.waitForLoad(driver, strConfig);
			
			
			

			if (riskDetailsData.get(0) != null && !(riskDetailsData.get(0).trim().isEmpty())) {
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_MenuItem_RiskDetails");
				CommonLib.smallDelay();
				// LinkedHashMap<String, String> riskDetailsTabData =
				// RTData.getRiskDetailsData();

				// Click on the Risk Details Menu Item
				WebElement menuItemRiskDetailsEle = repository.GetObject(driver, strConfig, "LN_MenuItem_RiskDetails");
				common.JSClick(driver, strConfig, menuItemRiskDetailsEle);
				CommonLib.smallDelay();
				CommonLib.waitForLoad(driver, strConfig);
				CommonLib.smallDelay();

				// -------Feature Switch Field ---------//
				// CommonLib.WaitForElementToBeVisible(driver, strConfig, "BTN_NBI_Disabled");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskDetails_PolicyType");

				// ----FEATURE SWITCH CODE - to be removed when one feature become stable and
				// previous feature is removed

				// This field is for current release 2.1 and will be made a dropdown in future release
				if (riskDetailsData.get(8) != null && !(riskDetailsData.get(8).trim().isEmpty())
						&& riskDetailsData.get(8).contentEquals("Yes")) {
					CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskDetails_PolicyType");
					Project.ExecuteRow(driver, strConfig, "TXT_RiskDetails_PolicyType", riskDetailsData.get(9));
				}

				// IF condition will be removed in future
				if (riskDetailsData.get(1) != null && !(riskDetailsData.get(1).trim().isEmpty())
						&& riskDetailsData.get(1).contentEquals("Yes")) {
					// IF condition will be removed in future

					// ----FEATURE SWITCH CODE

					/*-------Major and Minor Class of Business will be going in Release 2.2----*/
					/*-------COMMENTING TEMPORARILY----*/

					// MAJOR CLASS OF BUSINESS // ---- Select Major Class of Business Option from
					// the Dropdown [DATA FROM EXCEL]
					if ((riskDetailsData.get(2) != null && !(riskDetailsData.get(2).trim().isEmpty())
							&& riskDetailsData.get(2).trim().contentEquals("Yes"))
							|| (riskDetailsData.get(3) != null && !(riskDetailsData.get(3).trim().isEmpty())
									&& riskDetailsData.get(3).trim().contentEquals("Yes"))
							|| (riskDetailsData.get(4) != null && !(riskDetailsData.get(4).trim().isEmpty())
									&& riskDetailsData.get(4).trim().contentEquals("Yes"))) {
						CommonLib.WaitForElementToBeClickable(driver, strConfig,
								"DDO_RiskDetails_MajorClass_OfBusiness");

						if (riskDetailsData.get(2) != null && !(riskDetailsData.get(2).trim().isEmpty())
								&& riskDetailsData.get(2).trim().contentEquals("Yes")) {

							Project.ExecuteRow(driver, strConfig, "DDO_RiskDetails_MajorClass_OfBusiness", "Click");
							CommonLib.WaitForElementToBeClickable(driver, strConfig,
									"DTI_RiskDetails_MajorClass_OfBusiness");

							Project.ExecuteRow(driver, strConfig, "DTI_RiskDetails_MajorClass_OfBusiness",
									Constants.MajorClassOfBusiness.get(0));

							// MINOR CLASS OF BUSINESS // ---- Select Minor Class of Business Option from
							// the Dropdown [DATA FROM EXCEL] CommonLib.waitForLoad(driver, strConfig);
							CommonLib.WaitForElementToBeClickable(driver, strConfig,
									"DDO_RiskDetails_MinorClass_OfBusiness");

							Project.ExecuteRow(driver, strConfig, "DDO_RiskDetails_MinorClass_OfBusiness", "Click");
							CommonLib.WaitForElementToBeClickable(driver, strConfig,
									"DTI_RiskDetails_MinorClass_OfBusiness");

							if (riskDetailsData.get(5) != null && !(riskDetailsData.get(5).trim().isEmpty())) {
								Project.ExecuteRow(driver, strConfig, "DTI_RiskDetails_MinorClass_OfBusiness",
										riskDetailsData.get(5));
							} else {
								CommonLib.getLogger(strConfig).info(
										"FAIL: Minor Class not selected for Major Class Aviation in RTTestData.xlsx file");
								TestReporter.Fail(driver, strConfig, "Minor Class for Major Class Aviation ",
										"Not Selected in RTTestData.xlsx file");
							}

						}

						else if (riskDetailsData.get(3) != null && !(riskDetailsData.get(3).trim().isEmpty())
								&& riskDetailsData.get(3).trim().contentEquals("Yes")) {

							Project.ExecuteRow(driver, strConfig, "DDO_RiskDetails_MajorClass_OfBusiness", "Click");
							CommonLib.WaitForElementToBeClickable(driver, strConfig,
									"DTI_RiskDetails_MajorClass_OfBusiness");

							Project.ExecuteRow(driver, strConfig, "DTI_RiskDetails_MajorClass_OfBusiness",
									Constants.MajorClassOfBusiness.get(1));

							// MINOR CLASS OF BUSINESS // ---- Select Minor Class of Business Option from
							// the Dropdown [DATA FROM EXCEL] CommonLib.waitForLoad(driver, strConfig);
							CommonLib.WaitForElementToBeClickable(driver, strConfig,
									"DDO_RiskDetails_MinorClass_OfBusiness");

							Project.ExecuteRow(driver, strConfig, "DDO_RiskDetails_MinorClass_OfBusiness", "Click");
							CommonLib.WaitForElementToBeClickable(driver, strConfig,
									"DTI_RiskDetails_MinorClass_OfBusiness");

							if (riskDetailsData.get(6) != null && !riskDetailsData.get(6).trim().isEmpty()) {
								Project.ExecuteRow(driver, strConfig, "DTI_RiskDetails_MinorClass_OfBusiness",
										riskDetailsData.get(6));
							} else {
								CommonLib.getLogger(strConfig).info(
										"FAIL: Minor Class not selected for Major Class Marine in RTTestData.xlsx file");
								TestReporter.Fail(driver, strConfig, "Minor Class for Major Class Marine ",
										"Not Selected in RTTestData.xlsx file");
							}

						}

						else if (riskDetailsData.get(4) != null && !(riskDetailsData.get(4).trim().isEmpty())
								&& riskDetailsData.get(4).trim().contentEquals("Yes")) {

							Project.ExecuteRow(driver, strConfig, "DDO_RiskDetails_MajorClass_OfBusiness", "Click");
							CommonLib.WaitForElementToBeClickable(driver, strConfig,
									"DTI_RiskDetails_MajorClass_OfBusiness");

							Project.ExecuteRow(driver, strConfig, "DTI_RiskDetails_MajorClass_OfBusiness",
									Constants.MajorClassOfBusiness.get(2));

							// MINOR CLASS OF BUSINESS // ---- Select Minor Class of Business Option from
							// the Dropdown [DATA FROM EXCEL] CommonLib.waitForLoad(driver, strConfig);
							CommonLib.WaitForElementToBeClickable(driver, strConfig,
									"DDO_RiskDetails_MinorClass_OfBusiness");

							Project.ExecuteRow(driver, strConfig, "DDO_RiskDetails_MinorClass_OfBusiness", "Click");
							CommonLib.smallDelay();
							CommonLib.WaitForElementToBeClickable(driver, strConfig,
									"DTI_RiskDetails_MinorClass_OfBusiness");

							if (riskDetailsData.get(7) != null && !(riskDetailsData.get(7).trim().isEmpty())) {
								Project.ExecuteRow(driver, strConfig, "DTI_RiskDetails_MinorClass_OfBusiness",
										riskDetailsData.get(7));
							} else {
								CommonLib.getLogger(strConfig).info(
										"FAIL: Minor Class not selected for Major Class Non Marine in RTTestData.xlsx file");
								TestReporter.Fail(driver, strConfig, "Minor Class for Major Class Non Marine ",
										"Not Selected in RTTestData.xlsx file");
							}

						}
					} else {
						CommonLib.getLogger(strConfig)
								.info("INFO: Major Class of Business not selected in RTTestData.xlsx file.");
						TestReporter.Fail(driver, strConfig, "Major Class of Business",
								"Not Selected in RTTestData.xlsx file.");
					}

				}
				// IF-ELSE condition will be removed in future
				else {
					CommonLib.getLogger(strConfig)
							.info("INFO: Major Class of Business not selected in RTTestData.xlsx file.");
					TestReporter.Info(driver, strConfig, "Major Class of Business",
							"Not Selected in RTTestData.xlsx file.");

				}
				// IF-ELSE condition will be removed in future

				// PREMIUM CURRENCY CODE -
				// ---- Select Premium Currency Code Option from the Dropdown [DATA FROM EXCEL]

				if (riskDetailsData.get(10) != null && !(riskDetailsData.get(10)).trim().isEmpty()) {
					CommonLib.WaitForElementToBeClickable(driver, strConfig, "DDO_RiskDetails_PremiumCurrency_Code");
					Project.ExecuteRow(driver, strConfig, "DDO_RiskDetails_PremiumCurrency_Code", "Click");
					CommonLib.WaitForElementToBeClickable(driver, strConfig, "DTI_RiskDetails_PremiumCurrency_Code");

					Project.ExecuteRow(driver, strConfig, "DTI_RiskDetails_PremiumCurrency_Code",
							riskDetailsData.get(10));

				} else {
					CommonLib.getLogger(strConfig)
							.info("INFO: Premium Currency Code not selected in RTTestData.xlsx file. "
									+ "Hence default value 'UNITED STATES DOLLAR (USD)' retained");
					TestReporter.Info(driver, strConfig, "Premium Currency Code : ",
							"Not Selected in RTTestData.xlsx file."
									+ " Hence default value 'UNITED STATES DOLLAR (USD)' retained");
				}

				// LIMIT
				// ---- Enter Limit in the Text Input Field [DATA FROM EXCEL]
				if (riskDetailsData.get(11) != null && !(riskDetailsData.get(11)).trim().isEmpty()) {
					CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskDetails_Limit");
					Project.ExecuteRow(driver, strConfig, "TXT_RiskDetails_Limit", riskDetailsData.get(11));
				}

				// DEDUCTIBLE
				// ---- Enter Deductible in the Text Input Field [DATA FROM EXCEL]
				if (riskDetailsData.get(12) != null && !(riskDetailsData.get(12).trim().isEmpty())) {
					CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskDetails_Deductible");
					Project.ExecuteRow(driver, strConfig, "TXT_RiskDetails_Deductible", riskDetailsData.get(12));
				}

				// PREMIUM
				// ---- Enter Premium in the Text Input Field [DATA FROM EXCEL]
				if (riskDetailsData.get(13) != null && !(riskDetailsData.get(13).trim().isEmpty())) {
					CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskDetails_Premium");
					Project.ExecuteRow(driver, strConfig, "TXT_RiskDetails_Premium", riskDetailsData.get(13));
				}

				// CLIENT COMMISSION
				// ---- Enter Client Commission in the Text Input Field [DATA FROM EXCEL]
				if (riskDetailsData.get(14) != null && !(riskDetailsData.get(14).trim().isEmpty())) {

					if (Integer.parseInt(riskDetailsData.get(14)) >= 10
							&& Integer.parseInt(riskDetailsData.get(14)) <= 35) {
						CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskDetails_ClientCommission");
						Project.ExecuteRow(driver, strConfig, "TXT_RiskDetails_ClientCommission",
								riskDetailsData.get(14));

					} else {
						CommonLib.getLogger(strConfig).info(
								"FAIL: Client Commission must be between 10% and 35% and supplied value in RTTestData.xlsx file does not lie in this range");
						TestReporter.Fail(driver, strConfig, "Client Commission: ",
								"Must be between 10% and 35% and supplied value in RTTestData.xlsx file does not lie in this range");
					}
				}

				// INFORMATION
				// ---- Enter Information in the TextArea Input Field [DATA FROM EXCEL]
				if (riskDetailsData.get(15) != null && !(riskDetailsData.get(15).trim().isEmpty())) {
					CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskDetails_Information");
					Project.ExecuteRow(driver, strConfig, "TXT_RiskDetails_Information", riskDetailsData.get(15));
				}

				// TERMS
				// ---- Enter Terms in the TextArea Input Field [DATA FROM EXCEL]
				if (riskDetailsData.get(16) != null && !(riskDetailsData.get(16).trim().isEmpty())) {
					CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskDetails_Terms");
					Project.ExecuteRow(driver, strConfig, "TXT_RiskDetails_Terms", riskDetailsData.get(16));
				}

				// SAVE BUTTON
				Project.ExecuteRow(driver, strConfig, "BTN_Save", "Click");
				CommonLib.waitForLoad(driver, strConfig);
				CommonLib.WaitForElementToBeVisible(driver, strConfig, Constants.NotifMsg_XPATH);
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Save");

				String strNotifMsg = driver.findElement(By.xpath(Constants.NotifMsg_XPATH)).getText();
				String strRiskDetailsSaved_SuccessMsg = Constants.RiskDetailsSavedSuccessMsg;
				// Risk Details Saved Success Message Shown
				if (strNotifMsg.trim().contains(strRiskDetailsSaved_SuccessMsg)) {
					CommonLib.getLogger(strConfig).info("PASS: Risk Details Saved Success message shown - '"
							+ strRiskDetailsSaved_SuccessMsg + "'");
					TestReporter.Pass(driver, strConfig, " Risk Details Saved ",
							"Success message shown- '" + strRiskDetailsSaved_SuccessMsg + "'");
					BusinessRules.verifyRiskDetailsTab_AfterSave(driver, strConfig, strTestingType);

				}

				// Error Message Shown
				else {
					CommonLib.getLogger(strConfig).info("FAIL: Risk Details Saved Success message not shown");
					if (strNotifMsg.trim().contentEquals(Constants.QuoteAlreadyCreatedErrorMsg)) {
						CommonLib.getLogger(strConfig)
								.info("FAIL: Risk Details : 'Quote already created' error message shown");
						TestReporter.Fail(driver, strConfig, "Risk Details Saved  Error Message",
								" 'Quote already created' error message shown");
					}

					else if (strNotifMsg.trim().contentEquals(Constants.ConnectionTimeoutErrorMsg)) {
						CommonLib.getLogger(strConfig)
								.info("FAIL: Risk Details Saved  : 'Connection Timeout' error message shown");
						TestReporter.Fail(driver, strConfig, "Risk Details Saved  Error Message",
								" 'Connection Timeout' error message shown");
					}

					else if (strNotifMsg.trim().contentEquals(Constants.InternalServerErrorMsg)) {
						CommonLib.getLogger(strConfig)
								.info("FAIL: Risk Details Saved  : '500 Internal Server Error' message shown");
						TestReporter.Fail(driver, strConfig, "Risk Details Saved  Error Message",
								" '500 Internal Server Error' message shown");
					}
				}

			} else {

				CommonLib.getLogger(strConfig)
						.info("INFO: No RiskTracker Reference available. Cannot proceed with Saving Risk Details Screen");
				TestReporter.Info(driver, strConfig, "No RiskTracker Reference available",
						" Cannot proceed with Saving Risk Details Screen");
			}
		} catch (NoSuchElementException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (StaleElementReferenceException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (JavascriptException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (TimeoutException e) {
			TestReporter.Fail(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (NullPointerException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (IllegalArgumentException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		}

	}
	
	
	
	public static void verify_CancelBtn_On_RiskDetailsTab(RemoteWebDriver driver, String strConfig) {

		try {

			
			CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_MenuItem_RiskDetails");
			CommonLib.smallDelay();
			// LinkedHashMap<String, String> riskDetailsTabData =
			// RTData.getRiskDetailsData();

			// Click on the Risk Details Menu Item
			WebElement menuItemRiskDetailsEle = repository.GetObject(driver, strConfig, "LN_MenuItem_RiskDetails");
			common.JSClick(driver, strConfig, menuItemRiskDetailsEle);
			CommonLib.smallDelay();
			CommonLib.waitForLoad(driver, strConfig);
			CommonLib.smallDelay();

			// -------Feature Switch Field ---------//
			// CommonLib.WaitForElementToBeVisible(driver, strConfig, "BTN_NBI_Disabled");
			CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskDetails_PolicyType");

			CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Cancel");
			Project.ExecuteRow(driver, strConfig, "BTN_Cancel", "Click");
			CommonLib.waitForLoad(driver, strConfig);
			
			CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_RiskDashboard_NewRisk");
			CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_Logout_IntegroAccount");

		} catch (NoSuchElementException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (StaleElementReferenceException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (JavascriptException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (TimeoutException e) {
			TestReporter.Fail(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (NullPointerException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		} catch (IllegalArgumentException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

		}
	}

	public static void verifyRiskDetailsTab_AfterSave(RemoteWebDriver driver, String strConfig, String strTestingType) {
		CommonLib.waitForLoad(driver, strConfig);

		// NBI Button is enabled
		CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_NBI");

		// NTU, DMS DOCUMENTS,NOTES Button is visible and enabled
		CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Actions_NTU");
		CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Actions_Dms_Documents");
		CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Actions_FirmOrder");
		CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Actions_Notes");
		CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Actions_Copy");

		// Submit to Broker Ops Button is visible but not enabled
		CommonLib.WaitForElementToBeVisible(driver, strConfig, "BTN_Actions_SubmitTo_BrokerOps_Disabled");

	}
	
		public static void verifyRiskDetailsDefaultPopulatedFields(RemoteWebDriver driver, String strConfig) {
			
		}
		
		
		
		public static void verify_RiskDetails_MandatoryFields(RemoteWebDriver driver, String strConfig) {

			try {

				CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_MenuItem_RiskDetails");
				CommonLib.smallDelay();
				// LinkedHashMap<String, String> riskDetailsTabData =
				// RTData.getRiskDetailsData();

				// Click on the Risk Details Menu Item
				WebElement menuItemRiskDetailsEle = repository.GetObject(driver, strConfig, "LN_MenuItem_RiskDetails");
				common.JSClick(driver, strConfig, menuItemRiskDetailsEle);
				CommonLib.smallDelay();
				CommonLib.waitForLoad(driver, strConfig);
				CommonLib.smallDelay();

				// -------Wait for Mandatory Field to load properly ---------//
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "LBL_RiskDetails_PolicyType_MandatoryField");
				
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskDetails_PolicyType");
				
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskDetails_ClientCommission");
				
				
				WebElement policyTypeEle = repository.GetObject(driver, strConfig, "TXT_RiskDetails_PolicyType");
				WebElement clientCommisionEle = repository.GetObject(driver, strConfig, "TXT_RiskDetails_ClientCommission");
				
				policyTypeEle.clear();
				
				
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskDetails_PolicyType");
				
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Save");
				Project.ExecuteRow(driver, strConfig, "BTN_Save", "Click");
				CommonLib.waitForLoad(driver, strConfig);
				
				//Wait for the Validation Required Messages
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "LBL_RiskDetails_PolicyType_RequiredValidation_Msg");
				
				

			} catch (NoSuchElementException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (StaleElementReferenceException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (JavascriptException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (TimeoutException e) {
				TestReporter.Fail(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (NullPointerException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (IllegalArgumentException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			}
		
		}
		
		
		
		public static void verify_ClientCommision_RequiredValues_ErrorMessage(RemoteWebDriver driver, String strConfig) {

			try {

				CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_MenuItem_RiskDetails");
				CommonLib.smallDelay();
				// LinkedHashMap<String, String> riskDetailsTabData =
				// RTData.getRiskDetailsData();

				// Click on the Risk Details Menu Item
				WebElement menuItemRiskDetailsEle = repository.GetObject(driver, strConfig, "LN_MenuItem_RiskDetails");
				common.JSClick(driver, strConfig, menuItemRiskDetailsEle);
				CommonLib.smallDelay();
				CommonLib.waitForLoad(driver, strConfig);
				CommonLib.smallDelay();

				// -------Wait for Mandatory Field to load properly ---------//
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskDetails_PolicyType");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskDetails_ClientCommission");
				
				Project.ExecuteRow(driver, strConfig, "TXT_RiskDetails_PolicyType", "Test Policy");
				
				//Scenario 1
				//For values 1<=clientCommisionVal<=9
				String clientCommisionVal = Integer.toString(RTData.generateRandomNumbers(9, 1));
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskDetails_ClientCommission");
				Project.ExecuteRow(driver, strConfig, "TXT_RiskDetails_ClientCommission",
						clientCommisionVal);
				
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Save");
				Project.ExecuteRow(driver, strConfig, "BTN_Save", "Click");
				CommonLib.waitForLoad(driver, strConfig);
				
				//Wait for the Validation Required Messages
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "LBL_RiskDetails_ClientCommision_RequiredValidation_Msg");
				
				
				//Scenario 2
				//For values 36<=clientCommisionVal2<=100
				String clientCommisionVal2 = Integer.toString(RTData.generateRandomNumbers(100, 36));
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskDetails_ClientCommission");
				Project.ExecuteRow(driver, strConfig, "TXT_RiskDetails_ClientCommission",
						clientCommisionVal2);
				
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Save");
				Project.ExecuteRow(driver, strConfig, "BTN_Save", "Click");
				CommonLib.waitForLoad(driver, strConfig);
				
				//Wait for the Validation Required Messages
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "LBL_RiskDetails_ClientCommision_RequiredValidation_Msg");
				
				
				

			} catch (NoSuchElementException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (StaleElementReferenceException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (JavascriptException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (TimeoutException e) {
				TestReporter.Fail(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (NullPointerException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (IllegalArgumentException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			}
		
		}
		
		public static void generateNBIDoc(RemoteWebDriver driver, String strConfig, ArrayList<String> riskDetailsData) {
			CommonLib.waitForLoad(driver, strConfig);

			if (riskDetailsData.get(17) != null && !(riskDetailsData.get(17).trim().isEmpty())
					&& riskDetailsData.get(17).trim().contentEquals("Yes")) {
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_NBI");

				Project.ExecuteRow(driver, strConfig, "BTN_NBI", "Click");

				CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_NBI");
			}
		}
		
		public static void verifyRiskDashboard(RemoteWebDriver driver, String strConfig) {
			
			
			
		}
		
		
		public static Boolean searchRisk(RemoteWebDriver driver, String strConfig, String strRiskRef,
				Boolean openRiskCard) {
			Boolean riskFound = false;
			try {
				CommonLib.getLogger(strConfig).info("Inside searchRisk");
				TestReporter.Info(driver, strConfig, "Inside Function",
						"searchRisk()");
				if(strRiskRef !=null && !(strRiskRef.trim().isEmpty())) {
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskDashboard_SearchRisk");
				Project.ExecuteRow(driver, strConfig, "TXT_RiskDashboard_SearchRisk", strRiskRef);

				CommonLib.waitForLoad(driver, strConfig);
				CommonLib.Delay();
				
				String[] locatorDetails = CommonLib.getLocatorDetails_From_ObjectRepository(driver, strConfig,
						"LN_RiskDashboard_RiskCard_RiskRef");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, String.format(locatorDetails[1], strRiskRef));
				riskFound = CommonLib.isElementVisible(driver, strConfig, String.format(locatorDetails[1], strRiskRef));

				if (riskFound) {

					CommonLib.getLogger(strConfig).info("PASS: Risk present in Risk Tracker Dashboard");
					TestReporter.Pass(driver, strConfig, "Risk on Dashboard", "Risk present in Risk Tracker Dashboard");
					CommonLib.WaitForElementToBeClickable(driver, strConfig,
							String.format(locatorDetails[1], strRiskRef));

					if (openRiskCard.equals(true)) {
						WebElement riskCard_RiskRefEle = driver
								.findElement(By.xpath(String.format(locatorDetails[1], strRiskRef)));
						TestReporter.Info(driver, strConfig, "Open Risk Card: ", "TRUE");

						common.JSClick(driver, strConfig, riskCard_RiskRefEle);
						CommonLib.waitForLoad(driver, strConfig);
						CommonLib.WaitForElementToBeClickable(driver, strConfig, "DDO_RiskOverview_Division");
						return riskFound;
					}
				} else {
					CommonLib.getLogger(strConfig).info("INFO: Risk not present in Risk Tracker Dashboard");
					TestReporter.Info(driver, strConfig, "Risk on Dashboard",
							"Risk Not present in Risk Tracker Dashboard");

				}
			}
			else {
				CommonLib.getLogger(strConfig).info("FAIL: Risk not supplied/unavailable. Hence cannot proceed with Risk Search");
				TestReporter.Fail(driver, strConfig, "Risk not supplied/unavailable",
						"Cannot proceed with Risk Search");
			}
				
			} catch (NoSuchElementException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (StaleElementReferenceException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (JavascriptException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (TimeoutException e) {
				TestReporter.Fail(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (NullPointerException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (IllegalArgumentException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			}
			return riskFound;

		}
		
		public static void NTURisk(RemoteWebDriver driver, String strConfig, String  strTestingType, ArrayList<String> NTUData) {
			try {
				if (NTUData.get(0) != null && !(NTUData.get(0).trim().isEmpty())) {
					CommonLib.waitForLoad(driver, strConfig);
					// Wait for NTU Button is visible
					CommonLib.WaitForElementToBeVisible(driver, strConfig, "BTN_Actions_NTU");
					WebElement NTUBtnEle = repository.GetObject(driver, strConfig, "BTN_Actions_NTU");
					if (NTUBtnEle.isDisplayed()) {
						CommonLib.getLogger(strConfig).info("INFO: NTU button is visible.");
						TestReporter.Info(driver, strConfig, "NTU button : ", "Is Visible");
						// Wait for NTU Button is visible and enabled
						CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Actions_NTU");
						if (NTUBtnEle.isEnabled()) {
							
							
							CommonLib.getLogger(strConfig)
									.info("INFO: NTU button is enabled. Proceeding with NTU of Risk");

							Project.ExecuteRow(driver, strConfig, "BTN_Actions_NTU", "Click");

							// Wait for the NTU Pop Up window to show up
							CommonLib.WaitForElementToBeClickable(driver, strConfig, Constants.NTUPopUp);

							// Select the NTU Reason from Dropdown
							if (NTUData.get(1) != null && !(NTUData.get(1).trim().isEmpty())) {
								Project.ExecuteRow(driver, strConfig, "DDO_NTU_Reason", "Click");
								
								String[] NTUReasonOptionlocatorDetails = CommonLib
										.getLocatorDetails_From_ObjectRepository(driver, strConfig,
												"DDO_NTU_ReasonOption");

								CommonLib.WaitForElementToBeClickable(driver, strConfig,
										String.format(NTUReasonOptionlocatorDetails[1], NTUData.get(1)));

								WebElement NTUReasonOptionEle = driver.findElement(
										By.xpath(String.format(NTUReasonOptionlocatorDetails[1], NTUData.get(1))));

								common.JSClick(driver, strConfig, NTUReasonOptionEle);
							}

							else {
								CommonLib.getLogger(strConfig).info(
										"INFO: NTU Reason not specified in RTTestData.xlsx file.Hence Proceeding with NTU with Default Option");
								TestReporter.Info(driver, strConfig, "NTU Reason not specified in RTTestData.xlsx file",
										"Hence Proceeding with NTU with Default Option");
							}
							

							// Click on the Confirm Button on the NTU Pop Up
							String[] NTUBtnlocatorDetails = CommonLib.getLocatorDetails_From_ObjectRepository(driver,
									strConfig, "BTN_NTU_Confirm");
							CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_NTU_Confirm");

							Project.ExecuteRow(driver, strConfig, "BTN_NTU_Confirm", "Click");
							CommonLib.waitForLoad(driver, strConfig);

							// Verify Success Message is shown after Risk is NTU'd
							CommonLib.WaitForElementToBeVisible(driver, strConfig, Constants.NotifMsg_XPATH);

							String strNotifMsg = driver.findElement(By.xpath(Constants.NotifMsg_XPATH)).getText();

							if (strNotifMsg.trim().contentEquals(Constants.NTUSuccessMsg)) {
								CommonLib.getLogger(strConfig).info("PASS: NTU Risk Success message shown");
								TestReporter.Pass(driver, strConfig, "NTU Risk", "NTU Risk Success message shown");
								CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Actions_Reinstate");
								BusinessRules.verifyRisk_AfterNTU(driver, strConfig,strTestingType, NTUData);
							}

							else {
								CommonLib.getLogger(strConfig).info("FAIL: NTU Risk Success message not shown");
								if (strNotifMsg.trim().contentEquals(Constants.QuoteAlreadyCreatedErrorMsg)) {
									CommonLib.getLogger(strConfig)
											.info("FAIL: NTU Risk: 'Quote already created' error message shown");
									TestReporter.Fail(driver, strConfig, "NTU Risk Error Message",
											" 'Quote already created' error message shown");
								}

								else if (strNotifMsg.trim().contentEquals(Constants.ConnectionTimeoutErrorMsg)) {
									CommonLib.getLogger(strConfig)
											.info("FAIL: NTU Risk: 'Connection Timeout' error message shown");
									TestReporter.Fail(driver, strConfig, "NTU Risk Error Message",
											" 'Connection Timeout' error message shown");
								}

								else if (strNotifMsg.trim().contentEquals(Constants.InternalServerErrorMsg)) {
									CommonLib.getLogger(strConfig)
											.info("FAIL: NTU Risk: '500 Internal Server Error' message shown");
									TestReporter.Fail(driver, strConfig, "NTU Risk Error Message",
											" '500 Internal Server Error' message shown");
								}

							}

						} else {
							CommonLib.getLogger(strConfig)
									.info("FAIL: NTU button disabled. Hence cannot proceed with NTUing the Risk");
							TestReporter.Fail(driver, strConfig, "NTU button",
									"Is disabled and cannot proceed with NTUing the Risk");

						}
					} else {
						CommonLib.getLogger(strConfig)
								.info("FAIL: NTU button not visible. Hence cannot proceed with NTUing the Risk");
						TestReporter.Fail(driver, strConfig, "NTU button",
								"Is not visible and cannot proceed with NTUing the Risk");

					}
				}

				else {
					CommonLib.getLogger(strConfig).info(
							"INFO: Risk Tracker Reference not available. Hence cannot proceed with NTUing the Risk");
					TestReporter.Info(driver, strConfig, "Risk Tracker Reference not available",
							"Cannot proceed with NTUing the Risk");
				}
			} catch (NoSuchElementException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (StaleElementReferenceException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (JavascriptException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (TimeoutException e) {
				TestReporter.Fail(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (NullPointerException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (IllegalArgumentException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			}

		}
		
		
		public static void verify_CancelBtn_onNTUPopUp(RemoteWebDriver driver, String strConfig) {
			try {
			
					CommonLib.waitForLoad(driver, strConfig);
					// Wait for NTU Button is visible
					CommonLib.WaitForElementToBeVisible(driver, strConfig, "BTN_Actions_NTU");
					WebElement NTUBtnEle = repository.GetObject(driver, strConfig, "BTN_Actions_NTU");
					if (NTUBtnEle.isDisplayed()) {
						CommonLib.getLogger(strConfig).info("INFO: NTU button is visible.");
						TestReporter.Info(driver, strConfig, "NTU button : ", "Is Visible");
						// Wait for NTU Button is visible and enabled
						CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Actions_NTU");
						if (NTUBtnEle.isEnabled()) {
							TestReporter.Info(driver, strConfig, "NTU button : ", "Is Enabled");
							Project.ExecuteRow(driver, strConfig, "BTN_Actions_NTU", "Click");

							// Wait for the NTU Pop Up window to show up
							CommonLib.WaitForElementToBeVisible(driver, strConfig, Constants.NTUPopUp);

							// Click on the Confirm Button on the NTU Pop Up
							CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_NTU_Cancel");

							Project.ExecuteRow(driver, strConfig, "BTN_NTU_Cancel", "Click");
							CommonLib.waitForLoad(driver, strConfig);
							
							Boolean NTUPopUpVisible = common.isElementPresent( driver, strConfig, Constants.NTUPopUp);
							
							if(NTUPopUpVisible) {
								TestReporter.Fail(driver, strConfig, "NTU PopUp",
										"Still visible after clicking on Cancel Button on NTU Pop Up");
							}
							else {
								TestReporter.Pass(driver, strConfig, "NTU PopUp",
										"Disappeared after clicking on Cancel Button on NTU Pop Up");
							}



						} else {
							CommonLib.getLogger(strConfig)
									.info("FAIL: NTU button disabled. Hence cannot proceed with NTUing the Risk");
							TestReporter.Fail(driver, strConfig, "NTU button",
									"Is disabled and cannot proceed with NTUing the Risk");

						}
					} else {
						CommonLib.getLogger(strConfig)
								.info("FAIL: NTU button not visible. Hence cannot proceed with NTUing the Risk");
						TestReporter.Fail(driver, strConfig, "NTU button",
								"Is not visible and cannot proceed with NTUing the Risk");

					}
				
			} catch (NoSuchElementException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (StaleElementReferenceException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (JavascriptException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (TimeoutException e) {
				TestReporter.Fail(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (NullPointerException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (IllegalArgumentException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			}

		}
		
		
		
				
		
		
		public static void verifyRisk_AfterNTU(RemoteWebDriver driver, String strConfig, String  strTestingType, ArrayList<String> NTUData) {
			try {
				CommonLib.getLogger(strConfig).info("Inside verifyRisk_AfterNTU");
				CommonLib.waitForLoad(driver, strConfig);

				// RISK OVERVIEW TAB
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_MenuItem_RiskOverview");

				// Click on the Risk Details Menu Item
				WebElement menuItemRiskOverviewEle = repository.GetObject(driver, strConfig,
						"LN_MenuItem_RiskOverview");
				common.JSClick(driver, strConfig, menuItemRiskOverviewEle);
				CommonLib.waitForLoad(driver, strConfig);

				CommonLib.WaitForElementToBeClickable(driver, strConfig, "DDO_RiskOverview_Division");

				String riskTrackerRefNum = RTData.getRiskTracker_RefNo(driver, strConfig,
						Constants.riskTrackerRefNo_XPATH);

				// Verify RE-INSTATE, DMS DOCUMENTS and NOTES(0) button are visible and
				// enabled(or clickable)
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Actions_Reinstate");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Actions_Dms_Documents");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Actions_Notes");

				// --------Verify Stage Checked - CREATED and NTU
				// --------Verify Stage Disabled -  FIRM ORDER, SUBMIT TO BROKER OPS

				CommonLib.WaitForElementToBeVisible(driver, strConfig,
						Constants.riskOverview_Created_StageIcon_Checked_XPATH);
				CommonLib.WaitForElementToBeVisible(driver, strConfig,
						Constants.riskOverview_NBI_StageIcon_Disabled_XPATH);
				CommonLib.WaitForElementToBeVisible(driver, strConfig,
						Constants.riskOverview_Quote_StageIcon_Disabled_XPATH);
				CommonLib.WaitForElementToBeVisible(driver, strConfig,
						Constants.riskOverview_FirmOrder_StageIcon_Disabled_XPATH);
				CommonLib.WaitForElementToBeVisible(driver, strConfig,
						Constants.riskOverview_SubmittedTo_BrokerOps_StageIcon_Disabled_XPATH);
				CommonLib.WaitForElementToBeVisible(driver, strConfig,
						Constants.riskOverview_NTU_StageIcon_Checked_XPATH);

				// ---------------Verify Date Populated against Created Date Field------------
				WebElement CreatedDateEle = driver
						.findElement(By.xpath(Constants.riskOverview_Created_Stage_Date_Populated_XPATH));
				String createdDate = CreatedDateEle.getText();

				Boolean isCreatedDateFormatValid = RTData.isValidStatusDateAndFormat(createdDate);

				if (isCreatedDateFormatValid) {
					CommonLib.getLogger(strConfig)
							.info("PASS: Created Date populated in the format 'dd MMM yyyy' after Risk NTU.");
					TestReporter.Pass(driver, strConfig, "Created Date",
							"Populated in the format 'dd MMM yyyy' after Risk NTU.");
				}

				else {
					CommonLib.getLogger(strConfig)
							.info("FAIL: Created Date not populated in the format 'dd MMM yyyy' after Risk NTU.");
					TestReporter.Fail(driver, strConfig, "Created Date",
							"Not populated in the format 'dd MMM yyyy' after Risk NTU.");

				}

				// ---------------Verify Current Date Populated against NTU Date
				// Field------------
				WebElement NTUDateEle = driver
						.findElement(By.xpath(Constants.riskOverview_NTU_Stage_Date_Populated_XPATH));
				String riskNTUDate = NTUDateEle.getText();

				if (riskNTUDate.trim().contentEquals(RTData.getCurrentStageDate())) {
					CommonLib.getLogger(strConfig).info("PASS: NTU Date(as Current Date) visible after Risk NTU ");
					TestReporter.Pass(driver, strConfig, "NTU Date(as Current Date): ", "Visible after Risk NTU ");
				}

				else {
					CommonLib.getLogger(strConfig).info("FAIL: NTU Date(as Current Date)  not visible after Risk NTU");
					TestReporter.Fail(driver, strConfig, "NTU Date(as Current Date): ", "Not visible after Risk NTU");

				}

				// --- Verify Risk Overview Fields Disabled (except Cancel Button)
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "TXT_RiskOverview_UMR_Disabled");
				CommonLib.WaitForElementToBeVisible(driver, strConfig,
						"CBO_RiskOverview_NewOrRenewal_Classification_Disabled");
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "CBO_RiskOverview_Division_Disabled");
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "TXT_RiskOverview_AgentOrClient_Disabled");
				CommonLib.WaitForElementToBeVisible(driver, strConfig,
						"CHK_RiskOverview_DirectInsured_Placement_ONOFF_Switch_Disabled");
				CommonLib.WaitForElementToBeVisible(driver, strConfig,
						"CHK_RiskOverview_Reinsurance_ONOFF_Switch_Disabled");
				CommonLib.WaitForElementToBeVisible(driver, strConfig,
						"TXT_RiskOverview_Insured_Or_DirectInsured_Or_OriginalInsured_Disabled");
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "TXT_RiskOverview_Assured_Disabled");
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "CBO_RiskOverview_AccountExecutive_Disabled");
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "CBO_RiskOverview_PlacingBroker_Disabled");
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "BTN_Save_Disabled");
				//to be uncomment for phase 2
				//CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Cancel");

				// --- Verify Risk Details Fields Disabled (except Cancel Button)
				// RISK DETAILS TAB
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_MenuItem_RiskDetails");
				CommonLib.smallDelay();
				// Click on the Risk Details Menu Item
				WebElement menuItemRiskDetailsEle = repository.GetObject(driver, strConfig, "LN_MenuItem_RiskDetails");
				common.JSClick(driver, strConfig, menuItemRiskDetailsEle);
				// Project.ExecuteRow(driver, strConfig, "LN_MenuItem_RiskDetails", "Click");
				CommonLib.waitForLoad(driver, strConfig);

				CommonLib.WaitForElementToBeVisible(driver, strConfig, "TXT_RiskDetails_PolicyType_Disabled");
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "CBO_RiskDetails_PremiumCurrency_Code_Disabled");

				CommonLib.WaitForElementToBeVisible(driver, strConfig, "TXT_RiskDetails_Limit_Disabled");
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "TXT_RiskDetails_Premium_Disabled");
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "TXT_RiskDetails_Deductible_Disabled");
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "TXT_RiskDetails_ClientCommission_Disabled");
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "TXT_RiskDetails_Information_Disabled");
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "TXT_RiskDetails_Terms_Disabled");
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "BTN_Save_Disabled");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Cancel");

			} catch (NoSuchElementException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (StaleElementReferenceException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (JavascriptException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (TimeoutException e) {
				TestReporter.Fail(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (NullPointerException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (IllegalArgumentException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			}

		}

		public static void reinstateRisk(RemoteWebDriver driver, String strConfig, String  strTestingType, ArrayList<String> ReinstateData) {
			try {
				CommonLib.getLogger(strConfig).info("INFO: Inside reinstateRisk");
				if (ReinstateData.get(0) != null && !(ReinstateData.get(0).trim().isEmpty())) {
					CommonLib.waitForLoad(driver, strConfig);
					// Wait for Reinstate Button is visible and clickable
					CommonLib.WaitForElementToBeVisible(driver, strConfig, "BTN_Actions_Reinstate");
					WebElement ReinstateBtnEle = repository.GetObject(driver, strConfig, "BTN_Actions_Reinstate");

					if (ReinstateBtnEle.isDisplayed()) {
						CommonLib.getLogger(strConfig).info("INFO: Reinstate button is visible.");
						TestReporter.Info(driver, strConfig, "Reinstate button : ", "Is Visible");
						// Wait for Reinstate Button is visible and clickable
						CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Actions_Reinstate");

						if (ReinstateBtnEle.isEnabled()) {
							CommonLib.getLogger(strConfig).info(
									"INFO: Reinstate button is visible and enabled. Proceeding with unNTU or Reinstating the Risk");

							Project.ExecuteRow(driver, strConfig, "BTN_Actions_Reinstate", "Click");

							// Verify Success Message is shown after Risk is Reinstated
							CommonLib.WaitForElementToBeVisible(driver, strConfig, Constants.NotifMsg_XPATH);

							String strNotifMsg = driver.findElement(By.xpath(Constants.NotifMsg_XPATH)).getText();

							if (strNotifMsg.trim().contentEquals(Constants.ReinstateSuccessMsg)) {
								CommonLib.getLogger(strConfig).info("PASS: Reinstate Risk Success message shown");
								TestReporter.Pass(driver, strConfig, "Reinstate Risk",
										"Reinstate Risk Success message shown");
								CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Actions_NTU");
								BusinessRules.verifyRisk_AfterReinstate(driver, strTestingType, strConfig, ReinstateData);
							}

							else {
								CommonLib.getLogger(strConfig).info("FAIL: Reinstate Risk Success message not shown");
								if (strNotifMsg.trim().contentEquals(Constants.QuoteAlreadyCreatedErrorMsg)) {
									CommonLib.getLogger(strConfig)
											.info("FAIL: Reinstate Risk: 'Quote already created' error message shown");
									TestReporter.Fail(driver, strConfig, "Reinstate Risk Error Message",
											" 'Quote already created' error message shown");
								}

								else if (strNotifMsg.trim().contentEquals(Constants.ConnectionTimeoutErrorMsg)) {
									CommonLib.getLogger(strConfig)
											.info("FAIL: Reinstate Risk: 'Connection Timeout' error message shown");
									TestReporter.Fail(driver, strConfig, "Reinstate Risk Error Message",
											" 'Connection Timeout' error message shown");
								}

								else if (strNotifMsg.trim().contentEquals(Constants.InternalServerErrorMsg)) {
									CommonLib.getLogger(strConfig)
											.info("FAIL: Reinstate Risk: '500 Internal Server Error' message shown");
									TestReporter.Fail(driver, strConfig, "Reinstate Risk Error Message",
											" '500 Internal Server Error' message shown");
								}

							}

						} else {
							CommonLib.getLogger(strConfig).info(
									"FAIL: Reinstate button disabled. Hence cannot proceed with Reinstating a Risk");
							TestReporter.Fail(driver, strConfig, "Reinstate button",
									"Is disabled and cannot proceed with Reinstating a Risk");

						}

					} else {
						CommonLib.getLogger(strConfig).info(
								"FAIL: Reinstate button not visible. Hence cannot proceed with Reinstating a Risk");
						TestReporter.Fail(driver, strConfig, "Reinstate button",
								"Is not visible and cannot proceed with Reinstating a Risk");

					}
				}

				else {
					CommonLib.getLogger(strConfig).info(
							"INFO: Risk Tracker Reference not available. Hence cannot proceed with Reinstating the Risk");
					TestReporter.Info(driver, strConfig, "Risk Tracker Reference not available",
							"Cannot proceed with Reinstating the Risk");
				}
			} catch (NoSuchElementException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (StaleElementReferenceException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (JavascriptException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (TimeoutException e) {
				TestReporter.Fail(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (NullPointerException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (IllegalArgumentException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			}

		}
		
		
		public static void verifyRisk_AfterReinstate(RemoteWebDriver driver, String strConfig, String  strTestingType,
				ArrayList<String> NTUData) {

			try {
				CommonLib.getLogger(strConfig).info("Inside verifyRisk_AfterReinstate");
				CommonLib.waitForLoad(driver, strConfig);

				// RISK OVERVIEW TAB
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_MenuItem_RiskOverview");

				// Click on the Risk Details Menu Item
				WebElement menuItemRiskOverviewEle = repository.GetObject(driver, strConfig,
						"LN_MenuItem_RiskOverview");
				common.JSClick(driver, strConfig, menuItemRiskOverviewEle);
				CommonLib.waitForLoad(driver, strConfig);

				CommonLib.WaitForElementToBeClickable(driver, strConfig, "DDO_RiskOverview_Division");

				String riskTrackerRefNum = RTData.getRiskTracker_RefNo(driver, strConfig,
						Constants.riskTrackerRefNo_XPATH);

				// Verify NTU, DMS DOCUMENTS and NOTES(0) button are visible and enabled(or
				// clickable)
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Actions_NTU");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Actions_Dms_Documents");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Actions_Notes");
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "BTN_Actions_FirmOrder");
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "BTN_Actions_SubmitTo_BrokerOps");

				// --------Verify Stage Checked - CREATED
				// --------Verify Stage Unchecked - NBI, QUOTE, FIRM ORDER, SUBMIT TO BROKER OPS

				CommonLib.WaitForElementToBeVisible(driver, strConfig,
						Constants.riskOverview_Created_StageIcon_Checked_XPATH);
				CommonLib.WaitForElementToBeVisible(driver, strConfig,
						Constants.riskOverview_NBI_StageIcon_UnChecked_XPATH);
				CommonLib.WaitForElementToBeVisible(driver, strConfig,
						Constants.riskOverview_Quote_StageIcon_UnChecked_XPATH);
				CommonLib.WaitForElementToBeVisible(driver, strConfig,
						Constants.riskOverview_FirmOrder_StageIcon_UnChecked_XPATH);
				CommonLib.WaitForElementToBeVisible(driver, strConfig,
						Constants.riskOverview_SubmittedTo_BrokerOps_StageIcon_UnChecked_XPATH);

				// ---------------Verify Date Populated against Created Date Field------------
				WebElement CreatedDateEle = driver
						.findElement(By.xpath(Constants.riskOverview_Created_Stage_Date_Populated_XPATH));
				String createdDate = CreatedDateEle.getText();
				Boolean isCreatedDateFormatValid = RTData.isValidStatusDateAndFormat(createdDate);

				if (isCreatedDateFormatValid) {
					CommonLib.getLogger(strConfig)
							.info("PASS: Created Date populated in the format 'dd MMM yyyy' after Risk Reinstate");
					TestReporter.Pass(driver, strConfig, "Created Date",
							"Populated in the format 'dd MMM yyyy' after Risk Reinstate");
				}

				else {
					CommonLib.getLogger(strConfig)
							.info("FAIL: Created Date populated in the format 'dd MMM yyyy' after Risk Reinstate");
					TestReporter.Fail(driver, strConfig, "Created Date",
							"Populated in the format 'dd MMM yyyy' after Risk Reinstate");

				}

				// --- Verify Risk Overview Fields Enabled (except UMR and Agent/Client)
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "TXT_RiskOverview_UMR_Disabled");

				CommonLib.WaitForElementToBeClickable(driver, strConfig,
						"DDO_RiskOverview_NewOrRenewal_Classification");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "DDO_RiskOverview_Division");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskOverview_AgentOrClient");
				CommonLib.WaitForElementToBeClickable(driver, strConfig,
						"CHK_RiskOverview_DirectInsured_Placement_ONOFF_Switch");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "CHK_RiskOverview_Reinsurance_ONOFF_Switch");
				CommonLib.WaitForElementToBeClickable(driver, strConfig,
						"TXT_RiskOverview_Insured_Or_DirectInsured_Or_OriginalInsured");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskOverview_Assured");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "DDO_RiskOverview_AccountExecutive");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "DDO_RiskOverview_PlacingBroker");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Save");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Cancel");

				// --- Verify Risk Details Fields Enabled
				// RISK DETAILS TAB
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_MenuItem_RiskDetails");
				CommonLib.smallDelay();
				// Click on the Risk Details Menu Item
				WebElement menuItemRiskDetailsEle = repository.GetObject(driver, strConfig, "LN_MenuItem_RiskDetails");
				common.JSClick(driver, strConfig, menuItemRiskDetailsEle);
				CommonLib.waitForLoad(driver, strConfig);
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskDetails_PolicyType");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "DDO_RiskDetails_PremiumCurrency_Code");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskDetails_Limit");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskDetails_Premium");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskDetails_Deductible");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskDetails_ClientCommission");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskDetails_Information");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskDetails_Terms");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Save");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Cancel");

			} catch (NoSuchElementException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (StaleElementReferenceException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (JavascriptException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (TimeoutException e) {
				TestReporter.Fail(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (NullPointerException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (IllegalArgumentException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			}
		}
		
		public static void loginDMS(RemoteWebDriver driver, String strConfig, String strUsername, String strPassword) {
			CommonLib.getLogger(strConfig).info(" Inside verifyDMSLocation");
			/*CommonLib.waitForLoad(driver, strConfig);
			CommonLib.smallDelay();
			CommonLib.getLogger(strConfig).info(" Inside verifyDMSLocation");
			String pwd = CommonLib.DecryptPassword(strPassword);
			
			CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Actions_Dms_Documents");

			// Click DMS Button
			CommonLib.smallDelay();
			WebElement DMSBtnEle = repository.GetObject(driver, strConfig, "BTN_Actions_Dms_Documents");
			common.JSClick(driver, strConfig, DMSBtnEle);

			CommonLib.mediumDelay();
			CommonLib.waitForLoad(driver, strConfig); */

			try {
				Runtime.getRuntime().exec("C:/RT2Automation/RT2/AutoIT/ReadExcel.exe");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			CommonLib.highDelay();
			CommonLib.waitForLoad(driver, strConfig);

		}
		
		
		public static void verifyDMSLocation(RemoteWebDriver driver, String strConfig) {
			CommonLib.highDelay();
			WebElement riskFolderEle =repository.GetObject(driver, strConfig, "LBL_DMS_RiskFolder");
			
			System.out.println("riskFolderEle.isDisplayed()"+riskFolderEle.isDisplayed());
			//CommonLib.WaitForElementToBeVisible(driver, strConfig, "LBL_DMS_RiskFolder");
		}
		
		
		public static void firmOrder_Risk(RemoteWebDriver driver, String strConfig, String strTestingType, ArrayList<String> FirmOrderData) {

			try {
				CommonLib.waitForLoad(driver, strConfig);
				CommonLib.getLogger(strConfig).info(" Inside firmOrder_Risk");

				if (FirmOrderData.get(0) != null && !(FirmOrderData.get(0).trim().isEmpty())) {

					if (FirmOrderData.get(1) != null && !(FirmOrderData.get(1).trim().isEmpty())) {

						if (FirmOrderData.get(2) != null && !(FirmOrderData.get(2).trim().isEmpty())) {

							if (FirmOrderData.get(3) != null && !(FirmOrderData.get(3).trim().isEmpty())) {

								// RISK OVERVIEW TAB
								CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_MenuItem_RiskOverview");

								// Click on the Risk Details Menu Item
								WebElement menuItemRiskOverviewEle = repository.GetObject(driver, strConfig,
										"LN_MenuItem_RiskOverview");
								common.JSClick(driver, strConfig, menuItemRiskOverviewEle);
								CommonLib.waitForLoad(driver, strConfig);

								CommonLib.WaitForElementToBeClickable(driver, strConfig, "DDO_RiskOverview_Division");
								//Uncomment in Phase 2
								//CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskOverview_AgentOrClient");
								
								WebElement agentOrClientEle = repository.GetObject(driver, strConfig,
										"TXT_RiskOverview_AgentOrClient");
								String strAgentOrClient = agentOrClientEle.getAttribute("value");

								CommonLib.WaitForElementToBeVisible(driver, strConfig, "BTN_Actions_FirmOrder");
								WebElement FirmOrderBtnEle = repository.GetObject(driver, strConfig,
										"BTN_Actions_FirmOrder");

								if (FirmOrderBtnEle.isDisplayed()) {
									CommonLib.getLogger(strConfig).info("INFO: Firm Order button is visible.");
									TestReporter.Info(driver, strConfig, "Firm Order button : ", "Is Visible");
									CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Actions_FirmOrder");
									if (FirmOrderBtnEle.isEnabled()) {
										
										
										CommonLib.getLogger(strConfig).info(
												"INFO: Firm Order button is visible and enabled. Proceeding with taking the Risk to Firm Order");
										TestReporter.Info(driver, strConfig, "Firm Order button : ",
												"Visible and Enabled. Proceeding with taking the Risk to Firm Order");

										Project.ExecuteRow(driver, strConfig, "BTN_Actions_FirmOrder", "Click");

										CommonLib.WaitForElementToBeVisible(driver, strConfig,
												Constants.FirmOrder_PopUp_XPATH);

										CommonLib.WaitForElementToBeVisible(driver, strConfig,
												"TXT_FirmOrder_InceptionDate");
										CommonLib.WaitForElementToBeVisible(driver, strConfig,
												"TXT_FirmOrder_ExpiryDate");
										CommonLib.WaitForElementToBeVisible(driver, strConfig, "TXT_FirmOrder_SDD");
										CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_FirmOrder_PPW");

										BusinessRules.verifyDefault_FirmOrder_PopUp_FieldValues(driver, strConfig,
												strAgentOrClient);

										String[] inceptionDate = FirmOrderData.get(1).split("/");
										WebElement inceptionDateEle = repository.GetObject(driver, strConfig,
												"TXT_FirmOrder_InceptionDate");
										inceptionDateEle.sendKeys(inceptionDate[1]);
										inceptionDateEle.sendKeys(inceptionDate[0]);
										inceptionDateEle.sendKeys(inceptionDate[2]);
										inceptionDateEle.sendKeys(Keys.TAB);

										CommonLib.Delay();

										String[] expiryDate = FirmOrderData.get(2).split("/");
										WebElement expiryDateEle = repository.GetObject(driver, strConfig,
												"TXT_FirmOrder_ExpiryDate");
										expiryDateEle.sendKeys(expiryDate[1]);
										expiryDateEle.sendKeys(expiryDate[0]);
										expiryDateEle.sendKeys(expiryDate[2]);
										expiryDateEle.sendKeys(Keys.TAB);

										CommonLib.Delay();
										
										String[] settlementDueDate = FirmOrderData.get(3).split("/");
										WebElement settlementDueDateEle = repository.GetObject(driver, strConfig,
												"TXT_FirmOrder_SDD");
										settlementDueDateEle.sendKeys(settlementDueDate[1]);
										settlementDueDateEle.sendKeys(settlementDueDate[0]);
										settlementDueDateEle.sendKeys(settlementDueDate[2]);
										settlementDueDateEle.sendKeys(Keys.TAB);

										CommonLib.Delay();

										if (FirmOrderData.get(4) != null && !(FirmOrderData.get(3).trim().isEmpty())) {
											String PPWRequired = FirmOrderData.get(4);

											if (PPWRequired.trim().contentEquals("Yes")) {
												CommonLib.getLogger(strConfig).info("INFO: PPW Required");
												TestReporter.Info(driver, strConfig, "PPW : ", "Required");
												Project.ExecuteRow(driver, strConfig, "BTN_FirmOrder_PPW", "Click");

											} else {
												CommonLib.getLogger(strConfig).info("INFO: PPW Not Required");
												TestReporter.Info(driver, strConfig, "PPW : ", "Not Required");
											}
										}
										CommonLib.WaitForElementToBeVisible(driver, strConfig,
												"LBL_FirmOrder_DMS_SuccessMessage");
										
										CommonLib.WaitForElementToBeClickable(driver, strConfig,
												"BTN_FirmOrder_Confirm");
										
										Project.ExecuteRow(driver, strConfig, "BTN_FirmOrder_Confirm", "Click");
										

										CommonLib.WaitForElementToBeVisible(driver, strConfig,
												Constants.NotifMsg_XPATH);

										String strNotifMsg = driver.findElement(By.xpath(Constants.NotifMsg_XPATH))
												.getText();

										// Firm Order Success Message Shown
										if (strNotifMsg.trim().contentEquals(Constants.FirmOrderSuccessMsg)) {
											CommonLib.getLogger(strConfig)
													.info("PASS: Firm Order Risk Success message shown");
											TestReporter.Pass(driver, strConfig, "Firm Order Risk",
													"Success message shown");
											CommonLib.WaitForElementToBeClickable(driver, strConfig,
													"BTN_Actions_SubmitTo_BrokerOps");
											BusinessRules.verifyRisk_After_FirmOrder(driver, strConfig, strTestingType, FirmOrderData);
										}

										// Firm Order Error Message Shown
										else {
											CommonLib.getLogger(strConfig)
													.info("FAIL: Firm Order Risk Success message not shown");
											if (strNotifMsg.trim()
													.contentEquals(Constants.QuoteAlreadyCreatedErrorMsg)) {
												CommonLib.getLogger(strConfig).info(
														"FAIL: Firm Order Risk: 'Quote already created' error message shown");
												TestReporter.Fail(driver, strConfig, "Firm Order Risk Error Message",
														" 'Quote already created' error message shown");
											}

											else if (strNotifMsg.trim()
													.contentEquals(Constants.ConnectionTimeoutErrorMsg)) {
												CommonLib.getLogger(strConfig).info(
														"FAIL: Firm Order Risk: 'Connection Timeout' error message shown");
												TestReporter.Fail(driver, strConfig, "Firm Order Risk Error Message",
														" 'Connection Timeout' error message shown");
											}

											else if (strNotifMsg.trim()
													.contentEquals(Constants.InternalServerErrorMsg)) {
												CommonLib.getLogger(strConfig).info(
														"FAIL: Firm Order Risk: '500 Internal Server Error' message shown");
												TestReporter.Fail(driver, strConfig, "Firm Order Risk Error Message",
														" '500 Internal Server Error' message shown");
											}

										}

									}

									else {
										CommonLib.getLogger(strConfig).info(
												"FAIL: Firm Order button disabled. Hence cannot proceed with taking the Risk to Firm Order");
										TestReporter.Fail(driver, strConfig, "Firm Order button",
												"Is disabled and hence cannot proceed with taking the Risk to Firm Order");
									}
								} else {
									CommonLib.getLogger(strConfig).info(
											"FAIL: Firm Order button not visible. Hence cannot proceed with taking the Risk to Firm Order");
									TestReporter.Fail(driver, strConfig, "Firm Order button",
											"Is not visible and hence cannot proceed with taking the Risk to Firm Order");
								}

							} else {
								CommonLib.getLogger(strConfig).info(
										"INFO: No Settlement Due Date(MANDATORY FIELD) supplied in RTTestdata.xlsx file. Cannot proceed with Risk Firm Order");
								TestReporter.Info(driver, strConfig,
										"No Settlement Due Date(MANDATORY FIELD) supplied in RTTestdata.xlsx file",
										" Cannot proceed with Risk Firm Order");

							}
						} else {
							CommonLib.getLogger(strConfig).info(
									"INFO: No Expiry Date(MANDATORY FIELD) supplied in RTTestdata.xlsx file. Cannot proceed with Risk Firm Order");
							TestReporter.Info(driver, strConfig,
									"No Expiry Date(MANDATORY FIELD) supplied in RTTestdata.xlsx file",
									"Cannot proceed with Risk Firm Order");

						}

					} else {
						CommonLib.getLogger(strConfig).info(
								"INFO: No Inception Date(MANDATORY FIELD) supplied in RTTestdata.xlsx file.Cannot proceed with Risk Firm Order");
						TestReporter.Info(driver, strConfig,
								"No Inception Date(MANDATORY FIELD) supplied in RTTestdata.xlsx file",
								"Cannot proceed with Risk Firm Order");
					}
				} else {

					CommonLib.getLogger(strConfig)
							.info("INFO: No RiskTracker Reference available. Cannot proceed with Risk Firm Order");
					TestReporter.Info(driver, strConfig, "No RiskTracker Reference available",
							" Cannot proceed with Risk Firm Order");
				}
			} catch (NoSuchElementException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (StaleElementReferenceException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (JavascriptException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (TimeoutException e) {
				TestReporter.Fail(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (NullPointerException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (IllegalArgumentException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			}

		}
		
		
		public static void verify_CancelBtn_On_FirmOrder_PopUp(RemoteWebDriver driver, String strConfig) {

			try {
				CommonLib.waitForLoad(driver, strConfig);
				TestReporter.Info(driver, strConfig, "Executing : ",
						"verify_CancelBtn_On_FirmOrder_PopUp");
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "BTN_Actions_FirmOrder");
				WebElement FirmOrderBtnEle = repository.GetObject(driver, strConfig, "BTN_Actions_FirmOrder");

				if (FirmOrderBtnEle.isDisplayed()) {
					CommonLib.getLogger(strConfig).info("INFO: Firm Order button is visible.");
					TestReporter.Info(driver, strConfig, "Firm Order button : ", "Is Visible");
					CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Actions_FirmOrder");
					if (FirmOrderBtnEle.isEnabled()) {
						CommonLib.getLogger(strConfig).info(
								"INFO: Firm Order button is visible and enabled. Proceeding with verify_CancelBtn_On_FirmOrder_PopUp");
						TestReporter.Info(driver, strConfig, "Firm Order button : ",
								"Visible and Enabled. Proceeding with verify_CancelBtn_On_FirmOrder_PopUp");

						Project.ExecuteRow(driver, strConfig, "BTN_Actions_FirmOrder", "Click");

						CommonLib.WaitForElementToBeVisible(driver, strConfig, Constants.FirmOrder_PopUp_XPATH);

						CommonLib.WaitForElementToBeVisible(driver, strConfig, "TXT_FirmOrder_InceptionDate");
						CommonLib.WaitForElementToBeVisible(driver, strConfig, "TXT_FirmOrder_ExpiryDate");
						CommonLib.WaitForElementToBeVisible(driver, strConfig, "TXT_FirmOrder_SDD");
						CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_FirmOrder_PPW");
						CommonLib.WaitForElementToBeVisible(driver, strConfig, "LBL_FirmOrder_DMS_SuccessMessage");
						CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_FirmOrder_Confirm");
						CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_FirmOrder_Cancel");

						Project.ExecuteRow(driver, strConfig, "BTN_FirmOrder_Cancel", "Click");
						CommonLib.waitForLoad(driver, strConfig);
						Boolean FirmOrderPopUpVisible = CommonLib.isElementVisible( driver, strConfig, Constants.FirmOrder_PopUp_XPATH);
						
						if(FirmOrderPopUpVisible) {
							TestReporter.Fail(driver, strConfig, "Firm Order PopUp",
									"Still visible after clicking on Cancel Button on Firm Order PopUp");
						}
						else {
							TestReporter.Pass(driver, strConfig, "Firm Order PopUp",
									"Disappeared after clicking on Cancel Button on Firm Order PopUp");
						}


						

					}

					else {
						CommonLib.getLogger(strConfig).info(
								"FAIL: Firm Order button disabled. Hence cannot proceed with taking the Risk to Firm Order");
						TestReporter.Fail(driver, strConfig, "Firm Order button",
								"Is disabled and hence cannot proceed with taking the Risk to Firm Order");
					}
				} else {
					CommonLib.getLogger(strConfig).info(
							"FAIL: Firm Order button not visible. Hence cannot proceed with taking the Risk to Firm Order");
					TestReporter.Fail(driver, strConfig, "Firm Order button",
							"Is not visible and hence cannot proceed with taking the Risk to Firm Order");
				}

			} catch (NoSuchElementException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (StaleElementReferenceException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (JavascriptException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (TimeoutException e) {
				TestReporter.Fail(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (NullPointerException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (IllegalArgumentException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			}

		}
		
		
		
		
		
		
		public static void verifyDefault_FirmOrder_PopUp_FieldValues(RemoteWebDriver driver, String strConfig, String strAgentOrClient_From_RiskOverview_Tab) {
			
			WebElement agentOrClient_FirmOrder_Ele = repository.GetObject(driver, strConfig, "TXT_FirmOrder_AgentOrClient_Disabled");
			String strAgentOrClient_FirmOrder = agentOrClient_FirmOrder_Ele.getAttribute("value");
			
			if (strAgentOrClient_FirmOrder.trim().contentEquals(strAgentOrClient_From_RiskOverview_Tab)) {
				CommonLib.getLogger(strConfig).info(
						"PASS: AgentOrClient value from Risk Overview tab matched with AgentOrClient value in Firm order Pop Up .");
				TestReporter.Pass(driver, strConfig, "AgentOr Client Value",
						"Are same in Risk Overvie tab and Firm Order Pop Up");
			}

			else {
				CommonLib.getLogger(strConfig).info(
						"FAIL: AgentOrClient value from Risk Overview tab did not match with AgentOrClient value in Firm order Pop Up.");
				TestReporter.Fail(driver, strConfig, "AgentOr Client Value",
						"Are different in Risk Overvie tab and Firm Order Pop Up");
			}
			
			WebElement inceptionDate_FirmOrder_Ele = repository.GetObject(driver, strConfig, "TXT_FirmOrder_InceptionDate");
			String strInceptionDate_FirmOrder = inceptionDate_FirmOrder_Ele.getAttribute("value");
			
			String[] incepDate = strInceptionDate_FirmOrder.split("-");
			String inceptionDate = "";
			inceptionDate = incepDate[2]+"/"+incepDate[1]+"/"+incepDate[0];
			if (inceptionDate.trim().contentEquals(CommonLib.GetCurrentDate())) {
				CommonLib.getLogger(strConfig).info(
						"PASS: Inception Date Default value is equal to Current Date");
				TestReporter.Pass(driver, strConfig, "Inception Date Default Value",
						"Equal to Current Date");
			}

			else {
				CommonLib.getLogger(strConfig).info(
						"FAIL: Inception Date Default value is not equal to Current Date");
				TestReporter.Fail(driver, strConfig, "Inception Date Default Value",
						"Not equal to Current Date");
			}
			
			
			WebElement expiryDate_FirmOrder_Ele = repository.GetObject(driver, strConfig, "TXT_FirmOrder_ExpiryDate");
			String strExpiryDate_FirmOrder = expiryDate_FirmOrder_Ele.getAttribute("value");
			if (strExpiryDate_FirmOrder.trim().contentEquals(RTData.getCurrentDate_Plus_OneYear("yyyy-MM-dd").toString())) {
				CommonLib.getLogger(strConfig).info(
						"PASS: Expiry Date Default value is equal to (Current Date + One Year)");
				TestReporter.Pass(driver, strConfig, "Expiry Date Default Value",
						"Equal to (Current Date + One Year)");
			}

			else {
				CommonLib.getLogger(strConfig).info(
						"FAIL: Expiry Date Default value is not equal to (Current Date + One Year)");
				TestReporter.Fail(driver, strConfig, "Expiry Date Default Value",
						"Not equal to (Current Date + One Year)");
			}
		
			
			
			WebElement SDD_FirmOrder_Ele = repository.GetObject(driver, strConfig, "TXT_FirmOrder_SDD");
			String SDD_FirmOrder = SDD_FirmOrder_Ele.getAttribute("value");
			
			if (SDD_FirmOrder.trim().isEmpty()) {
				CommonLib.getLogger(strConfig).info(
						"PASS: Settlement Due Date default value is Empty");
				TestReporter.Pass(driver, strConfig, "Settlement Due Date default Value",
						"Empty");
			}

			else {
				CommonLib.getLogger(strConfig).info(
						"FAIL: Settlement Due Date default value is not Empty ");
				TestReporter.Fail(driver, strConfig, "Settlement Due Date default Value",
						"Not Empty");
			}
		}
		
		
		public static void verifyRisk_After_FirmOrder(RemoteWebDriver driver, String strConfig, String strTestingType,
				ArrayList<String> FirmOrderData) {
			try {
				CommonLib.getLogger(strConfig).info("Inside verifyRisk_After_FirmOrder");
				CommonLib.waitForLoad(driver, strConfig);
				CommonLib.Delay();

				// RISK OVERVIEW TAB
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_MenuItem_RiskOverview");

				// Click on the Risk Details Menu Item
				WebElement menuItemRiskOverviewEle = repository.GetObject(driver, strConfig,
						"LN_MenuItem_RiskOverview");
				common.JSClick(driver, strConfig, menuItemRiskOverviewEle);
				CommonLib.waitForLoad(driver, strConfig);

				CommonLib.WaitForElementToBeVisible(driver, strConfig, "DDO_RiskOverview_Division");

				String riskTrackerRefNum = RTData.getRiskTracker_RefNo(driver, strConfig,
						Constants.riskTrackerRefNo_XPATH);

				// Check Firm Order and NTU button are disabled
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "BTN_Actions_FirmOrder_Disabled");
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "BTN_Actions_NTU_Disabled");

				// Check Copy, Submit to Broker Ops, DMs Documents and Notes Buttons are enabled
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "BTN_Actions_Copy");
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "BTN_Actions_SubmitTo_BrokerOps");
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "BTN_Actions_Dms_Documents");
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "BTN_Actions_Notes");

				// --------Verify Stage Checked - CREATED and FIRM ORDER
				CommonLib.WaitForElementToBeVisible(driver, strConfig,
						Constants.riskOverview_Created_StageIcon_Checked_XPATH);
				CommonLib.WaitForElementToBeVisible(driver, strConfig,
						Constants.riskOverview_FirmOrder_StageIcon_Checked_XPATH);

				// --------Verify Stage Disabled - NBI, QUOTE,
				CommonLib.WaitForElementToBeVisible(driver, strConfig,
						Constants.riskOverview_NBI_StageIcon_Disabled_XPATH);
				CommonLib.WaitForElementToBeVisible(driver, strConfig,
						Constants.riskOverview_Quote_StageIcon_Disabled_XPATH);

				// --------Verify Stage Crossed - SUBMIT TO BROKER OPS
				CommonLib.WaitForElementToBeVisible(driver, strConfig,
						Constants.riskOverview_SubmittedTo_BrokerOps_StageIcon_UnChecked_XPATH);

				// Verify Valid Created Date Populated in the format "dd MMM yyyy"
				WebElement CreatedDateEle = driver
						.findElement(By.xpath(Constants.riskOverview_Created_Stage_Date_Populated_XPATH));
				String createdDate = CreatedDateEle.getText();
				Boolean isCreatedDateFormatValid = RTData.isValidStatusDateAndFormat(createdDate);

				if (isCreatedDateFormatValid) {
					CommonLib.getLogger(strConfig)
							.info("PASS: Created Date populated in the format 'dd MMM yyyy' after Risk Firm Order.");
					TestReporter.Pass(driver, strConfig, "Created Date",
							"Populated in the format 'dd MMM yyyy' after Risk Firm Order.");
				}

				else {
					CommonLib.getLogger(strConfig)
							.info("FAIL: Created Date populated in the format 'dd MMM yyyy' after Risk Firm Order.");
					TestReporter.Fail(driver, strConfig, "Created Date",
							"Populated in the format 'dd MMM yyyy' after Risk Firm Order.");

				}

				// ---------Verify Current Date Populated against Firm Order Date Field
				// ------------//
				WebElement FirmOrderDateEle = driver
						.findElement(By.xpath(Constants.riskOverview_FirmOrder_Stage_Date_Populated_XPATH));
				String FirmOrderDate = FirmOrderDateEle.getText();

				if (FirmOrderDate.trim().contentEquals(RTData.getCurrentStageDate())) {
					CommonLib.getLogger(strConfig)
							.info("PASS: Firm Order Date(as Current Date) visible after Risk Firm Order ");
					TestReporter.Pass(driver, strConfig, "Firm Order Date(as Current Date): ",
							"Visible after Risk Firm Order ");
				}

				else {
					CommonLib.getLogger(strConfig)
							.info("FAIL: Firm Order Date(as Current Date)  not visible after Risk Firm Order");
					TestReporter.Fail(driver, strConfig, "Firm Order Date(as Current Date): ",
							"Not visible after Risk Firm Order");

				}

				// --- Verify Risk Overview Fields Disabled (except Cancel Button)
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "TXT_RiskOverview_UMR_Disabled");
				WebElement UMRTxtEle = repository.GetObject(driver, strConfig, "TXT_RiskOverview_UMR");
				String strUMRvalue = UMRTxtEle.getAttribute("value");

				if (strUMRvalue != null && !strUMRvalue.isEmpty()) {
					CommonLib.getLogger(strConfig).info("PASS: UMR is populated in the UMR Field on Risk Overview tab");
					TestReporter.Pass(driver, strConfig, "UMR Value on Risk Overview tab",
							"Populated after Risk Firm Order");
				} else {
					CommonLib.getLogger(strConfig)
							.info("FAIL: UMR is not populated in the UMR Field on Risk Overview tab");
					TestReporter.Fail(driver, strConfig, "UMR Value on Risk Overview tab",
							"Not Populated after Risk Firm Order");
				}

				CommonLib.WaitForElementToBeVisible(driver, strConfig,
						"CBO_RiskOverview_NewOrRenewal_Classification_Disabled");
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "CBO_RiskOverview_Division_Disabled");
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "TXT_RiskOverview_AgentOrClient_Disabled");
				CommonLib.WaitForElementToBeVisible(driver, strConfig,
						"CHK_RiskOverview_DirectInsured_Placement_ONOFF_Switch_Disabled");
				CommonLib.WaitForElementToBeVisible(driver, strConfig,
						"CHK_RiskOverview_Reinsurance_ONOFF_Switch_Disabled");
				CommonLib.WaitForElementToBeVisible(driver, strConfig,
						"TXT_RiskOverview_Insured_Or_DirectInsured_Or_OriginalInsured_Disabled");
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "TXT_RiskOverview_Assured_Disabled");
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "CBO_RiskOverview_AccountExecutive_Disabled");
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "CBO_RiskOverview_PlacingBroker_Disabled");
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "BTN_Save_Disabled");
				//To uncomment in Phase 2
				//CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Cancel");

				// --- Verify Risk Details Fields Disabled (except Cancel Button)
				// RISK DETAILS TAB
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_MenuItem_RiskDetails");
				CommonLib.smallDelay();
				// Click on the Risk Details Menu Item
				WebElement menuItemRiskDetailsEle = repository.GetObject(driver, strConfig, "LN_MenuItem_RiskDetails");
				common.JSClick(driver, strConfig, menuItemRiskDetailsEle);
				// Project.ExecuteRow(driver, strConfig, "LN_MenuItem_RiskDetails", "Click");
				CommonLib.waitForLoad(driver, strConfig);
				
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "TXT_RiskDetails_PolicyType_Disabled");
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "CBO_RiskDetails_PremiumCurrency_Code_Disabled");
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "TXT_RiskDetails_Limit_Disabled");
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "TXT_RiskDetails_Premium_Disabled");
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "TXT_RiskDetails_Deductible_Disabled");
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "TXT_RiskDetails_ClientCommission_Disabled");
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "TXT_RiskDetails_Information_Disabled");
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "TXT_RiskDetails_Terms_Disabled");
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "BTN_Save_Disabled");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Cancel");

			} catch (NoSuchElementException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (StaleElementReferenceException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (JavascriptException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (TimeoutException e) {
				TestReporter.Fail(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (NullPointerException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (IllegalArgumentException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			}

		}
		
		
		public static void verify_FirmOrder_MandatoryFields(RemoteWebDriver driver, String strConfig) {

			try {
				CommonLib.waitForLoad(driver, strConfig);
				TestReporter.Info(driver, strConfig, "Executing : ", "verify_FirmOrder_MandatoryFields");
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "BTN_Actions_FirmOrder");
				WebElement FirmOrderBtnEle = repository.GetObject(driver, strConfig, "BTN_Actions_FirmOrder");

				if (FirmOrderBtnEle.isDisplayed()) {
					CommonLib.getLogger(strConfig).info("INFO: Firm Order button is visible.");
					TestReporter.Info(driver, strConfig, "Firm Order button : ", "Is Visible");
					CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Actions_FirmOrder");
					if (FirmOrderBtnEle.isEnabled()) {
						CommonLib.getLogger(strConfig).info(
								"INFO: Firm Order button is visible and enabled. Proceeding with verify_FirmOrder_MandatoryFields");
						TestReporter.Info(driver, strConfig, "Firm Order button : ",
								"Visible and Enabled. Proceeding with verify_FirmOrder_MandatoryFields");

						Project.ExecuteRow(driver, strConfig, "BTN_Actions_FirmOrder", "Click");

						CommonLib.WaitForElementToBeVisible(driver, strConfig, Constants.FirmOrder_PopUp_XPATH);

						CommonLib.WaitForElementToBeVisible(driver, strConfig, "TXT_FirmOrder_InceptionDate");
						CommonLib.WaitForElementToBeVisible(driver, strConfig, "TXT_FirmOrder_ExpiryDate");
						CommonLib.WaitForElementToBeVisible(driver, strConfig, "TXT_FirmOrder_SDD");
						CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_FirmOrder_PPW");
						CommonLib.WaitForElementToBeVisible(driver, strConfig, "LBL_FirmOrder_DMS_SuccessMessage");
						CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_FirmOrder_Confirm");
						CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_FirmOrder_Cancel");

						Project.ExecuteRow(driver, strConfig, "BTN_FirmOrder_Confirm", "Click");
						CommonLib.waitForLoad(driver, strConfig);
						
						CommonLib.WaitForElementToBeVisible(driver, strConfig, "LBL_FirmOrder_SDD_RequiredValidation_Msg");
						CommonLib.waitForLoad(driver, strConfig);
						Project.ExecuteRow(driver, strConfig, "BTN_FirmOrder_Cancel", "Click");

					}

					else {
						CommonLib.getLogger(strConfig).info(
								"FAIL: Firm Order button disabled. Hence cannot proceed with taking the Risk to Firm Order");
						TestReporter.Fail(driver, strConfig, "Firm Order button",
								"Is disabled and hence cannot proceed with taking the Risk to Firm Order");
					}
				} else {
					CommonLib.getLogger(strConfig).info(
							"FAIL: Firm Order button not visible. Hence cannot proceed with taking the Risk to Firm Order");
					TestReporter.Fail(driver, strConfig, "Firm Order button",
							"Is not visible and hence cannot proceed with taking the Risk to Firm Order");
				}

			} catch (NoSuchElementException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (StaleElementReferenceException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (JavascriptException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (TimeoutException e) {
				TestReporter.Fail(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (NullPointerException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (IllegalArgumentException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			}

		}
		
		public static void submitToBrokerOps(RemoteWebDriver driver, String strConfig, String strTestingType,
				ArrayList<String> SubmitToBrokerOpsData) {
			try {
				CommonLib.waitForLoad(driver, strConfig);
				CommonLib.getLogger(strConfig).info(" Inside submitToBrokerOps");
				if (SubmitToBrokerOpsData.get(0) != null && !(SubmitToBrokerOpsData.get(0).trim().isEmpty())) {
					
					if (SubmitToBrokerOpsData.get(1) != null && !(SubmitToBrokerOpsData.get(1).trim().isEmpty())) {
					CommonLib.WaitForElementToBeVisible(driver, strConfig, "BTN_Actions_SubmitTo_BrokerOps");
					WebElement submitToBrokerOpsBtnEle = repository.GetObject(driver, strConfig,
							"BTN_Actions_SubmitTo_BrokerOps");

					if (submitToBrokerOpsBtnEle.isDisplayed()) {
						CommonLib.getLogger(strConfig).info("INFO: Submit To Broker Ops button is visible.");
						TestReporter.Info(driver, strConfig, "Submit To Broker Ops button : ", "Is Visible");
						CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Actions_SubmitTo_BrokerOps");
						if (submitToBrokerOpsBtnEle.isEnabled()) {
							
							
							CommonLib.getLogger(strConfig).info(
									"INFO: Submit To Broker Ops button is visible and enabled. Proceeding with Submitting the Risk to Broker Ops");
							TestReporter.Info(driver, strConfig, "Submit To Broker Ops button : ",
									"Visible and Enabled. Proceeding with Submitting the Risk to Broker Ops");

							Project.ExecuteRow(driver, strConfig, "BTN_Actions_SubmitTo_BrokerOps", "Click");

							CommonLib.WaitForElementToBeVisible(driver, strConfig,
									Constants.SubmitToBrokerOps_PopUp_XPATH);

							WebElement PopUp_Title = driver
									.findElement(By.xpath(Constants.SubmitToBrokerOps_PopUp_Title_XPATH));
							if (PopUp_Title.getText().trim().contentEquals(Constants.SubmitToBrokerOps_PopUp_Title)) {
								CommonLib.getLogger(strConfig)
										.info("INFO: Submit To Broker Ops Title = 'CPT Submission'");
								TestReporter.Info(driver, strConfig, "Submit To Broker Ops Title : ",
										"'CPT Submission'");

							} else {
								CommonLib.getLogger(strConfig)
										.info("INFO: Submit To Broker Ops Title not equal to 'CPT Submission'");
								TestReporter.Info(driver, strConfig, "Submit To Broker Ops Title : ",
										"Not Equal to 'CPT Submission'");
							}
							CommonLib.WaitForElementToBeVisible(driver, strConfig, "TXT_SubmitToBrokerOps_SDD");
							CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_SubmitToBrokerOps_Cancel");
							
							String[] settlementDueDate = SubmitToBrokerOpsData.get(1).split("/");
							WebElement settlementDueDateEle = repository.GetObject(driver, strConfig,
									"TXT_SubmitToBrokerOps_SDD");
							settlementDueDateEle.sendKeys(settlementDueDate[1]);
							settlementDueDateEle.sendKeys(settlementDueDate[0]);
							settlementDueDateEle.sendKeys(settlementDueDate[2]);
							settlementDueDateEle.sendKeys(Keys.TAB);
							
							CommonLib.Delay();

							
							CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_SubmitToBrokerOps_Confirm");

							Project.ExecuteRow(driver, strConfig, "BTN_SubmitToBrokerOps_Confirm", "Click");

							CommonLib.WaitForElementToBeVisible(driver, strConfig, Constants.NotifMsg_XPATH);

							String strNotifMsg = driver.findElement(By.xpath(Constants.NotifMsg_XPATH)).getText();

							// Submit to Broker Ops Success Message Shown
							if (strNotifMsg.trim().contentEquals(Constants.SubmitToBrokerOpsSuccessMsg)) {
								CommonLib.getLogger(strConfig).info("PASS: Submit To Broker Ops Success message shown");
								TestReporter.Pass(driver, strConfig, "Submit To Broker Ops ", "Success message shown");

								BusinessRules.verifyRisk_After_SubmitToBrokerOps(driver, strConfig, strTestingType,
										SubmitToBrokerOpsData);
							}

							// Submit To Broker Ops Error Message Shown
							else {
								CommonLib.getLogger(strConfig)
										.info("FAIL: Submit To Broker Ops Success message not shown");
								if (strNotifMsg.trim().contentEquals(Constants.QuoteAlreadyCreatedErrorMsg)) {
									CommonLib.getLogger(strConfig).info(
											"FAIL: Submit To Broker Ops : 'Quote already created' error message shown");
									TestReporter.Fail(driver, strConfig, "Submit To Broker Ops Error Message",
											" 'Quote already created' error message shown");
								}

								else if (strNotifMsg.trim().contentEquals(Constants.ConnectionTimeoutErrorMsg)) {
									CommonLib.getLogger(strConfig).info(
											"FAIL: Submit To Broker Ops: 'Connection Timeout' error message shown");
									TestReporter.Fail(driver, strConfig, "Submit To Broker Ops Error Message",
											" 'Connection Timeout' error message shown");
								}

								else if (strNotifMsg.trim().contentEquals(Constants.InternalServerErrorMsg)) {
									CommonLib.getLogger(strConfig).info(
											"FAIL: Submit To Broker Ops: '500 Internal Server Error' message shown");
									TestReporter.Fail(driver, strConfig, "Submit To Broker Ops Error Message",
											" '500 Internal Server Error' message shown");
								}

							}

						}

						else {
							CommonLib.getLogger(strConfig).info(
									"FAIL: Submit To Broker Ops button disabled. Hence cannot proceed with Submit To Broker Ops");
							TestReporter.Fail(driver, strConfig, "Submit To Broker Ops button",
									"Is disabled and hence cannot proceed with Submit To Broker Ops");
						}
					} else {
						CommonLib.getLogger(strConfig).info(
								"FAIL: Submit To Broker Ops button not visible. Hence cannot proceed with Submit To Broker Ops");
						TestReporter.Fail(driver, strConfig, "Submit To Broker Ops button",
								"Is not visible and hence cannot proceed with Submit To Broker Ops");
					}
					
					} else {

						CommonLib.getLogger(strConfig)
								.info("INFO: No Settlement Due Date supplied. Cannot proceed with Risk Submit to Broker Ops");
						TestReporter.Info(driver, strConfig, "No Settlement Due Date supplied",
								"Cannot proceed with Risk Submit to Broker Ops");
					}
				} else {

					CommonLib.getLogger(strConfig)
							.info("INFO: No RiskTracker Reference available. Cannot proceed with Risk Submit to Broker Ops");
					TestReporter.Info(driver, strConfig, "No RiskTracker Reference available",
							" Cannot proceed with Risk Submit to Broker Ops");
				}
			} catch (NoSuchElementException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (StaleElementReferenceException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (JavascriptException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (TimeoutException e) {
				TestReporter.Fail(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (NullPointerException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (IllegalArgumentException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			}

		}
		
		
		public static void verify_CancelBtn_On_SubmitToBrokerOps_PopUp(RemoteWebDriver driver, String strConfig) {
			try {
				CommonLib.waitForLoad(driver, strConfig);
				
					CommonLib.WaitForElementToBeVisible(driver, strConfig, "BTN_Actions_SubmitTo_BrokerOps");
					WebElement submitToBrokerOpsBtnEle = repository.GetObject(driver, strConfig,
							"BTN_Actions_SubmitTo_BrokerOps");

					if (submitToBrokerOpsBtnEle.isDisplayed()) {
					
						TestReporter.Info(driver, strConfig, "Submit To Broker Ops button : ", "Is Visible");
						CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Actions_SubmitTo_BrokerOps");
						if (submitToBrokerOpsBtnEle.isEnabled()) {
							TestReporter.Info(driver, strConfig, "Submit To Broker Ops button : ",
									"Visible and Enabled. Proceeding with verify_CancelBtn_On_SubmitToBrokerOps_PopUp");

							Project.ExecuteRow(driver, strConfig, "BTN_Actions_SubmitTo_BrokerOps", "Click");

							CommonLib.WaitForElementToBeVisible(driver, strConfig,
									Constants.SubmitToBrokerOps_PopUp_XPATH);

							CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_SubmitToBrokerOps_Cancel");
							CommonLib.WaitForElementToBeVisible(driver, strConfig, "BTN_SubmitToBrokerOps_Confirm");

							Project.ExecuteRow(driver, strConfig, "BTN_SubmitToBrokerOps_Cancel", "Click");
							CommonLib.waitForLoad(driver, strConfig);
							
							Boolean SubmitToBrokerOpsPopUpVisible = common.isElementPresent( driver, strConfig, Constants.SubmitToBrokerOps_PopUp_XPATH);
							
							if(SubmitToBrokerOpsPopUpVisible) {
								TestReporter.Fail(driver, strConfig, "Submit to Broker Ops PopUp",
										"Still visible after clicking on Cancel Button on Submit to Broker Ops PopUp");
							}
							else {
								TestReporter.Pass(driver, strConfig, "Submit to Broker Ops PopUp",
										"Disappeared after clicking on Cancel Button on Submit to Broker Ops PopUp");
							}

						}

						else {
							CommonLib.getLogger(strConfig).info(
									"FAIL: Submit To Broker Ops button disabled. Hence cannot proceed with Submit To Broker Ops");
							TestReporter.Fail(driver, strConfig, "Submit To Broker Ops button",
									"Is disabled and hence cannot proceed with Submit To Broker Ops");
						}
					} else {
						CommonLib.getLogger(strConfig).info(
								"FAIL: Submit To Broker Ops button not visible. Hence cannot proceed with Submit To Broker Ops");
						TestReporter.Fail(driver, strConfig, "Submit To Broker Ops button",
								"Is not visible and hence cannot proceed with Submit To Broker Ops");
					}
					
					
			} catch (NoSuchElementException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (StaleElementReferenceException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (JavascriptException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (TimeoutException e) {
				TestReporter.Fail(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (NullPointerException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (IllegalArgumentException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			}

		}
		
		public static void verifyRisk_After_SubmitToBrokerOps(RemoteWebDriver driver, String strConfig, String strTestingType,
				ArrayList<String> SubmitToBrokerOpsData) {

			try {
				CommonLib.getLogger(strConfig).info("Inside verifyRisk_After_SubmitToBrokerOps");
				CommonLib.waitForLoad(driver, strConfig);
				CommonLib.Delay();
				// RISK OVERVIEW TAB
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_MenuItem_RiskOverview");

				// Click on the Risk Details Menu Item
				WebElement menuItemRiskOverviewEle = repository.GetObject(driver, strConfig,
						"LN_MenuItem_RiskOverview");
				common.JSClick(driver, strConfig, menuItemRiskOverviewEle);
				CommonLib.waitForLoad(driver, strConfig);

				CommonLib.WaitForElementToBeVisible(driver, strConfig, "DDO_RiskOverview_Division");

				// Check Copy, DMs Documents and Notes Buttons are enabled
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Actions_Dms_Documents");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Actions_Copy");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Actions_Notes");

				// --------Verify Stage Checked - CREATED and FIRM ORDER
				CommonLib.WaitForElementToBeVisible(driver, strConfig,
						Constants.riskOverview_Created_StageIcon_Checked_XPATH);
				CommonLib.WaitForElementToBeVisible(driver, strConfig,
						Constants.riskOverview_FirmOrder_StageIcon_Checked_XPATH);
				CommonLib.WaitForElementToBeVisible(driver, strConfig,
						Constants.riskOverview_SubmittedTo_BrokerOps_StageIcon_Checked_XPATH);

				// --------Verify Stage Disabled - NBI, QUOTE,
				CommonLib.WaitForElementToBeVisible(driver, strConfig,
						Constants.riskOverview_NBI_StageIcon_Disabled_XPATH);
				CommonLib.WaitForElementToBeVisible(driver, strConfig,
						Constants.riskOverview_Quote_StageIcon_Disabled_XPATH);

				// Verify Dates -Created Date has valid Format and Firm Order Date = Current
				// Date
				WebElement CreatedDateEle = driver
						.findElement(By.xpath(Constants.riskOverview_Created_Stage_Date_Populated_XPATH));
				String createdDate = CreatedDateEle.getText();
				Boolean isCreatedDateFormatValid = RTData.isValidStatusDateAndFormat(createdDate);

				if (isCreatedDateFormatValid) {
					CommonLib.getLogger(strConfig)
							.info("PASS: Created Date populated in the format 'dd MMM yyyy' after Risk Submit to Broker Ops");
					TestReporter.Pass(driver, strConfig, "Created Date",
							"Populated in the format 'dd MMM yyyy' after Submit to Broker Ops");
				}

				else {
					CommonLib.getLogger(strConfig)
							.info("FAIL: Created Date populated in the format 'dd MMM yyyy' after Risk Submit to Broker Ops");
					TestReporter.Fail(driver, strConfig, "Created Date",
							"Populated in the format 'dd MMM yyyy' after Submit to Broker Ops");

				}

				WebElement FirmOrderDateEle = driver
						.findElement(By.xpath(Constants.riskOverview_FirmOrder_Stage_Date_Populated_XPATH));
				String FirmOrderDate = FirmOrderDateEle.getText();
				Boolean isFirmOrderDateFormatValid = RTData.isValidStatusDateAndFormat(FirmOrderDate);

				if (isFirmOrderDateFormatValid) {
					CommonLib.getLogger(strConfig).info(
							"PASS: Firm Order Date populated in the format 'dd MMM yyyy' after Submit To Broker Ops");
					TestReporter.Pass(driver, strConfig, "Firm Order  Date",
							"Populated in the format 'dd MMM yyyy' after Submit To Broker Ops");
				}

				else {
					CommonLib.getLogger(strConfig).info(
							"FAIL: Firm Order Date populated in the format 'dd MMM yyyy' after Submit To Broker Ops");
					TestReporter.Fail(driver, strConfig, "Firm Order Date",
							"Populated in the format 'dd MMM yyyy' after Submit To Broker Ops");

				}

				// ---------Verify Current Date Populated against Submit to Broker Ops Date
				// Field
				// ------------//
				WebElement SubmittedToBrokerOpsDateEle = driver
						.findElement(By.xpath(Constants.riskOverview_SubmitToAdmin_Stage_Date_Populated_XPATH));
				String SubmittedToBrokerOpsDate = SubmittedToBrokerOpsDateEle.getText();

				if (SubmittedToBrokerOpsDate.trim().contentEquals(RTData.getCurrentStageDate())) {
					CommonLib.getLogger(strConfig).info(
							"PASS: Submitted to Broker Ops Date(as Current Date) visible after Submit to Broker Ops");
					TestReporter.Pass(driver, strConfig, "Submitted to Broker Ops Date(as Current Date): ",
							"Visible after Submitted to Broker Ops");
				}

				else {
					CommonLib.getLogger(strConfig).info(
							"FAIL: Submitted to Broker Ops Date(as Current Date) not visible after Submit to Broker Ops");
					TestReporter.Fail(driver, strConfig, "Submitted to Broker Ops Date(as Current Date): ",
							"Not Visible after Submitted to Broker Ops");

				}

				// --- Verify Risk Overview Fields Disabled (except Cancel Button)

				

				CommonLib.WaitForElementToBeVisible(driver, strConfig, "TXT_RiskOverview_UMR_Disabled");
				WebElement UMRTxtEle = repository.GetObject(driver, strConfig, "TXT_RiskOverview_UMR");
				String strUMRvalue = UMRTxtEle.getAttribute("value");

				if (strUMRvalue != null && !strUMRvalue.isEmpty()) {
					CommonLib.getLogger(strConfig).info("PASS: UMR is populated in the UMR Field on Risk Overview tab");
					TestReporter.Pass(driver, strConfig, "UMR Value on Risk Overview tab",
							"Populated after Risk Submitted to Broker Ops");
				} else {
					CommonLib.getLogger(strConfig)
							.info("FAIL: UMR is not populated in the UMR Field on Risk Overview tab");
					TestReporter.Fail(driver, strConfig, "UMR Value on Risk Overview tab",
							"Not Populated after Risk Submitted to Broker Ops");
				}

				CommonLib.WaitForElementToBeVisible(driver, strConfig,
						"CBO_RiskOverview_NewOrRenewal_Classification_Disabled");
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "CBO_RiskOverview_Division_Disabled");

				CommonLib.WaitForElementToBeVisible(driver, strConfig, "TXT_RiskOverview_AgentOrClient_Disabled");
				
				CommonLib.WaitForElementToBeVisible(driver, strConfig,
						"CHK_RiskOverview_DirectInsured_Placement_ONOFF_Switch_Disabled");
				CommonLib.WaitForElementToBeVisible(driver, strConfig,
						"CHK_RiskOverview_Reinsurance_ONOFF_Switch_Disabled");
				CommonLib.WaitForElementToBeVisible(driver, strConfig,
						"TXT_RiskOverview_Insured_Or_DirectInsured_Or_OriginalInsured_Disabled");
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "TXT_RiskOverview_Assured_Disabled");
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "CBO_RiskOverview_AccountExecutive_Disabled");
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "CBO_RiskOverview_PlacingBroker_Disabled");
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "BTN_Save_Disabled");
				//To uncomment in Phase 2
				//CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Cancel");

				// --- Verify Risk Details Fields Disabled (except Cancel Button)
				// RISK DETAILS TAB
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_MenuItem_RiskDetails");
				CommonLib.smallDelay();
				// Click on the Risk Details Menu Item
				WebElement menuItemRiskDetailsEle = repository.GetObject(driver, strConfig, "LN_MenuItem_RiskDetails");
				common.JSClick(driver, strConfig, menuItemRiskDetailsEle);
				CommonLib.waitForLoad(driver, strConfig);
				
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "TXT_RiskDetails_PolicyType_Disabled");
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "CBO_RiskDetails_PremiumCurrency_Code_Disabled");
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "TXT_RiskDetails_Limit_Disabled");
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "TXT_RiskDetails_Premium_Disabled");
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "TXT_RiskDetails_Deductible_Disabled");
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "TXT_RiskDetails_ClientCommission_Disabled");
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "TXT_RiskDetails_Information_Disabled");
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "TXT_RiskDetails_Terms_Disabled");
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "BTN_Save_Disabled");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Cancel");
			} catch (NoSuchElementException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (StaleElementReferenceException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (JavascriptException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (TimeoutException e) {
				TestReporter.Fail(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (NullPointerException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (IllegalArgumentException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			}
		}
		
		
			
		public static void copyRisk(RemoteWebDriver driver, String strConfig, String strTestingType,
				ArrayList<String> CopyRiskData) {
			try {
				CommonLib.waitForLoad(driver, strConfig);
				CommonLib.getLogger(strConfig).info(" Inside Copy Risk");

				if (CopyRiskData.get(0) != null && !(CopyRiskData.get(0).trim().isEmpty())) {

					CommonLib.WaitForElementToBeVisible(driver, strConfig, "BTN_Actions_Copy");
					WebElement copyRiskBtnEle = repository.GetObject(driver, strConfig, "BTN_Actions_Copy");

					if (copyRiskBtnEle.isDisplayed()) {
						CommonLib.getLogger(strConfig).info("INFO: Copy button is visible.");
						TestReporter.Info(driver, strConfig, "Copy button : ", "Is Visible");
						CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Actions_Copy");
						if (copyRiskBtnEle.isEnabled()) {
							
							CommonLib.getLogger(strConfig)
									.info("INFO: Copy button is visible and enabled. Proceeding with Copying the Risk");
							TestReporter.Info(driver, strConfig, "Copy button : ",
									"Visible and Enabled. Proceeding with Copying the Risk");

							Project.ExecuteRow(driver, strConfig, "BTN_Actions_Copy", "Click");

							CommonLib.WaitForElementToBeVisible(driver, strConfig, Constants.CopyRisk_PopUp_XPATH);

							CommonLib.WaitForElementToBeVisible(driver, strConfig,
									Constants.CopyRisk_PopUp_TitleQuestion_XPATH);

							CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_CopyRisk_No");
							CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_CopyRisk_Yes");

							Project.ExecuteRow(driver, strConfig, "BTN_CopyRisk_Yes", "Click");

							CommonLib.WaitForElementToBeVisible(driver, strConfig, Constants.NotifMsg_XPATH);

							String strNotifMsg = driver.findElement(By.xpath(Constants.NotifMsg_XPATH)).getText();
							String strRiskCopiedSuccessMsg = Constants.CopySuccessMsg + " " + CopyRiskData.get(0);
							System.out.println("strNotifMsg"+strNotifMsg);
							System.out.println("strRiskCopiedSuccessMsg"+strRiskCopiedSuccessMsg);
							// Copy Risk Success Message Shown
							if (strNotifMsg.trim().contentEquals(strRiskCopiedSuccessMsg)) {
								CommonLib.getLogger(strConfig).info(
										"PASS: Copy Risk Success message shown - '" + strRiskCopiedSuccessMsg + "'");
								TestReporter.Pass(driver, strConfig, " Copy Risk ",
										"Success message shown- '" + strRiskCopiedSuccessMsg + "'");
								BusinessRules.verify_Copy_SaveToConfirm_PageFields(driver, strTestingType,strConfig, CopyRiskData);
								BusinessRules.Save_CopySaveToConfirm_Details(driver, strConfig, strTestingType,CopyRiskData);

							}

							// Copy Risk Error Message Shown
							else {
								CommonLib.getLogger(strConfig).info("FAIL: Copy Risk Success message not shown");
								if (strNotifMsg.trim().contentEquals(Constants.QuoteAlreadyCreatedErrorMsg)) {
									CommonLib.getLogger(strConfig)
											.info("FAIL: Copy Risk : 'Quote already created' error message shown");
									TestReporter.Fail(driver, strConfig, "Copy Risk Error Message",
											" 'Quote already created' error message shown");
								}

								else if (strNotifMsg.trim().contentEquals(Constants.ConnectionTimeoutErrorMsg)) {
									CommonLib.getLogger(strConfig)
											.info("FAIL: Copy Risk : 'Connection Timeout' error message shown");
									TestReporter.Fail(driver, strConfig, "Copy Risk Error Message",
											" 'Connection Timeout' error message shown");
								}

								else if (strNotifMsg.trim().contentEquals(Constants.InternalServerErrorMsg)) {
									CommonLib.getLogger(strConfig)
											.info("FAIL: Copy Risk : '500 Internal Server Error' message shown");
									TestReporter.Fail(driver, strConfig, "Copy Risk Error Message",
											" '500 Internal Server Error' message shown");
								}

							}
						}

						else {
							CommonLib.getLogger(strConfig)
									.info("FAIL: Copy button disabled. Hence cannot proceed with Copy Risk");
							TestReporter.Fail(driver, strConfig, "Copy button",
									"Is disabled and hence cannot proceed with Copy Risk");
						}
					} else {
						CommonLib.getLogger(strConfig)
								.info("FAIL: Copy button not visible. Hence cannot proceed with Copy Risk");
						TestReporter.Fail(driver, strConfig, "Copy button",
								"Is not visible and hence cannot proceed with Copy Risk");
					}
				} else {

					CommonLib.getLogger(strConfig).info(
							"INFO: No RiskTracker Reference available. Cannot proceed with Copy Risk");
					TestReporter.Info(driver, strConfig, "No RiskTracker Reference available",
							" Cannot proceed with Copy Risk");
				}
			} catch (NoSuchElementException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (StaleElementReferenceException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (JavascriptException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (TimeoutException e) {
				TestReporter.Fail(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (NullPointerException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (IllegalArgumentException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			}

		}
		
		
		
		public static void verify_CancelBtn_On_CopyRisk_PopUp(RemoteWebDriver driver, String strConfig) {
			try {
				CommonLib.waitForLoad(driver, strConfig);

				CommonLib.WaitForElementToBeVisible(driver, strConfig, "BTN_Actions_Copy");
				WebElement copyRiskBtnEle = repository.GetObject(driver, strConfig, "BTN_Actions_Copy");

					if (copyRiskBtnEle.isDisplayed()) {
						CommonLib.getLogger(strConfig).info("INFO: Copy button is visible.");
						TestReporter.Info(driver, strConfig, "Copy button : ", "Is Visible");
						CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Actions_Copy");
						if (copyRiskBtnEle.isEnabled()) {
							CommonLib.getLogger(strConfig)
									.info("INFO: Copy button is visible and enabled. Proceeding with verify_CancelBtn_On_CopyRisk_PopUp");
							TestReporter.Info(driver, strConfig, "Copy button : ",
									"Visible and Enabled. Proceeding with verify_CancelBtn_On_CopyRisk_PopUp");

							Project.ExecuteRow(driver, strConfig, "BTN_Actions_Copy", "Click");

							CommonLib.WaitForElementToBeVisible(driver, strConfig, Constants.CopyRisk_PopUp_XPATH);

							CommonLib.WaitForElementToBeVisible(driver, strConfig,
									Constants.CopyRisk_PopUp_TitleQuestion_XPATH);

							CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_CopyRisk_No");
							CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_CopyRisk_Yes");

							Project.ExecuteRow(driver, strConfig, "BTN_CopyRisk_No", "Click");
							
							CommonLib.waitForLoad(driver, strConfig);
							
							Boolean CopyRiskPopUpVisible = common.isElementPresent( driver, strConfig, Constants.CopyRisk_PopUp_XPATH);
							
							if(CopyRiskPopUpVisible) {
								TestReporter.Fail(driver, strConfig, "Copy Risk PopUp",
										"Still visible after clicking on Cancel Button on Copy Risk PopUp");
							}
							else {
								TestReporter.Pass(driver, strConfig, "Copy Risk PopUp",
										"Disappeared after clicking on Cancel Button on Copy Risk PopUp");
							}

						}

						else {
							CommonLib.getLogger(strConfig)
									.info("FAIL: Copy button disabled. Hence cannot proceed with Copy Risk");
							TestReporter.Fail(driver, strConfig, "Copy button",
									"Is disabled and hence cannot proceed with Copy Risk");
						}
					} else {
						CommonLib.getLogger(strConfig)
								.info("FAIL: Copy button not visible. Hence cannot proceed with Copy Risk");
						TestReporter.Fail(driver, strConfig, "Copy button",
								"Is not visible and hence cannot proceed with Copy Risk");
					}
				
			} catch (NoSuchElementException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (StaleElementReferenceException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (JavascriptException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (TimeoutException e) {
				TestReporter.Fail(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (NullPointerException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (IllegalArgumentException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			}
		}
		
		
		
		
		
		public static void verify_Copy_SaveToConfirm_PageFields(RemoteWebDriver driver, String strConfig, String  strTestingType,
				ArrayList<String> CopyRiskData) {
			try {
				CommonLib.getLogger(strConfig).info("Inside verify_Copy_SaveToConfirm_PageFields");
				CommonLib.waitForLoad(driver, strConfig);

				WebElement copySaveToConfirmTitle = driver
						.findElement(By.xpath(Constants.Copy_SaveToConfirm_Title_XPATH));

				// Validate Copy Save to Confirm Screen displayed
				if (copySaveToConfirmTitle.isDisplayed()) {
					CommonLib.getLogger(strConfig).info("PASS: Copy-Save to Confirm Screen Shown");
					TestReporter.Pass(driver, strConfig, "Copy-Save to Confirm Screen ",
							"Shown after clicking 'Yes' on Copy PopUp");

				}

				else {
					CommonLib.getLogger(strConfig).info("FAIL: Copy-Save to Confirm Screen not shown");
					TestReporter.Fail(driver, strConfig, "Copy-Save to Confirm Screen ",
							"Not shown after clicking 'Yes' on Copy PopUp");

				}

				// RISK OVERVIEW TAB
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_MenuItem_RiskOverview");

				// Click on the Risk Details Menu Item
				WebElement menuItemRiskOverviewEle = repository.GetObject(driver, strConfig,
						"LN_MenuItem_RiskOverview");
				common.JSClick(driver, strConfig, menuItemRiskOverviewEle);
				CommonLib.waitForLoad(driver, strConfig);

				CommonLib.WaitForElementToBeClickable(driver, strConfig, "DDO_RiskOverview_Division");

				String riskTrackerRefNum = RTData.getRiskTracker_RefNo(driver, strConfig,
						Constants.riskTrackerRefNo_XPATH);

				// --------Verify Stage Checked - CREATED
				// --------Verify Stage Unchecked - NBI, QUOTE, FIRM ORDER, SUBMIT TO BROKER OPS
				CommonLib.WaitForElementToBeVisible(driver, strConfig,
						Constants.riskOverview_Created_StageIcon_Checked_XPATH);
				CommonLib.WaitForElementToBeVisible(driver, strConfig,
						Constants.riskOverview_FirmOrder_StageIcon_UnChecked_XPATH);
				CommonLib.WaitForElementToBeVisible(driver, strConfig,
						Constants.riskOverview_SubmittedTo_BrokerOps_StageIcon_UnChecked_XPATH);
				CommonLib.WaitForElementToBeVisible(driver, strConfig,
						Constants.riskOverview_NBI_StageIcon_UnChecked_XPATH);
				CommonLib.WaitForElementToBeVisible(driver, strConfig,
						Constants.riskOverview_Quote_StageIcon_UnChecked_XPATH);

				// ------------------------------------------------------
				CommonLib.WaitForElementToBeVisible(driver, strConfig,
						Constants.riskOverview_Created_Stage_Date_Populated_XPATH);
				WebElement CreatedDateEle = driver
						.findElement(By.xpath(Constants.riskOverview_Created_Stage_Date_Populated_XPATH));
				String createdDate = CreatedDateEle.getText();
				Boolean isCreatedDateFormatValid = RTData.isValidStatusDateAndFormat(createdDate);

				if (isCreatedDateFormatValid) {
					CommonLib.getLogger(strConfig)
							.info("PASS: Created Date populated in the format 'dd MMM yyyy' after Risk Firm Order.");
					TestReporter.Pass(driver, strConfig, "Created Date",
							"Populated in the format 'dd MMM yyyy' after Risk Firm Order.");
				}

				else {
					CommonLib.getLogger(strConfig)
							.info("FAIL: Created Date populated in the format 'dd MMM yyyy' after Risk Firm Order.");
					TestReporter.Fail(driver, strConfig, "Created Date",
							"Populated in the format 'dd MMM yyyy' after Risk Firm Order.");

				}

				// --- Verify Risk Overview Fields Enabled (except UMR and Agent/Client)
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "TXT_RiskOverview_UMR_Disabled");

				CommonLib.WaitForElementToBeClickable(driver, strConfig,
						"DDO_RiskOverview_NewOrRenewal_Classification");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "DDO_RiskOverview_Division");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskOverview_AgentOrClient");

				CommonLib.WaitForElementToBeClickable(driver, strConfig,
						"CHK_RiskOverview_DirectInsured_Placement_ONOFF_Switch");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "CHK_RiskOverview_Reinsurance_ONOFF_Switch");

				CommonLib.WaitForElementToBeClickable(driver, strConfig,
						"TXT_RiskOverview_Insured_Or_DirectInsured_Or_OriginalInsured");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskOverview_Assured");

				CommonLib.WaitForElementToBeClickable(driver, strConfig, "DDO_RiskOverview_AccountExecutive");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "DDO_RiskOverview_PlacingBroker");

				CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Save");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Cancel");

				// --- Verify Risk Details Fields Enabled (Except NBI Button which is disabled)
				// RISK DETAILS TAB
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_MenuItem_RiskDetails");
				CommonLib.smallDelay();
				// Click on the Risk Details Menu Item
				WebElement menuItemRiskDetailsEle = repository.GetObject(driver, strConfig, "LN_MenuItem_RiskDetails");
				common.JSClick(driver, strConfig, menuItemRiskDetailsEle);
				CommonLib.waitForLoad(driver, strConfig);

				// -------Feature Switch Field ---------//
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskDetails_PolicyType");

				// -------Feature Switch Field ---------//
				// -------Need to add Major and Minor Class of Business --------//

				CommonLib.WaitForElementToBeClickable(driver, strConfig, "DDO_RiskDetails_PremiumCurrency_Code");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskDetails_Limit");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskDetails_Premium");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskDetails_Deductible");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskDetails_ClientCommission");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskDetails_Information");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskDetails_Terms");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskDetails_Information");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskDetails_Terms");

				CommonLib.WaitForElementToBeVisible(driver, strConfig, "BTN_NBI_Disabled");

			} catch (NoSuchElementException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (StaleElementReferenceException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (JavascriptException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (TimeoutException e) {
				TestReporter.Fail(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (NullPointerException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (IllegalArgumentException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			}

		}
		
		public static void Save_CopySaveToConfirm_Details(RemoteWebDriver driver, String strConfig, String  strTestingType,
				ArrayList<String> CopyRiskData) {
			try {
				CommonLib.getLogger(strConfig).info("Inside Save_CopySaveToConfirm_Details");
				CommonLib.waitForLoad(driver, strConfig);

				CommonLib.WaitForElementToBeVisible(driver, strConfig, Constants.Copy_SaveToConfirm_Title_XPATH);
				// RISK OVERVIEW TAB
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_MenuItem_RiskOverview");

				// Click on the Risk Details Menu Item
				WebElement menuItemRiskOverviewEle = repository.GetObject(driver, strConfig,
						"LN_MenuItem_RiskOverview");
				common.JSClick(driver, strConfig, menuItemRiskOverviewEle);
				CommonLib.waitForLoad(driver, strConfig);
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "DDO_RiskOverview_Division");

				CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Save");
				Project.ExecuteRow(driver, strConfig, "BTN_Save", "Click");

				CommonLib.WaitForElementToBeVisible(driver, strConfig, Constants.NotifMsg_XPATH);

				String strNotifMsg = driver.findElement(By.xpath(Constants.NotifMsg_XPATH)).getText();
				String strRiskCopiedSuccessMsg = Constants.CopySaveToConfirm_SuccessMsg;
				// Copy Save to Confirm Success Message Shown
				if (strNotifMsg.trim().contains(strRiskCopiedSuccessMsg)) {
					CommonLib.getLogger(strConfig).info(
							"PASS: Copy Save to Confirm Success message shown - '" + strRiskCopiedSuccessMsg + "'");
					TestReporter.Pass(driver, strConfig, " Copy Save to Confirm ",
							"Success message shown- '" + strRiskCopiedSuccessMsg + "'");
					BusinessRules.verifyRisk_After_CopySaveToConfirm(driver, strTestingType,strConfig, CopyRiskData);
				}

				// Copy Save to Confirm Error Message Shown
				else {
					CommonLib.getLogger(strConfig).info("FAIL: Copy Save to Confirm  Success message not shown");
					if (strNotifMsg.trim().contentEquals(Constants.QuoteAlreadyCreatedErrorMsg)) {
						CommonLib.getLogger(strConfig)
								.info("FAIL: Copy Save to Confirm  : 'Quote already created' error message shown");
						TestReporter.Fail(driver, strConfig, "Copy Save to Confirm  Error Message",
								" 'Quote already created' error message shown");
					}

					else if (strNotifMsg.trim().contentEquals(Constants.ConnectionTimeoutErrorMsg)) {
						CommonLib.getLogger(strConfig)
								.info("FAIL: Copy Save to Confirm  : 'Connection Timeout' error message shown");
						TestReporter.Fail(driver, strConfig, "Copy Save to Confirm  Error Message",
								" 'Connection Timeout' error message shown");
					}

					else if (strNotifMsg.trim().contentEquals(Constants.InternalServerErrorMsg)) {
						CommonLib.getLogger(strConfig)
								.info("FAIL: Copy Save to Confirm  : '500 Internal Server Error' message shown");
						TestReporter.Fail(driver, strConfig, "Copy Save to Confirm  Error Message",
								" '500 Internal Server Error' message shown");
					}
				}

			} catch (NoSuchElementException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (StaleElementReferenceException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (JavascriptException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (TimeoutException e) {
				TestReporter.Fail(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (NullPointerException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (IllegalArgumentException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			}
		}
		
		
		public static void verifyRisk_After_CopySaveToConfirm(RemoteWebDriver driver, String strConfig, String  strTestingType,
				ArrayList<String> CopyRiskData) {
			try {
				CommonLib.getLogger(strConfig).info("Inside verifyRisk_After_CopySaveToConfirm");
				CommonLib.waitForLoad(driver, strConfig);

				// RISK OVERVIEW TAB
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_MenuItem_RiskOverview");

				// Click on the Risk Details Menu Item
				WebElement menuItemRiskOverviewCopiedRiskEle = repository.GetObject(driver, strConfig,
						"LN_MenuItem_RiskOverview");
				common.JSClick(driver, strConfig, menuItemRiskOverviewCopiedRiskEle);
				CommonLib.waitForLoad(driver, strConfig);

				String riskTrackerRefNum = RTData.getRiskTracker_RefNo(driver, strConfig,
						Constants.riskTrackerRefNo_XPATH);

				// --------Verify Stage Checked - CREATED
				// --------Verify Stage Unchecked - NBI, QUOTE, FIRM ORDER, SUBMIT TO BROKER OPS
				CommonLib.WaitForElementToBeVisible(driver, strConfig,
						Constants.riskOverview_Created_StageIcon_Checked_XPATH);
				CommonLib.WaitForElementToBeVisible(driver, strConfig,
						Constants.riskOverview_FirmOrder_StageIcon_UnChecked_XPATH);
				CommonLib.WaitForElementToBeVisible(driver, strConfig,
						Constants.riskOverview_SubmittedTo_BrokerOps_StageIcon_UnChecked_XPATH);
				CommonLib.WaitForElementToBeVisible(driver, strConfig,
						Constants.riskOverview_NBI_StageIcon_UnChecked_XPATH);
				CommonLib.WaitForElementToBeVisible(driver, strConfig,
						Constants.riskOverview_Quote_StageIcon_UnChecked_XPATH);

				// --------- Verify NTU, DMS DOCUMENTS AND NOTES BUTTON Visible and Enabled
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Actions_NTU");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Actions_Dms_Documents");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Actions_Notes");

				// --------- Verify FIRM ORDER AND SUBMIT TO BROKER OPS Visible and disabled
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "BTN_Actions_FirmOrder_Disabled");
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "BTN_Actions_SubmitTo_BrokerOps_Disabled");

				// ------------------------------------------------------
				CommonLib.WaitForElementToBeVisible(driver, strConfig,
						Constants.riskOverview_Created_Stage_Date_Populated_XPATH);
				WebElement CreatedDateEle = driver
						.findElement(By.xpath(Constants.riskOverview_Created_Stage_Date_Populated_XPATH));
				String createdDate = CreatedDateEle.getText();

				if (createdDate.contentEquals(RTData.getCurrentStageDate())) {
					CommonLib.getLogger(strConfig).info("PASS: Created Date(as current date) visible upon Copy Risk");
					TestReporter.Pass(driver, strConfig, "Created Date(as current date): ",
							"Date visible upon Copy Risk");
				}

				else {
					CommonLib.getLogger(strConfig)
							.info("FAIL: Created Date(as current date) not visible upon Copy Risk");
					TestReporter.Fail(driver, strConfig, "Created Date(as current date): ",
							"Date not visible upon Copy Risk");

				}

				// --- Verify Risk Overview Fields Enabled (except UMR and Agent/Client)
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "TXT_RiskOverview_UMR_Disabled");

				CommonLib.WaitForElementToBeClickable(driver, strConfig,
						"DDO_RiskOverview_NewOrRenewal_Classification");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "DDO_RiskOverview_Division");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskOverview_AgentOrClient");

				CommonLib.WaitForElementToBeClickable(driver, strConfig,
						"CHK_RiskOverview_DirectInsured_Placement_ONOFF_Switch");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "CHK_RiskOverview_Reinsurance_ONOFF_Switch");

				CommonLib.WaitForElementToBeClickable(driver, strConfig,
						"TXT_RiskOverview_Insured_Or_DirectInsured_Or_OriginalInsured");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskOverview_Assured");

				CommonLib.WaitForElementToBeClickable(driver, strConfig, "DDO_RiskOverview_AccountExecutive");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "DDO_RiskOverview_PlacingBroker");

				CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Save");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Cancel");

				// --- Verify Risk Details Fields Enabled
				// RISK DETAILS TAB
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_MenuItem_RiskDetails");
				CommonLib.smallDelay();
				// Click on the Risk Details Menu Item
				WebElement menuItemRiskDetailsAfterCopyEle = repository.GetObject(driver, strConfig,
						"LN_MenuItem_RiskDetails");
				common.JSClick(driver, strConfig, menuItemRiskDetailsAfterCopyEle);
				CommonLib.waitForLoad(driver, strConfig);

				// -------Feature Switch Field ---------//
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskDetails_PolicyType");

				// -------Feature Switch Field ---------//
				// -------Need to add Major and Minor Class of Business --------//

				CommonLib.WaitForElementToBeClickable(driver, strConfig, "DDO_RiskDetails_PremiumCurrency_Code");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskDetails_Limit");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskDetails_Premium");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskDetails_Deductible");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskDetails_ClientCommission");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskDetails_Information");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskDetails_Terms");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskDetails_Information");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskDetails_Terms");

				CommonLib.WaitForElementToBeVisible(driver, strConfig, "BTN_NBI_Disabled");
			} catch (NoSuchElementException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (StaleElementReferenceException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (JavascriptException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (TimeoutException e) {
				TestReporter.Fail(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (NullPointerException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (IllegalArgumentException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			}

		}
		
		public static Boolean searchRisk_ByView(RemoteWebDriver driver, String strConfig, String strViewOption, String strAccountExec, 
				String strRiskRef, Boolean openRiskCard) {
			Boolean riskFound = false;
			try {
				CommonLib.getLogger(strConfig).info("Inside searchRisk_ByView");
				TestReporter.Info(driver, strConfig, "Inside Function",
						"searchRisk_ByView()");
				if (strRiskRef != null && !(strRiskRef.trim().isEmpty())) {
					if (strViewOption != null && !(strViewOption.trim().isEmpty())) {
						// Select the Risk View from Dropdown on Risk Dashboard
						CommonLib.WaitForElementToBeClickable(driver, strConfig, "DDO_RiskDashboard_RiskView_Selector");
						Project.ExecuteRow(driver, strConfig, "DDO_RiskDashboard_RiskView_Selector", "Click");
						
						CommonLib.waitForLoad(driver, strConfig);

						CommonLib.WaitForElementToBeClickable(driver, strConfig, "DTI_RiskDashboard_RiskView_Selector");
						Project.ExecuteRow(driver, strConfig, "DTI_RiskDashboard_RiskView_Selector", strViewOption);
						CommonLib.waitForLoad(driver, strConfig);

						if (strViewOption.trim().contentEquals(Constants.Dashboard_RiskView.get(0))
								|| strViewOption.trim().contentEquals(Constants.Dashboard_RiskView.get(1))) {
							CommonLib.WaitForElementToBeClickable(driver, strConfig,
									"DDO_RiskDashboard_RiskView_AccountExecutive");
							Project.ExecuteRow(driver, strConfig, "DDO_RiskDashboard_RiskView_AccountExecutive",
									"Click");
							CommonLib.waitForLoad(driver, strConfig);

							CommonLib.WaitForElementToBeClickable(driver, strConfig,
									"DTI_RiskDashboard_RiskView_AccountExecutive");
							Project.ExecuteRow(driver, strConfig, "DTI_RiskDashboard_RiskView_AccountExecutive",
									strAccountExec);
							CommonLib.waitForLoad(driver, strConfig);
						}

						CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskDashboard_SearchRisk");
						CommonLib.Delay();
						Project.ExecuteRow(driver, strConfig, "TXT_RiskDashboard_SearchRisk", strRiskRef);
						CommonLib.waitForLoad(driver, strConfig);
						CommonLib.Delay();

						String[] locatorDetails = CommonLib.getLocatorDetails_From_ObjectRepository(driver, strConfig,
								"LN_RiskDashboard_RiskCard_RiskRef");
						CommonLib.WaitForElementToBeClickable(driver, strConfig,
								String.format(locatorDetails[1], strRiskRef));
						riskFound = common.isElementVisible(driver, strConfig,
								String.format(locatorDetails[1], strRiskRef));

						if (riskFound) {

							CommonLib.getLogger(strConfig).info("PASS: Risk present in Risk Tracker Dashboard");
							TestReporter.Pass(driver, strConfig, "Risk on Dashboard",
									"Risk present in Risk Tracker Dashboard");

							if (openRiskCard.equals(true)) {
								WebElement riskCard_RiskRefEle = driver
										.findElement(By.xpath(String.format(locatorDetails[1], strRiskRef)));
								TestReporter.Info(driver, strConfig, "Open Risk Card: ", "TRUE");
								
								common.JSClick(driver, strConfig, riskCard_RiskRefEle);
								CommonLib.waitForLoad(driver, strConfig);
								CommonLib.WaitForElementToBeClickable(driver, strConfig, "DDO_RiskOverview_Division");
								return riskFound;
							}
						} else {
							CommonLib.getLogger(strConfig).info("INFO: Risk not present in Risk Tracker Dashboard");
							TestReporter.Info(driver, strConfig, "Risk on Dashboard",
									"Risk Not present in Risk Tracker Dashboard");

						}
					} else {
						CommonLib.getLogger(strConfig)
								.info("INFO: Risk View Option Not provided.Proceeding with Default Risk Search");
						TestReporter.Info(driver, strConfig, "Risk View Option Not provided",
								"Proceeding with Default Risk Search");
						BusinessRules.searchRisk(driver, strConfig, strRiskRef, openRiskCard);

					}
				}

				else {
					CommonLib.getLogger(strConfig)
							.info("FAIL: Risk not supplied/unavailable. Hence cannot proceed with Risk Search");
					TestReporter.Fail(driver, strConfig, "Risk not supplied/unavailable",
							"Cannot proceed with Risk Search");
				}
			} catch (NoSuchElementException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (StaleElementReferenceException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (JavascriptException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (TimeoutException e) {
				TestReporter.Fail(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (NullPointerException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (IllegalArgumentException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			}
			return riskFound;

		}
		
		
		public static void renewRisk(RemoteWebDriver driver, String strConfig, ArrayList<String> RenewalData) {
			try {
				CommonLib.getLogger(strConfig).info("INFO: Inside renewRisk");
				if (RenewalData.get(2) != null && !(RenewalData.get(2).trim().isEmpty())) {
					CommonLib.waitForLoad(driver, strConfig);
					
					CommonLib.WaitForElementToBeVisible(driver, strConfig, "BTN_Actions_Renew");
					WebElement RenewBtnEle = repository.GetObject(driver, strConfig, "BTN_Actions_Renew");

					if (RenewBtnEle.isDisplayed()) {
						CommonLib.getLogger(strConfig).info("INFO: Renew button is visible.");
						TestReporter.Info(driver, strConfig, "Renew button : ", "Is Visible");
					
						CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Actions_Renew");

						if (RenewBtnEle.isEnabled()) {
							CommonLib.getLogger(strConfig).info(
									"INFO: Renew button is visible and enabled. Proceeding with Renewing the  Risk");

							Project.ExecuteRow(driver, strConfig, "BTN_Actions_Renew", "Click");

							// Verify Success Message is shown after Risk is Reinstated
							CommonLib.WaitForElementToBeVisible(driver, strConfig, Constants.NotifMsg_XPATH);

							String strNotifMsg = driver.findElement(By.xpath(Constants.NotifMsg_XPATH)).getText();

							if (strNotifMsg.trim().contentEquals(Constants.RenewRisk_SuccessMsg)) {
								CommonLib.getLogger(strConfig).info("PASS: Renew Risk Success message shown");
								TestReporter.Pass(driver, strConfig, "Renew Risk",
										"Success message shown");
								
								BusinessRules.verifyRisk_AfterRenewal(driver, strConfig, RenewalData);
							}

							else {
								CommonLib.getLogger(strConfig).info("FAIL: Renew Risk Success message not shown");
								if (strNotifMsg.trim().contentEquals(Constants.QuoteAlreadyCreatedErrorMsg)) {
									CommonLib.getLogger(strConfig)
											.info("FAIL: Renew Risk: 'Quote already created' error message shown");
									TestReporter.Fail(driver, strConfig, "Renew Risk Error Message",
											" 'Quote already created' error message shown");
								}

								else if (strNotifMsg.trim().contentEquals(Constants.ConnectionTimeoutErrorMsg)) {
									CommonLib.getLogger(strConfig)
											.info("FAIL: Renew Risk: 'Connection Timeout' error message shown");
									TestReporter.Fail(driver, strConfig, "Renew Risk Error Message",
											" 'Connection Timeout' error message shown");
								}

								else if (strNotifMsg.trim().contentEquals(Constants.InternalServerErrorMsg)) {
									CommonLib.getLogger(strConfig)
											.info("FAIL: Renew Risk: '500 Internal Server Error' message shown");
									TestReporter.Fail(driver, strConfig, "Renew Risk Error Message",
											" '500 Internal Server Error' message shown");
								}

							} 

						} else {
							CommonLib.getLogger(strConfig).info(
									"FAIL: Renew button disabled. Hence cannot proceed with Renewing a Risk");
							TestReporter.Fail(driver, strConfig, "Renew button",
									"Is disabled and cannot proceed with Renewing a Risk");

						}

					} else {
						CommonLib.getLogger(strConfig).info(
								"FAIL: Renew button not visible. Hence cannot proceed with Renewing a Risk");
						TestReporter.Fail(driver, strConfig, "Renew button",
								"Is not visible and cannot proceed with Renewing a Risk");

					}
				}

				else {
					CommonLib.getLogger(strConfig).info(
							"FAIL: Risk Tracker Reference not available. Hence cannot proceed with Renewing the Risk");
					TestReporter.Fail(driver, strConfig, "Risk Tracker Reference not available",
							"Cannot proceed with Renewing the Risk");
				}
			} catch (NoSuchElementException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (StaleElementReferenceException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (JavascriptException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (TimeoutException e) {
				TestReporter.Fail(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (NullPointerException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (IllegalArgumentException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			}

		}

		
		
		
		public static void verifyRisk_AfterRenewal(RemoteWebDriver driver, String strConfig,
				ArrayList<String> RenewalData) {
			
			CommonLib.getLogger(strConfig).info("Inside verifyRisk_AfterRenewal");
			CommonLib.waitForLoad(driver, strConfig);

			// RISK OVERVIEW TAB
			CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_MenuItem_RiskOverview");
			
			// RISK DETAILS TAB
			CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_MenuItem_RiskDetails");
						
			// INSTRUCTIONS SHEET TAB
			CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_MenuItem_InstructionsSheet");
			

			// Click on the Risk Details Menu Item
			WebElement menuItemRiskOverviewEle = repository.GetObject(driver, strConfig,
					"LN_MenuItem_RiskOverview");
			common.JSClick(driver, strConfig, menuItemRiskOverviewEle);
			CommonLib.waitForLoad(driver, strConfig);

			CommonLib.WaitForElementToBeClickable(driver, strConfig, "DDO_RiskOverview_Division");

			//Check Renewal Risk Ref and Expiring UMR generated
			String riskTrackerRefNum = RTData.getRiskTracker_RefNo(driver, strConfig,
					Constants.riskTrackerRefNo_XPATH);
			
			if(riskTrackerRefNum.trim()!=null || !(riskTrackerRefNum.trim().isEmpty()) ) {
				TestReporter.Pass(driver, strConfig, "Renewal Risk Tracker Ref Number", "Generated");
			}
			else {	
				TestReporter.Pass(driver, strConfig, "Renewal Risk Tracker Ref Number", "Not Generated");
			}
			
			
			String expringUMRNum = RTData.getExpiringUMR(driver, strConfig,
					Constants.ExpiringUMR_XPATH);
			
			if(expringUMRNum.trim()!=null || !(expringUMRNum.trim().isEmpty()) ) {
				TestReporter.Pass(driver, strConfig, "Expiring UMR Number", "Generated");
			}
			else {	
				TestReporter.Pass(driver, strConfig, "Expiring UMR Number", "Not Generated");
			}

			// Verify NTU, DMS DOCUMENTS and NOTES(0) button are visible and enabled
			// Verify Firm Order and Submit to Broker Ops disabled
			CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Actions_NTU");
			CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Actions_Dms_Documents");
			CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Actions_Notes");
			CommonLib.WaitForElementToBeVisible(driver, strConfig, "BTN_Actions_FirmOrder_Disabled");
			CommonLib.WaitForElementToBeVisible(driver, strConfig, "BTN_Actions_SubmitTo_BrokerOps_Disabled");

			// --------Verify Stage Checked - CREATED
			// --------Verify Stage Unchecked - NBI, QUOTE, FIRM ORDER, SUBMIT TO BROKER OPS

			CommonLib.WaitForElementToBeVisible(driver, strConfig,
					Constants.riskOverview_Created_StageIcon_Checked_XPATH);
			CommonLib.WaitForElementToBeVisible(driver, strConfig,
					Constants.riskOverview_NBI_StageIcon_UnChecked_XPATH);
			CommonLib.WaitForElementToBeVisible(driver, strConfig,
					Constants.riskOverview_Quote_StageIcon_UnChecked_XPATH);
			CommonLib.WaitForElementToBeVisible(driver, strConfig,
					Constants.riskOverview_FirmOrder_StageIcon_UnChecked_XPATH);
			CommonLib.WaitForElementToBeVisible(driver, strConfig,
					Constants.riskOverview_SubmittedTo_BrokerOps_StageIcon_UnChecked_XPATH);
			
			
			
			// ---------------Verify Date Populated against Created Date Field------------
			WebElement riskCreatedDateEle = driver
					.findElement(By.xpath(Constants.riskOverview_Created_Stage_Date_Populated_XPATH));
			String riskCreatedDate = riskCreatedDateEle.getText();

			if (riskCreatedDate.trim().contentEquals(RTData.getCurrentStageDate())) {
				CommonLib.getLogger(strConfig).info("PASS: Created Date visible upon Expiring Risk Renewal(creation of new Renewal Risk Record)"
						+ " in Risk Overview Tab");
				TestReporter.Pass(driver, strConfig, "Created Date: ",
						"Visible upon upon Expiring Risk Renewal(creation of new Renewal Risk Record) in Risk Overview Tab");
			}

			else {
				CommonLib.getLogger(strConfig).info("FAIL: Created Date not visible upon Expiring Risk Renewal(creation of new Renewal Risk Record)"
						+ " in Risk Overview Tab");
				TestReporter.Fail(driver, strConfig, "Created Date: ",
						"Not visible upon upon Expiring Risk Renewal(creation of new Renewal Risk Record) in Risk Overview Tab");

			}
		
			// --- Verify Risk Overview Fields Enabled (except UMR and Agent/Client)
			CommonLib.WaitForElementToBeVisible(driver, strConfig, "TXT_RiskOverview_UMR_Disabled");
			WebElement UMRTxtEle = repository.GetObject(driver, strConfig, "TXT_RiskOverview_UMR");
			if (UMRTxtEle.getText() != null && !(UMRTxtEle.getText().trim().isEmpty())) {
				CommonLib.getLogger(strConfig).info(
						"FAIL: UMR Text Input Field is not empty/UMR is populated in UMR Text Input Field after Renewing Expiring Risk");
				TestReporter.Fail(driver, strConfig, "UMR Text Input Field", "Not empty/Has value after Renewing Expiring Risk");
			} else {
				CommonLib.getLogger(strConfig).info(
						"PASS: UMR Text Input Field is empty/UMR is not populated in UMR Text Input Field after Renewing Expiring Risk");
				TestReporter.Pass(driver, strConfig, "UMR Text Input Field", "Empty after Renewing Expiring Risk");

			}

			CommonLib.WaitForElementToBeClickable(driver, strConfig,
					"DDO_RiskOverview_NewOrRenewal_Classification");
			CommonLib.WaitForElementToBeClickable(driver, strConfig, "DDO_RiskOverview_Division");
			CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskOverview_AgentOrClient");
			CommonLib.WaitForElementToBeClickable(driver, strConfig,
					"CHK_RiskOverview_DirectInsured_Placement_ONOFF_Switch");
			CommonLib.WaitForElementToBeClickable(driver, strConfig, "CHK_RiskOverview_Reinsurance_ONOFF_Switch");
			CommonLib.WaitForElementToBeClickable(driver, strConfig,
					"TXT_RiskOverview_Insured_Or_DirectInsured_Or_OriginalInsured");
			CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskOverview_Assured");
			CommonLib.WaitForElementToBeClickable(driver, strConfig, "DDO_RiskOverview_AccountExecutive");
			CommonLib.WaitForElementToBeClickable(driver, strConfig, "DDO_RiskOverview_PlacingBroker");
			CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Save");
			CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Cancel");

			// --- Verify Risk Details Fields Enabled
			// RISK DETAILS TAB
			CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_MenuItem_RiskDetails");
			CommonLib.smallDelay();
			// Click on the Risk Details Menu Item
			WebElement menuItemRiskDetailsEle = repository.GetObject(driver, strConfig, "LN_MenuItem_RiskDetails");
			common.JSClick(driver, strConfig, menuItemRiskDetailsEle);
			CommonLib.waitForLoad(driver, strConfig);
			CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskDetails_PolicyType");
			CommonLib.WaitForElementToBeClickable(driver, strConfig, "DDO_RiskDetails_PremiumCurrency_Code");
			CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskDetails_Limit");
			CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskDetails_Premium");
			CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskDetails_Deductible");
			CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskDetails_ClientCommission");
			CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskDetails_Information");
			CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskDetails_Terms");
			CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Save");
			CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Cancel");
			CommonLib.WaitForElementToBeVisible(driver, strConfig, "BTN_NBI_Disabled");

		
		}
		
		
		
		
		public static void save_RiskOverview(RemoteWebDriver driver, String strConfig, String strTestingType, 
				ArrayList<String> riskOverviewData) {

			String riskTrackerRefNum = null;
			try {
				
				
				CommonLib.getLogger(strConfig).info("Inside save_RiskOverview()");

				CommonLib.waitForLoad(driver, strConfig);
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_MenuItem_RiskOverview");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "DDO_RiskOverview_Division");

				// NEW/RENEWALCLASSIFICATION
				// ---- Select New/Renewal Classification Option from the Dropdown
				if (riskOverviewData.get(1) != null && !(riskOverviewData.get(1).trim().isEmpty())) {
					CommonLib.WaitForElementToBeClickable(driver, strConfig,
							"DDO_RiskOverview_NewOrRenewal_Classification");
					Project.ExecuteRow(driver, strConfig, "DDO_RiskOverview_NewOrRenewal_Classification", "Click");

					CommonLib.WaitForElementToBeClickable(driver, strConfig,
							"DTI_RiskOverview_NewOrRenewal_Classification");
					Project.ExecuteRow(driver, strConfig, "DTI_RiskOverview_NewOrRenewal_Classification",
							riskOverviewData.get(1));

				}

				// DIVISION
				// ---- Select Division Option from the Dropdown
				if (riskOverviewData.get(2) != null && !(riskOverviewData.get(2).trim().isEmpty())) {
					CommonLib.WaitForElementToBeClickable(driver, strConfig, "DDO_RiskOverview_Division");
					Project.ExecuteRow(driver, strConfig, "DDO_RiskOverview_Division", "Click");

					CommonLib.WaitForElementToBeClickable(driver, strConfig, "DTI_RiskOverview_Division");
					Project.ExecuteRow(driver, strConfig, "DTI_RiskOverview_Division", riskOverviewData.get(2));
				} else {
					CommonLib.getLogger(strConfig).info("info: No Division value supplied in RTTestdata.xlsx file.");
					TestReporter.Info(driver, strConfig, "Division value", "Not supplied in RTTestdata.xlsx file");
				}

				// AGENT/CLIENT Field will be disabled since the Risk Overview Tab has already
				// been saved

				// DIRECT INSURED PLACEMENT OR REINSURANCE
				if ((riskOverviewData.get(4) == null || riskOverviewData.get(4).trim().isEmpty()
						|| riskOverviewData.get(4).trim().contentEquals("No"))
						&& (riskOverviewData.get(6) == null || riskOverviewData.get(6).trim().isEmpty()
								|| riskOverviewData.get(6).trim().contentEquals("No"))) {

					if (riskOverviewData.get(3) != null && !(riskOverviewData.get(3).trim().isEmpty())) {
						CommonLib.WaitForElementToBeClickable(driver, strConfig,
								"TXT_RiskOverview_Insured_Or_DirectInsured_Or_OriginalInsured");
						Project.ExecuteRow(driver, strConfig,
								"TXT_RiskOverview_Insured_Or_DirectInsured_Or_OriginalInsured",
								riskOverviewData.get(3));
					}
				}

				else if (riskOverviewData.get(4) != null && !(riskOverviewData.get(4).trim().isEmpty())
						&& riskOverviewData.get(4).trim().contentEquals("Yes")) {
					CommonLib.WaitForElementToBeClickable(driver, strConfig,
							"CHK_RiskOverview_DirectInsured_Placement_ONOFF_Switch");
					Project.ExecuteRow(driver, strConfig, "CHK_RiskOverview_DirectInsured_Placement_ONOFF_Switch",
							"check");

					// This Logic needs to be clarified from BA or Dev
					// Since this is already populated when Direct Insured is Switched ON and the
					// value cannot be verified with Agent/Client entered previously
					if (riskOverviewData.get(5) != null && !(riskOverviewData.get(5).trim().isEmpty())) {
						CommonLib.WaitForElementToBeClickable(driver, strConfig,
								"TXT_RiskOverview_Insured_Or_DirectInsured_Or_OriginalInsured");
						Project.ExecuteRow(driver, strConfig,
								"TXT_RiskOverview_Insured_Or_DirectInsured_Or_OriginalInsured",
								riskOverviewData.get(5));
					}
				}

				else if (riskOverviewData.get(6) != null && !(riskOverviewData.get(6).trim().isEmpty())
						&& riskOverviewData.get(6).trim().contentEquals("Yes")) {
					CommonLib.WaitForElementToBeClickable(driver, strConfig,
							"CHK_RiskOverview_Reinsurance_ONOFF_Switch");
					Project.ExecuteRow(driver, strConfig, "CHK_RiskOverview_Reinsurance_ONOFF_Switch", "check");
					// This Logic needs to be clarified from BA or Dev
					if (riskOverviewData.get(7) != null && !(riskOverviewData.get(7).trim().isEmpty())) {
						CommonLib.WaitForElementToBeClickable(driver, strConfig,
								"TXT_RiskOverview_Insured_Or_DirectInsured_Or_OriginalInsured");
						Project.ExecuteRow(driver, strConfig,
								"TXT_RiskOverview_Insured_Or_DirectInsured_Or_OriginalInsured",
								riskOverviewData.get(7));
					}

					// This Logic needs to be clarified from BA or Dev
					if (riskOverviewData.get(8) != null && !(riskOverviewData.get(8).trim().isEmpty())) {
						CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskOverview_Reinsured");
						Project.ExecuteRow(driver, strConfig, "TXT_RiskOverview_Reinsured", riskOverviewData.get(8));
					}

				}
				// ASSURED - (Logic will change as there is a condition to enter Assured...
				// Currently it is kept as Mandatory)
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskOverview_Assured");
				Project.ExecuteRow(driver, strConfig, "TXT_RiskOverview_Assured", riskOverviewData.get(9));

				
				// PCP-Select PCP Option from the Dropdown

				Project.ExecuteRow(driver, strConfig, "DDO_RiskOverview_PCP", "Click");

				CommonLib.WaitForElementToBeClickable(driver, strConfig, "DTI_RiskOverview_PCP");
				// ---- Select PCP Option from the Dropdown [DATA FROM EXCEL]
				Project.ExecuteRow(driver, strConfig, "DTI_RiskOverview_PCP",
						riskOverviewData.get(13));
				
				// ACCOUNT EXECUTIVE
				// ---- Select Account Executive Option from the Dropdown
				if (riskOverviewData.get(10) != null && !(riskOverviewData.get(10).trim().isEmpty())) {
					Project.ExecuteRow(driver, strConfig, "DDO_RiskOverview_AccountExecutive", "Click");

					CommonLib.WaitForElementToBeClickable(driver, strConfig, "DTI_RiskOverview_AccountExecutive");
					// ---- Select Account Executive Option from the Dropdown [DATA FROM EXCEL]
					Project.ExecuteRow(driver, strConfig, "DTI_RiskOverview_AccountExecutive",
							riskOverviewData.get(10));
				} else {
					CommonLib.getLogger(strConfig).info("INFO: No Account Executive supplied in RTTestdata.xlsx file.");
					TestReporter.Info(driver, strConfig, "Account Executive", "Not supplied in RTTestdata.xlsx file.");
				}

				// Placing Broker
				// ---- Select Placing Broker Option from the Dropdown
				if (riskOverviewData.get(11) != null && !(riskOverviewData.get(11).trim().isEmpty())) {
					CommonLib.WaitForElementToBeClickable(driver, strConfig, "DDO_RiskOverview_PlacingBroker");
					Project.ExecuteRow(driver, strConfig, "DDO_RiskOverview_PlacingBroker", "Click");
					CommonLib.WaitForElementToBeClickable(driver, strConfig, "DTI_RiskOverview_PlacingBroker");
					// ---- Select Placing Broker Option from the Dropdown [DATA FROM EXCEL]
					Project.ExecuteRow(driver, strConfig, "DTI_RiskOverview_PlacingBroker", riskOverviewData.get(11));
				} else {
					CommonLib.getLogger(strConfig)
							.info("INFO: No Placing Broker(MANDATORY FIELD) supplied in RTTestdata.xlsx file.");
					TestReporter.Info(driver, strConfig, "Placing Broker(MANDATORY FIELD)",
							"Not supplied in RTTestdata.xlsx file.");
				}

				// SAVE BUTTON
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Save");
				Project.ExecuteRow(driver, strConfig, "BTN_Save", "Click");

				CommonLib.WaitForElementToBeVisible(driver, strConfig, Constants.NotifMsg_XPATH);

				String strNotifMsg = driver.findElement(By.xpath(Constants.NotifMsg_XPATH)).getText();
				String strRiskDetailsSavedSuccessMsg = Constants.RiskDetailsSavedSuccessMsg;
				// Risk Created Success Message Shown
				if (strNotifMsg.trim().contains(strRiskDetailsSavedSuccessMsg)) {
					CommonLib.getLogger(strConfig).info(
							"PASS: Risk Details Saved on Risk Overview: Success message shown - '" + strRiskDetailsSavedSuccessMsg + "'");
					TestReporter.Pass(driver, strConfig, " Risk Details Saved on Risk Overview:  ",
							"Success message shown- '" + strRiskDetailsSavedSuccessMsg + "'");

					CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Save");
					CommonLib.waitForLoad(driver, strConfig);

					CommonLib.WaitForElementToBeClickable(driver, strConfig, "DDO_RiskOverview_Division");
					CommonLib.WaitForElementToBeClickable(driver, strConfig, "DDO_RiskOverview_AccountExecutive");
					BusinessRules.verify_RiskOverview_TabAfter_RiskGeneration_Or_Saving_RiskOverviewTab(driver, strTestingType, strConfig, false);
				}

				// Error Message Shown
				else {
					CommonLib.getLogger(strConfig).info("FAIL: Risk Details Saved on Risk Overview: Success message not shown");
					if (strNotifMsg.trim().contentEquals(Constants.QuoteAlreadyCreatedErrorMsg)) {
						CommonLib.getLogger(strConfig)
								.info("FAIL: Risk Overview  : 'Quote already created' error message shown");
						TestReporter.Fail(driver, strConfig, "Risk Overview  :  Error Message",
								" 'Quote already created' error message shown");
					}

					else if (strNotifMsg.trim().contentEquals(Constants.ConnectionTimeoutErrorMsg)) {
						CommonLib.getLogger(strConfig)
								.info("FAIL: Risk Overview : 'Connection Timeout' error message shown");
						TestReporter.Fail(driver, strConfig, "Risk Overview  :  Error Message",
								" 'Connection Timeout' error message shown");
					}

					else if (strNotifMsg.trim().contentEquals(Constants.InternalServerErrorMsg)) {
						CommonLib.getLogger(strConfig)
								.info("FAIL: Risk Overview  : '500 Internal Server Error' message shown");
						TestReporter.Fail(driver, strConfig, "Risk Overview  : Error Message",
								" '500 Internal Server Error' message shown");
					}
				}

			} catch (NoSuchElementException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (StaleElementReferenceException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (JavascriptException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (TimeoutException e) {
				TestReporter.Fail(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (NullPointerException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (IllegalArgumentException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			}

		}
		
		
		//Regression Scenarios
		public static void verify_RiskOverview_And_RiskDetails_Info_OnDashboard_RiskCard(RemoteWebDriver driver, String strConfig, String strRiskRef) {
			{
			try {
				CommonLib.getLogger(strConfig).info("Inside verify_RiskOverview_And_RiskDetails_Info_OnDashboard_RiskCard");
				TestReporter.Info(driver, strConfig, "Inside Function",
						"verify_RiskOverview_And_RiskDetails_Info_OnDashboard_RiskCard");
				
				String riskOverview_UMR;
				String riskOverview_Client;
				String riskOverview_Status = "";
				String riskOverview_InsuredOrDirectInsuredOrReinsured;
				String riskOverview_Assured;
				String risk_DateModified ="";
				
				
				
				String riskDetails_COB;
				String riskOverview_RiskName;
				String riskOverview_InceptionDate;
				
				
				
				Boolean clickSave = false;
				riskOverview_UMR = repository.GetObject(driver, strConfig, "TXT_RiskOverview_UMR").getAttribute("value");
				riskOverview_Client = repository.GetObject(driver, strConfig, "TXT_RiskOverview_AgentOrClient").getAttribute("value");

				if (CommonLib.isElementVisible(driver, strConfig,
						Constants.riskOverview_NTU_StageIcon_Checked_XPATH)) {
					riskOverview_Status = "NTU";
					WebElement risk_DateModifiedEle = driver
							.findElement(By.xpath(Constants.riskOverview_NTU_Stage_Date_Populated_XPATH));
					risk_DateModified = risk_DateModifiedEle.getText();
					
				} 
				
				
				else if (CommonLib.isElementVisible(driver, strConfig,
						Constants.riskOverview_SubmittedTo_BrokerOps_StageIcon_Checked_XPATH)) {
					riskOverview_Status = "Submitted";
					WebElement risk_DateModifiedEle = driver
							.findElement(By.xpath(Constants.riskOverview_SubmitToAdmin_Stage_Date_Populated_XPATH));
					risk_DateModified = risk_DateModifiedEle.getText();
					
				} 
				
				else if (CommonLib.isElementVisible(driver, strConfig,
						Constants.riskOverview_FirmOrder_StageIcon_Checked_XPATH)) {
					riskOverview_Status = "Policy";
					WebElement risk_DateModifiedEle = driver
							.findElement(By.xpath(Constants.riskOverview_FirmOrder_Stage_Date_Populated_XPATH));
					risk_DateModified = risk_DateModifiedEle.getText();
				}

				else if (CommonLib.isElementVisible(driver, strConfig,
						Constants.riskOverview_Quote_StageIcon_Checked_XPATH)) {
					riskOverview_Status = "Quote";
					clickSave = true;
				} 
				
				else if (CommonLib.isElementVisible(driver, strConfig,
						Constants.riskOverview_NBI_StageIcon_Checked_XPATH)) {
					riskOverview_Status = "NBI";
					clickSave = true;
				}

				else if (CommonLib.isElementVisible(driver, strConfig,
						Constants.riskOverview_Created_StageIcon_Checked_XPATH)) {
					riskOverview_Status = "In Negotiation";
					clickSave = true;
				}
				
				Boolean isDirectInsuredSelected = repository.GetObject(driver, strConfig, "CHK_RiskOverview_DirectInsured_Placement_ONOFF_Switch").isSelected();				
				Boolean isReinsuredSelected = repository.GetObject(driver, strConfig, "CHK_RiskOverview_Reinsurance_ONOFF_Switch").isSelected();				
				
				
				if(isDirectInsuredSelected) {
					riskOverview_InsuredOrDirectInsuredOrReinsured = repository.GetObject(driver, strConfig, "TXT_RiskOverview_Insured_Or_DirectInsured_Or_OriginalInsured").getAttribute("value");
					
				}
				else if(isReinsuredSelected) {
					riskOverview_InsuredOrDirectInsuredOrReinsured = repository.GetObject(driver, strConfig, "TXT_RiskOverview_Reinsured").getAttribute("value");
					
				}
				else {
					riskOverview_InsuredOrDirectInsuredOrReinsured = repository.GetObject(driver, strConfig, "TXT_RiskOverview_Insured_Or_DirectInsured_Or_OriginalInsured").getAttribute("value");
					
				}
				riskOverview_Assured = repository.GetObject(driver, strConfig, "TXT_RiskOverview_Assured").getAttribute("value");
				
				//Navigate to Risk Details Tab
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_MenuItem_RiskDetails");
				CommonLib.smallDelay();
				// Click on the Risk Details Menu Item
				WebElement menuItemRiskDetailsEle = repository.GetObject(driver, strConfig, "LN_MenuItem_RiskDetails");
				common.JSClick(driver, strConfig, menuItemRiskDetailsEle);
				CommonLib.smallDelay();
				CommonLib.waitForLoad(driver, strConfig);
				CommonLib.smallDelay();

				// -------Feature Switch Field ---------//
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "TXT_RiskDetails_PolicyType");
				riskDetails_COB =  repository.GetObject(driver, strConfig, "TXT_RiskDetails_PolicyType").getAttribute("value");
				
				
				System.out.println("riskOverview_UMR"+riskOverview_UMR);
				System.out.println("riskOverview_Client"+riskOverview_Client);
				System.out.println("riskOverview_Status"+riskOverview_Status);
				System.out.println("riskOverview_InsuredOrDirectInsuredOrReinsured"+riskOverview_InsuredOrDirectInsuredOrReinsured);
				System.out.println("riskOverview_Assured"+riskOverview_Assured);
				System.out.println("riskDetails_COB"+riskDetails_COB);
				//Code to be included for Risk Name and Inception Date for Phase 2
				// riskOverview_RiskName = "";
				// riskOverview_InceptionDate = "";
				
				//Navigate Back to Risk Overview tab
				// Click on the Risk Details Menu Item
				WebElement menuItemRiskOverviewEle = repository.GetObject(driver, strConfig,
						"LN_MenuItem_RiskOverview");
				common.JSClick(driver, strConfig, menuItemRiskOverviewEle);
				CommonLib.waitForLoad(driver, strConfig);

				CommonLib.WaitForElementToBeVisible(driver, strConfig, "DDO_RiskOverview_Division");

				if(clickSave) {
				//Click on Save Button to record Modified Date as Current Date
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Save");
				Project.ExecuteRow(driver, strConfig, "BTN_Save", "Click");
				
				risk_DateModified = repository.GetObject(driver, strConfig, "TXT_RiskOverview_UMR").getText();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
				LocalDateTime now = LocalDateTime.now();  
				risk_DateModified = formatter.format(now).trim();
				
				System.out.println("riskOverview_DateModified"+risk_DateModified);
				}
				
				CommonLib.waitForLoad(driver, strConfig);
				BusinessRules.navigateToHomePage(driver, strConfig);
				
				String[] RiskCard_UMR;
				if(riskOverview_UMR.contentEquals("")) {
				 RiskCard_UMR = CommonLib.getLocatorDetails_From_ObjectRepository(driver, strConfig,
						"LBL_RiskDashboard_RiskCard_UMR_NotGenerated");
				}
				else {
				 RiskCard_UMR = CommonLib.getLocatorDetails_From_ObjectRepository(driver, strConfig,
							"LBL_RiskDashboard_RiskCard_UMR");
				}
				String[] RiskCard_Client= CommonLib.getLocatorDetails_From_ObjectRepository(driver, strConfig,
						"LBL_RiskDashboard_RiskCard_Client");
				String[] RiskCard_Status = CommonLib.getLocatorDetails_From_ObjectRepository(driver, strConfig,
						"LBL_RiskDashboard_RiskCard_Status");
				String[] RiskCard_InsuredOrDirectInsuredOrReinsured = CommonLib.getLocatorDetails_From_ObjectRepository(driver, strConfig,
						"LBL_RiskDashboard_RiskCard_InsuredOrDirectInsuredOrReinsured");
				String[] RiskCard_Assured = CommonLib.getLocatorDetails_From_ObjectRepository(driver, strConfig,
						"LBL_RiskDashboard_RiskCard_Assured");
				
				String[] RiskCard_DateModified = CommonLib.getLocatorDetails_From_ObjectRepository(driver, strConfig,
						"LBL_RiskDashboard_RiskCard_DateModified");
				String[] RiskCard_ClassOfBusiness = CommonLib.getLocatorDetails_From_ObjectRepository(driver, strConfig,
						"LBL_RiskDashboard_RiskCard_ClassOfBusiness");
			
				String riskOverview_Client_FirstWord = CommonLib.getFirstWord(riskOverview_Client);
				
				System.out.println("with dot riskOverview_Client"+riskOverview_Client_FirstWord);
				
				Boolean UMRMatch = CommonLib.isElementVisible(driver, strConfig, String.format(RiskCard_UMR[1], strRiskRef.trim(), riskOverview_UMR.trim()));
				Boolean ClientMatch = CommonLib.isElementVisible(driver, strConfig, String.format(RiskCard_Client[1], strRiskRef.trim(), riskOverview_Client_FirstWord.trim()));
				Boolean StatusMatch = CommonLib.isElementVisible(driver, strConfig, String.format(RiskCard_Status[1], strRiskRef.trim(),riskOverview_Status.trim()));
				
				Boolean InsuredOrDirectInsuredOrReinsuredMatch = false;
				if(riskOverview_InsuredOrDirectInsuredOrReinsured.trim().contentEquals("")) {
					 InsuredOrDirectInsuredOrReinsuredMatch = CommonLib.isElementVisible(driver, strConfig, String.format(RiskCard_InsuredOrDirectInsuredOrReinsured[1], strRiskRef.trim(), "Not available"));
					
				}
				else {
					 InsuredOrDirectInsuredOrReinsuredMatch = CommonLib.isElementVisible(driver, strConfig, String.format(RiskCard_InsuredOrDirectInsuredOrReinsured[1], strRiskRef.trim(), riskOverview_InsuredOrDirectInsuredOrReinsured.trim()));
					
				}
				
				Boolean AssuredMatch = false;
				if(!(riskOverview_Assured.trim().contentEquals(""))) {
					 AssuredMatch = CommonLib.isElementVisible(driver, strConfig, String.format(RiskCard_Assured[1], strRiskRef.trim(),riskOverview_Assured.trim()));
					
					 if(AssuredMatch) {
							TestReporter.Pass(driver, strConfig, "Match", "Assured on Risk Overview Tab Matches Assured on Dashboard RiskCard");
						}
						else {
							TestReporter.Fail(driver, strConfig, "No Match", "Assured on Risk Overview Tab does not match Assured on Dashboard RiskCard");
							
						}
				}
				
				Boolean DateModifiedMatch = CommonLib.isElementVisible(driver, strConfig, String.format(RiskCard_DateModified[1], strRiskRef.trim(),risk_DateModified.trim()));
				Boolean ClassOfBusinessMatch = CommonLib.isElementVisible(driver, strConfig, String.format(RiskCard_ClassOfBusiness[1], strRiskRef.trim(),riskDetails_COB.trim()));
				
				if(UMRMatch) {
					TestReporter.Pass(driver, strConfig, "Match", "UMR on Risk Overview Tab Matches UMR on Dashboard RiskCard");
				}
				else {
					TestReporter.Fail(driver, strConfig, "No Match", "UMR on Risk Overview Tab does not match UMR on Dashboard RiskCard");
					
				}
				
				if(ClientMatch) {
					TestReporter.Pass(driver, strConfig, "Match", "Client on Risk Overview Tab Matches Client on Dashboard RiskCard");
				}
				else {
					TestReporter.Fail(driver, strConfig, "No Match", "Client on Risk Overview Tab does not match Client on Dashboard RiskCard");
					
				}
				
				if(StatusMatch) {
					TestReporter.Pass(driver, strConfig, "Match", "Risk Status on Risk Overview Tab Matches Risk Status on Dashboard RiskCard");
				}
				else {
					TestReporter.Fail(driver, strConfig, "No Match", "Risk Status on Risk Overview Tab does not match Risk Status on Dashboard RiskCard");
					
				}
				
				
				if(InsuredOrDirectInsuredOrReinsuredMatch) {
					TestReporter.Pass(driver, strConfig, "Match", "Insured Or Direct Insured Or Reinsured on Risk Overview Tab Matches Insured Or Direct Insured Or Reinsured on Dashboard RiskCard");
				}
				else {
					TestReporter.Fail(driver, strConfig, "No Match", "Insured Or Direct Insured Or Reinsured on Risk Overview Tab does not match Insured Or Direct Insured Or Reinsured on Dashboard RiskCard");
					
				}
				
				
				
				if(DateModifiedMatch) {
					TestReporter.Pass(driver, strConfig, "Match", "Date on which Risk is Modified Matches Date Modified on Dashboard RiskCard");
				}
				else {
					TestReporter.Fail(driver, strConfig, "No Match", "Date on which Risk is Modified does not match Date Modified on Dashboard RiskCard");
					
				}
				
				if(ClassOfBusinessMatch) {
					TestReporter.Pass(driver, strConfig, "Match", "Policy value in Risk Details tab Matches Class of Business value on Dashboard RiskCard");
				}
				else {
					TestReporter.Fail(driver, strConfig, "No Match", "Policy value in Risk Details tab does not match Class of Business value on Dashboard RiskCard");
					
				}
				
				
				
			} catch (NoSuchElementException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (StaleElementReferenceException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (JavascriptException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (TimeoutException e) {
				TestReporter.Fail(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (NullPointerException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (IllegalArgumentException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			}

			catch (ArrayIndexOutOfBoundsException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			}
			}
			

		}
		
		public static void verify_Fields_BeforeAndAfter_DirectInsured_SwitchedON(RemoteWebDriver driver, String strConfig) {
			
			try {
				
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_RiskDashboard_NewRisk");
				Project.ExecuteRow(driver, strConfig, "BTN_RiskDashboard_NewRisk", "Click");
				CommonLib.waitForLoad(driver, strConfig);
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_MenuItem_RiskOverview");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "DDO_RiskOverview_Division");
				
				
				//This code need to be added for Default fields verification
				//Check Insured Label is displayed before Direct Insured/Reinsured Switched ON
				String insuredLbl = repository
						.GetObject(driver, strConfig, "LBL_RiskOverview_Insured_Or_DirectInsured_Or_OriginalInsured")
						.getText();
				
				if (insuredLbl.contentEquals("Insured")) {
					TestReporter.Pass(driver, strConfig, "Direct Insured and Reinsured Switched OFF(Default)",
							"'Insured' Text Field Label is visible'");
				}

				else {
					TestReporter.Fail(driver, strConfig, "Direct Insured and Reinsured Switched OFF(Default)",
							"'Insured' Text Field Label is not visible'.");
				}
				
				//Switch ON Direct Insured Field
				CommonLib.WaitForElementToBeClickable(driver, strConfig,
						"CHK_RiskOverview_DirectInsured_Placement_ONOFF_Switch");
				Project.ExecuteRow(driver, strConfig,
						"CHK_RiskOverview_DirectInsured_Placement_ONOFF_Switch", "Click");
				
				//Verify Insured Text Field Label changes to Direct Insured after Direct Insured Switched ON
				
				String directInsuredLbl = repository
						.GetObject(driver, strConfig, "LBL_RiskOverview_Insured_Or_DirectInsured_Or_OriginalInsured")
						.getText();
				
				if(directInsuredLbl.contentEquals("Direct Insured")){
					TestReporter.Pass(driver, strConfig, "Direct Insured Switched ON", 
							"'Insured' Text Field Label changes to 'Direct Insured'");
				}
				else {
					TestReporter.Fail(driver, strConfig, "Direct Insured Switched ON", 
							"'Insured' Text Field Label does not change to 'Direct Insured'");
				}
				
				
				
			} catch (NoSuchElementException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (StaleElementReferenceException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (JavascriptException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (TimeoutException e) {
				TestReporter.Fail(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (NullPointerException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (IllegalArgumentException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			}

			catch (ArrayIndexOutOfBoundsException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			}
			
		}
		
		
		public static void change_DefaultActive_Organization(RemoteWebDriver driver, String strConfig, String activeOrganisation) {
			
			try {
				
				//Click on the Settings link on the Dashboard
				CommonLib.waitForLoad(driver, strConfig);
				
				String[] Array_SettingsLink = ObjectRepository
						.GetObjectlocators("LN_Settings");
				System.out.println("LN Settings default"+String.format(
						Array_SettingsLink[1], Constants.defaultActiveOrg));
				CommonLib.WaitForElementToBeClickable(driver, strConfig, String.format(
						Array_SettingsLink[1], Constants.defaultActiveOrg));
				
				WebElement settingsEle = driver.findElementByXPath(String.format(
						Array_SettingsLink[1], Constants.defaultActiveOrg));
				common.JSClick(driver, strConfig, settingsEle);

				
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "LBL_Settings_Text");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "CBO_Settings_ActiveOrganisation");
				
				Project.ExecuteRow(driver, strConfig,"CBO_Settings_ActiveOrganisation", activeOrganisation);
				
				Project.ExecuteRow(driver, strConfig,"BTN_Settings_Save", "Click");
				
				CommonLib.WaitForElementToBeVisible(driver, strConfig, Constants.NotifMsg_XPATH);

				String strNotifMsg = driver.findElement(By.xpath(Constants.NotifMsg_XPATH)).getText();
				String strChangesSavedMsg = Constants.ChangesSavedMsg;
				// Verify Default Active Organisation Changed Success Message Shown
				if (strNotifMsg.trim().contains(strChangesSavedMsg)) {
					
					TestReporter.Pass(driver, strConfig, " Default Active Organisation  ",
							"Success message shown- '" + strChangesSavedMsg + "'");
					
					CommonLib.WaitForElementToBeClickable(driver, strConfig,String.format(
							Array_SettingsLink[1], activeOrganisation));
					
					Boolean defaultActiveOrgChanged = CommonLib.isElementEnabled(driver, strConfig, String.format(
							Array_SettingsLink[1], activeOrganisation));
					
					if(defaultActiveOrgChanged){
						TestReporter.Pass(driver, strConfig, "Default Active Organisation", 
								"Changed Successfully");
					}
					else {
						TestReporter.Fail(driver, strConfig, "Default Active Organisation", 
								"Not Changed");
					}
					
				}

				// Error Message Shown
				else {
					TestReporter.Fail(driver, strConfig, "Notification message",
							"Default Active Organisation Changed Success message not shown");
					
					if (strNotifMsg.trim().contentEquals(Constants.QuoteAlreadyCreatedErrorMsg)) {
						TestReporter.Fail(driver, strConfig, "Notification message",
								" 'Quote already created' error message shown");
					}

					else if (strNotifMsg.trim().contentEquals(Constants.ConnectionTimeoutErrorMsg)) {
						TestReporter.Fail(driver, strConfig, "Notification message",
								" 'Connection Timeout' error message shown");
					}

					else if (strNotifMsg.trim().contentEquals(Constants.InternalServerErrorMsg)) {
						TestReporter.Fail(driver, strConfig, "Notification message",
								" '500 Internal Server Error' message shown");
					}
				}
				
			} catch (NoSuchElementException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (StaleElementReferenceException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (JavascriptException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (TimeoutException e) {
				TestReporter.Fail(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (NullPointerException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (IllegalArgumentException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			}

			catch (ArrayIndexOutOfBoundsException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			}
			
			
		}
		
		
		public static void change_DefaultActive_Division(RemoteWebDriver driver, String strConfig, String activeDivision) {
			
			try {
				
				//Click on the Settings link on the Dashboard
				CommonLib.waitForLoad(driver, strConfig);
				
				String[] Array_SettingsLink = ObjectRepository
						.GetObjectlocators("LN_Settings");
				
				CommonLib.WaitForElementToBeClickable(driver, strConfig, String.format(
						Array_SettingsLink[1], Constants.defaultActiveOrg));
				
				WebElement settingsEle = driver.findElementByXPath(String.format(
						Array_SettingsLink[1], Constants.defaultActiveOrg));
				common.JSClick(driver, strConfig, settingsEle);

				//Wait for Settings Pop-Up and Dropdown - Active Organisation and Active Division
				CommonLib.WaitForElementToBeVisible(driver, strConfig, "LBL_Settings_Text");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "CBO_Settings_ActiveOrganisation");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Settings_Dropdown_ActiveDivision");
				
				Project.ExecuteRow(driver, strConfig,"BTN_Settings_Dropdown_ActiveDivision", "Click");
				
				CommonLib.waitForLoad(driver, strConfig);
				
				String[] Array_DivisionDropdownOption= ObjectRepository
						.GetObjectlocators("BTN_Settings_DropdownOption_ActiveDivision");
				
				CommonLib.WaitForElementToBeVisible(driver, strConfig, String.format(
						Array_DivisionDropdownOption[1],activeDivision));
				
				WebElement divisionOptionEle = driver.findElementByXPath(String.format(
						Array_DivisionDropdownOption[1],activeDivision));
				
				common.JSClick(driver, strConfig, divisionOptionEle);
				
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Settings_Save");
				Project.ExecuteRow(driver, strConfig,"BTN_Settings_Save", "Click");
				
				CommonLib.WaitForElementToBeVisible(driver, strConfig, Constants.NotifMsg_XPATH);

				String strNotifMsg = driver.findElement(By.xpath(Constants.NotifMsg_XPATH)).getText();
				String strChangesSavedMsg = Constants.ChangesSavedMsg;
				// Verify Default Active Organisation Changed Success Message Shown
				if (strNotifMsg.trim().contains(strChangesSavedMsg)) {
					
					TestReporter.Pass(driver, strConfig, " Default Active Organisation  ",
							"Success message shown- '" + strChangesSavedMsg + "'");
					
					
					//Navigate to Risk Overview tab and verify if the Default Active Division is changed
					CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_RiskDashboard_NewRisk");
						Project.ExecuteRow(driver, strConfig, "BTN_RiskDashboard_NewRisk", "Click");
						CommonLib.waitForLoad(driver, strConfig);
						CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_MenuItem_RiskOverview");
						CommonLib.WaitForElementToBeClickable(driver, strConfig, "DDO_RiskOverview_Division");
						
						
						String[] Array_RiskOverview_DivisionDropdown_Option= ObjectRepository
								.GetObjectlocators("LBL_RiskOverview_DivisionDropdown_DefaultOr_SelectedValue");
						
						CommonLib.WaitForElementToBeVisible(driver, strConfig, String.format(
								Array_RiskOverview_DivisionDropdown_Option[1],activeDivision));
						
						
						Boolean default_ActiveDiv_ChangedInside_RiskOverviewTab = CommonLib.isElementEnabled(driver, strConfig,String.format(
								Array_RiskOverview_DivisionDropdown_Option[1],activeDivision));
						
						
						if(default_ActiveDiv_ChangedInside_RiskOverviewTab){
							TestReporter.Pass(driver, strConfig, "Default Active Division", 
									"Changed Successfully in Risk Overview Tab");
						}
						else {
							TestReporter.Fail(driver, strConfig, "Default Active Division", 
									"Not changed in Risk Overview Tab");
						}
						
					
					
				}

				// Error Message Shown
				else {
					TestReporter.Fail(driver, strConfig, "Notification message",
							"Default Active Division Changed Success message not shown");
					
					if (strNotifMsg.trim().contentEquals(Constants.QuoteAlreadyCreatedErrorMsg)) {
						TestReporter.Fail(driver, strConfig, "Notification message",
								" 'Quote already created' error message shown");
					}

					else if (strNotifMsg.trim().contentEquals(Constants.ConnectionTimeoutErrorMsg)) {
						TestReporter.Fail(driver, strConfig, "Notification message",
								" 'Connection Timeout' error message shown");
					}

					else if (strNotifMsg.trim().contentEquals(Constants.InternalServerErrorMsg)) {
						TestReporter.Fail(driver, strConfig, "Notification message",
								" '500 Internal Server Error' message shown");
					}
				}
				
			} catch (NoSuchElementException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (StaleElementReferenceException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (JavascriptException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (TimeoutException e) {
				TestReporter.Fail(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (NullPointerException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (IllegalArgumentException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			}

			catch (ArrayIndexOutOfBoundsException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			}
			
			
		}
		
		
		
		public static void verify_Fields_BeforeAndAfter_Reinsured_SwitchedON(RemoteWebDriver driver, String strConfig) {
			
			try {
				
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_RiskDashboard_NewRisk");
				Project.ExecuteRow(driver, strConfig, "BTN_RiskDashboard_NewRisk", "Click");
				CommonLib.waitForLoad(driver, strConfig);
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_MenuItem_RiskOverview");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "DDO_RiskOverview_Division");
				
				//Check Insured Label is displayed before Reinsured Switched ON
				String insuredLbl = repository
						.GetObject(driver, strConfig, "LBL_RiskOverview_Insured_Or_DirectInsured_Or_OriginalInsured")
						.getText();
				
				if (insuredLbl.contentEquals("Insured")) {
					TestReporter.Pass(driver, strConfig, "Direct Insured and Reinsured Switched OFF(Default)",
							"'Insured' Text Field Label is visible'");
				}

				else {
					TestReporter.Fail(driver, strConfig, "Direct Insured and Reinsured Switched OFF(Default)",
							"'Insured' Text Field Label is not visible'.");
				}
				
				//Switch ON Reinsured Field
				CommonLib.WaitForElementToBeClickable(driver, strConfig,
						"CHK_RiskOverview_Reinsurance_ONOFF_Switch");
				
				Project.ExecuteRow(driver, strConfig,
						"CHK_RiskOverview_Reinsurance_ONOFF_Switch", "Click");
				
				
				//Verify Insured Text Field Label changes to Original Insured after Reinsured Insured Switched ON
				String OriginalInsuredLbl = repository
						.GetObject(driver, strConfig, "LBL_RiskOverview_Insured_Or_DirectInsured_Or_OriginalInsured")
						.getText();
				
				if(OriginalInsuredLbl.contentEquals("Original Insured")){
					TestReporter.Pass(driver, strConfig, "Reinsured Switched ON", 
							"'Insured' Text Field Label changes to 'Original Insured'");
				}
				else {
					TestReporter.Fail(driver, strConfig, "Reinsured Switched ON", 
							"'Insured' Text Field Label does not change to 'Original Insured'");
				}
				
				//Verify Reinsured Text Field becomes Visible and Enabled
				CommonLib.WaitForElementToBeClickable(driver, strConfig,
						"TXT_RiskOverview_Reinsured");
				
				String[] arrReinsured  = CommonLib.getLocatorDetails_From_ObjectRepository(driver, strConfig,
						"TXT_RiskOverview_Reinsured");
				Boolean reinsuredVisible = CommonLib.isElementEnabled(driver, strConfig,arrReinsured[1] );
				
				if(reinsuredVisible) {
					TestReporter.Pass(driver, strConfig, "Reinsured Switched ON", 
							"'Reinsured' Text Field is visible");
				}
				else {
					TestReporter.Fail(driver, strConfig, "Reinsured Switched ON", 
							"'Reinsured' Text Field not visible");
				}
				
				
			} catch (NoSuchElementException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (StaleElementReferenceException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (JavascriptException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (TimeoutException e) {
				TestReporter.Fail(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (NullPointerException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (IllegalArgumentException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			}

			catch (ArrayIndexOutOfBoundsException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			}
			
		}
		
		
		public static void verify_DirectInsured_SwitchedOFF_When_ReinsuredIs_SwitchedON(RemoteWebDriver driver,
				String strConfig) {

			try {

				CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_RiskDashboard_NewRisk");
				Project.ExecuteRow(driver, strConfig, "BTN_RiskDashboard_NewRisk", "Click");
				CommonLib.waitForLoad(driver, strConfig);
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_MenuItem_RiskOverview");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "DDO_RiskOverview_Division");

				// First Switch ON Direct Insured as a Prerequisite
				CommonLib.WaitForElementToBeClickable(driver, strConfig,
						"CHK_RiskOverview_DirectInsured_Placement_ONOFF_Switch");

				Project.ExecuteRow(driver, strConfig, "CHK_RiskOverview_DirectInsured_Placement_ONOFF_Switch", "Click");

				// Now Switch ON Reinsured Field
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "CHK_RiskOverview_Reinsurance_ONOFF_Switch");

				Project.ExecuteRow(driver, strConfig, "CHK_RiskOverview_Reinsurance_ONOFF_Switch", "Click");

				// Verify Direct Insured is unchecked or switched OFF
				CommonLib.WaitForElementToBeClickable(driver, strConfig,
						"CHK_RiskOverview_DirectInsured_Placement_ONOFF_Switch");

				String[] arrDirectInsured = CommonLib.getLocatorDetails_From_ObjectRepository(driver, strConfig,
						"CHK_RiskOverview_DirectInsured_Placement_ONOFF_Switch");

				WebElement directInsuredEle = driver.findElement(By.xpath(arrDirectInsured[1]));
				Boolean directInsuredSelected = directInsuredEle.isSelected();

				if (!directInsuredSelected) {
					TestReporter.Pass(driver, strConfig, "Direct Insured is Switched OFF",
							"After 'Reinsured' is switched ON");
				} else {
					TestReporter.Fail(driver, strConfig, "Direct Insured is not Switched OFF",
							"After 'Reinsured' is switched ON");
				}

			} catch (NoSuchElementException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (StaleElementReferenceException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (JavascriptException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (TimeoutException e) {
				TestReporter.Fail(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (NullPointerException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (IllegalArgumentException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (ArrayIndexOutOfBoundsException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			}

		}
		
		
		public static void verify_Reinsured_SwitchedOFF_When_DirectInsured_Is_SwitchedON(RemoteWebDriver driver,
				String strConfig) {

			try {

				CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_RiskDashboard_NewRisk");
				Project.ExecuteRow(driver, strConfig, "BTN_RiskDashboard_NewRisk", "Click");
				CommonLib.waitForLoad(driver, strConfig);
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_MenuItem_RiskOverview");
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "DDO_RiskOverview_Division");

				// First Switch ON Reinsured as a Prerequisite
				CommonLib.WaitForElementToBeClickable(driver, strConfig, "CHK_RiskOverview_Reinsurance_ONOFF_Switch");

				Project.ExecuteRow(driver, strConfig, "CHK_RiskOverview_Reinsurance_ONOFF_Switch", "Click");
				

				// Now Switch ON Direct Insured Field
				CommonLib.WaitForElementToBeClickable(driver, strConfig,
						"CHK_RiskOverview_DirectInsured_Placement_ONOFF_Switch");

				Project.ExecuteRow(driver, strConfig, "CHK_RiskOverview_DirectInsured_Placement_ONOFF_Switch", "Click");

				// Verify Reinsured is unchecked or Switched OFF
				CommonLib.WaitForElementToBeClickable(driver, strConfig,
						"CHK_RiskOverview_Reinsurance_ONOFF_Switch");

				String[] arrReinsured = CommonLib.getLocatorDetails_From_ObjectRepository(driver, strConfig,
						"CHK_RiskOverview_Reinsurance_ONOFF_Switch");

				WebElement reinsuredEle = driver.findElement(By.xpath(arrReinsured[1]));
				Boolean reinsuredSelected = reinsuredEle.isSelected();

				if (!reinsuredSelected) {
					TestReporter.Pass(driver, strConfig, "Reinsured is Switched OFF",
							"After 'Direct Insured' is switched ON");
				} else {
					TestReporter.Fail(driver, strConfig, "Reinsured is not Switched OFF",
							"After 'Direct Insured' is switched ON");
				}

			} catch (NoSuchElementException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (StaleElementReferenceException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (JavascriptException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (TimeoutException e) {
				TestReporter.Fail(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (NullPointerException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (IllegalArgumentException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (ArrayIndexOutOfBoundsException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			}

		}
		
		
		
		public static void verify_InsuredField_PopulatedWith_ClientName_When_DirectInsured_SwitchedON(RemoteWebDriver driver,
				String strConfig, String strClientName, String strClientACNo) {

			try {

				if (strClientName != null && !(strClientName.trim().isEmpty())) {

					if (strClientACNo != null && !(strClientACNo.trim().isEmpty())) {

						CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_RiskDashboard_NewRisk");
						Project.ExecuteRow(driver, strConfig, "BTN_RiskDashboard_NewRisk", "Click");
						CommonLib.waitForLoad(driver, strConfig);
						CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_MenuItem_RiskOverview");
						CommonLib.WaitForElementToBeClickable(driver, strConfig, "DDO_RiskOverview_Division");

						// AGENT/CLIENT
						// ---- Enter Agent/Client Name in the Text Input Field [DATA FROM EXCEL]

						CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskOverview_AgentOrClient");
						Project.ExecuteRow(driver, strConfig, "TXT_RiskOverview_AgentOrClient", strClientName);

						// ---- Wait for the Agent/Client Name and Account No Combination Options to
						// display [DATA FROM EXCEL]
						String[] STO_RiskOverview_AgentOrClient = ObjectRepository
								.GetObjectlocators("STO_RiskOverview_AgentOrClient");
						CommonLib.Delay();
						CommonLib.WaitForElementToBeClickable(driver, strConfig,
								String.format(STO_RiskOverview_AgentOrClient[1], strClientName, strClientACNo));

						// ---- Click on the desired option for Agent/Client Name and Account No
						// Combination [DATA FROM EXCEL]
						WebElement agentOrClient_SearchTxt_Output = driver.findElement(By
								.xpath(String.format(STO_RiskOverview_AgentOrClient[1], strClientName, strClientACNo)));

						common.JSClick(driver, strConfig, agentOrClient_SearchTxt_Output);

						// Now Switch ON Direct Insured Field
						CommonLib.WaitForElementToBeClickable(driver, strConfig,
								"CHK_RiskOverview_DirectInsured_Placement_ONOFF_Switch");

						Project.ExecuteRow(driver, strConfig, "CHK_RiskOverview_DirectInsured_Placement_ONOFF_Switch",
								"Click");

						// Verify Insured Field populated with the Client Name
						CommonLib.WaitForElementToBeClickable(driver, strConfig,
								"TXT_RiskOverview_Insured_Or_DirectInsured_Or_OriginalInsured");

						String[] arrInsured = CommonLib.getLocatorDetails_From_ObjectRepository(driver, strConfig,
								"TXT_RiskOverview_Insured_Or_DirectInsured_Or_OriginalInsured");

						WebElement insuredEle = driver.findElement(By.xpath(arrInsured[1]));
						String insuredValue = insuredEle.getAttribute("value");

						if (insuredValue.contentEquals(strClientName)) {
							TestReporter.Pass(driver, strConfig, "Insured Value is populated with Client Name",
									"After 'Direct Insured' is switched ON");
						} else {
							TestReporter.Pass(driver, strConfig, "Insured Value is not populated with Client Name",
									"After 'Direct Insured' is switched ON");
						}

					} else {

						CommonLib.getLogger(strConfig).info(
								"INFO: Client Account Number not provided in 'RiskOverviewRegression' sheet of RTTestData.xlsx workbook");
						TestReporter.Warning(driver, strConfig, "Client Account Number",
								"Not provided in 'RiskOverviewRegression' sheet of RTTestData.xlsx workbook");
					}

				} else {

					CommonLib.getLogger(strConfig).info(
							"INFO: Client Name not provided in 'RiskOverviewRegression' sheet of RTTestData.xlsx workbook");
					TestReporter.Warning(driver, strConfig, "Client Name",
							"Not provided in 'RiskOverviewRegression' sheet of RTTestData.xlsx workbook");
				}

			} catch (NoSuchElementException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (StaleElementReferenceException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (JavascriptException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (TimeoutException e) {
				TestReporter.Fail(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (NullPointerException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (IllegalArgumentException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (ArrayIndexOutOfBoundsException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			}

		}
		
		
		public static void verify_AmmendedInsured_FieldValue_isRetained_When_DirectInsured_SwitchedOFF(RemoteWebDriver driver,
				String strConfig, String strClientName, String strClientACNo) {

			try {

				if (strClientName != null && !(strClientName.trim().isEmpty())) {

					if (strClientACNo != null && !(strClientACNo.trim().isEmpty())) {

						CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_RiskDashboard_NewRisk");
						Project.ExecuteRow(driver, strConfig, "BTN_RiskDashboard_NewRisk", "Click");
						CommonLib.waitForLoad(driver, strConfig);
						CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_MenuItem_RiskOverview");
						CommonLib.WaitForElementToBeClickable(driver, strConfig, "DDO_RiskOverview_Division");

						// AGENT/CLIENT
						// ---- Enter Agent/Client Name in the Text Input Field [DATA FROM EXCEL]

						CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskOverview_AgentOrClient");
						Project.ExecuteRow(driver, strConfig, "TXT_RiskOverview_AgentOrClient", strClientName);

						// ---- Wait for the Agent/Client Name and Account No Combination Options to
						// display [DATA FROM EXCEL]
						String[] STO_RiskOverview_AgentOrClient = ObjectRepository
								.GetObjectlocators("STO_RiskOverview_AgentOrClient");
						CommonLib.Delay();
						CommonLib.WaitForElementToBeClickable(driver, strConfig,
								String.format(STO_RiskOverview_AgentOrClient[1], strClientName, strClientACNo));

						// ---- Click on the desired option for Agent/Client Name and Account No
						// Combination [DATA FROM EXCEL]
						WebElement agentOrClient_SearchTxt_Output = driver.findElement(By
								.xpath(String.format(STO_RiskOverview_AgentOrClient[1], strClientName, strClientACNo)));

						common.JSClick(driver, strConfig, agentOrClient_SearchTxt_Output);

						// Now Switch ON Direct Insured Field
						CommonLib.WaitForElementToBeClickable(driver, strConfig,
								"CHK_RiskOverview_DirectInsured_Placement_ONOFF_Switch");

						Project.ExecuteRow(driver, strConfig, "CHK_RiskOverview_DirectInsured_Placement_ONOFF_Switch",
								"Click");

						// Verify Insured Field populated with the Client Name
						CommonLib.WaitForElementToBeClickable(driver, strConfig,
								"TXT_RiskOverview_Insured_Or_DirectInsured_Or_OriginalInsured");

						String[] arrInsured = CommonLib.getLocatorDetails_From_ObjectRepository(driver, strConfig,
								"TXT_RiskOverview_Insured_Or_DirectInsured_Or_OriginalInsured");

						WebElement insuredEle = driver.findElement(By.xpath(arrInsured[1]));
						String insuredValue = insuredEle.getAttribute("value");

						if (insuredValue.contentEquals(strClientName)) {
							TestReporter.Pass(driver, strConfig, "Insured Value is equal to Client Name",
									"After 'Direct Insured' is switched ON");
						} else {
							TestReporter.Pass(driver, strConfig, "Insured Value is not equal to Client Name",
									"After 'Direct Insured' is switched ON");
						}

						// Amend the Client Name
						Project.ExecuteRow(driver, strConfig,
								"TXT_RiskOverview_Insured_Or_DirectInsured_Or_OriginalInsured",
								strClientName.concat("_Amended"));

						// Now Switch OFF Direct Insured Field
						CommonLib.WaitForElementToBeClickable(driver, strConfig,
								"CHK_RiskOverview_DirectInsured_Placement_ONOFF_Switch");

						Project.ExecuteRow(driver, strConfig, "CHK_RiskOverview_DirectInsured_Placement_ONOFF_Switch",
								"Click");

						// Verify Amended Insured Field value is retained
						CommonLib.WaitForElementToBeClickable(driver, strConfig,
								"TXT_RiskOverview_Insured_Or_DirectInsured_Or_OriginalInsured");

						String[] arrInsuredAmended = CommonLib.getLocatorDetails_From_ObjectRepository(driver,
								strConfig, "TXT_RiskOverview_Insured_Or_DirectInsured_Or_OriginalInsured");

						WebElement insuredAmmendedEle = driver.findElement(By.xpath(arrInsuredAmended[1]));
						String insuredAmended_Value = insuredAmmendedEle.getAttribute("value");

						if (insuredAmended_Value.contentEquals(strClientName.concat("_Amended"))) {
							TestReporter.Pass(driver, strConfig, "Amended Insured Value is retained",
									"After 'Direct Insured' is switched OFF");
						} else {
							TestReporter.Fail(driver, strConfig, "Amended Insured Value is not retained",
									"After 'Direct Insured' is switched OFF");
						}

					} else {

						CommonLib.getLogger(strConfig).info(
								"INFO: Client Account Number not provided in 'RiskOverviewRegression' sheet of RTTestData.xlsx workbook");
						TestReporter.Warning(driver, strConfig, "Client Account Number",
								"Not provided in 'RiskOverviewRegression' sheet of RTTestData.xlsx workbook");
					}

				} else {

					CommonLib.getLogger(strConfig).info(
							"INFO: Client Name not provided in 'RiskOverviewRegression' sheet of RTTestData.xlsx workbook");
					TestReporter.Warning(driver, strConfig, "Client Name",
							"Not provided in 'RiskOverviewRegression' sheet of RTTestData.xlsx workbook");
				}
			} catch (NoSuchElementException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (StaleElementReferenceException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (JavascriptException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (TimeoutException e) {
				TestReporter.Fail(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (NullPointerException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (IllegalArgumentException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (ArrayIndexOutOfBoundsException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			}

		}
		
		public static void verify_TootipAddress_UponHover_On_ClientFieldOption(RemoteWebDriver driver, 
				String strConfig, String strClient, String strClientACNo, String strClientAdresss) {

			try {

				if (!(strClient.isEmpty()) || strClient != null ) {

					if (!(strClientACNo.isEmpty()) || strClientACNo != null ) {

						if ( !(strClientAdresss.isEmpty()) || strClientAdresss != null) {

							CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_RiskDashboard_NewRisk");
							Project.ExecuteRow(driver, strConfig, "BTN_RiskDashboard_NewRisk", "Click");
							CommonLib.waitForLoad(driver, strConfig);
							CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_MenuItem_RiskOverview");
							CommonLib.WaitForElementToBeClickable(driver, strConfig, "DDO_RiskOverview_Division");

							// AGENT/CLIENT
							// ---- Enter Agent/Client Name in the Text Input Field [DATA FROM EXCEL]

							CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskOverview_AgentOrClient");
							Project.ExecuteRow(driver, strConfig, "TXT_RiskOverview_AgentOrClient", strClient);

							// ---- Wait for the Agent/Client Name and Account No Combination Options to
							// display [DATA FROM EXCEL]
							String[] STO_RiskOverview_AgentOrClient = ObjectRepository
									.GetObjectlocators("STO_RiskOverview_AgentOrClient");
							CommonLib.Delay();
							CommonLib.WaitForElementToBeClickable(driver, strConfig,
									String.format(STO_RiskOverview_AgentOrClient[1], strClient, strClientACNo));

							// Hover Mouse over the Client Option displayed
							WebElement clientOptionEle = driver
									.findElement(By.xpath(String.format(STO_RiskOverview_AgentOrClient[1], strClient, strClientACNo)));
							
							CommonLib.Delay();
							//new Actions(driver).moveToElement(clientOptionEle).build().perform();
							
							
							String javaScript = "var evObj = document.createEvent('MouseEvents');"
									+ "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
									+ "arguments[0].dispatchEvent(evObj);";
							((JavascriptExecutor) driver).executeScript(javaScript,
									clientOptionEle);
							
							//This method gives Stale Element Exception
							//CommonLib.hoverCursor_OverElement(driver, strConfig,clientOptionEle);
			
							// Verify Client Address is displayed upon hover
							String[] arrClient_Address = ObjectRepository
									.GetObjectlocators("NAV_RiskOverview_AgentOrClient_Tooltip_Address");

							CommonLib.WaitForElementToBeVisible(driver, strConfig,
									String.format(arrClient_Address[1], strClientAdresss));

							Boolean clientAddress_VisibleUpon_Hover = CommonLib.isElementVisible(driver, strConfig,
									String.format(arrClient_Address[1], strClientAdresss));

							if (clientAddress_VisibleUpon_Hover) {
								TestReporter.Pass(driver, strConfig, "Client Address",
										"Visible upon hover over the Client Option");
							} else {
								TestReporter.Fail(driver, strConfig, "Client Address",
										"Not visible upon hover over the Client Option");
							}
						} else {
							TestReporter.Fail(driver, strConfig, "Client Address",
									"Is Null or Empty(Not supplied) in RiskOverviewRegrssion Sheet of RTTestdata.xlsx file");
						}

					} else {
						TestReporter.Fail(driver, strConfig, "Client Account No",
								"Is Null or Empty(Not supplied) in RiskOverviewRegrssion Sheet of RTTestdata.xlsx file");
					}

				} else {
					TestReporter.Fail(driver, strConfig, "Client Name",
							"Is Null or Empty(Not supplied) in RiskOverviewRegrssion Sheet of RTTestdata.xlsx file");
				}

			} catch (NoSuchElementException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (StaleElementReferenceException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (JavascriptException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (TimeoutException e) {
				TestReporter.Fail(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (NullPointerException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (IllegalArgumentException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			}

			catch (ArrayIndexOutOfBoundsException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			}

		}
		
		
		
		
		public static String verify_WarningMsg_AfterSaving_UnapprovedClient_OnRiskOverviewTab(RemoteWebDriver driver, String strConfig,  ArrayList<String> riskOverviewData) {
			String riskTrackerRefNum = null;
			try {

				CommonLib.getLogger(strConfig).info("Inside createNewRisk");

				if (riskOverviewData.get(2) != null && !(riskOverviewData.get(2).trim().isEmpty())) {

					if (riskOverviewData.get(3) != null && !(riskOverviewData.get(3).trim().isEmpty())) {

						if (riskOverviewData.get(11) != null && !(riskOverviewData.get(12).trim().isEmpty())) {
							
							if (riskOverviewData.get(12) != null && !(riskOverviewData.get(13).trim().isEmpty())) {
								
								if (riskOverviewData.get(13) != null && !(riskOverviewData.get(13).trim().isEmpty())) {
									
									if (riskOverviewData.get(14) != null && !(riskOverviewData.get(13).trim().isEmpty())) {
								
							CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_RiskDashboard_NewRisk");
							Project.ExecuteRow(driver, strConfig, "BTN_RiskDashboard_NewRisk", "Click");
							CommonLib.waitForLoad(driver, strConfig);
							CommonLib.WaitForElementToBeClickable(driver, strConfig,
									"LN_MenuItem_RiskOverview");
							CommonLib.WaitForElementToBeClickable(driver, strConfig, "DDO_RiskOverview_Division");

							// NEW/RENEWALCLASSIFICATION
							// ---- Select New/Renewal Classification Option from the Dropdown
							if (riskOverviewData.get(1) != null && !(riskOverviewData.get(1).trim().isEmpty())) {
								CommonLib.WaitForElementToBeClickable(driver, strConfig,
										"DDO_RiskOverview_NewOrRenewal_Classification");
								Project.ExecuteRow(driver, strConfig, "DDO_RiskOverview_NewOrRenewal_Classification",
										"Click");
								CommonLib.WaitForElementToBeClickable(driver, strConfig, "DTI_RiskOverview_NewOrRenewal_Classification");
								Project.ExecuteRow(driver, strConfig, "DTI_RiskOverview_NewOrRenewal_Classification",
										riskOverviewData.get(1));
								
							}
					
							// DIVISION
							// ---- Select Division Option from the Dropdown
							if (riskOverviewData.get(2) != null && !(riskOverviewData.get(2).trim().isEmpty())) {
								CommonLib.WaitForElementToBeClickable(driver, strConfig, "DDO_RiskOverview_Division");
								Project.ExecuteRow(driver, strConfig, "DDO_RiskOverview_Division", "Click");

								CommonLib.WaitForElementToBeClickable(driver, strConfig, "DTI_RiskOverview_Division");
								Project.ExecuteRow(driver, strConfig, "DTI_RiskOverview_Division",
										riskOverviewData.get(2));
							} else {
								CommonLib.getLogger(strConfig).info(
										"info: No Division supplied in RTTestdata.xlsx file. Default Division value to be used");
								TestReporter.Info(driver, strConfig, "Division",
										"Not supplied in RTTestdata.xlsx file.Default Division value to be used");
							}

							// AGENT/CLIENT
							// ---- Enter Agent/Client Name in the Text Input Field [DATA FROM EXCEL]

							CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskOverview_AgentOrClient");
							Project.ExecuteRow(driver, strConfig, "TXT_RiskOverview_AgentOrClient",
									riskOverviewData.get(3));

							

							// DIRECT INSURED PLACEMENT OR REINSURANCE
							if ((riskOverviewData.get(6) == null || riskOverviewData.get(6).trim().isEmpty()
									|| riskOverviewData.get(6).trim().contentEquals("No"))
									&& (riskOverviewData.get(8) == null || riskOverviewData.get(8).trim().isEmpty()
											|| riskOverviewData.get(8).trim().contentEquals("No"))) {

								if (riskOverviewData.get(5) != null && !(riskOverviewData.get(5).trim().isEmpty())) {
									CommonLib.WaitForElementToBeClickable(driver, strConfig,
											"TXT_RiskOverview_Insured_Or_DirectInsured_Or_OriginalInsured");
									Project.ExecuteRow(driver, strConfig,
											"TXT_RiskOverview_Insured_Or_DirectInsured_Or_OriginalInsured",
											riskOverviewData.get(5));
								}
							}

							else if (riskOverviewData.get(6) != null && !(riskOverviewData.get(6).trim().isEmpty())
									&& riskOverviewData.get(6).trim().contentEquals("Yes")) {
								CommonLib.WaitForElementToBeClickable(driver, strConfig,
										"CHK_RiskOverview_DirectInsured_Placement_ONOFF_Switch");
								Project.ExecuteRow(driver, strConfig,
										"CHK_RiskOverview_DirectInsured_Placement_ONOFF_Switch", "Click");

								// This Logic needs to be clarified from BA or Dev
								// Since this is already populated when Direct Insured is Switched ON and the
								// value cannot be verified with Agent/Client entered previously
								if (riskOverviewData.get(7) != null && !(riskOverviewData.get(7).trim().isEmpty())) {
									CommonLib.WaitForElementToBeClickable(driver, strConfig,
											"TXT_RiskOverview_Insured_Or_DirectInsured_Or_OriginalInsured");
									Project.ExecuteRow(driver, strConfig,
											"TXT_RiskOverview_Insured_Or_DirectInsured_Or_OriginalInsured",
											riskOverviewData.get(6));
								}
							}

							else if (riskOverviewData.get(8) != null && !(riskOverviewData.get(8).trim().isEmpty())
									&& riskOverviewData.get(8).trim().contentEquals("Yes")) {
								CommonLib.WaitForElementToBeClickable(driver, strConfig,
										"CHK_RiskOverview_Reinsurance_ONOFF_Switch");
								Project.ExecuteRow(driver, strConfig, "CHK_RiskOverview_Reinsurance_ONOFF_Switch", "Click");
								// This Logic needs to be clarified from BA or Dev
								if (riskOverviewData.get(9) != null && !(riskOverviewData.get(9).trim().isEmpty())) {
									CommonLib.WaitForElementToBeClickable(driver, strConfig,
											"TXT_RiskOverview_Insured_Or_DirectInsured_Or_OriginalInsured");
									Project.ExecuteRow(driver, strConfig,
											"TXT_RiskOverview_Insured_Or_DirectInsured_Or_OriginalInsured",
											riskOverviewData.get(9));
								}

								// This Logic needs to be clarified from BA or Dev
								if (riskOverviewData.get(10) != null && !(riskOverviewData.get(10).trim().isEmpty())) {
									CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskOverview_Reinsured");
									Project.ExecuteRow(driver, strConfig, "TXT_RiskOverview_Reinsured",
											riskOverviewData.get(9));
								}

							}
							// ASSURED - (Logic will change as there is a condition to enter Assured...
							// Currently it is kept as Mandatory)
							CommonLib.WaitForElementToBeClickable(driver, strConfig, "TXT_RiskOverview_Assured");
							Project.ExecuteRow(driver, strConfig, "TXT_RiskOverview_Assured", riskOverviewData.get(11));

							// PCP-Select PCP Option from the Dropdown

							Project.ExecuteRow(driver, strConfig, "DDO_RiskOverview_PCP", "Click");

							CommonLib.WaitForElementToBeClickable(driver, strConfig, "DTI_RiskOverview_PCP");
							// ---- Select PCP Option from the Dropdown [DATA FROM EXCEL]
							Project.ExecuteRow(driver, strConfig, "DTI_RiskOverview_PCP",
									riskOverviewData.get(14));
							
							// ACCOUNT EXECUTIVE
							// ---- Select Account Executive Option from the Dropdown

							Project.ExecuteRow(driver, strConfig, "DDO_RiskOverview_AccountExecutive", "Click");

							CommonLib.WaitForElementToBeClickable(driver, strConfig, "DTI_RiskOverview_AccountExecutive");
							// ---- Select Account Executive Option from the Dropdown [DATA FROM EXCEL]
							Project.ExecuteRow(driver, strConfig, "DTI_RiskOverview_AccountExecutive",
									riskOverviewData.get(12));

							// PLACING BROKER
							// ---- Select Placing Broker Option from the Dropdown
							CommonLib.WaitForElementToBeClickable(driver, strConfig, "DDO_RiskOverview_PlacingBroker");
							Project.ExecuteRow(driver, strConfig, "DDO_RiskOverview_PlacingBroker", "Click");
							CommonLib.WaitForElementToBeClickable(driver, strConfig, "DTI_RiskOverview_PlacingBroker");
							// ---- Select Account Executive Option from the Dropdown [DATA FROM EXCEL]
							Project.ExecuteRow(driver, strConfig, "DTI_RiskOverview_PlacingBroker",
									riskOverviewData.get(13));

							// SAVE BUTTON
							CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Save");
							Project.ExecuteRow(driver, strConfig, "BTN_Save", "Click");
							
							

							//Wait for Unapproved Client Dialog
							CommonLib.WaitForElementToBeVisible(driver, strConfig, "LBL_RiskOverview_UnapprovedClient_SaveErr_Msg");
							
							String[] arrUnapproved_ClientDialog_Msg = ObjectRepository
									.GetObjectlocators("LBL_RiskOverview_UnapprovedClient_SaveErr_Msg");
							Boolean UnapprovedClient_WarningMessage_Visible = CommonLib.isElementVisible(driver, strConfig,
									arrUnapproved_ClientDialog_Msg[1]);

							if (UnapprovedClient_WarningMessage_Visible) {
								TestReporter.Pass(driver, strConfig, "Unapproved Client Saved Warning Message",
										"Displayed upon saving Risk Details for an Unapproved Client in Risk Overview tab");
							} else {
								TestReporter.Fail(driver, strConfig, "Unapproved Client Saved Warning Message",
										"Not displayed upon saving Risk Details for an Unapproved Client in Risk Overview tab");
							}

							
							} else {
								CommonLib.getLogger(strConfig).info(
										"INFO: No PCP(MANDATORY FIELD) supplied in RTTestdata.xlsx file. Cannot proceed with creating a New Risk");
								TestReporter.Info(driver, strConfig, "PCP(MANDATORY FIELD)",
										"Not supplied in RTTestdata.xlsx file.Cannot proceed with creating a New Risk");
							}
						
						} else {
							CommonLib.getLogger(strConfig).info(
									"INFO: No Placing Broker(MANDATORY FIELD) supplied in RTTestdata.xlsx file. Cannot proceed with creating a New Risk");
							TestReporter.Info(driver, strConfig, "Placing Broker(MANDATORY FIELD)",
									"Not supplied in RTTestdata.xlsx file.Cannot proceed with creating a New Risk");
						}
					} else {
						CommonLib.getLogger(strConfig).info(
								"INFO: No Account Executive(MANDATORY FIELD) supplied in RTTestdata.xlsx file. Cannot proceed with creating a New Risk");
						TestReporter.Info(driver, strConfig, "Account Executive(MANDATORY FIELD)",
								"Not supplied in RTTestdata.xlsx file.Cannot proceed with creating a New Risk");
					}

				} else {
					CommonLib.getLogger(strConfig).info(
							"INFO: No Assured(MANDATORY FIELD) supplied in RTTestdata.xlsx file. Cannot proceed with creating a New Risk");
					TestReporter.Info(driver, strConfig, "Assured(MANDATORY FIELD)",
							"Not supplied in RTTestdata.xlsx file.Cannot proceed with creating a New Risk");
				}
						
					} else {

						CommonLib.getLogger(strConfig).info(
								"INFO: No Agent/Client(MANDATORY FIELD) supplied in RTTestdata.xlsx file. Cannot proceed with creating a New Risk");
						TestReporter.Info(driver, strConfig, "Agent/Client(MANDATORY FIELD)",
								"Not supplied in RTTestdata.xlsx file.Cannot proceed with creating a New Risk");
					}
				} else {
					CommonLib.getLogger(strConfig).info(
							"INFO: No Division (MANDATORY FIELD) supplied in RTTestdata.xlsx file. Cannot proceed with creating a New Risk");
					TestReporter.Info(driver, strConfig, "Division(MANDATORY FIELD)",
							"Not supplied in RTTestdata.xlsx file.Cannot proceed with creating a New Risk");
				}

			
		
		


			} catch (NoSuchElementException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (StaleElementReferenceException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (JavascriptException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (TimeoutException e) {
				TestReporter.Fail(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (NullPointerException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			} catch (IllegalArgumentException e) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

			}

			return riskTrackerRefNum;

		}
		
		
		public static void logoutRiskTracker(RemoteWebDriver driver, String strConfig) {
		CommonLib.WaitForElementToBeVisible(driver, strConfig, "LN_Logout_IntegroAccount");	
		CommonLib.WaitForElementToBeClickable(driver, strConfig, "LN_Logout_IntegroAccount");
		CommonLib.getLogger(strConfig).info("INFO: Logging Out of Risk Tracker");
		TestReporter.Info(driver, strConfig, "LogOut Operation : ",
				" Logging Out of Risk Tracker");
		WebElement logOutEle =  repository.GetObject(driver, strConfig, "LN_Logout_IntegroAccount");
		common.JSClick(driver, strConfig, logOutEle);
		CommonLib.WaitForElementToBeClickable(driver, strConfig, "BTN_Login_IntegroAccount");
		
		CommonLib.getLogger(strConfig).info("INFO: Logging Out of Risk Tracker");
		TestReporter.Pass(driver, strConfig, "LogOut RiskTracker ",
				"Succesfully logged Out of Risk Tracker");

	}
	



}
	
	
	
	
