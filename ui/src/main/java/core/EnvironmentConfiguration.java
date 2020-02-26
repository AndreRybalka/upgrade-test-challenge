package core;

import core.utils.ConfigVariables.*;

/**
 * Persists all global variables
 *
 *
 */
public class EnvironmentConfiguration {

	private EnvironmentType env;
	private BannerType banner;
	private DeviceType device;
	private Browser browser;
	private Country locale;

	public EnvironmentConfiguration() {
		this.env = obtainEnvironmentType();
		this.banner = obtainBannerType();
		this.device = obtainDeviceType();
		this.browser = obtainBrowserName();
		this.locale = obtainLocaleName();
	}

	public EnvironmentType getEnv() {
		return env;
	}

	public void setEnv(EnvironmentType env) {
		this.env = env;
	}

	public BannerType getBanner() {
		return banner;
	}

	public void setBanner(BannerType banner) {
		this.banner = banner;
	}

	public DeviceType getDevice() {
		return device;
	}

	public void setDevice(DeviceType device) {
		this.device = device;
	}

	public Browser getBrowser() {
		return browser;
	}

	public void setBrowser(Browser browser) {
		this.browser = browser;
	}

	public Country getLocale() {
		return locale;
	}

	public void setLocale(Country locale) {
		this.locale = locale;
	}

	public EnvironmentType obtainEnvironmentType() {
		final String environmentType = System.getProperty(SystemPropertyKey.ENVIRONMENT.getValue());
		final EnvironmentType environment = EnvironmentType.getEnvironmentType(environmentType);
		return environment;
	}

	public BannerType obtainBannerType() {
		final String bannerType = System.getProperty(SystemPropertyKey.BANNER.getValue());
		final BannerType banner = BannerType.getBannerType(bannerType);
		return banner;
	}

	public Browser obtainBrowserName() {
		final String browserName = System.getProperty(SystemPropertyKey.BROWSER.getValue());
		final Browser browser = Browser.getBrowser(browserName);
		return browser;
	}

	public DeviceType obtainDeviceType() {
		final String deviceType = System.getProperty(SystemPropertyKey.DEVICE.getValue());
		final DeviceType device = DeviceType.getDeviceType(deviceType);
		return device;
	}

	public Country obtainLocaleName() {
		final String locale = System.getProperty(SystemPropertyKey.LOCALE.getValue());
		final Country country = Country.getCountry(locale);
		return country;
	}
}
