package tic_tac_toe_2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Field extends JPanel {

    private boolean whichPlayerActive = true;
    private int fieldSize;
    private int winLength;
    Player player1;
    Player player2;

    public Field() {

        setBackground(Color.GREEN);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {

            }
        });

    }

    void startGame(Settings settings) {
        fieldSize = settings.getFieldSize();
        winLength = settings.getWinLength();
        fieldInit();
        player1 = new HumanPlayer(settings.getPlayer1Name(), 'X');
        if (settings.getGameMode() == 0) player2 = new SkyNetPlayer("SkyNet", 'O');
        else player2 = new HumanPlayer(settings.getPlayer2Name(), 'O');
        int swap = (int)(Math.random()*2);
        if (swap == 1) {
            Player tempPlayer;
            tempPlayer = player1;
            player1 = player2;
            player2 = tempPlayer;
            player1.setDot('X');
            player2.setDot('O');
        }

    }

    void fieldInit() {

    }
}
