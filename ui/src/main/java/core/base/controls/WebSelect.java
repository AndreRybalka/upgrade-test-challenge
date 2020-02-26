package core.base.controls;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * Customized select WebElement
 */
public class WebSelect extends Select {

	public WebSelect(WebElement wrappedElement) {
		super(wrappedElement);
	}

	public List<String> getOptionValues() {
		List<String> options = new ArrayList<>();
		List<WebElement> list = this.getOptions();
		for (int i = 1; i < list.size(); i++) {
			options.add(list.get(i).getText());
		}
		return options;
	}

	/**
	 * @return true if element id displayed and has height
	 */
	public boolean isPresent() {
		try {
			return (this.getWrappedElement().getSize().getHeight() > 0 & this.getWrappedElement().isDisplayed());
		} catch (NoSuchElementException e) {
			return false;
		} catch (StaleElementReferenceException e2) {
			return false;
		}
	}
}
