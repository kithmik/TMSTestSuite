package com.tms.admin.userprofile;

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
    public void verifyThatAdminCanSuccessfullyLoginIntoAdminPanel() throws InterruptedException {
        userprofileObj.clickSignInButton(ExcelUtil.getCellData(1,2),ExcelUtil.getCellData(1,1));
//        commonStepsObj.saveTestResults(1,5);

    }

    @Test
    public void verifyThatAdminNotAllowedLoginWithWrongUsernameAndCorrectPassword() throws InterruptedException {
        userprofileObj.clickSignInButton(ExcelUtil.getCellData(2,2),ExcelUtil.getCellData(1,1));
//        commonStepsObj.saveTestResults(1,5);

    }

    @Test
    public void verifyThatAdminNotAllowedLoginWithCorrectUsernameAndWrongPassword() throws InterruptedException {
        userprofileObj.clickSignInButton(ExcelUtil.getCellData(3,2),ExcelUtil.getCellData(1,1));
//        commonStepsObj.saveTestResults(1,5);

    }

    @Test
    public void verifyThatUAdminNotAllowedLoginWithEmptyUsernameAndEmptyPassword() throws InterruptedException {
        userprofileObj.clickSignInButton(ExcelUtil.getCellData(4,2),ExcelUtil.getCellData(1,1));
//        commonStepsObj.saveTestResults(1,5);

    }



}

