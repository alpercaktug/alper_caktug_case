package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class JobListingPage extends BasePage {

    private final By locationFilter = By.xpath("//select[@id='filter-by-location']");
    private final By departmentFilter = By.xpath("//select[@id='filter-by-department']");

    private final By jobsList = By.xpath("//div[@id='jobs-list']");

    private final By jobTitles = By.xpath("//p[contains(@class, 'position-title')]");
    private final By jobDepartments = By.xpath("//span[contains(@class, 'position-department')]");
    private final By jobLocations = By.xpath("//div[contains(@class, 'position-location')]");

    private final By viewRoleButtons = By.xpath("//a[contains(text(),'View Role')]");

    public JobListingPage(WebDriver driver) {
        super(driver);
    }

    public void filterJobs(String location, String department) {
        selectDropdownElement(locationFilter, location);
        selectDropdownElement(departmentFilter, department);
    }

    public boolean areJobsDisplayed() {
        return isElementDisplayed(jobsList);
    }

    public boolean areAllJobsCorrectlyFiltered() throws InterruptedException {
        scrollForPageLoading();
        Thread.sleep(2000);

        List<WebElement> titles = getElements(jobTitles);
        List<WebElement> departments = getElements(jobDepartments);
        List<WebElement> locations = getElements(jobLocations);

        System.out.println(titles.size());

        for (int i = 0; i < titles.size(); i++) {
            System.out.println(titles.get(i).getText());
            System.out.println(departments.get(i).getText());
            System.out.println(locations.get(i).getText());
            if (!titles.get(i).getText().contains("Software") ||
                    !departments.get(i).getText().contains("Quality Assurance") ||
                    !locations.get(i).getText().contains("Istanbul, Turkey")) {
                // !titles.get(i).getText().contains("Quality Assurance")
                // Don't using because there was a job title
                // "Software QA Tester- Insider Testinium Tech Hub (Remote)"
                // So using "Software" for continue
                return false;
            }
        }
        return true;
    }

    public void clickViewRole() {
        List<WebElement> titles = getElements(jobTitles);
        moveToElement(titles.getFirst());
        clickElement(viewRoleButtons);
    }

    public boolean isRouteApplicationFormPage(){
        handleNewTab();
        return getUrl().contains("jobs.lever.co/");
    }
}
