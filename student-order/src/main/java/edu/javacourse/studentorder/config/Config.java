package edu.javacourse.studentorder.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    public static final String DB_URL = "db.url";
    public static final String DB_LOGIN = "db.login";
    public static final String DB_PASSWORD = "db.password";
    public static final String DB_LIMIT = "db.limit";
    public static final String CR_URL = "cr.url";


    private static final Properties properties = new Properties();

    public static synchronized String getProperty(String name) {
        if (properties.isEmpty()) {
            try (InputStream is = Config.class.getClassLoader().getResourceAsStream("config.properties")){
                properties.load(is);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return properties.getProperty(name);
    }
}
