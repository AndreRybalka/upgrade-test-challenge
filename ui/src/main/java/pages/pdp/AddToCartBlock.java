package pages.pdp;

import core.base.controls.BaseWebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import core.base.AbstractBaseComponent;

import static core.contsants.Delays.LONG_DELAY;
import static core.utils.ConfigVariables.CA;
import static core.utils.WaitingUtils.sleep;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
@Profile({CA})
public class AddToCartBlock extends AbstractBaseComponent {

    @FindBy(xpath = "//div[@class='c-purchase-buttons']/button[not(contains(@class,'is-success')) and @data-qa-pdp-add-to-bag-button]")
    private BaseWebElement addToCartButton;

    @FindBy(xpath = "//div[@class='c-purchase-buttons']/button[contains(@class,'is-success') and @data-qa-pdp-add-to-bag-button]")
    private BaseWebElement addedToCartButton;

    @FindBy(xpath = "//button[contains(@class, 'c-btn--is-user-action-needed')]")
    private BaseWebElement selectSizeAddToCartButton;

    @FindBy(xpath = "//a[@data-qa-id='medium-shopping-bag']//span[@class='c-shopping-bag-label']")
    private BaseWebElement cartAmount;

    @FindBy(xpath = "//main[@id='overlay-modal']//div[@aria-labelledby='modal-title']/div")
    private BaseWebElement noLongerAvailableItemPopUp;

    @FindBy(xpath = "//main[@id='overlay-modal']//button[@data-qa-id='overlay-close-button']")
    private BaseWebElement noLongerAvailableItemCloseButton;

    @FindBy(xpath = "//button[@aria-label = 'Add to bag']//span[@class='c-loading__dot']")
    private BaseWebElement addToBagLoadingDot;


    public BaseWebElement getAddToCartButton() {
        return addToCartButton;
    }

    public BaseWebElement getAddedToCartButton() {
        return addedToCartButton;
    }

    public BaseWebElement getSelectSizeAddToCartButton() {
        return selectSizeAddToCartButton;
    }

    public BaseWebElement getAddToBagLoadingDot() {
        return addToBagLoadingDot;
    }

    public BaseWebElement getCartAmount() {
        return cartAmount;
    }

    public BaseWebElement getNoLongerAvailableItemPopUp() {
        return noLongerAvailableItemPopUp;
    }

    public BaseWebElement getNoLongerAvailableItemCloseButton() {
        return noLongerAvailableItemCloseButton;
    }

    public int getCartAmountAdding() {
        sleep(LONG_DELAY);
        getCartAmount().waitElementBeNotStale();
        System.out.println(getCartAmount().getTrimText());
        return Integer.parseInt(String.valueOf(getCartAmount().getTrimText()
                .replace(" item", "")).replace("s",""));
    }
}
