package edu.matc.covidPulse.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.matc.covidPulse.entity.CountyCovidData;
import edu.matc.covidPulse.entity.CountyFips;
import edu.matc.covidPulse.entity.CountyResponse;
import edu.matc.covidPulse.persistence.GenericDao;
import edu.matc.covidPulse.transformer.CountyDataTransformer;
import lombok.extern.log4j.Log4j2;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Generates end points to access county related data from the Covid Pulse database.
 */
@Log4j2
@Path("/counties")
public class CountyService {

    private GenericDao<CountyCovidData> countyCovidDao;
    private ObjectMapper mapper;
    private String response;
    private int status;

    /**
     * Instantiates a new County service.
     */
    public CountyService() {
        countyCovidDao = new GenericDao<>(CountyCovidData.class);
        mapper = new ObjectMapper();
    }

    /**
     * Gets all counties data.
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @return the all counties data
     */
    @GET
    @Produces("application/json")
    public Response getAllCountiesData(
            @QueryParam("startDate") String startDate,
            @QueryParam("endDate") String endDate) {


        List<CountyCovidData> counties = getCountyResults("", startDate, endDate);

        log.debug("Counties found: {}", counties);

        List<CountyResponse> data = CountyDataTransformer.convertFrom(counties);

        try{
            response = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
            log.debug(response);
            status = 200;
        } catch (JsonProcessingException jsonException) {
            log.error(jsonException.getMessage());
            status = 500;
            response = "{\"error\": \"Unable to process your request at this time\"}";
        }

        return Response.status(status).entity(response).build();
    }

    /**
     * Gets county data for a single county.
     *
     * @param countyFipsCode the county fips code
     * @param startDate      the start date
     * @param endDate        the end date
     * @return the county data
     */
    @GET
    @Path("/{countyFipsCode}")
    @Produces("application/json")
    public Response getCountyData(
            @PathParam("countyFipsCode") String countyFipsCode,
            @QueryParam("startDate") String startDate,
            @QueryParam("endDate") String endDate) {

        List<CountyCovidData> counties = getCountyResults(countyFipsCode, startDate, endDate);

        log.debug("Counties found: {}", counties);

        List<CountyResponse> data = CountyDataTransformer.convertFrom(counties);

        try{
            response = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
            log.debug(response);
            status = 200;
        } catch (JsonProcessingException jsonException) {
            log.error(jsonException.getMessage());
            status = 500;
            response = "{\"error\": \"Unable to process your request at this time\"}";
        }

        return Response.status(status).entity(response).build();
    }

    private List<CountyCovidData> getCountyResults(String fipsCode, String start, String end) {
        LocalDate startDateTime = null;
        LocalDate endDateTime = null;

        if ((start != null) && (end != null)) {
            startDateTime = convertToLocalDateTime(start);
            endDateTime = convertToLocalDateTime(end);
        }

        if (fipsCode.isEmpty()) {
            return getAllFips(startDateTime, endDateTime);
        } else {
            return getOneFips(fipsCode, startDateTime, endDateTime);
        }
    }

    private List<CountyCovidData> getAllFips(LocalDate startDT, LocalDate endDT) {
        if ((startDT != null) && (endDT != null)) {
            return countyCovidDao.getByRange("date", startDT, endDT);
        } else {
            return countyCovidDao.getAll();
        }
    }

    private List<CountyCovidData> getOneFips(String fips, LocalDate startDT, LocalDate endDT) {
        GenericDao<CountyFips> fipsDao = new GenericDao<>(CountyFips.class);
        List<CountyFips> fipsList = fipsDao.getByPropertyEqual("fips", fips);

        if ((startDT != null) && (endDT != null)) {
            return countyCovidDao.getByRangeTwoParam("date", startDT, endDT, "fipsCode", fipsList.get(0));
        } else {
            return countyCovidDao.getByPropertyEqual("fipsCode", fipsList.get(0));
        }
    }

    private LocalDate convertToLocalDateTime (String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

        return LocalDate.parse(date, formatter);
    }
}
