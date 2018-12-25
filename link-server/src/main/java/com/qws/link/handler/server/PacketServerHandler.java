package com.qws.link.handler.server;

import com.qws.link.base.ByteArrayBuf;
import com.qws.link.handler.AbstractExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author qiwenshuai
 * @note  收到一手报文,然后进行解析级分发
 * @since 18-12-19 11:08 by jdk 1.8
 */
public class PacketServerHandler extends AbstractExecutor {

    private static final Logger logger = LoggerFactory.getLogger(PacketServerHandler.class);

    private ByteArrayBuf byteBuf;

    private Long serverTime;

    private String topic;

    public PacketServerHandler(byte[] bytes, Long serverTime, String topic) {
        this.byteBuf = ByteArrayBuf.warp(bytes);
        this.topic = topic;
        this.serverTime = serverTime;
    }

    @Override
    public void action() {

        //解码,保存报文内容.
        //解析指定位,然后看报文的类型
//        byteBuf.readBytes()


    }


    public static void main(String[] args) {
        byte[] a = new byte[1024];
        a[0]=1;
        a[1]=2;
        a[2]=3;
        System.out.println(ByteArrayBuf.warp(a).readBytes(1));
    }
}
