package core.testdata.globo.models;

public class OrderDetailsRegex {

	public final static String GST_TAX_REGEX = "([GST\\DTPS]+)(\\s+)(\\D[0-9.]+)";
	public final static String PST_TAX_REGEX = "([PST\\DTVP]+)(\\s+)(\\D[0-9.]+)";
	/**
	 * i.e. <br/>
	 * Your order (#023013069) will be delivered by Thursday, November 10. You will receive a
	 * confirmation email to autestermail@yopmail.com shortly.
	 */
	public final static String ORDER_NUMBER_REGEX = "(.*)([(#])([0-9]+)([)])(.*)";

	/**
	 * i.e. <br/>
	 * 10% OFF + Free Shipping - QARemove Promo Code
	 */
	public final static String PROMO_CODE_NAME_REGEX = "([\\D\\d]+[QA])(\\n)([\\D\\d]+)";
}
