package com.qws.link.controller;

import com.qws.link.mqtt.holder.MqttClientHolder;
import com.qws.link.realtime.CarInfoPacket;
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
        carInfoPacket.setAccelTripValue(1);
        carInfoPacket.setBrakeStatus(2);
        carInfoPacket.setBrakeTripValue(3);
        carInfoPacket.setCarStatus(4);
        carInfoPacket.setChargeStatus(5);
        carInfoPacket.setDcStatus(1);
        carInfoPacket.setDriveStatus(2);
        carInfoPacket.setGears(1);
        carInfoPacket.setMileage(12.2);
        carInfoPacket.setResistance(2);
        carInfoPacket.setRunModel(1);
        carInfoPacket.setSoc(3);
        carInfoPacket.setSpeed(4.2);
        carInfoPacket.setTotalCurrent(9.1);
        carInfoPacket.setTotalVoltage(10.1);
        mqttClientHolder.publish("carinfo", carInfoPacket, 0xf1, 0xfe, vin, 2);
        return "o";
    }

    /**
     * 消息订阅
     */
    @GetMapping(value = "/mqtt/sub")
    public String subscribeMessage() {
        mqttClientHolder.subsribe("carinfo",2);
        return "0";
    }
}
