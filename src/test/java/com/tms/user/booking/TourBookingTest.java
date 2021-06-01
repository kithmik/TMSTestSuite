package com.tms.user.booking;

import com.tms.base.BaseTest;
import com.tms.pages.admin.navigation.AdminViewPieceObjectPage;
import com.tms.pages.user.booking.TourBookingPage;
import com.tms.pages.user.navigation.UserViewPieceObjectPage;
import com.tms.util.common.CommonSteps;
import com.tms.util.excelutils.ExcelUtil;
import org.openqa.selenium.By;
import org.testng.annotations.*;

public class TourBookingTest extends BaseTest {

    private CommonSteps commonStepsObj;
    private TourBookingPage tourBookingPageObj;
    private UserViewPieceObjectPage userViewPieceObjectPageObj;

    @BeforeClass
    public void setup() throws InterruptedException {
        initialization();
        commonStepsObj = new CommonSteps(driver);
        tourBookingPageObj = new TourBookingPage(driver, commonStepsObj);
        userViewPieceObjectPageObj = new UserViewPieceObjectPage(driver);

        driver.findElement(By.xpath("//*[contains(text(),'/ Sign In')]")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).sendKeys("anuj@gmail.com");
        driver.findElement(By.xpath("//input[@id='password'][@required='']")).click();
        driver.findElement(By.xpath("//input[@id='password'][@required='']")).sendKeys("Test@123");
        driver.findElement(By.name("signin")).click();

        ExcelUtil.setExcelFileSheet("TourBooking");

    }

    @Test(priority = 1)
    public void verifyThatUserCanSuccessfullyBookTheTourPackageByAddingAllFields() {
        userViewPieceObjectPageObj.clickOnTourPackageMenu();
        tourBookingPageObj.clickOnDetailsButton();
        tourBookingPageObj.bookingTheTourPackageWithExcelData(ExcelUtil.getRowData(1));
        tourBookingPageObj.verifyBookingMessageWithExcel(ExcelUtil.getCellData(1,4));
        tourBookingPageObj.verifyTourDetailOfSubmittedEnquiryInTheDB(ExcelUtil.getCellData(1,1),ExcelUtil.getCellData(1,2),ExcelUtil.getCellData(1,3));

    }
    @Test(priority = 2)
    public void verifyThatUserCanSuccessfullyBookTheTourPackageByAddingOnlyMandatoryFields() throws InterruptedException {
        userViewPieceObjectPageObj.clickOnTourPackageMenu();
        tourBookingPageObj.clickOnDetailsButton();
        tourBookingPageObj.bookingTheTourPackageWithExcelData(ExcelUtil.getRowData(2));
        tourBookingPageObj.verifyBookingMessageWithExcel(ExcelUtil.getCellData(2,4));
        tourBookingPageObj.verifyTourDetailOfSubmittedEnquiryInTheDB(ExcelUtil.getCellData(2,1),ExcelUtil.getCellData(2,2),ExcelUtil.getCellData(2,3));

    }

    @Test(priority = 3)
    public void whenEnteringToDateVerifyThatUserCantAddADateWhichIsBeforeFromDate() throws InterruptedException {
        tourBookingPageObj.clickOnTourPackageMenu();
        tourBookingPageObj.clickOnDetailsButton();
        tourBookingPageObj.bookingTheTourPackageWithExcelData(ExcelUtil.getRowData(4));
        tourBookingPageObj.verifyErrorValidationMessageWithExcel(ExcelUtil.getCellData(4,4));

    }
    @Test(priority = 4)
    public void verifyThatUserCantSelectAPastDateFromNowAsTheFromDate() throws InterruptedException {
        tourBookingPageObj.clickOnTourPackageMenu();
        tourBookingPageObj.clickOnDetailsButton();
        tourBookingPageObj.bookingTheTourPackageWithExcelData(ExcelUtil.getRowData(5));
        tourBookingPageObj.verifyErrorValidationMessageWithExcel(ExcelUtil.getCellData(5,4));

    }
    @Test(priority = 5)
    public void verifyThatUserCantSubmitTheBookingFormByAddingInvalidDataForFromDateAndToDate() throws InterruptedException {
        tourBookingPageObj.clickOnTourPackageMenu();
        tourBookingPageObj.clickOnDetailsButton();
        tourBookingPageObj.bookingTheTourPackageWithExcelData(ExcelUtil.getRowData(6));
        tourBookingPageObj.verifyErrorValidationMessageWithExcel(ExcelUtil.getCellData(6,4));

    }
    @Test(priority = 6)
    public void verifyThatUserCantSubmitTheBookFormByAddingOnlyOptionalFields() throws InterruptedException {
        tourBookingPageObj.clickOnTourPackageMenu();
        tourBookingPageObj.clickOnDetailsButton();
        tourBookingPageObj.bookingTheTourPackageWithExcelData(ExcelUtil.getRowData(3));
        tourBookingPageObj.verifyValidationMessageWithExcel(ExcelUtil.getCellData(3,4));

    }


}