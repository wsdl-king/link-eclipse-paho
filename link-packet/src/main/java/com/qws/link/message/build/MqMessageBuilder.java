package com.qws.link.message.build;

import com.qws.link.message.base.LinkMessage;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * @author qiwenshuai
 * @note
 * @since 18-12-14 15:25 by jdk 1.8
 */
public class MqMessageBuilder {


    private MqMessageBuilder() {
    }


    public static MqMessageBuilder getInstance() {
        return Message.instance;
    }

    private static class Message {
        private static final MqMessageBuilder instance = new MqMessageBuilder();
    }


     public  MqttMessage buildMessage(LinkMessage linkMessage){
        return  new MqttMessage(linkMessage.finalUnBuild());

      }

}
