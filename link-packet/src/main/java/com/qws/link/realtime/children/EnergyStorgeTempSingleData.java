package com.qws.link.realtime.children;

/**
 * @author zhangsi
 * @date created in 2018/4/27 14:08
 */
public class EnergyStorgeTempSingleData {

    /** 可充电储能子系统号 BYTE 有效值范围：1～250。*/
    private Integer sysCode;
    /** 可充电储能温度探针个数 WORD N个温度探针，有效值范围：1～65531，“0xFF,0xFE”表示异常，“0xFF,0xFF”表示无效。*/
    private Integer probeNum;
    /** 各温度探针监测到的温度值 BYTE[N] 有效值范围：0～250 （数值偏移量40℃，表示-40℃～+210℃），最小计量单元：1℃，“0xFE”表示异常，“0xFF”表示无效。*/
    private Integer[] sysTemps;

    public Integer getSysCode() {
        return sysCode;
    }

    public void setSysCode(Integer sysCode) {
        this.sysCode = sysCode;
    }

    public Integer getProbeNum() {
        return probeNum;
    }

    public void setProbeNum(Integer probeNum) {
        this.probeNum = probeNum;
    }

    public Integer[] getSysTemps() {
        return sysTemps;
    }

    public void setSysTemps(Integer[] sysTemps) {
        this.sysTemps = sysTemps;
    }
}
