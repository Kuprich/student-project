package edu.javacourse.student.service;

import edu.javacourse.student.config.AppConfig;
import edu.javacourse.student.domain.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppConfig.class})
class StudentOrderServiceTest {

    @Autowired
    private StudentOrderService soService;
    @Autowired
    private StreetService streetService;
    @Autowired
    private StudentOrderStatusService sosService;
    @Autowired
    private PassportOfficeService poService;
    @Autowired
    private UniversityService uService;
    @Autowired
    private RegisterOfficeService roService;


    @Test
    public void getStreet() {
        assertTrue(streetService.getStreetById(1L).isPresent());
    }

    @Test
    public void saveStudentOrder() {
        StudentOrder so = new StudentOrder();
        so.setStudentOrderDate(LocalDateTime.of(2010, 01, 02, 0, 0));
        so.setStatus(sosService.getStudentOrderStatusById(1L).get());
        so.setHusband(buildPerson(true));
        so.setWife(buildPerson(false));
        so.setCertificateNumber("CERT_N");
        so.setRegisterOffice(roService.getRegisterOfficeById(1L).get());
        so.setMarriageDate(LocalDate.now());

        StudentOrder so_result = soService.saveStudentOrder(so);

        assertTrue(soService.getStudentOrderById(so_result.getStudentOrderId()).isPresent());
    }

    @Test
    public void getStudentOrders() {
        List<StudentOrder> so = soService.getStudentOrders();
        assertTrue(so.size() > 0);
    }



    private Adult buildPerson(boolean isHusband) {
        Adult p = new Adult();
        p.setPassportSeria("P_SERIA");
        p.setPassportNumber("P_NUM");
        p.setStudentNumber("12345");
        p.setUniversity(uService.getUniversityById(1L).get());
        p.setPassportOffice(poService.getPassportOfficeById(1L).get());
        if (isHusband) {
            p.setSurname("Иванов");
            p.setGivenName("Иван");
            p.setPatronymic("Иванович");
            p.setDateOfBirth(LocalDate.of(2000, 04, 06));
            p.setIssueDate(LocalDate.of(2014, 04, 30));
        }
        else {
            p.setSurname("Иванова");
            p.setGivenName("Ирина");
            p.setPatronymic("Ивановна");
            p.setDateOfBirth(LocalDate.of(2000, 05, 07));
            p.setIssueDate(LocalDate.of(2014, 05, 30));
        }
        Address address = new Address();
        address.setApartment("Apartment");
        address.setBuilding("Building");
        address.setExtension("Ext");
        address.setPostCode("PC");
        address.setStreet(streetService.getStreetById(2L).get());
        p.setAddress(address);

        return p;
    }


}