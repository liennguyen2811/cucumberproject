package com.fpt.automation.BillToBox.pageobjects;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class WebAbstractPage {

    By by;
    Select select;
    Actions action;
    WebElement element;
    long shortTimeout = 5;
    long longTimeout = 30;
    WebDriver driver;
    List<WebElement> elements;
    JavascriptExecutor jsExecutor;
    WebDriverWait waitExplicit;


    public WebAbstractPage(String browser) {
        this.driver = startBrowser(browser);;
        jsExecutor = (JavascriptExecutor) driver;
        waitExplicit = new WebDriverWait(driver, longTimeout);
        action = new Actions(driver);
    }


    public void openUrl(String urlValue) {
        driver.get(urlValue);
    }

    public void maximumBrowser(){
        driver.manage().window().maximize();
    }

    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public String getPageCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageCurrentSource() {
        return driver.getPageSource();
    }

    public void backToPage() {
        driver.navigate().back();
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public void forwardToPage() {
        driver.navigate().forward();
    }

    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    public void cancelAlert() {
        driver.switchTo().alert().dismiss();
    }

    public void waitToAlertPresence() {
        waitExplicit.until(ExpectedConditions.alertIsPresent());
    }

    public void sendkeyToAlert(String value) {
        driver.switchTo().alert().sendKeys(value);
    }

    public String getTextAlert() {
        return driver.switchTo().alert().getText();
    }

    public void clickToElement(String locator) {
        element = driver.findElement(By.xpath(locator));
        element.click();
    }

    public List<WebElement> getListElements(String locator) {
        return driver.findElements(By.xpath(locator));
    }

    public void sendkeyToElement(String locator, String value) {
        element = driver.findElement(By.xpath(locator));
        waitToElementPresence(locator);
        element.clear();
        element.sendKeys(value);
    }

    public void clearTextInElement(String locator) {
        element = driver.findElement(By.xpath(locator));
        waitToElementPresence(locator);
        element.clear();
    }

    public void clearTextHandleElement(String locator) {
        element = driver.findElement(By.xpath(locator));
        waitToElementPresence(locator);
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
    }

    public void selectItemInDropdown(String locator, String valueItem) {
        element = driver.findElement(By.xpath(locator));
        select = new Select(element);
        select.selectByVisibleText(valueItem);
    }

    public String getItemInDropdown(String locator) {
        element = driver.findElement(By.xpath(locator));
        select = new Select(element);
        return select.getFirstSelectedOption().getText();
    }

    public void sleepInSecond(long numberInSecond) {
        try {
            Thread.sleep(numberInSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void selectItemInDropdownJS(String parentLocator, String allItemLocator, String expectedItem) {
        element = driver.findElement(By.xpath(parentLocator));
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].click;", element);
        sleepInSecond(1);
        waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemLocator)));
        elements = driver.findElements(By.xpath(allItemLocator));
        for (WebElement item : elements) {
            System.out.println(item.getText());
            if (item.getText().contains(expectedItem)) {
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
                sleepInSecond(1);
                //element = driver.findElement(By.xpath(elements));
                element.click();
                sleepInSecond(2);
                break;
            }
        }
    }

    public String getAttributeValue(String locator, String attributeName) {
        element = driver.findElement(By.xpath(locator));
        return element.getAttribute(attributeName);
    }

    public String getTextElement(String locator) {
        element = driver.findElement(By.xpath(locator));
        return element.getText();
    }

    public String getColorElement(String locator) {
        element = driver.findElement(By.xpath(locator));
        return element.getCssValue("color");
    }

    public String getColorElementHandle(String locator) {
        element = driver.findElement(By.xpath(locator));
        return element.getCssValue("caret-color");
    }

    public int countElementNumber(String locator) {
        elements = driver.findElements(By.xpath(locator));
        return elements.size();
    }

    public void checkRadioButton(String locator) {
        element = driver.findElement(By.xpath(locator));
        if (!element.isSelected()) {
            element.click();
        }
    }

    public void uncheckRadioButton(String locator) {
        element = driver.findElement(By.xpath(locator));
        if (element.isSelected()) {
            element.click();
        }
    }

    public boolean isElementDisplayed(String locator) {
        try {
            //Handle element exists in DOM, display or not
            element = driver.findElement(By.xpath(locator));
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            // Handle element not exist in DOM
            return false;
        }
    }

    public boolean isElementSelected(String locator) {
        element = driver.findElement(By.xpath(locator));
        return element.isSelected();
    }

    public boolean isElementEnabled(String locator) {
        element = driver.findElement(By.xpath(locator));
        return element.isEnabled();
    }

    public void switchToChildWindowByID(String parent) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            if (!runWindow.equals(parent)) {
                driver.switchTo().window(runWindow);
                break;
            }
        }
    }

    public void switchToChildWindowByTitle(String title) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            driver.switchTo().window(runWindow);
            String currentWin = driver.getTitle();
            if (currentWin.equals(title)) {
                break;
            }
        }
    }

    public boolean closeAllWindowWithoutParent(String parentWindow) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            if (!runWindow.equals(parentWindow)) {
                driver.switchTo().window(runWindow);
                driver.close();
            }
        }
        driver.switchTo().window(parentWindow);
        if (driver.getWindowHandles().size() == 1)
            return true;
        else
            return false;
    }

    public void switchToFrameOrIframe(String locator) {
        element = driver.findElement(By.xpath(locator));
        driver.switchTo().frame(element);
    }

    public void switchToParentPage() {
        driver.switchTo().defaultContent();
    }

    public void hoverToElement(String locator) {
        element = driver.findElement(By.xpath(locator));
        action.moveToElement(element).perform();
    }

    public void doubleClickToElement(String locator) {
        element = driver.findElement(By.xpath(locator));
        action.doubleClick(element).perform();
    }

    public void sendKeyboardToElement(String locator, String key) {
        element = driver.findElement(By.xpath(locator));
        action.sendKeys(element, key).perform();
    }

    public boolean verifyImageLoaded(String locator) {
        boolean status;
        element = driver.findElement(By.xpath(locator));
        status = (boolean) jsExecutor.executeScript("return argument[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && argument [0].naturalWidth>0", element);
        if (status) {
            return true;
        } else {
            return false;
        }
    }

    public void waitToElementVisible(String locator) {
        by = By.xpath(locator);
        waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void waitToElementPresence(String locator) {
        by = By.xpath(locator);
        waitExplicit.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void waitForInvisibility(String locator, int maxSeconds) {
        element = driver.findElement(By.xpath(locator));
        Long startTime = System.currentTimeMillis();
        try {
            while (System.currentTimeMillis() - startTime < maxSeconds * 1000 && element.isDisplayed()) {
            }
        } catch (StaleElementReferenceException e) {
            return;
        }
    }

    public void waitToElementInvisible(String locator) {
        by = By.xpath(locator);
        waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public void waitToElementClickable(String locator) {
        element = driver.findElement(By.xpath(locator));
        waitExplicit.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void scrollToElement(String locator) {
        element = driver.findElement(By.xpath(locator));
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }

    public void jsScrollToElement(String locator) {
        element = driver.findElement(By.xpath(locator));
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void jSClickOn(String locator) {
        element = driver.findElement(By.xpath(locator));
        jsExecutor.executeScript("arguments[0].click()", element);
    }

    public void jSRunOnLocator(String javaScriptToRun) {
        jsExecutor.executeScript(javaScriptToRun);
    }

    public void clickElementByCoordinates(String locator, int xcord, int ycord) {
        element = driver.findElement(By.xpath(locator));
        action.moveToElement(element, xcord, ycord).click().build().perform();
    }

    public void highlightElement(String locator) {
        element = driver.findElement(By.xpath(locator));
        for (int i = 0; i < 10; i++) {
            jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
                    "color: red; border: 2px solid red;");
            jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
        }
    }

    public void close() {
        if (driver != null) {
            driver.quit();
        }
   }

    public void switchToNewTab() {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }

    public ArrayList<String> getTextElementsInList(String locator) {
        List<WebElement> listElements = driver.findElements(By.xpath(locator));
        ArrayList<String> arrayList = new ArrayList<>();
        for (WebElement listElement : listElements) {
            arrayList.add(listElement.getText());
        }
        return arrayList;
    }
    public static WebDriver startBrowser(String browser) {

        setDriversPath();
        switch (browser) {
            case "FIREFOX":
                return new FirefoxDriver();
            case "IE":
                return new InternetExplorerDriver();
            case "SAFARI":
                return new SafariDriver();
            case "EDGE":
                return new EdgeDriver();
            default:
                return new ChromeDriver();
        }
    }

    private static void setDriversPath() {
        if (SystemUtils.IS_OS_WINDOWS) {
            System.setProperty("webdriver.chrome.driver", "./src/bin/windows/chromedriver.exe");
            System.setProperty("webdriver.gecko.driver", "./src/bin/windows/geckodriver.exe");
//            todo: go to https://selenium.dev/documentation/en/webdriver/driver_requirements/#quick-reference
//             and download the binaries(ie, edge, edge chromium) and place them under src/bin/windows if you need them
            System.setProperty("webdriver.ie.driver", "./src/bin/windows/IEDriverServer.exe");
            System.setProperty("webdriver.edge.driver", "./src/bin/windows/msedgedriver.exe");
        } else if (SystemUtils.IS_OS_LINUX) {
            System.setProperty("webdriver.chrome.driver", "./src/bin/linux/chromedriver");
            System.setProperty("webdriver.gecko.driver", "./src/bin/linux/geckodriver");
        }
        else {
            System.setProperty("webdriver.chrome.driver", "./src/bin/macOS/chromedriver");
            System.setProperty("webdriver.gecko.driver", "./src/bin/macOS/geckodriver");
        }
    }

}
