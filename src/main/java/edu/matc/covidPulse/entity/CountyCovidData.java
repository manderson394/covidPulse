package edu.matc.covidPulse.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
public class CountyCovidData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "county_fips_code", referencedColumnName = "fips_code")
    private CountyFips fipsCode;

    private int cases;

    private int deaths;
}
