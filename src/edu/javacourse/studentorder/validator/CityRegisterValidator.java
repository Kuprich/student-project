package edu.javacourse.studentorder.validator;

import edu.javacourse.studentorder.domain.AnswerCityRegister;
import edu.javacourse.studentorder.domain.StudentOrder;
import edu.javacourse.studentorder.exception.CityRegisterException;

public class CityRegisterValidator {

    private CityRegisterChecker personChecker;

    public CityRegisterValidator() {
        personChecker = new RealCityRegisterChecker();
    }

    public AnswerCityRegister checkCityRegister(StudentOrder so) {
        try{
            personChecker.checkPerson(so.getHusband());
            personChecker.checkPerson(so.getWife());
            personChecker.checkPerson(so.getChild());
        } catch (CityRegisterException e) {
            e.printStackTrace();
        }


        AnswerCityRegister result = new AnswerCityRegister();
        result.success = true;
        return result;
    }
}
