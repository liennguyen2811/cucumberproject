package pageObjects;

public class B2BLoginPage extends MobileAbstractPage{
    public B2BLoginPage() {
        deviceElements = getDeviceElements("B2BLoginPage");
    }
    public void loginB2B(String user_name, String pass_word) {
        waitForVisibility(deviceElements.get("LOGIN_AND_START"));
        tapOnElement(deviceElements.get("LOGIN_AND_START"));
        waitForVisibility(deviceElements.get("BELGIUM_COUNTRY_SELECTOR"));
        tapOnElement(deviceElements.get("BELGIUM_COUNTRY_SELECTOR"));
        waitForVisibility(deviceElements.get("USER_NAME"));
        sendKeyToElement(deviceElements.get("USER_NAME"), user_name);
        waitForVisibility(deviceElements.get("PASSWORD"));
        sendKeyToElement(deviceElements.get("PASSWORD"), pass_word);

    }

    public void clickLogInButton(){
        waitForVisibility(deviceElements.get("LOGIN_BUTTON"));
        tapOnElement(deviceElements.get("LOGIN_BUTTON"));
    }

    public void chooseCountry(String country) {
        tapOnGetStartedButton();
        if (getAppName().contains("billtobox")) {
            if(isElementDisplayed(deviceElements.get("LOGO_" + country.toUpperCase() + "_IMAGE")))
                tapOnElement(deviceElements.get("LOGO_" + country.toUpperCase() + "_IMAGE"));
        }
    }

    public void tapOnGetStartedButton(){
        if(isElementDisplayed(deviceElements.get("LOGIN_AND_START")))
            tapOnElement(deviceElements.get("LOGIN_AND_START"));
    }

    public void logIn(String email, String password) {
        sleepInSecond(3);
        waitForVisibility(deviceElements.get("EMAIL_ADDRESS"));
        sendKeyAction((deviceElements.get("EMAIL_ADDRESS")), email);
        if (checkKeyBoard()) {
            hideKeyboard();
        }
        waitForVisibility(deviceElements.get("PASSWORD"));
        sendKeyAction((deviceElements.get("PASSWORD")), password);
        hideKeyboard();
        if (isAndroid()) {
            waitForVisibility(deviceElements.get("LOGIN_BUTTON"));
            tapOnElement(deviceElements.get("LOGIN_BUTTON"));
        }
    }


}
