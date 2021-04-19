package managers;

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

    public static Driver getHub() {
        String envHub = System.getProperty("hub");

        envHub = (envHub != null) ? envHub : "local";

        switch (envHub) {
            case "browserstack":
                return Driver.BROWSERSTACK;
            case "grid":
                return Driver.GRID;
            case "local":
                return Driver.LOCAL;
            default:
                return Driver.LOCAL;
        }
    }

    public static Browser getBrowser() {
        String envBrowser = System.getProperty("testBrowser");
      //ios
        // envBrowser = (envBrowser != null) ? envBrowser : "android";
         envBrowser = (envBrowser != null) ? envBrowser : "ios";

        switch (envBrowser.toLowerCase()) {
            case "firefox":
                return Browser.FIREFOX;
            case "chrome":
                return Browser.CHROME;
            case "ie":
                return Browser.IE;
            case "edge":
                return Browser.EDGE;
            case "android":
                return Browser.ANDROID;
            case "ios":
                return Browser.IOS;
            default:
                return Browser.SAFARI;
        }
    }

    public static String getTestrailRunId() {
        return System.getProperty("testRunId");
    }

    public static String getTestrailPlanId() {
        return System.getProperty("testPlanId");
    }

    public static Boolean useTestRail() {
        String envUseTestrail = System.getProperty("useTestrail");
        envUseTestrail = (envUseTestrail != null) ? envUseTestrail : "yes";

        return envUseTestrail.equals("yes");
    }

    public enum Driver {BROWSERSTACK, GRID, LOCAL}


    public enum Browser {FIREFOX, CHROME, IE, EDGE, SAFARI,ANDROID,IOS}

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