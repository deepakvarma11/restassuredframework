package Utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public String path;
	private Workbook workbook;
	private Sheet sheet;
	private Row row;
	private Cell cell;

	public FileInputStream fin;
	public FileOutputStream fileOut;

//	public static void main(String[] args) {
//		
//		
//		ExcelReader read = new ExcelReader(System.getProperty("user.dir") + "/src/test/resources/Suites/Suites.xlsx");
//		System.out.println(read.getCellData("APITestDatas", "HeaderValue", "TC_01"));
		
//		logger.info("Done " + read.getCellData("APITestDatas", "querykey1", "TC_01") );
//		read.getCellData("APITestData", "Body", "TC_01");
//	}

	public ExcelReader(String path) {
		this.path = path;

		try {
			fin = new FileInputStream(path);
			String fileExtension = path.substring(path.indexOf("."));
//			System.out.println(fileExtension);
			if (fileExtension.equalsIgnoreCase(".xlsx")) {
				workbook = new XSSFWorkbook(fin);
			} else {
				workbook = new HSSFWorkbook(fin);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getCellDataValue(String sheetName, String columnName, String CellValue) {

		String cellData = null;
		sheet = workbook.getSheet(sheetName);

		int index = workbook.getSheetIndex(sheetName);
		if (index == -1) {
			return "";
		}

		int rowCount = sheet.getLastRowNum() + 1;
//		System.out.println(rowCount);

		int rowNum = 0;
		int colNum = 0;

		for (int i = 1; i < rowCount; i++) {
			int col = 0;
			if ((sheet.getRow(i).getCell(col).getStringCellValue().equals(CellValue))) {
				rowNum = i;
			}
		}

		if (rowNum <= 0) {
			return "No row";
		}

		int lastColNum = 13;
		for (int i = 0; i < lastColNum; i++) {
			int row = 0;
			if ((sheet.getRow(row).getCell(i).getStringCellValue().trim()).equalsIgnoreCase(columnName.trim())) {
				colNum = i;
			}
		}

		if (colNum <= 0) {
			return "No Col";
		}

		row = sheet.getRow(rowNum);
		cell = row.getCell(colNum, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		cellData = cell.getStringCellValue().trim();

		return cellData;

//		row = sheet.getRow(2);
//		String value = row.getCell(2).getStringCellValue();
//		System.out.println(value);
//		try {
//			cell = row.getCell(6,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
//			System.out.println(cell.getStringCellValue() + "//");
//		}catch(Exception e) {
//			System.out.println(e.getMessage());
//		}

//		for(int i=0 ; i < rowCount;i++) {
//			row = sheet.getRow(i);
//			int lastColumn = Math.max(row.getLastCellNum(), 12);
//			
//			for(int j = 0; j<lastColumn;j++) {
//				System.out.print(row.getCell(j,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue() + "|| ");
//			}
//			
//			System.out.println();
//		}

	}
}
