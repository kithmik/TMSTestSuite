package com.tms.pages.admin.navigation;
import com.tms.util.common.CommonSteps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * @Author Nishani Mendis
 * Admin View Navigation's Web Elements and Its' Methods.
 *
 */

public class AdminViewPieceObjectPage {

    private WebDriver driver;
    private By tourPackages = By.xpath("//span[contains(text(),' Tour Packages')]");
    private By createPackages = By.xpath("//a[contains(text(),'Create')]");
    private By dashboard = By.xpath("//span[contains(text(),'Dashboard')]");
    private By managePackages = By.xpath("//li/a[contains(text(),'Manage')]");
    private By manageUsers = By.xpath("//span[contains(text(),'Manage Users')]");
    private By manageBooking = By.xpath("//span[contains(text(),'Manage Booking')]");
    private By manageIssues = By.xpath("//span[contains(text(),'Manage Issues')]");
    private By manageEnguiries = By.xpath("//span[contains(text(),'Manage Enquiries')]");
    private By managePages = By.xpath("//span[contains(text(),'Manage Pages')]");
    private static final Logger LOGGER = Logger.getLogger(AdminViewPieceObjectPage.class.getName());
    private CommonSteps commonStepsObj;

    public AdminViewPieceObjectPage(WebDriver driver){
        this.driver = driver;
    }

    public void clickOnDashboard(){
        LOGGER.info("clickOnDashboard  | AdminViewPieceObjectPage");
        driver.findElement(dashboard).click();
    }

    public void clickOnTourPackages() {
        LOGGER.info("clickOnTourPackages  | AdminViewPieceObjectPage");
        commonStepsObj = new CommonSteps(driver);
        commonStepsObj.waitUntilElementPresence(tourPackages,3000);
        driver.findElement(tourPackages).click();
    }

    public void clickOnCreatePackages(){
        LOGGER.info("clickOnTourPackages  | AdminViewPieceObjectPage");
        driver.findElement(createPackages).click();
    }

    public void clickOnManagePackages(){
        LOGGER.info("clickOnManagePackages  | AdminViewPieceObjectPage");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(managePackages).click();
    }

    public void clickOnManageUsers(){
        LOGGER.info("clickOnTourPackages  | AdminViewPieceObjectPage");
        driver.findElement(manageUsers).click();
    }

    public void clickOnManageBooking(){
        LOGGER.info("clickOnTourPackages  | AdminViewPieceObjectPage");
        driver.findElement(manageBooking).click();
    }

    public void clickOnManageIssues(){
        LOGGER.info("clickOnTourPackages  | AdminViewPieceObjectPage");
        driver.findElement(manageIssues).click();
    }

    public void clickOnManageEnguiries(){
        LOGGER.info("clickOnTourPackages  | AdminViewPieceObjectPage");
        driver.findElement(manageEnguiries).click();
    }

    public void clickOnManagePages(){
        LOGGER.info("clickOnTourPackages  | AdminViewPieceObjectPage");
        driver.findElement(managePages).click();
    }


}
