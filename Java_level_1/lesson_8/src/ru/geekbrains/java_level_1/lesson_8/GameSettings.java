package ru.geekbrains.java_level_1.lesson_8;

public class GameSettings { // Класс, содержащий все настройки игры

    private int gameMode; // Против компьютера или другого игрока
    private int fieldSize;
    private int winLength;

    public GameSettings(int gameMode, int fieldSize, int winLength) {
        this.gameMode = gameMode;
        this.fieldSize = fieldSize;
        this.winLength = winLength;
    }

    public int getGameMode() {
        return gameMode;
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public int getWinLength() {
        return winLength;
    }
}
