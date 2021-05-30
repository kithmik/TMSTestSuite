package com.tms.pages.admin.packages;

import com.tms.util.excelutils.ExcelUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
/**
 * @Author Nishani Mendis
 * Tour Package Creation Page Web Elements and Its' Methods.
 */

public class TourPackageCreationPage {

    private WebDriver driver;
    private By packageName = By.id("packagename");
    private By packageType = By.id("packagetype");
    private By packageLocation = By.id("packagelocation");
    private By packagePrice = By.id("packageprice");
    private By packageFeatures = By.id("packagefeatures");
    private By packageDetails = By.id("packagedetails");
    private By packageImage = By.id("packageimage");
    private By createButton = By.xpath("//button[contains(text(),'Create')]");
    private By resetButton = By.xpath("//button[contains(text(),'Reset')]");
    private By packageCreationSuccessMsg = By.xpath("//div[contains(text(),'Package Created Successfully')]");
    private By packageDataUpdateSuccessMsg = By.xpath("//div[contains(text(),'Package Updated Successfully')]");

    public TourPackageCreationPage(WebDriver driver){
        this.driver =driver;
    }

    public void enterPackageDetailsInExcel(XSSFRow row) {
        System.out.println("enterPackageDetails  | TourPackageCreationPage");
        driver.findElement(packageName).sendKeys(row.getCell(1).toString());
        driver.findElement(packageType).sendKeys(row.getCell(2).toString());
        driver.findElement(packageLocation).sendKeys(row.getCell(3).toString());
        driver.findElement(packagePrice).sendKeys(row.getCell(4).toString());
        driver.findElement(packageFeatures).sendKeys(row.getCell(5).toString());
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)");
        driver.findElement(packageDetails).sendKeys(row.getCell(6).toString());
        driver.findElement(packageImage).sendKeys(row.getCell(7).toString()+row.getCell(8).toString());
    }

    public void clickOnPackageCreateBtn() {
        System.out.println("clickOnPackageCreateBtn  | TourPackageCreationPage");
        driver.findElement(createButton).click();

    }

    public void clickOnResetBtn() {
        System.out.println("clickOnResetBtn  | TourPackageCreationPage");
        driver.findElement(resetButton).click();
        Assert.assertEquals(driver.findElement(packageName).getText(),"");
        Assert.assertEquals(driver.findElement(packageType).getText(),"");
        Assert.assertEquals(driver.findElement(packageLocation).getText(),"");
        Assert.assertEquals(driver.findElement(packagePrice).getText(),"");
        Assert.assertEquals(driver.findElement(packageFeatures).getText(),"");
        Assert.assertEquals(driver.findElement(packageDetails).getText(),"");
        Assert.assertEquals(driver.findElement(packageDetails).getText(),"");
    }

    public void verifyPackageCreationSuccessMsgWithExcel(String expectedResult) {
        System.out.println("verifyPackageCreationSuccessMsgWithExcel  | TourPackageCreationPage");
        Assert.assertEquals(driver.findElement(packageCreationSuccessMsg).getText(),expectedResult);
    }

    public void verifyPackageCreationInvalidMsgWithExcel(String expectedResult) {
        System.out.println("verifyPackageCreationInvalidMsgWithExcel  | TourPackageCreationPage");
        Assert.assertEquals(driver.findElement(packageLocation).getAttribute("validationMessage"),expectedResult);
    }

    public void verifyPackageCreationInvalidDataTypeWithExcel(String expectedResult) {
        System.out.println("verifyPackageCreationInvalidDataTypeWithExcel  | TourPackageCreationPage");
        Assert.assertEquals(driver.findElement(packagePrice).getAttribute("validationMessage"),expectedResult);
    }

    public void verifyPackageUpdateSuccessMsgWithExcel(String expectedResult) {
        System.out.println("verifyPackageUpdateSuccessMsgWithExcel  | TourPackageCreationPage");
        Assert.assertEquals(driver.findElement(packageDataUpdateSuccessMsg).getText(),expectedResult);
    }

    public void saveTestResults(int row, int column) {
        ExcelUtil.rowNumber = row ;
        ExcelUtil.columnNumber = column;
    }

}

