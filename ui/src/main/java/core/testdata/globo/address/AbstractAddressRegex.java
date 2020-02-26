package core.testdata.globo.address;

public abstract class AbstractAddressRegex {

    /**
     * i.e. <br/>
     * First Name Last Name <br/>
     * Street address 123 <br/>
     * City, PE S7K 0V1 <br/>
     * (123) 456-7897 <br/>
     * <p>
     * Where: <br/>
     * User name....group 1 <br/>
     * Street.......group 3 <br/>
     * City.........group 5 <br/>
     * State........group 7 <br/>
     * Postal code..group 9 <br/>
     * Phone........group 11 <br/>
     */
    public static final String SHIPPING_ADDRESS_WITH_NAME = "([a-zA-Z\\s]+)(\\n)([a-zA-Z0-9\\s]+)(\\n)([a-zA-Z]+)(,\\s+)([A-Z]+)(\\s)([A-Z0-9\\s]+)(\\n)([0-9\\D]+)";
    /**
     * i.e. <br/>
     * Valeriy Kolodochka <br/>
     * Raad Vlaamse Gemeenschapscommissie <br/>
     * Brussel, 1000 <br/>
     * 0789006781 <br/>
     * <p>
     * Where: <br/>
     * User name....group 1 <br/>
     * Street.......group 3 <br/>
     * City.........group 5 <br/>
     * Postal code..group 7 <br/>
     * Phone........group 9 <br/>
     */
    public static final String SHIPPING_ADDRESS_WITH_NAME_EU = "([a-zA-Z\\s]+)(\\n)([a-zA-Z0-9\\s]+)(\\n)([a-zA-Z]+)(,\\s+)([0-9]+)(\\n)([0-9]+)";

    /**
     * i.e. <br/>
     * 201 1st Street West <br/>
     * Saskatoon, SK S7K 1 <br/>
     * Estimated delivery date: 3-6 business days <br/>
     * <p>
     * Where: <br/>
     * Street.......group 1 <br/>
     * City.........group 3 <br/>
     * State........group 5 <br/>
     * Postal code..group 6 <br/>
     * Estimation...group 8 <br/>
     */
    public final static String COLLAPSED_STORE_SHIPPING_ADDRESS_REGEX = "([0-9a-zA-Z\\s]+)([\\n])([a-zA-Z]+)([,\\s]+)([A-Z]+)([A-Z0-9\\s]+)([\\n])([a-zA-Z\\D0-9]+)";

    /**
     * i.e. <br/>
     * Valeriy Kolodochka <br/>
     * 8 Windsor Street <br/>
     * Edinburgh, United Kingdom EH7 5JR <br/>
     * 01234 56789 <br/>
     * <p>
     * Where: <br/>
     * Name.........group 1 <br/>
     * Street.......group 3 <br/>
     * City.........group 5 <br/>
     * Country......group 7 <br/>
     * Postal code..group 9 <br/>
     * Phone........group 11 <br/>
     */

    public abstract String getPostCodeRegex();

    public final static String SAVED_SHIPPING_ADDRESS_UK = "([a-zA-Z\\s]+)(\\n)([a-zA-Z0-9\\s]+)(\\n)([a-zA-Z]+)(,\\s+)([United Kingdom]+)(\\s)([A-Z0-9\\s]+)(\\n)([0-9\\D]+)";
    public final static String PROVINCE_STATE_OPTION_VALUE_REGEX = "([A-Z]{2})";

    public final static String PHONE_NUMBER_REGEX = "([(][0-9]{3}[)])(\\s)([0-9]{3})(-)([0-9]{4})";
    public final static String PHONE_NUMBER_REGEX_UK = "([0][0-9]{4}\\s[0-9]{5,6})";

}
