package pages.common.commonblocks;

import core.base.controls.BaseWebElement;
import org.openqa.selenium.NoSuchElementException;
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
public class LanguageCheckBlock extends AbstractBaseComponent {

    @FindBy(xpath = "//div[@class='o-container']//a[contains(@class,'c-region-selector__language-link')]")
    private BaseWebElement languagesLabel;

    @FindBy(xpath = "//div[@class='c-popover__inner' and @aria-hidden='false']")
    private BaseWebElement languagesLabelPopUp;

    @FindBy(xpath = "//div[contains(@class, 'o-container')]//a[contains(@class,'language-link')]//span[@class='u-btn__content']")
    private List <BaseWebElement> languageLinks;

    @FindBy(xpath = "//div[@class='c-popover__inner' and @aria-hidden='false']//a[@data-qa-region-selector-other-locations-link]")
    private BaseWebElement otherCountries;

    @FindBy(xpath = "//div[@class='c-popover__inner' and @aria-hidden='false']/*[contains(@class,'c-region-selector')]")
    private List <BaseWebElement> popUpItems;



    public BaseWebElement getLanguagesLabel() {
        return languagesLabel;
    }


    public BaseWebElement getLanguagesLabelPopUp() {
        return languagesLabelPopUp;
    }

    public List <BaseWebElement> getLanguageLinks() {
        return languageLinks;
    }

    public BaseWebElement getOtherCountries() {
        return otherCountries;
    }

    public List<BaseWebElement> getPopUpItems() {
        return popUpItems;
    }

    public void clickOnLanguageLink(String linkName) {
        for (BaseWebElement element : getLanguageLinks()) {
            if (element.getTrimText().equalsIgnoreCase(linkName)) {
                element.setName(linkName + " language link");
                element.click();
                return;
            }
        }
        throw new NoSuchElementException("There is no any "+linkName+" language link available on pop up");
    }

    public void clickOnPopUpItem(String linkName) {
        for (BaseWebElement element : getPopUpItems()) {
            if (element.getTrimText().contains(linkName)) {
                element.setName(linkName + " pop up item");
                element.click();
                return;
            }
        }
        throw new NoSuchElementException("There is no any "+linkName+" pop up item available");
    }
}
