package ru.geekbrains.java_level_1.lesson_6;

public class Main {

    public static void main(String[] args) {

        Cat cat1 = new Cat();
        Cat cat2 = new Cat();
        Cat cat3 = new Cat();

        System.out.println(cat1.getMaxRunDist() + " " + cat1.getMaxJumpHeight());
        System.out.println(cat2.getMaxRunDist() + " " + cat2.getMaxJumpHeight());
        System.out.println(cat3.getMaxRunDist() + " " + cat3.getMaxJumpHeight());

        cat1.run(200);
        cat1.swim(200);
        cat1.jump(2);
        cat2.run(200);
        cat2.swim(200);
        cat2.jump(2);
        cat3.run(200);
        cat3.swim(200);
        cat3.jump(2);

        Dog dog1 = new Dog();
        Dog dog2 = new Dog();
        Dog dog3 = new Dog();

        System.out.println(dog1.getMaxRunDist() + " " + dog1.getMaxJumpHeight() + " " + dog1.getMaxSwimDist());
        System.out.println(dog2.getMaxRunDist() + " " + dog2.getMaxJumpHeight() + " " + dog2.getMaxSwimDist());
        System.out.println(dog3.getMaxRunDist() + " " + dog3.getMaxJumpHeight() + " " + dog3.getMaxSwimDist());

        dog1.run(500);
        dog1.swim(10);
        dog1.jump(0.5);
        dog2.run(500);
        dog2.swim(10);
        dog2.jump(0.5);
        dog3.run(500);
        dog3.swim(10);
        dog3.jump(0.5);

    }
}
