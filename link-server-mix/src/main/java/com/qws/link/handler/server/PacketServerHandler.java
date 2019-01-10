package com.qws.link.handler.server;

import com.qws.link.base.header.FMHeader;
import com.qws.link.base.header.GBHeader;
import com.qws.link.base.pakcet.FMPacket;
import com.qws.link.base.pakcet.GBPacket;
import com.qws.link.constant.FMPacketEnum;
import com.qws.link.constant.PacketEnum;
import com.qws.link.message.fm.FMMessage;
import com.qws.link.message.gb.GBMessage;
import com.qws.link.message.base.LinkMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * @author qiwenshuai
 * @note 收到一手报文, 然后进行解析级分发
 * @since 18-12-19 11:08 by jdk 1.8
 */
public class PacketServerHandler extends AbstractExecutor {


    private static final Logger logger = LoggerFactory.getLogger(PacketServerHandler.class);


    private LinkMessage linkMessage;

    private Long serverTime;

    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 协议类型---GB代表国标
     */
    private String type;

    public PacketServerHandler(LinkMessage linkMessage, Long serverTime, KafkaTemplate<String, String> kafkaTemplate, String type) {
        this.linkMessage = linkMessage;
        this.serverTime = serverTime;
        this.kafkaTemplate = kafkaTemplate;
        this.type = type;
    }

    @Override
    public void action() {
        try {
            switch (type) {
                case "GB":
                    GBMessage gbMessage = (GBMessage) linkMessage;
                    GBHeader gbHeader = (GBHeader) gbMessage.getBaseHeader();
                    GBPacket gbPacket = (GBPacket) gbMessage.getBasePacket();
                    // 根据header携带的command命令类型进行不同服务的调用
                    PacketEnum packetType = PacketEnum.getResponsePacketTypeByCommand(gbHeader.getCommand());
                    System.out.println(packetType.name());
                    break;
                case "FM":
                    FMMessage fmMessage = (FMMessage) linkMessage;
                    FMHeader fmHeader = (FMHeader) fmMessage.getBaseHeader();
                    FMPacket fmPacket = (FMPacket) fmMessage.getBasePacket();
                    // 根据header携带的command命令类型进行不同服务的调用
                    FMPacketEnum fmPacketEnum = FMPacketEnum.getResponsePacketTypeByCommand(fmHeader.getCommand());
                    System.out.println(fmPacketEnum.name());
                    break;
                default:
                    break;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
//         kafkaTemplate.send("otaUpgrade","otaUpgrade");

    }

}
