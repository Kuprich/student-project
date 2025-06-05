package edu.javacourse.city.config;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/rest")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        packages("edu.javacourse.city.web");
    }
}