package com.tms.user.booking;

import com.tms.base.BaseTest;
import com.tms.pages.user.booking.TourBookingPage;
import com.tms.pages.user.navigation.UserViewPieceObjectPage;
import com.tms.pages.user.userprofile.SignInPage;
import com.tms.util.common.CommonSteps;
import com.tms.util.excelutils.ExcelUtil;
import org.testng.annotations.*;
import java.lang.reflect.Method;

/**
 * @author Kithmi Kalpana
 * Test Class for book tour package by user
 */

import static com.tms.util.extentreports.ExtentTestManager.startTest;

public class TourBookingTest extends BaseTest {

    private CommonSteps commonStepsObj;
    private TourBookingPage tourBookingPageObj;
    private UserViewPieceObjectPage userViewPieceObjectPageObj;
    private SignInPage signInPageObj;

    @BeforeClass
    public void setup() throws InterruptedException {
        initialization();
        commonStepsObj = new CommonSteps(driver);
        tourBookingPageObj = new TourBookingPage(driver, commonStepsObj);
        userViewPieceObjectPageObj = new UserViewPieceObjectPage(driver);

        signInPageObj = new SignInPage(driver, commonStepsObj);

        ExcelUtil.setExcelFileSheet("UserLogin");
        //User login into the application
        signInPageObj.userLoginWithExcelData(ExcelUtil.getRowData(1));

        ExcelUtil.setExcelFileSheet("TourBooking");

    }

    @Test(priority = 1)
    public void verifyThatUserCanSuccessfullyBookTheTourPackageByAddingAllFields(Method method) {
        startTest(method.getName(), "Verify that user can successfully book the tour package by adding all fields");
        userViewPieceObjectPageObj.clickOnTourPackageMenu();
        tourBookingPageObj.clickOnDetailsButton();
        tourBookingPageObj.bookingTheTourPackageWithExcelData(ExcelUtil.getRowData(1));
        tourBookingPageObj.verifyBookingMessageWithExcel(ExcelUtil.getCellData(1,4));
        tourBookingPageObj.verifyTourDetailOfSubmittedEnquiryInTheDB(ExcelUtil.getCellData(1,1),ExcelUtil.getCellData(1,2),ExcelUtil.getCellData(1,3));

    }
    @Test(priority = 2)
    public void verifyThatUserCanSuccessfullyBookTheTourPackageByAddingOnlyMandatoryFields(Method method) {
        startTest(method.getName(), "Verify that user can successfully book the tour package by adding only mandatory fields");
        userViewPieceObjectPageObj.clickOnTourPackageMenu();
        tourBookingPageObj.clickOnDetailsButton();
        tourBookingPageObj.bookingTheTourPackageWithExcelData(ExcelUtil.getRowData(2));
        tourBookingPageObj.verifyBookingMessageWithExcel(ExcelUtil.getCellData(2,4));
        tourBookingPageObj.verifyTourDetailOfSubmittedEnquiryInTheDB(ExcelUtil.getCellData(2,1),ExcelUtil.getCellData(2,2),ExcelUtil.getCellData(2,3));

    }

    @Test(priority = 3)
    public void whenEnteringToDateVerifyThatUserCantAddADateWhichIsBeforeFromDate(Method method) {
        startTest(method.getName(), "When entering To date, verify that user can't add a date which is before From date");
        userViewPieceObjectPageObj.clickOnTourPackageMenu();
        tourBookingPageObj.clickOnDetailsButton();
        tourBookingPageObj.bookingTheTourPackageWithExcelData(ExcelUtil.getRowData(4));
        tourBookingPageObj.verifyErrorValidationMessageWithExcel(ExcelUtil.getCellData(4,4));

    }
    @Test(priority = 4)
    public void verifyThatUserCantSelectAPastDateFromNowAsTheFromDate(Method method) {
        startTest(method.getName(), "Verify that user can't select a past date from now as the From date");
        userViewPieceObjectPageObj.clickOnTourPackageMenu();
        tourBookingPageObj.clickOnDetailsButton();
        tourBookingPageObj.bookingTheTourPackageWithExcelData(ExcelUtil.getRowData(5));
        tourBookingPageObj.verifyErrorValidationMessageWithExcel(ExcelUtil.getCellData(5,4));

    }
    @Test(priority = 5)
    public void verifyThatUserCantSubmitTheBookingFormByAddingInvalidDataForFromDateAndToDate(Method method) {
        startTest(method.getName(), "Verify that user can't submit the booking form by adding invalid data for From date and To date");
        userViewPieceObjectPageObj.clickOnTourPackageMenu();
        tourBookingPageObj.clickOnDetailsButton();
        tourBookingPageObj.bookingTheTourPackageWithExcelData(ExcelUtil.getRowData(6));
        tourBookingPageObj.verifyBookingMessageWithExcel(ExcelUtil.getCellData(6,4));

    }
    @Test(priority = 6)
    public void verifyThatUserCantSubmitTheBookFormByAddingOnlyOptionalFields(Method method) {
        startTest(method.getName(), "Verify that user cant submit the book form by adding only optional fields");
        userViewPieceObjectPageObj.clickOnTourPackageMenu();
        tourBookingPageObj.clickOnDetailsButton();
        tourBookingPageObj.bookingTheTourPackageWithExcelData(ExcelUtil.getRowData(3));
        tourBookingPageObj.verifyValidationMessageWithExcel(ExcelUtil.getCellData(3,4));

    }

//    @AfterClass
//    public void logoutUser() {
//        commonStepsObj.scrollPageToTop();
//        userSignInPageObj.userLogout();
//    }
//
}