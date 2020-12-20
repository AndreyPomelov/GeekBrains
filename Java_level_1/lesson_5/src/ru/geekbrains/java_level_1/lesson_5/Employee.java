package ru.geekbrains.java_level_1.lesson_5;

public class Employee {

    private String name;
    private String position;
    private String email;
    private String phone;
    private int salary;
    private int age;

    public Employee(String _name, String _position, String _email, String _phone, int _salary, int _age) {
        name = _name;
        position = _position;
        email = _email;
        phone = _phone;
        salary = _salary;
        age = _age;
    }

    public int getAge() {
        return age;
    }

    public void showMeThisLazyIdler() {
        System.out.println("Ф.И.О.: \t" + name);
        System.out.println("Должность: \t" + position);
        System.out.println("Почта: \t\t" + email);
        System.out.println("Телефон: \t" + phone);
        System.out.println("Зарплата: \t" + salary);
        System.out.println("Возраст: \t" + age);
        System.out.println();
    }
}
