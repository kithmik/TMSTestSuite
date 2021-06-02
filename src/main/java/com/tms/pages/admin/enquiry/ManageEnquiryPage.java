package com.tms.pages.admin.enquiry;

import com.tms.pages.admin.navigation.AdminViewPieceObjectPage;
import com.tms.pages.user.enquiry.EnquiryPage;
import com.tms.util.common.CommonSteps;
import com.tms.util.dbutils.DbUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.Map;
import java.util.logging.Logger;
import static org.testng.Assert.assertEquals;

/**
 * @author Malsha Nishadini
 * Page Class for Manage Enquiries by admin
 *
 */

public class ManageEnquiryPage {
    private static final DbUtil dbUtil = new DbUtil();
    private static final Logger LOGGER = Logger.getLogger(EnquiryPage.class.getName());
    private WebDriver driver;
    private CommonSteps commonStepsObj;
    private AdminViewPieceObjectPage adminViewPieceObjectPage;

    public ManageEnquiryPage(WebDriver driver, AdminViewPieceObjectPage adminViewPieceObjectPage, CommonSteps commonStepsObj) {
        this.driver = driver;
        this.adminViewPieceObjectPage = adminViewPieceObjectPage;
        this.commonStepsObj = commonStepsObj;
    }

    private By pendingElement = By.xpath("//table/tbody/tr[last()]/td[last()]/span/a[1]");
    private By successfulMessage =  By.xpath("//*[@class= 'succWrap']");

    public void readEnquiryByAdmin(){
        LOGGER.info("Verifying read enquiry by admin");
        adminViewPieceObjectPage.clickOnManageEnguiries();
        commonStepsObj.scrollAtTheBottomOfThePage();
        commonStepsObj.waitUntilNextElementAppears(pendingElement,4000);
        driver.findElement(pendingElement).click();
        commonStepsObj.clickOkButtonOfConfirmPromt();

    }

    public void verifySuccessFullReadMessage(String expectedReadMessage){
        String successfulReadMessage = driver.findElement(successfulMessage).getText();
        assertEquals(successfulReadMessage, expectedReadMessage);

    }

    public void verifyStatusOfSubmittedEnquiryInTheDBAfterReading(String expectedStatus){
        Map<String, String> result = dbUtil.getDataFromEnquiryTable();
        if (result != null) {
            assertEquals(result.get("Status"), expectedStatus);
        } else {
            LOGGER.info("No db record found for Status");
        }
    }

}
