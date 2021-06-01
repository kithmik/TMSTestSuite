package com.tms.pages.admin.packages;
import com.tms.util.common.CommonSteps;
import com.tms.util.dbutils.DbUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.Map;
import java.util.logging.Logger;
import static org.testng.AssertJUnit.assertEquals;

/**
 * @Author Nishani Mendis
 *  Manage Package Page Web Elements and Its' Methods
 *  Verify Updated Package Details In the DB.
 */

public class ManagePackagePage {

    private WebDriver driver;
    private By viewDetailsBtn = By.xpath("//table/tbody/tr[last()]/td[last()]/span/a/button[contains(text(),'View Details')]");
    private static final DbUtil dbUtil = new DbUtil();
    private static final Logger LOGGER = Logger.getLogger(TourPackageCreationPage.class.getName());
    private CommonSteps commonStepsObj;

    public ManagePackagePage(WebDriver driver){
        this.driver = driver;
    }

    public void clickOnViewDetailsBtn(){
        LOGGER.info("clickOnViewDetailsBtn  | ManagePackagePage");
        commonStepsObj = new CommonSteps(driver);
        commonStepsObj.scrollAtTheBottomOfThePage();
        driver.findElement(viewDetailsBtn).click();
    }

    public void verifyUpdatedPackageDetailsInTheDB(String expectedPrice, String expectedFeatures){
        LOGGER.info("verifyUpdatedPackageDetailsInTheDB  | ManagePackagePage");
        Map<String, String> result = dbUtil.getDataFromTourPackagesTable();
        if (result != null) {
            LOGGER.info("Updated Tour Package Price : " + result.get("PackagePrice"));
            LOGGER.info("Updated Tour Package Features : " + result.get("PackageFetures"));
            assertEquals(result.get("PackagePrice"),expectedPrice);
            assertEquals(result.get("PackageFetures"),expectedFeatures);
        } else {
            LOGGER.info("Database has not updated successfully.");
        }
    }
}





