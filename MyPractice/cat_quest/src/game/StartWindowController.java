package game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StartWindowController {

    @FXML
    public TextField catName;

    @FXML
    public void buttonGo(ActionEvent actionEvent) {
        Logic.setStartWindowController(this);
        Stage stage = (Stage) catName.getScene().getWindow();
        stage.close();
        Logic.getController().startNewGame(catName.getText());

    }
}
