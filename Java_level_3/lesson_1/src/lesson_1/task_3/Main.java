package lesson_1.task_3;

import lesson_1.task_3.fruits.Apple;
import lesson_1.task_3.fruits.Orange;

public class Main {

    public static void main(String[] args) {

        Box<Apple> box1 = new Box<>();
        Box<Orange> box2 = new Box<>();

        System.out.println("Проверяем работоспособность методов.\n");
        box1.put(new Apple());
        // box1.put(new Orange()); Ошибка компиляции. По условию задачи нельзя положить апельсин в коробку с яблоками.
        box1.put(new Apple());
        box1.put(new Apple());

        box2.put(new Orange());
        box2.put(new Orange());
        box2.put(new Orange());

        System.out.println("Текущий вес коробок:");
        System.out.println("Яблоки - " + box1.getWeight());
        System.out.println("Апельсины - " + box2.getWeight());

        System.out.println("\nСравниваем коробки по весу:");
        System.out.println(box1.compare(box2));
        box2.put(new Orange());
        box1.put(new Apple());
        box1.put(new Apple());
        box1.put(new Apple());
        System.out.println(box1.compare(box2));

        Box<Apple> box3 = new Box<>();
        box3.put(new Apple());
        box3.put(new Apple());
        System.out.println("\nВес первой коробки с яблоками - " + box1.getWeight());
        System.out.println("Вес второй коробки с яблоками - " + box3.getWeight());
        System.out.println("Пересыпаем яблоки из первой коробки во вторую и снова проверяем вес.");
        // box1.pour(box2); Ошибка компиляции. По условию задачи нельзя пересыпать яблоки в коробку с апельсинами.
        box1.pour(box3);
        System.out.println("Вес первой коробки с яблоками - " + box1.getWeight());
        System.out.println("Вес второй коробки с яблоками - " + box3.getWeight());
    }
}
