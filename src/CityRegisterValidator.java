public class CityRegisterValidator {

    String hostName;
    String login;
    String password;

    AnswerCityRegister checkCityRegister(StudentOrder so) {
        System.out.println("checkCityRegister, hostname: " + hostName + " login: " + login + " password: " + password);
        AnswerCityRegister result = new AnswerCityRegister();
        result.success = true;
        return result;
    }
}
