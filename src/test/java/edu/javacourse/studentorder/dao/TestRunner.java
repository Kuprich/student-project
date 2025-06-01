package edu.javacourse.studentorder.dao;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;

public class TestRunner {
    public static void main(String[] args) {
        new TestRunner().runTests();
    }

    private void runTests() {
        try {
            Class cl = Class.forName("edu.javacourse.studentorder.dao.DictionaryDaoImplTest");

            Constructor cst = cl.getConstructor();
            Object entity = cst.newInstance();

            for (Method m : cl.getMethods()) {
                if (m.isAnnotationPresent(org.junit.Test.class)){
                    m.invoke(entity);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
