package com.qws.link.netty.client;

import com.qws.link.netty.handler.ClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author qiwenshuai
 * @note
 * @since 19-1-7 10:18 by jdk 1.8
 */
public class NettyClient {

    private static Bootstrap b = new Bootstrap();

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup work = new NioEventLoopGroup(1, new ThreadFactory() {
            private AtomicInteger index = new AtomicInteger(0);

            public Thread newThread(Runnable r) {
                return new Thread(r, "WORK_" + index.incrementAndGet());
            }
        });
        b.group(work).
                channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)   //设置NIO的模式
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel sc) {
                        sc.pipeline().addLast(new ClientHandler());
                    }
                });
         b.connect("127.0.0.1", 8172).sync();
    }

}
