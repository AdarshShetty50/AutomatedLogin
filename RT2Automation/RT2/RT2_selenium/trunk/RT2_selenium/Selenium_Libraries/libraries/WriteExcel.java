package libraries;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcel {
	
	
	public static void insertValueInCell(String strFileNameWithPath, String worksheetName, int rowNum, int ColNum, String value) {
	try {
	    FileInputStream file = new FileInputStream(new File(strFileNameWithPath));

	    XSSFWorkbook workbook = new XSSFWorkbook(file);
	    XSSFSheet sheet = workbook.getSheet(worksheetName);
	    Cell cell = null;

	    //Update the value of cell
	    cell = sheet.getRow(rowNum).getCell(ColNum);
	    cell.setCellValue(value);
	    

	    file.close();

	    FileOutputStream outFile = new FileOutputStream(new File(strFileNameWithPath));
	    workbook.write(outFile);
	    outFile.close();

	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e1) {
	    e1.printStackTrace();
	}
}

}
