package cucumber;
import common.Fixtures;
import common.TestConfig;
import cucumber.api.Scenario;
import cucumber.runtime.ScenarioImpl;
import gherkin.formatter.model.Result;
import helpers.BrowserstackHelper;
import helpers.TestrailClient;
import managers.DriverControl;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import testrail.APIException;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class CucumberTestListener {
    private TestrailClient tc;
    private WebDriver driver;
    private String bsSessionId;


    public void onTestStart() {
        driver = DriverControl.getDriver();
        if (TestConfig.getHub() == TestConfig.DriverHub.BROWSERSTACK) {
            bsSessionId = ((RemoteWebDriver) driver).getSessionId().toString();
        }
    }

    public void onTestSkipped(Scenario scenario) {
        driver = DriverControl.getDriver();
        String bsResultPage = "";

        if (TestConfig.getHub() == TestConfig.DriverHub.BROWSERSTACK) {
            bsResultPage = buildBrowserStackMessage(bsSessionId);
            BrowserstackHelper.failBsStatus(bsSessionId);
        }

        Fixtures.TearDown.close(driver);

        if (TestConfig.useTestRail()) {
            String comment = buildTestrailMessage(bsResultPage, buildConfTraceMessage(scenario));
            sendResultToTestrail(scenario, comment, TestConfig.TestRailStatus.SKIPPED.getStatusId());
        }
    }

    public void onTestFailure(Scenario scenario) {
        driver = DriverControl.getDriver();
        String bsResultPage = "";

        if (TestConfig.getHub() == TestConfig.DriverHub.BROWSERSTACK) {
            bsResultPage = buildBrowserStackMessage(bsSessionId);
            BrowserstackHelper.failBsStatus(bsSessionId);
        }
        Fixtures.TearDown.close(driver);

        if (TestConfig.useTestRail()) {
            String comment = buildTestrailMessage(bsResultPage, buildErrorTraceMessage(scenario));
            sendResultToTestrail(scenario, comment, TestConfig.TestRailStatus.FAILED.getStatusId());
        }
    }

    public void onTestSuccess( Scenario scenario) {
        driver = DriverControl.getDriver();

        String bsResultPage = "";

        if (TestConfig.getHub() == TestConfig.DriverHub.BROWSERSTACK) {
            bsResultPage = buildBrowserStackMessage(bsSessionId);
        }
        sendResultToTestrail(scenario, bsResultPage, TestConfig.TestRailStatus.PASSED.getStatusId());
    }

    private String buildBrowserStackMessage(String bsSessionId) {
        return "Browserstack result page" + System.lineSeparator()
                + "=============" + System.lineSeparator()
                + BrowserstackHelper.getBsUrl(bsSessionId) + System.lineSeparator();
    }

    private String buildErrorTraceMessage(Scenario scenario) {
        return "Error trace" + System.lineSeparator()
                + "=============" + System.lineSeparator() + getErrorTrace(scenario);

    }

    private String buildConfTraceMessage(Scenario scenario) {
        return "Configuration error trace" + System.lineSeparator()
                + "=============" + System.lineSeparator() + getConfErrorTrace(scenario);
    }

    private String buildTestrailMessage(String browserstackPage, String errorTrace){
       return browserstackPage + errorTrace;
    }

    private void sendResultToTestrail(Scenario scenario, String comment, String status) {
        if (TestConfig.useTestRail()) {
            String planId = TestConfig.getTestrailPlanId();

            if (planId != null) {
                sendResultToTestrailPlan(getTestCaseId(scenario), comment, planId, status);
            } else {
                String runId = TestConfig.getTestrailRunId();

                if (runId != null) {
                    sendResultToTestrailRun(getTestCaseId(scenario), comment, runId, status);
                } else {
                    System.out.println("Neither test plan id nor test run id have been provided");
                }
            }

        }
    }
    private static String getErrorTrace(Scenario scenario) {
        String FAILED_COMMENT = "";
        Field field = FieldUtils.getField(((ScenarioImpl) scenario).getClass(), "stepResults", true);
        field.setAccessible(true);
        try {
            ArrayList<Result> results = (ArrayList<Result>) field.get(scenario);
            System.out.println("" + results.toString());
            for (Result result : results) {
                if (result.getError() != null){
                    FAILED_COMMENT = scenario.getId() + "---" + result.getError().getMessage();
                }
            }
        } catch (Exception e) {
            FAILED_COMMENT = "Error while logging error" + e;
        }
        return FAILED_COMMENT;

    }

    private String getConfErrorTrace(Scenario scenario) {
        String confErrorTrace = null;
        Field field = FieldUtils.getField(((ScenarioImpl) scenario).getClass(), "stepResults", true);
        field.setAccessible(true);
        try {
            ArrayList<Result> results = (ArrayList<Result>) field.get(scenario);
            for (Result result : results) {
                if (result.getError() != null)
                    confErrorTrace = "Error Scenario:  " + scenario.getId() + "  " + result.getError();
            }
        } catch (Exception e) {
            confErrorTrace ="Error while logging error  " + e;
        }
        return confErrorTrace;
    }

    private String getTestCaseId(Scenario scenario) {
        String caseId = "";
        System.out.println(scenario.getSourceTagNames());
        for (String s : scenario.getSourceTagNames()) {
            if (s.contains("TestRail")) {
                String[] res = s.split("(\\(.*?)");
                caseId = res[1].substring(0, res[1].length() - 1); // Removing the last parenthesis
            }
        }
        return caseId;
    }

    private void sendResultToTestrailRun(String testCaseId, String comment, String runId, String statusId) {
        tc = new TestrailClient();
        try {
            tc.sendTestResultInRun(runId, testCaseId, statusId, comment, null);
        } catch (IOException | APIException e) {
            e.printStackTrace();
        }
    }

    private void sendResultToTestrailPlan(String testCaseId, String comment, String planId, String statusId) {
        tc = new TestrailClient();
        try {
            tc.sendTestResultInPlan(planId, testCaseId, statusId, comment, null);
        } catch (IOException | APIException e) {
            e.printStackTrace();
        }
    }

}