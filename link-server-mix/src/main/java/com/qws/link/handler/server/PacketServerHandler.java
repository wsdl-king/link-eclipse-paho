package com.qws.link.handler.server;

import com.qws.link.base.header.GBHeader;
import com.qws.link.base.pakcet.GBPacket;
import com.qws.link.constant.PacketEnum;
import com.qws.link.mqtt.gb.GBMessage;
import com.qws.link.mqtt.message.LinkMessage;
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
            if ("GB".equals(type)) {
                GBMessage gbMessage = (GBMessage) linkMessage;
                GBHeader gbHeader = (GBHeader) gbMessage.getBaseHeader();
                GBPacket gbPacket = (GBPacket) gbMessage.getBasePacket();
                // 根据header携带的command命令类型进行不同服务的调用
                PacketEnum packetType = PacketEnum.getResponsePacketTypeByCommand(gbHeader.getCommand());
                System.out.println(packetType.name());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//         kafkaTemplate.send("otaUpgrade","otaUpgrade");

    }

}
