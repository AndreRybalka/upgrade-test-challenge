package basetest;

import core.testdata.models.preparedData.Category;
import core.testdata.models.preparedData.Subcategory;

import static core.SLF4JLogger.stepInfo;
import static core.helpers.WebElementsActionsHelper.clickOnTheBaseWebElement;
import static core.testdata.provider.TestDataProviderHelper.getSiteUrl;

public class CommonPrecondition extends AbstractBaseTest {

    protected void addFirstFoundProductToCartForItemWithSize(Category mainCategory, Subcategory subCategory){
        commonPreSteps(mainCategory, subCategory);

        stepInfo("Select product size and color");
        pdpPage.selectColor().selectSize().addToCartWithErrorHandleByColourWithSize();

        stepInfo("Go to shopping cart page");
        clickOnTheBaseWebElement(homePage.getHeaderBlock().getShoppingBagIcon());
    }

    protected void addFirstFoundProductToCartForItemWithoutSize(Category mainCategory, Subcategory subCategory){
        commonPreSteps(mainCategory, subCategory);

        stepInfo("Select product size and color");
        pdpPage.selectColor().addToCartWithErrorHandleByColourWithoutSize();

        stepInfo("Go to shopping cart page");
        clickOnTheBaseWebElement(homePage.getHeaderBlock().getShoppingBagIcon());
    }

    private void commonPreSteps(Category mainCategory, Subcategory subCategory) {
        stepInfo("Open category and subcategory");
        homePage.getCategoriesBlock().openCategoryAndSubcategory(mainCategory, subCategory);

        stepInfo("Open PDP for first found item");
        productsListPage.openRandomPDPPageByLink();
    }

    protected void openSitePageAndAddFirstFoundProductToCartWithSize(Category mainCategory, Subcategory subCategory){
        openPage(getSiteUrl());
        addFirstFoundProductToCartForItemWithSize(mainCategory, subCategory);
    }

    protected void openSitePageAndAddFirstFoundProductToCartWithoutSize(Category mainCategory, Subcategory subCategory){
        openPage(getSiteUrl());
        addFirstFoundProductToCartForItemWithoutSize(mainCategory, subCategory);
    }
}