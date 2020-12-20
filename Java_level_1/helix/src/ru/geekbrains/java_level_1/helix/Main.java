package ru.geekbrains.java_level_1.helix;

import java.util.Scanner;

public class Main {

    static int[][] matrix;
    static int currentX;
    static int currentY;
    static int currentNumber = 1;
    static Scanner scanner = new Scanner(System.in);
    static int matrixSize;
    static boolean isDone;

    public static void main(String[] args) {

        matrix = matrixInit();
        currentX = currentY = matrixSize / 2; // ставим текущие координаты в центр матрицы
        matrix[currentX][currentY] = currentNumber; // заполняем центральную ячейку
        currentNumber++;
        while (true) {
            if (currentY != 0) goUp(); // проверяем, есть ли место сверху. Если да, идём вверх
            else break;                 // если нет, массив заполнен (так может быть, если пользователь задал размер массива = 1)
            if (isDone) break;          // если мы в точке 0,0 - задача выполнена, выходим из цикла заполнения матрицы
            goRight();
            goDown();
            goLeft();
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

        do {
            currentY--;
            matrix[currentX][currentY] = currentNumber;
            currentNumber++;
            if (currentX == 0 && currentY == 0) {
                isDone = true;  // если пришли в точку 0,0 - задача выполнена
                break;
            }
        } while (matrix[currentX + 1][currentY] != 0);

    }

    // Метод, заполняющий ячейки матрицы по горизонтали вправо
    static void goRight() {

        do {
            currentX++;
            matrix[currentX][currentY] = currentNumber;
            currentNumber++;
        } while (matrix[currentX][currentY + 1] != 0);

    }

    // Метод, заполняющий ячейки матрицы по вертикали вниз
    static void goDown() {

        do {
            currentY++;
            matrix[currentX][currentY] = currentNumber;
            currentNumber++;
        } while (matrix[currentX - 1][currentY] != 0);

    }

    // Метод, заполняющий ячейки матрицы по горизонтали влево
    static void goLeft() {

        do {
            currentX--;
            matrix[currentX][currentY] = currentNumber;
            currentNumber++;
        } while (matrix[currentX][currentY - 1] != 0);

    }
}
