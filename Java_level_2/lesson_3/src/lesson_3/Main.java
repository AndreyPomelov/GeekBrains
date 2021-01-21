package lesson_3;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        String[] array = {"Кошка", "Собака", "Енот", "Курица", "Кролик",
                "Кошка", "Собака", "Верблюд", "Курица", "Индюк",
                "Кошка", "Собака", "Енот", "Верблюд", "Енот",
                "Кошка", "Собака", "Лама", "Кошка", "Заяц"};

        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < array.length; i++) {
            String key = array[i];
            Integer value = map.getOrDefault(key, 0);
            map.put(key, value + 1);
        }

        System.out.println(map);

        PhoneBook phoneBook = new PhoneBook();

        phoneBook.add("Иванов", "+7 (999) 111-11-11");
        phoneBook.add("Петров", "+7 (999) 222-22-22");
        phoneBook.add("Сидоров", "+7 (999) 333-33-33");
        phoneBook.add("Иванов", "+7 (999) 444-44-44");

        System.out.println("Иванов: " + phoneBook.get("Иванов"));
        System.out.println("Петров: " + phoneBook.get("Петров"));
        System.out.println("Сидоров: " + phoneBook.get("Сидоров"));

    }
}
