package my_practice.cat_quest_2.windows;

import my_practice.cat_quest_2.Logic;
import my_practice.cat_quest_2.animals.*;
import my_practice.cat_quest_2.windows.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {

    private final int WIN_WIDTH = 500;
    private final int WIN_HEIGHT = 500;
    private final int WIN_POS_X = 700;
    private final int WIN_POS_Y = 200;

    private Cat cat;
    private Dog dog;
    private Mouse mouse;
    private Rat rat;

    private StartWindow startWindow;
    private GameOverWindow gameOverWindow;
    private ConfirmWindow confirmWindow;

    private Logic logic = new Logic();

    private final JButton jbStartNewGame = new JButton("Новая игра");
    private final JButton jbRecon = new JButton("Разведка");
    private final JButton jbHunt = new JButton("Охота");
    private final JButton jbEat = new JButton("Жрать");
    private final JButton jbVal = new JButton("Балдеть");
    private final JButton jbAttackBoss = new JButton("Атаковать босса");
    private final JButton jbExit = new JButton("Выход");

    private final JPanel jPanelButtons = new JPanel();
    private final JPanel leftInfoPanel = new JPanel();
    private final JPanel rightInfoPanel = new JPanel();
    private final JLabel jLabelStatus = new JLabel();
    private final JLabel jLabelBossStatus = new JLabel();
    private final JPanel mainPanel = new JPanel();
    private final JTextArea jTextAreaMain = new JTextArea();

    public MainWindow() {
        setTitle("Игра \"Котоквест\"");
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setBounds(WIN_POS_X, WIN_POS_Y, WIN_WIDTH, WIN_HEIGHT);
        setResizable(false);
        jPanelButtons.setLayout(new GridLayout(2, 3));
        jPanelButtons.add(jbRecon);
        jPanelButtons.add(jbHunt);
        jPanelButtons.add(jbEat);
        jPanelButtons.add(jbVal);
        jPanelButtons.add(jbAttackBoss);
        jPanelButtons.add(jbExit);
        add(jPanelButtons, BorderLayout.SOUTH);
        add(jbStartNewGame, BorderLayout.NORTH);
        leftInfoPanel.add(jLabelStatus);
        add(leftInfoPanel, BorderLayout.WEST);
        mainPanel.add(jTextAreaMain);
        add(mainPanel, BorderLayout.CENTER);
        jTextAreaMain.setEditable(false);
        jTextAreaMain.setText("Игра \"Котоквест\"\nАвтор - Андрей Помелов, г. Ижевск, 2021 г.");
        rightInfoPanel.add(jLabelBossStatus);
        add(rightInfoPanel, BorderLayout.EAST);
        jLabelStatus.setText("Статус:");
        jLabelBossStatus.setText("Статус босса:");

        addListeners();



        setVisible(true);
    }

    void addListeners() {
        jbStartNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StartWindow();
            }
        });
        jbRecon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logic.recon();
            }
        });
        jbHunt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logic.hunt();
            }
        });
        jbEat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logic.eat();
            }
        });
        jbVal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logic.val();
            }
        });
        jbAttackBoss.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logic.attackBoss();
            }
        });
        jbExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ConfirmWindow();
            }
        });
    }
}
