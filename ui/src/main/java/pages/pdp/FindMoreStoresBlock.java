package pages.pdp;

import core.base.controls.BaseWebElement;
import core.base.controls.WebInput;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import core.base.AbstractBaseComponent;


import java.util.List;

import static core.WebDriverFactory.getDriver;
import static core.helpers.WebElementsActionsHelper.clickOnTheBaseWebElement;
import static core.utils.ConfigVariables.CA;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
@Profile({CA})
public class FindMoreStoresBlock extends AbstractBaseComponent {
    @FindBy(id = "checkStoreAvaliablityButton")
    private BaseWebElement findMoreStoresButton;

    @FindBy(xpath = "//input[@id = 'location']")
    private WebInput cityOrZipForStoreSearching;

    @FindBy(xpath = "//div[@class = 'store-results-locations']")
    private BaseWebElement storeFinderResults;


    public BaseWebElement getStoreFinderResults() {
        return storeFinderResults;
    }

    public BaseWebElement getFindMoreStoresButton() {
        return findMoreStoresButton;
    }

    public WebInput getCityOrZipForStoreSearching() {
        return cityOrZipForStoreSearching;
    }

}
