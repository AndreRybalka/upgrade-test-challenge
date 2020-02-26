package pages.loginorcreateaccount;

import core.base.AbstractBasePage;
import core.base.controls.BaseWebElement;
import core.base.controls.WebInput;
import core.base.controls.WebSelect;
import core.testdata.models.preparedData.Address;
import core.testdata.models.preparedData.CreditCard;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static constants.Constants.VALUE;
import static core.SLF4JLogger.info;
import static core.contsants.Delays.MEDIUM_DELAY;
import static core.contsants.Delays.SHORT_DELAY;
import static core.helpers.WebElementsActionsHelper.backToDefaultContent;
import static core.utils.ConfigVariables.CA;
import static core.utils.WaitingUtils.sleep;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
@Profile({CA})
public class MyAccountPage extends AbstractBasePage {

    @FindBy(xpath = "//div[contains(@class,'c-my-account o-section-wrapper')]")
    private BaseWebElement myAccountMainSection;

    @FindBy(xpath = "//nav[@aria-label='Sidebar']/ul/li[contains(@class,'link-list-item')]/*")
    private List<BaseWebElement> sidebarItems;

    @FindBy(xpath = "//div[@data-qa-id='message-bar' and @aria-live='polite']")
    private BaseWebElement thanksForSigningBar;

    @FindBy(xpath = "//div[@data-qa-id='message-bar' and @aria-live='polite']//button[@data-qa-id='message-bar-box-close']")
    private BaseWebElement thanksForSigningBarCloseButton;

    @FindBy(xpath = ".//*[contains(@data-qa-id,'payment-method-card-remove-button-payment-method-')]")
    private List<BaseWebElement> removeCardLinks;

    @FindBy(xpath = "//main[@data-qa-id='overlay-modal']//button[contains(@class,'primary')]")
    private BaseWebElement confirmButton;

    @FindBy(xpath = ".//*[@data-qa-id='saved-payment-list-item']")
    private List<BaseWebElement> savedPaymentItem;

    @FindBy(xpath = ".//*[@data-qa-id='add-new-saved-payment-component']")
    private BaseWebElement addNewCardOverlay;

    @FindBy(name = "credit-card-number")
    private WebInput cardNumber;

    @FindBy(id = "creditCardExpiration")
    private WebInput expDate;

    @FindBy(id = "creditCardCvc")
    private WebInput cvcCode;

    @FindBy(id = "addressLine1")
    private WebInput streetAddress;

    @FindBy(xpath = "//ol[@id = 'addressLine1-results']//li")
    private List<BaseWebElement> addressDropdownList;

    @FindBy(xpath = ".//*[@data-qa-select='true' and @id='state']")
    private WebSelect province;

    @FindBy(xpath = "//*[contains(@id,'zipCode')]")
    private WebInput postalCode;

    @FindBy(id = "phoneNumber")
    private WebInput phoneNumber;

    @FindBy(xpath = ".//*[@data-qa-id='add-saved-payment-form-submit-button']")
    private BaseWebElement addACardButton;

    @FindBy(xpath = ".//*[@data-qa-id = 'add-saved-payment-button' ]")
    private BaseWebElement addNewCreditCard;

    @FindBy(xpath = ".//a[contains(@href, '/account/address-book')]")
    private BaseWebElement addressBookLink;

    @FindBy(xpath = "//div[@class='c-editable-group__wrapper-inner']//button[@data-qa-id='edit-button']")
    private List<BaseWebElement> addressBookEditButtons;

    @FindBy(xpath = "//div[contains(@class,'c-editable-group--is-active')]//button[contains(@class,'t-link-red')]")
    private BaseWebElement addressBookDeleteButton;

    @FindBy(xpath = ".//*[@data-qa-id = 'add-new-saved-payment-component' ]//button")
    private BaseWebElement addNewCreitCard;

    @FindBy(xpath = ".//*[contains(@data-qa-id, 'saved-payment-is-primary-card-label')]")
    private BaseWebElement primarySavedPayment;

    @FindBy(xpath = ".//*[contains(@data-qa-id, 'payment-method-card-primary-button-payment-method-')]")
    private List<BaseWebElement> setAsPrimaryPayment;

    @FindBy(css = ".c-sidebar li.c-sidebar-link-list-item button.u-btn")
    private BaseWebElement signOutButton;

    @FindBy(id = "country")
    private WebSelect country;

    @FindBy(xpath = "//div[@data-qa-id = 'credit-card-number-component']/div")
    private WebInput creditCardNumberPCI;



    public List<BaseWebElement> getAddressBookEditButtons() {
        return addressBookEditButtons;
    }

    public BaseWebElement getAddressBookDeleteButton() {
        return addressBookDeleteButton;
    }

    public BaseWebElement getThanksForSigningBar() {
        return thanksForSigningBar;
    }

    public BaseWebElement getMyAccountMainSection() {
        return myAccountMainSection;
    }

    public List<BaseWebElement> getSidebarItems() {
        return sidebarItems;
    }

    public BaseWebElement getThanksForSigningBarCloseButton() {
        return thanksForSigningBarCloseButton;
    }

    public List<BaseWebElement> getRemoveCardLinks() {
        return removeCardLinks;
    }

    public BaseWebElement getConfirmButton() {
        return confirmButton;
    }

    public List<BaseWebElement> getSavedPaymentItem() {
        return savedPaymentItem;
    }

    public BaseWebElement getAddNewCardOverlay() {
        return addNewCardOverlay;
    }

    public WebInput getCardNumber() {
        return cardNumber;
    }

    public WebInput getExpDate() {
        return expDate;
    }

    public WebInput getCvcCode() {
        return cvcCode;
    }

    public WebInput getStreetAddress() {
        return streetAddress;
    }

    public List<BaseWebElement> getAddressDropdownList() {
        return addressDropdownList;
    }

    public WebSelect getProvince() {
        return province;
    }

    public WebInput getPostalCode() {
        return postalCode;
    }

    public WebInput getPhoneNumber() {
        return phoneNumber;
    }

    public BaseWebElement getAddACardButton() {
        return addACardButton;
    }

    public BaseWebElement getAddNewCreditCard() { return addNewCreditCard; }

    public BaseWebElement getAddressBookLink() { return addressBookLink; }

    public BaseWebElement getAddNewCreitCard() {
        return addNewCreitCard;
    }

    public BaseWebElement getPrimarySavedPayment() {
        return primarySavedPayment;
    }

    public List<BaseWebElement> getSetAsPrimaryPayment() {
        return setAsPrimaryPayment;
    }

    public BaseWebElement getSignOutButton() {
        return signOutButton;
    }

    public WebSelect getCountry() {
        return country;
    }

    public WebInput getCardPCI() {
        return creditCardNumberPCI;
    }
    

    public void closeThanksForSigningBar(){
        getThanksForSigningBarCloseButton().waitForElementToBeClickable();
        getThanksForSigningBarCloseButton().click();
    }

    public List<String> getAllSidebarLinkNames() {
        List<String> itemsName = new ArrayList<>();
        for (BaseWebElement element : getSidebarItems()) {
            System.out.println(element.getTrimText());
            itemsName.add(element.getTrimText());
        }
        return itemsName;
    }

    public void clickOnLink(String linkName) {
        for (BaseWebElement element : getSidebarItems()) {
            if (element.getTrimText().equalsIgnoreCase(linkName)) {
                element.setName(linkName + " link");
                element.waitForElementToBeClickable();
                element.click();
                return;
            }
        }
        throw new NoSuchElementException("There is no any "+linkName+" link available on the page");
    }

    public void removeAllSavedPayments() {
        while (getRemoveCardLinks().size() > 0) {
            getRemoveCardLinks().get(0).waitForElementToBeClickable();
            getRemoveCardLinks().get(0).click();
            getConfirmButton().clickJS();
            getConfirmButton().waitForElementNotPresent();
            sleep(MEDIUM_DELAY);
        }
    }

    public void expandAddNewCreditCardForm() {
        getAddNewCreditCard().clickJS();
    }


    public void addNewSavedCard(CreditCard card, Address address) {
        expandAddNewCreditCardForm();
        getAddNewCardOverlay().waitForElementPresent();
        fulfillSavedCardDetails(card, address);
    }

    public void fulfillSavedCardDetails(CreditCard card, Address address) {
        getCardPCI().switchToFrame();
        getCardNumber().waitForElementPresent().type(card.getNumber());
        backToDefaultContent();
        getExpDate().type(card.getExMonth() + card.getExYear());
        while (!getExpDate().getAttribute(VALUE).equals(card.getExMonth()+"/"+card.getExYear())){
            getExpDate().clear();
            getExpDate().type(card.getExMonth() + card.getExYear());
        }
        getCvcCode().type(card.getCvc());
        waitForDocumentReadyState();
        if (getCountry().isPresent()){
            getCountry().selectByValue("ca");
        }
        getStreetAddress().waitForElementPresent().type(address.getStreet().substring(0, 6));
        getAddressDropdownList().stream().findAny().orElseThrow(java.util.NoSuchElementException::new).waitForElementPresent().click();
        if (getProvince().isPresent()) {
            getProvince().selectByVisibleText(address.getProvince());
        }
        sleep(SHORT_DELAY);
        //if(getPostalCode().getAttribute(VALUE).equals(EMPTY_STRING)){
        //    getPostalCode().type(address.getPostCode());
        //}
        sleep(SHORT_DELAY);
        getPhoneNumber().clickJS();
        getPhoneNumber().focus();
        getPhoneNumber().type(address.getPhoneNumber());
        sleep(SHORT_DELAY);
        getAddACardButton().waitElementBeNotStale();
        getAddACardButton().clickJS();
        getAddACardButton().waitForElementNotPresent();
    }

    public void removeAllAddressBooks() {
        for (int i = getAddressBookEditButtons().size()-1; i > 1; i--) {
            getAddressBookEditButtons().get(i).waitForElementToBeClickable();
            getAddressBookEditButtons().get(i).clickJS();
            getAddressBookDeleteButton().waitForElementPresent().waitForElementToBeClickable();
            getAddressBookDeleteButton().clickJS();
            sleep(MEDIUM_DELAY);
        }
    }

    public void addFiveSavedPayments(CreditCard card, Address address) {
        while (getSavedPaymentItem().size() < 5) {
            addNewSavedCard(card, address);
            info(getSavedPaymentItem().size() + " saved payments are already in Saved Payments");
        }
    }

}

