package com.tms.user.userprofile;

import com.tms.base.BaseTest;
import com.tms.pages.user.userprofile.UserSignInAndSignUpPage;
import com.tms.util.common.CommonSteps;
import com.tms.util.excelutils.ExcelUtil;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import static com.tms.util.extentreports.ExtentTestManager.startTest;
import java.lang.reflect.Method;

//@Listeners({ TestListners.class })
public class UserSignInAndSignUpTest extends BaseTest {

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
    public void verifyThatUserCanSuccessfullyLoginIntoAdminPanel(Method method) throws InterruptedException {
        startTest(method.getName(), "verifyThatUserCanSuccessfullyLoginIntoAdminPanel");
        userSignInAndSignUpPageObj.clickSignInButton(ExcelUtil.getCellData(1,2),ExcelUtil.getCellData(1,1));
//        commonStepsObj.saveTestResults(1,5);

    }

    @Test
    public void verifyThatUserNotAllowedLoginWithWrongUsernameAndCorrectPassword(Method method) throws InterruptedException {
        startTest(method.getName(), "verifyThatUserNotAllowedLoginWithWrongUsernameAndCorrectPassword");
        userSignInAndSignUpPageObj.clickSignInButton(ExcelUtil.getCellData(2,2),ExcelUtil.getCellData(1,1));
//        commonStepsObj.saveTestResults(1,5);

    }

    @Test
    public void verifyThatUserNotAllowedLoginWithCorrectUsernameAndWrongPassword(Method method) throws InterruptedException {
        startTest(method.getName(), "verifyThatUserNotAllowedLoginWithCorrectUsernameAndWrongPassword");
        userSignInAndSignUpPageObj.clickSignInButton(ExcelUtil.getCellData(3,2),ExcelUtil.getCellData(1,1));
//        commonStepsObj.saveTestResults(1,5);

    }

    @Test
    public void verifyThatUserNotAllowedLoginWithEmptyUsernameAndEmptyPassword(Method method) throws InterruptedException {
        startTest(method.getName(), "verifyThatUserNotAllowedLoginWithEmptyUsernameAndEmptyPassword");
        userSignInAndSignUpPageObj.clickSignInButton(ExcelUtil.getCellData(4,2),ExcelUtil.getCellData(1,1));
//        commonStepsObj.saveTestResults(1,5);

    }


    @Test
    public void verifyThatNewUserCanSignUp(Method method) throws InterruptedException {
        startTest(method.getName(), "verifyThatNewUserCanSignUp");
        userSignInAndSignUpPageObj.clickSignUp(ExcelUtil.getRowData(8));
        userSignInAndSignUpPageObj.verifyUserSignUpUsingDb(ExcelUtil.getCellData(8,1));

    }

    @Test
    public void verifyThatNewUserCanSignUpWithAlreadyGivenUserDetails(Method method) throws InterruptedException {
        startTest(method.getName(), "verifyThatNewUserCanSignUpWithAlreadyGivenUserDetails");
        userSignInAndSignUpPageObj.clickSignUp(ExcelUtil.getRowData(8));
        userSignInAndSignUpPageObj.signAgain(ExcelUtil.getCellData(8,3),ExcelUtil.getCellData(8,4));
    }

}

