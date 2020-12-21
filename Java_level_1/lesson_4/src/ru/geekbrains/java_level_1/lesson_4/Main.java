// Это закомментированное решение для крестиков-ноликов с полем 3 на 3
//package ru.geekbrains.java_level_1.lesson_4;
//
//import java.util.Random;
//import java.util.Scanner;
//
//public class Main {
//
//    static int fieldSizeX = 3;
//    static int fieldSizeY = 3;
//    static Random random = new Random();
//    static Scanner scanner = new Scanner(System.in);
//    static char[][] field;
//    static char dotPlayer = 'X';
//    static char dotAI = 'O';
//    static char empty = '.';
//    static int x, y;
//
//    public static void main(String[] args) {
//
//        fieldInit(fieldSizeY, fieldSizeX); // инициализируем поле
//	    fieldDraw(); // отрисовываем пустое поле
//
//	    while (true) {
//	        playerTurn(); // вызов метода хода игрока
//            // проверка, выиграл ли игрок
//	        if (isWin(dotPlayer)) {
//                System.out.println("Поздравляем! Вы победили!");
//                break;
//            }
//            // проверка, заполнено ли поле
//            if (isFieldFull()) {
//                System.out.println("Ну надо же! Ничья!");
//                break;
//            }
//            aiTurn(); // вызов метода хода компьютера
//            // проверка, выиграл ли компьютер
//            if (isWin(dotAI)) {
//                System.out.println("К сожалению, победил компьютер!");
//                break;
//            }
//            // проверка, заполнено ли поле
//            if (isFieldFull()) {
//                System.out.println("Ну надо же! Ничья!");
//                break;
//            }
//        }
//    }
//
//    // метод, инициализирующий поле
//    static void fieldInit(int y, int x) {
//        field = new char[y][x];
//        for (int i = 0; i < y; i++) {
//            for (int j = 0; j < x; j++) {
//                field[i][j] = empty;
//            }
//        }
//    }
//
//    // метод, отрисовывающий поле
//    static void fieldDraw () {
//        System.out.println("-------");
//        for (int i = 0; i < fieldSizeY; i++) {
//            System.out.print("|");
//            for (int j = 0; j < fieldSizeX; j++) {
//                System.out.print(field[j][i] + "|");
//            }
//            System.out.println();
//            System.out.println("-------");
//        }
//    }
//
//    // метод хода игрока
//    static void playerTurn() {
//        do {
//            System.out.println("Введите координаты незанятой ячейки, X и Y");
//            x = scanner.nextInt() - 1;
//            y = scanner.nextInt() - 1;
//        } while (!isCoordCorrect(x, y));
//        field[x][y] = dotPlayer;
//        fieldDraw();
//    }
//
//    // метод, проверяющий корректность координат
//    static boolean isCoordCorrect(int x, int y) {
//        return (x >= 0 && x <=2 && y >= 0 && y <=2 && field[x][y] == empty);
//    }
//
//    // метод, проверяющий победил ли игрок или компьютер
//    static boolean isWin(char dot) {
//        int dotCount = 0;
//        for (int i = 0; i < fieldSizeY; i++) {
//            for (int j = 0; j < fieldSizeX; j++) {
//                if (field[j][i] == dot) dotCount++;
//            }
//            if (dotCount == 3) return true;
//            dotCount = 0;
//        }
//        for (int i = 0; i < fieldSizeX; i++) {
//            for (int j = 0; j < fieldSizeY; j++) {
//                if (field[i][j] == dot) dotCount++;
//            }
//            if (dotCount == 3) return true;
//            dotCount = 0;
//        }
//        for (int i = 0, j = 0; i < fieldSizeX; i++, j++) {
//            if (field[i][j] == dot) dotCount++;
//        }
//        if (dotCount == 3) return true;
//        dotCount = 0;
//        for (int i = 2, j = 0; j < fieldSizeX; i--, j++) {
//            if (field[i][j] == dot) dotCount++;
//        }
//        return (dotCount == 3);
//    }
//
//    // метод, проверяющий, заполнено ли поле
//    static boolean isFieldFull() {
//        for (int i = 0; i < fieldSizeY; i++) {
//            for (int j = 0; j < fieldSizeX; j++) {
//                if (field[j][i] == empty) return false;
//            }
//        }
//        return true;
//    }
//
//    // метод хода компьютера
//    static void aiTurn() {
//        System.out.println("Ходит компьютер");
//        do {
//            x = random.nextInt(3);
//            y = random.nextInt(3);
//        } while (!isCoordCorrect(x, y));
//        field[x][y] = dotAI;
//        fieldDraw();
//    }
//}

// Это решение для крестиков-ноликов с размером поля 5 на 5 и логикой AI
package ru.geekbrains.java_level_1.lesson_4;

        import java.util.Random;
        import java.util.Scanner;

public class Main {

    static int fieldSizeX = 5;
    static int fieldSizeY = 5;
    static Random random = new Random();
    static Scanner scanner = new Scanner(System.in);
    static char[][] field;
    static char dotPlayer = 'X';
    static char dotAI = 'O';
    static char empty = '.';
    static int x, y;
    static int difficulty = 2; // переменная, регулирующая сложность игры
    // Регулирует реакцию AI на выстраиваемые последовательности символов.
    // Значение 2 - обычная сложность. 1 - AI реагирует раньше, но появляется
    // побочное явление в виде лишних ходов. Здесь можно ещё подумать и поработать.
    // Добавлена ради эксперимента, менять не рекомендуется. 2 - оптимальное значение.
    static int targetX; // Эти три переменные используются в логике хода AI
    static int targetY;
    static boolean isMoveDone;

    public static void main(String[] args) {

        fieldInit(fieldSizeY, fieldSizeX); // инициализируем поле
        fieldDraw(); // отрисовываем пустое поле

        while (true) {
            playerTurn(); // вызов метода хода игрока
            // проверка, выиграл ли игрок
            if (isWin(dotPlayer)) {
                System.out.println("Поздравляем! Вы победили!");
                break;
            }
            // проверка, заполнено ли поле
            if (isFieldFull()) {
                System.out.println("Ну надо же! Ничья!");
                break;
            }
            aiTurn(); // вызов метода хода компьютера
            // проверка, выиграл ли компьютер
            if (isWin(dotAI)) {
                System.out.println("К сожалению, победил компьютер!");
                break;
            }
            // проверка, заполнено ли поле
            if (isFieldFull()) {
                System.out.println("Ну надо же! Ничья!");
                break;
            }
        }
    }

    // метод, инициализирующий поле
    static void fieldInit(int y, int x) {
        field = new char[y][x];
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                field[i][j] = empty;
            }
        }
    }

    // метод, отрисовывающий поле
    static void fieldDraw () {
        System.out.println("-----------");
        for (int i = 0; i < fieldSizeY; i++) {
            System.out.print("|");
            for (int j = 0; j < fieldSizeX; j++) {
                System.out.print(field[j][i] + "|");
            }
            System.out.println();
            System.out.println("-----------");
        }
    }

    // метод хода игрока
    static void playerTurn() {
        do {
            System.out.println("Введите координаты незанятой ячейки, X и Y");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCoordCorrect(x, y));
        field[x][y] = dotPlayer;
        fieldDraw();
    }

    // метод, проверяющий корректность координат
    static boolean isCoordCorrect(int x, int y) {
        return (x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY && field[x][y] == empty);
    }

    // метод, проверяющий победил ли игрок или компьютер
    static boolean isWin(char dot) {
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (isUpDiagValid(j, i)) { // Смотрим, целесообразно ли проверять диагональ
                    if (checkUpDiag(j, i, dot)) return true; // Если да, проверяем диагональ
                } // Далее - аналогично со второй диагональю, горизонталью и вертикалью
                if (isDownDiagValid(j, i)) {
                    if (checkDownDiag(j, i, dot)) return true;
                }
                if (isHorizValid(j)) {
                    if (checkHoriz(j, i, dot)) return true;
                }
                if (isVertValid(i)) {
                    if (checkVert(j, i, dot)) return true;
                }
            }
        }
        return false;
    }

    // метод, проверяющий, заполнено ли поле
    static boolean isFieldFull() {
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (field[j][i] == empty) return false;
            }
        }
        return true;
    }

    // метод хода компьютера
    static void aiTurn() {
        isMoveDone = false; // Обозначаем, что на данный момент ход ещё не сделан
        System.out.println("Ходит компьютер");
        // Следующий цикл - попытка сыграть на победу или блокировку игрока
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                aiTurnUpDiag(j, i); // Пытаемся сходить по первой диагонали
                if (isMoveDone) break; // Если ход сделан, выходим из цикла
                aiTurnDownDiag(j, i); // Далее аналогично по второй диагонали, горизонтали и вертикали
                if (isMoveDone) break;
                aiTurnHoriz(j, i);
                if (isMoveDone) break;
                aiTurnVert(j, i);
                if (isMoveDone) break;
            }
            if (isMoveDone) break;
        }
        // Если не удалось сыграть на победу или блокировку, делаем обычный рандомный ход
        if (!isMoveDone) {
            do {
                x = random.nextInt(fieldSizeX);
                y = random.nextInt(fieldSizeY);
            } while (!isCoordCorrect(x, y));
            field[x][y] = dotAI;
            fieldDraw();
        }
    }

    // Метод, проверяющий целесообразность проверки по диагонали вверх
    static boolean isUpDiagValid(int x, int y) {
        return (x < fieldSizeX - 3 && y > fieldSizeY - 3);
    }

    // Метод, проверяющий диагональ вверх
    static boolean checkUpDiag(int x, int y, char dot) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            if (field[x++][y--] == dot) count++;
        }
        return count == 4;
    }

    // Метод, проверяющий целесообразность проверки по диагонали вниз
    static boolean isDownDiagValid(int x, int y) {
        return (x < fieldSizeX - 3 && y < fieldSizeY - 3);
    }

    // Метод, проверяющий диагональ вниз
    static boolean checkDownDiag(int x, int y, char dot) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            if (field[x++][y++] == dot) count++;
        }
        return count == 4;
    }

    // Метод, проверяющий целесообразность проверки по горизонтали
    static boolean isHorizValid(int x) {
        return (x < fieldSizeX - 3);
    }

    // Метод, проверяющий горизонталь
    static boolean checkHoriz(int x, int y, char dot) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            if (field[x++][y] == dot) count++;
        }
        return count == 4;
    }

    // Метод, проверяющий целесообразность проверки по вертикали
    static boolean isVertValid(int y) {
        return (y < fieldSizeX - 3);
    }

    // Метод, проверяющий вертикаль
    static boolean checkVert(int x, int y, char dot) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            if (field[x][y++] == dot) count++;
        }
        return count == 4;
    }

    // Попытка AI сходить по первой диагонали
    static void aiTurnUpDiag(int x, int y) {
        if (isUpDiagValid(x, y)) {
            int aiDotCount = 0;
            int playerDotCount = 0;
            int emptyDotCount = 0;
            for (int i = 0; i < 4; i++) {
                if (field[x][y] == dotPlayer) playerDotCount++;
                if (field[x][y] == dotAI) aiDotCount++;
                if (field[x][y] == empty) {
                    emptyDotCount++;
                    targetX = x;
                    targetY = y;
                }
                x++;
                y--;
            }
            if ((playerDotCount > difficulty || aiDotCount > difficulty) && emptyDotCount > 0) move();
        }
    }

    // Попытка AI сходить по второй диагонали
    static void aiTurnDownDiag(int x, int y) {
        if (isDownDiagValid(x, y)) {
            int aiDotCount = 0;
            int playerDotCount = 0;
            int emptyDotCount = 0;
            for (int i = 0; i < 4; i++) {
                if (field[x][y] == dotPlayer) playerDotCount++;
                if (field[x][y] == dotAI) aiDotCount++;
                if (field[x][y] == empty) {
                    emptyDotCount++;
                    targetX = x;
                    targetY = y;
                }
                x++;
                y++;
            }
            if ((playerDotCount > difficulty || aiDotCount > difficulty) && emptyDotCount > 0) move();
        }
    }

    // Попытка AI сходить по горизонтали
    static void aiTurnHoriz(int x, int y) {
        if (isHorizValid(x)) {
            int aiDotCount = 0;
            int playerDotCount = 0;
            int emptyDotCount = 0;
            for (int i = 0; i < 4; i++) {
                if (field[x][y] == dotPlayer) playerDotCount++;
                if (field[x][y] == dotAI) aiDotCount++;
                if (field[x][y] == empty) {
                    emptyDotCount++;
                    targetX = x;
                    targetY = y;
                }
                x++;
            }
            if ((playerDotCount > difficulty || aiDotCount > difficulty) && emptyDotCount > 0) move();
        }
    }

    // Попытка AI сходить по вертикали
    static void aiTurnVert(int x, int y) {
        if (isVertValid(y)) {
            int aiDotCount = 0;
            int playerDotCount = 0;
            int emptyDotCount = 0;
            for (int i = 0; i < 4; i++) {
                if (field[x][y] == dotPlayer) playerDotCount++;
                if (field[x][y] == dotAI) aiDotCount++;
                if (field[x][y] == empty) {
                    emptyDotCount++;
                    targetX = x;
                    targetY = y;
                }
                y++;
            }
            if ((playerDotCount > difficulty || aiDotCount > difficulty) && emptyDotCount > 0) move();
        }
    }

    // Метод, проставляющий символ AI в выбранную ячейку
    static void move() {
        field[targetX][targetY] = dotAI;
        fieldDraw();
        isMoveDone = true;
    }

}