package lesson_1.task_2;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Integer[] intArray = {1, 2, 3, 4, 5};
        String[] stringArray = {"A", "B", "C", "D", "E"};
        System.out.println(Arrays.asList(intArray));
        System.out.println(Arrays.asList(stringArray));
        System.out.println(arrayToList(intArray));
        System.out.println(arrayToList(stringArray));
    }

    public static <T> ArrayList<T> arrayToList (T[] array) {
        return new ArrayList<>(Arrays.asList(array));
     }
}
