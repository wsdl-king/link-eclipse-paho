package com.qws.link.message.client;

import com.qws.link.message.callback.LinkSendCallBack;
import com.qws.link.message.config.MqConfig;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author qiwenshuai
 * @note
 * @since 18-12-14 10:05 by jdk 1.8
 */

@Component
public class MqClient {

    private final MqConfig mqConfig;

    private MqttConnectOptions options;

    private final LinkSendCallBack linkSendCallBack;

    private MqttClient mqttClient;

    private Logger logger = LoggerFactory.getLogger(MqClient.class);

    @Autowired
    public MqClient(MqConfig mqConfig, LinkSendCallBack linkSendCallBack) {
        this.mqConfig = mqConfig;
        this.linkSendCallBack = linkSendCallBack;
    }

    @PostConstruct
    public void initMqtt() {
        logger.info("开始初始化mqtt 客户端");
        initOptions();
        try {
            mqttClient = new MqttClient(mqConfig.getHost(), mqConfig.getClientId(), new MemoryPersistence());
            mqttClient.setCallback(linkSendCallBack);
            mqttClient.connect(options);
        } catch (MqttException e) {
            logger.error("mqttClient start error", e);
        }

    }

    private void initOptions() {
        options = new MqttConnectOptions();
        if (mqConfig.getUserName() != null && mqConfig.getPassword() != null) {
            options.setUserName(mqConfig.getUserName());
            options.setPassword(mqConfig.getPassword().toCharArray());
        }
        options.setCleanSession(true);
        options.setKeepAliveInterval(20);
        options.setConnectionTimeout(30);
        options.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1_1);
        options.setAutomaticReconnect(true);
    }

    public MqttClient getMqttClient() {
        return mqttClient;
    }

    public void setMqttClient(MqttClient mqttClient) {
        this.mqttClient = mqttClient;
    }
}
