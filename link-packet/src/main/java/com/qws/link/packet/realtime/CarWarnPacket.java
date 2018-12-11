package com.qws.link.packet.realtime;

import com.qws.link.ByteUtils;
import com.qws.link.tbox.common.base.BasePacket;
import com.qws.link.packet.warn.CarWarnCommonPacket;
import java.io.Serializable;
import java.util.Arrays;

/**
 * 告警数据包
 */
public class CarWarnPacket implements BasePacket,Serializable{
	private Integer length=0;
	/** 最高报警等级 */
	private Integer warnLevel;
	/** 通用报警*/
	private CarWarnCommonPacket commonWarn;
	/** 用来检验是的标识 */
	private byte[] flagBytes;
	
	@Override
	public void build(byte[] bytes) {
		warnLevel=0xff & bytes[0];
		commonWarn=new CarWarnCommonPacket();
		byte[] commonWarnBytes = Arrays.copyOfRange(bytes, 1, 5);
		commonWarn.build(commonWarnBytes);
		flagBytes=commonWarnBytes;
		length=9;

	}
	
	@Override
	public byte[] unbuild()  {
		byte levelByte = warnLevel.byteValue();
		return ByteUtils.addAll(levelByte,commonWarn.unbuild(),new byte[]{0,0,0,0});
	}

	@Override
	public Integer length()  {
		return length;
	}
	
	public byte[] bytes(){
		return flagBytes;
	}
	
	public Integer getWarnLevel() {
		return warnLevel;
	}

	public void setWarnLevel(Integer warnLevel) {
		this.warnLevel = warnLevel;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public CarWarnCommonPacket getCommonWarn() {
		return commonWarn;
	}

	public void setCommonWarn(CarWarnCommonPacket commonWarn) {
		this.commonWarn = commonWarn;
	}

}
