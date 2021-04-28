package serialize;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import model.Package;
import model.PackageType;

public class ClaudiaHandler extends SimpleChannelInboundHandler<Package> {

    // Переменная, определяющая, авторизовался ли пользователь.
    boolean authorized = false;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Package aPackage) throws Exception {
        Package pack = aPackage;
        if (pack.getPackageType().equals(PackageType.REG)) {
            boolean regOK = DataBaseHandler.registration(pack.getLogin(), pack.getPassword());
            // TODO вернуть ответ клиенту
        }
        channelHandlerContext.writeAndFlush(aPackage);
    }
}
