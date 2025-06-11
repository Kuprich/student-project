package edu.javacourse.student.repository;

import edu.javacourse.student.domain.StudentOrder;
import edu.javacourse.student.domain.StudentOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentOrderStatusRepository extends JpaRepository<StudentOrderStatus, Long> {
}
