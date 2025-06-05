package edu.javacourse.city.dao;

import edu.javacourse.city.domain.PersonRequest;
import edu.javacourse.city.domain.PersonResponse;
import edu.javacourse.city.extension.PersonCheckException;

import java.sql.*;

public class PersonCheckDao {
    private static final String SQL_REQUEST = "select * " +
            "from cr_address_person cr_ap " +
            "inner join cr_address cr_a on cr_a.address_id = cr_ap.address_id " +
            "inner join cr_person cr_p on cr_p.person_id = cr_ap.person_id " +
            "where " +
            "current_date >= cr_ap.start_date and (current_date <= cr_ap.start_date or cr_ap.end_date is null) " +
            "and cr_p.sur_name ilike ? " +
            "and cr_p.given_name ilike ? " +
            "and cr_p.patronymic ilike ? " +
            "and cr_p.date_of_birth = ? " +
            "and cr_a.street_code = ? " +
            "and cr_a.building ilike ? ";

    private ConnectionBuilder connectionBuilder; // = new ConnectionBuilderImpl();

    public void setConnectionBuilder(ConnectionBuilder connectionBuilder){
        this.connectionBuilder = connectionBuilder;
    }

    private Connection getConnection() throws SQLException {
        return connectionBuilder.getConnection();
    }

    public PersonResponse checkPerson(PersonRequest request) throws PersonCheckException {
        PersonResponse response = new PersonResponse();

        String sql = SQL_REQUEST;

        sql += request.getExtension() != null ? "and cr_a.\"extension\" ilike ? " : "and cr_a.\"extension\" is null ";
        sql += request.getApartment() != null ? "and cr_a.apartment ilike ?;" : "and cr_a.apartment is null";


        try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            int idx = 1;
            stmt.setString(idx++, request.getSurName());
            stmt.setString(idx++, request.getGivenName());
            stmt.setString(idx++, request.getPatronymic());
            stmt.setDate(idx++, Date.valueOf(request.getDateOfBirth()));
            stmt.setInt(idx++, request.getStreetCode());
            stmt.setString(idx++, request.getBuilding());
            if (request.getExtension() != null)
                stmt.setString(idx++, request.getExtension());
            if (request.getApartment() != null)
                stmt.setString(idx, request.getApartment());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                response.setRegistered(true);
                response.setTemporal(rs.getBoolean("temporal"));
            }
        } catch (SQLException e) {
            throw new PersonCheckException(e);
        }

        return response;
    }
}
