package com.tms.pages.user.userprofile;

import com.tms.util.common.CommonSteps;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class UserSignInPage {
    private WebDriver driver;
    private CommonSteps commonStepsObj;
    private By signInElement =  By.xpath("//*[contains(text(),'/ Sign In')]");
    private By usernameElement =  By.id("email");
    private By passwordElement =  By.xpath("//input[@id='password'][@required='']");
    private By signInButtonElement =  By.name("signin");
    private By logoutElement =  By.xpath("//*[contains(text(),'/ Logout')]");

    public UserSignInPage(WebDriver driver, CommonSteps commonStepsObj) {
        this.driver = driver;
        this.commonStepsObj = commonStepsObj;
    }

    public void userLoginWithExcelData(XSSFRow row) throws InterruptedException {

        driver.findElement(signInElement).click();
        Thread.sleep(3000);
        driver.findElement(usernameElement).click();
        driver.findElement(usernameElement).sendKeys(row.getCell(1).toString());
        driver.findElement(passwordElement).click();
        driver.findElement(passwordElement).sendKeys(row.getCell(2).toString());
        driver.findElement(signInButtonElement).click();

    }

    public void userInvalidLoginWithExcelData(XSSFRow row) throws InterruptedException {
        driver.findElement(signInElement).click();
        Thread.sleep(3000);
        driver.findElement(usernameElement).click();
        driver.findElement(usernameElement).sendKeys(row.getCell(1).toString());
        driver.findElement(passwordElement).click();
        driver.findElement(passwordElement).sendKeys(row.getCell(2).toString());
        driver.findElement(signInButtonElement).click();

    }

    public void verifyUserLoginWithExcel(String expectedResult) {
        String ActualTitle = driver.getTitle();
        Assert.assertEquals(ActualTitle,expectedResult);
    }

    public void verifyInvalidUserLoginWithExcel(String expectedResult) {
        String ActualTitle = driver.getTitle();
        Assert.assertNotEquals(ActualTitle,expectedResult);
    }

    public void userLogout() {
        driver.findElement(logoutElement).click();
    }
}
