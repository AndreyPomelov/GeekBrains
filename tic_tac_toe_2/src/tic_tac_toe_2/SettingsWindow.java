package tic_tac_toe_2;

import javafx.scene.control.RadioButton;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsWindow extends JFrame {

    private final String WINDOW_TITLE = "Настройки игры";
    private final int WINDOW_HEIGHT = 400;
    private final int WINDOW_WIDTH = 400;
    private final int WINDOW_POS_X = 70;
    private final int WINDOW_POS_Y = 70;
    private final JButton jButtonStartNewGame = new JButton("Поехали!");
    private final JLabel LABEL_GAME_MODE = new JLabel("   Выбери режим игры");
    private final JRadioButton R_BUTTON_HUM_VS_AI = new JRadioButton("Сразись с интеллектом SkyNet!", true);
    private final JRadioButton R_BUTTON_HUM_VS_HUM = new JRadioButton("Игрок против игрока!");
    private final ButtonGroup R_BUTTON_GROUP = new ButtonGroup();
    private final GridLayout LAYOUT = new GridLayout(14, 1);
    private int currentFieldSize = 3;
    private int currentWinLength = 3;
    private final String STR_FLD_SIZE = "   Размер игрового поля:   ";
    private final String STR_WIN_L = "   Размер последовательности:   ";
    private final JLabel LABEL_SLIDER_FIELD_SIZE = new JLabel("   Выбери размер игрового поля");
    private final JLabel LABEL_SLIDER_FIELD_SIZE_NUMBER = new JLabel(STR_FLD_SIZE + currentFieldSize);
    private final JLabel LABEL_SLIDER_WIN_LENGTH = new JLabel("   Выбери размер выигрышной последовательности");
    private final JLabel LABEL_SLIDER_WIN_LENGTH_NUMBER = new JLabel(STR_WIN_L + currentWinLength);
    private final int MIN_FIELD_SIZE = 3;
    private final int MAX_FIELD_SIZE = 10;
    private final int MIN_WIN_LENGTH = 3;
    private final JSlider SLIDER_FIELD_SIZE = new JSlider(MIN_FIELD_SIZE, MAX_FIELD_SIZE, MIN_FIELD_SIZE);
    private final JSlider SLIDER_WIN_LENGTH = new JSlider(MIN_WIN_LENGTH, MIN_WIN_LENGTH, MIN_WIN_LENGTH);
    private final JLabel LABEL_PLAYER1_NAME = new JLabel("   Игрок 1, введи своё имя");
    private final JLabel LABEL_PLAYER2_NAME = new JLabel("   Игрок 2, введи своё имя");
    private final JTextField TFIELD_PLAYER1_NAME = new JTextField();
    private final JTextField TFIELD_PLAYER2_NAME = new JTextField();
    private GameWindow gameWindow;

    public SettingsWindow(GameWindow gameWindow) {

        this.gameWindow = gameWindow;
        setTitle(WINDOW_TITLE);
        setBounds(WINDOW_POS_X, WINDOW_POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setLayout(LAYOUT);
        add(LABEL_GAME_MODE);
        R_BUTTON_GROUP.add(R_BUTTON_HUM_VS_AI);
        R_BUTTON_GROUP.add(R_BUTTON_HUM_VS_HUM);
        add(R_BUTTON_HUM_VS_AI);
        add(R_BUTTON_HUM_VS_HUM);
        add(LABEL_PLAYER1_NAME);
        add(TFIELD_PLAYER1_NAME);
        add(LABEL_PLAYER2_NAME);
        add(TFIELD_PLAYER2_NAME);
        TFIELD_PLAYER2_NAME.setEnabled(false);
        R_BUTTON_HUM_VS_AI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TFIELD_PLAYER2_NAME.setEnabled(false);
            }
        });
        R_BUTTON_HUM_VS_HUM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TFIELD_PLAYER2_NAME.setEnabled(true);
            }
        });
        add(LABEL_SLIDER_FIELD_SIZE);
        add(LABEL_SLIDER_FIELD_SIZE_NUMBER);
        add(SLIDER_FIELD_SIZE);
        add(LABEL_SLIDER_WIN_LENGTH);
        add(LABEL_SLIDER_WIN_LENGTH_NUMBER);
        add(SLIDER_WIN_LENGTH);
        add(jButtonStartNewGame);
        SLIDER_FIELD_SIZE.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                LABEL_SLIDER_FIELD_SIZE_NUMBER.setText(STR_FLD_SIZE + SLIDER_FIELD_SIZE.getValue());
                SLIDER_WIN_LENGTH.setMaximum(SLIDER_FIELD_SIZE.getValue());
            }
        });
        SLIDER_WIN_LENGTH.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                LABEL_SLIDER_WIN_LENGTH_NUMBER.setText(STR_WIN_L + SLIDER_WIN_LENGTH.getValue());
            }
        });
        jButtonStartNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                startGame();
            }
        });

        setVisible(true);
    }

    void startGame() {
        int gameMode;
        if (R_BUTTON_HUM_VS_AI.isSelected()) gameMode = 0;
        else gameMode = 1;
        Settings settings = new Settings(gameMode, SLIDER_FIELD_SIZE.getValue(), SLIDER_WIN_LENGTH.getValue(),
                TFIELD_PLAYER1_NAME.getText(), TFIELD_PLAYER2_NAME.getText());
        Field field = new Field();
        gameWindow.setField(field);
        field.startGame(settings);
    }
    
}
