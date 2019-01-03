package com.qws.link.mqtt.message;

import com.qws.link.base.ByteArrayBuf;
import com.qws.link.base.header.BaseHeader;
import com.qws.link.base.pakcet.BasePacket;

/**
 * @author qiwenshuai
 * @note
 * @since 18-12-14 16:01 by jdk 1.8
 */
public abstract class LinkMessage {

    private BaseHeader baseHeader;
    private BasePacket basePacket;


    public LinkMessage(BaseHeader baseHeader, BasePacket basePacket) {
        this.baseHeader = baseHeader;
        this.basePacket = basePacket;

    }

    public byte[] finalUnBuild() {
        return unbuild(this.baseHeader, this.basePacket);
    }


    public abstract byte[] unbuild(BaseHeader baseHeader, BasePacket basePacket);

    public abstract LinkMessage build(ByteArrayBuf buf) throws Exception;

    public BaseHeader getBaseHeader() {
        return baseHeader;
    }

    public void setBaseHeader(BaseHeader baseHeader) {
        this.baseHeader = baseHeader;
    }

    public BasePacket getBasePacket() {
        return basePacket;
    }

    public void setBasePacket(BasePacket basePacket) {
        this.basePacket = basePacket;
    }
}
