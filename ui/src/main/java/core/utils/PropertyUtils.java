package core.utils;

import java.io.*;
import java.util.Properties;

import static core.SLF4JLogger.error;

/**
 * Works with property files
 *
 */
public final class PropertyUtils {

	private static Properties globalConfig;

	public static void readProperties(String path) {
		try {
			globalConfig = null;
			if (new File(path).exists() == false) {
				error("Cannot find '" + path + "' file.");
			}
			globalConfig = loadProperties(path);

		} catch (Exception e) {
			error("Error while reading property file '" + path + "': " + e.getMessage());
		}
	}

	/**
	 * Load properties from file
	 *
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static Properties loadProperties(String path) throws Exception {
		Properties result = new Properties();
		result.load(new FileInputStream(path));
		return result;
	}

	/**
	 * @param path
	 *            - to property file
	 * @param key
	 * @return property by specified key
	 */
	public static String getProperty(String path, String key) {
		readProperties(path);
		if (globalConfig.containsKey(key)) {
			return globalConfig.getProperty(key);
		}
		error("There was no property with key: " + key);
		return null;
	}

	/**
	 * Set/update property value in configuration file.
	 *
	 * @param key
	 *            - property key
	 * @param value
	 *            - value that should be set
	 * @param shouldWriteOnDisk
	 *            -
	 *            <li>if <b>true</b>, made changes will be written in Global property file alone and
	 *            not in Project Properties file;
	 *            <li>if <b>false</b> - property file remains unchanged, only loaded Properties
	 *            class instance will be updated.
	 */
	public static void setProperty(String path, String key, String value, boolean shouldWriteOnDisk) {
		readProperties(path);
		globalConfig.setProperty(key, value);

		if (shouldWriteOnDisk) {
			if (new File(path).exists() == false) {
				error("Cannot find '" + path + "' file.");
			}
			try {
				globalConfig.store(new FileOutputStream(path), "");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
