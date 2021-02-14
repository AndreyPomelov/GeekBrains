package lesson_1.task_1;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Integer[] intArray = {1, 2};
        String[] stringArray = {"A", "B"};
        System.out.println(Arrays.toString(intArray));
        System.out.println(Arrays.toString(stringArray));
        switchElements(intArray);
        switchElements(stringArray);
        System.out.println(Arrays.toString(intArray));
        System.out.println(Arrays.toString(stringArray));
    }

    public static <T> void switchElements (T[] array) {
        T temp = array[0];
        array [0] = array [1];
        array [1] = temp;
    }
}
