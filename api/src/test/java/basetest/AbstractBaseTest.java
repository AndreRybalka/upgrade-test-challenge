package basetest;

import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ValidatableResponse;
import com.jayway.restassured.specification.RequestSpecification;
import core.CustomContextInitializerAPI;
import core.TestListener;
import core.testdata.data.AbstractTestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import static com.jayway.restassured.RestAssured.given;
import static core.SLF4JLogger.info;
import static core.testdata.providers.TestDataProviderAPI.getRegion;



@Listeners({TestListener.class})
@ContextConfiguration(locations = {"classpath:applicationConfig.xml"}, initializers = CustomContextInitializerAPI.class)
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class})
public class AbstractBaseTest extends AbstractTestNGSpringContextTests {



    @Autowired
    protected AbstractTestData testData;
    protected RequestSpecification spec;
    protected RequestSpecification specMail;



    @BeforeMethod
    protected RequestSpecification createRequestSpec()  {

        info("Current locale is: " + getRegion().getLangValue().getValue());
        info("________________________");
        return spec = given().
                header("x-cf-source-id", "coding-challenge").
                header("x-cf-corr-id", "230ea84a-7199-41c9-bf38-fff27e35970d").
                //header("x-aldo-api-version", testData.getAldoApiVersion()).
                //header("user-agent", "soasta").
                //header("x-aldo-brand", testData.getAldoBrand()).
                contentType("application/json").
                given().
                log().all();
    }

    protected RequestSpecification createRequestSpecMail()  {
        return  given().
                header("Authorization", "Basic YXVUR3dKWEJ2S0pMaTJl").
                contentType("application/json").
                given().
                log().all();
    }

    protected RequestSpecification createRequestSpecCIS_CA()  {

        return  given().
                header("x-aldo-lang", "en").
                header("x-aldo-region", "ca").
                //header("x-aldo-api-version", testData.getAldoApiVersion()).
               /// header("x-aldo-brand", "callitspring").
                contentType("application/json").
                given().
                log().all();
    }

    protected RequestSpecification createRequestSpecCIS_US()  {

        return given().
                header("x-aldo-lang", "en_US").
                header("x-aldo-region", "us").
                //header("x-aldo-api-version", testData.getAldoApiVersion()).
                //header("x-aldo-brand", "callitspring").
                contentType("application/json").
                given().
                log().all();
    }

    protected RequestSpecification createRequestSpecGLOBO()  {

        return given().
                header("x-aldo-lang", "en").
                header("x-aldo-region", "ca").
                //header("x-aldo-api-version", testData.getAldoApiVersion()).
                //header("x-aldo-brand", "globoshoes").
                contentType("application/json").
                given().
                log().all();
    }

    protected Response extractResponseAndPrintLog(ValidatableResponse response) {
        return response.log().all().extract().response();

    }

    protected String generateNewEmailAddress(){
        return (int)(Math.random() * 100000) + ".1koyiaez@mailosaur.io";
    }

}
