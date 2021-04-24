package com.sysdig.test;

import core.driver.DriverCreatorListener;
import core.driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;

@Listeners({DriverCreatorListener.class})
public class BaseTest {

    protected WebDriver getDriverInstance() {
        return DriverFactory.getDriver();
    }
}
