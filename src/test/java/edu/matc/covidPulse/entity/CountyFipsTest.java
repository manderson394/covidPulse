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

    /**
     * Validates gets fips successfully.
     */
    @Test
    void getFipsSuccess() {
        assertEquals("01001", expectedCountyFips.getFips());
    }

    /**
     * Validates sets fips successfully.
     */
    @Test
    void setFipsSuccess() {
        expectedCountyFips.setFips("99999");
        assertEquals("99999", expectedCountyFips.getFips());
    }

    /**
     * Validates gets name successfully.
     */
    @Test
    void getNameSuccess() {
        assertEquals("Autauga", expectedCountyFips.getName());
    }

    /**
     * Validates sets name successfully.
     */
    @Test
    void setNameSuccess() {
        expectedCountyFips.setName("test");
        assertEquals("test", expectedCountyFips.getName());
    }

    /**
     * Validates gets state successfully.
     */
    @Test
    void getStateSuccess() {
        assertEquals("AL", expectedCountyFips.getState());
    }

    /**
     * Validates sets state successfully.
     */
    @Test
    void setStateSuccess() {
        expectedCountyFips.setState("WA");
        assertEquals("WA", expectedCountyFips.getState());
    }

    /**
     * Validates gets covid data set successfully.
     */
    @Test
    void getCovidDataSetSuccess() {
        assertEquals(0, expectedCountyFips.getCovidDataSet().size());
    }

    /**
     * Validates sets covid data set successfully.
     */
    @Test
    void setCovidDataSetSuccess() {
        HashSet<CountyCovidData> newSet = new HashSet<>();
        CountyCovidData covidData = new CountyCovidData();
        newSet.add(covidData);
        expectedCountyFips.setCovidDataSet(newSet);
        assertEquals(1, expectedCountyFips.getCovidDataSet().size());
    }
}