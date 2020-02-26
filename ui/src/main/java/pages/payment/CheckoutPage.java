package pages.payment;

import core.base.AbstractBasePage;
import core.base.controls.BaseWebElement;
import core.base.controls.WebInput;
import core.base.controls.WebRadio;
import core.testdata.ItemOrderDetails;
import core.testdata.models.preparedData.Address;
import core.testdata.models.preparedData.CreditCard;
import core.testdata.models.preparedData.User;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pages.cart.CartPage;
import pages.loginorcreateaccount.LogInPage;

import java.util.List;

import static constants.Constants.ALL_AFTER_DIGIT_PATTERN;
import static constants.Constants.FOR_ATTROBUTE;
import static core.SLF4JLogger.info;
import static core.SLF4JLogger.log;
import static core.contsants.Delays.LONG_DELAY;
import static core.utils.ConfigVariables.CA;
import static core.utils.RegExrMatchSubstringUtil.matchString;
import static core.utils.WaitingUtils.sleep;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;


@Component
@Scope(SCOPE_PROTOTYPE)
@Profile({CA})
public class CheckoutPage extends AbstractBasePage {

    @Autowired
    @Lazy
    private Step1Block step1Block;

    @Autowired
    @Lazy
    private Step2Block step2Block;

    @Autowired
    @Lazy
    private Step3Block step3Block;

    @Autowired
    @Lazy
    private SignInAndShippingPage signInAndShippingPage;

    @Autowired
    @Lazy
    private CollapsedBlocks сollapsedBlocks;

    @Autowired
    @Lazy
    private CartPage cartPage;

    @Autowired
    @Lazy
    private LogInPage logInPage;


    @FindBy(xpath = "//div[@class='o-section--primary']")
    private WebInput checkoutPageMainSection;

    @FindBy(xpath = "//div[@class = 'o-box-12' and position() = '1' and not (.//h4) and .//button]")
    private BaseWebElement useThisAddressForm;

    @FindBy(xpath = "//button[starts-with(@class, 'u-btn u-btn--secondary')]")
    private BaseWebElement useThisAddressButton;

    @FindBy(xpath = "//div[@class = 'c-loading c-loading--light']")
    private BaseWebElement dotsLoading;

    @FindBy(xpath = "//button[contains(@class, '--not-mobile') and not(contains(@class, 'hidden')) or contains(@class,'--has-wide-padding')]")
    private BaseWebElement abstractButton;

    @FindBy(xpath = "//div[@aria-labelledby='modal-title']//ul[contains(@class,'c-address-validation')]/li[1]//input[@type='radio']")
    private WebRadio suggestedAddress;

    @FindBy(xpath = "//div[@aria-labelledby='modal-title']//ul[contains(@class,'c-address-validation')]/li[1]//div[@class='u-large-body-2']")
    private BaseWebElement suggestedAddressText;

    @FindBy(css = "[data-qa-id='place-order-button']")
    private BaseWebElement placeOrderButton;

    @FindBy(xpath = "//button[contains(@aria-label,'submission in progress')]")
    private BaseWebElement continueDotsLoading;

    @FindBy(id = "savedPaymentMethod-addNew")
    private BaseWebElement addNewSavedPayment;

    @FindBy(xpath = ".//*[contains(@data-qa-id, 'saved-payment-radio-')]/input")
    private WebRadio firstSavedPayment;

    @FindBy(xpath = "//div[@data-qa-id='cart-summary']")
    private BaseWebElement subTotalForm;

    @FindBy(xpath = "//td[@data-qa-id='cart-summary-subtotal-value']")
    private BaseWebElement subTotalValue;

    @FindBy(xpath = "//ul[@data-qa-id='product-list']/li//span[@data-qa-id='product-final-price']")
    private List<BaseWebElement> productsFinalPrices;

    @FindBy(xpath = "//td[@data-qa-id='tax-amount']")
    private BaseWebElement taxesValue;

    @FindBy(xpath = "//div[@data-qa-id='cart-summary']//span[@data-qa-id='cart-summary-total-value']")
    private BaseWebElement totalPrice;

    @FindBy(xpath = "//button[@data-qa-cart-summary-button-open-add-promotion-form]")
    private BaseWebElement promoCodeEnteringLink;

    @FindBy(xpath = "//*[contains(@class, 'c-login-header__btn--sign-in') or contains(@class,'sign-in-line__btn')]")
    private BaseWebElement signInButtonOrLink;

    @FindBy(css = ".c-header-light__link .u-link")
    private BaseWebElement continueShoppingLink;

    @FindBy(id="billingSameAsShipping")
    private WebRadio sameAdressCheckbox;

    @FindBy(id="payPalBillingSameAsShipping")
    private WebRadio sameAddressCheckboxPayPal;





    public Step2Block getStep2Block() {
        return step2Block;
    }

    public SignInAndShippingPage getSignInAndShippingPage() {
        return signInAndShippingPage;
    }

    public Step3Block getStep3Block() {
        return step3Block;
    }

    public Step1Block getStep1Block() {
        return step1Block;
    }

    public CollapsedBlocks getСollapsedBlocks() {
        return сollapsedBlocks;
    }

    public CartPage getCartPage() {
        return cartPage;
    }

    public LogInPage getLogInPage() {
        return logInPage;
    }

    public WebRadio getSameAdressCheckbox() {return sameAdressCheckbox ;}

    public WebRadio getSameAddressCheckboxPayPal() {return sameAddressCheckboxPayPal; }

    public BaseWebElement getUseThisAddressButton() {
        return useThisAddressButton;
    }

    public BaseWebElement getDotsLoading() {
        return dotsLoading;
    }

    public WebInput getCheckoutPageMainSection() {
        return checkoutPageMainSection;
    }

    public BaseWebElement getUseThisAddressForm() {
        return useThisAddressForm;
    }

    public BaseWebElement getAbstractButton() {
        return abstractButton;
    }

    public BaseWebElement getPlaceOrderButton() {
        return placeOrderButton;
    }

    public WebRadio getSuggestedAddress() {
        return suggestedAddress;
    }

    public BaseWebElement getSuggestedAddressText() {
        return suggestedAddressText;
    }

    public BaseWebElement getContinueDotsLoading() {
        return continueDotsLoading;
    }

    public BaseWebElement getAddNewSavedPayment() {
        return addNewSavedPayment;
    }

    public WebRadio getFirstSavedPayment() {
        return firstSavedPayment;
    }

    public BaseWebElement getSubTotalForm() {
        return subTotalForm;
    }

    public BaseWebElement getSubTotalValue() {
        return subTotalValue;
    }

    public List<BaseWebElement> getProductsFinalPrices() {
        return productsFinalPrices;
    }

    public BaseWebElement getTaxesValue() {
        return taxesValue;
    }

    public BaseWebElement getTotalPrice() {
        return totalPrice;
    }


    public BaseWebElement getPromoCodeEnteringLink() {
        return promoCodeEnteringLink;
    }

    public BaseWebElement getSignInButtonOrLink() {
        return signInButtonOrLink;
    }

    public BaseWebElement getContinueShoppingLink() {
        return continueShoppingLink;
    }

    private int counter = 0;

    public CheckoutPage fillShippingDetailAsLoginUser(Address address) {
        getSignInAndShippingPage().fillAddressLogInUser(address);
        fillShippingDetailsCommonSteps();
        return this;
    }

    public CheckoutPage fillShippingDetailAsGuestUser(Address address) {
        getSignInAndShippingPage().fillAddressLogOffUser(address);
        fillShippingDetailsCommonSteps();
        return this;
    }

    public CheckoutPage selectDeliveryOption(ItemOrderDetails.CollectOption option) {
        getStep2Block().selectDeliveryOption(option);
        return this;
    }

    private void fillShippingDetailsCommonSteps() {
        getStep2Block().selectShippingMethod();
        pressContinueButton();
        handleAddressPopUpIfPresent();
    }

    public CheckoutPage pressContinueButton() {
        clickContinueButton(testData.getPaymentButtonTitle());
        getUseThisAddressForm().waitForElementPresent();
        if (getUseThisAddressForm().isPresent()) {
            getUseThisAddressButton().waitElementBeNotStale();
            getUseThisAddressButton().waitForElementToBeClickable();
            getUseThisAddressButton().click();
        }
        return this;
    }

    public CheckoutPage clickContinueButton(String buttonTitle) {
        getDotsLoading().waitForElementNotPresent();
        getAbstractButton().waitForAttributeContains("aria-disabled", "false");
        if (getAbstractButton().getTrimText().equalsIgnoreCase(buttonTitle)) {
            getAbstractButton().waitForElementToBeClickable();
            getAbstractButton().click();
            info("'" + buttonTitle + "' button was pressed");
        } else {
            throw new AssertionError("There was no '" + buttonTitle + "' button");
        }
        return this;
    }

    public String handleAddressPopUpIfPresent() {
        String address = "";
        if (getUseThisAddressForm().isPresent() && getSuggestedAddress().isEnabled()) {
            address = getSuggestedAddressText().getTrimText();
            getSuggestedAddress().click();
            getUseThisAddressButton().waitForElementToBeClickable();
            getUseThisAddressButton().click();
        }
        return address;
    }

    public CheckoutPage selectPaymentMethod(ItemOrderDetails.PaymentMethod paymentMethod) {
        getStep3Block().selectPaymentMethod(paymentMethod);
        return this;
    }

    public WebRadio getPaymentMethodElement(ItemOrderDetails.PaymentMethod paymentMethod) {
        return getStep3Block().getPaymentMethods().stream()
                .filter(paymentWebRadio ->
                        paymentWebRadio.getAttribute(FOR_ATTROBUTE)
                                .equalsIgnoreCase(paymentMethod.getValue()))
                .findFirst().get();
    }

    public CheckoutPage fillCreditCardDetails(CreditCard card) {
        getStep3Block().getCreditCardDetailsBlock().fillCreditCardDetails(card);
        info("Credit card details was successfully populated");
        return this;
    }

    public CheckoutPage clickReviewOrderButton() {
        clickContinueButton(testData.getReviewOrderButtonTitle());
        getAbstractButton().waitForElementNotPresent();
        return this;
    }

    public CheckoutPage placeOrder() {
        waitForDocumentReadyState();
        getPlaceOrderButton().waitForElementToBeClickable();
        getPlaceOrderButton().clickJS();
        info("Order was successfully placed");
        return this;
    }

    public CheckoutPage fillUserDataNotLoggedInUser(User user) {
        getStep1Block().fillUserName(user);
        getStep1Block().getEmailInput().type(user.getEmailAddress());
        String buttonName = testData.getShippingButtonTitle();
        clickContinueButton(buttonName);
        waitForContinueButtonNotPresent(buttonName);
        waitWhileLoadingDotsBeNotPresent();
        log("User data was successfully filled");
        sleep(LONG_DELAY);
        if (getStep2Block().isContinueAsGuestButtonPresent()) {
            while (getStep2Block().isContinueAsGuestButtonPresent() | counter > 5) {
                getStep2Block().clickOnContinueAsGuestButton();
                ++counter;
                log("'Continue as a quest' button was pushed at " + counter + " time");
            }
        }
        return this;
    }

    public CheckoutPage fillShippingDetailsWithoutContinuePressingLoggedOffUser(Address address) {
        getSignInAndShippingPage().fillAddressLogOffUser(address);
        getStep2Block().selectShippingMethod();
        return this;
    }

    private void waitForContinueButtonNotPresent(String buttonTitle) {
        if (getAbstractButton().isPresent() && getAbstractButton().getTrimText().equalsIgnoreCase(buttonTitle)) {
            getAbstractButton().waitForTextInElementNotPresent(buttonTitle);
        }
    }

    private void waitWhileLoadingDotsBeNotPresent() {
        getContinueDotsLoading().waitForElementNotPresent();
    }

    public CheckoutPage fillStoreDetails(Address address) {
        getStep2Block().fillStoreDetails(address);
        if (getAbstractButton().isPresent()) {
            getAbstractButton().waitForElementToBeClickable();
            clickContinueButton(testData.getStoreButtonTitle());
        }
        info("Collect in store option details was successfully filled");
        return this;
    }

    public void selectToAddNewSavedPaymentRadio() {
        if (getUseThisAddressForm().isPresent()) {
            getUseThisAddressButton().click();
        }
        getAddNewSavedPayment().waitElementBeNotStale();
        getAddNewSavedPayment().click();
    }

    public void selectFirstSavedPayment() {
        if (!getFirstSavedPayment().isSelected()) {
            getFirstSavedPayment().click();
        }
    }

    public double getProductsPricesAndConvertToDouble(List<BaseWebElement> finalPrices){
        double result = 0;
        for (BaseWebElement price: finalPrices){
           result += Double.parseDouble(matchString(ALL_AFTER_DIGIT_PATTERN, price.getTrimText()));
        }
        return result;
    }













}
