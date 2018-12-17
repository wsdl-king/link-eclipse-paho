package com.qws.link.packet.realtime;

import com.qws.link.ByteUtils;
import com.qws.link.packet.base.pakcet.GBPacket;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *可充电储能装置电压数据
 */
public class ChargeDeviceVoltagePacket implements GBPacket,Serializable{
	/**子系统个数*/
	private Integer sysNum;
	/**可充电储能子系统电压信息列表*/
	private List<SingleChargeDeviceVoltagePacket> voltageInfos;
	
	private int length;
	
	@Override
	public void build(byte[] bytes)  {
		sysNum = 0xff & bytes[0];
		int position=1;
		voltageInfos=new ArrayList<>(sysNum);
		for(int i=0;i<sysNum;i++){
			SingleChargeDeviceVoltagePacket voltage = new SingleChargeDeviceVoltagePacket();
			voltage.build(Arrays.copyOfRange(bytes, position, bytes.length));
			position+=voltage.length();
			voltageInfos.add(voltage);
		}
		length=position;
	}

	@Override
	public byte[] unbuild() {
		byte sysNumByte = sysNum.byteValue();
		length=1;
		byte[] result = new byte[]{sysNumByte};
		for(SingleChargeDeviceVoltagePacket voltageInfo:voltageInfos){
			result= ByteUtils.addAll(result,voltageInfo.unbuild());
			length+=voltageInfo.length();
		}
		return result;
	}

	@Override
	public Integer length()  {
		return length;
	}

	public Integer getSysNum() {
		return sysNum;
	}

	public void setSysNum(Integer sysNum) {
		this.sysNum = sysNum;
	}

	public List<SingleChargeDeviceVoltagePacket> getVoltageInfos() {
		return voltageInfos;
	}

	public void setVoltageInfos(List<SingleChargeDeviceVoltagePacket> voltageInfos) {
		this.voltageInfos = voltageInfos;
	}
	
}
