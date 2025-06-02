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

import javax.swing.plaf.basic.BasicToolBarUI;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;

public class DictionaryDaoImplTest {

    @BeforeClass
    public static void startUp() throws Exception {
        URL resourceCreateDb = DictionaryDaoImplTest.class.getClassLoader()
                .getResource("student-project_structure.sql");

        URL resourceFillDb = DictionaryDaoImplTest.class.getClassLoader()
                .getResource("student_project_data.sql");

        List<String> lines = Files.readAllLines(Paths.get(resourceCreateDb.toURI()));
        String sqlCreateDb = lines.stream().collect(Collectors.joining("\n"));

        lines = Files.readAllLines(Paths.get(resourceFillDb.toURI()));
        String sqlFillDb = lines.stream().collect(Collectors.joining("\n"));

        try (Connection con = ConnectionBuilder.getConnection()) {

            try (Statement stmt = con.createStatement()) {
                stmt.executeUpdate(sqlCreateDb);
                stmt.executeUpdate(sqlFillDb);
            } catch (Exception ex) {
                con.rollback();
                throw new RuntimeException(ex);
            }
        }
    }

    @Test
    public void testStreet() throws DaoException {
        List<Street> streets = new DictionaryDaoImpl().getStreets("пуш");
        Assert.assertTrue(streets.size() == 1);
    }

    @Test
    public void testPassportOffice() throws DaoException {
        List<PassportOffice> offices = new DictionaryDaoImpl().getPassportOffices("010020000000");
        Assert.assertTrue(offices.size() == 2);
    }

    @Test
    public void testRegisterOffice() throws DaoException {
        List<RegisterOffice> offices = new DictionaryDaoImpl().getRegisterOffices("010010000000");
        Assert.assertTrue(offices.size() == 2);
    }
    @Test
    public void testArea() throws DaoException {
        List<CountryArea> c1 = new DictionaryDaoImpl().getAreas("");
        Assert.assertTrue(c1.size() == 2);
        List<CountryArea> c2 = new DictionaryDaoImpl().getAreas("020000000000");
        Assert.assertTrue(c2.size() == 2);
        List<CountryArea> c3 = new DictionaryDaoImpl().getAreas("020020000000");
        Assert.assertTrue(c3.size() == 2);
        List<CountryArea> c4 = new DictionaryDaoImpl().getAreas("020020020000");
        Assert.assertTrue(c4.size() == 2);

    }
}

