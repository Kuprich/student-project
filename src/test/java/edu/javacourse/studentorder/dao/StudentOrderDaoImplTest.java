package edu.javacourse.studentorder.dao;

import edu.javacourse.studentorder.SaveStudentOrder;
import edu.javacourse.studentorder.domain.StudentOrder;
import edu.javacourse.studentorder.exception.DaoException;
import junit.framework.TestCase;
import org.junit.BeforeClass;
import org.junit.Test;

public class StudentOrderDaoImplTest extends TestCase {

    @BeforeClass
    public static void startUP() throws Exception {
        DbInitializer.initDb();
    }

    @Test
    public void testSaveStudentOrder() throws DaoException {
        StudentOrder so = SaveStudentOrder.buildStudentOrder(10);
        Long id = new StudentOrderDaoImpl().SaveStudentOrder(so);
    }

    @Test(expected = Exception.class)
    public void testSaveStudentOrderError() throws Exception {
        StudentOrder so = SaveStudentOrder.buildStudentOrder(10);
        try {
            so.getHusband().setSurName(null);
            Long id = new StudentOrderDaoImpl().SaveStudentOrder(so);
        } catch (DaoException e) {
        }

    }

    @Test
    public void testGetStudentOrders() throws DaoException {
        new StudentOrderDaoImpl().getStudentOrders();
    }
}