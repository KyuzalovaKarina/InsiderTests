package com.insider.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StartPage {

    private WebDriver driver;

    @FindBy(xpath = "//a[@id='mega-menu-1']/span[text()='More']")
    private WebElement buttonMore;

    @FindBy(xpath = "//a/h5[text()='Careers']")
    private WebElement buttonCareer;

    public StartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void openMainPage() {
        driver.get("https://useinsider.com/");
        System.out.println("Page was loaded");
        buttonMore.click();
        buttonCareer.click();

    }

}
