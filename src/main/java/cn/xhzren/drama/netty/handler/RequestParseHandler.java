package cn.xhzren.drama.netty.handler;

import cn.xhzren.drama.porto.DramaMessage.*;
import cn.xhzren.drama.porto.Receive.*;
import cn.xhzren.drama.porto.Receive.ReceiveMessage.*;
import cn.xhzren.drama.proto.Auth.*;
import cn.xhzren.drama.proto.Auth.AuthMessage.*;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.nio.ByteBuffer;

public class RequestParseHandler extends ChannelInboundHandlerAdapter {
//    List<Room> active = new ArrayList<>();

    ByteBuf data;
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof BinaryWebSocketFrame) {
            BinaryWebSocketFrame binary = (BinaryWebSocketFrame)msg;
            ConMessage conMessage = makeMessage(binary.content());
            if(conMessage.getRequestInfo().hasToken()) {
                ctx.fireChannelRead(msg);
            }else {
                byte[] res = AuthMessage.newBuilder()
                        .setAuthType(AuthType.NOT_TOKEN)
                        .setContent("test")
                        .build().toByteArray();
                data = Unpooled.buffer(res.length);
                data.writeBytes(res);
                ctx.writeAndFlush(new BinaryWebSocketFrame(data));
            }
        }
    }

    byte[] bytes;
    private ConMessage makeMessage(ByteBuf byteBuf) throws Exception {
        bytes = new byte[byteBuf.capacity()];
        byteBuf.readBytes(bytes);
        return ConMessage.parseFrom(bytes);
    }
}
