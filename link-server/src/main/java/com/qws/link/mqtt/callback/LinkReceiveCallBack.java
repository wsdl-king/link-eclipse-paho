package com.qws.link.mqtt.callback;

import com.qws.link.ByteUtils;
import com.qws.link.handler.LinkDispatchManager;
import com.qws.link.handler.server.PacketServerHandler;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author qiwenshuai
 * @note
 * @since 18-12-13 16:56 by jdk 1.8
 */
@Component
public class LinkReceiveCallBack implements MqttCallbackExtended {

    private static final Logger logger = LoggerFactory.getLogger(LinkReceiveCallBack.class);

    @Override
    public void connectComplete(boolean reconnect, String serverURI) {
        logger.info(" 连接完成 : connectComplete");
    }

    @Override
    public void connectionLost(Throwable cause) {
        logger.info("connectionLost");

    }

    @Override
    public void messageArrived(String topic, MqttMessage message) {
        //收到报文的时间.
        long time = System.currentTimeMillis();
        // 统一的报文处理入口
        PacketServerHandler packetServerHandler = new PacketServerHandler(message.getPayload(), time, topic);
        //丢给线程池去处理
        LinkDispatchManager.getInstance().addRunnable(packetServerHandler);
        String s = ByteUtils.asHex(message.getPayload());
        logger.info("topic: {}", s);
        logger.info("messageArrived");

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        logger.info("交货完成");

    }
}
