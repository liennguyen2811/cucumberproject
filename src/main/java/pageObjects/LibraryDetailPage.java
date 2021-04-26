package pageObjects;

public class LibraryDetailPage {
    WebAbstractPage web;
    public LibraryDetailPage (WebAbstractPage web) {
        this.web = web;
    }

//    private final WebDriver driver;
//
//    public LibraryDetailPage(WebDriver driver) {
//        this.driver = driver;
//        PageFactory.initElements(driver, this);
//    }
//    @FindBy(how = How.XPATH, using = "//div[text()='Billtobox APIs']")
//    private WebElement BilltoboxAPIs;
//
//    public boolean isBilltoboxAPIsTextDisplayed(){
//        return BilltoboxAPIs.isDisplayed();
//    }
//    public String  getNameAPIBillToBox(){
//        return BilltoboxAPIs.getText();
//    }

}
