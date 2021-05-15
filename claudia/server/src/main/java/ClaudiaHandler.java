import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import model.Package;
import model.PackageType;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

// Это основной хэндлер сервера
@Slf4j
public class ClaudiaHandler extends SimpleChannelInboundHandler<Package> {

    // Переменная, определяющая, авторизовался ли пользователь.
    boolean authorized = false;
    String userName;
    // Корневая папка пользователя на сервере
    String userDir;
    // Текущая папка, где находится пользователь
    String currentDir;
    private final Server server;
    DataBaseHandler dataBaseHandler;
    private DataInputStream in;
    private DataOutputStream out;
    private Socket socket;
    private ServerSocket fileServer;
    private final int BUFFER_SIZE = 512;
    private final int PORT = 8190;

    public ClaudiaHandler(Server server) {
        this.server = server;
        dataBaseHandler = new DataBaseHandler();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("Exception caught in main connection handler");
        cause.printStackTrace();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        log.debug("Client connected");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        log.debug("Client disconnected");
    }

    private void sendFilesList () {
        List<String> list = new ArrayList<>();
        File dir = new File(currentDir);
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                list.add(file.getName() + " (folder)");
            } else {
                list.add(file.getName());
            }
        }
        Package pack1 = new Package(PackageType.SHOW_FILES);
        pack1.setFilesList(list);
        server.write(pack1);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Package pack) throws Exception {

        if (pack.getPackageType().equals(PackageType.MAKE_DIR) && authorized) {
            File file = new File(currentDir + pack.getFileName());
            file.mkdir();
        }

        if (pack.getPackageType().equals(PackageType.SHOW_FILES) && authorized) {
            sendFilesList();
        }

        if (pack.getPackageType().equals(PackageType.REG)) {
            if (pack.getLogin().equals("") || pack.getPassword().equals("")) return;
            boolean regOK = DataBaseHandler.registration(pack.getLogin(), pack.getPassword());
            log.debug("Client registration: {}, login: {}", regOK, pack.getLogin());
            // TODO вернуть ответ клиенту
            if (regOK) server.write(new Package(PackageType.REG_OK));
            else server.write(new Package(PackageType.REG_FAIL));
        }

        if (pack.getPackageType().equals(PackageType.AUTH)) {
            if (pack.getLogin().equals("") || pack.getPassword().equals("")) return;
            String actualPass = DataBaseHandler.getPassword(pack.getLogin());
            authorized = actualPass.equals(pack.getPassword());
            if (authorized) {
                log.debug("Client authorised. Login: {}", pack.getLogin());
                userName = pack.getLogin();
                userDir = "server/userFiles/" + userName + "/";
                currentDir = userDir;
                File userRootDir = new File(userDir);
                userRootDir.mkdir();
                server.write(new Package(PackageType.AUTH_OK));
            } else server.write(new Package(PackageType.AUTH_FAIL));
        }

        if (pack.getPackageType().equals(PackageType.GET_FILE) && authorized) {
            new Thread(() -> {
                try {
                    if (pack.getFileName().equals("")) return;
                    File file = new File(currentDir + pack.getFileName());
                    fileServer = new ServerSocket(PORT);
                    socket = fileServer.accept();
                    out = new DataOutputStream(socket.getOutputStream());
                    out.writeLong(Files.size(Paths.get(currentDir + pack.getFileName())));
                    byte[] buffer = new byte[BUFFER_SIZE];
                    try (FileInputStream fis = new FileInputStream(file)){
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
                    fileServer.close();
                } catch (Exception e) {
                    log.error("File sending error");
                }
            }).start();
        }

        if (pack.getPackageType().equals(PackageType.FILE) && authorized) {
            new Thread(() -> {
                try {
                    if (pack.getFileName().equals("")) return;
                    File file = new File(currentDir + pack.getFileName());
                    file.delete();
                    file.createNewFile();
                    fileServer = new ServerSocket(PORT);
                    socket = fileServer.accept();
                    in = new DataInputStream(socket.getInputStream());
                    byte[] buffer = new byte[BUFFER_SIZE];
                    try (FileOutputStream fos = new FileOutputStream(currentDir + pack.getFileName())) {
                        for (int i = 0; i < (pack.getFileSize() + BUFFER_SIZE - 1) / BUFFER_SIZE; i++) {
                            int read = in.read(buffer);
                            fos.write(buffer, 0, read);
                        }
                    } catch (Exception e) {
                        log.error("File writing error");
                    }
                    in.close();
                    socket.close();
                    fileServer.close();
                } catch (Exception e) {
                    log.error("File writing error");
                }
            }).start();
        }

        if (pack.getPackageType().equals(PackageType.GO_TO_DIR) && authorized) {
            currentDir = currentDir + pack.getFileName() + "/";
            sendFilesList();
        }

        if (pack.getPackageType().equals(PackageType.TO_PARENT_DIR) && authorized) {
            if (!currentDir.equals(userDir)) {
                String s = currentDir.substring(0, currentDir.length() - 1);
                int index = s.lastIndexOf("/");
                currentDir = s.substring(0, index) + "/";
            }
            sendFilesList();
        }
    }
}
