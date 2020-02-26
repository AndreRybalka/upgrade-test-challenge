package pages.common.commonblocks;

import core.base.controls.BaseWebElement;
import core.testdata.models.preparedData.Category;
import core.testdata.models.preparedData.Subcategory;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import core.base.AbstractBaseComponent;
import pages.HomePage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static core.SLF4JLogger.log;
import static core.contsants.Delays.MEDIUM_DELAY;
import static core.helpers.WebElementsActionsHelper.hoverOnElement;
import static core.utils.ConfigVariables.CA;
import static core.utils.WaitingUtils.sleep;
import static java.lang.String.format;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
@Profile({CA})
public class CategoriesBlock extends AbstractBaseComponent {

    @Autowired
    @Lazy
    private HomePage homePage;

    public HomePage getHomePage() {
        return homePage;
    }

    @FindBy(xpath = "//div[@class='c-sticky-header__link']//ul[@class='c-navigation--primary__list']/li[contains(@class,'list-item')]/button")
    private List<BaseWebElement> categories;

    @FindBy(xpath = "//div[contains(@aria-labelledby,'regular-menu') and @aria-hidden='false']")
    private BaseWebElement categoriesListPopUp;

    @FindBy(xpath = "//div[contains(@aria-labelledby,'regular-menu') and @aria-hidden='false']//div[contains(@class,'c-navigation-mega-menu__inner')]" +
                    "//ul[not (contains(@class,'allow-overlay-scroll')) and contains(@class,'mega-menu-inner')]/li/a")
    private List<BaseWebElement> listOfSubcategories;



    public List<BaseWebElement> getCategories() {
        return categories;
    }

    public List<BaseWebElement> getListOfSubcategories() {
        return listOfSubcategories;
    }

    public BaseWebElement getCategoriesListPopUp() {
        return categoriesListPopUp;
    }

    public Set<String> getTopMenuDisplayedCategories() {
        Set<String> actualMenus = new HashSet<>();
        for (BaseWebElement webElement : getCategories()) {
            actualMenus.add(webElement.getTrimText());
        }
        return actualMenus;
    }

    public void openCategoryAndSubcategory(Category category, Subcategory subcategory) {
        for (BaseWebElement element : getCategories()) {
            if (element.getTrimText().equals(category.getCategoryTitle())) {
                sleep(MEDIUM_DELAY);
                hoverOnElement(element);
                getCategoriesListPopUp().waitElementBeNotStale();
                for (BaseWebElement subcategoryElement : getListOfSubcategories() ) {
                    if (subcategoryElement.getTrimText().equalsIgnoreCase(subcategory.getSubcategoryTitle())){
                        subcategoryElement.setName(subcategoryElement.getTrimText() + " category");
                        subcategoryElement.waitForElementToBeClickable();
                        subcategoryElement.click();
                        sleep(MEDIUM_DELAY);
                        if (getHomePage().getModalPopupCloseButton().isPresent()){
                            getHomePage().getModalPopupCloseButton().waitElementBeNotStale();
                            getHomePage().getModalPopupCloseButton().click();
                        }
                        return;
                    }
                }
                return;
            }
        }
        throw new NoSuchElementException("Category or subcategory was not found");
    }

    private void getActualMenus(ArrayList<String> actualMenus) {
        for (BaseWebElement webElement : getCategories()) {
            actualMenus.add(webElement.getTrimText());
        }
    }

    public boolean topMenusCount(List<String> menuLabels) {
        if (getCategories().size() != menuLabels.size()) {
            ArrayList<String> actualMenus = new ArrayList<>();
            getActualMenus(actualMenus);
            compareSizeAfterUnnecessaryCategoryRemoving(menuLabels, actualMenus);
            return true;
        }
        return false;
    }

    private void compareSizeAfterUnnecessaryCategoryRemoving(List<String> menuLabels, ArrayList<String> actualMenus) {
        if (actualMenus.size() == menuLabels.size()) {
            log("\033[31m'CLEARANCE' category was removed, current size is equals to menu Labels  \033[0m");
        } else {
            throw new IllegalArgumentException(format("Invalid count of top menus, expected %s but was %s",
                    menuLabels, actualMenus));
        }
    }
}
