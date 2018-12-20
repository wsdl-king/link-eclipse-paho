package com.qws.link.handler.server;

import com.qws.link.handler.AbstractExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author qiwenshuai
 * @note
 * @since 18-12-19 11:08 by jdk 1.8
 */
public class PacketServerHandler extends AbstractExecutor {

    private static final Logger logger = LoggerFactory.getLogger(PacketServerHandler.class);

    private byte[] bytes;

    private Long serverTime;

    private String topic;

    public PacketServerHandler(byte[] bytes, Long serverTime, String topic) {
        this.bytes = bytes;
        this.topic = topic;
        this.serverTime = serverTime;
    }

    @Override
    public void action() {

        //解码,保存报文内容.



    }
}
