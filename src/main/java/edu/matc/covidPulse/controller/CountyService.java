package edu.matc.covidPulse.controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/counties")
public class CountyService {

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllCountiesData(
            @QueryParam("startDate") String startDate,
            @QueryParam("endDate") String endDate) {

        String data = "{\"counties\": [{\"55025\": \"name\": \"Dane\", \"state\": \"WI\", [{\"date\": \"2020-03-30\", "
        + "\"cases\": \"100\"}]},{\"55079\": \"name\": \"Milwaukee\", \"state\": \"WI\", [{\"date\": \"2020-03-30\", "
        + "\"cases\": \"600\"}]}]}";

        return Response.status(200).entity(data).build();
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
}
