package edu.matc.covidPulse.persistence;

import edu.matc.covidPulse.entity.CountyCovidData;
import edu.matc.covidPulse.entity.CountyFips;
import edu.matc.covidPulse.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CountyCovidDataDaoTest {

    private GenericDao<CountyCovidData> countyCovidDao;
    private CountyCovidData expectedCountyCovid;
    private CountyFips expectedFips;

    /**
     * Performs setup for each unit test.
     */
    @BeforeEach
    void setup() {
        countyCovidDao = new GenericDao(CountyCovidData.class);
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");
        database.runSQL("insertData.sql");

        expectedFips = new CountyFips("53061", "Snohomish", "WA", new HashSet<>());

        expectedCountyCovid = new CountyCovidData(1, "2020-01-21",
                expectedFips, 1, 0, "Snohomish", "Washington");
        expectedFips.addCountyCovidData(expectedCountyCovid);

    }

    /**
     * Validates the get by id is successful.
     */
    @Test
    void getByIdSuccess() {
        CountyCovidData actualCountyCovid = countyCovidDao.getById(1);
        assertNotNull(actualCountyCovid);
        assertEquals(expectedCountyCovid, actualCountyCovid);

    }

    @Test
    void getAllSuccess() {
        List<CountyCovidData> countyCovidData = countyCovidDao.getAll();
        assertEquals(373, countyCovidData.size());
    }

    /**
     * Validates the get by property value is successful.
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<CountyCovidData> countyCovidList = countyCovidDao.getByPropertyEqual("id", 1);
        assertNotNull(countyCovidList);
        for (CountyCovidData countyCovid : countyCovidList) {
            assertEquals(expectedCountyCovid, countyCovid);
        }
    }

    /**
     * Validates insert is successful.
     */
    @Test
    void insertSuccess() {
        CountyCovidData newCountyCovid = new CountyCovidData(999999999,
                "2020-12-23", expectedFips, 23, 23, "Test County", "Not a State");
        int insertId = Integer.valueOf(countyCovidDao.insert(newCountyCovid));
        newCountyCovid.setId(insertId);
        CountyCovidData actualCountyCovid = countyCovidDao.getById(insertId);

        assertNotNull(actualCountyCovid);
        assertEquals(newCountyCovid, actualCountyCovid);



    }

    /**
     * Validates delete is successful.
     */
    @Test
    void deleteSuccess() {

        CountyCovidData extractedCountyCovid = countyCovidDao.getById(1);
        countyCovidDao.delete(extractedCountyCovid);
        assertNull(countyCovidDao.getById(1));
    }

    /**
     * Validates the get by property like is successful.
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<CountyCovidData> countyCovidList = countyCovidDao.getByPropertyLike("state", "Washington");
        assertFalse(countyCovidList.isEmpty());
    }

    /**
     * Validates successfully retrieving via date range.
     */
    @Test
    void getByRangeSuccess() {
        String startDate = "2020-01-21";
        String endDate = "2020-01-24";

        List<CountyCovidData> countyCovidList = countyCovidDao.getByRange("date", startDate, endDate);

        assertEquals(5, countyCovidList.size());
    }

    /**
     * Validates successfully retrieving via date range and an additional parameter.
     */
    @Test
    void getByRangeTwoParam() {
        String startDate = "2020-01-21";
        String endDate = "2020-01-24";

        List<CountyCovidData> countyCovidList = countyCovidDao.getByRangeTwoParam("date", startDate,
                endDate, "fipsCode", expectedFips);

        assertEquals(4, countyCovidList.size());
    }
}
