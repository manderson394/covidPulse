package edu.matc.covidPulse.persistence;

import edu.matc.covidPulse.entity.CountyCovidData;
import edu.matc.covidPulse.entity.CountyFips;
import edu.matc.covidPulse.test.util.Database;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CountyCovidDataDaoTest {

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

        expectedCountyCovid = new CountyCovidData(1, LocalDate.of(2020, 1, 21),
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
                LocalDate.of(2020, 12, 23), expectedFips, 23, 23, "Test County", "Not a State");
        int insertId = Integer.valueOf(countyCovidDao.insert(newCountyCovid));
        CountyCovidData actualCountyCovid = countyCovidDao.getById(insertId);

        assertNotNull(actualCountyCovid);
        assertEquals(expectedCountyCovid, actualCountyCovid);



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
        List<CountyCovidData> countyCovidList = countyCovidDao.getByPropertyLike("fipsCode", "53061");
        assertFalse(countyCovidList.isEmpty());
    }

    /**
     * Validates successfully retrieving via date range.
     */
    @Test
    void getByRangeSuccess() {
        LocalDate startDate = LocalDate.of(2020, 1, 21);
        LocalDate endDate = LocalDate.of(2020, 1, 24);

        List<CountyCovidData> countyCovidList = countyCovidDao.getByRange("date", startDate, endDate);

        assertEquals(5, countyCovidList.size());
    }

    /**
     * Validates successfully retrieving via date range and an additional parameter.
     */
    @Test
    void getByRangeTwoParam() {
        LocalDate startDate = LocalDate.of(2020, 1, 21);
        LocalDate endDate = LocalDate.of(2020, 1, 24);

        List<CountyCovidData> countyCovidList = countyCovidDao.getByRangeTwoParam("date", startDate,
                endDate, "fipsCode", expectedFips);

        assertEquals(4, countyCovidList.size());
    }
}
