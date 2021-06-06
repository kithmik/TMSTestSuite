package com.tms.admin.userprofile;

import com.tms.base.BaseTest;
import com.tms.pages.admin.userprofile.AdminSignInPage;
import com.tms.util.common.CommonSteps;
import com.tms.util.excelutils.ExcelUtil;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static com.tms.util.extentreports.ExtentTestManager.startTest;
import java.lang.reflect.Method;

public class AdminSignInTest extends BaseTest {
    private CommonSteps commonStepsObj;
    private AdminSignInPage adminSignInPageObj;

    @BeforeClass
    public void setup() throws InterruptedException {
        initialization();
        commonStepsObj = new CommonSteps(driver);
        adminSignInPageObj = new AdminSignInPage(driver, commonStepsObj);
        ExcelUtil.setExcelFileSheet("AdminLogin");
    }

    @Test(priority = 1)
    public void verifyThatAdminCanSuccessfullyLoginIntoAdminPanel(Method method) {
        startTest(method.getName(), "Verify that admin can successfully login into admin panel");
        adminSignInPageObj.adminLoginWithExcelData(ExcelUtil.getRowData(1));
        adminSignInPageObj.verifyAdminLoginWithExcel(ExcelUtil.getCellData(1,4));
        adminSignInPageObj.adminLogout();
    }

    @Test(priority = 2)
    public void verifyThatAdminNotAllowedLoginWithWrongUsernameAndCorrectPassword(Method method) {
        startTest(method.getName(), "Verify that admin not allowed login with wrong username and correct password");
        adminSignInPageObj.adminInvalidLoginWithExcelData(ExcelUtil.getRowData(2));
        commonStepsObj.clickOkButtonOfConfirmPromt();
        adminSignInPageObj.verifyInvalidAdminLoginWithExcel(ExcelUtil.getCellData(2,4));
    }

    @Test(priority = 3)
    public void verifyThatUAdminNotAllowedLoginWithEmptyUsernameAndEmptyPassword(Method method)  {
        startTest(method.getName(), "Verify that admin not allowed login with empty username and empty password");
        adminSignInPageObj.adminInvalidLoginWithExcelData(ExcelUtil.getRowData(4));
        adminSignInPageObj.verifyInvalidAdminLoginWithExcel(ExcelUtil.getCellData(4,4));
    }

}