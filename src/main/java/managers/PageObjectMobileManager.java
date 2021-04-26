package managers;

import pageObjects.*;

public class PageObjectMobileManager {
    private B2BLoginPage b2bLoginPage;

    public B2BLoginPage getB2BLoginPage() {
        return (b2bLoginPage == null) ? b2bLoginPage = new B2BLoginPage() :b2bLoginPage;
    }

}