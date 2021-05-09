import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.io.File;
import java.net.URL;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

// Класс-контроллер основного окна программы
public class MainController implements Initializable {

    public ListView localFilesList;
    public ListView serverFilesList;
    private AuthController authController;
    private final String FILES_DIR_PATH = "client/files/";

    public void upload(ActionEvent actionEvent) {
    }

    public void download(ActionEvent actionEvent) {
    }

    public void showFilesLists() {
        localFilesList.getItems().clear();
        serverFilesList.getItems().clear();
        File dir = new File(FILES_DIR_PATH);
        Platform.runLater(() -> {
            for (File file : dir.listFiles()) {
                localFilesList.getItems().add(file.getName());
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        authController = AuthController.authController;
        showFilesLists();
    }
}
