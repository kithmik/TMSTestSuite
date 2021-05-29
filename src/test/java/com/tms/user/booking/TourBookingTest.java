package com.tms.user.booking;

import com.tms.base.BaseTest;
import com.tms.pages.user.booking.TourBookingPage;
import com.tms.util.common.CommonSteps;
import com.tms.util.excelutils.ExcelUtil;
import com.tms.util.listners.TestListners;
import org.openqa.selenium.By;
import org.testng.annotations.*;

@Listeners({ TestListners.class })
public class TourBookingTest extends BaseTest {

    private CommonSteps commonStepsObj;
    private TourBookingPage tourBookingPageObj;

    @BeforeClass
    public void setup() throws InterruptedException {
        initialization();
        commonStepsObj = new CommonSteps(driver);
        tourBookingPageObj = new TourBookingPage(driver, commonStepsObj);

        driver.findElement(By.xpath("//*[contains(text(),'/ Sign In')]")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).sendKeys("anuj@gmail.com");

        driver.findElement(By.xpath("//input[@id='password'][@required='']")).click();
        driver.findElement(By.xpath("//input[@id='password'][@required='']")).sendKeys("Test@123");

        driver.findElement(By.name("signin")).click();

        ExcelUtil.setExcelFileSheet("TourBooking");

    }

//   @BeforeMethod
//    public void login() throws Exception {
//        commonStepsObj = new CommonSteps(driver);
//        tourBookingPageObj = new TourBookingPage(driver, commonStepsObj);
//
//        driver.findElement(By.xpath("//*[contains(text(),'/ Sign In')]")).click();
//        Thread.sleep(3000);
//        driver.findElement(By.id("email")).click();
//        driver.findElement(By.id("email")).sendKeys("anuj@gmail.com");
//
//        driver.findElement(By.xpath("//input[@id='password'][@required='']")).click();
//        driver.findElement(By.xpath("//input[@id='password'][@required='']")).sendKeys("Test@123");
//
//        driver.findElement(By.name("signin")).click();
//
//
//
//   }

//    @BeforeTest
//    public void setupTestData() {
//        //Set Test Data Excel and Sheet
//        System.out.println("************Setup Test Level Data**********");
//        ExcelUtil.setExcelFileSheet("TourBooking");
//    }

    @Test(priority = 1)
    public void verifyThatUserCanSuccessfullyBookTheTourPackageByAddingAllFields() {
        tourBookingPageObj.clickOnDetailsButton();
        tourBookingPageObj.bookingTheTourPackageWithExcelData(ExcelUtil.getRowData(1));
        tourBookingPageObj.verifyBookingMessageWithExcel(ExcelUtil.getCellData(1,4));
        tourBookingPageObj.saveTestResults(1,7);

    }
    @Test(priority = 2)
    public void verifyThatUserCanSuccessfullyBookTheTourPackageByAddingOnlyMandatoryFields() {
        tourBookingPageObj.clickOnTourPackageMenu();
        tourBookingPageObj.clickOnDetailsButton();
        tourBookingPageObj.bookingTheTourPackageWithExcelData(ExcelUtil.getRowData(2));
        tourBookingPageObj.verifyBookingMessageWithExcel(ExcelUtil.getCellData(2,4));
        tourBookingPageObj.saveTestResults(2,7);

    }

    @Test(priority = 4)
    public void whenEnteringToDateVerifyThatUserCantAddADateWhichIsBeforeFromDate() {
        tourBookingPageObj.clickOnTourPackageMenu();
        tourBookingPageObj.clickOnDetailsButton();
        tourBookingPageObj.bookingTheTourPackageWithExcelData(ExcelUtil.getRowData(4));
        tourBookingPageObj.verifyUnsuccessfulBookingMessageWithExcel(ExcelUtil.getCellData(4,4));
        tourBookingPageObj.saveTestResults(4,7);

    }
    @Test(priority = 5)
    public void verifyThatUserCantSelectAPastDateFromNowAsTheFromDate() {
        tourBookingPageObj.clickOnTourPackageMenu();
        tourBookingPageObj.clickOnDetailsButton();
        tourBookingPageObj.bookingTheTourPackageWithExcelData(ExcelUtil.getRowData(5));
        tourBookingPageObj.verifyBookingMessageWithExcel(ExcelUtil.getCellData(5,4));
        tourBookingPageObj.saveTestResults(5,7);

    }
    @Test(priority = 6)
    public void verifyThatUserCantSubmitTheBookingFormByAddingInvalidDataForFromDateAndToDate() {
        tourBookingPageObj.clickOnTourPackageMenu();
        tourBookingPageObj.clickOnDetailsButton();
        tourBookingPageObj.bookingTheTourPackageWithExcelData(ExcelUtil.getRowData(6));
        tourBookingPageObj.verifyUnsuccessfulBookingMessageWithExcel(ExcelUtil.getCellData(6,4));
        tourBookingPageObj.saveTestResults(6,7);

    }
    @Test(priority = 7)
    public void verifyThatUserCantSubmitTheBookFormByAddingOnlyOptionalFields() {
        tourBookingPageObj.clickOnTourPackageMenu();
        tourBookingPageObj.clickOnDetailsButton();
        tourBookingPageObj.bookingTheTourPackageWithExcelData(ExcelUtil.getRowData(3));
        tourBookingPageObj.verifyValidationMessageWithExcel(ExcelUtil.getCellData(3,4));
        tourBookingPageObj.saveTestResults(3,7);

    }

//    @Test()
//    public void verifyThatUserCanCancelTheOnlyBookingWhichHasMoreThan24HoursLeft() {
//        tourBookingPageObj.clickOnDetailsButton();
//        tourBookingPageObj.bookingTheTourPackageWithExcelData(ExcelUtil.getRowData(6));
//        tourBookingPageObj.verifyBookingMessageWithExcel(ExcelUtil.getCellData(6,4));
//        tourBookingPageObj.saveTestResults(6,7);
//
//    }
//    @Test
//    public void verifyThatUserCantCancelTheBookingWhichHasLessThan24HoursLeft() {
//        tourBookingPageObj.clickOnDetailsButton();
//        tourBookingPageObj.bookingTheTourPackageWithExcelData(ExcelUtil.getRowData(6));
//        tourBookingPageObj.verifyBookingMessageWithExcel(ExcelUtil.getCellData(6,4));
//        tourBookingPageObj.saveTestResults(6,7);
//
//    }



}