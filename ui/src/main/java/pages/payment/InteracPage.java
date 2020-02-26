
package pages.payment;

import core.base.AbstractBasePage;
import core.base.controls.BaseWebElement;
import core.base.controls.WebInput;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
public class InteracPage extends AbstractBasePage {

    @FindBy(className = "issuerLink")
    private BaseWebElement chooseInstitutionLink;

    @FindBy(id = "IDEBIT_TRACK2")
    private WebInput iDebitTrackInput;

    @FindBy(className = "btn-success")
    private WebInput fundPaymentButton;


    public BaseWebElement getChooseInstitutionLink() {
        return chooseInstitutionLink;
    }

    public WebInput getiDebitTrackInput() {
        return iDebitTrackInput;
    }

    public WebInput getFundPaymentButton() {
        return fundPaymentButton;
    }

    public void payWithInterac(String iDebitTrack) {
        getChooseInstitutionLink().click();
        getiDebitTrackInput().type(iDebitTrack);
        getFundPaymentButton().click();
    }
}
