package lesson_1.task_3;

import lesson_1.task_3.fruits.Apple;
import lesson_1.task_3.fruits.Orange;

public class Main {

    public static void main(String[] args) {

        Box box1 = new Box();
        Box box2 = new Box();

        box1.put(new Apple());
        box1.put(new Orange());

    }
}
