package lesson_1.task_3;

import lesson_1.task_3.fruits.Fruit;

import java.util.ArrayList;

public class Box <T extends Fruit> {

    private ArrayList<T> fruits;

    public void put(T fruit) {
        fruits.add(fruit);
    }

    public float getWeight() {
        float weight = 0;
        for (T fruit : fruits) {
            weight += fruit.weight;
        }
        return weight;
    }
}
