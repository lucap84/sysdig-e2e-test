package com.sysdig.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.HomePage;

public class HomePageLoadingTest extends BaseTest{

    private static final Logger LOGGER = LoggerFactory.getLogger(HomePageLoadingTest.class);
    private HomePage homePage;

    @BeforeClass
    public void setUp() {
        this.homePage = new HomePage(getDriverInstance());
        homePage.launchPage();
    }

    @Test
    public void testHomePageShouldBeLoaded() {
        LOGGER.info("testHomePageShouldBeLoaded...start");
        // GIVEN page loaded
        // THEN
        Assert.assertTrue(homePage.isHomePageDisplayed(), "The Sysdig home page should be correctly loaded.");
        Assert.assertTrue(homePage.isLoginFormDisplayed(), "The page should show a login form.");
        LOGGER.info("testHomePageShouldBeLoaded...done");
    }

}
