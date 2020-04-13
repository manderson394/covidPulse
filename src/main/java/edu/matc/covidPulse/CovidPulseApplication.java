package edu.matc.covidPulse;

import edu.matc.covidPulse.controller.CountyService;
import edu.matc.covidPulse.controller.StateService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class CovidPulseApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        HashSet h = new HashSet<Class<?>>();
        h.add(StateService.class);
        h.add(CountyService.class);
        return h;
    }
}
