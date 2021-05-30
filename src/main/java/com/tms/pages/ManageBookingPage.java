package com.tms.pages;

import com.tms.util.CommonSteps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ManageBookingPage {
    private WebDriver driver;
    private CommonSteps commonStepsObj;


    public ManageBookingPage(WebDriver driver, CommonSteps commonStepsObj) {
        this.driver = driver;
        this.commonStepsObj = commonStepsObj;
    }
    private By tourHistoryElement = By.xpath("//a[@href='tour-history.php']");


    public void clickOnMyTourHistoryMenu() {
        driver.findElement(tourHistoryElement).click();
    }
}
