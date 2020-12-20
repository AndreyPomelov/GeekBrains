package ru.geekbrains.java_level_1.lesson_5;

public class Main {

    public static void main(String[] args) {

        Employee[] gangList = new Employee[5];
        gangList[0] = new Employee("Al Capone", "Boss", "boss@gang.uy", "+598 (999) 111-11-11", 1000000, 41);
        gangList[1] = new Employee("Simo Hayha", "Sharpshooter", "killer@gang.uy", "+598 (999) 222-22-22", 500000, 47);
        gangList[2] = new Employee("Mika Hakkinen", "Driver", "corptaxi@gang.uy", "+598 (999) 333-33-33", 300000, 52);
        gangList[3] = new Employee("Dwayne Johnson", "Bodyguard", "tank@gang.uy", "+598 (999) 444-44-44", 400000, 48);
        gangList[4] = new Employee("Andrey Pomelov", "Junior Developer", "newbie@gang.uy", "+598 (999) 555-55-55", 100000, 37);

        for (int i = 0; i < gangList.length; i++) {
            if (gangList[i].getAge() > 40) gangList[i].showMeThisLazyIdler();
        }
    }
}
