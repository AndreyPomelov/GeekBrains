package ru.geekbrains.java_level_1.lesson_1;

public class Main {

    public static void main(String[] args) {

        byte b = 125;
        short s = 345;
        int i = 5;
        long l = 2343453232345L;

        float f = 10.0F;
        double d = 7.0;

        char c = 'X';

        boolean isTrue = true;

        String msg = "Hi!";

    }

    public static int calc (int a, int b, int c, int d) {
        return a * (b + (c / d));
    }

    public static boolean isSumInRange (int a, int b) {
        if ((a + b) >= 10 && (a + b) <= 20) return true;
        else return false;
    }

    public static void isPositive (int a) {
        if (a >= 0) System.out.println("Число " + a + " положительное");
        else System.out.println("Число " + a + " отрицательное");
    }

    public static boolean isNegative (int a) {
        if (a < 0) return true;
        else return false;
    }

    public static void sayHello (String name) {
        System.out.println("Привет, " + name + "!");
    }

    public static void isIntercalary (int year) {
        if (year % 400 == 0) System.out.println("Год " + year + " високосный");
        else if (year % 100 == 0) System.out.println("Год " + year + " невисокосный");
        else if (year % 4 == 0) System.out.println("Год " + year + " високосный");
        else System.out.println("Год " + year + " невисокосный");
    }
}
