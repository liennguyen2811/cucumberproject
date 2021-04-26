
package stepDefinitions;

import cucumber.TestContext;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import managers.PageObjectWebManager;
import pageObjects.LoginPage;
import pageObjects.WebAbstractPage;

public class LoginSteps {
    LoginPage loginPage;
    TestContext testContext;
    public static WebAbstractPage webAbstractPage = new WebAbstractPage("CHROME");
    PageObjectWebManager pageObjectWebManager = new PageObjectWebManager(webAbstractPage);

    public static WebAbstractPage getWebAbstractPage() {
        return webAbstractPage;
    }
    public LoginSteps(TestContext context) {
        testContext = context;
        loginPage = pageObjectWebManager.getLoginPage();
    }
        @Given("^User is on login Page$")
        public void b2b_user_is_on_login_page()  {

        }
         @Then("^Verify user login successfully$")
        public void b2b_verify_user_login_successfully() {

        }
         @And("^User enters \"([^\"]*)\" and \"([^\"]*)\"$")
        public void b2b_user_enters_something_and_something(String username, String password)  {
        loginPage.enterUsernamePassword(username,password);
        }
        @And("^User hit login button$")
         public void b2b_user_hit_login_button(){
        loginPage.clickLogInButton();
    }
}