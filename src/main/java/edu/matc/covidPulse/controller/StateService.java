package edu.matc.covidPulse.controller;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/states")
public class StateService {
    @GET
    @Produces("application/json")
    public Response getAllStatesData(@QueryParam("startDate") String startDate, @QueryParam("endDate") String endDate) {
        String data = "{\"states\": [{\"WI\": [{\"date\": \"2020-03-30\", \"cases\": \"1200\"}]},{\"MN\": [{\"date\": \"2020-03-30\", \"cases\": \"600\"}]}]}";

        if (startDate != null || endDate != null) {
            data = "{\"This will show cases from\": \"" + startDate + " to " + endDate + "\"}";
        }

        return Response.status(200).entity(data).build();
    }

    @GET
    @Path("/{state}")
    @Produces("text/plain")
    public Response getOneStateData(@PathParam("state") String state, @QueryParam("startDate") String startDate, @QueryParam("endDate") String endDate) {
        String data = "This will be data for the state of " + state;

        if (startDate != null || endDate != null) {
            data += " from " + startDate + " to " + endDate;
        }

        return Response.status(200).entity(data).build();
    }
}
