package com.qws.link.realtime.children;

import com.alibaba.fastjson.JSON;
import com.qws.link.ByteUtils;
import com.qws.link.base.ByteArrayBuf;
import com.qws.link.base.pakcet.GBPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import static com.qws.link.ByteUtils.byteArrayToInt;
import static com.qws.link.ByteUtils.toDouble;
import static com.qws.link.ByteUtils.toInt;

/**
 * 0x03
 * 实时信息上报-燃料电池
 */
public class FuelCellPacket implements GBPacket, Serializable {
    private static final Logger logger = LoggerFactory.getLogger(FuelCellPacket.class);
    private static final long serialVersionUID = -1243304016059259539L;

    /**
     * 燃料电池电压 长度 2 有效值范围：0～20000（表示0V～2000V），最小计量单元：0.1V
     */
    private Double cellVoltage;

    /**
     * 燃料电池电流 长度 2 有效值范围： 0～20000（表示0A～+2000A），最小计量单元：0.1A
     */
    private Double cellCurrent;

    /**
     * 燃料消耗率 长度 2 有效值范围：0～60000（表示0kg/100km～600kg/100km），最小计量单元：0.01kg/100km
     */
    private Double fuelConsumeRate;

    /**
     * 燃料电池温度探针总数 长度 2 N个燃料电池温度探针，有效值范围：0～65531
     */
    private Integer cellProbeNum;

    /**
     * 探针温度值 长度 1*N 有效值范围：0～240（数值偏移量40℃，表示-40℃～+200℃），最小计量单元：1℃。
     */
    private Integer[] probeTemps;

    /**
     * 氢系统中最高温度 长度 2 有效值范围：0～2400（偏移量40℃，表示-40℃～200℃），最小计量单元：0.1℃
     */
    private Double hydgMaxTemp;

    /**
     * 氢系统中最高温度探针代号 长度 1 有效值范围：1～252
     */
    private Integer maxTempProbe;

    /**
     * 氢气最高浓度 长度 2  有效值范围：0～60000（表示0ppm～50000ppm），最小计量单元：1ppm
     */
    private Integer hydgMaxConsi;

    /**
     * 氢气最高浓度传感器代号 长度 1  有效值范围：1～252
     */
    private Integer maxConsiSensor;

    /**
     * 氢气最高压力 长度 2   有效值范围：0～1000（表示0MPa～100MPa），最小计量单元：0.1MPa.
     */
    private Double hydgMaxPressure;

    /**
     * 氢气最高压力传感器代号 长度 1  有效值范围：1～252
     */
    private Integer maxPressureSensor;

    /**
     * 高压 DC/DC 状态 长度 1  0x01：工作；0x02：断开；“0xFE”表示异常，“0xFF”表示无效。
     */
    private Integer highPressureDCStatus;

    public Double getCellVoltage() {
        return cellVoltage;
    }

    public void setCellVoltage(Double cellVoltage) {
        this.cellVoltage = cellVoltage;
    }

    public Double getCellCurrent() {
        return cellCurrent;
    }

    public void setCellCurrent(Double cellCurrent) {
        this.cellCurrent = cellCurrent;
    }

    public Double getFuelConsumeRate() {
        return fuelConsumeRate;
    }

    public void setFuelConsumeRate(Double fuelConsumeRate) {
        this.fuelConsumeRate = fuelConsumeRate;
    }

    public Integer getCellProbeNum() {
        return cellProbeNum;
    }

    public void setCellProbeNum(Integer cellProbeNum) {
        this.cellProbeNum = cellProbeNum;
    }

    public Integer[] getProbeTemps() {
        return probeTemps;
    }

    public void setProbeTemps(Integer[] probeTemps) {
        this.probeTemps = probeTemps;
    }

    public Double getHydgMaxTemp() {
        return hydgMaxTemp;
    }

    public void setHydgMaxTemp(Double hydgMaxTemp) {
        this.hydgMaxTemp = hydgMaxTemp;
    }

    public Integer getMaxTempProbe() {
        return maxTempProbe;
    }

    public void setMaxTempProbe(Integer maxTempProbe) {
        this.maxTempProbe = maxTempProbe;
    }

    public Integer getHydgMaxConsi() {
        return hydgMaxConsi;
    }

    public void setHydgMaxConsi(Integer hydgMaxConsi) {
        this.hydgMaxConsi = hydgMaxConsi;
    }

    public Integer getMaxConsiSensor() {
        return maxConsiSensor;
    }

    public void setMaxConsiSensor(Integer maxConsiSensor) {
        this.maxConsiSensor = maxConsiSensor;
    }

    public Double getHydgMaxPressure() {
        return hydgMaxPressure;
    }

    public void setHydgMaxPressure(Double hydgMaxPressure) {
        this.hydgMaxPressure = hydgMaxPressure;
    }

    public Integer getMaxPressureSensor() {
        return maxPressureSensor;
    }

    public void setMaxPressureSensor(Integer maxPressureSensor) {
        this.maxPressureSensor = maxPressureSensor;
    }

    public Integer getHighPressureDCStatus() {
        return highPressureDCStatus;
    }

    public void setHighPressureDCStatus(Integer highPressureDCStatus) {
        this.highPressureDCStatus = highPressureDCStatus;
    }

    @Override
    public void build(ByteArrayBuf byteBuf) throws Exception {
        int index = byteBuf.readerIndex();
        // 燃料电池电压
        this.cellVoltage = toDouble(byteBuf.readBytes(2), 10, 0);
        // 燃料电池电流
        this.cellCurrent = toDouble(byteBuf.readBytes(2), 10, 0);
        // 燃料消耗率
        this.fuelConsumeRate = toDouble(byteBuf.readBytes(2), 100, 0);
        // 燃料电池温度探针总数
        this.cellProbeNum = byteArrayToInt(byteBuf.readBytes(2), 2);

        // 探针温度值
        Integer[] probeTempsArray = new Integer[cellProbeNum];
        for (int i = 0; i < cellProbeNum; i++) {
            probeTempsArray[i] = toInt(byteBuf.readByte(), 40);
        }
        this.probeTemps = probeTempsArray;
        // 氢系统最高温度
        this.hydgMaxTemp = toDouble(byteBuf.readBytes(2), 10, 40);
        // 氢系统最高温度探针代号
        this.maxTempProbe = toInt(byteBuf.readByte());
        // 氢气最高浓度
        this.hydgMaxConsi = byteArrayToInt(byteBuf.readBytes(2), 2);
        // 氢气最高浓度传感器代号
        this.maxConsiSensor = toInt(byteBuf.readByte());
        // 氢气最高压力
        this.hydgMaxPressure = toDouble(byteBuf.readBytes(2), 10, 0);
        // 氢气最高压力传感器代号
        this.maxPressureSensor = toInt(byteBuf.readByte());
        // 高压DC/DC状态
        this.highPressureDCStatus = toInt(byteBuf.readByte());
        logger.info("[RealtimeDataPacket.FuelCellDataPacket] bytes:{}, data:{}"
                , ByteUtils.asHex(byteBuf.getBytes(index, byteBuf.readerIndex() - index))
                , JSON.toJSONString(this));
    }

    @Override
    public byte[] unbuild() {
        // 燃料电池电压
        byte[] cellVoltageBytes = ByteUtils.doubleToByteArrayFF(this.getCellVoltage(), 10, 0, 2);
        // 燃料电池电流
        byte[] cellCurrentBytes = ByteUtils.doubleToByteArrayFF(this.getCellCurrent(), 10, 0, 2);
        // 燃料消耗率
        byte[] fuelConsumeRateBytes = ByteUtils.doubleToByteArrayFF(this.getFuelConsumeRate(), 100, 0, 2);
        // 燃料电池温度探针总数
        byte[] cellProbeNumBytes = ByteUtils.integerToByteArrayFF(this.getCellProbeNum(), 2);
        // 探针温度值
        Integer[] probeTempsArray = this.getProbeTemps();
        byte[] probeTempsBytes = new byte[this.getCellProbeNum()];
        for (int i = 0; i < this.getCellProbeNum(); i++) {
            probeTempsBytes[i] = (byte) (probeTempsArray[i] + 40);
        }
        // 氢系统中最高温度
        byte[] hydgMaxTempBytes = ByteUtils.doubleToByteArrayFF(this.getHydgMaxTemp(), 10, 40, 2);
        // 氢系统中最高温度探针代号
        byte maxTempProbeByte = ByteUtils.integerToByteAutoFF(this.getMaxTempProbe());
        // 氢气最高浓度
        byte[] hydgMaxConsiBytes = ByteUtils.integerToByteArrayFF(this.getHydgMaxConsi(), 2);
        // 氢气最高浓度传感器代号
        byte maxConsiSensorByte = ByteUtils.integerToByteAutoFF(this.getMaxConsiSensor());
        // 氢气最高压力
        byte[] hydgMaxPressureBytes = ByteUtils.doubleToByteArrayFF(this.getHydgMaxPressure(), 10, 0, 2);
        // 氢气最高压力传感器代号
        byte maxPressureSensorByte = ByteUtils.integerToByteAutoFF(this.getMaxPressureSensor());
        // 高压 DC/DC 状态
        byte highPressureDCStatusByte = ByteUtils.integerToByteAutoFF(this.getHighPressureDCStatus());

        return ByteUtils.addAll(cellVoltageBytes, cellCurrentBytes, fuelConsumeRateBytes, cellProbeNumBytes, probeTempsBytes, hydgMaxTempBytes
                , maxTempProbeByte, hydgMaxConsiBytes, maxConsiSensorByte, hydgMaxPressureBytes, maxPressureSensorByte, highPressureDCStatusByte);

    }

    @Override
    public Integer length() {
        return null;
    }
}
