package core.testdata.models.preparedData;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.base.MoreObjects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "countries",
        "type",
        "number",
        "exMonth",
        "exYear",
        "cvc"
})
public class CreditCard implements ICountry {

    @JsonProperty("countries")
    private String countries;
    @JsonProperty("type")
    private String type;
    @JsonProperty("number")
    private String number;
    @JsonProperty("exMonth")
    private String exMonth;
    @JsonProperty("exYear")
    private String exYear;
    @JsonProperty("cvc")
    private String cvc;

    public String getCountries() {
        return countries;
    }

    public String getType() {
        return type;
    }

    public String getNumber() {
        return number;
    }

    public String getExMonth() {
        return exMonth;
    }

    public String getExYear() {
        return exYear;
    }

    public String getCvc() {
        return cvc;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("countries", countries)
                .add("type", type)
                .add("number", number)
                .add("exMonth", exMonth)
                .add("exYear", exYear)
                .add("cvc", cvc)
                .toString();
    }
}