package core;

import com.google.common.base.Strings;
import core.utils.Directories;
import org.testng.*;
import org.testng.annotations.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static core.SLF4JLogger.*;
import static core.SLF4JLogger.getFileByteArray;
import static core.SLF4JLogger.setCurrentStep;
import static core.constants.Constants.SHORT_DELAY;
import static core.utils.Utils.sleep;
import static java.lang.System.getProperty;
import static java.lang.System.lineSeparator;
import static java.nio.file.Files.exists;
import static java.nio.file.Files.write;
import static java.nio.file.StandardCopyOption.COPY_ATTRIBUTES;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.util.Arrays.stream;

public class TestListener extends TestListenerAdapter {

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

    @Override
    public synchronized void onTestStart(ITestResult result) {
        setConfigurationFailed(false);
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
            getFileByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void onTestFailure(ITestResult iTestResult) {
        if (!isConfigurationFailed()) {
            sleep(SHORT_DELAY);
            stepFailed(iTestResult);
            setCurrentStep("");
            getFileByteArray();
        }
    }

    @Override
    public synchronized void onTestSuccess(ITestResult iTestResult) {
        stepInfo(getCurrentStep());
        log("TEST PASSED: " + getTestName());
        setCurrentStep("");
        getFileByteArray();
    }

    @Override
    public synchronized void onFinish(ITestContext testContext) {

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
                stream.forEach(TestListener::writeOrderNumberToTxt);
            }
        } catch (IOException ie) {
            ie.getStackTrace();
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
