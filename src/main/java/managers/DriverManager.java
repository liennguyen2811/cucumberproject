package managers;

import common.TestConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import models.BrowserstackConfig;
import models.GridConfig;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.browserstack.local.Local;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class DriverManager {
    public static WebDriver driverRunForMobileOrBrowser;
    public TestConfig.DriverHub driverHub;
    public GridConfig grid;
    public BrowserstackConfig browserStack;
    static HashMap<String, HashMap<String, String>> existingDevice;
    private static Local localInstance;

    public DriverManager() {
        driverHub = TestConfig.getHub();
        grid = new GridConfig();
        browserStack = new BrowserstackConfig();
        TestConfig.initEnvironment();
        existingDevice = TestConfig.existingDevice;
    }

    public static WebDriver getDriver() {
        return driverRunForMobileOrBrowser;
    }

    public WebDriver createDriver() {
        switch (driverHub) {
           case GRID: driverRunForMobileOrBrowser = setGridBrowser(TestConfig.getDriverType());
                break;
            case BROWSERSTACK:
                return driverRunForMobileOrBrowser = createBrowserstackBrowser(TestConfig.getTestNameForBrowserStack());
            default:
                return driverRunForMobileOrBrowser = createLocalDriver(TestConfig.getDriverType());
        }
        return driverRunForMobileOrBrowser;
    }

    private WebDriver createRemoteDriver() {
        throw new RuntimeException("RemoteWebDriver is not yet implemented");
    }

    private WebDriver createLocalDriver(TestConfig.DriverType driverType) {
        setDriversPath();
        switch (driverType) {
            case FIREFOX : driverRunForMobileOrBrowser = new FirefoxDriver();
                break;
            case CHROME :
                driverRunForMobileOrBrowser = new ChromeDriver();
                break;
            case INTERNETEXPLORER : driverRunForMobileOrBrowser = new InternetExplorerDriver();
                break;
            case EDGE:driverRunForMobileOrBrowser =new EdgeDriver();
                break;
            case ANDROID: driverRunForMobileOrBrowser = setAndroidDriver();
                break;
            case IOS: driverRunForMobileOrBrowser = setIOSDriver();
                break;
            default:
            return new ChromeDriver();
        }
        return driverRunForMobileOrBrowser;
    }
    public WebDriver setAndroidDriver() {
        String buildPath = setBuildPath();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", existingDevice.get("samsung_s6").get("device_name"));
        capabilities.setCapability("platformName", existingDevice.get("samsung_s6").get("platform_name"));
        capabilities.setCapability(MobileCapabilityType.UDID, existingDevice.get("samsung_s6").get("device_name"));
        capabilities.setCapability("app", buildPath);
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("newCommandTimeout", 3000);
        capabilities.setCapability(MobileCapabilityType.NO_RESET, false);
        try {
            return new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public WebDriver setIOSDriver() {
        String buildPath = setBuildPath();
        MutableCapabilities capabilities = new MutableCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, existingDevice.get("iphone_7").get("device_name"));
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, existingDevice.get("iphone_7").get("platform_name"));
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,"13.4");
        capabilities.setCapability(MobileCapabilityType.NO_RESET, false);
        capabilities.setCapability(MobileCapabilityType.UDID, existingDevice.get("iphone_7").get("udid"));
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, existingDevice.get("iphone_7").get("automation_name"));
        capabilities.setCapability("app", buildPath);
        capabilities.setCapability("autoAcceptAlerts", "true");
        capabilities.setCapability("newCommandTimeout","3000");
        try {
            return new IOSDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
    private String setBuildPath() {
        String workingDir = System.getProperty("user.dir");
        String appPath;
        if (TestConfig.getDriverType().toString().contentEquals("ANDROID")) {
            // appPath = workingDir + "\\src\\test\\app\\android\\" + existingDevice.get("samsung_s6").get("app_path");
            appPath = workingDir + "//src//test//app//android//" + existingDevice.get("samsung_s6").get("app_path");
        } else {
            // appPath = workingDir + "\\src\\test\\app\\ios\\" + existingDevice.get("iphone_7").get("app_path");
            appPath = workingDir + "/src/test/app/ios/" + existingDevice.get("iphone_7").get("app_path");
        }
        return appPath;
    }

    private void setDriversPath() {
        if (SystemUtils.IS_OS_WINDOWS) {
            System.setProperty("webdriver.chrome.driver", "./src/bin/windows/chromedriver.exe");
            System.setProperty("webdriver.gecko.driver", "./src/bin/windows/geckodriver.exe");
            System.setProperty("webdriver.ie.driver", "./src/bin/windows/IEDriverServer.exe");
            System.setProperty("webdriver.edge.driver", "./src/bin/windows/MicrosoftWebDriver.exe");
        } else if (SystemUtils.IS_OS_LINUX) {
            System.setProperty("webdriver.chrome.driver", "./src/bin/linux/chromedriver");
            System.setProperty("webdriver.gecko.driver", "./src/bin/linux/geckodriver");
        } else {
            System.setProperty("webdriver.chrome.driver", "./src/bin/macOS/chromedriver");
            System.setProperty("webdriver.gecko.driver", "./src/bin/macOS/geckodriver");
        }
    }
    public static void setupLocal() throws Exception {
        localInstance = new Local();
        Map<String, String> options = new HashMap<String, String>();
        options.put("key", "NTdNshs8XaxvsTdSJBzy");
        localInstance.start(options);
    }
    public static void tearDownLocal() throws Exception {
        localInstance.stop();
    }
    private DesiredCapabilities get_bs_capabilities(String testName) {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        if(TestConfig.getDriverType().toString().contentEquals("IOS")|| TestConfig.getDriverType().toString().contentEquals("ANDROID")) {
        try {
            setupLocal();
        } catch (Exception e) {
            e.printStackTrace();
        }
        capabilities.setCapability("name", testName);
        capabilities.setCapability("browserstack.user", "minhtran38");
        capabilities.setCapability("browserstack.key", "NTdNshs8XaxvsTdSJBzy");
        capabilities.setCapability("browserstack.local", true);
        capabilities.setCapability("project", "Second Java Project");
        capabilities.setCapability("build", "Java IOS Local");
        capabilities.setCapability("name", "local_test");
        capabilities.setCapability("app", "bs://a76bc99d12c96a50ed753da54b69488fc04b131c");
        capabilities.setCapability("autoAcceptAlerts", "true");
        //capabilities.setCapability("realMobile", "true");
        capabilities.setCapability("os_version", "13");
        capabilities.setCapability("device", "iPhone 11");
        } else {

            capabilities.setCapability("name", testName);
            capabilities.setCapability("browserName", "iPhone");
            capabilities.setCapability("device", "iPhone 8 Plus");
            capabilities.setCapability("realMobile", "true");
            capabilities.setCapability("os_version", "11");

        }
        return capabilities;
    }
    private Capabilities get_grid_capabilities(TestConfig.DriverType driverType) {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        switch (driverType) {
            case CHROME:
                return DesiredCapabilities.chrome();
            case FIREFOX:
                capabilities.setCapability("marionette", false);
                return capabilities.firefox();
            case IE:
                return DesiredCapabilities.internetExplorer();
            case SAFARI:
                return DesiredCapabilities.safari();
            case EDGE:
                return DesiredCapabilities.edge();
            default:
                return DesiredCapabilities.chrome();
        }
    }
    private WebDriver setGridBrowser(TestConfig.DriverType driverType) {
        return new RemoteWebDriver(grid.hub, get_grid_capabilities(driverType));
    }
    private WebDriver createBrowserstackBrowser(String testName) {
        if (TestConfig.getDriverType().toString().contentEquals("IOS")) {
            return new IOSDriver(browserStack.hub, get_bs_capabilities(testName));
        } else if (TestConfig.getDriverType().toString().contentEquals("ANDROID")) {
            return new AndroidDriver(browserStack.hub, get_bs_capabilities(testName));
        } else {
            return new RemoteWebDriver(browserStack.hub, get_bs_capabilities(testName));
        }
    }
}