package pages.pdp;

import core.base.AbstractBasePage;
import core.base.controls.BaseWebElement;
import core.base.controls.WebCheckbox;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pages.HomePage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static constants.Constants.*;
import static core.SLF4JLogger.log;
import static core.contsants.Constants.Attributes.CLASS;
import static core.contsants.Delays.MEDIUM_DELAY;
import static core.helpers.WebElementsVerificationsHelper.verifyElementPresent;
import static core.utils.ConfigVariables.CA;
import static core.utils.WaitingUtils.sleep;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;
import static org.testng.Assert.assertFalse;

@Component
@Scope(SCOPE_PROTOTYPE)
@Profile({CA})
public class ProductsListPage extends AbstractBasePage {

    @FindBy(xpath = "//div[contains(@class,'o-container')]//ul[contains(@class,'c-product-grid')]")
    private BaseWebElement productListBlock;

    @FindBy(xpath = "//div[@data-qa-id='product-tile']/a//img")
    private List<BaseWebElement> itemsListByImage;

    @FindBy(xpath = "//div[contains(@class,'c-product-landing')]//div[@data-qa-id='product-tile']")
    private List<BaseWebElement> itemsListByLink;

    @FindBy(xpath = "//button[@class='u-btn u-btn--tertiary c-load-more__button']")
    private BaseWebElement viewAllButton;

    @FindBy(xpath = "//button[@data-qa-id='open-filters-button']")
    private BaseWebElement filterButton;

    @FindBy(xpath = "//div[@class = 'c-filters-overlay__contents-wrapper']")
    private BaseWebElement filterOverlay;

    @FindBy(xpath = "//ul[@class='c-filter-group__list']/li[contains(@data-qa-id,'size') and not (contains(@class,'is-disabled'))]")
    private List<WebCheckbox> sizeCheckboxes;

    @FindBy(xpath = "//ul[@class='c-filter-group__list']/li[contains(@data-qa-id,'colour') and not (contains(@class,'is-disabled'))]")
    private List<WebCheckbox> colorCheckboxes;

    @FindBy(xpath = "//ul[@class='c-filter-group__list']/li[contains(@data-qa-id,'price') and not (contains(@class,'is-disabled'))]")
    private List<WebCheckbox> priceCheckboxes;

    @FindBy(xpath = "//ul[@class='c-filter-group__list']/li[contains(@data-qa-filter-size,'true') and not (contains(@class, '--is-disabled'))]")
    private List<WebCheckbox> heelsHeightCheckboxes;

    @FindBy(xpath = "//ul[@class='c-filter-group__list']/li[contains(@data-qa-id,'category') and not (contains(@class,'is-disabled'))]")
    private List<WebCheckbox> categoryCheckboxes;

    @FindBy(xpath = "//ul[@class='c-filter-group__list']/li[contains(@data-qa-id,'price') and contains(@class,'--is-checked')]")
    private BaseWebElement selectedPriceCheckbox;

    @FindBy(xpath = "//ul[@class='c-filter-group__list']/li[contains(@data-qa-filter-size,'true') and contains(@class,'--is-checked')]")
    private BaseWebElement selectedHeelsHeightCheckbox;

    @FindBy(xpath = "//ul[@class='c-filter-group__list']/li[contains(@data-qa-id,'category') and contains(@class,'--is-checked')]")
    private BaseWebElement selectedCategoryCheckbox;

    @FindBy(css = ".c-filters-overlay-controls .u-btn--primary")
    private BaseWebElement applayFilter;

    @FindBy(xpath = "//div[contains(@class, 'filters__filters-applied')]")
    private BaseWebElement appliedFilterArea;

    @FindBy(xpath = "//div[@class = 'c-product-tile__inner o-tile__inner']//button//span[@class = 'u-btn__content']")
    private List<BaseWebElement> addToWishListIcons;

    @FindBy(xpath = "//button[contains(@data-qa-id, 'favourites-btn')]")
    private List<BaseWebElement> addToWishListIconPLP;

    @FindBy(xpath = "//div[@class = 'c-product-tile__inner o-tile__inner' and .//span[@class = 'u-btn__content']]")
    private List<BaseWebElement> getProducts;

    @FindBy(xpath = "//h2//span[@class = 'c-heading__dash-wrap']//span[@class = 'c-markdown']")
    private List<BaseWebElement> productTitlesPLPPage;

//    @FindBy(css = "[style*=': left;'] > div")
//    private List<BaseWebElement> parentBreadcrumb;

    @FindBy(xpath = "//div[@class='c-breadcrumb']//ul//li[1]")
    private List<BaseWebElement> parentBreadcrumb;

    @FindBy(xpath = "//button[@data-qa-grid-control-applied-filter-button='true']")
    private List<BaseWebElement> filterOptionsApplied;




    @Autowired
    @Lazy
    private HomePage homePage;

    public HomePage getHomePage() {
        return homePage;
    }


    public BaseWebElement getProductListBlock() {
        return productListBlock;
    }

    public List<BaseWebElement> getItemsListByImage() {
        return itemsListByImage;
    }

    public List<BaseWebElement> getItemsListByLink() {
        return itemsListByLink;
    }

    public BaseWebElement getViewAllButton() {
        return viewAllButton;
    }

    public BaseWebElement getFilterButton() {
        return filterButton;
    }

    public BaseWebElement getFilterOverlay() {
        return filterOverlay;
    }

    public List<WebCheckbox> getSizeCheckboxes() {
        return sizeCheckboxes;
    }

    public List<WebCheckbox> getColorCheckboxes() {
        return colorCheckboxes;
    }

    public List<WebCheckbox> getPriceCheckboxes() {
        return priceCheckboxes;
    }

    public List<WebCheckbox> getHeelsHeightCheckboxes() {
        return heelsHeightCheckboxes;
    }

    public List<WebCheckbox> getCategoryCheckboxes() {
        return categoryCheckboxes;
    }

    public BaseWebElement getSelectedPriceCheckbox() {
        return selectedPriceCheckbox;
    }

    public BaseWebElement getSelectedHeelsHeightCheckbox() {
        return selectedHeelsHeightCheckbox;
    }

    public BaseWebElement getApplayFilter() {
        return applayFilter;
    }

    public BaseWebElement getAppliedFilterArea() {
        return appliedFilterArea;
    }

    public BaseWebElement getSelectedCategoryCheckbox() {
        return selectedCategoryCheckbox;
    }

    public List<BaseWebElement> getAddToWishListIcons() {
        return addToWishListIcons;
    }

    public List<BaseWebElement> getGetProducts() {
        return getProducts;
    }

    public List<BaseWebElement> getProductTitlesPLPPage() {
        return productTitlesPLPPage;
    }

    public List<BaseWebElement> getAddToWishListIconPLP() {
        return addToWishListIconPLP;
    }

    public List<BaseWebElement> getParentBreadcrumb() {
        return parentBreadcrumb;
    }

    public List<BaseWebElement> getFilterOptionsApplied() { return filterOptionsApplied; }



    public ProductsListPage openPDPPageByImageClick() {
        getItemsListByImage().get(0).click();
        return this;
    }

    public ProductsListPage openPDPPageByLinkIndex(int index) {
        getItemsListByLink().get(index).click();
        return this;
    }

    public ProductsListPage openRandomPDPPageByLink(){
        Random r = new Random();
        int index = r.nextInt(getItemsListByLink().size());
        getItemsListByLink().get(index).click();
        log(index + " product on PLP was opened");
        return this;
    }

    public void openLastPLPitem(){
        int lastItemIndex = getItemsListByLink().size() - 1;
        getItemsListByLink().get(lastItemIndex).click();
        log(lastItemIndex + " product on PLP was opened");
    }

    public String selectProductParameterOnFilterOverlay(List<WebCheckbox> productParam){
       getHomePage().closeModalPopUpIfPresent();

        WebCheckbox paramCheckbox =  productParam.stream()
                .filter(checkbox ->checkbox.getAttribute(ARIA_CHECKED).contains(FALSE))
                .findFirst().get();
        String selectedParameter = paramCheckbox.getAttribute(ARIA_LABEL);
        log("Selected parameter is: " + selectedParameter);
        paramCheckbox.check();
        return selectedParameter;
    }

    public String selectCheckboxProductParameterOnFilterOverlay(List<WebCheckbox> productPrices){
        getHomePage().closeModalPopUpIfPresent();

        WebCheckbox paramCheckbox =  productPrices.stream()
                .filter(checkbox ->!checkbox.getAttribute(CLASS).contains(IS_SELECTED))
                .findFirst().get();
        String selectedParameter = paramCheckbox.getTrimText();
        log("Selected parameter parameter is: " + selectedParameter);
        paramCheckbox.check();
        return selectedParameter;
    }


    public List<String> selectParametersForProductFiltration() {
        List<String> selectedParameters = new ArrayList<>();
        String selectedColor = selectProductParameterOnFilterOverlay(getColorCheckboxes());
        log("color parameter is successfully selected");
        sleep(MEDIUM_DELAY);
        /*String selectedSize = selectProductParameterOnFilterOverlay(getSizeCheckboxes());
        log("size parameter is successfully selected");
        sleep(MEDIUM_DELAY);*/
        String selectedPrice = selectCheckboxProductParameterOnFilterOverlay(getPriceCheckboxes());
        verifyElementPresent(getSelectedPriceCheckbox(),"selected price checkbox");
        log("price parameter is successfully selected");
        sleep(MEDIUM_DELAY);
        String selectedHeelsHeight = selectCheckboxProductParameterOnFilterOverlay(getHeelsHeightCheckboxes());
        verifyElementPresent(getSelectedHeelsHeightCheckbox(),"selected price checkbox");
        log("heels height parameter is successfully selected");
        // TODO CAN`T CLICK\PRESS TO CHECKBOX
        /*sleep(MEDIUM_DELAY);
        String selectedSubCategory = selectCheckboxProductParameterOnFilterOverlay(getCategoryCheckboxes());
        verifyElementPresent(getSelectedCategoryCheckbox(),"selected category checkbox");
        log("sub-category parameter is successfully selected");*/
        return Stream.of(/*selectedSubCategory,*/ selectedHeelsHeight, selectedColor, /*selectedSize,*/ selectedPrice).collect(Collectors.toCollection(() -> selectedParameters));

    }

    private BaseWebElement getSVGElement(int index) {
        return getAddToWishListIcons().get(index).findElement(By.xpath(".//parent::button//*[name()='svg']"));
    }

    private String getSVGAttribute(int i) {
        return getSVGElement(i)
                .getAttribute(ARIA_LABELLEDBY);
    }

    public String addToWishListFromCategoryPageAndGetTitle(PDPPage pdpPage) {
        for (int i = 0; i < getAddToWishListIcons().size(); i++) {
            if (!getSVGAttribute(i).contains(REMOVE_TITLE)) {
                getAddToWishListIcons().get(i).click();
                getGetProducts().get(i).click();
                break;
            }
        }
        return pdpPage.getProductTitleOnPDPPage().getTrimText();
    }



    public void verifyNoSelectedFilters(){
        getFilterButton().waitForElementPresent();
        assertFalse(getFilterOptionsApplied().size()>0, "Selected filter option is present");
        log("No selected filter options");
    }

    public void verifySelectedOptionFiltered(String selectedSize) {
        getFilterButton().waitForElementPresent();
        List<String> list = new ArrayList<>();
        for (BaseWebElement option : getFilterOptionsApplied()) {
            list.add(option.getText());
        }
        for (BaseWebElement option : getFilterOptionsApplied()) {
            String filterOption = option.getText().substring(option.getText().indexOf(" ")).replaceAll("\\s", "");
            if (filterOption.equals(selectedSize)) {
                log("Selected option: " + selectedSize + " is found");
                return;
            } else {
                log("Selected size is not found in filter");
                throw new Error("Error - expected size " + selectedSize + " is not found in filter option list: " + list);
            }
        }
    }
}
