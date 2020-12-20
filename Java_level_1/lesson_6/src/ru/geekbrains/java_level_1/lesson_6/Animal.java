package ru.geekbrains.java_level_1.lesson_6;

public abstract class Animal {

    private int maxRunDist;
    private double maxJumpHeight;

    public int getMaxRunDist() {
        return maxRunDist;
    }

    public double getMaxJumpHeight() {
        return maxJumpHeight;
    }

    public void setMaxRunDist(int maxRunDist) {
        this.maxRunDist = maxRunDist;
    }

    public void setMaxJumpHeight(double maxJumpHeight) {
        this.maxJumpHeight = maxJumpHeight;
    }

    abstract void run(int distance);

    abstract void swim(int distance);

    abstract void jump(double height);

}
