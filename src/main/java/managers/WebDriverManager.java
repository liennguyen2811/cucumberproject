package managers;

import enums.DriverType;
import enums.EnvironmentType;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class WebDriverManager {
    private WebDriver driver;
    private static DriverType driverType;
    private static EnvironmentType environmentType;
    static HashMap<String, HashMap<String, String>> existingDevice;

    public WebDriverManager() {
        driverType = FileReaderManager.getInstance().getConfigReader().getBrowser();
        environmentType = FileReaderManager.getInstance().getConfigReader().getEnvironment();
        TestConfig.initEnvironment();
        existingDevice = TestConfig.existingDevice;
    }

    public WebDriver getDriver() {
        if(driver == null) driver = createDriver();
        return driver;
    }

    private WebDriver createDriver() {
        switch (environmentType) {
            case LOCAL : driver = createLocalDriver();
                break;
            case REMOTE : driver = createRemoteDriver();
                break;
        }
        return driver;
    }

    private WebDriver createRemoteDriver() {
        throw new RuntimeException("RemoteWebDriver is not yet implemented");
    }

    private WebDriver createLocalDriver() {
        setDriversPath();
        switch (driverType) {
            case FIREFOX : driver = new FirefoxDriver();
                break;
            case CHROME :
                driver = new ChromeDriver();
                break;
            case INTERNETEXPLORER : driver = new InternetExplorerDriver();
                break;
            case EDGE:driver =new EdgeDriver();
                break;
            case ANDROID: driver = setAndroidDriver();
                break;
            case IOS: driver = setIOSDriver();
            default:
            return new ChromeDriver();
        }
        //if(FileReaderManager.getInstance().getConfigReader().getBrowserWindowSize()) driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(FileReaderManager.getInstance().getConfigReader().getImplicitlyWait(), TimeUnit.SECONDS);
        return driver;
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
        //capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, existingDevice.get("iphone_7").get("platform_version"));
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,"13.4");
        capabilities.setCapability(MobileCapabilityType.NO_RESET, false);
        capabilities.setCapability(MobileCapabilityType.UDID, existingDevice.get("iphone_7").get("udid"));
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, existingDevice.get("iphone_7").get("automation_name"));
        capabilities.setCapability("app", buildPath);
//        capabilities.setCapability("autoGrantPermissions", "true");
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
        if (TestConfig.getBrowser().toString().contentEquals("ANDROID")) {
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

    public void quitDriver() {
        driver.close();
        driver.quit();
    }

}