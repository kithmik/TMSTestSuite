package com.tms.user.userprofile;

import com.tms.admin.packages.PackageCreateAndUpdateTest;
import com.tms.base.BaseTest;
import com.tms.pages.user.navigation.UserViewPieceObjectPage;
import com.tms.pages.user.userprofile.UserProfilePage;
import com.tms.util.common.CommonSteps;
import com.tms.util.excelutils.ExcelUtil;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.logging.Logger;
import static com.tms.util.excelutils.ExcelUtil.setExcelFileSheet;
import static com.tms.util.extentreports.ExtentTestManager.startTest;

/**
 * @Author Nishani Mendis
 * Test User Profile Update with Valid and Invalid Inputs.
 *  1.verifyUserProfileUpdate
 *  2.verifyUserProfileUpdateWithInvalidDataTypeForMobilNumber
 */

public class UserProfileTest extends BaseTest {
    private UserViewPieceObjectPage userViewPieceObjectPage;
    private UserProfilePage userProfilePage;
    private CommonSteps commonStepsObj;
    private static final Logger LOGGER = Logger.getLogger(PackageCreateAndUpdateTest.class.getName());

    @BeforeClass
    public void setUp() throws Exception {
        initialization();
        userViewPieceObjectPage = new UserViewPieceObjectPage(driver,commonStepsObj);
        userProfilePage = new UserProfilePage(driver);
        driver.findElement(By.xpath("//*[contains(text(),'/ Sign In')]")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).sendKeys("anuj@gmail.com");
        driver.findElement(By.xpath("//input[@id=\"password\"][@required=\"\"]")).click();
        driver.findElement(By.xpath("//input[@id=\"password\"][@required=\"\"]")).sendKeys("Test@123");
        driver.findElement(By.name("signin")).click();
        setExcelFileSheet("UserProfile");
    }

    @Test(priority =1)
    public void verifyUserProfileUpdate(Method method) {
        startTest(method.getName(), "Verify that User Profile is able to update correctly and save updated data to the database ");
        userViewPieceObjectPage.clickOnMyProfile();
        userProfilePage.updateUserName(ExcelUtil.getRowData(1));
        userProfilePage.updateMobileNumber(ExcelUtil.getRowData(1));
        userProfilePage.clickOnUpdateBtn();
        userProfilePage.verifyUserProfileUpdateSuccessMsgWithExcel(ExcelUtil.getCellData(1,3));
        userProfilePage.verifyUpdatedNameAndMobileNumberInTheDB(ExcelUtil.getCellData(1,1),ExcelUtil.getCellData(1,2));
    }

    @Test(priority =2)
    public void verifyUserProfileUpdateWithInvalidDataTypeForMobileNumber(Method method) {
        startTest(method.getName(), "Verify that User Profile is unable to update correctly with invalid data type for mobile number ");
        userViewPieceObjectPage.clickOnMyProfile();
        userProfilePage.updateMobileNumber(ExcelUtil.getRowData(2));
        userProfilePage.clickOnUpdateBtn();
        userProfilePage.verifyUserProfileUpdateInvalidDataTypeWithExcel(ExcelUtil.getCellData(2,3));
    }
}

