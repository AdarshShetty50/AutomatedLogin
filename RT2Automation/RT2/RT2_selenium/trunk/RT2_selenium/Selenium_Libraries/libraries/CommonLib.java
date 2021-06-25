package libraries;

import java.io.BufferedReader;
import java.io.File;
//import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
//import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;

import javax.swing.JOptionPane;
import org.openqa.selenium.*;
import org.apache.bcel.Repository;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.model.InternalSheet.UnsupportedBOFType;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.server.FirefoxDriverProvider;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.relevantcodes.extentreports.ExtentReports;
import com.sun.jna.Native.ffi_callback;

//import bsh.ParseException;
import net.sourceforge.htmlunit.corejs.javascript.JavaScriptException;
import tests.Test1;
import tests.Test2;
import tests.Test3;
import tests.Test4;
import tests.Test5;

@SuppressWarnings("unused")

public class CommonLib {
	public static List<String> policyref = new ArrayList<String>();
	public static ObjectRepository repository = new ObjectRepository();
	static WebDriverWait wait;
	private long intstartTime;

	public boolean selectDate(RemoteWebDriver driver, String strConfig, String strDDMMYYYY) {
		if (strDDMMYYYY != null && strDDMMYYYY != "") {
			int intDay = Integer.parseInt(strDDMMYYYY.split("/")[0]);
			int intMonth = Integer.parseInt(strDDMMYYYY.split("/")[1]) - 1;
			String stryear = strDDMMYYYY.split("/")[2];

			WebElement ulYear = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select[2]"));
			Select sYear = new Select(ulYear);
			sYear.selectByValue(stryear);

			WebElement ulMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select[1]"));
			Select sMonth = new Select(ulMonth);
			sMonth.selectByValue(String.valueOf(intMonth));

			String xpathDays = ".//*[@id='ui-datepicker-div']/table/tbody/tr/td/a[text() = '" + intDay + "']";
			WebElement lnDay = driver.findElement(By.xpath(xpathDays));
			new Actions(driver).moveToElement(lnDay);
			try {
				driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
				JSClick(driver, strConfig, lnDay);
			} catch (TimeoutException e) {
				TestReporter.Error(driver, strConfig, "Timeout Exception", "Timeout Exception caught in- selectDate()");
				CommonLib.getLogger(strConfig).info("Timeout Exception caught in- selectDate()");
				return false;
			}
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			return true;
		} else {
			return false;
		}
	}

	public void NavigateMenuToSubmenu(RemoteWebDriver driver, String strConfig, String mainMenu, String subMenu) {
		CommonLib.getLogger(strConfig).info("Navigating -> " + mainMenu + " - " + subMenu);
		String[] arrDropDownLocators = ObjectRepository.GetObjectlocators("LN_Menu_DropdownMenu");
		WaitForElementToBeClickable(driver, strConfig, "LN_Menu_DropdownMenu");
		WebElement lnDropdown = driver.findElement(By.xpath(arrDropDownLocators[1]));
		JSClick(driver, strConfig, lnDropdown);

		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.linkText(mainMenu))).perform();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("$('.menus li:contains(" + mainMenu + ") .menus li a:contains(" + subMenu + ")').click();");

		js = null;
		lnDropdown = null;
		arrDropDownLocators = null;
		try {
			Thread.sleep(Constants.intSmallDelay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		CommonLib.waitForPageLoad(driver, strConfig);
	}

	/*
	 * Method Name: Roundoff Description: Round off to 2 decimal places Input:float
	 * Output: double
	 * 
	 */
	public double roundoff(float f) {
		return Math.round(f * 100.0) / 100.0;
	}

	/*
	 * Method Name: StringToFloat Description: Convert String to Float Input:String
	 * Output: Float
	 * 
	 */
	public Float StringToFloat(String s) {
		return Float.parseFloat(s);
	}

	/*
	 * Method Name: Description Input: Output: Developed By:
	 */
	@SuppressWarnings("deprecation")
	public WebDriver ReturnDriver(String strConfig, String BrowserName) {
		// CommonLib.getLogger().info("Inside ReturnDriver");
		WebDriver driver;
		try {
			if (BrowserName.equalsIgnoreCase("Firefox")) {
				System.setProperty("webdriver.gecko.driver",
						ProjectConstants.EXTERNALLIBRARIES + "/geckodriver-v0.17.0-win32.exe");
				driver = new FirefoxDriver();
			} else if (BrowserName.equalsIgnoreCase("Chrome")) {
				System.setProperty("webdriver.chrome.driver",
						ProjectConstants.EXTERNALLIBRARIES + "/chromedriver_v2.9_080617.exe");
				driver = new ChromeDriver();
			} else if (BrowserName.equalsIgnoreCase("IE")) {
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						false);
				capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
				capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
				capabilities.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR,
						UnexpectedAlertBehaviour.IGNORE);
				System.setProperty("webdriver.ie.driver",
						ProjectConstants.EXTERNALLIBRARIES + "/IEDriverServer_Win32_3.4.0.exe");
				driver = new InternetExplorerDriver(capabilities);
			} else {
				driver = new FirefoxDriver();
			}
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

			driver.manage().window().maximize();

			WebElement html = driver.findElement(By.tagName("html"));
			html.sendKeys(Keys.chord(Keys.CONTROL, "0"));
			return driver;
		} catch (WebDriverException e) {
//			TestReporter.Fail(driver, strConfig, "Web Driver Exception", e.getMessage());
			// CommonLib.getLogger().error(e.getMessage());
		}
		return null;
	}

	public static RemoteWebDriver getRemoteDriver(String Browser) throws MalformedURLException, 
	InterruptedException {
		DesiredCapabilities capabilities = getCapabilities_allbrowsers(Browser);
		
		RemoteWebDriver rdriver = new RemoteWebDriver(new URL("http://localhost:5555/wd/hub"), capabilities);

		rdriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		rdriver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
		rdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		rdriver.manage().window().maximize();
		rdriver.manage().deleteAllCookies();
		return rdriver;
	}

	private static DesiredCapabilities getCapabilities(String browser) {
		switch (browser) {
		case "IE":
			DesiredCapabilities IEcapabilities = DesiredCapabilities.internetExplorer();
			IEcapabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
			IEcapabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			IEcapabilities.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
			IEcapabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, Constants.strRTURL);
			IEcapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
					false);
			System.setProperty("webdriver.ie.driver",
					ProjectConstants.EXTERNALLIBRARIES + "/IEDriverServer_Win32_3.4.0.exe");

			return IEcapabilities;
		case "Chrome":
			System.out.println("chrome");
			ChromeOptions options = new ChromeOptions();
			options.setBinary(ProjectConstants.EXTERNALLIBRARIES + "/msedgedriver.exe");
			options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
			ChromeDriverService chrome = ChromeDriverService.createDefaultService();
			DesiredCapabilities CRCapabilities = DesiredCapabilities.edge();
			System.setProperty("webdriver.edge.driver", ProjectConstants.EXTERNALLIBRARIES + "/msedgedriver.exe");
			CRCapabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
			CRCapabilities.setCapability("chrome.switches", Arrays.asList("--incognito"));
			CRCapabilities.setBrowserName("chrome");
			CRCapabilities.setCapability("options", options);
			CRCapabilities.setCapability("chromedriverservice", chrome);
			CRCapabilities.merge((Capabilities) options);
			return CRCapabilities;
		case "Firefox":
			System.out.println("Setting up " + browser);
			@SuppressWarnings("static-access")
			DesiredCapabilities FFCapabilities = new DesiredCapabilities().firefox();
			FirefoxProfile profile = new FirefoxProfile();
			File f = new File("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
			profile.deleteExtensionsCacheIfItExists(f);
			FirefoxBinary firefoxBinary = new FirefoxBinary();
			System.setProperty("webdriver.gecko.driver", ProjectConstants.EXTERNALLIBRARIES + "/geckodriver.exe");
			return FFCapabilities;
		default:
			return null;
		}
	}

	private static DesiredCapabilities getCapabilities_allbrowsers(String browser) {
		switch (browser) {
		
		case "Chrome":
			ChromeOptions optionsc = new ChromeOptions();
			optionsc.addArguments("--incognito");
			optionsc.addArguments("--ignore-certificate-errors");
			optionsc.addArguments("disable-infobars");
			optionsc.setExperimentalOption("useAutomationExtension", false);
			optionsc.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

			// optionsc.setPageLoadStrategy
			DesiredCapabilities CRCapabilities = DesiredCapabilities.chrome();

			// optionsc.setExperimentalOption("prefs", chromePrefs);
			System.setProperty("webdriver.chrome.driver", ProjectConstants.EXTERNALLIBRARIES + "/chromedriver.exe");

			CRCapabilities.setPlatform(Platform.WIN10);

			CRCapabilities.setCapability(ChromeOptions.CAPABILITY, optionsc);
			CRCapabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);

			CRCapabilities.setCapability("applicationCacheEnabled", false);
			CRCapabilities.setCapability("seleniumProtocol", "WebDriver");
			CRCapabilities.acceptInsecureCerts();
			CRCapabilities.setAcceptInsecureCerts(true);
			CRCapabilities.setJavascriptEnabled(true);
			CRCapabilities.setCapability("javascript.enabled", true);
			CRCapabilities.setCapability("pageLoadStrategy", "normal");
			CRCapabilities.setCapability("maxInstances", 5);
			System.out.println(CRCapabilities);
			return CRCapabilities;
			
		case "IE":
			DesiredCapabilities IEcapabilities = DesiredCapabilities.internetExplorer();
			IEcapabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
			IEcapabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			IEcapabilities.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
			IEcapabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, Constants.strRTURL);
			IEcapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
					false);
			System.setProperty("webdriver.ie.driver",
					ProjectConstants.EXTERNALLIBRARIES + "/IEDriverServer_Win32_3.4.0.exe");

			return IEcapabilities;
		case "Firefox":
			System.out.println("Setting up " + browser);
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference(FirefoxProfile.ALLOWED_HOSTS_PREFERENCE, "localhost");
			FirefoxBinary firefoxBinary = new FirefoxBinary();
			/// firefoxBinary.addCommandLineOptions("--headless");
			DesiredCapabilities FFCapabilities = DesiredCapabilities.firefox();
			FirefoxOptions op = new FirefoxOptions();
			op.setBinary(firefoxBinary);
			op.setProfile(profile);
			op.setLogLevel(Level.INFO);
			op.addPreference("javascript.enabled", true);
			Capabilities options;
			options = op.toCapabilities();
			// FFCapabilities.setCapability("moz:webdriverClick", true);
			FFCapabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, true);
			FFCapabilities.setCapability("browserName", "firefox");
			// FFCapabilities.setCapability("setWindowRect", true);

			FFCapabilities.setPlatform(Platform.WIN10);
			FFCapabilities.setCapability("seleniumProtocol", "WebDriver");
			// FFCapabilities.setCapability("version", "80.0.3987.149");
			FFCapabilities.setCapability("maxInstances", 5);
			FFCapabilities.merge(options);
			System.setProperty("webdriver.gecko.driver", ProjectConstants.EXTERNALLIBRARIES + "/geckodriver.exe");
			return FFCapabilities;
		default:
			return null;
		}
	}

	/*
	 * Method Name: JSClick Description: NavigateToMenuAndSubMenu using linkText
	 * Input: Output: Developed By: Dayanand Dhange
	 */

	void hoverAndClick(RemoteWebDriver driver, String strConfig, WebElement hoverer, List<WebElement> element) {
		// declare new Action
		Actions actions = new Actions(driver);
		// Iterate through the WebElements from the Array

		// hover each of them
		try {

			TestReporter.Info(driver, strConfig, "Hovering to", hoverer.getText() + " menu and clicking on " + element);
			for (int i = 0; i < element.size(); i++) {
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				Action hovering = actions.moveToElement(hoverer)
						.moveToElement(driver.findElementById(element.get(i).getAttribute("id").toString())).click()
						.build();
				hovering.perform();
				System.out.println(driver.getWindowHandles());

				// driver.get("allans4.sg-host.com/");
				CommonLib.waitForPageLoad(driver, strConfig);
				TestReporter.Pass(driver, strConfig, "Hovering and clicking sucessfully completed on ",
						hoverer.getText() + " menu and " + element);

			}
		} catch (Exception e) {
			TestReporter.Fail(driver, strConfig, "Failed due to", e.toString());
			e.printStackTrace();
		}

	}

	/*
	 * Method Name: JSClick Description: Click operation through JavaScript Input:
	 * Output: Developed By: Dayanand Dhange
	 */
	public boolean JSClick(RemoteWebDriver driver, By by) {
		boolean result = false;
		int attempts = 0;
		while (attempts < 3) {
			try {
				WebElement element = driver.findElement(by);
				Capabilities cap = (driver).getCapabilities();
				if (cap.getBrowserName().equalsIgnoreCase("chrome")
						|| cap.getBrowserName().equalsIgnoreCase("firefox")) {

					element.click();
					result = true;
					break;
				} else {
					JavascriptExecutor js = (JavascriptExecutor) driver;

					System.out.println(element.getText());
					new Actions(driver).moveToElement(element).perform();
					js.executeScript("arguments[0].click();", element);
					result = true;
					break;
				}
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}
		return result;
	}

//	public boolean JSClick(RemoteWebDriver driver, String strConfig, String strelementName){
//		try{
//			boolean result = false;
//			int attempts = 0;
//			while(attempts < 3){
//				try{
//					String[] arrObjElement = ObjectRepository.GetObjectlocators(strelementName);
//					By byObjElement = ObjectRepository.GetObject(arrObjElement);
//					WebElement element = driver.findElement(byObjElement);
//					Capabilities cap = (driver).getCapabilities();
//					if(cap.getBrowserName().equalsIgnoreCase("chrome")||cap.getBrowserName().equalsIgnoreCase("firefox"))
//					{
//
//					result=CommonLib.click_On_Buttonheader(driver, strConfig, strelementName);	
//					break;
//					}else {
//					JavascriptExecutor js = (JavascriptExecutor) driver;
//					new Actions(driver).moveToElement(element).perform();
//					js.executeScript("arguments[0].click();",element);
//					result = true;
//					break;}
//				} catch(StaleElementReferenceException e) {}
//				attempts++;
//			}
//			return result;
//		} catch (NoSuchElementException e) {
//			TestReporter.Error(driver, strConfig, "JS Error", "Unable to click on element: " + strelementName);
//			return false;
//		} catch (Exception e) {
//			TestReporter.Error(driver, strConfig, "JS Error", "Unable to click on element: " + strelementName + " - " + e.getClass().getCanonicalName());
//			return false;
//		}
//	}

//	public boolean JSClick(RemoteWebDriver driver, String strConfig, WebElement e){
//		try{
//			boolean result = false;
//			int attempts = 0;
//			while(attempts < 3){
//				try{
//					Capabilities cap = (driver).getCapabilities();
//					if(cap.getBrowserName().equalsIgnoreCase("chrome")||cap.getBrowserName().equalsIgnoreCase("firefox"))
//					{
//						
//						e.click();
//						result=true;
//					break;
//					}
//					else {
//					JavascriptExecutor js = (JavascriptExecutor) driver;
//					new Actions(driver).moveToElement(e).perform();
//					js.executeScript("arguments[0].click();",e);
//					result = true;
//					break;}
//				} catch(StaleElementReferenceException ex){}
//				attempts++;
//			}
//			return result;
//		} catch (NoSuchElementException ex){
//			TestReporter.Error(driver, strConfig, "JS Error", "Unable to click on webelement: " + e.toString());
//			return false;
//		}
//	}

	public boolean JSClick(RemoteWebDriver driver, String strConfig, String strelementName) {
		try {
			boolean result = false;
			int attempts = 0;
			while (attempts < 3) {
				try {
					String[] arrObjElement = ObjectRepository.GetObjectlocators(strelementName);
					By byObjElement = ObjectRepository.GetObject(arrObjElement);
					System.out.println(byObjElement.toString());
					WebElement element = driver.findElement(byObjElement);
					JavascriptExecutor executor = (JavascriptExecutor) driver;
					WebDriverWait wait = new WebDriverWait(driver, 30);
					wait.until(ExpectedConditions.elementToBeClickable(byObjElement));
					driver.executeScript("arguments[0].scrollIntoView(true);", element);
					executor.executeScript("arguments[0].click();", element);
					result = true;
					break;
				} catch (StaleElementReferenceException e) {
				}
				attempts++;
			}
			if (result == false) {
				while (attempts < 3) {
					try {
						String[] arrObjElement = ObjectRepository.GetObjectlocators(strelementName);
						By byObjElement = ObjectRepository.GetObject(arrObjElement);
						System.out.println(byObjElement.toString());
						driver.findElement(byObjElement).click();
						result = true;
						break;
					} catch (StaleElementReferenceException e) {
					}
					attempts++;
				}
				return result;
			}
		} catch (NoSuchElementException e) {
			TestReporter.Error(driver, strConfig, "JS Error", "Unable to click on element: " + strelementName);
			return false;
		} catch (Exception e) {
			TestReporter.Error(driver, strConfig, "JS Error",
					"Unable to click on element: " + strelementName + " - " + e.getClass().getCanonicalName());
			return false;
		}
		return false;
	}

	public boolean JSClick(RemoteWebDriver driver, String strConfig, WebElement e) {
		try {
			boolean result = false;
			int attempts = 0;
			while (attempts < 3) {
				try {
					JavascriptExecutor js = (JavascriptExecutor) driver;
					// new Actions(driver).moveToElement(e).perform();
					WebDriverWait wait = new WebDriverWait(driver, 30);
					wait.until(ExpectedConditions.elementToBeClickable(e));
					driver.executeScript("arguments[0].scrollIntoView(true);", e);
					js.executeScript("arguments[0].click();", e);
					// js.executeScript("arguments[0].click();",e);
					result = true;
					break;
				} catch (StaleElementReferenceException ex) {
				}
				attempts++;
			}
			return result;
		} catch (NoSuchElementException ex) {
			TestReporter.Error(driver, strConfig, "JS Error", "Unable to click on webelement: " + e.toString());
			return false;
		}
	}

	/*
	 * Method Name: Description Input: Output: Developed By:
	 */
	public static void PageRefresh(RemoteWebDriver driver, String strConfig) {
		try {
			driver.navigate().refresh();
		} catch (TimeoutException e) {
			// do nothing
		}
	}

	public static void CaptureScreenshot(RemoteWebDriver driver, String strConfig) {
		if (Constants.strScreenShots == null || Constants.strScreenShots.equalsIgnoreCase("No")
				|| Constants.strScreenShots.equalsIgnoreCase("Only for Errors")) {
			return;
		}
		CommonLib.getLogger(strConfig).info("Inside CaptureScreenshot");
		try {
			String sPath = getScreenshotPath(strConfig) + "/" + "Screens_" + CommonLib.iScreenNum(strConfig) + ".jpg";
			waitForPageLoad(driver, strConfig);
			CommonLib.getLogger(strConfig)
					.info("Screenshot Taken- " + "Screens_" + CommonLib.iScreenNum(strConfig) + ".jpg");
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(sPath));
			CommonLib.putScreenNum(strConfig, CommonLib.iScreenNum(strConfig) + 1);
		} catch (Exception e) {
			TestReporter.Info(driver, strConfig, "Screenshot error", "Unable to capture screenshot");
			CommonLib.getLogger(strConfig).error(e.getMessage());
		}
		return;
	}

	public static void CaptureErrorScreenshot(RemoteWebDriver driver, String strConfig) {
		CommonLib.getLogger(strConfig).info("Inside Capture Error Screenshot");
		try {
			String sPath = getScreenshotPath(strConfig) + "/" + "Error_Screens_" + CommonLib.iScreenNum(strConfig)
					+ ".jpg";
			waitForPageLoad(driver, strConfig);
			CommonLib.getLogger(strConfig)
					.info("Screenshot Taken- " + "Error_Screens_" + CommonLib.iScreenNum(strConfig) + ".jpg");
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(sPath));
			CommonLib.putScreenNum(strConfig, CommonLib.iScreenNum(strConfig) + 1);
		} catch (Exception e) {
			TestReporter.Info(driver, strConfig, "Screenshot error", "Unable to capture screenshot");
			CommonLib.getLogger(strConfig).error(e.getMessage());
		}
		return;
	}

	/*
	 * Method Name: waitForJQueryToBeActive() Description: the code used to wait for
	 * Jquery to be Active if JQuery is Used Input: Output: Developed By: Adarsh
	 * Shetty: Found at
	 * https://stackoverflow.com/questions/49230269/wait-for-ajax-request-to-
	 * complete-selenium-webdriver
	 */
	public static void waitForJQueryToBeActive(RemoteWebDriver driver, String strConfig) {
		Boolean isJqueryUsed = (Boolean) ((JavascriptExecutor) driver)
				.executeScript("return (typeof(jQuery) != 'undefined')");
		if (isJqueryUsed) {
			while (true) {
				// JavaScript test to verify jQuery is active or not
				Boolean ajaxIsComplete = (Boolean) (((JavascriptExecutor) driver)
						.executeScript("return jQuery.active == 0"));
				if (ajaxIsComplete)
					break;
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
				}
			}
		}
	}

	/*
	 * Method Name: Description Input: Output: Developed By:
	 */
	public static void waitForLoad(RemoteWebDriver driver, String strConfig) {
		CommonLib.getLogger(strConfig).info("Inside waitForLoad");
		long istartTime = System.currentTimeMillis();
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, 90);
		try {
			wait.until(pageLoadCondition);
			long intEndTime = System.currentTimeMillis();
			long totalTime = intEndTime - istartTime;
			CommonLib.getLogger(strConfig).info("Waiting for page load " + (totalTime / 1000) + " Seconds");
		} catch (JavascriptException e) {
			TestReporter.Info(driver, strConfig, "JavaScript Exception",
					"Received JavaScript Exception on page" + driver.getTitle());
			CommonLib.getLogger(strConfig).info(e.getMessage());
		} catch (StaleElementReferenceException e) {
			TestReporter.Info(driver, strConfig, "StaleElementReference Exception",
					"Received StaleElementReference Exception on page" + driver.getTitle());
			CommonLib.getLogger(strConfig).info(e.getMessage());
		} catch (TimeoutException e) {
			TestReporter.Info(driver, strConfig, "Timeout Exception",
					"Received Timeout Exception on page" + driver.getTitle());
			CommonLib.getLogger(strConfig).info(e.getMessage());
		}
	}

	/*
	 * Method Name: Description Input: Output: Developed By:
	 */
	public static void waitForPageLoad(RemoteWebDriver driver, String strConfig) {
		String strLoaderXpath = null;
		strLoaderXpath = ".//*[@id='ajaxLoader']/img";
		String strLoaderXpath1 = ".//*[@id='maintenanceScreenAjaxLoader']/img";
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		try {
			driver.findElement(By.xpath(strLoaderXpath1));
			strLoaderXpath = strLoaderXpath1;
		} catch (NoSuchElementException e) {
			// do nothing
		}

		try {
			WebElement objLoader = driver.findElement(By.xpath(strLoaderXpath));
			if (objLoader.isDisplayed()) {
				int staleCounter = 0;
				for (Integer iWaitForLoader = 0; iWaitForLoader < 200; iWaitForLoader++) {
					try {
						Thread.sleep(Constants.intSmallDelay);
						CommonLib.getLogger(strConfig).info("Waiting for Page load Loop " + (iWaitForLoader + 1));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					try {
						objLoader = driver.findElement(By.xpath(strLoaderXpath));
					} catch (JavascriptException e) {
						TestReporter.Error(driver, strConfig, "JavaScriptException",
								"For object with xpath -" + strLoaderXpath);
					} catch (NoSuchElementException e) {
						TestReporter.Info(driver, strConfig, "NoSuchElementException",
								"Object Not found with xpath -" + strLoaderXpath);
						break;
					} catch (WebDriverException e) {
						TestReporter.Info(driver, strConfig, "Exception Thrown",
								"WebDriverException on waitforPageLoad");
					}

					try {
						if (!objLoader.isDisplayed()) {
							break;
						}
					} catch (StaleElementReferenceException e) {
						CommonLib.getLogger(strConfig).info("StaleElementReferenceException on waitforPageLoad");
						staleCounter += 1;
						if (staleCounter == 2) {
							break;
						}
					} catch (JavascriptException e) {
						CommonLib.getLogger(strConfig).info("JavascriptException on waitforPageLoad");
					} catch (WebDriverException e) {
						TestReporter.Info(driver, strConfig, "Exception Thrown",
								"WebDriverException on waitforPageLoad");
					} catch (NullPointerException e) {
						TestReporter.Info(driver, strConfig, "Exception Thrown",
								"NullPointerException on waitforPageLoad");
					}
				}
				try {
					if (objLoader.isDisplayed()) {
						TestReporter.Warning(driver, strConfig, "Infinite Load", "Infinite Loading Spinner");
						return;
					}
				} catch (StaleElementReferenceException | NoSuchElementException e) {
					CommonLib.getLogger(strConfig).info(e.getClass().getCanonicalName() + " on waitforPageLoad");
				}
			}
		} catch (NoSuchElementException e) {
//			TestReporter.Info(driver, strConfig, "Exception Thrown", "NoSuchElementException on waitforPageLoad");
		} catch (JavascriptException e) {
			TestReporter.Info(driver, strConfig, "Exception Thrown", "JavascriptException on waitforPageLoad");
		} catch (StaleElementReferenceException e) {
			e.printStackTrace();
			TestReporter.Info(driver, strConfig, "Exception Thrown",
					"StaleElementReferenceException on waitforPageLoad");
		} catch (ElementNotVisibleException e) {
			// do Nothing
		} catch (WebDriverException e) {
			TestReporter.Info(driver, strConfig, "Exception Thrown", "WebDriverException on waitforPageLoad");
		} finally {
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		}
	}

	/*
	 * Method Name: Description Input: Output: Developed By:
	 */
	public Boolean ElementExists(RemoteWebDriver driver, String strConfig, String strObjName) {
		boolean present;
		String[] arrObjDetails = ObjectRepository.GetObjectlocators(strObjName);
		By byObjDetails = ObjectRepository.GetObject(arrObjDetails);
		try {
			waitForLoad(driver, strConfig);
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			driver.findElement(byObjDetails);
			present = true;
			TestReporter.Pass(driver, strConfig, "Element exists",
					strObjName + " exists on the webpage " + driver.getTitle());
		} catch (NoSuchElementException e) {
			present = false;
			TestReporter.Fail(driver, strConfig, "Element doesn't exists",
					strObjName + " doesn't exists on the webpage.");
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return present;
	}

	public Boolean ElementExists(RemoteWebDriver driver, String strConfig, String strObjName, String Value) { // For
																												// radio
																												// button
		boolean present;
		String[] arrObjDetails = ObjectRepository.GetObjectlocators(strObjName + "_" + Value);
		By byObjDetails = ObjectRepository.GetObject(arrObjDetails);
		try {
			waitForLoad(driver, strConfig);
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			driver.findElement(byObjDetails);
			present = true;
		} catch (NoSuchElementException e) {
			present = false;
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return present;
	}

	public Boolean ElementExist(RemoteWebDriver driver, String strConfig, String strXpath) {
		boolean present;
//		String[] arrObjDetails = ObjectRepository.GetObjectlocators(strObjName);
		By byObjDetails = By.xpath(strXpath);
		try {
			waitForLoad(driver, strConfig);
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			driver.findElement(byObjDetails);
			present = true;
		} catch (NoSuchElementException e) {
			present = false;
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return present;
	}

	Boolean ActionButtonExists(RemoteWebDriver driver, String strConfig, String strXpath) {
		boolean present;
//		String[] arrObjDetails = ObjectRepository.GetObjectlocators(strObjName);
		By byObjDetails = By.xpath(strXpath);
		try {
			waitForLoad(driver, strConfig);
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			driver.findElement(byObjDetails);
			present = true;
		} catch (NoSuchElementException e) {
			present = false;
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return present;
	}

	/*
	 * Method Name: Description Input: Output: Developed By:
	 */
	public static void WaitUntilObjectExists(By Bydetails) {
		wait.until(ExpectedConditions.elementToBeClickable(Bydetails));
	}

	/*
	 * NOT USING THIS FUNCTION TO WAIT FOR ELEMENT Method Name: WaitForElement
	 * Description: Waits for some time until object is visible on screen Input:
	 * Header, or XPath Output: void Developed By: Stephen
	 */
	public static void WaitForElementToBeClickable(RemoteWebDriver driver, String strConfig, String Header) {
		String[] arrObjectDetails = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 40);

			if (Header.isEmpty()) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", "Please supply Header ");
			} else if (Header.contains("//")) {
				CommonLib.getLogger(strConfig).info("Waiting for element: " + Header);
				TestReporter.Info(driver, strConfig, "Waiting for Element to be Clickable: ", Header);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Header)));

				Boolean isElementClickable = isElementEnabled(driver, strConfig, Header);
				if (!isElementClickable) {
					TestReporter.Fail(driver, strConfig, Header, " is not clickable");
				} else {
					TestReporter.Pass(driver, strConfig, Header, " is clickable");
				}

			} else {
				arrObjectDetails = ObjectRepository.GetObjectlocators(Header);
				CommonLib.getLogger(strConfig).info("Waiting for element: " + Header);
				if (arrObjectDetails[0].equals("xpath")) {
					TestReporter.Info(driver, strConfig, "Waiting for Element to be Clickable: ", Header);
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(arrObjectDetails[1])));

					Boolean isElementClickable = isElementEnabled(driver, strConfig, arrObjectDetails[1]);
					if (!isElementClickable) {
						TestReporter.Fail(driver, strConfig, Header, " is not clickable");
					} else {
						TestReporter.Pass(driver, strConfig, Header, " is clickable");
					}

				} else if (arrObjectDetails[0].equals("id")) {
					TestReporter.Info(driver, strConfig, "Waiting for Element to be Clickable: ", Header);
					wait.until(ExpectedConditions.elementToBeClickable(By.id(arrObjectDetails[1])));

					Boolean isElementClickable = isElementEnabled(driver, strConfig, arrObjectDetails[1]);
					if (!isElementClickable) {
						TestReporter.Fail(driver, strConfig, Header, " is not clickable");
					} else {
						TestReporter.Pass(driver, strConfig, Header, " is clickable");
					}

				}
			}
		} catch (NoSuchElementException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown",
					"NoSuchElementException on- WaitForElement to be clickable: " + Header);
		} catch (StaleElementReferenceException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown",
					"StaleElementReferenceException on- WaitForElement to be clickable: " + Header);
		} catch (JavascriptException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown",
					"JavascriptException on- WaitForElement to be clickable: " + Header);
		} catch (TimeoutException e) {
			TestReporter.Fail(driver, strConfig, "Timeout Exception - Element: " + Header, "Not Clickable");
		} catch (NullPointerException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown",
					"NullPointerException on- WaitForElement to be clickable: " + Header);
		} catch (IllegalArgumentException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown",
					"IllegalArgumentException on- WaitForElement to be clickable: " + Header);
		}
	}

	public static void WaitForElementToBeVisible(RemoteWebDriver driver, String strConfig, String Header) {
		String[] arrObjectDetails = null;
		try {
			Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver).withTimeout(40, TimeUnit.SECONDS)
					.pollingEvery(200, TimeUnit.MILLISECONDS);
			// .ignoring(NoSuchElementException.class);

			if (Header.isEmpty()) {
				TestReporter.Error(driver, strConfig, "Exception Thrown", "Please supply Header ");
			} else if (Header.contains("//")) {
				CommonLib.getLogger(strConfig).info("Waiting for element: " + Header);
				TestReporter.Info(driver, strConfig, "Waiting for Element to be visible: ", Header);
				fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Header)));

				Boolean isElementVisible = isElementVisible(driver, strConfig, Header);
				if (!isElementVisible) {
					TestReporter.Fail(driver, strConfig, Header, " is not visible");
				} else {
					TestReporter.Pass(driver, strConfig, Header, " is visible");
				}
			} else {
				arrObjectDetails = ObjectRepository.GetObjectlocators(Header);
				CommonLib.getLogger(strConfig).info("Waiting for element: " + Header);
				if (arrObjectDetails[0].equals("xpath")) {
					TestReporter.Info(driver, strConfig, "Waiting for Element to be visible: ", Header);
					fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(arrObjectDetails[1])));
					Boolean isElementVisible = isElementVisible(driver, strConfig, arrObjectDetails[1]);
					if (!isElementVisible) {
						TestReporter.Fail(driver, strConfig, Header, " is not visible");
					} else {
						TestReporter.Pass(driver, strConfig, Header, " is visible");
					}
				} else if (arrObjectDetails[0].equals("id")) {
					TestReporter.Info(driver, strConfig, "Waiting for Element to be visible: ", Header);
					fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(arrObjectDetails[1])));
					Boolean isElementVisible = isElementVisible(driver, strConfig, arrObjectDetails[1]);
					if (!isElementVisible) {
						TestReporter.Fail(driver, strConfig, Header, " is not visible");
					} else {
						TestReporter.Pass(driver, strConfig, Header, " is visible");
					}
				}
			}
		} catch (NoSuchElementException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown",
					"NoSuchElementException on- WaitForElement to be visible: " + Header);

		} catch (StaleElementReferenceException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown",
					"StaleElementReferenceException on- WaitForElement to be visible: " + Header);

		} catch (JavascriptException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown",
					"JavascriptException on- WaitForElement to be visible: " + Header);

		} catch (TimeoutException e) {
			TestReporter.Fail(driver, strConfig, "Timeout Exception - Element: " + Header, "Not Visible");

		} catch (NullPointerException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown",
					"NullPointerException on- WaitForElement to be visible: " + Header);

		} catch (IllegalArgumentException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown",
					"IllegalArgumentException on- WaitForElement to be visible: " + Header);

		}
	}

	public static void WaitForElement(RemoteWebDriver driver, String strConfig, String Header, String Value) {
		// For Radio Buttons
		String[] arrObjectDetails = null;
		Header += "_" + Value;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			{
				arrObjectDetails = ObjectRepository.GetObjectlocators(Header);
				CommonLib.getLogger(strConfig).info("Waiting for element: " + Header);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(arrObjectDetails[1])));
			}
		} catch (NoSuchElementException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown",
					"NoSuchElementException on- WaitForElement: " + Header);
		} catch (StaleElementReferenceException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown",
					"StaleElementReferenceException on- WaitForElement: " + Header);
		} catch (JavascriptException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown",
					"JavascriptException on- WaitForElement: " + Header);
		} catch (TimeoutException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", "TimeoutException on- WaitForElement: " + Header);
		} catch (NullPointerException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown",
					"NullPointerException on- WaitForElement: " + Header);
		} catch (IllegalArgumentException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown",
					"IllegalArgumentException (xpath null) on- WaitForElement: " + Header);
		}
	}

	/*
	 * Method Name: WaitUntilScreenTitleAppears Description: Waits for next screen
	 * Title is visible (wait time 5 minutes max) Input: Expected screen title
	 * Output: void
	 */
	public static void WaitUntilScreenTitleAppears(RemoteWebDriver driver, String strConfig, String Title) {
		String[] arrObjXpath = ObjectRepository.GetObjectlocators("LBL_ScreenTitle");
		String strHeaderText = "";
		for (int iLoop = 0; iLoop <= 5; iLoop++) {
			new WebDriverWait(driver, 300).until((ExpectedConditions.elementToBeClickable(By.xpath(arrObjXpath[1]))));
			try {
				strHeaderText = driver.findElement(By.xpath(arrObjXpath[1])).getText().trim();
			} catch (NoSuchElementException e) {
				// do nothing
			}
			if (strHeaderText.equalsIgnoreCase(Title)) {
				CommonLib.getLogger(strConfig).info("Page loaded- " + strHeaderText);
				break;
			} else {
				try {
					Thread.sleep(Constants.intMediumDelay);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/*
	 * Method Name: Description Input: Output: Developed By:
	 */
	static By locatorValue(String locatorType, String value) {
		By by;
		switch (locatorType) {
		case "id":
			by = By.id(value);
			break;
		case "name":
			by = By.name(value);
			break;
		case "xpath":
			by = By.xpath(value);
			break;
		case "css":
			by = By.cssSelector(value);
			break;
		case "linkText":
			by = By.linkText(value);
			break;
		case "partialLinkText":
			by = By.partialLinkText(value);
			break;
		default:
			by = null;
			break;
		}
		return by;
	}

	/*
	 * Method Name: Description Input: Output: Developed By:
	 */
	public static String GetCurrentDate() {
		String CurrDate;
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		CurrDate = dateFormat.format(date);
		return CurrDate;
	}

	/*
	 * Method Name: Description Input: Output: Developed By:
	 */
	public static String GetCurrentTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
		Date date = new Date();
		long nextId = date.getTime();

		Timestamp ts = new Timestamp(nextId);

		return dateFormat.format(ts);
	}

	/*
	 * Method Name: Description Input: Output: Developed By:
	 */
	static String GetSpecificDate(String Type, int Quantity) {
		String SpecificDate;
		String CurrDate = GetCurrentDate();
		Type = Type.toUpperCase();
		int intCurrDate, intCurrMonth, intCurrYear;
		String[] arrSplitCurrDate = CurrDate.split("/");
		intCurrDate = Integer.parseInt(arrSplitCurrDate[0]);
		intCurrMonth = Integer.parseInt(arrSplitCurrDate[1]);
		intCurrYear = Integer.parseInt(arrSplitCurrDate[2]);
		switch (Type) {
		case "YEAR":
			int intSpecificYear = intCurrYear + Quantity;
			SpecificDate = Integer.toString(intCurrDate) + "/" + Integer.toString(intCurrMonth) + "/"
					+ Integer.toString(intSpecificYear);
			break;
		case "MONTH":
			if ((intCurrMonth + Quantity) > 12) {
				intCurrYear = intCurrYear + 1;
				intCurrMonth = intCurrMonth + Quantity - 12;
			} else {
				intCurrMonth = intCurrMonth + Quantity;
			}
			SpecificDate = Integer.toString(intCurrDate) + "/" + Integer.toString(intCurrMonth) + "/"
					+ Integer.toString(intCurrYear);
			break;
		case "DATE":
			if ((intCurrMonth % 2) > 0) {
				if ((intCurrDate + Quantity) > 31) {
					intCurrDate = intCurrDate + Quantity - 31;
					intCurrMonth = intCurrMonth + 1;
					if (intCurrMonth > 12) {
						intCurrMonth = intCurrMonth - 12;
						intCurrYear = intCurrYear + 1;
					}
				} else {
					intCurrDate = intCurrDate + Quantity;
				}
			} else if (intCurrMonth == 2) {
				if ((intCurrYear % 4) > 0) {
					intCurrDate = intCurrDate + Quantity;
					if (intCurrDate > 28) {
						intCurrDate = intCurrDate - 28;
						intCurrMonth = intCurrMonth + 1;
						if (intCurrMonth > 12) {
							intCurrMonth = intCurrMonth - 12;
							intCurrYear = intCurrYear + 1;
						}
					}
				} else {
					intCurrDate = intCurrDate + Quantity;
					if (intCurrDate > 29) {
						intCurrDate = intCurrDate - 29;
						intCurrMonth = intCurrMonth + 1;
						if (intCurrMonth > 12) {
							intCurrMonth = intCurrMonth - 12;
							intCurrYear = intCurrYear + 1;
						}
					}
				}
			} else {
				if ((intCurrDate + Quantity) > 30) {
					intCurrDate = intCurrDate + Quantity - 30;
					intCurrMonth = intCurrMonth + 1;
					if (intCurrMonth > 12) {
						intCurrMonth = intCurrMonth - 12;
						intCurrYear = intCurrYear + 1;
					}
				} else {
					intCurrDate = intCurrDate + Quantity;
				}
			}
			SpecificDate = Integer.toString(intCurrDate) + "/" + Integer.toString(intCurrMonth) + "/"
					+ Integer.toString(intCurrYear);
			break;
		default:
			SpecificDate = GetCurrentDate();
			break;
		}
		return SpecificDate;
	}

	/*
	 * Method Name: Description Input: Output: Developed By:
	 */
	void enter_Text(RemoteWebDriver driver, String strConfig, String locatorType, String value, String text) {
		try {
			By locator;
			locator = locatorValue(locatorType, value);
			WebElement element = driver.findElement(locator);
			element.clear();
			element.sendKeys(text);
			element.sendKeys(Keys.ENTER);
		} catch (NoSuchElementException e) {
			System.err.format("No Element Found to enter text" + e);
		}
	}

	void enter_Text(RemoteWebDriver driver, String xpathExp, String value) {
		try {
			WebElement element = driver.findElement(By.xpath(xpathExp));
			element.clear();
			element.sendKeys(value);
			element.sendKeys(Keys.ENTER);
			element.sendKeys(Keys.TAB);
		} catch (NoSuchElementException e) {
			System.err.format("No Element Found to enter text" + e);
		}
	}

	public static void enter_Text(RemoteWebDriver driver, String strConfig, WebElement ObjTextBox, String Value) {
		ObjTextBox.clear();
		ObjTextBox.sendKeys(Value);
		ObjTextBox.sendKeys(Keys.TAB);
		TestReporter.Pass(driver, strConfig, "Text Entered: ", Value);
		// ObjTextBox.sendKeys(Keys.ENTER);
	}

	public static String gettestcaseName(String strConfig) {
		switch (strConfig) {
		case "Config1":
			return Test1.getTestCaseName();
		case "Config2":
			return Test2.getTestCaseName();
		case "Config3":
			return Test3.getTestCaseName();
		case "Config4":
			return Test4.getTestCaseName();
		case "Config5":
			return Test5.getTestCaseName();
		default:
			return Test1.getTestCaseName();

		}
	}

	void enter_Text1(WebElement ObjTextBox, String Value) {
		ObjTextBox.clear();
		ObjTextBox.sendKeys(Value);
		// ObjTextBox.sendKeys(Keys.TAB);
		ObjTextBox.sendKeys(Keys.ENTER);
	}

	/*
	 * Method Name: Description Input: Output: Developed By:
	 */
	public void enter_URL(RemoteWebDriver driver, String strConfig, String URL) {
		// Requirements
		Logger logger;
		switch (strConfig) {
		case "Config1":
			logger = Test1.logger;
//			driver.switchTo().window(Test1.strDriverSessionTest);
			break;
		case "Config2":
			logger = Test2.logger;
//			driver.switchTo().window(Test2.strDriverSessionTest);
			break;
		case "Config3":
			logger = Test3.logger;
//			driver.switchTo().window(Test2.strDriverSessionTest);
			break;
		case "Config4":
			logger = Test4.logger;
//			driver.switchTo().window(Test2.strDriverSessionTest);
			break;
		case "Config5":
			logger = Test5.logger;
//			driver.switchTo().window(Test2.strDriverSessionTest);
			break;
		default:
			logger = Test1.logger;
//			driver.switchTo().window(Test1.strDriverSessionTest);
			break;
		}
		// Execution
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		try {
			logger.info("Inside enter_URL");
//			driver.navigate().to(URL);
			driver.get(URL);
		} catch (NoSuchSessionException e) {
			TestReporter.Error(driver, strConfig, "Driver Error",
					"Session ID is null. Using WebDriver after calling quit()?");
			logger.error(e.getMessage());
		} catch (TimeoutException e) {
			System.out.println("timeoutex");
			TestReporter.Info(driver, strConfig, "TimeoutException", "TimeoutException on enter_url");
			logger.error(e.getMessage());
		}
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
	}
	/*
	 * Method Name:WebElement_to_String Description:Checks if list is alphabetically
	 * sorted or not Input: Output: Developed By:Likith Yelamanchili
	 */

	public List<String> WebElement_to_String(List<WebElement> branchlist) {
		List<String> branch = new ArrayList<String>();
		for (int i = 0; i < branchlist.size(); i++) {
			branch.add(i, branchlist.get(i).getText());

		}
		return branch;
	}

	/*
	 * Method Name:isSorted Description:Checks if list is alphabetically sorted or
	 * not Input: Output: Developed By:Likith Yelamanchili
	 */
	public boolean isSorted(List<String> list) {
		boolean sorted = true;
		for (int i = 1; i < list.size(); i++) {
			if (list.get(i - 1).compareTo(list.get(i)) > 0)
				sorted = false;
		}

		return sorted;
	}

	void click_On_Link(RemoteWebDriver driver, String strConfig, String locatorType, String value) {
		try {
			By locator;
			locator = locatorValue(locatorType, value);
			WebElement element = driver.findElement(locator);
			element.click();
		} catch (NoSuchElementException e) {
			System.err.format("No Element Found to enter text" + e);
		}
	}

	public static boolean click_On_Buttonheader(RemoteWebDriver driver, String strConfig, String Header) {
		boolean b = false;
		try {
			String[] arrObjElement = ObjectRepository.GetObjectlocators(Header);
			By locator = ObjectRepository.GetObject(arrObjElement);
			WebElement element = driver.findElement(locator);
			element.click();
			b = true;
		} catch (NoSuchElementException e) {
			System.err.format("No Element Found to perform click" + e);
			b = false;
		}
		return b;
	}

	/*
	 * Method Name: Description Input: Output: Developed By:
	 */
	public void click_On_Button(RemoteWebDriver driver, String strConfig, String locatorType, String value) {
		try {
			By locator;
			locator = locatorValue(locatorType, value);
			WebElement element = driver.findElement(locator);
			element.click();
		} catch (NoSuchElementException e) {
			System.err.format("No Element Found to perform click" + e);
		}
	}

	/*
	 * Method Name: Description Input: Output: Developed By:
	 */
	void select_checkbox(RemoteWebDriver driver, String strConfig, String locatorType, String value) {
		try {
			By locator;
			locator = locatorValue(locatorType, value);
			WebElement element = driver.findElement(locator);
			element.click();
		} catch (NoSuchElementException e) {
			System.err.format("No Element found to perform selection" + e);
		}
	}

	public void select_checkbox(RemoteWebDriver driver, String strConfig, WebElement objCheckBox, String Value) {
		try {
			Boolean strStatus = objCheckBox.isSelected();
			if (strStatus == true && Value.equalsIgnoreCase("uncheck")) {
				JSClick(driver, strConfig, objCheckBox);
			} else if (strStatus == false && Value.equalsIgnoreCase("check")) {
				JSClick(driver, strConfig, objCheckBox);
			} else if (strStatus == true && Value.equalsIgnoreCase("check")) {
				// Do nothing
			} else if (Value.equalsIgnoreCase("click")) {
				JSClick(driver, strConfig, objCheckBox);
			}
		} catch (StaleElementReferenceException e) {
			CommonLib.getLogger(strConfig).info("StaleElementReferenceException on select_checkbox- " + objCheckBox);
		}
	}

	/*
	 * Method Name: Description Input: Output: Developed By:
	 */
	void click_radiobtn(RemoteWebDriver driver, String strConfig, String locatorType, String value) {
		try {
			By locator;
			locator = locatorValue(locatorType, value);
			WebElement element = driver.findElement(locator);
			element.click();
		} catch (NoSuchElementException e) {
			System.err.format("No Element found to perform selection" + e);
		}
	}

	/*
	 * Method Name: Description Input: Output: Developed By:
	 */
	void select_comboboxOption(RemoteWebDriver driver, String strConfig, String locatorType, String objvalue,
			String Option) {
		try {
			By locator;
			locator = locatorValue(locatorType, objvalue);
			WebElement element = driver.findElement(locator);
			Select se = new Select(element);
			if (Option.equalsIgnoreCase("SelectLast")) {
				int intTotalOptions = se.getOptions().size();
				se.selectByIndex(intTotalOptions - 1);
			} else {
				se.selectByVisibleText(Option);
			}

		} catch (NoSuchElementException e) {
			System.err.format("No Element found to perform selection" + e);
		}
	}

	public void select_comboboxOption(String strConfig, WebElement objSelect, String Option) {
		Select se = new Select(objSelect);
		if (Option.equalsIgnoreCase("SelectLast")) {
			int intTotalOptions = se.getOptions().size();
			se.selectByIndex(intTotalOptions - 1);
		} else {
			se.selectByVisibleText(Option);
		}
	}

	public static Boolean convert(String strConfig, String str) {
		String check = str;
		// Create a char array of given String
		char ch[] = str.toCharArray();
		for (int i = 0; i < str.length(); i++) {

			// If first character of a word is found
			if (i == 0 && ch[i] != ' ' || ch[i] != ' ' && ch[i - 1] == ' ') {

				// If it is in lower-case
				if (ch[i] >= 'a' && ch[i] <= 'z') {

					// Convert into Upper-case
					ch[i] = (char) (ch[i] - 'a' + 'A');
				}
			}

			// If apart from first character
			// Any one is in Upper-case
			else if (ch[i] >= 'A' && ch[i] <= 'Z')

				// Convert into Lower-Case
				ch[i] = (char) (ch[i] + 'a' - 'A');
		}

		// Convert the char array to equivalent String
		String st = new String(ch);
		if (st.equals(check)) {
			return true;
		} else {
			return false;
		}

	}

	void select_comboboxOption(RemoteWebDriver driver, String strConfig, String Header, String Value) {
		int attempts = 0;
		while (attempts < 3) {
			// System.out.println(Header);
			String[] Arr_ObjectDetails = ObjectRepository.GetObjectlocators(Header);
//			System.out.println(Arr_ObjectDetails[0]);
//			System.out.println(Arr_ObjectDetails[0]);
			By by = ObjectRepository.GetObject(Arr_ObjectDetails);
			waitForLoad(driver, strConfig);
			Select dropdown = new Select(driver.findElement(by));
			new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(driver.findElement(by)));

			for (int iWait = 1; iWait <= 5; iWait++) {
				int totalElements = dropdown.getOptions().size();
				if (totalElements > 1) {
					break;
				} else {
					try {
						CommonLib.getLogger(strConfig).info("Waiting for options to load in dropdown: " + Header);
						Thread.sleep(Constants.intSmallDelay);
					} catch (InterruptedException e) {
					}
				}
			}

			if (Value.equalsIgnoreCase("SelectLast")) {
				try {
					int selectOptions = dropdown.getOptions().size();
					dropdown.selectByIndex(selectOptions - 1);
					List<WebElement> selectedvalue = dropdown.getAllSelectedOptions();
					TestReporter.Pass(driver, strConfig, "Selected value", selectedvalue.get(0).toString());

					break;
				} catch (NoSuchElementException e) {
					try {
						Thread.sleep(Constants.intSmallDelay);
						dropdown.selectByIndex(1);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				} catch (StaleElementReferenceException e) {
					attempts++;
				}
			} else if (Value.equalsIgnoreCase("SelectFirst")) {
				try {
					dropdown.selectByIndex(1);
					break;
				} catch (NoSuchElementException e) {
					try {
						Thread.sleep(Constants.intSmallDelay);
						dropdown.selectByIndex(1);
						break;
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				} catch (StaleElementReferenceException e) {
					attempts++;
				}
			} else if (Value.equalsIgnoreCase("SecondLast")) {
				try {
					dropdown.selectByIndex(dropdown.getOptions().size() - 2);
					break;
				} catch (NoSuchElementException e) {
					try {
						Thread.sleep(Constants.intSmallDelay);
						by = ObjectRepository.GetObject(Arr_ObjectDetails);
						dropdown.selectByIndex(1);
						break;
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				} catch (StaleElementReferenceException e) {
					attempts++;
				}
			} else {
				try {
					waitForLoad(driver, strConfig);
					dropdown.selectByVisibleText(Value);
					TestReporter.Pass(driver, strConfig, "Selected value", Value);
					break;
				} catch (NoSuchElementException e) {
					try {
						Thread.sleep(Constants.intSmallDelay);
						by = ObjectRepository.GetObject(Arr_ObjectDetails);
						dropdown.selectByIndex(1);
						break;
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				} catch (StaleElementReferenceException e) {
					attempts++;
				} catch (JavaScriptException e) {
					// do nothing
				}
			}
		}
	}

	/*
	 * Method Name: Description Input: Output: Developed By:
	 */
	void close_Browser(RemoteWebDriver driver, String strConfig) {
		driver.quit();
	}

	/*
	 * Method Name: Description Input: Output: Developed By:
	 */
	void getscreenshot(RemoteWebDriver driver, String TestCaseName) throws Exception {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// The below method will save the screen shot in d drive with name
		// "screenshot.png"
		FileUtils.copyFile(scrFile, new File(ProjectConstants.RESULTS + "/" + TestCaseName + ".png"));
	}

	/*
	 * Method Name: Description Input: Output: Developed By:
	 */
	public int TblRowCount(WebElement table) {
		List<WebElement> TotalRows = table.findElements(By.tagName("tr"));
		int rowcount = TotalRows.size();
		return rowcount;
	}

	int RowCount(RemoteWebDriver driver, String strConfig, String strTable) {
		String[] arrObjDetails = ObjectRepository.GetObjectlocators(strTable);
		arrObjDetails[1] += "/tbody/tr";
		By byTableRows = ObjectRepository.GetObject(arrObjDetails);
		List<WebElement> tableRows = driver.findElements(byTableRows);
		int totalRows = tableRows.size();
		return totalRows;
	}

	int ColumnCount(RemoteWebDriver driver, String strConfig, String strTable) {
		String[] arrObjDetails = ObjectRepository.GetObjectlocators(strTable);
		arrObjDetails[1] += "/thead/tr/th";
		By byTableHeads = ObjectRepository.GetObject(arrObjDetails);
		List<WebElement> tableHeads = driver.findElements(byTableHeads);
		int totalCols = tableHeads.size();
		return totalCols;
	}

	String GetTableValues(RemoteWebDriver driver, String strConfig, String objTable, int col) {
		int rowcount = RowCount(driver, strConfig, objTable);

		String strActualTblCellValues = "";
		String arrActualTblCellValues[];
		for (int iRow = 1; iRow <= rowcount; iRow++) {
			arrActualTblCellValues = GetCellValue(driver, strConfig, objTable, iRow, col).split(" ");
			if (arrActualTblCellValues.length == 1) {
				if (iRow > 1) {
					strActualTblCellValues = strActualTblCellValues + ";" + arrActualTblCellValues[0];
				} else {
					strActualTblCellValues = arrActualTblCellValues[0];
				}
			}
			// For Installment Pattern table cell values (Premium, Commission, Tax)
			else if (iRow > 1) {
				strActualTblCellValues = strActualTblCellValues + ";" + arrActualTblCellValues[1];
			} else {
				strActualTblCellValues = arrActualTblCellValues[1];
			}
		}
		return strActualTblCellValues;
	}

	private String Split(String strArrGrossPremium) {
		// TODO Auto-generated method stub
		return null;
	}

//	/*
//		Method Name:
//		Description
//		Input:
//		Output: 
//		Developed By:
//	*/
//	int TblColumnCount(WebElement table, int row) {
//		List<WebElement> TotalRows = table.findElements(By.tagName("tr"));
//		List<WebElement> TotalCols = TotalRows.get(row).findElements(By.tagName("td"));
//		int ColumnCount = TotalCols.size();
//		return ColumnCount;
//	}

	/*
	 * Method Name: Description Input: Output: Developed By:
	 */
	WebElement TblChildElement(WebElement table, int row, int col) {
		WebElement ChildElement;
		List<WebElement> TotalRows = table.findElements(By.tagName("tr"));
		List<WebElement> TotalCols = TotalRows.get(row).findElements(By.tagName("td"));
		ChildElement = TotalCols.get(col);
		return ChildElement;
	}

	/**
	 * @Description : This is wrapper method wait for element presence located
	 * @param : locator - By identification of element
	 */
	public static void waitForElementPresence(RemoteWebDriver driver, String strConfig, By locator)
			throws NotFoundException {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public static void waitForElementVisibility(RemoteWebDriver driver, String strConfig, WebElement locator)
			throws NotFoundException {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(locator));
	}

	/*
	 * Method Name: Description Input: Output: Developed By:
	 */
	String GetCellValue(RemoteWebDriver driver, String strConfig, String TableName, Integer iRow, Integer iCol) {
		String[] objLocators = ObjectRepository.GetObjectlocators(TableName);
		String[] getCell = objLocators;
		Integer intTotalRows = RowCount(driver, strConfig, TableName);
		if (intTotalRows == 1) {
			getCell[1] += "/tbody/tr/td[" + iCol + "]";
		} else {
			getCell[1] += "/tbody/tr[" + iRow + "]/td[" + iCol + "]";
		}
		By byTableCell = ObjectRepository.GetObject(getCell);
		WebElement cellElement = driver.findElement(byTableCell);
		String strCellValue = cellElement.getText();
		return strCellValue;
	}

	String GetHeaderValue(RemoteWebDriver driver, String strConfig, String TableName, Integer iCol) {
		String[] objLocators = ObjectRepository.GetObjectlocators(TableName);
		objLocators[1] += "/thead/tr/th[" + iCol + "]";
		By byHeaderCell = ObjectRepository.GetObject(objLocators);
		WebElement cellHeader = driver.findElement(byHeaderCell);
		String strHeaderValue = cellHeader.getText();
		return strHeaderValue;
	}

	String GetCellInnerText(RemoteWebDriver driver, String strConfig, String TableName, Integer iRow, Integer iCol) {
		String strCellValue = null;
		int attempts = 0;
		while (attempts < 2) {
			try {
				String[] objLocators = ObjectRepository.GetObjectlocators(TableName);
				String[] getCell = objLocators;
				getCell[1] = getCell[1] + "/tbody/tr[" + iRow + "]/td[" + iCol + "]";
				By byTableCell = ObjectRepository.GetObject(getCell);
				WebElement cellElement = driver.findElement(byTableCell);
				strCellValue = cellElement.getAttribute("innerText").trim();
				break;
			} catch (StaleElementReferenceException e) {
				attempts++;
			}
		}
		return strCellValue;
	}

	/*
	 * Method Name: Description Input: Output: Developed By:
	 */
	void SelectActionForTableRow(RemoteWebDriver driver, String strConfig, String TableName, String strActionName,
			String strColumnName, Integer iRow) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] getRows = ObjectRepository.GetObjectlocators(TableName);
		getRows[1] += "/tbody/tr";
		By byTableBody = ObjectRepository.GetObject(getRows);
		List<WebElement> tblRows = driver.findElements(byTableBody);
		int totalRows = tblRows.size();
		// CommonLib.getLogger().info("Total Rows are: " + totalRows);

		int iCol = GetTableColumn(driver, strConfig, TableName, strColumnName);
		CommonLib.getLogger(strConfig).info("Column received: " + iCol);
		if (iCol < 0 || iRow < 0) {
			return;
		}
		String[] objLocators = ObjectRepository.GetObjectlocators(TableName);
		if (iRow == 0) {
			if (totalRows == 1) {
				objLocators[1] = objLocators[1] + "/tbody/tr/td[" + iCol + "]/div/div";
			} else {
				objLocators[1] = objLocators[1] + "/tbody/tr[" + iRow + "]/td[" + iCol + "]/div/div";
			}

		} else {
			objLocators[1] = objLocators[1] + "/tbody/tr[" + iRow + "]/td[" + iCol + "]/div/div";
		}
		By byActionCell = ObjectRepository.GetObject(objLocators);

		WebDriverWait driverWait = new WebDriverWait(driver, 15);
		driverWait.until(ExpectedConditions.elementToBeClickable(byActionCell));
		WebElement actionCell = driver.findElement(byActionCell);
		actionCell.click();

		WebElement lnkClick = driver.findElement(By.partialLinkText(strActionName));
		lnkClick.click();
	}

	/*
	 * Method Name: Description Input: Output: Developed By:
	 */
	Integer GetTableRowContainingValue(RemoteWebDriver driver, String strConfig, String strObjectName,
			String strValue) {
//		String[] arrObjDetails = ObjectRepository.GetObjectlocators(strObjectName);
//		By byDetails = ObjectRepository.GetObject(arrObjDetails);
		try {
//			WebElement objEntireTable = CommonLib.getDriver().findElement(byDetails);
			waitForLoad(driver, strConfig);
			Integer iExpectedCol = 0;
			Integer iExpectedRow = 0;

			int totalRows = RowCount(driver, strConfig, strObjectName);
//			int totalRows = TblRowCount(objEntireTable);
			// CommonLib.getLogger().info("Total Rows in the table: " + totalRows);
			String[] arrObjTableDetails;
			for (int i = 1; i <= totalRows; i++) {
				// List<WebElement> TotalCols =
				// objEntireTable.findElement(By.tagName("td").findElements(By.tagName("td"));
				String[] arrTable = ObjectRepository.GetObjectlocators(strObjectName);
				arrObjTableDetails = arrTable;
				if (totalRows == 1) {
					arrObjTableDetails[1] += "/tbody/tr/td";
				} else {
					arrObjTableDetails[1] += "/tbody/tr[" + (i) + "]/td";
				}
				By byRow = ObjectRepository.GetObject(arrObjTableDetails);
				List<WebElement> objTableRow = driver.findElements(byRow);
				// List<WebElement> TotalCols =
				// objTableRow.findElements(By.tagName("td"));
				// CommonLib.getLogger().info("Total Columns in the table are: " +
				// TotalCols.size());
				for (WebElement cellelement : objTableRow) {
					String currCellValue = cellelement.getText();
					// String currCellValue =
					// cellelement.getAttribute("innerText").trim();
					// CommonLib.getLogger().info("Cell Value is: " + currCellValue);
					if (currCellValue.contains(strValue)) {
						iExpectedRow = i;
						// = TotalCols.indexOf(cellelement);
						return iExpectedRow;
					}
				}
				arrObjTableDetails[1] = "";
			}
		} catch (NullPointerException e) {
			CommonLib.getLogger(strConfig).info("Received nullpointer exception in - GetTableRowContainingValue");
			CommonLib.getLogger(strConfig).info(e.toString());
			return 0;
		} catch (StaleElementReferenceException e) {
			CommonLib.getLogger(strConfig).info("Received Stale Element exception in - GetTableRowContainingValue");
			CommonLib.getLogger(strConfig).info(e.toString());
			return 0;
		} catch (NoSuchElementException e) {
			CommonLib.getLogger(strConfig).info("Received No Such Element exceptionin - GetTableRowContainingValue");
			CommonLib.getLogger(strConfig).info(e.toString());
		}
		return 0;
	}

	/*
	 * Method Name: SearchValueInTableColumn Description: Checks if value is present
	 * in table column and returns true or false Input: Table Name, Column Number,
	 * Value to be searched for Output: true/false Developed By: Stephen
	 */
	Boolean SearchValueInTableColumn(RemoteWebDriver driver, String strConfig, String strTableName, String colNumber,
			String Value) {
		Boolean blnMatchFound = false;
		String[] arrTable = ObjectRepository.GetObjectlocators(strTableName);
		String strTableXpath = arrTable[1];
		int totalRows = RowCount(driver, strConfig, strTableName);

		for (int i = 1; i <= totalRows; i += 2) {
			arrTable[1] = strTableXpath;
			arrTable[1] += "/tbody/tr[" + i + "]/td[" + colNumber + "]";
			String strCellValue = driver.findElementByXPath(arrTable[1]).getText().trim();
			if (strCellValue.equalsIgnoreCase(Value)) {
				blnMatchFound = true;
				break;
			}
		}
		return blnMatchFound;
	}

	/*
	 * Method Name:GetTableRowExactlyMatchedValue Description Input: Output:
	 * Developed By:
	 */
	Integer GetTableRowExactlyMatchedValue(RemoteWebDriver driver, String strConfig, String strObjectName,
			String strValue) {
		String[] arrObjDetails = ObjectRepository.GetObjectlocators(strObjectName);
		By byDetails = ObjectRepository.GetObject(arrObjDetails);
		try {
			WebElement objEntireTable = driver.findElement(byDetails);

			Integer iExpectedCol = -1;
			Integer iExpectedRow = -1;

			int totalRows = TblRowCount(objEntireTable);
			// System.out.println("Total Rows in the table: " + totalRows);
			String[] arrObjTableDetails;
			for (int i = 0; i <= totalRows - 1; i++) {
				// List<WebElement> TotalCols =
				// objEntireTable.findElement(By.tagName("td").findElements(By.tagName("td"));
				String[] arrTable = ObjectRepository.GetObjectlocators(strObjectName);
				arrObjTableDetails = arrTable;
				if (totalRows == 1) {
					arrObjTableDetails[1] += "/tbody/tr/td";
				} else {
					arrObjTableDetails[1] += "/tbody/tr[" + (i + 1) + "]/td";
				}
				By byRow = ObjectRepository.GetObject(arrObjTableDetails);
				List<WebElement> objTableRow = driver.findElements(byRow);
				// List<WebElement> TotalCols =
				// objTableRow.findElements(By.tagName("td"));
				// System.out.println("Total Columns in the table are: " +
				// TotalCols.size());
				for (WebElement cellelement : objTableRow) {
					String currCellValue = cellelement.getText();
					// String currCellValue =
					// cellelement.getAttribute("innerText").trim();
					// System.out.println("Cell Value is: " + currCellValue);
					if (currCellValue.equals(strValue)) {
						iExpectedRow = i;
						// = TotalCols.indexOf(cellelement);
						return iExpectedRow;
					}
				}
				arrObjTableDetails[1] = "";
			}
		} catch (NullPointerException e) {
			System.out.println("Received nullpointer exception");
			System.out.println(e.toString());
			return -1;
		} catch (StaleElementReferenceException e) {
			System.out.println("Received Stale Element exception");
			System.out.println(e.toString());
			return -1;
		} catch (NoSuchElementException e) {
			System.out.println("Received No Such Element exception");
			System.out.println(e.toString());
		}
		return -1;
	}

	/*
	 * Method Name: Description Input: Output: Developed By:
	 */
	Integer GetCoverRow(RemoteWebDriver driver, String strConfig, String strValue) {
		try {
			Integer iExpectedCol = -1;
			Integer iExpectedRow = -1;

			int totalRows = RowCount(driver, strConfig, "TBL_CoverSummary_SelectedCovers");

			String[] arrObjTableDetails;
			for (int i = 1; i <= totalRows; i++) {
				String[] arrTable = ObjectRepository.GetObjectlocators("TBL_CoverSummary_SelectedCovers");
				arrTable[1] = arrTable[1] + "//tr[" + i + "]//span[contains(@class, 'glyphicon-menu-hamburger')]";
				if (ActionButtonExists(driver, strConfig, arrTable[1]) == true) {
					arrObjTableDetails = ObjectRepository.GetObjectlocators("TBL_CoverSummary_SelectedCovers");
					if (totalRows == 1) {
						arrObjTableDetails[1] += "//tr/td[1]";
					} else {
						arrObjTableDetails[1] += "//tr[" + i + "]/td[1]/div/div";
					}
					By byRow = ObjectRepository.GetObject(arrObjTableDetails);
					WebElement objCoverCell = driver.findElement(byRow);
					String currCellValue = objCoverCell.getAttribute("innerText");
					currCellValue = currCellValue.trim();
					if (currCellValue.contains(strValue)) {
						iExpectedRow = i;
						return iExpectedRow;
					}
					arrObjTableDetails[1] = "";
				}
			}
		} catch (NullPointerException e) {
			CommonLib.getLogger(strConfig).info("Received nullpointer exception");
			CommonLib.getLogger(strConfig).info(e.toString());
			return -1;
		} catch (StaleElementReferenceException e) {
			CommonLib.getLogger(strConfig).info("Received Stale Element exception");
			CommonLib.getLogger(strConfig).info(e.toString());
			return -1;
		} catch (NoSuchElementException e) {
			CommonLib.getLogger(strConfig).info("Received No Such Element exception");
			CommonLib.getLogger(strConfig).info(e.toString());
		}
		return -1;
	}

	/*
	 * Method Name: Description Input: Output: Developed By:
	 */
	int GetTableRowforCover(RemoteWebDriver driver, String strConfig, String strCoverName) {
		String[] arrObjDetails = ObjectRepository.GetObjectlocators("TBL_CoverSummary_SelectedCovers");
		String[] arrObjBodyDetails = arrObjDetails;
		arrObjBodyDetails[1] += "/tbody";
		By byDetails = ObjectRepository.GetObject(arrObjBodyDetails);
		WebElement objBodyTable = driver.findElement(byDetails);
		int totalCovers = TblRowCount(objBodyTable);
		CommonLib.getLogger(strConfig).info("Total Cover Rows found " + totalCovers);

		if (totalCovers == 1) {
			String[] arrRowDetails = ObjectRepository.GetObjectlocators("TBL_CoverSummary_SelectedCovers");
			arrRowDetails[1] += "/tbody/tr/td[1]";
			By byCoverRow = ObjectRepository.GetObject(arrRowDetails);
			WebElement cellCover = driver.findElement(byCoverRow);
			String strScreenCoverName = cellCover.getText();
			if (strScreenCoverName.equalsIgnoreCase(strCoverName)) {
				return 1;
			}
		} else {
			for (int i = 1; i <= totalCovers; i++) {
				String[] arrRowDetails = ObjectRepository.GetObjectlocators("TBL_CoverSummary_SelectedCovers");
				arrRowDetails[1] += "/tbody/tr[" + i + "]/td[1]";
				By byCoverRow = ObjectRepository.GetObject(arrRowDetails);
				WebElement cellCover = driver.findElement(byCoverRow);
				String strScreenCoverName = cellCover.getText();
				if (strScreenCoverName.equalsIgnoreCase(strCoverName)) {
					return i;
				}
			}
		}
		return -1;
	}

	/*
	 * Method Name: Description Input: Output: Developed By:
	 */
	int GetTableColumn(RemoteWebDriver driver, String strConfig, String TableName, String strColumnName) {

		int iExpectedCol = -1;

		String[] objLocators = ObjectRepository.GetObjectlocators(TableName);
		objLocators[1] = objLocators[1] + "/thead/tr";
		By byTableHeader = ObjectRepository.GetObject(objLocators);
		WebElement objHeaderTable = driver.findElement(byTableHeader);
		List<WebElement> TotalCols = objHeaderTable.findElements(By.tagName("th"));
		for (WebElement cellElement : TotalCols) {
			if (strColumnName.equalsIgnoreCase(cellElement.getText())) {
				iExpectedCol = TotalCols.indexOf(cellElement);
				return iExpectedCol + 1;
			}
		}
		return -1;
	}

	/*
	 * Method Name: SelectActionForTable Description Input: Output: Developed By:
	 * Stephen
	 */
	public Boolean SelectActionForTable(RemoteWebDriver driver, String strConfig, String strTableName,
			String strActionName, String strColName, int iRow) {
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

		try {
			Thread.sleep(Constants.intSmallDelay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		int iCol = GetTableColumn(driver, strConfig, strTableName, strColName);
		logger.info(strColName + " Column received: " + iCol);
		if (iCol < 0 || iRow < 0) {
			return false;
		}
		String[] objLocators = ObjectRepository.GetObjectlocators(strTableName);
		String strTblAction = objLocators[1] + "//tr[" + iRow + "]//span[contains(@class, 'glyphicon-menu-hamburger')]";
		WebElement tblAction = driver.findElement(By.xpath(strTblAction));
		try {
			new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(tblAction));
			new Actions(driver).moveToElement(tblAction).perform();
			Thread.sleep(1000);
			JSClick(driver, strConfig, tblAction);
		} catch (NoSuchElementException | InterruptedException e) {
			CommonLib.getLogger(strConfig)
					.info("Timeout exception on clicking Action Button on Table- " + strTableName);
			TestReporter.Info(driver, strConfig, "Timeout exception",
					"Timeout exception on clicking Action Button on Table- " + strTableName);
		}
		CommonLib.getLogger(strConfig).info("Looking for Action- " + strActionName);
		boolean blnActionLinkFound = false;
		WebElement lnkClick = null;
		try {
			lnkClick = driver.findElement(By.partialLinkText(strActionName));
			blnActionLinkFound = true;
		} catch (NoSuchElementException e) {
			blnActionLinkFound = false;
		}
		try {
			if (!blnActionLinkFound) {
				new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(tblAction));
				new Actions(driver).moveToElement(tblAction).perform();
				JSClick(driver, strConfig, tblAction);
				lnkClick = driver.findElement(By.partialLinkText(strActionName));
			}
			new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(lnkClick));
			new Actions(driver).moveToElement(lnkClick).perform();
			JSClick(driver, strConfig, lnkClick);
			CommonLib.waitForPageLoad(driver, strConfig);
			waitForLoad(driver, strConfig);
		} catch (TimeoutException e) {
			CommonLib.getLogger(strConfig)
					.info("Timeout exception on clicking Action- " + strActionName + " on Table- " + strTableName);
			TestReporter.Info(driver, strConfig, "Timeout exception",
					"Timeout exception on clicking Action- " + strActionName + " on Table- " + strTableName);
		}
		return true;
	}

	public Boolean LinkToElementInProductAdmin(RemoteWebDriver driver, String strConfig, String strTableName,
			String Value, String SearchByName) {
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

		try {
			Thread.sleep(Constants.intSmallDelay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (SearchByName.equalsIgnoreCase("ALL") && Value.equalsIgnoreCase("Link")) {
			WebElement SelectAll = driver.findElement(
					By.xpath(ObjectRepository.GetObjectlocators(strTableName)[1] + "/thead/tr/th[1]//input"));
			JSClick(driver, strConfig, SelectAll);
			JSClick(driver, strConfig, "BTN_ProductAdmin_Link");
			if (driver
					.findElements(
							By.xpath(ObjectRepository.GetObjectlocators("BTN_AddWordingDetail_ConfirmationYes")[1]))
					.size() != 0)
				CommonLib.clickOnUnhiddenButton(driver, strConfig, "BTN_AddWordingDetail_ConfirmationYes");
		}
		int totalRow = driver.findElements(By.xpath(ObjectRepository.GetObjectlocators(strTableName)[1] + "/tbody/tr"))
				.size();
		boolean found = false;
		int row = 0;
		String[] arrSearchValues = SearchByName.split(";");
		for (int index = 0; index < arrSearchValues.length; index++) {

			for (int j = 1; j <= totalRow; j++) {
				String ElementName = driver
						.findElement(By.xpath(
								ObjectRepository.GetObjectlocators(strTableName)[1] + "/tbody/tr[" + j + "]/td[2]/div"))
						.getText();
				if (ElementName.equalsIgnoreCase(arrSearchValues[index])) {
					found = true;
					row = j;
					break;
				}
				if (j == totalRow) {
					if (found == true)
						break;
					if (found == false) {
						TestReporter.Error(driver, strConfig, "May be wrong data entered ,Please Change TestData",
								"Unable to find by Search Element");
						return false;
					}
				}
			}

			if (found == true) {
				switch (Value) {
				case "Copy":
					WebElement ClickonView = driver.findElement(
							By.xpath(ObjectRepository.GetObjectlocators(strTableName)[1] + "/tbody/tr[" + row
									+ "]/td[5]/div//span[contains(@class,'glyphicon glyphicon-menu-hamburger')]"));
					JSClick(driver, strConfig, ClickonView);
					CommonLib.clickOnUnhiddenButton(driver, strConfig, "BTN_ProductAdmin_Copy");
					break;
				case "View":
					WebElement ClickonCopy = driver.findElement(
							By.xpath(ObjectRepository.GetObjectlocators(strTableName)[1] + "/tbody/tr[" + row
									+ "]/td[5]/div//span[contains(@class,'glyphicon glyphicon-menu-hamburger')]"));
					JSClick(driver, strConfig, ClickonCopy);
					CommonLib.clickOnUnhiddenButton(driver, strConfig, "BTN_ProductAdmin_View");
					break;
				case "Link":
					WebElement ClickonLink = driver
							.findElement(By.xpath(ObjectRepository.GetObjectlocators(strTableName)[1] + "/tbody/tr["
									+ row + "]/td[1]//input"));
					JSClick(driver, strConfig, ClickonLink);
					JSClick(driver, strConfig, "BTN_ProductAdmin_Link");
					if (driver
							.findElements(By.xpath(
									ObjectRepository.GetObjectlocators("BTN_AddWordingDetail_ConfirmationYes")[1]))
							.size() != 0)
						CommonLib.clickOnUnhiddenButton(driver, strConfig, "BTN_AddWordingDetail_ConfirmationYes");
					break;
				}

			}
		}
		return true;
	}

	/*
	 * Method Name: Description Input: Output: Developed By:
	 */
	public void CloseAllDriverAndIEInstances() {

		String processName = "iexplore.exe";
		if (isProcessRunning(processName)) {
			killProcess(processName);
		}

		try {
			Thread.sleep(Constants.intMediumDelay);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String driverProcess = "IEDriverServer_Win32_3.4.0.exe";
		killProcess(driverProcess);

	}

	public void CloseAllIEInstances(String strConfig) {
		Logger logger = getLogger(strConfig);
		logger.info("Inside  CloseAllIEInstances");
		try {
			String processName = "iexplore.exe";
			if (isProcessRunning(processName)) {
				killProcess(processName);
			}
			try {
				Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer_Win32_3.4.0.exe");
			} catch (Exception e) {
			}

			Thread.sleep(Constants.intSmallDelay);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			logger.error(e1.getMessage());
		}
	}

	/*
	 * Method Name: Description Input: Output: Developed By:
	 */
	public static boolean isProcessRunning(String serviceName) {
		Process p;
		try {
			p = Runtime.getRuntime().exec("TASKLIST");
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.contains(serviceName)) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/*
	 * Method Name: Description Input: Output: Developed By:
	 */
	public static void killProcess(String serviceName) {
		try {
			Runtime.getRuntime().exec("Taskkill /IM " + serviceName + " /F");
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	/*
	 * public void CloseAllIEInstances() { try { Process proc =
	 * Runtime.getRuntime().exec("iexplore.exe"); InputStream procOutput =
	 * (InputStream) proc.getInputStream(); try { if (0== proc.waitFor()) {
	 * proc.exitValue(); } } catch (InterruptedException e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); } } catch (IOException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } }
	 */

	public static void FolderCreate(String strConfig, String testCaseName) {
		// TODO Auto-generated method stub
		Logger logger = getLogger(strConfig);
		switch (strConfig) {
		case "Config1":
			logger.info("Inside FolderCreate");
			try {
				Test1.setFullScreenshotFilePath(Test1.getScreenshotFilePath() + "/" + testCaseName);
//				test1.FullScreenshotFilePath = Test1.ScreenshotFilePath + "/" + testCaseName ;				
				logger.info("FullScreenshotFilePath=" + Test1.getFullScreenshotFilePath());
				if (new File(Test1.getFullScreenshotFilePath()).exists()) {
					for (File file : new File(Test1.getFullScreenshotFilePath()).listFiles())
						file.delete();
				} else {
					new File(Test1.getFullScreenshotFilePath()).mkdirs();
				}
				Test1.iScreenNum = 1;
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			break;
		case "Config2":
			Test2 test2 = new Test2();
			logger.info("Inside FolderCreate");
			try {
				Test2.setFullScreenshotFilePath(Test2.getScreenshotFilePath() + "/" + testCaseName);
//				test2.FullScreenshotFilePath = Test2.ScreenshotFilePath + "/" + testCaseName ;				
				logger.info("FullScreenshotFilePath=" + Test2.getFullScreenshotFilePath());
				if (new File(Test2.getFullScreenshotFilePath()).exists()) {
					for (File file : new File(Test2.getFullScreenshotFilePath()).listFiles())
						file.delete();
				} else {
					new File(Test2.getFullScreenshotFilePath()).mkdirs();
				}
				Test2.iScreenNum = 1;
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			break;
		default:
			logger.info("Inside FolderCreate");
			try {
				Test1.setFullScreenshotFilePath(Test1.getScreenshotFilePath() + "/" + testCaseName);
//				test1.FullScreenshotFilePath = Test1.ScreenshotFilePath + "/" + testCaseName ;				
				logger.info("FullScreenshotFilePath=" + Test1.getFullScreenshotFilePath());
				if (new File(Test1.getFullScreenshotFilePath()).exists()) {
					for (File file : new File(Test1.getFullScreenshotFilePath()).listFiles())
						file.delete();
				} else {
					new File(Test1.getFullScreenshotFilePath()).mkdirs();
				}
				Test1.iScreenNum = 1;
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			break;
		}

	}

	public int DateDifference(String strPolicyStartDate, String strPolicyEndDate) throws java.text.ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dtPolicyStartDate = sdf.parse(strPolicyStartDate);
		Date dtPolicyEndDate = sdf.parse(strPolicyEndDate);

		long diffInMillies = Math.abs(dtPolicyEndDate.getTime() - dtPolicyStartDate.getTime());
		long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		return (int) (diff);
	}

	public static String setPastOrFutureDates(RemoteWebDriver driver, String strConfig, int days) {
		SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		Date date = new Date();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		String returnDate = sd.format(cal.getTime());
		System.out.println(returnDate);
		return returnDate;

	}

	public static Logger getLogger(String strConfig) {
		Logger logger = null;
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
		return logger;
	}

	public static XSSFWorkbook getOutputWorkbook(String strConfig) {
		XSSFWorkbook outputworkbook = null;
		switch (strConfig) {
		case "Config1":
			outputworkbook = Test1.getO_workbook1();
			break;
		case "Config2":
			outputworkbook = Test2.getO_workbook2();
			break;
		case "Config3":
			outputworkbook = Test3.getO_workbook3();
			break;
		case "Config4":
			outputworkbook = Test4.getO_workbook4();
			break;
		case "Config5":
			outputworkbook = Test5.getO_workbook5();
			break;
		default:
			outputworkbook = Test1.getO_workbook1();
			break;
		}
		return outputworkbook;
	}

	public static XSSFWorkbook getDriverWorkbook(String strConfig) {
		XSSFWorkbook driverworkbook = null;
		switch (strConfig) {
		case "Config1":
			driverworkbook = Test1.D_workbook;
			break;
		case "Config2":
			driverworkbook = Test2.D_workbook;
			break;
		case "Config3":
			driverworkbook = Test3.D_workbook;
			break;
		case "Config4":
			driverworkbook = Test4.D_workbook;
			break;
		case "Config5":
			driverworkbook = Test5.D_workbook;
			break;
		default:
			driverworkbook = Test1.D_workbook;
			break;
		}
		return driverworkbook;
	}

	public static XSSFWorkbook getTestDataWorkbook(String strConfig) {
		XSSFWorkbook TestDataworkbook = null;
		switch (strConfig) {
		case "Config1":
			TestDataworkbook = Test1.I_workbook;
			break;
		case "Config2":
			TestDataworkbook = Test2.I_workbook;
			break;
		case "Config3":
			TestDataworkbook = Test3.I_workbook;
			break;
		case "Config4":
			TestDataworkbook = Test4.I_workbook;
			break;
		case "Config5":
			TestDataworkbook = Test5.I_workbook;
			break;
		default:
			TestDataworkbook = Test1.I_workbook;
			break;
		}
		return TestDataworkbook;
	}

	public static String getFilePathwithName(String strConfig) {
		String strFilePathwithName = null;
		switch (strConfig) {
		case "Config1":
			strFilePathwithName = Test1.getStrFilePathwithName();
			break;
		case "Config2":
			strFilePathwithName = Test2.getStrFilePathwithName();
			break;
		case "Config3":
			strFilePathwithName = Test3.getStrFilePathwithName();
			break;
		case "Config4":
			strFilePathwithName = Test4.getStrFilePathwithName();
			break;
		case "Config5":
			strFilePathwithName = Test5.getStrFilePathwithName();
			break;
		}
		return strFilePathwithName;
	}

	public static WebDriver getDriver(String strConfig) {
		RemoteWebDriver testDriver;
		switch (strConfig) {
		case "Config1":
			return Test1.driver;
		case "Config2":
			return Test2.driver2;
		case "Config3":
			return Test3.driver3;
		case "Config4":
			return Test4.driver4;
		case "Config5":
			return Test5.driver5;
		default:
			return Test1.driver;
		}
	}

	public static String getTestCaseName(String strConfig) {
		switch (strConfig) {
		case "Config1":
			return Test1.getTestCaseName();
		case "Config2":
			return Test2.getTestCaseName();
		case "Config3":
			return Test3.getTestCaseName();
		case "Config4":
			return Test4.getTestCaseName();
		case "Config5":
			return Test5.getTestCaseName();
		default:
			return Test1.getTestCaseName();
		}
	}

	public static String getTestCaseDesc(String strConfig) {
		String strTestCaseDesc = null;
		switch (strConfig) {
		case "Config1":
			return Test1.getTestCaseDesc();
		case "Config2":
			return Test2.getTestCaseDesc();
		case "Config3":
			return Test3.getTestCaseDesc();
		case "Config4":
			return Test4.getTestCaseDesc();
		case "Config5":
			return Test5.getTestCaseDesc();
		default:
			return Test1.getTestCaseDesc();
		}
	}

	public static String getTestCaseModule(String strConfig) {
		String strTestCaseModule = null;
		switch (strConfig) {
		case "Config1":
			return Test1.getTestCaseModule();
		case "Config2":
			return Test2.getTestCaseModule();
		case "Config3":
			return Test3.getTestCaseModule();
		case "Config4":
			return Test4.getTestCaseModule();
		case "Config5":
			return Test5.getTestCaseModule();
		default:
			return Test1.getTestCaseModule();
		}
	}

	public static String getScreenshotPath(String strConfig) {
		String strScreenshotPath = null;
		switch (strConfig) {
		case "Config1":
			return Test1.getFullScreenshotFilePath();
		case "Config2":
			return Test2.getFullScreenshotFilePath();
		case "Config3":
			return Test3.getFullScreenshotFilePath();
		case "Config4":
			return Test4.getFullScreenshotFilePath();
		case "Config5":
			return Test5.getFullScreenshotFilePath();
		default:
			return Test1.getFullScreenshotFilePath();
		}
	}

	public static Integer iScreenNum(String strConfig) {
		int iScreenNum = 0;
		switch (strConfig) {
		case "Config1":
			return Test1.iScreenNum;
		case "Config2":
			return Test2.iScreenNum;
		case "Config3":
			return Test3.iScreenNum;
		case "Config4":
			return Test4.iScreenNum;
		case "Config5":
			return Test5.iScreenNum;
		default:
			return Test1.iScreenNum;
		}
	}

	private static void putScreenNum(String strConfig, int i) {
		// TODO Auto-generated method stub
		switch (strConfig) {
		case "Config1":
			Test1.iScreenNum = i;
			break;
		case "Config2":
			Test2.iScreenNum = i;
			break;
		case "Config3":
			Test3.iScreenNum = i;
			break;
		case "Config4":
			Test4.iScreenNum = i;
			break;
		case "Config5":
			Test5.iScreenNum = i;
			break;
		}
	}

	public static void setBlnStopCurrentTestCase(String strConfig, boolean b) {
		// TODO Auto-generated method stub
		switch (strConfig) {
		case "Config1":
			Test1.blnStopCurrentTestCase = b;
			break;
		case "Config2":
			Test2.blnStopCurrentTestCase = b;
			break;
		case "Config3":
			Test3.blnStopCurrentTestCase = b;
			break;
		case "Config4":
			Test4.blnStopCurrentTestCase = b;
			break;
		case "Config5":
			Test5.blnStopCurrentTestCase = b;
			break;
		default:
			Test1.blnStopCurrentTestCase = b;
			break;
		}
	}

	public static void clickOnUnhiddenButton(RemoteWebDriver driver, String strConfig, String Object) {
		try {
			String[] objLocators = ObjectRepository.GetObjectlocators(Object);
			By byBtnYes = ObjectRepository.GetObject(objLocators);
			List<WebElement> btnsYes = driver.findElements(byBtnYes);
			int btnMatchingNodes = btnsYes.size();
			for (int numberiterate = 0; numberiterate < btnMatchingNodes; numberiterate++) {
				WebElement ele_ToClick = driver.findElements(byBtnYes).get(numberiterate);
				int ele_ToClickXCoordinate = ele_ToClick.getLocation().getX();
				if (ele_ToClickXCoordinate != 0) {
					JavascriptExecutor oJse = (JavascriptExecutor) driver;
					oJse.executeScript("arguments[0].click();", ele_ToClick);
					CommonLib.waitForPageLoad(driver, strConfig);
					break;
				}
			}
		} catch (ElementNotVisibleException e) {
			e.getMessage();
		}
	}

	public static String DecryptPassword(String EncryptedPassword) {
		String decryptedPassword;
		byte[] decryptedPasswordBytes = Base64.getDecoder().decode(EncryptedPassword);
		decryptedPassword = new String(decryptedPasswordBytes);
		return decryptedPassword;
	}

	public LinkedHashMap<String, String> getReferencesFromTable(RemoteWebDriver driver, String strConfig,
			String tblName) {
		LinkedHashMap<String, String> objReferences = new LinkedHashMap<String, String>();
		String[] arrObjLocators = ObjectRepository.GetObjectlocators(tblName);
		int tblRows = RowCount(driver, strConfig, tblName);
		int index = 1;

		for (int iRow = 1; iRow <= tblRows; iRow++) {
			if (!ElementExist(driver, strConfig, arrObjLocators[1] + "/tbody/tr[" + iRow + "]/td[2]")) {
				continue;
			}
			String ref = GetCellValue(driver, strConfig, tblName, iRow, 2);
			objReferences.put("Ref_" + index, ref);
			index++;
		}
		return objReferences;
	}

	public LinkedHashMap<String, String> emptyDOValues(String strConfig, LinkedHashMap<String, String> DO_Name) {
		ArrayList<String> keysInDO = new ArrayList<>(DO_Name.keySet());
		int totalKeys = keysInDO.size();
		for (int iKey = 1; iKey < totalKeys; iKey++) {
			String key = keysInDO.get(iKey);
			String value = DO_Name.get(key);
			DO_Name.replace(key, "");
		}
		return DO_Name;
	}

	public void select_comboboxfromxpath(RemoteWebDriver driver, String strConfig, String xpath, String Value) {
		int attempts = 0;
		while (attempts < 3) {
			Select dropdown = new Select(driver.findElement(By.xpath(xpath)));
			new WebDriverWait(driver, 10)
					.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(xpath))));

			for (int iWait = 1; iWait <= 5; iWait++) {
				int totalElements = dropdown.getOptions().size();
				if (totalElements > 1) {
					break;
				} else {
					try {
						CommonLib.getLogger(strConfig).info("Waiting for options to load in dropdown:");
						Thread.sleep(Constants.intSmallDelay);
					} catch (InterruptedException e) {
					}
				}
			}

			if (Value.equalsIgnoreCase("SelectLast")) {
				try {
					dropdown.selectByIndex(1);
					break;
				} catch (NoSuchElementException e) {
					try {
						Thread.sleep(Constants.intSmallDelay);
						dropdown.selectByIndex(1);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				} catch (StaleElementReferenceException e) {
					attempts++;
				}
			} else if (Value.equalsIgnoreCase("SelectFirst")) {
				try {
					dropdown.selectByIndex(1);
					break;
				} catch (NoSuchElementException e) {
					try {
						Thread.sleep(Constants.intSmallDelay);
						dropdown.selectByIndex(1);
						break;
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				} catch (StaleElementReferenceException e) {
					attempts++;
				}
			} else if (Value.equalsIgnoreCase("SecondLast")) {
				try {
					dropdown.selectByIndex(dropdown.getOptions().size() - 2);
					break;
				} catch (NoSuchElementException e) {
					try {
						Thread.sleep(Constants.intSmallDelay);
						// by = ObjectRepository.GetObject(Arr_ObjectDetails);
						dropdown.selectByIndex(1);
						break;
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				} catch (StaleElementReferenceException e) {
					attempts++;
				}
			} else {
				try {
					CommonLib.waitForPageLoad(driver, strConfig);
					dropdown.selectByVisibleText(Value);
					break;
				} catch (NoSuchElementException e) {
					try {
						Thread.sleep(Constants.intSmallDelay);
						dropdown.selectByIndex(1);
						break;
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				} catch (StaleElementReferenceException e) {
					attempts++;
				} catch (JavaScriptException e) {
					// do nothing
				}
			}
		}
	}

	public static void Delay() {
		try {
			Thread.sleep(Constants.intDelay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void smallDelay() {
		try {
			Thread.sleep(Constants.intSmallDelay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void mediumDelay() {
		try {
			Thread.sleep(Constants.intMediumDelay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void highDelay() {
		try {
			Thread.sleep(Constants.intHighDelay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static String[] getLocatorDetails_From_ObjectRepository(RemoteWebDriver driver, String strConfig,
			String Header) {
		String[] arrObjectDetails = null;

		if (Header.isEmpty()) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", "Please supply Header ");
		} else {
			arrObjectDetails = ObjectRepository.GetObjectlocators(Header);

		}
		return arrObjectDetails;

	}

	// Function to wait for Spinner to disappear
	public static boolean isElementDisplayed(RemoteWebDriver driver, String strConfig, WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 1);

			Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver).withTimeout(40, TimeUnit.SECONDS)
					.pollingEvery(200, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);
			wait.until(ExpectedConditions.visibilityOf(element));
			return element.isDisplayed();
		} catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.StaleElementReferenceException
				| org.openqa.selenium.TimeoutException e) {
			return false;
		}
	}

	public static void waitForElementToBeGone(RemoteWebDriver driver, String strConfig, WebElement element,
			int timeout) {
		if (isElementDisplayed(driver, strConfig, element)) {
			new WebDriverWait(driver, timeout).until(ExpectedConditions.not(ExpectedConditions.visibilityOf(element)));
		}
	}

	public static boolean isElementPresent(RemoteWebDriver driver, String strConfig, String Header) {
		try {
			driver.findElement(By.xpath(Header));
			return true;
		} catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.StaleElementReferenceException
				| org.openqa.selenium.TimeoutException e) {
			return false;
		}
	}

	public static boolean isElementVisible(RemoteWebDriver driver, String strConfig, String Header) {
		try {
			driver.findElement(By.xpath(Header)).isDisplayed();
			return true;
		} catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.StaleElementReferenceException
				| org.openqa.selenium.ElementNotVisibleException | org.openqa.selenium.TimeoutException e) {
			return false;
		}
	}

	public static boolean isElementEnabled(RemoteWebDriver driver, String strConfig, String Header) {
		try {
			driver.findElement(By.xpath(Header)).isEnabled();
			return true;
		} catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.StaleElementReferenceException
				| org.openqa.selenium.ElementNotVisibleException | org.openqa.selenium.TimeoutException e) {
			return false;
		}
	}

	public static String getFirstWord(String text) {

		int index = text.indexOf(' ');

		if (index > -1) { // Check if there is more than one word.

			return text.substring(0, index).trim(); // Extract first word.

		} else {

			return text; // Text is the first word itself.
		}
	}

	// This Method gives Stale Element Exception. So don't use this Function
	// Instead use the Code in the Try block of this Function by passing the
	// Webelement
	public static void hoverCursor_OverElement(RemoteWebDriver driver, String strConfig, WebElement hoverTarget_Ele) {
		try {

			String[] arrObjectDetails = null;
			String javaScript = "var evObj = document.createEvent('MouseEvents');"
					+ "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
					+ "arguments[0].dispatchEvent(evObj);";

			((JavascriptExecutor) driver).executeScript(javaScript, hoverTarget_Ele);

		} catch (UnsupportedCommandException e) {
			TestReporter.Error(driver, strConfig, "Exception Thrown", e.getMessage());

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

	public static void changeSliderInterval(RemoteWebDriver driver, String strConfig, String header,
			String sliderInterval) {
		try {
			int slideIntervalCounter = 0;
			String[] arrLocator = ObjectRepository.GetObjectlocators(header);
			WebElement sliderEle = driver.findElement(By.xpath(arrLocator[1]));

			int sliderLimit = Integer.parseInt(sliderInterval);

			for (int i = 1; i <= sliderLimit; i++) {
				sliderEle.sendKeys(Keys.ARROW_RIGHT);
				slideIntervalCounter = slideIntervalCounter + 1;
			}

			if (slideIntervalCounter == sliderLimit) {
				TestReporter.Pass(driver, strConfig, "Slider ", "Interval Changed Successfully");
			}

			else {
				TestReporter.Pass(driver, strConfig, "Slider ", "Interval Change Issue");
			}

		}

		catch (NoSuchElementException e) {
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

}
