package pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BasePage {

    private static final Logger LOGGER = LoggerFactory.getLogger(BasePage.class);
    private static final long DEFAULT_WAIT_TIME = 20L;

    private WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    protected void launchPage(String url) {
        LOGGER.info("Launching page: " + url);
        getDriver().manage().window().maximize();
        getDriver().get(url); // the method will block until the load is complete
    }

    protected void clearInputAndSendKeys(WebElement inputElement, String textToSet) {
        WebElement input = this.waitForElementDisplayed(inputElement);
        input.clear();
        input.sendKeys(textToSet);
    }

    public WebElement waitForElementDisplayed(WebElement element) {
        return waitForElementDisplayed(element, DEFAULT_WAIT_TIME);
    }

    public WebElement waitForElementDisplayed(WebElement element, long timeout) {
        WebDriverWait wait = new WebDriverWait(this.driver, timeout);
        wait.until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    public boolean isElementDisplayed(WebElement element) {
        return isElementDisplayed(element, DEFAULT_WAIT_TIME);
    }

    public boolean isElementDisplayed(WebElement element, long timeout) {
        boolean isDisplayed = false;
        try {
            isDisplayed = this.waitForElementDisplayed(element, timeout) != null;
        } catch (NoSuchElementException | StaleElementReferenceException | TimeoutException e) {
            // assume the element is not displayed.
            LOGGER.info("Error during element.isDisplayed(): " + e.getMessage());
        }
        return isDisplayed;
    }
}