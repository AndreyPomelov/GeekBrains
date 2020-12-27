package ru.geekbrains.java_level_1.lesson_8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Map extends JPanel {

    public static final int MODE_VS_AI = 0;
    public static final int MODE_VS_HUMAN = 1;

    private char[][] field;
    private int fieldSizeX;
    private int fieldSizeY;
    private int winLength;

    private int cellHeight;
    private int cellWidth;

    private int currentCellClickX = 0;
    private int currentCellClickY = 0;

    boolean isInitialized = false;
    boolean gameCondition = true;
    boolean isPressed = false;

    char dotPlayer = 'X';
    char dotAI = 'O';
    char empty = ' ';

    Map() {
        setBackground(Color.ORANGE);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                currentCellClickX = e.getX() / cellWidth;
                currentCellClickY = e.getY() / cellHeight;
                System.out.println("X " + currentCellClickX + " Y " + currentCellClickY);
                if (isCoordCorrect(currentCellClickX, currentCellClickY)) {
                    if (gameCondition) playerTurn();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
        drawX(g);
    }

    void startNewGame (GameSettings gameSettings) {
        fieldSizeX = gameSettings.getFieldSize();
        fieldSizeY = gameSettings.getFieldSize();
        cellWidth = getWidth() / fieldSizeX;
        cellHeight = getHeight() / fieldSizeY;
        winLength = gameSettings.getWinLength();
        field = new char[fieldSizeX][fieldSizeY];
        isInitialized = true;
        repaint();
        fieldInit(fieldSizeX, fieldSizeY);
    }

    void game() {
        playerTurn();

    }

    // метод хода игрока
    void playerTurn() {
        field[currentCellClickX][currentCellClickY] = dotPlayer;
        System.out.println("Метка поставлена " + currentCellClickX + " " + currentCellClickY);
        repaint();
        System.out.println("Игрок сходил успешно");
        gameCondition = !gameCondition;
        if (isWin(dotPlayer)) {
            System.out.println("PLAYER WIN");
        }
    }

    // метод, проверяющий победил ли игрок или компьютер
    boolean isWin(char dot) {
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

    // Метод, проверяющий целесообразность проверки по диагонали вверх
    boolean isUpDiagValid(int x, int y) {
        return (x <= fieldSizeX - winLength && y >= winLength - 1);
    }

    // Метод, проверяющий диагональ вверх
    boolean checkUpDiag(int x, int y, char dot) {
        int count = 0;
        for (int i = 0; i < winLength; i++) {
            if (field[x++][y--] == dot) count++;
        }
        return count == winLength;
    }

    // Метод, проверяющий целесообразность проверки по диагонали вниз
    boolean isDownDiagValid(int x, int y) {
        return (x <= fieldSizeX - winLength && y <= fieldSizeY - winLength);
    }

    // Метод, проверяющий диагональ вниз
    boolean checkDownDiag(int x, int y, char dot) {
        int count = 0;
        for (int i = 0; i < winLength; i++) {
            if (field[x++][y++] == dot) count++;
        }
        return count == winLength;
    }

    // Метод, проверяющий целесообразность проверки по горизонтали
    boolean isHorizValid(int x) {
        return (x <= fieldSizeX - winLength);
    }

    // Метод, проверяющий горизонталь
    boolean checkHoriz(int x, int y, char dot) {
        int count = 0;
        for (int i = 0; i < winLength; i++) {
            if (field[x++][y] == dot) count++;
        }
        return count == winLength;
    }

    // Метод, проверяющий целесообразность проверки по вертикали
    boolean isVertValid(int y) {
        return (y <= fieldSizeX - winLength);
    }

    // Метод, проверяющий вертикаль
    boolean checkVert(int x, int y, char dot) {
        int count = 0;
        for (int i = 0; i < winLength; i++) {
            if (field[x][y++] == dot) count++;
        }
        return count == winLength;
    }



    void drawX(Graphics g) {
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (field[j][i] == dotPlayer){
                    g.drawLine(cellWidth * j + 10, cellHeight * i + 10,
                            cellWidth * (j + 1) - 10, cellHeight * (i + 1) - 10);
                    g.drawLine(cellWidth * (j + 1) - 10, cellHeight * i + 10,
                            cellWidth * j + 10, cellHeight * (i + 1) - 10);
                }
                if (field[j][i] == dotAI) {
                    g.drawOval(cellWidth * j + 10, cellHeight * i + 10,
                            cellWidth - 20, cellHeight - 20);
                }
            }
        }

    }

    // метод, проверяющий корректность координат
    boolean isCoordCorrect(int x, int y) {
        return (x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY && field[x][y] == empty);
    }

    // метод, инициализирующий поле
    void fieldInit(int x, int y) {
        field = new char[x][y];
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                field[j][i] = empty;
            }
        }
    }

    void update(MouseEvent e) {
        currentCellClickX = e.getX() / cellWidth;
        currentCellClickY = e.getY() / cellHeight;
        repaint();
    }

    void render (Graphics g) {
        if (!isInitialized) return;

        int panelHeight = getHeight();
        int panelWidth = getWidth();

        cellHeight = panelHeight / fieldSizeY;
        cellWidth = panelWidth / fieldSizeX;

        for (int i = 0; i < fieldSizeY; i++) {
            int y = i * cellHeight;
            g.drawLine(0, y, panelWidth, y);
        }

        for (int i = 0; i < fieldSizeX; i++) {
            int x = i * cellWidth;
            g.drawLine(x, 0, x, panelHeight);
        }
    }
}
