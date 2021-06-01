package com.tms.admin.booking;

import com.tms.base.BaseTest;
import com.tms.pages.admin.navigation.AdminViewPieceObjectPage;
import com.tms.pages.admin.booking.ManageBookingPage;
import com.tms.pages.user.booking.TourBookingPage;
import com.tms.pages.user.navigation.UserViewPieceObjectPage;
import com.tms.util.common.CommonSteps;
import com.tms.util.excelutils.ExcelUtil;
import org.openqa.selenium.By;
import org.testng.annotations.*;


public class ManageBookingTest extends BaseTest {

    private CommonSteps commonStepsObj;
    private ManageBookingPage manageBookingPageObj;
    private TourBookingPage tourBookingPageObj;

    private AdminViewPieceObjectPage adminViewPieceObjectPageObj;
    private UserViewPieceObjectPage userViewPieceObjectPageObj;

    @BeforeMethod
    public void setup() throws InterruptedException {

        if(!isBrowserOpen) {
            initialization();
        }

        commonStepsObj = new CommonSteps(driver);
        manageBookingPageObj = new ManageBookingPage(driver, commonStepsObj);
        tourBookingPageObj = new TourBookingPage(driver, commonStepsObj);
        userViewPieceObjectPageObj = new UserViewPieceObjectPage(driver);

        adminViewPieceObjectPageObj = new AdminViewPieceObjectPage(driver);
        ExcelUtil.setExcelFileSheet("TourBooking");

        //user login
        driver.findElement(By.xpath("//*[contains(text(),'/ Sign In')]")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).sendKeys("anuj@gmail.com");
        driver.findElement(By.xpath("//input[@id='password'][@required='']")).click();
        driver.findElement(By.xpath("//input[@id='password'][@required='']")).sendKeys("Test@123");
        driver.findElement(By.name("signin")).click();

        //booking
        userViewPieceObjectPageObj.clickOnTourPackageMenu();
        tourBookingPageObj.clickOnDetailsButton();
        tourBookingPageObj.bookingTheTourPackageWithExcelData(ExcelUtil.getRowData(1));

        //user logout
        driver.findElement(By.xpath("//*[contains(text(),'/ Logout')]")).click();

        //admin login
        driver.findElement(By.xpath("//*[contains(text(),'Admin Login')]")).click();
        Thread.sleep(3000);
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).sendKeys("Test@123");
        driver.findElement(By.name("login")).click();

    }

    @Test(priority = 1)
    public void verifyThatAdminCanConfirmTheSuccessfullyAddedBooking() throws InterruptedException {
        adminViewPieceObjectPageObj.clickOnManageBooking();
        manageBookingPageObj.confirmTheTourPackageByAdmin();
        commonStepsObj.clickOkButtonOfConfirmPromt();
        manageBookingPageObj.verifyAdminActionBookingMessageWithExcel(ExcelUtil.getCellData(8,4));
        manageBookingPageObj.verifyAdminTourDetailOfSubmittedEnquiryInTheDB(ExcelUtil.getCellData(8,6));
        manageBookingPageObj.verifyApplicationActionBookingMessageWithExcel(ExcelUtil.getCellData(8,7));

    }



    @Test(priority = 2)
    public void verifyThatAdminCanCancelTheSuccessfullyAddedBooking() throws InterruptedException {
        adminViewPieceObjectPageObj.clickOnManageBooking();
        manageBookingPageObj.cancelTheTourPackageByAdmin();
        commonStepsObj.clickOkButtonOfConfirmPromt();
        manageBookingPageObj.verifyAdminActionBookingMessageWithExcel(ExcelUtil.getCellData(9,4));
        manageBookingPageObj.verifyApplicationActionBookingMessageWithExcel(ExcelUtil.getCellData(9,7));
        manageBookingPageObj.verifyAdminTourDetailOfSubmittedEnquiryInTheDB(ExcelUtil.getCellData(9,6));

    }


    @AfterMethod
    public void logoutAdmin(){
        driver.findElement(By.className("dropdown-toggle")).click();
        driver.findElement(By.xpath("//*[@href='logout.php']")).click();
        driver.get(properties.getProperty("baseUrl"));

    }


}
