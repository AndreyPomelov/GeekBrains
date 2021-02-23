package game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
        primaryStage.setTitle("Игра \"Котоквест\"");
        primaryStage.setScene(new Scene(root, 1024, 600));
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(event -> {
            try {
                Logic.saveGame();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
