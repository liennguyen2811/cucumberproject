
package stepDefinitions;

import cucumber.TestContext;
import cucumber.api.java.en.Then;
import enums.Context;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pageObjects.LibraryDetailPage;

public class LibraryDetailSteps {
    WebDriver driver;
    LibraryDetailPage libraryDetailPage;
    TestContext testContext;

    public LibraryDetailSteps(TestContext context) {
        testContext = context;
        libraryDetailPage = testContext.getPageObjectManager().getLibraryDetailPage();
    }
    @Then("^Library Detail page displays$")
    public void library_Detail_page_displays() {
        libraryDetailPage.isBilltoboxAPIsTextDisplayed();
    }
    @Then("^Verify the order details$")
    public void verify_the_order_details() {
        String apiName = (String)testContext.scenarioContext.getContext(Context.API_NAME);
        Assert.assertTrue(libraryDetailPage.getNameAPIBillToBox().equals(apiName));
    }

}