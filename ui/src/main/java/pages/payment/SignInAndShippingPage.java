package pages.payment;

import core.base.AbstractBasePage;
import core.base.controls.*;
import core.testdata.ItemOrderDetails;
import core.testdata.models.preparedData.Address;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

import static constants.Constants.EMPTY_STRING;
import static constants.Constants.VALUE;
import static core.SLF4JLogger.info;
import static core.contsants.Delays.SHORT_DELAY;
import static core.utils.ConfigVariables.CA;
import static core.utils.Utils.getCurrentCountry;
import static core.utils.WaitingUtils.sleep;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
@Profile({CA})
public class SignInAndShippingPage extends AbstractBasePage {

    @FindBy(xpath = "//div[@class = 'shipping-radio']/label[@class = 'input-inline']//span[not(@*)]")
    private List<WebRadio> deliveryOptions;

    @FindBy(xpath = "//input[@name='firstName']")
    private WebInput firstNameField;

    @FindBy(xpath = "//input[@name='lastName']")
    private WebInput lastNameField;

    @FindBy(xpath = "//input[@name='addressLine2']")
    private WebInput address2Field;

    @FindBy(xpath = "//input[@id='addressLine1']")
    private WebInput addressField;

    @FindBy(xpath = "//input[@id='city']")
    private WebInput cityNameField;

    @FindBy(xpath = "//select[@id='state']")
    private WebSelect stateField;

    @FindBy(xpath = "//input[@id='phoneNumber']")
    private WebInput phoneSection1Field;

    @FindBy(id = "qas-interaction-dialog")
    private WebCheckbox acceptTypedAddressWindow;

    @FindBy(id = "qas_interaction_2")
    private WebCheckbox acceptTypedAddressWindowAcceptButton;

    @FindBy(xpath = "//input[@id= 'savedAddress-add-new']")
    private BaseWebElement addNewAddressButton;

    @FindBy(xpath = "//ol[@id = 'addressLine1-results']//li")
    private List<BaseWebElement> addressDropdownList;

    @FindBy(id = "addressLine2")
    private WebInput apartmentInput;

    @FindBy(id = "state")
    private WebSelect provinceDropDown;

    @FindBy(xpath = "//input[@id='zipCode']")
    private WebInput postalCodeInput;

    @FindBy(id = "extension")
    private WebInput extensionInput;

    @FindBy(id = "phoneNumber")
    private WebInput phoneNumberInput;

    @FindBy(id = "country")
    private WebSelect country;


    public WebInput getFirstNameField() {
        return firstNameField;
    }

    public WebInput getLastNameField() {
        return lastNameField;
    }

    public WebInput getAddress2Field() {
        return address2Field;
    }

    public WebInput getAddressField() {
        return addressField;
    }

    public List<BaseWebElement> getAddressDropdownList() {
        return addressDropdownList;
    }

    public WebSelect getProvinceDropDown() {
        return provinceDropDown;
    }

    public WebInput getPostalCodeInput() {
        return postalCodeInput;
    }

    public WebInput getExtensionInput() {
        return extensionInput;
    }

    public WebInput getPhoneNumberInput() {
        return phoneNumberInput;
    }

    public WebInput getCityNameField() {
        return cityNameField;
    }

    public WebSelect getStateField() {
        return stateField;
    }

    public WebInput getPhoneSection1Field() {
        return phoneSection1Field;
    }

    public WebCheckbox getAcceptTypedAddressWindow() {
        return acceptTypedAddressWindow;
    }

    public WebCheckbox getAcceptTypedAddressWindowAcceptButton() {
        return acceptTypedAddressWindowAcceptButton;
    }

    public List<WebRadio> getDeliveryOptions() {return deliveryOptions;}

    public BaseWebElement getAddNewAddressButton() {
        return addNewAddressButton;
    }

    public WebInput getApartmentInput() {
        return apartmentInput;
    }

    public WebSelect getCountry() {
        return country;
    }




    public SignInAndShippingPage selectDeliveryOption(ItemOrderDetails.CollectOption option) {
        for (BaseWebElement collectOption : getDeliveryOptions()) {
            collectOption.waitForElementPresent();
            if (collectOption.isEnabled() && collectOption.getTrimText().contains(option.getValue())) {
                collectOption.click();
                info("Collect option: '" + option.getValue() + "' was found");
                break;
            }
        }
        return this;
    }

    public void fillAddressLogInUser(Address address) {
        getAddNewAddressButton().clickJS();
        commonStepsForUserAddressFill(address);
    }

    public void fillAddressLogOffUser(Address address) {
        commonStepsForUserAddressFill(address);
    }

    private void commonStepsForUserAddressFill(Address address) {
        typeStreetDetails(address);
        getAddressDropdownList().stream().findAny().orElseThrow(NoSuchElementException::new).waitForElementPresent().click();
        getApartmentInput().clear();
        fillAddressDetails(address);
    }

    private void typeStreetDetails(Address store) {
        waitForDocumentReadyState();
        if (getCountry().isPresent()){
            getCountry().selectByValue("ca");
        }
        getAddressField().waitElementBeNotStale();
        getAddressField().waitForElementToBeClickable();
        getAddressField().clickJS();
        getAddressField().waitForElementPresent().type(store.getStreet().substring(0, 6));
    }

    private void fillAddressDetails(Address address) {
        if (getProvinceDropDown().isPresent()) {
            getApartmentInput().clear();
            if (getProvinceDropDown().isPresent() &
                    getCurrentCountry().toLowerCase().contains(CA) ) {
                getProvinceDropDown().selectByVisibleText(address.getProvince());
            }
            sleep(SHORT_DELAY);
            if (getPostalCodeInput().getAttribute(VALUE).equals(EMPTY_STRING)) {
                getPostalCodeInput().type(address.getPostCode());
            }
            getExtensionInput().clear();
            sleep(SHORT_DELAY);
                getPhoneNumberInput().type(address.getPhoneNumber());
            }
        }
}
