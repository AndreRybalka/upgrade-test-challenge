package pages.payment;

import core.base.AbstractBaseComponent;
import core.base.controls.BaseWebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static core.utils.ConfigVariables.CA;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
@Profile({CA})
public class CollapsedBlocks extends AbstractBaseComponent {

    @FindBy(css = ".c-editable-group__payment-done g")
    private BaseWebElement thirdPartyPaymentIcon;

    @FindBy(css = ".c-editable-group__payment-done text")
    private BaseWebElement thirdPartyPaymentTitle;



    public BaseWebElement getThirdPartyPaymentIcon() {
        return thirdPartyPaymentIcon;
    }

    public BaseWebElement getThirdPartyPaymentTitle() {
        return thirdPartyPaymentTitle;
    }
}