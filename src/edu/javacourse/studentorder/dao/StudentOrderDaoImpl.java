package edu.javacourse.studentorder.dao;

import edu.javacourse.studentorder.config.Config;
import edu.javacourse.studentorder.domain.*;
import edu.javacourse.studentorder.exception.DaoException;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StudentOrderDaoImpl implements StudentOrderDao {

    private static final String INSERT_ORDER = "INSERT INTO jc_student_order(" +
            "student_order_status, student_oder_date, " +
            "h_sur_name, h_given_name, h_patronymic, h_date_of_birth, h_passport_seria, h_passport_number, h_passport_date, h_passport_office_id, h_post_index, h_street_code, h_building, h_extension, h_apartment, h_university_id, h_student_number, " +
            "w_sur_name, w_given_name, w_patronymic, w_date_of_birth, w_passport_seria, w_passport_number, w_passport_date, w_passport_office_id, w_post_index, w_street_code, w_building, w_extension, w_apartment, w_university_id, w_student_number, " +
            "certificate_id, register_office_id, marriage_date) " +
            "VALUES (?, ?, " +
            "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
            "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
            "?, ?, ?);";

    private static final String INSERT_CHILD = "INSERT INTO public.jc_student_child(" +
            "student_order_id, c_sur_name, c_given_name, c_patronymic, c_date_of_birth, c_certificate_number, c_certificate_date, c_register_office_id, c_post_index, c_street_code, c_building, c_extension, c_apartment)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String SELECT_STUDENT_ORDERS = "SELECT * FROM public.jc_student_order " +
            "WHERE student_order_status = 0 " +
            "ORDER BY student_oder_date;";


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
                int idx = 1;
                stmt.setInt(idx++, StudentOrder.StudentOrderStatus.START.ordinal());
                stmt.setTimestamp(idx++, Timestamp.valueOf(LocalDateTime.now()));

                // husband
                idx = SetParamsForAdult(stmt, so.getHusband(), idx);

                // wife
                idx = SetParamsForAdult(stmt, so.getWife(), idx);

                // register
                stmt.setString(idx++, so.getMarriageCertificateId());
                stmt.setLong(idx++, so.getMarriageOffice().getOfficeId());
                stmt.setDate(idx++, Date.valueOf(so.getMarriageDate()));

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

    @Override
    public List<StudentOrder> getStudentOrders() throws DaoException {

        List<StudentOrder> result = new ArrayList<>();

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(SELECT_STUDENT_ORDERS)) {

            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                StudentOrder studentOrder = new StudentOrder();
                fillStudentOrder(resultSet, studentOrder);
                fillMarriage(resultSet, studentOrder);
                studentOrder.setHusband(fillAdult(resultSet, "h_"));
                studentOrder.setWife(fillAdult(resultSet, "w_"));
                result.add(studentOrder);
            }

            resultSet.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;

    }

    private Adult fillAdult(ResultSet resultSet, String prefix) throws SQLException {
        Adult result = new Adult();
        result.setSurName(resultSet.getString(prefix + "sur_name"));
        result.setGivenName(resultSet.getString(prefix + "given_name"));
        result.setPatronymic(resultSet.getString(prefix + "patronymic"));
        result.setDateOfBirth(resultSet.getDate(prefix + "date_of_birth").toLocalDate());
        result.setPassportSeria(resultSet.getString(prefix + "passport_seria"));
        result.setPassportNumber(resultSet.getString(prefix + "passport_number"));
        result.setIssueDate(resultSet.getDate(prefix + "passport_date").toLocalDate());
        result.setIssueDepartment(new PassportOffice(resultSet.getLong(prefix + "passport_office_id"), "Fake", "Fake"));

        Address address = new Address();
        address.setPostCode(resultSet.getString(prefix + "post_index"));
        address.setBuilding(resultSet.getString(prefix + "building"));
        address.setApartment(resultSet.getString(prefix + "apartment"));
        address.setExtension(resultSet.getString(prefix + "extension"));

        Street street = new Street();
        street.setStreetCode(resultSet.getLong(prefix + "street_code"));

        address.setStreet(street);
        result.setAddress(address);

        result.setUniversity(new University(resultSet.getLong(prefix + "university_id"), "Fake university"));
        result.setStudentId(resultSet.getString(prefix + "student_number"));

        return result;
    }

    private void fillMarriage(ResultSet resultSet, StudentOrder studentOrder) throws SQLException {
        studentOrder.setMarriageCertificateId(resultSet.getString("certificate_id"));
        long moId = resultSet.getLong("register_office_id");
        studentOrder.setMarriageOffice(new RegisterOffice(moId, "Fake ID", "Fake office name"));
        studentOrder.setMarriageDate(resultSet.getDate("marriage_date").toLocalDate());
    }

    private void fillStudentOrder(ResultSet resultSet, StudentOrder studentOrder) throws SQLException {
        studentOrder.setStudentOrderId(resultSet.getLong("student_order_id"));
        studentOrder.setStudentOrderStatus(StudentOrder.StudentOrderStatus.Get(resultSet.getInt("student_order_status")));
        studentOrder.setStudentOrderDate(resultSet.getTimestamp("student_oder_date").toLocalDateTime());
    }

    private void saveChildren(Connection con, StudentOrder studentOrder, long studentOrderId) {

        try (PreparedStatement stmt = con.prepareStatement(INSERT_CHILD)) {
            for (Child child : studentOrder.getChildren()) {
                stmt.setLong(1, studentOrderId);
                setParamsForChild(stmt, child, 2);
                stmt.addBatch();
            }
            stmt.executeBatch();
        } catch (SQLException e) {
            new DaoException(e);
        }
    }

    private static int SetParamsForAdult(PreparedStatement stmt, Adult adult, int idx) throws SQLException {
        idx = setParamsForPerson(stmt, adult, idx);
        stmt.setString(idx++, adult.getPassportSeria());
        stmt.setString(idx++, adult.getPassportNumber());
        stmt.setDate(idx++, Date.valueOf(adult.getIssueDate()));
        stmt.setLong(idx++, adult.getIssueDepartment().getOfficeId());
        idx = setParamsForAddress(stmt, adult, idx);
        idx = setParamsForUniversity(stmt, adult, idx);
        return idx;
    }

    private static int setParamsForPerson(PreparedStatement stmt, Person person, int idx) throws SQLException {
        stmt.setString(idx++, person.getSurName());
        stmt.setString(idx++, person.getGivenName());
        stmt.setString(idx++, person.getPatronymic());
        stmt.setDate(idx++, Date.valueOf(person.getDateOfBirth()));
        return idx;
    }

    private static int setParamsForAddress(PreparedStatement stmt, Person person, int idx) throws SQLException {
        stmt.setString(idx++, person.getAddress().getPostCode());
        stmt.setLong(idx++, person.getAddress().getStreet().getStreetCode());
        stmt.setString(idx++, person.getAddress().getBuilding());
        stmt.setString(idx++, person.getAddress().getExtension());
        stmt.setString(idx++, person.getAddress().getApartment());
        return idx;
    }

    private static int setParamsForUniversity(PreparedStatement stmt, Adult adult, int idx) throws SQLException {
        stmt.setLong(idx++, adult.getUniversity().getUniversityId());
        stmt.setString(idx++, adult.getUniversity().getUniversityName());
        return idx;
    }

    private static void setParamsForChild(PreparedStatement stmt, Child child, int idx) throws SQLException {
        idx = setParamsForPerson(stmt, child, idx);
        stmt.setString(idx++, child.getCertificateNumber());
        stmt.setDate(idx++, Date.valueOf(child.getIssueDate()));
        stmt.setLong(idx++, child.getIssueDepartment().getOfficeId());
        setParamsForAddress(stmt, child, idx);
    }
}
