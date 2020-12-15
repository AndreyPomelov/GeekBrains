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
    static int smartMoveStatus; // переменная, используемая в логике хода AI
    static int difficulty = 2; // переменная, регулирующая сложность игры
    // Регулирует реакцию AI на выстраиваемые последовательности символов.
    // Добавлена ради эксперимента, менять не рекомендуется. 2 - оптимальное значение.

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
        int dotCount = 0;
        // проверяем вертикали и горизонтали
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (field[j][i] == dot) dotCount++;
                else dotCount = 0;
                if (dotCount == 4) return true;
            }
            dotCount = 0;
        }
        for (int i = 0; i < fieldSizeX; i++) {
            for (int j = 0; j < fieldSizeY; j++) {
                if (field[i][j] == dot) dotCount++;
                else dotCount = 0;
                if (dotCount == 4) return true;
            }
            dotCount = 0;
        }
        // проверяем все диагонали, в которых по условию возможна победа
        for (int i = 0, j = 0; i < fieldSizeX; i++, j++) {
            if (field[i][j] == dot) dotCount++;
            else dotCount = 0;
            if (dotCount == 4) return true;
        }
        dotCount = 0;
        for (int i = (fieldSizeY - 1), j = 0; j < fieldSizeX; i--, j++) {
            if (field[i][j] == dot) dotCount++;
            else dotCount = 0;
            if (dotCount == 4) return true;
        }
        dotCount = 0;
        for (int i = 0, j = 1; i < fieldSizeX - 1; i++, j++) {
            if (field[i][j] == dot) dotCount++;
            else dotCount = 0;
            if (dotCount == 4) return true;
        }
        dotCount = 0;
        for (int i = 1, j = 0; i < fieldSizeX; i++, j++) {
            if (field[i][j] == dot) dotCount++;
            else dotCount = 0;
            if (dotCount == 4) return true;
        }
        dotCount = 0;
        for (int i = fieldSizeX - 2, j = 0; i >= 0; i--, j++) {
            if (field[i][j] == dot) dotCount++;
            else dotCount = 0;
            if (dotCount == 4) return true;
        }
        dotCount = 0;
        for (int i = fieldSizeX - 1, j = 1; i > 0; i--, j++) {
            if (field[i][j] == dot) dotCount++;
            else dotCount = 0;
            if (dotCount == 4) return true;
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
        System.out.println("Ходит компьютер");
        smartMoveStatus = analyzeX(); // Анализируем строки
        if (smartMoveStatus != -1) smartMoveX(); // Если анализ успешен, реализуем логику хода по строкам
        if (smartMoveStatus != -2) smartMoveStatus = analyzeY(); // Если ход ещё не сделан, анализируем столбцы
        if (smartMoveStatus >= 0) smartMoveY(); // Если анализ успешен, реализуем логику хода по столбцам
        // Если ни один из проведённых анализов не дал результата, делаем обычный рандомный ход
        if (smartMoveStatus != -2) {
            do {
                x = random.nextInt(fieldSizeX);
                y = random.nextInt(fieldSizeY);
            } while (!isCoordCorrect(x, y));
            field[x][y] = dotAI;
            fieldDraw();
        }
        smartMoveStatus = -1;
    }

    // Метод, анализирующий строки поля
    static int analyzeX() {
        int playerDotCount = 0;
        int aiDotCount = 0;
        int emptyDotCount = 0;
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (field[j][i] == dotPlayer) playerDotCount++;
                else if (field[j][i] == dotAI) aiDotCount++;
                else emptyDotCount++;
            }
            // Возвращаем координату строки, в которой нашлась искомая последовательность
            if ((playerDotCount > difficulty || aiDotCount > 1) && emptyDotCount > 0) return i;
            playerDotCount = aiDotCount = emptyDotCount = 0;
        }
        return -1;
    }

    // Метод, анализирующий столбцы поля
    static int analyzeY() {
        int playerDotCount = 0;
        int aiDotCount = 0;
        int emptyDotCount = 0;
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (field[i][j] == dotPlayer) playerDotCount++;
                else if (field[i][j] == dotAI) aiDotCount++;
                else emptyDotCount++;
            }
            // Возвращаем координату столбца, в котором нашлась искомая последовательность
            if ((playerDotCount > difficulty || aiDotCount > 1) && emptyDotCount > 0) return i;
            playerDotCount = aiDotCount = emptyDotCount = 0;
        }
        return -1;
    }

    // Метод логики хода AI по строкам
    // Логика конечно примитивная, здесь можно ещё работать и работать.
    // Но по крайней мере, это уже не просто "тупой рандом" =)
    // Благодаря двум верхним методам анализа и двум нижним методам логики хода,
    // в первую очередь AI пытается либо сделать такой ход, чтобы выиграть,
    // либо заблокировать последовательность, которую выстраивает игрок.
    // В цикле взяты две управляющие переменные, для того чтобы AI в первую
    // очередь ходил ближе к середине строки или столбца, т.к. если, например,
    // игрок строит свою последовательность с конца строки, то бессмысленно
    // пытаться сделать блокирующий ход в начале строки. И наоборот.
    // Не стал доделывать логику блокировки по диагоналям, т.к. принцип один и тот же.
    static void smartMoveX() {
        for (int i = fieldSizeX / 2, j = fieldSizeX / 2; i >=0; i--, j++) {
            if (field[i][smartMoveStatus] == empty) {
                field[i][smartMoveStatus] = dotAI;
                fieldDraw();
                smartMoveStatus = -2;
                break;
            }
            if (field[j][smartMoveStatus] == empty) {
                field[j][smartMoveStatus] = dotAI;
                fieldDraw();
                smartMoveStatus = -2;
                break;
            }
        }
    }

    // Метод логики хода AI по столбцам
    static void smartMoveY() {
        for (int i = fieldSizeY / 2, j = fieldSizeY / 2; i >=0; i--, j++) {
            if (field[smartMoveStatus][i] == empty) {
                field[smartMoveStatus][i] = dotAI;
                fieldDraw();
                smartMoveStatus = -2;
                break;
            }
            if (field[smartMoveStatus][j] == empty) {
                field[smartMoveStatus][j] = dotAI;
                fieldDraw();
                smartMoveStatus = -2;
                break;
            }
        }
    }
}