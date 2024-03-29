package libraries;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Constants {

	//-------------RT constants---------------//
	public static String strRTURL = null;
	public static String strEnvironment = null;
	public static String strScreenShots = null;
	public static String strAutomationReportReceiver = null;

	public static Integer intDelay = 1000;
	public static Integer intSmallDelay = 2000;
	public static Integer intMediumDelay = 5000;
	public static Integer intHighDelay = 10000;
	public static Integer intMaxAttempts = 10; 
	
	
	public static String defaultActiveOrg = "Tysers Insurance Brokers Limited";
	
	public static List<String> Dashboard_RiskView = Arrays.asList("All Expiring Risks By Account Executive", 
			"All Risks By Account Executive", "My Default View",
			"My Expiring Risks", "Risks Requiring Action");
	
	
	public static String DMSLoginPageTitle = "OpenText Authentication Service";
	
	public static String loadingSpinner = "//*[contains(@class, 'Spinner')]";
	
	public static String riskTrackerRefNo_XPATH = "//h3[text()='Risk Tracker:']/span";
	
	public static String ExpiringUMR_XPATH = "//h3[text()='Expiring UMR']/span";
	
	
	
	public static String incorrectUserIdOrPwd_ErrorMsg = "Incorrect user ID or password. Type the correct user ID and password, and try again.";
	
	public static String UnapprovedClient_Save_ErrMsg = " This Agent/Client has not been authorised - please ensure relevant approval is obtained before Firm Order.";
	
	// Risk Dashboard
	public static String RiskCard_RiskRefNo= "//*[@id='ListOfProducts']/a[contains(@href,'%s')]//*[contains(@class,'db-card-ref')]//*[contains(text(),'%s')]";
	
	public static String agentOrClient_SearchText_Output_XPATH = "//*[text()='Agent/Client']//ancestor::label/ancestor::div/following-sibling::div//*[text()='%s']/ancestor::div/following-sibling::div/*[text()='%s']";
	
	public static String RGB_Grey = "rgb(121, 121, 121)"; 
	public static String RGB_Blue = "rgb(0, 90, 166)";
	public static String RGB_Green = "rgb(86, 180, 113)"; 
	public static String RGB_Red = "rgb(241, 77, 77)"; 
	
	
	
	//----Currently Not Used-----
	public static String riskOverviewStatus_XPATH = "//*[@id='StatusCard']//*[text()='Status']";
	public static String riskOverviewStageTitle_XPATH = "//*[@id='StatusCard']//*[text()='Stage Title:']";
	public static String riskOverviewActionDate_XPATH = "//*[@id='StatusCard']//*[text()='Action Date:']";
	
	public static String riskOverview_StageTitle_Created_XPATH = "//*[text()='Stage Title:']//ancestor::*/following-sibling::*//*[text()='Created']";
	public static String riskOverview_StageTitle_NBI_XPATH = "//*[text()='Stage Title:']//ancestor::*/following-sibling::*//*[text()='NBI']";
	public static String riskOverview_StageTitle_Quote_XPATH = "//*[text()='Stage Title:']//ancestor::*/following-sibling::*//*[text()='Quote']";
	public static String riskOverview_StageTitle_FirmOrder_XPATH = "//*[text()='Stage Title:']//ancestor::*/following-sibling::*//span[text()='Firm Order']";
	public static String riskOverview_StageTitle_SubmittedTo_Admin_XPATH = "//*[text()='Stage Title:']//ancestor::*/following-sibling::*//*[text()='Submitted to Admin']";
	public static String riskOverview_StageTitle_NTU_XPATH = "//*[text()='Stage Title:']//ancestor::*/following-sibling::*//*[text()='Created']";
	//----Currently Not Used-----
	
	
	public static String firmOrder_Btn_Disabled_XPATH = "//*[text()='Actions']/following-sibling::*//button[text()='Firm Order'][@disabled]";
	
	//------- Stage Icon Checked and Unchecked -> Risk Overview Tab
	public static String riskOverview_Created_StageIcon_Checked_XPATH = "//*[text()='Created']/ancestor::*/preceding-sibling::div[contains(@class,'status-line')]//i[contains(@class,'check')]";
	public static String riskOverview_Created_StageIcon_UnChecked_XPATH = "//*[text()='Created']/ancestor::*/preceding-sibling::div[contains(@class,'status-line')]//i[contains(@class,'remove')]";
	
	public static String riskOverview_NBI_StageIcon_Checked_XPATH = "//*[text()='NBI']/ancestor::*/preceding-sibling::div[contains(@class,'status-line')]//i[contains(@class,'check')]";
	public static String riskOverview_NBI_StageIcon_UnChecked_XPATH = "//*[text()='NBI']/ancestor::*/preceding-sibling::div[contains(@class,'status-line')]//i[contains(@class,'remove')]";
	public static String riskOverview_NBI_StageIcon_Disabled_XPATH = "//*[text()='NBI']/ancestor::*/preceding-sibling::div[contains(@class,'status-line')]//i[contains(@class,'minus')]";
	
	public static String riskOverview_Quote_StageIcon_Checked_XPATH = "//*[text()='Quote']/ancestor::*/preceding-sibling::div[contains(@class,'status-line')]//i[contains(@class,'check')]";
	public static String riskOverview_Quote_StageIcon_UnChecked_XPATH = "//*[text()='Quote']/ancestor::*/preceding-sibling::div[contains(@class,'status-line')]//i[contains(@class,'remove')]";
	public static String riskOverview_Quote_StageIcon_Disabled_XPATH = "//*[text()='Quote']/ancestor::*/preceding-sibling::div[contains(@class,'status-line')]//i[contains(@class,'minus')]";
	
	public static String riskOverview_FirmOrder_StageIcon_Checked_XPATH = "//*[text()='Firm Order']/ancestor::*/preceding-sibling::div[contains(@class,'status-line')]//i[contains(@class,'check')]";
	public static String riskOverview_FirmOrder_StageIcon_UnChecked_XPATH = "//*[text()='Firm Order']/ancestor::*/preceding-sibling::div[contains(@class,'status-line')]//i[contains(@class,'remove')]";
	public static String riskOverview_FirmOrder_StageIcon_Disabled_XPATH = "//*[text()='Firm Order']/ancestor::*/preceding-sibling::div[contains(@class,'status-line')]//i[contains(@class,'minus')]";
	
	public static String riskOverview_SubmittedTo_BrokerOps_StageIcon_Checked_XPATH = "//*[text()='Submitted to Broker Ops']/ancestor::*/preceding-sibling::div[contains(@style,'z-index')]//i[contains(@class,'check')]";
	public static String riskOverview_SubmittedTo_BrokerOps_StageIcon_UnChecked_XPATH = "//*[text()='Submitted to Broker Ops']/ancestor::*/preceding-sibling::div[contains(@style,'z-index')]//i[contains(@class,'remove')]";
	public static String riskOverview_SubmittedTo_BrokerOps_StageIcon_Disabled_XPATH = "//*[text()='Submitted to Broker Ops']/ancestor::*/preceding-sibling::div[contains(@style,'z-index')]//i[contains(@class,'minus')]";
	
	public static String riskOverview_NTU_StageIcon_Checked_XPATH = "//*[text()='NTU']/ancestor::*/preceding-sibling::div[contains(@style,'z-index')]//i[contains(@class,'check')]";
	//------- Stage Icon Checked and Unchecked -> Risk Overview Tab
	
	//------- Date Populated against the Stage -> Risk Overview Tab
	public static String riskOverview_Created_Stage_Date_Populated_XPATH = "//*[text()='Created']/ancestor::div/following-sibling::div[contains(@style,'text-align: right')]//span";
	public static String riskOverview_NBI_Stage_Date_Populated_XPATH = "//*[text()='NBI']/ancestor::div/following-sibling::div[contains(@style,'text-align: right')]//span";
	public static String riskOverview_Quote_Stage_Date_Populated_XPATH = "//*[text()='Quote']/ancestor::div/following-sibling::div[contains(@style,'text-align: right')]//span";
	public static String riskOverview_FirmOrder_Stage_Date_Populated_XPATH = "//*[text()='Firm Order']/ancestor::div/following-sibling::div[contains(@style,'text-align: right')]//span";
	public static String riskOverview_SubmitToAdmin_Stage_Date_Populated_XPATH = "//*[text()='Submitted to Broker Ops']/ancestor::div/following-sibling::div[contains(@style,'text-align: right')]//span";
	public static String riskOverview_NTU_Stage_Date_Populated_XPATH = "//*[text()='NTU']/ancestor::div/following-sibling::div[contains(@style,'text-align: right')]//span";
	//------- Date Populated against the Stage -> Risk Overview Tab
	
	
	//Success and Error Messages
	public static String NotifMsg_XPATH = "//*[@id='feedbackMessageContainer']/*[contains(@class,'feedback-message')]/*[contains(@class,'feedback-message-text')]";
	public static String MandatoryFieldRequired_Msg = "Please check the highlighted details";
	
	public static String ChangesSavedMsg = "Changes saved successfully";
	public static String NewRiskCreatedMsg = "Created New Prospect - Reference";
	public static String RiskDetailsSavedSuccessMsg = "Successfully saved."; 
	public static String NTUSuccessMsg = "Item is now NTU."; 
	public static String ReinstateSuccessMsg = "Item is now re-instated."; 
	public static String FirmOrderSuccessMsg = "Item is now a Firm Order."; 
	public static String SubmitToBrokerOpsSuccessMsg = "Item successfully submitted."; 
	public static String CopySuccessMsg = "New Prospect copied from"; 
	public static String CopySaveToConfirm_SuccessMsg = "Created New Prospect - Reference";
	public static String RenewRisk_SuccessMsg = "Renewal successfully created";
	public static String SuccessMsg_XPATH = "//*[@id='feedbackMessageContainer']/*[contains(@class,'feedback-message-success')]/*[contains(@class,'feedback-message-text')]";
	
	
	public static String QuoteAlreadyCreatedErrorMsg = "There is already a quote created for this prospect number";
	public static String ConnectionTimeoutErrorMsg = "The connection has timed out";
	public static String InternalServerErrorMsg = "500 Internal Server Error";
	public static String ErrorMsg_XPATH = "//*[@id='feedbackMessageContainer']/*[contains(@class,'feedback-message-error')]/*[contains(@class,'feedback-message-text')]";
	
	
	//------- NTU
	public static String NTUPopUp = "//*[@id='NtuPopup']";
	
	public static List<String> UserRole = Arrays.asList("Account Handler", "User Administrator", "Senior Insurance Tech",
			"Operations Team Lead", "Operations Tech", "Administrations Team"
	);
	
	//----------Firm Order
	public static String FirmOrder_PopUp_XPATH="//*[@id='FirmOrderPopup']";
	
	//----------Submit to Broker Ops
	public static String SubmitToBrokerOps_PopUp_XPATH="//*[@id='SubmitPopup']";
	public static String SubmitToBrokerOps_PopUp_Title_XPATH = "//*[@id='SubmitPopup']//h1";
	public static String SubmitToBrokerOps_PopUp_Title = "CPT Submission";
	
	//------Copy Risk----------
	public static String CopyRisk_PopUp_XPATH="//*[@id='CopyConfirmationPopup']";
	public static String CopyRisk_PopUp_TitleQuestion_XPATH = "//*[@id='CopyConfirmationPopup']//*[contains(text(),'Would you like to create copy of this Risk?')]";
	public static String Copy_SaveToConfirm_Title_XPATH = "//h3[contains(text(),'Risk Tracker:')]/*[contains(text(),'Copy - Save to Confirm')]";
	
	public static List<String> UserRoles = Arrays.asList("Account Handler", "User Administrator",
			"Senior Insurance Tech", "Operations Team Lead", "Operations Tech", "Administrations Team");

	public static List<String> RiskDashboardFields = Arrays.asList("RiskCard_RiskRef");
	
	public static List<String> RiskOverviewFields = Arrays.asList("NewOrRenewalClassification", "Department",
			"AgentOrClient", "AgentOrClient_AccountNo","Insured", "DirectInsuredPlacement","DirectInsured", "Reinsurance", 
			"OriginalInsured","Reinsured", "AccountExecutive", "InternalBroker");
	
	public static List<String> RiskDetailsFields = Arrays.asList("MajorClassOfBusiness_Aviation",
			"MajorClassOfBusiness_Marine", "MajorClassOfBusiness_NonMarine", "MinorClassOfBusiness_Aviation",
			"MinorClassOfBusiness_Marine", "MinorClassOfBusiness_NonMarine",
			"PremiumCurrencyCode", "Limit", "Premium", "Deductible", "ClientCommission", "Information", "Terms");
	
	public static List<String> NewOrRenewalClassification = Arrays.asList("Select a Classification", "New Policy (with New Client)",
			"Cross-selling (New Policy / Existing Client)", "Renewal Policy",
			"Up-selling (Renewal with additional coverage/CoB)",
			"Down-selling (Renewal with reduced coverage/CoB)");
	
	public static List<String> Department = Arrays.asList("Aviation (AV)","Casualty_integro Conversion (IM)",
			"Construction (CF)", "Entertainment Contingency (EC)", "ERM - Entertainment Risk Mangement (ER)",
			"HPAK/CPB (XL)","International P&C (IF)","International Treaty (YT)","Management Risk (MR)",
			"Marine (MA)","Marine (MF)","North American (NA)","Property & Casualty (E&S) (PC)","Property & Casualty (YF)",
			"Property_integro Conversion (TM)","Reinsurance_integro Conversion (TR)","Schemes (SM)","Speciality (SY)",
			"Sport (SP)","T-Pro (TP)","Terrorism (PV)","Tysers Reinsurance (YR)", "UK Corporate Commercial (CI)");
	
	public static List<String> MajorClassOfBusiness = Arrays.asList("Aviation", "Marine",
			"Non-Marine");
	
	public static List<String> MinorClassOfBusiness_Aviation = Arrays.asList("AIRCRAFT HULL", "AIRCRAFT HULL WAR",
			"AIRCRAFT LIABILITY", "ALL RISKS","ATC LIABILITY","AVIATION MISCELLANEOUS","AVIATION PASSENGER LIABILITY",
			"AVIATION PREMISES & HANGARKEEPERS","AVIATION PREMISES AND PRODUCTS LIABILITY","AVIATION WAR", 
			"CONTRACTORS ALL RISKS","Excess AVN52 Liability","Excess Liability","EXCESS WAR  HI-JACKING AND OTHER PERILS",
			"FIRE & PERILS","HANGARKEEPERS AND PRODUCTS LEGAL LIABILITY","HANGARKEEPERS AND PRODUCTS LIABILITY",
			"HULL AND LIABILITY","HULL DEDUCTIBLE","HULLS SPARES & LIABILITY","LIABILITY","LOSS OF LICENCE",
			"OWNERS & OPERATORS LIABILITY","PERSONAL ACCIDENT","PREMISES  HANGARKEEPERS & PRODUCTS LIABS",
			"SPACECRAFT LAUNCH INSURANCE","TERRORISM","THIRD PARTY AND PASSENGER LIABILITY");
	
	
	public static List<String> MinorClassOfBusiness_Marine = Arrays.asList("AGENCY", "ATA CARNET",
			"AVERAGE DISBURSMENTS", "AVIATION DIRECT", "AVIATION REINSURANCE", "BUILDERS RISKS", "CARGO COVER",
			"CARGO LLOYDS COVER DECLARATIONS", "CARGO MARKET REINSURANCE", "CARGO REINSURANCE", "CARGO REJECTION",
			"CHARTERERS LIABILITY", "CONFISCATION", "CONTINGENCY STRIKES RISKS",
			"COVER AGAINST BOYCOTTS DEVIATION EXPENSES", "DAMAGE TO HULL", "DEVIATION EXPENSES", "ENERGY",
			"EQUIPMENT &/OR MACHINERY &/OR CAMP BUILDIN", "EXCESS DELAY REINSURANCE", "EXCESS OF LOSS",
			"Fine Art & Specie Reinsurance", "FIRE", "FMC BOND", "FREIGHT", "FREIGHT  DEMURRAGE & DEFENCE",
			"HULL AND MACHINERY", "HULL DISBURSEMENTS", "HULL INSURANCE", "HULL MARKET REINSURANCE", "HULL PACKAGE",
			"HULL REINSURANCE", "INCREASED VALUES R/I", "KIDNAP AND RANSOM", "LIABILITY", "LIVESTOCK",
			"LOSS OF EARNINGS", "MARINE CARGO", "MARINE CARGO DIRECT FACULTATIVE", "MARINE CONTINGENCY REINSURANCE",
			"MISCELLANEOUS", "Miscellaneous Reinsurance", "MORTGAGEES INTEREST", "OIL AND ENERGY", "OVERSIDE EQUIPMENT",
			"PERSONAL ACCIDENT & ILLNESS", "Political Risks", "PRIZE INDEMNITY", "PROTECTION & INDEMNITY",
			"SPECIAL CONTINGENCY", "SPECIE", "STRIKES", "STRIKES  RIOTS & CIVIL COMMOTIONS & MALICI",
			"TERRORISM AND SABOTAGE", "TRANSPORT OPERATORS EQUIPMENT", "WAR RISKS", "YACHT");
	
	public static List<String> MinorClassOfBusiness_NonMarine = Arrays.asList("Accident & Health", "ACCIDENT DIRECT",
			"ADVERSE WEATHER", "ADVERTISING", "AIRSIDE LIABILITY", "ALL RISKS", "ALL RISKS",
			"ALL RISKS OF DIRECT PHYSICAL LOSS X FLD EQ", "ALL RISKS OF MORTALITY",
			"ALL RISKS OF PHYSICAL LOSS OR DAMAGE", "ANIMAL ENTERTAINMENT", "ASSETS ALL RISKS", "AUTO PHYSICAL DAMAGE",
			"Aviation", "AVIATION HULL & LIABILITY", "AVIATION PREMISES", "AVIATION PREMISES  HANGARKEEPERS & PRODUCT",
			"BAILEES", "BANKERS BLANKET BOND", "BLOCKS OF FLATS", "BLOODSTOCK", "BUILDERS RISKS", "BURGLARY",
			"CANCELLATION", "CANCELLATION AND NON-APPEARANCE", "CASH", "Cash in Transit", "Cash on Premises",
			"CAST INSURANCE", "CHARITY TRUSTEES LIABILITY", "CLASSIC CAR MOTOR FLEET SELF DRIVE HIRE", "CLUB COMBINED",
			"CLUB LIABILITY", "COMBINED LIABILITY", "COMBINED MATERIAL DAMAGE / BUSINESS INTERR", "COMMERCIAL COMBINED",
			"COMMERCIAL CRIME", "COMMERCIAL GENERAL LIABILITY REINSURANCE",
			"COMMERCIAL GENERAL LIABILITY/PROF INDEMNIT", "COMMERCIAL LEGAL EXPENSES",
			"COMPREHENSIVE GENERAL LIABILITY", "COMPUTER", "COMPUTER CRIME", "Confidential Contracts",
			"CONSTRUCTION ALL RISKS ANNUAL COVER", "CONSTRUCTION ALL RISKS ONE OFF", "CONTINGENCY DIRECT",
			"Contingency Ins/Reins", "Contingency Medical", "CONTINGENCY REINSURANCE", "CONTINGENCY STRIKES RISKS",
			"CONTRACTORS ALL RISKS", "CONTRACTORS COMBINED", "CONTRACTORS PLANT AND EQUIPMENT",
			"CONTRACTORS PLANT AND MACHINERY", "CONTRACTUAL BONUS INSURANCE",
			"CORPORATE KIDNAP/EXTORTION/PRODUCTS EXTORT", "CREDIT", "CRIME", "CRITICAL ILLNESS", "CROP",
			"CYBER LIABILITY INSURANCE", "D&O Liability & Corporate Reimbursement", "DEALERS BLOCK INSURANCE",
			"DEALERS OPEN LOT", "DEATH  DISABLEMENT  DISGRACE", "DEATH AND DISGRACE", "DEATH AND DISGRACE INSURANCE",
			"DECENNIAL INSURANCE", "DEFECTIVE TITLE", "DEFECTIVE TITLE INSURANCE", "Defence Based Acts",
			"Deterioration Of Stock", "DIFFERENCE IN CONDITIONS", "DIRECT", "DIRECTORS AND OFFICERS",
			"DIRECTORS AND OFFICERS LIABILITY", "DREAD DISEASE", "EARTHQUAKE AND FLOOD FACILITY",
			"Earthquake Shock Insurance", "EMPLOYERS LIABILITY", "EMPLOYERS/PUBLIC/PRODUCTS LIABILITY INSURA",
			"EMPLOYMENT PRACTICES LIABILITY", "ENGINEERING", "ENGINEERING INSPECTION", "ENVIRONMENTAL",
			"EQUIPMENT INSURANCE", "ERECTION ALL RISKS", "ERRORS AND OMISSIONS", "ERRORS AND OMISSIONS",
			"EVACUATION & REPATRIATION REINSURANCE", "Event Cancellation", "EXCESS DIRECTORS & OFFICERS LIABILITY",
			"EXCESS EMPLOYERS LIABILITY", "EXCESS HAIL  PHYSICAL DAMAGE & CONSEQUENTI", "EXCESS LIABILITY",
			"EXCESS MOTOR TRUCK CARGO", "EXCESS OF LOSS", "EXCESS OF LOSS BONUS INDEMNITY REINSURANCE",
			"EXCESS PROFESSIONAL INDEMNITY", "EXCESS PUBLIC/PRODUCTS LIABILITY", "Failure to Fulfil", "FIDELITY",
			"FILM PRODUCER'S INDEMNITY", "Film Production", "Film Production Package", "FINE ART", "FINE ART",
			"FIRE  E.C. AND V.V.M. ONLY", "FIRE  THEFT & COLLISION", "FIRE AND ALLIED PERILS REINSURANCE",
			"FIRE DIRECT", "Fire including Excess of Loss", "FIRE REINSURANCE", "FISHING TOURNAMENTS", "Flood",
			"GARAGE KEEPERS LIABILITY", "GENERAL AUTOMOBILE BUSINESS", "GENERAL COMMERCIAL & RETAIL BUSINESS",
			"General Specie", "GOODS IN TRANSIT", "GROUP INCOME PROTECTION INSURANCE", "GROUP LIFE INSURANCE",
			"GROUP LONG TERM DISABILITY", "GROUP PERSONAL ACCIDENT & TRAVEL", "GUMBALL RALLY", "HOLE IN ONE",
			"Hole in One", "HOUSEHOLD", "HOUSEHOLDERS", "INDUSTRIAL ALL RISKS", "INLAND MARINE", "INSURANCE BOND",
			"JEWELLERS BLOCK", "Jewellery", "KEYMAN", "Kidnap and Ransom", "LATENT DEFECTS",
			"LAWYERS PROFESSIONAL LIABILITY", "LEGAL DEFENCE COSTS", "LEGAL EXPENSES", "LEGAL EXPENSES", "LIABILITY",
			"LIABILITY DIRECT", "LIABILITY REINSURANCE", "LIQUIDATED DAMAGES/CONTRACTURAL LIABILITY", "LIQUOR LAN",
			"LIVESTOCK", "LOSS OF LICENSE", "LOSS RECOVERY", "MACHINERY BREAKDOWN", "MALPRACTICE",
			"MARINA OPERATORS LIABILITY", "MARINE CARGO", "MEDICAL EXPENSES", "MEDICAL EXPENSES REINSURANCE",
			"Medical Malpractice/Healthcare", "MISCELLANEOUS", "MOTOR", "MOTOR DIRECT", "MOTOR FLEET",
			"MOTOR REINSURANCE", "MOTOR TRADERS", "MOTOR TRUCK CARGO", "MULTI-MEDIA PROTECTOR",
			"N.MAR. CONFISCATION EXPROPRIATION NATIONAL", "NON APPEARANCE", "Non Appearance",
			"NON MARINE DIRECT NORTH AMERICAN FACULTATI", "NON MARINE MINING RISKS",
			"NON MARINE REINSURANCE NORTH AMERICAN FACU", "NON MARINE SPECIAL MULTIPERIL",
			"NON MARINE SPECTATOR LIABILITY", "NON MARINE WAR", "NON-MARINE", "NON-MARINE", "OFF-TRACK", "OFFICE",
			"On Track", "ON-TRACK", "Over Redemption", "OVER REDEMPTION REINSURANCE", "OVER-REDEMPTION",
			"PENSION TRUSTEES LIABILITY", "PERSONAL ACCIDENT", "PERSONAL ACCIDENT", "PERSONAL UMBRELLA LIABILITY",
			"PLUVOIUS", "POLITICAL RIOTS", "POLLUTION LEGAL LIABILITY", "PRE-LAUNCH", "Premises & Transit",
			"PREMIUM OVERRIDERS", "PRIVATE MEDICAL", "PRIZE INDEMNITY", "PRIZE INDEMNITY REINSURANCE",
			"PRODUCTS LIABILITY", "PROFESSIONAL INDEMNITY", "PROFESSIONAL INDEMNITY", "PROPERTY", "PROPERTY ALL RISKS",
			"PROPERTY OWNERS", "PUBLIC LIABILITY", "PUBLIC/PRODUCTS LIABILITY", "REIMBURSEMENT COST INSURANCE",
			"REINSURANCE", "RESIDENTIAL PROPERTY BINDER", "RESTRICTIVE COVENANT",
			"SINGLE PROJECT PROFESSIONAL INDEMNITY", "SOUTH AFRICAN HOUSEHOLDERS BUSINESS", "Special Contingency",
			"SPECIAL CONTINGENCY COVER", "Special Programs", "Special Risks Insurance", "SPORTS TOURS",
			"Storage & Transit", "TELEVISION PRODUCTION RISKS", "TERM LIFE ASSURANCE", "Term Life Insurance",
			"TERRORISM", "THE RESERVE FACILITY LINESLIP", "THEFT", "THIRD PARTY LIABILITY", "TOTAL LOSS ONLY",
			"TOUR OPERATORS LIABILITY", "TRANSIT  BURGLARY  HOLD-UP AND FIDELITY", "Transmission Failure",
			"TRANSPORT OPERATORS EQUIPMENT", "Travel", "TRUCKING", "UK Contents", "UNINSURED LOSS RECOVERY",
			"WAREHOUSEMANS LIABIALITY", "WARRANTY AND INDEMNITY INSURANCE", "WEATHER DOWNTIME",
			"WEATHER DOWNTIME INSURANCE", "WHAT IS THIS", "WINDSTORM AND FLOOD ONLY", "YACHT");
			

	public static List<String> PremiumCurrencyCode = Arrays.asList("CANADIAN DOLLAR (CAD)","EURO-BANKING ONLY (EUR)",
			"POUND STERLING (GBP)","UNITED STATES DOLLAR (USD)","ALBANIAN LEK (ALL)","ARAB EMIRATES DIRHAM (AED)",
			"Argentinean Peso (ARS)","ARUBAN GILDER (AFL)","AUSTRALIAN DOLLARS (AUD)","AUSTRALIAN DOLLARS BANKING (ASD)",
			"AZERBAIJANI NEW MANAT (AZN)","BAHAMIAN DOLLAR (BSD)","BAHRAINI DINAR (BHD)",
			"BANGLADESH TAKA (BDT)","BARBADOS DOLLARS (BBD)","BELIZE DOLLARS (BZD)","Bermudian Dollar (BMD)",
			"BHUTANESE NGULTRUM (BTN)","Bosnia Herzegovina Marka (BAM)","BOTSWANA PULA (BWP)","BRAZILIAN REAL (BRL)",
			"BRUNEI DOLLARS (BND)","CAYMAN ISLAND DOLLARS (KYD)","CENTRAL AFRICAN CFA FRANC BEAC (XAF)","CHILEAN PESO (CLP)",
			"CHINESE YUAN REMINBI (CNY)","CNV. CANADIAN DOLLAR (CC$)","Colombian Peso (COP)","CONVERTIBLE EURO (ERO)",
			"CONVERTIBLE STERLING (CV�)","COVERTABLE US DOLLAR (CV$)","CZECH KORUNA (CZK)","DANISH KRONER (DKK)","DANISH KRONER BANKING (DKR)",
			"DOLLAR/POUNDS CONV (US$)","DOMINICAN REPUBLIC PESO (DOP)","E. CARIBBEAN DOLLAR (XCD)","EGYPTIAN POUNDS (EGP)",
			"GUATEMALAN QUETZAL (GTQ)","HONG KONG DOLLARS (HKD)","HUNGARIAN FORINT (HUF)","Icelandic Kr�na (ISK)",
			"INDIAN RUPEES (INR)","INDONESIAN RUPIAH (IDR)","INDONESIAN RUPIAH (*IR)","IRANIAN RIAL (IRR)","IRAQ DINAR (IRD)",
			"ISRAELI SHEKEL (ILS)","JAMACAN DOLLARS (JMD)","JAPANESE YEN (JPY)","JAPANESE YEN BANKING (YEN)",
			"JORDANIAN DINAR (JOD)","KENYAN SHILLING (KES)","KUWAIT DINAR (KWD)","LEBANESE POUNDS (LBP)",
			"LIBYAN DINARS (LYD)","LITHUANIAN LITA (LTL)","MACAU PATACAS (MOP)","Malagasy Ariary (MGA)",
			"MALAYSIAN RINGGITT (MYR)","MAURITIUS RUPEE (MUR)","MEXICAN NUEVO PESO (MXN)","MONGOLIAN TUGHRIK (MNT)",
			"MOROCCAN DIRHAM (MAD)","Mozambican Metical (MZN)","NAMIBIAN DOLLARS (NAD)","NEPALESE RUPEE (NPR)",
			"NETHERLAND ANTILLES GUILDER (ANG)","New Turkish Lira (TRY)","NEW TURKISH LIRA (YTD)","NEW ZEALAND DOLLAR (NZD)",
			"NIGERIAN NAIRA (NGN)","NIL DEC INDIAN RUPEE (*IN)","NIL DEC ITALIAN LIRE (*IL)","NIL DEC S.KOREAN WON (*KW)",
			"NIL DEC SPANISH PASE (*SP)","NIL DEC TURKISH LIRA (*TL)","NORWEGIAN KRONE (NOK)","NORWEGIAN KRONE BANKING (NKR)",
			"NZ DOLLAR BANKING (DNZ)","OMANI RIAL (OMR)","PAKISTAN RUPEE (PKR)","Panamanian Balboa (PAB)",
			"PAPUA NEW GUINEAN KINA (PGK)","Paraguayan Guaran� (PYG)","Peruvian Sol (PEN)","PHILIPPINES PESO (PHP)",
			"POLISH ZLOTY (PLN)","POLISH ZLOTY BANKING CURRENCY (PLZ)","POUNDS/DOLLAR CONV (UK�)","QATAR RIYALS (QAR)",
			"RUSSIAN ROUBLE (RUB)","S.A. RANDS BANKING (RZA)","SAUDI RIYAL (SAR)","SEYCHELLES RUPEE (SCR)",
			"SINGAPORE DOLLAR (SGD)","SOUTH AFRICAN RAND (ZAR)","SOUTH KOREAN WON (WON)","SOUTH KOREAN WON (KRW)",
			"SOUTH SUDANESE POUND (SSP)","Sri Lankan Rupees (LKR)","SUDAN REPUBLIC POUND (SDP)","SUDANESE DINAR (SDD)",
			"SUDANESE POUND (SDG)","SWAZILAND ELILANGENI (SZL)","Swedish Krona (SKR)","SWEDISH KRONA (SEK)",
			"SWISS FRANCS BANKING (SWF)","SWITZERLAND FRANC (CHF)","SYRIAN POUND (SYP)","TAIWANESE DOLLARS (TWD)",
			"THAILAND BAHT (THB)","TRINIDAD & TOBAGO $ (TTD)","TUNISIAN DINAR (TND)","UKRAINIAN HRYVNIA (UAH)",
			"UNIDADES DE FORMENTO (CLF)","Venezuelan Bol�var Fuerte (VEF)","VENEZUELAN BOLIVARS (VEB)","VIETNAMESE DONG (VND)",
			"ZIMBABWE DOLLARS (ZWE)");
	 
	public static List<String> NTUReason = Arrays.asList("NTU - Discarded - uneconomical",
			"NTU - Discarded - expertise unavailable", "NTU - Discarded - resources unavailable",
			"NTU - Discarded - unauthorised", "NTU - No Response", "NTU - Lost To Competitor");

	public static List<String> RiskNTUFields = Arrays.asList("RiskRef","NTUReason");
	
	
	
	


	public XSSFWorkbook O_workbook = null;
	public InputStream O_XLSXfiletoread = null;
	public static boolean timeCapture = false;
	
	public static String strErrorScreenshot = "Yes";
	
	public static LinkedHashMap<Long, String> DefaultDO_GridDetails = new LinkedHashMap<Long, String>();
	
	public static LinkedHashMap<String, String> DefaultDO_HomePage = new LinkedHashMap<String, String>();

	//----------Not used at the moment --- Also commented on Page Classes------------------
	public static LinkedHashMap<String, String> getDefaultDO_HomePage() {
			return DefaultDO_HomePage;
		}
		
	public void init_DefaultDO_HomePage() {
			LinkedHashMap<String, String> defaultDO_HomePage_Local = getDefaultDO_HomePage();
			defaultDO_HomePage_Local.put("strDOName", "DefaultDO_HomePage");
		defaultDO_HomePage_Local.put("BR_verifyElementsDisplayed", "True");				
			setDefaultDO_HomePage(defaultDO_HomePage_Local);
		}	
		
	public static void setDefaultDO_HomePage(LinkedHashMap<String, String> defaultDO_HomePage) {
			DefaultDO_HomePage = defaultDO_HomePage;
		}	
	//----------Not used at the moment --- Also commented on Page Classes------------------
	
	public XSSFWorkbook getOutPutWorkbook(String strFilePathwithName) {
		try {
			File file = new File(strFilePathwithName);
			if (!file.exists()) {
				O_workbook = new XSSFWorkbook();
				XSSFSheet outputsheet = O_workbook.createSheet("OutputSheet");
				outputsheet.createRow(0);
				XSSFRow headerRow = outputsheet.getRow(0);
				XSSFCellStyle headerStyle = ReadExcel.getHeaderStyle(O_workbook);
				String[] arrOutputHeader =  {"TCID", "Status", "RiskTracker_Reference","Summary", "Description", "StartTime"};
				for (int i = 0; i <= 5; i++) {
					headerRow.createCell(i);
					headerRow.getCell(i).setCellValue(arrOutputHeader[i]);
					headerRow.getCell(i).setCellStyle(headerStyle);
				}
				FileOutputStream out = new FileOutputStream(strFilePathwithName);
				O_workbook.write(out);
				out.close();
			} else {
				O_workbook = new XSSFWorkbook(new FileInputStream(new File(strFilePathwithName)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return O_workbook;
	}

	public XSSFWorkbook getDriverWorkbook() {
		XSSFWorkbook d_workbook = null;
		try {
			FileInputStream D_XLSXfiletoread = new FileInputStream(ProjectConstants.DRIVEREXCEL);
			d_workbook = new XSSFWorkbook(D_XLSXfiletoread);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return d_workbook;
	}

	public XSSFWorkbook getTestDataWorkbook() {
		XSSFWorkbook i_workbook = null;
		try {
			FileInputStream I_XLSXfiletoread = new FileInputStream(ProjectConstants.TESTDATAEXCEL);
			i_workbook = new XSSFWorkbook(I_XLSXfiletoread);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i_workbook;
	}
	
}
	
	
	
	
	