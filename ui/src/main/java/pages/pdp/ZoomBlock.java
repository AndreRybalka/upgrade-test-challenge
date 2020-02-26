package pages.pdp;

import core.base.controls.BaseWebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import core.base.AbstractBaseComponent;

import java.util.List;

import static core.SLF4JLogger.log;
import static core.utils.ConfigVariables.CA;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;


@Component
@Scope(SCOPE_PROTOTYPE)
@Profile({CA})
public class ZoomBlock extends AbstractBaseComponent {

    @FindBy(xpath = "//div[not(contains(@class,'ui--hidden')) and contains(@class,'pswp__ui')]")
    private BaseWebElement imageZoomTopBar;

    @FindBy(xpath = "//div[not(contains(@class,'ui--hidden')) and contains(@class,'pswp__ui')]//button[contains(@class,'full-screen')]")
    private BaseWebElement fullScreenButton;

    @FindBy(xpath = "//div[@aria-label='Photo gallery' and contains(@class,'pswp--fs')]")
    private BaseWebElement zoomedImage;

    @FindBy(xpath = "//div[not(contains(@class,'ui--hidden')) and contains(@class,'pswp__ui')]//button[contains(@class, 'button--close')]")
    private BaseWebElement closeZoomWindowButton;

    @FindBy(xpath = " //div[not(contains(@class,'ui--hidden')) and contains(@class,'pswp__ui')]//ul[@class='c-carousel-modal__indicator-ctrls']/li/button")
    private List<BaseWebElement> changeImageButtons;





    public BaseWebElement getImageZoomTopBar() {
        return imageZoomTopBar;
    }

    public BaseWebElement getFullScreenButton() {
        return fullScreenButton;
    }

    public BaseWebElement getCloseZoomWindowButton() {
        return closeZoomWindowButton;
    }

    public BaseWebElement getZoomedImage() {
        return zoomedImage;
    }

    public List<BaseWebElement> getChangeImageButtons() {
        return changeImageButtons;
    }

    public void clickOnImageChangeButton(List<BaseWebElement>buttons){
        for (BaseWebElement imageButton: buttons){
            imageButton.waitForElementToBeClickable();
            imageButton.click();
        }
        log("all available images were verified");
    }
}
