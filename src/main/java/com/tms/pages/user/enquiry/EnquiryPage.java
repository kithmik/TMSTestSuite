package com.tms.pages.user.enquiry;

import com.tms.pages.user.navigation.UserViewPieceObjectPage;
import com.tms.util.common.CommonSteps;
import com.tms.util.dbutils.DbUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.Map;
import java.util.logging.Logger;
import static org.testng.Assert.assertEquals;

/**
 * @author Malsha Nishadini
 * Page Class for Enquiry Submission by a user
 *
 */

public class EnquiryPage {
    private static final DbUtil dbUtil = new DbUtil();
    private static final Logger LOGGER = Logger.getLogger(EnquiryPage.class.getName());
    private WebDriver driver;
    private UserViewPieceObjectPage userViewPieceObjectPage;


    public EnquiryPage(WebDriver driver) {
        this.driver = driver;

    }

    private By fullName = By.id("fname");
    private By email = By.id("email");
    private By mobileNo = By.id("mobileno");
    private By subject = By.id("subject");
    private By description = By.id("description");
    private By submitBtn = By.name("submit1");
    private By successfulMessage = By.xpath("//div[@class='succWrap']");

    public void submitEnquiry(XSSFRow row){
        LOGGER.info("Verifying submit enquiry");
        userViewPieceObjectPage.clickOnEnquiryTab();
        driver.findElement(fullName).sendKeys(row.getCell(1).toString());
        driver.findElement(email).sendKeys(row.getCell(2).toString());
        driver.findElement(mobileNo).sendKeys(row.getCell(3).toString());
        driver.findElement(subject).sendKeys(row.getCell(4).toString());
        driver.findElement(description).sendKeys(row.getCell(5).toString());
        driver.findElement(submitBtn).click();

    }

    public void verifySuccessFullSubmissionMessage(String expectedSuccessfulMessage){
        String successfulSubmissionMessage = driver.findElement(successfulMessage).getText();
        assertEquals(successfulSubmissionMessage, expectedSuccessfulMessage);

    }

    public void verifyValidationMessageForValidEmail(String expectedValidationMessage){
        String validationMessage = driver.findElement(email).getAttribute("validationMessage");
        assertEquals(validationMessage, expectedValidationMessage);
    }

    public void verifyValidationMessageForValidMobileNo(String expectedValidationMessage){
        String validationMessage = driver.findElement(mobileNo).getAttribute("validationMessage");
        assertEquals(validationMessage, expectedValidationMessage);
    }

    public void verifyValidationMessageForValidSubject(String expectedValidationMessage){
        String validationMessage = driver.findElement(subject).getAttribute("validationMessage");
        assertEquals(validationMessage, expectedValidationMessage);
    }

    public void verifyValidationMessageWhenHavingEmptyTextFields(String emptyTextFieldId, String expectedValidationMessage){
        String validationMessage = driver.findElement(By.id(emptyTextFieldId)).getAttribute("validationMessage");
        assertEquals(validationMessage, expectedValidationMessage);

    }

    public void verifyFullNameOfSubmittedEnquiryInTheDB(String expectedFullName){
        Map<String, String> result = dbUtil.getDataFromEnquiryTable();
        if (result != null) {
            assertEquals(expectedFullName, result.get("FullName"));
        } else {
            LOGGER.info("No db record found for Full Name");
        }
    }

}
