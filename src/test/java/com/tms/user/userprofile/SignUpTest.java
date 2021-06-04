package com.tms.user.userprofile;

//public class SignUpTest {
//}


import com.tms.base.BaseTest;
import com.tms.pages.user.navigation.UserViewPieceObjectPage;
import com.tms.pages.user.userprofile.SignUpPage;
import com.tms.pages.user.userprofile.UserProfilePage;
import com.tms.util.common.CommonSteps;
import com.tms.util.excelutils.ExcelUtil;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.tms.util.excelutils.ExcelUtil.setExcelFileSheet;
import static com.tms.util.extentreports.ExtentTestManager.startTest;

public class SignUpTest extends BaseTest {
    private SignUpPage signUpPage;
    private UserViewPieceObjectPage userViewPieceObjectPage;
    private CommonSteps commonStepsObj;

    @BeforeClass
    public void setUp() throws Exception {
        initialization();
        signUpPage = new SignUpPage(driver);
        userViewPieceObjectPage = new UserViewPieceObjectPage(driver);
        setExcelFileSheet("UserSignUp");
    }

    @Test(priority =1)
    public void verifyUserSignUp(Method method) throws InterruptedException {
        startTest(method.getName(), "Verify that User is able to Sign Up Successfully By Entering  Valid Data to all the fields ");
        userViewPieceObjectPage.clickOnSignUp();
        signUpPage.enterUserDetails(ExcelUtil.getRowData(1));
        signUpPage.clickOnSignUpBtn();
        signUpPage.verifyUserSignUpUsingDb(ExcelUtil.getCellData(1,1));
    }
}