package edu.javacourse.student.repository;

import edu.javacourse.student.domain.CountryArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryAreaRepository extends JpaRepository<CountryArea, String> {
}



