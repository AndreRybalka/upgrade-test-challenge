package pages;

import core.base.AbstractBasePage;
import core.base.controls.BaseWebElement;
import core.base.controls.WebInput;
import core.base.controls.WebRadio;
import core.base.controls.WebSelect;
import core.testdata.models.preparedData.User;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pages.loginorcreateaccount.signup.AbstractCreateAccountPage;
import pages.payment.SignInAndShippingPage;

import java.util.List;

import static constants.Constants.*;
import static core.helpers.WebElementsActionsHelper.selectByValue;
import static core.utils.ConfigVariables.CA;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
@Profile({CA})
public class AListPage extends AbstractBasePage {

    @Autowired
    @Lazy
    protected AbstractCreateAccountPage createAccountPage;

    @Autowired
    @Lazy
    private SignInAndShippingPage signInAndShippingPage;




    @FindBy(xpath = "//div[not(contains(@data-qa-id,'aListSignUp'))]/input[@id='firstName']")
    private WebInput upperFirstNameField;

    @FindBy(xpath = "//div[contains(@data-qa-id,'aListSignUp')]/input[@id='firstName']")
    private WebInput lowerFirstNameField;

    @FindBy(xpath = "//div[not(contains(@data-qa-id,'aListSignUp'))]/input[@id='lastName']")
    private WebInput upperLastNameField;

    @FindBy(xpath = "//div[contains(@data-qa-id,'aListSignUp')]/input[@id='lastName']")
    private WebInput lowerLastNameField;

    @FindBy(xpath = "//input[@id='newPassword']")
    private WebInput newPasswordField;

    @FindBy(xpath = "//input[@name='gender']")
    private List<WebRadio> genderSelect;

    @FindBy(xpath = "//select[@id='dateOfBirth-day']")
    private WebSelect birthdayDaySelect;

    @FindBy(xpath = "//select[@id='dateOfBirth-month']")
    private WebSelect birthdayMonthSelect;

    @FindBy(xpath = "//select[@id='dateOfBirth-year']")
    private WebSelect birthdayYearSelect;

    @FindBy(xpath = "//button[@data-qa-id='sign-up-button']")
    private BaseWebElement signUpButton;

    @FindBy(xpath = "//div[@class='c-a-list-sign-up o-container']")
    private BaseWebElement mainAlistSection;


    public AbstractCreateAccountPage getCreateAccountPage() {
        return createAccountPage;
    }

    public SignInAndShippingPage getSignInAndShippingPage() {
        return signInAndShippingPage;
    }


    public WebInput getUpperFirstNameField() {
        return upperFirstNameField;
    }

    public WebInput getUpperLastNameField() {
        return upperLastNameField;
    }

    public WebInput getNewPasswordField() {
        return newPasswordField;
    }

    public List<WebRadio> getGenderSelect() {
        return genderSelect;
    }

    public WebSelect getBirthdayDaySelect() {
        return birthdayDaySelect;
    }

    public WebSelect getBirthdayMonthSelect() {
        return birthdayMonthSelect;
    }

    public WebSelect getBirthdayYearSelect() {
        return birthdayYearSelect;
    }

    public WebInput getLowerFirstNameField() {
        return lowerFirstNameField;
    }

    public WebInput getLowerLastNameField() {
        return lowerLastNameField;
    }

    public BaseWebElement getSignUpButton() {
        return signUpButton;
    }

    public BaseWebElement getMainAlistSection() {
        return mainAlistSection;
    }


    public void createAListAccount(User user) {
        getUpperFirstNameField().type(user.getFirstName());
        getUpperLastNameField().type(user.getLastName());
        getCreateAccountPage().getMail().type(user.getEmailAddress());
        getNewPasswordField().type(user.getPassword());
        getCreateAccountPage().getConfirmPassword().type(user.getPassword());
        selectGender(user.getGender());
        getLowerFirstNameField().type(user.getFirstName());
        getLowerLastNameField().type(user.getLastName());
        getBirthdayDaySelect().selectByIndex(1);
        getBirthdayMonthSelect().selectByIndex(1);
        getBirthdayYearSelect().selectByIndex(1);
        getSignInAndShippingPage().getAddressField().type(user.getAddress());
        getSignInAndShippingPage().getCityNameField().type(user.getCity());
        selectByValue(getSignInAndShippingPage().getStateField(), user.getState());
        getSignInAndShippingPage().getPostalCodeInput().type(user.getZip());
        getSignInAndShippingPage().getPhoneSection1Field().type(user.getPhoneFirstField());
        getSignUpButton().waitForAttributeContains(ARIA_DISABLED, FALSE);
        getSignUpButton().click();
        getMainAlistSection().waitForElementNotPresent();

    }

    private void selectGender(String gender){
        for (WebRadio item: getGenderSelect()){
            if (item.getAttribute(VALUE).equalsIgnoreCase(gender)){
                item.clickJS();
                return;
            }
        }
        throw new NoSuchElementException("There is no any "+gender+" gender radio available");
    }
}
