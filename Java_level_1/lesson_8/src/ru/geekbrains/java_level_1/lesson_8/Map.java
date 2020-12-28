package ru.geekbrains.java_level_1.lesson_8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Map extends JPanel {

    private char[][] field;
    private int fieldSizeX;
    private int fieldSizeY;
    private int winLength;

    private int cellHeight;
    private int cellWidth;

    private int currentCellClickX = 0;
    private int currentCellClickY = 0;

    private boolean isInitialized = false;
    private boolean gameCondition = true;
    private boolean isMoveDone;
    private boolean gameOver = false;

    private char dotPlayer = 'X';
    private char dotAI = 'O';
    private char empty = ' ';

    private int targetX;
    private int targetY;

    private Random random = new Random();

    private GameSettings gameSettings;
    private GameWindow gameWindow;

    private String strPlayer1 = "   Ходит первый игрок. Выбери свободную ячейку.";
    private String strPlayer2 = "   Ходит второй игрок. Выбери свободную ячейку.";

    Map(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        setBackground(Color.ORANGE);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                currentCellClickX = e.getX() / cellWidth;
                currentCellClickY = e.getY() / cellHeight;
                if (isCoordCorrect(currentCellClickX, currentCellClickY)) playerTurn();
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
        this.gameSettings = gameSettings;
        gameWindow.textField.setText(strPlayer1);
        fieldSizeX = gameSettings.getFieldSize();
        fieldSizeY = gameSettings.getFieldSize();
        cellWidth = getWidth() / fieldSizeX;
        cellHeight = getHeight() / fieldSizeY;
        winLength = gameSettings.getWinLength();
        field = new char[fieldSizeX][fieldSizeY];
        isInitialized = true;
        repaint();
        fieldInit(fieldSizeX, fieldSizeY);
        gameCondition = true;
    }

    // метод хода игрока
    private void playerTurn() {
        char currentDot;
        if (gameCondition) currentDot = dotPlayer;
        else currentDot = dotAI;
        field[currentCellClickX][currentCellClickY] = currentDot;
        repaint();
        if (gameCondition && gameSettings.getGameMode() == 1) gameWindow.textField.setText(strPlayer2);
        else gameWindow.textField.setText(strPlayer1);
        gameCondition = !gameCondition;
        if (isWin(currentDot)) {
            gameOver = true;
            if (currentDot == dotPlayer) new WinWindow(1, this, gameWindow);
            if (currentDot == dotAI && gameSettings.getGameMode() == 1)
                new WinWindow(2, this, gameWindow);
        }
        if (!gameOver && isFieldFull()) {
            new WinWindow(3, this, gameWindow);
        }
        if (gameSettings.getGameMode() == 1) return;
        else {
            if (!gameOver) aiTurn();
            gameCondition = !gameCondition;
        }
    }

    // метод хода компьютера
    private void aiTurn() {
        isMoveDone = false; // Обозначаем, что на данный момент ход ещё не сделан
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
                targetX = random.nextInt(fieldSizeX);
                targetY = random.nextInt(fieldSizeY);
            } while (!isCoordCorrect(targetX, targetY));
            move();
        }
        repaint();
        if (isWin(dotAI)) {
            gameOver = true;
            new WinWindow(0, this, gameWindow);
        }
        if (isFieldFull()) {
            new WinWindow(3, this, gameWindow);
        }
    }

    // Метод, проставляющий символ AI в выбранную ячейку
    private void move() {
        field[targetX][targetY] = dotAI;
        repaint();
        isMoveDone = true;
    }

    // Попытка AI сходить по первой диагонали
    private void aiTurnUpDiag(int x, int y) {
        if (isUpDiagValid(x, y)) {
            int aiDotCount = 0;
            int playerDotCount = 0;
            int emptyDotCount = 0;
            for (int i = 0; i < winLength; i++) {
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
            if ((playerDotCount > winLength - 2 || aiDotCount > winLength - 2) && emptyDotCount > 0) move();
        }
    }

    // Попытка AI сходить по второй диагонали
    private void aiTurnDownDiag(int x, int y) {
        if (isDownDiagValid(x, y)) {
            int aiDotCount = 0;
            int playerDotCount = 0;
            int emptyDotCount = 0;
            for (int i = 0; i < winLength; i++) {
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
            if ((playerDotCount > winLength - 2 || aiDotCount > winLength - 2) && emptyDotCount > 0) move();
        }
    }

    // Попытка AI сходить по горизонтали
    private void aiTurnHoriz(int x, int y) {
        if (isHorizValid(x)) {
            int aiDotCount = 0;
            int playerDotCount = 0;
            int emptyDotCount = 0;
            for (int i = 0; i < winLength; i++) {
                if (field[x][y] == dotPlayer) playerDotCount++;
                if (field[x][y] == dotAI) aiDotCount++;
                if (field[x][y] == empty) {
                    emptyDotCount++;
                    targetX = x;
                    targetY = y;
                }
                x++;
            }
            if ((playerDotCount > winLength - 2 || aiDotCount > winLength - 2) && emptyDotCount > 0) move();
        }
    }

    // Попытка AI сходить по вертикали
    private void aiTurnVert(int x, int y) {
        if (isVertValid(y)) {
            int aiDotCount = 0;
            int playerDotCount = 0;
            int emptyDotCount = 0;
            for (int i = 0; i < winLength; i++) {
                if (field[x][y] == dotPlayer) playerDotCount++;
                if (field[x][y] == dotAI) aiDotCount++;
                if (field[x][y] == empty) {
                    emptyDotCount++;
                    targetX = x;
                    targetY = y;
                }
                y++;
            }
            if ((playerDotCount > winLength - 2 || aiDotCount > winLength - 2) && emptyDotCount > 0) move();
        }
    }

    // метод, проверяющий победил ли игрок или компьютер
    private boolean isWin(char dot) {
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
    private boolean isFieldFull() {
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (field[j][i] == empty) return false;
            }
        }
        gameOver = true;
        repaint();
        return true;
    }

    // Метод, проверяющий целесообразность проверки по диагонали вверх
    private boolean isUpDiagValid(int x, int y) {
        return (x <= fieldSizeX - winLength && y >= winLength - 1);
    }

    // Метод, проверяющий диагональ вверх
    private boolean checkUpDiag(int x, int y, char dot) {
        int count = 0;
        for (int i = 0; i < winLength; i++) {
            if (field[x++][y--] == dot) count++;
        }
        return count == winLength;
    }

    // Метод, проверяющий целесообразность проверки по диагонали вниз
    private boolean isDownDiagValid(int x, int y) {
        return (x <= fieldSizeX - winLength && y <= fieldSizeY - winLength);
    }

    // Метод, проверяющий диагональ вниз
    private boolean checkDownDiag(int x, int y, char dot) {
        int count = 0;
        for (int i = 0; i < winLength; i++) {
            if (field[x++][y++] == dot) count++;
        }
        return count == winLength;
    }

    // Метод, проверяющий целесообразность проверки по горизонтали
    private boolean isHorizValid(int x) {
        return (x <= fieldSizeX - winLength);
    }

    // Метод, проверяющий горизонталь
    private boolean checkHoriz(int x, int y, char dot) {
        int count = 0;
        for (int i = 0; i < winLength; i++) {
            if (field[x++][y] == dot) count++;
        }
        return count == winLength;
    }

    // Метод, проверяющий целесообразность проверки по вертикали
    private boolean isVertValid(int y) {
        return (y <= fieldSizeX - winLength);
    }

    // Метод, проверяющий вертикаль
    private boolean checkVert(int x, int y, char dot) {
        int count = 0;
        for (int i = 0; i < winLength; i++) {
            if (field[x][y++] == dot) count++;
        }
        return count == winLength;
    }

    private void drawX(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
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
    private boolean isCoordCorrect(int x, int y) {
        return (x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY && field[x][y] == empty);
    }

    // метод, инициализирующий поле
    private void fieldInit(int x, int y) {
        field = new char[x][y];
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                field[j][i] = empty;
            }
        }
    }

    private void render (Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
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
