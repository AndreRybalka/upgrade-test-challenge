package smoke.loan;


import basetest.AbstractBaseTest;
import core.base.AbstractBasePage;
import core.testdata.models.preparedData.User;
import core.testdata.provider.TestDataProvider;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.model.SeverityLevel;

import static constants.Constants.*;
import static core.SLF4JLogger.log;
import static core.SLF4JLogger.stepInfo;
import static core.helpers.ComparisonHelper.verifyStringContains;
import static core.helpers.ComparisonHelper.verifyStringsEqual;
import static core.helpers.WebElementsActionsHelper.clickOnTheBaseWebElement;
import static core.helpers.WebElementsVerificationsHelper.verifyConditionIsTrue;
import static core.helpers.WebElementsVerificationsHelper.verifyElementPresent;
import static core.testdata.provider.TestDataProviderHelper.getSiteUrl;
import static core.utils.ConfigVariables.CA;
import static core.utils.RegExrMatchSubstringUtil.matchString;
import static core.utils.Utils.generateMailAddress;
import static java.lang.String.format;
import static org.junit.Assert.assertTrue;

/**
 * Created by Andrii Rybalka on 25/2/2020.
 */

public class NewUserLoanOfferTests extends AbstractBaseTest {

    @Test(groups = {CA} , dataProvider = "userDataProvider", dataProviderClass = TestDataProvider.class)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Description: log in/log out test.")
    public void verifyNewUserLoanOffer(User user) {

        stepInfo("Open page with url: " + NONDMFFUNNEL_PAGE);
        openPage(NONDMFFUNNEL_PAGE);

        stepInfo("Fill and submit -Check Your Rate- form");
        funnelPage.inputDesiredAmountFieldValue();
        funnelPage.selectPurpose();
        clickOnTheBaseWebElement(funnelPage.getCheckYourRateButton());
        verifyStringContains(funnelPage.getPageUrl(), PERSONAL_INFORMATION, "Current URL contains necessary parameter");

        stepInfo("Create user");
        String urlToLeave = funnelPage.getPageUrl();
        user.setEmailAddress(generateMailAddress());
        personalInformationPage.createUserStepContact(user);
        waitForUrlToBeChanged(urlToLeave);

        stepInfo("Enter income");
        urlToLeave = personalInformationPage.getPageUrl();
        personalInformationPage.inputIncomeValues();
        waitForUrlToBeChanged(urlToLeave);

        stepInfo("Create account");
        urlToLeave = personalInformationPage.getPageUrl();
        user.setEmailAddress(generateMailAddress());
        createAccountPage.createAccount(user);
        waitForUrlToBeChanged(urlToLeave);

        stepInfo("Get user offer values");
        String loanAmount = offerPage.getUserLoanAmountValue();
        String monthlyPayment = offerPage.getMonthlyPaymentValue();
        String defaultLoanTerm = offerPage.getDefaultLoanTermValue();
        String defaultLoanInterestRate = offerPage.getDefaultLoanInterestRateValue();
        String defaultMoreInfoAPR = offerPage.getDefaultMoreInfoAPRValue();

        stepInfo("Logout");
        urlToLeave = offerPage.getPageUrl();
        offerPage.signOut();
        waitForUrlToBeChanged(urlToLeave);

        stepInfo("Log in");
        openPage(LOGIN_PAGE);
        logInPage.logIn(user);

        stepInfo("Get user offer and compare it with previously saved values");
        verifyStringsEqual(loanAmount, offerPage.getUserLoanAmountValue(), "User loan amount value is not changed");
        verifyStringsEqual(monthlyPayment, offerPage.getMonthlyPaymentValue(), "Monthly payment value is not changed");
        verifyStringsEqual(defaultLoanTerm, offerPage.getDefaultLoanTermValue(), "Default loan term value is not changed");
        verifyStringsEqual(defaultLoanInterestRate, offerPage.getDefaultLoanInterestRateValue(),
                "Default loan interest value is not changed");
        verifyStringsEqual(defaultMoreInfoAPR, offerPage.getDefaultMoreInfoAPRValue(), "Default ARP value is not changed");
    }
}
