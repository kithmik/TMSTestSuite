package com.tms.admin.booking;

import com.tms.base.BaseTest;
import com.tms.pages.admin.navigation.AdminViewPieceObjectPage;
import com.tms.pages.admin.booking.ManageBookingPage;
import com.tms.pages.admin.userprofile.AdminSignInPage;
import com.tms.pages.user.booking.TourBookingPage;
import com.tms.pages.user.navigation.UserViewPieceObjectPage;
import com.tms.pages.user.userprofile.UserSignInPage;
import com.tms.util.common.CommonSteps;
import com.tms.util.excelutils.ExcelUtil;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.*;
import java.lang.reflect.Method;
import static com.tms.util.extentreports.ExtentTestManager.startTest;

/**
 * @author Kithmi Kalpana
 * Test Class for manage booking by admin
 *
 */


public class ManageBookingTest extends BaseTest {

    private CommonSteps commonStepsObj;
    private ManageBookingPage manageBookingPageObj;
    private TourBookingPage tourBookingPageObj;
    private AdminViewPieceObjectPage adminViewPieceObjectPageObj;
    private UserViewPieceObjectPage userViewPieceObjectPageObj;
    private UserSignInPage userSignInPageObj;
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
        userSignInPageObj = new UserSignInPage(driver, commonStepsObj);
        adminViewPieceObjectPageObj = new AdminViewPieceObjectPage(driver);
        adminSignInPageObj = new AdminSignInPage(driver, commonStepsObj);


        ExcelUtil.setExcelFileSheet("UserLogin");
        //User login into the application
        userSignInPageObj.userLoginWithExcelData(ExcelUtil.getRowData(1));

        ExcelUtil.setExcelFileSheet("TourBooking");
        //User booking the tour package
        userViewPieceObjectPageObj.clickOnTourPackageMenu();
        tourBookingPageObj.clickOnDetailsButton();
        tourBookingPageObj.bookingTheTourPackageWithExcelData(ExcelUtil.getRowData(1));

        //user logout
        userSignInPageObj.userLogout();

        ExcelUtil.setExcelFileSheet("AdminLogin");
        //admin login into the admin panel
        adminSignInPageObj.adminLoginWithExcelData(ExcelUtil.getRowData(1));

        ExcelUtil.setExcelFileSheet("TourBooking");


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

        adminSignInPageObj.adminLogout();
        driver.get(properties.getProperty("baseUrl"));

    }


}
