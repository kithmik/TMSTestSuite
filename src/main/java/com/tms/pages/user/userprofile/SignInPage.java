package com.tms.pages.user.userprofile;

//public class SignInPage {
//}


import com.tms.util.common.CommonSteps;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SignInPage {
    private WebDriver driver;
    private CommonSteps commonStepsObj;


    public SignInPage(WebDriver driver, CommonSteps commonStepsObj) {
        this.driver = driver;
        this.commonStepsObj = commonStepsObj;
    }

    private By signInElement =  By.xpath("//*[contains(text(),'/ Sign In')]");
    private By usernameElement =  By.name("email");
    private By passwordElement =  By.xpath("//input[@id='password'][@required='']");
    private By signInButtonElement =  By.name("signin");
    private By logoutElement =  By.xpath("//*[@href='logout.php']");



    public void userLoginWithExcelData(XSSFRow row) throws InterruptedException {

        driver.findElement(signInElement).click();
        Thread.sleep(3000);
        driver.findElement(usernameElement).click();
        driver.findElement(usernameElement).sendKeys(row.getCell(1).toString());
        driver.findElement(passwordElement).click();
        driver.findElement(passwordElement).sendKeys(row.getCell(2).toString());
        driver.findElement(signInButtonElement).click();


//        driver.findElement(By.xpath("//*[contains(text(),'/ Sign In')]")).click();
//        Thread.sleep(3000);
//        driver.findElement(By.id("email")).click();
//        driver.findElement(By.id("email")).sendKeys(row.getCell(1).toString());
//        driver.findElement(By.xpath("//input[@id='password'][@required='']")).click();
//        driver.findElement(By.xpath("//input[@id='password'][@required='']")).sendKeys(row.getCell(2).toString());
//        driver.findElement(By.name("signin")).click();


    }

    public void userInvalidLoginWithExcelData(XSSFRow row) throws InterruptedException {


//        driver.findElement(By.xpath("//*[contains(text(),'/ Sign In')]")).click();
//        Thread.sleep(3000);
//        driver.findElement(By.id("email")).click();
//        driver.findElement(By.id("email")).sendKeys(row.getCell(1).toString());
//        driver.findElement(By.xpath("//input[@id='password'][@required='']")).click();
//        driver.findElement(By.xpath("//input[@id='password'][@required='']")).sendKeys(row.getCell(2).toString());
//        driver.findElement(By.name("signin")).click();

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

    public void userLogout() {
        driver.findElement(By.className("dropdown-toggle")).click();
        commonStepsObj.waitUntilElementPresence(logoutElement,4000);
        driver.findElement(logoutElement).click();

    }

}
