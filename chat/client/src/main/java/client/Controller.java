package client;

import commands.Command;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable {
    @FXML
    public ListView<String> clientList;
    public Label lab1;
    public Label lab2;
    public Label lab3;
    public Label lab4;
    public ListView<String> localDir;
    public ListView<String> serverDir;
    public Button buttonSend;
    public Button buttonUpload;
    public Button buttonDownload;
    @FXML
    private TextArea textArea;
    @FXML
    private TextField textField;
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private HBox authPanel;
    @FXML
    private HBox messagePanel;
    @FXML
    private final String HISTORY_DIR_PATH = "client/history";
    @FXML
    private final String HISTORY_FILE_NAME = "history.txt";
    @FXML
    private final int NUMBER_OF_LINES_TO_RESTORE = 100;

    private static Socket socket;
    private static final int PORT = 8189;
    private static final String IP_ADDRESS = "localhost";

    private static DataInputStream in;
    private static DataOutputStream out;

    private boolean authenticated;
    private String nickname;

    private Stage stage;
    private Stage regStage;
    private RegController regController;

    private final String PATHNAME = "D:\\Андрей\\JavaRepository\\GeekBrains\\chat\\client\\files\\";

    // Метод, отображающий в окне клиента список файлов, которые есть в папке.
    public void showFileList() {
        // Правильно ли делать такие вещи в потоке исполнения JavaFX?
        Platform.runLater(() -> {
            File dir = new File(PATHNAME);
            for (File file : dir.listFiles()) {
                localDir.getItems().add(file.getName());
            }
        });
    }

    // Не нравится этот метод.
    // Просто захардкодил из-за отсутствия времени.
    // По уму надо бы как-то это в цикле делать.
    // Смысл метода в том, что после успешной авторизации клиента
    // мы скрываем форму авторизации и отображаем основное окно приложения.
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
        authPanel.setVisible(!authenticated);
        authPanel.setManaged(!authenticated);
        clientList.setVisible(authenticated);
        clientList.setManaged(authenticated);
        lab1.setVisible(authenticated);
        lab1.setManaged(authenticated);
        lab2.setVisible(authenticated);
        lab2.setManaged(authenticated);
        lab3.setVisible(authenticated);
        lab3.setManaged(authenticated);
        lab4.setVisible(authenticated);
        lab4.setManaged(authenticated);
        localDir.setVisible(authenticated);
        localDir.setManaged(authenticated);
        serverDir.setVisible(authenticated);
        serverDir.setManaged(authenticated);
        textField.setVisible(authenticated);
        textField.setManaged(authenticated);
        buttonSend.setVisible(authenticated);
        buttonSend.setManaged(authenticated);
        buttonUpload.setVisible(authenticated);
        buttonUpload.setManaged(authenticated);
        buttonDownload.setVisible(authenticated);
        buttonDownload.setManaged(authenticated);

        if (!authenticated) {
            nickname = "";
        }

        setTitle(nickname);
        textArea.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            stage = (Stage) textField.getScene().getWindow();
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    System.out.println("bye");
                    if (socket != null && !socket.isClosed()) {
                        try {
                            out.writeUTF(Command.END);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        });

        setAuthenticated(false);
    }

    private void connect() {
        try {
            socket = new Socket(IP_ADDRESS, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(() -> {
                try {
                    //цикл аутентификации
                    while (true) {
                        String str = in.readUTF();
                        System.out.println(str);
                        if (str.startsWith("/")) {
                            if (str.equals(Command.END)) {
                                System.out.println("server disconnected us");
                                throw new RuntimeException("server disconnected us");
                            }
                            if (str.startsWith(Command.AUTH_OK)) {
                                nickname = str.split("\\s")[1];
                                setAuthenticated(true);
                                restoreHistory();
                                break;
                            }

                            if (str.equals(Command.REG_OK)) {
                                regController.resultTryToReg(true);
                            }
                            if (str.equals(Command.REG_NO)) {
                                regController.resultTryToReg(false);
                            }
                        } else {
                            textArea.appendText(str + "\n");
                        }
                    }

                    // Отображаем список файлов в папке после успешной авторизации клиента.
                    showFileList();

                    //цикл работы
                    while (true) {
                        String str = in.readUTF();

                        if (str.startsWith("/")) {
                            if (str.equals(Command.SERVER_FILES_LIST)) {
                                showServerFiles();
                            }
                            if (str.equals(Command.END)) {
                                setAuthenticated(false);
                                break;
                            }

                            if (str.startsWith(Command.CLIENT_LIST)) {
                                String[] token = str.split("\\s");
                                Platform.runLater(() -> {
                                    clientList.getItems().clear();
                                    for (int i = 1; i < token.length; i++) {
                                        clientList.getItems().add(token[i]);
                                    }
                                });
                            }
                        } else {
                            saveToHistory(str);
                            textArea.appendText(str + "\n");
                        }
                    }
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    setAuthenticated(false);
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод, принимающий от сервера список файлов и отображающий его в окне приложения
    private void showServerFiles() throws IOException {
        Platform.runLater(() -> {
            serverDir.getItems().clear();
        });
        while (true) {
            String fileName = in.readUTF();
            // Если ловим служебную команду, что список файлов закончен, выходим из цикла
            if (fileName.equals(Command.END_OF_FILES_LIST)) break;
            Platform.runLater(() -> {
                serverDir.getItems().add(fileName);
            });
        }
    }

    public void saveToHistory (String message) throws IOException {
        File dir = new File(HISTORY_DIR_PATH);
        File file = new File(HISTORY_DIR_PATH, HISTORY_FILE_NAME);
        if (!dir.exists()) dir.mkdir();
        if (!file.exists()) file.createNewFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
        writer.write(message + "\n");
        writer.flush();
        writer.close();
    }

    public void restoreHistory () throws IOException {
        File dir = new File(HISTORY_DIR_PATH);
        File file = new File(HISTORY_DIR_PATH, HISTORY_FILE_NAME);
        if (!dir.exists() || !file.exists()) return;
        // Счётчик строк в файле
        // Не очень нравится такой подход, но не нашёл метода, который возвращал бы количество строк в файле
        Scanner scanner = new Scanner(file);
        int lineCount = 0;
        while (scanner.hasNextLine()) {
            lineCount++;
            scanner.nextLine();
        }
        scanner.close();
        // Конец счётчика строк в файле
        // Задаём стартовую строку, с которой будет восстановлена история
        int startLine = lineCount - NUMBER_OF_LINES_TO_RESTORE;
        if (startLine < 0) startLine = 0;
        LineNumberReader reader = new LineNumberReader(new FileReader(file));
        for (int i = 0; i < startLine; i++) {
            reader.readLine();
        }
        while (true) {
            String message = reader.readLine();
            if (message != null) textArea.appendText(message + "\n");
            else break;
        }
        reader.close();
    }

    public void sendMsg(ActionEvent actionEvent) {
        try {
            if (textField.getText().trim().length() > 0) {
                out.writeUTF(textField.getText());
                textField.clear();
                textField.requestFocus();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void trytoAuth(ActionEvent actionEvent) {
        if (socket == null || socket.isClosed()) {
            connect();
        }

        String msg = String.format("%s %s %s", Command.AUTH, loginField.getText().trim(), passwordField.getText().trim());
        try {
            out.writeUTF(msg);
            passwordField.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setTitle(String title) {
        Platform.runLater(() -> {
            if (title.equals("")) {
                stage.setTitle("Квазимодо");
            } else {
                stage.setTitle(String.format("Квазимодо [ %s ]", title));
            }
        });
    }

    public void clientListClicked(MouseEvent mouseEvent) {
        System.out.println(clientList.getSelectionModel().getSelectedItem());
        String msg = String.format("%s %s ", Command.PRIVATE_MSG, clientList.getSelectionModel().getSelectedItem());
        textField.setText(msg);
    }

    public void clickRegBtn(ActionEvent actionEvent) {
        if (regStage == null) {
            createRegWindow();
        }
        regStage.show();
    }

    private void createRegWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/reg.fxml"));
            Parent root = fxmlLoader.load();

            regController = fxmlLoader.getController();
            regController.setController(this);

            regStage = new Stage();
            regStage.setTitle("Квазимодо регистрация");
            regStage.setScene(new Scene(root, 400, 300));

            regStage.initModality(Modality.APPLICATION_MODAL);
            regStage.initStyle(StageStyle.UTILITY);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tryToReg(String login, String password, String nickname) {
        String message = String.format("%s %s %s %s", Command.REG, login, password, nickname);
        if (socket == null || socket.isClosed()) {
            connect();
        }

        try {
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод, закачивающий файл на сервер
    public void upload(ActionEvent actionEvent) throws IOException {

        // Запускаю закачку файла на сервер в новом потоке.
        // Но не думаю, что это решение проблемы, т.к. поток вывода
        // используется тот же самый, что и для отправки сообщений в чат.
        // Клиент же может отправить сообщение, не дожидаясь закачки файла до конца.
        // Подумать над этим.
        // Изучить Netty, посмотреть, какие инструменты даст эта технология для решения вопроса.
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Сначала посылаем служебную команду, чтобы сервер понял,
                    // что мы вообще файл пытаемся отправить.
                    out.writeUTF(Command.SEND_FILE);

                    String filename = localDir.getSelectionModel().getSelectedItem();
                    out.writeUTF(filename);

                    long fileSize = Files.size(Paths.get(PATHNAME, filename));
                    out.writeLong(fileSize);

                    byte[] buffer = new byte[512];

                    try (FileInputStream fis = new FileInputStream(PATHNAME + "\\" + filename)) {
                        int read;
                        while (true) {
                            read = fis.read(buffer);
                            if (read == -1) break;
                            out.write(buffer, 0, read);
                        }
                        out.flush();
                    }
                }
                catch (Exception e) {
                    System.err.println("Error while uploading file");
                }
            }
        }).start();
    }

    // Метод, скачивающий файл с сервера
    public void download(ActionEvent actionEvent) {
        // TODO Пока не реализован
    }
}