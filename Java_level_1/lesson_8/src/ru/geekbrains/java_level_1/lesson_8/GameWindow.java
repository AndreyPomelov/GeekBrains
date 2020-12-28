package ru.geekbrains.java_level_1.lesson_8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {

    private static final int WINDOW_POS_X = 300;
    private static final int WINDOW_POS_Y = 300;
    private static final int WINDOW_HEIGHT = 580;
    private static final int WINDOW_WIDTH = 508;
    StartNewGameWindow startNewGameWindow;
    Map field;
    TextField textField;

    public GameWindow() {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(WINDOW_POS_X, WINDOW_POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Игра Крестики-Нолики");
        setResizable(false);

        JPanel bottomPanel = new JPanel(new GridLayout(1, 2));
        JButton btnStartNewGame = new JButton("Старт");
        JButton btnExit = new JButton("Выход");
        bottomPanel.add(btnStartNewGame);
        bottomPanel.add(btnExit);
        add(bottomPanel, BorderLayout.SOUTH);
        field = new Map(this);
        add(field, BorderLayout.CENTER);
        textField = new TextField();
        textField.setEditable(false);
        add(textField, BorderLayout.NORTH);
        textField.setText("   Игра Крестики-нолики. Для начала игры нажми кнопку Старт.");
        startNewGameWindow = new StartNewGameWindow(this, field);

        btnStartNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startNewGameWindow.setVisible(true);
            }
        });

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);

    }
}
