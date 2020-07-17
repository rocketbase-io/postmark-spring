package io.rocketbase.mail.dto.webhook.sub;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Geo {

    @JsonProperty("CountryISOCode")
    private String countryIsoCode;

    @JsonProperty("Country")
    private String country;

    @JsonProperty("RegionISOCode")
    private String regionIsoCode;

    @JsonProperty("Region")
    private String region;

    @JsonProperty("City")
    private String city;

    @JsonProperty("Zip")
    private String zip;

    @JsonProperty("Coords")
    private String coords;

    @JsonProperty("IP")
    private String ip;
}
