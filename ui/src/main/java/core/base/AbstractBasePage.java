package core.base;

import core.base.controls.BaseWebElement;
import core.testdata.globo.AbstractTestData;
import core.utils.ConfigVariables;
import core.utils.Directories;
import core.utils.PropertyUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import pages.common.commonblocks.*;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static core.WebDriverFactory.getDriver;
import static java.util.Objects.requireNonNull;
import static org.openqa.selenium.support.PageFactory.initElements;


public abstract class AbstractBasePage {

    private final static int EXPLICIT_TIMEOUT = Integer.valueOf(Objects.requireNonNull(PropertyUtils
            .getProperty(Directories.getEnvironmentPropertiesPath(), ConfigVariables.PropertyKey.SMALL_TIMEOUT.getValue())));

    @FindBy(className = "c-loading")
    private BaseWebElement loadingIcon;

    public BaseWebElement getLoadingIcon() {
        return loadingIcon;
    }

    @Autowired
    @Lazy
    private LanguageCheckBlock languageCheckBlock;

    @Autowired
    @Lazy
    private InternationalBlock internationalBlock;

    @Autowired
    @Lazy
    private CategoriesBlock categoriesBlock;

    @Autowired
    @Lazy
    private HeaderBlock headerBlock;

    @Autowired
    @Lazy
    private FooterBlock footerBlock;

    @Autowired
    protected AbstractTestData testData;

    public FooterBlock getFooterBlock() {
        return footerBlock;
    }

    public LanguageCheckBlock getLanguageCheckBlock() {
        return languageCheckBlock;
    }

    public InternationalBlock getInternationalBlock() {
        return internationalBlock;
    }

    public CategoriesBlock getCategoriesBlock() {
        return categoriesBlock;
    }

    public HeaderBlock getHeaderBlock() {
        return headerBlock;
    }

    protected AbstractBasePage() {
        initElements(new HtmlElementDecorator(getDriver()), this);
    }

    public void getANewWindow(String parentWindow) {
        Set<String> handles = getDriver().getWindowHandles();
        for (String windowHandle : handles) {
            if (!windowHandle.equals(parentWindow)) {
                getDriver().switchTo().window(windowHandle);
            }
        }
    }

    public void closeChildWindow(String parentWindow) {
        getDriver().close();
        getDriver().switchTo().window(parentWindow);
    }

    public String getPageUrl(){
        return getDriver().getCurrentUrl();
    }

    public void waitForDocumentReadyState() {
        Wait<JavascriptExecutor> wait = new FluentWait<>((JavascriptExecutor) getDriver())
                .withTimeout(EXPLICIT_TIMEOUT, TimeUnit.MILLISECONDS).ignoring(WebDriverException.class)
                .pollingEvery(100, TimeUnit.MILLISECONDS);
        try {
            wait.until(javascriptExecutor -> ((String) requireNonNull(javascriptExecutor).executeScript("return document.readyState;"))
                    .equals("complete"));
        } catch (TimeoutException e) {
            throw new TimeoutException("Document state is "
                    + ((JavascriptExecutor) getDriver()).executeScript("return document.readyState;")
                    + ". Original message is: " + e.getMessage());
        }
    }

    public String getWindowHandle() {
        String currentWindow = getDriver().getWindowHandle();
        for (String windowHandle : getDriver().getWindowHandles()) {
            if (!windowHandle.equals(currentWindow)) {
                return windowHandle;
            }
        }
        return currentWindow;
    }


    public void switchToWindow() {
        getDriver().switchTo().window(getWindowHandle());
        getDriver().manage().window().maximize();
    }

    public void switchToMainWindowWithoutClose(){
        Set<String> windowHandles = getDriver().getWindowHandles();
        String mainWindowHandle = ((String) windowHandles.toArray()[0]);
        getDriver().switchTo().window(mainWindowHandle);
    }

    public BaseWebElement getElementWhichShouldBeReinitialize(int index, List<BaseWebElement> listOfTheElements) {
        return listOfTheElements.get(index);
    }


}
