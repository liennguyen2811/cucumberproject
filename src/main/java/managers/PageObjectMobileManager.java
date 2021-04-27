package managers;

import pageObjects.*;

public class PageObjectMobileManager {
    private B2BLoginPage b2bLoginPage;
    private FintekLoginPage fintekLoginPage;

    public B2BLoginPage getB2BLoginPage() {
        return (b2bLoginPage == null) ? b2bLoginPage = new B2BLoginPage() :b2bLoginPage;
    }
    public FintekLoginPage getFintekLoginPage() {
        return (fintekLoginPage == null) ? fintekLoginPage = new FintekLoginPage() :fintekLoginPage;
    }

}