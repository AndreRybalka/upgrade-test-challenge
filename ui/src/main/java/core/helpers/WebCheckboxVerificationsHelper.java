package core.helpers;

import core.base.controls.WebCheckbox;

import static core.SLF4JLogger.log;
import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;


public class WebCheckboxVerificationsHelper {

    public static void verifyWebCheckboxSelected(WebCheckbox webCheckbox, String elementName) {
        log(format("Checking that [%s] is selected", elementName));
        assertThat(webCheckbox.isSelected()).as(format("[%s] selected", elementName)).isTrue();
    }

    public static void verifyWebCheckboxSelected(WebCheckbox webCheckbox) {
        verifyWebCheckboxSelected(webCheckbox, webCheckbox.getName());
    }

}
