package com.qws.link.mqtt.gb;


import com.qws.link.ByteUtils;
import com.qws.link.base.ByteArrayBuf;
import com.qws.link.base.header.BaseHeader;
import com.qws.link.base.header.GBHeader;
import com.qws.link.base.pakcet.BasePacket;
import com.qws.link.base.pakcet.GBPacket;
import com.qws.link.mqtt.message.LinkMessage;
import com.qws.link.realtime.children.CarInfoPacket;

import java.io.Serializable;

/**
 * @author qiwenshuai
 * @note 国标协议 最终构建类LinkMessage
 * @since 18-12-14 14:10 by jdk 1.8
 */
public class GBMessage extends LinkMessage implements Serializable {
    private static final long serialVersionUID = 5677587474108917056L;
    /**
     * 国标数据头
     */
    private GBHeader gbHeader;
    /**
     * 各种类型的国标packet包
     */
    private GBPacket gbPacket;

    /**
     * 总体报文的长度
     */
    private Integer length;


    public GBMessage(GBHeader gbHeader, GBPacket gbPacket) {
        super(gbHeader, gbPacket);
        this.gbPacket = gbPacket;
        this.gbHeader = gbHeader;
    }

    /**
     * mqtt拿到的数据,解锁为实体
     */
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
        byte[] headerBytes = baseHeader.unbuild();
        byte[] packetBytes = basePacket.unbuild();
        return ByteUtils.addAll(headerBytes, packetBytes);
    }


    /**
     * 解封成我的国标信息类体
     */
    @Override
    public LinkMessage build(ByteArrayBuf buf) throws Exception {
        this.gbHeader = new GBHeader(buf);
        this.gbPacket = new CarInfoPacket();
        gbPacket.build(buf);
        return new GBMessage(gbHeader, gbPacket);
    }

    public GBHeader getGbHeader() {
        return gbHeader;
    }

    public void setGbHeader(GBHeader gbHeader) {
        this.gbHeader = gbHeader;
    }

    public GBPacket getGbPacket() {
        return gbPacket;
    }

    public void setGbPacket(GBPacket gbPacket) {
        this.gbPacket = gbPacket;
    }
}
