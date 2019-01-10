package com.qws.link.handler.holder;

import com.qws.link.handler.manager.LinkDispatchManager;
import com.qws.link.handler.server.PacketServerHandler;
import com.qws.link.message.base.LinkMessage;
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

    @Autowired
    KafkaTemplate kafkaTemplate;


    public void messageArrived(long time, LinkMessage linkMessage) {
        PacketServerHandler packetServerHandler = new PacketServerHandler(linkMessage, time, kafkaTemplate, "GB");
        LinkDispatchManager.getInstance().addRunnable(packetServerHandler);
    }


}
