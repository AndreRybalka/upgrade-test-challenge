package pages.common.commonblocks;

import core.base.AbstractBaseComponent;
import core.base.controls.BaseWebElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

import static core.utils.ConfigVariables.CA;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;


@Component
@Scope(SCOPE_PROTOTYPE)
@Profile({CA})
public class InternationalBlock extends AbstractBaseComponent {

    @FindBy(xpath = "//div[@id='intl-dispatch-option']")
    private BaseWebElement mainInterSection;

    @FindBy(xpath = "//div[@role='tab']")
    private List <BaseWebElement> regions;

    @FindBy(xpath = "//div[@role='tabpanel' and @aria-hidden='false']")
    private BaseWebElement countriesBlock;

    @FindBy(xpath = "//div[@role='tabpanel' and @aria-hidden='false']//a[contains(@class,'c-intl-dispatch__country')]")
    private List <BaseWebElement> countries;





    public BaseWebElement getMainInterSection() {
        return mainInterSection;
    }

    public List<BaseWebElement> getRegions() {
        return regions;
    }

    public List<BaseWebElement> getCountries() {
        return countries;
    }

    public BaseWebElement getCountriesBlock() {
        return countriesBlock;
    }

    public void clickOnRegion(String linkName) {
        for (BaseWebElement element : getRegions()) {
            if (element.getTrimText().equalsIgnoreCase(linkName)) {
                element.setName(linkName + " region");
                element.click();
                return;
            }
        }
        throw new NoSuchElementException("There is no any "+linkName+" region available on the page");
    }

    public void clickOnCountry(String linkName) {
        for (BaseWebElement element : getCountries()) {
            if (element.getTrimText().equalsIgnoreCase(linkName)) {
                element.setName(linkName + " country");
                element.click();
                return;
            }
        }
        throw new NoSuchElementException("There is no any "+linkName+" country available on the page");
    }

}
