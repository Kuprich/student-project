package edu.javacourse.studentorder.validator;

import edu.javacourse.studentorder.domain.Person;
import edu.javacourse.studentorder.domain.register.AnswerCityRegister;
import edu.javacourse.studentorder.domain.Child;
import edu.javacourse.studentorder.domain.StudentOrder;
import edu.javacourse.studentorder.domain.register.AnswerCityRegisterItem;
import edu.javacourse.studentorder.domain.register.CityRegisterResponse;
import edu.javacourse.studentorder.exception.CityRegisterException;
import edu.javacourse.studentorder.exception.TransportException;
import edu.javacourse.studentorder.validator.register.CityRegisterChecker;
import edu.javacourse.studentorder.validator.register.FakeCityRegisterChecker;

public class CityRegisterValidator {

    private static final String EX_T_CODE = "T_ERROR";


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
        AnswerCityRegisterItem.CityError error = null;
        AnswerCityRegisterItem.CityStatus status = null;
        try{
            CityRegisterResponse response =  personChecker.checkPerson(person);
            status = response.isExisting()
                    ? AnswerCityRegisterItem.CityStatus.YES
                    : AnswerCityRegisterItem.CityStatus.NO;
        } catch (CityRegisterException ex) {
            status = AnswerCityRegisterItem.CityStatus.ERROR;
            error = new AnswerCityRegisterItem.CityError(ex.getCode(), ex.getMessage());
            ex.printStackTrace();
        } catch (TransportException ex) {
            status = AnswerCityRegisterItem.CityStatus.ERROR;
            error = new AnswerCityRegisterItem.CityError(EX_T_CODE, ex.getMessage());
            ex.printStackTrace();
        }
        return new AnswerCityRegisterItem(status, person, error);
    }

}
