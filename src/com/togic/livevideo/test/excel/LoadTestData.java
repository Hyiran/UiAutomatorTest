package com.togic.livevideo.test.excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import android.util.Log;

import com.togic.livevideo.test.excel.ExcelOption;

public class LoadTestData {

	private int totalRowCount;
	private static final String TAG = "AutoTestLog";

	public static String cellValue;

	
	public Map<String, HashMap<String, Map<String, String>>> loadTestCasetest(
			int sheetindex, String filepath) throws IOException {
		

		Map<String, HashMap<String, Map<String, String>>> testcases = new HashMap<String, HashMap<String, Map<String, String>>>();
		Map<String, Map<String, String>> testcase = new HashMap<String, Map<String, String>>();
		Map testdata = new HashMap();
		int tesecaseNum;
		boolean FLAG_CLEAR_TESTDATA = false;
		ExcelOption exc = new ExcelOption();
		int totalRowCount = exc.getTotalRowCount(0, filepath);
		String emptyTestID = null;
		
		FileInputStream xlsfile = new FileInputStream(filepath);
		HSSFWorkbook wb = new HSSFWorkbook(xlsfile);
		HSSFSheet sheet = wb.getSheetAt(sheetindex);
		String testID = null;
		
		for (int i = 1; i < totalRowCount; i++) {
			for (int j=0; j<16;j++) {
				HSSFRow row = null;
				HSSFCell cell = null;
				String cellValue = null;
				try {
					row = sheet.getRow(i);
					cell = row.getCell(j);
				} catch(Exception e) {
					row = sheet.createRow(i);
					cell = row.getCell(j);
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
				if (j==0) {
					testID = cellValue;
					if (!testID.isEmpty()) {
						testdata.put("testId", testID);
						emptyTestID = testID;
					} else {
						testdata.put("testId", emptyTestID);
					}
				} else if (j==1) {
					testdata.put("testDesign", cellValue);
				} else if (j==2) {
					testdata.put("testResult", cellValue);
				} else if (j==3) {
					testdata.put("flagExec", cellValue);
				} else if (j==4) {
					
				} else if (j==5) {
					testdata.put("optionKeyWord", cellValue);
				} else if (j==6) {
					testdata.put("action", cellValue);
				} else if (j==7) {
					testdata.put("argv1", cellValue);
				} else if (j==8) {
					testdata.put("argv2", cellValue);
				} else if (j==9) {
					testdata.put("returnValue", cellValue);
				} else if (j==10) {
					testdata.put("expectResultKeyWord", cellValue);
				} else if (j==11) {
					testdata.put("expectResultValue", cellValue);
				} else if (j==12){
					testdata.put("checkCondition", cellValue);
				}else if (j==13){
					testdata.put("execFailKeyword", cellValue);
				}else if (j==14){
					testdata.put("execFailElement", cellValue);
				} else {
					testdata.put("execFailCondition", cellValue);
				}
			}
			testdata.put("rowNumber", i);
			testcase.put(exc.getCellValue(0, filepath, i, 4), LoadTestData
					.cloneTestdata((HashMap<String, String>) testdata));
			if (!testID.isEmpty()) {
				// testcases.put(testID, (HashMap<String, Map<String,
				// String>>)testcase);
				testcases
						.put(testID,
								LoadTestData
										.cloneTestcase((HashMap<String, Map<String, String>>) testcase));

			} else {
				// testcases.put(emptyTestID, (HashMap<String, Map<String,
				// String>>)Example.mapClone((HashMap<String, Map<String,
				// String>>) testcase));

			}
			// System.out.println(Float.valueOf(exc.getCellValue(0, filepath, i,
			// 4)));
			Log.d(TAG, testdata.toString());
			if (!testID.isEmpty()) {
				testdata.clear();
				testcase.clear();
			}
		}
		xlsfile.close();
		return testcases;
	}
	
	
	
	
	
	
	public Map<String, HashMap<String, Map<String, String>>> loadTestCase(
			int sheetindex, String filepath) throws IOException {

		Map<String, HashMap<String, Map<String, String>>> testcases = new HashMap<String, HashMap<String, Map<String, String>>>();
		Map<String, Map<String, String>> testcase = new HashMap<String, Map<String, String>>();
		Map testdata = new HashMap();
		int tesecaseNum;
		boolean FLAG_CLEAR_TESTDATA = false;
		ExcelOption exc = new ExcelOption();


		int totalRowCount = exc.getTotalRowCount(0, filepath);
		String emptyTestID = null;
		for (int i = 1; i < totalRowCount; i++) {
			String testID = exc.getCellValue(0, filepath, i, 0);
			if (!testID.isEmpty()) {
				testdata.put("testId", testID);
				emptyTestID = testID;
			} else {
				testdata.put("testId", emptyTestID);
			}
			testdata.put("testDesign", exc.getCellValue(0, filepath, i, 1));
			testdata.put("testResult", exc.getCellValue(0, filepath, i, 2));
			testdata.put("flagExec", exc.getCellValue(0, filepath, i, 3));
			testdata.put("optionKeyWord", exc.getCellValue(0, filepath, i, 5));
			testdata.put("action", exc.getCellValue(0, filepath, i, 6));
			testdata.put("argv1", exc.getCellValue(0, filepath, i, 7));
			testdata.put("argv2", exc.getCellValue(0, filepath, i, 8));
			testdata.put("returnValue", exc.getCellValue(0, filepath, i, 9));
			testdata.put("expectResultKeyWord",
					exc.getCellValue(0, filepath, i, 10));
			testdata.put("expectResultValue",
					exc.getCellValue(0, filepath, i, 11));
			testdata.put("checkCondition", exc.getCellValue(0, filepath, i, 12));
			testdata.put("rowNumber", i);
			testcase.put(exc.getCellValue(0, filepath, i, 4), LoadTestData
					.cloneTestdata((HashMap<String, String>) testdata));
			if (!testID.isEmpty()) {
				// testcases.put(testID, (HashMap<String, Map<String,
				// String>>)testcase);
				testcases
						.put(testID,
								LoadTestData
										.cloneTestcase((HashMap<String, Map<String, String>>) testcase));

			} else {
				// testcases.put(emptyTestID, (HashMap<String, Map<String,
				// String>>)Example.mapClone((HashMap<String, Map<String,
				// String>>) testcase));

			}
			// System.out.println(Float.valueOf(exc.getCellValue(0, filepath, i,
			// 4)));
			Log.d(TAG, testdata.toString());
			if (!testID.isEmpty()) {
				testdata.clear();
				testcase.clear();
			}
		}
		// System.out.println(testcases);
		// System.out.println(testcases.get("Test_001").get("2.0"));
		return testcases;
	}

	public static HashMap<String, Map<String, String>> cloneTestcase(
			HashMap<String, Map<String, String>> Maptest) {

		Map<String, Map<String, String>> MapC001 = new HashMap<String, Map<String, String>>();

		MapC001.putAll(Maptest);
		return (HashMap<String, Map<String, String>>) MapC001;

	}

	public static HashMap<String, String> cloneTestdata(
			HashMap<String, String> Maptest) {

		Map<String, String> MapC001 = new HashMap<String, String>();

		MapC001.putAll(Maptest);
		return (HashMap<String, String>) MapC001;

	}

}