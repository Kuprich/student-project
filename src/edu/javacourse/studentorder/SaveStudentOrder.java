package edu.javacourse.studentorder;

import edu.javacourse.studentorder.domain.*;


public class SaveStudentOrder {

    public static void main(String[] args) {
        StudentOrder so = new StudentOrder();
        long ans = saveStudentOrder(so);
        System.out.println(ans);
    }
    static long saveStudentOrder(StudentOrder studentOrder){
        long answer = 199;
        System.out.println("hLastName = " + studentOrder.getStudentOrderId());
        return answer;
    }

    static StudentOrder buildStudentOrder(){
        Adult husband = new Adult();
        husband.setGivenName("Name");

        StudentOrder studentOrder = new StudentOrder();
        studentOrder.setHusband(husband);

        return studentOrder;
    }

}