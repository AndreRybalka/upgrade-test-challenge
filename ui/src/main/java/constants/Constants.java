package constants;


import java.util.Arrays;
import java.util.List;

public class Constants {

    private Constants() {
    }

    //Loan purposes
    public static final String DEBT_CONSOLIDATION = "DEBT_CONSOLIDATION";

    //Url
    public static final String PERSONAL_INFORMATION = "personal-information";
    public static final String NONDMFFUNNEL_PAGE = "https://www.credify.tech/funnel/nonDMFunnel";
    public static final String LOGIN_PAGE = "https://www.credify.tech/portal/login";

    // Attributes
    public static final String ARIA_DISABLED ="aria-disabled";
    public static final String FALSE ="false";
    public static final String TRUE ="true";
    public static final String VALUE = "value";
    public static final String STYLE = "style";
    public static final String EMPTY_STRING = "";
    public static final String FOR_ATTROBUTE = "for";
    public static final String ID = "id";
    public static final String MAX_LENGTH = "maxlength";
    public static final String ARIA_CHECKED = "aria-checked";
    public static final String ARIA_LABEL = "aria-label";
    public static final String IS_SELECTED = "is-checked";
    public static final String TITLE = "title";
    public static final String COUNTER = "counter";
    public static final String ARIA_LABELLEDBY = "aria-labelledby";
    public static final String REMOVE_TITLE = "Remove";
    public static final String PATTERN_FOR_REQUEST_POSTDATA = "\"postData\":\".*";

    // My account links
    public static final String OVERVIEW = "Overview";
    public static final String ORDER_HISTORY = "Order history";
    public static final String FAVOURITES = "Favourites";
    public static final String OUR_PICKS = "Our picks";
    public static final String PAYMENT = "Payment";
    public static final String ADDRESS_BOOK = "Address book";
    public static final String MY_INFO = "My info";
    public static final String DETAILS = "Details";
    public static final String A_LIST_PREFERENCES = "A-List Preferences";
    public static final String MY_ACCOUNT_PREFERENCES = "My account preferences";
    public static final String SIGN_OUT = "Sign out";

    // Footer Links
    public static final String TRACK_MY_ORDER = "Track My Order";


    public static final List<String> ALL_MY_ACCOUNT_SIDEBAR_LINKS = Arrays.asList(OVERVIEW, ORDER_HISTORY, OUR_PICKS, PAYMENT, ADDRESS_BOOK, MY_ACCOUNT_PREFERENCES, SIGN_OUT);

    // Language links
    public static final String FR_LANGUAGE = "Fr";
    public static final String EN_LANGUAGE = "En";

    // International regions
    public static final String MIDDLE_EAST_AFRICA = "Middle East and Africa";

    // International countries
    public static final String SOUTH_AFRICA = "South Africa";
    public static final String EGYPT = "Egypt";

    // Language pop up items
    public static final String UNITED_STATES = "United States";
    public static final String CANADA = "Canada";
    public static final String OTHER_COUNTRIES = "See all 27 countries";

    // A-list gender attribute name
    public static final String MALE = "Male";
    public static final String FEMALE = "Female";

    // Pages URL ends
    public static final String PREFERENCES_PAGE = "/account/preferences";
    public static final String A_LIST_PAGE = "/newsletter";

    public static final String ACCOUNT_PAGE = "/account";
    public static final String INTERNATIONAL_PAGE = "/international";
    public static final String ME_TO_WE_PAGE = "/me-to-we";
    public static final String SERVER_ERROR_PAGE = "/serverErrorPage";
    public static final String PAYMENT_METHOD_PAGE = "/add-smoke.payment-method";
    public static final String ORDER_CONFIRMATION_PAGE = "/orderConfirmation";
    public static final String PAY_PAL_PAYMENT_TYPE_PAGE = "/payPalPaymentType";
    public static final String SIGN_IN_AND_SHIPPING_PAGE = "/signInAndShipping";
    public final static String FIND_STORES_PAGE = "find-in-store";
    public final static String FIND_STORES_RESULTS_PAGE = "store-finder/results";
    public final static String CHECKOUT_PAGE = "/checkout";
    public final static String STORE_LOCATOR_PAGE = "/store-locator";
    public final static String TRACK_ORDER_PAGE = "/track-order";
    public static final String WISH_LIST_PAGE = "/favourites";
    public static final String COLOR_URL_ATTRIBUTE = "Colour";
    public static final String PRICE_URL_ATTRIBUTE = "Price";
    public static final String SIZE_URL_ATTRIBUTE = "Size";
    public static final String HEIGHT_URL_ATTRIBUTE = "height";
    public static final String SUB_CATEGORY_URL_ATTRIBUTE = "Category";
    public static final String REWARDS_LOGIN_PAGE = "rewards.globoshoes.com";

    // selected values
    public static final String MIDDLE_AGE_SELECTION = "25to34";
    public static final String PERFECT_FIT_SELECTION = "PerfectFit";

    // Element text
    public static final String ME_TO_WE_PAGE_CENTRAL_BLOCK_TEXT = "Together, WE can make a change.";
    public static final String INTERAC_FIRST_PART_MESSAGE = "You will be directed to the Interac web page prompting you to select your online bank.";

    public static final String INTERAC_BASE_PART_MESSAGE = "The INTERAC ® Online payment option is currently only available to cardholders with online banking" +
            " at the following financial institutions: RBC Royal Bank, Scotiabank, TD Canada Trust, and BMO Bank of Montreal.";
    public static final String INTERAC_TRADE_MARK_MESSAGE = "® Trademark of Interac Inc. Used under license.";
    public static final String PAYPAL_TITLE = "PayPal-icon";
    public static final String EXPRESS_SHIPPING_TITLE = "Express";
    public static final String STANDARD_SHIPPING_TITLE = "Standard Free";
    public static final String TEST_TEXT = "Test text";
    public static final String CHARS_TOO_SHORT = "characters too short";
    public static final String ACTIVE = "active";
    public static final String REWARDS_LOGIN_TITLE = "JOIN THE FAMILY!";

    // Activate and deactivate new features
    public static final String ACTIVATE_CHECKOUT_IMPROVEMENTS =  "window.ABTest.activateFeature('checkoutImprovements')";
    public static final String DEACTIVATE_CHECKOUT_IMPROVEMENTS =  "window.ABTest.deactivateFeature('checkoutImprovements')";
    public static final String ACTIVATE_SECURE_PLACE =  "window.ABTest.activateFeature('securePlaceOrderText')";
    public static final String DEACTIVATE_SECURE_PLACE =  "window.ABTest.deactivateFeature('securePlaceOrderText')";
    public static final String ACTIVATE_FREE_POLICY =  "window.ABTest.activateFeature('freeReturnPolicy')";
    public static final String DEACTIVATE_FREE_POLICY =  "window.ABTest.deactivateFeature('freeReturnPolicy')";
    public static final String ACTIVATE_STICKY_CHECKOUT_BUTTON =  "window.ABTest.activateFeature('stickyCheckoutButton')";
    public static final String DEACTIVATE_STICKY_CHECKOUT_BUTTON =  "window.ABTest.deactivateFeature('stickyCheckoutButton')";
    public static final String ACTIVATE_STICKY_CHECKOUT_POP_UP =  "window.ABTest.activateFeature('stickyCheckoutPopup')";
    public static final String DEACTIVATE_STICKY_CHECKOUT_POP_UP =  "window.ABTest.deactivateFeature('stickyCheckoutPopup')";
    public static final String ACTIVATE_CHECKOUT_TOP_BUTTON =  "window.ABTest.activateFeature('checkoutTopButton')";
    public static final String DEACTIVATE_CHECKOUT_TOP_BUTTON =  "window.ABTest.deactivateFeature('checkoutTopButton')";
    public static final String ACTIVATE_OPEN_CARD_ON_ADD_PRODUCT ="window.ABTest.activateFeature('openCartOnAddProduct')";
    public static final String DEACTIVATE_OPEN_CARD_ON_ADD_PRODUCT ="window.ABTest.deactivateFeature('openCartOnAddProduct')";
    public static final String ACTIVATE_EXPANDING_TAXES ="window.ABTest.activateFeature('expandingTaxes')";
    public static final String DEACTIVATE_EXPANDING_TAXES ="window.ABTest.deactivateFeature('expandingTaxes')";
    public static final String ACTIVATE_INTERNATIONAL_BILLING_MODE =  "window.ABTest.activateFeature('internationalCreditCard')";
    public static final String DEACTIVATE_INTERNATIONAL_BILLING_MODE =  "window.ABTest.deactivateFeature('internationalCreditCard')";


    public static List<String> FULL_COMMANDS_SCOPE = Arrays.asList(ACTIVATE_CHECKOUT_IMPROVEMENTS,ACTIVATE_SECURE_PLACE,ACTIVATE_FREE_POLICY,
            ACTIVATE_STICKY_CHECKOUT_BUTTON,ACTIVATE_STICKY_CHECKOUT_POP_UP,ACTIVATE_CHECKOUT_TOP_BUTTON, ACTIVATE_OPEN_CARD_ON_ADD_PRODUCT);

    public static List<String>WITHOUT_OPEN_CARD_ON_ADD_PRODUCT = Arrays.asList(ACTIVATE_CHECKOUT_IMPROVEMENTS,ACTIVATE_SECURE_PLACE,ACTIVATE_FREE_POLICY,
            ACTIVATE_STICKY_CHECKOUT_BUTTON,ACTIVATE_STICKY_CHECKOUT_POP_UP,ACTIVATE_CHECKOUT_TOP_BUTTON, DEACTIVATE_OPEN_CARD_ON_ADD_PRODUCT);

    public static List<String>MAIN_PROD_TOGGELS = Arrays.asList(ACTIVATE_CHECKOUT_IMPROVEMENTS, DEACTIVATE_INTERNATIONAL_BILLING_MODE, DEACTIVATE_OPEN_CARD_ON_ADD_PRODUCT, ACTIVATE_STICKY_CHECKOUT_POP_UP,
            ACTIVATE_STICKY_CHECKOUT_BUTTON, DEACTIVATE_CHECKOUT_TOP_BUTTON, DEACTIVATE_OPEN_CARD_ON_ADD_PRODUCT, ACTIVATE_EXPANDING_TAXES);

    public static List<String>MAIN_PROD_TOGGELS_WITH_IB = Arrays.asList(ACTIVATE_CHECKOUT_IMPROVEMENTS, DEACTIVATE_OPEN_CARD_ON_ADD_PRODUCT, ACTIVATE_STICKY_CHECKOUT_POP_UP,
            ACTIVATE_STICKY_CHECKOUT_BUTTON, DEACTIVATE_CHECKOUT_TOP_BUTTON, DEACTIVATE_OPEN_CARD_ON_ADD_PRODUCT, ACTIVATE_EXPANDING_TAXES, ACTIVATE_INTERNATIONAL_BILLING_MODE);

    public static List<String>MAIN_PROD_TOGGELS_WITHOUT_IB = Arrays.asList(ACTIVATE_CHECKOUT_IMPROVEMENTS, DEACTIVATE_OPEN_CARD_ON_ADD_PRODUCT, ACTIVATE_STICKY_CHECKOUT_POP_UP,
            ACTIVATE_STICKY_CHECKOUT_BUTTON, DEACTIVATE_CHECKOUT_TOP_BUTTON, DEACTIVATE_OPEN_CARD_ON_ADD_PRODUCT, ACTIVATE_EXPANDING_TAXES, DEACTIVATE_INTERNATIONAL_BILLING_MODE);

    public static List<String> DEACTIVATE_IMPROVEMENTS_AND_OPEN_CARD_ON_ADD_PRODUCT = Arrays.asList(DEACTIVATE_CHECKOUT_IMPROVEMENTS, DEACTIVATE_OPEN_CARD_ON_ADD_PRODUCT);

    public static List<String> DEACTIVATE_IB_AND_OPEN_CARD_ON_ADD_PRODUCT = Arrays.asList(DEACTIVATE_INTERNATIONAL_BILLING_MODE, DEACTIVATE_OPEN_CARD_ON_ADD_PRODUCT);


   // Regexp
   public static final String SELECT_DIGITS_PATTERN = "\\d+";
   public static final String ALL_AFTER_DIGIT_PATTERN = "\\d(.*)";
   public static final String COLLAPSED_USER_DETAILS = "([a-zA-z]+)(\\s+)([a-zA-z]+)(\\n)([a-z0-9@.]+)";
   public static final String PATTERN_FOR_REQUEST_ID = "status\\W+\"((?!20)\\d+)\"\\W+[a-z-]*\\W:\"[0-9a-z-]*\"";
   public static final String PATTERN_FOR_URL_REQUEST = "\"url\":\".*?(?=\")\"";
    public static final String ONLY_DIGITS_PATTERN = "\\d+\\.?\\,?\\d+";


   // Numeric values
   public static final int QUANTITY_TO_SELECT = 2;

   // Promo code
   public final static  String PROMO_CODE_15_DOLLARS_CA = "U3R-A91C-6NA3-6KX8";
   public final static  String PROMO_CODE_15_DOLLARS_US = "R3P-DBMH-TFAZ-CABX";

   // fields length validators
   public static final int LENGTH_FOR_AUTO_FILL_SIGN_UP_FIELDS = 7;
   public final static int FIRST_LAST_NAME_LIMIT = 25;
    public final static int ERROR_SEEDING_LIMIT = 5;
    public final static int ADDRESS_COMPANY_LIMIT = 35;
    public final static int CITY_LIMIT = 50;
    public final static int CREDIT_CARD_LIMIT = 22;
    public final static int EXPIRATION_LIMIT = 5;
    public final static int CVC_LIMIT = 4;
    public final static int DEFAULT_LIMIT = 22;
    public final static int PHONE_LIMIT = 15;
    public static final int ONE_ITEM_AMOUNT = 1;
    public static final int TWO_ITEM_AMOUNT = 2;
    public static final String DOUBLE_ZERO = "0.00";
    public static final String EMPTY_AMOUNT = "0";
    public static final String EMPTY_ARRAY = "[]";

    // for field autocomplete
   // public static final String NO_SIZE_PART_TO_FIND = "celid";
    public static final String NO_SIZE_PART_TO_FIND = "aferas";
   // public static final String NO_SIZE_SUGGESTION = "celide";
    public static final String NO_SIZE_SUGGESTION = "aferasa";
   // public static final String PART_TO_FIND = "matea";
    public static final String PART_TO_FIND = "arthe";
   // public static final String SUGGESTION = "mateare";
    public static final String SUGGESTION = "arther";

    // main categories
    public static final String WOMEN = "Women";
    public static final String MEN = "Men";
    public static final String KIDS = "Kids";
    public static final String SALE = "Sale";
    public static final String BRANDS = "Brands";
    public static final String WOMEN_FR = "Femmes";
    public static final String MEN_FR = "Hommes";
    public static final String KIDS_FR = "Enfants";
    public static final String SALE_FR = "Soldes";
    public static final String BRANDS_FR = "Marques";
    public static final String HANDBAGS = "HANDBAGS";
    public static final String ACCESSORIES = "ACCESSORIES";
    public static final String CLEARANCE = "CLEARANCE";

    // mail generator string
    public static final String CONSTANT_STRING_FOR_EMAIL_GENERATION = "ABCDEFGHIJKLMNOPQRSTUVWXYZ._-0123456789abcdefghijklmnopqrstuvwxyz";

    //Negative validations
    public static final String WRONG_PASSWORD = "Wrong Password";

    public static final class Suites{
        public static final String FAST_SMOKE = "fastsmoke";
    }

}
