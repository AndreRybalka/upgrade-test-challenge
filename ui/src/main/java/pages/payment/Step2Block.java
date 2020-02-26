package pages.payment;

import core.base.AbstractBasePage;
import core.base.controls.BaseWebElement;
import core.base.controls.WebInput;
import core.base.controls.WebRadio;
import core.testdata.ItemOrderDetails;
import core.testdata.models.preparedData.Address;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static core.SLF4JLogger.error;
import static core.SLF4JLogger.info;
import static core.SLF4JLogger.log;
import static core.utils.ConfigVariables.CA;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
@Profile({CA})
public class Step2Block extends AbstractBasePage {

    @FindBy(xpath = "//label[contains(@class,'c-form-field-wrapper') and contains(@aria-describedby,'shippingMethod')]")
    private List<WebRadio> deliveryOptions;

    @FindBy(xpath = "//div[@aria-checked='true']//label[contains(@class,'c-form-field-wrapper') and contains(@aria-describedby,'shippingMethod')]")
    private BaseWebElement selectedDeliveryOption;

    @FindBy(xpath = "//div[@data-qa-checkout-step2-delivery-method]")
    private BaseWebElement deliveryMethodsGroup;

    @FindBy(css = "[data-qa-id='delivery-methods-option'] [data-qa-id='header']")
    private List<WebRadio> shippingMethods;

    @FindBy(xpath = " //div[contains(@class,'shipping-options-group')]")
    private BaseWebElement deliveryOptionsGroup;

    @FindBy(xpath = "//div[contains(@data-qa-id,'existing-user-form')]//div[contains(@data-qa-id,'continue-as-a-guest-button')]/button")
    private BaseWebElement continueAsGuestButton;

    @FindBy(id = "findStore")
    private WebInput storePostCodeInput;

    @FindBy(id = "findStore-list")
    private BaseWebElement autocomleteStoreAddressBlock;

    @FindBy(xpath = "//*[@data-qa-id= 'form-autocomplete-result-item-button' and .//span[@class = 'c-form-autocomplete-results__suggestion-content']]")
    private List<BaseWebElement> storeAddressesList;

    @FindBy(css = ".c-account-option .c-form-field-wrapper")
    private List<BaseWebElement> closestStoresList;

    @FindBy(xpath = "//div[contains(@class,'c-account-option')]//div[contains(@class,'option__children')]//div[contains(@class,'c-store-info--is-selected')]")
    private BaseWebElement selectedClosestStore;


    public List<WebRadio> getShippingMethods() {
        return shippingMethods;
    }

    public WebInput getStorePostCodeInput() {
        return storePostCodeInput;
    }

    public BaseWebElement getAutocomleteStoreAddressBlock() {
        return autocomleteStoreAddressBlock;
    }

    public List<WebRadio> getDeliveryOptions() {
        return deliveryOptions;
    }

    public BaseWebElement getDeliveryOptionsGroup() {
        return deliveryOptionsGroup;
    }

    public BaseWebElement getSelectedDeliveryOption() {
        return selectedDeliveryOption;
    }

    public BaseWebElement getDeliveryMethodsGroup() {
        return deliveryMethodsGroup;
    }

    public BaseWebElement getContinueAsGuestButton() {
        return continueAsGuestButton;
    }

    public List<BaseWebElement> getStoreAddressesList() {
        return storeAddressesList;
    }

    public List<BaseWebElement> getClosestStoresList() {
        return closestStoresList;
    }

    public BaseWebElement getSelectedClosestStore() {
        return selectedClosestStore;
    }

    public List<String> getDeliveryMethodsTitles() {
        return getShippingMethods().stream()
                .map(WebRadio::getTrimText)
                .map(elem -> elem.split(":")[0].trim())
                .collect(Collectors.toList());

    }

    public void selectDeliveryOption(ItemOrderDetails.CollectOption option) {
        for (BaseWebElement collectOption : getDeliveryOptions()) {
            collectOption.waitElementBeNotStale();
            collectOption.waitForElementPresent();
            if (collectOption.isDisplayed() &&
                    collectOption.getTrimText().contains(option.getValue())) {
                try {
                    collectOption.waitElementBeNotStale();
                    collectOption.waitForElementToBeClickable();
                    collectOption.click();
                    selectedDeliveryOption.waitForTextInElementPresent(option.getValue());
                } catch (WebDriverException e) {
                    log("Waiting for element to be clickable");
                    collectOption.click();
                    selectedDeliveryOption.waitForTextInElementPresent(option.getValue());
                    info("Collect option: '" + option.getValue() + "' was found");
                    break;
                }
            }
        }
    }

    public void selectShippingMethod() {
        for (WebRadio shippingMethod : getShippingMethods()) {
            if (shippingMethod.isEnabled()) {
                shippingMethod.click();
                info("Shipping method was successfully selected");
                break;
            }
        }
    }

    public boolean isContinueAsGuestButtonPresent() {
        return getContinueAsGuestButton().isPresent();
    }

    public void clickOnContinueAsGuestButton() {
        getContinueAsGuestButton().click();
    }

    public void fillStoreDetails(Address address) {
        int counter = 0;
        getStorePostCodeInput().waitForElementPresent();
        getStorePostCodeInput().clickJS();
        getStorePostCodeInput().waitElementBeNotStale();
        getStorePostCodeInput().typeWithCheck(address.getPostCode());
        getAutocomleteStoreAddressBlock().waitForElementToBeClickable();
        for (BaseWebElement storeAddress : getStoreAddressesList()) {
            BaseWebElement suggestedAddress = getStoreAddressesList().stream().findAny().get();
            suggestedAddress.waitForElementPresent().waitForElementToBeClickable();
            if (storeAddress.getTrimText().replaceAll("\\s+", " ")
                    .contains(address.getPostCode())) {
                storeAddress.clickJS();
                info("Store address with postcode: '" + address.getPostCode() + "' was successfully found");
                BaseWebElement closestStore = getClosestStoresList().stream().findAny().orElseThrow(NoSuchElementException::new);
                closestStore.waitForElementPresent();
                String selectedClosestStoresText = closestStore.getTrimText();
                closestStoreRadioButtonStatus(counter, closestStore);
                info(String.format("Closest store [%s] was successfully selected", selectedClosestStoresText));
                break;
            }
        }
    }

    private void closestStoreRadioButtonStatus(int counter, BaseWebElement closestStore) {
        while (!getSelectedClosestStore().isPresent() && counter < 3) {
            log("counter count : " + counter);
            closestStore.waitForElementToBeClickable();
            closestStore.click();
            ++counter;
        }
        if (counter == 3) {
            closestStore.clickJS();
        } else if (!getSelectedClosestStore().isPresent()) {
            error("Closest store isn`t selected yet. Counter number is " + counter);
            throw new IllegalArgumentException("Closest store isn`t selected yet.");
        }

    }
}
