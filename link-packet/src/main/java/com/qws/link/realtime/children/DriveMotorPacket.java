package com.qws.link.realtime.children;

/**
 * 0x02
 * 实时信息上报-驱动电机数据(停车充电过程不传输此数据)
 */
public class DriveMotorPacket {

    // 驱动电机个数 长度 1 有效值1～253
    private Integer driveMotorNum;
    // 驱动电机总成信息列表
    private DriveMotorSinglePacket[] driveMotorSinglePackets;

    public Integer getDriveMotorNum() {
        return driveMotorNum;
    }

    public void setDriveMotorNum(Integer driveMotorNum) {
        this.driveMotorNum = driveMotorNum;
    }

    public DriveMotorSinglePacket[] getDriveMotorSinglePackets() {
        return driveMotorSinglePackets;
    }
    public void setDriveMotorSinglePackets(DriveMotorSinglePacket[] driveMotorSinglePackets) {
        this.driveMotorSinglePackets = driveMotorSinglePackets;
    }
}
