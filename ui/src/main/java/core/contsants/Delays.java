package core.contsants;

import core.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Delays {

    static WebDriver driver = WebDriverFactory.getDriver();

    public static void waitUntilVisibility(WebElement element)
    {
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static final int INSTANT_DELAY = 500;
    public static final int SHORT_DELAY = 1000;
    public static final int MEDIUM_DELAY = 3000;
    public static final int LONG_DELAY = 10000;
    public static final int MAX_WAITING_TILL_ORDER_TRACKING_BE_VISIABLE = 20;
}
