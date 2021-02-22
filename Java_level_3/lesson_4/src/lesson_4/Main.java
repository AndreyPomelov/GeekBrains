package lesson_4;

public class Main {

    private static final Object monitor = new Object();
    private static volatile char next = 'A';

    public static void main(String[] args) {

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (monitor) {
                    for (int i = 0; i < 5; i++) {
                        while (next != 'A') {
                            waitNext();
                        }
                        System.out.print("A");
                        next = 'B';
                        monitor.notifyAll();
                    }
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (monitor) {
                    for (int i = 0; i < 5; i++) {
                        while (next != 'B') {
                            waitNext();
                        }
                        System.out.print("B");
                        next = 'C';
                        monitor.notifyAll();
                    }
                }
            }
        });

        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (monitor) {
                    for (int i = 0; i < 5; i++) {
                        while (next != 'C') {
                            waitNext();
                        }
                        System.out.print("C");
                        next = 'A';
                        monitor.notifyAll();
                    }
                }
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();
    }

    private static void waitNext() {
        try {
            monitor.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
