package core.base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;

import java.util.concurrent.TimeUnit;

import static core.WebDriverFactory.getDriver;
import static java.util.Objects.requireNonNull;
import static org.openqa.selenium.support.PageFactory.initElements;

public abstract class AbstractBaseComponent {

    protected AbstractBaseComponent() {
        initElements(new HtmlElementDecorator(getDriver()), this);
    }
}
