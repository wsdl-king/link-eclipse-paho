package com.qws.link.packet.warn;


import com.qws.link.ByteUtils;
import com.qws.link.packet.base.pakcet.GBPacket;

public class CarWarnCommonPacket  extends BaseWarnPacket  implements GBPacket {
	/**1：温度差异报警；0：正常*/
	private Integer tempDiff;
	/**1：电池高温报警；0：正常*/
	private Integer highTemp;
	/**1：车载储能装置类型过压报警；0：正常*/
	private Integer energyDeviceOverVoltage;
	/**1：车载储能装置类型欠压报警；0：正常*/
	private Integer energyDeviceUnderVoltage;
	/**1：SOC低报警；0：正常*/
	private Integer socLow;
	/**1：单体电池过压报警；0：正常*/
	private Integer singleCellOverVoltage;
	/**1：单体电池欠压报警；0：正常*/
	private Integer singleCellUnderVoltage;
	/**1：SOC过高报警；0：正常*/
	private Integer socHigh;
	/**1：SOC跳变报警；0：正常*/
	private Integer socHop;
	/**1：可充电储能系统不匹配报警；0：正常*/
	private Integer energyDeviceNoMatch;
	/**1：电池单体一致性差报警；0：正常*/
	private Integer singleCellLowMatch;
	/**1：绝缘报警；0：正常*/
	private Integer insulation;
	/**1：DC-DC温度报警；0：正常*/
	private Integer dcTemp;
	/**1：制动系统报警；0：正常*/
	private Integer brakeSys;
	/**1：DC-DC状态报警；0：正常*/
	private Integer dcStatus;
	/**1：驱动电机控制器温度报警；0：正常*/
	private Integer motorControlTemp;
	/**1：高压互锁状态报警；0：正常*/
	private Integer highVoltageEachLock;
	/**1：驱动电机温度报警；0：正常*/
	private Integer motorTemp;
	/**1：车载储能装置类型过充；0：正常*/
	private Integer energyDeviceOverCharge;
	/** 用来检验是的标识 */
	private byte[] flagBytes;
	
	@Override
	public void build(byte[] bytes){
		byte[] bits = ByteUtils.byte2BitArray(bytes[3]);
		tempDiff=0xff & bits[0];
		highTemp=0xff & bits[1];
		energyDeviceOverVoltage=0xff & bits[2];
		energyDeviceUnderVoltage=0xff & bits[3];
		socLow=0xff & bits[4];
		singleCellOverVoltage=0xff & bits[5];
		singleCellUnderVoltage=0xff & bits[6];
		socHigh=0xff & bits[7];
		bits = ByteUtils.byte2BitArray(bytes[2]);
		socHop=0xff & bits[0];
		energyDeviceNoMatch=0xff & bits[1];
		singleCellLowMatch=0xff & bits[2];
		insulation=0xff & bits[3];
		dcTemp=0xff & bits[4];
		brakeSys=0xff & bits[5];
		dcStatus=0xff & bits[6];
		motorControlTemp=0xff & bits[7];
		bits = ByteUtils.byte2BitArray(bytes[1]);
		highVoltageEachLock=0xff & bits[0];
		motorTemp=0xff & bits[1];
		energyDeviceOverCharge=0xff & bits[2];
		flagBytes=bytes;
	}

	@Override
	public byte[] unbuild(){
		byte warnFlag4=(byte)(tempDiff+(highTemp<<1)+(energyDeviceOverVoltage<<2)+(energyDeviceUnderVoltage<<3)
				+(socLow<<4)+(singleCellOverVoltage<<5)+(singleCellUnderVoltage<<6)+(socHigh<<7));
		byte warnFlag3=(byte)(socHop+(energyDeviceNoMatch<<1)+(singleCellLowMatch<<2)+(insulation<<3)
				+(dcTemp<<4)+(brakeSys<<5)+(dcStatus<<6)+(motorControlTemp<<7));
		byte warnFlag2=(byte)(highVoltageEachLock+(motorTemp<<1)+(energyDeviceOverCharge<<2));
		byte warnFlag=0;
		return new byte[]{warnFlag,warnFlag2,warnFlag3,warnFlag4};
	}

	@Override
	public Integer length() {
		// TODO Auto-generated method stub
		return 4;
	}

	public Integer getTempDiff() {
		return tempDiff;
	}

	public void setTempDiff(Integer tempDiff) {
		this.tempDiff = tempDiff;
	}

	public Integer getHighTemp() {
		return highTemp;
	}

	public void setHighTemp(Integer highTemp) {
		this.highTemp = highTemp;
	}

	public Integer getEnergyDeviceOverVoltage() {
		return energyDeviceOverVoltage;
	}

	public void setEnergyDeviceOverVoltage(Integer energyDeviceOverVoltage) {
		this.energyDeviceOverVoltage = energyDeviceOverVoltage;
	}

	public Integer getEnergyDeviceUnderVoltage() {
		return energyDeviceUnderVoltage;
	}

	public void setEnergyDeviceUnderVoltage(Integer energyDeviceUnderVoltage) {
		this.energyDeviceUnderVoltage = energyDeviceUnderVoltage;
	}

	public Integer getSocLow() {
		return socLow;
	}

	public void setSocLow(Integer socLow) {
		this.socLow = socLow;
	}

	public Integer getSingleCellOverVoltage() {
		return singleCellOverVoltage;
	}

	public void setSingleCellOverVoltage(Integer singleCellOverVoltage) {
		this.singleCellOverVoltage = singleCellOverVoltage;
	}

	public Integer getSingleCellUnderVoltage() {
		return singleCellUnderVoltage;
	}

	public void setSingleCellUnderVoltage(Integer singleCellUnderVoltage) {
		this.singleCellUnderVoltage = singleCellUnderVoltage;
	}

	public Integer getSocHigh() {
		return socHigh;
	}

	public void setSocHigh(Integer socHigh) {
		this.socHigh = socHigh;
	}

	public Integer getSocHop() {
		return socHop;
	}

	public void setSocHop(Integer socHop) {
		this.socHop = socHop;
	}

	public Integer getEnergyDeviceNoMatch() {
		return energyDeviceNoMatch;
	}

	public void setEnergyDeviceNoMatch(Integer energyDeviceNoMatch) {
		this.energyDeviceNoMatch = energyDeviceNoMatch;
	}

	public Integer getSingleCellLowMatch() {
		return singleCellLowMatch;
	}

	public void setSingleCellLowMatch(Integer singleCellLowMatch) {
		this.singleCellLowMatch = singleCellLowMatch;
	}

	public Integer getInsulation() {
		return insulation;
	}

	public void setInsulation(Integer insulation) {
		this.insulation = insulation;
	}

	public Integer getDcTemp() {
		return dcTemp;
	}

	public void setDcTemp(Integer dcTemp) {
		this.dcTemp = dcTemp;
	}

	public Integer getBrakeSys() {
		return brakeSys;
	}

	public void setBrakeSys(Integer brakeSys) {
		this.brakeSys = brakeSys;
	}

	public Integer getDcStatus() {
		return dcStatus;
	}

	public void setDcStatus(Integer dcStatus) {
		this.dcStatus = dcStatus;
	}

	public Integer getMotorControlTemp() {
		return motorControlTemp;
	}

	public void setMotorControlTemp(Integer motorControlTemp) {
		this.motorControlTemp = motorControlTemp;
	}

	public Integer getHighVoltageEachLock() {
		return highVoltageEachLock;
	}

	public void setHighVoltageEachLock(Integer highVoltageEachLock) {
		this.highVoltageEachLock = highVoltageEachLock;
	}

	public Integer getMotorTemp() {
		return motorTemp;
	}

	public void setMotorTemp(Integer motorTemp) {
		this.motorTemp = motorTemp;
	}

	public Integer getEnergyDeviceOverCharge() {
		return energyDeviceOverCharge;
	}

	public void setEnergyDeviceOverCharge(Integer energyDeviceOverCharge) {
		this.energyDeviceOverCharge = energyDeviceOverCharge;
	}

	@Override
	public byte[] bytes() {
		// TODO Auto-generated method stub
		return flagBytes;
	}

	@Override
	public String type() {
		// TODO Auto-generated method stub
		return "h";
	}

}
