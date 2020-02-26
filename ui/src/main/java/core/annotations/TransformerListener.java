package core.annotations;

import core.utils.RetryAnalyzer;
import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import static core.utils.ConfigVariables.BannerType.getBannerType;
import static core.utils.ConfigVariables.SystemPropertyKey.BANNER;
import static java.lang.String.format;
import static java.lang.System.getProperty;

public class TransformerListener implements IAnnotationTransformer {

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        for (Annotation methodAnnotation : testMethod.getAnnotations()) {
            if (methodAnnotation instanceof Disable) {
                disable(annotation, testMethod);
            }

            else {
                annotation.setRetryAnalyzer(RetryAnalyzer.class);
            }
        }
    }

    private void disable(ITestAnnotation annotation, Method testMethod) {
        for (String environment : testMethod.getAnnotation(Disable.class).environments()) {
            if (getBannerType(getProperty(BANNER.getValue())).name().equalsIgnoreCase(environment)) {
                System.out.println(format("Test [%s] is disabled (it is not available on [%s] environment)",
                        testMethod.getName(), environment));
                annotation.setEnabled(false);
            }
        }
    }
}