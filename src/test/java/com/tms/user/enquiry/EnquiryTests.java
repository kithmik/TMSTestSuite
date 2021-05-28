package com.tms.user.enquiry;

import com.tms.base.BaseTest;
import com.tms.pages.EnquiryPage;
import com.tms.util.excelutils.ExcelUtil;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import com.tms.util.listners.TestListners;


@Listeners({ TestListners.class })
public class EnquiryTests extends BaseTest {

    private EnquiryPage enquiryPage;

    @BeforeClass
    public void setup()
    {
        initialization();
    }

    @BeforeMethod
    public void login() {
        enquiryPage = new EnquiryPage(driver);
        driver.findElement(By.xpath("//a[contains(text(),' Enquiry ')]")).click();

    }

    @BeforeTest
    public void setupTestData() {
        //Set Test Data Excel and Sheet
        System.out.println("************Setup Test Level Data**********");
        ExcelUtil.setExcelFileSheet("EnquiryData");
    }

    @Test
    public void verifySubmittingEnquiryForm() {
        enquiryPage.submittingFormWithExcelData(ExcelUtil.getRowData(1));
        enquiryPage.saveTestResults(1,6);

    }


}