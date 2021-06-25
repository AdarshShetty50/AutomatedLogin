package libraries;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;

public class ObjectRepository {

	//static CommonLib common = new CommonLib();

	public static By GetObject(String[] locatordetails) {
		By locators;
		String locatorType = locatordetails[0];
		String value = locatordetails[1];

		locators = CommonLib.locatorValue(locatorType, value);
		return locators;
	}

	public WebElement GetObject(RemoteWebDriver driver, String strConfig, String header) {
		String[] arrObjectLocators = GetObjectlocators(header);
		By byGetObject = GetObject(arrObjectLocators);
		CommonLib.waitForLoad(driver, strConfig);
		WebElement objElement = driver.findElement(byGetObject);
		return objElement;
	}

	public static List<WebElement> GetObjects(RemoteWebDriver driver, String strConfig, String header) {
		String[] arrObjectLocators = GetObjectlocators(header);
		By byGetObject = GetObject(arrObjectLocators);
		CommonLib.waitForLoad(driver, strConfig);
		List<WebElement> objElement = driver.findElements(byGetObject);
		return objElement;
	}

	public static String[] GetObjectlocators(String header) {
		// TODO Auto-generated method stub
		String[] locatordetails = new String[2];

		// add all the object details in this switch statement

		switch (header) {
		// -----------------------Header

		// ------RT 2.0 Objects ------------

		// ------- Login into Risk Tracker 2.0 Web Application------//
		case "BTN_Login_IntegroAccount":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//button[@type='button']/*[text()='Login With TYsers Account']";
			break;

		case "TXT_Login_IntegroUsername":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//input[@type='email']";
			break;

		case "BTN_Login_IntegroNext":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[@type='submit'][@value='Next']";
			break;

		case "TXT_Login_IntegroPassword":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//input[@id='passwordInput'][@type='password']";
			break;

		case "BTN_SignIn":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[@id='submitButton'][text()='Sign in']";
			break;

		case "LBL_Stay_SignedIn":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[contains(text(),'Stay signed in?')]";
			break;

		case "BTN_Stay_SignedIn_NO":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[@type='button'][@value='No']";
			break;
			
			
		case "LBL_InvalidLogin_UserId_Or_Password":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[@id='loginArea']//*[@id='errorText']";
			break;
			
			
			
			
			// ------- RT 2.0 Dashboard - Components ------//
		case "TXT_RiskDashboard_SearchRisk":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//input[@type='search' and contains(@id,'Search')]";
			break;
			
		case "DDO_RiskDashboard_RiskView_Selector":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[text()='new risk']/following-sibling::*//select/following-sibling::*";
			break;
			
		// Enter Department Value and click Enter
		case "DTI_RiskDashboard_RiskView_Selector":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[text()='new risk']/following-sibling::*//div[contains(@class,'choices__list--dropdown')]//input[@type='text' and @placeholder='Search']";
			break;
			
			
		case "DDO_RiskDashboard_RiskView_AccountExecutive":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[text()='new risk']/following-sibling::*/span//select/following-sibling::*";
			break;
			
		// Enter Department Value and click Enter
		case "DTI_RiskDashboard_RiskView_AccountExecutive":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[text()='new risk']/following-sibling::*/span//div[contains(@class,'choices__list--dropdown')]//input[@type='text' and @placeholder='Search']";
			break;
			
			
		case "LN_RiskDashboard_RiskCard_HeaderColor":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[@id='ListOfProducts']/a[contains(@href,'%s')]//*[contains(@class,'db-card-header')][contains(@style,'%s')]";
			break;
			
			
		case "LN_RiskDashboard_RiskCard_RiskRef":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[@id='ListOfProducts']/a[contains(@href,'%s')]";
			break;
			
			
		case "LBL_RiskDashboard_RiskCard_RiskRef":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[@id='ListOfProducts']/a[contains(@href,'%s')]//*[contains(@class,'db-card-ref')]//*[contains(text(),'%s')]";
			break;
			
		case "LBL_RiskDashboard_RiskCard_Client":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[@id='ListOfProducts']/a[contains(@href,'%s')]//*[contains(@class,'db-card-header')]//*[contains(text(),'%s')]";
			break;
			
		case "LBL_RiskDashboard_RiskCard_DateModified":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[@id='ListOfProducts']/a[contains(@href,'%s')]//*[contains(@class,'db-card-date')]//*[contains(text(),'%s')]";
			break;
		
		case "LBL_RiskDashboard_RiskCard_Status":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[@id='ListOfProducts']/a[contains(@href,'%s')]//*[contains(@id,'Status')]//*[contains(text(),'%s')]";
			break;
			
			
		case "LBL_RiskDashboard_RiskCard_StatusText":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[@id='ListOfProducts']/a[contains(@href,'%s')]//*[contains(@id,'Status')]//span";
			break;
			
		case "LBL_RiskDashboard_RiskCard_UMR":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[@id='ListOfProducts']/a[contains(@href,'%s')]//*[contains(@id,'UMR')]//*[contains(text(),'%s')]";
			break;
			
			
		case "LBL_RiskDashboard_RiskCard_UMR_NotGenerated":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[@id='ListOfProducts']/a[contains(@href,'%s')]//*[contains(@id,'UMR')]";
			break;
			
			
			
		case "LBL_RiskDashboard_RiskCard_InceptionDate":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[@id='ListOfProducts']/a[contains(@href,'%s')]//*[contains(@id,'InceptionDate')]//*[contains(text(),'%s')]";
			break;
			
		case "LBL_RiskDashboard_RiskCard_InsuredOrDirectInsuredOrReinsured":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[@id='ListOfProducts']/a[contains(@href,'%s')]//*[contains(@id,'Insured')]//*[contains(text(),'%s')]";
			break;
			
		case "LBL_RiskDashboard_RiskCard_Assured":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[@id='ListOfProducts']/a[contains(@href,'%s')]//*[contains(@id,'Assured')]//*[contains(text(),'%s')]";
			break; 				 
			
		case "LBL_RiskDashboard_RiskCard_RiskName":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[@id='ListOfProducts']/a[contains(@href,'%s')]//*[contains(@id,'RiskName')]//*[contains(text(),'%s')]";
			break;
			
			
		case "LBL_RiskDashboard_RiskCard_ClassOfBusiness":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[@id='ListOfProducts']/a[contains(@href,'%s')]//*[contains(@id,'Classofbusiness')]//*[contains(text(),'%s')]";
			break;
		
		case "LBL_RiskDashboard_RiskCard_ClassOfBusiness_Text":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[@id='ListOfProducts']/a[contains(@href,'%s')]//*[contains(@id,'Classofbusiness')]/span";
			break;
			
			
			
		case "BTN_RiskDashboard_Filter":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[contains(@class,'filter-button')]";
			break;
			
		case "LBL_RiskDashboard_Filter_Limit":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[@data-block='Patterns.FilterBlock']//*[contains(@id,'Limit')]";
			break;	
			
		case "LBL_RiskDashboard_Filter_Limit_LowerHandle":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[@data-block='Patterns.FilterBlock']//*[contains(@id,'Limit')]//*[@data-block='Interaction.RangeSliderInterval']//*[contains(@class,'noUi-handle-lower')]";
			break;	
			
		case "LBL_RiskDashboard_Filter_Limit_UpperHandle":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[@data-block='Patterns.FilterBlock']//*[contains(@id,'Limit')]//*[@data-block='Interaction.RangeSliderInterval']//*[contains(@class,'noUi-handle-upper')]";
			break;
			// ------- RT 2.0 Dashboard - Components ------//
			

		// ------- RT 2.0 Dashboard - Common Components ------//
		case "LN_UserSupport":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//a//*[text()='User Support']";
			break;

		//Active Organization Value will be coming from Excel and needs to be substituted in the xpath
		case "LN_Settings":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//a//*[contains(text(),'%s')]";
			break;
			
		case "LBL_Settings_Text":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[@class='popup-dialog']//*[contains(text(),'Settings')]";
			break;	
		
			
		case "CBO_Settings_ActiveOrganisation":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//label[text()='Select Active Organisation']/following-sibling::*[contains(@id,'Organisation')]//select[contains(@id,'Organisation')]";
			break;
			
		case "CBO_Settings_ActiveDivision":
			locatordetails[0] = "xpath";
			locatordetails[1] = "";
			break;
			
		case "BTN_Settings_Dropdown_ActiveDivision":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//label[text()='Select Active Division']/following-sibling::*//*[contains(@id, 'Dropdown_Department')]";
			break;	
			
		case "BTN_Settings_DropdownOption_ActiveDivision":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//label[text()='Select Active Division']/following-sibling::*//*[contains(@id, 'Dropdown_Department')]//*[contains(@class,'dropdown-popup-row')]/*[@class='list-item']//*[@class='columns-item']/*[text()='%s']";
			break;	
			
			
			
		case "BTN_Settings_Save":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[@class='popup-dialog']//*[contains(text(),'Settings')]/ancestor::*/following-sibling::*//button[text()='Save']";
			break;
			
			

		case "LN_Logout_IntegroAccount":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//a//*[text()='Log Out']";
			break;
			
		//-------Objects created as per Dev Env and Admin Login-----------//
		//------RT2 Dashboard----------------------//
		case "LN_RiskDashboard_AdminHomepage":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*/a[text()='Risk Dashboard']";
			break;
			
		case "LBL_RiskDashboard_AdminDashboardText":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[contains(@id,'Title')]/h1[text()='Admin Dashboard']";
			break;
			
			
		case "BTN_RiskDashboard_NewRisk":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*/button[text()='new risk']";
			break;
			
			
		// Menu Options available after clicking on the "New Risk" button on Dashboard
		// Or Opening an Existing Risk------- //
		case "LN_MenuItem_Dashboard":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[text()='Dashboard']/ancestor::a[@role='menuitem']";
			break;

		case "LN_MenuItem_RiskOverview":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[text()='Risk Overview']/ancestor::a[@role='menuitem']";
			break;
			
		case "LN_MenuItem_RiskDetails":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[text()='Risk Details']/ancestor::a[@role='menuitem']";
			break;
			
		case "LN_MenuItem_InstructionsSheet":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[text()='Instruction Sheet']/ancestor::a[@role='menuitem']";
			break;
			
		//-----Risk Overview tab Objects-------START-----//
		
		case "DDO_RiskOverview_NewOrRenewal_Classification":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[text()='New/Renewal Classification']/following-sibling::*//select/following-sibling::*";
			break;
			
		// Enter Department Value and click Enter
		case "DTI_RiskOverview_NewOrRenewal_Classification":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[text()='New/Renewal Classification']/following-sibling::div//div[contains(@class,'choices__list--dropdown')]//input[@type='text' and @placeholder='Search']";
			break;
			
		
		case "DDO_RiskOverview_Division":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[text()='Division']/following-sibling::*//select/following-sibling::*";
			break;
			
		case "LBL_RiskOverview_DivisionDropdown_DefaultOr_SelectedValue":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[text()='Division']/following-sibling::*//select/following-sibling::*/*[contains(text(),'%s')]";
			break;	
			

		// Enter Department Value and click Enter
		case "DTI_RiskOverview_Division":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//input[@type='text' and @placeholder='Select a department']";
			break;
						
			//-----	Agent/Client Name and Agent/Client Account No will be coming from Excel and needs to be substituted in the xpath	
		case "TXT_RiskOverview_AgentOrClient":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[text()='Client']/ancestor::label/following-sibling::*//input[@type='search']";
			break;	
			
		case "STO_RiskOverview_AgentOrClient":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[text()='Client']//ancestor::label/ancestor::*/following-sibling::*//*[text()='%s']/ancestor::*/following-sibling::*/*[text()='%s']";
			break;	
			
		case "LBL_RiskOverview_AgentOrClient_MandatoryField":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[text()='Client']/ancestor::*[contains(@class,'mandatory')]";
			break;		
			
		case "NAV_RiskOverview_AgentOrClient_Tooltip_Address":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[contains(@id,'Tooltip')]/span[text()='%s']";
			break;
			
		case "LBL_RiskOverview_UnapprovedClient_SaveErr_Msg":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[@class='popup-dialog']//*[contains(text(),' This Agent/Client has not been authorised - please ensure relevant approval is obtained before Firm Order.')]";
			break;
			
		case "BTN_RiskOverview_UnapprovedClient_SaveErr_MsgDialog_OKBtn":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[@class='popup-dialog']//*[contains(text(),' This Agent/Client has not been authorised - please ensure relevant approval is obtained before Firm Order.')]/ancestor::*[contains(@id, 'Header')]/following-sibling::*//button[text()='OK']";
			break;	
			
			
			
			
			
			
		case "CHK_RiskOverview_DirectInsured_Placement_ONOFF_Switch":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//input[@type='checkbox' and contains(@id,'DirectInsurancePlacement')]";
			break;
			
		case "CHK_RiskOverview_Reinsurance_ONOFF_Switch":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//input[@type='checkbox' and contains(@id,'Reinsurance')]";
			break;
			
		case "TXT_RiskOverview_Insured_Or_DirectInsured_Or_OriginalInsured":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//input[@type='text' and contains(@id,'Insured')]";
			break;
			
		case "LBL_RiskOverview_Insured_Or_DirectInsured_Or_OriginalInsured":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//label[contains(@for,'Insured')]/span";
			break;
			
			
			
		case "TXT_RiskOverview_Reinsured":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//input[@type='text' and contains(@id,'Reinsured')]";
			break;
				
		case "TXT_RiskOverview_Assured":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//input[@type='text' and contains(@id,'Assured')]";
			break;
			
		case "DDO_RiskOverview_PCP":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[contains(text(),'PCP')]/ancestor::*/following-sibling::*//select/following-sibling::*";
			break;
			
		case "DTI_RiskOverview_PCP":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[contains(text(),'PCP')]/ancestor::*/following-sibling::*//*[contains(@class,'choices__list--dropdown')]//input[@type='text' and @placeholder='Search']";
			break;
			
		case "LBL_RiskOverview_PCP_MandatoryField":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[contains(text(),'PCP')]/ancestor::*[contains(@class,'mandatory')]";
			break;
			
		//--Click to open Account Executive Dropdown Options--
		case "DDO_RiskOverview_AccountExecutive":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[text()='Account Executive']/ancestor::*/following-sibling::*//select/following-sibling::*";
			
			break;
					
		//Enter Account Executive Value and click Enter 
		case "DTI_RiskOverview_AccountExecutive":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[text()='Account Executive']/ancestor::*/following-sibling::*//*[contains(@class,'choices__list--dropdown')]//input[@type='text' and @placeholder='Search']";
			break;	
			
		case "LBL_RiskOverview_AccountExecutive_MandatoryField":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[text()='Account Executive'][contains(@class,'mandatory')]";
			
			break;	
		
		// --Click to open Internal Broker Dropdown Options--
		case "DDO_RiskOverview_PlacingBroker":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[text()='Placing Broker']/ancestor::*/following-sibling::*//select/following-sibling::*";
			break;

		// Enter Internal Broker Value and click Enter
		case "DTI_RiskOverview_PlacingBroker":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[text()='Placing Broker']/ancestor::*/following-sibling::div//div[contains(@class,'choices__list--dropdown')]//input[@type='text' and @placeholder='Search']";
			break;
			
		case "LBL_RiskOverview_PlacingBroker_MandatoryField":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[text()='Placing Broker'][contains(@class,'mandatory')]";
			break;	
			
		
		case "BTN_Cancel":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//button[text()='Cancel']";
			break;
			
		case "BTN_Save":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[text()='Save']/ancestor::button";
			break;
			
			
		//------	After Risk Creation is Complete
			
		// Blank UMR field	
		case "TXT_RiskOverview_UMR":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//label[text()='UMR']/following-sibling::*//input[@type='text'][contains(@id,'Umr')]";
			break;
			
		case "TXT_RiskOverview_UMR_Disabled":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//label[text()='UMR']/following-sibling::*//input[@type='text'][contains(@id,'Umr')][@disabled]";
			break;	
			
			
			
		
			
		//ACTION Buttons
		case "BTN_Actions_NTU":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[text()='Actions']/following-sibling::*//button[text()='NTU']";
			break;
			
		case "BTN_Actions_NTU_Disabled":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[text()='Actions']/following-sibling::*//button[text()='NTU'][@disabled]";
			break;
			
		case "BTN_Actions_FirmOrder":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[text()='Actions']/following-sibling::*//button[text()='Firm Order']";
			break;
			
		case "BTN_Actions_FirmOrder_Disabled":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[text()='Actions']/following-sibling::*//button[text()='Firm Order'][@disabled]";
			break;
			
		case "BTN_Actions_SubmitTo_BrokerOps":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[text()='Actions']/following-sibling::*//button[text()='Submit to Broker Ops']";
			break;
			
		case "BTN_Actions_SubmitTo_BrokerOps_Disabled":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[text()='Actions']/following-sibling::*//button[text()='Submit to Broker Ops'][@disabled]";
			break;
			
			
		case "BTN_Actions_Dms_Documents":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[text()='Actions']/following-sibling::*//a/*[text()='DMS Documents']";
			break;
			
		case "BTN_Actions_Reinstate":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[text()='Actions']/following-sibling::*//button[text()='Re-instate']";
			break;
			
		case "BTN_Actions_Notes":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[text()='Actions']/following-sibling::*//*[contains(text(),'General Notes')]/ancestor::a";
			break;	
			
		
		case "BTN_Actions_Copy":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[text()='Actions']/following-sibling::*//button[text()='Copy']";
			break;	
			
		case "BTN_Actions_Renew":
			locatordetails[0] = "xpath";
			locatordetails[1] = "//*[text()='Actions']/following-sibling::*//*[text()='Renew']/ancestor::button";
			break;
			
			
			
			
			
			
			
			
			//-----Risk Overview tab Objects-------END-----//
			
			
			
			//-------Risk Details tab Objects ------------ START---------//
			
			
			//-- Input Policy type (to be removed for release 2.2)
			case "TXT_RiskDetails_PolicyType":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//input[@type='text' and contains(@id,'PolicyType')]";
				break;
				
			case "TXT_RiskDetails_PolicyType_Disabled":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//input[@type='text' and contains(@id,'PolicyType')][@disabled]";
				break;	
				
			case "LBL_RiskDetails_PolicyType_MandatoryField":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//*[text()='Policy Type'][contains(@class,'mandatory')]";
				break;
				
			
			// --Click to open Major Class of Business Combobox--
			case "DDO_RiskDetails_MajorClass_OfBusiness":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//*[text()='Major Class of Business']/following-sibling::div//select/following-sibling::div";
				break;

			// Enter Major Class of Business Value and click Enter
			case "DTI_RiskDetails_MajorClass_OfBusiness":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//*[text()='Major Class of Business']/following-sibling::div//div[contains(@class,'choices__list--dropdown')]//input[@type='text' and @placeholder='Search']";
				break;
				
				
			// --Click to open Minor Class of Business Combobox--
			case "DDO_RiskDetails_MinorClass_OfBusiness":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//*[text()='Minor Class of Business']/following-sibling::div//select/following-sibling::div";

				break;

			// Enter Minor Class of Business Value and click Enter
			case "DTI_RiskDetails_MinorClass_OfBusiness":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//*[text()='Minor Class of Business']/following-sibling::div//div[contains(@class,'choices__list--dropdown')]//input[@type='text' and @placeholder='Search']";
				break;
				
				
			// --Click to open Premium Currency Code Combobox--
			case "DDO_RiskDetails_PremiumCurrency_Code":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//*[text()='Premium Currency Code']/ancestor::*/following-sibling::*//select/following-sibling::*";
				break;
				

			// Enter Premium Currency Code Value and click Enter
			case "DTI_RiskDetails_PremiumCurrency_Code":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//*[text()='Premium Currency Code']/ancestor::div/following-sibling::div//div[contains(@class,'choices__list--dropdown')]//input[@type='text' and @placeholder='Search']";
				break;
				
				
			// Enter Limit Amount
			case "TXT_RiskDetails_Limit":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//input[@type='text' and contains(@id,'LimitAmount')]";
				break;
			
			// Enter Premium Amount
			case "TXT_RiskDetails_Premium":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//input[@type='text' and contains(@id,'PremiumAmount')]";
				break;
				
			// Enter Deductible Amount
			case "TXT_RiskDetails_Deductible":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//input[@type='text' and contains(@id,'DeductibleAmount')]";
				break;
				
				
			// Enter Client Commission
			case "TXT_RiskDetails_ClientCommission":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//input[@type='text' and contains(@id,'ClientCommission')]";
				break;
				
			// Enter Information
			case "TXT_RiskDetails_Information":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//textarea[contains(@id,'Information')]";
				break;
			
			// Enter Information
			case "TXT_RiskDetails_Terms":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//textarea[contains(@id,'Terms')]";
				break;
				
			case "BTN_NBI":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//*[text()='NBI']/ancestor::button";
				break;	
				
				
				
				
				
			// -------Risk Details tab Objects ------------ START---------//
				
				
				
			//--------NTU----------//
			case "BTN_NTU_Cancel":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//*[@id='NtuPopup']//button[text()='Cancel']";
				break;
				
			case "BTN_NTU_Confirm":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//*[@id='NtuPopup']//button[@type='submit']";
				break;
				
			case "DDO_NTU_Reason":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//*[@id='NtuPopup']//label[text()='Reason']/following-sibling::*[contains(@id,'Dropdown_Reason')]";
				break;
				
			case "DDO_NTU_ReasonOption":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//*[contains(@class,'dropdown-popup-row')]//*[text()='%s']";
				break;
				
				
			//Disabled Fields - Risk Overview Tab
				
			case "CBO_RiskOverview_NewOrRenewal_Classification_Disabled":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//*[text()='New/Renewal Classification']/following-sibling::*//*[@role='combobox'][@aria-disabled='true']";
				break;
				
			case "CBO_RiskOverview_Department_Disabled":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//*[text()='Department']/following-sibling::*//*[@role='combobox'][@aria-disabled='true']";
				break;
				
			case "CBO_RiskOverview_Division_Disabled":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//*[text()='Division']/following-sibling::*//*[@role='combobox'][@aria-disabled='true']";
				break;
				
			case "TXT_RiskOverview_AgentOrClient_Disabled":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//*[text()='Client']/ancestor::label/following-sibling::*//input[@type='search'][@disabled]";
				break;	
				
				
			case "CHK_RiskOverview_DirectInsured_Placement_ONOFF_Switch_Disabled":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//input[@type='checkbox' and contains(@id,'DirectInsurancePlacement')][@disabled]";
				break;
				
			case "CHK_RiskOverview_Reinsurance_ONOFF_Switch_Disabled":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//input[@type='checkbox' and contains(@id,'Reinsurance')][@disabled]";
				break;
				
			case "TXT_RiskOverview_Insured_Or_DirectInsured_Or_OriginalInsured_Disabled":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//input[@type='text' and contains(@id,'Insured')][@disabled]";
				break;
				
			case "TXT_RiskOverview_Reinsured_Disabled":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//input[@type='text' and contains(@id,'Reinsured')][@disabled]";
				break;
					
			case "TXT_RiskOverview_Assured_Disabled":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//input[@type='text' and contains(@id,'Assured')][@disabled]";
				break;
				
				
				
				
				
			case "CBO_RiskOverview_AccountExecutive_Disabled":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//*[text()='Account Executive']/ancestor::*/following-sibling::*//*[@role='combobox'][@aria-disabled='true']";
				break;
				
			
			case "CBO_RiskOverview_InternalBroker_Disabled":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//*[text()='Internal Broker']/following-sibling::*//*[@role='combobox'][@aria-disabled='true']";
				break;
				
			case "CBO_RiskOverview_PlacingBroker_Disabled":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//*[text()='Placing Broker']/ancestor::*/following-sibling::*//*[@role='combobox'][@aria-disabled='true']";
				break;
				
			case "BTN_Save_Disabled":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//*[text()='Save']/ancestor::button[@disabled]";
				break;
				
			case "BTN_NBI_Disabled":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//*[text()='NBI']/ancestor::button[@disabled]";
				break;
				
				
			//Disabled Fields - Risk Details Tab
			case "CBO_RiskDetails_PremiumCurrency_Code_Disabled":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//*[text()='Premium Currency Code']/ancestor::*/following-sibling::*//*[@role='combobox'][@aria-disabled='true']";
				break;
				
			
			// Limit Amount
			case "TXT_RiskDetails_Limit_Disabled":
				locatordetails[0] = "xpath";
				locatordetails[1] = "// input[@type='text' and contains(@id,'LimitAmount')][@disabled]";
				break;

			// Premium Amount
			case "TXT_RiskDetails_Premium_Disabled":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//input[@type='text' and contains(@id,'PremiumAmount')][@disabled]";
				break;

			// Deductible Amount
			case "TXT_RiskDetails_Deductible_Disabled":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//input[@type='text' and contains(@id,'DeductibleAmount')][@disabled]";
				break;

			// Client Commission
			case "TXT_RiskDetails_ClientCommission_Disabled":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//input[@type='text' and contains(@id,'ClientCommission')][@disabled]";
				break;

			// Information
			case "TXT_RiskDetails_Information_Disabled":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//textarea[contains(@id,'Information')][@disabled]";
				break;

			// Terms
			case "TXT_RiskDetails_Terms_Disabled":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//textarea[contains(@id,'Terms')][@disabled]";
				break;

				
				
			// --------NTU----------//
			case "TXT_DMS_Username":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//*[@id='otds_username']";
				break;
				
				
			case "TXT_DMS_Password":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//*[@id='otds_password']";
				break;
				
			case "BTN_DMS_SignIn":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//*[@type='submit'][@id='loginbutton'][@value='Sign in']";
				break;
				
			case "TXT_DMS_Search":
				locatordetails[0] = "xpath";
				//locatordetails[1] = "//*[@type='search'][@id='fulltextwhere1'][@title='Enter search terms in the form of keywords']";
				locatordetails[1] = "//*[@type='search'][@id='fulltextwhere1']";
				break;
				
				
				

			// --------DMS--------//
			

			// --------DMS--------//
				
			//-----Firm order------//
			case "TXT_FirmOrder_AgentOrClient_Disabled":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//*[@id='FirmOrderPopup']//*[text()='Agent/Client']/ancestor::label/following-sibling::*//input[@type='search'][@disabled]";
				break;
				
			case "TXT_FirmOrder_InceptionDate":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//*[@id='FirmOrderPopup']//input[@type='date'][contains(@id,'InceptionDate')]";
				break;
				
				
			case "TXT_FirmOrder_ExpiryDate":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//*[@id='FirmOrderPopup']//input[@type='date'][contains(@id,'ExpiryDate')]";
				break;
				
				
			case "TXT_FirmOrder_SDD":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//*[@id='FirmOrderPopup']//input[@type='date'][contains(@id,'SDD')]";
				break;
				
				
			case "BTN_FirmOrder_PPW":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//*[@id='FirmOrderPopup']//input[@type='checkbox'][contains(@id,'Switch_Ppw')]";
				break;
				
				
			case "BTN_FirmOrder_Cancel":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//*[@id='FirmOrderPopup']//button[text()='Cancel']";
				break;
				
				
			case "BTN_FirmOrder_Confirm":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//*[@id='FirmOrderPopup']//button[@type='submit']";
				break;
				
			case "LBL_FirmOrder_DMS_SuccessMessage":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//*[text()='DMS checked sucessfully.']";
				break;
				
			case "TXT_SubmitToBrokerOps_SDD":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//*[@id='SubmitPopup']//input[@type='date'][contains(@id,'SDD')]";
				break;	
				
				
				
				//-----Firm order------//
				
				
				//-----Submit To Broker Ops-----//
				
			case "BTN_SubmitToBrokerOps_Cancel":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//*[@id='SubmitPopup']//button[text()='Cancel']";
				break;
				
				
			case "BTN_SubmitToBrokerOps_Confirm":
				locatordetails[0] = "xpath";
				locatordetails[1] = "//*[@id='SubmitPopup']//button[@type='submit']";
				break;
				
				//-----Submit To Broker Ops-----//
				
				
				
				//-----Copy Risk-----//

				case "BTN_CopyRisk_No":
					locatordetails[0] = "xpath";
					locatordetails[1] = "//*[@id='CopyConfirmationPopup']//button[text()='No']";
					break;

				case "BTN_CopyRisk_Yes":
					locatordetails[0] = "xpath";
					locatordetails[1] = "//*[@id='CopyConfirmationPopup']//button[text()='Yes']";
					break;
				
				//-----Copy Risk-----//
					
					
					
					//Validation Message
					
					
					
						
					
				//Risk Overview Page Validation Required Fields
				case "LBL_RiskOverview_Client_RequiredValidation_Msg":
					locatordetails[0] = "xpath";
					locatordetails[1] = "//*[text()='Client']/ancestor::label/following-sibling::span[@class='input-search']/*[text()='Required field!']";
					break;	
					
					
				case "LBL_RiskOverview_Assured_RequiredValidation_Msg":
					locatordetails[0] = "xpath";
					locatordetails[1] = "//input[@type='text' and contains(@id,'Assured')]/following-sibling::*[text()='Please enter a (Re-)Insured or Assured name']";
					break;
					
					
				case "LBL_RiskOverview_PCP_RequiredValidation_Msg":
					locatordetails[0] = "xpath";
					locatordetails[1] = "//*[contains(text(),'PCP')]/ancestor::label/following-sibling::*//*[text()='Required field!']";
					break;
				
				case "LBL_RiskOverview_PlacingBroker_RequiredValidation_Msg":
					locatordetails[0] = "xpath";
					locatordetails[1] = "//*[text()='Placing Broker']/ancestor::*/following-sibling::*//*[text()='Required field!']";
					break;
					
					
				//Firm Order PopUp Validation Required Fields
				case "LBL_FirmOrder_SDD_RequiredValidation_Msg":
					locatordetails[0] = "xpath";
					locatordetails[1] = "//*[contains(id,SDD)]/*[text()='Required field!']";
					break;
					
					
				//Risk Details Page Validation Required Fields
				case "LBL_RiskDetails_PolicyType_RequiredValidation_Msg":
					locatordetails[0] = "xpath";
					locatordetails[1] = "//input[@type='text' and contains(@id,'PolicyType')]/following-sibling::*[text()='Required field!']";
					break;	
						
						
				case "LBL_RiskDetails_ClientCommision_RequiredValidation_Msg":
					locatordetails[0] = "xpath";
					locatordetails[1] = "//input[@type='text' and contains(@id,'ClientCommission')]/following-sibling::*[text()='Commission must be between 10% and 35%']";
					break;	
				
				case "BTN_ErrorMsg_IconCancel":
					locatordetails[0] = "xpath";
					locatordetails[1] = "//*[@id='feedbackMessageContainer']/*[contains(@class,'feedback-message')]/i";
					break;
					
					
					
					
		//--------------DMS--------------//
					
		
			
					
					
					
		//--------------DMS--------------//
		// ------- Admin HomePage - Risk Tracker 2.0 Web Application------//
		

		// ------RT 2.0 Objects ------------

		default:
			Reporter.log("Object not defined for - " + header);
		}

		return locatordetails;
	}

}
