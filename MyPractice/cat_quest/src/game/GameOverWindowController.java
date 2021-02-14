package game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GameOverWindowController {

    @FXML
    public Button button;

    @FXML
    public void buttonPressed(ActionEvent actionEvent) {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }
}
