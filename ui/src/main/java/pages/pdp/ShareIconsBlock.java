package pages.pdp;

import core.base.controls.BaseWebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import core.base.AbstractBaseComponent;

import static core.WebDriverFactory.getDriver;
import static core.utils.ConfigVariables.CA;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
@Profile({CA})
public class ShareIconsBlock extends AbstractBaseComponent {

    @FindBy(id = "a2apage_dropdown")
    private BaseWebElement shareDropDown;

    @FindBy(xpath = "//div[@class = 'productSharediv']")
    private BaseWebElement shareIconsComponents;

    @FindBy(xpath = "//div[@class = 'a2a_kit a2a_default_style']//a[@class = 'a2a_dd']")
    private BaseWebElement shareIcon;

    @FindBy(xpath = "//div[@class = 'a2a_kit a2a_default_style']//a[@class = 'a2a_button_facebook']")
    private BaseWebElement shareFacebookIcon;

    @FindBy(xpath = "//div[@class = 'a2a_kit a2a_default_style']//a[@class = 'a2a_button_twitter']")
    private BaseWebElement shareTwitterIcon;

    @FindBy(xpath = "//div[@class = 'a2a_kit a2a_default_style']//a[@class = 'a2a_button_email']")
    private BaseWebElement shareEmailIcon;

    @FindBy(xpath = "//div[@class = 'a2a_kit a2a_default_style']//a/img[@alt = 'Print']")
    private BaseWebElement printIcon;

    @FindBy(xpath = "//div[@id = 'bd']")
    private BaseWebElement twitterPopUpWindow;

    @FindBy(id = "content")
    private BaseWebElement facebookPopUpWindow;

    public BaseWebElement getShareDropDown() {
        return shareDropDown;
    }

    public BaseWebElement getShareIconsComponents() {
        return shareIconsComponents;
    }

    public BaseWebElement getShareIcon() {
        return shareIcon;
    }

    public BaseWebElement getShareFacebookIcon() {
        return shareFacebookIcon;
    }

    public BaseWebElement getShareTwitterIcon() {
        return shareTwitterIcon;
    }

    public BaseWebElement getShareEmailIcon() {
        return shareEmailIcon;
    }

    public BaseWebElement getPrintIcon() {
        return printIcon;
    }

    public BaseWebElement getTwitterPopUpWindow() {
        return twitterPopUpWindow;
    }

    public BaseWebElement getFacebookPopUpWindow() {
        return facebookPopUpWindow;
    }

    public void printOptionActionsCheckUsingJavaScript() {
        getDriver().switchTo().window(getDriver().getWindowHandles().toArray()[1].toString());
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        executor.executeScript("document.body.contains(document.getElementsByClassName('print default')[0]);");
        executor.executeScript("document.getElementsByClassName('cancel')[0].click();");
        getDriver().switchTo().window(getDriver().getWindowHandles().toArray()[0].toString());
    }
}
