package edu.javacourse.city.dao;

import edu.javacourse.city.domain.PersonRequest;
import edu.javacourse.city.domain.PersonResponse;
import edu.javacourse.city.extension.PersonCheckException;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class PersonCheckDaoTest {

    @org.junit.Test
    public void checkPerson_with_Extension_and_Apartment() {
        PersonRequest request = new PersonRequest();

        request.setSurName("Иванов");
        request.setGivenName("Иван");
        request.setPatronymic("Иванович");
        request.setDateOfBirth(LocalDate.of(1980, 05, 15));
        request.setStreetCode(1);
        request.setBuilding("10");
        request.setExtension("А");
        request.setApartment("15");

        PersonCheckDao checker = new PersonCheckDao();
        try {
            PersonResponse response = checker.checkPerson(request);
            Assert.assertTrue(response.isRegistered());
            Assert.assertFalse(response.isTemporal());
        } catch (PersonCheckException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void checkPerson_without_Extension_and_Apartment() {
        PersonRequest request = new PersonRequest();

        request.setSurName("Иванов");
        request.setGivenName("Иван");
        request.setPatronymic("Иванович");
        request.setDateOfBirth(LocalDate.of(1980, 05, 15));
        request.setStreetCode(1);
        request.setBuilding("10");

        PersonCheckDao checker = new PersonCheckDao();
        try {
            PersonResponse response = checker.checkPerson(request);
            Assert.assertFalse(response.isRegistered());
            Assert.assertFalse(response.isTemporal());
        } catch (PersonCheckException e) {
            throw new RuntimeException(e);
        }
    }
}