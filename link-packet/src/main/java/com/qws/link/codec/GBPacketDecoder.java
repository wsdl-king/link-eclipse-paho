package com.qws.link.codec;

import com.alibaba.fastjson.JSON;
import com.qws.link.ByteUtils;
import com.qws.link.base.ByteArrayBuf;
import com.qws.link.base.header.GBHeader;
import com.qws.link.base.pakcet.GBPacket;
import com.qws.link.constant.PacketEnum;
import com.qws.link.message.gb.GBMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;


/**
 * 国标协议编码解析
 */
public class GBPacketDecoder {

    private static final Logger logger = LoggerFactory.getLogger(GBPacketDecoder.class);

    private static final String type = "GB";

    public GBMessage decoder(byte[] bytes) throws Exception {

        if (bytes.length < GBHeader.HEADER_LENGTH) {
            throw new IllegalArgumentException("the length of byte is not enough");
        }

        if (!GBHeader.HEADER_BEGIN.equals(ByteUtils.getStringFromBytes(new byte[]{bytes[0], bytes[1]}))) {
            throw new IllegalArgumentException("the packet is not begin with '##'");
        }

        byte[] headerBytes = Arrays.copyOfRange(bytes, 0, GBHeader.HEADER_LENGTH);
        byte[] bodyBytes = Arrays.copyOfRange(bytes, GBHeader.HEADER_LENGTH, bytes.length - 1);
        byte parity = bytes[bytes.length - 1];

        if (logger.isDebugEnabled()) {
            logger.debug("headerBytes:{}", ByteUtils.asHex(headerBytes));
            logger.debug("bodyBytes:{}", ByteUtils.asHex(bodyBytes));
            logger.debug("parityByte:{}", ByteUtils.asHex(new byte[]{parity}));
        }

        if (CheckCode.mathParity(headerBytes, bodyBytes) != parity) {
            if (logger.isDebugEnabled()) {
                logger.debug("[CheckCode.mathParity], parity:{}", ByteUtils.asHex(new byte[]{CheckCode.mathParity(headerBytes, bodyBytes)}));
            }
            throw new IllegalArgumentException("the packet checked fail");
        }

        GBHeader header = new GBHeader(ByteArrayBuf.wrap(headerBytes));

        if (logger.isDebugEnabled()) {
            logger.debug("header:{}", JSON.toJSONString(header));
        }

        if (header.getDataLength() != bodyBytes.length) {
            throw new IllegalArgumentException("the length of body byte is not enough");
        }

        PacketEnum packetType = PacketEnum.getResponsePacketTypeByCommand(header.getCommand());
        if (packetType == null) {
            throw new NullPointerException(" this packetType is null ");
        }
        GBPacket packet = packetType.getResponsePacketInstance();
        packet.build(ByteArrayBuf.wrap(bodyBytes));

        return new GBMessage(header, packet,type);
    }

    public static void main(String[] args) {
        byte[] b = new byte[1024];
        b[0] = 35;
        b[1] = 35;
        String stringFromBytes = ByteUtils.getStringFromBytes(new byte[]{b[0], b[1]});
        System.out.println(stringFromBytes);

    }

}
