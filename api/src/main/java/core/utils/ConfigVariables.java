package core.utils;

import core.testdata.providers.TestDataProviderAPI;
import org.testng.util.Strings;

import static core.utils.ConfigVariables.Lang.*;

public class ConfigVariables {

    public static final String CA = "en";
    public static final String CA_FR = "fr";
    public static final String US = "en_US";
    public static final String UK = "en_UK";
    public static final String IE_EN = "en_IE";
    public static final String EU_DE = "de_EU";
    public static final String IT_IT = "it_IT";
    public static final String FR_FR = "fr_FR";


    public enum SystemPropertyKey {
      REGION("region"), LANG("lang"), SUITE("suite");


        private final String value;

        SystemPropertyKey(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }


    public enum Region {

        CA_EN(CA_EN_LANG,"ca"), CA_FR(CA_FR_LANG,"ca"), US(US_LANG, "us"), UK(UK_LANG,"uk"), IE_EN(IE_EN_LANG, "ie"), EU_DE(EU_DE_LANG,"de"), IT_IT(EU_IT_LANG, "it"), FR_FR(EU_FR_LANG, "fr");

        private final String regionValue;
        private final Lang langValue;

        Region(Lang langValue, String regionValue) {
            this.langValue = langValue;
            this.regionValue = regionValue;
        }

        public String getValue() {
            return regionValue;
        }

        public Lang getLangValue() {
            return langValue;
        }

        public static Region getRegion(final String lang) {
            if (!Strings.isNullOrEmpty(lang)) {
                for (final Region region : Region.values()) {
                    if (region.getLangValue().getValue().equalsIgnoreCase(lang)) {
                        return region;
                    }
                }
            }

            return TestDataProviderAPI.getRegion();
        }
    }

    public enum Lang {
        CA_EN_LANG("en"),CA_FR_LANG("fr"), US_LANG("en_US"), UK_LANG("en_UK"), IE_EN_LANG("en_IE"), EU_DE_LANG("de_EU"), EU_IT_LANG("it_IT"), EU_FR_LANG("fr_FR");

        private final String value;

        private Lang(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static Lang getLang(final String lang) {
            if (!Strings.isNullOrEmpty(lang)) {
                for (final Lang langItem : Lang.values()) {
                    if (langItem.getValue().equalsIgnoreCase(lang)) {
                        return langItem;
                    }
                }
            }
            return CA_EN_LANG;
        }

    }

    public enum PropertyKey {
        TIMEOUT_IN_SECONDS("timeoutInSeconds"), BIG_TIMEOUT("bigTimeout"), SMALL_TIMEOUT("smallTimeout");

        private final String value;

        private PropertyKey(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
