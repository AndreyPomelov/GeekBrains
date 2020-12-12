package ru.geekbrains.java_level_1.lesson_3;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

//        Задание 1.

        gameStart();

//        Задание 2.

        wordsGame();

//      Задание 3.

        String s = "Предложение     один  Теперь     предложение    два   Тут  предложение          три";

        System.out.println(s);
        String s1 = s.replaceAll(" +", " ");
        System.out.println(s1);

        StringBuilder s2 = new StringBuilder(s1);

        for (int i = 1; i < s1.length(); i++) {
            if (s1.charAt(i) >= 'А' && s1.charAt(i) <= 'Я') {
                s2.setCharAt(i - 1, '.');
            }
        }

        System.out.println(s2);

        StringBuilder s3 = new StringBuilder(s2);

        int moveError = 0;

        for (int i = 0; i < s2.length(); i++) {
            if (s2.charAt(i) == '.') {
                s3.insert(i + 1 + moveError, ' ');
                moveError++;
            }
        }

        s3.insert(s3.length(), '.');

        System.out.println(s3);


    }

    public static void gameStart() {

        boolean continueGame = true;
        while (continueGame) {

            Random random = new Random();
            int number = random.nextInt(10);
            int tryCount = 3;
            int answer;
            Scanner scanner = new Scanner(System.in);
            System.out.println("Угадайте число от 0 до 9.");

            for (int i = 0; i < tryCount; i++) {
                answer = scanner.nextInt();
                if (answer < 0 || answer > 9) System.out.println("Вы ввели число не в заданном диапазоне.");
                else if (answer == number) {
                    System.out.println("Вы угадали!");
                    break;
                } else if (answer < number) System.out.println("Загаданное число больше.");
                else System.out.println("Загаданное число меньше.");
                if (i == 2) System.out.println("Попытки закончились!");
            }

            System.out.println("Повторить игру ещё раз? 1 - да / 0 нет.");
             answer = scanner.nextInt();
            if (answer == 0) continueGame = false;

        }

    }

    public static void wordsGame() {

        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry",
                "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea",
                "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
        Random random = new Random();
        String question = words[random.nextInt(words.length)];
        System.out.println("Угадайте загаданное слово.");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String answer = scanner.nextLine();
            if (answer.equals(question)) {
                System.out.println("Вы угадали!");
                break;
            } else {
                for (int i = 0; i < 15; i++) {
                    if (i >= answer.length() || i >= question.length()) System.out.print("#");
                    else if (answer.charAt(i) == question.charAt(i)) System.out.print(answer.charAt(i));
                    else System.out.print("#");
                }
                System.out.println("");
            }
        }


    }

}
