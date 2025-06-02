package edu.javacourse.studentorder.dao;

//import static org.junit.jupiter.api.Assertions.*;

import edu.javacourse.studentorder.domain.CountryArea;
import edu.javacourse.studentorder.domain.PassportOffice;
import edu.javacourse.studentorder.domain.RegisterOffice;
import edu.javacourse.studentorder.domain.Street;
import edu.javacourse.studentorder.exception.DaoException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DictionaryDaoImplTest {

    private static final Logger logger = LoggerFactory.getLogger(DictionaryDaoImplTest.class);

    @BeforeClass
    public static void startUp() throws Exception {
        DbInitializer.initDb();
    }

    @Test
    public void testStreet() throws DaoException {
        logger.info("info");
        List<Street> streets = new DictionaryDaoImpl().getStreets("пуш");
        Assert.assertEquals(1, streets.size());
    }

    @Test
    public void testPassportOffice() throws DaoException {
        List<PassportOffice> offices = new DictionaryDaoImpl().getPassportOffices("010020000000");
        Assert.assertEquals(2, offices.size());
    }

    @Test
    public void testRegisterOffice() throws DaoException {
        List<RegisterOffice> offices = new DictionaryDaoImpl().getRegisterOffices("010010000000");
        Assert.assertEquals(2, offices.size());
    }
    @Test
    public void testArea() throws DaoException {
        List<CountryArea> c1 = new DictionaryDaoImpl().getAreas("");
        Assert.assertEquals(2, c1.size());
        List<CountryArea> c2 = new DictionaryDaoImpl().getAreas("020000000000");
        Assert.assertEquals(2, c2.size());
        List<CountryArea> c3 = new DictionaryDaoImpl().getAreas("020020000000");
        Assert.assertEquals(2, c3.size());
        List<CountryArea> c4 = new DictionaryDaoImpl().getAreas("020020020000");
        Assert.assertEquals(2, c4.size());

    }
}

