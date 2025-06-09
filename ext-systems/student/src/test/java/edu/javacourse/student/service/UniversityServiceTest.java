package edu.javacourse.student.service;


import edu.javacourse.student.domain.University;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:springContext.xml"})
public class UniversityServiceTest {

    @Autowired
    UniversityService universityService;

    @Test
    @Transactional
    public void getUniversities_test() {
        List<University> result = universityService.getUniversities();
    }

    @Test
    public void getUniversities_with_faculties_test() {
        List<University> result = universityService.getAllWithFaculties();
    }
}