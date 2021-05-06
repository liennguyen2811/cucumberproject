package common;

import managers.EnvironmentDataManager;

import java.util.HashMap;

public final class TestConfig {
    public static String testNameForBrowserstack;
    public static HashMap<String, String> environmentUrls;
    public static HashMap<String, HashMap<String, String>> existingUsers;
    public static HashMap<String, HashMap<String, String>> existingDevice;
    public static HashMap<String, HashMap<String, String>> androidElements;
    public static HashMap<String, HashMap<String, String>> iOSElements;
    public static String appURL;
    public static String getEnvironment() {
        String envEnvironment = System.getProperty("testEnvironment");
        return (envEnvironment != null) ? envEnvironment : "test";
    }

    public static DriverHub getHub() {
        String envHub = System.getProperty("hub");

        envHub = (envHub != null) ? envHub : "local";

        switch (envHub) {
            case "browserstack":
                return DriverHub.BROWSERSTACK;
            case "grid":
                return DriverHub.GRID;
            case "local":
                return DriverHub.LOCAL;
            default:
                return DriverHub.LOCAL;
        }
    }

    public static DriverType getDriverType() {
        String envBrowser = System.getProperty("testBrowser");
      //ios
         envBrowser = (envBrowser != null) ? envBrowser : "android";
//         envBrowser = (envBrowser != null) ? envBrowser : "ios";

        switch (envBrowser.toLowerCase()) {
            case "firefox":
                return DriverType.FIREFOX;
            case "chrome":
                return DriverType.CHROME;
            case "ie":
                return DriverType.IE;
            case "edge":
                return DriverType.EDGE;
            case "android":
                return DriverType.ANDROID;
            case "ios":
                return DriverType.IOS;
            default:
                return DriverType.SAFARI;
        }
    }

    public static String getTestrailRunId() {
        String testRunId = System.getProperty("testBrowser");
        //ios
        testRunId = (testRunId != null) ? testRunId : "12182";
        return testRunId;
        //return System.getProperty("testRunId");
    }

    public static String getTestrailPlanId() {
        return System.getProperty("testPlanId");
    }

    public static Boolean useTestRail() {
        String envUseTestrail = System.getProperty("useTestrail");
        envUseTestrail = (envUseTestrail != null) ? envUseTestrail : "yes";

        return envUseTestrail.equals("yes");
    }

    public enum DriverHub {BROWSERSTACK, GRID, LOCAL}


    public enum DriverType {FIREFOX, CHROME, IE, EDGE,INTERNETEXPLORER, SAFARI,ANDROID,IOS}

    public enum TestRailStatus {
        PASSED("1"),
        BLOCKED("2"),
        UNTESTED("3"),
        RETEST("4"),
        FAILED("5"),
        SKIPPED("6");

        private final String statusId;

        TestRailStatus(String statusId) {
            this.statusId = statusId;
        }

        public String getStatusId(){
           return statusId;
        }
    }

    public static void setTestrailTestNameForBrowserStack(String testNameForBrowserStack){
        testNameForBrowserstack = testNameForBrowserStack;
    }

    public static String getTestNameForBrowserStack() {
        return testNameForBrowserstack;
    }

    public static void initEnvironment(){
        EnvironmentDataManager environmentData = new EnvironmentDataManager(getEnvironment());
        environmentUrls = environmentData.environmentUrls;
        existingUsers = environmentData.existingUsers;
        existingDevice = environmentData.existingDevice;
        appURL = environmentUrls.get("base_url");
        androidElements = environmentData.androidElements;
        iOSElements = environmentData.iOSElements;
    }
}