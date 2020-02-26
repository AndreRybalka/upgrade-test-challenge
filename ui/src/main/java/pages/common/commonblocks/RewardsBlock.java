package pages.common.commonblocks;

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
public class RewardsBlock  extends AbstractBaseComponent {


    @FindBy(css = ".o-rewards-banner")
    private BaseWebElement rewardsBlock;

    @FindBy(xpath = "//a[contains(@class, 'u-btn--secondary') and contains(text(), 'Sign up!')]")
    private BaseWebElement rewardsSignUpButton;


    public BaseWebElement getRewardsBlock(){return rewardsBlock;}

    public BaseWebElement getRewardsSignUpButton(){return rewardsSignUpButton;}

}
