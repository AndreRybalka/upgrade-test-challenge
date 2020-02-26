package core.utils;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

import static core.WebDriverFactory.getDriver;


public class XPathExecutorUtils {

    private static Document pageSource = null;
    private static XPathExpression expr = null;

    public static String getElementTextByXpathExecutor(String textXpath) {
        Function<String, String> stringFunction = (resultString) -> resultString;
        checkThatObjectIsNotNull(XPathExecutorUtils::getDocumentBuilder)
                .ifPresent((documentBuilder) -> {
                    pageSource = parsePageSource();
                    compileXpathExpression(textXpath);
                    checkThatObjectIsNotNull(() -> expr).ifPresent(expression ->
                            stringFunction.apply(stringResultOfEvaluation()));
                });
        return stringFunction.apply(stringResultOfEvaluation().trim());
    }

    private static <T> Optional<T> checkThatObjectIsNotNull(Supplier<T> resolver) {
        try {
            T result = resolver.get();
            return Optional.ofNullable(result);
        } catch (NullPointerException e) {
            return Optional.empty();
        }
    }

    private static Document parsePageSource() {
        try {
            pageSource = getDocumentBuilder().parse(new ByteArrayInputStream(getDriver().getPageSource().getBytes()));
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        return pageSource;
    }

    private static void compileXpathExpression(String stringXpath) {
        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();
        try {
            expr = xpath.compile(stringXpath);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    private static String stringResultOfEvaluation() {
        String resultString = "";
        try {
            resultString = (String) expr.evaluate(pageSource, XPathConstants.STRING);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return resultString;
    }

    private static DocumentBuilder getDocumentBuilder() {
        DocumentBuilder newDocumentBuilder = null;
        try {
            newDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return newDocumentBuilder;
    }
}






