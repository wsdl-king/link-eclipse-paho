package com.qws.link.protocol;

import com.qws.link.mqtt.gb.GBMessage;
import com.qws.link.mqtt.message.LinkMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author qiwenshuai
 * @note 此处需要编码的操作，变成字节
 * @since 19-1-5 16:30 by jdk 1.8
 */
public class UpgradeEncoder extends MessageToByteEncoder<LinkMessage> {

    private Logger logger = LoggerFactory.getLogger(UpgradeEncoder.class);

    @Override
    protected void encode(ChannelHandlerContext ctx, LinkMessage msg, ByteBuf out) throws Exception {
        if (msg == null || msg.getBaseHeader() == null || msg.getBasePacket() == null) {
            logger.error(" linkMessage not initialize, please check it");
            throw new Exception("编码失败,没有数据信息!");
        }
        byte[] bytes = msg.finalUnBuild();
        out.writeBytes(Unpooled.copiedBuffer(bytes));
    }
}
