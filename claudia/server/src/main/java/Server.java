import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import lombok.extern.slf4j.Slf4j;
import model.Package;

@Slf4j
public class Server {

    private SocketChannel serverChannel;
    private final ClaudiaHandler claudiaHandler = new ClaudiaHandler(this);

    public Server() {

        EventLoopGroup authGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(authGroup, workGroup).
                    channel(NioServerSocketChannel.class).
                    childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) {
                            serverChannel = channel;
                            channel.pipeline().addLast(
                                    new ObjectDecoder(ClassResolvers.cacheDisabled(null)),
                                    new ObjectEncoder(),
                                    claudiaHandler
                            );
                        }
                    });
            ChannelFuture future = bootstrap.bind(8189).sync();
            log.debug("Server is online");
            future.channel().closeFuture().sync();

        } catch (Exception e) {
            log.error("Exception: " + e);
        } finally {
            authGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    public void write(Package pack) {
        serverChannel.writeAndFlush(pack);
    }
}
