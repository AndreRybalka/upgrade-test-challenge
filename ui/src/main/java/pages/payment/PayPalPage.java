package pages.payment;


import core.base.AbstractBasePage;
import core.base.controls.BaseWebElement;
import core.base.controls.WebInput;
import core.testdata.models.preparedData.PayPal;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static core.SLF4JLogger.log;
import static core.WebDriverFactory.getDriver;
import static core.contsants.Delays.LONG_DELAY;
import static core.utils.WaitingUtils.sleep;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
public class PayPalPage extends AbstractBasePage {

    @FindBy(id = "spinner")
    private BaseWebElement loadingIcon;

    @FindBy(xpath = "//button[@id='btnLogin']")
    private BaseWebElement LogInButton;

    @FindBy(xpath = "//div[@id='main' and @role='main']")
    private BaseWebElement payPalFormFrame;

    @FindBy(xpath = "//button[@value='Next']")
    private BaseWebElement payPalNextButton;

    @FindBy(id = "email")
    private WebInput emailInput;

    @FindBy(id = "password")
    private WebInput passwordInput;

    @FindBy(id = "btnLogin")
    private BaseWebElement loginButton;

    @FindBy(id = "confirmButtonTop")
    private BaseWebElement payNowButton;

    @FindBy(xpath = "//iframe[contains(@name,'lightbox_container')]")
    private BaseWebElement payPalBackgroundFrame;

    @FindBy(xpath = "//iframe[@class = 'zoid-component-frame zoid-visible']")
    private BaseWebElement payPalButtonFrame;

    @FindBy(xpath = "//div[@class='buttons reviewButton']/button[@ng-click='continue()']")
    private BaseWebElement  wayToPayContinueButton;



    public BaseWebElement getLoadingIcon() {
        return loadingIcon;
    }

    public BaseWebElement getPayPalButtonFrame() {
        return payPalButtonFrame;
    }

    public BaseWebElement getLogInButton() {
        return LogInButton;
    }

    public BaseWebElement getPayPalFormFrame() {
        return payPalFormFrame;
    }

    public BaseWebElement getPayPalNextButton() {
        return payPalNextButton;
    }

    public WebInput getEmailInput() {
        return emailInput;
    }

    public WebInput getPasswordInput() {
        return passwordInput;
    }

    public BaseWebElement getLoginButton() {
        return loginButton;
    }

    public BaseWebElement getPayNowButton() {
        return payNowButton;
    }

    public BaseWebElement getPayPalBackgroundFrame() {
        return payPalBackgroundFrame;
    }

    public BaseWebElement getWayToPayContinueButton() {
        return wayToPayContinueButton;
    }





    public PayPalPage login(PayPal payPal) {
        getLoadingIcon().waitForElementNotPresent();
        getPayPalFormFrame().waitForElementPresent();
        log("Next button is present: " + getPayPalNextButton().isPresent() );
        getEmailInput().waitForElementPresent();
        getEmailInput().type(payPal.getEmail());
        if (getPayPalNextButton().isPresent()) {
            getPayPalNextButton().waitForElementToBeClickable();
            getPayPalNextButton().click();
        }
        getPasswordInput().type(payPal.getPassword());
        getLoginButton().waitForElementToBeClickable();
        getLoginButton().click();
        return this;
    }

    public void confirmPayment() {
        getDriver().switchTo().parentFrame();
        getLoadingIcon().waitForElementNotPresent();
        if (getWayToPayContinueButton().isPresent()){
            getWayToPayContinueButton().click();
            getLoadingIcon().waitForElementNotPresent();
        }
        getPayNowButton().waitForElementPresent();
        getPayNowButton().waitForElementToBeClickable();
        sleep(LONG_DELAY);
        getPayNowButton().click();
    }

    public void payWithPayPal(PayPal payPal) {
        switchToWindow();
        login(payPal).confirmPayment();
        switchToMainWindowWithoutClose();
        getPayPalBackgroundFrame().waitForElementNotPresent();
    }

    public void switchToPayPalButton() {
        getDriver().switchTo().frame(getPayPalButtonFrame().getWrappedElement());
    }
}
