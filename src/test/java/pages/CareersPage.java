package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CareersPage extends BasePage {

    private final By locationsSection = By.xpath("//section[@id='career-our-location']");
    private final By teamsSection = By.xpath("//section[@id='career-find-our-calling']");
    private final By lifeAtInsiderSection = By.xpath("//section[@data-id='a8e7b90']");

    public CareersPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCareersPageOpened() {
        return getUrl().contains("/careers");
    }

    public boolean isSectionsDisplayed() {
        return isElementDisplayed(locationsSection) &&
                isElementDisplayed(teamsSection) &&
                isElementDisplayed(lifeAtInsiderSection);
    }
}
