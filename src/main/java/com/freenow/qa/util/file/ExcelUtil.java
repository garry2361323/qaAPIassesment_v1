package com.freenow.qa.util.file;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.util.Internal;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.lang.reflect.Method;

public class ExcelUtil {
    public static ArrayList<Object[]> getDataFromExcel(Method m) throws IOException {
        XSSFSheet sheet = null;
        ArrayList<Object[]> myData = new ArrayList<Object[]>();
        FileInputStream fis = new FileInputStream(new File("src/main/java/com/freenow/qa/testdata/testdata.xlsx"));
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        if (m.getName().equalsIgnoreCase("testfindByStatus")) {
            sheet = wb.getSheetAt(0);
        } else if (m.getName().equalsIgnoreCase("testfindByTags")) {
            sheet = wb.getSheetAt(1);
        } else if (m.getName().equalsIgnoreCase("testfindByPetId")) {
            sheet = wb.getSheetAt(2);
        }
        Iterator<Row> rowIterator = sheet.iterator();
        String status = null;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                if (cell.getCellType().equals(CellType.STRING)) {
                    System.out.println(cell.getStringCellValue());
                    status = cell.getStringCellValue();
                } else if (cell.getCellType().equals(CellType.NUMERIC)) {
                    System.out.println(cell.getNumericCellValue());
                    status = Integer.toString((int) cell.getNumericCellValue());
                }
                Object ob[] = {status};
                myData.add(ob);
            }
        }
        return myData;
    }
}
