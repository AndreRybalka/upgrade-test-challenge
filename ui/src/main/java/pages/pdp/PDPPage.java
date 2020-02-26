package pages.pdp;

import core.base.AbstractBasePage;
import core.base.controls.BaseWebElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pages.HomePage;
import pages.cart.CartPage;

import java.util.List;
import java.util.stream.Collectors;

import static core.SLF4JLogger.*;
import static core.contsants.Constants.Attributes.ALT;
import static core.contsants.Delays.LONG_DELAY;
import static core.contsants.Delays.MEDIUM_DELAY;
import static core.helpers.WebElementsActionsHelper.clickJSOnTheBaseWebElement;
import static core.utils.ConfigVariables.CA;
import static core.utils.WaitingUtils.sleep;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;


@Component
@Scope(SCOPE_PROTOTYPE)
@Profile({CA})
public class PDPPage extends AbstractBasePage {

    @Autowired
    @Lazy
    private AddToCartBlock addToCartBlock;

    @Autowired
    @Lazy
    private HomePage homePage;

    @Autowired
    @Lazy
    private FindMoreStoresBlock findMoreStoresBlock;

    @Autowired
    @Lazy
    private ProductColorBlock productColorBlock;

    @Autowired
    @Lazy
    private ProductSizeBlock productSizeBlock;

    @Autowired
    @Lazy
    private ShareIconsBlock shareIconsBlock;

    @Autowired
    @Lazy
    private TrueFitBlock trueFitBlock;

    @Autowired
    @Lazy
    private LearnMoreBlock learnMoreBlock;

    @Autowired
    @Lazy
    private ZoomBlock zoomBlock;

    @Autowired
    @Lazy
    private CartPage cartPage;

    @Autowired
    @Lazy
    protected WriteReviewPopup writeReviewPopup;


    @FindBy(xpath = "//h2[@class='c-heading c-buy-module__product-title']")
    private BaseWebElement productTitle;

    @FindBy(xpath = "//div[contains(@class,'c-carousel-product-overview')]//button[contains(@class,'overview__zoom')]")
    private BaseWebElement itemMainImageZoomButton;

    @FindBy(xpath = "//div[contains(@class,'pswp--has_mouse')]")
    private BaseWebElement loadingIndicator;

    @FindBy(css = "#product-detail-info-panel__reviews-content .c-reviews__container")
    private BaseWebElement reviewArea;

    @FindBy(xpath = "//div[contains(@class, 'u-show@md-only')]//button[contains(@class, 'c-reviews__btn')]//span[contains(text(), 'Write a review')]")
    private BaseWebElement writeReviewButton;

    @FindBy(xpath = "//div[contains(@class, 'u-hide@under-lg-only')]//div[@id='product-detail-info-panel__reviews-control']//div")
    private BaseWebElement reviewDropToogle;

    @FindBy(xpath = "//h2[@class = 'c-heading c-buy-module__product-title']")
    private BaseWebElement productTitleOnPDPPage;

    @FindBy(xpath = "//div[contains(@class,'c-product-detail__carousel-wrapper')]//button[starts-with(@data-qa-id, 'favourites-btn')]")
    private BaseWebElement addToWishListIcon;

    @FindBy(xpath = "//div[contains(@class,'c-carousel-product-overview')]//button[contains(@data-qa-id,'favourites-btn') and contains(@data-qa-id,'false')]")
    private BaseWebElement emptyHeartIcon;

    @FindBy(xpath = "//div[contains(@class,'c-carousel-product-overview')]//button[contains(@data-qa-id,'favourites-btn') and contains(@data-qa-id,'true')]")
    private BaseWebElement filledHeartIcon;

    @FindBy(xpath = "//div[contains(@class,'c-product-price c-buy-module')]/span[contains(@class,'reduced')]")
    private BaseWebElement reducedProductPrice;

    @FindBy(xpath = "//div[contains(@class,'c-buy-module__formatted-price')]")
    private BaseWebElement baseProductPrice;

    @FindBy(css = "[class~='c-breadcrumb--product-detail'] ul[class='c-breadcrumb__list'] li")
    private List<BaseWebElement> breadcrumbs;

    @FindBy(xpath = "//ul[@id='PdpProductSizeSelectorOpts']//li[contains(@class,'is-checked')]")
    private BaseWebElement currentlySelectedSize;


    public WriteReviewPopup getWriteReviewPopup() {
        return writeReviewPopup;
    }

    public AddToCartBlock getAddToCartBlock() {
        return addToCartBlock;
    }

    public FindMoreStoresBlock getFindMoreStoresBlock() {
        return findMoreStoresBlock;
    }

    public ProductColorBlock getProductColorBlock() {
        return productColorBlock;
    }

    public HomePage getHomePage() {
        return homePage;
    }

    public CartPage getCartPage() {
        return cartPage;
    }

    public List<BaseWebElement> getBreadcrumbs() {
        return breadcrumbs;
    }

    public ProductSizeBlock getProductSizeBlock() {
        return productSizeBlock;
    }

    public ShareIconsBlock getShareIconsBlock() {
        return shareIconsBlock;
    }

    public TrueFitBlock getTrueFitBlock() {
        return trueFitBlock;
    }

    public LearnMoreBlock getLearnMoreBlock() {
        return learnMoreBlock;
    }

    public ZoomBlock getZoomBlock() {
        return zoomBlock;
    }

    public BaseWebElement getProductTitle() {
        return productTitle;
    }

    public BaseWebElement getItemMainImageZoomButton() {
        return itemMainImageZoomButton;
    }

    public BaseWebElement getLoadingIndicator() {
        return loadingIndicator;
    }

    public BaseWebElement getReviewArea(){
        return reviewArea;
    }

    public BaseWebElement getWriteReviewButton() {
        return writeReviewButton;
    }

    public BaseWebElement getReviewDropToogle(){
        return reviewDropToogle;
    }

    public BaseWebElement getProductTitleOnPDPPage() {
        return productTitleOnPDPPage;
    }

    public BaseWebElement getAddToWishListIcon() {
        return addToWishListIcon;
    }

    public BaseWebElement getEmptyHeartIcon() {
        return emptyHeartIcon;
    }

    public BaseWebElement getFilledHeartIcon() {
        return filledHeartIcon;
    }

    public BaseWebElement getReducedProductPrice() {
        return reducedProductPrice;
    }

    public BaseWebElement getBaseProductPrice() {
        return baseProductPrice;
    }

    public BaseWebElement getCurrentlySelectedSize() { return currentlySelectedSize; }



    public void selectItemWithSizeOtherFromAlreadySelected() {
        try{
            String sizeToSelect = getNotSelectedProductSizes().stream().findAny().orElseThrow(java.util.NoSuchElementException::new);
            getProductSizeBlock().getProductSizeOptions().stream()
                    .filter(size -> size.getTrimText().equals(sizeToSelect)).findFirst()
                    .orElseThrow(java.util.NoSuchElementException::new).click();
            log("Product size: [" + sizeToSelect + "] was successfully selected");
        }catch (java.util.NoSuchElementException e){
            error("Product size: was not successfully selected, the next color was selected");
            selectNextColor();
            selectSize();
        }
    }

    private List<String> getNotSelectedProductSizes() {
        return getProductSizeBlock().getProductSizeOptions().stream()
                .filter(option -> getNumberFromSize(option.getTrimText()) > getNumberFromSize(getProductSizeBlock().getCurrentlySelectedSize().getTrimText()))
                .map(BaseWebElement::getTrimText).collect(Collectors.toList());
    }

    public PDPPage selectColor() {
        for (BaseWebElement color : getProductColorBlock().getProductColorOptions()) {
            if (color.isEnabled() && !color.isSelected()) {
                if (getHomePage().getModalPopupCloseButton().isPresent()){
                    getHomePage().getModalPopupCloseButton().waitElementBeNotStale();
                    getHomePage().getModalPopupCloseButton().click();
                }
                String colorTitle = color.getAttribute(ALT).trim();
                color.waitElementBeNotStale();
                color.waitForElementToBeClickable();
                color.clickJS();
                if (getProductColorBlock().getCurrentlySelectedColorLabel().getAttribute(ALT).trim().equalsIgnoreCase(colorTitle)) {
                    getProductColorBlock().getCurrentlySelectedColorNameIdentificator().waitForTextInElementPresentEquals(colorTitle);
                } else
                    getProductColorBlock().getCurrentlySelectedColorLabel().waitForTextInElementPresentContains(colorTitle);
                log("Product color option: [" + colorTitle + "] was successfully selected");
                break;
            }
        }
        return this;
    }

    public PDPPage selectSize() {
        sleep(MEDIUM_DELAY);
        for (BaseWebElement productSize : getProductSizeBlock().getProductSizeOptions()) {
            if (productSize.isPresent() && !productSize.isSelected()) {
                productSize.click();
                getProductSizeBlock().getCurrentlySelectedSize().waitElementBeNotStale();
                log("Product size: [" + getProductSizeBlock().getCurrentlySelectedSize().getTrimText() + "] was successfully selected");
                return this;
            }
        }
        selectNextColor();
        return selectSize();
    }

    public PDPPage addToCartWithErrorHandleByColourWithSize() {
        boolean retry = true;
        while (retry){
            getAddToCartBlock().getAddToBagLoadingDot().waitForElementNotPresent();
            getAddToCartBlock().getAddToCartButton().waitElementBeNotStale();
            getAddToCartBlock().getAddToCartButton().waitForElementToBeClickable();
            getAddToCartBlock().getAddToCartButton().click();
            getAddToCartBlock().getAddToBagLoadingDot().waitForElementNotPresent();
            if (getAddToCartBlock().getNoLongerAvailableItemPopUp().isPresent()) {
                handlePopupError();
                try {
                    selectItemWithSizeOtherFromAlreadySelected();
                } catch (IllegalArgumentException ex) {
                    selectNextColor();
                    selectSize();
                }
            } else {
                retry = false;
                log("Item successfully added to bag");
                getCartPage().closeCart();
            }

        }
        return this;
    }


    public PDPPage addToCartWithErrorHandleByColourWithoutSize() {
        boolean retry = true;
        while (retry) {
            getAddToCartBlock().getAddToBagLoadingDot().waitForElementNotPresent();
            getAddToCartBlock().getAddToCartButton().waitElementBeNotStale();
            getAddToCartBlock().getAddToCartButton().waitForElementToBeClickable();
            getAddToCartBlock().getAddToCartButton().click();
            getAddToCartBlock().getAddToBagLoadingDot().waitForElementNotPresent();
            if (getAddToCartBlock().getNoLongerAvailableItemPopUp().isPresent()) {
                handlePopupError();
                selectNextColor();
            } else {
                retry = false;
                log("Item successfully added to bag");
                getCartPage().closeCart();
            }

        }
        return this;
    }

    private void handlePopupError() {
        getAddToCartBlock().getNoLongerAvailableItemPopUp().waitForElementPresent();
        getAddToCartBlock().getNoLongerAvailableItemCloseButton().click();
        getAddToCartBlock().getNoLongerAvailableItemPopUp().waitForElementNotPresent();
        log("Error adding product to bag. Error handled.");
    }

    public void selectNextColor() {
        try {
            getProductColorBlock().getNextColor().click();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("No more colours available for selection, or your item has just one colour and it is not available. \n" +
                    "Next color locator: {'method':'xpath','selector':'./parent::p/parent::label/parent::div/following-sibling::div[1]//a[contains(@class,'colorVariant')]'}");
        }
    }

    private double getNumberFromSize(String size) {
        if (size.endsWith("½")) {
            return Double.valueOf(size.replace("½", ".5"));
        }
        return Double.valueOf(size);
    }

    public void postAlreadySubmittedReview (){
        // DELAY due to bug with long processing post requests
        sleep(LONG_DELAY);
        stepInfo("Verify write review button and click it");
        clickJSOnTheBaseWebElement(getWriteReviewButton());
        sleep(MEDIUM_DELAY);
    }

    public String getExactProductPrice(){
        if (getReducedProductPrice().isPresent()){
            return getReducedProductPrice().getTrimText();
        }
        return getBaseProductPrice().getTrimText();
    }

}