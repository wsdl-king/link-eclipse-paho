package com.qws.link.realtime.children;

/**
 * 0x06
 * 实时信息上报-极值数据
 */
public class ExtremumPacket {

    /** 最高电压电池子系统号 长度 1 有效值范围：1～250，“0xFE”表示异常，“0xFF”表示无效。*/
    private Integer maxVoltageCellSysNum;
    /** 最高电压电池单体代号 长度 1 有效值范围：1～250，“0xFE”表示异常，“0xFF”表示无效。*/
    private Integer maxVoltageCellCode;
    /** 电池单体电压最高值 长度 2 有效值范围：0～15000（表示0V～15V），最小计量单元：0.001V，“0xFF,0xFE”表示异常，“0xFF,0xFF”表示无效。*/
    private Double cellMaxVoltage;
    /** 最低电压电池子系统号 长度 1 有效值范围：1～250，“0xFE”表示异常，“0xFF”表示无效*/
    private Integer minVoltageCellSysNum;
    /** 最低电压电池单体代号 长度 1 有效值范围：1～250，“0xFE”表示异常，“0xFF”表示无效。*/
    private Integer minVoltageCellCode;
    /** 电池单体电压最低值 长度 2 有效值范围：0～15000（表示0V～15V），最小计量单元：0.001V，“0xFF,0xFE”表示异常，“0xFF,0xFF”表示无效。*/
    private Double cellMinVoltage;
    /** 最高温度子系统号码 长度 1 有效值范围：1～250，“0xFE”表示异常，“0xFF”表示无效。*/
    private Integer maxTempSysNum;
    /** 最高温度探针序号 长度 1 有效值范围：1～250，“0xFE”表示异常，“0xFF”表示无效。*/
    private Integer maxTempProbeSeq;
    /** 最高温度值 长度 1 有效值范围：0～250（数值偏移量40℃，表示-40℃～+210℃），最小计量单元：1℃，“0xFE”表示异常，“0xFF”表示无效。*/
    private Integer maxTemp;
    /** 最低温度子系统号码 长度 1 有效值范围：1～250，“0xFE”表示异常，“0xFF”表示无效。*/
    private Integer minTempSysNum;
    /** 最低温度探针序号 长度 1 有效值范围：1～250，“0xFE”表示异常，“0xFF”表示无效。*/
    private Integer minTempProbeSeq;
    /** 最低温度值 长度 1 有效值范围：0～250（数值偏移量40℃，表示-40℃～+210℃），最小计量单元：1℃，“0xFE”表示异常，“0xFF”表示无效。*/
    private Integer minTemp;

    public Integer getMaxVoltageCellSysNum() {
        return maxVoltageCellSysNum;
    }

    public void setMaxVoltageCellSysNum(Integer maxVoltageCellSysNum) {
        this.maxVoltageCellSysNum = maxVoltageCellSysNum;
    }

    public Integer getMaxVoltageCellCode() {
        return maxVoltageCellCode;
    }

    public void setMaxVoltageCellCode(Integer maxVoltageCellCode) {
        this.maxVoltageCellCode = maxVoltageCellCode;
    }

    public Double getCellMaxVoltage() {
        return cellMaxVoltage;
    }

    public void setCellMaxVoltage(Double cellMaxVoltage) {
        this.cellMaxVoltage = cellMaxVoltage;
    }

    public Integer getMinVoltageCellSysNum() {
        return minVoltageCellSysNum;
    }

    public void setMinVoltageCellSysNum(Integer minVoltageCellSysNum) {
        this.minVoltageCellSysNum = minVoltageCellSysNum;
    }

    public Integer getMinVoltageCellCode() {
        return minVoltageCellCode;
    }

    public void setMinVoltageCellCode(Integer minVoltageCellCode) {
        this.minVoltageCellCode = minVoltageCellCode;
    }

    public Double getCellMinVoltage() {
        return cellMinVoltage;
    }

    public void setCellMinVoltage(Double cellMinVoltage) {
        this.cellMinVoltage = cellMinVoltage;
    }

    public Integer getMaxTempSysNum() {
        return maxTempSysNum;
    }

    public void setMaxTempSysNum(Integer maxTempSysNum) {
        this.maxTempSysNum = maxTempSysNum;
    }

    public Integer getMaxTempProbeSeq() {
        return maxTempProbeSeq;
    }

    public void setMaxTempProbeSeq(Integer maxTempProbeSeq) {
        this.maxTempProbeSeq = maxTempProbeSeq;
    }

    public Integer getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(Integer maxTemp) {
        this.maxTemp = maxTemp;
    }

    public Integer getMinTempSysNum() {
        return minTempSysNum;
    }

    public void setMinTempSysNum(Integer minTempSysNum) {
        this.minTempSysNum = minTempSysNum;
    }

    public Integer getMinTempProbeSeq() {
        return minTempProbeSeq;
    }

    public void setMinTempProbeSeq(Integer minTempProbeSeq) {
        this.minTempProbeSeq = minTempProbeSeq;
    }

    public Integer getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(Integer minTemp) {
        this.minTemp = minTemp;
    }
}
