package com.tms.admin.booking;

import com.tms.base.BaseTest;
import com.tms.pages.admin.navigation.AdminViewPieceObjectPage;
import com.tms.pages.admin.booking.ManageBookingPage;
import com.tms.pages.admin.userprofile.AdminSignInPage;
import com.tms.pages.user.booking.TourBookingPage;
import com.tms.pages.user.navigation.UserViewPieceObjectPage;
import com.tms.pages.user.userprofile.UserSignInAndSignUpPage;
import com.tms.admin.userprofile.AdminSignInTest;
import com.tms.util.common.CommonSteps;
import com.tms.util.excelutils.ExcelUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.*;

import java.lang.reflect.Method;

import static com.tms.util.extentreports.ExtentTestManager.startTest;


public class ManageBookingTest extends BaseTest {

    private CommonSteps commonStepsObj;
    private ManageBookingPage manageBookingPageObj;
    private TourBookingPage tourBookingPageObj;
    private AdminViewPieceObjectPage adminViewPieceObjectPageObj;
    private UserViewPieceObjectPage userViewPieceObjectPageObj;
    private UserSignInAndSignUpPage userSignInAndSignUpPageObj;
    private AdminSignInPage adminSignInPageObj;


    @BeforeMethod
    public void setup() throws InterruptedException {

        if(!isBrowserOpen) {
            initialization();
        }

        commonStepsObj = new CommonSteps(driver);
        manageBookingPageObj = new ManageBookingPage(driver, commonStepsObj);
        tourBookingPageObj = new TourBookingPage(driver, commonStepsObj);
        userViewPieceObjectPageObj = new UserViewPieceObjectPage(driver);
        userSignInAndSignUpPageObj = new UserSignInAndSignUpPage(driver, commonStepsObj);
        adminViewPieceObjectPageObj = new AdminViewPieceObjectPage(driver);
        adminSignInPageObj = new AdminSignInPage(driver, commonStepsObj);


        ExcelUtil.setExcelFileSheet("Login");
        //User login
        userSignInAndSignUpPageObj.clickSignInButton(ExcelUtil.getCellData(1,1),ExcelUtil.getCellData(1,2));

        ExcelUtil.setExcelFileSheet("TourBooking");

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
    public void verifyThatAdminCanConfirmTheSuccessfullyAddedBooking(Method method) throws InterruptedException {
        startTest(method.getName(), "Verify that admin can confirm the successfully added booking");
        adminViewPieceObjectPageObj.clickOnManageBooking();
        manageBookingPageObj.confirmTheTourPackageByAdmin();
        commonStepsObj.clickOkButtonOfConfirmPromt();
        manageBookingPageObj.verifyAdminActionBookingMessageWithExcel(ExcelUtil.getCellData(8,4));
        manageBookingPageObj.verifyAdminTourDetailOfSubmittedEnquiryInTheDB(ExcelUtil.getCellData(8,6));
        manageBookingPageObj.verifyApplicationActionBookingMessageWithExcel(ExcelUtil.getCellData(8,7));

    }



    @Test(priority = 2)
    public void verifyThatAdminCanCancelTheSuccessfullyAddedBooking(Method method) throws InterruptedException {
        startTest(method.getName(), "Verify that admin can cancel the successfully added booking");
        adminViewPieceObjectPageObj.clickOnManageBooking();
        manageBookingPageObj.cancelTheTourPackageByAdmin();
        commonStepsObj.clickOkButtonOfConfirmPromt();
        manageBookingPageObj.verifyAdminActionBookingMessageWithExcel(ExcelUtil.getCellData(9,4));
        manageBookingPageObj.verifyApplicationActionBookingMessageWithExcel(ExcelUtil.getCellData(9,7));
        manageBookingPageObj.verifyAdminTourDetailOfSubmittedEnquiryInTheDB(ExcelUtil.getCellData(9,6));

    }


    @AfterMethod
    public void logoutAdmin() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,-1000)");
        Thread.sleep(2000);

    //    commonStepsObj.waitUntilElementPresence(By.className("dropdown-toggle"),2000);
        driver.findElement(By.className("dropdown-toggle")).click();
        Thread.sleep(2000);

       // commonStepsObj.waitUntilElementPresence(By.xpath("//*[@href='logout.php']"),4000);
        driver.findElement(By.xpath("//*[@href='logout.php']")).click();
        driver.get(properties.getProperty("baseUrl"));

    }


}
