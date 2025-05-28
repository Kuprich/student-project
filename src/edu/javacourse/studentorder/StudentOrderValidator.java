package edu.javacourse.studentorder;

import edu.javacourse.studentorder.domain.*;
import edu.javacourse.studentorder.mail.MailSender;
import edu.javacourse.studentorder.validator.ChildrenValidator;
import edu.javacourse.studentorder.validator.CityRegisterValidator;
import edu.javacourse.studentorder.validator.StudentValidator;
import edu.javacourse.studentorder.validator.WeddingValidator;

public class StudentOrderValidator {
    public static void main(String[] args) {
        checkAll();
    }

    private static void checkAll() {
        //while (true) {
            StudentOrder so = readStudentOrder();

            if (so == null) return;

            AnswerCityRegister cityAnswer = checkCityRegister(so);
            //if (!cityAnswer.success) continue;
            AnswerWedding wedAnswer = checkWedding(so);
            AnswerChildren childAnswer = checkChildren(so);
            AnswerStudent studentAnswer = checkStudent(so);

            sendMail(so);
       // }

    }

    static StudentOrder readStudentOrder() {
        return new StudentOrder();
    }

    static AnswerCityRegister checkCityRegister(StudentOrder so) {
        CityRegisterValidator validator = new CityRegisterValidator();
        validator.hostName = "host1";
        validator.login = "login1";
        validator.password = "password1";
        return validator.checkCityRegister(so);
    }

    static AnswerWedding checkWedding(StudentOrder so) {
        WeddingValidator validator = new WeddingValidator();
        return validator.checkWedding(so);
    }

    static AnswerChildren checkChildren(StudentOrder so) {
        ChildrenValidator validator = new ChildrenValidator();
        return validator.checkChildren(so);
    }

    static AnswerStudent checkStudent(StudentOrder so) {
        StudentValidator validator = new StudentValidator();
        return validator.checkStudent(so);
    }

    static void sendMail(StudentOrder so) {
        MailSender mailSender = new MailSender();
        mailSender.sendMail(so);
    }
}
