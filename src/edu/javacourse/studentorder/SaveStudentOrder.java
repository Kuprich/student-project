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
        List<Street> tmp = new DictionaryDaoImpl().getStreet("пуш");
        for (Street street : tmp) {
            System.out.println(street.getStreetCode() + " : " + street.getStreetName());
        }
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

        Adult husband = new Adult("Петров", "Виктор", "Сергеевич", LocalDate.of(1997, 8, 24));
        husband.setPassportSeria("" + (1000 + id));
        husband.setPassportNumber("" + (100000 + id));
        husband.setIssueDate(LocalDate.of(2017, 9, 15));
        husband.setIssueDepartment("Отдел милиции №" + id);
        husband.setStudentId("" + (100000 + id));
        husband.setAddress(address);

        Adult wife = new Adult("Петрова", "Вероника", "Алексеевна", LocalDate.of(1998, 3, 12));
        wife.setPassportSeria("" + (200 + id));
        wife.setPassportNumber("" + (200000 + id));
        wife.setIssueDate(LocalDate.of(2018, 4, 5));
        wife.setIssueDepartment("Отдел милиции №" + id);
        wife.setStudentId("" + (200000 + id));
        wife.setAddress(address);

        Child child1 = new Child("Петрова", "Ирина", "Алексеевна", LocalDate.of(2018, 3, 12));
        child1.setCertificateNumber("" + (300000 + id));
        child1.setIssueDate(LocalDate.of(2018, 4, 5));
        child1.setIssueDepartment("Отдел ЗАГС №" + id);
        child1.setAddress(address);

        Child child2 = new Child("Петров", "Игорь", "Алексеевич", LocalDate.of(2018, 3, 12));
        child2.setCertificateNumber("" + (300000 + id));
        child2.setIssueDate(LocalDate.of(2018, 4, 5));
        child2.setIssueDepartment("Отдел ЗАГС №" + id);
        child2.setAddress(address);


        so.setHusband(husband);
        so.setWife(wife);
        so.addChild(child1);
        so.addChild(child2);

        return so;
    }

}