package cucumber;

import managers.DriverManager;
import managers.PageObjectMobileManager;
import managers.PageObjectWebManager;
import pageObjects.WebAbstractPage;

public class TestContext {
    private DriverManager driverManager;
    private PageObjectMobileManager pageObjectMobileManager;
    private PageObjectWebManager pageObjectWebManager;
    public ScenarioContext scenarioContext;
    public WebAbstractPage webAbstractPage;

    public TestContext(){
        scenarioContext = new ScenarioContext();
        //webAbstractPage = new WebAbstractPage("CHROME"); it should be init at login page if following the flow to test
        pageObjectWebManager = new PageObjectWebManager(webAbstractPage);
        pageObjectMobileManager = new PageObjectMobileManager();
    }
    public DriverManager getWebDriverManager() {
        return driverManager;
    }

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
