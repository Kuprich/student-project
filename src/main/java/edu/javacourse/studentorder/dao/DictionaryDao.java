package edu.javacourse.studentorder.dao;

import edu.javacourse.studentorder.domain.*;
import edu.javacourse.studentorder.exception.DaoException;

import java.util.List;

public interface DictionaryDao {
    public List<Street> getStreets(String pattern) throws DaoException;
    public List<PassportOffice> getPassportOffices(String officeAreaId) throws DaoException;
    public List<RegisterOffice> getRegisterOffices(String officeAreaId) throws DaoException;
    public List<CountryArea> getAreas(String areaId) throws  DaoException;
}

