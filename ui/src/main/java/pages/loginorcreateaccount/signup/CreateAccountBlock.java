package pages.loginorcreateaccount.signup;

import core.base.controls.BaseWebElement;
import core.base.controls.WebCheckbox;
import core.base.controls.WebInput;
import core.base.controls.WebSelect;
import core.testdata.models.preparedData.User;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import core.base.AbstractBaseComponent;

import static core.SLF4JLogger.error;
import static core.helpers.WebElementsActionsHelper.selectByValue;
import static core.utils.ConfigVariables.CA;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
@Profile({CA})
public class CreateAccountBlock extends AbstractBaseComponent {

    @Autowired
    @Lazy
    private CreateAccountBlock createAccountBlock;

    @FindBy(id = "register.firstName")
    private WebInput firstNameField;

    @FindBy(id = "register.lastName")
    private WebInput lastNameField;

    @FindBy(id = "register.checkEmail")
    private WebInput checkEmailField;

    @FindBy(id = "password")
    private WebInput passwordField;

    @FindBy(id = "register.checkPwd")
    private WebInput checkPasswordField;

    @FindBy(id = "register.address1")
    private WebInput addressField;

    @FindBy(id = "register.address2")
    private WebInput secondAddressField;

    @FindBy(id = "register.company")
    private WebInput companyField;

    @FindBy(xpath = "//input[@id='city']")
    private WebInput cityField;

    @FindBy(xpath = "//select[@id='state']")
    private WebSelect stateField;

    @FindBy(id = "register.postCode")
    private WebInput zipCodeField;

    @FindBy(xpath = "//div[@class ='input-phone-number ']//input[@id = 'phone']")
    private WebInput phoneNumberInputFirstField;

    @FindBy(xpath = "//div[@class ='input-phone-number ']//input[@id = 'defaultAddress.canPhone2']")
    private WebInput phoneNumberInputSecondField;

    @FindBy(xpath = "//div[@class ='input-phone-number ']//input[@id = 'defaultAddress.canPhone3']")
    private WebInput phoneNumberInputThirdField;

    @FindBy(xpath = "//div[@class ='input-phone-number ']//input[@id = 'defaultAddress.phoneExt']")
    private WebInput phoneNumberInputExtField;

    @FindBy(xpath = "//input[@name = 'isEmailSignedUp']//following-sibling::span")
    private WebCheckbox emailSignedUpCheckBox;

    @FindBy(xpath = "//div[contains(@class, 'email-signup-radio') and not (contains(@class, 'inactive'))]")
    private WebCheckbox isEmailSignedUpCheckBoxInactive;

    @FindBy(id = "register.genderItems")
    private WebSelect gender;

    @FindBy(id = "yeardropdown")
    private WebSelect yearOfBirth;

    @FindBy(id = "monthdropdown")
    private WebSelect monthOfBirth;

    @FindBy(id = "daydropdown")
    private WebSelect dayOfBirth;

    @FindBy(id = "field_205")
    private BaseWebElement registrationButton;

    @FindBy(xpath = "//div [@class = 'main-content']/h1")
    private BaseWebElement welcomeMessage;

    public CreateAccountBlock getCreateAccountBlock() {
        return createAccountBlock;
    }

    public WebInput getFirstNameField() {
        return firstNameField;
    }

    public WebInput getLastNameField() {
        return lastNameField;
    }

    public WebInput getCheckEmailField() {
        return checkEmailField;
    }

    public WebInput getPasswordField() {
        return passwordField;
    }

    public WebInput getCheckPasswordField() {
        return checkPasswordField;
    }

    public WebInput getAddressField() {
        return addressField;
    }

    public WebInput getSecondAddressField() {
        return secondAddressField;
    }

    public WebInput getCompanyField() {
        return companyField;
    }

    public WebInput getCityField() {
        return cityField;
    }

    public WebSelect getStateField() {
        return stateField;
    }

    public WebInput getZipCodeField() {
        return zipCodeField;
    }

    public WebInput getPhoneNumberInputFirstField() {
        return phoneNumberInputFirstField;
    }

    public WebInput getPhoneNumberInputSecondField() {
        return phoneNumberInputSecondField;
    }

    public WebInput getPhoneNumberInputThirdField() {
        return phoneNumberInputThirdField;
    }

    public WebInput getPhoneNumberInputExtField() {
        return phoneNumberInputExtField;
    }

    public WebCheckbox getEmailSignedUpCheckBox() {
        return emailSignedUpCheckBox;
    }

    public WebCheckbox getIsEmailSignedUpCheckBoxInactive() {
        return isEmailSignedUpCheckBoxInactive;
    }

    public WebSelect getGender() {
        return gender;
    }

    public WebSelect getYearOfBirth() {
        return yearOfBirth;
    }

    public WebSelect getMonthOfBirth() {
        return monthOfBirth;
    }

    public WebSelect getDayOfBirth() {
        return dayOfBirth;
    }

    public BaseWebElement getRegistrationButton() {
        return registrationButton;
    }

    public BaseWebElement getWelcomeMessage() {
        return welcomeMessage;
    }

    public void createUserAccount(User user, String email) {
        getFirstNameField().type(user.getFirstName());
        getLastNameField().type(user.getLastName());
        getCheckEmailField().type(email);
        getPasswordField().type(user.getPassword());
        getCheckPasswordField().type(user.getPassword());
        getAddressField().type(user.getAddress());
        getSecondAddressField().type(user.getAddress2());
        getCompanyField().type(user.getCompany());
        getCityField().type(user.getCity());
        selectByValue(getStateField(), user.getState());
        getZipCodeField().type(user.getZip());
        getPhoneNumberInputFirstField().type(user.getPhoneFirstField());
        getPhoneNumberInputSecondField().type(user.getPhoneSecondField());
        getPhoneNumberInputThirdField().type(user.getPhoneThirdField());
        getPhoneNumberInputExtField().type(user.getPhoneExtField());
        isEmailCheckBoxChecked();
        selectByValue(getGender(), user.getGender());
        selectByValue(getDayOfBirth(), user.getDayOfBirth());
        selectByValue(getMonthOfBirth(), user.getMonthOfBirth());
        selectByValue(getYearOfBirth(), user.getYearOfBirth());
        getRegistrationButton().click();
    }

    private void isEmailCheckBoxChecked() {
        if (getIsEmailSignedUpCheckBoxInactive().isPresent()) {
            error("\033[31mCheck email box was selected by default but it should not be so  \033[0m");
            getEmailSignedUpCheckBox().getCheckboxInputElement().waitForElementPresent().waitForElementToBeClickable();
            getEmailSignedUpCheckBox().getCheckboxInputElement().clickJS();
            getEmailSignedUpCheckBox().getCheckboxInputElement().waitForElementPresent().waitForElementToBeClickable();
            getEmailSignedUpCheckBox().getCheckboxInputElement().clickJS();
        } else {
            getEmailSignedUpCheckBox().getCheckboxInputElement().waitForElementPresent().waitForElementToBeClickable();
            getEmailSignedUpCheckBox().getCheckboxInputElement().clickJS();
        }
    }
}
