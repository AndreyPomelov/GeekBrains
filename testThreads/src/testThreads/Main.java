package testThreads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private static final Object monitor = new Object();

    public static void main(String[] args) {

//        new Thread(() -> {
//            try {
//                numbers();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
//
//        new Thread(() -> {
//            try {
//                numbers();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    numbers();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        executorService.shutdown();
    }

    private static void numbers() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            System.out.println(i);
        }
        synchronized (monitor) {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(1000);
                System.out.println(i + 10);
                if (i == 5) monitor.wait(5000);
            }
        }
    }
}
