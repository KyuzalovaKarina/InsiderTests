package com.insider;

import io.restassured.RestAssured;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.time.Duration;

public class BaseTest {
    protected static WebDriver driver;
    protected static String browser = System.getProperty("browser").toUpperCase();

    @BeforeClass
    public static void setUp() {
        driver = WebDriverFactory.create(WebDriverName.valueOf(browser));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        driver.manage().window().maximize();
        System.out.println("Driver created");
    }


    @AfterClass
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
        System.out.println("Driver quit");
    }

    @AfterMethod
    public static void takeScreenshot(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                TakesScreenshot ts = (TakesScreenshot) driver;
                File source = ts.getScreenshotAs(OutputType.FILE);
                FileHandler.copy(source, new File("target\\" + result.getName() + ".png"));
                System.out.println("Screenshot taken");

            } catch (Exception e) {
                System.out.println("Exception while taking screenshot " + e.getMessage());
            }
        }
    }
}