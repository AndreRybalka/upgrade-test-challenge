package pages.payment;

import core.base.AbstractBasePage;
import core.base.controls.BaseWebElement;
import core.base.controls.WebInput;
import core.testdata.models.preparedData.User;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static core.utils.ConfigVariables.CA;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
@Profile({CA})
public class Step1Block extends AbstractBasePage {

    @FindBy(id = "firstName")
    private WebInput firstNameInput;

    @FindBy(id = "lastName")
    private WebInput lastNameInput;

    @FindBy(id = "email")
    private WebInput emailInput;

    @FindBy(xpath = "//div[@data-qa-id='checkout-page-step-1-done-state']")
    private BaseWebElement userDetails;



    public WebInput getFirstNameInput() {
        return firstNameInput;
    }

    public WebInput getLastNameInput() {
        return lastNameInput;
    }

    public WebInput getEmailInput() {
        return emailInput;
    }

    public BaseWebElement getUserDetails() {
        return userDetails;
    }


    public void fillUserName(User user) {
        getFirstNameInput().waitForElementPresent();
        getFirstNameInput().waitForElementPresent().type(user.getFirstName());
        getLastNameInput().type(user.getLastName());
    }
}
