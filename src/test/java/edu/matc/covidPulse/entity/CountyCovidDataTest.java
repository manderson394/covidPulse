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

        expectedCountyCovid = new CountyCovidData(1, "2020-01-21",
                expectedFips, 1, 0, "Snohomish", "Washington");
        expectedFips.addCountyCovidData(expectedCountyCovid);

        newCountyCovid = new CountyCovidData(999999999,
                "2020-12-23", expectedFips, 23, 23, "Test County", "Not a State");
    }

    /**
     * Validates no argument constructor object creation.
     */
    @Test
    void noArgsConstructorSuccess() {
        assertNotNull(new CountyCovidData());
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

    /**
     * Validates gets id successfully.
     */
    @Test
    void getIdSuccess() {
        assertEquals(1, expectedCountyCovid.getId());
    }

    /**
     * Validates gets county name successfully.
     */
    @Test
    void getCountyNameSuccess() {
        assertEquals("Snohomish", expectedCountyCovid.getCountyName());
    }

    /**
     * Validates sets county name successfully.
     */
    @Test
    void setCountyNameSuccess() {
        expectedCountyCovid.setCountyName("test");
        assertEquals("test", expectedCountyCovid.getCountyName());
    }

    /**
     * Validates gets state successfully.
     */
    @Test
    void getStateSuccess() {
        assertEquals("Washington", expectedCountyCovid.getState());
    }

    /**
     * Validates sets state successfully.
     */
    @Test
    void setStateSuccess() {
        expectedCountyCovid.setState("AK");
        assertEquals("AK", expectedCountyCovid.getState());
    }

    /**
     * Validates gets date successfully.
     */
    @Test
    void getDateSuccess() {
        assertEquals("2020-01-21", expectedCountyCovid.getDate());
    }

    /**
     * Validates sets date successfully.
     */
    @Test
    void setDateSuccess() {
        expectedCountyCovid.setDate("2020-01-22");
        assertEquals("2020-01-22", expectedCountyCovid.getDate());
    }

    /**
     * Validates gets fips code successfully.
     */
    @Test
    void getFipsCodeSuccess() {
        assertEquals(expectedFips, expectedCountyCovid.getFipsCode());
    }

    /**
     * Validates sets fips code successfully.
     */
    @Test
    void setFipsCodeSuccess() {
        CountyFips newFips = new CountyFips("53331", "test", "WA", new HashSet<>());
        expectedCountyCovid.setFipsCode(newFips);
        assertEquals(newFips, expectedCountyCovid.getFipsCode());
    }

    /**
     * Validates gets cases successfully.
     */
    @Test
    void getCasesSuccess() {
        assertEquals(1, expectedCountyCovid.getCases());
    }

    /**
     * Validates sets cases successfully.
     */
    @Test
    void setCasesSuccess() {
        expectedCountyCovid.setCases(3);
        assertEquals(3, expectedCountyCovid.getCases());
    }

    /**
     * Validates gets deaths successfully.
     */
    @Test
    void getDeathsSuccess() {
        assertEquals(0, expectedCountyCovid.getDeaths());
    }

    /**
     * Validates sets deaths successfully.
     */
    @Test
    void setDeathsSuccess() {
        expectedCountyCovid.setDeaths(44);
        assertEquals(44, expectedCountyCovid.getDeaths());
    }

}