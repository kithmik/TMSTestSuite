package com.tms.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {
    public static WebDriver driver;
    public static Properties properties;

    public BaseTest(){
        try {
            properties = new Properties();
            FileInputStream ip = new FileInputStream("src/main/resources/config/basic_config.properties");
            properties.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void initialization() {
        String browserName = properties.getProperty("browser");

        if(browserName.equals("chrome")){
            System.setProperty("webdriver.chrome.driver", properties.getProperty("webdriver.chrome.path"));
            driver = new ChromeDriver();
        }
        else if(browserName.equals("firefox")){
            System.setProperty("webdriver.gecko.driver", properties.getProperty("webdriver.firefox.path"));
            driver = new FirefoxDriver();
        }


        driver.manage().window().maximize();

        driver.get(properties.getProperty("baseUrl"));
    }

    @AfterSuite
    public void closeBrowser(){
        driver.close();
    }

}

