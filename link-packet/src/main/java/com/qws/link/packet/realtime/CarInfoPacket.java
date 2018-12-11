package com.qws.link.packet.realtime;


import com.qws.link.ByteUtils;
import com.qws.link.tbox.common.base.BasePacket;

import java.io.Serializable;

/**
 * 车辆信息数据包
 */
public class CarInfoPacket implements BasePacket,Serializable{
	private static final long serialVersionUID = -2067986491147110602L;
	/** 车辆状态 0x01：车辆启动状态；0x02：熄火；0x03：其他状态；“0xFE”表示异常，“0xFF”表示无效。  */
	private Integer carStatus;
	/** 充电状态 0x01：停车充电；0x02：行驶充电；0x03：未充电状态；0x04：充电完成；“0xFE”表示异常，“0xFF”表示无效。 */
	private Integer chargeStatus;
	/** 运行模式 0x01: 纯电；0x02：混动；0x03：燃油；0xFE表示异常；0xFF表示无效 */
	private Integer runModel;
	/** 车速 “0xFF,0xFE”表示异常，“0xFF,0xFF”表示无效。 单元：km/h 范围：0至220*/
	private Double speed;
	/** 累计里程 “0xFF, 0xFF, 0xFF,0xFE”表示异常，“0xFF,0xFF,0xFF,0xFF”表示无效 单元：km 范围：0至999999.9*/
	private Double mileage;
	/** 总电压 “0xFF,0xFE”表示异常，“0xFF,0xFF”表示无效。 单元：V 范围：0至1000*/
	private Double totalVoltage;
	/** 总电流 “0xFF,0xFE”表示异常，“0xFF,0xFF”表示无效。 单元：A 范围：-1000至1000*/
	private Double totalCurrent;
	/** soc “0xFE”表示异常，“0xFF”表示无效 范围：0至100*/
	private Integer soc;
	/** dc状态 0x01：工作；0x02：断开，“0xFE”表示异常，“0xFF”表示无效 */
	private Integer dcStatus;
	/** 挡位 0空挡 1-6为1-6挡 13倒挡 14自动D挡 15停车p挡*/
	private Integer gears;
	/** 绝缘电阻 单元：KΩ 范围：0至60000*/
	private Integer resistance;
	/**驱动状态 1有效 0无效*/
	private Integer driveStatus;
	/**制动状态 1有效 0无效*/
	private Integer brakeStatus;
	/**加速踏板行程值 百分比*/
	private Integer accelTripValue;
	/**制动踏板行程值  百分比*/
	private Integer brakeTripValue;
	
	@Override
	public void build(byte[] bytes){
		carStatus = 0xff & bytes[0];
		chargeStatus = 0xff & bytes[1];
		runModel = 0xff & bytes[2];
		speed = ByteUtils.toDouble(bytes[3], bytes[4],10, 0);
		mileage = ByteUtils.toDouble(bytes[5], bytes[6],bytes[7],bytes[8],10, 0);
		totalVoltage = ByteUtils.toDouble(bytes[9], bytes[10],10, 0);
		totalCurrent = ByteUtils.toDouble(bytes[11], bytes[12],10, 1000);
		soc = 0xff & bytes[13];
		dcStatus = 0xff & bytes[14];
		byte[] gearBytes = ByteUtils.byte2BitArray(bytes[15]);
		driveStatus = 0xff & gearBytes[5];
		brakeStatus = 0xff & gearBytes[4];
		gears = 0x0f & bytes[15];
		resistance = ByteUtils.byteArrayToInt(new byte[]{0x00,0x00,bytes[16],bytes[17]}, 0);
		accelTripValue=0xff & bytes[18];
		brakeTripValue=0xff & bytes[19];
	}
	@Override
	public byte[] unbuild() {
		byte carStatusByte = carStatus.byteValue();
		byte chargeStatusByte = chargeStatus.byteValue();
		byte runModelByte = runModel.byteValue();
		byte[] speedBytes = ByteUtils.intToByteArray(ByteUtils.DoubleToInt(speed,10,0),2);
		byte[] totalMileageBytes = ByteUtils.intToByteArray(ByteUtils.DoubleToInt(mileage,10,0),4);
		byte[] totalVoltageBytes = ByteUtils.intToByteArray(ByteUtils.DoubleToInt(totalVoltage,10,0),2);
		byte[] totalCurrentBytes = ByteUtils.intToByteArray(ByteUtils.DoubleToInt(totalCurrent,10,1000),2);
		byte socByte = soc.byteValue();
		byte dcStatusByte = dcStatus.byteValue();
		byte gearsByte = (byte)((driveStatus<<5)+(brakeStatus<<4)+gears);
		byte[] resistanceBytes = ByteUtils.intToByteArray(resistance,2);
		byte accelTripValueByte = accelTripValue.byteValue();
		byte breakValueByte =brakeTripValue.byteValue();
		
		return ByteUtils.addAll(carStatusByte,
				chargeStatusByte,
				runModelByte,
				speedBytes,
				totalMileageBytes,
				totalVoltageBytes,
				totalCurrentBytes,
				socByte,
				dcStatusByte,
				gearsByte,
				resistanceBytes,
				accelTripValueByte,breakValueByte);
	}
	 
	@Override
	public Integer length() {
		return 20;
	}
	public Integer getCarStatus() {
        return this.carStatus;
    }
    public void setCarStatus(Integer carStatus) {
        this.carStatus = carStatus;
    }
	public Integer getChargeStatus() {
        return this.chargeStatus;
    }
    public void setChargeStatus(Integer chargeStatus) {
        this.chargeStatus = chargeStatus;
    }
	public Integer getRunModel() {
        return this.runModel;
    }
    public void setRunModel(Integer runModel) {
        this.runModel = runModel;
    }
	public Double getSpeed() {
        return this.speed;
    }
    public void setSpeed(Double speed) {
        this.speed = speed;
    }
	public Double getTotalVoltage() {
        return this.totalVoltage;
    }
    public void setTotalVoltage(Double totalVoltage) {
        this.totalVoltage = totalVoltage;
    }
	public Double getTotalCurrent() {
        return this.totalCurrent;
    }
    public void setTotalCurrent(Double totalCurrent) {
        this.totalCurrent = totalCurrent;
    }
	public Integer getSoc() {
        return this.soc;
    }
    public void setSoc(Integer soc) {
        this.soc = soc;
    }
	public Integer getDcStatus() {
        return this.dcStatus;
    }
    public void setDcStatus(Integer dcStatus) {
        this.dcStatus = dcStatus;
    }
	public Integer getGears() {
        return this.gears;
    }
    public void setGears(Integer gears) {
        this.gears = gears;
    }
	public Integer getResistance() {
        return this.resistance;
    }
    public void setResistance(Integer resistance) {
        this.resistance = resistance;
    }
	public Integer getDriveStatus() {
		return driveStatus;
	}
	public void setDriveStatus(Integer driveStatus) {
		this.driveStatus = driveStatus;
	}
	public Integer getBrakeStatus() {
		return brakeStatus;
	}
	public void setBrakeStatus(Integer brakeStatus) {
		this.brakeStatus = brakeStatus;
	}
	public Integer getAccelTripValue() {
		return accelTripValue;
	}
	public void setAccelTripValue(Integer accelTripValue) {
		this.accelTripValue = accelTripValue;
	}
	public Integer getBrakeTripValue() {
		return brakeTripValue;
	}
	public void setBrakeTripValue(Integer brakeTripValue) {
		this.brakeTripValue = brakeTripValue;
	}
	public Double getMileage() {
		return mileage;
	}
	public void setMileage(Double mileage) {
		this.mileage = mileage;
	}
}
