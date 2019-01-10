package com.qws.link.message.gb;


import com.alibaba.fastjson.JSON;
import com.qws.link.ByteUtils;
import com.qws.link.base.ByteArrayBuf;
import com.qws.link.base.header.BaseHeader;
import com.qws.link.base.header.GBHeader;
import com.qws.link.base.pakcet.BasePacket;
import com.qws.link.base.pakcet.GBPacket;
import com.qws.link.codec.CheckCode;
import com.qws.link.constant.PacketEnum;
import com.qws.link.message.base.LinkMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author qiwenshuai
 * @note 国标协议 最终构建类LinkMessage
 * @since 18-12-14 14:10 by jdk 1.8
 */
public class GBMessage extends LinkMessage implements Serializable {
    private static final long serialVersionUID = 5677587474108917056L;

    private Integer length;

    private static final Logger logger = LoggerFactory.getLogger(GBMessage.class);


    /**
     * 创建消息必须调用此构造方法
     */
    public GBMessage(BaseHeader gbHeader, BasePacket gbPacket, String type) {
        super(gbHeader, gbPacket, type);
    }

    /**
     * 解析消息可以调用此构造方法
     */
    public GBMessage() {
        super();
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    /**
     * 构建最终报文(国标模式)
     */
    @Override
    public byte[] unbuild(BaseHeader baseHeader, BasePacket basePacket) {
        if(null==baseHeader||null==basePacket){
            logger.error("构造国标报文时候,没有按照指定的构造方法构造");
            return  new byte[0];
        }
        byte[] headerBytes = baseHeader.unbuild();
        byte[] packetBytes = basePacket.unbuild();
        byte checkCode = CheckCode.mathParity(headerBytes, packetBytes);
        return ByteUtils.addAll(headerBytes, packetBytes, checkCode);
    }


    /**
     * 解封成我的国标信息类体
     */
    @Override
    public LinkMessage build(ByteArrayBuf buf) throws Exception {
        byte[] bytes = buf.array();

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
        return new GBMessage(header, packet, type);
    }

}
