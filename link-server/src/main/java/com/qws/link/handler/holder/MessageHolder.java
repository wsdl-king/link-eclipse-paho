package com.qws.link.handler.holder;

import com.qws.link.handler.manager.LinkDispatchManager;
import com.qws.link.handler.server.PacketServerHandler;
import com.qws.link.mqtt.holder.MqttClientHolder;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author qiwenshuai
 * @note mqtt消息 信息持有者 通过这个持有者,进行线程池添加
 * @since 18-12-29 16:23 by jdk 1.8
 */
@Component
public class MessageHolder {

    private static final String tboxTopic = "device/info";

    private final KafkaTemplate kafkaTemplate;

    @Autowired
    MqttClientHolder mqttClientHolder;

    @Autowired
    public MessageHolder(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void messageArrived(long time, String topic, MqttMessage message) {
        PacketServerHandler packetServerHandler = new PacketServerHandler(message.getPayload(), time, topic, kafkaTemplate,"GB");
        LinkDispatchManager.getInstance().addRunnable(packetServerHandler);
    }


    public void doSubscribe() {
        mqttClientHolder.subsribe(tboxTopic, 1);
    }


}
