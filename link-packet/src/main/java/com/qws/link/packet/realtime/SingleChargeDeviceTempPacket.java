package com.qws.link.packet.realtime;

import com.qws.link.ByteUtils;
import com.qws.link.packet.base.BasePacket;
import com.qws.link.packet.base.GBPacket;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 可充电储能子系统温度信息
 */
public class SingleChargeDeviceTempPacket implements GBPacket,Serializable{
	/**系统编号*/
	private Integer sysNo;
	/**探针总数*/
	private Integer probeNum;
	/**温度值*/
	private List<Integer> temps;
	
	private int length;
	
	@Override
	public void build(byte[] bytes) {
		sysNo=0xff & bytes[0];
		probeNum= ByteUtils.byteArrayToInt(new byte[]{0x00,0x00,bytes[1],bytes[2]}, 0);
		temps=new ArrayList<>(probeNum);
		for(int i=0;i<probeNum;i++){
			temps.add((0xff & bytes[3+i])-40);
		}
		length=3+probeNum;
	}

	@Override
	public byte[] unbuild(){
		byte sysNoByte = sysNo.byteValue();
		byte[] probeNumBytes = ByteUtils.intToByteArray(probeNum, 2);
		byte[] tempsBytes = new byte[probeNum];
		for(int i=0;i<probeNum;i++){
			tempsBytes[i]=(byte)(temps.get(i)+40);
		}
		length=1+2+probeNum;
		return ByteUtils.addAll(sysNoByte,probeNumBytes,tempsBytes);
	}

	@Override
	public Integer length() {
		// TODO Auto-generated method stub
		return length;
	}

	public Integer getSysNo() {
		return sysNo;
	}

	public void setSysNo(Integer sysNo) {
		this.sysNo = sysNo;
	}

	public Integer getProbeNum() {
		return probeNum;
	}

	public void setProbeNum(Integer probeNum) {
		this.probeNum = probeNum;
	}

	public List<Integer> getTemps() {
		return temps;
	}

	public void setTemps(List<Integer> temps) {
		this.temps = temps;
	}
	
}
