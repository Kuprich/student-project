package edu.javacourse.student.service;

import edu.javacourse.student.domain.RegisterOffice;
import edu.javacourse.student.repository.RegisterOfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterOfficeService {
    @Autowired
    private RegisterOfficeRepository repository;

    public Optional<RegisterOffice> getRegisterOfficeById(long id) {
        return repository.findById(id);
    }
}
