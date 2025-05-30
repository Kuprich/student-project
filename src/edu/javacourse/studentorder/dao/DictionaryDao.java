package edu.javacourse.studentorder.dao;

import edu.javacourse.studentorder.domain.Street;
import edu.javacourse.studentorder.exception.DaoException;

import java.sql.SQLException;
import java.util.List;

public interface DictionaryDao {
    public List<Street> getStreet(String pattern) throws DaoException;
}
