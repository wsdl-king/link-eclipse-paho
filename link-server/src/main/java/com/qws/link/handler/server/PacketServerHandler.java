package com.qws.link.handler.server;

import com.qws.link.base.ByteArrayBuf;
import com.qws.link.base.header.GBHeader;
import com.qws.link.base.pakcet.GBPacket;
import com.qws.link.codec.GBCodecHolder;
import com.qws.link.constant.PacketEnum;
import com.qws.link.mqtt.gb.GBMessage;
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

    private ByteArrayBuf byteBuf;

    private Long serverTime;

    private String topic;

    private KafkaTemplate<String, String> kafkaTemplate;

    public PacketServerHandler(byte[] bytes, Long serverTime, String topic, KafkaTemplate<String, String> kafkaTemplate) {
        this.byteBuf = ByteArrayBuf.wrap(bytes);
        this.topic = topic;
        this.serverTime = serverTime;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void action() {
        try {
            //假设收到的就是完整的报文,直接解码
            GBMessage gbMessage = GBCodecHolder.decoder().decoder(byteBuf.array());
            GBHeader gbHeader = gbMessage.getGbHeader();
            GBPacket gbPacket = gbMessage.getGbPacket();
            // 根据header携带的command命令类型进行不同服务的调用
            PacketEnum packetType = PacketEnum.getResponsePacketTypeByCommand(gbMessage.getGbHeader().getCommand());
            System.out.println(packetType.name());
        } catch (Exception e) {
            e.printStackTrace();
        }
//         kafkaTemplate.send("otaUpgrade","otaUpgrade");

    }

}
