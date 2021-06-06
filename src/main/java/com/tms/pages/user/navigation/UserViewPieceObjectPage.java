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
    private By signUpBtn = By.xpath("//a[contains(text(),'Sign Up')]");
    private By myProfileBtn = By.xpath("//a[@href='profile.php']");
    private By enquiryTab = By.xpath("//a[contains(text(),' Enquiry ')]");
    private By tourPackageElement = By.xpath("//a[@href='package-list.php']");
    private static final Logger LOGGER = Logger.getLogger(UserViewPieceObjectPage.class.getName());

    public UserViewPieceObjectPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnSignUp() {
        LOGGER.info("clickOnSignUp | UserViewPieceObjectPage");
        driver.findElement(signUpBtn).click();
    }

    public void clickOnMyProfile() {
        LOGGER.info("clickOnMyProfile | UserViewPieceObjectPage");
        driver.findElement(myProfileBtn).click();
    }

    public void clickOnEnquiryTab(){
        driver.findElement(enquiryTab).click();

    }

    public void clickOnTourPackageMenu() {
        LOGGER.info("clickOnTourPackageMenu | UserViewPieceObjectPage");
        commonStepsObj=new CommonSteps(driver);
        commonStepsObj.waitUntilNextElementAppears(tourPackageElement,4000);
        driver.findElement(tourPackageElement).click();
    }

}
