# Sysdig Technical Test

## Test requirements
```
The exercise consists in write automated tests against app.sysdigcloud.com, with the goal of testing that webpage. With the following requirements:
* We don't want you to spend more than 2 hours in the exercise. 
* Write the tests in Java.
* Use the Selenium library
* Document everything in a README file.
* Document also possible improvements if more time was available for this task.
```

## Technical description

This is a Selenium test suite focused on `http://app.sysdigcloud.com` page.

* Language: `Java 1.8`
* Library to interact with the browser: `Selenium 3.x`
* Other used libraries: `TestNG 6.x`, `sl4j 1.7.x`

I used a DriverFactory to encapsulate the complexity of driver creation and giving the possibility
to select different type of browsers (now are included Chrome and Firefox).

I used the PageObject pattern to interact with the page, incapsulate any future change on selectors and making the test code more readable.


### Features tested:
* Home page loading
* Login
* Forgot password
    * !!!NOTE!!!: I assumed a requirement: a forgot password email should be sent only for existing users.
      Because of this I expect **one test will fail** when test forgot password with an inexistent account email.
```
      [ERROR]   ForgotPasswordTest.testForgotPassword:47 The forgot password email should be sent only for existing users. expected [false] but found [true]
```

### Configure
* Maven installed and configured
* Put desired WebDrivers into `src/main/resources/drivers` (at least `chromedriver`)
* (optional) Configure the suites to run into `src/test/resources/suites/testng.xml`


### How to run

* Ensure to have correctly configured the project 
* `mvn clean test`

#### Options:
* To test the "happy path" on login page, pass valid credentials with `-Dusr='<username>' -Dpsw='<password>'`
* Choose browser adding `-Dbrowser=<chrome|firefox>` (deafult `chrome`) 
* Run headless adding `-Dheadless`
* If you want disable any suite, configure `suites/testng.xml`

Example: `mvn clean test -Dbrowser=chrome -Dheadless`

## Possible improvements

Unfortunately 2 hours was not enough to fully test that page.

Improvements to curren tests:
* Add assertions for invalid credential syntax
* Test also with different viewports simulating mobile traffic
* Test the other types of login: oauth, saml and openID
* Test the switch region dropdown menu
* Add JavaDoc and a better logging if you run it headless
