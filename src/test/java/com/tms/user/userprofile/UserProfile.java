package com.tms.user.userprofile;

import com.tms.base.BaseTest;
import com.tms.pages.user.userprofile.userprofile;
import com.tms.util.common.CommonSteps;
import com.tms.util.excelutils.ExcelUtil;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import static com.tms.util.extentreports.ExtentTestManager.startTest;
import java.lang.reflect.Method;

//@Listeners({ TestListners.class })
public class UserProfile extends BaseTest {

    private CommonSteps commonStepsObj;
    private userprofile userprofileObj;

    @BeforeClass
    public void setup() throws InterruptedException {
        initialization();
        commonStepsObj = new CommonSteps(driver);
        userprofileObj = new userprofile(driver, commonStepsObj);

        ExcelUtil.setExcelFileSheet("Login");

    }




    @Test
    public void verifyThatUserCanSuccessfullyLoginIntoAdminPanel(Method method) throws InterruptedException {
        startTest(method.getName(), "verifyThatUserCanSuccessfullyLoginIntoAdminPanel");
        userprofileObj.clickSignInButton(ExcelUtil.getCellData(1,2),ExcelUtil.getCellData(1,1));
//        commonStepsObj.saveTestResults(1,5);

    }

    @Test
    public void verifyThatUserNotAllowedLoginWithWrongUsernameAndCorrectPassword(Method method) throws InterruptedException {
        startTest(method.getName(), "verifyThatUserNotAllowedLoginWithWrongUsernameAndCorrectPassword");
        userprofileObj.clickSignInButton(ExcelUtil.getCellData(2,2),ExcelUtil.getCellData(1,1));
//        commonStepsObj.saveTestResults(1,5);

    }

    @Test
    public void verifyThatUserNotAllowedLoginWithCorrectUsernameAndWrongPassword(Method method) throws InterruptedException {
        startTest(method.getName(), "verifyThatUserNotAllowedLoginWithCorrectUsernameAndWrongPassword");
        userprofileObj.clickSignInButton(ExcelUtil.getCellData(3,2),ExcelUtil.getCellData(1,1));
//        commonStepsObj.saveTestResults(1,5);

    }

    @Test
    public void verifyThatUserNotAllowedLoginWithEmptyUsernameAndEmptyPassword(Method method) throws InterruptedException {
        startTest(method.getName(), "verifyThatUserNotAllowedLoginWithEmptyUsernameAndEmptyPassword");
        userprofileObj.clickSignInButton(ExcelUtil.getCellData(4,2),ExcelUtil.getCellData(1,1));
//        commonStepsObj.saveTestResults(1,5);

    }


    @Test
    public void verifyThatNewUserCanSignUp(Method method) throws InterruptedException {
        startTest(method.getName(), "verifyThatNewUserCanSignUp");
        userprofileObj.clickSignUp(ExcelUtil.getRowData(8));
        userprofileObj.verifyUserSignUpUsingDb(ExcelUtil.getCellData(8,1));

    }

    @Test
    public void verifyThatNewUserCanSignUpWithAlreadyGivenUserDetails(Method method) throws InterruptedException {
        startTest(method.getName(), "verifyThatNewUserCanSignUpWithAlreadyGivenUserDetails");
        userprofileObj.clickSignUp(ExcelUtil.getRowData(8));
        userprofileObj.signAgain(ExcelUtil.getCellData(8,3),ExcelUtil.getCellData(8,4));
    }

}

