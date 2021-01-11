package geekbrains.tic_tac_toe_3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {

    private static SettingsWindow settingsWindow;
    private static Settings settings;
    private static Field field;
    private static Player player1;
    private static Player player2;
    private final char player1Dot = 'X';
    private final char player2Dot = 'O';

    private final int WINDOW_POS_X = 500;
    private final int WINDOW_POS_Y = 200;
    private final int WINDOW_WIDTH = 531;
    private final int WINDOW_HEIGHT = 600;

    private final JTextField infoTextField = new JTextField("   Автор - Андрей Помелов, г. Ижевск, 2021 г.");
    private final JButton jbtStartNewGame;
    private final JButton jbtExit;

    GameWindow () {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Игра \"Крестики-нолики\"");
        setBounds(WINDOW_POS_X, WINDOW_POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);

        add(infoTextField, BorderLayout.NORTH);
        field = new Field(this);
        add(GameWindow.field, BorderLayout.CENTER);
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2));
        jbtStartNewGame = new JButton("Старт новой игры");
        jbtExit = new JButton("Выход");
        bottomPanel.add(jbtStartNewGame);
        bottomPanel.add(jbtExit);
        add(bottomPanel, BorderLayout.SOUTH);
        jbtStartNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingsWindow = new SettingsWindow(GameWindow.this);
                settingsWindow.setVisible(true);
            }
        });
        jbtExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    void newGameStart(Settings settings) {

        GameWindow.settings = settings;
        createPlayers();
        field.fieldInit(player1, player2, settings);
    }

    void createPlayers() {

        player1 = new HumanPlayer(settings.getPlayer1Name(), player1Dot);
        if (settings.getGameMode() == 0) player2 = new SkyNetPlayer("SkyNet", player2Dot);
        else player2 = new HumanPlayer(settings.getPlayer2Name(), player2Dot);
        int swap = (int)(Math.random() * 2);
        if (swap == 1) {
            Player tempPlayer = player1;
            player1 = player2;
            player2 = tempPlayer;
            player1.setDot(player1Dot);
            player2.setDot(player2Dot);
        }
    }

    public void setText(String string) {
        infoTextField.setText(string);
    }

    public void gameOver(Player player, boolean isWin) {
        new GameOverWindow(player, isWin, this);
    }
}
