package pages.payment;

import core.base.controls.BaseWebElement;
import core.utils.Utils;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import core.base.AbstractBasePage;

import java.util.regex.Matcher;

import static core.SLF4JLogger.info;
import static core.utils.ConfigVariables.CA;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
@Profile({CA})
public class OrderConfirmationPage extends AbstractBasePage {

    @FindBy(xpath = "//div[@data-qa-id = 'confirmation-subheading']/strong")
    private BaseWebElement orderNumber;

    @FindBy(xpath = "//div[@class = 'c-confirmation-header__subhead']//strong")
    private BaseWebElement orderNumberElement;

    @FindBy(className = "c-confirmation-header__subhead")
    private BaseWebElement confirmationMessage;



    public BaseWebElement getOrderNumber() {
        return orderNumber;
    }

    public BaseWebElement getOrderNumberElement() {
        return orderNumberElement;
    }

    public BaseWebElement getConfirmationMessage() {
        return confirmationMessage;
    }

    public String getOrderNumberText() {
        Matcher matcher = Utils.getMatcher(testData.getOrderNumberRegex(), getConfirmationMessage().getTrimText());
        if (matcher.matches()) {
            info("Order number was successfully found");
        }
        return matcher.group(3);
    }
}
