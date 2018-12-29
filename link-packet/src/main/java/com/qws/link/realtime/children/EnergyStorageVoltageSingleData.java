package com.qws.link.realtime.children;

import java.io.Serializable;

/**
 */
public class EnergyStorageVoltageSingleData  implements Serializable {

    private static final long serialVersionUID = -3707487552130701682L;
    /** 可充电储能子系统号 Byte 有效值范围：1～250 */
    private Integer sysCode;

    /** 可充电储能装置电压 WORD 有效值范围：0～10000（表示0V～1000V），最小计量单元：0.1V，“0xFF,0xFE”表示异常，“0xFF,0xFF”表示无效。 */
    private Double voltage;

    /** 可充电储能装置电流 WORD 有效值范围： 0～20000（数值偏移量1000A，表示-1000A～+1000A），最小计量单元：0.1A，“0xFF,0xFE”表示异常，“0xFF,0xFF”表示无效。 */
    private Double current;

    /** 单体电池总数 WORD N个电池单体，有效值范围：1～65531，“0xFF,0xFE”表示异常，“0xFF,0xFF”表示无效。 */
    private Integer singleBatterySum;

    /** 本帧起始电池序号 WORD 当本帧单体个数超过200时，应拆分成多帧数据进行传输，有效值范围：1～65531。 */
    private Integer frameBatteryCode;

    /** 本帧单体电池总数 BYTE 本帧单体总数 m;有效值范围：1～200。 */
    private Integer frameBatterySum;

    /** 单体电池电压 2×m WORD 有效值范围：0～60000（表示0V～60.000V），最小计量单元：0.001V，单体电池电压个数等于本帧单体电池总数m，“0xFF,0xFE”表示异常，“0xFF,0xFF”表示无效。 */
    private Double[] singleBatteryVoltage;

    public Integer getSysCode() {
        return sysCode;
    }

    public void setSysCode(Integer sysCode) {
        this.sysCode = sysCode;
    }

    public Double getVoltage() {
        return voltage;
    }

    public void setVoltage(Double voltage) {
        this.voltage = voltage;
    }

    public Double getCurrent() {
        return current;
    }

    public void setCurrent(Double current) {
        this.current = current;
    }

    public Integer getSingleBatterySum() {
        return singleBatterySum;
    }

    public void setSingleBatterySum(Integer singleBatterySum) {
        this.singleBatterySum = singleBatterySum;
    }

    public Integer getFrameBatteryCode() {
        return frameBatteryCode;
    }

    public void setFrameBatteryCode(Integer frameBatteryCode) {
        this.frameBatteryCode = frameBatteryCode;
    }

    public Integer getFrameBatterySum() {
        return frameBatterySum;
    }

    public void setFrameBatterySum(Integer frameBatterySum) {
        this.frameBatterySum = frameBatterySum;
    }

    public Double[] getSingleBatteryVoltage() {
        return singleBatteryVoltage;
    }

    public void setSingleBatteryVoltage(Double[] singleBatteryVoltage) {
        this.singleBatteryVoltage = singleBatteryVoltage;
    }

}
