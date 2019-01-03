package com.qws.link.codec;

/**
 * 国标协议编吗/解码持有者
 */
public class GBCodecHolder {

    private static final GBPacketDecoder decoder = new GBPacketDecoder();

    private static final GBPacketEncoder encoder = new GBPacketEncoder();

    public static GBPacketDecoder decoder() {
        return decoder;
    }

    public static GBPacketEncoder encoder() {
        return encoder;
    }
}
