import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import lombok.extern.slf4j.Slf4j;
import model.Package;
import model.PackageType;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
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
    private final String FILES_DIR = "client/files/";
    private String currentDir = "client/files/";
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private final String IP_ADDRESS = "localhost";
    private final int PORT = 8190;
    private final int BUFFER_SIZE = 512;

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
                byte[] buffer = new byte[BUFFER_SIZE];
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
        Package pack = new Package(PackageType.GET_FILE);
        pack.setFileName(serverFilesList.getSelectionModel().getSelectedItem().toString());
        if (pack.getFileName().equals("") || pack.getFileName().endsWith("(folder)")) return;
        authController.client.write(pack);
        new Thread(() -> {
            try {
                socket = new Socket(IP_ADDRESS, PORT);
                in = new DataInputStream(socket.getInputStream());
                long fileSize = in.readLong();
                byte[] buffer = new byte[BUFFER_SIZE];
                try (FileOutputStream fos = new FileOutputStream(currentDir + pack.getFileName())) {
                    for (int i = 0; i < (fileSize + BUFFER_SIZE - 1) / BUFFER_SIZE; i++) {
                        int read = in.read(buffer);
                        fos.write(buffer, 0, read);
                    }
                }
                in.close();
                socket.close();
            } catch (Exception e) {
                log.error("File download error");
            }
            Platform.runLater(() -> {
                showFilesLists();
            });
        }).start();
    }

    public void showFilesLists() {
        localFilesList.getItems().clear();
        serverFilesList.getItems().clear();
        File dir = new File(currentDir);
        Platform.runLater(() -> {
            localFilesList.getItems().add("..");
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

    private String getParent(String path) {
        if (path.equals(FILES_DIR)) {
            return FILES_DIR;
        } else {
            String s = path.substring(0, path.length() - 1);
            int index = s.lastIndexOf("/");
            String result = s.substring(0, index) + "/";
            return result;
        }
    }

    public void goToLocalDir(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() != 2) return;
        String longFolderName = localFilesList.getSelectionModel().getSelectedItem().toString();
        if (longFolderName.endsWith("(folder)")) {
            int index = longFolderName.lastIndexOf("(");
            String folderName = longFolderName.substring(0, index - 1);
            currentDir = currentDir + folderName + "/";
        } else if (longFolderName.equals("..")) {
            currentDir = getParent(currentDir);
        } else return;
        showFilesLists();
    }

    public void goToRemoteDir(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() != 2) return;
        String longFolderName = serverFilesList.getSelectionModel().getSelectedItem().toString();
        if (longFolderName.endsWith("(folder)")) {
            int index = longFolderName.lastIndexOf("(");
            Package pack = new Package(PackageType.GO_TO_DIR);
            pack.setFileName(longFolderName.substring(0, index - 1));
            authController.client.write(pack);
        } else if (longFolderName.equals("..")) {
            authController.client.write(new Package(PackageType.TO_PARENT_DIR));
        }
    }
}
