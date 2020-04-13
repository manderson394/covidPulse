package edu.matc.covidPulse.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.matc.covidPulse.entity.CountyCovidData;
import edu.matc.covidPulse.entity.CountyFips;
import edu.matc.covidPulse.persistence.GenericDao;
import edu.matc.covidPulse.test.util.PropertiesLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.Entity;
import javax.ws.rs.core.Response;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AllServiceTest {

    private GenericDao dao;

    private AllService allService;
    private GenericDao countyCovidDataDao;
    private GenericDao expectedCountyCovid;

    @BeforeEach
    void setUp() {

        allService = new AllService();

        countyCovidDataDao = new GenericDao(CountyCovidData.class);

        expectedCountyCovid = new GenericDao(CountyCovidData.class);

    }

    @Test
    void getAllData() throws JsonProcessingException {

        List<CountyCovidData> expectedCountyCovidData = expectedCountyCovid.getAll();

        List<CountyCovidData> countyCovidData = countyCovidDataDao.getAll();

        assertNotNull(countyCovidData);
        assertEquals(expectedCountyCovidData.getClass(), countyCovidData.getClass());

        String test = "testing";

    }
}