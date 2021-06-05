package com.tms.user.userprofile;

//public class SignInTest {
//}

import com.tms.base.BaseTest;

import com.tms.pages.user.userprofile.SignInPage;
import com.tms.util.common.CommonSteps;
import com.tms.util.excelutils.ExcelUtil;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static com.tms.util.extentreports.ExtentTestManager.startTest;
import java.lang.reflect.Method;

public class SignInTest extends BaseTest {

    private CommonSteps commonStepsObj;
    private SignInPage userSignInPageObj;

    @BeforeClass
    public void setup() throws InterruptedException {

        initialization();
        commonStepsObj = new CommonSteps(driver);
        userSignInPageObj = new SignInPage(driver, commonStepsObj);
        ExcelUtil.setExcelFileSheet("UserLogin");
    }


    @Test(priority = 1)
    public void verifyThatUserCanSuccessfullyLogin(Method method) throws InterruptedException {
        startTest(method.getName(), "Verify that user can successfully login");
        userSignInPageObj.userLoginWithExcelData(ExcelUtil.getRowData(1));
        userSignInPageObj.verifyUserLoginWithExcel(ExcelUtil.getCellData(1,4));
//        userSignInPageObj.userLogout();
    }


    @Test(priority = 2)
    public void verifyThatUserNotAllowedLoginWithWrongUsernameAndCorrectPassword(Method method) throws InterruptedException {
        startTest(method.getName(), "Verify that user not allowed login with wrong username and correct password");
        userSignInPageObj.userInvalidLoginWithExcelData(ExcelUtil.getRowData(2));
        commonStepsObj.clickOkButtonOfConfirmPromt();
        userSignInPageObj.verifyUserLoginWithExcel(ExcelUtil.getCellData(2,4));
    }

    @Test(priority = 3)
    public void verifyThatUserNotAllowedLoginWithCorrectUsernameAndWrongPassword(Method method) throws InterruptedException {
        startTest(method.getName(), "Verify that user not allowed login with correct username and wrong password");
        userSignInPageObj.userInvalidLoginWithExcelData(ExcelUtil.getRowData(3));
        commonStepsObj.clickOkButtonOfConfirmPromt();
        userSignInPageObj.verifyUserLoginWithExcel(ExcelUtil.getCellData(3,4));
    }

    @Test(priority = 4)
    public void verifyThatUUserNotAllowedLoginWithEmptyUsernameAndEmptyPassword(Method method) throws InterruptedException {
        startTest(method.getName(), "Verify that user not allowed login with empty username and empty password");
        userSignInPageObj.userInvalidLoginWithExcelData(ExcelUtil.getRowData(4));
        userSignInPageObj.verifyUserLoginWithExcel(ExcelUtil.getCellData(4,4));
    }


}
