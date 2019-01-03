package com.qws.link.mqtt.callback;

import com.qws.link.ByteUtils;
import com.qws.link.handler.holder.MessageHolder;
import com.qws.link.handler.manager.LinkDispatchManager;
import com.qws.link.handler.server.PacketServerHandler;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author qiwenshuai
 * @note
 * @since 18-12-13 16:56 by jdk 1.8
 */
@Component
public class LinkReceiveCallBack implements MqttCallbackExtended {

    private static final Logger logger = LoggerFactory.getLogger(LinkReceiveCallBack.class);


    @Autowired
    MessageHolder messageHolder;

    @Override
    public void connectComplete(boolean reconnect, String serverURI) {
        logger.info(" 连接完成 : connectComplete,开始订阅mqtt");
        messageHolder.doSubscribe();
        logger.info("订阅mqtt服务端topic完成 : connectComplete,开始订阅mqtt");
    }

    @Override
    public void connectionLost(Throwable cause) {
        logger.info("connectionLost");
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) {
        //收到报文的时间.
        long time = System.currentTimeMillis();
        logger.info(" has received message ,time is :{}", time);
        if (message.getPayload() == null || message.getPayload().length == 0) return;
        //丢给一个消息 持有者对象处理,这个消息处理者对象会额外包装一层加入到线程池.
        messageHolder.messageArrived(time, topic, message);
        String s = ByteUtils.asHex(message.getPayload());
        logger.info("topic: {}", s);
        logger.info("messageArrived");

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        logger.info("message is deliveryComplete");
        //取消订阅topic

    }
}
