package stepDefinitions;

import com.browserstack.local.Local;
import com.cucumber.listener.Reporter;
import common.Fixtures;
import common.TestConfig;
import cucumber.CucumberTestListener;
import cucumber.TestContext;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

public class Hooks {
    TestContext testContext;
    WebDriver driverType;
    Local localInstance;
    String appURL;
    protected static HashMap<String, HashMap<String, String>> existingUsers;

    @Rule
    public TestName testName = new TestName();
    CucumberTestListener cucumberTestListener = new CucumberTestListener();

    public Hooks(TestContext context) {
        testContext = context;
        TestConfig.initEnvironment();
    }

    @Before
    public void BeforeSteps() {
        Reporter.assignAuthor("Lien Nguyen");
        appURL = TestConfig.appURL;
        driverType = Fixtures.SetUp.initBrowserOrAppMobile(appURL);
        existingUsers = TestConfig.existingUsers;
    }

    @After
    public void AfterSteps(Scenario scenario) {
        Fixtures.TearDown.close(driverType);
        logResultToTestRail(scenario);
    }
    private void logResultToTestRail(Scenario scenario) {
        if (!scenario.isFailed()) {
            cucumberTestListener.onTestSuccess(scenario);
        } else {
            cucumberTestListener.onTestFailure(scenario);
        }
        }
    }


