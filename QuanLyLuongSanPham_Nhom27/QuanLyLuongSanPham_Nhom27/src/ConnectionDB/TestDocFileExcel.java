/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConnectionDB;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import 

/**
 *
 * @author Acer
 */
import java.io.PrintStream;
public class TestDocFileExcel {
    public static void main(String[] args) {
        try {
            readFileExcel();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void readFileExcel() throws IOException {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF8"));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        FileInputStream file = new FileInputStream("src/ExcelFile/InputPhongBan.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(file);
        XSSFSheet sheet = wb.getSheetAt(0);
        FormulaEvaluator formula = wb.getCreationHelper().createFormulaEvaluator();
        for (Row row : sheet){
            if (row.getCell(0) != null){
                System.out.println(row.getCell(0).getStringCellValue().getClass().getSimpleName());
            }
            if (row.getCell(1) != null){
                System.out.println(row.getCell(1));
            }
        }
        wb.close();
        file.close();
    }
}
