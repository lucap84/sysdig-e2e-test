package com.sysdig.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjects.HomePage;

public class ForgotPasswordTest extends BaseTest{

    private static final Logger LOGGER = LoggerFactory.getLogger(ForgotPasswordTest.class);
    private HomePage homePage;

    @BeforeMethod
    public void setUp() {
        this.homePage = new HomePage(getDriverInstance());
        homePage.launchPage();
    }

    @Test
    public void testGoToForgotPasswordSection() {
        LOGGER.info("testGoToForgotPasswordSection...start");
        // GIVEN page loaded
        homePage.isHomePageDisplayed();
        // WHEN
        homePage.gotoForgotPassword();

        // THEN
        Assert.assertTrue(homePage.isForgotPasswordDisplayed(), "The forgot password form should be displayed");
        LOGGER.info("testGoToForgotPasswordSection...done");
    }

    @Test(dataProvider = "usernamesProvider")
    public void testForgotPassword(String username, boolean isForgotPasswordEmailSent) {
        LOGGER.info("testForgotPassword...start");
        // GIVEN
        homePage.isHomePageDisplayed();

        // WHEN
        homePage.gotoForgotPassword();
        homePage.fillOutForgotPasswordForm(username);
        homePage.submitForgotPassword();

        // THEN
        Assert.assertEquals(homePage.isForgotPasswordFeedbackDisplayed(), isForgotPasswordEmailSent,
                "The forgot password email should be sent only for existing users.");

        if (!isForgotPasswordEmailSent) {
            homePage.goBackToLogin();
            Assert.assertTrue(homePage.isHomePageDisplayed(), "The login page should be visible.");
        }
        LOGGER.info("testForgotPassword...done");
    }

    @DataProvider(name = "usernamesProvider")
    public Object[][] credentialsProvider() {
        return new Object[][]{
                {"", false}, // syntactically incorrect
                {"username", false}, // syntactically incorrect
                {"inexistent.account@mail.com", false}, // syntactically correct, account not present: email sent
                {"existing.account@mail.com", true}, // syntactically correct, account present: email sent
        };
    }
}
