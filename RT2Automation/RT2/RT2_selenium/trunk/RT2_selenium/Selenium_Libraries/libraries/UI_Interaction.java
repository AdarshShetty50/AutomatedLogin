package libraries;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.Set;

import org.apache.bcel.Repository;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.util.Strings;

import com.gargoylesoftware.htmlunit.javascript.host.Element;
import com.relevantcodes.extentreports.ExtentReports;


import tests.Test1;
import tests.Test2;
import tests.Test3;
import tests.Test4;
import tests.Test5;

@SuppressWarnings("unused")
public class UI_Interaction {
	ReadExcel excel = new ReadExcel();
	Constants constants = new Constants();
	DictionaryObjects dictionaryObjects = new DictionaryObjects();
	ProjectConstants projectConstants = new ProjectConstants();
	CommonLib common = new CommonLib();
	ObjectRepository repository = new ObjectRepository();
	BusinessRules br = new BusinessRules();
	Project project = new Project();	
	

	public void NavigatetoPage(RemoteWebDriver driver, String strConfig, String strPageName){
		String strMenu = "1";
		String strPage = "1";
//		switch (strPageName) {
//		case "Manage Application":
//			strMenu = "1";
//			strPage = "1";
//			break;
//		case "Manage Broker":
//			strMenu = "1";
//			strPage = "2";
//			break;
//		case "Manage Member":
//			strMenu = "1";
//			strPage = "3";
//			break;
//		case "Manage Cover":
//			strMenu = "1";
//			strPage = "4";
//			break;
//		case "Manage Risk":
//			strMenu = "1";
//			strPage = "5";
//			break;
//		case "Manage Policy Clauses":
//			strMenu = "1";
//			strPage = "6";
//			break;
//		case "Manage Party":
//			strMenu = "1";
//			strPage = "7";
//			break;
//		case "Manage Subjectivities":
//			strMenu = "1";
//			strPage = "8";
//			break;
//		case "Preparation Summary":
//			strMenu = "1";
//			strPage = "9";
//			break;
//		case "Broker Commission":
//			strMenu = "2";
//			strPage = "1";
//			break;
//		case "Premium":
//			strMenu = "2";
//			strPage = "2";
//			break;
//		case "Instalments":
//			strMenu = "2";
//			strPage = "3";
//			break;
//		case "Premium Comments":
//			strMenu = "2";
//			strPage = "4";
//			break;
//		case "Renewal Information":
//			strMenu = "2";
//			strPage = "5";
//			break;
//		case "Pricing Summary":
//			strMenu = "2";
//			strPage = "6";
//			break;
//		case "Assessment":
//			strMenu = "3";
//			break;
//		case "Quotation":
//			strMenu = "4";
//			break;
//		}

//		WebElement lnDropdown = repository.GetObject(driver, strConfig, "LN_Menu_DropdownMenu");
//		lnDropdown.click();
//		//Menu Click
//		String[] arrMenuLocators = ObjectRepository.GetObjectlocators("LN_Menu_DropdownMenu");
//		if (!strPageName.equalsIgnoreCase("Assessment") && !strPageName.equalsIgnoreCase("Quotation")) {
//			String strMenuName = null;
//			if (strMenu == "1") {
//				strMenuName = "Preparation";
//			} else if (strMenu == "2") {
//				strMenuName = "Pricing";
//			}
//			driver.findElement(By.partialLinkText(strMenuName)).click();
//		}
//		//Page Click
//		driver.findElement(By.partialLinkText(strPageName)).click();
//		lnDropdown = null;
//		CommonLib.waitForPageLoad(driver, strConfig);
	}


	
	public static int daysBetween(Date dtPolicyStartDate, Date dtRenewalDate) {
		// TODO Auto-generated method stub
		Calendar calPolicyStartDate = Calendar.getInstance();
		calPolicyStartDate.setTime(dtPolicyStartDate);

		Calendar calPolicyEndDate = Calendar.getInstance();
		calPolicyEndDate.setTime(dtRenewalDate);

		LocalDate localStartDate = LocalDate.of(calPolicyStartDate.get(Calendar.YEAR), calPolicyStartDate.get(Calendar.MONTH), calPolicyStartDate.get(Calendar.DAY_OF_MONTH));
		LocalDate localEndDate = LocalDate.of(calPolicyEndDate.get(Calendar.YEAR), calPolicyEndDate.get(Calendar.MONTH), calPolicyEndDate.get(Calendar.DAY_OF_MONTH));

		Long days = ChronoUnit.DAYS.between(localStartDate, localEndDate);
		return days.intValue();
//		long days = ChronoUnit.DAYS.between(temporal1Inclusive, temporal2Exclusive)
//		return 0;
	}
		
	
}				

