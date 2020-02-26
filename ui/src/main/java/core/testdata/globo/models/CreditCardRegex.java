package core.testdata.globo.models;

import core.testdata.models.preparedData.CreditCard;

public class CreditCardRegex {

	public static final String CREDIT_CARD_TYPES_REGEX = "([a-zA-Z\\s]+)([:])([\\sa-zA-Z,]+)";

	public static final String CREDIT_CARD_NUMBER_FORMATTING_REGEX = "([0-9]{4}\\s)([0-9]{4}\\s)([0-9]{4}\\s)([0-9]{4})";

	public static final String AMEX_CREDIT_CARD_NUMBER_FORMATTING_REGEX = "([0-9]{4}\\s)([0-9]{6}\\s)([0-9]{5})";

	public static final String EXPIRATION_DATE_FORMATTING_REGEX = "([0-9]{2}[\\D][0-9]{2})";

	public static final String CVC_FORMATTING_REGEX = "([0-9]{3})";

	public static final String AMEX_CVC_FORMATTING_REGEX = "([0-9]{4})";



	/**
	 * @param creditCard
	 * @return i.e. ([VISA]+)([ending in ]+)([1111]+)(\n)([Exp. ]+)([11\\D20]+)
	 */
	public static String getCollapsedCreditCardDetailsRegex(CreditCard creditCard) {
		return "([" + creditCard.getType().toString().toUpperCase() + "]+)([ending in ]+)(["
				+ creditCard.getNumber().substring(creditCard.getNumber().length() - 4) + "]+)(\n)([Exp. ]+)(["
				+ creditCard.getExMonth() + "\\D" + creditCard.getExYear() + "]+)";
	}
}
