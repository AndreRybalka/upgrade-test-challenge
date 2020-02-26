package core;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import static core.testdata.providers.TestDataProviderAPI.getRegion;

public class CustomContextInitializerAPI implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    @Autowired
    public void initialize(final ConfigurableApplicationContext ctx) {
        final ConfigurableEnvironment environment = ctx.getEnvironment();
        String locales = getRegion().getLangValue().getValue();
        environment.setActiveProfiles(locales);
    }
}
