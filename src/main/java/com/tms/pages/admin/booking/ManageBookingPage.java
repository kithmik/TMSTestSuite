package com.tms.pages.admin.booking;

import com.tms.util.common.CommonSteps;
import com.tms.util.excelutils.ExcelUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ManageBookingPage {
    private WebDriver driver;
    private CommonSteps commonStepsObj;

    public ManageBookingPage(WebDriver driver, CommonSteps commonStepsObj) {
        this.driver = driver;
        this.commonStepsObj = commonStepsObj;
    }

    private By confirmElement = By.xpath("//table/tbody/tr[last()]/td[last()]/span/a[2]");
    private By cancelElement = By.xpath("//table/tbody/tr[last()]/td[last()]/span/a[1]");
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

    public void verifyAdminActionBookingMessageWithExcel(String expectedResult){
        String ValidationMessage = driver.findElement(bookingConfirmElement).getText();
        Assert.assertEquals(ValidationMessage,expectedResult);
    }



}
