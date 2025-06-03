package edu.javacourse.city.dao;

import edu.javacourse.city.domain.PersonRequest;
import edu.javacourse.city.domain.PersonResponse;
import edu.javacourse.city.extension.PersonCheckException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonCheckDao {
    private static final String SQL_REQUEST = "select * " +
            "from cr_address_person cr_ap " +
            "inner join cr_address cr_a on cr_a.address_id = cr_ap.address_id " +
            "inner join cr_person cr_p on cr_p.person_id = cr_ap.person_id " +
            "where  cr_p.sur_name ilike 'иванов' " +
            "and cr_p.given_name ilike 'иван' " +
            "and cr_p.patronymic ilike 'иванович' " +
            "and cr_p.date_of_birth = '1980-05-15' " +
            "and cr_a.street_code = 1 " +
            "and cr_a.building ilike '10' " +
            "and cr_a.\"extension\" ilike 'А' " +
            "and cr_a.apartment ilike '15'";

    public PersonResponse checkPerson(PersonRequest request) throws PersonCheckException {
        PersonResponse response = new PersonResponse();

        try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(SQL_REQUEST)) {
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

    private Connection getConnection() {
        return null;
    }
}
