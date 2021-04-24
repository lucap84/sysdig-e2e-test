package com.sysdig.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjects.HomePage;

public class LoginTest extends BaseTest{

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginTest.class);
    private HomePage homePage;

    @BeforeMethod
    public void setUp() {
        this.homePage = new HomePage(getDriverInstance());
        homePage.launchPage();
    }


    @Test(dataProvider = "credentialsProvider")
    public void testLogin(String username, String password, boolean isErrorMessageShowed, boolean isSuccessfulLogin) {
        LOGGER.info("testLogin...start");
        // GIVEN page loaded and not empty credentials
        if (username == null && password == null) return; // with empty credentials we skip the test
        // WHEN
        homePage.login(username, password);

        // THEN
        Assert.assertEquals(homePage.isLoginErrorDisplayed(), isErrorMessageShowed, "if the credentials are wrong an error message should be showed.");
        Assert.assertEquals(homePage.isLoggedIn(), isSuccessfulLogin, "if the credentials are correct we should see the logged in page");

        // TODO could be improved testing that only syntactically correct credentials cause a login network calls
        LOGGER.info("testLogin...done");
    }

    @DataProvider(name = "credentialsProvider")
    public Object[][] credentialsProvider() {
        return new Object[][] {
                {"username1@mail.com", "password", true, false}, // syntactically valid, wrong credentials
                {"username2", "password", false, false}, // syntactically NOT valid: this should not trigger any login request
                {"username2@mail.com", "", false, false}, // syntactically  NOT valid: this should not trigger any login request
                {"", "password123", false, false}, // syntactically  NOT valid: this should not trigger any login request
                {System.getProperty("usr"), System.getProperty("psw"), false, true} // this should be valid credentials passed as test input
        };
    }
}
