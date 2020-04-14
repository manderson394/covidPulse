package edu.matc.covidPulse.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity(name = "CountyCovidData")
@Table(name = "COUNTY_COVID_DATA")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CountyCovidData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @NotNull
    private String date;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "county_fips_code", referencedColumnName = "fips")
    private CountyFips fipsCode;

    @NotNull
    private int cases;

    @NotNull
    private int deaths;

    @Column(name = "county_name")
    private String countyName;

    private String state;

    @Override
    public String toString() {
        return "CountyCovidData{" +
                "id=" + id +
                ", date=" + date +
                ", fipsCode=" + fipsCode +
                ", cases=" + cases +
                ", deaths=" + deaths +
                ", countyName='" + countyName + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountyCovidData that = (CountyCovidData) o;
        return id == that.id &&
                cases == that.cases &&
                deaths == that.deaths &&
                Objects.equals(date, that.date) &&
                Objects.equals(fipsCode, that.fipsCode) &&
                Objects.equals(countyName, that.countyName) &&
                Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, fipsCode, cases, deaths, countyName, state);
    }
}
