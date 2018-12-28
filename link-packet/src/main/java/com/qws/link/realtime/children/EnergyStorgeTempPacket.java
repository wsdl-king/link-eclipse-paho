package com.qws.link.realtime.children;

/**
 * 0x09
 * 可充电储能装置温度数据
 */
public class EnergyStorgeTempPacket {

    /** 可充电储能子系统个数 BYTE N个可充电储能装置，有效值范围：1～250，“0xFE”表示异常，“0xFF”表示无效。 */
    private Integer sysNum;
    /** 可充电储能子系统温度信息列表 */
    private EnergyStorgeTempSingleData[] energyStorgeTempSingleData;

    public EnergyStorgeTempPacket() {
    }

    public EnergyStorgeTempPacket(Integer sysNum, EnergyStorgeTempSingleData[] energyStorgeTempSingleData) {
        this.sysNum = sysNum;
        this.energyStorgeTempSingleData = energyStorgeTempSingleData;
    }

    public Integer getSysNum() {
        return sysNum;
    }

    public void setSysNum(Integer sysNum) {
        this.sysNum = sysNum;
    }

    public EnergyStorgeTempSingleData[] getEnergyStorgeTempSingleData() {
        return energyStorgeTempSingleData;
    }

    public void setEnergyStorgeTempSingleData(EnergyStorgeTempSingleData[] energyStorgeTempSingleData) {
        this.energyStorgeTempSingleData = energyStorgeTempSingleData;
    }
}
