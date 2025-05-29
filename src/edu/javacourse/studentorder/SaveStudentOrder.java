package edu.javacourse.studentorder;

import edu.javacourse.studentorder.domain.*;


public class SaveStudentOrder {

    public static void main(String[] args) {
//        StudentOrder so = buildStudentOrder(10);
//
//        System.out.println(so.getHusband().getPersonString());

//        long ans = saveStudentOrder(so);
//        System.out.println(ans);
    }
    static long saveStudentOrder(StudentOrder studentOrder){
        long answer = 199;
        System.out.println("hLastName = " + studentOrder.getStudentOrderId());
        return answer;
    }

    static StudentOrder buildStudentOrder(long id){
        StudentOrder studentOrder = new StudentOrder();
        studentOrder.setStudentOrderId(id);

        return studentOrder;
    }

}