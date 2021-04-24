package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    public static final long TIMEOUT_SM = 5L;
    public static final String HOME_PAGE_URL = "http://app.sysdigcloud.com/";

    public static final String HOME_PAGE_LOGGED_OUT = "body.ember-application.login";
    public static final String HOME_PAGE_LOGGED_IN = "body div[data-test='user-avatar']";
    public static final String HOME_LOGO = "img.login__logo";

    public static final String LOGIN_FORM = "form.login__form";
    public static final String LOGIN_FORM_USERNAME_INPUT = "input[data-test='login-username']";
    public static final String LOGIN_FORM_PASSWORD_INPUT = "input[data-test='login-password']";
    public static final String LOGIN_FORM_SUBMIT = "button[data-id='submit-login']";
    public static final String LOGIN_ERROR_MESSAGE = "p[data-test='login-error-message']";

    public static final String FORGOT_PASSWORD_LINK = "a[data-test='link-forgot-password']";
    public static final String GOBACK_TO_LOGIN_LINK = "div a.login__link";
    public static final String FORGOT_PASSWORD_USERNAME = "input[data-test='forgot-password-username']";
    public static final String FORGOT_PASSWORD_FEEDBACK_MESSAGE = "div p.login__feedback-message";
    public static final String FORGOT_PASSWORD_SUBMIT = "button[data-test='submit-forgot-password']";


    private WebDriver driver;

    @FindBy(css = HOME_PAGE_LOGGED_OUT)
    private WebElement homePage;

    @FindBy(css = HOME_PAGE_LOGGED_IN)
    private WebElement homePageLoggedIn;

    @FindBy(css = HOME_LOGO)
    private WebElement homeLogo;

    @FindBy(css = LOGIN_FORM)
    private WebElement loginForm;

    @FindBy(css = LOGIN_FORM_USERNAME_INPUT)
    private WebElement inputUsername;

    @FindBy(css = LOGIN_FORM_PASSWORD_INPUT)
    private WebElement inputPassword;

    @FindBy(css = LOGIN_FORM_SUBMIT)
    private WebElement btnSubmit;

    @FindBy(css = LOGIN_ERROR_MESSAGE)
    private WebElement pErrorMessage;

    @FindBy(css = FORGOT_PASSWORD_LINK)
    private WebElement linkForgotPassword;

    @FindBy(css = FORGOT_PASSWORD_USERNAME)
    private WebElement inputForgotPasswordUsername;

    @FindBy(css = FORGOT_PASSWORD_FEEDBACK_MESSAGE)
    private WebElement divForgotPasswordFeedbackMessage;

    @FindBy(css = FORGOT_PASSWORD_SUBMIT)
    private WebElement btnForgotPasswordSubmit;

    @FindBy(css = GOBACK_TO_LOGIN_LINK)
    private WebElement linkGoBackToLogin;


    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this); // With this we can use annotations @FindBy to find WebElement
    }

    public void launchPage() {
        super.launchPage(HOME_PAGE_URL);
    }

    public boolean isHomePageDisplayed() {
        return isElementDisplayed(homePage) && isElementDisplayed(homeLogo) && isElementDisplayed(btnSubmit);
    }

    public boolean isLoginFormDisplayed() {
        return isElementDisplayed(loginForm);
    }

    public boolean isLoginErrorDisplayed() {
        return isElementDisplayed(pErrorMessage, TIMEOUT_SM);
    }

    public void fillOutLoginForm(String username, String password) {
        this.clearInputAndSendKeys(inputUsername, username);
        this.clearInputAndSendKeys(inputPassword, password);
    }

    public void login(String username, String password) {
        this.fillOutLoginForm(username, password);
        btnSubmit.click();
    }

    public void gotoForgotPassword() {
        linkForgotPassword.click();
    }

    public void fillOutForgotPasswordForm(String username) {
        this.clearInputAndSendKeys(inputForgotPasswordUsername, username);
    }

    public void submitForgotPassword() {
        btnForgotPasswordSubmit.click();
    }

    public void goBackToLogin() {
        linkGoBackToLogin.click();
    }

    public boolean isForgotPasswordDisplayed() {
        return isElementDisplayed(inputForgotPasswordUsername, TIMEOUT_SM);
    }

    public boolean isForgotPasswordFeedbackDisplayed() {
        return isElementDisplayed(divForgotPasswordFeedbackMessage, TIMEOUT_SM);
    }

    public boolean isLoggedIn() {
        return isElementDisplayed(homePageLoggedIn, TIMEOUT_SM);
    }

}
