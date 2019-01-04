package com.qws.link.codec;


import com.qws.link.ByteUtils;
import com.qws.link.base.header.GBHeader;
import com.qws.link.base.pakcet.GBPacket;
import com.qws.link.mqtt.gb.GBMessage;

/**
 * 国标协议编码
 */
public class GBPacketEncoder {

    public byte[] encoder(GBMessage message) {
        GBHeader header = (GBHeader) message.getBaseHeader();
        GBPacket body = (GBPacket) message.getBasePacket();

        byte[] bodyBytes = body.unbuild();
        header.setDataLength(bodyBytes.length);
        byte[] headerBytes = header.unbuild();

        byte checkCode = CheckCode.mathParity(headerBytes, bodyBytes);

        return ByteUtils.addAll(headerBytes, bodyBytes, checkCode);
    }

}
