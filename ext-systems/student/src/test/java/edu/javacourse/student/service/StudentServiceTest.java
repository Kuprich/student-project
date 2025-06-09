package edu.javacourse.student.service;


import edu.javacourse.student.domain.Student;
import edu.javacourse.student.view.StudentRequest;
import edu.javacourse.student.view.StudentResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:springContext.xml"})
public class StudentServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceTest.class);

    @Autowired
    private StudentService studentService;

    @Test
    @Transactional
    public void getStudentInfo_test(){
        StudentRequest request = new StudentRequest();
        request.setLastName("Last");
        request.setFirstName("First");
        request.setMiddleName("Middle");
        request.setPassportSeria("1234");
        request.setPassportNumber("123456");
        request.setPassportDate(LocalDate.of(2014, 10, 10));
        request.setDateOfBirth(LocalDate.of(2000, 01, 02));

        List<StudentResponse> studentInfo = studentService.getStudentInfo(request);
        //List<Student> sts = studentService.getStudents();
        //System.out.println("-------------->" + sts.size());
        LOGGER.info("DONE!!!");
    }

}