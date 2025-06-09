package edu.javacourse.student.service;


import edu.javacourse.student.view.StudentRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:springContext.xml"})
public class StudentServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceTest.class);

    @Autowired
    private StudentService studentService;

    @Test
    public void getStudentInfo_test(){
        StudentRequest request = new StudentRequest();
        request.setLastName("LastName");
        request.setFirstName("FirstName");
        request.setMiddleName("MiddleName");
        request.setPassportSeria("1234");
        request.setPassportNumber("123456");
        request.setPassportDate(LocalDate.of(2010, 01, 01));
        request.setDateOfBirth(LocalDate.of(2000, 01, 01));

        studentService.getStudentInfo(request);
        LOGGER.info("DONE!!!");
    }

}