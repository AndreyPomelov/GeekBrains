package dz7;

class Testable {
    private static Testable instance;//Singleton

    private Testable() {
    }

    @BeforeSuite
    private static Testable showBegin() {
        System.out.println("Begun!");
        if (instance == null) return new Testable();
        else return instance;
    }

    /*
    To check RuntimeException

    @BeforeSuite
    private static void makeBeforeFault(){
        System.out.println("Fault in before!!");
    }*/

    @Test(priority = Priority.ENORMOUS)
    private void show() {
        System.out.println("Shown nothing.");
    }

    @Test(priority = Priority.BIG)
    private void show(int number) {
        System.out.println("Shown number: " + number);
    }

    @Test
    private void show(float number) {
        System.out.println("Shown float: " + number);
    }

    @Test(priority = Priority.SMALL)
    private void show(double number) {
        System.out.println("Shown double: " + number);
    }

    @Test(priority = Priority.VERY_BIG)
    private void show(Boolean bool) {
        System.out.println("Shown bool: " + bool);
    }

    @Test(priority = Priority.SMALL)
    private void show(byte number) {
        System.out.println("Shown byte: " + number);
    }

    @Test
    private void show(String str) {
        System.out.println("Shown string: " + str);
    }

    @Test(priority = Priority.MINIMUM)
    private void show(String str1, String str2) {
        System.out.println("Shown strings: " + str1 + " " + str2);
    }

    @AfterSuite
    private static void showEnd() {
        System.out.println("End!");
        instance = null;
    }
}
