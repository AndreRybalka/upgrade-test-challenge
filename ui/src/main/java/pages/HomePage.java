package pages;

import core.base.AbstractBasePage;
import core.base.controls.BaseWebElement;
import core.base.controls.WebInput;
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
import static core.helpers.WebElementsActionsHelper.clickOnTheBaseWebElement;
import static core.utils.ConfigVariables.CA;
import static core.utils.RegExrMatchSubstringUtil.matchString;
import static core.utils.WaitingUtils.sleep;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

/**
 * Created by andrii.rybalka on 22/02/2020.
 */

@Component
@Scope(SCOPE_PROTOTYPE)
@Profile({CA})
public class HomePage extends AbstractBasePage {

    @Autowired
    @Lazy
    private LearnMoreBlock learnMoreBlock;

    @FindBy(xpath = "//div[@id='searchHeader-validation']//input[@type = 'search']")
    private WebInput searchField;

    @FindBy(xpath = "//div[contains(@id,'promotional-banner-carousel')]//button[@data-qa-id='message-bar-box-close']")
    private BaseWebElement promoBannerNotificationClose;

    @FindBy(xpath = "//button[@class = 'u-btn c-navigation__btn c-navigation__search-btn u-btn--has-custom-focus']")
    private BaseWebElement searchButton;

    @FindBy(xpath = "//div[contains(@class,'c-navigation c-navigation--cart')]//div[contains(@class,'input__wrapper-suggestion-list')]")
    private BaseWebElement SOLRSearchSuggestionContainer;

    @FindBy(xpath = "//*[@id='searchHeader-results']")
    private List<BaseWebElement> SOLRSearchSuggestionList;

    @FindBy(xpath = "//a[contains(@class,' c-footer-cta__btn')]")
    private BaseWebElement aListSignUpButton;

    @FindBy(xpath = "//header[contains(@class,'sticky-header')]/div[@class='o-container']")
    private BaseWebElement mainActionsHeader;

    @FindBy(xpath = "//main[@data-qa-id='overlay-modal']//button[@data-qa-id='overlay-close-button']")
    private BaseWebElement modalPopupCloseButton;

    @FindBy(xpath = "//div[@id='message-bar' and @aria-live='polite']")
    private BaseWebElement thanksForSigningUpMessage;

    @FindBy(xpath = "//div[@id='message-bar' and @aria-live='polite']//button[contains(@class,'btn-close')]")
    private BaseWebElement thanksForSigningUpMessageCloseButton;

    @FindBy(xpath = "//div[contains(@class,'order-lookup--is-on-homepage')]")
    private BaseWebElement trackOrderBlock;

    @FindBy(xpath = "//input[@id='orderNumber-order-lookup']")
    private WebInput trackOrderNumberField;

    @FindBy(xpath = "//input[@id='zipCode-order-lookup']")
    private WebInput trackOrderPostalCodeField;

    @FindBy(xpath = "//div[contains(@class,'c-order-lookup')]/button")
    private BaseWebElement trackOrderButton;

    @FindBy(css = ".c-order-lookup .c-form-error-handling__well")
    private BaseWebElement trackOrderErrorMessage;

    @FindBy(xpath = "//div[contains(@class, 'c-footer__container')]//a[contains(@href,'customer-service')]")
    private List<BaseWebElement> trackOrderFooterLink;

    @FindBy(xpath = "//a[@data-qa-id = 'medium-favourites-counter-link']//parent::div")
    private BaseWebElement favoritesIconOnHeader;

    @FindBy(xpath = "//a[starts-with(@data-qa-id, 'medium-favourites-counter')]")
    private BaseWebElement amountOfTheFavoritesItems;

    @FindBy(xpath = "//a[@data-qa-id = 'medium-favourites-counter-link']//span[2]")
    private BaseWebElement favotitesIconOnHeaderUserSpan;




    public BaseWebElement getFavotitesIconOnHeaderUserSpan() {
        return favotitesIconOnHeaderUserSpan;
    }

    public BaseWebElement getAmountOfTheFavoritesItems() {
        return amountOfTheFavoritesItems;
    }

    public LearnMoreBlock getLearnMoreBlock() {
        return learnMoreBlock;
    }

    public BaseWebElement getSOLRSearchSuggestionContainer() {
        return SOLRSearchSuggestionContainer;
    }

    public List<BaseWebElement> getSOLRSearchSuggestionList() {
        return SOLRSearchSuggestionList;
    }

    public WebInput searchItemsField() {
        return searchField;
    }

    public BaseWebElement getSearchButton() {
        return searchButton;
    }

    public BaseWebElement getMainActionsHeader() {
        return mainActionsHeader;
    }

    public BaseWebElement getModalPopupCloseButton() {
        return modalPopupCloseButton;
    }

    public BaseWebElement getPromoBannerNotificationClose() {
        return promoBannerNotificationClose;
    }

    public BaseWebElement getaListSignUpButton() {
        return aListSignUpButton;
    }

    public BaseWebElement getThanksForSigningUpMessage() {
        return thanksForSigningUpMessage;
    }

    public BaseWebElement getThanksForSigningUpMessageCloseButton() {
        return thanksForSigningUpMessageCloseButton;
    }

    public BaseWebElement getTrackOrderBlock() {
        return trackOrderBlock;
    }

    public WebInput getTrackOrderNumberField() {
        return trackOrderNumberField;
    }

    public WebInput getTrackOrderPostalCodeField() {
        return trackOrderPostalCodeField;
    }

    public BaseWebElement getTrackOrderButton() {
        return trackOrderButton;
    }

    public BaseWebElement getTrackOrderErrorMessage() {
        return trackOrderErrorMessage;
    }

    public List<BaseWebElement> getTrackOrderFooterLink() {
        return trackOrderFooterLink;
    }

    public BaseWebElement getFavoritesIconOnHeader() {
        return favoritesIconOnHeader;
    }



    public void gotoBasketPage() {
        clickOnTheBaseWebElement(getHeaderBlock().getShoppingBagIcon());
        info("Basket page was opened");
    }

    public BaseWebElement getFirstSORLSearchResult(String partToFind, String brand) {
        return getSOLRSearchSuggestionList()
                .stream()
                .filter(solrlResult -> solrlResult.getTrimText()
                        .startsWith(partToFind) || solrlResult.getTrimText().equals(brand))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    public void closePromoBannerIfPresent(){
      if (getPromoBannerNotificationClose().isPresent()){
          getPromoBannerNotificationClose().waitForElementToBeClickable();
          getPromoBannerNotificationClose().clickJS();
      }
    }

    public void closeModalPopUpIfPresent(){
        if (getModalPopupCloseButton().isPresent()){
            clickOnTheBaseWebElement(getModalPopupCloseButton());
        }
    }

    public void checkIncomingMessage(String mail, String password, int minutes){
         new EmailUtil().check(mail, password, minutes);
    }


    public void clickOnFooterLink(String linkName) {
        for (BaseWebElement element : getTrackOrderFooterLink()) {
            if (element.getTrimText().equalsIgnoreCase(linkName)) {
                element.setName(linkName + " link");
                element.waitForElementToBeClickable();
                element.clickJS();
                return;
            }
        }
        throw new org.openqa.selenium.NoSuchElementException("There is no any "+linkName+" link available on the page");
    }

    public String favoritesAmount() {
        sleep(MEDIUM_DELAY);
        String amount = matchString(SELECT_DIGITS_PATTERN, getAmountOfTheFavoritesItems().getTrimText());
        if (amount.equals(EMPTY_STRING)) {
            return EMPTY_AMOUNT;
        }
        return amount;
    }

    public void verifyHeartIconAndCounterPresence() {
        getFavoritesIconOnHeader().waitForElementPresent();
        getFavotitesIconOnHeaderUserSpan().waitForAttributeContains(CLASS, COUNTER);
    }

    public void closeThanksMessage() {
        getThanksForSigningUpMessageCloseButton()
                .waitForElementPresent().waitForElementToBeClickable();
        getThanksForSigningUpMessageCloseButton().click();
    }

    public String getLocalStorageItems() {
        String items = (String) ((JavascriptExecutor) getDriver())
                .executeScript("return localStorage.getItem(\"aldo-favourites-us\");");
        if (!Objects.equals(items, null)) {
            return items;
        }
        return EMPTY_ARRAY;
    }

    public void gotoLoginPage(){
        if(getHeaderBlock().getLogInLink().isPresent()) {
            clickOnTheBaseWebElement(getHeaderBlock().getLogInLink());
        }
    }
}
