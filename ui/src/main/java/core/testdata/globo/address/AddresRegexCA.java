package core.testdata.globo.address;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import static core.utils.ConfigVariables.CA;

@Component
@Profile(CA)
public class AddresRegexCA extends AbstractAddressRegex {
    private final String POST_CODE_REGEX_CA = "([A-Z]{1}[0-9]{1}[A-Z]{1} [0-9]{1}[A-Z]{1}[0-9]{1})";

    @Override
    public String getPostCodeRegex() {
        return POST_CODE_REGEX_CA;
    }
}
