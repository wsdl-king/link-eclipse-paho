package com.qws.link.realtime.children;

/**
 * 每个驱动电机数据
 */
public class DriveMotorSinglePacket {

    /** 驱动电机序号 BYTE 驱动电机顺序号，有效值范围1～253*/
    private Integer driveMotorSeq;
    /** 驱动电机状态 BYTE 0x01：耗电；0x02：发电；0x03：关闭状态；0x04：准备状态“0xFE”表示异常，“0xFF”表示无效。*/
    private Integer driveMotorStatus;
    /** 驱动电机控制器温度 BYTE 有效值范围：0～250 （数值偏移量40℃，表示-40℃～+210℃），最小计量单元：1℃，“0xFE”表示异常，“0xFF”表示无效。*/
    private Integer controllerTemp;
    /** 驱动电机转速 WORD 有效值范围：0～65531（数值偏移量20000表示-20000 r/min～45531r/min），最小计量单元：1r/min，“0xFF,0xFE”表示异常，“0xFF,0xFF”表示无效*/
    private Integer revs;
    /** 驱动电机转矩 WORD 有效值范围：0～65531（数值偏移量20000表示-2000N*m～4553.1N*m），最小计量单元：0.1N*m，“0xFF,0xFE”表示异常，“0xFF,0xFF”表示无效。*/
    private Double torque;
    /** 驱动电机温度 BYTE 有效值范围：0～250 （数值偏移量40℃，表示-40℃～+210℃），最小计量单元：1℃，“0xFE”表示异常，“0xFF”表示无效。*/
    private Integer driveMotorTemp;
    /** 电机控制器输入电压 WORD 有效值范围：0～60000（表示0V～6000V），最小计量单元：0.1V，“0xFF,0xFE”表示异常，“0xFF,0xFF”表示无效。*/
    private Double controllerVoltage;
    /** 电机控制器直流母线电流 WORD 有效值范围： 0～20000（数值偏移量1000A，表示-1000A～+1000A），最小计量单元：0.1A，“0xFF,0xFE”表示异常，“0xFF,0xFF”表示无效。*/
    private Double controllerCurrent;

    public DriveMotorSinglePacket() {}

    public DriveMotorSinglePacket(Integer driveMotorSeq, Integer driveMotorStatus, Integer controllerTemp, Integer revs, Double torque, Integer driveMotorTemp, Double controllerVoltage, Double controllerCurrent) {
        this.driveMotorSeq = driveMotorSeq;
        this.driveMotorStatus = driveMotorStatus;
        this.controllerTemp = controllerTemp;
        this.revs = revs;
        this.torque = torque;
        this.driveMotorTemp = driveMotorTemp;
        this.controllerVoltage = controllerVoltage;
        this.controllerCurrent = controllerCurrent;
    }

    public Integer getDriveMotorSeq() {
        return driveMotorSeq;
    }

    public void setDriveMotorSeq(Integer driveMotorSeq) {
        this.driveMotorSeq = driveMotorSeq;
    }

    public Integer getDriveMotorStatus() {
        return driveMotorStatus;
    }

    public void setDriveMotorStatus(Integer driveMotorStatus) {
        this.driveMotorStatus = driveMotorStatus;
    }

    public Integer getControllerTemp() {
        return controllerTemp;
    }

    public void setControllerTemp(Integer controllerTemp) {
        this.controllerTemp = controllerTemp;
    }

    public Integer getRevs() {
        return revs;
    }

    public void setRevs(Integer revs) {
        this.revs = revs;
    }

    public Double getTorque() {
        return torque;
    }

    public void setTorque(Double torque) {
        this.torque = torque;
    }

    public Integer getDriveMotorTemp() {
        return driveMotorTemp;
    }

    public void setDriveMotorTemp(Integer driveMotorTemp) {
        this.driveMotorTemp = driveMotorTemp;
    }

    public Double getControllerVoltage() {
        return controllerVoltage;
    }

    public void setControllerVoltage(Double controllerVoltage) {
        this.controllerVoltage = controllerVoltage;
    }

    public Double getControllerCurrent() {
        return controllerCurrent;
    }

    public void setControllerCurrent(Double controllerCurrent) {
        this.controllerCurrent = controllerCurrent;
    }
}
