package ru.geekbrains.java_level_1.lesson_6;

public class Cat extends Animal {

    public Cat() {
        setMaxRunDist((int)(Math.random()*101) + 150);
        setMaxJumpHeight(Math.random() + 1.5);
    }

    @Override
    void run(int distance) {
        System.out.println("run: " + (distance <= getMaxRunDist()));
    }

    @Override
    void swim(int distance) {
        System.out.println("swim: false");
    }

    @Override
    void jump(double height) {
        System.out.println("jump: " + (height <= getMaxJumpHeight()));
    }
}
