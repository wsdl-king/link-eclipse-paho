package com.qws.link.realtime.children;

import com.alibaba.fastjson.JSON;
import com.qws.link.ByteUtils;
import com.qws.link.base.ByteArrayBuf;
import com.qws.link.base.pakcet.GBPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.Serializable;

/**
 * 0x02
 * 实时信息上报-驱动电机数据(停车充电过程不传输此数据)
 */
public class DriveMotorPacket implements GBPacket, Serializable {

    private static final Logger logger = LoggerFactory.getLogger(DriveMotorPacket.class);
    private static final long serialVersionUID = 1595504994344570169L;

    // 驱动电机个数 长度 1 有效值1～253
    private Integer driveMotorNum;
    // 驱动电机总成信息列表
    private DriveMotorSingleData[] driveMotorSingleDatas;

    public Integer getDriveMotorNum() {
        return driveMotorNum;
    }

    public void setDriveMotorNum(Integer driveMotorNum) {
        this.driveMotorNum = driveMotorNum;
    }

    public DriveMotorSingleData[] getDriveMotorSingleDatas() {
        return driveMotorSingleDatas;
    }

    public void setDriveMotorSingleDatas(DriveMotorSingleData[] driveMotorSingleDatas) {
        this.driveMotorSingleDatas = driveMotorSingleDatas;
    }

    @Override
    public void build(ByteArrayBuf byteBuf) throws Exception {
        int beginIndex = byteBuf.readerIndex();

        // 驱动电机个数
        this.driveMotorNum = ByteUtils.toInt(byteBuf.readByte());
        // 驱动电机总成信息列表
        DriveMotorSingleData[] singleDatasArray = new DriveMotorSingleData[driveMotorNum];

        for (int i = 0; i < driveMotorNum; i++) {

            DriveMotorSingleData singleData = new DriveMotorSingleData(
                    ByteUtils.toIntWithValid(byteBuf.readByte()),  // driveMotorSeq
                    ByteUtils.toIntWithValid(byteBuf.readByte()),  // driveMotorStatus
                    ByteUtils.toIntWithValid(byteBuf.readByte(), 40),  // controllerTemp
                    ByteUtils.toIntWithValid(byteBuf.readBytes(2), 2, 20000),   // revs
                    ByteUtils.toDoubleWithValid(byteBuf.readBytes(2), 10, 2000),    // torque (先减再除)
                    ByteUtils.toIntWithValid(byteBuf.readByte(), 40),  // driveMotorTemp
                    ByteUtils.toDoubleWithValid(byteBuf.readBytes(2), 10, 0), // controllerVoltage
                    ByteUtils.toDoubleWithValid(byteBuf.readBytes(2), 10, 1000)  // controllerCurrent
            );

            singleDatasArray[i] = singleData;
        }

        this.driveMotorSingleDatas = singleDatasArray;

        logger.info("[RealtimeDataPacket.DriveMotorDataPacket] bytes:{}, data:{}"
                , ByteUtils.asHex(byteBuf.getBytes(beginIndex, byteBuf.readerIndex() - beginIndex))
                , JSON.toJSONString(this));
    }
    @Override
    public byte[] unbuild() {
        // 驱动电机个数
        byte driveMotorNumByte = ByteUtils.integerToByteAutoZero(this.getDriveMotorNum());

        // 驱动电机总成信息列表
        byte[] dataBytes = new byte[0];
        DriveMotorSingleData[] dataArray = this.getDriveMotorSingleDatas();
        for (int i = 0; i < driveMotorNumByte; i++) {
            // 驱动电机序号
            byte driveMotorSeq = ByteUtils.integerToByteAutoFF(dataArray[i].getDriveMotorSeq());
            // 驱动电机状态
            byte driveMotorStatus = ByteUtils.integerToByteAutoFF(dataArray[i].getDriveMotorStatus());
            // 驱动电机控制器温度
            byte controllerTemp = ByteUtils.integerToByteAutoFF(dataArray[i].getControllerTemp(),40);
            // 驱动电机转速
            byte[] revs = ByteUtils.integerToByteArrayFF(dataArray[i].getRevs(), 20000, 2);
            // 驱动电机转矩
            double torqueNum = dataArray[i].getTorque() == null ? 0 : dataArray[i].getTorque();
            byte[] torque = ByteUtils.integerToByteArrayFF((int)(torqueNum * 10) + 20000, 2);
            // 驱动电机温度
            byte driveMotorTemp = ByteUtils.integerToByteAutoFF(dataArray[i].getDriveMotorTemp(), 40);
            // 电机控制器输入电压
            byte[] controllerVoltage = ByteUtils.doubleToByteArrayFF(dataArray[i].getControllerVoltage(), 10, 0, 2);
            // 电机控制器直流母线电流
            byte[] controllerCurrent = ByteUtils.doubleToByteArrayFF(dataArray[i].getControllerCurrent(), 10, 1000, 2);
            dataBytes = ByteUtils.addAll(dataBytes, driveMotorSeq, driveMotorStatus, controllerTemp, revs, torque, driveMotorTemp
                    , controllerVoltage, controllerCurrent);
        }

        return ByteUtils.addAll(driveMotorNumByte, dataBytes);
    }

    @Override
    public Integer length() {
        return null;
    }
}
