package edu.javacourse.studentorder;

import edu.javacourse.studentorder.dao.DictionaryDao;
import edu.javacourse.studentorder.dao.DictionaryDaoImpl;
import edu.javacourse.studentorder.domain.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;


public class SaveStudentOrder {

    public static void main(String[] args) throws Exception {
        List<Street> streets = new DictionaryDaoImpl().getStreet("пуш");
        for (Street street : streets) {
            System.out.println(street.getStreetCode() + " : " + street.getStreetName());
        }

        List<PassportOffice> p_offices = new DictionaryDaoImpl().getPassportOffices("010020000000");
        for (PassportOffice office : p_offices) {
            System.out.println(office.getOfficeId()+ " : " + office.getOfficeAreaId() + " : " + office.getOfficeName());
        }

        List<RegisterOffice> r_offices = new DictionaryDaoImpl().getRegisterOffices("010010000000");
        for (RegisterOffice office : r_offices) {
            System.out.println(office.getOfficeId()+ " : " + office.getOfficeAreaId() + " : " + office.getOfficeName());
        }

//        List<Street> tmp = new DictionaryDaoImpl().getStreet("пуш");
//        for (Street street : tmp) {
//            System.out.println(street.getStreetCode() + " : " + street.getStreetName());
//        }
        //Class.forName("org.postgresql.Driver");




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

    static StudentOrder buildStudentOrder(long id) {
        StudentOrder so = new StudentOrder();
        so.setStudentOrderId(id);
        so.setMarriageCertificateId("" + (123456000 + id));
        so.setMarriageDate(LocalDate.of(2016, 7, 4));
        so.setMarriageOffice("Отдел ЗАГС");

        Address address = new Address(new Street(1, "ул. Иванова"), "Заневский пр.", "12", "", "142");
        PassportOffice passportOffice = new PassportOffice(1, "010001010", "Office name");
        Adult husband = new Adult("Петров", "Виктор", "Сергеевич", LocalDate.of(1997, 8, 24));
        husband.setPassportSeria("" + (1000 + id));
        husband.setPassportNumber("" + (100000 + id));
        husband.setIssueDate(LocalDate.of(2017, 9, 15));
        husband.setIssueDepartment(passportOffice);
        husband.setStudentId("" + (100000 + id));
        husband.setAddress(address);

        Adult wife = new Adult("Петрова", "Вероника", "Алексеевна", LocalDate.of(1998, 3, 12));
        wife.setPassportSeria("" + (200 + id));
        wife.setPassportNumber("" + (200000 + id));
        wife.setIssueDate(LocalDate.of(2018, 4, 5));
        wife.setIssueDepartment(passportOffice);
        wife.setStudentId("" + (200000 + id));
        wife.setAddress(address);

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