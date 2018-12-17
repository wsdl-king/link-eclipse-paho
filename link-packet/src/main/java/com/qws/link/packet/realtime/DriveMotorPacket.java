package com.qws.link.packet.realtime;


import com.qws.link.ByteUtils;
import com.qws.link.packet.base.pakcet.GBPacket;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 驱动电机数据包
 */
public class DriveMotorPacket implements GBPacket,Serializable{
	/**驱动电机个数*/
	private Integer num;
	/**驱动电机总成信息列表*/
	private List<DriveMotorInfoPacket> infos;
	/***/
	private int length;

	@Override
	public void build(byte[] bytes)  {
		num=0xff & bytes[0];
		infos=new ArrayList<>(num);
		int position=1;
		for(int i=0;i<num;i++){
			DriveMotorInfoPacket info = new DriveMotorInfoPacket();
			info.build(Arrays.copyOfRange(bytes, position, position+info.length()));
			position+=info.length();
			infos.add(info);
		}
		length=position;
	}

	@Override
	public byte[] unbuild() {
		byte numByte = num.byteValue();
		byte[] result = new byte[]{numByte};
		for(int i=0;i<num;i++){
			result= ByteUtils.addAll(result,infos.get(i).unbuild());
		}
		length =result.length;
		return result;
	}
	
	@Override
	public Integer length()  {
		return length;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public List<DriveMotorInfoPacket> getInfos() {
		return infos;
	}

	public void setInfos(List<DriveMotorInfoPacket> infos) {
		this.infos = infos;
	}
	
}
