package pages.loginorcreateaccount.signup;

import core.base.AbstractBasePage;
import core.base.controls.BaseWebElement;
import core.base.controls.WebInput;
import core.testdata.models.preparedData.User;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static core.utils.ConfigVariables.*;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
@Profile({ CA })
public abstract class AbstractCreateAccountPage extends AbstractBasePage {


    @FindBy(xpath = "//input[@name='username']")
    private WebInput mail;

    @FindBy(xpath = "//input[@id='firstName']")
    private WebInput firstName;

    @FindBy(xpath = "//input[@id='lastName']")
    private WebInput lastName;

    @FindBy(xpath = "//input[@name='password']")
    private WebInput password;

    @FindBy(xpath = "//input[@id='confirmPassword']")
    private WebInput confirmPassword;

    @FindBy(xpath = "//button[@data-auto='submitPersonalInfo']")
    private BaseWebElement submitPersonalInfoButton;


    @FindBy(xpath = "//div[@class='sc-kfGgVZ sc-esjQYD fCisXT']")
    private BaseWebElement termsOfUseCheckBox;



    public WebInput getMail() {
        return mail;
    }

    public WebInput getFirstName() {
        return firstName;
    }

    public WebInput getLastName() {
        return lastName;
    }

    public WebInput getPassword() {
        return password;
    }

    public WebInput getConfirmPassword() {
        return confirmPassword;
    }

    public BaseWebElement getSubmitPersonalInfoButton() {
        return submitPersonalInfoButton;
    }

    public BaseWebElement getTermsOfUseCheckbox() {
        return termsOfUseCheckBox;
    }


    public abstract void createAccount(User user);

}
