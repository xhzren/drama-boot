package cn.xhzren.drama.netty;

import cn.xhzren.drama.netty.handler.DramaMessageHandler;
import cn.xhzren.drama.netty.handler.RequestParseHandler;
import cn.xhzren.drama.netty.handler.ShakeHandsHandler;
import cn.xhzren.drama.porto.DramaMessage;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.stream.ChunkedWriteHandler;

public class DramaServerStart {

    private int port;

    private EventLoopGroup boss = new NioEventLoopGroup();
    private EventLoopGroup work = new NioEventLoopGroup();

    private ServerBootstrap bs;
    private ChannelFuture future;

    public DramaServerStart(int port) {
        this.port = port;
    }

    public static void main(String[] args) {
        DramaServerStart start = new DramaServerStart(9887);
        start.run();
    }

    public void run() {
        bs= new ServerBootstrap().group(boss, work)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) {
                        ch.pipeline().addLast(new HttpServerCodec());
                        ch.pipeline().addLast(new ChunkedWriteHandler());
                        ch.pipeline().addLast(new HttpObjectAggregator(9128));
                        ch.pipeline().addLast(new WebSocketServerProtocolHandler("/ws","dou"));
                        ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());
                        ch.pipeline().addLast(new ProtobufDecoder(DramaMessage.ConMessage.getDefaultInstance()));
                        ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
                        ch.pipeline().addLast(new ProtobufEncoder());
                        ch.pipeline().addLast(new ShakeHandsHandler());
                        ch.pipeline().addLast(new RequestParseHandler());
                        ch.pipeline().addLast(new DramaMessageHandler());
                    }
                });
       try {
          future = bs.bind(port).sync();
       }catch (Exception e) {
           boss.shutdownGracefully();
           work.shutdownGracefully();
           e.printStackTrace();
       }
    }

    public void close() {
        boss.shutdownGracefully();
        work.shutdownGracefully();
    }

    public ChannelFuture getFuture() {
        return future;
    }
}
