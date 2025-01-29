package tests;

import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

public class CareersTest {
    private HomePage homePage;
    private CareersPage careersPage;
    private JobListingPage jobListingPage;
    private QualityAssurancePage qaPage;

    @BeforeMethod
    public void setUp() {
        WebDriver driver = DriverManager.getWebDriver();
        homePage = new HomePage(driver);
        careersPage = new CareersPage(driver);
        qaPage = new QualityAssurancePage(driver);
        jobListingPage = new JobListingPage(driver);
    }

    @Test
    public void testCareersPageAndJobFilter() throws InterruptedException {

        // Step 1: Navigate to homepage and check is opened
        homePage.navigateTo("https://useinsider.com/");
        homePage.acceptCookie();
        Assert.assertTrue(homePage.isHomePageOpened(), "Home page is not opened");

        // Step 2: Navigate to Careers Page and check is opened
        homePage.goToCareersPage();
        Assert.assertTrue(careersPage.isCareersPageOpened(), "Careers page is not opened");
        Assert.assertTrue(careersPage.isSectionsDisplayed(), "Some career sections are missing");

        // Step 3: Navigate to QA Page and check is opened
        qaPage.navigateTo("https://useinsider.com/careers/quality-assurance/");

        // Step 4: Go to QA job listings
        qaPage.clickSeeAllQAJobs();

        // Step 5: Filter and validate job details
        jobListingPage.filterJobs("Istanbul, Turkey", "Quality Assurance");
        Thread.sleep(2000);
        Assert.assertTrue(jobListingPage.areJobsDisplayed(), "No jobs found after filtering");

        Assert.assertTrue(jobListingPage.areAllJobsCorrectlyFiltered(), "Filtered jobs do not match criteria");

        // Step 6: Click a job and validate Lever Application page
        jobListingPage.clickViewRole();
        Assert.assertTrue(jobListingPage.isRouteApplicationFormPage(), "Not redirected to Lever application form");
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
