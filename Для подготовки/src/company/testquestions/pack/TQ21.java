package company.testquestions.pack;

public class TQ21 {
    public static void main(String args[]) {
        int i = 0;
        for (i = 1; i < 5; i++) continue;
        for (i = 0; ; i++) break; // один раз цикл отработает, так как присутствует break. Без него цикл будет
        // выполняться бесконечно
        for (; i < 5 ? false : true; ) ;
    }
}
// Все три запсии являются допустимыми! Даже такая запись "for (; ; )" будет компилироваться и выполняться бесконечно.
