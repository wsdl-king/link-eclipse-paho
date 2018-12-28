package com.qws.link.realtime.children;

/**
 * 0x07
 * 实时信息上报-报警数据
 */
public class WarningPacket {

    /** 最高报警级别 */
    private Integer maxWarnLevel;
    /** 温度差异报警 */
    private Integer tempDifferenceWarn;
    /** 电池高温报警 */
    private Integer cellHighTempWarn;
    /** 车载储能装置过压报警 */
    private Integer chargeHighVoltageWarn;
    /** 车载储能装置欠压报警 */
    private Integer chargeLowVoltageWarn;
    /** SOC低报警 */
    private Integer lowSocWarn;
    /** 单体电池过压报警 */
    private Integer cellHightVoltageWarn;
    /** 单体电池低压报警 */
    private Integer cellLowVoltageWarn;
    /** SOC过高报警 */
    private Integer highSocWarn;
    /** SOC跳变报警 */
    private Integer socJumpWarn;
    /** 可充电储能系统不匹配报警 */
    private Integer chargeMismatchWarn;
    /** 电池单体一致性查报警 */
    private Integer cellInconsistWarn;
    /** 绝缘报警 */
    private Integer insulationWarn;
    /** DC-DC温度报警 */
    private Integer dcTempWarn;
    /** 制动系统报警 */
    private Integer brakingWarn;
    /** DC-DC状态报警 */
    private Integer dcStatusWarn;
    /** 驱动电机控制器温度报警 */
    private Integer driveMotorControllerTempWarn;
    /** 高压互锁状态报警 */
    private Integer highVoltageLockWarn;
    /** 驱动电机温度报警 */
    private Integer driveMotorTempWarn;
    /** 车载储能装置类型过充报警 */
    private Integer chargeTypeHighWarn;
    /** 可充电储能装置故障总数 */
    private Integer chargeFaultSum;
    /** 可充电储能装置故障代码列表 */
    private WarningFaultCode[] chargeFaultCodes;
    /** 驱动电机故障总数 */
    private Integer driveMotorFaultSum;
    /** 驱动电机故障代码列表 */
    private WarningFaultCode[] driveMotorFaultCodes;
    /** 发动机故障总数 */
    private Integer engineFaultSum;
    /** 发动机故障列表 */
    private WarningFaultCode[] engineFaultList;
    /** 其他故障总数 */
    private Integer otherFaultSum;
    /** 其他故障列表 */
    private WarningFaultCode[] otherFaultList;

    public Integer getMaxWarnLevel() {
        return maxWarnLevel;
    }

    public void setMaxWarnLevel(Integer maxWarnLevel) {
        this.maxWarnLevel = maxWarnLevel;
    }

    public Integer getTempDifferenceWarn() {
        return tempDifferenceWarn;
    }

    public void setTempDifferenceWarn(Integer tempDifferenceWarn) {
        this.tempDifferenceWarn = tempDifferenceWarn;
    }

    public Integer getCellHighTempWarn() {
        return cellHighTempWarn;
    }

    public void setCellHighTempWarn(Integer cellHighTempWarn) {
        this.cellHighTempWarn = cellHighTempWarn;
    }

    public Integer getChargeHighVoltageWarn() {
        return chargeHighVoltageWarn;
    }

    public void setChargeHighVoltageWarn(Integer chargeHighVoltageWarn) {
        this.chargeHighVoltageWarn = chargeHighVoltageWarn;
    }

    public Integer getChargeLowVoltageWarn() {
        return chargeLowVoltageWarn;
    }

    public void setChargeLowVoltageWarn(Integer chargeLowVoltageWarn) {
        this.chargeLowVoltageWarn = chargeLowVoltageWarn;
    }

    public Integer getLowSocWarn() {
        return lowSocWarn;
    }

    public void setLowSocWarn(Integer lowSocWarn) {
        this.lowSocWarn = lowSocWarn;
    }

    public Integer getCellHightVoltageWarn() {
        return cellHightVoltageWarn;
    }

    public void setCellHightVoltageWarn(Integer cellHightVoltageWarn) {
        this.cellHightVoltageWarn = cellHightVoltageWarn;
    }

    public Integer getCellLowVoltageWarn() {
        return cellLowVoltageWarn;
    }

    public void setCellLowVoltageWarn(Integer cellLowVoltageWarn) {
        this.cellLowVoltageWarn = cellLowVoltageWarn;
    }

    public Integer getHighSocWarn() {
        return highSocWarn;
    }

    public void setHighSocWarn(Integer highSocWarn) {
        this.highSocWarn = highSocWarn;
    }

    public Integer getSocJumpWarn() {
        return socJumpWarn;
    }

    public void setSocJumpWarn(Integer socJumpWarn) {
        this.socJumpWarn = socJumpWarn;
    }

    public Integer getChargeMismatchWarn() {
        return chargeMismatchWarn;
    }

    public void setChargeMismatchWarn(Integer chargeMismatchWarn) {
        this.chargeMismatchWarn = chargeMismatchWarn;
    }

    public Integer getCellInconsistWarn() {
        return cellInconsistWarn;
    }

    public void setCellInconsistWarn(Integer cellInconsistWarn) {
        this.cellInconsistWarn = cellInconsistWarn;
    }

    public Integer getInsulationWarn() {
        return insulationWarn;
    }

    public void setInsulationWarn(Integer insulationWarn) {
        this.insulationWarn = insulationWarn;
    }

    public Integer getDcTempWarn() {
        return dcTempWarn;
    }

    public void setDcTempWarn(Integer dcTempWarn) {
        this.dcTempWarn = dcTempWarn;
    }

    public Integer getBrakingWarn() {
        return brakingWarn;
    }

    public void setBrakingWarn(Integer brakingWarn) {
        this.brakingWarn = brakingWarn;
    }

    public Integer getDcStatusWarn() {
        return dcStatusWarn;
    }

    public void setDcStatusWarn(Integer dcStatusWarn) {
        this.dcStatusWarn = dcStatusWarn;
    }

    public Integer getDriveMotorControllerTempWarn() {
        return driveMotorControllerTempWarn;
    }

    public void setDriveMotorControllerTempWarn(Integer driveMotorControllerTempWarn) {
        this.driveMotorControllerTempWarn = driveMotorControllerTempWarn;
    }

    public Integer getHighVoltageLockWarn() {
        return highVoltageLockWarn;
    }

    public void setHighVoltageLockWarn(Integer highVoltageLockWarn) {
        this.highVoltageLockWarn = highVoltageLockWarn;
    }

    public Integer getDriveMotorTempWarn() {
        return driveMotorTempWarn;
    }

    public void setDriveMotorTempWarn(Integer driveMotorTempWarn) {
        this.driveMotorTempWarn = driveMotorTempWarn;
    }

    public Integer getChargeTypeHighWarn() {
        return chargeTypeHighWarn;
    }

    public void setChargeTypeHighWarn(Integer chargeTypeHighWarn) {
        this.chargeTypeHighWarn = chargeTypeHighWarn;
    }

    public Integer getChargeFaultSum() {
        return chargeFaultSum;
    }

    public void setChargeFaultSum(Integer chargeFaultSum) {
        this.chargeFaultSum = chargeFaultSum;
    }

    public Integer getDriveMotorFaultSum() {
        return driveMotorFaultSum;
    }

    public void setDriveMotorFaultSum(Integer driveMotorFaultSum) {
        this.driveMotorFaultSum = driveMotorFaultSum;
    }

    public Integer getEngineFaultSum() {
        return engineFaultSum;
    }

    public void setEngineFaultSum(Integer engineFaultSum) {
        this.engineFaultSum = engineFaultSum;
    }

    public Integer getOtherFaultSum() {
        return otherFaultSum;
    }

    public void setOtherFaultSum(Integer otherFaultSum) {
        this.otherFaultSum = otherFaultSum;
    }

    public WarningFaultCode[] getChargeFaultCodes() {
        return chargeFaultCodes;
    }

    public void setChargeFaultCodes(WarningFaultCode[] chargeFaultCodes) {
        this.chargeFaultCodes = chargeFaultCodes;
    }

    public WarningFaultCode[] getDriveMotorFaultCodes() {
        return driveMotorFaultCodes;
    }

    public void setDriveMotorFaultCodes(WarningFaultCode[] driveMotorFaultCodes) {
        this.driveMotorFaultCodes = driveMotorFaultCodes;
    }

    public WarningFaultCode[] getEngineFaultList() {
        return engineFaultList;
    }

    public void setEngineFaultList(WarningFaultCode[] engineFaultList) {
        this.engineFaultList = engineFaultList;
    }

    public WarningFaultCode[] getOtherFaultList() {
        return otherFaultList;
    }

    public void setOtherFaultList(WarningFaultCode[] otherFaultList) {
        this.otherFaultList = otherFaultList;
    }
}
