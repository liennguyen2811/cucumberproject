package common;

import com.browserstack.local.Local;
import managers.DriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.awt.*;
public class Fixtures {
    public static class SetUp {

        public static WebDriver initBrowserOrAppMobile(String applicationURL) {
            DriverManager driverManager = new DriverManager();
            WebDriver driverType;
            if(TestConfig.getDriverType().toString().contentEquals("IOS")|| TestConfig.getDriverType().toString().contentEquals("ANDROID")){
                driverType = driverManager.createDriver();
            }
            else {
                driverType = driverManager.createDriver();
                maximizeWindow(driverType);
                driverType.get(applicationURL);
            }
            return driverType;
        }
    }

    public static class TearDown {

        public static void close(WebDriver driverType) {
            System.out.println("if come here 1" + driverType.toString());
            if (driverType != null) {
                System.out.println("if come here 2" + driverType.toString());
                driverType.quit();
            }
        }
    }
    public static class TearDownBrowserStacklocal {

        public static void close(Local localInstance) throws Exception {
            System.out.println("if come here 1" + localInstance.toString());
            if (localInstance != null) {
                System.out.println("if come here 2");
                localInstance.stop();
                System.out.println("if come here 3");
            }
        }
    }
    private static void maximizeWindow(WebDriver browser) {
        try {
            browser.manage().window().maximize();
        } catch (WebDriverException ex) {
            try {
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Dimension screenResolution = new Dimension((int)
                        toolkit.getScreenSize().getWidth(), (int)
                        toolkit.getScreenSize().getHeight());
                browser.manage().window().setSize(screenResolution);
            } catch (WebDriverException e) {
                ((JavascriptExecutor)browser).executeScript("window.moveTo(0,0); window.resizeTo(screen.width, screen.height);");
            }
        }
    }
}
