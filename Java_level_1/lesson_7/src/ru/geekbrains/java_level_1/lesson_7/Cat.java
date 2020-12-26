package ru.geekbrains.java_level_1.lesson_7;

public class Cat {

    private String name;
    private int appetite;
    private boolean fullness;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        this.fullness = false;
    }

    public String getName() {
        return name;
    }

    public void setFullness(boolean fullness) {
        this.fullness = fullness;
    }

    public void isFullness() {
        if (fullness) System.out.println(name + " сыт и доволен!");
        else System.out.println(name + " голоден и несчастен! Примите срочные меры!");
    }

    public boolean checkFullness() {
        return fullness;
    }

    public void eat(Plate plate) {
        System.out.println(name + " проголодался и идёт есть.");
        plate.decreaseFood(appetite, this);
        plate.info();
    }
}
