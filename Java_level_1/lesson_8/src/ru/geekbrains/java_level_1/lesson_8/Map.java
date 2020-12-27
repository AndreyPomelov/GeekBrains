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

    private int currentCellClickX = -1;
    private int currentCellClickY = -1;
    private boolean isClicked;

    boolean isInitialized = false;

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
                isClicked = true;
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
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
        game();
    }

    void game() {

    }

    // метод хода игрока
    void playerTurn() {
        do {

        } while (!isCoordCorrect(currentCellClickX, currentCellClickY));
        field[currentCellClickX][currentCellClickY] = dotPlayer;
        repaint();
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
