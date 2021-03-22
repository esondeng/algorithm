package com.test.netty;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

/**
 * @author dengxiaolin
 * @since 2020/08/07
 */
public class NettyClient {

    public static void main(String[] args) {
        // 创建一个Bootstrap实例
        Bootstrap bootstrap = new Bootstrap();
        // 指定EventLoopGroup注册Channel
        bootstrap.group(new NioEventLoopGroup())
                // 指定Channel类型为NioSocketChannel
                .channel(NioSocketChannel.class)
                // 添加ChannelHandler
                .handler(new ChannelInitializer<SocketChannel>() {
                    // 创建一个通道初始化对象
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        // 往pipeline中添加自定义的handler
                        socketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter() {

                            // 通道就绪事件(就是在bootstrap启动助手配置中addlast了handler之后就会触发此事件)
                            @Override
                            public void channelActive(ChannelHandlerContext ctx) {
                                System.out.println("Client :" + ctx);
                                //向服务器端发消息
                                ctx.writeAndFlush(Unpooled.copiedBuffer("你好啊！\n", CharsetUtil.UTF_8));

                            }

                            // 数据读取事件
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) {
                                //传来的消息包装成字节缓冲区
                                ByteBuf byteBuf = (ByteBuf) msg;
                                //Netty提供了字节缓冲区的toString方法，并且可以设置参数为编码格式：CharsetUtil.UTF_8
                                System.out.println("服务器端发来的消息：" + byteBuf.toString(CharsetUtil.UTF_8));

                            }

                        });
                    }
                });

        // 使用配置好的启动器连接远程地址
        ChannelFuture future = bootstrap.connect(new InetSocketAddress("localhost", 8080));
        future.addListener((channelFuture) -> {
            if (channelFuture.isSuccess()) {
                System.out.println("Connection established");
            }
            else {
                System.err.println("Connection attempt failed");
                channelFuture.cause().printStackTrace();
            }
        });
    }
}
