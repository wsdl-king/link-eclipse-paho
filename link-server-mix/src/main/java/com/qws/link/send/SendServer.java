package com.qws.link.send;

import com.qws.link.answer.ReceivePacket;
import com.qws.link.base.header.FMHeader;
import com.qws.link.entity.ChannelMap;
import com.qws.link.message.base.LinkMessage;
import io.netty.channel.Channel;

/**
 * @author qiwenshuai
 * @note 这个类用来发送命令
 * @since 19-1-8 15:06 by jdk 1.8
 */
public class SendServer {


    private SendServer() {

    }

    /**
     * 发送应答包,告诉盒子我收到了,暂时不考虑加密
     */
    public static void sendFMReceivedPacket(LinkMessage linkMessage) {
        FMHeader header = (FMHeader) linkMessage.getBaseHeader();
        String sn = header.getSn();
        //来者不善,不是FE开头的
        if (header.getAnswer() != (byte) 0xFE) return;
        header.setAnswer((byte) 1);
        header.setDataLength(0);
        linkMessage.setBaseHeader(header);
        linkMessage.setBasePacket(new ReceivePacket());
        send(sn, linkMessage);
        if (header.getCommand() == 4) {
            // 不信任盒子踢出登录
            // 移除绑定channel
            closeChannel(sn);

        }
    }


    /**
     * 命令发送基类包
     */
    public static void send(String key, Object object) {
        ChannelMap.getChannel(key).writeAndFlush(object);
    }

    private static void closeChannel(String sn) {
          Channel  channel = ChannelMap.getChannel(sn);
                channel.close();
        }

}
