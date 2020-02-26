package core.base.controls;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static core.SLF4JLogger.log;
import static java.lang.String.format;

/**
 * Customized input WebElement
 *
 */
public class WebInput extends BaseWebElement {

	public WebInput(WebElement wrappedElement) {
		super(wrappedElement);
	}

	private int counter;

	private static final String INPUT_LABEL = "./following-sibling::label";
	private static final String VALIDATION_MESSAGE = "./following-sibling::div[contains(@class,'c-form-field-validation')]";

	public BaseWebElement getMandatoryMessage(String text) {
		this.type(text);
		this.clear();
		this.blur();
		return this.findElement(By.xpath(VALIDATION_MESSAGE));
	}

	private BaseWebElement getInvalidMessage(String text) {
		this.type(text);
		this.blur();
		return this.findElement(By.xpath(VALIDATION_MESSAGE));
	}

	public boolean isMandatory(String text, String expected) {
		try {
			getMandatoryMessage(text).waitForTextInElementPresentEquals(expected);
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}

	public boolean isMandatory(String expected) {
			return isMandatory("test", expected);
	}

	public boolean isInvalid(String text, String message) {
		try {
			getInvalidMessage(text).waitForTextInElementPresentEquals(message);
			this.clear();
			this.blur();
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}

	public String getLabelText() {
		return this.getWrappedElement().findElement(By.xpath(INPUT_LABEL)).getText().trim();
	}

	public int getCharactersAmount() {
		return this.getAttribute("value").length();
	}

	public void clear() {
		this.getWrappedElement().sendKeys(Keys.chord(Keys.CONTROL, "a"));
		this.getWrappedElement().sendKeys(Keys.BACK_SPACE);
		if(!getWrappedElement().getAttribute("value").isEmpty()){
			int typed = getWrappedElement().getAttribute("value").length();
			this.getWrappedElement().click();
			for(int i=0; i<typed;i++) {
				this.getWrappedElement().sendKeys(Keys.chord(Keys.HOME));
				this.getWrappedElement().sendKeys(Keys.chord(Keys.SHIFT, Keys.END));
				this.getWrappedElement().sendKeys(Keys.BACK_SPACE);
			}
		}
	}

	/**
	 * Clear input field and type new specified text
	 * 
	 * @param text
	 */
	public void type(String text) {
		this.clear();
		log(format("Entering [%s] text in '%s'", text, this.getName()));
		this.getWrappedElement().sendKeys(text);
	}

	public void typeEnter(String text) {
		this.clear();
		log(format("Entering [%s] text in '%s'", text, this.getName()));
		this.getWrappedElement().sendKeys(text + Keys.ENTER);
	}

	public void typeWithCheck(String text) {
		counter++;
		this.clear();
		log(format("Entering [%s] text in '%s'", text, this.getName()));
		this.getWrappedElement().sendKeys(text);
		while(counter<6) {
			if (!this.getWrappedElement().getAttribute("value").equalsIgnoreCase(text)) {
				log("Entered text contains mistake. Re-enter attempt: "+counter);
				typeWithCheck(text);
			}else return;
		}
	}

	//TODO bad practice, implement Visitor pattern here in future http://stackoverflow.com/questions/450807/how-do-i-make-the-method-return-type-generic
	@SuppressWarnings("unchecked")
	public WebInput waitForElementPresent() {
		waitForExpectedCondition(ExpectedConditions.visibilityOf(this.getWrappedElement()), "present");
		return this;
	}
}
