package edu.javacourse.studentorder.dao;

import edu.javacourse.studentorder.config.Config;
import edu.javacourse.studentorder.domain.Adult;
import edu.javacourse.studentorder.domain.Child;
import edu.javacourse.studentorder.domain.Person;
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

    private static final String INSERT_CHILD = "INSERT INTO public.jc_student_child(" +
            "student_order_id, c_sur_name, c_given_name, c_patronymic, c_date_of_birth, c_certificate_number, c_certificate_date, c_register_office_id, c_post_index, c_street_code, c_building, c_extension, c_apartment)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

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

            con.setAutoCommit(false);
            try {
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
                long studentOrderId = (generatedKeys.next()) ? generatedKeys.getLong("student_order_id") : -1;

                if (studentOrderId != -1)
                    saveChildren(con, so, studentOrderId);

                return -1L;
            } catch (SQLException e) {
                con.rollback();
                throw e;
            }


        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private void saveChildren(Connection con, StudentOrder studentOrder, long studentOrderId) {
        try (PreparedStatement stmt = con.prepareStatement(INSERT_CHILD)) {
            for(Child child : studentOrder.getChildren()){
                stmt.setLong(1, studentOrderId);
                setParamsForChild(stmt, child, 2);
                stmt.addBatch();
            }
            stmt.executeBatch();
        } catch (SQLException e) {
            new DaoException(e);
        }
    }

    private static void SetParamsForAdult(PreparedStatement stmt, Adult adult, int idx) throws SQLException {
        idx = setParamsForPerson(stmt, adult, idx);
        stmt.setString(idx++, adult.getPassportSeria());
        stmt.setString(idx++, adult.getPassportNumber());
        stmt.setDate(idx++, Date.valueOf(adult.getIssueDate()));
        stmt.setLong(idx++, adult.getIssueDepartment().getOfficeId());
        setParamsForAddress(stmt, adult, idx);
    }

    private static int setParamsForPerson(PreparedStatement stmt, Person person, int idx) throws SQLException {
        stmt.setString(idx++, person.getSurName());
        stmt.setString(idx++, person.getGivenName());
        stmt.setString(idx++, person.getPatronymic());
        stmt.setDate(idx++, Date.valueOf(person.getDateOfBirth()));
        return idx;
    }

    private static void setParamsForAddress(PreparedStatement stmt, Person person, int idx) throws SQLException {
        stmt.setString(idx++, person.getAddress().getPostCode());
        stmt.setLong(idx++, person.getAddress().getStreet().getStreetCode());
        stmt.setString(idx++, person.getAddress().getBuilding());
        stmt.setString(idx++, person.getAddress().getExtension());
        stmt.setString(idx, person.getAddress().getApartment());
    }

    private static void setParamsForChild(PreparedStatement stmt, Child child, int idx) throws SQLException {
        idx = setParamsForPerson(stmt, child, idx);
        stmt.setString(idx++, child.getCertificateNumber());
        stmt.setDate(idx++, Date.valueOf(child.getIssueDate()));
        stmt.setLong(idx++, child.getIssueDepartment().getOfficeId());
        setParamsForAddress(stmt, child, idx);
    }
}
