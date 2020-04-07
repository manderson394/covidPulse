package edu.matc.covidPulse.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.matc.covidPulse.entity.CountyCovidData;
import edu.matc.covidPulse.persistence.GenericDao;
import lombok.extern.log4j.Log4j2;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Log4j2
@Path("/counties")
public class CountyService {

    private GenericDao<CountyCovidData> countyCovidDao;
    private String data;
    private int status;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllCountiesData(
            @QueryParam("startDate") String startDate,
            @QueryParam("endDate") String endDate) {

        countyCovidDao = new GenericDao<>(CountyCovidData.class);

        List<CountyCovidData> counties = getCountyResults(null, startDate, endDate);


        ObjectMapper mapper = new ObjectMapper();

        status = 200;

        try {
            String data = mapper.writeValueAsString(counties);
        } catch (JsonProcessingException jsonException) {
            log.error("Could not process object into JSON :" + counties);
            data = "{ error: \"Unable to process your request at this time.\" }";
            status = 422;
        }

//        String data = "{\"counties\": [{\"55025\": \"name\": \"Dane\", \"state\": \"WI\", [{\"date\": \"2020-03-30\", "
//        + "\"cases\": \"100\"}]},{\"55079\": \"name\": \"Milwaukee\", \"state\": \"WI\", [{\"date\": \"2020-03-30\", "
//        + "\"cases\": \"600\"}]}]}";


        return Response.status(status).entity(data).build();
    }

    @GET
    @Path("/{countyFipsCode}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getCountyData(
            @PathParam("countyFipsCode") String countyFipsCode,
            @QueryParam("startDate") String startDate,
            @QueryParam("endDate") String endDate) {

        String data = "{\"55025\": \"name\": \"Dane\", \"state\": \"WI\", [{\"date\": \"2020-03-30\", "
        + "\"cases\": \"100\"}]}]}";

        return Response.status(200).entity(data).build();
    }

    private List<CountyCovidData> getCountyResults(String fipsCode, String start, String end) {
        int fipsInt = Integer.valueOf(fipsCode);
        LocalDateTime startDateTime = convertToLocalDateTime(start);
        LocalDateTime endDateTime = convertToLocalDateTime(end);

        if (fipsCode.isEmpty()) {
            return getAllFips(startDateTime, endDateTime);
        } else {
            return getOneFips(fipsInt, startDateTime, endDateTime);
        }
    }

    private List<CountyCovidData> getAllFips(LocalDateTime startDT, LocalDateTime endDT) {

        //TODO how do we want to handle start and end? In SQL or in Java? Probably SQL

    }

    private List<CountyCovidData> getOneFips(int fipsInteger, LocalDateTime startDT, LocalDateTime endDT) {

    }

    private LocalDateTime convertToLocalDateTime (String date) {
        String delim = "[-]";
        String[] pieces = date.split(delim);
        int year = Integer.valueOf(pieces[2]);
        int month = Integer.valueOf(pieces[0]);
        int day = Integer.valueOf(pieces[1]);
        return LocalDateTime.of(year, month, day, 0, 0);
    }
}
