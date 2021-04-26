package pageObjects;

public class LibraryPage {

    public static final String WELCOME_LIBRARY_TEXT = "//h1[text()='Welcome to the API Library']";
    public static final String BILLTOBOX_API = "//mat-card-title[text()='Billtobox APIs']";
    public static final String LIBRARY_TAP = "//span[text()=' Library']";

    WebAbstractPage web;
    public LibraryPage (WebAbstractPage web) {
        this.web = web;
    }

    public boolean isWelcomeLibraryTextDisplayed() {
        web.waitToElementVisible(WELCOME_LIBRARY_TEXT);
        return web.isElementDisplayed(WELCOME_LIBRARY_TEXT);
    }
    public void clickLibraryTap(){
        web.waitToElementVisible(LIBRARY_TAP);
        web.clickToElement(LIBRARY_TAP);
    }

    public String getLiraryName() {
        web.waitToElementVisible(BILLTOBOX_API);
        return web.getTextElement(BILLTOBOX_API);
    }

    public void clickAPIBillToBox(){
        web.waitToElementVisible(BILLTOBOX_API);
        web.clickToElement(BILLTOBOX_API);
    }

}
