package com.tms.admin.packages;

import com.tms.base.BaseTest;
import com.tms.pages.admin.navigation.AdminViewPieceObjectPage;
import com.tms.pages.admin.packages.ManagePackagePage;
import com.tms.pages.admin.packages.TourPackageCreationPage;
import com.tms.pages.admin.packages.UpdateTourPackagePage;
import com.tms.util.excelutils.ExcelUtil;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.logging.Logger;
import static com.tms.util.excelutils.ExcelUtil.setExcelFileSheet;

/**
 * @Author Nishani Mendis
 * Test Tour Package Create and Update with Valid and Invalid Inputs.
 *  1.verifyTourPackageCreationByFillingAllTheFieldsWithCorrectInputs
 *  2.VerifyTourPackageCreationWithoutFillingMandatoryFields
 *  3.VerifyTourPackageCreationWithoutFillingOptionalFields
 *  4.VerifyTourPackageCreationWithInvalidPriceTypeData
 *  5.verifyResetButton
 *  6.verifyTourPackageDetailsUpdate
 */
public class PackageCreateAndUpdateTest extends BaseTest {

    private AdminViewPieceObjectPage adminViewPieceObjectPageObj;
    private TourPackageCreationPage tourPackageCreationPageObj;
    private ManagePackagePage managePackagePageObj;
    private UpdateTourPackagePage updateTourPackagePageObj;
    private static final Logger LOGGER = Logger.getLogger(PackageCreateAndUpdateTest.class.getName());


    @BeforeClass
    public void setUp(){
        LOGGER.info("*********** Set Up Test **********");
        initialization();
        adminViewPieceObjectPageObj = new AdminViewPieceObjectPage(driver);
        tourPackageCreationPageObj = new TourPackageCreationPage(driver);
        managePackagePageObj = new ManagePackagePage(driver);
        updateTourPackagePageObj = new UpdateTourPackagePage(driver);
        driver.findElement(By.xpath("//a[contains(text(),'Admin Login')]")).click();
        driver.findElement(By.className("name")).click();
        driver.findElement(By.className("name")).sendKeys("admin");
        driver.findElement(By.className("password")).click();
        driver.findElement(By.className("password")).sendKeys("Test@123");
        driver.findElement(By.className("login")).click();
        setExcelFileSheet("Package");

    }

    @Test (priority = 1)
    public void verifyTourPackageCreationByFillingAllTheFieldsWithCorrectInputs(){
            LOGGER.info("*********** Test Start-:  verifyTourPackageCreationByFillingAllTheFieldsWithCorrectInputs | PackageCreateAndUpdateTest **********");
            adminViewPieceObjectPageObj.clickOnTourPackages();
            adminViewPieceObjectPageObj.clickOnCreatePackages();
            tourPackageCreationPageObj.enterPackageDetailsInExcel(ExcelUtil.getRowData(1));
            tourPackageCreationPageObj.clickOnPackageCreateBtn();
            tourPackageCreationPageObj.verifyPackageCreationSuccessMsgWithExcel(ExcelUtil.getCellData(1,9));
            tourPackageCreationPageObj.verifyCreatedPackageNameInTheDB(ExcelUtil.getCellData(1,1));
            LOGGER.info("########## Test End-:  verifyTourPackageCreationByFillingAllTheFieldsWithCorrectInputs | PackageCreateAndUpdateTest ########## ");
    }

    @Test(priority = 2)
    public void VerifyTourPackageCreationWithoutFillingMandatoryFields() {
        LOGGER.info("*********** Test Start-: VerifyTourPackageCreationWithoutFillingMandatoryFields | PackageCreateAndUpdateTest *********** ");
        adminViewPieceObjectPageObj.clickOnTourPackages();
        adminViewPieceObjectPageObj.clickOnCreatePackages();
        tourPackageCreationPageObj.enterPackageDetailsInExcel(ExcelUtil.getRowData(2));
        tourPackageCreationPageObj.clickOnPackageCreateBtn();
        tourPackageCreationPageObj.verifyPackageCreationInvalidMsgWithExcel(ExcelUtil.getCellData(2, 9));
        LOGGER.info("########## Test End-: VerifyTourPackageCreationWithoutFillingMandatoryFields | PackageCreateAndUpdateTest ########### ");
    }

    @Test(priority = 3)
    public void VerifyTourPackageCreationWithoutFillingOptionalFields() {
            LOGGER.info("********** Test Start-: VerifyTourPackageCreationWithoutFillingOptionalFields | PackageCreateAndUpdateTest **********");
            adminViewPieceObjectPageObj.clickOnTourPackages();
            adminViewPieceObjectPageObj.clickOnCreatePackages();
            tourPackageCreationPageObj.enterPackageDetailsInExcel(ExcelUtil.getRowData(3));
            tourPackageCreationPageObj.clickOnPackageCreateBtn();
            tourPackageCreationPageObj.verifyPackageCreationSuccessMsgWithExcel(ExcelUtil.getCellData(1,9));
            LOGGER.info("########## Test End-: VerifyTourPackageCreationWithoutFillingOptionalFields | PackageCreateAndUpdateTest ##########");
    }

    @Test(priority = 4)
    public void VerifyTourPackageCreationWithInvalidPriceTypeData() {
            LOGGER.info("********** Test Start-: VerifyTourPackageCreationWithInvalidPriceTypeData | PackageCreateAndUpdateTest **********");
            adminViewPieceObjectPageObj.clickOnTourPackages();
            adminViewPieceObjectPageObj.clickOnCreatePackages();
            tourPackageCreationPageObj.enterPackageDetailsInExcel(ExcelUtil.getRowData(4));
            tourPackageCreationPageObj.clickOnPackageCreateBtn();
            tourPackageCreationPageObj.verifyPackageCreationInvalidDataTypeWithExcel(ExcelUtil.getCellData(4, 9));
            LOGGER.info("########## Test End-: VerifyTourPackageCreationWithInvalidPriceTypeData | PackageCreateAndUpdateTest ##########");
    }

    @Test (priority =5)
    public void verifyResetButton() {
            LOGGER.info("*********** Test Start-: verifyResetButton | PackageCreateAndUpdateTest ***********");
            adminViewPieceObjectPageObj.clickOnTourPackages();
            adminViewPieceObjectPageObj.clickOnCreatePackages();
            tourPackageCreationPageObj.enterPackageDetailsInExcel(ExcelUtil.getRowData(5));
            tourPackageCreationPageObj.clickOnResetBtn();
            LOGGER.info("########### Test End-: verifyResetButton | PackageCreateAndUpdateTest ##########");
    }

    @Test(priority = 6, dependsOnMethods = "verifyTourPackageCreationByFillingAllTheFieldsWithCorrectInputs")
    public void verifyTourPackageDetailsUpdate() {
        LOGGER.info("********** Start-: verifyTourPackageDetailsUpdate | PackageCreateAndUpdateTest **********");
        adminViewPieceObjectPageObj.clickOnTourPackages();
        adminViewPieceObjectPageObj.clickOnManagePackages();
        managePackagePageObj.clickOnViewDetailsBtn();
        updateTourPackagePageObj.updatePackagePriceAndFeatures(ExcelUtil.getRowData(6));
        updateTourPackagePageObj.clickOnUpdateBtn();
        updateTourPackagePageObj.verifyPackageUpdateSuccessMsgWithExcel(ExcelUtil.getCellData(6, 9));
        managePackagePageObj.verifyUpdatedPackageDetailsInTheDB(ExcelUtil.getCellData(6, 4), ExcelUtil.getCellData(6, 5));
        LOGGER.info("########## End-: verifyTourPackageDetailsUpdate | PackageCreateAndUpdateTest ##########");
    }
}