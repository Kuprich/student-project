package edu.javacourse.student.service;

import edu.javacourse.student.domain.PassportOffice;
import edu.javacourse.student.repository.PassportOfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PassportOfficeService {
    @Autowired
    private PassportOfficeRepository repository;

    public Optional<PassportOffice> getPassportOfficeById(long id) {
        return repository.findById(id);
    }
}
