package my_practice.cat_quest_2.windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfirmWindow extends JFrame {

    private final int WIN_WIDTH = 300;
    private final int WIN_HEIGHT = 200;
    private final int WIN_POS_X = 800;
    private final int WIN_POS_Y = 300;

    private final JLabel jLabel = new JLabel("   Вы уверены?");
    private final JPanel jPanelButtons = new JPanel();
    private final JButton jButtonExit = new JButton("Выход");
    private final JButton jButtonCancel = new JButton("Отмена");

    public ConfirmWindow() {
        setTitle("Выйти из игры?");
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setBounds(WIN_POS_X, WIN_POS_Y, WIN_WIDTH, WIN_HEIGHT);
        setResizable(false);
        add(jLabel, BorderLayout.CENTER);
        jPanelButtons.setLayout(new GridLayout(1, 2));
        jPanelButtons.add(jButtonExit);
        jPanelButtons.add(jButtonCancel);
        add(jPanelButtons, BorderLayout.SOUTH);
        addListeners();
        setVisible(true);
    }

    private void addListeners() {
        jButtonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        jButtonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }
}
