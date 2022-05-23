package com.insider.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;
import java.util.Set;

public class JobsPage {

    private WebDriver driver;

    @FindBy(xpath = "//div[contains(@class,'position-list-item-wrapper')]")
    public List<WebElement> listPositions;


    public JobsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void checkJobs() throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 1000);");
        Thread.sleep(5000);
        for (WebElement el : listPositions) {
            String jobName = el.findElement(By.tagName("p")).getText();
            System.out.println("JobName=" + jobName);
            Assert.assertTrue(jobName.contains("Quality Assurance"));
            String department = el.findElement(By.tagName("span")).getText();
            System.out.println("Department=" + department);
            Assert.assertTrue(department.contains("Quality Assurance"));
            String place = el.findElement(By.tagName("div")).getText();
            System.out.println("Place=" + place);
            Assert.assertTrue(place.contains("Istanbul, Turkey"));
        }
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", listPositions.get(0).findElement(By.tagName("a")));
        switchTabs();
        Assert.assertTrue(driver.getTitle().contains("Quality Assurance"));
    }

    private void switchTabs() {
        String currentHandle = null;
        try {
            final Set<String> handles = driver.getWindowHandles();
            if (handles.size() > 1) {
                currentHandle = driver.getWindowHandle();
            }
            if (currentHandle != null) {
                for (final String handle : handles) {
                    driver.switchTo().window(handle);
                }
            }
        } catch (Exception e) {
            System.out.println("Switching tabs failed");
        }
    }
}
