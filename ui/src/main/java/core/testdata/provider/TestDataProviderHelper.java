package core.testdata.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.testdata.models.preparedData.*;
import core.utils.ConfigVariables.BannerType;
import core.utils.ConfigVariables.Locale;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static core.utils.ConfigVariables.BannerType.getBannerType;
import static core.utils.ConfigVariables.SystemPropertyKey.BANNER;
import static core.utils.ConfigVariables.SystemPropertyKey.LOCALE;
import static core.utils.Utils.getCurrentCountry;
import static java.lang.System.getProperty;
import static org.springframework.util.ResourceUtils.getFile;


public class TestDataProviderHelper {
    private TestData testData;
    private static Predicate<ICountry> countryPredicate = (country) -> country.getCountries().equals(getCurrentCountry())
            || country.getCountries().contains(getCurrentCountry());

    private TestDataProviderHelper() {
        try {
            testData = new ObjectMapper().readValue(getFile("classpath:"
                    + getBanner().getTestDataSet()), TestData.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class SingletonHolder {
        private static final TestDataProviderHelper HELPER_INSTANCE = new TestDataProviderHelper();
    }

    static TestDataProviderHelper getDataProviderHelper() {
        return SingletonHolder.HELPER_INSTANCE;
    }

    TestData getTestData() {
        return testData;
    }

    List<User> getUsers() {
        return getTestData().getUsers();
    }

    List<Address> getAddress() {
        return getTestData().getAddresses();
    }

    List<CreditCard> getCreditCards() {
        return getTestData().getCreditCards();
    }

    List<PayPal> getPayPals() {
        return getTestData().getPayPals();
    }

    List<VisaCheckoutAccount> getVisaCheckoutAccounts() {
        return getTestData().getVisaCheckoutAccounts();
    }

    List<Interac> getInteracs() {
        return testData.getInteracs();
    }

    Interac getCanadaInterac() {
        return new HashSet<>(getInteracs()).iterator().next();
    }

    <T extends ICountry> T getCurrentLocaleData(List<T> dataJson) {
        return getJSONData(dataJson)
                .findFirst()
                .get();
    }

    <T extends ICountry> List<T> getCurrentLocaleDataList(List<T> dataJson) {
        return getJSONData(dataJson)
                .collect(Collectors.toList());
    }

    private <T extends ICountry> Stream<T> getJSONData(List<T> dataJson) {
       return dataJson
                .stream()
                .filter(countryPredicate);
    }

    private static BannerType getBanner() {
        return getBannerType(getProperty(BANNER.getValue()));
    }

    private static Locale getLocale() {
        return Locale.getLocale(getProperty(LOCALE.getValue()));
    }

    public static String getSiteUrl() {
        return getBanner().getURL() + getLocale().value();
    }

    public static String getSiteUrl(Locale locale) {
        return getBanner().getURL() + locale.value();
    }
}
