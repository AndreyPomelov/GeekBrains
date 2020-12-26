package ru.geekbrains.java_level_1.lesson_8;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class StartNewGameWindow extends JFrame {

    private static final int WINDOW_WIDTH = 350;
    private static final int WINDOW_HEIGHT = 230;
    private static final int MIN_WIN_LEN = 3;
    private static final int MAX_WIN_LEN = 10;
    private static final int MIN_FIELD_SIZE = 3;
    private static final int MAX_FIELD_SIZE = 10;
    private static final String STR_WIN_LEN = "Выигрышная последовательность: ";
    private static final String STR_FIELD_SIZE = "Размер поля: ";
    private JRadioButton radioVsAi = new JRadioButton("Игра против компьютера", true);
    private JRadioButton radioVsHuman = new JRadioButton("Игра друг против друга");
    private ButtonGroup gameMode = new ButtonGroup();
    private JSlider sliderFieldSize;
    private JSlider sliderWinLength;
    private final GameWindow gameWindow;

    public StartNewGameWindow(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        Rectangle gameWindowBounds = gameWindow.getBounds();
        int posX = (int)gameWindowBounds.getCenterX() - WINDOW_WIDTH / 2;
        int posY = (int)gameWindowBounds.getCenterY() - WINDOW_HEIGHT / 2;
        setLocation(posX, posY);
        setTitle("Настройки игры");
        setLayout(new GridLayout(10,1));
        addSettings();


    }

    void addSettings() {
        add(new JLabel("Выберите режим игры:"));
        gameMode.add(radioVsAi);
        gameMode.add(radioVsHuman);
        add(radioVsAi);
        add(radioVsHuman);
        add(new JLabel("Выберите размер поля:"));
        final JLabel lblFieldSize = new JLabel(STR_FIELD_SIZE + MIN_FIELD_SIZE);
        add(lblFieldSize);
        sliderFieldSize = new JSlider(MIN_FIELD_SIZE, MAX_FIELD_SIZE, MIN_FIELD_SIZE);
        add(sliderFieldSize);
        sliderFieldSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int currentFieldSize = sliderFieldSize.getValue();
                lblFieldSize.setText(STR_FIELD_SIZE + currentFieldSize);
                sliderWinLength.setMaximum(currentFieldSize);
            }
        });


    }
}
