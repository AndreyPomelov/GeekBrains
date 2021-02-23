package game;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class helpWindowController implements Initializable {

    @FXML
    public TextArea area;

    @FXML
    public void buttonPressed() {
        Stage stage = (Stage) area.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        area.setText(Help.help);
    }
}
