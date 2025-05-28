public class StudentOrderValidator {
    public static void main(String[] args) {
        checkAll();
    }

    private static void checkAll() {
        checkCityRegister();
        checkWedding();
        checkChildren();
        checkStudent();
    }

    static void checkCityRegister(){
        System.out.println("checkCityRegister");
    }

    static void checkWedding(){
        System.out.println("checkWedding");
    }

    static void checkChildren(){
        System.out.println("checkChildren");
    }

    static void checkStudent(){
        System.out.println("checkStudent");
    }
}
