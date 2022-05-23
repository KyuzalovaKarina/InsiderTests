package com.insider.tests;

import com.insider.BaseTest;
import com.insider.pages.CareerPage;
import com.insider.pages.JobsPage;
import com.insider.pages.StartPage;
import org.testng.annotations.Test;

public class FindJob extends BaseTest {

    @Test
    public void findJob() throws InterruptedException {
        StartPage startPage = new StartPage(driver);
        startPage.openMainPage();
        //wait
        CareerPage careerPage = new CareerPage(driver);
        careerPage.selectQAJobCategory();
        JobsPage jobsPage = new JobsPage(driver);
        jobsPage.checkJobs();
    }
}
