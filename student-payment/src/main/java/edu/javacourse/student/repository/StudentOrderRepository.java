package edu.javacourse.student.repository;

import edu.javacourse.student.domain.StudentOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentOrderRepository extends JpaRepository<StudentOrder, Long> {
}

