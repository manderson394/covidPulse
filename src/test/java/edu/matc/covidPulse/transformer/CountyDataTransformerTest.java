package edu.matc.covidPulse.transformer;

import edu.matc.covidPulse.entity.CountyCovidData;
import edu.matc.covidPulse.entity.CountyDataItem;
import edu.matc.covidPulse.entity.CountyFips;
import edu.matc.covidPulse.entity.CountyResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests the <code>CountyDataTransformer</code> class.
 * @author Matt Anderson, Nate Elliot, Kevin Klemm
 * @version 11
 */
class CountyDataTransformerTest {

    private CountyCovidData data;
    private CountyFips fips;
    private CountyResponse response;
    private CountyDataItem item;
    private List<CountyCovidData> covidDataList = new ArrayList<>();
    private List<CountyResponse> responses = new ArrayList<>();

    /**
     * Sets up unit tests.
     */
    @BeforeEach
    void setUp() {
        fips = new CountyFips("01001", "TestName", "AL", new HashSet<>());
        data = new CountyCovidData(1, LocalDate.of(2020, 1, 21), fips,
                2, 1, "TestName", "TestState");
        covidDataList.add(data);

        item = new CountyDataItem("2020-01-21", 2, 1);
        List<CountyDataItem> itemList = new ArrayList<>();
        itemList.add(item);
        response = new CountyResponse("01001", "TestName");
        response.setCountyData(itemList);
        responses.add(response);

    }

    /**
     * Validates successful list conversion.
     */
    @Test
    void convertFromSuccess() {
        assertEquals(response, CountyDataTransformer.convertFrom(covidDataList).get(0));
    }
}