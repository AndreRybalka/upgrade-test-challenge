package core;

import core.utils.ConfigVariables.*;
import core.utils.Directories;
import core.utils.PropertyUtils;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static core.SLF4JLogger.*;
import static java.lang.System.lineSeparator;
import static java.nio.file.Files.exists;
import static java.nio.file.Files.write;
import static java.nio.file.StandardCopyOption.COPY_ATTRIBUTES;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.util.Arrays.stream;

/**
 * Manages test behavior
 */
public class TestListenerCIS extends TestListenerAdapter {
    private static int testId;
    private String testName;
    private static ThreadLocal<Boolean> isConfigurationFailed = new ThreadLocal<>();

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public void setConfigurationFailed(boolean isFailed) {
        isConfigurationFailed.set(isFailed);
    }

    public boolean isConfigurationFailed() {
        return isConfigurationFailed.get();
    }

    private boolean hasAfterConfiguration(ITestResult iTestResult) {
        return stream(iTestResult.getTestClass().getRealClass().getMethods())
                .anyMatch(method -> method.getAnnotation(AfterClass.class) != null
                        || method.getAnnotation(AfterGroups.class) != null
                        || method.getAnnotation(AfterSuite.class) != null
                        || method.getAnnotation(AfterMethod.class) != null
                        || method.getAnnotation(AfterTest.class) != null);
    }

    private boolean isBeforeConfiguration(ITestResult iTestResult) {
        ITestNGMethod iTestNGMethod = iTestResult.getMethod();
        return iTestNGMethod.isBeforeClassConfiguration() || iTestNGMethod.isBeforeGroupsConfiguration()
                || iTestNGMethod.isBeforeMethodConfiguration() || iTestNGMethod.isBeforeSuiteConfiguration()
                || iTestNGMethod.isBeforeTestConfiguration();
    }

    @Override
    public synchronized void onTestStart(ITestResult result) {
        setConfigurationFailed(false);
        WebDriverFactory.createWebDriverInstance();
        String testName = result.getMethod().getMethodName() + "_Id_" + (++testId);
        SLF4JLogger.setTestName(testName);
        setTestName(testName);
        resetReportFile();
    }

    @Override
    public synchronized void onConfigurationFailure(ITestResult iTestResult) {
        try {
            setConfigurationFailed(true);
            stepInfo("CONFIGURATION FAILED");
            WebDriverFactory.captureScreenshot(getTestName());
            WebDriverFactory.getScreenshotFile();
            getFileByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            WebDriverFactory.removeWebDriver();
        }
    }

    @Override
    public synchronized void onConfigurationSuccess(ITestResult iTestResult) {
        ITestNGMethod iTestNGMethod = iTestResult.getMethod();
        if (!isBeforeConfiguration(iTestResult) && iTestNGMethod.isAfterTestConfiguration()) {
            WebDriverFactory.removeWebDriver();
        }
    }

    @Override
    public synchronized void onTestFailure(ITestResult iTestResult) {
        if (!isConfigurationFailed()) {
            String screeenshot = WebDriverFactory.captureScreenshot(getTestName());
            WebDriverFactory.getScreenshotFile();
            stepFailed(iTestResult, screeenshot);
            setCurrentStep("");
            getNetworkLogs();
            getFileByteArray();
        }
        if (!hasAfterConfiguration(iTestResult)) {
            WebDriverFactory.removeWebDriver();
        }
    }

    @Override
    public synchronized void onTestSuccess(ITestResult iTestResult) {
        stepInfo(getCurrentStep());
        log("TEST PASSED: " + getTestName());
        setCurrentStep("");
        getFileByteArray();
        if (!hasAfterConfiguration(iTestResult)) {
            WebDriverFactory.removeWebDriver();
        }
    }

    @Override
    public synchronized void onFinish(ITestContext testContext) {
        String pathToFile = Directories.getAllureEnvironmentFilePath();
        PropertyUtils.setProperty(pathToFile, "Banner",
                BannerType.getBannerType(System.getProperty(SystemPropertyKey.BANNER.getValue())).toString(), true);
        PropertyUtils.setProperty(pathToFile, "Locale",
                Country.getCountry(System.getProperty(SystemPropertyKey.LOCALE.getValue())).getValue().toLowerCase(),
                true);
        PropertyUtils.setProperty(pathToFile, "Browser",
                Browser.getBrowser(System.getProperty(SystemPropertyKey.BROWSER.getValue())).toString(), true);
        PropertyUtils.setProperty(pathToFile, "Device",
                DeviceType.getDeviceType(System.getProperty(SystemPropertyKey.DEVICE.getValue())).toString(), true);
        try {
            if (exists(Paths.get(Directories.getAllureResultPropertiesPath()))) {
                Files.copy(Paths.get(Directories.getAllureEnvironmentFilePath()),
                        Paths.get(Directories.getAllureResultPropertiesPath()), REPLACE_EXISTING);
            } else {
                Files.copy(Paths.get(Directories.getAllureEnvironmentFilePath()),
                        Paths.get(Directories.getAllureResultPropertiesPath()), COPY_ATTRIBUTES);
            }
            if (exists(Paths.get(Directories.getOrdersFilePath()))) {
                Stream<String> stream = Files.lines(Paths.get(Directories.getOrdersFilePath()));
                stream.forEach(TestListenerCIS::writeOrderNumberToTxt);
            }
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

    private static void writeOrderNumberToTxt(String orderDetails) {
        Path path = Paths.get(Directories.getAllureResultPropertiesPath());
        try {
            write(path, (lineSeparator() + orderDetails + lineSeparator()).getBytes(), APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
