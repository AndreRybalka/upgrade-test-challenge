package core.base.controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Customized radio button WebElement
 *
 */
public class WebRadio extends BaseWebElement {

	public WebRadio(WebElement wrappedElement) {
		super(wrappedElement);
	}

	public boolean isRadioSelected() {
		return this.findElement(By.xpath(".//input")).isSelected();
	}

	public void selectRadio(){
		this.findElement(By.xpath(".//input")).clickJS();
	}
}
