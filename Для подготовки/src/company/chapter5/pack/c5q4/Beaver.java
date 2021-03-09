package company.chapter5.pack.c5q4;

public class Beaver extends Rodent {
    protected static Number chew() throws Exception {
        System.out.println("Beaver is chewing");
        return 1;
    }

    public static void main(String[] args) throws Exception {
        Beaver.chew();
        Rodent.chew();
    }
}
