package com.tms.pages.user.booking;

import com.tms.pages.user.enquiry.EnquiryPage;
import com.tms.util.common.CommonSteps;
import com.tms.util.dbutils.DbUtil;
import com.tms.util.excelutils.ExcelUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import java.util.Map;
import java.util.logging.Logger;
import static org.testng.Assert.assertEquals;

/**
 * @author Kithmi Kalpana
 * Page Class for book tour package by user
 *
 */


public class TourBookingPage {
    private WebDriver driver;
    private CommonSteps commonStepsObj;
    private By detailsButtonElement = By.xpath("//a[@href='package-details.php?pkgid=1']");
    private By fromDateElement = By.id("datepicker");
    private By toDateElement = By.id("datepicker1");
    private By commentElement = By.name("comment");
    private By bookButtonElement = By.name("submit2");
    private By bookingSuccessElement = By.xpath("//*[contains(@class, 'succWrap')]");
    private By tourPackageElement = By.xpath("//a[@href='package-list.php']");

    private static final DbUtil dbUtil = new DbUtil();
    private static final Logger LOGGER = Logger.getLogger(TourBookingPage.class.getName());

    public TourBookingPage(WebDriver driver, CommonSteps commonStepsObj) {
        this.driver = driver;
        this.commonStepsObj = commonStepsObj;

    }

    public void clickOnDetailsButton() {
        driver.findElement(detailsButtonElement).click();
    }

    public void clickOnTourPackageMenu() {
        driver.findElement(tourPackageElement).click();
    }

    public void bookingTheTourPackageWithExcelData(XSSFRow row) {
        commonStepsObj.scrollPage();
        commonStepsObj.waitUntilNextElementAppears(fromDateElement,4000);
        driver.findElement(fromDateElement).click();
        driver.findElement(fromDateElement).sendKeys(row.getCell(1).toString());
        driver.findElement(toDateElement).click();
        driver.findElement(toDateElement).sendKeys(row.getCell(2).toString());
        driver.findElement(commentElement).sendKeys(row.getCell(3).toString());
        driver.findElement(bookButtonElement).click();
    }
    public void verifyBookingMessageWithExcel(String expectedResult) {
        String BookingMessage = driver.findElement(bookingSuccessElement).getText();
        Assert.assertEquals(BookingMessage,expectedResult);
    }

    public void verifyValidationMessageWithExcel(String expectedResult) {
        String ValidationMessage = driver.findElement(fromDateElement).getAttribute("validationMessage");
        Assert.assertEquals(ValidationMessage,expectedResult);
    }
    public void verifyErrorValidationMessageWithExcel(String expectedResult) {
        String ValidationMessage = driver.findElement(fromDateElement).getAttribute("validationMessage");
        Assert.assertEquals(ValidationMessage,expectedResult);
    }

    public void verifyTourDetailOfSubmittedEnquiryInTheDB(String expectedFromDate,String expectedToDate,String expectedComment){
        Map<String, String> result = dbUtil.getDataFromBookingTable();
        if (result != null) {
            LOGGER.info("From Date : " + result.get("FromDate"));
            assertEquals(expectedFromDate, result.get("FromDate"));
            LOGGER.info("To Date : " + result.get("ToDate"));
            assertEquals(expectedToDate, result.get("ToDate"));
            LOGGER.info("Comment : " + result.get("Comment"));
            assertEquals(expectedComment, result.get("Comment"));
        } else {
            LOGGER.info("No db record found for Full Name");
        }
    }




}
