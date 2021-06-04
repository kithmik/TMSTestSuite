package com.tms.admin.packages;

import com.tms.base.BaseTest;
import com.tms.pages.admin.navigation.AdminViewPieceObjectPage;
import com.tms.pages.admin.packages.ManagePackagePage;
import com.tms.pages.admin.packages.TourPackageCreationPage;
import com.tms.pages.admin.packages.UpdateTourPackagePage;
import com.tms.pages.admin.userprofile.AdminSignInPage;
import com.tms.util.common.CommonSteps;
import com.tms.util.excelutils.ExcelUtil;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.lang.reflect.Method;
import java.util.logging.Logger;
import static com.tms.util.excelutils.ExcelUtil.setExcelFileSheet;
import static com.tms.util.extentreports.ExtentTestManager.startTest;

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

    private CommonSteps commonStepsObj;
    private AdminSignInPage adminSignInPageObj;
    private AdminViewPieceObjectPage adminViewPieceObjectPageObj;
    private TourPackageCreationPage tourPackageCreationPageObj;
    private ManagePackagePage managePackagePageObj;
    private UpdateTourPackagePage updateTourPackagePageObj;
    private static final Logger LOGGER = Logger.getLogger(PackageCreateAndUpdateTest.class.getName());


    @BeforeClass
    public void setUp() throws InterruptedException {
        LOGGER.info("*********** Set Up Test **********");
        initialization();
        commonStepsObj = new CommonSteps(driver);
        adminSignInPageObj = new AdminSignInPage(driver, commonStepsObj);
        adminViewPieceObjectPageObj = new AdminViewPieceObjectPage(driver);
        tourPackageCreationPageObj = new TourPackageCreationPage(driver);
        managePackagePageObj = new ManagePackagePage(driver);
        updateTourPackagePageObj = new UpdateTourPackagePage(driver);
        ExcelUtil.setExcelFileSheet("AdminLogin");
        adminSignInPageObj.adminLoginWithExcelData(ExcelUtil.getRowData(1));
        setExcelFileSheet("Package");

    }

    @Test (priority = 1)
    public void verifyTourPackageCreationByFillingAllTheFieldsWithCorrectInputs(Method method){
        startTest(method.getName(), "Verify that admin is able to create Tour Package successfully by filling all the fields with correct inputs and save newly adding Package data to the database ");
        adminViewPieceObjectPageObj.clickOnTourPackages();
        adminViewPieceObjectPageObj.clickOnCreatePackages();
        tourPackageCreationPageObj.enterPackageDetailsInExcel(ExcelUtil.getRowData(1));
        tourPackageCreationPageObj.clickOnPackageCreateBtn();
        tourPackageCreationPageObj.verifyPackageCreationSuccessMsgWithExcel(ExcelUtil.getCellData(1,9));
        tourPackageCreationPageObj.verifyCreatedPackageNameInTheDB(ExcelUtil.getCellData(1,1));
    }

    @Test(priority = 2 ,dependsOnMethods = "verifyTourPackageCreationByFillingAllTheFieldsWithCorrectInputs")
    public void verifyTourPackageDetailsUpdate(Method method) {
        startTest(method.getName(), "Verify that Tour Package details update and save in database correctly. ");
        adminViewPieceObjectPageObj.clickOnTourPackages();
        adminViewPieceObjectPageObj.clickOnManagePackages();
        managePackagePageObj.clickOnViewDetailsBtn();
        updateTourPackagePageObj.updatePackagePriceAndFeatures(ExcelUtil.getRowData(2));
        updateTourPackagePageObj.clickOnUpdateBtn();
        updateTourPackagePageObj.verifyPackageUpdateSuccessMsgWithExcel(ExcelUtil.getCellData(2, 9));
        managePackagePageObj.verifyUpdatedPackageDetailsInTheDB(ExcelUtil.getCellData(2, 4), ExcelUtil.getCellData(2, 5));
    }

    @Test(priority = 3)
    public void verifyTourPackageCreationWithoutFillingMandatoryFields(Method method) {
        startTest(method.getName(), "Verify that admin is unable to create Tour Package successfully without filling mandatory fields. ");
        adminViewPieceObjectPageObj.clickOnTourPackages();
        adminViewPieceObjectPageObj.clickOnCreatePackages();
        tourPackageCreationPageObj.enterPackageDetailsInExcel(ExcelUtil.getRowData(3));
        tourPackageCreationPageObj.clickOnPackageCreateBtn();
        tourPackageCreationPageObj.verifyPackageCreationInvalidMsgWithExcel(ExcelUtil.getCellData(3, 9));
    }

    @Test(priority = 4)
    public void verifyTourPackageCreationWithoutFillingOptionalFields(Method method) {
        startTest(method.getName(), "Verify that admin is able to create Tour Package successfully without filling optional field. ");
        adminViewPieceObjectPageObj.clickOnTourPackages();
        adminViewPieceObjectPageObj.clickOnCreatePackages();
        tourPackageCreationPageObj.enterPackageDetailsInExcel(ExcelUtil.getRowData(4));
        tourPackageCreationPageObj.clickOnPackageCreateBtn();
        tourPackageCreationPageObj.verifyPackageCreationSuccessMsgWithExcel(ExcelUtil.getCellData(4,9));
    }

    @Test(priority = 5)
    public void verifyTourPackageCreationWithInvalidPriceTypeData(Method method) {
        startTest(method.getName(), "Verify that admin is unable to create Tour Package successfully by giving invalid data type for price. ");
        adminViewPieceObjectPageObj.clickOnTourPackages();
        adminViewPieceObjectPageObj.clickOnCreatePackages();
        tourPackageCreationPageObj.enterPackageDetailsInExcel(ExcelUtil.getRowData(5));
        tourPackageCreationPageObj.clickOnPackageCreateBtn();
        tourPackageCreationPageObj.verifyPackageCreationInvalidDataTypeWithExcel(ExcelUtil.getCellData(5, 9));
    }

    @Test (priority =6)
    public void verifyResetButtonFunctionality(Method method) {
        startTest(method.getName(), "Verify that Reset button is working correctly. ");
        adminViewPieceObjectPageObj.clickOnTourPackages();
        adminViewPieceObjectPageObj.clickOnCreatePackages();
        tourPackageCreationPageObj.enterPackageDetailsInExcel(ExcelUtil.getRowData(6));
        tourPackageCreationPageObj.clickOnResetBtn();
    }


}