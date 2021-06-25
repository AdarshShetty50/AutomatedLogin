package libraries;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.RowIdLifetime;

import libraries.Constants;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Reporter;
@SuppressWarnings("unused")
public class ReadExcel {

	
	static XSSFWorkbook GetExcelWorkbook(String filename) {
		XSSFWorkbook workbook = null;
		InputStream XLSXfiletoread = null;
		try {
			XLSXfiletoread = new FileInputStream(filename);
			workbook = new XSSFWorkbook(XLSXfiletoread); 
		} catch (FileNotFoundException e) {
			File file = new File(filename);
			try {
				XSSFWorkbook workbook1 = new XSSFWorkbook();
				FileOutputStream out = new FileOutputStream(file);
				out.close();
				return workbook1;
			} catch (EncryptedDocumentException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
//			Reporter.log(e.getMessage());
			
//			CommonLib.getLogger(strConfig).info(e.getMessage());
		} catch (IOException e) {
			Reporter.log(e.getMessage());
			e.printStackTrace();
		}
		return workbook;
	}
	
	
	/*
	  Method Name: readXLSXfile
	  Description: Returns the Worksheet from the file provided in the input params
	  Input Parameters: filename - excel file location, worksheetname - sheet to be worked.
	  Output: Excel worksheet object of type XSSFSheet
	  Author: Rahul Vaidya
	  Creation Date: 30/03/2016
	  Last Modified By: Rahul Vaidya
	  Last Modified Date: 30/03/2016
	*/
	
	public XSSFSheet readXLSXfile(String filename, String worksheetname) {
		XSSFWorkbook workbook = null;
		workbook = GetExcelWorkbook(filename);
		XSSFSheet sheet = workbook.getSheet(worksheetname);
		return sheet;
	}
	
	public static XSSFCellStyle getHeaderStyle(XSSFWorkbook wb){
		XSSFCellStyle headerStyle = wb.createCellStyle();
		headerStyle.setAlignment(CellStyle.ALIGN_CENTER);
		//headerStyle.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.index);
		headerStyle.setFillBackgroundColor(IndexedColors.LIGHT_BLUE.index);
		headerStyle.setBorderBottom(CellStyle.BORDER_THICK);
		headerStyle.setBorderLeft(CellStyle.BORDER_THICK);
		headerStyle.setBorderRight(CellStyle.BORDER_THICK);
		headerStyle.setBorderTop(CellStyle.BORDER_THICK);
		return headerStyle;
	}
	
	public XSSFCellStyle getDataStyle(XSSFWorkbook wb){
		XSSFCellStyle dataStyle = wb.createCellStyle();
		dataStyle.setAlignment(CellStyle.ALIGN_CENTER);
		//dataStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
		dataStyle.setBorderBottom(CellStyle.BORDER_THIN);
		dataStyle.setBorderLeft(CellStyle.BORDER_THIN);
		dataStyle.setBorderRight(CellStyle.BORDER_THIN);
		dataStyle.setBorderTop(CellStyle.BORDER_THIN);
		return dataStyle;
	}
	
	/*
	  Method Name: ReadSheetByIndex
	  Description: returns the sheet by the index provided from the file location in input
	  Input Parameters: filename: filelocation, sheetindex: index of the sheet to be accessed
	  Output: returns the sheet object of type XSSFSheet 
	  Author: Rahul Vaidya
	  Creation Date: 30/03/2016
	  Last Modified By: Rahul Vaidya
	  Last Modified Date: 30/03/2016
	*/
	
	public XSSFSheet ReadSheetByIndex(String filename, int sheetindex) {
		InputStream XLSXfiletoread = null;
		XSSFWorkbook workbook = null;
		try {
			XLSXfiletoread = new FileInputStream(filename);
			workbook = new XSSFWorkbook(XLSXfiletoread);
		} catch (FileNotFoundException e) {
//			Main.logger.info(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}

		XSSFSheet sheet = workbook.getSheetAt(sheetindex);

		return sheet;
	}
	
	/*
	  Method Name: TotalSheets
	  Description: Provides the total no. of sheets in the given excel object
	  Input Parameters: workbook- Excel workbook object of the type XSSFWorkbook
	  Output: Sheets count- returns no. of sheets 
	  Author: Rahul Vaidya
	  Creation Date: 30/03/2016
	  Last Modified By: Rahul Vaidya
	  Last Modified Date: 30/03/2016
	*/
	
	public int TotalSheets(XSSFWorkbook workbook) {
		int Sheetscount;
		Sheetscount = workbook.getNumberOfSheets();
		return Sheetscount;
	}
	
	/*
	  Method Name: CellValue
	  Description: Returns the cell value in string format
	  Input Parameters: worksheet, row & column
	  Output: cell value in string format
	  Author: Rahul Vaidya
	  Creation Date: 30/03/2016
	  Last Modified By: Rahul Vaidya
	  Last Modified Date: 30/03/2016
	*/
	
	public String CellValue(XSSFSheet sheet, int row, int column) {
		DataFormatter formatter = new DataFormatter();
		String cellvalue;
		XSSFRow currentrow = sheet.getRow(row);
		if (currentrow != null) {
			XSSFCell currentcell = currentrow.getCell(column, Row.RETURN_BLANK_AS_NULL);
			if (currentcell != null) {
				cellvalue = formatter.formatCellValue(currentcell);
			} else {
				cellvalue = null;
			}
		} else {
			cellvalue = null;
		}
		return cellvalue;
	}
	
	/*
	  Method Name: rowcount
	  Description: Provides the rowcount for a particular sheet
	  Input Parameters: worksheet
	  Output: number of rows in int format
	  Author: Rahul Vaidya
	  Creation Date: 30/03/2016
	  Last Modified By: Rahul Vaidya
	  Last Modified Date: 30/03/2016
	*/
	
	public int rowcount(XSSFSheet sheet) {
		int rowcount = sheet.getLastRowNum();
		return rowcount;
	}
	
	/*
	  Method Name: columncount
	  Description: provides the total columns in a particular row
	  Input Parameters: sheet and row number
	  Output: total columns in the row in integer format
	  Author: Rahul Vaidya
	  Creation Date: 30/03/2016
	  Last Modified By: Rahul Vaidya
	  Last Modified Date: 30/03/2016
	*/
	
	public int columncount(XSSFSheet sheet, int row) {
		int columncnt;
		columncnt = sheet.getRow(row).getPhysicalNumberOfCells();
		return columncnt;
	}
	
	/*
	  Method Name:
	  Description: 
	  Input Parameters: 
	  Output: 
	  Author: Rahul Vaidya
	  Creation Date: 30/03/2016
	  Last Modified By: Rahul Vaidya
	  Last Modified Date: 30/03/2016
	*/
	
	int RowNumber(XSSFSheet sheet, String SearchData, int cols) {
		int RowNo;
		RowNo = 0;
		int rowcount = sheet.getLastRowNum();

		for (int i = 1; i <= rowcount; i = i + 1) {
			String Cellval = CellValue(sheet, i, cols);
			if(Cellval != null && !Cellval.trim().isEmpty()) {
			if (Cellval.equalsIgnoreCase(SearchData)) {
				RowNo = i;
				break;
			}
			}
		}
		return RowNo;
	}

	public static XSSFWorkbook getWorkBook(String strFileNameWithPath) {
		XSSFWorkbook workbook = null;
		try {
			String ExcelFile = strFileNameWithPath;
			FileInputStream XLSXfiletoread = new FileInputStream(ExcelFile);
			workbook = new XSSFWorkbook(XLSXfiletoread);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return workbook;
	}

	public void saveExcelFile(String strConfig, String strFilePathwithName, XSSFWorkbook outputWorkbook) throws FileNotFoundException {
		// TODO Auto-generated method stub
		CommonLib.getLogger(strConfig).info("Inside  Save Excel");
//		FileOutputStream out = new FileOutputStream(strFilePathwithName);
		try {
		
			FileOutputStream out = new FileOutputStream(strFilePathwithName);
			outputWorkbook.write(out);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			CommonLib.getLogger(strConfig).error(e.getMessage());
		}
		
	}
}
