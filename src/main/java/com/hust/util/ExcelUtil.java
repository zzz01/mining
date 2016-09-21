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

    public static List<String[]> read(String filename, InputStream inputStream)
            throws FileNotFoundException, IOException {
        List<String[]> list = new ArrayList<String[]>();
        Workbook workbook;
        if(filename.endsWith("xls")){
            workbook = new HSSFWorkbook(inputStream);
        }else{
            workbook = new XSSFWorkbook(inputStream);
        }
        Sheet sheet = workbook.getSheetAt(0);
        int rowNum = sheet.getLastRowNum();
        int colNum = sheet.getRow(0).getLastCellNum();
        for (int i = 0; i <= rowNum; i++) {
            String[] rowStr = new String[colNum];
            for (int j = 0; j < colNum; j++) {
                try {
                    Cell cell = sheet.getRow(i).getCell(j);
                    if (cell.getCellType() == 0) {
                        rowStr[j] = TimeUtil.convert(cell);
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

    public static HSSFWorkbook exportToExcel(List<String[]>...lists) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        for (int k = 0; k < lists.length; k++) {
            List<String[]> list = lists[k];
            Sheet sheet = workbook.createSheet("sheet" + (k + 1));
            for (int i = 0; i < list.size(); i++) {
                String[] rowList = list.get(i);
                Row row = sheet.createRow(i);
                for (int j = 0; j < rowList.length; j++) {
                    Cell cell = row.createCell(j);
                    cell.setCellValue(rowList[j]);
                }
            }
        }
        return workbook;
    }
}
