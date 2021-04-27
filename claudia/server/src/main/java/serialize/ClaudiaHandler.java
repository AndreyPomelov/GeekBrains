package serialize;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import model.Package;
import model.PackageType;

public class ClaudiaHandler extends SimpleChannelInboundHandler<Package> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Package aPackage) throws Exception {
        Package pack = aPackage;
        if (pack.getPackageType().equals(PackageType.REG)) {
            
        }
        channelHandlerContext.writeAndFlush(aPackage);
    }
}
