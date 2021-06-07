package com.tms.pages.admin.booking;

import com.tms.pages.user.booking.TourBookingPage;
import com.tms.util.common.CommonSteps;
import com.tms.util.dbutils.DbUtil;
import com.tms.util.excelutils.ExcelUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.Map;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

/**
 * @author Kithmi Kalpana
 * Page Class for manage tour package by admin
 *
 */

public class ManageBookingPage {
    private WebDriver driver;
    private CommonSteps commonStepsObj;
    private By confirmElement = By.xpath("//table/tbody/tr[last()]/td[last()]/span/a[2]");
    private By cancelElement = By.xpath("//table/tbody/tr[last()]/td[last()]/span/a[1]");
    private By ActionElement = By.xpath("//table/tbody/tr[last()]/td[last()]");
    private By bookingConfirmElement =  By.xpath("//*[@class= 'succWrap']");

    private static final DbUtil dbUtil = new DbUtil();
    private static final Logger LOGGER = Logger.getLogger(TourBookingPage.class.getName());


    public ManageBookingPage(WebDriver driver, CommonSteps commonStepsObj) {
        this.driver = driver;
        this.commonStepsObj = commonStepsObj;
    }

    public void cancelTheTourPackageByAdmin(){
        LOGGER.info("cancelTheTourPackageByAdmin | ManageBookingPage");
        commonStepsObj.scrollAtTheBottomOfThePage();
        commonStepsObj.waitUntilNextElementAppears(cancelElement,4000);
        driver.findElement(cancelElement).click();
    }

    public void confirmTheTourPackageByAdmin(){
        LOGGER.info("confirmTheTourPackageByAdmin | ManageBookingPage");
        commonStepsObj.scrollAtTheBottomOfThePage();
        commonStepsObj.waitUntilNextElementAppears(confirmElement,4000);
        driver.findElement(confirmElement).click();
    }

    public void verifyAdminActionBookingMessageWithExcel(String expectedResult) {
        LOGGER.info("verifyAdminActionBookingMessageWithExcel | ManageBookingPage");
        commonStepsObj.scrollPageToTop();
        commonStepsObj.waitUntilNextElementAppears(bookingConfirmElement,4000);
        String ValidationMessage = driver.findElement(bookingConfirmElement).getText();
        Assert.assertEquals(ValidationMessage,expectedResult);
    }

    public void verifyApplicationActionBookingMessageWithExcel(String expectedResult) throws InterruptedException {
        LOGGER.info("verifyApplicationActionBookingMessageWithExcel | ManageBookingPage");
        commonStepsObj.Wait();
        String ValidationMessage = driver.findElement(ActionElement).getAttribute("value");
        System.out.println(driver.findElement(ActionElement).getAttribute("value"));
        Assert.assertEquals(ValidationMessage,expectedResult);
    }

    public void verifyAdminTourDetailOfSubmittedEnquiryInTheDB(String expectedAction) {
        LOGGER.info("verifyAdminTourDetailOfSubmittedEnquiryInTheDB | ManageBookingPage");
        Map<String, String> result = dbUtil.getDataFromBookingTable();
        if (result != null) {
            LOGGER.info("Action : " + result.get("status"));
            assertEquals(expectedAction, result.get("status"));

        } else {
            LOGGER.info("No DB record found");
        }
    }


}
