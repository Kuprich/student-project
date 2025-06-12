package edu.javacourse.student.service;

import edu.javacourse.student.domain.University;
import edu.javacourse.student.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UniversityService {
    @Autowired
    private UniversityRepository repository;

    @Transactional
    public Optional<University> getUniversityById(Long id) {
        return repository.findById(id);
    }
}
