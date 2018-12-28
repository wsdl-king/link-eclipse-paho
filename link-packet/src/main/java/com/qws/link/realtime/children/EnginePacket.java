package com.qws.link.realtime.children;

/**
 * 0x04
 * 实时信息上报-发动机数据(停车充电过程无需上传)
 */
public class EnginePacket {

    /** 发动机状态 BYTE */
    private Integer engineStatus;
    /** 曲轴转速 WORD */
    private Integer crankshaftSpeed;
    /** 燃料消耗率 WORD*/
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
}
