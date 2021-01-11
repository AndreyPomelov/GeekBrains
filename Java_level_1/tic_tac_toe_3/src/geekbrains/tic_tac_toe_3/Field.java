package geekbrains.tic_tac_toe_3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Field extends JPanel {

    private int numberOfCellsX;
    private int numberOfCellsY;
    private int cellWidth;
    private int cellHeight;
    private Player player1;
    private Player player2;
    private final char emptyDot = '.';
    static char[][] map;
    private Settings settings;
    private boolean isInitialized = false;
    private final GameWindow gameWindow;

    Field(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    void fieldInit(Player player1, Player player2, Settings settings) {
        setBackground(Color.GREEN);
        this.player1 = player1;
        this.player2 = player2;
        this.settings = settings;
        map = new char[settings.getFieldSize()][settings.getFieldSize()];
        numberOfCellsX = settings.getFieldSize();
        numberOfCellsY = settings.getFieldSize();
        cellWidth = getWidth() / numberOfCellsX;
        cellHeight = getHeight() / numberOfCellsY;
        mapInit();
        setVisible(true);
        isInitialized = true;
        if (player1.isAI) gameWindow.setText("   По щелчку мыши сходит " + player1.getName() + ".");
        else gameWindow.setText("   Ходит " + player1.getName() + ". Выбери свободную ячейку.");
        GameLogic gameLogic = new GameLogic(player1, player2, settings, gameWindow, this);
        player1.setGameLogic(gameLogic);
        player2.setGameLogic(gameLogic);
        repaint();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int cellX;
                int cellY;
                cellX = e.getX() / cellWidth;
                if (cellX == numberOfCellsX) cellX--;
                cellY = e.getY() / cellHeight;
                if (cellY == numberOfCellsY) cellY--;
                gameLogic.move(cellX, cellY);
            }
        });
    }

    void mapInit() {
        for (int i = 0; i < numberOfCellsY; i++) {
            for (int j = 0; j < numberOfCellsX; j++) {
                map[j][i] = emptyDot;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawLines(g);
        drawX(g);
    }

    void drawLines(Graphics g) {
        if (!isInitialized) return;

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));

        for (int i = 1; i < numberOfCellsY; i++) {
            int y = i * cellHeight;
            g.drawLine(0, y, getWidth(), y);
        }

        for (int i = 1; i < numberOfCellsX; i++) {
            int x = i * cellWidth;
            g.drawLine(x, 0, x, getHeight());
        }
    }

    private void drawX(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        for (int i = 0; i < numberOfCellsY; i++) {
            for (int j = 0; j < numberOfCellsX; j++) {
                if (map[j][i] == player1.getDot()){
                    g.drawLine(cellWidth * j + 10, cellHeight * i + 10,
                            cellWidth * (j + 1) - 10, cellHeight * (i + 1) - 10);
                    g.drawLine(cellWidth * (j + 1) - 10, cellHeight * i + 10,
                            cellWidth * j + 10, cellHeight * (i + 1) - 10);
                }
                if (map[j][i] == player2.getDot()) {
                    g.drawOval(cellWidth * j + 10, cellHeight * i + 10,
                            cellWidth - 20, cellHeight - 20);
                }
            }
        }
    }
}
