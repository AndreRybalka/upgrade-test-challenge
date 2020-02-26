package core.annotations;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;


import static core.SLF4JLogger.info;
import static core.SLF4JLogger.log;
import static core.SLF4JLogger.stepInfo;
import static core.testdata.providers.TestDataProviderAPI.getRegion;
import static java.lang.String.format;

public class TransformerListener implements IAnnotationTransformer {

    private void disable(ITestAnnotation annotation, Method testMethod) {
        for (String environment : testMethod.getAnnotation(Disable.class).environments()) {
            if (getRegion().getLangValue().getValue().equalsIgnoreCase(environment)) {
                info(format("Test [%s] is disabled (it is not available on [%s] environment)",
                        testMethod.getName(), environment));
                annotation.setEnabled(false);
            }
        }
    }

    @Override
    public void transform (final ITestAnnotation annotation, final Class testClass, final Constructor testConstructor,
                           final Method testMethod) {

        String [] groups = annotation.getGroups ();
        if (!Arrays.asList(groups).contains(getRegion().getLangValue().getValue())){
            info(format("Test [%s] isn`t contains [%s] locale",
                    testMethod.getName(), getRegion().getLangValue().getValue()));
            annotation.setEnabled (false);
        }
        for (Annotation methodAnnotation : testMethod.getAnnotations()) {
            if (methodAnnotation instanceof Disable) {
                disable(annotation, testMethod);
            }
        }



    }
}
