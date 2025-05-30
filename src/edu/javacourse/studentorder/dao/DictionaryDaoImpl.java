package edu.javacourse.studentorder.dao;

import edu.javacourse.studentorder.config.Config;
import edu.javacourse.studentorder.domain.Street;
import edu.javacourse.studentorder.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DictionaryDaoImpl implements DictionaryDao {

    private static final String GET_STREET = "SELECT * FROM jc_street WHERE LOWER(street_name) LIKE LOWER(?)";

    private Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection (
                Config.getProperty(Config.DB_URL),
                Config.getProperty(Config.DB_LOGIN),
                Config.getProperty(Config.DB_PASSWORD)
        );
        return connection;
    }
    public List<Street> getStreet(String pattern) throws DaoException {
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_STREET)) {
            stmt.setString(1, "%" +pattern+ "%");
            ResultSet rs = stmt.executeQuery();
            List<Street> result = new ArrayList<>();
            while (rs.next()) {
                result.add(new Street(rs.getLong("street_code"), rs.getString("street_name")));
            }
            return result;
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }
}
