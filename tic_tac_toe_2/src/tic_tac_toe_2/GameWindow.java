package tic_tac_toe_2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {

    private final String WINDOW_TITLE = "Игра \"Крестики-нолики\"";
    private final int WINDOW_HEIGHT = 600;
    private final int WINDOW_WIDTH = 500;
    private final int WINDOW_POS_X = 50;
    private final int WINDOW_POS_Y = 50;
    private final JLabel gameInfo = new JLabel();
    Field field = new Field();
    private final JButton jButtonStartNewGame = new JButton("Старт");
    private final JButton jButtonExit = new JButton("Выход");
    private final JPanel buttonsPanel = new JPanel(new GridLayout(1, 2));

    public void setField(Field field) {
        this.field = field;
    }

    public GameWindow() {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle(WINDOW_TITLE);
        setBounds(WINDOW_POS_X, WINDOW_POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setLayout(new BorderLayout());
        add(gameInfo, BorderLayout.NORTH);
        gameInfo.setText("   Нажми \"Старт\" для начала игры!");
        add(field, BorderLayout.CENTER);
        buttonsPanel.add(jButtonStartNewGame);
        buttonsPanel.add(jButtonExit);
        add(buttonsPanel, BorderLayout.SOUTH);
        jButtonStartNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createGameSettingsWindow();
            }
        });
        jButtonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        setVisible(true);
    }

    void createGameSettingsWindow() {
        new SettingsWindow(this);
    }

}
