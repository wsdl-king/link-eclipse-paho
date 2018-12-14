package cn.qws.link.controller;

import cn.qws.link.mqtt.client.MqClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author qiwenshuai
 * @note
 * @since 18-12-13 17:02 by jdk 1.8
 */

@RestController
public class DemoRestController {


    @Autowired
    MqClient mqClient;

    /**
     * 消息发布
     */
    @GetMapping(value = "/mqtt/send/{data}")
    public String publishMessage(@PathVariable String data) {

        return "Message sent to Broker";
    }

    /**
     * 消息订阅
     */
    @GetMapping(value = "/mqtt/sub")
    public String subscribeMessage() {
        String clientId = mqClient.getMqttClient().getClientId();
        return clientId;
    }
}
