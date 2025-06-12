package edu.javacourse.student.repository;

import edu.javacourse.student.domain.StudentChild;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentChildRepository extends JpaRepository<StudentChild, Long> {
}
