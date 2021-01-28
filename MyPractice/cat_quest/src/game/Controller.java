package game;

import game.animals.Cat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    @FXML
    public Label leftPanelLabel;

    @FXML
    public MenuItem menuItemExit;

    @FXML
    public Label rightPanelLabel;

    @FXML
    public TextArea mainTextArea;

    @FXML
    private Controller controller;

    @FXML
    public static Cat cat;

    @FXML
    public void menuItemStartPressed(ActionEvent actionEvent) throws IOException {
        controller = this;
        Logic.setController(this);
        TextAppend.controller = this;
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
        if (cat == null) return;
    }

    @FXML
    public void buttonEat(ActionEvent actionEvent) {
        if (cat == null) return;
    }

    @FXML
    public void buttonVal(ActionEvent actionEvent) {
        if (cat == null) return;
    }

    @FXML
    public void buttonRecon(ActionEvent actionEvent) throws InterruptedException {
        if (cat == null) return;
        Logic.recon();
    }

    @FXML
    public void buttonAttack(ActionEvent actionEvent) throws IOException {
        if (cat == null) return;
    }
}
