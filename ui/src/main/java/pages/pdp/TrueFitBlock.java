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
public class TrueFitBlock extends AbstractBaseComponent {

    @FindBy(xpath = "//span[@class = 'tfc-button-wrapper']//div[@data-tip-id = 'nouser-tip']//td")
    private BaseWebElement findYourTrueFit;

    @FindBy(xpath = "//span[@id = 'tfc-auto-GenderQuestionOptionButton-Female']//td")
    private BaseWebElement genderConfirmationFemale;

    @FindBy(xpath = "//span[@id = 'tfc-auto-GenderQuestionOptionButton-Male']//td")
    private BaseWebElement genderConfirmationMale;

    @FindBy(xpath = "//span[@class = 'tfc-button option tfc-cfg-multi-option' and starts-with(@id, 'tfc-auto-CategoryQuestionOptionButton')]//td")
    private BaseWebElement fitMeForButton;

    @FindBy(xpath = "//div[@class = 'button-content']")
    private BaseWebElement nextButton;

    @FindBy(xpath = "//div[@class = 'classes-ajax-target']//td[@class = 'tfc-valign']")
    private BaseWebElement brandType;

    @FindBy(xpath = "//iframe[@id = 'tfc-fitrec-register-iframe']")
    private BaseWebElement trueFitIFrame;

    @FindBy(xpath = "//div[@class = 'main-title tfc-cfg-primary-title']")
    private BaseWebElement primaryTitleTrueFit;

    public BaseWebElement getFindYourTrueFit() {
        return findYourTrueFit;
    }

    public BaseWebElement getGenderConfirmationFemale() {
        return genderConfirmationFemale;
    }

    public BaseWebElement getGenderConfirmationMale() {
        return genderConfirmationMale;
    }

    public BaseWebElement getFitMeForButton() {
        return fitMeForButton;
    }

    public BaseWebElement getNextButton() {
        return nextButton;
    }

    public BaseWebElement getBrandType() {
        return brandType;
    }

    public BaseWebElement getTrueFitIFrame() {
        return trueFitIFrame;
    }

    public BaseWebElement getPrimaryTitleTrueFit() {
        return primaryTitleTrueFit;
    }
}
