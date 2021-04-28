package stepDefinitions;

import com.browserstack.local.Local;
import com.cucumber.listener.Reporter;
import common.Fixtures;
import common.TestConfig;
import cucumber.TestContext;
import cucumber.api.Result;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import helpers.TestrailClient;
import managers.DriverManager;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import testrail.APIClient;
import testrail.APIException;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Hooks {
    TestContext testContext;
    WebDriver driverType;
    Local localInstance;
    String appURL;
    Scenario scenario;
    DriverManager driverManager;
    protected static HashMap<String, HashMap<String, String>> existingUsers;
    private static APIClient client = null;
    //private static String runId = getProperties().getProperty("runIdTestRail" );
    String runId = "R12182";
    private static final int FAIL_STATE = 5;
    private static final int SUCCESS_STATE = 1;
    private static final String SUCCESS_COMMENT = "This test passed with Selenium";
    private static final String FAILED_COMMENT = "This test failed with Selenium";

    @Rule
    public TestName testName = new TestName();
    TestrailClient testrailClient = new TestrailClient();

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
        //Fixtures.TearDownBrowserStacklocal.close(localInstance);
//        try {
//            System.out.println("if come here clear at after test");
//            driverManager.tearDownLocal();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } // to be close instance of browserstack, will refactor then move to fixture

        //testContext.getWebAbstractPage().close();
    }
    private void logResultToTestRail(Scenario scenario) {
        String caseId = "";
        System.out.println(scenario.getSourceTagNames());

        for (String s : scenario.getSourceTagNames()) {
            if (s.contains("TestRail" )) {

                String[] res = s.split("(\\(.*?)" );

                caseId = res[1].substring(0, res[1].length() - 1); // Removing the last parenthesis
            }
        }

        Map<String, Serializable> data = new HashMap<>();

        if (!scenario.isFailed()) {
            data.put("status_id", SUCCESS_STATE);
            data.put("comment", SUCCESS_COMMENT);

        } else {
            data.put("status_id", FAIL_STATE);
            data.put("comment", logError(scenario));
        }

        if (!caseId.equals("" )) {
            try {

                if (System.getenv("runIdTestRail" ) != null && System.getenv("runTestRailId" ).equals("" )) {
                    runId = "12182";
                }

                testrailClient.sendTestResultInRun("12182", "C223421", "statusId", "comment", "defectId");
            } catch (IOException | APIException e) {
                e.printStackTrace();
            }
        }
    }

    //   As per https://stackoverflow.com/a/58506614/6654475
    private static String logError(Scenario scenario) {
        try {
            Class clasz = ClassUtils.getClass("cucumber.runtime.java.JavaHookDefinition$ScenarioAdaptor" );
            Field fieldScenario = FieldUtils.getField(clasz, "scenario", true);
            if (fieldScenario != null) {

                fieldScenario.setAccessible(true);
                Object objectScenario = fieldScenario.get(scenario);

                Field fieldStepResults = objectScenario.getClass().getDeclaredField("stepResults" );
                fieldStepResults.setAccessible(true);

                ArrayList<Result> results = (ArrayList<Result>) fieldStepResults.get(objectScenario);
                for (Result result : results) {
                    if (result.getError() != null) {
                        return FAILED_COMMENT + "\n" + result.getErrorMessage();
                    }
                }
            }

            return FAILED_COMMENT;

        } catch (IllegalAccessException | NoSuchFieldException | ClassNotFoundException e) {
            return FAILED_COMMENT;
        }
}}