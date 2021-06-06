package com.tms.pages.admin.userprofile;

import com.tms.util.common.CommonSteps;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class AdminSignInPage {
    private WebDriver driver;
    private CommonSteps commonStepsObj;

    private By signInElement =  By.xpath("//*[contains(text(),'Admin Login')]");
    private By usernameElement =  By.name("username");
    private By passwordElement =  By.name("password");
    private By loginButtonElement =  By.name("login");
    private By logoutElement =  By.xpath("//*[@href='logout.php']");

    public AdminSignInPage(WebDriver driver, CommonSteps commonStepsObj) {
        this.driver = driver;
        this.commonStepsObj = commonStepsObj;
    }

    public void adminLoginWithExcelData(XSSFRow row) {
        commonStepsObj.waitUntilElementPresence(signInElement,4000);
        driver.findElement(signInElement).click();
        commonStepsObj.waitUntilElementPresence(usernameElement,4000);
        driver.findElement(usernameElement).click();
        driver.findElement(usernameElement).sendKeys(row.getCell(1).toString());
        driver.findElement(passwordElement).click();
        driver.findElement(passwordElement).sendKeys(row.getCell(2).toString());
        driver.findElement(loginButtonElement).click();
    }

    public void adminInvalidLoginWithExcelData(XSSFRow row) {
        commonStepsObj.waitUntilElementPresence(usernameElement,5000);
        driver.findElement(usernameElement).click();
        driver.findElement(usernameElement).sendKeys(row.getCell(1).toString());
        driver.findElement(passwordElement).click();
        driver.findElement(passwordElement).sendKeys(row.getCell(2).toString());
        driver.findElement(loginButtonElement).click();
    }

    public void verifyAdminLoginWithExcel(String expectedResult) {
        String ActualTitle = driver.getTitle();
        Assert.assertEquals(ActualTitle,expectedResult);
    }

    public void verifyInvalidAdminLoginWithExcel(String expectedResult) {
        String ActualTitle = driver.getTitle();
        Assert.assertNotEquals(ActualTitle,expectedResult);
    }

    public void adminLogout() {
        driver.findElement(By.className("dropdown-toggle")).click();
        commonStepsObj.waitUntilElementPresence(logoutElement,4000);
        driver.findElement(logoutElement).click();
    }

}