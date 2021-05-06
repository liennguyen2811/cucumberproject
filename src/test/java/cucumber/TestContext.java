package cucumber;

import managers.DriverControl;
import managers.PageObjectMobileManager;
import managers.PageObjectWebManager;
import pageObjects.WebAbstractPage;

public class TestContext {
    private DriverControl driverControl;
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
    public DriverControl getWebDriverManager() {
        return driverControl;
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
