package com.tms.user.booking;

import com.tms.base.BaseTest;
import com.tms.pages.user.booking.ManageBookingPage;
import com.tms.util.common.CommonSteps;
import com.tms.util.excelutils.ExcelUtil;
import com.tms.util.listners.TestListners;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ TestListners.class })
public class ManageBookingTest extends BaseTest {

    private CommonSteps commonStepsObj;
    private ManageBookingPage manageBookingPageObj;

    @BeforeClass
    public void setup() throws InterruptedException {
        initialization();
        commonStepsObj = new CommonSteps(driver);
        manageBookingPageObj = new ManageBookingPage(driver, commonStepsObj);

        driver.findElement(By.xpath("//*[contains(text(),'/ Sign In')]")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).sendKeys("anuj@gmail.com");

        driver.findElement(By.xpath("//input[@id='password'][@required='']")).click();
        driver.findElement(By.xpath("//input[@id='password'][@required='']")).sendKeys("Test@123");

        driver.findElement(By.name("signin")).click();

        ExcelUtil.setExcelFileSheet("TourBooking");

    }

    @Test
    private void sss(){
        manageBookingPageObj.clickOnMyTourHistoryMenu();

    }
}
