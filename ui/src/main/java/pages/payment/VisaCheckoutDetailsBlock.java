package pages.payment;

import core.base.AbstractBasePage;
import core.base.controls.BaseWebElement;
import core.base.controls.WebInput;
import core.testdata.models.preparedData.VisaCheckoutAccount;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static core.contsants.Delays.MEDIUM_DELAY;
import static core.utils.ConfigVariables.CA;
import static core.utils.WaitingUtils.sleep;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;


@Component
@Scope(SCOPE_PROTOTYPE)
@Profile({ CA})
public class VisaCheckoutDetailsBlock extends AbstractBasePage {

    @FindBy(id = "visaCheckout-contents")
    private BaseWebElement visaCheckoutMessage;

    @FindBy(css = "#VisaCheckoutButton")
    private BaseWebElement visaCheckoutButton;

    @FindBy(css = ".o-box-12.u-text-italic-fine-print")
    private BaseWebElement visaCheckoutRedirectMessage;

    @FindBy(xpath = "//iframe[@frameborder='0' and @id and not(@scrolling='no')]")
    private BaseWebElement visaCheckoutIFrame;

    @FindBy(id = "user_name")
    private WebInput visaCheckoutEmailField;

    @FindBy(xpath = "//input[@class='viewButton-button' and @name= 'Continue']")
    private BaseWebElement visaCheckoutContinueButton;

    @FindBy(id = "password")
    private WebInput visaCheckoutPasswordField;

    @FindBy(xpath = "//input[@class='viewButton-button' and @name= 'Sign in to Visa Checkout']")
    private BaseWebElement visaCheckoutSignInButton;

    @FindBy(xpath = "//a[@aria-label='Continue without setting' and text()= 'No, thanks']")
    private BaseWebElement noStaySignedButton;



    public BaseWebElement getVisaCheckoutMessage() {
        return visaCheckoutMessage;
    }

    public BaseWebElement getVisaCheckoutButton() {
        return visaCheckoutButton;
    }

    public BaseWebElement getVisaCheckoutRedirectMessage() {
        return visaCheckoutRedirectMessage;
    }

    public BaseWebElement getVisaCheckoutIFrame() {
        return visaCheckoutIFrame;
    }

    public WebInput getVisaCheckoutEmailField() {
        return visaCheckoutEmailField;
    }

    public BaseWebElement getVisaCheckoutContinueButton() {
        return visaCheckoutContinueButton;
    }

    public WebInput getVisaCheckoutPasswordField() {
        return visaCheckoutPasswordField;
    }

    public BaseWebElement getVisaCheckoutSignInButton() {
        return visaCheckoutSignInButton;
    }

    public BaseWebElement getNoStaySignedButton() {
        return noStaySignedButton;
    }


    public void performPayment(VisaCheckoutAccount visaCheckoutAccount){
        getVisaCheckoutIFrame().waitForElementPresent();
        getVisaCheckoutIFrame().switchTo();
        sleep(MEDIUM_DELAY);
        getVisaCheckoutEmailField().type(visaCheckoutAccount.getEmail());
        getVisaCheckoutContinueButton().click();

        getVisaCheckoutPasswordField().waitForElementPresent();
        getVisaCheckoutPasswordField().waitElementBeNotStale();
        getVisaCheckoutPasswordField().type(visaCheckoutAccount.getPassword());
        getVisaCheckoutSignInButton().waitForElementPresent();
        getVisaCheckoutSignInButton().waitForElementToBeClickable();
        getVisaCheckoutSignInButton().click();
        getVisaCheckoutSignInButton().waitForElementNotPresent();

        getVisaCheckoutContinueButton().waitForElementToBeClickable();
        getVisaCheckoutContinueButton().click();
        getVisaCheckoutContinueButton().waitForElementNotPresent();

        if (getNoStaySignedButton().isPresent()){
            getNoStaySignedButton().waitForElementToBeClickable();
            getNoStaySignedButton().click();
        }
    }
}
