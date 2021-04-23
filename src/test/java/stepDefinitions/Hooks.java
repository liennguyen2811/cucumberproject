package stepDefinitions;

import com.cucumber.listener.Reporter;
import common.Fixtures;
import common.TestConfig;
import cucumber.TestContext;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

public class Hooks {
    TestContext testContext;
    WebDriver driverType;
    String appURL;
    protected static HashMap<String, HashMap<String, String>> existingUsers;
    public Hooks(TestContext context) {
        testContext = context;
    }

    @Before
    public void BeforeSteps() {
        Reporter.assignAuthor("Lien Nguyen");
        appURL = TestConfig.appURL;
        driverType = Fixtures.SetUp.initBrowserOrAppMobile(appURL);
        existingUsers = TestConfig.existingUsers;
        //testContext.getWebDriverManager().getDriver().get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
 /*What all you can perform here
 Starting a webdriver
 Setting up DB connections
 Setting up test data
 Setting up browser cookies
 Navigating to certain page
 or anything before the test
 */
    }

//    @After
//    public void AfterSteps() {
//        Fixtures.TearDown.close(driverType);
//    }

}