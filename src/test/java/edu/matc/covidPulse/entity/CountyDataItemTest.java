package edu.matc.covidPulse.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests the <code>COuntyDataItem</code> class.
 * @author Matt Anderson, Nate Elliot, Kevin Klemm
 * @version 11
 */
class CountyDataItemTest {

    private CountyDataItem countyItem;
    private CountyDataItem newCountyItem;

    /**
     * Sets up unit tests.
     */
    @BeforeEach
    void setUp() {
        countyItem = new CountyDataItem("date", 1, 2);
        newCountyItem = new CountyDataItem("newDate", 2, 3);
    }

    /**
     * Validates gets date successfully.
     */
    @Test
    void getDateSuccess() {
        assertEquals("date", countyItem.getDate());
    }

    /**
     * Validates gets cases successfully.
     */
    @Test
    void getCasesSuccess() {
        assertEquals(1, countyItem.getCases());
    }

    /**
     * Validates gets deaths successfully.
     */
    @Test
    void getDeathsSuccess() {
        assertEquals(2, countyItem.getDeaths());
    }

    /**
     * Validates sets date successfully.
     */
    @Test
    void setDateSuccess() {
        countyItem.setDate("another");
        assertEquals("another", countyItem.getDate());
    }

    /**
     * validates sets cases successfully.
     */
    @Test
    void setCasesSuccess() {
        countyItem.setCases(23);
        assertEquals(23, countyItem.getCases());
    }

    /**
     * Validates sets deaths successfully.
     */
    @Test
    void setDeathsSuccess() {
        countyItem.setDeaths(33);
        assertEquals(33, countyItem.getDeaths());
    }

    /**
     * Test equals.
     */
    @Test
    void testEqualsSuccess() {
        assertEquals(countyItem, countyItem);
        assertNotEquals(countyItem, newCountyItem);
    }

    /**
     * Test hash code.
     */
    @Test
    void testHashCodeSuccess() {
        assertEquals(countyItem.hashCode(), countyItem.hashCode());
        assertNotEquals(countyItem.hashCode(), newCountyItem.hashCode());
    }

    /**
     * Test to string.
     */
    @Test
    void testToStringSuccess() {
        String expectedString = "CountyDataItem(date=date, cases=1, deaths=2)";
        assertEquals(expectedString, countyItem.toString());
    }
}