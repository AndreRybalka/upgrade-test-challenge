package pages;

import core.base.AbstractBasePage;
import core.base.controls.BaseWebElement;
import core.base.controls.WebInput;
import core.base.controls.WebRadio;
import core.testdata.models.preparedData.User;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pages.loginorcreateaccount.signup.AbstractCreateAccountPage;

import java.util.List;

import static constants.Constants.*;
import static core.helpers.WebElementsActionsHelper.selectByValue;
import static core.utils.ConfigVariables.CA;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;
/**
 * Created by andrii.rybalka on 22/02/2020.
 */

@Component
@Scope(SCOPE_PROTOTYPE)
@Profile({CA})
public class PersonalInformationPage extends AbstractBasePage {

    @Autowired
    @Lazy
    protected AbstractCreateAccountPage createAccountPage;

    @FindBy(name = "borrowerFirstName")
    private WebInput firstNameInput;

    @FindBy(name = "borrowerLastName")
    private WebInput lastNameInput;

    @FindBy(name = "borrowerStreet")
    private WebInput streetInput;

    @FindBy(xpath = "//ul[@class='geosuggest__suggests']//li")
    private List<BaseWebElement> searchSuggestions;

    @FindBy(name = "borrowerCity")
    private WebInput cityInput;

    @FindBy(name = "borrowerState")
    private WebInput stateInput;

    @FindBy(name = "borrowerZipCode")
    private WebInput zipCodeInput;

    @FindBy(name = "borrowerDateOfBirth")
    private WebInput dateOfBirthInput;

    @FindBy(xpath = "//button[@data-auto='continuePersonalInfo']")
    private BaseWebElement continueButton;

    @FindBy(name = "borrowerIncome")
    private WebInput incomeInput;

    @FindBy(name = "borrowerAdditionalIncome")
    private WebInput AdditionalIncomeInput;

    public AbstractCreateAccountPage getCreateAccountPage() {
        return createAccountPage;
    }

    public WebInput getFirstNameInput() {
        return firstNameInput;
    }

    public WebInput getLastNameInput() {
        return lastNameInput;
    }

    public WebInput getStreetInput() {
        return streetInput;
    }

    public List<BaseWebElement> getSearchSuggestions() {
        return searchSuggestions;
    }

    public WebInput getCityInput() { return cityInput; }

    public WebInput getStateInput() {
        return stateInput;
    }

    public WebInput getZipCodeInput() {
        return zipCodeInput;
    }

    public WebInput getDateOfBirthInput() { return dateOfBirthInput; }

    public BaseWebElement getContinueButton() { return continueButton; }

    public WebInput getIncomeIncomeInput() { return incomeInput; }

    public WebInput getAdditionalIncomeIncomeInput() { return AdditionalIncomeInput; }

    public void createUserStepContact(User user) {
        getFirstNameInput().type(user.getFirstName());
        getLastNameInput().type(user.getLastName());
        searchSuggest(user.getAddress());
        getCityInput().type(user.getCity());
        getStateInput().type(user.getState());
        getZipCodeInput().type(user.getZip());
        getDateOfBirthInput().type(user.getDayOfBirth());
        getContinueButton().click();

    }

    public void searchSuggest(String searchData) {
        getStreetInput().type(searchData);
        for (BaseWebElement searchSuggestion : getSearchSuggestions()) {
            if (searchSuggestion.getTrimText().contains(searchData)) {
                searchSuggestion.click();
                break;
            }
        }
    }

    public void inputIncomeValues(){
        getIncomeIncomeInput().type(testData.getIncomeAmount());
        getAdditionalIncomeIncomeInput().type(testData.getAdditionalIncomeAmount());
        getContinueButton().click();
        getContinueButton().click();
    }

}
