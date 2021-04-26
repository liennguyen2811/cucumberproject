
package stepDefinitions;

import cucumber.TestContext;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import enums.Context;
import managers.PageObjectWebManager;
import pageObjects.LibraryPage;

public class LibrarySteps {
    LoginSteps loginSteps;
    LibraryPage libraryPage;
    TestContext testContext;
    //WebAbstractPage webAbstractPage;
    //PageObjectWebManager pageObjectWebManager = new PageObjectWebManager(webAbstractPage);

    public LibrarySteps(TestContext context) {
        testContext = context;
        PageObjectWebManager pageObjectWebManager = new PageObjectWebManager(loginSteps.getWebAbstractPage());
        libraryPage = pageObjectWebManager.getLibraryPage();
    }

//    public LibrarySteps(TestContext context) {
//        testContext = context;
//        libraryPage = testContext.getPageObjectWebManager().getLibraryPage();
//    }
    @Then("^Library page displays$")
    public boolean library_page_displays(){
        return libraryPage.isWelcomeLibraryTextDisplayed();
    }
    @When("^User choose the API to view$")
    public void user_choose_the_API_to_view()  {
        String apiName = libraryPage.getLiraryName();
        testContext.scenarioContext.setContext(Context.API_NAME, apiName);
    }
    @When("^User clicks Library tap$")
    public void user_clicks_Library_Tab() {
        libraryPage.clickLibraryTap();
    }

    @When("^User clicks API name$")
    public void user_clicks_API_name() {
        libraryPage.clickAPIBillToBox();
    }
}