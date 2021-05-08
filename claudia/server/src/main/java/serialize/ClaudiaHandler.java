package serialize;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import model.Package;
import model.PackageType;

// Это основной хэндлер сервера
@Slf4j
public class ClaudiaHandler extends SimpleChannelInboundHandler<Package> {

    // Переменная, определяющая, авторизовался ли пользователь.
    boolean authorized = false;
    String userName;
    // Корневая папка пользователя на сервере
    String userDir;

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("Exception caught in main connection handler");
    }

    // Текущая папка, где находится пользователь
    String currentDir;

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
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Package aPackage) throws Exception {
        Package pack = aPackage;

        if (pack.getPackageType().equals(PackageType.REG)) {
            boolean regOK = DataBaseHandler.registration(pack.getLogin(), pack.getPassword());
            log.debug("Client registration: {}, login: {}", regOK, pack.getLogin());
            // TODO вернуть ответ клиенту
        }

        if (pack.getPackageType().equals(PackageType.AUTH)) {
            String actualPass = DataBaseHandler.getPassword(pack.getLogin());
            authorized = actualPass.equals(pack.getPassword());
            if (authorized) {
                log.debug("Client authorised. Login: {}", pack.getLogin());
                userName = pack.getLogin();
                userDir = "claudia/server/userFiles/" + userName;
                currentDir = userDir;
            }
            // TODO вернуть ответ клиенту
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

        channelHandlerContext.writeAndFlush(aPackage);
    }
}
