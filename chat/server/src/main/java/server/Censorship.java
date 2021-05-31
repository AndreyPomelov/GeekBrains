package server;

import java.util.HashMap;
import java.util.Map;

public class Censorship {
    public static final Map<String, String> map = new HashMap<>();

    // Цензура. Пример списка запрещённых слов, и на что они заменяются.
    static {
        map.put("кошак", "котик");
        map.put("кошака", "котика");
        map.put("кошаку", "котику");
        map.put("кошаком", "котиком");
        map.put("кошаки", "котики");
        map.put("кошаков", "котиков");
        map.put("кошакам", "котикам");
        map.put("кошаками", "котиками");
    }
}
