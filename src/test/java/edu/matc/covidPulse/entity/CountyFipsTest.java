package edu.matc.covidPulse.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests the <code>CountyFips</code> class.
 * @author Matt Anderson, Kevin Klemm, Nate Elliot
 * @version 11
 */
class CountyFipsTest {

    private CountyFips expectedCountyFips;
    private CountyCovidData expectedCountyCovid;
    private CountyFips newCountyFips;

    /**
     * Sets up instance variables before each unit test.
     */
    @BeforeEach
    void setUp() {

        expectedCountyFips = new CountyFips("01001", "Autauga", "AL", new HashSet<>());

        expectedCountyCovid = new CountyCovidData(1, LocalDate.of(2020, 1, 21),
                expectedCountyFips, 1, 0, "Snohomish", "Washington");

        newCountyFips = new CountyFips("11111", "Tester", "NA", new HashSet<>());
    }

    /**
     * Validates adding County COVID data successfully.
     */
    @Test
    void addCountyCovidDataSuccess() {
        expectedCountyFips.addCountyCovidData(expectedCountyCovid);
        assertTrue(expectedCountyFips.getCovidDataSet().contains(expectedCountyCovid));
    }

    /**
     * Validates County COVID Data retrieval success.
     */
    @Test
    void getCountyCovidDataSuccess() {
        expectedCountyFips.addCountyCovidData(expectedCountyCovid);
        assertTrue(expectedCountyFips.getCovidDataSet().size() > 0);

    }

    /**
     * Validates to string success.
     */
    @Test
    void testToStringSuccess() {
        String expectedString = "CountyFips{fips='01001', name='Autauga', state='AL'}";
        assertEquals(expectedString, expectedCountyFips.toString());
    }

    /**
     * Validates equality is evaluated successfully.
     */
    @Test
    void testEqualsSuccess() {
        assertEquals(expectedCountyFips, expectedCountyFips);
        assertNotEquals(newCountyFips, expectedCountyFips);
    }

    /**
     * Validates successful hashcode creation.
     */
    @Test
    void testHashCodeSuccess() {
        assertEquals(expectedCountyFips.hashCode(), expectedCountyFips.hashCode());
        assertNotEquals(newCountyFips.hashCode(), expectedCountyFips.hashCode());
    }
}