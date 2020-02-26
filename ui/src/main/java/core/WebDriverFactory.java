package core;

import core.utils.ConfigVariables.PropertyKey;
import core.utils.Directories;
import core.utils.PropertyUtils;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import static core.SLF4JLogger.error;
import static core.SLF4JLogger.info;
import static java.util.Collections.singletonList;

/**
 * Global point of access to WerbDriver object
 *
 */
public class WebDriverFactory {

	private static ThreadLocal<WebDriver> webdriver = new ThreadLocal<>();
	private static ThreadLocal<byte[]> screenshotFile = new ThreadLocal<>();
	private static ThreadLocal<EnvironmentConfiguration> environmentConfiguration = new ThreadLocal<>();
	private final static int IMPLICIT_TIMEOUT = Integer.valueOf(PropertyUtils
			.getProperty(Directories.getEnvironmentPropertiesPath(), PropertyKey.TIMEOUT_IN_SECONDS.getValue()));

	public static WebDriver getDriver() {
		return webdriver.get();
	}

	public static void setWebDriver(WebDriver driver) {
		webdriver.set(driver);
	}

	public static EnvironmentConfiguration getEnvironmentConfiguration() {
		return environmentConfiguration.get();
	}

	public static void setEnvironmentConfiguration(EnvironmentConfiguration envConfig) {
		environmentConfiguration.set(envConfig);
	}

	public static byte[] getScreenshotFileBytes() {
		return screenshotFile.get();
	}

	public static void setScreenshotFileBytes(byte[] screenshotFileBytes) {
		screenshotFile.set(screenshotFileBytes);
	}

	public static ChromeOptions disableBar(){
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--enable-automation");
		return chromeOptions;
	}
	private static DesiredCapabilities setCapabilitiesChrome(){
		LoggingPreferences logPrefs = new LoggingPreferences();
		logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
		logPrefs.enable(LogType.BROWSER, Level.ALL);
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
		capabilities.setCapability("chrome.switches", singletonList("--incognito"));
		capabilities.setCapability(ChromeOptions.CAPABILITY, disableBar());
		capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
		return capabilities;
	}


	public static void createWebDriverInstance() {
		setEnvironmentConfiguration(new EnvironmentConfiguration());
		switch (getEnvironmentConfiguration().getDevice()) {
            case DESKTOP:
                switch (getEnvironmentConfiguration().getBrowser()) {
                    case FIREFOX:
                        FirefoxDriverManager.getInstance().setup();
                        setWebDriver(new FirefoxDriver());
                        info("Firefox webdriver created");
                        break;
                    case CHROME:
						ChromeDriverManager.getInstance().version("80.0.3987.106").setup();
                        setWebDriver(new ChromeDriver(setCapabilitiesChrome()));
                        info("Chrome webdriver created");
                        break;
                    case IE:
                        InternetExplorerDriverManager.getInstance().setup();
                        DesiredCapabilities capabilities = getIECapabilities();
                        setWebDriver(new InternetExplorerDriver(capabilities));
                        info("IE webdriver created");
                        break;
                }
                maximizeWindow();
                break;
            case MOBILE:
            case TABLET:
        }
		getDriver().manage().timeouts().implicitlyWait(IMPLICIT_TIMEOUT, TimeUnit.SECONDS);
		getDriver().manage().timeouts().setScriptTimeout(IMPLICIT_TIMEOUT, TimeUnit.SECONDS);
	}

	public static void removeWebDriver() {
		if (!getDriver().getWindowHandles().isEmpty()) {
			getDriver().quit();
			webdriver.remove();
			info("Web Driver was successfully removed");
		}
	}

	public static void maximizeWindow() {
		getDriver().manage().window().maximize();
		info("Window was successfully maximized to size: '" + getDriver().manage().window().getSize() + "'!!!!");
	}

	public static void openPage(final String url) {
		getDriver().manage().deleteAllCookies();
		getDriver().get(url);
	}

	public static void openPageDuringTestExecution(final String url) {
		getDriver().get(url);
	}

	private static DesiredCapabilities getIECapabilities() {
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
		capabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "");
		return capabilities;
	}

	/**
	 * Return paths to browser and desktop screenshots
	 *
	 * @return
	 */
	public static String captureScreenshot(String testName) {
		String browserScreenshotURL;
		String screenshot = null;
		try {
			File currentDir = new File(Directories.getResultsPath() + File.separator + "screenshots");
			currentDir.mkdirs();

			String screenshotsFolder = currentDir.getCanonicalPath() + File.separator;
			String filename = System.currentTimeMillis() + "_" + testName + "_Browser.png";

			File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
			setScreenshotFileBytes(((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES));
			FileUtils.copyFile(scrFile, new File(screenshotsFolder + filename));
			browserScreenshotURL = screenshotsFolder + filename;

			System.err.println(String.format("Browser screenshot: '%1$s'", browserScreenshotURL));

			screenshot = String.format(" Browser screenshot: '%1$s'", browserScreenshotURL);

		} catch (Exception e) {
			if (e.getMessage() != null) {
				error("Error in screenshots ocurred: " + e.getMessage());
			} else {
				error("Error in screenshots ocurred: Message is undefined");
			}
		}
		return screenshot;
	}

	@Attachment(value = "Screenshot of failed test", type = "image/png")
	public static byte[] getScreenshotFile() {
		return getScreenshotFileBytes();
	}
}
