package com.tms.pages.user.enquiry;

import com.tms.util.dbutils.DbUtil;
import com.tms.util.excelutils.ExcelUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Map;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class EnquiryPage {
    private static final DbUtil dbUtil = new DbUtil();
    private static final Logger LOGGER = Logger.getLogger(EnquiryPage.class.getName());
    private WebDriver driver;


    //Constructor
    public EnquiryPage(WebDriver driver) {

        this.driver = driver;
    }

    //Web Elements
    private By fullName = By.id("fname");
    private By email = By.id("email");
    private By mobileNo = By.id("mobileno");
    private By subject = By.id("subject");
    private By description = By.id("description");
    private By submitBtn = By.name("submit1");
    private By successfulMessage = By.xpath("//div[@class='succWrap']");



    public void submitEnquiry(XSSFRow row) {
        driver.findElement(fullName).sendKeys(row.getCell(1).toString());
        driver.findElement(email).sendKeys(row.getCell(2).toString());
        driver.findElement(mobileNo).sendKeys(row.getCell(3).toString());
        driver.findElement(subject).sendKeys(row.getCell(4).toString());
        driver.findElement(description).sendKeys(row.getCell(5).toString());
        driver.findElement(submitBtn).click();



    }

    public void verifyValidationMessageWhenHavingEmptyTextFields(By emptyTextField, String expectedValidationMessage){
        String validationMessage = driver.findElement(emptyTextField).getAttribute("validationMessage");
        assertEquals(validationMessage, expectedValidationMessage);

    }

    public void verifyValidationMessageForValidEmail(String expectedValidationMessage){
        String validationMessage = driver.findElement(email).getAttribute("validationMessage");
        assertEquals(validationMessage, expectedValidationMessage);
    }

    public void verifySuccessFullSubmissionMessage(String expectedSuccessfulMessage){
        String successfulSubmissionMessage = driver.findElement(successfulMessage).getText();
        assertEquals(successfulSubmissionMessage, expectedSuccessfulMessage);

    }

    public void verifyFullNameOfSubmittedEnquiryInTheDB(String expectedFullName){
        Map<String, String> result = dbUtil.getDataFromEnquiryTable();
        if (result != null) {
            LOGGER.info("Full Name : " + result.get("FullName"));
            assertEquals(expectedFullName, result.get("FullName"));
        } else {
            LOGGER.info("No db record found for Full Name");
        }
    }



    public void saveTestResults(int row, int column) {
        ExcelUtil.rowNumber = row ;
        ExcelUtil.columnNumber = column;

    }


}
