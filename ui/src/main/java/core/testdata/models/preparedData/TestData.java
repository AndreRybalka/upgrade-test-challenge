package core.testdata.models.preparedData;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "user",
        "shippingAddress",
        "store",
        "creditCard",
        "payPal",
        "visaCheckoutAccount",
        "interac"
})
public class TestData {

    @JsonProperty("user")
    private List<User> users;
    @JsonProperty("address")
    private List<Address> addresses;
    @JsonProperty("creditCard")
    private List<CreditCard> creditCards;
    @JsonProperty("payPal")
    private List<PayPal> payPals;
    @JsonProperty("visaCheckoutAccount")
    private List<VisaCheckoutAccount> visaCheckoutAccounts;
    @JsonProperty("interac")
    private List<Interac> interacs;

    public List<User> getUsers() {
        return users;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public List<CreditCard> getCreditCards() {
        return creditCards;
    }

    public List<PayPal> getPayPals() {
        return payPals;
    }

    public List<VisaCheckoutAccount> getVisaCheckoutAccounts() {
        return visaCheckoutAccounts;
    }

    public List<Interac> getInteracs() {
        return interacs;
    }
}