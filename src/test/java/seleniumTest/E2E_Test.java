package seleniumTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class E2E_Test {
    private static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","chromedriver89");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://developer-portal-client-uat.nxt.uat.unifiedpost.com/");


        driver.findElement(By.id("username")).sendKeys("liennth7@unifiedpost.com");;
        driver.findElement(By.id("password")).sendKeys("P@ssword1");
        driver.findElement(By.id("kc-login")).click();

    }

}