package geekbrains.tic_tac_toe_3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverWindow extends JFrame {

    private Player player;
    private GameWindow gameWindow;
    private boolean isWin;

    private final int WINDOW_WIDTH = 300;
    private final int WINDOW_HEIGHT = 200;
    private JLabel jLabel;
    private JButton jButton = new JButton("OK");

    GameOverWindow(Player player, boolean isWin, GameWindow gameWindow) {
        this.player = player;
        this.gameWindow = gameWindow;
        this.isWin = isWin;
        setTitle("Игра окончена");
        setResizable(false);
        Rectangle rectangle = new Rectangle(gameWindow.getBounds());
        int posX = (int) rectangle.getCenterX() - WINDOW_WIDTH / 2;
        int posY = (int) rectangle.getCenterY() - WINDOW_HEIGHT / 2;
        setBounds(posX, posY, WINDOW_WIDTH, WINDOW_HEIGHT);
        addText();
        add(jButton, BorderLayout.SOUTH);
        setVisible(true);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void addText() {
        jLabel = new JLabel();
        if (isWin) jLabel.setText("   В игре побеждает " + player.getName() + "!");
        else jLabel.setText("   Ничья!");
        add(jLabel, BorderLayout.CENTER);
    }


}
