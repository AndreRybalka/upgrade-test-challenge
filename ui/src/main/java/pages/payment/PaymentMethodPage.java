package pages.payment;

import core.base.AbstractBasePage;
import core.base.controls.BaseWebElement;
import core.base.controls.WebInput;
import core.base.controls.WebRadio;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

import static core.SLF4JLogger.error;
import static core.SLF4JLogger.log;
import static core.testdata.ItemOrderDetails.BillingAddress;
import static core.utils.ConfigVariables.CA;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
@Profile({CA})
public class PaymentMethodPage extends AbstractBasePage {

    @FindBy(id = "address.firstName")
    private WebInput firstNameField;

    @FindBy(id = "address.surname")
    private WebInput lastNameField;

    @FindBy(id = "address.line2")
    private WebInput address2Field;

    @FindBy(id = "address.line1")
    private WebInput addressField;

    @FindBy(id = "address.company")
    private WebInput companyField;

    @FindBy(id = "address.townCity")
    private WebInput cityNameField;

    @FindBy(id = "address.postcode")
    private WebInput postCodeField;

    @FindBy(xpath = "//div[@class = 'smoke.payment-address-container']//div[@class = 'smoke.payment-column']")
    private List<WebRadio> billingAddressOptions;

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

    public WebInput getCompanyField() {
        return companyField;
    }

    public WebInput getCityNameField() {
        return cityNameField;
    }

    public WebInput getPostCodeField() {
        return postCodeField;
    }

    public List<WebRadio> getBillingAddressOptions() {
        return billingAddressOptions;
    }

    public WebRadio selectBillingAddress(BillingAddress billingAddress) {
        getBillingAddressOptions().stream().findAny().ifPresent(BaseWebElement::waitForElementPresent);
        for (WebRadio billingAddressElement : getBillingAddressOptions()) {
            billingAddressElement.waitElementBeNotStale();
            billingAddressElement.waitForElementPresent();
            if (billingAddressElement.isRadioSelected() && billingAddressElement.getTrimText().contains(billingAddress.getValue())) {
                log("Billing option : " + billingAddress.getValue() + " selected by default");
                return billingAddressElement;
            } else if (!billingAddressElement.isRadioSelected() && billingAddressElement.getTrimText().contains(billingAddress.getValue())) {
                return selectRadioIfItIsNotSelected(billingAddress, billingAddressElement);
            }
        }
        return radioWasNotSelected(billingAddress.getValue());
    }


    private WebRadio selectRadioIfItIsNotSelected(BillingAddress billingAddress, WebRadio billingAddressElement) {
        billingAddressElement.selectRadio();
        if (billingAddressElement.isRadioSelected()) {
            log("Billing option : " + billingAddress.getValue() + " was selected");
            return billingAddressElement;
        } else {
            return radioWasNotSelected(billingAddress.getValue());
        }
    }

    private WebRadio radioWasNotSelected(String radioValue) {
        error("Billing option :" + radioValue + " was found, but was NOT selected");
        throw new IllegalArgumentException("Billing option :" + radioValue + " was found, but was NOT selected or was NOT Found");
    }
}
