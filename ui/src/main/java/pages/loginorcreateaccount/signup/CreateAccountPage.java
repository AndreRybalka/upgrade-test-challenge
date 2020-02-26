package pages.loginorcreateaccount.signup;

import core.testdata.models.preparedData.User;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static core.contsants.Constants.Attributes.ARIA_DISABLED;
import static core.utils.ConfigVariables.*;
import static java.lang.String.valueOf;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
@Profile({CA})
public class CreateAccountPage extends AbstractCreateAccountPage {

    @Override
    public void createAccount(User user) {
        getMail().type(user.getEmailAddress());
        getPassword().type(user.getPassword());
        getTermsOfUseCheckbox().click();
        getSubmitPersonalInfoButton().click();
    }
}
