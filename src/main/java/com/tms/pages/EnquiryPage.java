package com.tms.pages;

import com.tms.util.excelutils.ExcelUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EnquiryPage {
    private WebDriver driver;

    public EnquiryPage(WebDriver driver) {
        this.driver = driver;
    }

    private By enquirypage = By.xpath("//a[contains(text(),' Enquiry ')]");
    private By fullName = By.id("fname");
    private By email = By.id("email");
    private By mobileNo = By.id("mobileno");
    private By subject = By.id("subject");
    private By description = By.id("description");
    private By submitbtn = By.name("submit1");


    public void submittingFormWithExcelData(XSSFRow row) {
        driver.findElement(fullName).sendKeys(row.getCell(1).toString());
        driver.findElement(email).sendKeys(row.getCell(2).toString());
        driver.findElement(mobileNo).sendKeys(row.getCell(3).toString());
        driver.findElement(subject).sendKeys(row.getCell(4).toString());
        driver.findElement(description).sendKeys(row.getCell(5).toString());
        driver.findElement(submitbtn).click();

    }

    public void saveTestResults(int row, int column) {
        ExcelUtil.rowNumber = row ;
        ExcelUtil.columnNumber = column;

    }


}
