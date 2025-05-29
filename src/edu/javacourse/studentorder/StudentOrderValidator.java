package edu.javacourse.studentorder;

import edu.javacourse.studentorder.domain.*;
import edu.javacourse.studentorder.mail.MailSender;
import edu.javacourse.studentorder.validator.ChildrenValidator;
import edu.javacourse.studentorder.validator.CityRegisterValidator;
import edu.javacourse.studentorder.validator.StudentValidator;
import edu.javacourse.studentorder.validator.WeddingValidator;

public class StudentOrderValidator {

    private ChildrenValidator childrenValidator;
    private CityRegisterValidator cityRegisterValidator;
    private StudentValidator studentValidator;
    private WeddingValidator weddingValidator;
    private MailSender mailSender;

    public StudentOrderValidator() {
        childrenValidator = new ChildrenValidator();
        cityRegisterValidator = new CityRegisterValidator();
        studentValidator = new StudentValidator();
        weddingValidator = new WeddingValidator();
        mailSender = new MailSender();
    }

    public static void main(String[] args) {
        StudentOrderValidator validator = new StudentOrderValidator();
        validator.checkAll();
    }

    private void checkAll() {
        //while (true) {
        StudentOrder[] sos = readStudentOrders();
        for (StudentOrder so : sos) {
            System.out.println("");
            checkOneOrder(so);
        }

    }
    private void checkOneOrder(StudentOrder so){
        AnswerCityRegister cityAnswer = checkCityRegister(so);
        AnswerWedding wedAnswer = checkWedding(so);
        AnswerChildren childAnswer = checkChildren(so);
        AnswerStudent studentAnswer = checkStudent(so);
        sendMail(so);
    }

    private StudentOrder[] readStudentOrders() {
        StudentOrder[] soArray = new StudentOrder[3];
        for (int i = 0; i < soArray.length; i++){
            soArray[i] = SaveStudentOrder.buildStudentOrder(i);
        }
        return soArray;
    }

    private AnswerCityRegister checkCityRegister(StudentOrder so) {
        return cityRegisterValidator.checkCityRegister(so);
    }

    private AnswerWedding checkWedding(StudentOrder so) {
        return weddingValidator.checkWedding(so);
    }

    private AnswerChildren checkChildren(StudentOrder so) {
        return childrenValidator.checkChildren(so);
    }

    private AnswerStudent checkStudent(StudentOrder so) {
        return studentValidator.checkStudent(so);
    }

    private void sendMail(StudentOrder so) {
        mailSender.sendMail(so);
    }
}
