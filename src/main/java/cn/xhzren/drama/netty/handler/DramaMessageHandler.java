package cn.xhzren.drama.netty.handler;

import cn.xhzren.drama.porto.DramaMessage.*;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public class DramaMessageHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof TextWebSocketFrame) {
            TextWebSocketFrame text = (TextWebSocketFrame)msg;
            System.out.println("" + text.text());
        }else if(msg instanceof BinaryWebSocketFrame) {
            BinaryWebSocketFrame binary = (BinaryWebSocketFrame)msg;
//            ConMessage conMessage = makeMessage(binary.content());
        }
    }




    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        System.out.println(ctx.channel().id() +"is active");
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
//        Jedis jedis = RedisHelper.getJedis();
//        jedis.set(ctx.channel().id().asShortText(), ctx.channel().id().asShortText());
//        RedisHelper.close(jedis);
//        System.out.println(ctx.channel().id() + "-> 加入了server");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
//        System.out.println(ctx.channel().id() + "-> 离去了server");
//        Jedis jedis = RedisHelper.getJedis();
//        jedis.del(ctx.channel().id().asShortText());
//        RedisHelper.close(jedis);
    }

}
