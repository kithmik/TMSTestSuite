package com.tms.admin.enquiry;

import com.tms.base.BaseTest;
import com.tms.pages.admin.enquiry.ManageEnquiryPage;
import com.tms.pages.admin.navigation.AdminViewPieceObjectPage;
import com.tms.pages.user.enquiry.EnquiryPage;
import com.tms.pages.user.navigation.UserViewPieceObjectPage;
import com.tms.util.common.CommonSteps;
import com.tms.util.excelutils.ExcelUtil;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.lang.reflect.Method;
import static com.tms.util.extentreports.ExtentTestManager.startTest;

/**
 * @author Malsha Nishadini
 * Test Class for Manage Enquiries by admin
 *
 */

public class ManageEnquiryTest extends BaseTest{
    private ManageEnquiryPage manageEnquiryPage;
    private AdminViewPieceObjectPage adminViewPieceObjectPage;
    private UserViewPieceObjectPage userViewPieceObjectPage;
    private EnquiryPage enquiryPage;
    private CommonSteps commonStepsObj;

    @BeforeClass
    public void setup(){
        initialization();

    }

    @BeforeClass
    public void setupTestData() {
        ExcelUtil.setExcelFileSheet("Enquiry");
    }

    @BeforeMethod
    public void login() throws InterruptedException {
        manageEnquiryPage = new ManageEnquiryPage(driver, commonStepsObj);
        adminViewPieceObjectPage = new AdminViewPieceObjectPage(driver);
        userViewPieceObjectPage = new UserViewPieceObjectPage(driver);
        //enquiryPage = new EnquiryPage(driver);

        //submit enquiry
        userViewPieceObjectPage.clickOnEnquiryTab();
        enquiryPage.submitEnquiry(ExcelUtil.getRowData(1));


        //admin login
        driver.findElement(By.xpath("//*[contains(text(),'Admin Login')]")).click();
        Thread.sleep(3000);
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).sendKeys("Test@123");
        driver.findElement(By.name("login")).click();
    }

    @Test(priority = 1)
    public void verifyAdminCanReadTheEnquiry(Method method){
        startTest(method.getName(), "Verify that admin is able to read enquiry and mark enquiry as \"Read\"");
        adminViewPieceObjectPage.clickOnManageEnguiries();
        manageEnquiryPage.readEnquiryByAdmin();
        commonStepsObj.clickOkButtonOfConfirmPromt();
        manageEnquiryPage.verifySuccessFullReadMessage(ExcelUtil.getCellData(8,6));
        manageEnquiryPage.verifyStatusOfSubmittedEnquiryInTheDBAfterReading(1);

    }


}
