package edu.javacourse.studentorder;

import edu.javacourse.studentorder.domain.*;
import edu.javacourse.studentorder.domain.children.AnswerChildren;
import edu.javacourse.studentorder.domain.register.AnswerCityRegister;
import edu.javacourse.studentorder.domain.student.AnswerStudent;
import edu.javacourse.studentorder.domain.wedding.AnswerWedding;
import edu.javacourse.studentorder.mail.MailSender;
import edu.javacourse.studentorder.validator.ChildrenValidator;
import edu.javacourse.studentorder.validator.CityRegisterValidator;
import edu.javacourse.studentorder.validator.StudentValidator;
import edu.javacourse.studentorder.validator.WeddingValidator;

import java.util.LinkedList;
import java.util.List;

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
        List<StudentOrder> soList = readStudentOrders();
        for (StudentOrder so : soList) {
            System.out.println("");
            checkOneOrder(so);
        }

    }
    private void checkOneOrder(StudentOrder so){
        AnswerCityRegister cityAnswer = checkCityRegister(so);
  //      AnswerWedding wedAnswer = checkWedding(so);
 //       AnswerChildren childAnswer = checkChildren(so);
   //     AnswerStudent studentAnswer = checkStudent(so);
    //    sendMail(so);
    }

    private List<StudentOrder> readStudentOrders() {
        List<StudentOrder> soList = new LinkedList<>();
        for (int i = 0; i < 4; i++){
            soList.add(SaveStudentOrder.buildStudentOrder(i));
        }
        return soList;
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
