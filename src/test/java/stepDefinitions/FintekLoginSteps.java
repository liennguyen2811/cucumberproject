
package stepDefinitions;

import cucumber.TestContext;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import pageObjects.FintekLoginPage;

public class FintekLoginSteps {
    FintekLoginPage fintekloginPage;
    TestContext testContext;

    public FintekLoginSteps(TestContext context) {
        testContext = context;
        fintekloginPage = testContext.getPageObjectMobileManager().getFintekLoginPage();
    }
    @Given("^Fintek User is on login Page$")
    public void fintek_user_is_on_login_page() {

    }

    @Then("^Fintek Verify user login successfully$")
    public void fintek_verify_user_login_successfully() {

    }

    @And("^Fintek User select language$")
    public void fintek_user_select_language() {
        fintekloginPage.clickOKButtonOnPopUp();
        //fintekloginPage.selectLanguageInList("English");

    }

    @And("^Fintek User select country then hit Next button$")
    public void fintek_user_select_country_then_hit_next_button()  {
        fintekloginPage.selectCountryInList("Estonia");
        fintekloginPage.clickNext();
    }

    @And("^Fintek User select method login \"([^\"]*)\"$")
    public void fintek_user_select_method_login_something(String username) {
        fintekloginPage.selectMethodLogin(username);
    }


    @And("^Fintexk User enters \"([^\"]*)\" and \"([^\"]*)\"$")
    public void fintexk_user_enters_something_and_something(String username, String password) {
        fintekloginPage.logInByUserName(username, password);
    }


//    mobileLoginPage.clickOKButtonOnPopUp();
//    mobileLoginPage.selectLanguageInList("English");
//    mobileLoginPage.selectCountryInList("Estonia");
//    mobileLoginPage.clickNext();
//    //2. Select method login
//    mobileLoginPage.selectMethodLogin("Username");
//    //3. Input user and password
//    mobileLoginPage.logInByUserName(username, password);
}