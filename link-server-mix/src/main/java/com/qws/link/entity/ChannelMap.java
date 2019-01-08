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


    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @apiNote key必须保证全局唯一, 在链接建立的时候, 需要保存对应key的channel
     */
    public static void addChannelMap(String key, Channel channel) {
        if (!CHANNEL_CONCURRENT_HASH_MAP.containsKey(key)) {
            synchronized (ChannelMap.class) {
                if (!CHANNEL_CONCURRENT_HASH_MAP.containsKey(key)) {
                    // 如果对应的key已经在map中存在,那么就不允许添加.
                    CHANNEL_CONCURRENT_HASH_MAP.put(key, channel);
                }
            }
        }
    }

    public static Channel getChannel(String key) {
        return CHANNEL_CONCURRENT_HASH_MAP.get(key);
    }


    /**
     * @apiNote key必须保证全局唯一, 在链接关闭的时候, 需要去除对应key的channel
     */
    public static void removeChannel(String key) {
        if (CHANNEL_CONCURRENT_HASH_MAP.containsKey(key)) {
            synchronized (ChannelMap.class) {
                if (CHANNEL_CONCURRENT_HASH_MAP.containsKey(key)) {
                    // 如果对应的key已经在map中存在,那么就不允许添加.
                    CHANNEL_CONCURRENT_HASH_MAP.remove(key);
                }
            }
        }
    }
}
