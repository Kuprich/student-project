package edu.javacourse.studentorder.validator;

import edu.javacourse.studentorder.domain.Adult;
import edu.javacourse.studentorder.domain.Child;
import edu.javacourse.studentorder.domain.CityRegisterCheckerResponse;
import edu.javacourse.studentorder.domain.Person;
import edu.javacourse.studentorder.exception.CityRegisterException;

public class FakeCityRegisterChecker implements CityRegisterChecker {

    public static final String GOOD_1 = "1000";
    public static final String GOOD_2 = "2000";

    public static final String BAD_1 = "1001";
    public static final String BAD_2 = "2001";

    public static final String ERROR_1 = "1002";
    public static final String ERROR_2 = "2002";

    public CityRegisterCheckerResponse checkPerson(Person person) throws CityRegisterException {
        CityRegisterCheckerResponse response = new CityRegisterCheckerResponse();

        if (person instanceof Adult){
            Adult adult = (Adult) person;
            String ps = adult.getPassportSeria();
            if (ps.equals(GOOD_1) || ps.equals(GOOD_2)){
                response.setExisting(true);
                response.setTemporal(false);
            }
            if (ps.equals(BAD_1) || ps.equals(BAD_2)){
                response.setExisting(false);
            }
            if (ps.equals(ERROR_1) || ps.equals(ERROR_2)){
                CityRegisterException exception = new CityRegisterException("Fake Error");
                throw exception;
            }
        }
        if (person instanceof Child){
            response.setExisting(true);
            response.setTemporal(true);

        }

        System.out.println(response);

        return response;

    }
}

