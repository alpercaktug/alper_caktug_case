package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {

    private static WebDriver webDriver;

    // Singleton WebDriver instance
    public static WebDriver getWebDriver() {
        if (webDriver == null) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            options.addArguments("--start-maximized");
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver(options);
        }
        return webDriver;
    }

    public static void quitDriver() {
        if (webDriver != null) {
            webDriver.quit();
            webDriver = null;
        }
    }
}
