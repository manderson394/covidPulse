package edu.matc.covidPulse.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a single state record.
 */
@Entity(name = "StateCovidRecord")
@Table(name = "STATE_COVID_DATA")
public class StateCovidRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;
    @Column(name = "state")
    private String state;
    @Column(name = "date")
    private String date;
    @Column(name = "state_fips")
    private String fipsCode;
    @Column(name = "cases")
    private int cases;
    @Column(name = "deaths")
    private int deaths;

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * Sets state.
     *
     * @param state the state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets fips code.
     *
     * @return the fips code
     */
    public String getFipsCode() {
        return fipsCode;
    }

    /**
     * Sets fips code.
     *
     * @param fipsCode the fips code
     */
    public void setFipsCode(String fipsCode) {
        this.fipsCode = fipsCode;
    }

    /**
     * Gets cases.
     *
     * @return the cases
     */
    public int getCases() {
        return cases;
    }

    /**
     * Sets cases.
     *
     * @param cases the cases
     */
    public void setCases(int cases) {
        this.cases = cases;
    }

    /**
     * Gets deaths.
     *
     * @return the deaths
     */
    public int getDeaths() {
        return deaths;
    }

    /**
     * Sets deaths.
     *
     * @param deaths the deaths
     */
    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StateCovidRecord that = (StateCovidRecord) o;
        return id == that.id &&
                cases == that.cases &&
                deaths == that.deaths &&
                state.equals(that.state) &&
                date.equals(that.date) &&
                fipsCode.equals(that.fipsCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, state, date, fipsCode, cases, deaths);
    }

    @Override
    public String toString() {
        return "StateCovidRecord{" +
                "id=" + id +
                ", state='" + state + '\'' +
                ", date=" + date +
                ", fipsCode='" + fipsCode + '\'' +
                ", cases=" + cases +
                ", deaths=" + deaths +
                '}';
    }
}
