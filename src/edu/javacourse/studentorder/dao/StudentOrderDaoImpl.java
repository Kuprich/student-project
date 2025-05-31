package edu.javacourse.studentorder.dao;

import edu.javacourse.studentorder.config.Config;
import edu.javacourse.studentorder.domain.Adult;
import edu.javacourse.studentorder.domain.StudentOrder;
import edu.javacourse.studentorder.exception.DaoException;

import java.sql.*;
import java.time.LocalDateTime;

public class StudentOrderDaoImpl implements StudentOrderDao {

    private static final String INSERT_ORDER = "INSERT INTO jc_student_order(" +
            "student_order_status, student_oder_date, " +
            "h_sur_name, h_given_name, h_patronymic, h_date_of_birth, h_passport_seria, h_passport_number, h_passport_date, h_passport_office_id, h_post_index, h_street_code, h_building, h_extension, h_apartment, " +
            "w_sur_name, w_given_name, w_patronymic, w_date_of_birth, w_passport_seria, w_passport_number, w_passport_date, w_passport_office_id, w_post_index, w_street_code, w_building, w_extension, w_apartment, " +
            "certificate_id, register_office_id, marriage_date) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(
                Config.getProperty(Config.DB_URL),
                Config.getProperty(Config.DB_LOGIN),
                Config.getProperty(Config.DB_PASSWORD)
        );
        return connection;
    }

    @Override
    public Long SaveStudentOrder(StudentOrder so) throws DaoException {
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(INSERT_ORDER, new String[]{"student_order_id"})) {

            stmt.setInt(1, StudentOrder.StudentOrderStatus.START.ordinal());
            stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));

            // husband
            SetParamsForAdult(stmt, so.getHusband(), 3);

            // wife
            SetParamsForAdult(stmt, so.getWife(), 16);

            // register
            stmt.setString(29, so.getMarriageCertificateId());
            stmt.setLong(30, so.getMarriageOffice().getOfficeId());
            stmt.setDate(31, Date.valueOf(so.getMarriageDate()));

            var sqlResult = stmt.toString();

            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) return generatedKeys.getLong("student_order_id");

            return -1L;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private static void SetParamsForAdult(PreparedStatement stmt, Adult adult, int parameterIndex) throws SQLException {
        stmt.setString(parameterIndex++, adult.getSurName());
        stmt.setString(parameterIndex++, adult.getGivenName());
        stmt.setString(parameterIndex++, adult.getPatronymic());
        stmt.setDate(parameterIndex++, Date.valueOf(adult.getDateOfBirth()));
        stmt.setString(parameterIndex++, adult.getPassportSeria());
        stmt.setString(parameterIndex++, adult.getPassportNumber());
        stmt.setDate(parameterIndex++, Date.valueOf(adult.getIssueDate()));
        stmt.setLong(parameterIndex++, adult.getIssueDepartment().getOfficeId());
        stmt.setString(parameterIndex++, adult.getAddress().getPostCode());
        stmt.setLong(parameterIndex++, adult.getAddress().getStreet().getStreetCode());
        stmt.setString(parameterIndex++, adult.getAddress().getBuilding());
        stmt.setString(parameterIndex++, adult.getAddress().getExtension());
        stmt.setString(parameterIndex, adult.getAddress().getApartment());
    }
}
