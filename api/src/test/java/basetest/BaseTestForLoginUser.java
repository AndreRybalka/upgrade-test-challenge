package basetest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.response.Cookies;
import com.jayway.restassured.response.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static core.SLF4JLogger.info;
import static core.constants.Constants.USER_FIRST_NAME;
import static core.constants.Constants.USER_LAST_NAME;
import static core.testdata.providers.TestDataProviderAPI.getRegion;

public class BaseTestForLoginUser extends AbstractBaseTest{

    private Cookies userLoginCookies;


    protected Cookies  userLoginPreConditions(String[] functionalParameters, String userMail) {

        for (String param: functionalParameters){

        Response response = extractResponseAndPrintLog(spec.
                body("{ \"username\": \"" + userMail + "\", \"password\": \"" + testData.getUpgradeUserPassword() + "\"}").
                when().
                post(testData.getHostName() + "/auth/login").
                then().
                body(matchesJsonSchemaInClasspath("schemas/user-schema.json")).
                statusCode(200));

        userLoginCookies = response.getDetailedCookies();

            switch (param)
            {
                case "address":
                    Response getAddressesResponse = (extractResponseAndPrintLog(spec.
                            cookie(String.valueOf(userLoginCookies)).
                            get(testData.getHostName() + "/addresses").
                            then().
                            statusCode(200)));
                    if(getAddressesResponse.getBody().asString().contains("addresses")) {

                        ArrayList addressesId = getAddressesResponse.path("addresses.id");
                        for (int i = 0; i < addressesId.size(); i++) {
                            extractResponseAndPrintLog(spec.
                                    cookie(String.valueOf(userLoginCookies)).
                                    delete(testData.getHostName() + "/addresses/" + addressesId.get(i)).
                                    then().
                                    statusCode(200));
                            info("Address with "+addressesId.get(i)+" ID was successfully removed");
                        }
                    }
                    info("Address parameter was successfully activated");
                    break;

                case "cart":
                    Response getProductsInCart = (extractResponseAndPrintLog(spec.
                            cookie(String.valueOf(userLoginCookies)).
                            get(testData.getHostName() + "/cart").
                            then().
                            statusCode(200)));

                    if(!getProductsInCart.path("entries").toString().equalsIgnoreCase("[]")){
                        extractResponseAndPrintLog(spec.
                                cookie(String.valueOf(userLoginCookies)).
                                delete(testData.getHostName() + "/cart/0").
                                then().
                                statusCode(200));
                        info("User cart was successfully cleaned");
                    }
                    info("Cart parameter was successfully activated");
                    break;

                case "wishList":

                    Response getWishListResponse = extractResponseAndPrintLog(spec.
                            cookie(String.valueOf(userLoginCookies)).
                            get(testData.getHostName() + "/user/wishlist").
                            then().
                            statusCode(200));

                    if(!getWishListResponse.getBody().asString().equals("{}")) {
                        ArrayList itemsArticle = getWishListResponse.path("entries.product.baseOptions.selected.code");
                        for (Object anItemsArticle : itemsArticle) {
                            String convertToString = String.valueOf(anItemsArticle).replace("[","").replace("]","");
                            extractResponseAndPrintLog(spec.
                                    cookie(String.valueOf(userLoginCookies)).
                                    delete(testData.getHostName() + "/user/wishlist/" + convertToString).
                                    then().
                                    statusCode(200));
                            info("Item with " + convertToString + " article was successfully removed");
                        }
                        info("Wish list parameter was successfully activated");
                        break;
                    }


                default:
                    info("Default state was successfully activated");
            }
        }
        return userLoginCookies;
    }

    protected HashMap newAddress(String requestType) throws IOException {

        JSONObject addressData = new JSONObject()
                .put("firstName", USER_FIRST_NAME)
                .put("lastName", USER_LAST_NAME)
                .put("line1", testData.getFirstUserAddressLine())
                .put("line2", "")
                .put("postalCode", testData.getUserAddressPostalCode())
                .put("extension", "123")
                .put("phone", testData.getUserAddressPhoneNumber())
                .put("town", testData.getUserAddressCity())
                .put("country", new JSONObject()
                        .put("isocode", getRegion().getValue().toUpperCase()));

        if (getRegion().getValue().toUpperCase().equals("CA")||getRegion().getValue().toUpperCase().equals("US")){
            addressData.put("region", new JSONObject()
                    .put("isocode", testData.getUserAddressRegionISOCode()));
        }
        if (getRegion().getValue().toUpperCase().equals("EU")){
            addressData.put("country", new JSONObject()
                    .put("isocode","BE")
                    .put("name", "Belgium"));
        }
        JSONObject address = new JSONObject()
                .put("address", addressData);

        if (requestType.equals("validation")){
            return new ObjectMapper().readValue(addressData.toString(), HashMap.class);
        }
        else if (requestType.equals("update")){
            JSONObject addressUpdate = new JSONObject()
                    .put("address", addressData
                            .put("line1", testData.getFirstUserAddressLineUpdate())
                            .put("phone", testData.getUserAddressPhoneNumberUpdate())
                            .put("town", testData.getUserAddressCityUpdate())
                            .put("postalCode", testData.getUserAddressPostalCodeUpdate()));
            return new ObjectMapper().readValue(addressUpdate.toString(), HashMap.class);
        }
        else {
            return new ObjectMapper().readValue(address.toString(), HashMap.class);
        }
    }

     protected boolean responseAsArrayContainsValue(ArrayList arrayList, String value){
        for (int i = 0; i <arrayList.size() ; i++) {
            if (String.valueOf(arrayList.get(i)).contains(value)) {
                return true;
            }
        }
        return false;
    }




}
