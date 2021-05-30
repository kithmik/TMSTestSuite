package com.tms.pages.user.navigation;

import com.tms.util.common.CommonSteps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserViewPieceObjectPage {

    private WebDriver driver;
    private CommonSteps commonStepsObj;
    private CommonSteps commonSteps;

    //Constructor
    public UserViewPieceObjectPage (WebDriver driver, CommonSteps commonStepsObj){

        this.driver = driver;
        this.commonStepsObj = commonStepsObj;
    }

    public UserViewPieceObjectPage (WebDriver driver){
        this.driver = driver;
        commonSteps = new CommonSteps(driver);

    }

    //Web Elements
    private By myProfileBtn = By.xpath("//a[@href='profile.php']");
    private By enquiryTab = By.xpath("//a[contains(text(),' Enquiry ')]");

    private By tourPackageElement = By.xpath("//a[@href='package-list.php']");

    /**
     * @Author Nishani
     */
    public void clickOnMyProfile() {
        System.out.println("clickOnMyProfile | UserViewPieceObjectPage");
        driver.findElement(myProfileBtn).click();
    }

    public void clickOnEnquiryTab(){
        driver.findElement(enquiryTab).click();

    }

    public void clickOnTourPackageMenu() {
        commonSteps.waitUntilNextElementAppears(tourPackageElement,4000);
        driver.findElement(tourPackageElement).click();
    }

}
