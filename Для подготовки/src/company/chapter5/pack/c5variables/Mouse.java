package company.chapter5.pack.c5variables;

public class Mouse extends Rodent {
    protected int tailLength = 8;

    public void getMouseDetails() {
        System.out.println("[tail=" + tailLength + ",parentTail=" + super.tailLength + "]");
    }

    public void getRodentDetails() {
        System.out.println("[parentTail=" + 200 + "%]");
    }

    public static void main(String[] args) {
        Mouse mouse = new Mouse();
        mouse.getRodentDetails();
        mouse.getMouseDetails();

        Rodent rodent = new Mouse();
        rodent.getRodentDetails();
        //Если вы ссылаетесь на переменную из родительского класса, используется переменная,
        // определенная в родительском классе. Кроме того, если вы ссылаетесь на переменную из дочернего класса,
        // используется переменная, определенная в дочернем классе.
    }
}
