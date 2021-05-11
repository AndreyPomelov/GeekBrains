import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import lombok.extern.slf4j.Slf4j;
import model.Package;
import model.PackageType;

import java.io.File;
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

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Package pack) throws Exception {

        if (pack.getPackageType().equals(PackageType.MAKE_DIR)) {
            File file = new File(currentDir + pack.getFileName());
            file.mkdir();
        }

        if (pack.getPackageType().equals(PackageType.SHOW_FILES)) {
            List<String> list = new ArrayList<>();
            File dir = new File(currentDir);
            for (File file : dir.listFiles()) {
                list.add(file.getName());
            }
            Package pack1 = new Package(PackageType.SHOW_FILES);
            pack1.setFilesList(list);
            server.write(pack1);
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

        if (pack.getPackageType().equals(PackageType.FILE)) {
            // TODO Принять файл
        }

        if (pack.getPackageType().equals(PackageType.CREATE_DIR)) {
            // TODO Создать папку
        }

        if (pack.getPackageType().equals(PackageType.GO_TO_DIR)) {
            // TODO Перейти в папку
        }

    }
}
