package com.qws.link.realtime.children;

/**
 * 0x08
 * 可充电储能装置电压数据
 */
public class EnergyStorageVoltagePacket {

    /** 可充电储能子系统个数 BYTE N个可充电储能子系统，有效值范围：1～250，“0xFE”表示异常，“0xFF”表示无效。 */
    private Integer sysNum;

    /** 可充电储能子系统电压信息列表 */
    private EnergyStorageVoltageSingleData[] energyStorageVoltageSingleData;

    public EnergyStorageVoltagePacket() {
    }

    public EnergyStorageVoltagePacket(Integer sysNum, EnergyStorageVoltageSingleData[] energyStorageVoltageSingleData) {
        this.sysNum = sysNum;
        this.energyStorageVoltageSingleData = energyStorageVoltageSingleData;
    }

    public Integer getSysNum() {
        return sysNum;
    }

    public void setSysNum(Integer sysNum) {
        this.sysNum = sysNum;
    }

    public EnergyStorageVoltageSingleData[] getEnergyStorageVoltageSingleData() {
        return energyStorageVoltageSingleData;
    }

    public void setEnergyStorageVoltageSingleData(EnergyStorageVoltageSingleData[] energyStorageVoltageSingleData) {
        this.energyStorageVoltageSingleData = energyStorageVoltageSingleData;
    }
}
