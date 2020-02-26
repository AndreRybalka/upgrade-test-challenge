package pages.payment;

import core.base.AbstractBaseComponent;
import core.base.controls.BaseWebElement;
import core.base.controls.WebCheckbox;
import core.base.controls.WebInput;
import core.testdata.models.preparedData.Address;
import core.testdata.models.preparedData.User;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static core.utils.ConfigVariables.*;
import static core.utils.RegExrMatchSubstringUtil.replaceUselessString;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
@Profile({CA})
public class InteracDetailsBlock extends AbstractBaseComponent {

    @FindBy(xpath = "//div[@id='IOP-contents']//div[contains(@class, 'u-reset-paragraph')]")
    private BaseWebElement interacMessage;

    @FindBy(xpath = "//div[@id='IOP-contents']//div[contains(@class, 'u-reset-paragraph')]/div[position()=1]")
    private BaseWebElement interacFirstPartMessage;

    @FindBy(xpath = "//div[@id='IOP-contents']//div[contains(@class, 'u-reset-paragraph')]/div[position()=2]")
    private BaseWebElement interacBasePartMessage;

    @FindBy(css = ".u-text-italic-fine-print i")
    private BaseWebElement interacTrademarkMessage;

    @FindBy(css = ".u-text-italic-fine-print a")
    private BaseWebElement interacLearnMoreLink;

    @FindBy(css = "[aria-describedby='interacBillingSameAsShipping-validation']")
    private WebCheckbox interacSameBillingAddressCheckbox;

    @FindBy(xpath = "//div[@id='IOP-contents']//div[position()=3]")
    private BaseWebElement interacBillingAddress;

    @FindBy(xpath = "//ol[@id = 'interacAddressLine1-results']//li")
    private List<BaseWebElement> interacAddressDropdownList;

    public BaseWebElement getInteracBasePartMessage() {
        return interacBasePartMessage;
    }

    public BaseWebElement getInteracTrademarkMessage() {
        return interacTrademarkMessage;
    }

    public BaseWebElement getInteracLearnMoreLink() {
        return interacLearnMoreLink;
    }

    public WebCheckbox getInteracSameBillingAddressCheckbox() {
        return interacSameBillingAddressCheckbox;
    }

    public BaseWebElement getInteracMessage() {
        return interacMessage;
    }

    public BaseWebElement getInteracFirstPartMessage() {
        return interacFirstPartMessage;
    }
}
