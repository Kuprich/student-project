package edu.javacourse.studentorder.validator;

import edu.javacourse.studentorder.domain.Person;
import edu.javacourse.studentorder.domain.register.AnswerCityRegister;
import edu.javacourse.studentorder.domain.Child;
import edu.javacourse.studentorder.domain.StudentOrder;
import edu.javacourse.studentorder.domain.register.AnswerCityRegisterItem;
import edu.javacourse.studentorder.domain.register.CityRegisterResponse;
import edu.javacourse.studentorder.exception.CityRegisterException;
import edu.javacourse.studentorder.validator.register.CityRegisterChecker;
import edu.javacourse.studentorder.validator.register.FakeCityRegisterChecker;

public class CityRegisterValidator {

    private CityRegisterChecker personChecker;

    public CityRegisterValidator() {
        personChecker = new FakeCityRegisterChecker();
    }

    public AnswerCityRegister checkCityRegister(StudentOrder so) {
        AnswerCityRegister result = new AnswerCityRegister();

        result.addItem(checkPerson(so.getHusband()));
        result.addItem(checkPerson(so.getWife()));

        for (Child child : so.getChildren()) {
            result.addItem(checkPerson(child));
        }

        return result;
    }

    public AnswerCityRegisterItem checkPerson(Person person){
        try{
            CityRegisterResponse response =  personChecker.checkPerson(person);
        } catch (CityRegisterException e) {
            e.printStackTrace();
        }
        return null;
    }

}
