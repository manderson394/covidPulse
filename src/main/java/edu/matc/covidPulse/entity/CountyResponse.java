package edu.matc.covidPulse.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CountyResponse {

    @JsonProperty("county_fips")
    private String countyFips;

    @JsonProperty("county")
    private String countyName;

    @JsonProperty("data")
    private List<CountyDataItem> countyData;

    public CountyResponse() {
        countyData = new ArrayList<>();
    }

    public CountyResponse(String countyFips, String countyName) {
        this();
        this.countyFips = countyFips;
        this.countyName = countyName;
    }
}
