
package stepDefinitions;

import cucumber.TestContext;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import enums.Context;
import org.openqa.selenium.WebDriver;
import pageObjects.LibraryPage;

public class LibrarySteps {
    WebDriver driver;
    LibraryPage libraryPage;
    TestContext testContext;


    public LibrarySteps(TestContext context) {
        testContext = context;
        libraryPage = testContext.getPageObjectManager().getLibraryPage();
    }
    @Then("^Library page displays$")
    public boolean library_page_displays(){
        return libraryPage.isWelcomeLibraryTextDisplayed();
    }
    @When("^User choose the API to view$")
    public void user_choose_the_API_to_view()  {
        String apiName = libraryPage.getLiraryName();
        testContext.scenarioContext.setContext(Context.API_NAME, apiName);
    }
    @When("^User clicks API name$")
    public void user_clicks_API_name() {
        libraryPage.clickAPIBillToBox();
    }
}