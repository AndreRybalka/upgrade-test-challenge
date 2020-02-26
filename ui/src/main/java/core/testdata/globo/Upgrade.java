package core.testdata.globo;

import core.testdata.models.TopMenus;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import static constants.Constants.*;
import static core.utils.ConfigVariables.CA;
import static java.util.Arrays.asList;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
@Profile(CA)
public class Upgrade extends AbstractTestData {

    @Override
    public String getDesiredAmount() {
        return "2000";
    }

    @Override
    public String getIncomeAmount() {
        return "130000";
    }

    @Override
    public String getAdditionalIncomeAmount() {
        return "6000";
    }

    @Override
   public String getPaymentButtonTitle() {
        return "Enter payment info";
    }

    @Override
    public String getReviewOrderButtonTitle() {
        return "Review your order";
    }

    @Override
    public String getShippingButtonTitle() {
        return "Enter shipping info";
    }

    @Override
    public String getStoreButtonTitle() {
        return "Enter payment info";
    }

    @Override
    public TopMenus getTopMenus() {
        return new TopMenus(new ArrayList<>(asList(WOMEN, MEN, KIDS, SALE, BRANDS)));
    }

    @Override
    public String getPromoCode() {
        return PROMO_CODE_15_DOLLARS_CA;
    }

    @Override
    public String heelsItemLink() { return "/women/boots/ankle-boots/Laraocia/p/11366866"; }

    @Override
    public String noSizeItemHomePage() {
        return "/handbags/crossbody-bags/Billington/p/11364165";
    }

    @Override
    public String withSizeItemPDP() {
        return "/women/footwear/flats/Lintun/p/11000194";
    }

    @Override
    public String heelsPLP() { return "/women/footwear/heels"; }

    @Override
    public String trackMyOrderPage() {return "/customer-service/track-order";}

    @Override
    public String colorParameterInURL() {
        return "Colour";
    }

    @Override
    public String solrlSearchPartToFindNoSize() {
        return "billi";
    }

    @Override
    public String solrlSearchProductNoSize() {
        return "billington";
    }

    @Override
    public String solrlSearchPartToFindWithSize() {
        return "dwead";
    }

    @Override
    public String solrlSearchProductWithSize() {
        return "dweadda";
    }

    @Override
    public String thirdStateValue() {
        return "MB";
    }
}
