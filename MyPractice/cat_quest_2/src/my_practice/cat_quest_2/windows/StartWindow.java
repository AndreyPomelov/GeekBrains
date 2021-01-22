package my_practice.cat_quest_2.windows;

import javax.swing.*;

public class StartWindow extends JFrame {

    private final int WIN_WIDTH = 300;
    private final int WIN_HEIGHT = 200;
    private final int WIN_POS_X = 800;
    private final int WIN_POS_Y = 300;

    private final JPanel jPanelCenter = new JPanel();
    private final JPanel jPanelButtons = new JPanel();
    private final JLabel jLabelCatName = new JLabel("   Назови своего кошака:");
    private final JTextField jTextFieldCatName = new JTextField();
    private final JLabel jLabelWarning = new JLabel("   Внимание! Старт новой игры сбросит весь прогресс в текущей игре!");
    private final JButton jButtonStart = new JButton("Поехали!");
    private final JButton jButtonCancel = new JButton("Отмена");

    public StartWindow() {
        setTitle("Игра \"Котоквест\"");
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setBounds(WIN_POS_X, WIN_POS_Y, WIN_WIDTH, WIN_HEIGHT);
        setResizable(false);


        
    }

}
