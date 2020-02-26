package pages;

import core.base.AbstractBasePage;
import core.base.controls.BaseWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pages.pdp.PDPPage;

import java.util.List;
import java.util.stream.Collectors;

import static constants.Constants.*;
import static core.WebDriverFactory.getDriver;
import static core.contsants.Delays.SHORT_DELAY;
import static core.helpers.WebElementsActionsHelper.clickOnTheBaseWebElement;
import static core.utils.ConfigVariables.CA;
import static core.utils.RegExrMatchSubstringUtil.matchString;
import static core.utils.WaitingUtils.sleep;
import static org.apache.commons.collections4.CollectionUtils.containsAll;
import static org.apache.commons.collections4.CollectionUtils.isEqualCollection;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
@Profile({CA})
public class WishListPage extends AbstractBasePage {

    @FindBy(xpath = "//h2[contains(@class, 'c-heading c-favourites-list-item__product-name')]//span[@class = 'c-markdown']")
    private List<BaseWebElement> wishListProductTitleList;

    @FindBy(xpath = "//button[contains(@class, 'u-btn u-btn ') and .//span[@class = 'u-btn__content']]")
    private List<BaseWebElement> wishListRemoveButtons;

    @FindBy(xpath = "//h2[@class = 'c-heading c-favourites-empty__title']")
    private BaseWebElement emptyWishListMessage;

    @FindBy(xpath = "//h1[@class ='c-heading c-breadcrumb-header__title c-breadcrumb-header__title--is-breadcrumb' or @class = 'c-heading c-favourites-header__title u-text-thin']")
    private BaseWebElement favoritesAmountNotADigit;

    @FindBy(xpath = "//picture[@class = 'c-picture c-picture--is-loaded']")
    private List<BaseWebElement> wishListItemsIcons;

    @FindBy(xpath = "//button[starts-with(@class,'u-btn u-btn--primary') and contains(@class, '_add-to-bag-btn') or contains(@class, '__edit-sizes-btn') ]")
    private List<BaseWebElement> addToBagButtonsList;



    public List<BaseWebElement> getWishListProductTitleList() {
        return wishListProductTitleList;
    }

    public List<BaseWebElement> getWishListRemoveButtons() {
        return wishListRemoveButtons;
    }

    public BaseWebElement getEmptyWishListMessage() {
        return emptyWishListMessage;
    }

    public BaseWebElement getFavoritesAmountNotADigit() {
        return favoritesAmountNotADigit;
    }

    public List<BaseWebElement> getWishListItemsIcons() {
        return wishListItemsIcons;
    }

    public List<BaseWebElement> getAddToBagButtonsList() {
        return addToBagButtonsList;
    }



    public void removeAddedItemsFromWishList() {
        for (int i = 0; i < getWishListRemoveButtons().size();) {
            sleep(SHORT_DELAY);
            getWishListRemoveButtons().get(i).waitElementBeNotStale();
            clickOnTheBaseWebElement(getWishListRemoveButtons().get(i));
        }
    }

    public BaseWebElement getFirstDeleteWishListProductLink() {
        return getWishListRemoveButtons().stream().findFirst().get();
    }

    private List<String> getSortedStringCollection(List<String> strings) {
        return strings.stream().sorted().collect(Collectors.toList());
    }

    private List<String> getSortedTitlesFromTheCollection(List<BaseWebElement> someCollection) {
        return someCollection.stream()
                .map(BaseWebElement::getTrimText).collect(Collectors.toList()).stream().sorted().collect(Collectors.toList());
    }

    public boolean compareWishListItemsCollectionAndOtherCollection(List<BaseWebElement> wishListProductTitleList, List<String> otherCollection) {
        return isEqualCollection(getSortedTitlesFromTheCollection(wishListProductTitleList),
                getSortedStringCollection(otherCollection)) ||
                containsAll(getSortedTitlesFromTheCollection(wishListProductTitleList),
                        getSortedStringCollection(otherCollection));
    }

    public boolean isWishListContainsProduct(String titleOfTheProductWhichWillBeAddedToTheWshList) {
        return getSortedTitlesFromTheCollection(getWishListProductTitleList())
                .contains(titleOfTheProductWhichWillBeAddedToTheWshList);
    }

    public String getDigitFromFavoritesAmount() {
        String result;
        getFavoritesAmountNotADigit().waitElementBeNotStale();
        result = matchString(SELECT_DIGITS_PATTERN, getFavoritesAmountNotADigit().getTrimText());
        if (result.equals(EMPTY_STRING)) {
            return EMPTY_AMOUNT;
        }
        return result;
    }

    public static int subtractionOfTheWishListAmount(Integer firstAmount, Integer secondAmount) {
        return firstAmount - secondAmount;
    }

    private BaseWebElement getBackInitializedElement(int index, List<BaseWebElement> listOfTheElements) {
        WishListPage wishListPage = PageFactory.initElements(getDriver(), WishListPage.class);
        return wishListPage.getElementWhichShouldBeReinitialize(index, listOfTheElements);
    }

    public void checkThatEachItemInWishListHasFilledHeartIcon(PDPPage productPage, HomePage homePage) {
        for (int i = 0; i < getWishListItemsIcons().size(); i++) {
            BaseWebElement icon = getBackInitializedElement(i, getWishListItemsIcons());
            icon.waitForElementToBeClickable();
            icon.click();
            if (!(productPage.getFilledHeartIcon().isDisplayed() && productPage.getFilledHeartIcon().isPresent())) {
                throw new IllegalArgumentException("The Icon should be filled");
            }
            homePage.getFavoritesIconOnHeader().click();
        }
    }
}
