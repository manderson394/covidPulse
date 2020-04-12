package edu.matc.covidPulse.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "CountyFips")
@Table(name = "COUNTY_FIPS")
@Data
public class CountyFips {

    @Id
    @Column(name = "fips")
    @NotNull
    private String fips;

    @NotNull
    private String name;

    @NotNull
    private String state;

    @OneToMany(mappedBy = "fipsCode", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, orphanRemoval = false, fetch = FetchType.LAZY)
    @Getter
    private Set<CountyCovidData> covidDataSet;


    public CountyFips() {
        covidDataSet = new HashSet();
    }

    public CountyFips(String fips, String name, String state) {
        this();
        this.fips = fips;
        this.name = name;
        this.state = state;
    }

    public CountyFips(String fips, String name, String state, Set<CountyCovidData> covidDataSet) {
        this();
        this.fips = fips;
        this.name = name;
        this.state = state;
        this.covidDataSet = covidDataSet;
    }

    public void addCountyCovidData(CountyCovidData countyCovidData) {
        covidDataSet.add(countyCovidData);
    }

    @Override
    public String toString() {
        return "CountyFips{" +
                "fips='" + fips + '\'' +
                ", name='" + name + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountyFips that = (CountyFips) o;
        return Objects.equals(fips, that.fips) &&
                Objects.equals(name, that.name) &&
                Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fips, name, state);
    }
}
