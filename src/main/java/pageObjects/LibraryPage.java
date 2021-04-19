package pageObjects;

import managers.EnvironmentDataManager;
import managers.FileReaderManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import testDataTypes.Customer;

import java.io.IOException;

public class LibraryPage {

    private final WebDriver driver;

    public LibraryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = "//h1[text()='Welcome to the API Library']")
    private WebElement welcomeLibraryText;
    @FindBy(how = How.XPATH, using = "//mat-card-title[text()='Billtobox APIs']")
    private WebElement billtoboxAPIs;

    public boolean isWelcomeLibraryTextDisplayed() {
        return welcomeLibraryText.isDisplayed();
    }

    public String getLiraryName() {
        return billtoboxAPIs.getText();
    }

    public void clickAPIBillToBox(){
        billtoboxAPIs.click();
    }

    public void checkCustomer(String customerName) {
        Customer customer = FileReaderManager.getInstance().getJsonReader().getCustomerByName(customerName);
        fill_PersonalDetails(customer);
    }

    public void checkgetUrl() {
        String url = FileReaderManager.getInstance().getJsonReader().getEnvironment_urls();
    }

    public void checkMapTest(String test) {
        try {
            EnvironmentDataManager environmentDataManager = new EnvironmentDataManager(test);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void fill_PersonalDetails(Customer customer) {
        System.out.println("Check read json " + customer.firstName);
        System.out.println("Check read json " + customer.phoneNumber.mob);
    }
}
