package core.helpers;

import core.base.controls.BaseWebElement;
import core.base.controls.WebSelect;
import org.openqa.selenium.WebElement;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import static core.SLF4JLogger.log;
import static core.WebDriverFactory.getDriver;
import static core.utils.Utils.robot;
import static java.lang.String.format;

public class WebElementsActionsHelper {
    public static void selectByValue(WebSelect webSelect, String selectName, String value) {
        log(format("Selecting [%s] value from [%s]", value, selectName));
        webSelect.selectByValue(value);
    }

    public static void selectByValue(WebSelect webSelect, String value) {
        selectByValue(webSelect, webSelect.getName(), value);
    }

    public static void selectByVisibleText(WebSelect webSelect, String selectName, String text) {
        log(format("Selecting [%s] text from [%s]", text, selectName));
        webSelect.selectByVisibleText(text);
    }

    public static void selectByVisibleText(WebSelect webSelect, String text) {
        selectByVisibleText(webSelect, webSelect.getName(), text);
    }

    public static List<BaseWebElement> getAllOptions(WebSelect webSelect) {
        log(format("Selecting all options form [%s] ", webSelect.getName()));
        List<BaseWebElement> baseWebElements = new ArrayList<>();
        for (WebElement webOptions : webSelect.getOptions()) {
            baseWebElements.add(new BaseWebElement(webOptions));
        }
        return baseWebElements;
    }

    public static String selectDifferentByText(List<BaseWebElement> webElements, String unwantedText) {
        for (BaseWebElement element : webElements) {
            if (!element.getTrimText().equals(unwantedText)) {
                element.click();
                return element.getTrimText();
            }
        }
        throw new IllegalArgumentException(format("Unable to select text different to [%s]", unwantedText));
    }

    public static String selectDifferentByAttribute(List<BaseWebElement> webElements, String attribute, String differentToSelect) {
        for (BaseWebElement element : webElements) {
            if (!element.getAttribute(attribute).equals(differentToSelect)) {
                element.click();
                return element.getAttribute(attribute);
            }
        }
        throw new IllegalArgumentException(format("Unable to select different to [%s], using attribute [%s]", differentToSelect, attribute));
    }

    public static String selectByAttribute(List<BaseWebElement> webElements, String attribute, String textToSelect) {
        for (BaseWebElement element : webElements) {
            if (element.getAttribute(attribute).equals(textToSelect)) {
                element.click();
                return element.getAttribute(attribute);
            }
        }
        throw new IllegalArgumentException(format("Unable to select [%s], using attribute [%s]", textToSelect, attribute));
    }

    public static void clickOnTheBaseWebElement(BaseWebElement baseWebElement) {
        baseWebElement.waitElementBeNotStale();
        baseWebElement.waitForElementToBeClickable();
        baseWebElement.focus();
        baseWebElement.click();
    }

    public static void clickJSOnTheBaseWebElement(BaseWebElement baseWebElement) {
        baseWebElement.scrollTo();
        baseWebElement.waitElementBeNotStale();
        baseWebElement.focus();
        baseWebElement.clickJS();
    }

    public static void hoverOnElement(BaseWebElement baseWebElement) {
        baseWebElement.mouseOverElement();
    }

    public static void unfocusInput() {
        robot().keyPress(KeyEvent.VK_ESCAPE);
    }

    public static void backToDefaultContent(){
        getDriver().switchTo().defaultContent();
    }
}
