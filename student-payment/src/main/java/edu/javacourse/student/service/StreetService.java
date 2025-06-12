package edu.javacourse.student.service;

import edu.javacourse.student.domain.Street;
import edu.javacourse.student.repository.StreetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class StreetService {

    @Autowired
    private StreetRepository streetRepository;

    @Transactional
    public Optional<Street> getStreetById(Long id) {
        return streetRepository.findById(id);
    }
}
