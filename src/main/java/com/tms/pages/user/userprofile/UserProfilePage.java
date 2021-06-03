package com.tms.pages.user.userprofile;

import com.tms.util.common.CommonSteps;
import com.tms.util.dbutils.DbUtil;
import com.tms.util.excelutils.ExcelUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import java.util.Map;
import java.util.logging.Logger;
import static org.testng.AssertJUnit.assertEquals;
/**
 * @Author Nishani Mendis
 *  User Profile Page- Web Elements and Its' Methods
 *  Verify Updated Name And Mobile Number In the DB.
 *
 */

public class UserProfilePage {

    private WebDriver driver;
    private CommonSteps commonSteps;
    private By userName = By.id("name");
    private By mobileNumber = By.id("mobileno");
    private By updateBtn = By.xpath("//button[contains(text(),'Update')]");
    private By updateSuccessMsg = By.xpath("//div[contains(text(),'Profile Updated Successfully')]");
    private static final DbUtil dbUtil = new DbUtil();
    private static final Logger LOGGER = Logger.getLogger(UserProfilePage.class.getName());
    private By loggedGmailAccount = By.name("email");

    public UserProfilePage(WebDriver driver){
        this.driver = driver;
    }

    public void updateUserName(XSSFRow row) {
        LOGGER.info("updateUserName | UserProfilePage");
        driver.findElement(userName).clear();
        driver.findElement(userName).sendKeys(row.getCell(1).toString());
    }

    public void updateMobileNumber(XSSFRow row) {
        LOGGER.info("updateMobileNumber | UserProfilePage");
        driver.findElement(mobileNumber).clear();
        driver.findElement(mobileNumber).sendKeys(row.getCell(2).toString());
    }

    public void clickOnUpdateBtn() {
        LOGGER.info("clickOnUpdateBtn | UserProfilePage");
        driver.findElement(updateBtn).click();

    }

    public void verifyUserProfileUpdateSuccessMsgWithExcel(String expectedResult) {
        LOGGER.info("verifyUserProfileUpdateSuccessMsgWithExcel  | UserProfilePage");
        Assert.assertEquals(driver.findElement(updateSuccessMsg).getText(),expectedResult);
    }

    public void verifyUserProfileUpdateInvalidDataTypeWithExcel(String expectedResult) {
        LOGGER.info("verifyUserProfileUpdateInvalidDataTypeWithExcel  | UserProfilePage");
        Assert.assertEquals(driver.findElement(mobileNumber).getAttribute("validationMessage"),expectedResult);
    }

    public void verifyUpdatedNameAndMobileNumberInTheDB(String expectedFullName, String expectedMobileNumber){
        LOGGER.info("verifyUpdatedNameAndMobileNumberInTheDB | UserProfilePage");
        String gmail = driver.findElement(loggedGmailAccount).getAttribute("value");
        Map<String, String> result = dbUtil.getDataFromUserTable(gmail);
        if (result != null) {
            LOGGER.info("Full Name : " + result.get("FullName"));
            assertEquals(result.get("FullName"), expectedFullName);
            LOGGER.info("Mobile Number : " + result.get("MobileNumber"));
            assertEquals( result.get("MobileNumber"), expectedMobileNumber);
        } else {
            LOGGER.info("Db record has not updated Successfully.");
        }
    }

}
