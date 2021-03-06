package com.tms.user.enquiry;

import static com.tms.util.extentreports.ExtentTestManager.startTest;
import com.tms.base.BaseTest;
import com.tms.pages.user.enquiry.EnquiryPage;
import com.tms.pages.user.navigation.UserViewPieceObjectPage;
import com.tms.util.excelutils.ExcelUtil;
import org.testng.annotations.*;
import java.lang.reflect.Method;

/**
 * @author Malsha Nishadini
 * Test Class for Enquiry Submission by a user
 *
 */

public class EnquiryTest extends BaseTest {

    private UserViewPieceObjectPage userViewPieceObjectPage;
    private EnquiryPage enquiryPage;

    @BeforeClass
    public void setup()
    {
        initialization();
        userViewPieceObjectPage = new UserViewPieceObjectPage(driver);
        enquiryPage = new EnquiryPage(driver, userViewPieceObjectPage);

        //setup data
        ExcelUtil.setExcelFileSheet("Enquiry");

    }

    @Test(priority = 1)
    public void verifySubmitEnquiryWithValidValuesForAllTextFields(Method method){
        startTest(method.getName(), "Verify that user is able to successfully submit an enquiry by entering valid values for all text fields");
        enquiryPage.submitEnquiry(ExcelUtil.getRowData(1));
        enquiryPage.verifySuccessFullSubmissionMessage(ExcelUtil.getCellData(1,6));
        enquiryPage.verifyFullNameOfSubmittedEnquiryInTheDB(ExcelUtil.getCellData(1,1));

    }

    @Test(priority = 2)
    public void verifySubmitEnquiryWithEmailWhichHasMissingAtSign(Method method){
        startTest(method.getName(), "Verify that user is unable to submit an enquiry with email address which has missing '@' sign");
        enquiryPage.submitEnquiry(ExcelUtil.getRowData(2));
        enquiryPage.verifyValidationMessageForValidEmail(ExcelUtil.getCellData(2,7));

    }

    @Test(priority = 3)
    public void verifySubmitEnquiryWithEmailWhichHasMissingUsername(Method method){
        startTest(method.getName(), "Verify that user is unable to submit an enquiry with email address which has missing username");
        enquiryPage.submitEnquiry(ExcelUtil.getRowData(3));
        enquiryPage.verifyValidationMessageForValidEmail(ExcelUtil.getCellData(3,7));

    }

    @Test(priority = 4)
    public void verifySubmitEnquiryWithSpecialCharactersValueForMobileNoTextField(Method method){
        startTest(method.getName(), "Verify that user is unable to submit an enquiry with special characters value for the Mobile No text field");
        enquiryPage.submitEnquiry(ExcelUtil.getRowData(4));
        enquiryPage.verifyValidationMessageForValidMobileNo(ExcelUtil.getCellData(4,7));
    }

    @Test(priority = 5)
    public void verifySubmitEnquiryWithLessThan10DigitsValueForMobileNoTextField(Method method){
        startTest(method.getName(), "Verify that user is unable to submit an enquiry with less than 10 digits value for Mobile No text filed");
        enquiryPage.submitEnquiry(ExcelUtil.getRowData(5));
        enquiryPage.verifyValidationMessageForValidMobileNo(ExcelUtil.getCellData(5,7));

    }

    @Test(priority = 6)
    public void verifySubmitEnquiryWithEmptyValueForFullNameTextField(Method method){
        startTest(method.getName(), "Verify that user is unable to submit an enquiry with empty value for the Full name text field");
        enquiryPage.submitEnquiry(ExcelUtil.getRowData(6));
        enquiryPage.verifyValidationMessageWhenHavingEmptyTextFields("Full name", ExcelUtil.getCellData(6,7));
    }

    @Test(priority = 7)
    public void verifySubmitEnquiryWithEmptyValueForDescriptionTextField(Method method){
        startTest(method.getName(), "Verify that user is unable to submit an enquiry with empty value for the Description text field");
        enquiryPage.submitEnquiry(ExcelUtil.getRowData(7));
        enquiryPage.verifyValidationMessageWhenHavingEmptyTextFields("Description", ExcelUtil.getCellData(7,7));
    }

}
