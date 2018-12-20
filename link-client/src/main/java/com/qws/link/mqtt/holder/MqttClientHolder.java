package com.qws.link.mqtt.holder;

import com.qws.link.mqtt.client.MqClient;
import com.qws.link.base.header.GBHeader;
import com.qws.link.base.pakcet.GBPacket;
import com.qws.link.mqtt.build.MqMessageBuilder;
import com.qws.link.mqtt.gb.GBMessage;
import com.qws.link.prototype.GBStrategy;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author qiwenshuai
 * @note mqtt client 持有者 打算以这个类发起关于client 的各种请求
 * @since 18-12-14 11:45 by jdk 1.8
 */
@Component
public class MqttClientHolder {


    private final GBStrategy gbStrategy;

    private final MqClient mqClient;

    private Logger logger = LoggerFactory.getLogger(MqttClientHolder.class);

    @Autowired
    public MqttClientHolder(MqClient mqClient, GBStrategy gbStrategy) {
        this.mqClient = mqClient;
        this.gbStrategy = gbStrategy;
    }

    /**
     * 订阅
     */
    Boolean subsribe(String topic, String qos) {
        if (!mqClient.getMqttClient().isConnected()) {
            return false;
        }
        try {
            mqClient.getMqttClient().subscribe(topic);
            return true;
        } catch (MqttException e) {
            logger.error("订阅mqtt服务端失败", e);
            return false;
        }
    }


    /**
     * 默认的话 调用的是国标未加密的包,由rest调用
     */
    public Boolean publish(String type, String topic, int command, int answer, String vin, int qos) {
        return gbPublish(type, topic, "##", command, answer, vin, 1, qos);
    }

    /**
     * 发布,我打算自己组装message,作为最基类的发送消息,由rest调用
     */
    public Boolean gbPublish(String type, String topic, String begin, int command, int answer, String vin, Integer
            encryptType, int qos) {
        // 这里来组装 header 和packet 最后进行调用
        GBHeader gbHeader = new GBHeader(begin, command, answer, vin, encryptType, 1);
//        GBPacket packet = gbStrategy.getGBPacket(type);
//        return gbSend(topic, gbHeader, packet, qos);
        return null;
    }


    /**
     * 最终发布者发布,不带起始符和加密类型
     */
    private Boolean gbSend(String topic, GBHeader gbHeader, GBPacket gbPacket, int qos) {
        if (!mqClient.getMqttClient().isConnected()) {
            return false;
        }
        try {
            //构建最终的请求体
            MqttMessage mqttMessage = MqMessageBuilder.getInstance().buildMessage(new GBMessage(gbHeader, gbPacket));
            mqttMessage.setQos(qos);
            mqClient.getMqttClient().publish(topic, mqttMessage);
            //发送给mqtt服务器
            return true;
        } catch (Exception e) {
            logger.error("订阅mqtt服务端失败", e);
            return false;
        }
    }

}
