package game;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GameOverWindowController {

    @FXML
    public Button button;

    @FXML
    public static Controller controller;

    @FXML
    public void buttonPressed() {
        Stage stage = (Stage) button.getScene().getWindow();
        controller.leftPanelLabel.setText(" Статус:");
        Logic.eraseRightPanel();
        controller.mainTextArea.setText("Игра \"Котоквест\"" +
                "\nРазработчик - Андрей Помелов" +
                "\nДизайнер - Юлия Помелова" +
                "\nEmail: k-udm@ya.ru" +
                "\nг. Ижевск, 2021 г.\n\nДля начала игры выбери пункт \"Новая игра\" в Меню.\n\n");
        stage.close();
    }
}
