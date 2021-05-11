import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Package;
import model.PackageType;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

// Класс-контроллер основного окна программы
public class MainController implements Initializable {

    public ListView localFilesList;
    public ListView serverFilesList;
    public TextField localDirName;
    public TextField remoteDirName;
    private AuthController authController;
    private String currentDir = "client/files/";

    public void upload(ActionEvent actionEvent) {
    }

    public void download(ActionEvent actionEvent) {
    }

    public void showFilesLists() {
        localFilesList.getItems().clear();
        serverFilesList.getItems().clear();
        File dir = new File(currentDir);
        Platform.runLater(() -> {
            for (File file : dir.listFiles()) {
                if (file.isDirectory()) localFilesList.getItems().add(file.getName());
            }
            for (File file : dir.listFiles()) {
                if (file.isFile()) localFilesList.getItems().add(file.getName());
            }
        });
        authController.client.write(new Package(PackageType.SHOW_FILES));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        authController = AuthController.authController;
        showFilesLists();
        Handler.mainController = this;
    }

    public void createLocalDirectory(ActionEvent actionEvent) {
        if (localDirName.getText().equals("")) return;
        File newDir = new File(currentDir + localDirName.getText());
        newDir.mkdir();
        showFilesLists();
    }

    public void createRemoteDirectory(ActionEvent actionEvent) {
        if (remoteDirName.getText().equals("")) return;
        Package pack = new Package(PackageType.MAKE_DIR);
        pack.setFileName(remoteDirName.getText());
        authController.client.write(pack);
        showFilesLists();
    }
}
