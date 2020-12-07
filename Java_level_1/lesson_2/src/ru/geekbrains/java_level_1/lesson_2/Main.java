package ru.geekbrains.java_level_1.lesson_2;

public class Main {

    public static void main(String[] args) {

        // Задание 1
        int[] numbers = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == 0) numbers[i] = 1;
            else numbers [i] = 0;
        }

        // Задание 2
        int[] massive = new int[8];

        for (int i = 0; i < massive.length; i++) {
            massive[i] = i * 3;
        }

        // Задание 3
        int[] massive1 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};

        for (int i = 0; i < massive1.length; i++) {
            if (massive1[i] < 6) massive1[i] *= 2;
        }

        // Задание 4, вариант 1 (если мы имеем в виду одну диагональ)
        int[][] square = new int[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i == j) square[i][j] = 1;
                else square[i][j] = 0;
            }
        }

        // Задание 4, вариант 2 (если имелись в виду обе диагонали)
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i == j || i + j == 9) square[i][j] = 1;
                else square[i][j] = 0;
            }
        }

        // Задание 5
        int[] massive2 = {2, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        int max = massive2[0], min = massive2[0];
        for (int i = 0; i < massive2.length; i++) {
            if (massive2[i] < min) min = massive2[i];
            if (massive2[i] > max) max = massive2[i];
        }
        System.out.println("Минимальное значение - " + min);
        System.out.println("Максимальное значение - " + max);

        // Следующий код содержит проверку задания 6
        // Подставляя различные значения в массив, можно убедиться в работоспособности метода checkBalance
        int[] massive3 = {3,100,1,1,2,1,1,1,96};
        boolean balance = checkBalance(massive3);
        System.out.println(balance);

        // Следующий код содержит проверку задания 7
        // Исходя из условий задачи не совсем понял, что делать с теми элементами массива,
        // которые были сдвинуты, но на место которых подставить уже нечего, т.к.
        // длина массива заканчивается. Поэтому в моём решении туда подставляются просто нули.
        // В работоспособности метода можно убедиться, подставляя различные значения delta,
        // как положительные, так и отрицательные.
        int[] massive4 = {7,5,8,2,3,9,4,8,5,7};
        moveNumbers(massive4, 3);
        for (int i = 0; i < massive4.length; i++) {
            System.out.print(massive4[i] + " ");
        }
    }

    // Задание 6
    public static boolean checkBalance(int[] massive) {
        int sumLeft = 0;
        int sumRight = 0;
        for (int i = 0; i < massive.length - 1; i++) {
            sumLeft = sumLeft + massive[i];
            for (int j = i + 1; j < massive.length; j++) {
                sumRight = sumRight + massive[j];
            }
            if (sumLeft == sumRight) return true;
            else {
                sumRight = 0;
            }
        }
        return false;
    }

    // Задание 7
    public static void moveNumbers (int[] massive, int delta) {
        if (delta > 0) {
            for (int i = 0; i < massive.length; i++) {
                if ((i + delta) > (massive.length - 1)) {
                    massive[i] = 0;
                    continue;
                }
                massive[i] = massive[i + delta];
            }
        }
        else if (delta < 0) {
            for (int i = massive.length - 1; i >= 0; i--) {
                if ((i + delta) < 0) {
                    massive[i] = 0;
                    continue;
                }
                massive[i] = massive[i + delta];
            }
        }
    }
}
