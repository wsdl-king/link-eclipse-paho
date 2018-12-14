package com.qws.link.packet.realtime;


import com.qws.link.ByteUtils;
import com.qws.link.packet.base.BasePacket;
import com.qws.link.packet.base.GBPacket;

import java.io.Serializable;

/**
 * 极值数据包
 */
public class ExtremumPacket implements GBPacket,Serializable{
	private static final long serialVersionUID = -5231142837709202694L;
	/** 最高电压电池子系统号 “0xFE”表示异常，“0xFF”表示无效 范围：1至250*/
	private Integer highestVoltageCellsNo;
	/** 最高电压电池单体代号 “0xFE”表示异常，“0xFF”表示无效 范围：1至250*/
	private Integer highestVoltageCellNo;
	/** 电池单体电压最高值 “0xFF,0xFE”表示异常，“0xFF,0xFF”表示无效 单元：V 范围：1至15*/
	private Double highestVoltageSingle;
	/** 最低电压电池子系统号 “0xFE”表示异常，“0xFF”表示无效 范围：1至250*/
	private Integer lowestVoltageCellsNo;
	/** 最低电压电池单体代号 “0xFE”表示异常，“0xFF”表示无效 范围：1至250*/
	private Integer lowestVoltageCellNo;
	/** 电池单体电压最低值 “0xFF,0xFE”表示异常，“0xFF,0xFF”表示无效 单元：V 范围：1至15*/
	private Double lowestVoltageSingle;
	/** 最高温度子系统号 “0xFE”表示异常，“0xFF”表示无效 范围：1至250*/
	private Integer highestTempCellsNo;
	/** 最高温度探针单体代号 “0xFE”表示异常，“0xFF”表示无效 范围：1至250*/
	private Integer highestTempProbeNo;
	/** 最高温度值 “0xFE”表示异常，“0xFF”表示无效 单元：℃ 范围：-40至210*/
	private Integer highestTemp;
	/** 最低温度子系统号 “0xFE”表示异常，“0xFF”表示无效 范围：1至250*/
	private Integer lowestTempCellsNo;
	/** 最低温度探针子系统代号 “0xFE”表示异常，“0xFF”表示无效 范围：1至250*/
	private Integer lowestTempProbeNo;
	/** 最低温度值 “0xFE”表示异常，“0xFF”表示无效 单元：℃ 范围：-40至210*/
	private Integer lowestTemp;
	
	@Override
	public void build(byte[] bytes)  {
		highestVoltageCellsNo = 0xff & bytes[0];
		highestVoltageCellNo = 0xff & bytes[1];
		highestVoltageSingle = ByteUtils.toDouble(bytes[2], bytes[3],1000, 0);
		lowestVoltageCellsNo = 0xff & bytes[4];
		lowestVoltageCellNo = 0xff & bytes[5];
		lowestVoltageSingle = ByteUtils.toDouble(bytes[6], bytes[7],1000, 0);
		highestTempCellsNo = 0xff & bytes[8];
		highestTempProbeNo = 0xff & bytes[9];
		highestTemp = (0xff & bytes[10])-40;
		lowestTempCellsNo = 0xff & bytes[11];
		lowestTempProbeNo = 0xff & bytes[12];
		lowestTemp = (0xff & bytes[13])-40;
	}
	@Override
	public byte[] unbuild()  {
		byte highestVoltageCellsNoByte = highestVoltageCellsNo.byteValue();
		byte highestVoltageCellNoByte = highestVoltageCellNo.byteValue();
		byte[] highestVoltageSingleBytes = ByteUtils.intToByteArray(ByteUtils.DoubleToInt(highestVoltageSingle,1000,0),2);
		byte lowestVoltageCellsNoByte = lowestVoltageCellsNo.byteValue();
		byte lowestVoltageCellNoByte = lowestVoltageCellNo.byteValue();
		byte[] lowestVoltageSingleBytes = ByteUtils.intToByteArray(ByteUtils.DoubleToInt(lowestVoltageSingle,1000,0),2);
		byte highestTempCellsNoByte = highestTempCellsNo.byteValue();
		byte highestTempProbeNoByte = highestTempProbeNo.byteValue();
		byte highestTempByte = Integer.valueOf(40+highestTemp).byteValue();
		byte lowestTempCellsNoByte = lowestTempCellsNo.byteValue();
		byte lowestTempProbeNoByte = lowestTempProbeNo.byteValue();
		byte lowestTempByte = Integer.valueOf(40+lowestTemp).byteValue();
		return ByteUtils.addAll(highestVoltageCellsNoByte,highestVoltageCellNoByte,highestVoltageSingleBytes,lowestVoltageCellsNoByte,lowestVoltageCellNoByte,lowestVoltageSingleBytes,highestTempCellsNoByte,highestTempProbeNoByte,highestTempByte,lowestTempCellsNoByte,lowestTempProbeNoByte,lowestTempByte);
	}
 
	@Override
	public Integer length() {
		return 14;
	}
	public Integer getHighestVoltageCellsNo() {
        return this.highestVoltageCellsNo;
    }
    public void setHighestVoltageCellsNo(Integer highestVoltageCellsNo) {
        this.highestVoltageCellsNo = highestVoltageCellsNo;
    }
	public Integer getHighestVoltageCellNo() {
        return this.highestVoltageCellNo;
    }
    public void setHighestVoltageCellNo(Integer highestVoltageCellNo) {
        this.highestVoltageCellNo = highestVoltageCellNo;
    }
	public Double getHighestVoltageSingle() {
        return this.highestVoltageSingle;
    }
    public void setHighestVoltageSingle(Double highestVoltageSingle) {
        this.highestVoltageSingle = highestVoltageSingle;
    }
	public Integer getLowestVoltageCellsNo() {
        return this.lowestVoltageCellsNo;
    }
    public void setLowestVoltageCellsNo(Integer lowestVoltageCellsNo) {
        this.lowestVoltageCellsNo = lowestVoltageCellsNo;
    }
	public Integer getLowestVoltageCellNo() {
        return this.lowestVoltageCellNo;
    }
    public void setLowestVoltageCellNo(Integer lowestVoltageCellNo) {
        this.lowestVoltageCellNo = lowestVoltageCellNo;
    }
	public Double getLowestVoltageSingle() {
        return this.lowestVoltageSingle;
    }
    public void setLowestVoltageSingle(Double lowestVoltageSingle) {
        this.lowestVoltageSingle = lowestVoltageSingle;
    }
	public Integer getHighestTempCellsNo() {
        return this.highestTempCellsNo;
    }
    public void setHighestTempCellsNo(Integer highestTempCellsNo) {
        this.highestTempCellsNo = highestTempCellsNo;
    }
	public Integer getHighestTempProbeNo() {
        return this.highestTempProbeNo;
    }
    public void setHighestTempProbeNo(Integer highestTempProbeNo) {
        this.highestTempProbeNo = highestTempProbeNo;
    }
	public Integer getHighestTemp() {
        return this.highestTemp;
    }
    public void setHighestTemp(Integer highestTemp) {
        this.highestTemp = highestTemp;
    }
	public Integer getLowestTempCellsNo() {
        return this.lowestTempCellsNo;
    }
    public void setLowestTempCellsNo(Integer lowestTempCellsNo) {
        this.lowestTempCellsNo = lowestTempCellsNo;
    }
	public Integer getLowestTempProbeNo() {
        return this.lowestTempProbeNo;
    }
    public void setLowestTempProbeNo(Integer lowestTempProbeNo) {
        this.lowestTempProbeNo = lowestTempProbeNo;
    }
	public Integer getLowestTemp() {
        return this.lowestTemp;
    }
    public void setLowestTemp(Integer lowestTemp) {
        this.lowestTemp = lowestTemp;
    }
}
