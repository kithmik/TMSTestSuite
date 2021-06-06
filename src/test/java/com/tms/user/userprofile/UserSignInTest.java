package com.tms.user.userprofile;

import com.tms.base.BaseTest;
import com.tms.pages.user.userprofile.UserSignInPage;
import com.tms.util.common.CommonSteps;
import com.tms.util.excelutils.ExcelUtil;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.tms.util.extentreports.ExtentTestManager.startTest;

public class UserSignInTest extends BaseTest {
    private CommonSteps commonStepsObj;
    private UserSignInPage userSignInPageObj;

    @BeforeClass
    public void setup() {

        initialization();
        commonStepsObj = new CommonSteps(driver);
        userSignInPageObj = new UserSignInPage(driver, commonStepsObj);
        ExcelUtil.setExcelFileSheet("UserLogin");
    }


    @Test(priority = 1)
    public void verifyThatUserCanSuccessfullyLogin(Method method) throws InterruptedException {
        startTest(method.getName(), "Verify that user can successfully login");
        userSignInPageObj.userLoginWithExcelData(ExcelUtil.getRowData(1));
        userSignInPageObj.verifyUserLoginWithExcel(ExcelUtil.getCellData(1,4));
        userSignInPageObj.userLogout();
    }


    @Test(priority = 2)
    public void verifyThatUserNotAllowedLoginWithWrongUsernameAndCorrectPassword(Method method) throws InterruptedException {
        startTest(method.getName(), "Verify that user not allowed login with wrong username and correct password");
        userSignInPageObj.userInvalidLoginWithExcelData(ExcelUtil.getRowData(2));
        commonStepsObj.clickOkButtonOfConfirmPromt();
        userSignInPageObj.verifyInvalidUserLoginWithExcel(ExcelUtil.getCellData(2,4));
    }

    @Test(priority = 3)
    public void verifyThatUserNotAllowedLoginWithCorrectUsernameAndWrongPassword(Method method) throws InterruptedException {
        startTest(method.getName(), "Verify that user not allowed login with correct username and wrong password");
        userSignInPageObj.userInvalidLoginWithExcelData(ExcelUtil.getRowData(3));
        commonStepsObj.clickOkButtonOfConfirmPromt();
        userSignInPageObj.verifyInvalidUserLoginWithExcel(ExcelUtil.getCellData(3,4));
    }

    @Test(priority = 4)
    public void verifyThatUUserNotAllowedLoginWithEmptyUsernameAndEmptyPassword(Method method) throws InterruptedException {
        startTest(method.getName(), "Verify that user not allowed login with empty username and empty password");
        userSignInPageObj.userInvalidLoginWithExcelData(ExcelUtil.getRowData(4));
        commonStepsObj.clickOkButtonOfConfirmPromt();
        userSignInPageObj.verifyInvalidUserLoginWithExcel(ExcelUtil.getCellData(4,4));
    }

}
