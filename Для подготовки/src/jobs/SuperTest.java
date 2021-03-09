package jobs;

public class SuperTest {
    static {
        System.out.println("1");
    }

    {
        System.out.println("3");
    }

    public SuperTest() {
        System.out.println("4");
    }
}

class Test extends SuperTest {
    static {
        System.out.println("2");
    }

    {
        System.out.println("5");
    }

    public Test() {
        System.out.println("6");
    }

    public static void main(String[] args) {
        new Test();
    }
}


/**
 * Родитель - Наследник
 * Статический блок, потом простой блок, конструктор
 */
