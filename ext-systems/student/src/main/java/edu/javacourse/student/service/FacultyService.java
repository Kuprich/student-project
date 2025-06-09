package edu.javacourse.student.service;

import edu.javacourse.student.dao.FacultyRepository;
import edu.javacourse.student.domain.Faculty;
import edu.javacourse.student.domain.University;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyService {
    @Autowired
    private FacultyRepository facultyRepository;

    @Transactional(readOnly = true)
    public List<Faculty> getFaculties() {
        return facultyRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Faculty> getFacultyById(Long facultyId) {
        return facultyRepository.findById(facultyId);
    }
}
