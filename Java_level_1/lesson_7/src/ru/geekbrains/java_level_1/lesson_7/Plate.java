package ru.geekbrains.java_level_1.lesson_7;

public class Plate {

    private int food;

    public Plate(int food) {
        this.food = food;
    }

    public void decreaseFood (int i, Cat cat) {
        if (i > food) System.out.println("Но в тарелке недостаточно еды!");
        else {
            food -= i;
            cat.setFullness(true);
            System.out.println("Теперь " + cat.getName() + " сыт и доволен!");
        }
    }

    public void addFood(int food) {
        this.food += food;
        System.out.println("Тарелка пополнена на " + food + " единиц еды!\n");
    }

    public void info() {
        System.out.println("В тарелке осталось " + food + " единиц еды.\n");
    }
}
