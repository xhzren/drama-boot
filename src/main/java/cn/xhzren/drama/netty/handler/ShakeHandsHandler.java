package cn.xhzren.drama.netty.handler;

import cn.xhzren.drama.porto.DramaMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

public class ShakeHandsHandler extends SimpleChannelInboundHandler<Object> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) {
//        DramaMessage.ConMessage.parseFrom();
        if(msg instanceof FullHttpRequest) {
            FullHttpRequest request = (FullHttpRequest)msg;
            //如果不成功或者消息头不包含"Upgrade"，说明不是websocket连接，报400异常
            if(request.decoderResult().isSuccess() ||
                    (!"websocket".equals(request.headers().get("Upgrade")))) {
                sendHttpResponse(ctx,request,new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.INTERNAL_SERVER_ERROR));
            }
        }else {
            ctx.fireChannelRead(msg);
        }
    }

    private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest request, FullHttpResponse response) {
        if(response.status().code() != HttpResponseStatus.OK.code()) {
            ByteBuf buf = Unpooled.copiedBuffer(response.status().toString(), CharsetUtil.UTF_8);
            response.content().writeBytes(buf);
            buf.release();
        }
        //发送message
        ChannelFuture f = ctx.channel().writeAndFlush(response);
    }

}
