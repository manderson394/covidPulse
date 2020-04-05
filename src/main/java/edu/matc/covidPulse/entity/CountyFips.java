package edu.matc.covidPulse.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Data
public class CountyFips {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    private String name;

    @OneToMany(mappedBy = "fipsCode", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, orphanRemoval = false, fetch = FetchType.LAZY)
    private Set<CountyCovidData> covidDataSet;

    @Column(name = "fips_code")
    private int fipsCode;

    @Override
    public String toString() {
        return "CountyFips{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fipsCode=" + fipsCode +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountyFips that = (CountyFips) o;
        return id == that.id &&
                fipsCode == that.fipsCode &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, fipsCode);
    }
}
