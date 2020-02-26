package pages.pdp;

import core.base.controls.BaseWebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import core.base.AbstractBaseComponent;

import static core.utils.ConfigVariables.CA;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
@Profile({CA})
public class LearnMoreBlock extends AbstractBaseComponent {

    @FindBy(xpath = "//div[contains(@class,'area--top-left')]//button[contains(@class,'btn--primary')]")
    private BaseWebElement learnMoreLink;

    @FindBy(xpath = "//div[contains(@class,'centered c-content')]//span[@class='c-markdown']")
    private BaseWebElement centralBlockInfo;

    public BaseWebElement getLearnMoreLink() {
        return learnMoreLink;
    }

    public BaseWebElement getCentralBlockInfo() {
        return centralBlockInfo;
    }

}
