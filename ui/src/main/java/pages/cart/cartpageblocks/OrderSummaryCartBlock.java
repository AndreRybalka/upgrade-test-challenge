package pages.cart.cartpageblocks;

import core.base.AbstractBaseComponent;
import core.base.controls.BaseWebElement;
import core.base.controls.WebInput;
import core.base.controls.WebSelect;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

import static core.helpers.ComparisonHelper.verifyDoubleValuesEquals;
import static core.utils.ConfigVariables.CA;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;
import static pages.cart.CartPage.getShippingCharges;

@Component
@Scope(SCOPE_PROTOTYPE)
@Profile({CA})
public class OrderSummaryCartBlock extends AbstractBaseComponent {

    @Autowired
    @Lazy
    private OrderSummaryCartBlock orderSummaryCartBlock;

    @FindBy(xpath = "//td[contains(@data-qa-id,'cart-summary-subtotal-value')]")
    private BaseWebElement subtotalValue;

    @FindBy(id = "ShippingCalculate")
    private WebSelect shippingFee;

    @FindBy(css = ".c-cart-summary .c-cart-summary__total-price")
    private BaseWebElement displayedTotal;

    @FindBy(xpath = "//td[(@data-qa-id='tax-amount')]")
    private List<BaseWebElement> taxItem;

    @FindBy(xpath = "//button[@aria-label='Estimated taxes']")
    private BaseWebElement estimateTaxesLink;

    @FindBy(xpath = "//input[@id='postalCode']")
    private WebInput taxesPostalCodeInput;

    @FindBy(xpath = "//td[not(button) and contains(@class,'promotion-item-wrapper')]//following-sibling::td[contains(@class, 'amount-discounted')]")
    private BaseWebElement discountPrice;

    @FindBy(xpath = "//*[contains(@class, 'notification--applied-promo')]//span[contains(text(),'%') or contains(text(), '$')]")
    private BaseWebElement appliedPricePromo;

    @FindBy(xpath = "//button[contains(@class,'remove-button')]/following::td[contains(@class,'promotion-item__amount-discounted')]")
    private BaseWebElement promotionAmount;

    public BaseWebElement getAppliedPricePromo() { return appliedPricePromo; }

    public OrderSummaryCartBlock getOrderSummaryCartBlock() {
        return orderSummaryCartBlock;
    }

    public BaseWebElement getEstimateTaxesLink() {
        return estimateTaxesLink;
    }

    public WebInput getTaxesPostalCodeInput() {
        return taxesPostalCodeInput;
    }

    public BaseWebElement getDiscountPrice() {
        return discountPrice;
    }

    public BaseWebElement getSubtotalValue(){ return subtotalValue; }

    public WebSelect getShippingFee(){ return shippingFee; }

    public BaseWebElement getDisplayedTotal(){ return displayedTotal;}

    public BaseWebElement getPromotionAmount(){ return promotionAmount; }

    public double displayedOrderTotal() { return getDisplayedTotal().getPriceAsNumber(); }

    public double orderSubtotal() { return getSubtotalValue().getPriceAsNumber(); }

    public double voucherAmount(){ return promotionAmount.getPriceAsNumber(); }

    public List<BaseWebElement> getTaxItems() { return taxItem; }

    protected double roundDoubleValue(double value){
        return Math.round(value*100)/100.0d;
    }

    public double getPromotion(){
        if(getAppliedPricePromo().isPresent()){
            String quantityPromo = getAppliedPricePromo().getText().split("%")[0].replaceAll("[^\\d]", "");
            return roundDoubleValue(orderSubtotal()*(Double.parseDouble(quantityPromo)/100));
        }
        return 0;
    }

    public double totalTax(){
        if(getTaxItems().size() == 1) {
            return getTaxItems().get(0).getPriceAsNumber();
        }
        double GST = getTaxItems().get(0).getPriceAsNumber();
        double PST = getTaxItems().get(1).getPriceAsNumber();
        double taxes = GST + PST;
        return taxes;
    }

    public void verifyCartTotalWithTaxes() {

        for (int i = 1; i <= getShippingFee().getOptionValues().size(); i++) {
            double orderTotalByCal = roundDoubleValue((orderSubtotal() + getShippingCharges(getShippingFee(), i) + totalTax()));
            verifyDoubleValuesEquals(orderTotalByCal, displayedOrderTotal(), "verify double prices are equals");
        }
    }

    public void verifyCartTotalWithVoucher() {

        for (int i = 1; i <= getShippingFee().getOptionValues().size(); i++) {
            double orderTotalByCal = roundDoubleValue((orderSubtotal() + getShippingCharges(getShippingFee(), i)) - voucherAmount());
            verifyDoubleValuesEquals(orderTotalByCal, displayedOrderTotal(), "verify double prices are equals");
        }
    }

    public void verifyCartTotalWithPromoAndTaxes(){
        for (int i = 1; i <= getShippingFee().getOptionValues().size(); i++) {
            double orderTotalByCal = roundDoubleValue((orderSubtotal() + getShippingCharges(getShippingFee(), i) - getPromotion()   + totalTax()));
            verifyDoubleValuesEquals(orderTotalByCal, displayedOrderTotal(), "verify double prices are equals");
        }
    }

    public void verifyCartTotalWithPromoVoucherAndTaxes() {
        for (int i = 1; i <= getShippingFee().getOptionValues().size(); i++) {
            double orderTotalByCal = roundDoubleValue((orderSubtotal() + getShippingCharges(getShippingFee(), i) - getPromotion() - voucherAmount() + totalTax()));
            verifyDoubleValuesEquals(orderTotalByCal, displayedOrderTotal(), "verify double prices are equals");
        }
    }

}