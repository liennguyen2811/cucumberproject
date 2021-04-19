package pageObjects;

import managers.FileReaderManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(how = How.ID, using = "username")
    private WebElement useName;
    @FindBy(how = How.ID, using = "password")
    private WebElement password;
    @FindBy(how = How.ID, using = "kc-login")
    private WebElement loginButton;
    public void navigateToLogInPage() {
        driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
    }
    public void enterUsername(String user_name) {
        useName.sendKeys(user_name);
    }
    public void enterPassword(String passWord) {
        password.sendKeys(passWord);
    }
    public void clickLoginButton() {
        loginButton.click();
    }
}
