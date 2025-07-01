package com.jenkinsBuilDemo.StepDefs;

import io.cucumber.java.en.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFDialogsheet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class JavaPractice {

    @Given("^Read and print Excel data (.+)$")
    public void readExcelFile(String tcID) throws IOException {
        InputStream fis = new FileInputStream("TestData/Datasheet.xlsx");
        Workbook workbook = WorkbookFactory.create(fis);
        Sheet sheet = workbook.getSheet("feature");
        Row row = sheet.getRow(0);
        System.out.println("TC_ID = "+row.getCell(0).getStringCellValue()+"; Data = "+row.getCell(1).getStringCellValue());
    }

    @Given("^Read and print JSON data$")
    public void readJSON(){

    }

}
