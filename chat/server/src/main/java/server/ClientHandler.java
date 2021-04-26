package server;

import commands.Command;

import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.sql.SQLException;

public class ClientHandler implements Runnable {
    private Server server;
    private Socket socket;

    private DataInputStream in;
    private DataOutputStream out;

    private String nickname;
    private String login;
    private final String PATHNAME = "D:\\Андрей\\JavaRepository\\GeekBrains\\chat\\server\\files";
    private final int BUFFER_SIZE = 512;

    public ClientHandler(Server server, Socket socket) throws SQLException {
        try {
            this.server = server;
            this.socket = socket;
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(() -> {
                try {
                    socket.setSoTimeout(120000);
                    //цикл аутентификации
                    while (true) {
                        String str = in.readUTF();

                        // В цикле аутентификации нужна проверка на цензуру, например,
                        // для того чтобы нельзя было зарегистрироваться с нецензурным ником.
                        str = censorship(str);

                        if (str.startsWith("/")) {
                            if (str.equals(Command.END)) {
                                System.out.println("client want to disconnected ");
                                out.writeUTF(Command.END);
                                throw new RuntimeException("client want to disconnected");
                            }
                            if (str.startsWith(Command.AUTH)) {
                                String[] token = str.split("\\s");
                                String newNick = server.getAuthService()
                                        .getNicknameByLoginAndPassword(token[1], token[2]);
                                login = token[1];
                                if (newNick != null) {
                                    if (!server.isLoginAuthenticated(login)) {
                                        nickname = newNick;
                                        sendMsg(Command.AUTH_OK + " " + nickname);
                                        server.subscribe(this);
                                        break;
                                    } else {
                                        sendMsg("С этим логинов уже вошли");
                                    }
                                } else {
                                    sendMsg("Неверный логин / пароль");
                                }
                            }

                            if (str.startsWith(Command.REG)) {
                                String[] token = str.split("\\s");
                                if (token.length < 4) {
                                    continue;
                                }
                                boolean regSuccessful = server.getAuthService()
                                        .registration(token[1], token[2], token[3]);
                                if (regSuccessful) {
                                    sendMsg(Command.REG_OK);
                                } else {
                                    sendMsg(Command.REG_NO);
                                }
                            }
                        }
                    }

                    socket.setSoTimeout(0);

                    //цикл работы
                    while (true) {
                        String str = in.readUTF();

                        // Запускаем проверку на цензуру сразу же после получения сообщения сервером от клиента,
                        // перед проверками на служебные сообщения, для того чтобы, например,
                        // нельзя было сменить ник на нецензурный.
                        str = censorship(str);

                        if (str.startsWith("/")) {
                            // Если принимаем служебную команду, которая говорит серверу
                            // о том, что дальше пойдёт файл, то запускаем соответствующий
                            // метод, принимающий файл.
                            if (str.equals(Command.SEND_FILE)) {
                                receiveFile();
                            }
                            if (str.startsWith(Command.SHOW_FILE)) {
                                String[] arr = str.split("\\s+", 2);
                                String fileName = arr[1];
                                showFile(fileName);
                            }
                            if (str.equals(Command.SERVER_FILES_LIST)) {
                                showFilesList();
                            }
                            if (str.equals(Command.END)) {
                                out.writeUTF(Command.END);
                                break;
                            }
                            if (str.startsWith(Command.NICK_CHANGE)) {
                                String[] token = str.split("\\s+", 2);
                                server.broadcastMsg(this, "" + nickname + " меняет ник на " + token[1]);
                                server.changeNick(nickname, token[1]);
                                nickname = token[1];
                                server.broadcastClientList();
                            }

                            if (str.startsWith(Command.PRIVATE_MSG)) {
                                String[] token = str.split("\\s+", 3);
                                if (token.length < 3) {
                                    continue;
                                }
                                server.privateMsg(this, token[1], token[2]);
                            }
                        } else {
                            server.broadcastMsg(this, str);
                        }
                    }


                } catch (SQLException e) {
                    System.out.println("Ошибка базы данных в классе ClientHandler");
                    e.printStackTrace();
                }

                catch (SocketTimeoutException e) {
                    try {
                        out.writeUTF(Command.END);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("Client disconnected");
                    server.unsubscribe(this);
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

    // Метод, показывающий содержимое файла
    private void showFile(String fileName) throws IOException {
        File file = new File(PATHNAME + "\\" + fileName);
        System.out.println(file.getName());

        // Две проверки на существование файла и на то, что он текстовый
        if (!file.exists()) {
            out.writeUTF("Файл не существует");
            return;
        }

        if (!fileName.endsWith(".txt")) {
            out.writeUTF("Это не текстовый файл");
            return;
        }

        out.writeUTF(Command.SHOW_FILE);
        FileInputStream fis = new FileInputStream(file);
        int read;
        byte[] buffer = new byte[512];

        while (true) {
            read = fis.read(buffer);
            if (read == -1) break;
            out.write(buffer, 0, read);
        }
        out.write(-1);
        out.flush();
    }

    // Метод, отправляющий клиенту список файлов из папки
    private void showFilesList() throws IOException {
        File dir = new File(PATHNAME);
        // Сначала отправляем служебную команду, чтобы клиент понял, что дальше пойдёт список файлов
        out.writeUTF(Command.SERVER_FILES_LIST);
        for (File file : dir.listFiles()) {
            out.writeUTF(file.getName());
        }
        // Служебной командой оповещаем клиента, что список файлов закончен
        out.writeUTF(Command.END_OF_FILES_LIST);
    }

    private String censorship(String message) {
        String[] arrayMsg = message.split("\\s");
        message = "";
        for (int i = 0; i < arrayMsg.length; i++) {
            if (Censorship.map.get(arrayMsg[i]) != null)
                arrayMsg[i] = Censorship.map.get(arrayMsg[i]);
            message += arrayMsg[i] + " ";
        }
        return message.trim();
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNickname() {
        return nickname;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public void run() {

    }

    // Метод, принимающий файл и сохраняющий в папку на сервере
    private void receiveFile() throws IOException {

        String fileName = in.readUTF();
        long fileSize = in.readLong();
        String filePath = PATHNAME + "\\" + fileName;
        byte[] buffer = new byte[BUFFER_SIZE];

        try (FileOutputStream fos = new FileOutputStream(filePath)){
            for (int i = 0; i < (fileSize + BUFFER_SIZE - 1) / BUFFER_SIZE; i++) {
                int read = in.read(buffer);
                fos.write(buffer, 0, read);
            }
        }
    }
}
