
package stepDefinitions;

import cucumber.TestContext;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import pageObjects.B2BLoginPage;

public class B2BLoginSteps {
    B2BLoginPage b2bloginPage;
    TestContext testContext;

    public B2BLoginSteps(TestContext context) {
        testContext = context;
        b2bloginPage = testContext.getPageObjectMobileManager().getB2BLoginPage();
    }
    @Given("^B2B User is on login Page$")
    public void b2b_user_is_on_login_page()  {

    }
    @Then("^B2B Verify user login successfully$")
    public void b2b_verify_user_login_successfully() {

    }
    @And("^B2B User enters \"([^\"]*)\" and \"([^\"]*)\"$")
    public void b2b_user_enters_something_and_something(String username, String password)  {
    b2bloginPage.loginB2B(username,password);
    }
    @And("^B2B User hit login button$")
    public void b2b_user_hit_login_button(){
        b2bloginPage.clickLogInButton();
    }
}