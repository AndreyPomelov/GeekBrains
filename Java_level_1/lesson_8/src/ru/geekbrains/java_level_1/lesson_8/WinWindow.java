package ru.geekbrains.java_level_1.lesson_8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WinWindow extends JFrame {

    private int WINDOW_WIDTH = 200;
    private int WINDOW_HEIGHT = 150;

    WinWindow (int whoIsWin, Map field) {
        setTitle("Игра окончена");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        Rectangle gameWindowBounds = field.getBounds();
        int posX = (int)gameWindowBounds.getCenterX() - WINDOW_WIDTH / 2;
        int posY = (int)gameWindowBounds.getCenterY() - WINDOW_HEIGHT / 2;
        setLocation(posX, posY);
        JLabel winLabel = new JLabel();
        if (whoIsWin == 0) winLabel.setText("Победил компьютер!");
        if (whoIsWin == 1) winLabel.setText("Победил игрок №1!");
        if (whoIsWin == 2) winLabel.setText("Победил игрок №2!");
        setLayout(new BorderLayout());
        add(winLabel, BorderLayout.CENTER);
        JButton buttonOK = new JButton("ОК");
        add(buttonOK, BorderLayout.SOUTH);
        setVisible(true);
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new GameWindow();
            }
        });
    }
}
