package edu.javacourse.studentorder;

import edu.javacourse.studentorder.dao.StudentOrderDao;
import edu.javacourse.studentorder.dao.StudentOrderDaoImpl;
import edu.javacourse.studentorder.domain.*;

import java.time.LocalDate;
import java.util.List;


public class SaveStudentOrder {

    public static void main(String[] args) throws Exception {
//        List<Street> streets = new DictionaryDaoImpl().getStreet("пуш");
//        for (Street street : streets) {
//            System.out.println(street.getStreetCode() + " : " + street.getStreetName());
//        }
//
//        List<PassportOffice> p_offices = new DictionaryDaoImpl().getPassportOffices("010020000000");
//        for (PassportOffice office : p_offices) {
//            System.out.println(office.getOfficeId()+ " : " + office.getOfficeAreaId() + " : " + office.getOfficeName());
//        }
//
//        List<RegisterOffice> r_offices = new DictionaryDaoImpl().getRegisterOffices("010010000000");
//        for (RegisterOffice office : r_offices) {
//            System.out.println(office.getOfficeId()+ " : " + office.getOfficeAreaId() + " : " + office.getOfficeName());
//        }

//        List<CountryArea> c1 = new DictionaryDaoImpl().getAreas("");
//        for (CountryArea area : c1) {
//            System.out.println(area.getAreaId() + " : " + area.getAreaName());
//        }
//
//        System.out.println("");
//
//        List<CountryArea> c2 = new DictionaryDaoImpl().getAreas("020000000000");
//        for (CountryArea area : c2) {
//            System.out.println(area.getAreaId() + " : " + area.getAreaName());
//        }
//
//        System.out.println("");
//
//        List<CountryArea> c3 = new DictionaryDaoImpl().getAreas("020020000000");
//        for (CountryArea area : c3) {
//            System.out.println(area.getAreaId() + " : " + area.getAreaName());
//        }
//
//        System.out.println("");
//
//        List<CountryArea> c4 = new DictionaryDaoImpl().getAreas("020020020000");
//        for (CountryArea area : c4) {
//            System.out.println(area.getAreaId() + " : " + area.getAreaName());
//        }
        //Class.forName("org.postgresql.Driver");

        StudentOrderDaoImpl studentOrderDao = new StudentOrderDaoImpl();
        //studentOrderDao.SaveStudentOrder(buildStudentOrder(1));

        List<StudentOrder> studentOrders = studentOrderDao.getStudentOrders();




//        StudentOrder so = buildStudentOrder(10);
//        System.out.println(so.getHusband().getPersonString());
//        long ans = saveStudentOrder(so);
//        System.out.println(ans);
    }

    static long saveStudentOrder(StudentOrder studentOrder) {
        long answer = 199;
        System.out.println("hLastName = " + studentOrder.getStudentOrderId());
        return answer;
    }

    public static StudentOrder buildStudentOrder(long id) {
        StudentOrder so = new StudentOrder();
        so.setStudentOrderId(id);
        so.setStudentOrderStatus(StudentOrder.StudentOrderStatus.START); //TODO: check it
        so.setMarriageCertificateId("" + (123456000 + id));
        so.setMarriageDate(LocalDate.of(2016, 7, 4));
        so.setMarriageOffice(new RegisterOffice(1, "", ""));

        Address address = new Address(new Street(1, "ул. Иванова"), "123", "12", "", "142");
        PassportOffice passportOffice = new PassportOffice(1, "010001010", "Office name");
        Adult husband = new Adult("Петров", "Виктор", "Сергеевич", LocalDate.of(1997, 8, 24));
        husband.setPassportSeria("" + (1000 + id));
        husband.setPassportNumber("" + (100000 + id));
        husband.setIssueDate(LocalDate.of(2017, 9, 15));
        husband.setIssueDepartment(passportOffice);
        husband.setStudentId("" + (100000 + id));
        husband.setAddress(address);
        husband.setUniversity(new University(1, "First University"));

        Adult wife = new Adult("Петрова", "Вероника", "Алексеевна", LocalDate.of(1998, 3, 12));
        wife.setPassportSeria("" + (200 + id));
        wife.setPassportNumber("" + (200000 + id));
        wife.setIssueDate(LocalDate.of(2018, 4, 5));
        wife.setIssueDepartment(passportOffice);
        wife.setStudentId("" + (200000 + id));
        wife.setAddress(address);
        wife.setUniversity(new University(2, "Second University"));

        RegisterOffice r_office =  new RegisterOffice(1, "", "");

        Child child1 = new Child("Петрова", "Ирина", "Алексеевна", LocalDate.of(2018, 3, 12));
        child1.setCertificateNumber("" + (300000 + id));
        child1.setIssueDate(LocalDate.of(2018, 4, 5));
        child1.setIssueDepartment(r_office);
        child1.setAddress(address);

        Child child2 = new Child("Петров", "Игорь", "Алексеевич", LocalDate.of(2018, 3, 12));
        child2.setCertificateNumber("" + (300000 + id));
        child2.setIssueDate(LocalDate.of(2018, 4, 5));
        child2.setIssueDepartment(r_office);
        child2.setAddress(address);


        so.setHusband(husband);
        so.setWife(wife);
        so.addChild(child1);
        so.addChild(child2);

        return so;
    }

}