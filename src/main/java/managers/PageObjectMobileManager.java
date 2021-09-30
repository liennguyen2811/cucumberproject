package managers;

import pageObjects.*;

public class PageObjectMobileManager {
    private B2BLoginPage b2bLoginPage;
    private B2BDashboardPage b2bDashboardPage;
    private FintekLoginPage fintekLoginPage;

    public B2BLoginPage getB2BLoginPage() {
        return (b2bLoginPage == null) ? b2bLoginPage = new B2BLoginPage() :b2bLoginPage;
    }

    public B2BDashboardPage getb2bDashboardPage() {
        return (b2bDashboardPage == null) ? b2bDashboardPage = new B2BDashboardPage() :b2bDashboardPage;
    }
    public FintekLoginPage getFintekLoginPage() {
        return (fintekLoginPage == null) ? fintekLoginPage = new FintekLoginPage() :fintekLoginPage;
    }

}