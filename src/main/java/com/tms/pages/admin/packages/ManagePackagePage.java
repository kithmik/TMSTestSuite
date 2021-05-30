package com.tms.pages.admin.packages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @Author Nishani Mendis
 *  Manage Package Page Web Elements and Its' Methods
 */

public class ManagePackagePage {
    private WebDriver driver;
    private By viewDetailsBtn = By.xpath("//*[@id=\"table\"]/tbody/tr[1]/td[7]/span/a/button");

    public ManagePackagePage(WebDriver driver){
        this.driver = driver;
    }

    public void clickOnViewDetailsBtn(){
        System.out.println("clickOnViewDetailsBtn  | ManagePackagePage");
        driver.findElement(viewDetailsBtn).click();
    }
}


