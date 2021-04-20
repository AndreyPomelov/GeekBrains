import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ChatController implements Initializable {
    public ListView<String> listView;
    public TextField input;
    private DataInputStream is;
    private DataOutputStream os;
    // Объявил новые переменные для передачи файлов, чтобы пока не путаться.
    // Позже необходимо будет оптимизировать переменные.
    private InputStream fileIS;
    private OutputStream fileOS;
    // Переменная, которая содержит абсолютный путь к папке с файлами.
    private final String PATHNAME = "D:\\Андрей\\JavaRepository\\GeekBrains\\Java_level_5\\client\\src\\main\\resources\\files\\";
    private Socket socket;

    // Метод, отображающий в окне клиента список файлов, которые есть в папке.
    public void showFileList() {
        File dir = new File(PATHNAME);
        for (File file : dir.listFiles()) {
            listView.getItems().add(file.getName());
        }
    }

    public void send(ActionEvent actionEvent) throws IOException {
//        os.writeUTF(input.getText());
//        os.flush();
//        input.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            socket = new Socket("localhost", 8189);
            is = new DataInputStream(socket.getInputStream());
            os = new DataOutputStream(socket.getOutputStream());
            ReadHandler handler = new ReadHandler(is, message -> Platform.runLater(() -> listView.getItems().add(message)));
            Thread readThread = new Thread(handler);
            readThread.setDaemon(true);
            readThread.start();
            // Сразу отображаем список файлов при инициализации окна приложения.
            showFileList();
        } catch (Exception e) {
            System.err.println("Socket error");
            e.printStackTrace();
        }
    }

    // Метод, отправляющий файл на сервер.
    public void upload(MouseEvent mouseEvent) throws IOException {
        String fileName = listView.getSelectionModel().getSelectedItem();
        System.out.println("Sending " + fileName);
        fileIS = new FileInputStream(PATHNAME + fileName);
        fileOS = new BufferedOutputStream(socket.getOutputStream());
        byte[] buffer = new byte[1024];
        int read;
        while ((read = fileIS.read(buffer)) != -1) {
            fileOS.write(buffer, 0, read);
        }
        fileOS.flush();
        fileOS.close();
        fileIS.close();
        System.out.println("File uploaded");
    }
}
