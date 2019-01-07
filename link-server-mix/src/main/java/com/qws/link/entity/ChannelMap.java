package com.qws.link.entity;


import io.netty.channel.*;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author qiwenshuai
 * @note 存放一个 key-Channel 对应的实体,key可能是 vin/deviceId/sn
 * @since 19-1-7 09:44 by jdk 1.8
 */
public class ChannelMap {

    private static final ConcurrentHashMap<String, Channel> CHANNEL_CONCURRENT_HASH_MAP = new ConcurrentHashMap<>();

    private Channel channel;

    private String id;

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public static synchronized void addChannelMap(String key, Channel channel) {
        synchronized (ChannelMap.class) {
            // 如果对应的key已经在map中存在,那么就不允许添加.
            if (CHANNEL_CONCURRENT_HASH_MAP.containsKey(key)) {
                return;
            }
            CHANNEL_CONCURRENT_HASH_MAP.put(key, channel);
        }
    }

    public static Channel getChannel(String key) {
        return CHANNEL_CONCURRENT_HASH_MAP.get(key);
    }

    public static synchronized void removeChannel(String key) {
        CHANNEL_CONCURRENT_HASH_MAP.remove(key);

    }
}
