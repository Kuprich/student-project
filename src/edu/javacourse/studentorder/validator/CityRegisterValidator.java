package edu.javacourse.studentorder.validator;

import edu.javacourse.studentorder.domain.AnswerCityRegister;
import edu.javacourse.studentorder.domain.StudentOrder;

public class CityRegisterValidator {

    public String hostName;
    public String login;
    public String password;

    public AnswerCityRegister checkCityRegister(StudentOrder so) {
        System.out.println("checkCityRegister, hostname: " + hostName + " login: " + login + " password: " + password);
        AnswerCityRegister result = new AnswerCityRegister();
        result.success = true;
        return result;
    }
}
