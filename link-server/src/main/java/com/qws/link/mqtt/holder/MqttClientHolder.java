package com.qws.link.mqtt.holder;

import com.qws.link.ByteUtils;
import com.qws.link.base.header.GBHeader;
import com.qws.link.base.pakcet.GBPacket;
import com.qws.link.codec.CheckCode;
import com.qws.link.mqtt.build.MqMessageBuilder;
import com.qws.link.mqtt.client.MqClient;
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


    @Autowired
    MqClient mqClient;

    private Logger logger = LoggerFactory.getLogger(MqttClientHolder.class);


    /**
     * 订阅
     */
    public Boolean subsribe(String topic, Integer qos) {
        if (!mqClient.getMqttClient().isConnected()) {
            logger.error(" 订阅mqtt服务端失败,原因是当前与mqtt的链接未建立!");
            return false;
        }
        try {
            mqClient.getMqttClient().subscribe(topic, qos);
            return true;
        } catch (MqttException e) {
            logger.error("订阅mqtt服务端失败", e);
            return false;
        }
    }

    /**
     * 默认的话 调用的是国标未加密的包,由rest调用
     */
    public Boolean publish(String topic, GBPacket packet, int command, int answer, String vin, int qos) {
        return gbPublish(topic, packet, "##", command, answer, vin, 1, qos);
    }

    /**
     * 发布,我打算自己组装message,作为最基类的发送消息,由rest调用
     */
    public Boolean gbPublish(String topic, GBPacket packet, String begin, int command, int answer, String vin, Integer
            encryptType, int qos) {
        // 这里来组装 header 和packet 最后进行调用
        GBHeader gbHeader = new GBHeader(begin, command, answer, vin, encryptType, 1);
        //不过由于我是发送者...苦逼的发送者
        return gbSend(topic, gbHeader, packet, qos);
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
//            MqttMessage mqttMessage = MqMessageBuilder.getInstance().buildMessage(new GBMessage(gbHeader, gbPacket));
            byte[] headerBytes = ByteUtils.hexStringToBytes("23230201464d542d4345393837363534333231323301014c");
            byte[] body = ByteUtils.hexStringToBytes("120b0f0b2b260102030100000001c62e0d9527103f021011cc005302010103454e214e206900e627100500070932f402322e940601200e8d013d0e7401023601013507000000000000000005000b00000009000c0009000c000500550016003b0801010d952710005d00015d0e7f0e7f0e800e810e760e820e810e810e7a0e840e7e0e840e840e800e820e810e840e840e810e840e840e7f0e800e800e850e880e870e810e820e810e880e8d0e850e820e850e840e860e890e830e7f0e850e830e830e850e830e7c0e7a0e7c0e870e850e850e820e7b0e7c0e840e7d0e7f0e7d0e830e7a0e740e7e0e860e810e7e0e880e820e880e870e820e840e860e7f0e880e8b0e840e7f0e850e810e840e840e840e820e830e810e7c0e840e810e810e840e840e840e82090101001b353636353535363636363636363636363636363635363636363636b10003036400");
            byte b = CheckCode.mathParity(headerBytes, body);
            byte[] bytes = ByteUtils.addAll(headerBytes, body,b);
            MqttMessage mqttMessage = new MqttMessage(bytes);
            mqttMessage.setQos(qos);
            mqClient.getMqttClient().publish(topic, mqttMessage);
            //发送给mqtt服务器
            return true;
        } catch (Exception e) {
            logger.error("订阅mqtt服务端失败", e);
            return false;
        }
    }

    public static void main(String[] args) {
        byte[] headerBytes = ByteUtils.hexStringToBytes("23230201464d542d434539383736353433323132330101a2");
        byte[] body = ByteUtils.hexStringToBytes("120b0f0b2b260102030100000001c62e0d9527103f021011cc005302010103454e214e206900e627100500070932f402322e940601200e8d013d0e7401023601013507000000000000000005000b00000009000c0009000c000500550016003b0801010d952710005d00015d0e7f0e7f0e800e810e760e820e810e810e7a0e840e7e0e840e840e800e820e810e840e840e810e840e840e7f0e800e800e850e880e870e810e820e810e880e8d0e850e820e850e840e860e890e830e7f0e850e830e830e850e830e7c0e7a0e7c0e870e850e850e820e7b0e7c0e840e7d0e7f0e7d0e830e7a0e740e7e0e860e810e7e0e880e820e880e870e820e840e860e7f0e880e8b0e840e7f0e850e810e840e840e840e820e830e810e7c0e840e810e810e840e840e840e82090101001b353636353535363636363636363636363636363635363636363636b10003039800");
        byte b = CheckCode.mathParity(headerBytes, body);
    }
}
