package edu.javacourse.student.service;

import edu.javacourse.student.domain.Street;
import edu.javacourse.student.repository.StreetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StreetService {

    @Autowired
    private StreetRepository streetRepository;

    public Optional<Street> getStreetById(Long id) {
        return streetRepository.findById(id);
    }
}
