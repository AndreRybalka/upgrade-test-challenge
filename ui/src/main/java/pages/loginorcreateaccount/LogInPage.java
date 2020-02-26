package pages.loginorcreateaccount;

import constants.Constants;
import core.base.AbstractBasePage;
import core.base.controls.BaseWebElement;
import core.base.controls.WebInput;
import core.testdata.models.preparedData.User;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static constants.Constants.WRONG_PASSWORD;
import static core.SLF4JLogger.info;
import static core.contsants.Constants.Attributes.ARIA_DISABLED;
import static core.contsants.Delays.SHORT_DELAY;
import static core.helpers.WebElementsVerificationsHelper.verifyElementPresent;
import static core.utils.ConfigVariables.CA;
import static core.utils.WaitingUtils.sleep;
import static java.lang.String.valueOf;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;


@Component
@Scope(SCOPE_PROTOTYPE)
@Profile({CA})
public class LogInPage extends AbstractBasePage {

    @FindBy(xpath="//main[@data-qa-id='overlay-modal']")
    private BaseWebElement preLoginPopUp;

    @FindBy(xpath="//a[contains(@class, 'u-link--basic-text')]")
    private BaseWebElement registerAccount;

    @FindBy(xpath="//main[@data-qa-id='overlay-modal']//button[@data-qa-id='email-login-button']")
    private BaseWebElement loginWithEmail;

    @FindBy(xpath="//main[@data-qa-id='overlay-modal']//button[@data-qa-id='overlay-close-button']")
    private BaseWebElement closePreLoginPopUp;

    @FindBy(xpath="//main[@data-qa-id='overlay-modal']//button[@data-qa-id='create-account']")
    private BaseWebElement createAccountLink;

    @FindBy(xpath="//div[@class='layout__section section']")
    private BaseWebElement loginForm;

    @FindBy(xpath="//div[@aria-labelledby='modal-title']//div[contains(@class,'header-paragraph c-modal')]")
    private BaseWebElement loginPopUpRegister;

    @FindBy(xpath = "//input[@data-auto='username']")
    private WebInput existingUserEmailInput;

    @FindBy(xpath = "//input[@data-auto='password']")
    private WebInput existingUserPasswordInput;

    @FindBy(xpath = "//button[@data-auto='login']")
    private BaseWebElement loginFormSighInButton;

    @FindBy(css = "[data-qa-id='login-server-error']")
    private BaseWebElement errorMessage;

    @FindBy(id = "username-validation")
    private BaseWebElement emailRequiredMessage;

    @FindBy(id = "password-validation")
    private BaseWebElement passwordRequiredMessage;

    @FindBy(css = ".c-login-form__footer .u-link--basic-text")
    private BaseWebElement forgotPasswordLink;

    @FindBy(xpath = "//button[contains(@class,'u-btn--regular')][@type='submit']")
    private BaseWebElement confirmRequestSending;


    public WebInput getExistingUserEmailInput() {
        return existingUserEmailInput;
    }

    public WebInput getExistingUserPasswordInput() {
        return existingUserPasswordInput;
    }

    public BaseWebElement getLoginFormSighInButton() {
        return loginFormSighInButton;
    }

    public BaseWebElement getPreLoginPopUp() {
        return preLoginPopUp;
    }

    public BaseWebElement getLoginWithEmail() {
        return loginWithEmail;
    }

    public BaseWebElement getClosePreLoginPopUp() {
        return closePreLoginPopUp;
    }

    public BaseWebElement getCreateAccountLink() {
        return createAccountLink;
    }

    public BaseWebElement getRegisterAccount() {
        return registerAccount;
    }

    public BaseWebElement getLoginForm() {
        return loginForm;
    }

    public BaseWebElement getErrorMessage() {
        return errorMessage;
    }

    public BaseWebElement getLoginPopUpRegister() {
        return loginPopUpRegister;
    }

    public BaseWebElement getEmailRequiredMessage() {
        return emailRequiredMessage;
    }

    public BaseWebElement getPasswordRequiredMessage() {
        return passwordRequiredMessage;
    }

    public BaseWebElement getForgotPasswordLink() {
        return forgotPasswordLink;
    }

    public BaseWebElement getConfirmRequestSending() {
        return confirmRequestSending;
    }

    private void clickOnSignInEmail(){
        getLoginWithEmail().waitForElementToBeClickable();
        getLoginWithEmail().click();
    }

    private void inputUserCredentials(User user){
        getExistingUserEmailInput().type(user.getEmailAddress());
        getExistingUserPasswordInput().type(user.getPassword());
    }

    public void pressCreateAccountLink(){
        getRegisterAccount().waitForElementToBeClickable();
        getRegisterAccount().click();
    }

    public void logIn(User user) {
        getLoginForm().waitForElementPresent();
        inputUserCredentials(user);
        getLoginFormSighInButton().waitForElementToBeClickable();
        getLoginFormSighInButton().click();
        getLoginFormSighInButton().waitForElementNotPresent();
    }

    public void loginWithWrongPassword(User user) {
        getPreLoginPopUp().waitForElementPresent();
        clickOnEmailSignInIfPresent();
        user.setPassword(WRONG_PASSWORD);
        getLoginForm().waitForElementPresent();
        inputUserCredentials(user);
        getLoginFormSighInButton().waitForAttributeContains(Constants.ARIA_DISABLED,Constants.FALSE);
        getLoginFormSighInButton().click();
    }

    public void verifyLoginFormFieldsValidation() {
        checkLoginEmailFieldValidation();
        checkPasswordFieldValidation();
    }

    private void checkLoginEmailFieldValidation() {
        getExistingUserEmailInput().waitForElementPresent();
        getExistingUserEmailInput().type("t");
        getExistingUserEmailInput().clear();
        getExistingUserPasswordInput().click();
        verifyElementPresent(getEmailRequiredMessage(),"mail required message");
    }

    private void checkPasswordFieldValidation() {
        getExistingUserPasswordInput().waitForElementPresent();
        getExistingUserPasswordInput().type("t");
        getExistingUserPasswordInput().clear();
        getExistingUserEmailInput().click();
        verifyElementPresent(getPasswordRequiredMessage(),"password required message");
    }

    public void clickOnEmailSignInIfPresent(){
        if (getLoginWithEmail().isPresent()){
            clickOnSignInEmail();
        }
    }

    public void fillMailField(String mail){
        getExistingUserEmailInput().waitForElementPresent();
        getExistingUserEmailInput().clickJS();
        sleep(SHORT_DELAY);
        getExistingUserEmailInput().type(mail);
        info("Mail field was successfully filled");
    }

    public void submitLoginForm() {
        getConfirmRequestSending().waitForElementPresent().waitForAttributeContains(ARIA_DISABLED, valueOf(false));
        getConfirmRequestSending().clickJS();
        getConfirmRequestSending().waitForElementNotPresent();
        info("Login form was successfully submitted");
    }

    public void typeMailAndSentRequestToPasswordChange(String mail){
        fillMailField(mail);
        submitLoginForm();
        waitForDocumentReadyState();
    }

}
