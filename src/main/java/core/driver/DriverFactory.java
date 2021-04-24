package core.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(DriverFactory.class);
    private static final String BROWSER_FROM_BUILD_ARG = System.getProperty("browser");
    private static final String HEADLESS_BROWSER_FROM_BUILD_ARG = System.getProperty("headless");

    private static WebDriver driver;

    private static final String CHROME_DRIVER_PROPERTY_KEY = "webdriver.chrome.driver";
    private static final String GECKO_DRIVER_PROPERTY_KEY = "webdriver.gecko.driver";

    private static final String CHROME_DRIVER_PATH = "src/main/resources/drivers/chromedriver";
    private static final String GECKO_DRIVER_PATH = "src/main/resources/drivers/geckodriver";

    private static WebDriver getDriver(String browserType) {
        LOGGER.info("Creating browser driver...");
        switch (browserType) {
            case BrowserType.CHROME:
                System.setProperty(CHROME_DRIVER_PROPERTY_KEY, CHROME_DRIVER_PATH);
                ChromeOptions chromeOptions = new ChromeOptions();
                if (HEADLESS_BROWSER_FROM_BUILD_ARG != null) {
                    chromeOptions.addArguments("--headless");
                }
                driver = new ChromeDriver(chromeOptions);
                break;
            case BrowserType.FIREFOX:
                System.setProperty(GECKO_DRIVER_PROPERTY_KEY, GECKO_DRIVER_PATH);
                FirefoxOptions ffxOptions = new FirefoxOptions();
                if (HEADLESS_BROWSER_FROM_BUILD_ARG != null) {
                    ffxOptions.addArguments("--headless");
                }
                driver = new FirefoxDriver(ffxOptions);
                break;
            default:
                System.setProperty(CHROME_DRIVER_PROPERTY_KEY, CHROME_DRIVER_PATH);
                driver = new ChromeDriver();
                break;
        }
        LOGGER.info("...created");
        return driver;
    }

    public static WebDriver getDriver() {
        if (driver == null) return getDriver(BROWSER_FROM_BUILD_ARG != null ? BROWSER_FROM_BUILD_ARG : BrowserType.CHROME);
        return driver;
    }
}
