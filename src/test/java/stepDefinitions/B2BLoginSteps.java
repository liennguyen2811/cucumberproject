
package stepDefinitions;

import cucumber.TestContext;
import org.openqa.selenium.WebDriver;
import pageObjects.LoginPage;

public class B2BLoginSteps {
    WebDriver driver;
    LoginPage loginPage;
    TestContext testContext;

    public B2BLoginSteps(TestContext context) {
        testContext = context;
        loginPage = testContext.getPageObjectManager().getLoginPage();
    }
}