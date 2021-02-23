package game;

import game.animals.Cat;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable, Serializable {

    private static final long serialVersionUID = 1L;

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
    public Menu menu;

    @FXML
    public static Controller controller;

    @FXML
    public static Cat cat;

    @FXML
    public synchronized void menuItemStartPressed() throws IOException {
        controller = this;
        Logic.setController(this);
        GameOverWindowController.controller = this;
        Parent root = FXMLLoader.load(getClass().getResource("startWindow.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    public synchronized void menuItemExitPressed() throws IOException {
        Logic.saveGame();
        Stage stage = (Stage) leftPanelLabel.getScene().getWindow();
        stage.close();
    }

    @FXML
    public synchronized void buttonHunt() {
        if (cat == null) return;
        blockButtons();
        mainTextArea.clear();
        Logic.hunt();
    }

    @FXML
    public synchronized void buttonEat() {
        if (cat == null) return;
        blockButtons();
        mainTextArea.clear();
        Logic.eat();
    }

    @FXML
    public synchronized void buttonVal() {
        if (cat == null) return;
        blockButtons();
        mainTextArea.clear();
        Logic.val();
    }

    @FXML
    public synchronized void buttonRecon() {
        if (cat == null) return;
        blockButtons();
        mainTextArea.clear();
        Logic.recon();
    }

    @FXML
    public synchronized void buttonAttack() {
        if (cat == null) return;
        blockButtons();
        mainTextArea.clear();
        Logic.bossAttack();
    }

    @FXML
    public synchronized void buttonSteal() {
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
        controller = this;
    }

    @FXML
    public synchronized void blockButtons() {
        buttonPanel.setDisable(true);
        menu.setDisable(true);
    }

    @FXML
    public synchronized void unblockButtons() {
        buttonPanel.setDisable(false);
        menu.setDisable(false);
    }

    @FXML
    public synchronized void gameOver(boolean winGame) throws IOException {
        Platform.runLater(() -> {
            Parent root;
            try {
                if (winGame) root = FXMLLoader.load(getClass().getResource("gameOverWindow.fxml"));
                else root = FXMLLoader.load(getClass().getResource("badEnd.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Игра окончена!");
                stage.setResizable(false);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void menuItemSavePressed() throws IOException {
        if (cat == null) return;
        Logic.saveGame();
    }

    public void menuItemLoadPressed() throws IOException, ClassNotFoundException {
        Logic.loadGame();
    }

    public void menuItemHelpPressed() {
        Platform.runLater(() -> {
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("helpWindow.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Справка по игре.");
                stage.setResizable(false);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
