package com.qws.link.realtime.base;

import com.qws.link.ByteUtils;
import com.qws.link.base.ByteArrayBuf;
import com.qws.link.base.pakcet.BasePacket;
import com.qws.link.base.pakcet.GBPacket;
import com.qws.link.constant.RealtimeEnum;
import com.qws.link.realtime.children.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * @author qiwenshuai
 * @note
 * @since 18-12-28 16:07 by jdk 1.8
 */
public class RealTimePacket  implements GBPacket, Serializable {

    private static final Logger logger = LoggerFactory.getLogger(RealTimePacket.class);
    /**
     * 上传时间
     */
    protected Long uploadTime;
    /**
     * 整车数据
     */
    protected CarInfoPacket carInfoPacket;
    /**
     * 驱动电机数据
     */
    protected DriveMotorPacket driveMotorPacket;
    /**
     * 燃料电池数据
     */
    protected FuelCellPacket fuelCellPacket;
    /**
     * 发动机数据(停车充电过程不传输)
     */
    protected EnginePacket enginePacket;
    /**
     * 车辆位置数据
     */
    protected VehicleLocationPacket vehicleLocationPacket;
    /**
     * 极值数据
     */
    protected ExtremumPacket extremumPacket;
    /**
     * 报警数据
     */
    protected WarningPacket warningPacket;
    /**
     * 可充电储能装置电压数据
     */
    protected EnergyStorageVoltagePacket energyStorageVoltagePacket;
    /**
     * 可充电储能装置温度数据
     */
    protected EnergyStorgeTempPacket energyStorgeTempPacket;

    /**
     * OTA升级节点上报--- 此信息为自定义扩展的数据
     */
    protected OtaNodePacket otaNodePacket;



    public void build(ByteArrayBuf byteBuf) throws Exception {
        uploadTime = ByteUtils.dateBytes2Long(byteBuf.readBytes(6));

        while (byteBuf.readableBytes() > 0) {
            // 获取信息类型标志
            int typeId = ByteUtils.toInt(byteBuf.readByte());
            // 获取类型
            RealtimeEnum realtimeEnum = RealtimeEnum.getRealtimeDataType(typeId);
            if (realtimeEnum == null) throw new IllegalArgumentException(String.format("实时数据包解析错误, 未知的数据类型: %d", typeId));

            unwrapped(realtimeEnum, byteBuf);
        }

    }
    private void unwrapped(RealtimeEnum realtimeEnum, ByteArrayBuf byteBuf) throws Exception {

        BasePacket packet = realtimeEnum.getDataClass().newInstance();
        packet.build(byteBuf);
        //反射给不同的包赋值
        RealtimeEnum.dataSetMethod(realtimeEnum.getId()).invoke(this, packet);
    }

    @Override
    public byte[] unbuild() {
        return new byte[0];
    }

    @Override
    public Integer length() {
        return null;
    }

    public static Logger getLogger() {
        return logger;
    }

    public Long getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Long uploadTime) {
        this.uploadTime = uploadTime;
    }

    public CarInfoPacket getCarInfoPacket() {
        return carInfoPacket;
    }

    public void setCarInfoPacket(CarInfoPacket carInfoPacket) {
        this.carInfoPacket = carInfoPacket;
    }

    public DriveMotorPacket getDriveMotorPacket() {
        return driveMotorPacket;
    }

    public void setDriveMotorPacket(DriveMotorPacket driveMotorPacket) {
        this.driveMotorPacket = driveMotorPacket;
    }

    public FuelCellPacket getFuelCellPacket() {
        return fuelCellPacket;
    }

    public void setFuelCellPacket(FuelCellPacket fuelCellPacket) {
        this.fuelCellPacket = fuelCellPacket;
    }

    public EnginePacket getEnginePacket() {
        return enginePacket;
    }

    public void setEnginePacket(EnginePacket enginePacket) {
        this.enginePacket = enginePacket;
    }

    public VehicleLocationPacket getVehicleLocationPacket() {
        return vehicleLocationPacket;
    }

    public void setVehicleLocationPacket(VehicleLocationPacket vehicleLocationPacket) {
        this.vehicleLocationPacket = vehicleLocationPacket;
    }

    public ExtremumPacket getExtremumPacket() {
        return extremumPacket;
    }

    public void setExtremumPacket(ExtremumPacket extremumPacket) {
        this.extremumPacket = extremumPacket;
    }

    public WarningPacket getWarningPacket() {
        return warningPacket;
    }

    public void setWarningPacket(WarningPacket warningPacket) {
        this.warningPacket = warningPacket;
    }

    public EnergyStorageVoltagePacket getEnergyStorageVoltagePacket() {
        return energyStorageVoltagePacket;
    }

    public void setEnergyStorageVoltagePacket(EnergyStorageVoltagePacket energyStorageVoltagePacket) {
        this.energyStorageVoltagePacket = energyStorageVoltagePacket;
    }

    public EnergyStorgeTempPacket getEnergyStorgeTempPacket() {
        return energyStorgeTempPacket;
    }

    public void setEnergyStorgeTempPacket(EnergyStorgeTempPacket energyStorgeTempPacket) {
        this.energyStorgeTempPacket = energyStorgeTempPacket;
    }

    public OtaNodePacket getOtaNodePacket() {
        return otaNodePacket;
    }

    public void setOtaNodePacket(OtaNodePacket otaNodePacket) {
        this.otaNodePacket = otaNodePacket;
    }
}
