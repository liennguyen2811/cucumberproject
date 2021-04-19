package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LibraryDetailPage {

    private final WebDriver driver;

    public LibraryDetailPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(how = How.XPATH, using = "//div[text()='Billtobox APIs']")
    private WebElement BilltoboxAPIs;

    public boolean isBilltoboxAPIsTextDisplayed(){
        return BilltoboxAPIs.isDisplayed();
    }
    public String  getNameAPIBillToBox(){
        return BilltoboxAPIs.getText();
    }

}
