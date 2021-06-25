package libraries;
import java.time.LocalDateTime;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import tests.Test1;
@SuppressWarnings("unused")
public class DictionaryObjects extends Constants {
	public static Data_Interaction interact= new Data_Interaction();
	public static ReadExcel read= new ReadExcel();
	
	public static LinkedHashMap<String, String> testcasedetails= new LinkedHashMap<String, String>();
	private static LinkedHashMap<String,String> objTestCaseDetails_DO_Config1 = new LinkedHashMap<String, String>();
	private static LinkedHashMap<String,String> objTestCaseDetails_DO_Config2 = new LinkedHashMap<String, String>();
	private static LinkedHashMap<String,String> objTestCaseDetails_DO_Config3 = new LinkedHashMap<String, String>();
	private static LinkedHashMap<String,String> objTestCaseDetails_DO_Config4 = new LinkedHashMap<String, String>();
	private static LinkedHashMap<String,String> objTestCaseDetails_DO_Config5 = new LinkedHashMap<String, String>();

	private	static	LinkedHashMap<String,	String>	objHomePage_DO_Config1	=	new	LinkedHashMap<String,	String>();
	private	static	LinkedHashMap<String,	String>	objHomePage_DO_Config2	=	new	LinkedHashMap<String,	String>();
	private	static	LinkedHashMap<String,	String>	objHomePage_DO_Config3	=	new	LinkedHashMap<String,	String>();
	private	static	LinkedHashMap<String,	String>	objHomePage_DO_Config4	=	new	LinkedHashMap<String,	String>();
	private	static	LinkedHashMap<String,	String>	objHomePage_DO_Config5	=	new	LinkedHashMap<String,	String>();
	
	private static LinkedHashMap<String,String> objOutput_DO_Config1 = new LinkedHashMap<String, String>();
	private static LinkedHashMap<String,String> objOutput_DO_Config2 = new LinkedHashMap<String, String>();
	private static LinkedHashMap<String,String> objOutput_DO_Config3 = new LinkedHashMap<String, String>();
	private static LinkedHashMap<String,String> objOutput_DO_Config4 = new LinkedHashMap<String, String>();
	private static LinkedHashMap<String,String> objOutput_DO_Config5 = new LinkedHashMap<String, String>();
	
	private static LinkedHashMap<String,String> objDriverDetails_DO_Config1 = new LinkedHashMap<String, String>();
	private static LinkedHashMap<String,String> objDriverDetails_DO_Config2 = new LinkedHashMap<String, String>();
	private static LinkedHashMap<String,String> objDriverDetails_DO_Config3 = new LinkedHashMap<String, String>();
	private static LinkedHashMap<String,String> objDriverDetails_DO_Config4 = new LinkedHashMap<String, String>();
	private static LinkedHashMap<String,String> objDriverDetails_DO_Config5 = new LinkedHashMap<String, String>();
	
	public static LinkedHashMap<String, String> getObjHomePage_DO(String strConfig) {
		switch (strConfig) {
		case "Config1":
			return objHomePage_DO_Config1;
		case "Config2":
			return objHomePage_DO_Config2;
		case "Config3":
			return objHomePage_DO_Config3;
		case "Config4":
			return objHomePage_DO_Config4;
		case "Config5":
			return objHomePage_DO_Config5;
		default:
			return objHomePage_DO_Config1;
		}

	}
	
	public static LinkedHashMap<String, String> getObjOutput_DO(String strConfig) {
		switch (strConfig) {
		case "Config1":
			return objOutput_DO_Config1;
		case "Config2":
			return objOutput_DO_Config2;
		case "Config3":
			return objOutput_DO_Config3;
		case "Config4":
			return objOutput_DO_Config4;
		case "Config5":
			return objOutput_DO_Config5;
		default:
			return objOutput_DO_Config1;
		}
	}

	
	
	public static void setObjOutput_DO(String strConfig, LinkedHashMap<String, String> objOutput_DO) {
		switch (strConfig) {
		case "Config1":
			DictionaryObjects.objOutput_DO_Config1 = objOutput_DO;
			break;
		case "Config2":
			DictionaryObjects.objOutput_DO_Config2 = objOutput_DO;
			break;
		case "Config3":
			DictionaryObjects.objOutput_DO_Config3 = objOutput_DO;
			break;
		case "Config4":
			DictionaryObjects.objOutput_DO_Config4 = objOutput_DO;
			break;
		case "Config5":
			DictionaryObjects.objOutput_DO_Config5 = objOutput_DO;
			break;
		default:
			DictionaryObjects.objOutput_DO_Config1 = objOutput_DO;
			break;
		}
	}

	public static LinkedHashMap<String, String> getObjDriverDetails_DO(String strConfig) {
		switch (strConfig) {
		case "Config1":
			return objDriverDetails_DO_Config1;
		case "Config2":
			return objDriverDetails_DO_Config2;
		case "Config3":
			return objDriverDetails_DO_Config3;
		case "Config4":
			return objDriverDetails_DO_Config4;
		case "Config5":
			return objDriverDetails_DO_Config5;
		default:
			return objDriverDetails_DO_Config1;
		}
	}

	

	public static void setObjDriverDetails_DO(String strConfig, LinkedHashMap<String, String> objDriverDetails_DO) {
		switch (strConfig) {
		case "Config1":
			DictionaryObjects.objDriverDetails_DO_Config1 = objDriverDetails_DO;
			break;
		case "Config2":
			DictionaryObjects.objDriverDetails_DO_Config2 = objDriverDetails_DO;
			break;
		case "Config3":
			DictionaryObjects.objDriverDetails_DO_Config3 = objDriverDetails_DO;
			break;
		case "Config4":
			DictionaryObjects.objDriverDetails_DO_Config4 = objDriverDetails_DO;
			break;
		case "Config5":
			DictionaryObjects.objDriverDetails_DO_Config5 = objDriverDetails_DO;
			break;
		default:
			DictionaryObjects.objDriverDetails_DO_Config1 = objDriverDetails_DO;
			break;
		}
	}
	
	
	public void init_ObjDriverDetails_DO(String strConfig){
		LinkedHashMap<String, String> ObjDriverDetails_DO = getObjDriverDetails_DO(strConfig);
		ObjDriverDetails_DO.put("strDOName", "objDriverDetails_DO");
//		ObjDriverDetails_DO.put("CBO_CreateApplication_Underwriter", "");
//		ObjDriverDetails_DO.put("TXT_CreateApplication_PolicyStartDate", "");
//		ObjDriverDetails_DO.put("TXT_CreateApplication_PolicyEndDate", "");
//		ObjDriverDetails_DO.put("CBO_ApplicationDetails_MemberAssuredType", "");
//		ObjDriverDetails_DO.put("CBO_ApplicationDetails_RiskType", "");
//		ObjDriverDetails_DO.put("CBO_ApplicationDetails_TemplateCategory", "");
//		ObjDriverDetails_DO.put("CBO_ApplicationDetails_TemplateType", "");
//		ObjDriverDetails_DO.put("TXT_BrokerSummary_BrokerSearch", "");
//		ObjDriverDetails_DO.put("BRCH_Member_AddNewMember", "");
//		ObjDriverDetails_DO.put("TXT_MemberSummary_MemberSearch", "");
//		ObjDriverDetails_DO.put("TotalNumberOfVessels", "");
//		ObjDriverDetails_DO.put("strPolicyReferenceForMTA", "");
//		ObjDriverDetails_DO.put("strPolicyReferenceForGrpRenewals", "");
//		ObjDriverDetails_DO.put("strPolicyReferenceForCRM", "");
//		ObjDriverDetails_DO.put("strTestCasesToExecuteForNB&MTA", "");
//		ObjDriverDetails_DO.put("NewPolicy_RegressionTC", "");
//		ObjDriverDetails_DO.put("TXT_GroupRenewals_SearchFromDate", "");
//		ObjDriverDetails_DO.put("TXT_GroupRenewals_SearchToDate", "");
//		ObjDriverDetails_DO.put("TXT_GroupRenewals_MemberReference", "");
//		ObjDriverDetails_DO.put("CBO_GroupRenewals_MemberAssuredGroup", "");
//		ObjDriverDetails_DO.put("TXT_GroupRenewals_Broker", "");
//		ObjDriverDetails_DO.put("CBO_GroupRenewals_Underwriter", "");
//		ObjDriverDetails_DO.put("TXT_GroupRenewals_RiskReference", "");
//		ObjDriverDetails_DO.put("CBO_GroupRenewals_VesselCategory", "");
		setObjDriverDetails_DO(strConfig, ObjDriverDetails_DO);
	}

	public void init_objHomePage_DO(String strConfig){
		LinkedHashMap<String, String> objHomePage_DO_Local = getObjHomePage_DO(strConfig);
		String tcid;
		tcid=CommonLib.gettestcaseName(strConfig);
//System.out.println(tcid);
		objHomePage_DO_Local=populate(objHomePage_DO_Local,tcid);
		objHomePage_DO_Local.put("strDOName", "objHomePage_DO");
		//objHomePage_DO_Local.put("TCID", tcid);
		//objHomePage_DO_Local.put("iTotalIterations", "1");
		//objHomePage_DO_Local.put("iCurrentIteration", "1");
		//objHomePage_DO_Local.put("strSheetName", "");
		//objHomePage_DO_Local.put("StopTCAfterCurrentScreen", "");
		System.out.println(objHomePage_DO_Local);
		//objHomePage_DO_Local.put("BR_calculatorverfiy", "True");
		setObjHomePage_DO(strConfig, objHomePage_DO_Local);
	}
	
	public void init_ObjOutput_DO(String strConfig){
		LinkedHashMap<String, String> ObjOutput_DO = getObjOutput_DO(strConfig);
		ObjOutput_DO.put("TCID", CommonLib.gettestcaseName(strConfig));
		ObjOutput_DO.put("RiskTracker_Reference", "");
		ObjOutput_DO.put("Status", "");
		ObjOutput_DO.put("MessageType", "");
		ObjOutput_DO.put("Message", "");
		ObjOutput_DO.put("StartTime", LocalDateTime.now().toString());
		setObjOutput_DO(strConfig, ObjOutput_DO);
	}
	
	public void init_ObjTestCaseDetails_DO(String strConfig){
		LinkedHashMap<String, String> ObjTestCaseDetails_DO = getObjTestCaseDetails_DO(strConfig);
		 String tcid;
		tcid=CommonLib.gettestcaseName(strConfig);

		ObjTestCaseDetails_DO=populate(ObjTestCaseDetails_DO,tcid);
		
	    ObjTestCaseDetails_DO.put("strTestCaseID", tcid);
//		ObjTestCaseDetails_DO.put("intCurrentIteration", "");
//		ObjTestCaseDetails_DO.put("intTotalIterations", "");
//		ObjTestCaseDetails_DO.put("StopTCAfterCurrentScreen", "NO");
		ObjTestCaseDetails_DO.put("iErrorCount", "");
		ObjTestCaseDetails_DO.put("Message", "");
		setObjTestCaseDetails_DO(strConfig, ObjTestCaseDetails_DO);
	}

	public static String gettestcasevalue(String key)
	{
		return testcasedetails.get(key);
		
	}

	public static LinkedHashMap<String, String> populate(LinkedHashMap<String, String> input, String key) {
		System.out.println(key);
		XSSFSheet sheet = read.readXLSXfile(ProjectConstants.TESTDATAEXCEL, "HomePage");
		int rowcount = read.rowcount(sheet);
		// Iterator<Row> rowIterator = sheet.iterator();
		int colcount = read.columncount(sheet, 0);
		System.out.println(rowcount + "   " + colcount);
		for (int i = 0; i <= rowcount; i++) {
			for (int j = 0; j < colcount; j++) {// System.out.println(read.CellValue(sheet, 0, j)+"
												// "+read.CellValue(sheet, i, j));
				try {
					if (read.CellValue(sheet, i, 0).equalsIgnoreCase(key)) {
						// System.out.println(read.CellValue(sheet, 0, j)+" "+read.CellValue(sheet, i,
						// j));
						testcasedetails.put(read.CellValue(sheet, 0, j), read.CellValue(sheet, i, j));

					}
				} catch (NullPointerException e) {
					break;

				}
			}

		}
		return testcasedetails;
	}
		
	
	public static void setObjHomePage_DO(String strConfig, LinkedHashMap<String, String> objHomePage_DO) {
		switch (strConfig) {
		case "Config1":
			DictionaryObjects.objHomePage_DO_Config1 = objHomePage_DO;
			break;
		case "Config2":
			DictionaryObjects.objHomePage_DO_Config2 = objHomePage_DO;
			break;
		case "Config3":
			DictionaryObjects.objHomePage_DO_Config3 = objHomePage_DO;
			break;
		case "Config4":
			DictionaryObjects.objHomePage_DO_Config4 = objHomePage_DO;
			break;
		case "Config5":
			DictionaryObjects.objHomePage_DO_Config5 = objHomePage_DO;
			break;
		default:
			DictionaryObjects.objHomePage_DO_Config1 = objHomePage_DO;
			break;
		}
		
	}
	public static LinkedHashMap<String, String> getObjTestCaseDetails_DO(String strConfig) {
		switch (strConfig) {
		case "Config1":
			return objTestCaseDetails_DO_Config1;
		case "Config2":
			return objTestCaseDetails_DO_Config2;
		case "Config3":
			return objTestCaseDetails_DO_Config3;
		case "Config4":
			return objTestCaseDetails_DO_Config4;
		case "Config5":
			return objTestCaseDetails_DO_Config5;
		default:
			return objTestCaseDetails_DO_Config1;
		}
	}

	
	
	public static void setObjTestCaseDetails_DO(String strConfig, LinkedHashMap<String, String> objTestCaseDetails_DO) {
		switch (strConfig) {
		case "Config1":
			DictionaryObjects.objTestCaseDetails_DO_Config1 = objTestCaseDetails_DO;
			break;
		case "Config2":
			DictionaryObjects.objTestCaseDetails_DO_Config2 = objTestCaseDetails_DO;
			break;
		case "Config3":
			DictionaryObjects.objTestCaseDetails_DO_Config3 = objTestCaseDetails_DO;
			break;
		case "Config4":
			DictionaryObjects.objTestCaseDetails_DO_Config4 = objTestCaseDetails_DO;
			break;
		case "Config5":
			DictionaryObjects.objTestCaseDetails_DO_Config5 = objTestCaseDetails_DO;
			break;
		default:
			DictionaryObjects.objTestCaseDetails_DO_Config1 = objTestCaseDetails_DO;
			break;
		}
	}
	
}






