package ru.geekbrains.java_level_1.lesson_6;

public class Dog extends Animal {

    private int maxSwimDist;

    public Dog() {
        setMaxRunDist((int)(Math.random()*101) + 450);
        setMaxJumpHeight(Math.random()*0.4 + 0.3);
        setMaxSwimDist((int)(Math.random()*5) + 8);
    }

    public int getMaxSwimDist() {
        return maxSwimDist;
    }

    public void setMaxSwimDist(int maxSwimDist) {
        this.maxSwimDist = maxSwimDist;
    }

    @Override
    void run(int distance) {
        System.out.println("run: " + (distance <= getMaxRunDist()));
    }

    @Override
    void swim(int distance) {
        System.out.println("swim: " + (distance <= getMaxSwimDist()));
    }

    @Override
    void jump(double height) {
        System.out.println("jump: " + (height <= getMaxJumpHeight()));
    }
}
