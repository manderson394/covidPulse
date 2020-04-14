package edu.matc.covidPulse.persistence;

import edu.matc.covidPulse.entity.StateCovidRecord;
import edu.matc.covidPulse.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StateCovidRecordDaoTest {
    private GenericDao dao;
    private StateCovidRecord expectedRecord;

    @BeforeEach
    void setup() {
        dao = new GenericDao(StateCovidRecord.class);
        Database database = Database.getInstance();
        database.runSQL("insertData.sql");

        expectedRecord = new StateCovidRecord();
        expectedRecord.setId(1);
        expectedRecord.setState("WA");
        expectedRecord.setFipsCode("53");
        expectedRecord.setCases(1);
        expectedRecord.setDeaths(0);
        expectedRecord.setDate("2020-01-21");

    }

    @Test
    void getByIdSuccess() {
        StateCovidRecord actualRecord = (StateCovidRecord) dao.getById(1);
        assertNotNull(actualRecord);
        assertEquals(expectedRecord, actualRecord);
    }

    @Test
    void insertSuccess() {
        StateCovidRecord recordToInsert = new StateCovidRecord();
        recordToInsert.setId(99999);
        recordToInsert.setState("WI");
        recordToInsert.setFipsCode("99");
        recordToInsert.setCases(100);
        recordToInsert.setDeaths(200);
        recordToInsert.setDate("2020-02-20");

        int id = Integer.parseInt(dao.insert(recordToInsert));

        StateCovidRecord insertedRecord = (StateCovidRecord) dao.getById(id);

        assertNotNull(insertedRecord);
        assertEquals(recordToInsert, insertedRecord);
    }

    @Test
    void deleteSuccess() {
        StateCovidRecord recordToDelete = (StateCovidRecord) dao.getById(1);
        dao.delete(recordToDelete);
        assertNull(dao.getById(1));
    }

    @Test
    void getByPropertyLikeSuccess() {
        List<StateCovidRecord> records = dao.getByPropertyLike("state", "WA");
        assertFalse(records.isEmpty());
        assertTrue(records.size() > 1);
    }

    @Test
    void getByPropertyEqualSuccess() {
        List<StateCovidRecord> records = dao.getByPropertyEqual("id", 1);
        assertNotNull(records);
        assertEquals(expectedRecord, records.get(0));
    }

    @Test
    void getByRangeSuccess() {
        String startDate = "2020-01-21";
        String endDate = "2020-01-24";

        List<StateCovidRecord> records = dao.getByRange("date", startDate, endDate);

        assertEquals(5, records.size());
    }

    @Test
    void getByRangeTwoParamSuccess() {
        String startDate = "2020-01-21";
        String endDate = "2020-01-24";

        List<StateCovidRecord> records = dao.getByRangeTwoParam("date", startDate,
                endDate, "state", "WA");

        assertEquals(4, records.size());
    }
}
