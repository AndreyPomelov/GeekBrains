package lesson_3;
import java.util.*;

public class PhoneBook {

    // Справочник содержит в себе HashMap, содержащий
    // в качестве ключа имя с типом String,
    // а в качестве значения - список телефонов (ArrayList).
    private final Map<String, ArrayList<String>> MAP;

    public PhoneBook() {
        MAP = new HashMap<>();
    }

    public void add(String name, String phone) {
        // Если в справочнике ещё не существует такой фамилии,
        // то просто добавляем новую запись в справочник.
        if (MAP.get(name) == null) {
            ArrayList<String> list = new ArrayList<>(Arrays.asList(phone));
            MAP.put(name, list);
        }
        // Если такая фамилия уже существует, получаем список
        // телефонов по заданной фамилии и добавляем в список
        // ещё один телефон.
        else MAP.get(name).add(phone);
    }

    public ArrayList<String> get(String s) {
        return MAP.get(s);
    }
}
