package com.tms.user.userprofile;

import com.tms.base.BaseTest;
import com.tms.pages.user.userprofile.userprofile;
import com.tms.util.common.CommonSteps;
import com.tms.util.excelutils.ExcelUtil;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
    public void verifyThatUserCanSuccessfullyLoginIntoAdminPanel() throws InterruptedException {
        userprofileObj.clickSignInButton(ExcelUtil.getCellData(1,2),ExcelUtil.getCellData(1,1));
//        commonStepsObj.saveTestResults(1,5);

    }

    @Test
    public void verifyThatUserNotAllowedLoginWithWrongUsernameAndCorrectPassword() throws InterruptedException {
        userprofileObj.clickSignInButton(ExcelUtil.getCellData(2,2),ExcelUtil.getCellData(1,1));
//        commonStepsObj.saveTestResults(1,5);

    }

    @Test
    public void verifyThatUserNotAllowedLoginWithCorrectUsernameAndWrongPassword() throws InterruptedException {
        userprofileObj.clickSignInButton(ExcelUtil.getCellData(3,2),ExcelUtil.getCellData(1,1));
//        commonStepsObj.saveTestResults(1,5);

    }

    @Test
    public void verifyThatUserNotAllowedLoginWithEmptyUsernameAndEmptyPassword() throws InterruptedException {
        userprofileObj.clickSignInButton(ExcelUtil.getCellData(4,2),ExcelUtil.getCellData(1,1));
//        commonStepsObj.saveTestResults(1,5);

    }


    @Test
    public void verifyThatNewUserCanSignUp() throws InterruptedException {
        userprofileObj.clickSignUp(ExcelUtil.getRowData(8));

    }

    @Test
    public void verifyThatNewUserCanSignUpWithAlreadyGivenUserDetails() throws InterruptedException {
        userprofileObj.clickSignUp(ExcelUtil.getRowData(8));
        userprofileObj.signAgain(ExcelUtil.getCellData(8,3),ExcelUtil.getCellData(8,4));
    }

}

