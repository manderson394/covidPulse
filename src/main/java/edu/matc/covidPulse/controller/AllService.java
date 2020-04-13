package edu.matc.covidPulse.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.matc.covidPulse.entity.CountyCovidData;
import edu.matc.covidPulse.entity.CountyFips;
import edu.matc.covidPulse.persistence.GenericDao;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.*;

@Path("/all")
@Transactional
public class AllService {

    private GenericDao countyFipsDao;
    private GenericDao countyCovidDataDao;

    public AllService() {  }

    @GET
    @Path("/")
    @Produces("application/json")
    public Response getAllData() {

        countyFipsDao = new GenericDao(CountyFips.class);
        countyCovidDataDao = new GenericDao(CountyCovidData.class);

        List<CountyCovidData> countyCovidDataList = countyCovidDataDao.getAll();

        String json = "";

        try {

            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            json = mapper.writeValueAsString(countyCovidDataList);

        }
        catch (JsonProcessingException e) {

            e.printStackTrace();

        }

        return Response.status(200).entity(json).build();

        //return Response.status(200).entity(countyCovidDataList).build();
    }

    // NOTE: not including optional dates here since it would be the same as county data with date constraints.
}
