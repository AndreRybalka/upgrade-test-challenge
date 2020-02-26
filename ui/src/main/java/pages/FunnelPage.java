package pages;

import core.base.AbstractBasePage;
import core.base.controls.BaseWebElement;
import core.base.controls.WebInput;
import core.base.controls.WebSelect;
import core.testdata.models.preparedData.Address;
import core.testdata.models.preparedData.User;
import core.utils.EmailUtil;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pages.pdp.LearnMoreBlock;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import static constants.Constants.*;
import static core.SLF4JLogger.info;
import static core.WebDriverFactory.getDriver;
import static core.contsants.Constants.Attributes.CLASS;
import static core.contsants.Delays.MEDIUM_DELAY;
import static core.helpers.WebElementsActionsHelper.*;
import static core.utils.ConfigVariables.CA;
import static core.utils.RegExrMatchSubstringUtil.matchString;
import static core.utils.WaitingUtils.sleep;
import static java.lang.String.valueOf;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

/**
 * Created by andrii.rybalka on 22/02/2020.
 */
@Component
@Scope(SCOPE_PROTOTYPE)
@Profile({CA})
public class FunnelPage extends AbstractBasePage{

    @FindBy(name = "desiredAmount")
    private WebInput desiredAmountInput;

    @FindBy(xpath = "//select[@data-auto='dropLoanPurpose']")
    private WebSelect purposeDropdown;

    @FindBy(xpath = "//button[@data-auto='CheckYourRate']")
    private BaseWebElement checkYourRateButton;

    public WebInput getDesiredAmountInput() {
        return desiredAmountInput;
    }

    public WebSelect getPurposeDropdown() {return purposeDropdown;}

    public BaseWebElement getCheckYourRateButton() {return checkYourRateButton;}

    public void inputDesiredAmountFieldValue(){
        getDesiredAmountInput().type(testData.getDesiredAmount());
    }

    public void selectPurpose (){
        waitForDocumentReadyState();
        if (getPurposeDropdown().isPresent()){
            selectByValue(getPurposeDropdown(), DEBT_CONSOLIDATION);
        }

    }

}
