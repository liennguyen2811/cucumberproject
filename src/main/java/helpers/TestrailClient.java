package helpers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import testrail.APIClient;
import testrail.APIException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestrailClient {

    private static final String TEST_RAIL_URL = "pasword ne";
    private static final String TEST_RAIL_USER = "username ne
    private static final String TEST_RAIL_KEY = "APIkey ne";

    private APIClient testrailClient;
    private String projectId = "22";


    public TestrailClient() {
        this.testrailClient = new APIClient(TEST_RAIL_URL);
        testrailClient.setUser(TEST_RAIL_USER);
        testrailClient.setPassword(TEST_RAIL_KEY);
    }

    private JSONObject getPlan(String planId) throws IOException, APIException, APIException {
        return (JSONObject) testrailClient.sendGet("get_plan/" + planId);
}

    private JSONArray getTests(String runId) throws IOException, APIException {
        return (JSONArray) testrailClient.sendGet("get_tests/" + runId);
    }

    public void sendTestResultInRun(String runId, String caseId, String statusId, String comment, String defectId) throws IOException, APIException {
        Map<String, String> testValues = new HashMap<>();
        testValues.put("status_id", statusId);
        testValues.put("comment", comment);
        testValues.put("defects", defectId);
        testrailClient.sendPost("add_result_for_case/" + runId + "/" + caseId, testValues);
    }

    public void sendTestResultInPlan(String planId, String caseId, String statusId, String comment, String defectId) throws IOException, APIException {
        ArrayList<String> runsForCaseInPlan = getRunsForCaseInTestPlan(planId, caseId);
        if (runsForCaseInPlan.isEmpty()) {
            System.out.println("C" + caseId + " is not part of the plan with id: " + planId);
        }
        else {

            for (String runId : getRunsInTestPlan(planId)) {
                sendTestResultInRun(runId, caseId, statusId, comment, defectId);
            }
        }
    }

    private ArrayList<String> getRunsInTestPlan(String planId) throws IOException, APIException {
        JSONArray entries = (JSONArray) (getPlan(planId).get("entries"));
        ArrayList<String> runs = new ArrayList<>();
        for (Object entry: entries) {
            JSONArray planRuns =(JSONArray) ((JSONObject) entry).get("runs");
            String id = String.valueOf(((JSONObject) planRuns.get(0)).get("id"));
            runs.add(id);
        }
        return runs;
    }

    private String getTestIdOfCaseFromRun(String caseId, String runId) throws IOException, APIException {
        String testId = null;
        JSONArray tests = getTests(runId);
        for (Object test : tests) {
            if (String.valueOf(((JSONObject) test).get("case_id")).equals(caseId)) {
                testId = String.valueOf(((JSONObject) test).get("id"));
                break;
            }
        }
        return testId;
    }

    private ArrayList<String> getRunsForCaseInTestPlan(String planId, String caseId) throws IOException, APIException {
        ArrayList<String> runs = new ArrayList<>();
        for (String runId : getRunsInTestPlan(planId)) {
            if (isCasePartOfRun(caseId, runId)) {
                runs.add(runId);
            }
        }
        return runs;
    }

    private boolean isCasePartOfRun(String caseId, String runId) throws IOException, APIException {
        boolean isPart = false;
        for(Object test : getTests(runId)) {
            if(String.valueOf(((JSONObject) test).get("case_id")).equals(caseId)) {
                isPart = true;
                break;
            }
        }
        return isPart;
    }
}
