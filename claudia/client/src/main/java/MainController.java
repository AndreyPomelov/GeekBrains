import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;
import model.Package;
import model.PackageType;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

// Класс-контроллер основного окна программы
@Slf4j
public class MainController implements Initializable {

    public ListView localFilesList;
    public ListView serverFilesList;
    public TextField localDirName;
    public TextField remoteDirName;
    private AuthController authController;
    private String currentDir = "client/files/";
    private Socket socket;
    private DataOutputStream out;
    private final String IP_ADDRESS = "localhost";
    private final int PORT = 8190;

    public void upload(ActionEvent actionEvent) throws IOException {
        Package pack = new Package(PackageType.FILE);
        pack.setFileName(localFilesList.getSelectionModel().getSelectedItem().toString());
        if (pack.getFileName().equals("") || pack.getFileName().endsWith("(folder)")) return;
        pack.setFileSize(Files.size(Paths.get(currentDir, pack.getFileName())));
        authController.client.write(pack);
        new Thread(() -> {
            try {
                socket = new Socket(IP_ADDRESS, PORT);
                out = new DataOutputStream(socket.getOutputStream());
                byte[] buffer = new byte[512];
                try (FileInputStream fis = new FileInputStream(currentDir + pack.getFileName())) {
                    int read;
                    while (true) {
                        read = fis.read(buffer);
                        if (read == -1) break;
                        out.write(buffer, 0, read);
                    }
                    out.flush();
                }
                out.close();
                socket.close();
                Platform.runLater(() -> {
                    showFilesLists();
                });
            } catch (Exception e) {
                log.error("File upload error");
            }
        }).start();
    }

    public void download(ActionEvent actionEvent) {
    }

    public void showFilesLists() {
        localFilesList.getItems().clear();
        serverFilesList.getItems().clear();
        File dir = new File(currentDir);
        Platform.runLater(() -> {
            for (File file : dir.listFiles()) {
                if (file.isDirectory()) localFilesList.getItems().add(file.getName() + " (folder)");
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
