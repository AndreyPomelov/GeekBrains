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
import java.nio.file.Paths;
import java.util.ResourceBundle;

// Класс-контроллер основного окна программы
@Slf4j
public class MainController implements Initializable {

    public ListView<String> localFilesList;
    public ListView<String> serverFilesList;
    public TextField localDirName;
    public TextField remoteDirName;
    private AuthController authController;
    // Переменная с адресом корневого каталога
    private final String FILES_DIR = "client/files/";
    // Переменная с адресом текущего каталога
    private String currentDir = "client/files/";
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private final String IP_ADDRESS = "localhost";
    private final int PORT = 8190;
    private final int BUFFER_SIZE = 512;

    // Метод, отправляющий файл на сервер
    public void upload(ActionEvent actionEvent) throws IOException {
        // Сначала создаём и отправляем информационный пакет на сервер
        // с именем и размером файла
        Package pack = new Package(PackageType.FILE);
        pack.setFileName(localFilesList.getSelectionModel().getSelectedItem().toString());
        if (pack.getFileName().equals("") || pack.getFileName().endsWith("(folder)")) return;
        pack.setFileSize(Files.size(Paths.get(currentDir, pack.getFileName())));
        authController.client.write(pack);
        // Отправляем сам файл
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

    // Метод, принимающий файл с сервера
    public void download(ActionEvent actionEvent) {
        // Сначала создаём пакет для сервера с информацией о том,
        // какой именно файл мы хотим получить
        Package pack = new Package(PackageType.GET_FILE);
        pack.setFileName(serverFilesList.getSelectionModel().getSelectedItem().toString());
        if (pack.getFileName().equals("") || pack.getFileName().endsWith("(folder)")) return;
        authController.client.write(pack);
        // Принимаем файл
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

    // Метод, отображающий списки локальных и серверных файлов и папок
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

    // Метод, создающий локальную папку
    public void createLocalDirectory(ActionEvent actionEvent) {
        if (localDirName.getText().equals("")) return;
        File newDir = new File(currentDir + localDirName.getText());
        newDir.mkdir();
        showFilesLists();
        localDirName.clear();
    }

    // Метод, отправляющий на сервер запрос о создании папки на сервере
    public void createRemoteDirectory(ActionEvent actionEvent) {
        if (remoteDirName.getText().equals("")) return;
        Package pack = new Package(PackageType.MAKE_DIR);
        pack.setFileName(remoteDirName.getText());
        authController.client.write(pack);
        showFilesLists();
        remoteDirName.clear();
    }

    // Метод, возвращающий родительскую папку заданной папки
    private String getParent(String path) {
        // Проверка. Если текущая папка соответствует корневой
        // папке, возвращаем адрес корневой папки
        // (чтобы не было возможности уйти вверх по иерархии)
        if (path.equals(FILES_DIR)) {
            return FILES_DIR;
        } else {
            String s = path.substring(0, path.length() - 1);
            int index = s.lastIndexOf("/");
            String result = s.substring(0, index) + "/";
            return result;
        }
    }

    // Метод, открывающий выбранную локальную папку
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

    // Метод, открывающий выбранную папку на сервере
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
