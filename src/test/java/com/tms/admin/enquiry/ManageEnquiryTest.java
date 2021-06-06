package com.tms.admin.enquiry;

import com.tms.base.BaseTest;
import com.tms.pages.admin.enquiry.ManageEnquiryPage;
import com.tms.pages.admin.navigation.AdminViewPieceObjectPage;
import com.tms.pages.admin.userprofile.AdminSignInPage;
import com.tms.pages.user.enquiry.EnquiryPage;
import com.tms.pages.user.navigation.UserViewPieceObjectPage;
import com.tms.util.common.CommonSteps;
import com.tms.util.excelutils.ExcelUtil;
import org.testng.annotations.*;
import java.lang.reflect.Method;
import static com.tms.util.extentreports.ExtentTestManager.startTest;

/**
 * @author Malsha Nishadini
 * Test Class for Manage Enquiries by admin
 *
 */

public class ManageEnquiryTest extends BaseTest{
    private AdminViewPieceObjectPage adminViewPieceObjectPageObj;
    private UserViewPieceObjectPage userViewPieceObjectPage;
    private CommonSteps commonStepsObj;
    private EnquiryPage enquiryPage;
    private AdminSignInPage adminSignInPageObj;
    private ManageEnquiryPage manageEnquiryPage;

    @BeforeClass
    public void setUp(){
        initialization();
        adminViewPieceObjectPageObj = new AdminViewPieceObjectPage(driver);
        userViewPieceObjectPage = new UserViewPieceObjectPage(driver);
        commonStepsObj = new CommonSteps(driver);
        enquiryPage = new EnquiryPage(driver, userViewPieceObjectPage);
        adminSignInPageObj = new AdminSignInPage(driver, commonStepsObj);
        manageEnquiryPage = new ManageEnquiryPage(driver, adminViewPieceObjectPageObj, commonStepsObj);

        //setup data - enquiry
        ExcelUtil.setExcelFileSheet("Enquiry");

        //submit enquiry
        enquiryPage.submitEnquiry(ExcelUtil.getRowData(1));

        //setup data - admin
        ExcelUtil.setExcelFileSheet("AdminLogin");
        //admin login into the admin panel
        adminSignInPageObj.adminLoginWithExcelData(ExcelUtil.getRowData(1));

    }

    @Test (priority = 1)
    public void verifyAdminIsAbleToReadEnquiryAndMarkEnquiryAsRead(Method method){
        startTest(method.getName(), "Verify that admin is able to read enquiry and mark enquiry as \"Read\"");
        manageEnquiryPage.readEnquiryByAdmin();
        ExcelUtil.setExcelFileSheet("Enquiry");
        manageEnquiryPage.verifySuccessFullReadMessage(ExcelUtil.getCellData(8,6));
        manageEnquiryPage.verifyStatusOfSubmittedEnquiryInTheDBAfterReading("1");

    }

}
