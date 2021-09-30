package pageObjects;

public class B2BDashboardPage extends MobileAbstractPage{

    public B2BDashboardPage() {
        deviceElements = getDeviceElements("B2BDashboardPage");
    }

    public void selectBrandAndCompany(String... nameBrandCompany) {
        String BRAND_SPECIFIC = String.format(deviceElements.get("BRAND"), nameBrandCompany[0]);
        sleepInSecond(2);// for just in case the response for login is too long so that the brand name be tapped on
        if(isElementDisplayedWithWaitTime(BRAND_SPECIFIC,10))
            tapOnElement(BRAND_SPECIFIC);
        sleepInSecond(5);
        if (isElementDisplayed(deviceElements.get("OK_BUTTON"))) {
            tapOnElement(deviceElements.get("OK_BUTTON"));
        }

        String NAME_COMPANY_SPECIFIC = String.format(deviceElements.get("NAME_COMPANY_BRAND"), nameBrandCompany[1]);
        sleepInSecond(4);// to handle something weird in virtual device iso pro max 8
        waitForVisibility(NAME_COMPANY_SPECIFIC);
        sleepInSecond(3);// for just in case the response for login is too long so that the company cannot be tapped on
        tapOnElement(NAME_COMPANY_SPECIFIC);
        sleepInSecond(2);//for loading
    }

    public void goToActiveDocs() {
        sleepInSecond(3);
        waitForVisibility(deviceElements.get("ACTIVE_DOCS_BUTTON"));
        tapOnElement(deviceElements.get("ACTIVE_DOCS_BUTTON"));
        handleConfirmMessageNewDocComing();
    }

    public void goToDocCenter() {
        sleepInSecond(3);
        waitForVisibility(deviceElements.get("DOC_CENTER_BUTTON"));
        tapOnElement(deviceElements.get("DOC_CENTER_BUTTON"));
    }

    public void goToArchive() {
        sleepInSecond(3);
        waitForVisibility(deviceElements.get("ACTIVE_DOCS_BUTTON"));
        tapOnElement(deviceElements.get("ACTIVE_DOCS_BUTTON"));
        handleConfirmMessageNewDocComing();
    }
    public void handleConfirmMessageNewDocComing() {
        if (isElementDisplayedWithWaitTime(deviceElements.get("OK_BUTTON"), 5)) {
            tapOnElement(deviceElements.get("OK_BUTTON"));
        }
    }
    public boolean isDashboardButtonDisplayed() {
        waitForVisibility(deviceElements.get("DASHBOARD_BUTTON"));
        return isElementDisplayed(deviceElements.get("DASHBOARD_BUTTON"));
    }
}
