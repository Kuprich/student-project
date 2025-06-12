package edu.javacourse.student.service;

import edu.javacourse.student.domain.StudentChild;
import edu.javacourse.student.repository.StudentChildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentChildService {
    @Autowired
    private StudentChildRepository repository;

    @Transactional
    public Optional<StudentChild> getStudentChildById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public StudentChild save(StudentChild studentChild) {
        return repository.save(studentChild);
    }

    @Transactional
    public List<StudentChild> getChildren() {
        return repository.findAll();
    }

}
