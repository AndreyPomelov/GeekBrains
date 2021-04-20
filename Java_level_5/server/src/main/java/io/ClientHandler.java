package io;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private final Socket socket;
    private final Server server;
    private DataInputStream is;
    private DataOutputStream os;
    // Переменная, содержащая путь к папке, где будут сохраняться переданные файлы.
    private final String DEST_PATHNAME = "D:\\Андрей\\JavaRepository\\GeekBrains\\Java_level_5\\server\\src\\main\\resources\\files\\";
    // Объявил новые переменные для передачи файлов, чтобы пока не путаться.
    // Позже необходимо будет оптимизировать переменные.
    private InputStream fileIS;
    private OutputStream fileOS;

    public ClientHandler(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    public void sendMessage(String message) throws IOException {
        os.writeUTF(message);
        os.flush();
    }

    // Переписал метод на приём файлов вместо сообщений.
    // Пока файл передаётся без имени и сохраняется под заранее заданным именем,
    // что в реальных условиях конечно же бессмысленно.
    // Необходимо как-то передавать имя файла серверу вместе с файлом.
    @Override
    public void run() {
        try {
            while (true) {
                fileIS = new BufferedInputStream(socket.getInputStream());
                fileOS = new FileOutputStream(DEST_PATHNAME + "file");
                System.out.println("Connection is active");
                byte[] buffer = new byte[1024];
                int read;
                while ((read = fileIS.read(buffer)) != -1) {
                    fileOS.write(buffer, 0, read);
                }
                fileOS.flush();
                fileOS.close();
                fileIS.close();
                System.out.println("File received");
            }
        }
        catch (Exception e) {
            System.err.println("File transfer exception");
        }


    }
}
