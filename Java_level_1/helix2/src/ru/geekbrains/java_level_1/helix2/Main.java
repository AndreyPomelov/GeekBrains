package ru.geekbrains.java_level_1.helix2;

import java.util.Scanner;

public class Main {

    static int[][] matrix;
    static int currentX;
    static int currentY;
    static int currentNumber = 1;
    static Scanner scanner = new Scanner(System.in);
    static int matrixSize;
    static boolean isDone;
    static int centerCoord;

    public static void main(String[] args) {

        matrix = matrixInit();
        matrix[currentX][currentY] = currentNumber; // заполняем центральную ячейку
        currentNumber++;
        while (true) {
            if (matrixSize > 1) goRight(); // если размер равен 1, продолжать не имеет смысла, т.к. уже всё сделано
            else break;
            if (isDone) break;
            goDown();
            goLeft();
            goUp();
        }
        showMatrix();                   // отображаем в консоли нашу готовую матрицу
    }

    // Запрашиваем у пользователя размер массива и инициализируем массив.
    static int[][] matrixInit() {

        do {
            System.out.println("Введите положительную нечётную размерность матрицы.");
            matrixSize = scanner.nextInt();
        } while (matrixSize <= 0 || matrixSize % 2 == 0);
        int[][] array = new int[matrixSize][matrixSize];
        centerCoord = matrixSize / 2;
        return array;
    }

    // Метод, отображающий в консоли нашу матрицу.
    static void showMatrix() {

        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                System.out.print(matrix[j][i] + "\t");
            }
            System.out.println();
        }
    }

    // Метод, заполняющий ячейки матрицы по вертикали вверх
    static void goUp() {

        while (matrix[currentX][currentY - 1] == 0) {
            currentY--;
            matrix[currentX][currentY] = currentNumber;
            currentNumber++;
        }

    }

    // Метод, заполняющий ячейки матрицы по горизонтали вправо
    static void goRight() {

        while (currentX < matrixSize - 1 && matrix[currentX + 1][currentY] == 0) {
            currentX++;
            matrix[currentX][currentY] = currentNumber;
            currentNumber++;
            if (currentX == centerCoord && currentY == centerCoord) isDone = true; // если мы в центре поля - всё готово!
        }

    }

    // Метод, заполняющий ячейки матрицы по вертикали вниз
    static void goDown() {

        while (currentY < matrixSize - 1 && matrix[currentX][currentY + 1] == 0) {
            currentY++;
            matrix[currentX][currentY] = currentNumber;
            currentNumber++;
        }

    }

    // Метод, заполняющий ячейки матрицы по горизонтали влево
    static void goLeft() {

        while (currentX > 0 && matrix[currentX - 1][currentY] == 0) {
            currentX--;
            matrix[currentX][currentY] = currentNumber;
            currentNumber++;
        }

    }
}
