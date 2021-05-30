package com.tms.user.enquiry;

import com.tms.base.BaseTest;
import com.tms.pages.user.enquiry.EnquiryPage;
import com.tms.pages.user.navigation.UserViewPieceObjectPage;
import com.tms.util.common.CommonSteps;
import com.tms.util.excelutils.ExcelUtil;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import com.tms.util.listners.TestListners;


@Listeners({ TestListners.class })
public class EnquiryTest extends BaseTest {

    private EnquiryPage enquiryPage;
    private UserViewPieceObjectPage userViewPieceObjectPage;
    private CommonSteps commonStepsObj;

    @BeforeClass
    public void setup()
    {
        initialization();
    }

    @BeforeMethod
    public void login() {
        enquiryPage = new EnquiryPage(driver);
        userViewPieceObjectPage = new UserViewPieceObjectPage(driver, commonStepsObj);


    }

    @BeforeTest
    public void setupTestData() {
        //Set Test Data Excel and Sheet
        System.out.println("************Setup Test Level Data**********");
        ExcelUtil.setExcelFileSheet("Enquiry");
    }

    @Test
    public void verifyThatUserIsAbleToSuccessfullySubmitAnEnquiryByEnteringValidValuesForAllTheTextFields(){
        userViewPieceObjectPage.clickOnEnquiryTab();
        enquiryPage.submitEnquiry(ExcelUtil.getRowData(1));
        enquiryPage.verifySuccessFullSubmissionMessage("SUCCESS:Enquiry Successfully submitted");
        enquiryPage.verifyFullNameOfSubmittedEnquiryInTheDB(ExcelUtil.getCellData(1,1));
        enquiryPage.saveTestResults(1,6);
    }


}
