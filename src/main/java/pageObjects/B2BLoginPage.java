package pageObjects;

public class B2BLoginPage extends MobileAbstractPage{
    public static final String LOGIN_AND_START = "//*[contains(@resource-id,'filledButtonLayout')]";
    public static final String BELGIUM_COUNTRY_SELECTOR = "//*[contains(@resource-id,'belgium_logo')]";
    public static final String USER_NAME = "//*[contains(@resource-id,'edittextEmail')]";
    public static final String PASSWORD = "//*[contains(@resource-id,'edittextPassword')]";
    public static final String LOGIN_BUTTON = "//*[contains(@resource-id,'filledBackgroundText')]";


    public void loginB2B(String user_name, String pass_word) {
        waitForVisibility(LOGIN_AND_START);
        tapOnElement(LOGIN_AND_START);
        waitForVisibility(BELGIUM_COUNTRY_SELECTOR);
        tapOnElement(BELGIUM_COUNTRY_SELECTOR);
        waitForVisibility(USER_NAME);
        sendKeyToElement(USER_NAME, user_name);
        waitForVisibility(PASSWORD);
        sendKeyToElement(PASSWORD, pass_word);
    }

    public void clickLogInButton(){
        waitForVisibility(LOGIN_BUTTON);
        tapOnElement(LOGIN_BUTTON);
    }
}
