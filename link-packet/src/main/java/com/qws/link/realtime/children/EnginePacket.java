package com.qws.link.realtime.children;

import com.alibaba.fastjson.JSON;
import com.qws.link.ByteUtils;
import com.qws.link.base.ByteArrayBuf;
import com.qws.link.base.pakcet.GBPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * 0x04
 * 实时信息上报-发动机数据(停车充电过程无需上传)
 */
public class EnginePacket implements GBPacket, Serializable {

    private static final Logger logger = LoggerFactory.getLogger(EnginePacket.class);

    private static final long serialVersionUID = 221377240933507690L;
    /**
     * 发动机状态 BYTE
     */
    private Integer engineStatus;
    /**
     * 曲轴转速 WORD
     */
    private Integer crankshaftSpeed;
    /**
     * 燃料消耗率 WORD
     */
    private Double fuelConsumeRate;

    public Integer getEngineStatus() {
        return engineStatus;
    }

    public void setEngineStatus(Integer engineStatus) {
        this.engineStatus = engineStatus;
    }

    public Integer getCrankshaftSpeed() {
        return crankshaftSpeed;
    }

    public void setCrankshaftSpeed(Integer crankshaftSpeed) {
        this.crankshaftSpeed = crankshaftSpeed;
    }

    public Double getFuelConsumeRate() {
        return fuelConsumeRate;
    }

    public void setFuelConsumeRate(Double fuelConsumeRate) {
        this.fuelConsumeRate = fuelConsumeRate;
    }

    @Override
    public void build(ByteArrayBuf byteBuf) throws Exception {
        int index = byteBuf.readerIndex();

        this.engineStatus = ByteUtils.toInt(byteBuf.readByte());
        this.crankshaftSpeed = ByteUtils.byteArrayToInt(byteBuf.readBytes(2), 2);
        this.fuelConsumeRate = ByteUtils.toDouble(byteBuf.readBytes(2), 100, 0);

        logger.info("[RealtimeDataPacket.EnginePacket] bytes:{}, data:{}"
                , ByteUtils.asHex(byteBuf.getBytes(index, byteBuf.readerIndex() - index))
                , JSON.toJSONString(this));
    }

    @Override
    public byte[] unbuild() {

        byte engineStatusBytes = ByteUtils.integerToByteAutoFF(this.getEngineStatus());

        byte[] crankshaftSpeedBytes = ByteUtils.integerToByteArrayFF(this.getCrankshaftSpeed(), 2);

        byte[] fuelConsumeRateBytes = ByteUtils.doubleToByteArrayFF(this.getFuelConsumeRate(), 100, 0, 2);

        return ByteUtils.addAll(engineStatusBytes, crankshaftSpeedBytes, fuelConsumeRateBytes);
    }

    @Override
    public Integer length() {
        return null;
    }
}
