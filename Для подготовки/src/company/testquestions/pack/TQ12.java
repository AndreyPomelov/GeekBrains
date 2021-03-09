package company.testquestions.pack;

public class TQ12 {
    public static void main(String args[]) {
        Integer x = new Integer(1);
        String x2 = "1"; // если записать таким образом, будет ошщибка на 10 строке.
        switch (x2) {
            default:
                System.out.println("default");
            case "1":
                System.out.println("correct");
        }
    }
}
