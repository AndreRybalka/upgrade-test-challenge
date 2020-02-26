package core.base.controls;

import core.WebDriverFactory;
import core.utils.ConfigVariables.PropertyKey;
import core.utils.Directories;
import core.utils.PropertyUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static core.SLF4JLogger.*;
import static core.contsants.Delays.SHORT_DELAY;
import static core.utils.WaitingUtils.sleep;
import static java.lang.String.format;

/**
 * Customizes standard Selenium WebElement
 */
public class BaseWebElement extends TypifiedElement {

    private int TRIES = 0;
    private static final String IFRAME = "./iframe";


    public BaseWebElement(WebElement wrappedElement) {
        super(wrappedElement);
    }

    //TODO create constants class and import
    private static final int BIG_TIMEOUT = Integer.valueOf(
            PropertyUtils.getProperty(Directories.getEnvironmentPropertiesPath(), PropertyKey.BIG_TIMEOUT.getValue()));
    private static final int SMALL_TIMEOUT = Integer.valueOf(PropertyUtils
            .getProperty(Directories.getEnvironmentPropertiesPath(), PropertyKey.SMALL_TIMEOUT.getValue()));
    private static final int IMPLICIT_TIMEOUT = Integer.valueOf(PropertyUtils
            .getProperty(Directories.getEnvironmentPropertiesPath(), PropertyKey.TIMEOUT_IN_SECONDS.getValue()));

    public static WebDriver getDriver() {
        return WebDriverFactory.getDriver();
    }

    //todo architecture gap, this method should be only in frames
    public void switchTo() {
        getDriver().switchTo().frame(this.getWrappedElement());
    }

    public void mouseOverElement() {
        try {
            new Actions(getDriver()).moveToElement(this.getWrappedElement()).build().perform();
            info("Mouse over action on element was executed");
        } catch (Exception e) {
            error("Mouse over action on element was NOT executed");
        }
        return;
    }

    /**
     * @return converted price from String to double
     */
    public double getPriceAsNumber() {
        return Double.valueOf(this.getPriceText());
    }

    /**
     * @return only numbers and dot
     */
    public String getPriceText() {
        return this.getTrimText().replaceAll("[^0-9.]", "");
    }

    /**
     * @return true if element id displayed and has height
     */
    public boolean isPresent() {
        getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
        try {
            return (this.getWrappedElement().isDisplayed() & this.getWrappedElement().getSize().getHeight() > 0);
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        } finally {
            getDriver().manage().timeouts().implicitlyWait(IMPLICIT_TIMEOUT, TimeUnit.SECONDS);
        }
    }

    public String getAttribute(String attributeName) {
        return this.getWrappedElement().getAttribute(attributeName);
    }

    /**
     * @return trimmed web element text
     */
    public String getTrimText() {
        return this.getWrappedElement().getText().trim();
    }

    public String getText() {
        return this.getWrappedElement().getText();
    }

    /**
     * Wait for specified 'ExpectedCondition'
     *
     * @param condition
     */
    public void waitForExpectedCondition(final ExpectedCondition<?> condition, String message) {
        try {
            Wait<WebDriver> wait = new FluentWait<>(getDriver()).withTimeout(BIG_TIMEOUT, TimeUnit.MILLISECONDS)
                    .pollingEvery(500, TimeUnit.MILLISECONDS)
                    .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
                    .withMessage("Timed out after " + (BIG_TIMEOUT / 1000) + " seconds waiting for '" + this.getName()
                            + "' element " + message);
            wait.until(condition);
        } catch (TimeoutException e) {
            e.printStackTrace();
            throw new AssertionError("TimeoutException: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public <T extends BaseWebElement> T waitForElementPresent() {
        waitForExpectedCondition(ExpectedConditions.visibilityOf(this.getWrappedElement()), "present");
        return (T) this;
    }

    /**
     * Waiting for element does not present
     */
    public void waitForElementNotPresent() {
        Wait<WebDriver> wait = new FluentWait<>(getDriver()).withTimeout(BIG_TIMEOUT, TimeUnit.MILLISECONDS)
                .pollingEvery(500, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
                .withMessage("Timed out after " + (BIG_TIMEOUT / 1000) + " seconds waiting for '" + this.getName()
                        + "' element not present");
        wait.until((webDriver) -> !this.isPresent());
    }

    public void waitForElementToBeClickable() {
        waitForExpectedCondition(ExpectedConditions.elementToBeClickable(this.getWrappedElement()), "to be clickable");
    }

    public void waitElementBeNotStale() {
        int maxTriesAmount = 5;
        while (TRIES <= maxTriesAmount) {
            try {
                sleep(SHORT_DELAY);
                debug("Sleeping for " + SHORT_DELAY +" milliseconds");
                this.isPresent();
                break;
            } catch (StaleElementReferenceException e) {
                ++TRIES;
                error(format("Element with size value [%s] is not attached to document, StaleElementReferenceException is caught, try to verify is element present TRIES amount [%s]",
                        this.getWrappedElement().getText().trim(), TRIES));
                waitElementBeNotStale();
            }
        }
    }

    public void waitForAttributeContains(String attribute, String value) {
        waitForExpectedCondition(ExpectedConditions.attributeContains(this.getWrappedElement(), attribute, value),
                "'" + attribute + "' attribute contains: '" + value + "'");
    }

    public void waitForAttributeNotContains(String attribute, String value) {
        waitForExpectedCondition(
                ExpectedConditions.not(ExpectedConditions.attributeContains(this.getWrappedElement(), attribute, value)),
                "'" + attribute + "' attribute not contains: '" + value + "'");
    }

    public void waitForTextInElementPresentEquals(String value) {
        Wait<WebDriver> wait = getWebDriverWaitCommonForWaitForTextPresent(value);
        wait.until((webDriver) -> this.getTrimText().equalsIgnoreCase(value));
    }

    public void waitForTextInElementPresentContains(String value) {
        Wait<WebDriver> wait = getWebDriverWaitCommonForWaitForTextPresent(value);
        wait.until((webDriver) -> this.getTrimText().contains(value));
    }

    private Wait<WebDriver> getWebDriverWaitCommonForWaitForTextPresent(String value) {
        return new FluentWait<>(getDriver()).withTimeout(SMALL_TIMEOUT, TimeUnit.MILLISECONDS)
                .pollingEvery(500, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
                .withMessage("Timed out after waiting for [" + value + "] text present in " +
                        "[" + this.getName() + "], actual text is [" + this.getTrimText() + "]");
    }

    public void waitForTextInElementNotPresent(String value) {
        Wait<WebDriver> wait = new FluentWait<>(getDriver()).withTimeout(SMALL_TIMEOUT, TimeUnit.MILLISECONDS)
                .pollingEvery(500, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
                .withMessage("Timed out after " + (BIG_TIMEOUT / 1000) + " seconds waiting for '" + this.getName()
                        + "' text in element present");
        wait.until((webDriver) -> !this.getTrimText().equals(value));
    }

    /**
     * Focus to this web element
     */
    public void focus() {
        executeCommand("focus()");
        info("Javascript operation 'focus()' was executed");
    }

    /**
     * Removes focus from current element
     */
    public void blur() {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].focus(); arguments[0].blur(); return true",
                this);
    }

    /**
     * Allows to execute Javascript on current element
     *
     * @param command without closing semicolon (e.g. to get innerHTML of an element specify command =
     *                "innerHTML")
     * @return result of command execution
     */
    private String executeCommand(String command) {
        return String.valueOf(
                ((JavascriptExecutor) getDriver()).executeScript("return arguments[0]." + command + ";", this));
    }

    public void highlight() {
//		final JavascriptExecutor js = (JavascriptExecutor) getDriver();
//		js.executeScript(
//				"element = arguments[0];original_style = element.getAttribute('style');"
//						+ "element.setAttribute('style', original_style + '; border: 5px solid red;');"
//						+ "setTimeout(function(){element.setAttribute('style', original_style);}, 1000);",
//				this.getWrappedElement());
    }

    public void click() {
        highlight();
        this.getWrappedElement().click();
        log("Click on the '" + this.getName() + "' was executed");
    }

    /**
     * Click on element using javascript
     */
    public void clickJS() {
        highlight();
        String code = "var element = arguments[0];" + "clickEvent = document.createEvent(\"MouseEvents\");"
                + "clickEvent.initEvent(\"mousedown\", true, true);" + "element.dispatchEvent(clickEvent);"
                + "clickEvent = document.createEvent(\"MouseEvents\");" + "clickEvent.initEvent(\"click\", true, true);"
                + "element.dispatchEvent(clickEvent);" + "clickEvent = document.createEvent(\"MouseEvents\");"
                + "clickEvent.initEvent(\"mouseup\", true, true);" + "element.dispatchEvent(clickEvent);";
        ((JavascriptExecutor) getDriver()).executeScript(code, this.getWrappedElement());
    }

    /**
     * Move mouse cursor to the middle of the element and click
     */
    public void clickAction() {
        new Actions(getDriver()).moveToElement(this.getWrappedElement()).click().build().perform();
    }

    public void scrollTo() {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", this);
        log("Scroll to the '" + this.getName() + "' was executed");
    }

    public BaseWebElement findElement(By by, String elementName) {
        BaseWebElement element = new BaseWebElement(this.getWrappedElement().findElement(by));
        element.setName(elementName);
        return element;
    }

    public BaseWebElement findElement(By by) {
        return findElement(by, "foundWebElement");
    }

    public List<BaseWebElement> findElements(By by, String elementName) {
        List<BaseWebElement> elements = new ArrayList<>();
        try {
            List<WebElement> findElements = this.getWrappedElement().findElements(by);
            for (int i = 0; i < findElements.size(); i++) {
                BaseWebElement webelement = new BaseWebElement(findElements.get(i));
                webelement.setName(format("%s [%d]", elementName, i));
                elements.add(webelement);
            }
        } catch (Exception e) {
            error("Elements were NOT found");
        }
        return elements;
    }

    public List<BaseWebElement> findElements(By by) {
        return findElements(by, "foundWebElement");
    }

    public void waitForTextInElementPresent(String value) {
        Wait<WebDriver> wait = new FluentWait<>(getDriver()).withTimeout(SMALL_TIMEOUT, TimeUnit.MILLISECONDS)
                .pollingEvery(500, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
                .withMessage("Timed out after waiting for [" + value + "] text present in " +
                        "[" + this.getName() + "], actual text is [" + this.getTrimText() + "]");
        wait.until((webDriver) -> this.getTrimText().contains(value));
    }

    public static void waitUntilVisibility(BaseWebElement element)
    {
        WebDriverWait wait = new WebDriverWait(getDriver(),10);
        wait.until(ExpectedConditions.visibilityOf(element.getWrappedElement()));
    }
    public void switchToFrame() {
        BaseWebElement element = new BaseWebElement(this.getWrappedElement().findElement(By.xpath(IFRAME)));
        element.switchTo();
    }
}
