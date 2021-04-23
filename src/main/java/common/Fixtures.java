package common;

import managers.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.awt.*;

public class Fixtures {
    public static class SetUp {

        public static WebDriver initBrowserOrAppMobile(String applicationURL) {
            WebDriverManager driverManager = new WebDriverManager();
            WebDriver driverType;
            if(TestConfig.getDriverType().toString().contentEquals("IOS")|| TestConfig.getDriverType().toString().contentEquals("ANDROID")){
                driverType = driverManager.createDriver();
                System.out.println("Lien get driver at initBrowserOrAppMobile---" + driverType.hashCode());
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
            if (driverType != null) {
                driverType.quit();
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
