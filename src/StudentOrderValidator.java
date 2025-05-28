public class StudentOrderValidator {
    public static void main(String[] args) {
        checkAll();
    }

    private static void checkAll() {
        while (true) {
            StudentOrder so = readStudentOrder();

            if (so == null) return;

            AnswerCityRegister cityAnswer = checkCityRegister(so);
            if (!cityAnswer.success) continue;
            AnswerWedding wedAnswer = checkWedding(so);
            AnswerChildren childAnswer = checkChildren(so);
            AnswerStudent studentAnswer = checkStudent(so);

            sendMail(so);
        }

    }

    static void sendMail(StudentOrder so) {
    }

    static StudentOrder readStudentOrder() {
        return new StudentOrder();
    }

    static AnswerCityRegister checkCityRegister(StudentOrder so) {
        System.out.println("checkCityRegister");
        AnswerCityRegister result = new AnswerCityRegister();
        result.success = true;
        return result;
    }

    static AnswerWedding checkWedding(StudentOrder so) {
        System.out.println("checkWedding");
        return new AnswerWedding();
    }

    static AnswerChildren checkChildren(StudentOrder so) {
        System.out.println("checkChildren");
        return new AnswerChildren();
    }

    static AnswerStudent checkStudent(StudentOrder so) {
        System.out.println("checkStudent");
        return new AnswerStudent();
    }
}
