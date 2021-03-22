package com.test.netty;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.CharsetUtil;

/**
 * @author dengxiaolin
 * @since 2020/08/05
 */
public class NettyServer {
    public static void main(String[] args) {

        final int maxLength = 100;

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootStrap = new ServerBootstrap();
            ServerBootstrap serverBootstrap = serverBootStrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler())
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("sout1", new ChannelInboundHandlerAdapter() {
                                        @Override
                                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                            //传来的消息包装成字节缓冲区
                                            ByteBuf byteBuf = (ByteBuf) msg;
                                            //Netty提供了字节缓冲区的toString方法，并且可以设置参数为编码格式：CharsetUtil.UTF_8
                                            System.out.println("客户端发来的消息-1handler：" + byteBuf.toString(CharsetUtil.UTF_8));
                                            ctx.writeAndFlush(Unpooled.copiedBuffer("服务器已收到消息", CharsetUtil.UTF_8));
                                            super.channelRead(ctx, msg);
                                        }
                                    }
                            ).addLast("sout2", new ChannelInboundHandlerAdapter() {
                                        @Override
                                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                            //传来的消息包装成字节缓冲区
                                            ByteBuf byteBuf = (ByteBuf) msg;
                                            //Netty提供了字节缓冲区的toString方法，并且可以设置参数为编码格式：CharsetUtil.UTF_8
                                            System.out.println("客户端发来的消息-2handler：" + byteBuf.toString(CharsetUtil.UTF_8));
                                        }
                                    }
                            );

                        }
                    })
                    // 服务端接受连接的队列长度，如果队列已满，客户端连接将被拒绝
                    .option(ChannelOption.SO_BACKLOG, 128)
                    // CP会主动探测空闲连接的有效性。可以将此功能视为TCP的心跳机制，需要注意的是：默认的心跳间隔是7200s即2小时
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            // 发起同步连接操作
            ChannelFuture channelFuture = serverBootstrap.bind("localhost", 8080).sync();
            channelFuture.channel().closeFuture().sync();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }


}
