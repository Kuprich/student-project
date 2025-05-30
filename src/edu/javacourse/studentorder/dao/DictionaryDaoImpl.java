package edu.javacourse.studentorder.dao;

import edu.javacourse.studentorder.config.Config;
import edu.javacourse.studentorder.domain.CountryArea;
import edu.javacourse.studentorder.domain.PassportOffice;
import edu.javacourse.studentorder.domain.RegisterOffice;
import edu.javacourse.studentorder.domain.Street;
import edu.javacourse.studentorder.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DictionaryDaoImpl implements DictionaryDao {

    private static final String GET_STREET = "SELECT * FROM jc_street WHERE LOWER(street_name) LIKE LOWER(?);";
    private static final String GET_PASSPORT_OFFICE = "SELECT * FROM jc_passport_office WHERE p_office_area_id = ?;";
    private static final String GET_REGISTER_OFFICE = "SELECT * FROM jc_register_office WHERE r_office_area_id = ?;";
    private static final String GET_AREA = "SELECT * FROM jc_country_struct WHERE area_id LIKE ? AND area_id <> ?;";

    private Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(
                Config.getProperty(Config.DB_URL),
                Config.getProperty(Config.DB_LOGIN),
                Config.getProperty(Config.DB_PASSWORD)
        );
        return connection;
    }

    @Override
    public List<Street> getStreet(String pattern) throws DaoException {
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_STREET)) {
            stmt.setString(1, "%" + pattern + "%");
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

    @Override
    public List<PassportOffice> getPassportOffices(String officeAreaId) throws DaoException {
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_PASSPORT_OFFICE)) {
            stmt.setString(1, officeAreaId);
            ResultSet rs = stmt.executeQuery();
            List<PassportOffice> result = new ArrayList<>();
            while (rs.next()) {
                result.add(new PassportOffice(rs.getLong("p_office_id"), rs.getString("p_office_area_id"), rs.getString("p_office_name")));
            }
            return result;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<RegisterOffice> getRegisterOffices(String officeAreaId) throws DaoException {
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_REGISTER_OFFICE)) {
            stmt.setString(1, officeAreaId);
            ResultSet rs = stmt.executeQuery();
            List<RegisterOffice> result = new ArrayList<>();
            while (rs.next()) {
                result.add(new RegisterOffice(rs.getLong("r_office_id"), rs.getString("r_office_area_id"), rs.getString("r_office_name")));
            }
            return result;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<CountryArea> getAreas(String areaId) throws DaoException {
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_AREA)) {
            String param1 = buildParam(areaId);
            String param2 = areaId;
            stmt.setString(1, param1);
            stmt.setString(2, param2);
            ResultSet rs = stmt.executeQuery();
            List<CountryArea> result = new ArrayList<>();
            while (rs.next()) {
                result.add(new CountryArea(rs.getString("area_id"), rs.getString("area_name")));
            }
            return result;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private String buildParam(String areaId) throws SQLException {
        if (areaId == null || areaId.trim().isEmpty()) {
            return "__0000000000";
        } else if (areaId.endsWith("0000000000")) return areaId.substring(0, 2) + "___0000000";
        else if (areaId.endsWith("0000000")) return areaId.substring(0, 5) + "___0000";
        else if (areaId.endsWith("0000")) return areaId.substring(0, 8) + "____";

        throw new SQLException("Invalid parameter areaId: " + areaId);
    }
}
