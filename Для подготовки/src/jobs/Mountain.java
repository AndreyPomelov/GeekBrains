package jobs;

public class Mountain {
    static String name = "static Himalaya";
    String name2 = "Himalaya";

    static Mountain getMountain() {
        System.out.println("Getting Name ");
        return null;
    }

    public static void main(String[] args) {

        System.out.println(getMountain().name);
        System.out.println(getMountain().name2);
    }
    /**
     * Что произойдет при попытке выполнения данного кода:
     * 1. Будет выведено «Himalaya» но НЕ будет выведено «Getting Name „
     * 2. Будет выведено “Getting Name » и «Himalaya»
     * 3. Ничего не будет выведено
     * 4. Будет выброшен NullPointerException
     * 5. Будет выведено «Getting Name », а потом выброшено NullPointerException
     */
}
