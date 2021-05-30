package com.tms.pages.user.navigation;
import com.tms.util.common.CommonSteps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UserViewPieceObjectPage {

    private WebDriver driver;
    private CommonSteps commonSteps;

    private By myProfileBtn = By.xpath("//a[@href='profile.php']");
    private By tourPackageElement = By.xpath("//a[@href='package-list.php']");
    private By tourHistoryElement = By.xpath("//a[@href='tour-history.php']");




    public UserViewPieceObjectPage (WebDriver driver){
        this.driver = driver;
        commonSteps = new CommonSteps(driver);

    }

    /**
     * @Author Nishani
     */
    public void clickOnMyProfile() {
        System.out.println("clickOnMyProfile | UserViewPieceObjectPage");
        driver.findElement(myProfileBtn).click();
    }







    public void clickOnTourPackageMenu() {
        commonSteps.waitUntilNextElementAppears(tourPackageElement,4000);
        driver.findElement(tourPackageElement).click();
    }


}
