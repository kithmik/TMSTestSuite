package com.tms.pages.admin.packages;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 *  @Author Nishani
 *  Update Tour Package Page Web Elements and Its' Methods.
 */

public class UpdateTourPackagePage {
    private WebDriver driver;
    private By packagePrice = By.id("packageprice");
    private By packageFeatures = By.id("packagefeatures");
    private By updateBtn = By.xpath("//button[contains(text(),'Update')]");

    public UpdateTourPackagePage(WebDriver driver){
        this.driver =driver;
    }

    public void updatePackagePriceAndFeatures(XSSFRow row) {
        System.out.println("updatePackagePriceAndFeatures  | UpdateTourPackagePage");
        driver.findElement(packagePrice).clear();
        driver.findElement(packagePrice).sendKeys(row.getCell(4).toString());
    }

    public void clickOnUpdateBtn() {
        System.out.println("clickOnUpdateBtn  | UpdateTourPackagePage");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)");
        driver.findElement(updateBtn).click();
    }

}
