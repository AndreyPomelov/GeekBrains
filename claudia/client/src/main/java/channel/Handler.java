package channel;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import model.Package;

// Это класс-хэндлер сетевого соединения
public class Handler extends SimpleChannelInboundHandler<Package> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Package aPackage) throws Exception {
        Package pack = aPackage;

        channelHandlerContext.writeAndFlush(aPackage);
    }
}
