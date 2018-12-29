package com.qws.link.realtime.children;

import com.alibaba.fastjson.JSON;
import com.qws.link.ByteUtils;
import com.qws.link.base.ByteArrayBuf;
import com.qws.link.base.pakcet.GBPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * 0x06
 * 实时信息上报-极值数据
 */
public class ExtremumPacket implements GBPacket, Serializable {

    private static final long serialVersionUID = -9207473137171030238L;

    private static final Logger logger = LoggerFactory.getLogger(ExtremumPacket.class);
    /**
     * 最高电压电池子系统号 长度 1 有效值范围：1～250，“0xFE”表示异常，“0xFF”表示无效。
     */
    private Integer maxVoltageCellSysNum;
    /**
     * 最高电压电池单体代号 长度 1 有效值范围：1～250，“0xFE”表示异常，“0xFF”表示无效。
     */
    private Integer maxVoltageCellCode;
    /**
     * 电池单体电压最高值 长度 2 有效值范围：0～15000（表示0V～15V），最小计量单元：0.001V，“0xFF,0xFE”表示异常，“0xFF,0xFF”表示无效。
     */
    private Double cellMaxVoltage;
    /**
     * 最低电压电池子系统号 长度 1 有效值范围：1～250，“0xFE”表示异常，“0xFF”表示无效
     */
    private Integer minVoltageCellSysNum;
    /**
     * 最低电压电池单体代号 长度 1 有效值范围：1～250，“0xFE”表示异常，“0xFF”表示无效。
     */
    private Integer minVoltageCellCode;
    /**
     * 电池单体电压最低值 长度 2 有效值范围：0～15000（表示0V～15V），最小计量单元：0.001V，“0xFF,0xFE”表示异常，“0xFF,0xFF”表示无效。
     */
    private Double cellMinVoltage;
    /**
     * 最高温度子系统号码 长度 1 有效值范围：1～250，“0xFE”表示异常，“0xFF”表示无效。
     */
    private Integer maxTempSysNum;
    /**
     * 最高温度探针序号 长度 1 有效值范围：1～250，“0xFE”表示异常，“0xFF”表示无效。
     */
    private Integer maxTempProbeSeq;
    /**
     * 最高温度值 长度 1 有效值范围：0～250（数值偏移量40℃，表示-40℃～+210℃），最小计量单元：1℃，“0xFE”表示异常，“0xFF”表示无效。
     */
    private Integer maxTemp;
    /**
     * 最低温度子系统号码 长度 1 有效值范围：1～250，“0xFE”表示异常，“0xFF”表示无效。
     */
    private Integer minTempSysNum;
    /**
     * 最低温度探针序号 长度 1 有效值范围：1～250，“0xFE”表示异常，“0xFF”表示无效。
     */
    private Integer minTempProbeSeq;
    /**
     * 最低温度值 长度 1 有效值范围：0～250（数值偏移量40℃，表示-40℃～+210℃），最小计量单元：1℃，“0xFE”表示异常，“0xFF”表示无效。
     */
    private Integer minTemp;

    public Integer getMaxVoltageCellSysNum() {
        return maxVoltageCellSysNum;
    }

    public void setMaxVoltageCellSysNum(Integer maxVoltageCellSysNum) {
        this.maxVoltageCellSysNum = maxVoltageCellSysNum;
    }

    public Integer getMaxVoltageCellCode() {
        return maxVoltageCellCode;
    }

    public void setMaxVoltageCellCode(Integer maxVoltageCellCode) {
        this.maxVoltageCellCode = maxVoltageCellCode;
    }

    public Double getCellMaxVoltage() {
        return cellMaxVoltage;
    }

    public void setCellMaxVoltage(Double cellMaxVoltage) {
        this.cellMaxVoltage = cellMaxVoltage;
    }

    public Integer getMinVoltageCellSysNum() {
        return minVoltageCellSysNum;
    }

    public void setMinVoltageCellSysNum(Integer minVoltageCellSysNum) {
        this.minVoltageCellSysNum = minVoltageCellSysNum;
    }

    public Integer getMinVoltageCellCode() {
        return minVoltageCellCode;
    }

    public void setMinVoltageCellCode(Integer minVoltageCellCode) {
        this.minVoltageCellCode = minVoltageCellCode;
    }

    public Double getCellMinVoltage() {
        return cellMinVoltage;
    }

    public void setCellMinVoltage(Double cellMinVoltage) {
        this.cellMinVoltage = cellMinVoltage;
    }

    public Integer getMaxTempSysNum() {
        return maxTempSysNum;
    }

    public void setMaxTempSysNum(Integer maxTempSysNum) {
        this.maxTempSysNum = maxTempSysNum;
    }

    public Integer getMaxTempProbeSeq() {
        return maxTempProbeSeq;
    }

    public void setMaxTempProbeSeq(Integer maxTempProbeSeq) {
        this.maxTempProbeSeq = maxTempProbeSeq;
    }

    public Integer getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(Integer maxTemp) {
        this.maxTemp = maxTemp;
    }

    public Integer getMinTempSysNum() {
        return minTempSysNum;
    }

    public void setMinTempSysNum(Integer minTempSysNum) {
        this.minTempSysNum = minTempSysNum;
    }

    public Integer getMinTempProbeSeq() {
        return minTempProbeSeq;
    }

    public void setMinTempProbeSeq(Integer minTempProbeSeq) {
        this.minTempProbeSeq = minTempProbeSeq;
    }

    public Integer getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(Integer minTemp) {
        this.minTemp = minTemp;
    }

    @Override
    public void build(ByteArrayBuf byteBuf) throws Exception {
        int index = byteBuf.readerIndex();

        // 最高电压电池子系统号
        this.maxVoltageCellSysNum = ByteUtils.toInt(byteBuf.readByte());
        // 最高电压电池单体代号
        this.maxVoltageCellCode = ByteUtils.toInt(byteBuf.readByte());
        // 电池单体电压最高值
        this.cellMaxVoltage = ByteUtils.toDouble(byteBuf.readBytes(2), 1000, 0);
        // 最低电压电池子系统号
        this.minVoltageCellSysNum = ByteUtils.toInt(byteBuf.readByte());
        // 最低电压电池单体代号
        this.minVoltageCellCode = ByteUtils.toInt(byteBuf.readByte());
        // 电池单体电压最低值
        this.cellMinVoltage = ByteUtils.toDouble(byteBuf.readBytes(2), 1000, 0);
        // 最高温度子系统号码
        this.maxTempSysNum = ByteUtils.toInt(byteBuf.readByte());
        // 最高温度探针序号
        this.maxTempProbeSeq = ByteUtils.toInt(byteBuf.readByte());
        // 最高温度值
        this.maxTemp = ByteUtils.toInt(byteBuf.readByte(), 40);
        // 最低温度子系统号码
        this.minTempSysNum = ByteUtils.toInt(byteBuf.readByte());
        // 最低温度探针序号
        this.minTempProbeSeq = ByteUtils.toInt(byteBuf.readByte());
        // 最低温度值
        this.minTemp = ByteUtils.toInt(byteBuf.readByte(), 40);

        logger.info("[RealtimeDataPacket.ExtremumPacket] bytes:{}, data:{}"
                , ByteUtils.asHex(byteBuf.getBytes(index, byteBuf.readerIndex() - index))
                , JSON.toJSONString(this));
    }

    @Override
    public byte[] unbuild() {

        // 最高电压电池子系统号
        byte maxVoltageCellSysNumByte = ByteUtils.integerToByteAutoFF(this.getMaxVoltageCellSysNum());
        // 最高电压电池单体代号
        byte maxVoltageCellCodeByte = ByteUtils.integerToByteAutoFF(this.getMaxVoltageCellCode());
        // 电池单体电压最高值
        byte[] cellMaxVoltageBytes = ByteUtils.doubleToByteArrayFF(this.getCellMaxVoltage(), 1000, 0, 2);
        // 最低电压电池子系统号
        byte minVoltageCellSysNumByte = ByteUtils.integerToByteAutoFF(this.getMinVoltageCellSysNum());
        // 最低电压电池单体代号
        byte minVoltageCellCodeByte = ByteUtils.integerToByteAutoFF(this.getMinVoltageCellCode());
        // 电池单体电压最低值
        byte[] cellMinVoltageBytes = ByteUtils.doubleToByteArrayFF(this.getCellMinVoltage(), 1000, 0, 2);
        // 最高温度子系统号码
        byte maxTempSysNumByte = ByteUtils.integerToByteAutoFF(this.getMaxTempSysNum());
        // 最高温度探针序号
        byte maxTempProbeSeqByte = ByteUtils.integerToByteAutoFF(this.getMaxTempProbeSeq());
        // 最高温度值
        byte maxTempByte = ByteUtils.integerToByteAutoFF(this.getMaxTemp(), 40);
        // 最低温度子系统号码
        byte minTempSysNumByte = ByteUtils.integerToByteAutoFF(this.getMinTempSysNum());
        // 最低温度探针序号
        byte minTempProbeSeqByte = ByteUtils.integerToByteAutoFF(this.getMinTempProbeSeq());
        // 最低温度值
        byte minTempByte = ByteUtils.integerToByteAutoFF(this.getMinTemp(), 40);

        return ByteUtils.addAll(maxVoltageCellSysNumByte, maxVoltageCellCodeByte, cellMaxVoltageBytes, minVoltageCellSysNumByte, minVoltageCellCodeByte
                ,cellMinVoltageBytes, maxTempSysNumByte, maxTempProbeSeqByte, maxTempByte, minTempSysNumByte, minTempProbeSeqByte, minTempByte);

    }

    @Override
    public Integer length() {
        return null;
    }
}
