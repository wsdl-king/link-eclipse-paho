package com.qws.link.realtime.children;

/**
 * 0x03
 * 实时信息上报-燃料电池
 */
public class FuelCellPacket {

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
}
