package io.rocketbase.mail.dto.webhook.sub;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OperationSystem {

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Company")
    private String company;

    @JsonProperty("Family")
    private String family;
}
