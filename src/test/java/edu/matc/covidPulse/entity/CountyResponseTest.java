package edu.matc.covidPulse.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests the <code>CountyResponse</code> class.
 * @author Matt Anderson, Kevin Klemm, Nate Elliot
 * @version 11
 */
class CountyResponseTest {

    private CountyResponse countyResponse;
    private CountyResponse newCountyResponse;
    private CountyDataItem dataItem;

    /**
     * Sets up unit tests.
     */
    @BeforeEach
    void setUp() {
        countyResponse = new CountyResponse( "11111", "test county");
        newCountyResponse = new CountyResponse("22222", "new test county");
    }

    /**
     * Validates gets county fips successfully.
     */
    @Test
    void getCountyFipsSuccess() {
        assertEquals("11111", countyResponse.getCountyFips());
    }

    /**
     * Validates gets county name successfully.
     */
    @Test
    void getCountyNameSuccess() {
        assertEquals("test county", countyResponse.getCountyName());
    }

    /**
     * Validates gets county data successfully.
     */
    @Test
    void getCountyDataSuccess() {
        assertEquals(0, countyResponse.getCountyData().size());
    }

    /**
     * Validates sets county fips successfully.
     */
    @Test
    void setCountyFipsSuccess() {
        countyResponse.setCountyFips("33333");
        assertEquals("33333", countyResponse.getCountyFips());
    }

    /**
     * Validates sets county name successfully.
     */
    @Test
    void setCountyNameSuccess() {
        countyResponse.setCountyName("new");
        assertEquals("new", countyResponse.getCountyName());
    }

    /**
     * Validates ets county data successfully.
     */
    @Test
    void setCountyDataSuccess() {
        List<CountyDataItem> dataList = new ArrayList<>();
        dataList.add(new CountyDataItem("date", 2, 3));
        countyResponse.setCountyData(dataList);
        assertEquals(dataList, countyResponse.getCountyData());
    }

    /**
     * Test equals success.
     */
    @Test
    void testEqualsSuccess() {
        assertEquals(countyResponse, countyResponse);
        assertNotEquals(countyResponse, newCountyResponse);
    }

    /**
     * Test hash code success.
     */
    @Test
    void testHashCodeSuccess() {
        assertEquals(countyResponse.hashCode(), countyResponse.hashCode());
        assertNotEquals(countyResponse.hashCode(), newCountyResponse.hashCode());
    }

    /**
     * Test to string success.
     */
    @Test
    void testToStringSuccess() {
        String expectedString = "CountyResponse(countyFips=11111, countyName=test county, countyData=[])";
        assertEquals(expectedString, countyResponse.toString());
    }
}