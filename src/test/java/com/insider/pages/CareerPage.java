package com.insider.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class CareerPage {
    private WebDriver driver;

    @FindBy(xpath = "//a[text()='See all teams']")
    private WebElement buttonSeeAllTeams;

    @FindBy(xpath = "//a/h3[text()='Quality Assurance']")
    private WebElement buttonQA;

    @FindBy(xpath = "//a[text()='See all QA jobs']")
    private WebElement buttonAllJobs;

    @FindBy(xpath = "//section[@id='career-our-location']")
    private WebElement blockLocations;

    @FindBy(xpath = "//section[@id='career-find-our-calling']")
    private WebElement blockTeams;

    @FindBy(xpath = "//section[@data-id='efd8002']")
    private WebElement blockLife;

    public CareerPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void selectQAJobCategory() {
        WebDriverWait waitForElement = new WebDriverWait(driver, Duration.ofSeconds(15));
        Assert.assertTrue(blockLife.isDisplayed());
        Assert.assertTrue(blockLocations.isDisplayed());
        Assert.assertTrue(blockTeams.isDisplayed());
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", buttonSeeAllTeams);
        js.executeScript("arguments[0].click()", buttonQA);
        js.executeScript("arguments[0].click()", buttonAllJobs);
    }
}
