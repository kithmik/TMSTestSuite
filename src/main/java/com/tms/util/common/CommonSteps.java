package com.tms.util.common;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class CommonSteps {

    private WebDriver driver;

    public CommonSteps(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement waitUntilElementPresence(By locator, long timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public WebElement waitUntilElementClickable(By locator, long timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement waitUntilNextElementAppears(By locator, long timeOut) {
        WebElement element = new WebDriverWait(driver, timeOut)
                .until(ExpectedConditions.presenceOfElementLocated(locator));
        return element;
    }

    public void scrollPage(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)");

    }

    public void scrollAtTheBottomOfThePage(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void clickOkButtonOfConfirmPromt(){
        driver.switchTo( ).alert( ).accept();
    }

    public void tab() {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1)); //switches to new tab
    }

}
