package game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    @FXML
    public Label leftPanelLabel;

    @FXML
    public MenuItem menuItemExit;

    @FXML
    Label localLeftLabel;

    @FXML
    public void menuItemStartPressed(ActionEvent actionEvent) throws IOException {
        Logic.setController(this);
        localLeftLabel = leftPanelLabel;
        Parent root = FXMLLoader.load(getClass().getResource("startWindow.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void menuItemExitPressed(ActionEvent actionEvent) {
        Stage stage = (Stage) leftPanelLabel.getScene().getWindow();
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
    public void buttonAttack(ActionEvent actionEvent) throws IOException {
    }

    @FXML
    public void startNewGame(String catName) {
        System.out.println(catName);
        Logic.getController().leftPanelLabel.setText(catName);
    }
}
