package pages.common.commonblocks;

import core.base.controls.BaseWebElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import core.base.AbstractBaseComponent;

import java.util.Arrays;
import java.util.stream.Collectors;

import static core.SLF4JLogger.error;
import static core.SLF4JLogger.log;
import static core.contsants.Delays.MEDIUM_DELAY;
import static core.contsants.Delays.SHORT_DELAY;
import static core.utils.ConfigVariables.CA;
import static core.utils.WaitingUtils.sleep;
import static java.awt.SystemColor.info;
import static java.lang.Integer.*;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
@Profile({CA})
public class HeaderBlock extends AbstractBaseComponent {
    @FindBy(xpath = "//nav[@class='c-top-bar__navigation']/a[contains(@class,'btn-account')]/span")
    private BaseWebElement logInLink;

    @FindBy(xpath = "//button[@data-qa-pdp-find-in-store-button]")
    private BaseWebElement storeLocationInLink;

    @FindBy(xpath = "//a[@data-qa-id='medium-shopping-bag']")
    private BaseWebElement shoppingBagIcon;

    @FindBy(xpath = "//button[@data-qa-id='message-bar-box-close']")
    private BaseWebElement notificationBannerCloseButton;

    @FindBy(xpath = "//div[@class='c-drawer__body c-scrollable']")
    private BaseWebElement siderWindow;


    public BaseWebElement getLogInLink() {
        return logInLink;
    }

    public BaseWebElement getNotificationBannerCloseButton() {
        return notificationBannerCloseButton;
    }

    public BaseWebElement getStoreLocationInLink() {
        return storeLocationInLink;
    }

    public BaseWebElement getShoppingBagIcon() {
        return shoppingBagIcon;
    }

    public BaseWebElement getSiderWindow() {
        return siderWindow;
    }


    public void closeNotificationIfPresent(){
        sleep(SHORT_DELAY);
        if (getNotificationBannerCloseButton().isPresent()){
            getNotificationBannerCloseButton().waitForElementToBeClickable();
            getNotificationBannerCloseButton().click();
        }
    }
}
