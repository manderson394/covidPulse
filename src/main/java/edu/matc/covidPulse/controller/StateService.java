package edu.matc.covidPulse.controller;

import edu.matc.covidPulse.entity.StateCovidRecord;
import edu.matc.covidPulse.entity.StateResponse;
import edu.matc.covidPulse.persistence.GenericDao;
import edu.matc.covidPulse.transformer.StateDataTransformer;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/states")
public class StateService {
    private GenericDao dao;

    public StateService() {
        dao = new GenericDao(StateCovidRecord.class);
    }

    @GET
    @Produces("application/json")
    public Response getAllStatesData(@QueryParam("startDate") String startDate, @QueryParam("endDate") String endDate) {
        List<StateCovidRecord> data = new ArrayList<>();
        if (startDate != null || endDate != null) {
            // restrict data returned
        } else {
            data = dao.getAll();
        }

        List<StateResponse> responseBody = StateDataTransformer.from(data);

        return Response.status(200).entity(responseBody).build();
    }

    @GET
    @Path("/{state}")
    @Produces("text/plain")
    public Response getOneStateData(@PathParam("state") String state, @QueryParam("startDate") String startDate, @QueryParam("endDate") String endDate) {
        if (startDate != null || endDate != null) {
            // restrict data
        }

        List<StateCovidRecord> data = dao.getByPropertyLike("state", state);
        List<StateResponse> responseBody = StateDataTransformer.from(data);

        return Response.status(200).entity(responseBody).build();
    }
}
