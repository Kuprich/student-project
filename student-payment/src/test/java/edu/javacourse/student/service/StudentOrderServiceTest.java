package edu.javacourse.student.service;

import edu.javacourse.student.config.AppConfig;
import edu.javacourse.student.domain.Person;
import edu.javacourse.student.domain.StudentOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppConfig.class})
class StudentOrderServiceTest {

    @Autowired
    private StudentOrderService so_service;

    @Test
    public void saveStudentOrder() {
        StudentOrder so = new StudentOrder();
        so.setHusband(buildPerson(true));
        so.setWife(buildPerson(false));
        StudentOrder so_result = so_service.saveStudentOrder(so);

        assertTrue(so_service.getStudentOrderById(so_result.getStudentOrderId()).isPresent());
    }

    @Test
    public void getStudentOrders() {
        List<StudentOrder> so = so_service.getStudentOrders();
        assertTrue(so.size() > 0);
    }

    private Person buildPerson(boolean isHusband) {
        Person p = new Person();
        if (isHusband) {
            p.setSurname("Иванов");
            p.setGivenName("Иван");
            p.setPatronymic("Иванович");
            p.setDateOfBirth(LocalDate.of(2000, 04, 06));
        }
        else {
            p.setSurname("Иванова");
            p.setGivenName("Ирина");
            p.setPatronymic("Ивановна");
            p.setDateOfBirth(LocalDate.of(2000, 05, 07));
        }
        return p;
    }


}