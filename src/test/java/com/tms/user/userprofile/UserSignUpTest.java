package com.tms.user.userprofile;

import com.tms.base.BaseTest;
import com.tms.pages.user.userprofile.UserSignInAndSignUpPage;
import com.tms.util.common.CommonSteps;
import com.tms.util.excelutils.ExcelUtil;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.tms.base.BaseTest.initialization;
import static com.tms.util.extentreports.ExtentTestManager.startTest;

public class UserSignUpTest extends BaseTest {
    private CommonSteps commonStepsObj;
    private UserSignInAndSignUpPage userSignInAndSignUpPageObj;

    @BeforeClass
    public void setup() throws InterruptedException {
        initialization();
        commonStepsObj = new CommonSteps(driver);
        userSignInAndSignUpPageObj = new UserSignInAndSignUpPage(driver, commonStepsObj);

        ExcelUtil.setExcelFileSheet("Login");

    }

    @Test
    public void verifyThatNewUserCanSignUp(Method method) throws InterruptedException {
        startTest(method.getName(), "verifyThatNewUserCanSignUp");
        userSignInAndSignUpPageObj.clickSignUp(ExcelUtil.getRowData(8));
//        userSignInAndSignUpPageObj.verifyUserSignUpUsingDb(ExcelUtil.getCellData(8,1));
        userSignInAndSignUpPageObj.verifyUserSignUpUsingDb(ExcelUtil.getCellData(8,1));

    }

    @Test
    public void verifyThatNewUserCanSignUpWithAlreadyGivenUserDetails(Method method) throws InterruptedException {
        startTest(method.getName(), "verifyThatNewUserCanSignUpWithAlreadyGivenUserDetails");
        userSignInAndSignUpPageObj.clickSignUp(ExcelUtil.getRowData(8));
        userSignInAndSignUpPageObj.signAgain(ExcelUtil.getCellData(8,3),ExcelUtil.getCellData(8,4));
    }
}
