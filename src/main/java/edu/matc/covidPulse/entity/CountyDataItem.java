package edu.matc.covidPulse.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CountyDataItem {

    @JsonProperty("date")
    private String date;

    @JsonProperty("cases")
    private int cases;

    @JsonProperty("deaths")
    private int deaths;
}
