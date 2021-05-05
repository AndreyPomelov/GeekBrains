package channel;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

// Этот класс пока не используется
@Slf4j
public class ByteToStringHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        StringBuilder message = new StringBuilder();

        while (buf.isReadable()) {
            message.append((char) buf.readByte());
        }

        ctx.fireChannelRead(message.toString());
    }
}
