package core.testdata.data;

import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;

import static core.constants.Constants.*;
import static core.utils.ConfigVariables.CA;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
@Profile(CA)
public class TestDataCA_EN extends AbstractTestData {

    @Override
    public String getFirstUserAddressLine() {
        return "709 Dufferin St";
    }

    @Override
    public String getUserAddressPhoneNumber() {
        return "1111111111";
    }

    @Override
    public String getUserAddressCity() {
        return "Toronto";
    }

    @Override
    public String getUserAddressPostalCode() {
        return "M6K 2B6";
    }

    @Override
    public String getUserAddressRegionISOCode() {
        return "CA-ON";
    }

    @Override
    public String getFirstUserAddressLineUpdate() {
        return "6113 Yonge Street";
    }

    @Override
    public String getUserAddressPhoneNumberUpdate() {
        return "2222222222";
    }

    @Override
    public String getUserAddressCityUpdate() {
        return "Toronto";
    }

    @Override
    public String getUserAddressPostalCodeUpdate() {
        return "M2M 3W2";
    }

    @Override
    public String getUserAddressRegionISOCodeUpdate() {
        return "CA-ON";
    }

    @Override
    public String getWomenText() {
        return WOMEN_CA_EN;
    }

    @Override
    public String getMenText() {
        return MEN_CA_EN;
    }

    @Override
    public String getGiftCardText() {
        return "Gift Cards";
    }

    @Override
    public String getMXText() {
        return null;
    }

    @Override
    public String getProductSku() {
        return "49123810-98";
    }

    @Override
    public String getProductArticleWithSize() {
        return "38753235-55";
    }

    @Override
    public String getProductName() {
        return "Lousana";
    }

    @Override
    public String getHubrisProductCode() {
        return "49123829";
    }

    @Override
    public String getTaxValue() {
        return TAX_VALUE_CA_EN;
    }

    @Override
    public String storesAmount() {
        return "173";
    }

    @Override
    public String getStoreLatitude() {
        return "45.503376";
    }

    @Override
    public String getStoreLongitude() {
        return "-73.5698987";
    }

    @Override
    public String getStoreName() {
        return "1014";
    }

    @Override
    public Map<String,String> getLocaleMainAttributes() {
        return new LinkedHashMap<String, String>() {{
           put("CA","Canada");
        }};
    }

    @Override
    public Map<String, String> getCartTypes() {
        return new LinkedHashMap<String, String>() {{
            put("amex","American Express");
            put("discover","Discover");
            put("visa","Visa");
            put("master","Mastercard");
        }};
    }


    @Override
    public Map<String, String> getAdditionalProductOffer() {
        return new LinkedHashMap<String, String>() {{
            put("Vitiano","49783253-0");
        }};
    }

    @Override
    public String getHubrisProductCodeWithAdditionalOfferInCart() {
        return "49123829";
    }

    @Override
    public List<String> additionalProductsOnPDP() {
        return new ArrayList<String>(){{
            add("Iocco");
            add("Seviade");
        }};
    }

    @Override
    public String getPlaceID() {
        return "ChIJg7h5tss0K4gR2ZCmEj6GUt4";
    }

    @Override
    public String getPlaceLatitude() {
        return "43.6560484";
    }

    @Override
    public String getPlaceLongitude() {
        return "-79.3847495";
    }

    @Override
    public String getUnexpectedCountryIsoCode() {
        return "US";
    }

    @Override
    public String getPageCMSFirstJsonElement() {
        return "74kV6mF7hx2StlbgC3Z6mX";
    }

    @Override
    public String getKibanaCustomerKeyRegion() { return "_CA_PROD"; }

    @Override
    public String getKibanaLanguage() {
        return "ENG";
    }

    @Override
    public String getCreateAccountEmailSubject() {
        return "Your account has successfully been created.";
    }

    @Override
    public String getResetPasswordEmailSubject() { return "Reset Your Password"; }

    @Override
    public String countryAbbreviation() { return COUNTRY_CA; }

    @Override
    public String getLanguageIsoCode() { return "en_CA"; }
}
