
package core.testdata.models.preparedData;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "countries",
        "searchPostCode",
        "street",
        "city",
        "province",
        "postCode"
})
public class Address implements ICountry {

    @JsonProperty("countries")
    private String countries;
    @JsonProperty("searchPostCode")
    private String searchPostCode;
    @JsonProperty("street")
    private String street;
    @JsonProperty("city")
    private String city;
    @JsonProperty("province")
    private String province;
    @JsonProperty("postCode")
    private String postCode;
    @JsonProperty("phoneFirstField")
    private String phoneNumber;

    public String getCountries() {
        return countries;
    }

    public String getSearchPostCode() {
        return searchPostCode;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}