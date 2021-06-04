package com.tms.admin.userprofile;

import com.tms.base.BaseTest;
import com.tms.pages.user.userprofile.UserSignInAndSignUpPage;
import com.tms.util.common.CommonSteps;
import com.tms.util.excelutils.ExcelUtil;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.tms.util.extentreports.ExtentTestManager.startTest;
import java.lang.reflect.Method;

//@Listeners({ TestListners.class })
public class AdminSignInTest extends BaseTest {

    private CommonSteps commonStepsObj;
    private UserSignInAndSignUpPage userSignInAndSignUpPageObj;

    @BeforeClass
    public void setup() throws InterruptedException {
        initialization();
        commonStepsObj = new CommonSteps(driver);
        userSignInAndSignUpPageObj = new UserSignInAndSignUpPage(driver, commonStepsObj);

        ExcelUtil.setExcelFileSheet("Login-Admin");

    }




    @Test
    public void verifyThatAdminCanSuccessfullyLoginIntoAdminPanel(Method method) throws InterruptedException {
        startTest(method.getName(), "verifyThatAdminCanSuccessfullyLoginIntoAdminPanel");
        userSignInAndSignUpPageObj.clickAdminLogin(ExcelUtil.getCellData(1,1),ExcelUtil.getCellData(1,2));
//        commonStepsObj.saveTestResults(1,5);

    }

    @Test
    public void verifyThatAdminNotAllowedLoginWithWrongUsernameAndCorrectPassword(Method method) throws InterruptedException {
        startTest(method.getName(), "verifyThatAdminNotAllowedLoginWithWrongUsernameAndCorrectPassword");

        userSignInAndSignUpPageObj.clickAdminLogin(ExcelUtil.getCellData(2,1),ExcelUtil.getCellData(1,2));
//        commonStepsObj.saveTestResults(1,5);

    }

    @Test
    public void verifyThatAdminNotAllowedLoginWithCorrectUsernameAndWrongPassword(Method method) throws InterruptedException {
        startTest(method.getName(), "verifyThatAdminNotAllowedLoginWithCorrectUsernameAndWrongPassword");

        userSignInAndSignUpPageObj.clickAdminLogin(ExcelUtil.getCellData(3,1),ExcelUtil.getCellData(1,2));
//        commonStepsObj.saveTestResults(1,5);

    }

    @Test
    public void verifyThatUAdminNotAllowedLoginWithEmptyUsernameAndEmptyPassword(Method method) throws InterruptedException {
        startTest(method.getName(), "verifyThatUAdminNotAllowedLoginWithEmptyUsernameAndEmptyPassword");

        userSignInAndSignUpPageObj.clickAdminLogin(ExcelUtil.getCellData(4,1),ExcelUtil.getCellData(1,2));
//        commonStepsObj.saveTestResults(1,5);

    }



}

