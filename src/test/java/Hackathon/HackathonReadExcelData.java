package Hackathon;

import java.io.FileInputStream;
import java.io.IOException;



import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class HackathonReadExcelData {



public static String brandOfBike,invalidMailId;


public static String Sheet1() throws IOException
{
String excelSheetPath = "src\\test\\resources\\ZigExcel.xlsx";
FileInputStream fis = new FileInputStream(excelSheetPath);
@SuppressWarnings("resource")
XSSFWorkbook workbook = new XSSFWorkbook(fis);

Sheet sheet1 = workbook.getSheet("Brand");
for(Row row1 : sheet1)
{
Cell cell = row1.getCell(0);
brandOfBike = cell.getStringCellValue();
}
return brandOfBike;
}

public static String Sheet2() throws IOException
{
String excelSheetPath = "src\\test\\resources\\ZigExcel.xlsx";
FileInputStream fis = new FileInputStream(excelSheetPath);
@SuppressWarnings("resource")
XSSFWorkbook workbook = new XSSFWorkbook(fis);
Sheet sheet2 = workbook.getSheet("Credentials");
for(Row row : sheet2)
{
Cell cell = row.getCell(0);
invalidMailId = cell.getStringCellValue();

}
return invalidMailId;
}



}
