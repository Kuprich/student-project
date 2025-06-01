package edu.javacourse.studentorder.dao;

import edu.javacourse.studentorder.domain.StudentOrder;
import edu.javacourse.studentorder.exception.DaoException;

import java.util.List;

public interface StudentOrderDao {
    public Long SaveStudentOrder(StudentOrder so) throws DaoException;
    public List<StudentOrder> getStudentOrders() throws DaoException;
}


