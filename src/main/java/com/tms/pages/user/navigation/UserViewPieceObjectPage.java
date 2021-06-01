package com.tms.pages.user.navigation;

import com.tms.util.common.CommonSteps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.logging.Logger;

/**
 * @Author Nishani Mendis
 * User View Navigation's Web Elements and Its' Methods.
 *
 */

public class UserViewPieceObjectPage {

    private WebDriver driver;
    private CommonSteps commonStepsObj;
    private CommonSteps commonSteps;

    public UserViewPieceObjectPage (WebDriver driver, CommonSteps commonStepsObj){
        this.driver = driver;
        this.commonStepsObj = commonStepsObj;
    }

    public UserViewPieceObjectPage (WebDriver driver){
        this.driver = driver;
        commonSteps = new CommonSteps(driver);

    }

    private By myProfileBtn = By.xpath("//a[@href='profile.php']");
    private By enquiryTab = By.xpath("//a[contains(text(),' Enquiry ')]");
    private By tourPackageElement = By.xpath("//a[@href='package-list.php']");
    private static final Logger LOGGER = Logger.getLogger(UserViewPieceObjectPage.class.getName());

    public void clickOnMyProfile() {
        LOGGER.info("clickOnMyProfile | UserViewPieceObjectPage");
        driver.findElement(myProfileBtn).click();
    }

    public void clickOnEnquiryTab(){
        driver.findElement(enquiryTab).click();

    }

    public void clickOnTourPackageMenu() {
        LOGGER.info("clickOnTourPackageMenu | UserViewPieceObjectPage");
        commonSteps.waitUntilNextElementAppears(tourPackageElement,4000);
        driver.findElement(tourPackageElement).click();
    }

}
