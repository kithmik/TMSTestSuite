package com.tms.pages;
import com.tms.util.CommonSteps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UserViewPieceObjectPage {

    private WebDriver driver;
    private CommonSteps commonSteps;

    private By myProfileBtn = By.xpath("//a[@href='profile.php']");


    public UserViewPieceObjectPage (WebDriver driver){
        this.driver = driver;
    }

    /**
     * @Author Nishani
     */
    public void clickOnMyProfile() {
        System.out.println("clickOnMyProfile | UserViewPieceObjectPage");
        driver.findElement(myProfileBtn).click();
    }
}
