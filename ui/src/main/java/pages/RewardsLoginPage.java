package pages;

import core.base.AbstractBasePage;
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
public class RewardsLoginPage extends AbstractBasePage {


    @FindBy(className = "newLogin")
    private BaseWebElement rewardsLoginForm;

    @FindBy(css = "#ContentPane h2")
    private BaseWebElement rewardsLoginTitle;

    public BaseWebElement getRewardsLoginForm(){return rewardsLoginForm;}

    public BaseWebElement getRewardsLoginTitle(){return rewardsLoginTitle;}


}
