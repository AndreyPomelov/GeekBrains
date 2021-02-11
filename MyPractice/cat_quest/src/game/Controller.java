package game;

import game.animals.Cat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public Label leftPanelLabel;

    @FXML
    public MenuItem menuItemExit;

    @FXML
    public Label rightPanelLabel;

    @FXML
    public TextArea mainTextArea;

    @FXML
    public FlowPane buttonPanel;

    @FXML
    private Controller controller;

    @FXML
    public static Cat cat;

    @FXML
    public synchronized void menuItemStartPressed(ActionEvent actionEvent) throws IOException {
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
    public synchronized void menuItemExitPressed(ActionEvent actionEvent) {
        Stage stage = (Stage) leftPanelLabel.getScene().getWindow();
        stage.close();
    }

    @FXML
    public synchronized void buttonHunt(ActionEvent actionEvent) {
        if (cat == null) return;
        blockButtons();
        mainTextArea.clear();
        Logic.hunt();
    }

    @FXML
    public synchronized void buttonEat(ActionEvent actionEvent) {
        if (cat == null) return;
        blockButtons();
        mainTextArea.clear();
        Logic.eat();
    }

    @FXML
    public synchronized void buttonVal(ActionEvent actionEvent) {
        if (cat == null) return;
        blockButtons();
        mainTextArea.clear();
        Logic.val();
    }

    @FXML
    public synchronized void buttonRecon(ActionEvent actionEvent) throws InterruptedException {
        if (cat == null) return;
        blockButtons();
        mainTextArea.clear();
        Logic.recon();
    }

    @FXML
    public synchronized void buttonAttack(ActionEvent actionEvent) throws IOException {
        if (cat == null) return;
        blockButtons();
        mainTextArea.clear();
        Logic.bossAttack();
    }

    @FXML
    public synchronized void buttonSteal(ActionEvent actionEvent) {
        if (cat == null) return;
        blockButtons();
        mainTextArea.clear();
        Logic.steal();
    }

    @Override
    public synchronized void initialize(URL location, ResourceBundle resources) {
        mainTextArea.setText("Игра \"Котоквест\"" +
                "\nРазработчик - Андрей Помелов" +
                "\nДизайнер - Юлия Помелова" +
                "\nEmail: k-udm@ya.ru" +
                "\nг. Ижевск, 2021 г.\n\nДля начала игры выбери пункт \"Новая игра\" в Меню.\n\n");
    }

    @FXML
    public synchronized void blockButtons() {
        buttonPanel.setDisable(true);
    }

    @FXML
    public synchronized void unblockButtons() {
        buttonPanel.setDisable(false);
    }
}
