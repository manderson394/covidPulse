package edu.matc.covidPulse.servlet;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.json.Json;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

@WebServlet(
        urlPatterns = {""}
)

public class Home extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String domain = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        Client client = ClientBuilder.newClient();

        String geographic = request.getParameter("geographic").trim();
        String searchBox = request.getParameter("searchBox").trim();
        String startDate = request.getParameter("startDate").trim();
        String endDate = request.getParameter("endDate").trim();
        String pathString = "";

        WebTarget target;

        switch (geographic) {
            case "states":
                if (searchBox.isEmpty()) {
                    target = client.target("http://localhost:8080/covidPulse_war/api")
                            .path("{geographic}").resolveTemplate("geographic", geographic)
                            .queryParam("startDate", startDate)
                            .queryParam("endDate", endDate);
                }
                else {
                    target = client.target("http://localhost:8080/covidPulse_war/api")
                            .path("{geographic}").resolveTemplate("geographic", geographic)
                            .path("{state}").resolveTemplate("state", searchBox)
                            .queryParam("startDate", startDate)
                            .queryParam("endDate", endDate);
                }
                break;
            default:
                if (searchBox.isEmpty()) {
                    target = client.target("http://localhost:8080/covidPulse_war/api")
                            .path("{geographic}").resolveTemplate("geographic", geographic)
                            .queryParam("startDate", startDate)
                            .queryParam("endDate", endDate);
                }
                else {
                    target = client.target("http://localhost:8080/covidPulse_war/api")
                            .path("{geographic}").resolveTemplate("geographic", geographic)
                            .path("{countyFipsCode}").resolveTemplate("countyFipsCode", searchBox)
                            .queryParam("startDate", startDate)
                            .queryParam("endDate", endDate);
                }
                break;
        }

        String json = target.request(MediaType.APPLICATION_JSON).get(String.class);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        List<Map<String, Object>> map = mapper.readValue(json, List.class);
        //String map = mapper.readValue(json, String.class);


        request.setAttribute("cases", map);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);

    }

}
