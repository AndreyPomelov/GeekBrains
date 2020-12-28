package ru.geekbrains.java_level_1.lesson_8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WinWindow extends JFrame {

    private final int WINDOW_WIDTH = 200;
    private final int WINDOW_HEIGHT = 150;
    private GameWindow gameWindow;

    WinWindow (int whoIsWin, Map field, GameWindow _gameWindow) {
        gameWindow = _gameWindow;
        setTitle("Игра окончена");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        Rectangle gameWindowBounds = field.getBounds();
        int posX = (int)gameWindowBounds.getCenterX() - WINDOW_WIDTH / 2;
        int posY = (int)gameWindowBounds.getCenterY() - WINDOW_HEIGHT / 2;
        setLocation(posX, posY);
        JLabel winLabel = new JLabel();
        if (whoIsWin == 0) winLabel.setText("   Победил компьютер!");
        if (whoIsWin == 1) winLabel.setText("   Победил игрок №1!");
        if (whoIsWin == 2) winLabel.setText("   Победил игрок №2!");
        if (whoIsWin == 3) winLabel.setText("   Ничья!");
        setLayout(new BorderLayout());
        add(winLabel, BorderLayout.CENTER);
        JButton buttonOK = new JButton("ОК");
        add(buttonOK, BorderLayout.SOUTH);
        setVisible(true);
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                gameWindow.setVisible(false);
                new GameWindow();
            }
        });
    }
}
