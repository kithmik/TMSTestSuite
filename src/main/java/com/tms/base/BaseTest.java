package com.tms.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class BaseTest {
    public static WebDriver driver;
    public static Properties properties;
    private static final Logger LOGGER = Logger.getLogger(BaseTest.class.getName());

    protected static boolean isBrowserOpen = false;
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

    public WebDriver getDriver() {
        return driver;
    }

    public static void initialization() {
        LOGGER.info("Tests is starting!");
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

        isBrowserOpen = !driver.toString().contains("null");
    }

    @AfterClass
    public void closeBrowser(){
        LOGGER.info("Tests are ending!");
        driver.close();
        isBrowserOpen = !driver.toString().contains("null");
    }

}

