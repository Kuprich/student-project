package edu.javacourse.studentorder.dao;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;

public class DbInitializer {
    public static void initDb() throws Exception {
        URL resourceCreateDb = DictionaryDaoImplTest.class.getClassLoader()
                .getResource("student-project_structure.sql");

        URL resourceFillDb = DictionaryDaoImplTest.class.getClassLoader()
                .getResource("student_project_data.sql");

        List<String> lines = Files.readAllLines(Paths.get(resourceCreateDb.toURI()));
        String sqlCreateDb = lines.stream().collect(Collectors.joining("\n"));

        lines = Files.readAllLines(Paths.get(resourceFillDb.toURI()));
        String sqlFillDb = lines.stream().collect(Collectors.joining("\n"));

        try (Connection con = ConnectionBuilder.getConnection()) {

            try (Statement stmt = con.createStatement()) {
                stmt.executeUpdate(sqlCreateDb);
                stmt.executeUpdate(sqlFillDb);
            } catch (Exception ex) {
                con.rollback();
                throw new RuntimeException(ex);
            }
        }
    }
}
