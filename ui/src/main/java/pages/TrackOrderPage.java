package pages;

import core.base.AbstractBasePage;
import core.base.controls.BaseWebElement;
import core.base.controls.WebInput;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static core.SLF4JLogger.error;
import static core.SLF4JLogger.log;
import static core.WebDriverFactory.getDriver;
import static core.helpers.WebElementsVerificationsHelper.*;
import static core.helpers.WebElementsVerificationsHelper.verifyConditionIsTrue;
import static core.utils.ConfigVariables.CA;
import static core.utils.WaitingUtils.sleep;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;


@Component
@Scope(SCOPE_PROTOTYPE)
@Profile({CA})
public class TrackOrderPage extends AbstractBasePage {


    @FindBy(xpath = "//div[contains(@class, 'c-helper--is-default')] ")
    private BaseWebElement trackOrderBlock;

    @FindBy(xpath = "//main[@data-qa-id='main']//div[contains(@class,'content--has-helper')and not(contains(@class,'accordion-child'))]//div[contains(@class,'c-order-lookup')]/button")
    private BaseWebElement trackOrderButton;

    @FindBy(xpath = "//main[@data-qa-id='main']//div[contains(@class,'content--has-helper')and not(contains(@class,'accordion-child'))]//input[@id='orderNumber-order-lookup']")
    private WebInput orderNumberField;

    @FindBy(xpath = "//main[@data-qa-id='main']//div[contains(@class,'content--has-helper')and not(contains(@class,'accordion-child'))]//input[@id='zipCode-order-lookup']")
    private WebInput zipCodeField;

    @FindBy(xpath = "//div[@class='c-order-history-container__header']/h1[contains(@class,'order-history')]")
    private BaseWebElement currentOrderNumber;

    @FindBy(xpath = "//main[@data-qa-id='main']//section/div[@class='c-order-history-item']")
    private BaseWebElement orderTrackingMainBlock;

    @FindBy(xpath = "//div[@class='c-order-history-item-header__address']")
    private BaseWebElement orderAddress;

    @FindBy(xpath = "//span[@data-qa-id='cart-summary-total-value']")
    private BaseWebElement totalOrderPrice;



    public BaseWebElement getTrackOrderBlock(){return trackOrderBlock;}

    public BaseWebElement getTrackOrderButton() {
        return trackOrderButton;
    }

    public WebInput getOrderNumberField() {
        return orderNumberField;
    }

    public WebInput getZipCodeField() {
        return zipCodeField;
    }

    public BaseWebElement getCurrentOrderNumber() {
        return currentOrderNumber;
    }

    public BaseWebElement getOrderTrackingMainBlock() {
        return orderTrackingMainBlock;
    }

    public BaseWebElement getOrderAddress() {
        return orderAddress;
    }

    public BaseWebElement getTotalOrderPrice() {
        return totalOrderPrice;
    }



    public void waitWhileOrderBeProcessed( int minutes){
        for (int i = 0; i < minutes; i++) {
            if (getTrackOrderButton().isPresent()) {
                getTrackOrderButton().click();
                log("Waiting...");
                sleep(60000);
                log(i+1 + " minute has passed.");
            }
            else if(i>=minutes & getTrackOrderButton().isPresent() ){
                error(minutes + "has passed but order tracking information is still absent");
                throw new IndexOutOfBoundsException();
            }
            else {
                log("Waiting is successfully over");
                break;
            }
        }
    }

    public void validateOrderNumberInfo(String orderNumber, String postalCode, String totalPrice){
        verifyConditionIsTrue(orderNumber.equals(getCurrentOrderNumber().getTrimText()),"order number is the same");
        verifyElementPresent(getOrderTrackingMainBlock(),"order tracking main block");
        verifyElementTextContains(getOrderAddress(), postalCode,"address line block");
        verifyElementTextEquals(getTotalOrderPrice(), totalPrice,"total price is the same");
        verifyConditionIsTrue(getDriver().getCurrentUrl().contains(orderNumber), "current URL contains order: " + orderNumber);
    }
}
