package geekbrains.tic_tac_toe_3;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsWindow extends JFrame {

    private static GameWindow gameWindow;

    private final int WINDOW_WIDTH = 400;
    private final int WINDOW_HEIGHT = 500;

    private final int MIN_FIELD_SIZE = 3;
    private final int MAX_FIELD_SIZE = 10;
    private final int MIN_WIN_LENGTH = 3;

    private JRadioButton jrbHumVsAI = new JRadioButton("Сразись с интеллектом SkyNet!", true);
    private JRadioButton jrbHumVsHum = new JRadioButton("Игрок против игрока!");
    private ButtonGroup btnGroup = new ButtonGroup();
    private JTextField textFPlayer1Name = new JTextField();
    private JTextField textFPlayer2Name = new JTextField();
    private JSlider sliderFieldSize = new JSlider(MIN_FIELD_SIZE, MAX_FIELD_SIZE, MIN_FIELD_SIZE);
    private JSlider sliderWinLength = new JSlider(MIN_WIN_LENGTH, MIN_WIN_LENGTH, MIN_WIN_LENGTH);
    private JLabel lblFieldSize = new JLabel("   Размер поля: " + MIN_FIELD_SIZE);
    private JLabel lblWinLength = new JLabel("   Размер последовательности: " + MIN_WIN_LENGTH);
    private JButton startButton = new JButton("Поехали!");

    SettingsWindow (GameWindow gameWindow) {

        this.gameWindow = gameWindow;
        setTitle("Настройки игры");
        Rectangle rectangle = new Rectangle(gameWindow.getBounds());
        int posX = (int) rectangle.getCenterX() - WINDOW_WIDTH / 2;
        int posY = (int) rectangle.getCenterY() - WINDOW_HEIGHT / 2;
        setBounds(posX, posY, WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        setLayout(new GridLayout(14, 1));

        addRadioButtons();
        addPlayersNames();
        addSliders();
        addStartButton();

    }

    void newGameStart() {
        SettingsWindow.this.setVisible(false);
        int gameMode;
        if (jrbHumVsAI.isSelected()) gameMode = 0;
        else gameMode = 1;
        Settings settings = new Settings(gameMode, textFPlayer1Name.getText(),
                textFPlayer2Name.getText(), sliderFieldSize.getValue(), sliderWinLength.getValue());
        gameWindow.newGameStart(settings);
    }

    void addStartButton() {

        add(startButton);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newGameStart();
            }
        });
    }

    void addSliders() {

        add(new JLabel("   Выбери размер поля:"));
        add(lblFieldSize);
        add(sliderFieldSize);
        add(new JLabel("   Выбери размер выигрышной последовательности:"));
        add(lblWinLength);
        add(sliderWinLength);
        sliderFieldSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                lblFieldSize.setText("   Размер поля: " + sliderFieldSize.getValue());
                sliderWinLength.setMaximum(sliderFieldSize.getValue());
            }
        });
        sliderWinLength.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                lblWinLength.setText("   Размер последовательности: " + sliderWinLength.getValue());
            }
        });
    }

    void addPlayersNames() {

        add(new JLabel("   Игрок 1, введи своё имя:"));
        add(textFPlayer1Name);
        add(new JLabel("   Игрок 2, введи своё имя:"));
        add(textFPlayer2Name);
        textFPlayer2Name.setEnabled(false);
    }

    void addRadioButtons() {

        add(new JLabel("   Выбери режим игры:"));
        btnGroup.add(jrbHumVsAI);
        btnGroup.add(jrbHumVsHum);
        add(jrbHumVsAI);
        add(jrbHumVsHum);
        jrbHumVsAI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textFPlayer2Name.setEnabled(false);
            }
        });
        jrbHumVsHum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textFPlayer2Name.setEnabled(true);
            }
        });
    }
}
