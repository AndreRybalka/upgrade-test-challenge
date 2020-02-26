
package core.utils;

import core.base.controls.BaseWebElement;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static constants.Constants.LENGTH_FOR_AUTO_FILL_SIGN_UP_FIELDS;
import static core.SLF4JLogger.error;
import static core.WebDriverFactory.getDriver;
import static core.contsants.Delays.MEDIUM_DELAY;
import static core.contsants.Delays.SHORT_DELAY;
import static core.helpers.StringGenereteHelpers.generateRandomEmailLocalPart;
import static core.testdata.provider.TestDataProviderHelper.getSiteUrl;
import static core.utils.WaitingUtils.sleep;

/**
 * General methods helpers
 */
public final class Utils {

	private  static int LOCALE_LIMIT = 2;
	private static Robot robot = null;
	private static final int timeToScroll = 500;
	private static final int timeToScrollRobot = 1000;

	/**
	 * Sort list by alphabet
	 *
	 * @param list
	 */
	public static List<String> sortList(List<String> list) {
		List<String> sortedList = new ArrayList<>();
		sortedList.addAll(list);
		Collections.sort(sortedList, new Comparator<String>() {
			public int compare(String o1, String o2) {
				return o1.toString().compareTo(o2.toString());
			}
		});
		return sortedList;
	}

	/**
	 * Method for generation email address
	 *
	 * @return generated email address
	 */

    public static String randomEmailLocalPart() {
        return generateRandomEmailLocalPart(LENGTH_FOR_AUTO_FILL_SIGN_UP_FIELDS).toLowerCase();
    }
	public static String generateMailAddress() {
		String prefix = "candidate";
		String currentTime = String.valueOf(System.currentTimeMillis());
		return String.format("%s%s%s%s", prefix, randomEmailLocalPart(), currentTime, "@upgrade-challenge.com");
	}

	public static String getCurrentCountry() {
		return Arrays.stream(getSiteUrl().split("/")).filter(locale -> locale.length() == LOCALE_LIMIT).findFirst().get().toUpperCase();
	}

	public static String getCurrentLocale(){
		return getDriver().getCurrentUrl().replaceAll("^.*com.?","").replaceAll("[_].*","")
				.replace("/","_").toUpperCase();
	}

	public static String getConfirmationThatCurrentPageHasEnglishLanguageInterface(){
		return  getDriver().getCurrentUrl().replaceAll("^.*com.?([\\/][a-z]{2})[\\/]","")
				.replaceAll("[_].*", "");
	}

	public static void mouseDoubleClickAction() {
		robot().mousePress(InputEvent.BUTTON1_MASK);
		robot().mouseRelease(InputEvent.BUTTON1_MASK);
		robot().mousePress(InputEvent.BUTTON1_MASK);
		robot().mouseRelease(InputEvent.BUTTON1_MASK);
	}

	/**
	 * @return random generated phone number
	 */
	public static String generatePhoneNumber() {
		final String numbers = "0123456789";
		final Random rand = new Random();

		// consider using a Map<String,Boolean> to check whether the identifier is being used or not
		final Set<String> identifiers = new HashSet<String>();

		StringBuilder phone = new StringBuilder();
		while (phone.toString().length() == 0) {
			for (int i = 0; i < 9; i++)
				phone.append(numbers.charAt(rand.nextInt(numbers.length())));
			if (identifiers.contains(phone.toString()))
				phone = new StringBuilder();
		}
		return "0" + phone.toString();
	}

	public static String getRandomAlphabetic(int charactersAmount) {
		return RandomStringUtils.randomAlphabetic(charactersAmount);
	}

	public static String getRandomNumeric(int charactersAmount) {
		return RandomStringUtils.randomNumeric(charactersAmount);
	}

	public static Matcher getMatcher(String regex, String matchedString) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(matchedString);
		return matcher;
	}

	public static String timestamp(String format) {
		return new SimpleDateFormat(format).format(new Date());
	}

	public static Double getPriceAsDoubleValue(BaseWebElement element){
		return Double.parseDouble(element.getTrimText().replace("$", ""));
	}

	public static Robot robot() {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		return robot;
	}

	public static void scrollMethod(int scrollStep, int increment) {
		int SCROLL = scrollStep;
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ESCAPE);
		} catch (AWTException e) {
			e.printStackTrace();
		}
		long curTime = System.currentTimeMillis();
		while (System.currentTimeMillis() - curTime < timeToScroll) {
			((JavascriptExecutor) getDriver())
					.executeScript(String
							.format("scrollBy(0, %d)", SCROLL -= increment));
		}
	}

	public static void scrollDownUsingRobot(){
		sleep(MEDIUM_DELAY);
		long curTime = System.currentTimeMillis();
		while (System.currentTimeMillis() - curTime < timeToScrollRobot) {
			robot().keyPress(KeyEvent.VK_DOWN);
		}
	}

	public static double getStringAsDouble(String trimText) {
		return Double.parseDouble(trimText);
	}

	public static List<String> getListOfTheProductsTitlesUtil(List<BaseWebElement> productTitles) {
		List<String> titles = new ArrayList<>();
		try {
			titles = productTitles.stream()
					.map(element -> {
						sleep(SHORT_DELAY);
						element.waitElementBeNotStale();
						return element.getTrimText();
					})
					.collect(Collectors.toList());
		} catch (StaleElementReferenceException e) {
			error(" StaleElementReferenceException has caught");
			getListOfTheProductsTitlesUtil(productTitles);
		}
		return titles;
	}

	public static void navigateBack() {
		getDriver().navigate().back();
	}

	public static int getStringAsInt(String trimText) {
		return Integer.parseInt(trimText);
	}
}