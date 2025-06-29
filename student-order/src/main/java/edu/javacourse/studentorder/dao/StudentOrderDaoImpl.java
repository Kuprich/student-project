package edu.javacourse.studentorder.dao;

import edu.javacourse.studentorder.config.Config;
import edu.javacourse.studentorder.domain.*;
import edu.javacourse.studentorder.exception.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentOrderDaoImpl implements StudentOrderDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentOrderDaoImpl.class);

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

    private static final String SELECT_STUDENT_ORDERS = "SELECT so.*, ro.r_office_area_id, ro.r_office_name, " +
            "po_h.p_office_area_id h_p_office_area_id, po_h.p_office_name h_p_office_name, " +
            "po_w.p_office_area_id w_p_office_area_id, po_w.p_office_name w_p_office_name " +
            "FROM jc_student_order so " +
            "INNER JOIN jc_register_office ro ON ro.r_office_id = so.register_office_id " +
            "INNER JOIN jc_passport_office po_h ON po_h.p_office_id = so.h_passport_office_id " +
            "INNER JOIN jc_passport_office po_w ON po_w.p_office_id = so.w_passport_office_id " +
            "WHERE student_order_status = ? " +
            "LIMIT ?";

    private static final String SELECT_CHILDREN = "SELECT sch.* , ro.r_office_area_id, ro.r_office_name " +
            "FROM jc_student_child sch " +
            "INNER JOIN jc_register_office ro ON ro.r_office_id = sch.c_register_office_id " +
            "WHERE sch.student_order_id IN ";

    private static final String SELECT_STUDENT_ORDERS_ALL = "SELECT so.*, ro.r_office_area_id, ro.r_office_name,  " +
            "po_h.p_office_area_id h_p_office_area_id, po_h.p_office_name h_p_office_name,  " +
            "po_w.p_office_area_id w_p_office_area_id, po_w.p_office_name w_p_office_name,  " +
            "sch.* , ro_ch.r_office_area_id, ro_ch.r_office_name " +
            "FROM jc_student_order so  " +
            "INNER JOIN jc_register_office ro ON ro.r_office_id = so.register_office_id  " +
            "INNER JOIN jc_passport_office po_h ON po_h.p_office_id = so.h_passport_office_id  " +
            "INNER JOIN jc_passport_office po_w ON po_w.p_office_id = so.w_passport_office_id  " +
            "INNER JOIN jc_student_child sch ON sch.student_order_id = so.student_order_id " +
            "INNER JOIN jc_register_office ro_ch ON ro_ch.r_office_id = sch.c_register_office_id " +
            "WHERE student_order_status = ? " +
            "LIMIT ?";

    private Connection getConnection() throws SQLException {
        return ConnectionBuilder.getConnection();
    }

    @Override
    public Long SaveStudentOrder(StudentOrder so) throws DaoException {
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(INSERT_ORDER, new String[]{"student_order_id"})) {
            con.setAutoCommit(false);
            LOGGER.info("SO:{}", so);
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
                con.commit();
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                long studentOrderId = (generatedKeys.next()) ? generatedKeys.getLong("student_order_id") : -1;

                if (studentOrderId != -1)
                    saveChildren(con, so, studentOrderId);

                return -1L;
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
                con.rollback();
                throw e;
            }


        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DaoException(e);
        }
    }

    @Override
    public List<StudentOrder> getStudentOrders() throws DaoException {
        return getStudentOrdersOneSelect();
//        return getStudentOrdersTwoSelect();
    }

    private List<StudentOrder> getStudentOrdersTwoSelect() {
        List<StudentOrder> result = new ArrayList<>();

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(SELECT_STUDENT_ORDERS)) {
            stmt.setInt(1, StudentOrder.StudentOrderStatus.START.ordinal());
            stmt.setInt(1, Integer.parseInt(Config.getProperty(Config.DB_LIMIT)));

            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                StudentOrder studentOrder = getStudentOrder(resultSet);
                result.add(studentOrder);
            }

            findChildren(con, result);

            resultSet.close();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }

        return result;
    }

    private List<StudentOrder> getStudentOrdersOneSelect() {
        List<StudentOrder> result = new ArrayList<>();

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(SELECT_STUDENT_ORDERS_ALL)) {
            stmt.setInt(1, StudentOrder.StudentOrderStatus.START.ordinal());
            stmt.setInt(2, Integer.parseInt(Config.getProperty(Config.DB_LIMIT)));
            ResultSet resultSet = stmt.executeQuery();

            Map<Long, StudentOrder> map = new HashMap<>();
            int count = 0;
            while (resultSet.next()) {
                long soId = resultSet.getLong("student_order_id");
                if (!map.containsKey(soId)) {
                    StudentOrder studentOrder = getStudentOrder(resultSet);
                    result.add(studentOrder);
                    map.put(soId, studentOrder);
                }
                StudentOrder studentOrder = map.get(soId);
                studentOrder.addChild(fillChild(resultSet));
                count++;
            }
            if (count >= Integer.parseInt(Config.getProperty(Config.DB_LIMIT))) {
                result.remove(result.size() - 1);
            }

            resultSet.close();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }

        return result;
    }

    private StudentOrder getStudentOrder(ResultSet resultSet) throws SQLException {
        StudentOrder studentOrder = new StudentOrder();
        fillStudentOrder(resultSet, studentOrder);
        fillMarriage(resultSet, studentOrder);
        studentOrder.setHusband(fillAdult(resultSet, "h_"));
        studentOrder.setWife(fillAdult(resultSet, "w_"));
        return studentOrder;
    }

    private void findChildren(Connection con, List<StudentOrder> result) throws SQLException {
        String soIds = "(" + result.stream()
                .map(so -> "" + so.getStudentOrderId())
                .collect(Collectors.joining(",")) + ")";
        Map<Long, StudentOrder> map = result.stream()
                .collect(Collectors.toMap(so -> so.getStudentOrderId(), so -> so));

        try (PreparedStatement stmt = con.prepareStatement(SELECT_CHILDREN + soIds)) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                //Child hs = fillChild(resultSet);
                map.get(resultSet.getLong("student_order_id"))
                        .addChild(fillChild(resultSet));
            }
        }
    }

    private Child fillChild(ResultSet resultSet) throws SQLException {
        Child child = new Child();
        child.setSurName(resultSet.getString("c_sur_name"));
        child.setGivenName(resultSet.getString("c_given_name"));
        child.setPatronymic(resultSet.getString("c_patronymic"));
        child.setDateOfBirth(resultSet.getDate("c_date_of_birth").toLocalDate());
        child.setCertificateNumber(resultSet.getString("c_certificate_number"));

        RegisterOffice registerOffice = new RegisterOffice();
        registerOffice.setOfficeId(resultSet.getLong("c_register_office_id"));
        registerOffice.setOfficeAreaId(resultSet.getString("r_office_area_id"));
        registerOffice.setOfficeName(resultSet.getString("r_office_name"));

        child.setIssueDepartment(registerOffice);

        Street street = new Street();
        street.setStreetCode(resultSet.getLong("c_street_code"));
        street.setStreetName("");

        Address address = new Address();
        address.setStreet(street);
        address.setPostCode(resultSet.getString("c_post_index"));
        address.setExtension(resultSet.getString("c_extension"));
        address.setApartment(resultSet.getString("c_apartment"));
        address.setBuilding(resultSet.getString("c_building"));

        child.setAddress(address);

        child.setIssueDate(resultSet.getDate("c_certificate_date").toLocalDate());

        return child;


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

        Long officeId = resultSet.getLong(prefix + "passport_office_id");
        String officeAreaId = resultSet.getString(prefix + "p_office_area_id");
        String officeName = resultSet.getString(prefix + "p_office_name");

        result.setIssueDepartment(new PassportOffice(officeId, officeAreaId, officeName));

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
        long registerOfficeId = resultSet.getLong("register_office_id");
        String officeAreaId = resultSet.getString("r_office_area_id");
        String officeName = resultSet.getString("r_office_name");
        studentOrder.setMarriageOffice(new RegisterOffice(registerOfficeId, officeAreaId, officeName));
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
            con.commit();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
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
