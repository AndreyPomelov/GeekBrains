package company.testquestions.pack;

public class TQ26 {
    public static void main(String args[]) {
        String[][][] arr = {{{"a", "b", "c"}, {"d", "e", null}}, {{"x"}, null}, {{"y"}}, {{"z", "p"}, {}}};
        System.out.println(arr[0].length);
        System.out.println(arr[1].length);
        System.out.println(arr[2].length);
        System.out.println(arr[3].length);


        System.out.println(arr[0][1][2]);
        // Тут проще идти по фигурным скобкам, напечается null, так как напечтатется из нулевого массива в трехмерном
        // массиве, который содержит два массива нулевой и превый, так как вторая цифра 1, то напечатается элемент
        // первого массива под вторым номером, а это null.
    }
}
