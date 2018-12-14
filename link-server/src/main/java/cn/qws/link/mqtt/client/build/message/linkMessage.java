package cn.qws.link.mqtt.client.build.message;

import com.qws.link.packet.base.BaseHeader;
import com.qws.link.packet.base.BasePacket;

/**
 * @author qiwenshuai
 * @note
 * @since 18-12-14 16:01 by jdk 1.8
 */
public abstract    class linkMessage {

    private BaseHeader baseHeader;
    private BasePacket basePacket;


    public linkMessage(BaseHeader baseHeader, BasePacket basePacket) {
        this.baseHeader = baseHeader;
        this.basePacket = basePacket;

    }

    public byte[] finalUnBuild() {
        return unbuild(this.baseHeader, this.basePacket);
    }


    public  abstract   byte[] unbuild(BaseHeader baseHeader, BasePacket basePacket);

    public  abstract   linkMessage  build(byte[] bytes);

}
