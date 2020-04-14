package edu.matc.covidPulse.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.matc.covidPulse.entity.StateCovidRecord;
import edu.matc.covidPulse.entity.StateResponse;
import edu.matc.covidPulse.persistence.GenericDao;
import edu.matc.covidPulse.transformer.StateDataTransformer;
import lombok.extern.log4j.Log4j2;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * The type State service.
 */
@Log4j2
@Path("/states")
public class StateService {
    private GenericDao dao;
    private int status;
    private ObjectMapper mapper = new ObjectMapper();

    /**
     * Instantiates a new State service.
     */
    public StateService() {
        dao = new GenericDao(StateCovidRecord.class);
    }

    /**
     * Gets all states data.
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @return the all states data
     */
    @GET
    @Produces("application/json")
    public Response getAllStatesData(@QueryParam("startDate") String startDate, @QueryParam("endDate") String endDate) {
        List<StateCovidRecord> data = new ArrayList<>();
        if (startDate != null && endDate != null) {
            // restrict data returned by date range
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
            data = dao.getByRange("date", startDate, endDate);
        } else {
            data = dao.getAll();
        }

        List<StateResponse> responses = StateDataTransformer.from(data);
        String responseBody = "";

        try {
            responseBody = mapper.writeValueAsString(responses);
            log.debug(responseBody);
            status = 200;
        } catch (JsonProcessingException exception) {
            log.error(exception.getMessage());
            status = 500;
            responseBody = "{\"error\": \"Unable to process your request at this time\"}";
        }

        return Response.status(status).entity(responseBody).build();
    }

    /**
     * Gets one state data.
     *
     * @param state     the state
     * @param startDate the start date
     * @param endDate   the end date
     * @return the one state data
     */
    @GET
    @Path("/{state}")
    @Produces("application/json")
    public Response getOneStateData(@PathParam("state") String state, @QueryParam("startDate") String startDate, @QueryParam("endDate") String endDate) {
        List<StateCovidRecord> data = new ArrayList<>();
        if (startDate != null && endDate != null) {
            // restrict data by date range
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
            data = dao.getByRangeTwoParam("date", startDate, endDate, "state", state);
        } else {
            data = dao.getByPropertyLike("state", state);
        }

        List<StateResponse> responses = StateDataTransformer.from(data);
        String responseBody = "";

        try {
            responseBody = mapper.writeValueAsString(responses);
            log.debug(responseBody);
            status = 200;
        } catch (JsonProcessingException exception) {
            log.error(exception.getMessage());
            status = 500;
            responseBody = "{\"error\": \"Unable to process your request at this time\"}";
        }

        return Response.status(status).entity(responseBody).build();
    }
}
