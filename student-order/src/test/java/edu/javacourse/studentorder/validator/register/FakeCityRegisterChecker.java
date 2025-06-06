package edu.javacourse.studentorder.validator.register;

import edu.javacourse.studentorder.domain.Adult;
import edu.javacourse.studentorder.domain.Child;
import edu.javacourse.studentorder.domain.register.CityRegisterResponse;
import edu.javacourse.studentorder.domain.Person;
import edu.javacourse.studentorder.exception.CityRegisterException;
import edu.javacourse.studentorder.exception.TransportException;

public class FakeCityRegisterChecker implements CityRegisterChecker {

    public static final String GOOD_1 = "1000";
    public static final String GOOD_2 = "2000";

    public static final String BAD_1 = "1001";
    public static final String BAD_2 = "2001";

    public static final String ERROR_1 = "1002";
    public static final String ERROR_2 = "2002";

    public static final String T_ERROR_1 = "1003";
    public static final String T_ERROR_2 = "2003";


    public CityRegisterResponse checkPerson(Person person) throws CityRegisterException, TransportException {
        CityRegisterResponse response = new CityRegisterResponse();

        if (person instanceof Adult adult){
            String ps = adult.getPassportSeria();
            if (ps.equals(GOOD_1) || ps.equals(GOOD_2)){
                response.setRegistered(true);
                response.setTemporal(false);
            }
            if (ps.equals(BAD_1) || ps.equals(BAD_2)){
                response.setRegistered(false);
            }
            if (ps.equals(ERROR_1) || ps.equals(ERROR_2)){
                CityRegisterException exception = new CityRegisterException("1", "GRN Error");
                throw exception;
            }
            if (ps.equals(T_ERROR_1) || ps.equals(T_ERROR_2)){
                CityRegisterException exception = new CityRegisterException("1", "Transport Error");
                throw exception;
            }
        }
        if (person instanceof Child){
            response.setRegistered(true);
            response.setTemporal(true);

        }

        System.out.println(response);

        return response;

    }
}

