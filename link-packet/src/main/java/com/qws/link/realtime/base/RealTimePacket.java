package com.qws.link.realtime.base;

import com.qws.link.realtime.children.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author qiwenshuai
 * @note
 * @since 18-12-28 16:07 by jdk 1.8
 */
public class RealTimePacket {

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


}
