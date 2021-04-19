package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {
    private final WebDriver driver;
    private Object Customer;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(how = How.XPATH, using = "//span[text()=' Dashboard']")
    private WebElement dashboard;
    @FindBy(how = How.XPATH, using = "//span[text()=' Library']")
    private WebElement library;

    public void goToDashboard() {
        dashboard.isDisplayed();
    }
    public void goToLibrary() {
        library.click();
    }
    public boolean isDashboardTapDisplayed(){
        return dashboard.isDisplayed();

    }

}
