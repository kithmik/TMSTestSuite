package com.tms.pages.user.userprofile;

//public class SignUpPage {
//}

import com.tms.pages.user.navigation.UserViewPieceObjectPage;
import com.tms.util.common.CommonSteps;
import com.tms.util.dbutils.DbUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.logging.Logger;

import static org.testng.AssertJUnit.assertEquals;

public class SignUpPage {

    private static final DbUtil dbUtil = new DbUtil();
    private WebDriver driver;
    private By fullName = By.xpath("//input[@name='fname']");
    private By mobileNumber = By.name("mobno");
    private By email = By.name("email");
    private By password = By.name("password");
    private By repeatPassword = By.name("repeatpassword");
    private By createAccountBtn = By.name("submit");
    private static final Logger LOGGER = Logger.getLogger(UserProfilePage.class.getName());
    private CommonSteps commonSteps;


    public SignUpPage(WebDriver driver){
        this.driver = driver;
        commonSteps= new CommonSteps(driver);
    }

    public void enterUserDetails(XSSFRow row) throws InterruptedException {
        LOGGER.info("enterUserDetails | SignUpPage");
        commonSteps.waitUntilElementPresence(fullName,60000);
        driver.findElement(fullName).sendKeys(row.getCell(1).toString());
        driver.findElement(mobileNumber).clear();
        driver.findElement(mobileNumber).sendKeys(row.getCell(2).toString());
        driver.findElement(email).clear();
        driver.findElement(email).sendKeys(row.getCell(3).toString());
        driver.findElement(password).clear();
        driver.findElement(password).sendKeys(row.getCell(4).toString());
        driver.findElement(repeatPassword).clear();
        driver.findElement(repeatPassword).sendKeys(row.getCell(5).toString());
    }

    public void clickOnSignUpBtn() {
        LOGGER.info("clickOnSignUpBtn | SignUpPage");
        driver.findElement(createAccountBtn).click();

    }

    public void verifyUserSignUpSuccessMsgWithExcel(String expectedResult) {
        LOGGER.info("verifyUserSignUpSuccessMsgWithExcel  | UserProfilePage");
        Assert.assertEquals(driver.switchTo().alert().getText(),expectedResult);
    }

    public void verifyUserSignUpUsingDb(String user){
        LOGGER.info(user);

        String status = dbUtil.getRegisteredUser(user);
        if (status != null) {
            LOGGER.info("Status : " + status);
            assertEquals("success", status);

            LOGGER.info("User has registered Successfully.");
        }
    }


}


