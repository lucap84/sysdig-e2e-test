package core.driver;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.*;

public class DriverCreatorListener implements ITestListener, ISuiteListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(DriverCreatorListener.class);

    @Override
    public void onStart(ITestContext context) {
        LOGGER.info("Initializing WebDriver");
        DriverFactory.getDriver();
    }

    @Override
    public void onFinish(ITestContext context) {
        WebDriver driver = DriverFactory.getDriver();
        if (driver != null) {
            LOGGER.info("Closing the browser.");
            driver.quit();
        }
    }

    @Override
    public void onStart(ISuite suite) {}

    @Override
    public void onFinish(ISuite suite) {}

    @Override
    public void onTestStart(ITestResult result) {}

    @Override
    public void onTestSuccess(ITestResult result) {}

    @Override
    public void onTestFailure(ITestResult result) {}

    @Override
    public void onTestSkipped(ITestResult result) {}

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}

}
