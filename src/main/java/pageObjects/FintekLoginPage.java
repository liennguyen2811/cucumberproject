package pageObjects;

public class FintekLoginPage extends MobileAbstractPage{
    public static final String LOGIN_AND_START = "//*[contains(@resource-id,'filledButtonLayout')]";
    public static final String OK_BUTTON= "//XCUIElementTypeButton[@name='OK']";
    public static final String COUNTRY_LANGUAGE_SCREEN_TITLE= "//XCUIElementTypeStaticText[@name='tvScreenTitle']";
    public static final String PICKER_LANGUAGE= "//XCUIElementTypeImage[@name='ic_down_arrow']/parent::*";
    public static final String DONE_BUTTON= "//*[@name='Done']";
    public static final String PICKER_LANGUAGE_TEXT= "//XCUIElementTypeStaticText[@name='tvLanguageSet']";
    public static final String SELECT_LANGUAGE_IN_LIST = "//XCUIElementTypePickerWheel";
    public static final String SELECT_COUNTRY_IN_LIST= "//XCUIElementTypeStaticText[@value='%s']";
    public static final String BUTTON_NEXT= "//XCUIElementTypeButton[@name='btnSave']";
    public static final String LOGIN_BUTTON= "//XCUIElementTypeButton[@name='btnLogin']";
    public static final String METHOD_USERNAME= "//XCUIElementTypeOther[@name='tovUsername']/XCUIElementTypeStaticText";
    public static final String USERNAME_TEXT= "//XCUIElementTypeStaticText[@name='tvUsername']";
    public static final String USERNAME_TEXTBOX= "//XCUIElementTypeTextField[@name='edtUsername']";
    public static final String USERNAME_ERROR= "//XCUIElementTypeStaticText[@name='tvUsernameError']";
    public static final String PASSWORD_TEXT= "//XCUIElementTypeStaticText[@name='tvPassword']";
    public static final String PASSWORD_TEXTBOX= "//XCUIElementTypeSecureTextField[@name='edtPassword']";
    public static final String PASSWORD_ERROR= "//XCUIElementTypeStaticText[@name='tvPasswordError']";
    public static final String SHOW_PASSWORD= "//XCUIElementTypeButton[@name='plash eye']";

    public void selectLanguageInList(String language) {
        scrollToElement(PICKER_LANGUAGE, "Up");
        waitForVisibility(PICKER_LANGUAGE);
        tapOnElement(PICKER_LANGUAGE);
        if (isAndroid()) {
            String xpath = String.format(SELECT_LANGUAGE_IN_LIST, language);
            waitForVisibility(xpath);
            tapOnElement(xpath);
        } else {
            waitForVisibility(SELECT_LANGUAGE_IN_LIST);
            sendKeyToElement(SELECT_LANGUAGE_IN_LIST, language);
            sendKeyToElement(SELECT_LANGUAGE_IN_LIST, language);
            sleepInSecond(1);
            tapOnElement(DONE_BUTTON);
        }
    }

    public void selectCountryInList(String country) {
        String xpath = String.format(SELECT_COUNTRY_IN_LIST, country);
        scrollToElement(xpath, "Down");
        waitForVisibility(xpath);
        tapOnElement(xpath);
    }

    public void clickNext() {
        waitForVisibility(BUTTON_NEXT);
        tapOnElement(BUTTON_NEXT);
    }

    public void selectMethodLogin(String authnMethod) {
        sleepInSecond(3);
        String xpath = METHOD_USERNAME;
        waitForVisibility(xpath);
        tapOnElement(xpath);
    }
    public void clickOKButtonOnPopUp() {
        try {
            waitForVisibility(OK_BUTTON);
            tapOnElement(OK_BUTTON);
        } catch (Exception ex) {
        }
    }

    public void logInByUserName(String Username, String Password) {
        waitForVisibility(USERNAME_TEXTBOX);
        tapOnElement(USERNAME_TEXTBOX);
        sendKeyToElement(USERNAME_TEXTBOX, Username);
        // sendKeyCode("xpath", USERNAME_TEXTBOX, Keys.ENTER);

        waitForVisibility(PASSWORD_TEXTBOX);
        tapOnElement(PASSWORD_TEXTBOX);
        sendKeyToElement(PASSWORD_TEXTBOX, Password);
        // sendKeyCode("xpath", PASSWORD_TEXTBOX, Keys.ENTER);

        hideKeyboard();
        waitForVisibility(LOGIN_BUTTON);
        tapOnElement(LOGIN_BUTTON);
    }

}
