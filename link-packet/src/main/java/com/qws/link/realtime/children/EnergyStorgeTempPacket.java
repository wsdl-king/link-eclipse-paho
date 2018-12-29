package com.qws.link.realtime.children;

import com.alibaba.fastjson.JSON;
import com.qws.link.ByteUtils;
import com.qws.link.base.ByteArrayBuf;
import com.qws.link.base.pakcet.GBPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * 0x09
 * 可充电储能装置温度数据
 */
public class EnergyStorgeTempPacket implements GBPacket, Serializable {

    private static final Logger logger = LoggerFactory.getLogger(EnergyStorgeTempPacket.class);
    private static final long serialVersionUID = 6574568949486116816L;

    /**
     * 可充电储能子系统个数 BYTE N个可充电储能装置，有效值范围：1～250，“0xFE”表示异常，“0xFF”表示无效。
     */
    private Integer sysNum;
    /**
     * 可充电储能子系统温度信息列表
     */
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

    @Override
    public void build(ByteArrayBuf byteBuf) throws Exception {
        int index = byteBuf.readerIndex();

        this.sysNum = ByteUtils.toIntWithValid(byteBuf.readByte());
        EnergyStorgeTempSingleData[] singleDatas = null;
        if (sysNum == null) {
            singleDatas = new EnergyStorgeTempSingleData[0];
        } else {
            singleDatas = new EnergyStorgeTempSingleData[sysNum];
            for (int i = 0; i < sysNum; i++) {
                EnergyStorgeTempSingleData singleData = new EnergyStorgeTempSingleData();
                Integer sysCode = ByteUtils.toIntWithValid(byteBuf.readByte());
                Integer probeNum = ByteUtils.toIntWithValid(byteBuf.readBytes(2), 2);
                Integer[] sysTemps = new Integer[0];
                if (probeNum != null && probeNum != 0) {
                    sysTemps = new Integer[probeNum];
                    for (int j = 0; j < probeNum; j++) {
                        sysTemps[j] = ByteUtils.toIntWithValid(byteBuf.readByte(), 40);
                    }
                }
                singleData.setSysCode(sysCode);
                singleData.setProbeNum(probeNum);
                singleData.setSysTemps(sysTemps);
                singleDatas[i] = singleData;
            }
        }

        this.energyStorgeTempSingleData = singleDatas;

        logger.info("[RealtimeDataPacket.EnergyStorgeTempDataPacket] bytes:{}, data:{}"
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
