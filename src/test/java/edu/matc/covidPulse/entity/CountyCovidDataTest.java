package edu.matc.covidPulse.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests the <code>CountyCovidData</code> class.
 * @author Matt Anderson, Nate Elliot, Kevin Klemm
 * @version 11
 */
class CountyCovidDataTest {

    private CountyCovidData expectedCountyCovid;
    private CountyFips expectedFips;
    private CountyCovidData newCountyCovid;

    /**
     * Sets up instance variables before each unit test.
     */
    @BeforeEach
    void setUp() {

        expectedFips = new CountyFips("53061", "Snohomish", "WA", new HashSet<>());

        expectedCountyCovid = new CountyCovidData(1, LocalDate.of(2020, 1, 21),
                expectedFips, 1, 0, "Snohomish", "Washington");
        expectedFips.addCountyCovidData(expectedCountyCovid);

        newCountyCovid = new CountyCovidData(999999999,
                LocalDate.of(2020, 12, 23), expectedFips, 23, 23, "Test County", "Not a State");
    }

    /**
     * Validates successful to string creation.
     */
    @Test
    void testToStringSuccess() {
        String expectedString = "CountyCovidData{id=1, date=2020-01-21, fipsCode=CountyFips{fips='53061', name='Snohomish', state='WA'}, cases=1, deaths=0, countyName='Snohomish', state='Washington'}";
        assertEquals(expectedString, expectedCountyCovid.toString());
    }

    /**
     * Validates successfully testing equality.
     */
    @Test
    void testEqualsSucces() {
        assertEquals(expectedCountyCovid, expectedCountyCovid);
        assertNotEquals(newCountyCovid, expectedCountyCovid);
    }

    /**
     * Validates successful hash code creation.
     */
    @Test
    void testHashCodeSuccess() {
        assertEquals(expectedCountyCovid.hashCode(), expectedCountyCovid.hashCode());
        assertNotEquals(newCountyCovid.hashCode(), expectedCountyCovid.hashCode());
    }

    /**
     * Validates successfully setting the id.
     */
    @Test
    void setIdSuccess() {
        expectedCountyCovid.setId(1000);
        String expectedString = "CountyCovidData{id=1000, date=2020-01-21, fipsCode=CountyFips{fips='53061', name='Snohomish', state='WA'}, cases=1, deaths=0, countyName='Snohomish', state='Washington'}";
        assertEquals(expectedString, expectedCountyCovid.toString());
    }
}