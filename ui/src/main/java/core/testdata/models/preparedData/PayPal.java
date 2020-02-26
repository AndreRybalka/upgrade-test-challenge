package core.testdata.models.preparedData;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "countries",
        "email",
        "password"
})
public class PayPal implements ICountry {

    @JsonProperty("countries")
    private String countries;
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;

    public String getCountries() {
        return countries;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}