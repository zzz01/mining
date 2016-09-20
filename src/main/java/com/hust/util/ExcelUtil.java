package com.hust.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

    public static List<String[]> read(InputStream inputStream) throws FileNotFoundException, IOException {
        List<String[]> list = new ArrayList<String[]>();
        Workbook workbook;
        workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        int rowNum = sheet.getLastRowNum();
        int colNum = sheet.getRow(0).getLastCellNum();
        for (int i = 0; i <= rowNum; i++) {
            String[] rowStr = new String[colNum];
            for (int j = 0; j < colNum; j++) {
                try {
                    Cell cell = sheet.getRow(i).getCell(j);
                    if (cell.getCellType() == 0) {
                        rowStr[j] = Time.convert(cell);
                    } else {
                        rowStr[j] = cell.toString();
                    }
                } catch (Exception e) {
                    rowStr[j] = "";
                }
            }
            list.add(rowStr);
        }
        workbook.close();
        return list;
    }

    public static HSSFWorkbook exportToExcel(List<String[]> list) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("结果");
        for (int i = 0; i < list.size(); i++) {
            String[] rowList = list.get(i);
            Row row = sheet.createRow(i);
            for (int j = 0; j < rowList.length; j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue(rowList[j]);
            }
        }
        return workbook;
    }
}
