package com.qws.link.realtime.children;

import com.alibaba.fastjson.JSON;
import com.qws.link.ByteUtils;
import com.qws.link.base.ByteArrayBuf;
import com.qws.link.base.pakcet.GBPacket;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * 0x07
 * 实时信息上报-报警数据
 */
public class WarningPacket implements GBPacket, Serializable {

    private static final Logger logger = LoggerFactory.getLogger(WarningPacket.class);

    private static final long serialVersionUID = 1575291752538892216L;
    /**
     * 最高报警级别
     */
    private Integer maxWarnLevel;
    /**
     * 温度差异报警
     */
    private Integer tempDifferenceWarn;
    /**
     * 电池高温报警
     */
    private Integer cellHighTempWarn;
    /**
     * 车载储能装置过压报警
     */
    private Integer chargeHighVoltageWarn;
    /**
     * 车载储能装置欠压报警
     */
    private Integer chargeLowVoltageWarn;
    /**
     * SOC低报警
     */
    private Integer lowSocWarn;
    /**
     * 单体电池过压报警
     */
    private Integer cellHightVoltageWarn;
    /**
     * 单体电池低压报警
     */
    private Integer cellLowVoltageWarn;
    /**
     * SOC过高报警
     */
    private Integer highSocWarn;
    /**
     * SOC跳变报警
     */
    private Integer socJumpWarn;
    /**
     * 可充电储能系统不匹配报警
     */
    private Integer chargeMismatchWarn;
    /**
     * 电池单体一致性查报警
     */
    private Integer cellInconsistWarn;
    /**
     * 绝缘报警
     */
    private Integer insulationWarn;
    /**
     * DC-DC温度报警
     */
    private Integer dcTempWarn;
    /**
     * 制动系统报警
     */
    private Integer brakingWarn;
    /**
     * DC-DC状态报警
     */
    private Integer dcStatusWarn;
    /**
     * 驱动电机控制器温度报警
     */
    private Integer driveMotorControllerTempWarn;
    /**
     * 高压互锁状态报警
     */
    private Integer highVoltageLockWarn;
    /**
     * 驱动电机温度报警
     */
    private Integer driveMotorTempWarn;
    /**
     * 车载储能装置类型过充报警
     */
    private Integer chargeTypeHighWarn;
    /**
     * 可充电储能装置故障总数
     */
    private Integer chargeFaultSum;
    /**
     * 可充电储能装置故障代码列表
     */
    private WarningFaultCode[] chargeFaultCodes;
    /**
     * 驱动电机故障总数
     */
    private Integer driveMotorFaultSum;
    /**
     * 驱动电机故障代码列表
     */
    private WarningFaultCode[] driveMotorFaultCodes;
    /**
     * 发动机故障总数
     */
    private Integer engineFaultSum;
    /**
     * 发动机故障列表
     */
    private WarningFaultCode[] engineFaultList;
    /**
     * 其他故障总数
     */
    private Integer otherFaultSum;
    /**
     * 其他故障列表
     */
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

    public WarningFaultCode[] getChargeFaultCodes() {
        return chargeFaultCodes;
    }

    public void setChargeFaultCodes(WarningFaultCode[] chargeFaultCodes) {
        this.chargeFaultCodes = chargeFaultCodes;
    }

    public Integer getDriveMotorFaultSum() {
        return driveMotorFaultSum;
    }

    public void setDriveMotorFaultSum(Integer driveMotorFaultSum) {
        this.driveMotorFaultSum = driveMotorFaultSum;
    }

    public WarningFaultCode[] getDriveMotorFaultCodes() {
        return driveMotorFaultCodes;
    }

    public void setDriveMotorFaultCodes(WarningFaultCode[] driveMotorFaultCodes) {
        this.driveMotorFaultCodes = driveMotorFaultCodes;
    }

    public Integer getEngineFaultSum() {
        return engineFaultSum;
    }

    public void setEngineFaultSum(Integer engineFaultSum) {
        this.engineFaultSum = engineFaultSum;
    }

    public WarningFaultCode[] getEngineFaultList() {
        return engineFaultList;
    }

    public void setEngineFaultList(WarningFaultCode[] engineFaultList) {
        this.engineFaultList = engineFaultList;
    }

    public Integer getOtherFaultSum() {
        return otherFaultSum;
    }

    public void setOtherFaultSum(Integer otherFaultSum) {
        this.otherFaultSum = otherFaultSum;
    }

    public WarningFaultCode[] getOtherFaultList() {
        return otherFaultList;
    }

    public void setOtherFaultList(WarningFaultCode[] otherFaultList) {
        this.otherFaultList = otherFaultList;
    }

    @Override
    public void build(ByteArrayBuf byteBuf) throws Exception {
        int index = byteBuf.readerIndex();
        byteBuf.readBytes(5);

        // 可充电储能装置故障总数
        Integer chargeFaultSum = ByteUtils.toInt(byteBuf.readByte());
        // 可充电储能装置故障代码列表
        WarningFaultCode[] chargeFaultCodes = new WarningFaultCode[0];
        if (chargeFaultSum != null) {
            chargeFaultCodes = new WarningFaultCode[chargeFaultSum];
            for (int i = 0; i < chargeFaultSum; i++) {
                if (ByteUtils.isCorrectData(byteBuf.getBytes(4))) {
                    chargeFaultCodes[i] = new WarningFaultCode(
                            ByteUtils.byteArrayToInt(byteBuf.readBytes(2), 2),
                            ByteUtils.byteArrayToInt(byteBuf.readBytes(2), 2));
                } else {
                    index += 4;
                }
            }
        }


        // 驱动电机故障总数
        Integer driveMotorFaultSum = ByteUtils.toInt(byteBuf.readByte());
        // 驱动电机故障代码列表
        WarningFaultCode[] driveMotorFaultCodes = new WarningFaultCode[0];
        if (driveMotorFaultSum != null) {
            driveMotorFaultCodes = new WarningFaultCode[driveMotorFaultSum];
            for (int i = 0; i < driveMotorFaultSum; i++) {
                if (ByteUtils.isCorrectData(byteBuf.getBytes(4))) {
                    driveMotorFaultCodes[i] = new WarningFaultCode(
                            ByteUtils.byteArrayToInt(byteBuf.readBytes(2), 2),
                            ByteUtils.byteArrayToInt(byteBuf.readBytes(2), 2));
                } else {
                    index += 4;
                }
            }
        }


        // 发动机故障总数
        Integer engineFaultSum = ByteUtils.toInt(byteBuf.readByte());
        // 发动机故障列表
        WarningFaultCode[] engineFaultList = new WarningFaultCode[0];
        if (engineFaultSum != null) {
            engineFaultList = new WarningFaultCode[engineFaultSum];
            for (int i = 0; i < engineFaultSum; i++) {
                if (ByteUtils.isCorrectData(byteBuf.getBytes(4))) {
                    engineFaultList[i] = new WarningFaultCode(
                            ByteUtils.byteArrayToInt(byteBuf.readBytes(2), 2),
                            ByteUtils.byteArrayToInt(byteBuf.readBytes(2), 2));
                } else {
                    index += 4;
                }
            }
        }


        // 其他故障总数
        Integer otherFaultSum = ByteUtils.toInt(byteBuf.readByte());
        // 其他故障列表
        WarningFaultCode[] otherFaultList = new WarningFaultCode[0];
        if (otherFaultSum != null) {
            otherFaultList = new WarningFaultCode[otherFaultSum];
            for (int i = 0; i < otherFaultSum; i++) {
                if (ByteUtils.isCorrectData(byteBuf.getBytes(4))) {
                    otherFaultList[i] = new WarningFaultCode(
                            ByteUtils.byteArrayToInt(byteBuf.readBytes(2), 2),
                            ByteUtils.byteArrayToInt(byteBuf.readBytes(2), 2));
                } else {
                    index += 4;
                }
            }
        }

        this.setChargeFaultSum(chargeFaultSum);
        this.setChargeFaultCodes(chargeFaultCodes);
        this.setDriveMotorFaultSum(driveMotorFaultSum);
        this.setDriveMotorFaultCodes(driveMotorFaultCodes);
        this.setEngineFaultSum(engineFaultSum);
        this.setEngineFaultList(engineFaultList);
        this.setOtherFaultSum(otherFaultSum);
        this.setOtherFaultList(otherFaultList);

        logger.info("[RealtimeDataPacket.thisPacket] bytes:{}, data:{}"
                , ByteUtils.asHex(byteBuf.getBytes(index, byteBuf.readerIndex() - index))
                , JSON.toJSONString(this));
    }

    @Override
    public byte[] unbuild() {
        // 最高报警等级
        byte maxWarnLevelByte = ByteUtils.intToByteArray(this.getMaxWarnLevel(), 1)[0];

        /** 通用报警标志位 */
        // 温度差异报警
        byte tempDifferenceWarnByte = ByteUtils.integerToByte(this.getTempDifferenceWarn());
        // 电池高温报警
        byte cellHighTempWarnByte = ByteUtils.integerToByte(this.getCellHighTempWarn());
        // 车载储能装置过压报警
        byte chargeHighVoltageWarnByte = ByteUtils.integerToByte(this.getChargeHighVoltageWarn());
        // 车载储能装置欠压报警
        byte chargeLowVoltageWarnByte = ByteUtils.integerToByte(this.getCellLowVoltageWarn());
        // SOC低报警
        byte lowSocWarnByte = ByteUtils.integerToByte(this.getLowSocWarn());
        // 单体电池过压报警
        byte cellHightVoltageWarnByte = ByteUtils.integerToByte(this.getCellHightVoltageWarn());
        // 单体电池低压报警
        byte cellLowVoltageWarnByte = ByteUtils.integerToByte(this.getCellLowVoltageWarn());
        // SOC过高报警
        byte highSocWarnByte = ByteUtils.integerToByte(this.getHighSocWarn());

        // warn flag 3
        byte warnFlag3 = (byte)(tempDifferenceWarnByte + (cellHighTempWarnByte << 1) + (chargeHighVoltageWarnByte << 2)
                + (chargeLowVoltageWarnByte << 3) + (lowSocWarnByte << 4) + (cellHightVoltageWarnByte << 5)
                + (cellLowVoltageWarnByte << 6) + (highSocWarnByte << 7));

        // SOC跳变报警
        byte socJumpWarnByte = ByteUtils.integerToByte(this.getSocJumpWarn());
        // 可充电储能系统不匹配报警
        byte chargeMismatchWarnByte = ByteUtils.integerToByte(this.getChargeMismatchWarn());
        // 电池单体一致性查报警
        byte cellInconsistWarnByte = ByteUtils.integerToByte(this.getCellInconsistWarn());
        // 绝缘报警
        byte insulationWarnByte = ByteUtils.integerToByte(this.getInsulationWarn());
        // DC-DC温度报警
        byte dcTempWarnByte = ByteUtils.integerToByte(this.getDcTempWarn());
        // 制动系统报警
        byte brakingWarnByte = ByteUtils.integerToByte(this.getBrakingWarn());
        // DC-DC状态报警
        byte dcStatusWarnByte = ByteUtils.integerToByte(this.getDcStatusWarn());
        // 驱动电机控制器温度报警
        byte driveMotorControllerTempWarnByte = ByteUtils.integerToByte(this.getDriveMotorControllerTempWarn());

        //warn flag 2
        byte warnFlag2 = (byte) (socJumpWarnByte + (chargeMismatchWarnByte << 1) + (cellInconsistWarnByte << 2) + (insulationWarnByte << 3)
                + (dcTempWarnByte << 4) + (brakingWarnByte << 5) + (dcStatusWarnByte << 6) + (driveMotorControllerTempWarnByte << 7));

        // 高压互锁状态报警
        byte highVoltageLockWarnBytes = ByteUtils.integerToByte(this.getHighVoltageLockWarn());
        // 驱动电机温度报警
        byte driveMotorTempWarnBytes = ByteUtils.integerToByte(this.getDriveMotorTempWarn());
        // 车载储能装置类型过充报警
        byte chargeTypeHighWarnBytes = ByteUtils.integerToByte(this.getChargeTypeHighWarn());

        // warn flag 1
        byte warnFlag1 = (byte) (highVoltageLockWarnBytes + (driveMotorTempWarnBytes << 1) + (chargeTypeHighWarnBytes << 2));
        // warn flag0
        byte warnFlag0 = 0;

        // 可充电储能装置故障总数
        byte[] chargeFaultCodesBytes = new byte[0];
        if (this.getChargeFaultSum() == null) {
            chargeFaultCodesBytes = ArrayUtils.add(chargeFaultCodesBytes, (byte)0x00);
        } else {
            byte chargeFaultSumByte = this.getChargeFaultSum().byteValue();
            chargeFaultCodesBytes = ArrayUtils.add(chargeFaultCodesBytes, chargeFaultSumByte);
            WarningFaultCode[] faultCodes = this.getChargeFaultCodes();
            for (int i = 0; i < chargeFaultSumByte; i++) {
                chargeFaultCodesBytes = ByteUtils.addAll(chargeFaultCodesBytes, ByteUtils.intToByteArray2(faultCodes[i].getPartId()), ByteUtils.intToByteArray2(faultCodes[i].getFaultSeq()));
            }
        }

        // 驱动电机故障总数
        byte[] driveMotorFaultCodesBytes = new byte[0];
        if (this.getDriveMotorFaultSum() == null) {
            driveMotorFaultCodesBytes = ArrayUtils.add(driveMotorFaultCodesBytes, (byte)0x00);
        } else {
            byte sum = this.getDriveMotorFaultSum().byteValue();
            driveMotorFaultCodesBytes = ArrayUtils.add(driveMotorFaultCodesBytes, sum);
            WarningFaultCode[] faultCodes = this.getDriveMotorFaultCodes();
            for (int i = 0; i < sum; i++) {
                driveMotorFaultCodesBytes = ByteUtils.addAll(driveMotorFaultCodesBytes, ByteUtils.intToByteArray2(faultCodes[i].getPartId()), ByteUtils.intToByteArray2(faultCodes[i].getFaultSeq()));
            }
        }

        // 发动机故障总数
        byte[] engineFaultCodesBytes = new byte[0];
        if (this.getEngineFaultSum() == null) {
            engineFaultCodesBytes = ArrayUtils.add(engineFaultCodesBytes, (byte)0x00);
        } else {
            byte sum = this.getEngineFaultSum().byteValue();
            engineFaultCodesBytes = ArrayUtils.add(engineFaultCodesBytes, sum);
            WarningFaultCode[] faultCodes = this.getEngineFaultList();
            for (int i = 0; i < sum; i++) {
                engineFaultCodesBytes = ByteUtils.addAll(engineFaultCodesBytes, ByteUtils.intToByteArray2(faultCodes[i].getPartId()), ByteUtils.intToByteArray2(faultCodes[i].getFaultSeq()));
            }
        }

        // 其他故障总数
        byte[] otherFaultCodesBytes = new byte[0];
        if (this.getOtherFaultSum() == null) {
            otherFaultCodesBytes = ArrayUtils.add(otherFaultCodesBytes, (byte)0x00);
        } else {
            byte sum = this.getOtherFaultSum().byteValue();
            otherFaultCodesBytes = ArrayUtils.add(otherFaultCodesBytes, sum);
            WarningFaultCode[] faultCodes = this.getOtherFaultList();
            for (int i = 0; i < sum; i++) {
                otherFaultCodesBytes = ByteUtils.addAll(otherFaultCodesBytes, ByteUtils.intToByteArray2(faultCodes[i].getPartId()), ByteUtils.intToByteArray2(faultCodes[i].getFaultSeq()));
            }
        }

        return ByteUtils.addAll(maxWarnLevelByte, warnFlag0, warnFlag1, warnFlag2, warnFlag3, chargeFaultCodesBytes, driveMotorFaultCodesBytes, engineFaultCodesBytes, otherFaultCodesBytes);

    }

    @Override
    public Integer length() {
        return null;
    }
}
