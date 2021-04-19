package managers;

import org.openqa.selenium.WebDriver;
import pageObjects.DashboardPage;
import pageObjects.LibraryDetailPage;
import pageObjects.LibraryPage;
import pageObjects.LoginPage;

public class PageObjectManager {

    private WebDriver driver;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private LibraryPage libraryPage;
    private LibraryDetailPage libraryDetailPage;

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage getLoginPage() {
        return (loginPage == null) ? loginPage = new LoginPage(this.driver) : loginPage;
    }
    public DashboardPage getDashboardPage() {
        return (dashboardPage == null) ? dashboardPage = new DashboardPage(driver) : dashboardPage;
    }
    public LibraryPage getLibraryPage() {
        return (libraryPage == null) ? libraryPage = new LibraryPage(driver) : libraryPage;
    }
    public LibraryDetailPage getLibraryDetailPage() {
        return (libraryDetailPage == null) ? libraryDetailPage = new LibraryDetailPage(driver) : libraryDetailPage;
    }
}