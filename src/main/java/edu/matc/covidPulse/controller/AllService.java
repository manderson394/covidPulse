package edu.matc.covidPulse.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.matc.covidPulse.entity.CountyCovidData;
import edu.matc.covidPulse.entity.CountyFips;
import edu.matc.covidPulse.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.*;

@Path("/all")
@Transactional
public class AllService {

    //private GenericDao countyFipsDao;
    private GenericDao countyCovidDataDao;
    ObjectMapper mapper;

    private final Logger logger = LogManager.getLogger(this.getClass());

    public AllService() {
        countyCovidDataDao = new GenericDao<>(CountyCovidData.class);
        mapper = new ObjectMapper();
    }

    @GET
    @Produces("application/json")
    public Response getAllData() {

        //countyFipsDao = new GenericDao(CountyFips.class);

        List<CountyCovidData> countyCovidDataList = countyCovidDataDao.getAll();

        String json = "";

        try {

            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(countyCovidDataList);
        }
        catch (JsonProcessingException e) {
            logger.error(e.getMessage());
        }

        return Response.status(200).entity(json).build();

        //return Response.status(200).entity(countyCovidDataList).build();
    }

}
