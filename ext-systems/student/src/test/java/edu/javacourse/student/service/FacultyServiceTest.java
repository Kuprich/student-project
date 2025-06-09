package edu.javacourse.student.service;

import edu.javacourse.student.domain.Faculty;
import edu.javacourse.student.view.StudentRequest;
import edu.javacourse.student.view.StudentResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:springContext.xml"})
public class FacultyServiceTest {

    @Autowired
    private FacultyService facultyService;

    @Test
    @Transactional
    public void getFaculties_test() {
        List<Faculty> faculties = facultyService.getFaculties();
    }

}