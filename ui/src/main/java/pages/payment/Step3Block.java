package pages.payment;

import core.base.AbstractBasePage;
import core.base.controls.BaseWebElement;
import core.base.controls.WebInput;
import core.base.controls.WebRadio;
import core.base.controls.WebSelect;
import core.testdata.ItemOrderDetails;
import core.testdata.models.preparedData.Address;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

import static core.SLF4JLogger.info;
import static core.contsants.Delays.LONG_DELAY;
import static core.contsants.Delays.SHORT_DELAY;
import static core.utils.ConfigVariables.CA;
import static core.utils.WaitingUtils.sleep;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;


@Component
@Scope(SCOPE_PROTOTYPE)
@Profile({CA})
public class Step3Block extends AbstractBasePage {

    @Autowired
    @Lazy
    private CreditCardDetailsBlock creditCardDetailsBlock;

    @Autowired
    @Lazy
    private PayPalDetailsBlock payPalDetailsBlock;

    @Autowired
    @Lazy
    private VisaCheckoutDetailsBlock visaCheckoutDetailsBlock;

    @Autowired
    @Lazy
    private InteracDetailsBlock interacDetailsBlock;


    @FindBy(xpath = "//div[contains(@class,'grid--with-spacing')]//div[contains(@data-qa-id,'payment-method')]/label")
    private List<WebRadio> paymentMethods;

    @FindBy(xpath = "//div[contains(@class,'grid--with-spacing')]/div[@aria-hidden='false']//input[contains(@id,'Line1')]")
    private WebInput addressLineInput;

    @FindBy(xpath = "//div[contains(@class,'grid--with-spacing')]/div[@aria-hidden='false']//ol[contains(@id,'Line1-results')]/li")
    private List<BaseWebElement> addressDropdownList;

    @FindBy(xpath = "//div[contains(@class,'grid--with-spacing')]/div[@aria-hidden='false']//input[contains(@id,'honeNumber')]")
    private WebInput phoneNumberInput;

    @FindBy(xpath = "//div[@data-qa-id='credit-card-billing-address-summary']//input[@name='firstName']")
    private WebInput billingFirstNameInput;

    @FindBy(xpath = "//div[@data-qa-id='credit-card-billing-address-summary']//input[@name='lastName']")
    private WebInput billingLastNameInput;

    @FindBy(xpath = "//div[@data-qa-id='credit-card-billing-address-summary']//input[@name='addressLine2']")
    private WebInput billingApartmentInput;

    @FindBy(xpath = "//div[@data-qa-id='credit-card-billing-address-summary']//input[@name='city']")
    private WebInput billingCityInput;

    @FindBy(xpath = "//div[@data-qa-id='credit-card-billing-address-summary']//input[@id='zipCode']")
    private WebInput billingPostalInput;

    @FindBy(id = "country")
    private WebSelect country;



    public CreditCardDetailsBlock getCreditCardDetailsBlock() {
        return creditCardDetailsBlock;
    }

    public PayPalDetailsBlock getPayPalDetailsBlock() {
        return payPalDetailsBlock;
    }

    public VisaCheckoutDetailsBlock getVisaCheckoutDetailsBlock() {
        return visaCheckoutDetailsBlock;
    }

    public InteracDetailsBlock getInteracDetailsBlock() {
        return interacDetailsBlock;
    }

    public WebInput getBillingFirstNameInput() {
        return billingFirstNameInput;
    }

    public WebInput getBillingLastNameInput() {
        return billingLastNameInput;
    }

    public WebInput getBillingApartmentInput() {
        return billingApartmentInput;
    }

    public WebInput getBillingCityInput() {
        return billingCityInput;
    }

    public WebInput getBillingPostalInput() {
        return billingPostalInput;
    }

    public List<WebRadio> getPaymentMethods() {
        return paymentMethods;
    }

    public WebInput getAddressLineInput() {
        return addressLineInput;
    }

    public List<BaseWebElement> getAddressDropdownList() {
        return addressDropdownList;
    }

    public WebInput getPhoneNumberInput() {
        return phoneNumberInput;
    }

    public WebSelect getCountry() {return country;}

    public void selectPaymentMethod(ItemOrderDetails.PaymentMethod paymentMethod) {
        sleep(SHORT_DELAY);
        for (BaseWebElement payment : getPaymentMethods()) {
            if (payment.getAttribute("for").equalsIgnoreCase(paymentMethod.getValue())) {
                payment.waitElementBeNotStale();
                payment.waitForElementPresent().waitForElementToBeClickable();
                payment.click();
                info("Payment method: '" + paymentMethod.getValue() + "' was successfully selected");
                break;
            }
        }
    }

    private void typeStreetDetails (Address address){
        waitForDocumentReadyState();
        if (getCountry().isPresent()){
            getCountry().selectByValue("ca");
        }
        getAddressLineInput().waitElementBeNotStale();
        getAddressLineInput().waitForElementToBeClickable();
        getAddressLineInput().clickJS();

        getAddressLineInput().waitForElementPresent().type(address.getStreet().substring(0, 5));
    }

    public void processBillingDetailsForStoreShillingOption(Address address){
        waitForDocumentReadyState();
        if (getCountry().isPresent()){
            getCountry().selectByValue("ca");
        }
        typeStreetDetails(address);
        sleep(LONG_DELAY);
        getAddressDropdownList().stream().findFirst().orElseThrow(java.util.NoSuchElementException::new).waitForElementPresent().click();
        getPhoneNumberInput().waitForElementPresent();
        getPhoneNumberInput().clickJS();
        getPhoneNumberInput().focus();
        getPhoneNumberInput().type(address.getPhoneNumber());

    }
}
