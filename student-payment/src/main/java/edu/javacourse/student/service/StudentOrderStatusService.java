package edu.javacourse.student.service;

import edu.javacourse.student.domain.StudentOrder;
import edu.javacourse.student.domain.StudentOrderStatus;
import edu.javacourse.student.repository.StudentOrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentOrderStatusService {
    @Autowired
    private StudentOrderStatusRepository repository;

    public Optional<StudentOrderStatus> getStudentOrderStatusById(Long id) {
        return repository.findById(id);
    }
}


