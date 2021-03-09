package jobs;

public class Overload {
    Exception exception = new Exception();

    public void method(Object o) {
        System.out.println("Object");
    }

    public void method(java.io.FileNotFoundException f) {
        System.out.println("FileNotFoundException");
    }

    public void method(java.io.IOException i) {
        System.out.println("IOException");
    }

    public static void main(String args[]) {
        Overload test = new Overload();
        test.method(null); // new java.io.FileNotFoundException();
    }
}
/**
 * Результатом его компиляции и выполнения будет:
 * 1. Ошибка компиляции
 * 2. Ошибка времени выполнения
 * 3. «Object»
 * 4. «FileNotFoundException»
 * 5. «IOException»
 */

