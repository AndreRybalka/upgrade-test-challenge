package core;

import core.utils.ConfigVariables;
import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Intercepts test methods that have both specified locale and test suite as group
 */
public class GroupInterceptorCIS implements IMethodInterceptor {

    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> methodInstances, ITestContext iTestContext) {

        String localeGroup = System.getProperty(ConfigVariables.SystemPropertyKey.LOCALE.getValue());
        String testSuite = System.getProperty(ConfigVariables.SystemPropertyKey.SUITE.getValue());

        List<IMethodInstance> result = new ArrayList<>();

        for (IMethodInstance instance : methodInstances) {
            List<String> groups = Arrays.asList(instance.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).groups());
            if (groups.contains(localeGroup) && groups.contains(testSuite)) {
                result.add(instance);
            }
        }
        return result;
    }
}
