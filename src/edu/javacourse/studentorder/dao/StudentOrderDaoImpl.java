package edu.javacourse.studentorder.dao;

import edu.javacourse.studentorder.config.Config;
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
            stmt.setString(3, so.getHusband().getSurName());
            stmt.setString(4, so.getHusband().getGivenName());
            stmt.setString(5, so.getHusband().getPatronymic());
            stmt.setDate(6, Date.valueOf(so.getHusband().getDateOfBirth()));
            stmt.setString(7, so.getHusband().getPassportSeria());
            stmt.setString(8, so.getHusband().getPassportNumber());
            stmt.setDate(9, Date.valueOf(so.getHusband().getIssueDate()));
            stmt.setLong(10, so.getHusband().getIssueDepartment().getOfficeId());
            stmt.setString(11, so.getHusband().getAddress().getPostCode());
            stmt.setLong(12, so.getHusband().getAddress().getStreet().getStreetCode());
            stmt.setString(13, so.getHusband().getAddress().getBuilding());
            stmt.setString(14, so.getHusband().getAddress().getExtension());
            stmt.setString(15, so.getHusband().getAddress().getApartment());

            // wife
            stmt.setString(16, so.getWife().getSurName());
            stmt.setString(17, so.getWife().getGivenName());
            stmt.setString(18, so.getWife().getPatronymic());
            stmt.setDate(19, Date.valueOf(so.getWife().getDateOfBirth()));
            stmt.setString(20, so.getWife().getPassportSeria());
            stmt.setString(21, so.getWife().getPassportNumber());
            stmt.setDate(22, Date.valueOf(so.getWife().getIssueDate()));
            stmt.setLong(23, so.getWife().getIssueDepartment().getOfficeId());
            stmt.setString(24, so.getWife().getAddress().getPostCode());
            stmt.setLong(25, so.getWife().getAddress().getStreet().getStreetCode());
            stmt.setString(26, so.getWife().getAddress().getBuilding());
            stmt.setString(27, so.getWife().getAddress().getExtension());
            stmt.setString(28, so.getWife().getAddress().getApartment());

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
}
