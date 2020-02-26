package core.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import static core.SLF4JLogger.error;
import static core.WebDriverFactory.getDriver;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int retryCount = 0;
    private int maxRetryCount = 1;


    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            error("Retrying test " + result.getName() + " with status "
                    + getResultStatusName(result.getStatus()) + " for the " + (retryCount) + " time(s).");
            ++retryCount;
            return true;
        }
        return false;
    }

    public String getResultStatusName(int status) {
        String resultName = null;
        if(status==1)
            resultName = "SUCCESS";
        if(status==2)
            resultName = "FAILURE";
        getDriver().quit();
        if(status==3)
            resultName = "SKIP";
        getDriver().quit();
        return resultName;
    }
}
