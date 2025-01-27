package cartapp.Pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class BasePageObject {

    protected WebDriver driver;
    protected Logger log;

    public BasePageObject(WebDriver driver, Logger log) {
        this.driver = driver;
        this.log = log;
    }

    /**
     * Open page with given URL
     */
    protected void openUrl (String url) {
        driver.get(url);
    }
    /**
     * Get URL of a current page in browser
     */
    public String getCurrentUrl () {
        return driver.getCurrentUrl();
    }
    /**
     * Get title of a current page in browser
     */
    public String getCurrentPageTitle () {
        return driver.getTitle();
    }
    /**
     * Get source of a current page
     */
    public String getCurrentPageSource () {
        return driver.getPageSource();
    }
    /**
     * Find element using given locator
     */
    protected WebElement find (By locator) {
        return driver.findElement(locator);
    }
    /**
     * Find all elements using given locator
     */
    protected List<WebElement> findAll (By locator) {
        return driver.findElements(locator);
    }
    /**
     * Click on element with given locator when it's visible
     */
    protected void click (By locator) {
        waitForVisibilityOf(locator, Duration.ofSeconds(5));
        find(locator).click();
    }

    /**
     * Select option from dropdown element using given locator
     */
    protected void selectOption (By dropdownLocator, int index) {
        log.info("Selecting option " + index + " from dropdown");
        WebElement dropdownElement = find(dropdownLocator);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByIndex(index);
    }

    /**
     * Type given text into element with given locator
     */
    protected void type (String text, By locator) {
        waitForVisibilityOf(locator, Duration.ofSeconds(5));
        find(locator).sendKeys(text);
    }

    /**
     * Wait for user to be redirected to given URL
     * @param url
     * @param timeOut
     */
    protected void waitForUrlVisibility(String url, Duration timeOut) {
        timeOut = (timeOut != null) ? timeOut : Duration.ofSeconds(30);
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        wait.until(ExpectedConditions.urlToBe(url));
    }

    /**
     * Wait for specific ExpectedCondition for the given amount of time in seconds
     */
    private void waitFor(ExpectedCondition<WebElement>condition, Duration timeOut) {
        timeOut = (timeOut != null) ? timeOut : Duration.ofSeconds(30);
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        wait.until(condition);
    }

    /**
     * Wait for given number of seconds for element with given locator to be visible on the page
     */
    protected void waitForVisibilityOf(By locator, Duration... timeOut) {
        int attempts = 0;
        while (attempts < 2) {
            try {
                waitFor(ExpectedConditions.visibilityOfElementLocated(locator),
                        (timeOut.length > 0 ? timeOut[0] : null));
                break;
            } catch (StaleElementReferenceException e) {
            }
            attempts++;
        }
    }
    /**
     * Wait for alert present and then switch to it
     */
    protected Alert switchToAlert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        return driver.switchTo().alert();
    }

    /**
     * Switch to new window based on Page Title
     */
    protected void switchToWindowWithTitle (String expectedTitle) {
        //Switching to new window
        String firstWindow = driver.getWindowHandle();

        Set<String> allWindows = driver.getWindowHandles();
        Iterator<String> windowsIterator = allWindows.iterator();

        while (windowsIterator.hasNext()) {
            String windowHandle = windowsIterator.next().toString();
            if (!windowHandle.equals(firstWindow)) {
                driver.switchTo().window(windowHandle);
                if (getCurrentPageTitle().equals(expectedTitle)) {
                    break;
                }
            }
        }
    }
    /**
     * Switch to iFrame using its locator
     */
    protected void switchToFrame (By frameLocator) {
        driver.switchTo().frame(find(frameLocator));
    }
    /**
     * Press key on given locator
     */
    protected void pressKey (By locator, Keys key) {
        find(locator).sendKeys(key);
    }
    /**
     * Press key using action class
     */
    public void pressKeyWithActions (Keys key) {
        log.info("Pressing " + key.name() + " using Action class");
        Actions action = new Actions(driver);
        action.sendKeys(key).build().perform();
    }
}
