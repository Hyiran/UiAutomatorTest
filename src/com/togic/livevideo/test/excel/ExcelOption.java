package com.togic.livevideo.test.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import android.util.Log;


public class ExcelOption {
	public static String cellValue;
	public static String rowValue;
	private int totalRowCount;
	private static final String TAG = "AutoTestLog";

	
	
	public Integer getTotalRowCount(int sheetindex, String filepath) throws IOException {
		FileInputStream excelfile = new FileInputStream(filepath);
		HSSFWorkbook wbook = new HSSFWorkbook(excelfile);
		HSSFSheet sheet = wbook.getSheetAt(sheetindex);
		totalRowCount = sheet.getLastRowNum() + 1;
		excelfile.close();
		return totalRowCount;
	}
	

	public String getCellValue(int sheetindex, String filepath, int rownum, int colnum) throws IOException {

		FileInputStream xlsfile = new FileInputStream(filepath);
		HSSFWorkbook wb = new HSSFWorkbook(xlsfile);
		HSSFSheet sheet = wb.getSheetAt(sheetindex);
		// row :0 clo:0 取第一行第一列
		HSSFRow row = null;
		HSSFCell cell = null;
		try {
			row = sheet.getRow(rownum);
			cell = row.getCell(colnum);
		} catch(Exception e) {
			row = sheet.createRow(rownum);
			cell = row.getCell(colnum);
		}
		if (cell == null) {// 单元格为空设置cellStr为空串  
			
			cellValue = "";  
        } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {// 对布尔值的处理  
        	cellValue = String.valueOf(cell.getBooleanCellValue());  
        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {// 对数字值的处理  
        	cellValue = cell.getNumericCellValue() + "";             
        } else {// 字符串处理  
        	cellValue = cell.getStringCellValue();  
        } 
		xlsfile.close();
		//System.out.println(cellValue);
		return cellValue;
	}
	
	public String getRowValue(int sheetindex, String filepath, int rownum, int colnum) throws IOException {

		FileInputStream xlsfile = new FileInputStream(filepath);
		HSSFWorkbook wb = new HSSFWorkbook(xlsfile);
		HSSFSheet sheet = wb.getSheetAt(sheetindex);
		// row :0 clo:0 取第一行第一列
		HSSFRow row = null;
		HSSFCell cell = null;
		try {
			row = sheet.getRow(rownum);
			cell = row.getCell(colnum);
		} catch(Exception e) {
			row = sheet.createRow(rownum);
			cell = row.getCell(colnum);
		}
		if (cell == null) {// 单元格为空设置cellStr为空串  
			
			cellValue = "";  
        } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {// 对布尔值的处理  
        	cellValue = String.valueOf(cell.getBooleanCellValue());  
        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {// 对数字值的处理  
        	cellValue = cell.getNumericCellValue() + "";             
        } else {// 字符串处理  
        	cellValue = cell.getStringCellValue();  
        } 
		xlsfile.close();
		//System.out.println(cellValue);
		return cellValue;
	}
	public void setValue(int sheetindex, String filepath, int rownum, int colnum, String value) throws IOException {

		FileInputStream xlsfile = new FileInputStream(filepath);
		HSSFWorkbook wbook = new HSSFWorkbook(xlsfile);
		HSSFSheet sheet0 = wbook.getSheetAt(sheetindex);
		// row :0 clo:0 取第一行第一列
		HSSFRow row = null;
		HSSFCell cell = null;
		try {
			row = sheet0.getRow(rownum);
			cell = row.getCell(colnum);
		} catch(Exception e) {
			row = sheet0.createRow(rownum);
			cell = row.getCell(colnum);
		}
		row.createCell(colnum).setCellValue(value);
		
        FileOutputStream os = new FileOutputStream(filepath);  
		wbook.write(os); 
		os.close(); 
		xlsfile.close();
	}
	public int converStr2Num(String value) {
		String sec[] = value.toString().split("\\.");
		return Integer.parseInt(sec[0]);
	}
}