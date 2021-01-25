package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class Controller {

    @FXML
    public static Stage stage;
    @FXML
    public javafx.scene.control.TextArea textArea;
    @FXML
    public javafx.scene.control.TextField textField;
    @FXML
    public javafx.scene.control.Button btn;
    @FXML
    public MenuItem exitItem;

    @FXML
    public void clickExit(ActionEvent actionEvent) {
        stage.close();
    }

    @FXML
    public void sendMessage(ActionEvent actionEvent) {
        textArea.appendText(textField.getText() + "\n");
        textField.clear();
        textField.requestFocus();
    }
}
