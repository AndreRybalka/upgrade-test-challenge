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
public class ProductSizeBlock extends AbstractBaseComponent {

    @FindBy(xpath = "//div[@class='c-product-info-panel-controls']//ul[@id = 'PdpProductSizeSelectorOpts']/li[@role='radio' and @aria-checked='true']")
    private BaseWebElement currentlySelectedSize;

    @FindBy(xpath = "//div[@class='c-product-info-panel-controls']//ul[@id = 'PdpProductSizeSelectorOpts']/li[not(contains(@class,'is-disabled'))]")
    private List<BaseWebElement> productSizeOptions;

    public BaseWebElement getCurrentlySelectedSize() {
        return currentlySelectedSize;
    }

    public List<BaseWebElement> getProductSizeOptions() {
        return productSizeOptions;
    }

}
