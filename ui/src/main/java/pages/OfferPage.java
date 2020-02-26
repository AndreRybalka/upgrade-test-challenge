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
public class OfferPage extends AbstractBasePage{

    @FindBy(xpath = "//span[@data-auto='userLoanAmount']")
    private BaseWebElement userLoanAmount;

    @FindBy(xpath = "//span[@data-auto='defaultMonthlyPayment']")
    private BaseWebElement monthlyPayment;

    @FindBy(xpath = "//div[@data-auto='defaultLoanTerm']")
    private BaseWebElement defaultLoanTerm;

    @FindBy(xpath = "//div[@data-auto='defaultLoanInterestRate']")
    private BaseWebElement defaultLoanInterestRate;

    @FindBy(xpath = "//div[@data-auto='defaultMoreInfoAPR']")
    private BaseWebElement defaultMoreInfoAPR;

    @FindBy(xpath = "//div[@class='header-nav']")
    private BaseWebElement menuHamburger;

    @FindBy(xpath = "//ul[@class='list--unstyled header-nav-menu__items']")
    private BaseWebElement menuOptionsList;

    @FindBy(xpath = "//ul[@class='list--unstyled header-nav-menu__items']//li")
    private List<BaseWebElement> menuOptionsListElements;


    //a[@href='/funnel/logout']

    public BaseWebElement getUserLoanAmount() { return userLoanAmount; }

    public BaseWebElement getMonthlyPayment() { return monthlyPayment; }

    public BaseWebElement getDefaultLoanTerm() { return defaultLoanTerm; }

    public BaseWebElement getDefaultLoanInterestRate() { return defaultLoanInterestRate; }

    public BaseWebElement getDefaultMoreInfoAPR() { return defaultMoreInfoAPR; }

    public BaseWebElement getMenuHamburger() { return menuHamburger; }

    public BaseWebElement getMenuOptionsList() { return menuOptionsList; }

    public List<BaseWebElement> getMenuOptionsListElements() { return menuOptionsListElements; }

    public String getUserLoanAmountValue() {
        String value = getUserLoanAmount().getPriceText();
        return value;
    }

    public String getMonthlyPaymentValue() {
        String value = getMonthlyPayment().getPriceText();
        return value;
    }

    public String getDefaultLoanTermValue() {
        String value = getDefaultLoanTerm().getPriceText();
        return value;
    }

    public String getDefaultLoanInterestRateValue(){
        String value = getDefaultLoanInterestRate().getPriceText();
        return value;
    }

    public String getDefaultMoreInfoAPRValue(){
        String value = getDefaultMoreInfoAPR().getPriceText();
        return value;
    }

    public void signOut(){
        getMenuHamburger().waitForElementPresent();
        getMenuHamburger().waitForElementToBeClickable();
        getMenuHamburger().click();
        getMenuOptionsList().waitForElementPresent();
        getMenuOptionsList().waitForElementToBeClickable();
        getMenuOptionsListElements().get(1).waitForElementToBeClickable();
        getMenuOptionsListElements().get(1).click();
    }
}
