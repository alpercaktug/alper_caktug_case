package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class HomePage extends BasePage {

    private final By companyMenu = By.xpath("//a[contains(text(), 'Company')]");
    private final By careersLink = By.xpath("//a[contains(text(), 'Careers')]");
    private final By acceptAllCookieButton = By.xpath("//a[@id='wt-cli-accept-all-btn']");


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isHomePageOpened() {
        return getTitle().contains("Insider");
    }

    public void acceptCookie(){
        clickElement(acceptAllCookieButton);
    }

    public void goToCareersPage() {
        clickElement(companyMenu);
        clickElement(careersLink);
    }
}
