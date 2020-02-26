package core.testdata.data;

import java.util.List;
import java.util.Map;

import static core.constants.Constants.*;

public abstract class AbstractTestData {

    public String getHostName() { return UPGRADE_HOSTNAME; }


    public String getUpgradeUserPassword() {
        return UPGRADE_USER_PASSWORD;
    }

    public String getUpgradeWrongUserPassword() {
        return UPGRADE_WRONG_USER_PASSWORD;
    }

    public String getUpgradeUserName() {
        return UPGRADE_USER_NAME;
    }


    public String getSecureToken() {
        return COMPOSER_API_SECURE_TOKEN;
    }

    public String getPort() {
        return COMPOSER_PORT;
    }

    //public String getHostName() { return "0"; }

    public String getNewComposerUserPassword() {
        return NEW_COMPOSER_USER_PASSWORD ;
    }

    public String getComposerUserPassword() {
        return "0";
    }

    public String getSearchMailosaur(){return SEARCH_MAILOSAUR;}

    public String getDeleteAllMailosaur(){return DELETE_ALL_MAILOSAUR;}

    public String getComposerUserName() {
        return "0";
    }

    public String getNewComposerUserName() {
        return NEW_COMPOSER_USER_NAME;
    }

    public String getAldoApiVersion(){
        return "2";
    }

    public String getAldoBrand(){
       return COMPOSER_ALDO_BRAND;
   }

    public String getUnexpectedCountryIsoCode(){
        return "DE";
    }

    public String getTempUserName(){
    return "aldoonemoreacctest@gmail.com";
    }

    public String womenTextOnDiffLocales(){
    return WOMEN_CA_EN;
    }


    public abstract String getFirstUserAddressLine();

    public abstract String getFirstUserAddressLineUpdate();

    public abstract String getUserAddressPhoneNumber();

    public abstract String getUserAddressPhoneNumberUpdate();

    public abstract String getUserAddressCity();

    public abstract String getUserAddressCityUpdate();

    public abstract String getUserAddressPostalCode();

    public abstract String getUserAddressPostalCodeUpdate();

    public abstract String getUserAddressRegionISOCode();

    public abstract String getUserAddressRegionISOCodeUpdate();

    public abstract String getWomenText();

    public abstract String getMenText();

    public abstract String getGiftCardText();

    public abstract String getMXText();

    public abstract String getProductSku();

    public abstract String getProductArticleWithSize();

    public abstract String getProductName();

    public abstract String getHubrisProductCode();

    public abstract String getHubrisProductCodeWithAdditionalOfferInCart();

    public abstract String getTaxValue();

    public abstract String storesAmount();

    public abstract String getStoreLatitude();

    public abstract String getStoreLongitude();

    public abstract String getStoreName();

    public abstract Map<String, String> getLocaleMainAttributes();

    public abstract Map<String, String> getCartTypes();

    public abstract Map<String, String> getAdditionalProductOffer();

    public abstract List<String> additionalProductsOnPDP();

    public abstract String getPlaceID();

    public abstract String getPlaceLatitude();

    public abstract String getPlaceLongitude();

    public abstract String getPageCMSFirstJsonElement();

    public abstract String countryAbbreviation();

    public abstract String getLanguageIsoCode();

    //Data for email verification tests
    public abstract String getKibanaCustomerKeyRegion();

    public abstract String getKibanaLanguage();

    public abstract String getCreateAccountEmailSubject();

    public abstract String getResetPasswordEmailSubject();

}
