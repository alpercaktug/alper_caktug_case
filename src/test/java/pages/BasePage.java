package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    private static final int TIMEOUT = 30;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public String getTitle(){
        return driver.getTitle();
    }

    public String getUrl(){
        return driver.getCurrentUrl();
    }

    public WebElement getElement(By locator) {
        return waitForElementToBeVisible(locator);
    }

    public List<WebElement> getElements(By locator) {
        return waitForElementsToBeVisible(locator);
    }

    public void clickElement(By locator) {
        waitForElementToBeClickable(locator).click();
    }

    public void moveToElement(WebElement element) {
       // WebElement element = waitForElementToBeVisible(locator);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public void scrollForPageLoading(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 500);");

    }

    public void handleNewTab() {
        String originalTab = driver.getWindowHandle();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(driver -> driver.getWindowHandles().size() > 1);

        Set<String> allTabs = driver.getWindowHandles();

        for (String tab : allTabs) {
            if (!tab.equals(originalTab)) {
                driver.switchTo().window(tab);
                System.out.println("Switched to new tab: " + driver.getTitle());
                break;
            }
        }
    }

    public String getText(By locator) {
        return waitForElementToBeVisible(locator).getText().trim();
    }

    public void enterText(By locator, String text) {
        waitForElementToBeVisible(locator).sendKeys(text);
    }

    public void selectDropdownElement(By locator, String text){
        WebElement dropdownElement = waitForElementToBeVisible(locator);
        Select select = new Select(dropdownElement);
        select.selectByVisibleText(text); // Select by visible text
    }

    public boolean isElementDisplayed(By locator) {
        return waitForElementToBeVisible(locator).isDisplayed();
    }

    protected WebElement waitForElementToBeVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected List<WebElement> waitForElementsToBeVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    protected WebElement waitForElementToBeClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
}
