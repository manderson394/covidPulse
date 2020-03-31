package edu.matc.covidPulse.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/allData")
public class AllService {
    @GET
    @Path("/allData")
    @Produces("text/plain")
    public Response getAllData() {
        String data = "This will be all of the data";
        return Response.status(200).entity(data).build();
    }

    // NOTE: not including optional dates here since it would be the same as county data with date constraints.
}
