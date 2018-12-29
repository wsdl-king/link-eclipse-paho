package com.qws.link.realtime.children;

import com.alibaba.fastjson.JSON;
import com.qws.link.ByteUtils;
import com.qws.link.base.ByteArrayBuf;
import com.qws.link.base.pakcet.GBPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * 0x08
 * 可充电储能装置电压数据
 */
public class EnergyStorageVoltagePacket implements GBPacket, Serializable {
    private static final Logger logger = LoggerFactory.getLogger(EnergyStorageVoltagePacket.class);
    private static final long serialVersionUID = 8501998446694689375L;

    /**
     * 可充电储能子系统个数 BYTE N个可充电储能子系统，有效值范围：1～250，“0xFE”表示异常，“0xFF”表示无效。
     */
    private Integer sysNum;

    /**
     * 可充电储能子系统电压信息列表
     */
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

    @Override
    public void build(ByteArrayBuf byteBuf) throws Exception {
        Integer index = byteBuf.readerIndex();
        this.sysNum = ByteUtils.toIntWithValid(byteBuf.readByte());

        EnergyStorageVoltageSingleData[] singleDatas = null;

        if (sysNum == null) {
            singleDatas = new EnergyStorageVoltageSingleData[0];
        } else {
            singleDatas = new EnergyStorageVoltageSingleData[sysNum];
            for (int i = 0; i < sysNum; i++) {
                EnergyStorageVoltageSingleData singleData = new EnergyStorageVoltageSingleData();
                Integer sysCode = ByteUtils.toIntWithValid(byteBuf.readByte());
                Double voltage = ByteUtils.toDoubleWithValid(byteBuf.readBytes(2), 10, 0);
                Double current = ByteUtils.toDoubleWithValid(byteBuf.readBytes(2), 10, 1000);
                Integer singleBatterySum = ByteUtils.toIntWithValid(byteBuf.readBytes(2), 2);
                Integer frameBatteryCode = ByteUtils.toIntWithValid(byteBuf.readBytes(2), 2);
                Integer frameBatterySum = ByteUtils.toIntWithValid(byteBuf.readByte());
                Double[] singleBatteryVoltage = new Double[0];
                if (frameBatterySum != null) {
                    singleBatteryVoltage = new Double[frameBatterySum];
                    for (int j = 0; j < frameBatterySum; j++) {
                        singleBatteryVoltage[j] = ByteUtils.toDoubleWithValid(byteBuf.readBytes(2), 1000, 0);
                    }
                }

                singleData.setSysCode(sysCode);
                singleData.setVoltage(voltage);
                singleData.setCurrent(current);
                singleData.setSingleBatterySum(singleBatterySum);
                singleData.setFrameBatteryCode(frameBatteryCode);
                singleData.setFrameBatterySum(frameBatterySum);
                singleData.setSingleBatteryVoltage(singleBatteryVoltage);
                singleDatas[i] = singleData;
            }
        }
        this.energyStorageVoltageSingleData = singleDatas;
        logger.info("[RealtimeDataPacket.EnergyStorageVoltageDataPacket] bytes:{}, data:{}"
                , ByteUtils.asHex(byteBuf.getBytes(index, byteBuf.readerIndex() - index))
                , JSON.toJSONString(this));
    }

    @Override
    public byte[] unbuild() {
        return new byte[0];
    }

    @Override
    public Integer length() {
        return null;
    }
}
