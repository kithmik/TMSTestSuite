package com.tms.pages.user.booking;

import com.tms.util.common.CommonSteps;
import com.tms.util.excelutils.ExcelUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class TourBookingPage {
    private WebDriver driver;
    private CommonSteps commonStepsObj;


    public TourBookingPage(WebDriver driver, CommonSteps commonStepsObj) {
        this.driver = driver;
        this.commonStepsObj = commonStepsObj;

    }

    private By detailsButtonElement = By.xpath("//a[@href=\"package-details.php?pkgid=1\"]");
    private By fromDateElement = By.id("datepicker");
    private By toDateElement = By.id("datepicker1");
    private By commentElement = By.name("comment");
    private By bookButtonElement = By.name("submit2");
    private By bookingSuccessElement = By.xpath("//*[contains(@class, 'succWrap')]");
    private By tourPackageElement = By.xpath("//a[@href='package-list.php']");


    public void clickOnDetailsButton() {
        driver.findElement(detailsButtonElement).click();
    }

    public void clickOnTourPackageMenu() {
        driver.findElement(tourPackageElement).click();
    }

    public void bookingTheTourPackageWithExcelData(XSSFRow row) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)");
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
    public void verifyUnsuccessfulBookingMessageWithExcel(String expectedResult) {
        Assert.assertEquals("",expectedResult);
    }

    public void verifyValidationMessageWithExcel(String expectedResult) {
        String ValidationMessage = driver.findElement(fromDateElement).getAttribute("validationMessage");
        Assert.assertEquals(ValidationMessage,expectedResult);

    }
    public void saveTestResults(int row, int column) {
        ExcelUtil.rowNumber = row ;
        ExcelUtil.columnNumber = column;
    }

//driver.findElement(fullName).getAttribute("validationMessage")

}
