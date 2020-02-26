package pages.payment;

import core.base.AbstractBaseComponent;
import core.base.controls.BaseWebElement;
import core.base.controls.WebCheckbox;
import core.base.controls.WebInput;
import core.testdata.models.preparedData.CreditCard;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


import static core.contsants.Constants.Attributes.VALUE;
import static core.helpers.WebElementsActionsHelper.backToDefaultContent;
import static core.utils.ConfigVariables.*;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
@Profile({ CA})
public class CreditCardDetailsBlock extends AbstractBaseComponent {

    @FindBy(xpath = "//form/input[@name='credit-card-number']")
    private WebInput numberInput;

    @FindBy(xpath = "//div[contains(@class,'grid--with-spacing')]/div[@aria-hidden='false']//input[@id='creditCardExpiration']")
    private WebInput expirationInput;

    @FindBy(xpath = "//div[contains(@class,'grid--with-spacing')]/div[@aria-hidden='false']//input[@id='creditCardCvc']")
    private WebInput cvcInput;

    @FindBy(css = "[aria-describedby='billingSameAsShipping-validation']")
    private WebCheckbox sameBillingAddressCheckbox;

    @FindBy(xpath = "//input[@name='credit-card-number']")
    private WebInput cardNumberInputPCI;

    @FindBy(xpath = "//div[@data-qa-id = 'credit-card-number-component']/div")
    private WebInput creditCardNumberPCI;



    public WebInput getExpirationInput() {
        return expirationInput;
    }

    public WebInput getCvcInput() {
        return cvcInput;
    }

    public WebInput getNumberInput() {
        return numberInput;
    }

    public WebCheckbox getSameBillingAddressCheckbox() {
        return sameBillingAddressCheckbox;
    }

    public WebInput getCardPCI() {
        return creditCardNumberPCI;
    }

    public WebInput getCardNumberInputPCI() {
        return cardNumberInputPCI;
    }



    public void fillCreditCardDetails(CreditCard card) {
        getCardPCI().switchToFrame();
        getNumberInput().type(card.getNumber());
        backToDefaultContent();
        getExpirationInput().type(card.getExMonth() + card.getExYear());
        getCvcInput().type(card.getCvc());
    }


}
