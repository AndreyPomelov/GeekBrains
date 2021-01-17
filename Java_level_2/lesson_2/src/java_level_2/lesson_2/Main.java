package java_level_2.lesson_2;

import java_level_2.lesson_2.exception.MyArrayDataException;
import java_level_2.lesson_2.exception.MyArraySizeException;

public class Main {

    public static void main(String[] args) {

        String[][] array = {
                {"1", "2", "3", "4"},
                {"5", "6", "seven", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };
        
        try {
            arraySum(array);
        }
        catch (MyArraySizeException e) {
            System.out.println("Здесь обрабатывается исключение MyArraySizeException");
            e.printStackTrace();
        }
        catch (MyArrayDataException e) {
            System.out.println("Здесь обрабатывается исключение MyArrayDataException");
            e.printStackTrace();
        }
        finally {
            System.out.println("Выполнение блока finally");
        }

        System.out.println("Работа программы завершена успешно");
    }

    public static void arraySum (String[][] array) throws MyArraySizeException, MyArrayDataException {

        checkArraySize(array);

        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    sum += Integer.parseInt(array[j][i]);
                }
                catch (NumberFormatException e) {
                    throw new MyArrayDataException(j, i);
                }
            }
        }
        System.out.println("Сумма элементов массива равна " + sum);
    }

    public static void checkArraySize(String[][] array) throws MyArraySizeException {
        if (array.length != 4) throw new MyArraySizeException();
        for (int i = 0; i < array.length; i++) {
            if (array[i].length != 4) throw new MyArraySizeException();
        }
    }
}
