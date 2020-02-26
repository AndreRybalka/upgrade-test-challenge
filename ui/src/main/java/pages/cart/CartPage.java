package pages.cart;

import core.base.AbstractBasePage;
import core.base.controls.BaseWebElement;
import core.base.controls.WebInput;
import core.base.controls.WebSelect;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pages.cart.cartpageblocks.OrderSummaryCartBlock;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static constants.Constants.DOUBLE_ZERO;
import static constants.Constants.ONLY_DIGITS_PATTERN;
import static core.SLF4JLogger.info;
import static core.contsants.Constants.Attributes.ARIA_DISABLED;
import static core.contsants.Delays.LONG_DELAY;
import static core.contsants.Delays.MEDIUM_DELAY;
import static core.contsants.Delays.SHORT_DELAY;
import static core.helpers.ComparisonHelper.verifyDoubleValuesEquals;
import static core.helpers.ComparisonHelper.verifyStringsEqual;
import static core.helpers.WebElementsActionsHelper.getAllOptions;
import static core.helpers.WebElementsActionsHelper.selectByVisibleText;
import static core.utils.ConfigVariables.CA;
import static core.utils.RegExrMatchSubstringUtil.matchString;
import static core.utils.Utils.getStringAsDouble;
import static core.utils.WaitingUtils.sleep;
import static java.lang.Boolean.valueOf;
import static java.lang.Double.parseDouble;
import static java.lang.String.format;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;


@Component
@Scope(SCOPE_PROTOTYPE)
@Profile({CA})
public class CartPage extends AbstractBasePage {

    private final DecimalFormat df = new DecimalFormat("#.##");

    @Autowired
    @Lazy
    private OrderSummaryCartBlock orderSummaryCartBlock;

    @FindBy(xpath = "//header[@role='banner']")
    private BaseWebElement quantityHeaderOnCartPage;

    @FindBy(xpath = "//h1[@data-qa-id='cart-header-title']/span/span[@class='c-markdown']")
    private BaseWebElement currentItemAmount;

    @FindBy(xpath = "//li[@data-qa-id='product-size']")
    private List <BaseWebElement> currentItemSize;

    @FindBy(xpath = "//ul[@class='c-product-list c-cart__list']")
    private BaseWebElement cartItemsContainer;

    @FindBy(xpath = "//button[contains(@class,'button--checkout')]")
    private BaseWebElement checkOutButton;

    @FindBy(xpath = "//li[contains(@class,'edit-action')]/button[@data-qa-id='product-remove-button']")
    private List<BaseWebElement> removeItemButton;

    @FindBy(xpath = "//li[contains(@class,'edit-action')]/button[@data-qa-id='product-edit-button']")
    private BaseWebElement editItemButton;

    @FindBy(xpath = "//table[@class='o-summary-table']//button[contains(@class,'taxes-link')]")
    private BaseWebElement taxesShippingPromoCodeLink;

    @FindBy(xpath = "//div[contains(@class,'romotion-add')]/button")
    private BaseWebElement promoCodeLink;

    @FindBy(xpath = "//input[@id = 'promoCode']")
    private WebInput enterPromoCodeInput;

    @FindBy(xpath = "//button[contains(@class,'add-promotion-submit')]")
    private BaseWebElement submitPromoCodeButton;

    @FindBy(xpath = "//button[@data-qa-cart-summary-estimate-taxes-submit-button]")
    private BaseWebElement submitTaxesButton;

    @FindBy(xpath = "//button[@data-qa-id='edit-tax-button']")
    private BaseWebElement editTaxesButton;

    @FindBy(xpath = "//div[@data-qa-id='cart-summary-taxes-title']")
    private BaseWebElement taxesTitle;

    @FindBy(xpath = "//button[@data-qa-id = 'overlay-close-button']")
    private BaseWebElement closeCartButton;

    @FindBy(xpath = "//div[@data-button]")
    private BaseWebElement paypalCheckoutButton;

    @FindBy(id = "VisaCheckoutButton")
    private BaseWebElement visaCheckoutButton;

    @FindBy(xpath = "//*[contains(@data-qa-id,'product-quantity')]")
    private BaseWebElement productQuantity;

    @FindBy(xpath = "//*[contains(@data-qa-id,'product-size')]")
    private BaseWebElement productSize;

    @FindBy(xpath = "//span[contains(@data-qa-id,'product-style')]")
    private BaseWebElement productColor;

    @FindBy(xpath = "//li[contains(@class,'item--is-editing')]")
    private BaseWebElement productEditingBlock;

    @FindBy(xpath = "//ul[contains(@id, 'CartProductSizeSelectorOpts')]//li")
    private List<BaseWebElement> productSizeOptionsValues;

    @FindBy(xpath = "//button[contains(@class, 'c-product-list-item-edit__button--update')]")
    private BaseWebElement saveEditsButton;

    @FindBy(xpath = "//div[@aria-checked = 'true']//..//following-sibling::li[1]")
    private BaseWebElement nextColor;

    @FindBy(xpath = "//ul[contains(@id, 'CartProductColorSelectorOpts')]//li//div[@role='radio']")
    private List<BaseWebElement> productColorOptionsValues;

    @FindBy(xpath = "//ul[contains(@id, 'CartProductColorSelectorOpts')]//preceding-sibling::div//span[@class='c-product-option__label-current-selection']")
    private BaseWebElement productColorSelected;

    @FindBy(xpath = "//ul[@id='CartProductSizeSelectorOpts-47802983']/li[@aria-checked='true']")
    private BaseWebElement checkedSize;

    @FindBy(className = "c-product-list-item-edit__quantity")
    private WebSelect quantityDropdown;

    @FindBy(xpath = "//ul[@data-qa-id= 'product-list']//li[starts-with(@class ,'c-product-list-item ')]")
    private List<BaseWebElement> listOfTheProducts;

    @FindBy(xpath = "//li[@class = 'c-product-list-item__edit-action']/button[not(@aria-label)]")
    private List<BaseWebElement> moveToFavoritesList;

    @FindBy(xpath = "//h2[@class = 'c-heading c-product-list-item__product-name' and @data-qa-id ]")
    private List<BaseWebElement> listOfTheProductsTitles;

    @FindBy(css = ".c-cart-empty__title")
    private BaseWebElement shoppingCartEmptyTitle;

    @FindBy(xpath = "//td[@data-qa-id='cart-summary-subtotal-value']")
    private BaseWebElement subtotalCartPrice;

    @FindBy(xpath = "//span[@class='c-cart-summary__total-price']")
    private BaseWebElement totalCartPrice;

    @FindBy(xpath = "//select[@id='ShippingCalculate']")
    private WebSelect shippingDropDown;

    @FindBy(className = "c-product-list-item__product-name")
    private BaseWebElement productName;

    @FindBy(className = "c-product-list-item__final-price")
    private BaseWebElement productFinalPrice;

    @FindBy(xpath = ".//div[@data-qa-id='cart-summary']//tr[contains(@class, 'c-cart-summary-promotion-item')]//td[not(contains(@class,'c-cart-summary-promotion-item__amount-discounted'))]")
    private BaseWebElement promotionTitle;

    @FindBy(xpath = "//li[@data-qa-id='product-size']")
    private List<BaseWebElement> itemsSizes;




    public BaseWebElement getProductQuantity() {
        return productQuantity;
    }

    public BaseWebElement getProductSize() {
        return productSize;
    }

    public BaseWebElement getProductColor() {
        return productColor;
    }

    public BaseWebElement getEditItemButton() {
        return editItemButton;
    }

    public BaseWebElement getProductEditingBlock() {
        return productEditingBlock;
    }

    public BaseWebElement getPromoCodeLink() {
        return promoCodeLink;
    }

    public BaseWebElement getSubmitTaxesButton() {
        return submitTaxesButton;
    }

    public BaseWebElement getEditTaxesButton() {
        return editTaxesButton;
    }

    public BaseWebElement getTaxesTitle() {
        return taxesTitle;
    }

    public List<BaseWebElement> getProductSizeOptionsValues() {
        return productSizeOptionsValues;
    }

    public BaseWebElement getSaveEditsButton() {
        return saveEditsButton;
    }

    public BaseWebElement getNextColor() {
        return nextColor;
    }

    public List<BaseWebElement> getProductColorOptionsValues() {
        return productColorOptionsValues;
    }

    public BaseWebElement getProductColorSelected() {
        return productColorSelected;
    }

    public BaseWebElement getCheckedSize() {
        return checkedSize;
    }

    public WebSelect getQuantityDropdown() {
        return quantityDropdown;
    }

    public BaseWebElement getPaypalCheckoutButton() {
        return paypalCheckoutButton;
    }

    public BaseWebElement getVisaCheckoutButton() {
        return visaCheckoutButton;
    }

    public OrderSummaryCartBlock getOrderSummaryCartBlock() {
        return orderSummaryCartBlock;
    }

    public BaseWebElement getQuantityHeaderOnCartPage() {
        return quantityHeaderOnCartPage;
    }

    public BaseWebElement getCurrentItemAmount() {
        return currentItemAmount;
    }

    public List <BaseWebElement> getCurrentItemSize() {
        return currentItemSize;
    }

    public BaseWebElement getCheckOutButton() {
        return checkOutButton;
    }

    public BaseWebElement getTaxesShippingPromoCodeLink() {
        return taxesShippingPromoCodeLink;
    }

    public WebInput getEnterPromoCodeInput() {
        return enterPromoCodeInput;
    }

    public BaseWebElement getSubmitPromoCodeButton() {
        return submitPromoCodeButton;
    }

    public List<BaseWebElement> getRemoveItemButton() {
        return removeItemButton;
    }

    public BaseWebElement getCloseCartButton() {
        return closeCartButton;
    }

    public BaseWebElement getCartItemsContainer() {
        return cartItemsContainer;
    }

    public List<BaseWebElement> getListOfTheProducts() {
        return listOfTheProducts;
    }

    public List<BaseWebElement> getMoveToFavoritesList() {
        return moveToFavoritesList;
    }

    public List<BaseWebElement> getListOfTheProductsTitles() {
        return listOfTheProductsTitles;
    }

    public BaseWebElement getShoppingCartEmptyTitle() {
        return shoppingCartEmptyTitle;
    }

    public BaseWebElement getSubtotalCartPrice() {
        return subtotalCartPrice;
    }

    public BaseWebElement getTotalCartPrice() {
        return totalCartPrice;
    }

    public WebSelect getShippingDropDown() {
        return shippingDropDown;
    }

    public BaseWebElement getProductName() {
        return productName;
    }

    public BaseWebElement getProductFinalPrice() { return productFinalPrice; }

    public BaseWebElement getPromotionTitle(){ return promotionTitle; }

    public List<BaseWebElement> getItemsSizes() { return itemsSizes; }

    public String getItemAmountOnCart() {
        getCurrentItemAmount().waitElementBeNotStale();
        return getCurrentItemAmount().getTrimText().replace("(", "").replace(")", "");
    }

    public String getItemSizeOnCart(BaseWebElement element) {
        return element.getTrimText().replace("Size ", "");
    }

    public boolean compareAddedItemsSize(){
        sleep(LONG_DELAY);
        List<String> differentSizes = new ArrayList<>();
        for (BaseWebElement itemSizeElement: getCurrentItemSize()){
          differentSizes.add(getItemSizeOnCart(itemSizeElement));
      }
        // Can`t add duplicate if present, duplicate will be skipped
        Set<String> differentSizeWithoutDuplicate = new HashSet<>(differentSizes);
        return differentSizes.size() == differentSizeWithoutDuplicate.size();
    }

    public void removeAllItemsIfPresent() {
        sleep(MEDIUM_DELAY);
        if (getCartItemsContainer().isPresent()) {
            for (BaseWebElement removeButton : getRemoveItemButton()) {
                removeButton.waitForElementPresent();
                removeButton.waitForElementToBeClickable();
                removeButton.clickJS();
                sleep(MEDIUM_DELAY);
            }
        }
    }

    public void closeCart(){
        getCloseCartButton().waitForElementToBeClickable();
        getCloseCartButton().click();
    }

    public String selectDifferentSizeByText(String unwantedSize) {
        for (BaseWebElement productSize : getProductSizeOptionsValues()) {
            if (!productSize.getTrimText().equals(unwantedSize) && !valueOf(productSize.getAttribute(ARIA_DISABLED))) {
                if (!(getStringAsDouble(productSize.getTrimText()) > getStringAsDouble(unwantedSize))) {
                    continue;
                }
                productSize.click();
                return productSize.getTrimText();
            }
        }
        throw new IllegalArgumentException(format("Unable to select size different to [%s]", unwantedSize));
    }

    public void selectDifferentColorByText(String currentColor) {
        for (BaseWebElement colorOption : getProductColorOptionsValues()) {
            String colorSelected = colorOption.getAttribute("title");
            if (!colorSelected.equalsIgnoreCase(currentColor)) {
                colorOption.click();
               getProductColorSelected().waitForTextInElementPresent(colorSelected);

                if (!getCheckedSize().isPresent()){
                    for (BaseWebElement productSize : getProductSizeOptionsValues()) {
                        if(!valueOf(productSize.getAttribute(ARIA_DISABLED))){
                            productSize.click();
                            return;
                        }
                    }
                }
                return;
            }
        }
        throw new IllegalArgumentException(format("Unable to select color different to [%s]", currentColor));
    }

    public void saveChanges() {
        if (getSaveEditsButton().isDisplayed() && getSaveEditsButton().isEnabled()) {
            getSaveEditsButton().waitForElementToBeClickable();
            getSaveEditsButton().click();
            getSaveEditsButton().waitForElementNotPresent();
        } else if (getSaveEditsButton().isDisplayed() && !getSaveEditsButton().isEnabled()) {
            getNextColor().click();
            sleep(SHORT_DELAY);
            saveChanges();
        }
    }

    public String getProductSizeAfterEditing() {
        return getProductSize().getTrimText().replaceAll(".*[A-Za-z]", "").trim();
    }

    public void addEachItemToWishList() {
        getMoveToFavoritesList().forEach(moveToFavorites -> {
            sleep(SHORT_DELAY);
            moveToFavorites.waitForElementToBeClickable();
            moveToFavorites.click();
            moveToFavorites.waitForElementNotPresent();
        });
    }

    public void selectEachShippingOptionAndCalculateTotalPrice(WebSelect select, String subTotalPrice){
        String shippingPrice;
        List<BaseWebElement> options = getAllOptions(select);
        for (int i = 1; i <options.size() ; i++) {
            if (matchString(ONLY_DIGITS_PATTERN,options.get(i).getTrimText()).replace(",",".").isEmpty()) {
                shippingPrice = DOUBLE_ZERO;
            } else {
                shippingPrice = matchString(ONLY_DIGITS_PATTERN, options.get(i).getTrimText().replace(",","."));
            }

            selectByVisibleText(select,options.get(i).getText());
            sleep(LONG_DELAY);
            info("Calculate total price with data : shippingPrice " + shippingPrice + "; subtotalPrice " +subTotalPrice +"; totalPrice " + getTotalCartPrice().getTrimText());
            verifyDoubleValuesEquals(parseDouble(df.format(parseDouble(subTotalPrice) -orderSummaryCartBlock.getPromotion()+ parseDouble(matchString(ONLY_DIGITS_PATTERN,  shippingPrice)))),
                    parseDouble(matchString(ONLY_DIGITS_PATTERN, getTotalCartPrice().getTrimText().replace(",","."))), "comparing cart sub total prices and total price");
        }
    }

    public static double getShippingCharges(WebSelect select, int option){
        int count=0;
        String shippingPrice;
        List<BaseWebElement> options = getAllOptions(select);
        selectByVisibleText(select,options.get(option).getText());
        info("Waiting for option value display");
        do{
            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                info("Timer is broken.");
            }
            count++;
        }
        while(count<60 && !select.getFirstSelectedOption().getText().equals(options.get(option).getText()));
        if (matchString(ONLY_DIGITS_PATTERN,options.get(option).getTrimText()).replace(",",".").isEmpty()) {
            shippingPrice = DOUBLE_ZERO;
        } else {
            shippingPrice = matchString(ONLY_DIGITS_PATTERN,options.get(option).getTrimText()).replace(",",".");
        }
        return parseDouble(shippingPrice);

    }


    public String getFirstItemSize(){
        String firstItemSize;
        try{
            firstItemSize= getItemsSizes().get(0).getText();
        }catch (IndexOutOfBoundsException e){
            info("No Selected items");
            throw e;
        }
        return firstItemSize;
    }

    public void verifySelectedSizeInCart(String selectedSize){
        String firdstItemValue = getFirstItemSize().substring(getFirstItemSize().indexOf(" ")).replaceAll("\\s","");
        verifyStringsEqual(firdstItemValue, selectedSize, "First item in cart has no required size");
    }
}