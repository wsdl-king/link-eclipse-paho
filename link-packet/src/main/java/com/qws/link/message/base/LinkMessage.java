package com.qws.link.message.base;

import com.qws.link.base.ByteArrayBuf;
import com.qws.link.base.header.BaseHeader;
import com.qws.link.base.pakcet.BasePacket;

/**
 * @author qiwenshuai
 * @note
 * @since 18-12-14 16:01 by jdk 1.8
 */
public abstract class LinkMessage {

    protected BaseHeader baseHeader;
    protected BasePacket basePacket;
    protected String type;

    public LinkMessage(BaseHeader baseHeader, BasePacket basePacket, String type) {
        this.baseHeader = baseHeader;
        this.basePacket = basePacket;
        this.type = type;

    }

    public LinkMessage() {

    }

    public byte[] finalUnBuild() {
        return unbuild(this.baseHeader, this.basePacket);
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public abstract byte[] unbuild(BaseHeader baseHeader, BasePacket basePacket);

    public abstract LinkMessage build(ByteArrayBuf buf) throws Exception;

//    public Object  getMessageByType(){
//        if("GB".equals(type)){
//            return  (GBMessage)this;
//        }
//    }
}
