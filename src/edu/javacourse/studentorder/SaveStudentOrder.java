package edu.javacourse.studentorder;

import edu.javacourse.studentorder.domain.*;


public class SaveStudentOrder {

    public static void main(String[] args) {
        StudentOrder so = buildStudentOrder();

        System.out.println(so.getHusband().getPersonString());

//        long ans = saveStudentOrder(so);
//        System.out.println(ans);
    }
    static long saveStudentOrder(StudentOrder studentOrder){
        long answer = 199;
        System.out.println("hLastName = " + studentOrder.getStudentOrderId());
        return answer;
    }

    static StudentOrder buildStudentOrder(){
        Adult husband = new Adult();
        husband.setGivenName("GivenName");
        husband.setSurName("SurName");
        husband.setPassportNumber("passport");

        StudentOrder studentOrder = new StudentOrder();
        studentOrder.setHusband(husband);

        return studentOrder;
    }

}