package core.utils;

import org.testng.util.Strings;

import java.util.EnumSet;

public final class ConfigVariables {

    public static final String CA = "ca";

    public enum SystemPropertyKey {
        ENVIRONMENT("env"), BANNER("banner"), BROWSER("browser"), DEVICE("device"), LOCALE("locale"), SUITE("suite");

        private final String value;

        SystemPropertyKey(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }


    public enum PropertyKey {
        TIMEOUT_IN_SECONDS("timeoutInSeconds"), BIG_TIMEOUT("bigTimeout"), SMALL_TIMEOUT("smallTimeout");

        private final String value;

        PropertyKey(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }



    public enum EnvironmentType {
        LOCAL, REMOTE;

        public static EnvironmentType getEnvironmentType(final String environmentType) {
            if (Strings.isNullOrEmpty(environmentType)) {
                for (final EnvironmentType environment : EnvironmentType.values()) {
                    if (environment.toString().equalsIgnoreCase(environmentType)) {
                        return environment;
                    }
                }
            }
            return LOCAL;
        }

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }



    public enum DeviceType {
        DESKTOP, TABLET, MOBILE;

        public static DeviceType getDeviceType(final String deviceType) {
            if (Strings.isNullOrEmpty(deviceType)) {
                for (final DeviceType device : DeviceType.values()) {
                    if (device.toString().equalsIgnoreCase(deviceType)) {
                        return device;
                    }
                }
            }
            return DESKTOP;
        }

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }

    public enum Browser {
        FIREFOX, CHROME, IE; // SAFARI, ANDROID, IPHONE

        public static Browser getBrowser(final String browserName) {
            if (!Strings.isNullOrEmpty(browserName)) {
                for (final Browser browser : Browser.values()) {
                    if (browser.toString().equalsIgnoreCase(browserName)) {
                        return browser;
                    }
                }
            }
            return CHROME;
        }

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }



    public enum Country {
        CA("CA"), CA_FR("CA_FR");

        private final String value;

        Country(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static Country getCountry(final String locale) {
            if (!Strings.isNullOrEmpty(locale)) {
                for (final Country country : Country.values()) {
                    if (country.getValue().equalsIgnoreCase(locale)) {
                        return country;
                    }
                }
            }
            return CA;
        }
    }

    public enum Locale {
        CA_EN(Country.CA, "/ca/en"), CA_FR(Country.CA_FR, "/ca/fr");
        public static EnumSet<Locale> globo = EnumSet.of(CA_EN, CA_FR);
        private final Country country;
        private final String value;

        Locale(Country country, String value) {
            this.country = country;
            this.value = value;
        }

        public Country country() {
            return country;
        }

        public String value() {
            return value;
        }

        public static Locale getLocale(final String country) {
            if (!Strings.isNullOrEmpty(country)) {
                for (final Locale locale : Locale.values()) {
                    if (locale.country().getValue().equalsIgnoreCase(country)) {
                        return locale;
                    }
                }
            }
            return CA_EN;
        }
    }

    public enum BannerType {
        PROD("http://www.globoshoes.com", Locale.globo, "testData.json"),
        UAT("http://uat.globoshoes.com", Locale.globo, "testData.json"),
        QA3("http://qa3.globoshoes.com", Locale.globo, "testData.json"),
        PERF("http://perf.globoshoes.com", Locale.globo, "testData.json");

        private final String url;
        private final EnumSet<Locale> locales;
        private final String testData;

        BannerType(String url, EnumSet<Locale> locales, String testData) {
            this.url = url;
            this.locales = locales;
            this.testData = testData;
        }

        public String getURL() {
            return url;
        }

        public EnumSet<Locale> locales() {
            return locales;
        }

        public String getTestDataSet() {
            return testData;
        }

        public static BannerType getBannerType(final String bannerType) {
            if (!Strings.isNullOrEmpty(bannerType)) {
                for (final BannerType banner : BannerType.values()) {
                    if (banner.name().equalsIgnoreCase(bannerType)) {
                        return banner;
                    }
                }
            }
            return UAT;
        }

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }

    public enum Gender {
        MALE, FEMALE
    }

    public enum Language {
        EN, FR
    }
}