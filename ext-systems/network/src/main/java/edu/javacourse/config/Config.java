package edu.javacourse.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private static final Properties properties = new Properties();

    public static synchronized Properties getProperties() {
        if (properties.isEmpty()) {
            try (InputStream is = Config.class.getClassLoader().getResourceAsStream("server.properties")){
                properties.load(is);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return properties;
    }

}
