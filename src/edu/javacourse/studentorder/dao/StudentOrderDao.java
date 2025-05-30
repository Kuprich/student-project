package edu.javacourse.studentorder.dao;

import edu.javacourse.studentorder.domain.StudentOrder;
import edu.javacourse.studentorder.exception.DaoException;

public interface StudentOrderDao {
    public Long SaveStudentOrder(StudentOrder so) throws DaoException;
}


