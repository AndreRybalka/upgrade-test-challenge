package core.utils;

import core.utils.ConfigVariables.BannerType;

import java.io.File;
import java.io.IOException;

import static core.SLF4JLogger.error;

/**
 * Class dedicated for obtaining paths to files
 */
public class Directories {

	/**
	 * @return path to the root directory
	 */
	public static String getRootDirectoryPath() {
		try {
			return new File(".").getCanonicalPath();
		} catch (IOException e) {
			error("Could not get common folder path. Original exception: " + e.getMessage());
			return ".." + File.separator + "common";
		}
	}

	public static String getEnvironmentPropertiesPath() {
		return getRootDirectoryPath() + File.separator + "env.properties";
	}

	public static String getResourcesPath() {
		return getRootDirectoryPath() + File.separator + "src" + File.separator + "main" + File.separator + "resources";
	}

	public static String getResultsPath() {
		return getRootDirectoryPath() + File.separator + "results";
	}

	public static String getPathToReportFile(String fileName) {
		return getResultsPath() + File.separator + fileName + ".txt";
	}

	public static String getPathToOrdersFile() {
		return getResultsPath() + File.separator + "orderNumbers" + ".txt";
	}

	public static String getSLF4jProperties() {
		return getRootDirectoryPath() + File.separator + "log4j.properties";
	}

	/**
	 * Returns path to environment file for allure
	 */
	public static String getAllureEnvironmentFilePath() {
		return getResourcesPath() + File.separator + "allureProperties" + File.separator + "environment.properties";
	}

	/**
	 * Returns path to orders file for allure
	 */
	public static String getOrdersFilePath() {
		return getResultsPath() + File.separator + "orderNumbers.txt";
	}

	/**
	 * Returns path to folder with allure results
	 */
	public static String getAllureResultsPath() {
		return getRootDirectoryPath() + File.separator + "target" + File.separator + "allure-results";
	}

	/**
	 * Returns path to folder with allure results
	 */
	public static String getAllureResultsOrdersPath() {
		return getRootDirectoryPath() + File.separator + "target" + File.separator + "allure-results" + File.separator
				+ "orderNumbers.txt";
	}

	/**
	 * Returns path to folder with allure results
	 */
	public static String getAllureResultPropertiesPath() {
		return getRootDirectoryPath() + File.separator + "target" + File.separator + "allure-results" + File.separator
				+ "environment.properties";
	}

	public static String getTestDataSetPath(BannerType bannerType) {
		return getResourcesPath() + File.separator + bannerType.getTestDataSet();
	}
}
