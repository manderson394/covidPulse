package edu.matc.covidPulse.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import edu.matc.covidPulse.entity.CountyFips;
import edu.matc.covidPulse.persistence.GenericDao;
import edu.matc.covidPulse.test.util.PropertiesLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class AllServiceTest {

    private GenericDao dao;

    private AllService allService;

    @BeforeEach
    void setUp() {

        allService = new AllService();

    }

    @Test
    void getAllData() throws JsonProcessingException {

        // List<CountyFips> countyFips = allService.getAllData();

        Response countyFips = allService.getAllData();

        String test = "testing";

    }
}