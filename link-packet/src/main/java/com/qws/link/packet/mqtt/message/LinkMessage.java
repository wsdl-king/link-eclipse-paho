package com.qws.link.packet.mqtt.message;

import com.qws.link.packet.base.header.BaseHeader;
import com.qws.link.packet.base.pakcet.BasePacket;

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

    public abstract LinkMessage build(byte[] bytes);

}
