package edu.javacourse.studentorder.dao;

import edu.javacourse.studentorder.domain.Street;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DirectoryDao {
    private Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection (
                "jdbc:postgresql://localhost:5434/jc_student",
                "postgres", "postgres"
        );
        return connection;
    }
    public List<Street> getStreet(String pattern) throws SQLException {
        Connection con = getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM jc_street WHERE LOWER(street_name) LIKE LOWER('% "+pattern+" %')");
        List<Street> result = new ArrayList<>();
        while(rs.next()){
            result.add(new Street(rs.getLong("street_code"),rs.getString("string_name")));
        }
        return result;
    }
}
