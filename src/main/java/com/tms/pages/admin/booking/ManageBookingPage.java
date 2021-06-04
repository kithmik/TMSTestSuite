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

public class ManageBookingPage {
    private WebDriver driver;
    private CommonSteps commonStepsObj;
    private static final DbUtil dbUtil = new DbUtil();
    private static final Logger LOGGER = Logger.getLogger(TourBookingPage.class.getName());

    public ManageBookingPage(WebDriver driver, CommonSteps commonStepsObj) {
        this.driver = driver;
        this.commonStepsObj = commonStepsObj;
    }

    private By confirmElement = By.xpath("//table/tbody/tr[last()]/td[last()]/span/a[2]");
    private By cancelElement = By.xpath("//table/tbody/tr[last()]/td[last()]/span/a[1]");
    private By ActionElement = By.xpath("//table/tbody/tr[last()]/td[last()]");
    private By bookingConfirmElement =  By.xpath("//*[@class= 'succWrap']");


    public void cancelTheTourPackageByAdmin(){
        commonStepsObj.scrollAtTheBottomOfThePage();
        commonStepsObj.waitUntilNextElementAppears(cancelElement,4000);
        driver.findElement(cancelElement).click();
    }

    public void confirmTheTourPackageByAdmin(){
        commonStepsObj.scrollAtTheBottomOfThePage();
        commonStepsObj.waitUntilNextElementAppears(confirmElement,4000);
        driver.findElement(confirmElement).click();
    }

    public void verifyAdminActionBookingMessageWithExcel(String expectedResult) throws InterruptedException {
        commonStepsObj.waitUntilNextElementAppears(bookingConfirmElement,4000);
        String ValidationMessage = driver.findElement(bookingConfirmElement).getText();
        Assert.assertEquals(ValidationMessage,expectedResult);
    }

    public void verifyApplicationActionBookingMessageWithExcel(String expectedResult) throws InterruptedException {
        Thread.sleep(3000);
        String ValidationMessage = driver.findElement(ActionElement).getAttribute("value");
        System.out.println(driver.findElement(ActionElement).getAttribute("value"));
        Assert.assertEquals(ValidationMessage,expectedResult);
    }

    public void verifyAdminTourDetailOfSubmittedEnquiryInTheDB(String expectedAction) {
        Map<String, String> result = dbUtil.getDataFromBookingTable();
        if (result != null) {
            LOGGER.info("Action : " + result.get("status"));
            assertEquals(expectedAction, result.get("status"));

        } else {
            LOGGER.info("No db record found for Full Name");
        }
    }


}
