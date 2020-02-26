package core.helpers;

import core.testdata.models.preparedData.ICountry;

import java.util.List;

import static core.SLF4JLogger.log;
import static java.lang.String.format;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ComparisonHelper {
    public static void verifyStringsEqual(String actual, String expected, String description) {
        log(format("Checking '%s', expected [%s], actual - [%s]", description, expected, actual));
        assertThat(expected).as(description).isEqualTo(actual);
    }

    public static void verifyStringEndsWith(String actual, String expected, String description) {
        log(format("Checking that '%s', ends with [%s]", actual, expected));
        assertThat(actual).as(description).endsWith(expected);
    }

    public static void verifyStringNotEndsWith(String actual, String expected, String description) {
        log(format("Checking that '%s', NOT ends with [%s]", actual, expected));
        assertThat(actual).as(description).doesNotEndWith(expected);
    }

    public static void verifyStringContains(String actual, String expectedToContain, String description) {
        log(format("Checking '%s', expected [%s] to contain - [%s]", description, actual, expectedToContain));
        assertThat(actual).as(description).containsIgnoringCase(expectedToContain);
    }

    public static void verifyStringsNotEqual(String actual, String unexpected, String description) {
        log(format("Checking '%s', unexpected [%s], actual - [%s]", description, unexpected, actual));
        assertThat(unexpected).as(description).isNotEqualTo(actual);
    }

    public static void verifyListNotEmpty(List actual, String listName) {
        log(format("Checking that '%s', is not null or empty", listName));
        assertThat(actual.size()).as(format("'%s' size", listName)).isNotEqualTo(0);
        assertThat(actual).as(format("'%s' is not null", listName)).isNotNull();
    }

   /* public static void verifyDoubleValuesEquals(Double actual, Double expected) {
        log(format("Checking that '%.2f' equals '%.2f'", actual, expected));
        assertThat(actual).isEqualTo(expected);
    }*/

    public static void verifyDoubleValuesEquals(Double actual, Double expected, String description) {
        log(format("Checking that '%.3f' equals '%.3f' action/result [%s] was checked", actual, expected, description));
        assertThat(actual).as(description).isEqualTo(expected);
    }

    public static void verifyIntegerValuesEquals(Integer actual, Integer expected) {
        log(format("Checking that '%d' equals '%d'", actual, expected));
        assertThat(actual).isEqualTo(expected);
    }
}
