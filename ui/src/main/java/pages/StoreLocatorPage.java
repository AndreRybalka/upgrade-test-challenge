package pages;

import core.base.AbstractBasePage;
import core.base.controls.BaseWebElement;
import core.testdata.models.preparedData.Address;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pages.pdp.PDPPage;

import java.util.List;

import static constants.Constants.STORE_LOCATOR_PAGE;
import static core.WebDriverFactory.getDriver;
import static core.helpers.ComparisonHelper.verifyStringContains;
import static core.helpers.WebElementsActionsHelper.*;
import static core.helpers.WebElementsVerificationsHelper.verifyConditionIsFalse;
import static core.helpers.WebElementsVerificationsHelper.verifyElementPresent;
import static core.utils.ConfigVariables.CA;
import static core.utils.Utils.mouseDoubleClickAction;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
@Profile({CA})
public class StoreLocatorPage extends AbstractBasePage {

    @FindBy(id = "storeFinderButton")
    private BaseWebElement searchStoresButton;

    @FindBy(className = "input-inline")
    private BaseWebElement findAStoreTitle;

    @FindBy(xpath = "//div[@class='c-find-in-store__inner']//div[contains(@class,'suggestion-list')]")
    private BaseWebElement suggestionsList;

    @FindBy(xpath = "//ol[@id='location-results']//li")
    private List<BaseWebElement> suggestionsListElements;

    @FindBy(xpath = "//div[contains(@class,'find-in-store__results')]")
    private BaseWebElement findStoreResults;

    @FindBy(className = "o-close-button")
    private BaseWebElement closeButton;

    @FindBy(xpath = "//ol[@class='c-store-locator-list']//li//button[contains(@class,'store-info-hours-menu__title')]")
    private List<BaseWebElement> storeWorkingHoursLinks;

    @FindBy(xpath = "//ol[contains(@class,'nfo-hours-menu__list--is-visible')]")
    private BaseWebElement dailyWorkingHoursInfoBlock;

    @FindBy(xpath = "//div[contains(@class,'tab-panel c-store-locator-results__map')]")
    private BaseWebElement mapWithStoreLocation;

    @FindBy(xpath = "//button[@title='Toggle fullscreen view']")
    private BaseWebElement mapScalingButton;

    @FindBy(xpath = "//img[contains(@src,'4h4v-2h')]")
    private List<BaseWebElement> maximazedMapImages;

    @FindBy(xpath = "//ol[@class='c-store-locator-list']//li//div[@class='c-store-info__controls']//a[contains(@data-qa-id,'store-details')]")
    private List<BaseWebElement> storeDetailsLinks;




    public BaseWebElement getSearchStoresButton() {
        return searchStoresButton;
    }

    public BaseWebElement getFindAStoreTitle() {
        return findAStoreTitle;
    }

    public BaseWebElement getDailyWorkingHoursInfoBlock() {
        return dailyWorkingHoursInfoBlock;
    }

    public BaseWebElement getMapWithStoreLocation() {
        return mapWithStoreLocation;
    }

    public BaseWebElement getMapScalingButton() {
        return mapScalingButton;
    }

    public List<BaseWebElement> getStoreDetailsLinks() {
        return storeDetailsLinks;
    }

    public BaseWebElement getSuggestionsList() {
        return suggestionsList;
    }

    public List<BaseWebElement> getSuggestionsListElements() {
        return suggestionsListElements;
    }

    public BaseWebElement getFindStoreResults() {
        return findStoreResults;
    }

    public BaseWebElement getCloseButton() {
        return closeButton;
    }

    public List<BaseWebElement> getStoreWorkingHoursLinks() {
        return storeWorkingHoursLinks;
    }

    public List<BaseWebElement> getMaximazedMapImages() {
        return maximazedMapImages;
    }

    public void clickOnTheSearchStoreButton(PDPPage page) {
        try {
            if (getSearchStoresButton().isPresent()) {
                clickOnTheBaseWebElement(getSearchStoresButton());
            }
        } catch (WebDriverException e) {
            unfocusInput();
            if (!page.getFindMoreStoresBlock().getStoreFinderResults().isPresent()) {
                handleSearchAStoreButtonNotClickable(page);
            }
        }
    }

    private void handleSearchAStoreButtonNotClickable(PDPPage page) {
        hoverOnElement(getFindAStoreTitle());
        mouseDoubleClickAction();
        unfocusInput();
        getSearchStoresButton().waitForElementToBeClickable();
        getSearchStoresButton().clickAction();
        clickOnTheSearchStoreButton(page);
    }

    public void typePostCodeAndSearchStore(PDPPage page, Address store) {
        page.getFindMoreStoresBlock().getCityOrZipForStoreSearching().waitForElementPresent().waitElementBeNotStale();
        page.getFindMoreStoresBlock().getCityOrZipForStoreSearching().type(store.getSearchPostCode());
        getSuggestionsList().waitForElementPresent();
        getSuggestionsListElements().get(0).waitForElementToBeClickable();
        getSuggestionsListElements().get(0).click();
        getSuggestionsList().waitForElementNotPresent();
    }

    public void verifyStoreInfoIfPresent() {
        if (!getStoreWorkingHoursLinks().isEmpty()) {
            clickOnTheBaseWebElement( getStoreWorkingHoursLinks().get(0));
            verifyElementPresent(getDailyWorkingHoursInfoBlock());
            verifyElementPresent(getMapWithStoreLocation());
            clickOnTheBaseWebElement(getMapScalingButton());
            verifyConditionIsFalse(getMaximazedMapImages().isEmpty(), "maximized images aren`t present");
            clickOnTheBaseWebElement(getMapScalingButton());
            verifyConditionIsFalse(getStoreDetailsLinks().isEmpty(), "maximized images aren`t present");
            clickOnTheBaseWebElement(getStoreDetailsLinks().get(0));
            switchToWindow();
            verifyStringContains(getDriver().getCurrentUrl(), STORE_LOCATOR_PAGE, "Current URL is an expected one");
            switchToWindow();



        }
    }

}
