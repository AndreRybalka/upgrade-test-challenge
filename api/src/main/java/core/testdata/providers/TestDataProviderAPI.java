package core.testdata.providers;


import core.utils.ConfigVariables;

import java.util.List;


import static core.utils.ConfigVariables.SystemPropertyKey.*;
import static java.lang.System.getProperty;
import static java.util.Arrays.asList;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class TestDataProviderAPI {

    private static ConfigVariables.Lang getLang() {
        return ConfigVariables.Lang.getLang(System.getProperty(ConfigVariables.SystemPropertyKey.LANG.getValue()));
    }

    public static ConfigVariables.Region getRegion() {
        return ConfigVariables.Region.getRegion(getLang().getValue());
    }

}
