package pageObjects;

import common.TestConfig;

public class LoginPage{

    public static final String EMAIL_TEXTBOX = "//input[@name='username']";
    public static final String PASSWORD_TEXTBOX = "//input[@name='password']";
    public static final String LOGIN_BUTTON = "//input[@id='kc-login']";

    WebAbstractPage web;
    public LoginPage (WebAbstractPage web) {
        this.web = web;
    }

    public void enterUsernamePassword(String username, String password){
        web.openUrl(TestConfig.appURL);
        web.maximumBrowser();
        web.waitToElementVisible(EMAIL_TEXTBOX);
        web.sendKeyboardToElement(EMAIL_TEXTBOX,username);
        web.waitToElementVisible(PASSWORD_TEXTBOX);
        web.sendKeyboardToElement(PASSWORD_TEXTBOX, password);
    }
    public void clickLogInButton(){
        web.waitToElementVisible(LOGIN_BUTTON);
        web.clickToElement(LOGIN_BUTTON);
    }

}
