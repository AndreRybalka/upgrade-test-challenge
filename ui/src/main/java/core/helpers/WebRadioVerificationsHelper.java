package core.helpers;

import core.base.controls.WebRadio;

import static core.SLF4JLogger.log;
import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;



public class WebRadioVerificationsHelper {

    public static void verifyWebRadioNotSelected(WebRadio webRadio, String elementName) {
        log(format("Checking that [%s] is NOT selected", elementName));
        assertThat(webRadio.isRadioSelected()).as(format("[%s] selected", elementName)).isFalse();
    }

    public static void verifyWebRadioNotSelected(WebRadio webRadio) {
        verifyWebRadioNotSelected(webRadio, webRadio.getName());
    }

    public static void verifyWebRadioSelected(WebRadio webRadio, String elementName) {
        log(format("Checking that [%s] is NOT selected", elementName));
        assertThat(webRadio.isRadioSelected()).as(format("[%s] selected", elementName)).isTrue();
    }

    public static void verifyWebRadioSelected(WebRadio webRadio) {
        verifyWebRadioNotSelected(webRadio, webRadio.getName());
    }

}
