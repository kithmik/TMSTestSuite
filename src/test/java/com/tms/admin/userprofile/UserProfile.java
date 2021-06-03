package com.tms.admin.userprofile;

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
    public void verifyThatAdminCanSuccessfullyLoginIntoAdminPanel(Method method) throws InterruptedException {
        startTest(method.getName(), "verifyThatAdminCanSuccessfullyLoginIntoAdminPanel");
        userprofileObj.clickSignInButton(ExcelUtil.getCellData(1,2),ExcelUtil.getCellData(1,1));
//        commonStepsObj.saveTestResults(1,5);

    }

    @Test
    public void verifyThatAdminNotAllowedLoginWithWrongUsernameAndCorrectPassword(Method method) throws InterruptedException {
        startTest(method.getName(), "verifyThatAdminNotAllowedLoginWithWrongUsernameAndCorrectPassword");

        userprofileObj.clickSignInButton(ExcelUtil.getCellData(2,2),ExcelUtil.getCellData(1,1));
//        commonStepsObj.saveTestResults(1,5);

    }

    @Test
    public void verifyThatAdminNotAllowedLoginWithCorrectUsernameAndWrongPassword(Method method) throws InterruptedException {
        startTest(method.getName(), "verifyThatAdminNotAllowedLoginWithCorrectUsernameAndWrongPassword");

        userprofileObj.clickSignInButton(ExcelUtil.getCellData(3,2),ExcelUtil.getCellData(1,1));
//        commonStepsObj.saveTestResults(1,5);

    }

    @Test
    public void verifyThatUAdminNotAllowedLoginWithEmptyUsernameAndEmptyPassword(Method method) throws InterruptedException {
        startTest(method.getName(), "verifyThatUAdminNotAllowedLoginWithEmptyUsernameAndEmptyPassword");

        userprofileObj.clickSignInButton(ExcelUtil.getCellData(4,2),ExcelUtil.getCellData(1,1));
//        commonStepsObj.saveTestResults(1,5);

    }



}

