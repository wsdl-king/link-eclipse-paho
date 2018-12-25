package com.qws.link.realtime;

import com.qws.link.ByteUtils;
import com.qws.link.base.ByteArrayBuf;
import com.qws.link.base.pakcet.GBPacket;
import java.io.Serializable;
/**
 * 车辆登入数据 ---国标
 */
public class RegPacket implements GBPacket, Serializable {
    /**
     * 数据采集时间
     */
    private Long uploadTime;
    /**
     * 登入流水号
     */
    private Integer loginSnArg;
    /**
     * SIM卡ICCID号
     */
    private String iccid;
    /**
     * 可充电储能子系统数
     */
    private Integer chargeEnergySubSysNum;
    /**
     * 可充电储能系统编码长度
     */
    private Integer chargeEnergySysCodeLength;
    /**
     * 可充电储能系统编码
     */
    private String[] chargeEnergySysCode;


    @Override
    public void build(ByteArrayBuf byteBuf) throws Exception {
        uploadTime = ByteUtils.dateBytes2Long(byteBuf.readBytes(6));
        loginSnArg = ByteUtils.byteArrayToInt(byteBuf.readBytes(2), 2);
        iccid = ByteUtils.getStringFromBytes(byteBuf.readBytes(20));
        chargeEnergySubSysNum = ByteUtils.toInt(byteBuf.readByte());
        chargeEnergySysCodeLength = ByteUtils.toInt(byteBuf.readByte());
        chargeEnergySysCode = new String[0];
        if (chargeEnergySubSysNum != null && chargeEnergySubSysNum > 0) {
            chargeEnergySysCode = new String[chargeEnergySubSysNum];
            for (int i = 0; i < chargeEnergySubSysNum; i++) {
                chargeEnergySysCode[i] = ByteUtils.getStringFromBytes(byteBuf.readBytes(chargeEnergySysCodeLength));
            }
        }
    }

    @Override
    public byte[] unbuild() {
        // 上报时间
        byte[] uploadTimeBytes = ByteUtils.longTimeToByteArray(this.uploadTime);
        // 登入流水号
        byte[] loginSnArgBytes = ByteUtils.intToByteArray(this.loginSnArg, 2);
        // ICCID
        byte[] iccidBytes = this.iccid.getBytes();
        // 可充电储能子系统数
        byte chargeEnergySubSysNumBytes = ByteUtils.integerToByteAutoZero(this.chargeEnergySubSysNum);
        // chargeEnergySysCodeLength
        byte chargeEnergySysCodeLengthBytes = ByteUtils.integerToByteAutoFF(this.chargeEnergySysCodeLength);
        // 可充电储能系统编码
        byte[] chargeEnergySysCodeBytes = new byte[0];

        for (int i = 0; i < chargeEnergySubSysNumBytes; i++) {
            ByteUtils.addAll(chargeEnergySysCodeBytes, this.chargeEnergySysCode[i].getBytes());
        }
        return ByteUtils.addAll(uploadTimeBytes, loginSnArgBytes, iccidBytes, chargeEnergySubSysNumBytes, chargeEnergySysCodeLengthBytes, chargeEnergySysCodeBytes);

    }
    @Override
    public Integer length() {
        return null;
    }
}
