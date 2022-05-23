package com.insider;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

public class WebDriverFactory {
    public static WebDriver create(WebDriverName webDriverName) {
        return create(webDriverName, new MutableCapabilities());
    }

    public static WebDriver create(WebDriverName webDriverName, MutableCapabilities browserOptions) {
        switch (webDriverName) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver(new ChromeOptions().merge(browserOptions));
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver(new FirefoxOptions().merge(browserOptions));
            default:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver(new ChromeOptions().merge(browserOptions));
        }
    }
}