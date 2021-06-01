package com.tms.user.enquiry;

import static com.tms.util.extentreports.ExtentTestManager.startTest;
import com.tms.base.BaseTest;
import com.tms.pages.user.enquiry.EnquiryPage;
import com.tms.pages.user.navigation.UserViewPieceObjectPage;
import com.tms.util.common.CommonSteps;
import com.tms.util.excelutils.ExcelUtil;
import org.testng.annotations.*;
import java.lang.reflect.Method;

/**
 * @author Malsha Nishadini
 * Test Class for Enquiry Submission by a user
 *
 */

public class EnquiryTest extends BaseTest {

    private EnquiryPage enquiryPage;
    private UserViewPieceObjectPage userViewPieceObjectPage;
    private CommonSteps commonStepsObj;

    @BeforeClass
    public void setup()
    {
        initialization();
    }

    @BeforeClass
    public void setupTestData() {
        ExcelUtil.setExcelFileSheet("Enquiry");
    }

    @BeforeMethod
    public void login() {
        enquiryPage = new EnquiryPage(driver);
        userViewPieceObjectPage = new UserViewPieceObjectPage(driver, commonStepsObj);

    }

    @Test(priority = 1, description = "Verify that user is able to successfully submit an enquiry by entering valid values for all text fields")
    public void submitEnquiryTestWithValidValuesForAllTextFields(Method method){
        startTest(method.getName(), "Verify that user is able to successfully submit an enquiry by entering valid values for all text fields");
        userViewPieceObjectPage.clickOnEnquiryTab();
        enquiryPage.submitEnquiry(ExcelUtil.getRowData(1));
        enquiryPage.verifySuccessFullSubmissionMessage("SUCCESS:Enquiry Successfully submitted");
        enquiryPage.verifyFullNameOfSubmittedEnquiryInTheDB(ExcelUtil.getCellData(1,1));

    }




}
