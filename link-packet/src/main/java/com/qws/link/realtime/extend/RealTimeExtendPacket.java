package com.qws.link.realtime.extend;

import com.qws.link.ByteUtils;
import com.qws.link.base.ByteArrayBuf;
import com.qws.link.base.pakcet.BasePacket;
import com.qws.link.base.pakcet.GBPacket;
import com.qws.link.constant.RealtimeEnum;
import com.qws.link.realtime.base.RealTimePacket;
import com.qws.link.realtime.children.OtaNodePacket;

import java.io.Serializable;

/**
 * @author qiwenshuai
 * @code: 0xB1
 * @note 这里基于国标协议实时数据上报扩展了OTA升级节点上报的数据, 在此项目中, 可能更多的使用此项目
 * @since 18-12-28 16:11 by jdk 1.8
 */
public class RealTimeExtendPacket extends RealTimePacket  implements GBPacket, Serializable {

    /**
     * OTA升级节点上报--- 此信息为自定义扩展的数据
     */
    protected OtaNodePacket otaNodePacket;

    @Override
    public void build(ByteArrayBuf byteBuf) throws Exception {
        uploadTime = ByteUtils.dateBytes2Long(byteBuf.readBytes(6));

        while (byteBuf.readableBytes() > 0) {

            // 获取信息类型标志
            int typeId = ByteUtils.toInt(byteBuf.readByte());

            // 获取类型
            RealtimeEnum realtimeEnum = RealtimeEnum.getRealtimeDataType(typeId);

            if (realtimeEnum == null) throw new IllegalArgumentException(String.format("实时数据包解析错误, 未知的数据类型: %d", typeId));

            unwrapped(realtimeEnum, byteBuf);

            /*switch (realtimeDataType) {

                case vehicleBaseData: {

                    VehicleBaseDataPacket packet = (VehicleBaseDataPacket) realtimeDataType.getPacketClass().newInstance();

                    packet.unwrapped(byteBuf);

                    vehicleBaseData = packet.getVehicleBaseData();

                    break;
                }
                case driveMotorData: {

                    DriveMotorDataPacket packet = (DriveMotorDataPacket) realtimeDataType.getPacketClass().newInstance();

                    packet.unwrapped(byteBuf);

                    driveMotorData = packet.getDriveMotorData();

                    break;
                }
                case fuelCellData: {

                    FuelCellDataPacket packet = (FuelCellDataPacket) realtimeDataType.getPacketClass().newInstance();

                    packet.unwrapped(byteBuf);

                    fuelCellData = packet.getFuelCellData();

                    break;
                }
                case engineData: {

                    EngineDataPacket packet = (EngineDataPacket) realtimeDataType.getPacketClass().newInstance();

                    packet.unwrapped(byteBuf);

                    engineData = packet.getEngineData();

                    break;
                }
                case vehicleLocationData: {

                    VehicleLocationDataPacket packet = (VehicleLocationDataPacket) realtimeDataType.getPacketClass().newInstance();

                    packet.unwrapped(byteBuf);

                    vehicleLocationData = packet.getVehicleLocationData();

                    break;
                }
                case extremumData: {

                    ExtremumDataPacket packet = (ExtremumDataPacket) realtimeDataType.getPacketClass().newInstance();

                    packet.unwrapped(byteBuf);

                    extremumData = packet.getExtremumData();

                    break;
                }
                case warningData: {

                    WarningDataPacket packet = (WarningDataPacket) realtimeDataType.getPacketClass().newInstance();

                    packet.unwrapped(byteBuf);

                    warningData = packet.getWarningData();

                    break;
                }
                case energyStorageVoltageData: {

                    EnergyStorageVoltageDataPacket packet = (EnergyStorageVoltageDataPacket) realtimeDataType.getPacketClass().newInstance();

                    packet.unwrapped(byteBuf);

                    energyStorageVoltageData = packet.getEnergyStorageVoltageData();

                    break;
                }
                case energyStorageTempData: {

                    EnergyStorgeTempDataPacket packet = (EnergyStorgeTempDataPacket) realtimeDataType.getPacketClass().newInstance();

                    packet.unwrapped(byteBuf);

                    energyStorgeTempData = packet.getEnergyStorgeTempData();

                    break;
                }
                case gpsData: {

                    GpsPacket packet = (GpsPacket) realtimeDataType.getPacketClass().newInstance();

                    packet.unwrapped(byteBuf);

                    gpsData = packet.getGpsData();

                    break;
                }
                case vehicularCommunicationData: {
                    VehicularCommunicationPacket packet = (VehicularCommunicationPacket) realtimeDataType.getPacketClass().newInstance();
                    packet.unwrapped(byteBuf);
                    vehicularCommunicationData = packet.getVehicularCommunicationData();
                    break;
                }
                *//*case customDriveMotorCodeData: {
                    break;
                }
                case customDriveMotorControllerData: {
                    break;
                }*//*
                case airStatusData: {
                    AirStatusPacket packet = (AirStatusPacket) realtimeDataType.getPacketClass().newInstance();
                    packet.unwrapped(byteBuf);
                    airStatusData = packet.getAirStatusData();
                    break;
                }
                case vehicleStatusData: {
                    VehicleStatusPacket packet = (VehicleStatusPacket) realtimeDataType.getPacketClass().newInstance();
                    packet.unwrapped(byteBuf);
                    vehicleStatusData = packet.getVehicleStatusData();
                    break;
                }
                case otherStatusData:{
                    OtherStatusPacket packet = (OtherStatusPacket) realtimeDataType.getPacketClass().newInstance();
                    packet.unwrapped(byteBuf);
                    otherStatusData = packet.getOtherStatusData();
                    break;
                }
                case tyreStatusData: {
                    TyreStatusPacket packet = (TyreStatusPacket) realtimeDataType.getPacketClass().newInstance();
                    packet.unwrapped(byteBuf);
                    tyreStatusData = packet.getTyreStatusData();
                    break;
                }
                case safeDriveData: {
                    SafeDrivePacket packet = (SafeDrivePacket) realtimeDataType.getPacketClass().newInstance();
                    packet.unwrapped(byteBuf);
                    safeDriveData = packet.getSafeDriveData();
                    break;
                }
            }*/
        }

    }

    @Override
    public byte[] unbuild() {
        return new byte[0];
    }

    @Override
    public Integer length() {
        return null;
    }


    private void unwrapped(RealtimeEnum realtimeEnum, ByteArrayBuf byteBuf) throws Exception {

        BasePacket packet = realtimeEnum.getDataClass().newInstance();

        packet.build(byteBuf);

        Object data = realtimeEnum.dataGetMethod(realtimeEnum.getId()).invoke(packet);

        realtimeEnum.dataSetMethod(realtimeEnum.getId()).invoke(this, data);
    }
}
