package pages;

import core.base.AbstractBasePage;
import core.base.controls.BaseWebElement;
import core.base.controls.WebInput;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

import static core.SLF4JLogger.stepInfo;
import static core.WebDriverFactory.getDriver;
import static core.helpers.WebElementsVerificationsHelper.verifyElementPresent;
import static core.utils.ConfigVariables.CA;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;


@Component
@Scope(SCOPE_PROTOTYPE)
@Profile({CA})
public class SocialLogInPage extends AbstractBasePage {

    private String facebookPattern = ".*[\\s][A-Za-z]+[\\s]";

    @FindBy(xpath = "//button[@data-qa-id = 'facebook-login-button']")
    private BaseWebElement facebookSignInButton;

    @FindBy(xpath = "//button[contains(.,'sign in with Facebook') or contains(.,'mit Facebook einloggen')]")
    private BaseWebElement facebookSignInButtonOldUI;

    @FindBy(xpath = "//button[@data-qa-id = 'email-login-button']")
    private BaseWebElement emailSignInButton;

    @FindBy(xpath = "//span[@class= 'c-markdown']")
    private BaseWebElement emailSignInLable;

    @FindBy(xpath = "//div[@class ='u-link--basic-text auth-type-chooser__create-link' ]//button//span[@class = 'u-btn__content']")
    private BaseWebElement createAccountLink;

    @FindBy(id = "email")
    private WebInput facebookSignInEmail;

    @FindBy(id = "pass")
    private WebInput facebookSignInPassword;

    @FindBy(id = "u_0_0")
    private BaseWebElement facebookSignInLogInButton;

    @FindBy(id = "forgot-password-link")
    private BaseWebElement facebookSignInForgotPasswordLink;

    @FindBy(xpath = "//a[@class = '_42ft _4jy0 _4jy3 _4jy2 selected _51sy']")
    private BaseWebElement facebookSignInCreateNewAccount;

    @FindBy(xpath = "//div//h2[@id = 'homelink'] ")
    private BaseWebElement facebookSignInHomeLable;

    @FindBy(id = "booklet")
    private BaseWebElement facebookSignInFormElement;

    @FindBy(xpath = "//div[@class = 'pam login_error_box uiBoxRed']")
    private BaseWebElement facebookSignInAlertMessage;

    @FindBy(xpath = "//nav//button[@data-qa-id= 'overlay-close-button']//span")
    private BaseWebElement closeEmailFormButton;

    @FindBy(xpath = "//div[@class = 'o-box-12']")
    private BaseWebElement emailForm;

    @FindBy(xpath = "//div[@class = 'c-modal__header']")
    private BaseWebElement signInAccountForm;

    @FindBy(xpath = "//button[contains(@class , '--is-small c-btn--facebook')]")
    private BaseWebElement signInWithFacebookButtonOnCheckOutPage;

    @FindBy(xpath = "//button[contains(@class , '--is-small c-btn--email')]")
    private BaseWebElement signInWithEmailButtonOnCheckOutPage;

    @FindBy(xpath = "//span//button//span[@class = 'u-btn__content']")
    private BaseWebElement signInWithFacebookEmailOverlayLink;

    @FindBy(xpath = "//div[@class = 'c-editorial-text__wrapper-title o-box-12 o-box-6@md-mid']//h1[@class]")
    private BaseWebElement registerAccountWithFacebookTitle;

    @FindBy(id = "Blue_1_")
    private BaseWebElement facebookLable;

    @FindBy(xpath = "//div[@class = 'c-facebook-profile__avatar']")
    private BaseWebElement facebookProfileAvatar;

    @FindBy(xpath = "//div[@class = 'c-facebook-profile__fullname']")
    private BaseWebElement facebookFullName;

    @FindBy(xpath = "//div[@class = 'o-box-12 c-facebook-profile']")
    private BaseWebElement facebookProfile;

    @FindBy(xpath = "//input[@type = 'email']")
    private WebInput emailFacebookField;

    @FindBy(id = "firstName")
    private WebInput firstNameFacebookField;

    @FindBy(id = "lastName")
    private WebInput lastNameFacebookField;

    @FindBy(id = "account-submit")
    private BaseWebElement createAccountButton;

    @FindBy(xpath = "//button[@data-qa-id = 'overlay-close-button']//span[@class = 'u-btn__content']")
    private BaseWebElement logInWithFacebookCloseFormButton;

    @FindBy(xpath = "//button[contains(@class,'u-btn u-btn--') and contains(@class, 'u-btn--regular') and not(contains(@class, 'small'))]")
    private List<BaseWebElement> logInOptions;

    public String getFacebookPattern() {
        return facebookPattern;
    }

    public BaseWebElement getFacebookSignInButton() {
        return facebookSignInButton;
    }

    public BaseWebElement getFacebookSignInButtonOldUI() {
        return facebookSignInButtonOldUI;
    }

    public WebInput getFacebookSignInEmail() {
        return facebookSignInEmail;
    }

    public WebInput getFacebookSignInPassword() {
        return facebookSignInPassword;
    }

    public BaseWebElement getFacebookSignInLogInButton() {
        return facebookSignInLogInButton;
    }

    public BaseWebElement getFacebookSignInHomeLable() {
        return facebookSignInHomeLable;
    }

    public BaseWebElement getFacebookSignInFormElement() {
        return facebookSignInFormElement;
    }


    public BaseWebElement getCloseEmailFormButton() {
        return closeEmailFormButton;
    }

    public BaseWebElement getEmailForm() {
        return emailForm;
    }

    public BaseWebElement getSignInAccountForm() {
        return signInAccountForm;
    }

    public BaseWebElement getSignInWithFacebookButtonOnCheckOutPage() {
        return signInWithFacebookButtonOnCheckOutPage;
    }

    public BaseWebElement getSignInWithEmailButtonOnCheckOutPage() {
        return signInWithEmailButtonOnCheckOutPage;
    }

    public BaseWebElement getSignInWithFacebookEmailOverlayLink() {
        return signInWithFacebookEmailOverlayLink;
    }

    public BaseWebElement getRegisterAccountWithFacebookTitle() {
        return registerAccountWithFacebookTitle;
    }

    public BaseWebElement getFacebookLable() {
        return facebookLable;
    }

    public BaseWebElement getFacebookProfileAvatar() {
        return facebookProfileAvatar;
    }


    public BaseWebElement getCreateAccountButton() {
        return createAccountButton;
    }

    public BaseWebElement getLogInWithFacebookCloseFormButton() {
        return logInWithFacebookCloseFormButton;
    }

    public List<BaseWebElement> getLogInOptions() {
        return logInOptions;
    }


    public void closeFacebookRegistrationWindowIfItIsDisplays() {
        if(getLogInWithFacebookCloseFormButton().isPresent() &&
                getLogInWithFacebookCloseFormButton().isDisplayed()){
            getLogInWithFacebookCloseFormButton().click();
        }
    }
    public void logInWithFacebook(String parentWindow, String email, String password) {
        getFacebookSignInHomeLable().waitForElementPresent();
        getFacebookSignInFormElement().waitForElementPresent();
        getFacebookSignInEmail().waitForElementPresent();
        getFacebookSignInEmail().type(email);
        getFacebookSignInPassword().type(password);
        getFacebookSignInLogInButton().click();
        getDriver().switchTo().window(parentWindow);
        getCloseEmailFormButton().waitForElementNotPresent();
    }


    public void facebookLogInSteps(String email, String password) {

        String parentWindow = getDriver().getWindowHandle();
        goToTheFacebookLogIn();

        stepInfo("LogIn with Facebook credentials");
        logInWithFacebook(parentWindow, email, password);
    }

    private void facebookSignInWindowIsOpenVerifications() {
        switchToWindow();
        verifyElementPresent(getFacebookSignInFormElement());
        verifyElementPresent(getFacebookSignInHomeLable());
    }

    private void goToTheFacebookLogIn() {
        if (getFacebookSignInButton().isPresent()) {
            getFacebookSignInButton().click();
        } else {
            getFacebookSignInButtonOldUI().click();
        }
        facebookSignInWindowIsOpenVerifications();
    }
}
