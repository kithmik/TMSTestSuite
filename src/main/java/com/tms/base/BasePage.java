package com.tms.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    private WebDriver driver;

    public void verifyPageLoad(long timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
    }

}
