package edu.javacourse.studentorder.dao;

import edu.javacourse.studentorder.domain.*;
import edu.javacourse.studentorder.exception.DaoException;

import java.util.List;

public interface DictionaryDao {
    List<Street> getStreets(String pattern) throws DaoException;
    List<PassportOffice> getPassportOffices(String officeAreaId) throws DaoException;
    List<RegisterOffice> getRegisterOffices(String officeAreaId) throws DaoException;
    List<CountryArea> getAreas(String areaId) throws  DaoException;
}

