package basetest;

import core.CustomContextInitializerCIS;
import core.TestListenerCIS;
import core.WebDriverFactory;
import core.base.controls.BaseWebElement;
import core.testdata.globo.AbstractTestData;
import core.testdata.globo.address.AbstractAddressRegex;
import core.testdata.models.preparedData.Category;
import core.testdata.models.preparedData.Subcategory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Listeners;
import pages.*;
import pages.cart.CartPage;
import pages.common.commonblocks.RewardsBlock;
import pages.loginorcreateaccount.LogInPage;
import pages.loginorcreateaccount.MyAccountPage;
import pages.PersonalInformationPage;
import pages.loginorcreateaccount.signup.AbstractCreateAccountPage;
import pages.payment.*;
import pages.pdp.PDPPage;
import pages.pdp.ProductsListPage;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static core.SLF4JLogger.info;
import static core.SLF4JLogger.stepInfo;
import static core.WebDriverFactory.getDriver;
import static core.testdata.globo.AbstractTestData.writeOrderNumber;
import static core.utils.ConfigVariables.PropertyKey.SMALL_TIMEOUT;
import static core.utils.Directories.getEnvironmentPropertiesPath;
import static core.utils.PropertyUtils.getProperty;
import static java.util.Objects.requireNonNull;

@Listeners({TestListenerCIS.class})
@ContextConfiguration(locations = {"classpath:applicationConfig.xml"}, initializers = CustomContextInitializerCIS.class)
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class})
public abstract class AbstractBaseTest extends AbstractTestNGSpringContextTests {

    private final static int EXPLICIT_TIMEOUT = Integer.valueOf(requireNonNull(
            getProperty(getEnvironmentPropertiesPath(), SMALL_TIMEOUT.getValue())));

    @Autowired
    @Lazy
    protected FunnelPage funnelPage;

    @Autowired
    @Lazy
    protected PersonalInformationPage personalInformationPage;

    @Autowired
    @Lazy
    protected OfferPage offerPage;

    @Autowired
    protected AbstractAddressRegex addressRegex;

    @Autowired
    protected AbstractTestData testData;

    @Autowired
    @Lazy
    protected LogInPage logInPage;

    @Autowired
    @Lazy
    protected PayPalPage payPalPage;

    @Autowired
    @Lazy
    protected HomePage homePage;

    @Autowired
    @Lazy
    protected PDPPage pdpPage;

    @Autowired
    @Lazy
    protected CartPage cartPage;

    @Autowired
    @Lazy
    protected WishListPage wishListPage;

    @Autowired
    @Lazy
    protected OrderConfirmationPage orderConfirmationPage;

    @Autowired
    @Lazy
    protected PaymentMethodPage paymentMethodPage;

    @Autowired
    @Lazy
    protected SignInAndShippingPage signInAndShippingPage;

    @Autowired
    @Lazy
    protected ProductsListPage productsListPage;

    @Autowired
    @Lazy
    protected AListPage aListPage;

    @Autowired
    @Lazy
    protected InteracPage interacPage;

    @Autowired
    @Lazy
    protected StoreLocatorPage storeLocatorPage;

    @Autowired
    @Lazy
    protected MyAccountPage myAccountPage;

    @Autowired
    @Lazy
    protected AbstractCreateAccountPage createAccountPage;

    @Autowired
    @Lazy
    protected CheckoutPage checkoutPage;

    @Autowired
    @Lazy
    protected TrackOrderPage trackOrderPage;


    @Autowired
    @Lazy
    protected SocialLogInPage socialLogInPage;

    @Autowired
    @Lazy
    protected RewardsBlock rewardsBlock;

    @Autowired
    @Lazy
    protected RewardsLoginPage rewardsLoginPage;

    protected void openPage(final String url) {
        WebDriverFactory.openPage(url);
        info("Page was successfully opened with url: '" + url + "'");
    }

    protected void openPageToBackTo(final String url) {
        WebDriverFactory.openPageDuringTestExecution(url);
        info("Page was successfully reopened for registered user with url: '" + url + "'");
    }

    protected void saveOrderNumber(String orderNumber) {
        info("Current order is : " + orderNumber);
        writeOrderNumber(orderNumber);
    }

    protected void selectProduct(Category mainCategory, Subcategory subCategory) {
        stepInfo("Open existing subcategory from the list");
        homePage.getCategoriesBlock().openCategoryAndSubcategory(mainCategory, subCategory);

        stepInfo("Go to product details page");
        productsListPage.openRandomPDPPageByLink();

        stepInfo("Add to bag product with size");
        pdpPage.selectColor().selectSize().addToCartWithErrorHandleByColourWithSize();

        pdpPage.getLoadingIndicator().waitForElementNotPresent();
    }

    /**
     * Wait for global JS variable 'document' will have complete readyState
     */
    protected void waitForDocumentReadyState() {
        Wait<JavascriptExecutor> wait = new FluentWait<>((JavascriptExecutor) getDriver())
                .withTimeout(EXPLICIT_TIMEOUT, TimeUnit.MILLISECONDS).ignoring(WebDriverException.class)
                .pollingEvery(100, TimeUnit.MILLISECONDS);
        try {
            wait.until(javascriptExecutor -> (requireNonNull(javascriptExecutor).executeScript("return document.readyState;"))
                    .equals("complete"));
        } catch (TimeoutException e) {
            throw new TimeoutException("Document state is "
                    + ((JavascriptExecutor) getDriver()).executeScript("return document.readyState;")
                    + ". Original message is: " + e.getMessage());
        }
    }

    protected void executeCommand(String command) {
        ((JavascriptExecutor) getDriver()).executeScript(command);
    }

    protected void executeCoupleOfCommands(List<String> commands){
        commands.forEach(this::executeCommand);
    }

    protected double roundDoubleValue(double value){
        return Math.round(value*100)/100.0d;
    }

    protected String concatenateElementsByDelimiter(List<BaseWebElement> elements, String delimiter) {
        elements.get(0).waitElementBeNotStale();
        List <String> currentValues = elements.stream()
                .filter(element -> !element.waitForElementPresent().getTrimText().isEmpty())
                .map(BaseWebElement::getTrimText).collect(Collectors.toList());

        return String.join(delimiter,currentValues);
    }

    protected void waitForUrlToBeChanged(final String url) {
        Wait<String> wait = new FluentWait<String>(url).pollingEvery(500, TimeUnit.MILLISECONDS)
                .withTimeout(EXPLICIT_TIMEOUT, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
                .withMessage("Timed out after " + (EXPLICIT_TIMEOUT / 1000) + " seconds waiting for '" + url
                        + "' to be changed");
        wait.until((str) -> ExpectedConditions.not(ExpectedConditions.urlContains(url)));
    }


}
