package core;

import core.utils.ConfigVariables.Country;
import core.utils.ConfigVariables.SystemPropertyKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * Describes custom context initialization
 *
 *
 */
public class CustomContextInitializerCIS implements ApplicationContextInitializer<ConfigurableApplicationContext> {

	/**
	 * Sets locale value as active profile
	 */
	@Override
	@Autowired
	public void initialize(final ConfigurableApplicationContext ctx) {
		final ConfigurableEnvironment environment = ctx.getEnvironment();
		String locales = Country.getCountry(System.getProperty(SystemPropertyKey.LOCALE.getValue())).getValue().toLowerCase();
		environment.setActiveProfiles(locales);
	}
}
