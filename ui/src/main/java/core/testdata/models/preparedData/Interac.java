package core.testdata.models.preparedData;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "idebitTrack2"
})
public class Interac {

    @JsonProperty("idebitTrack2")
    private String idebitTrack2;

    public String getIdebitTrack2() {
        return idebitTrack2;
    }
}