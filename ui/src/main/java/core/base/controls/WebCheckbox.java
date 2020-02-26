package core.base.controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static core.SLF4JLogger.log;

/**
 * Customized checkbox WebElement <br/>
 * Should be declared as @FindBy(css ="[aria-describedby='aria-describedby-value']")<br/>
 *
 */
public class WebCheckbox extends BaseWebElement {

	public WebCheckbox(WebElement wrappedElement) {
		super(wrappedElement);
	}

	public BaseWebElement getCheckboxInputElement() {
		return this.findElement(By.xpath(".//preceding-sibling::input"));
	}

	public BaseWebElement getMessageElement() {
		return this.findElement(By.xpath(".//small"));
	}

	public boolean isSelected() {
		return Boolean.valueOf(this.findElement(By.xpath("./preceding-sibling::input")).getAttribute("value"));
	}

	public void check() {
		this.waitForElementToBeClickable();
		this.waitElementBeNotStale();
		this.click();
	}
}
