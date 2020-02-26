package pages.pdp;

import core.base.controls.BaseWebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import core.base.AbstractBaseComponent;

import java.util.List;

import static core.utils.ConfigVariables.CA;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
@Profile({CA})
public class ProductColorBlock extends AbstractBaseComponent {

    @FindBy(xpath = "//ul[@id = 'PdpProductColorSelectorOpts']//a[contains(@class,'option--is-checked')]//img")
    private BaseWebElement currentlySelectedColorLabel;

    @FindBy(xpath = "//ul[@id = 'PdpProductColorSelectorOpts']//img")
    private List<BaseWebElement> productColorOptions;

    @FindBy(xpath = "//ul[@id = 'PdpProductColorSelectorOpts']//a[contains(@class,'option--is-checked')]/parent::li//following-sibling::li[1]//img")
    private BaseWebElement nextColor;

    @FindBy(xpath = "//label[@id='PdpProductColorSelectorOptsLabel']/span[contains(@class,'current-selection')]")
    private BaseWebElement currentlySelectedColorNameIdentificator;



    public List<BaseWebElement> getProductColorOptions() {
        return productColorOptions;
    }

    public BaseWebElement getCurrentlySelectedColorLabel() {
        return currentlySelectedColorLabel;
    }

    public BaseWebElement getNextColor() {
        return nextColor;
    }

    public BaseWebElement getCurrentlySelectedColorNameIdentificator() {
        return currentlySelectedColorNameIdentificator;
    }
}
