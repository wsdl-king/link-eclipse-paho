package com.qws.link.realtime;


import com.qws.link.ByteUtils;
import com.qws.link.base.ByteArrayBuf;
import com.qws.link.base.pakcet.GBPacket;
import java.io.Serializable;

import static com.qws.link.ByteUtils.toDouble;
import static com.qws.link.ByteUtils.toInt;

/**
 * 车辆信息数据包--国标协议
 */
public class CarInfoPacket implements GBPacket, Serializable {

    private static final long serialVersionUID = 3020949311959219308L;
    /**
     * 车辆状态 0x01：车辆启动状态；0x02：熄火；0x03：其他状态；“0xFE”表示异常，“0xFF”表示无效。
     */
    private Integer carStatus;
    /**
     * 充电状态 0x01：停车充电；0x02：行驶充电；0x03：未充电状态；0x04：充电完成；“0xFE”表示异常，“0xFF”表示无效。
     */
    private Integer chargeStatus;
    /**
     * 运行模式 0x01: 纯电；0x02：混动；0x03：燃油；0xFE表示异常；0xFF表示无效
     */
    private Integer runModel;
    /**
     * 车速 “0xFF,0xFE”表示异常，“0xFF,0xFF”表示无效。 单元：km/h 范围：0至220
     */
    private Double speed;
    /**
     * 累计里程 “0xFF, 0xFF, 0xFF,0xFE”表示异常，“0xFF,0xFF,0xFF,0xFF”表示无效 单元：km 范围：0至999999.9
     */
    private Double mileage;
    /**
     * 总电压 “0xFF,0xFE”表示异常，“0xFF,0xFF”表示无效。 单元：V 范围：0至1000
     */
    private Double totalVoltage;
    /**
     * 总电流 “0xFF,0xFE”表示异常，“0xFF,0xFF”表示无效。 单元：A 范围：-1000至1000
     */
    private Double totalCurrent;
    /**
     * soc “0xFE”表示异常，“0xFF”表示无效 范围：0至100
     */
    private Integer soc;
    /**
     * dc状态 0x01：工作；0x02：断开，“0xFE”表示异常，“0xFF”表示无效
     */
    private Integer dcStatus;
    /**
     * 档位 BYTE 0空挡, 1~6:1~6挡, 13:倒挡, 14:自动D挡, 15:停车P挡
     */
    private Integer gear;
    /**
     * 驱动力 1 有驱动力 0 无驱动力
     */
    private Integer drivingForce;
    /**
     * 制动力 1 有制动力 0 无制动力
     */
    private Integer brakingForce;
    /**
     * 绝缘电阻 WORD
     */
    private Integer insulateResist;
    /**
     * 加速踏板行程
     */
    private Integer accPedal;
    /**
     * 制动踏板行程
     */
    private Integer brakePedal;


    @Override
    public void build(ByteArrayBuf byteBuf) {
        // 车辆状态
        this.carStatus = toInt(byteBuf.readByte());
        // 充电状态
        this.chargeStatus = toInt(byteBuf.readByte());
        // 工作模式
        this.runModel = toInt(byteBuf.readByte());
        // 车速
        this.speed = toDouble(byteBuf.readBytes(2), 10, 0);
        // 累计里程
        this.mileage = toDouble(byteBuf.readBytes(4), 10, 0);
        // 总电压
        this.totalVoltage = toDouble(byteBuf.readBytes(2), 10, 0);
        // 总电流
        this.totalCurrent = toDouble(byteBuf.readBytes(2), 10, 1000);
        // SOC
        this.soc = toInt(byteBuf.readByte());
        // DC-DC状态
        this.dcStatus = toInt(byteBuf.readByte());

        byte gearByte = byteBuf.readByte();
        if (ByteUtils.isCorrectData(gearByte)) {
            byte[] gearBytes = ByteUtils.byte2BitArray(gearByte);
            // 挡位
            this.gear = (gearBytes[0] + (gearBytes[1] << 1) + (gearBytes[2] << 2) + (gearBytes[3] << 3));
            // 制动力
            this.brakingForce = gearBytes[4] & 0xFF;
            // 驱动力
            this.drivingForce = gearBytes[5] & 0xFF;
        }
        // 绝缘电阻
        this.insulateResist = ByteUtils.toIntWithValid(byteBuf.readBytes(2), 2);
        // 加速踏板行程
        this.accPedal = ByteUtils.toIntWithValid(byteBuf.readByte());
        // 制动踏板行程
        this.brakePedal = ByteUtils.toIntWithValid(byteBuf.readByte());
    }

    @Override
    public byte[] unbuild() {

        // 车辆状态
        byte carStatusBytes = ByteUtils.integerToByteAutoFF(this.carStatus);
        // 充电状态
        byte chargeStatusBytes = ByteUtils.integerToByteAutoFF(this.chargeStatus);
        // 工作模式
        byte runModelBytes = ByteUtils.integerToByteAutoFF(this.runModel);
        // 车速
        byte[] speedBytes = ByteUtils.doubleToByteArrayFF(this.speed, 10, 0, 2);
        // 累计里程
        byte[] sumKmBytes = ByteUtils.doubleToByteArrayFF(this.mileage, 10, 0);
        // 总电压
        byte[] totalVoltageBytes = ByteUtils.doubleToByteArrayFF(this.totalVoltage, 10, 0, 2);
        // 总电流
        byte[] totalCurrentBytes = ByteUtils.doubleToByteArrayFF(this.totalCurrent, 10, 1000, 2);
        // SOC
        byte socBytes = ByteUtils.integerToByteAutoFF(this.soc);
        // DC-DC状态
        byte dcStatusBytes = ByteUtils.integerToByteAutoFF(this.dcStatus);
        // 档位
        byte gearByte = ByteUtils.integerToByteAutoZero(this.gear);
        // 驱动力
        byte drivingForceByte = (byte) (ByteUtils.integerToByteAutoZero(this.drivingForce) << 5);
        // 制动力
        byte brakingForceByte = (byte) (ByteUtils.integerToByteAutoZero(this.brakingForce) << 4);
        // 档位bytes
        byte gearBytes = (byte) (gearByte + drivingForceByte + brakingForceByte);
        // 绝缘电阻
        byte[] insulateResistBytes = ByteUtils.integerToByteArrayFF(this.insulateResist, 2);
        //加速踏板行程
        byte accPedalBytes = ByteUtils.integerToByteAutoFF(this.accPedal);
        //制动踏板行程
        byte brakePedalBytes = ByteUtils.integerToByteAutoFF(this.brakePedal);

        return ByteUtils.addAll(carStatusBytes, chargeStatusBytes, runModelBytes, speedBytes, sumKmBytes, totalVoltageBytes, totalCurrentBytes, socBytes, dcStatusBytes, gearBytes, insulateResistBytes, accPedalBytes, brakePedalBytes);

    }

    @Override
    public Integer length() {
        return null;
    }

    public Integer getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(Integer carStatus) {
        this.carStatus = carStatus;
    }

    public Integer getChargeStatus() {
        return chargeStatus;
    }

    public void setChargeStatus(Integer chargeStatus) {
        this.chargeStatus = chargeStatus;
    }

    public Integer getRunModel() {
        return runModel;
    }

    public void setRunModel(Integer runModel) {
        this.runModel = runModel;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Double getMileage() {
        return mileage;
    }

    public void setMileage(Double mileage) {
        this.mileage = mileage;
    }

    public Double getTotalVoltage() {
        return totalVoltage;
    }

    public void setTotalVoltage(Double totalVoltage) {
        this.totalVoltage = totalVoltage;
    }

    public Double getTotalCurrent() {
        return totalCurrent;
    }

    public void setTotalCurrent(Double totalCurrent) {
        this.totalCurrent = totalCurrent;
    }

    public Integer getSoc() {
        return soc;
    }

    public void setSoc(Integer soc) {
        this.soc = soc;
    }

    public Integer getDcStatus() {
        return dcStatus;
    }

    public void setDcStatus(Integer dcStatus) {
        this.dcStatus = dcStatus;
    }

    public Integer getGear() {
        return gear;
    }

    public void setGear(Integer gear) {
        this.gear = gear;
    }

    public Integer getDrivingForce() {
        return drivingForce;
    }

    public void setDrivingForce(Integer drivingForce) {
        this.drivingForce = drivingForce;
    }

    public Integer getBrakingForce() {
        return brakingForce;
    }

    public void setBrakingForce(Integer brakingForce) {
        this.brakingForce = brakingForce;
    }

    public Integer getInsulateResist() {
        return insulateResist;
    }

    public void setInsulateResist(Integer insulateResist) {
        this.insulateResist = insulateResist;
    }

    public Integer getAccPedal() {
        return accPedal;
    }

    public void setAccPedal(Integer accPedal) {
        this.accPedal = accPedal;
    }

    public Integer getBrakePedal() {
        return brakePedal;
    }

    public void setBrakePedal(Integer brakePedal) {
        this.brakePedal = brakePedal;
    }

}
