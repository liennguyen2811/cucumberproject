package managers;

import pageObjects.*;

public class PageObjectWebManager {
    private WebAbstractPage webAbstractPage;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private LibraryPage libraryPage;
    private LibraryDetailPage libraryDetailPage;

    public PageObjectWebManager(WebAbstractPage webAbstractPage) {
        this.webAbstractPage = webAbstractPage;
    }

    public LoginPage getLoginPage() {
        return (loginPage == null) ? loginPage = new LoginPage(webAbstractPage) : loginPage;
    }
    public DashboardPage getDashboardPage() {
        return (dashboardPage == null) ? dashboardPage = new DashboardPage(webAbstractPage) : dashboardPage;
    }
    public LibraryPage getLibraryPage() {
        return (libraryPage == null) ? libraryPage = new LibraryPage(webAbstractPage) : libraryPage;
    }
    public LibraryDetailPage getLibraryDetailPage() {
        return (libraryDetailPage == null) ? libraryDetailPage = new LibraryDetailPage(webAbstractPage) : libraryDetailPage;
    }

}