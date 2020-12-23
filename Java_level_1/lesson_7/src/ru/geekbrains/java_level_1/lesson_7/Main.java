package ru.geekbrains.java_level_1.lesson_7;

public class Main {

    public static void main(String[] args) {

        Cat[] cats = {new Cat("Пумба", 30),
                new Cat("Тимон", 10),
                new Cat("Том", 25),
                new Cat("Сильвестр", 15),
                new Cat("Бутч", 25)};

        Plate plate = new Plate(100);

        feedAllCats(cats, plate);
        checkAllCats(cats);
        System.out.println("\nПополняем тарелку и снова кормим котиков.\n");
        plate.addFood(10);
        feedAllCats(cats, plate);
        checkAllCats(cats);
    }

    static void feedAllCats(Cat[] cats, Plate plate) {
        for (int i = 0; i < cats.length; i++) {
            if (!cats[i].checkFullness()) cats[i].eat(plate);
        }
    }

    static void checkAllCats(Cat[] cats) {
        System.out.println("Проверяем, все ли сыты в зоопарке.\n");
        for (int i = 0; i < cats.length; i++) {
            cats[i].isFullness();
        }
    }
}
