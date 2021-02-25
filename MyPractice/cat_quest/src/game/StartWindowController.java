package game;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StartWindowController {

    @FXML
    public TextField catName;

    @FXML
    public void buttonGo() {
        Logic.setStartWindowController();
        Stage stage = (Stage) catName.getScene().getWindow();
        stage.close();
        Logic.startNewGame(catName.getText());
    }
}
