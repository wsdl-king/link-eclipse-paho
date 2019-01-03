package com.qws.link.controller;

import com.qws.link.mqtt.holder.MqttClientHolder;
import com.qws.link.realtime.children.CarInfoPacket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author qiwenshuai
 * @note 暴露出来一些接口给服务之间调用
 * @since 18-12-13 17:02 by jdk 1.8
 */

@RestController
public class DemoRestController {


    @Autowired
    MqttClientHolder mqttClientHolder;

    /**
     * 消息发布
     */
    @GetMapping(value = "/mqtt/sendCarInfo")
    public String sendCarInfo() {
        //
        String vin = "qws12345678901234";
        CarInfoPacket carInfoPacket = new CarInfoPacket();
        carInfoPacket.setAccPedal(1);
        carInfoPacket.setBrakePedal(2);
        carInfoPacket.setBrakingForce(3);
        carInfoPacket.setCarStatus(4);
        carInfoPacket.setChargeStatus(5);
        carInfoPacket.setDcStatus(1);
        carInfoPacket.setDrivingForce(2);
        carInfoPacket.setGear(1);
        carInfoPacket.setMileage(12.2);
        carInfoPacket.setInsulateResist(12);
        carInfoPacket.setRunModel(1);
        carInfoPacket.setSoc(3);
        carInfoPacket.setSpeed(4.2);
        carInfoPacket.setTotalCurrent(9.1);
        carInfoPacket.setTotalVoltage(10.1);
        mqttClientHolder.publish("device/info", carInfoPacket, 0x02, 0xfe, vin, 1);
        return "o";
    }

    /**
     * 消息订阅
     */
    @GetMapping(value = "/mqtt/sub")
    public String subscribeMessage() {
        mqttClientHolder.subsribe("device/info",1);
        return "0";
    }
}
