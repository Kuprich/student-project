package edu.javacourse.student.service;

import edu.javacourse.student.config.AppConfig;
import edu.javacourse.student.domain.StudentOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppConfig.class})
class StudentOrderServiceTest {

    @Autowired
    private StudentOrderService so_service;

    @Test
    public void getStudentOrders() {
        List<StudentOrder> so = so_service.getStudentOrders();
        assertTrue(so.size() > 0);
    }
}