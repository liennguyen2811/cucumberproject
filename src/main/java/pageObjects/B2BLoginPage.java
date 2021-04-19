package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class B2BLoginPage {

    private final WebDriver driver;

    public B2BLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(how = How.ID, using = "com.unifiedpost.billtobox.dev:id/filledButtonLayout")
    private WebElement login_and_getstart;
    @FindBy(how = How.ID, using = "com.unifiedpost.billtobox.dev:id/belgium_logo")
    private WebElement country_selector_begium;
    @FindBy(how = How.ID, using = "com.unifiedpost.billtobox.dev:id/edittextEmail")
    private WebElement useName;
    @FindBy(how = How.ID, using = "com.unifiedpost.billtobox.dev:id/edittextPassword")
    private WebElement password;
    @FindBy(how = How.ID, using = "com.unifiedpost.billtobox.dev:id/filledBackgroundText")
    private WebElement loginButton;

    public void loginB2B(String user_name) {
        login_and_getstart.click();
        country_selector_begium.click();
        useName.sendKeys(user_name);
        password.sendKeys();
        loginButton.click();
    }
}
