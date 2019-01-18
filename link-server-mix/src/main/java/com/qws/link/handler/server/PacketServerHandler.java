package com.qws.link.handler.server;

import com.alibaba.fastjson.JSON;
import com.qws.link.base.header.FMHeader;
import com.qws.link.base.header.GBHeader;
import com.qws.link.base.pakcet.FMPacket;
import com.qws.link.base.pakcet.GBPacket;
import com.qws.link.constant.FMPacketEnum;
import com.qws.link.constant.PacketEnum;
import com.qws.link.entity.ChannelAttr;
import com.qws.link.entity.ChannelMap;
import com.qws.link.exception.MessageTypeNotFoundException;
import com.qws.link.login.FMRegPacket;
import com.qws.link.logout.FMLogoutPacket;
import com.qws.link.message.fm.FMMessage;
import com.qws.link.message.gb.GBMessage;
import com.qws.link.message.base.LinkMessage;
import com.qws.link.send.SendServer;
import io.netty.channel.Channel;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * @author qiwenshuai
 * @note 收到一手报文, 然后进行解析级分发
 * @since 18-12-19 11:08 by jdk 1.8
 */
public class PacketServerHandler extends AbstractExecutor {


    private static final String CHANNEL = "netty.channel";

    private static final Logger logger = LoggerFactory.getLogger(PacketServerHandler.class);


    private LinkMessage linkMessage;

    /**
     * 服务器接受报文时间
     */
    private Long serverTime;

    /**
     * 登录管道
     */
    private Channel channel;

    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 协议类型---GB代表国标
     */
    private String type;

    public PacketServerHandler(LinkMessage linkMessage, Long serverTime, KafkaTemplate<String, String> kafkaTemplate, String type, Channel channel) {
        this.linkMessage = linkMessage;
        this.serverTime = serverTime;
        this.kafkaTemplate = kafkaTemplate;
        this.channel = channel;
        this.type = type;
    }

    @Override
    public void action() {
        try {
            switch (type) {
                //下文是解析国标协议的,目前不做考虑
              /*  case "GB":
                    GBMessage gbMessage = (GBMessage) linkMessage;
                    GBHeader gbHeader = (GBHeader) gbMessage.getBaseHeader();
                    GBPacket gbPacket = (GBPacket) gbMessage.getBasePacket();
                    // 根据header携带的command命令类型进行不同服务的调用
                    PacketEnum packetType = PacketEnum.getResponsePacketTypeByCommand(gbHeader.getCommand());
                    break;*/
                case "FM":
                    FMMessage fmMessage = (FMMessage) linkMessage;
                    FMHeader fmHeader = (FMHeader) fmMessage.getBaseHeader();
                    FMPacket fmPacket = (FMPacket) fmMessage.getBasePacket();
                    // 根据header携带的command命令类型进行不同服务的调用
                    FMPacketEnum fmPacketEnum = FMPacketEnum.getResponsePacketTypeByCommand(fmHeader.getCommand());
                    if (null == fmPacketEnum) {
                        throw new MessageTypeNotFoundException("没有找到与之对应解析类型, fmPacketEnum 为null");
                    }
                    processingMessages(fmPacketEnum, fmHeader, fmPacket);
                    break;
                default:
                    break;
            }
            //发给你应答包,告诉盒子俺收到了
            SendServer.sendFMReceivedPacket(linkMessage);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    //如果是登录的话 需要sn和channel进行绑定
    private void bindChannel(String sn) {
        AttributeKey<Object> objectAttributeKey = AttributeKey.valueOf(CHANNEL);
        ChannelAttr channelAttr = new ChannelAttr();
        channelAttr.setSn(sn);
        channel.attr(objectAttributeKey).set(channelAttr);
        ChannelMap.addChannelMap(sn, channel);
    }

    private void processingMessages(FMPacketEnum packetType, FMHeader fmHeader, FMPacket fmPacket) throws Exception {
        String sn = fmHeader.getSn();
        switch (packetType) {
            case LOGIN:
                //登录
                //sn和channel进行绑定--这个还是要考虑 以哪个为优先绑定
                bindChannel(sn);
                //登录逻辑
                if (fmPacket instanceof FMRegPacket) {
                    FMRegPacket regPacket = (FMRegPacket) fmPacket;
//                    kafkaTemplate.send("mytopic", fmHeader.getSn(), JSON.toJSONString(regPacket));
                }
                break;
            case LOGOUT:
                //登出
                if (null == ChannelMap.getChannel(sn)) {
                    //未登录就登出
                    throw new IllegalArgumentException("车辆未登录就出现了登出包,未存在补发数据,拒绝应答");
                } else {
                    //登出逻辑 kafka等
                    System.out.println("我要登录出去了 ");
                }
                break;

            case CONTROLANSWER:
                //升级命令应答

            default:
                break;
        }
    }

}
