package core;

import com.google.common.base.Strings;
import core.utils.Directories;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.*;
import java.net.InetAddress;
import java.util.Calendar;

import static core.utils.Utils.timestamp;

public class SLF4JLogger {
    private static final Logger LOGGER = LoggerFactory.getLogger(SLF4JLogger.class);
    private static ThreadLocal<String> currentStep = new ThreadLocal<>();
    private static ThreadLocal<String> testName = new ThreadLocal<>();

    private static boolean isError = false;

    public static void configureSLF4JLogger() {
        BasicConfigurator.configure();
        PropertyConfigurator.configure(Directories.getSLF4jProperties());
    }

    public static String getCurrentStep() {
        return currentStep.get();
    }

    public static void setCurrentStep(String testStep) {
        currentStep.set(testStep);
    }

    public static boolean isError() {
        return isError;
    }

    public static void setError(boolean error) {
        isError = error;
    }

    public static String getTestName() {
        return testName.get();
    }

    public static void setTestName(String className) {
        testName.set(className);
    }

    /**
     * Add step info into log file
     *
     * @param stepName
     */
    public static void stepInfo(String stepName) {
        if (!Strings.isNullOrEmpty(getCurrentStep())) {
            info("STEP PASSED: " + getCurrentStep());
            logReportToFile(getTestName(), getCurrentStep().trim(), 1);
        }
        setCurrentStep(stepName);
    }

    /**
     * Marks step as FAILED in log file, adds warning message
     *
     * @param result
     */
    public static void stepFailed(ITestResult result) {
        String correctedMessage = result.getThrowable().getMessage();
        result.getThrowable().printStackTrace();
        if (correctedMessage == null) {
            correctedMessage = "Message is undefined";
        }
        String stepError = getCurrentStep().trim() + "; failure message: " + correctedMessage;
        info("STEP FAILED: " + stepError + ";");
        logReportToFile(getTestName(), stepError, 0);
    }

    public static void info(String message) {
        LOGGER.info(message);
    }

    public static void log(String message) {
        LOGGER.info(message);
        logReportToFile(getTestName(), message, 2);

    }

    public static void error(String message) {
        LOGGER.error(message);
    }

    public static void debug(String message) {
        LOGGER.debug(message);
    }

    /**
     * Writes the success/failure report to the file
     *
     * @param scriptName
     *            - Name of the test
     * @param stepName
     *            - Step name
     * @param intStatus
     *            - Status (e.g: 0-Failed, 1-Passed, 2-Warning)
     */
    public static void logReportToFile(String scriptName, String stepName, int intStatus) {
        synchronized (testName.get()) {
            String stepStatus;
            switch (intStatus) {
                case 0:
                    stepStatus = "STEP FAILED:";
                    break;
                case 1:
                    stepStatus = "STEP PASSED:";
                    break;
                case 2:
                    stepStatus = "";
                    break;
                default:
                    stepStatus = "Undefined";
            }

            // Appending log to the report
            if (!Strings.isNullOrEmpty(stepStatus)) {
                stepName = "\"" + stepName + "\"";
            }

            try {
                FileInputStream inputStream = new FileInputStream(getPathToReportFile());
                DataInputStream in = new DataInputStream(inputStream);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String FileContent = br.readLine();
                in.close();
                br.close();
                inputStream.close();

                FileWriter fstream = new FileWriter(getPathToReportFile(), true);
                BufferedWriter out = new BufferedWriter(fstream);

                if (FileContent == null) {
                    out.write("");
                    out.write("\t" + "Automation Test Log" + "\t" + "\n");
                    out.write("\n" + "Test Script Name: " + "\t"
                            + scriptName.split("\\.")[scriptName.split("\\.").length - 1] + "\n");
                    InetAddress addr = InetAddress.getLocalHost();
                    String strOSInfo = addr.getHostName() + " " + System.getProperty("os.name") + " "
                            + System.getProperty("os.version");
                    out.write("Environment: " + "\t" + "\"" + strOSInfo + "\"" + "\n");
                    out.write("Start Time: " + "\t" + Calendar.getInstance().getTime() + "\n");
                    out.write("\n");
                }

                out.write(timestamp("MM/dd/yyyy HH:mm:ss") + " " + stepStatus + " " + stepName + "\n");
                out.close();
                fstream.close();
            } catch (Exception e) {
                error("Error in logReportToFile: " + e.getMessage());
            }
        }
    }

    /**
     * Obtaining log file as an array of bytes
     */
    @Attachment(value = "Steps", type = "text/plain")
    public static byte[] getFileByteArray() {
        File file = new File(getPathToReportFile());
        FileInputStream fileInputStream;
        byte[] bfile = new byte[(int) file.length()];
        try {
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bfile);
            fileInputStream.close();
        } catch (IOException e) {
            error("Can not read log file");
        }
        return bfile;
    }

    public static void resetReportFile() {
        File logDir = new File(Directories.getResultsPath());
        File logFile = new File(getPathToReportFile());
        try {
            if (!logDir.exists()) {
                logDir.mkdir();
                logFile.createNewFile();
            } else if (logFile.exists()) {
                logFile.delete();
                logFile.createNewFile();
            } else {
                logFile.createNewFile();
                info("Log file is successfully created");
            }
        } catch (IOException e) {
            error("Log file was NOT created: " + e.getMessage());
        }
    }

    private static String getPathToReportFile() {
        return Directories.getPathToReportFile(getTestName());
    }
}
