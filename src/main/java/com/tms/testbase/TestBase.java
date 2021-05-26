package com.tms.testbase;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.util.Properties;

public class TestBase {
    public static WebDriver driver;
    protected static String baseUrl, browser;
    private Properties properties;

    public TestBase() {

    }

    @BeforeSuite
    public void initialization() {
        try {
            getProperties();
            browser = properties.getProperty("browser");
            baseUrl = properties.getProperty("baseUrl");

            driver.manage().window().maximize();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void getProperties() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream("./src/main/resources/config/basic_config.properties"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
