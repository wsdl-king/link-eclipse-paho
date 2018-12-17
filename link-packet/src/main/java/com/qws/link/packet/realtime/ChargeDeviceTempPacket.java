package com.qws.link.packet.realtime;

import com.qws.link.ByteUtils;
import com.qws.link.packet.base.pakcet.GBPacket;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 可充电储能装置温度
 */
public class ChargeDeviceTempPacket implements GBPacket,Serializable{
	private static final long serialVersionUID = 7887048097309842642L;
	/**子系统个数*/
	private Integer sysNum;
	/**可充电储能子系统温度信息列表*/
	private List<SingleChargeDeviceTempPacket> tempInfos;
	private int length;

	@Override
	public void build(byte[] bytes) {
		sysNum = 0xff & bytes[0];
		int position=1;
		tempInfos=new ArrayList<>(sysNum);
		for(int i=0;i<sysNum;i++){
			SingleChargeDeviceTempPacket temp = new SingleChargeDeviceTempPacket();
			temp.build(Arrays.copyOfRange(bytes, position, bytes.length));
			position+=temp.length();
			tempInfos.add(temp);
		}
		length=position;
	}

	@Override
	public byte[] unbuild() {
		byte sysNumByte = sysNum.byteValue();
		length=1;
		byte[] result = new byte[]{sysNumByte};
		for(SingleChargeDeviceTempPacket tempInfo:tempInfos){
			result= ByteUtils.addAll(result,tempInfo.unbuild());
			length+=tempInfo.length();
		}
		return result;
	}

	@Override
	public Integer length(){
		// TODO Auto-generated method stub
		return length;
	}

	public Integer getSysNum() {
		return sysNum;
	}

	public void setSysNum(Integer sysNum) {
		this.sysNum = sysNum;
	}

	public List<SingleChargeDeviceTempPacket> getTempInfos() {
		return tempInfos;
	}

	public void setTempInfos(List<SingleChargeDeviceTempPacket> tempInfos) {
		this.tempInfos = tempInfos;
	}
	
}
