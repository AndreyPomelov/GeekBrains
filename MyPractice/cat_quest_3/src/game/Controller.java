package game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class Controller {

    @FXML
    static Stage stage;
    @FXML
    public MenuItem menuItemExit;

    @FXML
    public void menuItemExitPressed(ActionEvent actionEvent) {
        stage.close();
    }

    @FXML
    public void buttonHunt(ActionEvent actionEvent) {
    }

    @FXML
    public void buttonEat(ActionEvent actionEvent) {
    }

    @FXML
    public void buttonVal(ActionEvent actionEvent) {
    }

    @FXML
    public void buttonRecon(ActionEvent actionEvent) {
    }

    @FXML
    public void buttonAttack(ActionEvent actionEvent) {
    }
}
