package pageObjects;

import com.google.common.collect.ImmutableMap;
import com.opencsv.CSVReader;
import common.TestConfig;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.connection.ConnectionState;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.KeyInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileReader;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static managers.DriverControl.driverRunForMobileOrBrowser;

public class MobileAbstractPage {
    TouchAction touch;
    Actions action;
    WebElement element;
    By by;
    long sortTimeout = 5;
    long longTimeout = 30;
    WebDriver driver = driverRunForMobileOrBrowser;
    List<String[]> csvReader;
    JavascriptExecutor jsExecutor;
    WebDriverWait waitExplicit;
    HashMap<String, String> deviceElements;

    public MobileAbstractPage() {
        try {
            touch = new TouchAction((MobileDriver) driver);
            jsExecutor = (JavascriptExecutor) driver;
            waitExplicit = new WebDriverWait(driver, longTimeout);
            action = new Actions(driver);
            csvReader = new CSVReader(new FileReader(System.getProperty("user.dir")
                   + "/src/test/resources/testdata/language.csv")).readAll();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    protected WebDriver getDriver() {
        return driverRunForMobileOrBrowser;
    }

    By elementAttribute(String locator) {
        by = By.xpath(locator);
        return by;
    }

    public Boolean isElementPresent(By targetElement) throws InterruptedException {
        return driver.findElements(targetElement).size() > 0;
    }

    public void hideKeyboard() {
        ((AppiumDriver) driver).hideKeyboard();
    }

    public void back() {
        ((AndroidDriver) driver).pressKey(new KeyEvent().withKey(AndroidKey.BACK));
    }

    public void waitForVisibility(String locator) {
        by = elementAttribute(locator);
        try {
            waitExplicit.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
        } catch (TimeoutException e) {
            System.out.println("Element is not visible: " + by);
            throw e;
        }
    }

    public void tap(double xPosition, double yPosition) {
        HashMap<String, Double> tapObject = new HashMap<String, Double>();
        tapObject.put("startX", xPosition);
        tapObject.put("startY", yPosition);
        jsExecutor.executeScript("mobile: tap", tapObject);
    }

    public WebElement findElement(String locator) {
        by = elementAttribute(locator);
        try {
            return driver.findElement(by);
        } catch (NoSuchElementException e) {
            System.out.println(this.getClass().getName() + "findElement" + "Element not found" + locator);
            throw e;
        }
    }

    public void clickElementByText(String Class, String Name) {
        List<WebElement> elements = driver.findElements(By.className(Class));
        for (WebElement element : elements) {
            if (element.getText().equals(Name)) {
                element.click();
            }
        }
    }

    public void tapOnElement(String locator) {
        try {
            element = driver.findElement(elementAttribute(locator));
            element.click();
        } catch (NoSuchElementException e) {
            System.out.println(this.getClass().getName() + "findElement" + "Element not click" + locator);
            throw e;
        }
    }

    public <WebElement> List getListElements(String locator) {
        try {
            sleepInSecond(2);
            return driver.findElements(By.xpath(locator));
        } catch (NoSuchElementException e) {
            System.out.println(this.getClass().getName() + "findElements" + "element not found" + locator);
            throw e;
        }
    }

    public String getAlertText() {
        try {
            Alert alert = driver.switchTo().alert();
            return alert.getText();
        } catch (NoAlertPresentException e) {
            throw e;
        }
    }

    public String getAttribute(String locator, String nameAttribute) {
        by = elementAttribute(locator);
        try {
            element = driver.findElement(by);
            return element.getAttribute(nameAttribute);
        } catch (NoAlertPresentException e) {
            throw e;
        }
    }

    public boolean isAlertPresent() {
        try {
            waitExplicit.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public void acceptAlert() {
        try {
            waitExplicit.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
        } catch (Exception ex) {
        }
    }

    public void dismissAlert() {
        waitExplicit.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().dismiss();
    }

    public void getNetworkConnection() {
        System.out.println(((AndroidDriver) driver).getConnection());
    }

    public void setNetworkConnection(boolean airplaneMode, boolean wifi, boolean data) {

        long mode = 1L;

        if (wifi) {
            mode = 2L;
        } else if (data) {
            mode = 4L;
        }

        ConnectionState connectionState = new ConnectionState(mode);
        ((AndroidDriver) driver).setConnection(connectionState);
        System.out.println("Your current connection settings are :" + ((AndroidDriver) driver).getConnection());
    }

    public void getContext() {
        ((AppiumDriver) driver).getContextHandles();
    }

    public void getPageSource() {
        System.out.println(((AppiumDriver) driver).getPageSource());
    }

    public void setContext(String context) {

        Set<String> contextNames = ((AppiumDriver) driver).getContextHandles();

        if (contextNames.contains(context)) {
            ((AppiumDriver) driver).context(context);
            System.out.println("Context changed successfully");
        } else {
            System.out.println(context + "not found on this page");
        }

        System.out.println("Current context" + ((AppiumDriver) driver).getContext());
    }

    public void longPress(By locator) {
        try {
            element = driver.findElement(locator);
            LongPressOptions longPressOptions = new LongPressOptions();
            longPressOptions.withElement(ElementOption.element(element));
            touch.longPress(longPressOptions).release().perform();
            System.out.println("Long press successful on element: " + element);
        } catch (NoSuchElementException e) {
            System.out.println(this.getClass().getName() + "findElement" + "Element not found" + locator);
            throw e;
        }
    }

    public void longPress(int x, int y) {
        PointOption pointOption = new PointOption();
        pointOption.withCoordinates(x, y);
        touch.tap(pointOption).release().perform();
        System.out.println("Tab successful on coordinates: " + "( " + x + "," + y + " )");

    }

    public void longPress(By locator, int x, int y) {
        try {
            element = driver.findElement(locator);
            TouchAction touch = new TouchAction((MobileDriver) driver);
            LongPressOptions longPressOptions = new LongPressOptions();
            longPressOptions.withPosition(new PointOption().withCoordinates(x, y))
                    .withElement(ElementOption.element(element));
            touch.longPress(longPressOptions).release().perform();
            System.out.println(
                    "Long press successful on element: " + element + "on coordinates" + "( " + x + "," + y + " )");
        } catch (NoSuchElementException e) {
            System.out.println(this.getClass().getName() + "findElement" + "Element not found" + locator);
            throw e;
        }
    }

    public void swipe(double startX, double startY, double endX, double endY, double duration) {
        HashMap<String, Double> swipeObject = new HashMap<String, Double>();
        // swipeObject.put("touchCount", 1.0);
        swipeObject.put("startX", startX);
        swipeObject.put("startY", startY);
        swipeObject.put("endX", endX);
        swipeObject.put("endY", endY);
        swipeObject.put("duration", duration);
        jsExecutor.executeScript("mobile: swipe", swipeObject);
    }

    static String UiScrollable(String uiSelector) {
        return "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(" + uiSelector
                + ".instance(0));";
    }

    public void openNotifications() {
        ((AndroidDriver) driver).openNotifications();
    }

    public void launchApp() {
        ((AppiumDriver) driver).launchApp();
    }

    public void click(String elementByName) {
        ((AppiumDriver) driver).findElementByName(elementByName).click();
    }

    public void scrollDown(int swipeTimes, int durationForSwipe) {
        Dimension dimension = driver.manage().window().getSize();

        for (int i = 0; i <= swipeTimes; i++) {
            int start = (int) (dimension.getHeight() * 0.5);
            int end = (int) (dimension.getHeight() * 0.3);
            int x = (int) (dimension.getWidth() * .5);

            new TouchAction((AppiumDriver) driver).press(PointOption.point(x, start))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(durationForSwipe)))
                    .moveTo(PointOption.point(x, end))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(durationForSwipe)))
                    .release()
                    .perform();
        }
    }

    public void scrollUp(int swipeTimes, int durationForSwipe) {
        Dimension dimension = driver.manage().window().getSize();

        for (int i = 1; i <= swipeTimes; i++) {
            int start = (int) (dimension.getHeight() * 0.3);
            int end = (int) (dimension.getHeight() * 0.9);
            int x = (int) (dimension.getWidth() * .5);
            new TouchAction((AppiumDriver) driver).press(PointOption.point(x, start))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(durationForSwipe)))
                    .moveTo(PointOption.point(x, end))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(durationForSwipe))).release().perform();
        }
    }

    public void sleepInSecond(long numberInSecond) {
        try {
            Thread.sleep(numberInSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sendKeyToElement(String locator, String value) {
        element = driver.findElement(elementAttribute(locator));
        waitForVisibility(locator);
        if(!locator.contains("PickerWheel"))
            element.clear();
        element.sendKeys(value);
    }

    public boolean isElementDisplayed(String locator) {
        try {
            element = driver.findElement(elementAttribute(locator));
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            System.out.println(this.getClass().getName() + "findElement" + "Element not display" + locator);
            return false;
        }
    }

    public void sendKeyCode(String locator, Keys key) {
        element = driver.findElement(elementAttribute(locator));
        element.sendKeys(key);
    }

    public String getTextElement(String locator) {
        element = findElement(locator);
        return element.getText();
    }

    public void sendKeyAction(String locator, String input) {
        element = driver.findElement(elementAttribute(locator));
        tapOnElement(locator);
        KeyInput keyboard = new KeyInput("keyboard");
        // Sequence sendKeys = new Sequence(keyboard, 0);
        char[] arrayText = input.toCharArray();
        element.clear();

        for (char c : arrayText) {
            Sequence sendKeys = new Sequence(keyboard, 0);
            sendKeys.addAction(keyboard.createKeyDown(Character.toString(c).codePointAt(0)));
            sendKeys.addAction(keyboard.createKeyUp(Character.toString(c).codePointAt(0)));
            ((AppiumDriver) driver).perform(Arrays.asList(sendKeys));
        }
    }

    public void swipeHorizontal(int y, int start, int end) {
        new TouchAction((AppiumDriver) driver).press(PointOption.point(start, y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).moveTo(PointOption.point(end, y)).release()
                .perform();
    }

    public void scrollToElement(String locator, String direction) {
        int count = 0;
        int maxTries = 3;
        while (true) {
            by = elementAttribute(locator);
            try {
                waitExplicit.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
                break;
            } catch (TimeoutException e) {
                swipeVertical(direction);
                if (++count == maxTries)
                    throw e;
            }
        }
    }

    public void swipeVertical(String direction) {
        Dimension dimension = driver.manage().window().getSize();
        int start, end, x;

        if (direction.toLowerCase().equals("up")) {
            start = (int) (dimension.getHeight() * 0.3);
            end = (int) (dimension.getHeight() * 0.8);
            x = (int) (dimension.getWidth() * .5);
        } else {
            start = (int) (dimension.getHeight() * 0.5);
            end = (int) (dimension.getHeight() * 0.3);
            x = (int) (dimension.getWidth() * .5);
        }
        scroll(x, start, x, end);
    }

    public void swipeHorizontal(String locator, String direction) {
        Rectangle rec = getElementRect(locator);
        int y, start, end;
        if (direction.toLowerCase().equals("left")) {
            y = rec.y + rec.height / 2;
            start = rec.x + rec.width * 3 / 4;
            end = rec.x + rec.width * 1 / 4;
        } else {
            y = rec.y + rec.height / 2;
            start = rec.x + rec.width * 1 / 4;
            end = rec.x + rec.width * 5 / 4;
        }
        scroll(start, y, end, y);
    }

    public void scroll(int x1, int y1, int x2, int y2) {
        new TouchAction((AppiumDriver) driver).press(PointOption.point(x1, y1))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).moveTo(PointOption.point(x2, y2)).release()
                .perform();
    }

    public Rectangle getElementRect(String locator) {
        element = driver.findElement(elementAttribute(locator));
        return element.getRect();
    }

    public void scrollUpPos(int start, double end) {
        Dimension dimension = driver.manage().window().getSize();
        int x = (int) (dimension.getWidth() * .5);
        new TouchAction((AppiumDriver) driver).press(PointOption.point(x, start))
                .moveTo(PointOption.point(x, (int) (dimension.getHeight() * 0.5)))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1))).release().perform();
    }

    public boolean isAndroid() {
        return TestConfig.getDriverType().toString().equals("ANDROID");
    }

    public HashMap<String, String> getDeviceElements(String pageObject) {
        HashMap<String, String> deviceElements;
        if (isAndroid()) {
            deviceElements = TestConfig.androidElements.get(pageObject);
        } else {
            deviceElements = TestConfig.iOSElements.get(pageObject);
        }
        return deviceElements;
    }

    public void longPressWithElement(String location) {
        Rectangle rec = getElementRect(location);
        PointOption pointOption = new PointOption();
        pointOption.withCoordinates(rec.x + (rec.width / 2), rec.y + (rec.width / 2));
        touch.tap(pointOption).release().perform();
        System.out.println("Tab successful on coordinates: " + "( " + rec.x + "," + rec.y + " )");
    }

    public void resetApplication() {
        ((AppiumDriver) driver).resetApp();
    }

    public boolean compareLanguage(String language, String ...elementsName) {
        boolean result = false;

        for(String[] row : csvReader) {
            if(row[0].equals(language)) {
                for(String element : elementsName) {
                    if(!row[Arrays.asList(csvReader.get(0)).indexOf(element)].equals("")) {
                        result = getTextElement(deviceElements.get(element))
                            .equals(row[Arrays.asList(csvReader.get(0)).indexOf(element)]);
                    } else {
                        result = !getTextElement(deviceElements.get(element)).contains("scenes");
                    }
                }
            }
        }
        return result;
    }

    public void resetApplication(int seconds) {
        sleepInSecond(3);
        String capabilities = ((AppiumDriver) driver).getSessionDetails().toString();
        String appPackage;
        if(isAndroid()) {
            appPackage = capabilities.substring(capabilities.indexOf("appPackage")+11,capabilities.indexOf(","));
        }
        else {
            appPackage = capabilities.substring(capabilities.indexOf("CFBundleIdentifier")+19,capabilities.indexOf("deviceName")-2);
        }
        System.out.println(appPackage);
        ((AppiumDriver) driver).terminateApp(appPackage);
        sleepInSecond(seconds);
        ((AppiumDriver) driver).activateApp(appPackage);
    }

    public String getAppId() {
        String capabilities = ((AppiumDriver) driver).getSessionDetails().toString();
        if(isAndroid()) {
            return capabilities.substring(capabilities.indexOf("appPackage")+11,capabilities.indexOf(","));
        }
        else {
            return capabilities.substring(capabilities.indexOf("CFBundleIdentifier")+19,capabilities.indexOf("deviceName")-2);
        }
    }

    public void enrollBiometricAbstract(boolean status) {
        ((AppiumDriver) driver).executeScript("mobile:enrollBiometric", ImmutableMap.of("isEnabled", status));
    }

    /**
     * @param status
     */
    public void setBiometricStatusAbstract(boolean status) {
        try {
            ((AppiumDriver) driver).executeScript("mobile:sendBiometricMatch", ImmutableMap.of("type", "faceId", "match", status));
        } catch (Exception ex) {
            ((AppiumDriver) driver).executeScript("mobile:sendBiometricMatch", ImmutableMap.of("type", "touchId", "match", status));
        }
    }
    public void allowAlert() {
        try {
            sleepInSecond(5);
            driver.switchTo().alert().accept();
        }
        catch(Exception ex) {}
    }

    public void notAllowAlert() {
        try {
            sleepInSecond(5);
            driver.switchTo().alert().dismiss();
        }
        catch(Exception ex) {}
    }

    /**
     * @param status
     */
    public void fingerTouchPrint(boolean status) {
        sleepInSecond(10);
        if(status) {
            ((AndroidDriver) driver).fingerPrint(1);
        }
        else {
            ((AndroidDriver) driver).fingerPrint(2);
        }
    }

    public boolean checkKeyBoard() {
        if(isAndroid())
            return ((AndroidDriver) driver).isKeyboardShown();
        else
            return ((IOSDriver) driver).isKeyboardShown();
    }
}
