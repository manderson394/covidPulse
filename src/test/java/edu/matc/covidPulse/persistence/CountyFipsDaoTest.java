package edu.matc.covidPulse.persistence;

import edu.matc.covidPulse.entity.CountyFips;
import edu.matc.covidPulse.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CountyFipsDaoTest {

    private GenericDao<CountyFips> fipsDao;
    private CountyFips expectedCountyFips;

    /**
     * Performs setup for each unit test.
     */
    @BeforeEach
    void setup() {
        fipsDao = new GenericDao(CountyFips.class);
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");
        database.runSQL("insertData.sql");

        expectedCountyFips = new CountyFips("01001", "Autauga", "AL", new HashSet<>());

    }

    /**
     * Validates the get by property value is successful.
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<CountyFips> fipsList = fipsDao.getByPropertyEqual("fips", "01001");
        assertNotNull(fipsList);
        for (CountyFips fips : fipsList) {
            assertEquals(expectedCountyFips, fips);
        }
    }

    /**
     * Validates insert is successful.
     */
    @Test
    void insertSuccess() {
        CountyFips newFips = new CountyFips("99999", "Test", "AK", new HashSet<>());
        String insertFips = fipsDao.insert(newFips);
        List<CountyFips> fipsList = fipsDao.getByPropertyEqual("fips", "99999");

        for (CountyFips actualFips : fipsList) {
            assertNotNull(actualFips);
            assertEquals(newFips, actualFips);
        }


    }

    /**
     * Validates delete is successful.
     */
    @Test
    void deleteSuccess() {
        List<CountyFips> fipsList = fipsDao.getByPropertyEqual("fips", "01001");
        assertFalse(fipsDao.getByPropertyEqual("fips", "01001").isEmpty());
    }

    /**
     * Validates the get by property like is successful.
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<CountyFips> fipsList = fipsDao.getByPropertyLike("fips", "1");
        assertFalse(fipsList.isEmpty());
    }

    /**
     * Validates the get all is successful.
     */
    @Test
    void getAllSuccess() {
        List<CountyFips> allFips = fipsDao.getAll();
        assertFalse(allFips.isEmpty());
        assertEquals(3143, allFips.size());
    }
}
