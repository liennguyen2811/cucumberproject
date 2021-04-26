package cucumber;

import managers.PageObjectMobileManager;
import managers.PageObjectWebManager;
import managers.DriverManager;
import pageObjects.WebAbstractPage;

public class TestContext {
    private DriverManager driverManager;
    private WebAbstractPage webAbstractPage;
    private PageObjectMobileManager pageObjectMobileManager;
    private PageObjectWebManager pageObjectWebManager;
    public ScenarioContext scenarioContext;

    public TestContext(){
        scenarioContext = new ScenarioContext();
//        webAbstractPage = new WebAbstractPage("CHROME");
//        pageObjectWebManager = new PageObjectWebManager(webAbstractPage);
    }
//    public WebDriverManager getWebDriverManager() {
//        return webDriverManager;
//    }
    public WebAbstractPage getWebAbstractPage() {
        return webAbstractPage;
    }

    public PageObjectMobileManager getPageObjectMobileManager() {
        return pageObjectMobileManager;
    }

    public PageObjectWebManager getPageObjectWebManager() {
        return pageObjectWebManager;
    }

    public ScenarioContext getScenarioContext() {
        return scenarioContext;
    }
}
