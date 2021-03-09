package dz7;

/**
 * enum
 */

class Priority {
    static final int MINIMUM = 1;
    static final int TINY = 2;
    static final int VERY_SMALL = 3;
    static final int SMALL = 4;
    static final int NORMAL = 5;
    static final int BIG = 6;
    static final int VERY_BIG = 7;
    static final int HUGE = 8;
    static final int ENORMOUS = 9;
    static final int MAXIMUM = 10;

    private Priority(){
        System.out.println("класс не может имет конструктор");
    }
}
