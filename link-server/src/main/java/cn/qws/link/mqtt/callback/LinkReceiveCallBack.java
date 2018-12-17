package cn.qws.link.mqtt.callback;

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
        logger.info(message + "");
        logger.info("messageArrived");

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        logger.info("deliveryComplete");

    }
}
