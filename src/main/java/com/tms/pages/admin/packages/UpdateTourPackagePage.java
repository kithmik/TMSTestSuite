package com.tms.pages.admin.packages;
import com.tms.util.common.CommonSteps;
import com.tms.util.dbutils.DbUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.logging.Logger;

/**
 *  @Author Nishani
 *  Update Tour Package Page Web Elements and Its' Methods.
 */

public class UpdateTourPackagePage {
    private WebDriver driver;
    private By packagePrice = By.id("packageprice");
    private By packageFeatures = By.id("packagefeatures");
    private By updateBtn = By.xpath("//button[contains(text(),'Update')]");
    private By packageDataUpdateSuccessMsg = By.xpath("//div[contains(text(),'Package Updated Successfully')]");
    private static final Logger LOGGER = Logger.getLogger(TourPackageCreationPage.class.getName());
    private CommonSteps commonStepsObj;

    public UpdateTourPackagePage(WebDriver driver){
        this.driver =driver;
    }

    public void updatePackagePriceAndFeatures(XSSFRow row) {
        LOGGER.info("updatePackagePriceAndFeatures  | UpdateTourPackagePage");
        driver.findElement(packagePrice).clear();
        driver.findElement(packagePrice).sendKeys(row.getCell(4).toString());
        driver.findElement(packageFeatures).clear();
        driver.findElement(packageFeatures).sendKeys(row.getCell(5).toString());
    }

    public void clickOnUpdateBtn() {
        LOGGER.info("clickOnUpdateBtn  | UpdateTourPackagePage");
        commonStepsObj = new CommonSteps(driver);
        commonStepsObj.scrollAtTheBottomOfThePage();
        driver.findElement(updateBtn).click();
    }

    public void verifyPackageUpdateSuccessMsgWithExcel(String expectedResult) {
        LOGGER.info("verifyPackageUpdateSuccessMsgWithExcel  | UpdateTourPackagePage");
        Assert.assertEquals(driver.findElement(packageDataUpdateSuccessMsg).getText(),expectedResult);
    }

}
