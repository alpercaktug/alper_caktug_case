package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class QualityAssurancePage extends BasePage{

    private final By seeAllQAJobsButton = By.xpath("//a[contains(@href, 'careers/open-positions/?department=qualityassurance')]");

    public QualityAssurancePage(WebDriver driver) {
        super(driver);
    }

    public void clickSeeAllQAJobs(){
        clickElement(seeAllQAJobsButton);
    }
}
