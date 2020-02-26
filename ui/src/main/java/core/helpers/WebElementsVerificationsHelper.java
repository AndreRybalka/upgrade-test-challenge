package core.helpers;

import core.WebDriverFactory;
import core.base.controls.BaseWebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

import static core.SLF4JLogger.log;
import static core.contsants.Constants.CssAttributes.FONT_WEIGHT;
import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;
import static org.testng.Assert.assertTrue;


public class WebElementsVerificationsHelper {

    public static void verifyElementPresent(BaseWebElement element, String elementName) {
        log(format("Checking that [%s] is present", elementName));
        element.waitForElementPresent();
        assertThat(element.isPresent()).as(format("[%s] presented", elementName)).isTrue();
    }

    public static void verifyElementPresent(BaseWebElement element) {
        verifyElementPresent(element, element.getName());
    }

    public static void verifyElementNotPresent(BaseWebElement element, String elementName) {
        log(format("Checking that [%s] is NOT present", elementName));
        assertThat(element.isPresent()).as(format("[%s] presented", elementName)).isFalse();
    }

    public static void verifyElementNotPresent(BaseWebElement element) {
        verifyElementNotPresent(element, element.getName());
    }

    public static void verifyElementDisplayed(TypifiedElement element, String elementName) {
        log(format("Checking that [%s] is displayed", elementName));
        new WebDriverWait(WebDriverFactory.getDriver(), 10).until(visibilityOf(element.getWrappedElement()));
        assertThat(element.isDisplayed()).as(format("[%s] displayed", elementName)).isTrue();
    }

    public static void verifyConditionIsTrue(Boolean condition, String description) {
        log(format("Checking that [%s]", description));
        assertThat(condition).isTrue();
    }

    public static void verifyConditionIsFalse(Boolean condition, String description) {
        log(format("Checking that [%s]", description));
        assertThat(condition).isFalse();
    }

    public static void verifyElementDisplayed(TypifiedElement element) {
        verifyElementDisplayed(element, element.getName());
    }

    public static void verifyElementTextContains(BaseWebElement element, String expectedText, String elementName) {
        log(format("Checking that [%s] text contains [%s]", elementName, expectedText));
        assertThat(element.getTrimText()).as(format("[%s] text", elementName)).containsIgnoringCase(expectedText);
    }

    public static void verifyElementTextEquals(BaseWebElement element, String expectedText) {
        verifyElementTextEquals(element, expectedText, element.getName());
    }

    public static void verifyElementTextEquals(BaseWebElement element, String expectedText, String elementName) {
        log(format("Checking that [%s] text equals [%s]", elementName, expectedText));
        assertThat(element.getTrimText()).as(format("[%s] text", elementName)).isEqualTo(expectedText);
    }

    public static void verifyElementTextNotEquals(BaseWebElement element, String expectedText) {
        verifyElementTextNotEquals(element, expectedText, element.getName());
    }

    public static void verifyElementTextNotEquals(BaseWebElement element, String expectedText, String elementName) {
        log(format("Checking that [%s] text not equals [%s]", elementName, expectedText));
        assertThat(element.getTrimText()).as(format("[%s] text", elementName)).isNotEqualTo(expectedText);
    }

    public static void verifyElementTextLength(BaseWebElement element, int expectedLength, String elementName) {
        log(format("Checking that [%s] text length equals [%d]", elementName, expectedLength));
        assertThat(element.getTrimText().length()).as(format("[%s] text length", elementName)).isEqualTo(expectedLength);
    }

    public static void verifyElementTextLength(BaseWebElement element, int expectedLength) {
        verifyElementTextLength(element, expectedLength, element.getName());
    }

    public static void verifyElementTextIsBold(BaseWebElement element, String elementName) {
        log(format("Checking that [%s] text is bold", elementName));
        String fontWeight = element.getWrappedElement().getCssValue(FONT_WEIGHT);
        assertTrue(fontWeight.equals("bold") || fontWeight.equals("700"),
                format("[%s] text was NOT bold, expected [%s] attribute to equal [bold] || [700], but was [%s]", elementName, FONT_WEIGHT, fontWeight));
    }

    public static void verifyElementTextIsBold(BaseWebElement element) {
        verifyElementTextIsBold(element, element.getName());
    }

    public static void verifyElementAttributeContains(BaseWebElement element, String elementName, String attribute, String attributeToContain) {
        log(format("Checking that [%s] attribute [%s] contains [%s]", elementName, attribute, attributeToContain));
        assertThat(element.getAttribute(attribute))
                .as(format("[%s] attribute [%s] NOT contains [%s]", elementName, attribute, attributeToContain))
                .contains(attributeToContain);
    }

    public static void verifyElementAttributeContains(BaseWebElement element, String attribute, String attributeToContain) {
        verifyElementAttributeContains(element, element.getName(), attribute, attributeToContain);
    }

    public static void verifyElementAttributeNotContains(BaseWebElement element, String elementName, String attribute, String attributeToContain) {
        log(format("Checking that [%s] attribute [%s] NOT contains [%s]", elementName, attribute, attributeToContain));
        assertThat(element.getAttribute(attribute))
                .as(format("[%s] attribute [%s] contains [%s]", elementName, attribute, attributeToContain))
                .doesNotContain(attributeToContain);
    }

    public static void verifyElementAttributeNotContains(BaseWebElement element, String attribute, String attributeToContain) {
        verifyElementAttributeNotContains(element, element.getName(), attribute, attributeToContain);
    }
}
