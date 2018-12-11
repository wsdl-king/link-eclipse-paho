package com.qws.link.packet.realtime;

import com.qws.link.ByteUtils;
import com.qws.link.tbox.common.base.BasePacket;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 可充电储能子系统电压信息
 */
public class SingleChargeDeviceVoltagePacket implements BasePacket,Serializable{
	
	/**可充电储能子系统号*/
	private Integer sysNo;
	/**可充电储能装置电压*/
	private Double voltage;
	/**可充电储能装置电流*/
	private Double current;
	/**单体电池总数*/
	private Integer allCellsNum;
	/**本帧起始电池序号*/
	private Integer cellsNo;
	/**本帧单体电池总数*/
	private Integer cellsNum;
	/**单体电池电压*/
	private List<Double> voltages;
	
	private int length;
	
	@Override
	public void build(byte[] bytes)  {
		sysNo=0xff & bytes[0];
		voltage= ByteUtils.toDouble(bytes[1], bytes[2], 10, 0);
		current=ByteUtils.toDouble(bytes[3], bytes[4], 10, 1000);
		allCellsNum=ByteUtils.byteArrayToInt(new byte[]{0x00,0x00,bytes[5],bytes[6]}, 0);
		cellsNo=ByteUtils.byteArrayToInt(new byte[]{0x00,0x00,bytes[7],bytes[8]}, 0);
		cellsNum=0xff & bytes[9];
		voltages=new ArrayList<Double>(cellsNum);
		for(int i=0;i<cellsNum;i++){
			voltages.add(ByteUtils.toDouble(bytes[10+2*i], bytes[11+2*i], 1000, 0));
		}
		length=10+cellsNum*2;
	}

	@Override
	public byte[] unbuild() {
		byte sysNoByte = sysNo.byteValue();
		byte[] voltageBytes = ByteUtils.intToByteArray(ByteUtils.DoubleToInt(voltage, 10, 0), 2);
		byte[] currentBytes = ByteUtils.intToByteArray(ByteUtils.DoubleToInt(current, 10, 1000), 2);
		byte[] allCellsNumBytes = ByteUtils.intToByteArray(allCellsNum, 2);
		byte[] cellsNoBytes = ByteUtils.intToByteArray(cellsNo, 2);
		byte cellsNumByte = cellsNum.byteValue();
		byte[] voltagesBytes = new byte[cellsNumByte*2];
		byte[] tempBytes;
		for(int i=0;i<cellsNum;i++){
			tempBytes=ByteUtils.intToByteArray(ByteUtils.DoubleToInt(voltages.get(i), 1000, 0), 2);
			voltagesBytes[2*i]=tempBytes[0];
			voltagesBytes[2*i+1]=tempBytes[1];
		}
		length=10+2*cellsNum;
		return ByteUtils.addAll(sysNoByte,voltageBytes,currentBytes,allCellsNumBytes,cellsNoBytes,cellsNumByte,voltagesBytes);
	}

	@Override
	public Integer length() {
		return length;
	}

	public Integer getSysNo() {
		return sysNo;
	}

	public void setSysNo(Integer sysNo) {
		this.sysNo = sysNo;
	}

	public Double getVoltage() {
		return voltage;
	}

	public void setVoltage(Double voltage) {
		this.voltage = voltage;
	}

	public Double getCurrent() {
		return current;
	}

	public void setCurrent(Double current) {
		this.current = current;
	}

	public Integer getAllCellsNum() {
		return allCellsNum;
	}

	public void setAllCellsNum(Integer allCellsNum) {
		this.allCellsNum = allCellsNum;
	}

	public Integer getCellsNo() {
		return cellsNo;
	}

	public void setCellsNo(Integer cellsNo) {
		this.cellsNo = cellsNo;
	}

	public Integer getCellsNum() {
		return cellsNum;
	}

	public void setCellsNum(Integer cellsNum) {
		this.cellsNum = cellsNum;
	}

	public List<Double> getVoltages() {
		return voltages;
	}

	public void setVoltages(List<Double> voltages) {
		this.voltages = voltages;
	}
	
}
