package com.qws.link.constant;

import com.qws.link.ReflectUtil;
import com.qws.link.base.ByteArrayBuf;
import com.qws.link.base.pakcet.BasePacket;
import com.qws.link.realtime.children.*;
import com.qws.link.realtime.extend.RealTimeExtendPacket;

import java.lang.reflect.Method;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * 实时信息上报- 枚举数据类型
 */
public enum RealtimeEnum {

    /** 整车数据 */
    carInfoPacket(0x01,  CarInfoPacket.class),
    /** 驱动电机数据 */
    driveMotorPacket(0x02, DriveMotorPacket.class),
    /** 燃料电池数据 */
    fuelCellPacket(0x03,FuelCellPacket.class),
    /** 发动机数据 */
    enginePacket(0x04, EnginePacket.class),
    /** 车辆定位数据 */
    vehicleLocationPacket(0x05, VehicleLocationPacket.class),
    /** 极值数据 */
    extremumPacket(0x06,ExtremumPacket.class),
    /** 报警数据 */
    warningPacket(0x07, WarningPacket.class),
    /** 可充电储能装置电压数据 */
    energyStorageVoltagePacket(0x08, EnergyStorageVoltagePacket.class),
    /** 可充电储能装置温度数据 */
    energyStorgeTempPacket(0x09, EnergyStorgeTempPacket.class),
    /** OTA节点类 */
    otaNodeData(0xB1, OtaNodePacket.class);

    private Integer id;

    private Class<? extends  BasePacket> dataClass;

    private static final Map<Integer, RealtimeEnum> realtimeDataTypeMap = new HashMap<>();

    private static final Map<Integer, Method> DATA_GET_METHOD_MAP = new HashMap<>();

    private static final Map<Integer, Method> DATA_SET_METHOD_MAP = new HashMap<>();

    private RealtimeEnum(Integer id,  Class<? extends  BasePacket> dataClass) {
        this.id = id;
        this.dataClass = dataClass;
    }

    static {
        for (RealtimeEnum type : EnumSet.allOf(RealtimeEnum.class)) {
            realtimeDataTypeMap.put(type.getId(), type);
            try {
               Method get = BasePacket.class.getMethod(ReflectUtil.GET + type.getDataClass().getSimpleName());
                Method set = RealTimeExtendPacket.class.getMethod(ReflectUtil.SET + type.getDataClass().getSimpleName(), type.getDataClass());
                DATA_GET_METHOD_MAP.put(type.getId(), get);
                DATA_SET_METHOD_MAP.put(type.getId(), set);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    public static RealtimeEnum getRealtimeDataType(Integer id) {

        return realtimeDataTypeMap.containsKey(id) ? realtimeDataTypeMap.get(id) : null;
    }

    public static Method dataGetMethod(Integer id) {

        return DATA_GET_METHOD_MAP.get(id);
    }

    public static Method dataSetMethod(Integer id) {

        return DATA_SET_METHOD_MAP.get(id);
    }

    public static void main(String[] args) throws Exception {
        new RealTimeExtendPacket().build(ByteArrayBuf.warp(new byte[0]));
    }

    public Integer getId() {
        return id;
    }

    public Class<? extends  BasePacket>  getDataClass() {
        return dataClass;
    }
}
