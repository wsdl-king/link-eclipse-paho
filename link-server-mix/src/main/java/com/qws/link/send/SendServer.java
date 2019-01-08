package com.qws.link.send;

import com.qws.link.entity.ChannelMap;

/**
 * @author qiwenshuai
 * @note 这个类用来发送命令
 * @since 19-1-8 15:06 by jdk 1.8
 */
public class SendServer {


    public static void send(String key, Object object) {
        ChannelMap.getChannel(key).writeAndFlush(object);
    }


    private SendServer() {

    }
}
