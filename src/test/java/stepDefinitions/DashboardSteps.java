package stepDefinitions;

import cucumber.TestContext;
import cucumber.api.java.en.Given;
import pageObjects.DashboardPage;

public class DashboardSteps {
    DashboardPage dashboardPage;
    TestContext testContext;

    public DashboardSteps(TestContext context) {
        testContext = context;
        dashboardPage = testContext.getPageObjectManager().getDashboardPage();
    }
    @Given("^User is on Dashboard page$")
    public void user_is_on_Dashboard_page()  {
        dashboardPage.goToDashboard();
    }

    @Given("^User clicks Library tab$")
    public void user_clicks_Library_tab()  {
        dashboardPage.goToLibrary();
    }

}
