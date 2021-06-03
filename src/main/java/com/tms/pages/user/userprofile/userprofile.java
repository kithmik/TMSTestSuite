package com.tms.pages.user.userprofile;

import com.tms.util.common.CommonSteps;
import com.tms.util.dbutils.DbUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Map;
import java.util.logging.Logger;

import static org.testng.AssertJUnit.assertEquals;

public class userprofile {
    private WebDriver driver;
    private CommonSteps commonStepsObj;

    private static final Logger LOGGER = Logger.getLogger(UserProfilePage.class.getName());
    private static final DbUtil dbUtil = new DbUtil();


    public userprofile(WebDriver driver, CommonSteps commonStepsObj) {
        this.driver = driver;
        this.commonStepsObj = commonStepsObj;

    }


    public void clickOnDetailsButton(String admin,String adminPassword) {

        By username = By.name("username");
        By password = By.name("password");
        By login = By.name("login");
        driver.findElement(By.xpath("//*[contains(text(),'Admin Login')]")).click();
        driver.findElement(username).click();
        driver.findElement(username).sendKeys(admin);
        driver.findElement(password).click();
        driver.findElement(password).sendKeys(adminPassword);

        driver.findElement(login).click();
//
//        System.out.println("Login Done with Click");
    }

    public void clickSignInButton(String email,String password) throws InterruptedException {
        driver.findElement(By.xpath("//*[contains(text(),'/ Sign In')]")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.xpath("//input[@id='password'][@required='']")).click();
        driver.findElement(By.xpath("//input[@id='password'][@required='']")).sendKeys(password);
        driver.findElement(By.name("signin")).click();
//        ExcelUtil.setExcelFileSheet("Login");
    }

    public void clickSignUp(XSSFRow row) throws InterruptedException {

        driver.findElement(By.xpath("//*[contains(text(),'Sign Up')]")).click();
        Thread.sleep(3000);
        driver.findElement(By.name("fname")).click();
        driver.findElement(By.name("fname")).sendKeys(row.getCell(1).toString());

        driver.findElement(By.name("mobno")).click();
        driver.findElement(By.name("mobno")).sendKeys(row.getCell(2).toString());

        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).sendKeys(row.getCell(3).toString());

        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).sendKeys(row.getCell(4).toString());

        driver.findElement(By.name("repeatpassword")).click();
        driver.findElement(By.name("repeatpassword")).sendKeys(row.getCell(5).toString());

        driver.findElement(By.name("submit")).click();

    }



    public void signAgain(String email,String password) throws InterruptedException {
        driver.findElement(By.xpath("//*[contains(text(),'/ Sign In')]")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.xpath("//input[@id='password'][@required='']")).click();
        driver.findElement(By.xpath("//input[@id='password'][@required='']")).sendKeys(password);
        driver.findElement(By.name("signin")).click();
//        ExcelUtil.setExcelFileSheet("Login");
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