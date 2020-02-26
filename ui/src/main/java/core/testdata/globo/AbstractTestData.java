package core.testdata.globo;

import core.testdata.globo.models.OrderDetailsRegex;
import core.testdata.models.TopMenus;
import core.testdata.models.preparedData.User;
import core.utils.Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;

import static constants.Constants.*;
import static core.SLF4JLogger.error;
import static core.SLF4JLogger.getTestName;
import static core.contsants.Environments.PROD;
import static core.utils.ConfigVariables.BannerType.getBannerType;
import static core.utils.ConfigVariables.SystemPropertyKey.BANNER;
import static core.utils.Directories.getPathToOrdersFile;
import static core.utils.Utils.timestamp;
import static java.lang.String.format;
import static java.lang.System.getProperty;
import static java.lang.System.lineSeparator;
import static java.nio.file.Files.*;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.util.Arrays.asList;
import static org.apache.commons.lang.StringUtils.EMPTY;
import static org.apache.commons.lang.StringUtils.isNotEmpty;

public abstract class AbstractTestData {

    public abstract String getDesiredAmount();

    public abstract String getIncomeAmount();

    public abstract String getAdditionalIncomeAmount();

    public abstract String getPaymentButtonTitle();

    public abstract String getReviewOrderButtonTitle();

    public abstract String getShippingButtonTitle();

    public abstract String getStoreButtonTitle();

    public abstract String heelsItemLink();

    public abstract String noSizeItemHomePage();

    public abstract String withSizeItemPDP();

    public abstract String getPromoCode();

    public abstract String heelsPLP();

    public abstract String colorParameterInURL();

    public abstract String solrlSearchPartToFindNoSize();

    public abstract String solrlSearchProductNoSize();

    public abstract String solrlSearchPartToFindWithSize();

    public abstract String solrlSearchProductWithSize();

    public abstract String thirdStateValue();

    public abstract  String trackMyOrderPage();



    public  String  getOrderNumberRegex(){
        return OrderDetailsRegex.ORDER_NUMBER_REGEX;
    }

    public TopMenus getTopMenus() {
        return new TopMenus(new ArrayList<>(asList(WOMEN, MEN, SALE)));
    }

    public static void writeOrderNumber(String orderNumber) {
        writeOrderNumberToTxt(format("%s= Order number: [%s], on time: [%s]",
                getTestName().replaceAll("(?i)(.*)_Id.*$", "$1"),
                orderNumber, timestamp("MM/dd/yyyy HH:mm z")));
    }

    private static void writeOrderNumberToTxt(String orderNumber) {
        Path path = Paths.get(getPathToOrdersFile());
        try {
            if (exists(path)) {
                write(path, (orderNumber + lineSeparator()).getBytes(), APPEND);
            } else if (notExists(path)) {
                if (path.getParent() != null) {
                    Files.createDirectories(path.getParent());
                }
                write(path, (orderNumber + lineSeparator()).getBytes(), CREATE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User getUser(String userDetails) {
        User user = new User();
        Matcher matcher = Utils.getMatcher(COLLAPSED_USER_DETAILS, userDetails);
        if (matcher.matches()) {
            user.setEmailAddress(matcher.group(5));
            user.setFirstName(matcher.group(1));
            user.setLastName(matcher.group(3));
        } else {
            error("User details did NOT match with regex");
        }
        return user;
    }


    public enum PromoCode {

        ORDER("QS2-10OFF", "10% OFF - QA", "CE0-SZ2A-PTYE-RZZR", "you saved money"), ORDER_SHIPPING("QS3-10FREE",
                "10% OFF + Free Shipping - QA", EMPTY, EMPTY), SHIPPING("QS4-SHIP", "Free Shipping - QA", EMPTY, EMPTY);

        private String testValue;
        private String testTitle;
        private String prodValue;
        private String prodTitle;

        PromoCode(String testValue, String testTitle, String prodValue, String prodTitle) {
            this.testValue = testValue;
            this.testTitle = testTitle;
            this.prodValue = prodValue;
            this.prodTitle = prodTitle;
        }

        public String getValue() {
            if (getBannerType(getProperty(BANNER.getValue())).toString().equalsIgnoreCase(PROD)) {
                if (isNotEmpty(prodValue)) {
                    return prodValue;
                }
                throw new IllegalArgumentException(format("No value found for [%s] promo on [%s] environment", this.name(), PROD));
            }
            return testValue;
        }

        public String getTitle() {
            if (getBannerType(getProperty(BANNER.getValue())).toString().equalsIgnoreCase(PROD)) {
                if (isNotEmpty(prodTitle)) {
                    return prodTitle;
                }
                throw new IllegalArgumentException(format("No title found for [%s] promo on [%s] environment", this.name(), PROD));
            }
            return testTitle;
        }
    }
}


