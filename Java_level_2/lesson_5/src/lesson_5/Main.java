package lesson_5;

import java.util.Arrays;

public class Main {

    static final int size = 10_000_000;
    static final int half = size / 2;
    static final int check = 5_678_432;
    static float[] array = new float[size];
    static final int divider = 5;

    public static void main(String[] args) {

        simpleProcessing();
        threadProcessing();
        multipleThreadProcessing();
    }

    static void multipleThreadProcessing() {

        arrayInit();

        long timer = System.currentTimeMillis();

        int massiveSize = size / divider;

        float[][] multArrays = new float[divider][];
        ArrayProcessor[] arrayProcessors = new ArrayProcessor[divider];
        Thread[] threads = new Thread[divider];

        for (int i = 0; i < multArrays.length; i++) {
            multArrays[i] = new float[massiveSize];
            System.arraycopy(array, massiveSize * i, multArrays[i], 0, massiveSize);
            arrayProcessors[i] = new ArrayProcessor(multArrays[i], massiveSize * i);
            threads[i] = new Thread(arrayProcessors[i]);
            threads[i].start();
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < multArrays.length; i++) {
            System.arraycopy(multArrays[i], 0, array, massiveSize * i, massiveSize);
        }

        System.out.println("Время выполнения в несколько потоков - " + (System.currentTimeMillis() - timer) + " миллисекунд");
        System.out.println("Проверочное значение - " + array[check]);
    }

    static void threadProcessing() {

        arrayInit();

        long timer = System.currentTimeMillis();

        float[] array1 = new float[half];
        float[] array2 = new float[half];

        System.arraycopy(array, 0, array1, 0, half);
        System.arraycopy(array, half, array2, 0, half);

        ArrayProcessor arrayProcessor1 = new ArrayProcessor(array1, 0);
        ArrayProcessor arrayProcessor2 = new ArrayProcessor(array2, half);

        Thread thread1 = new Thread(arrayProcessor1);
        Thread thread2 = new Thread(arrayProcessor2);

        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(array1, 0, array, 0, half);
        System.arraycopy(array2, 0, array, half, half);

        System.out.println("Время выполнения в 2 потока - " + (System.currentTimeMillis() - timer) + " миллисекунд");
        System.out.println("Проверочное значение - " + array[check]);
    }

    static void simpleProcessing() {

        arrayInit();

        long timer = System.currentTimeMillis();

        ArrayProcessor arrayProcessor = new ArrayProcessor(array, 0);
        arrayProcessor.run();

        System.out.println("Время выполнения в 1 поток - " + (System.currentTimeMillis() - timer) + " миллисекунд");
        System.out.println("Проверочное значение - " + array[check]);
    }

    static void arrayInit() {
        Arrays.fill(array, 1);
    }
}
