package com.tms.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


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


    public AdminViewPieceObjectPage(WebDriver driver){
        this.driver = driver;
    }

    /**
     * @Author Nishani
     */
    public void clickOnDashboard(){
        System.out.println("clickOnDashboard  | AdminViewPieceObjectPage");
        driver.findElement(dashboard).click();
    }

    /**
     * @Author Nishani
     */
    public void clickOnTourPackages() {
        System.out.println("clickOnTourPackages  | AdminViewPieceObjectPage");
        driver.findElement(tourPackages).click();
    }
    /**
     * @Author Nishani
     */
    public void clickOnCreatePackages(){
        System.out.println("clickOnTourPackages  | AdminViewPieceObjectPage");
        driver.findElement(createPackages).click();
    }
    /**
     * @Author Nishani
     */
    public void clickOnManagePackages(){
        System.out.println("clickOnTourPackages  | AdminViewPieceObjectPage");
        driver.findElement(managePackages).click();
    }
    /**
     * @Author Nishani
     */
    public void clickOnManageUsers(){
        System.out.println("clickOnTourPackages  | AdminViewPieceObjectPage");
        driver.findElement(manageUsers).click();
    }
    /**
     * @Author Nishani
     */
    public void clickOnManageBooking(){
        System.out.println("clickOnTourPackages  | AdminViewPieceObjectPage");
        driver.findElement(manageBooking).click();
    }   /**
     * @Author Nishani
     */
    public void clickOnManageIssues(){
        System.out.println("clickOnTourPackages  | AdminViewPieceObjectPage");
        driver.findElement(manageIssues).click();
    }   /**
     * @Author Nishani
     */
    public void clickOnManageEnguiries(){
        System.out.println("clickOnTourPackages  | AdminViewPieceObjectPage");
        driver.findElement(manageEnguiries).click();
    }
    /**
     * @Author Nishani
     */
    public void clickOnManagePages(){
        System.out.println("clickOnTourPackages  | AdminViewPieceObjectPage");
        driver.findElement(managePages).click();
    }


}
