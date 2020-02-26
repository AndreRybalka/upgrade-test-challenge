package pages.payment;

import core.base.AbstractBasePage;
import core.base.controls.BaseWebElement;
import core.base.controls.WebCheckbox;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static core.WebDriverFactory.getDriver;
import static core.contsants.Delays.MEDIUM_DELAY;
import static core.utils.ConfigVariables.CA;

import static core.utils.WaitingUtils.sleep;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
@Profile({ CA})
public class PayPalDetailsBlock extends AbstractBasePage {

    @FindBy(css = "[aria-describedby='payPalBillingSameAsShipping-validation']")
    private WebCheckbox payPalSameBillingAddressCheckbox;

    @FindBy(css = "#payPal-contents .u-reset-paragraph")
    private BaseWebElement payPalRules;

    @FindBy(css = "#paypal-button iframe")
    private BaseWebElement payPalButtonFrame;

    @FindBy(className = "paypal-button")
    private BaseWebElement payPalButton;


    public WebCheckbox getPayPalSameBillingAddressCheckbox() {
        return payPalSameBillingAddressCheckbox;
    }

    public BaseWebElement getPayPalRules() {
        return payPalRules;
    }

    public BaseWebElement getPayPalButtonFrame() {
        return payPalButtonFrame;
    }

    public BaseWebElement getPayPalButton() {
        return payPalButton;
    }

    public void clickPayPalButton() {
        sleep(MEDIUM_DELAY);
        getPayPalButtonFrame().waitForElementPresent();
        getPayPalButtonFrame().waitElementBeNotStale();
        getPayPalButtonFrame().switchTo();
        getPayPalButton().waitForElementPresent().waitForElementToBeClickable();
        getPayPalButton().click();
        getDriver().switchTo().parentFrame();
    }
}
