package com.qws.link.netty.server;

import com.qws.link.common.IpUtils;
import com.qws.link.netty.codec.UpgradeEncoder;
import com.qws.link.netty.handler.ServerHandler;
import com.qws.link.netty.codec.UpgradeDecoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author qiwenshuai
 * @note 启动类
 * @since 19-1-5 16:13 by jdk 1.8
 */
@Component
public class NettyServer {

    private Logger logger = LoggerFactory.getLogger(NettyServer.class);

    private EventLoopGroup bossGroup;

    private EventLoopGroup workGroup;

    private static final int port = 8182;
    private static final int MAX_FRAME_LENGTH = 1024 * 1024;  //最大长度
    private static final int LENGTH_FIELD_OFFSET = 22;  //长度字段偏移字节数--依据国标
    private static final int LENGTH_FIELD_LENGTH = 2;  //长度字段所占的字节数--依据国标
    private static final int LENGTH_ADJUSTMENT = 1;//数据长度字段之后剩下包的字节数--依据国标BCC校验码
    private static final int INITIAL_BYTES_TO_STRIP = 0;//表示从整个包第一个字节开始，向后忽略的字节数


    public void initNetty() {
        logger.info("开始初始化netty服务端");
        initServer();
    }

    /**
     * ChanneOption.SO_REUSEADDR对应于套接字选项中的SO_REUSEADDR，这个参数表示允许重复使用本地地址和端口，
     * 比如，某个服务器进程占用了TCP的80端口进行监听，此时再次监听该端口就会返回错误，使用该参数就可以解决问题，该参数允许共用该端口，这个在服务器程序中比较常使用，
     * 比如某个进程非正常退出，该程序占用的端口可能要被占用一段时间才能允许其他进程使用，而且程序死掉以后，内核一需要一定的时间才能够释放此端口，不设置SO_REUSEADDR
     * 就无法正常使用该端口。
     * ChannelOption.SO_RCVBUF 接受buff缓冲区
     */

    private void initServer() {
        // 启动辅助类
        ServerBootstrap bootstrap = new ServerBootstrap();
        bossGroup = new NioEventLoopGroup(2, new ThreadFactory() {
            private AtomicInteger index = new AtomicInteger(0);

            public Thread newThread(Runnable r) {
                return new Thread(r, "BOSS_" + index.incrementAndGet());
            }
        });
        workGroup = new NioEventLoopGroup(4, new ThreadFactory() {
            private AtomicInteger index = new AtomicInteger(0);

            public Thread newThread(Runnable r) {
                return new Thread(r, "WORK_" + index.incrementAndGet());
            }
        });
        bootstrap.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_REUSEADDR, true)
                .option(ChannelOption.SO_BACKLOG, 1024)//设置TCP缓冲区
                .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .option(ChannelOption.SO_RCVBUF, 1024 * 1024) // 设置接受数据的缓存大小
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel ch) throws Exception {
                        initHandler(ch.pipeline());
                    }
                })
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
        bootstrap.bind("192.168.88.152", port).addListener((ChannelFutureListener) channelFuture -> {
            if (channelFuture.isSuccess())
                logger.info("服务端启动成功【" + IpUtils.getHost() + ":" + port + "】");
            else
                logger.info("服务端启动失败【" + IpUtils.getHost() + ":" + port + "】");
        });

    }


    /**
     * 初始化channelHandler处理器
     */
    private void initHandler(ChannelPipeline channelPipeline) {

        //心跳处理机制
        channelPipeline.addLast(new IdleStateHandler(30, 0, 0));
        //固定长度解码
        channelPipeline.addLast(new UpgradeDecoder(MAX_FRAME_LENGTH, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGTH, LENGTH_ADJUSTMENT, INITIAL_BYTES_TO_STRIP, false));
        // 我实际的业务处理机制
        channelPipeline.addLast(new ServerHandler());
        //编码器
        channelPipeline.addLast(new UpgradeEncoder());

    }


    /**
     * 关闭资源
     */
    public void shutdown() {
        if (workGroup != null && bossGroup != null) {
            try {
                // 优雅关闭
                bossGroup.shutdownGracefully().sync();
                workGroup.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                logger.info("服务端关闭资源失败【" + IpUtils.getHost() + ":" + port + "】");
            }
        }
    }
}
