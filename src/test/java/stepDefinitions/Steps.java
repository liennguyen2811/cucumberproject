
package stepDefinitions;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class Steps {
    WebDriver driver;
    @Given("^User is on LogIn Page$")
    public void user_is_on_Home_Page() {
        System.setProperty("webdriver.chrome.driver","chromedriver89");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://developer-portal-client-uat.nxt.uat.unifiedpost.com/");
    }

    @When("^User enters UserName and Password$")
    public void user_enters_UserName_and_Password()  {
        driver.findElement(By.id("username")).sendKeys("liennth7@unifiedpost.com");;
        driver.findElement(By.id("password")).sendKeys("P@ssword1");
        driver.findElement(By.id("kc-login")).click();
    }
}