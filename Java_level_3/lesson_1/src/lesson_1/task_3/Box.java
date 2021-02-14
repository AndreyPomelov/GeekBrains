package lesson_1.task_3;

import lesson_1.task_3.fruits.Fruit;

import java.util.ArrayList;

public class Box <T extends Fruit> {

    private final ArrayList<T> fruits = new ArrayList<>();

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

    public boolean compare(Box<?> box) {
        return this.getWeight() == box.getWeight();
    }

    public void pour(Box<T> destinationBox) {
        destinationBox.fruits.addAll(this.fruits);
        this.fruits.clear();
    }
}
