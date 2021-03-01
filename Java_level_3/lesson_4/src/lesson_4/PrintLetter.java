package lesson_4;

public class PrintLetter implements Runnable {

    private static final Object monitor = new Object();
    private static volatile char next = 'A';
    private final char printLetter;
    private final char nextLetter;

    public PrintLetter(char printLetter, char nextLetter) {
        this.printLetter = printLetter;
        this.nextLetter = nextLetter;
        new Thread(this).start();
    }

    @Override
    public void run() {
        synchronized (monitor) {
            for (int i = 0; i < 5; i++) {
                while (next != printLetter) {
                    waitNext();
                }
                System.out.print(printLetter);
                next = nextLetter;
                monitor.notifyAll();
            }
        }
    }

    private static void waitNext() {
        try {
            monitor.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
