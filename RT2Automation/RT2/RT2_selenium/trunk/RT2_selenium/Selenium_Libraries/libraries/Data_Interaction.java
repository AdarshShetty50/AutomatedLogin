package libraries;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;

import com.google.common.io.Files;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import net.sourceforge.htmlunit.corejs.javascript.tools.debugger.Main;
@SuppressWarnings("unused")
public class Data_Interaction {

	ReadExcel excel = new ReadExcel();
	public static Constants constants = new Constants();
	public static DictionaryObjects dictionaryObjects = new DictionaryObjects();
	ProjectConstants projectConstants = new ProjectConstants();
	private CSVReader reader1;
	public static Logger logger;
	

	public LinkedHashMap<String, String> UpdateScreenDOWithDriver(String strConfig, LinkedHashMap<String, String> objTargetDOName, LinkedHashMap<String, String> objSourceDOName){
		ArrayList<String> arrKeySet = new ArrayList<>(objTargetDOName.keySet());
		for (int i = 0; i < arrKeySet.size(); i++) {
			String strKey = arrKeySet.get(i);
			String strValue = objTargetDOName.get(strKey);

			if(objTargetDOName.containsKey(strKey)) {
				if(objSourceDOName.containsKey(strKey)){
					if(strKey != "strDOName"){
						if(objSourceDOName.get(strKey).toUpperCase() != "DEFAULT" & objSourceDOName.get(strKey) != ""){
							String PreviousVal = objTargetDOName.replace(strKey, objSourceDOName.get(strKey));
//							Reporter.log("Target DO '" + objTargetDOName.get("strDOName") + "'s Value for Key: " + strKey + " is replaced to: " + objSourceDOName.get(strKey));
							CommonLib.getLogger(strConfig).info("Target DO '" + objTargetDOName.get("strDOName") + "'s Value for Key: " + strKey + " is replaced to: " + objSourceDOName.get(strKey));
						}
					}
				} else {
//					Reporter.log(strKey + " doesnt exist in Target DO." + objTargetDOName.get("strDOName"));
					CommonLib.getLogger(strConfig).info(strKey + " doesnt exist in Target DO." + objTargetDOName.get("strDOName"));
				}
			} else {
//				Reporter.log(strKey + " doesnt exist in Target DO." + objTargetDOName.get("strDOName"));
				CommonLib.getLogger(strConfig).info(strKey + " doesnt exist in Target DO." + objTargetDOName.get("strDOName"));
			}
		}
		return objTargetDOName;
	}

	public LinkedHashMap<String, String> UpdateDOValues(String strConfig, LinkedHashMap<String, String> objDOName, String strFileNameWithPath, String strWorksheetName){
//		XSSFWorkbook excelWorkBook = excel.GetExcelWorkbook(strFileNameWithPath);

//		XSSFSheet currSheet = excelWorkBook.getSheet(strWorksheetName);
		XSSFSheet currSheet = null;		
		currSheet = CommonLib.getTestDataWorkbook(strConfig).getSheet(strWorksheetName);
		

		Integer intRowCount = excel.rowcount(currSheet);
		//Main.logger.info("Rows in " + strWorksheetName + " has rowcount: " + intRowCount);
		LinkedHashMap<String, String> objTestCaseDetails_DO = DictionaryObjects.getObjTestCaseDetails_DO(strConfig);
		String strTestCaseID = objTestCaseDetails_DO.get("strTestCaseID");
		Integer intCurrentIteration = Integer.parseInt(objTestCaseDetails_DO.get("intCurrentIteration"));
		if(intRowCount > 0){
			try {
				for (int i = 0; i <= intRowCount; i++) {
					if(excel.CellValue(currSheet, i, 0).equalsIgnoreCase(strTestCaseID)){
						//Main.logger.info("Test Case found on row: " + i+1);
						Integer expectedCurrentIteration = Integer.parseInt(excel.CellValue(currSheet, i, 2));
						//Main.logger.info("Expected Iteration: " + expectedCurrentIteration + " ActualCurrentIteration: " + intCurrentIteration);
						if(expectedCurrentIteration==intCurrentIteration){
							int intCurrSheetColumns = excel.columncount(currSheet, 0);
							//Main.logger.info("Total Columns: " + intCurrSheetColumns);
							for (int j = 0; j < intCurrSheetColumns; j++) {
								String strKey = excel.CellValue(currSheet, 0, j);
								String strItem = excel.CellValue(currSheet, i, j);
								//Main.logger.info("Expected Key: " + strKey + " Expected Value: " + strItem);
								if(strKey != null && objDOName.containsKey(strKey)){
									//Main.logger.info("Key found: " + strKey);
									if(strItem == null || strItem.isEmpty()){
										String previousVal = objDOName.replace(strKey, "");
									} else if(strItem.compareToIgnoreCase("Default")!=0){
										String previousVal = objDOName.replace(strKey, strItem);
										//Main.logger.info("Key: "+ strKey + " Replacing value: " + previousVal + " with new val: " + strItem);
									}
								} else {
									//								Reporter.log("Worksheet Name = " + strWorksheetName + " Column no = " + i);
									//								Reporter.log(strKey + " not available in the Target DO: " + objDOName.get("strDOName"));
								}
							}
						}
					}
				}
			}catch (NullPointerException e) {
				Reporter.log("Nullpointer Exception caught");
			}
		}
		return objDOName;
	}

	/*
	  Method Name: UpdateDOValuesFromExcel
	  Description: Reads Excel file and puts it in a LinkedHashMap (for RI Rules)
	  Dependencies: ReadExcel - getWorkBook(strFileNameWithPath), CellValue(sheet, iRow, iCol)
	  Created By: Stephen Samuel
	*/
	public LinkedHashMap<String, String> UpdateRIRulesFromExcel(String strConfig, String strFileNameWithPath, String strWorksheetName, String strCoverCode){
		XSSFWorkbook workbook = ReadExcel.getWorkBook(strFileNameWithPath);
		XSSFSheet sheet = workbook.getSheet(strWorksheetName);

		LinkedHashMap<String, String> objRIDetails_DO = null;
		objRIDetails_DO = new LinkedHashMap<String, String>();

		int intRowCount = sheet.getPhysicalNumberOfRows();
		if(intRowCount > 0) {
			try {
				for (int iRow = 0; iRow < intRowCount; iRow++) { //iterate rows
					String strCellValue = excel.CellValue(sheet, iRow, 1);
					if(strCellValue != null){
						if(strCellValue.equalsIgnoreCase(strCoverCode)) {
							int intColumnCount = sheet.getRow(iRow).getPhysicalNumberOfCells();
							for (int iCol = 0; iCol < intColumnCount; iCol++) { //iterate columns
								String strKey = excel.CellValue(sheet, 0, iCol); //header
								String strItem = excel.CellValue(sheet, iRow, iCol); //value

								if(strItem == null) {
									strItem = "";
								}
								objRIDetails_DO.put(strKey, strItem);
							}
						}
					}
				}
			} catch (NullPointerException e) {
				CommonLib.getLogger(strConfig).error("Nullpointer Exception caught");
				e.printStackTrace();
			}
		}
		return objRIDetails_DO;
	}

		

	public String ReadDataFromExcel(String strConfig, String strSheetPath, String strSheetName, String strTCID, String strColName, String strOccurence){
		String returnVal = "";
		XSSFSheet datasheet = null;
		
			datasheet = CommonLib.getTestDataWorkbook(strConfig).getSheet(strSheetName);
		//System.out.println(datasheet.getSheetName());
		int iRowCount = datasheet.getLastRowNum();
		int iColCount = excel.columncount(datasheet, 0);
		//System.out.println(iColCount + "  " + iRowCount);
		int RowNo = -1;
		int intCol = -1;
		for (int j = 0; j <= iColCount; j++) {
			String strExcelColName = excel.CellValue(datasheet, 0, j);
			//CommonLib.getLogger(strConfig).info("Actual ColName: " + strExcelColName);
			//logger.info("Actual ColName: " + strExcelColName);
			if(strExcelColName.equalsIgnoreCase(strColName)){
				intCol = j;
				break;
			}
		}

		for (int i = 1; i <= iRowCount; i = i + 1) {
			String actTCID = excel.CellValue(datasheet, i, 0);
			String actIteration = excel.CellValue(datasheet, i, 2);
//			CommonLib.getLogger(strConfig).info("Actual ColName: " + actTCID);
			if (actTCID != null && actIteration != null) {
//				CommonLib.getLogger(strConfig).info("1st if");
//				CommonLib.getLogger(strConfig).info(actIteration+ "  " + strOccurence + "  " + actTCID + "  " + strTCID);
				if (actTCID.equalsIgnoreCase(strTCID) &&  actIteration.equalsIgnoreCase(strOccurence)) {
					//CommonLib.getLogger(strConfig).info("passed 2 if's");
					RowNo = i;
					break;
				}
			} else {
				RowNo = 0;
			}
		}
		//System.out.println ( excel.CellValue(datasheet, RowNo, intCol));
		if (RowNo > 0 && intCol >= 0) {
			returnVal = excel.CellValue(datasheet, RowNo, intCol);
		}
		if (returnVal != null) {
			return returnVal;
		} else {
			return "";
		}
	}

	

	public static void Init_DictionaryObjects(String strConfig) {
		//*** Dictionary Objects ***
		
		dictionaryObjects.init_objHomePage_DO(strConfig);
		dictionaryObjects.init_ObjTestCaseDetails_DO(strConfig);
		dictionaryObjects.init_ObjDriverDetails_DO(strConfig);
		dictionaryObjects.init_ObjOutput_DO(strConfig);

		
		//*** Constants ***
	
		constants.init_DefaultDO_HomePage();

		
		
	}
}
