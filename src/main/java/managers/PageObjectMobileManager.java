package managers;

import pageObjects.*;

public class PageObjectManager {

   // private WebDriver driver;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private LibraryPage libraryPage;
    private LibraryDetailPage libraryDetailPage;
    private B2BLoginPage b2bLoginPage;

//    public PageObjectManager(WebDriver driver) {
//        this.driver = driver;
//        System.out.println("Lien driver at PageObjectManager " + driver.hashCode());
//    }

    public LoginPage getLoginPage() {
        return (loginPage == null) ? loginPage = new LoginPage() : loginPage;
    }
    public DashboardPage getDashboardPage() {
        return (dashboardPage == null) ? dashboardPage = new DashboardPage() : dashboardPage;
    }
    public LibraryPage getLibraryPage() {
        return (libraryPage == null) ? libraryPage = new LibraryPage() : libraryPage;
    }
    public LibraryDetailPage getLibraryDetailPage() {
        return (libraryDetailPage == null) ? libraryDetailPage = new LibraryDetailPage() : libraryDetailPage;
    }
    public B2BLoginPage getB2BLoginPage() {
        return (b2bLoginPage == null) ? b2bLoginPage = new B2BLoginPage() :b2bLoginPage;
    }
}