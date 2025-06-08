package edu.javacourse.register.dao;

import edu.javacourse.register.domain.MarriageCertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MarriageDao extends JpaRepository<MarriageCertificate, Long> {
    List<MarriageCertificate> findByNumber(String number);
    List<MarriageCertificate> findByNum(@Param("number") String number);
}
