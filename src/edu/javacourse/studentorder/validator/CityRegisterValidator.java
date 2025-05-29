package edu.javacourse.studentorder.validator;

import edu.javacourse.studentorder.domain.AnswerCityRegister;
import edu.javacourse.studentorder.domain.Child;
import edu.javacourse.studentorder.domain.StudentOrder;
import edu.javacourse.studentorder.exception.CityRegisterException;

public class CityRegisterValidator {

    private CityRegisterChecker personChecker;

    public CityRegisterValidator() {
        personChecker = new FakeCityRegisterChecker();
    }

    public AnswerCityRegister checkCityRegister(StudentOrder so) {
        try{
            personChecker.checkPerson(so.getHusband());
            personChecker.checkPerson(so.getWife());
            for (Child child : so.getChildren()) {
                personChecker.checkPerson(child);
            }
           // personChecker.checkPerson(so.getChildren());
        } catch (CityRegisterException e) {
            e.printStackTrace();
        }


        AnswerCityRegister result = new AnswerCityRegister();
        result.success = true;
        return result;
    }
}
