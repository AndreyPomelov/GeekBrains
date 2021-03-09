package jobs;

import java.util.*;
import java.util.stream.Collectors;

public class OneFindElement {
    public int findUsingIterator(String name, List<Integer> customers) {
        Iterator<Integer> iterator = customers.iterator();
        while (iterator.hasNext()) {
            Integer integer = iterator.next();
            if (integer.equals(name)) {
                return integer;
            }
        }
        return 0;
    }

    public void goMap(HashMap map) {
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        for (int i = 0; ; ++i) {
            if (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                System.out.println(i + " " + entry.getKey() + " " + entry.getValue());
            } else break;
        }

        //or

        Map<String, String> gfg = new HashMap<String, String>();

        // enter name/url pair
        gfg.put("GFG", "geeksforgeeks.org");
        gfg.put("Practice", "practice.geeksforgeeks.org");
        gfg.put("Code", "code.geeksforgeeks.org");
        gfg.put("Quiz", "quiz.geeksforgeeks.org");

        // using for-each loop for iteration over Map.entrySet()
        for (Map.Entry<String, String> entry : gfg.entrySet())
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());
    }

    public void arrayToList() {
        int[] spam = new int[]{1, 2, 3};
        ArrayList arrayList = (ArrayList) Arrays.stream(spam)
                .boxed()
                .collect(Collectors.toList());
    }

    private int factorial(int n) {
        int result = 1;
        if (n == 1 || n == 0) {
            return result;
        }
        result = n * factorial(n - 1);
        return result;
    }
}
