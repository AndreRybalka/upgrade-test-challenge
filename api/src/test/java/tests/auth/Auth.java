package tests.auth;

import basetest.AbstractBaseTest;
import com.jayway.restassured.response.Cookies;
import com.jayway.restassured.response.Response;
import org.testng.annotations.Test;

import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static core.constants.Constants.*;
import static core.testdata.providers.TestDataProviderAPI.getRegion;
import static core.utils.ConfigVariables.*;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Auth extends AbstractBaseTest {

    Cookies userLoginCookies;

    @Test
    public void upgradeUserLogin() {

        Response response = extractResponseAndPrintLog(spec.
                body("{ \"username\": \"" + testData.getUpgradeUserName() + "\", \"password\": \"" + testData.getUpgradeUserPassword() + "\"}").
                when().
                post(testData.getHostName() + "login").
                then().
                body(matchesJsonSchemaInClasspath("schemas/upgrade-user-schema.json")).
                statusCode(200));

        assertEquals(response.path("loansInReview.productType").toString().replace("[", "").replace("]",""), PRODUCT_TYPE);
    }

    @Test
    public void upgradeUserLoginUnAuthorized() {

        Response response = extractResponseAndPrintLog(spec.
                body("{ \"username\": \"" + testData.getUpgradeUserName() + "\", \"password\": \"" + testData.getUpgradeWrongUserPassword() + "\"}").
                when().
                post(testData.getHostName() + "login").
                then().
                statusCode(401));
    }
}
