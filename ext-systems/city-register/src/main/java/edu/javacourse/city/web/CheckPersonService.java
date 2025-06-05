package edu.javacourse.city.web;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/check")
public class CheckPersonService {
    public CheckPersonService() {
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String check(){
        return "check person method";
    }

}
